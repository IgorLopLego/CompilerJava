package encoder;

import parser.node.Block;
import parser.node.Program;
import parser.node.declaration.Declaration;
import parser.node.declaration.Declarations;
import parser.node.declaration.FunctionDeclaration;
import parser.node.declaration.StructDeclaration;
import parser.node.declaration.variable.VariableDeclaration;
import parser.node.expression.*;
import parser.node.statement.ExpressionStatement;
import parser.node.statement.Statement;
import parser.node.statement.Statements;
import parser.node.terminal.*;
import tam.Instruction;
import tam.Machine;
import viewer.Visitor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Optional;

public class Encoder implements Visitor {
    private int nextAdr = Machine.CB;
    private int currentLevel = 0;

    private void emit(int op, int n, int r, int d) {
        if (n > 255) {
            System.out.println("Operand too long");
            n = 255;
        }

        Instruction instr = new Instruction();
        instr.op = op;
        instr.n = n;
        instr.r = r;
        instr.d = d;

        if (nextAdr >= Machine.PB)
            System.out.println("Program too large");
        else
            Machine.code[nextAdr++] = instr;
    }

    public void encode(Program p) {
        p.accept(this, null);
    }

    private void patch(int adr, int d) {
        Machine.code[adr].d = d;
    }

    private int displayRegister(int currentLevel, int entityLevel) {
        if (entityLevel == 0)
            return Machine.SBr;
        else if (currentLevel - entityLevel <= 6)
            return Machine.LBr + currentLevel - entityLevel;
        else {
            System.out.println("Accessing across to many levels");
            return Machine.L6r;
        }
    }

    public void saveTargetProgram(String fileName) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));

            System.out.println("***TAM***");
            for (int i = Machine.CB; i < nextAdr; ++i) {
                System.out.println(Machine.code[i]);
                Machine.code[i].write(out);
            }

            out.close();

            var inStream = new DataInputStream((new FileInputStream(fileName)));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Trouble writing " + fileName);
        }
    }


    @Override
    public Optional<Object> visit(Program program, Object arguments) {
        currentLevel = 0;

        program.block.accept(this, new Address());

        emit(Machine.HALTop, 0, 0, 0);

        return Optional.empty();
    }

    @Override
    public Optional<Object> visit(Block block, Object arguments) {
        int before = nextAdr;
        emit(Machine.JUMPop, 0, Machine.CB, 0);

        int size = (Integer) block.declarations.accept(this, arguments);

        patch(before, nextAdr);

        if (size > 0) {
            emit(Machine.PUSHop, 0, 0, size);
        }

        block.statements.accept(this, null);

        return Optional.of(size);
    }

    @Override
    public Optional<Object> visit(Declarations declarations, Object arguments) {
        int startDisplacement = ((Address) arguments).displacement;

        for (Declaration declaration : declarations.declarations) {
            arguments = declaration.accept(this, arguments);
        }

        Address adr = (Address) arguments;
        int size = adr.displacement - startDisplacement;

        return Optional.ofNullable(Integer.valueOf(size));
    }


    @Override
    public Optional<Object> visit(Statements statements, Object arguments) {
        for (Statement stat : statements.statements)
            stat.accept(this, null);

        return Optional.empty();
    }


    @Override
    public Optional<Object> visit(FunctionDeclaration functionDeclaration, Object arguments) {
        return Optional.empty();
    }


    @Override
    public Optional<Object> visit(StructDeclaration structDeclaration, Object arguments) {
        return Optional.empty();
    }


    @Override
    public Optional<Object> visit(VariableDeclaration variableDeclaration, Object arguments) {
        variableDeclaration.address = (Address) arguments;

        return Optional.of(new Address((Address) arguments, 1));
    }


    @Override
    public Optional<Object> visit(IntegerLiteralExpression integerLiteralExpression, Object arguments) {
        boolean valueNeeded = (Boolean) arguments;

        Integer literal = (Integer) integerLiteralExpression.literal.accept(this, null);

        if (valueNeeded)
            emit(Machine.LOADLop, 1, 0, literal);

        return Optional.empty();
    }


    @Override
    public Optional<Object> visit(IntegerLiteral integerLiteral, Object arguments) {
        return Optional.of(Integer.valueOf(integerLiteral.spelling));
    }


    @Override
    public Optional<Object> visit(StringLiteralExpression stringLiteralExpression, Object arguments) {

        return Optional.empty();
    }


    @Override
    public Optional<Object> visit(StringLiteral stringLiteral, Object arguments) {
        return Optional.of(Integer.valueOf(stringLiteral.spelling));
    }


    @Override
    public Optional<Object> visit(BooleanLiteralExpression booleanLiteralExpression, Object arguments) {
        return Optional.empty();
    }


    @Override
    public Optional<Object> visit(BooleanLiteral booleanLiteral, Object arguments) {
        return Optional.empty();
    }


    @Override
    public Optional<Object> visit(Identifier identifier, Object arguments) {
        return Optional.empty();
    }


    @Override
    public Optional<Object> visit(VariableExpression variableExpression, Object arguments) {
        boolean valueNeeded = ((Boolean) arguments).booleanValue();

        Address address = variableExpression.declaration.address;
        int register = displayRegister(currentLevel, address.level);

        if (valueNeeded)
            emit(Machine.LOADop, 1, register, address.displacement);

        return Optional.of(address);
    }


    @Override
    public Optional<Object> visit(BinaryExpression binaryExpression, Object arguments) {
        return Optional.empty();
    }


    @Override
    public Optional<Object> visit(Operator operator, Object arguments) {
        return Optional.ofNullable(operator.spelling);
    }


    @Override
    public Optional<Object> visit(ExpressionStatement expressionStatement, Object arguments) {
        expressionStatement.expression.accept(this, Boolean.FALSE);

        return Optional.empty();
    }
}