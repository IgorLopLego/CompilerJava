package checker;

import parser.node.Block;
import parser.node.Program;
import parser.node.declaration.Declarations;
import parser.node.declaration.FunctionDeclaration;
import parser.node.declaration.StructDeclaration;
import parser.node.declaration.VariableDeclaration;
import parser.node.expression.IntegerLiteralExpression;
import parser.node.statement.Statements;
import parser.node.terminal.Identifier;
import parser.node.terminal.IntegerLiteral;
import viewer.Visitor;

import java.util.Optional;

public class CheckerVisitor implements Visitor {
    private final IdentificationTable identificationTable;

    public CheckerVisitor() {
        identificationTable = new IdentificationTable();
    }

    public void check(Program program) {
        program.accept(this, null);
    }


    @Override
    public Optional<Object> visit(Program program, Object arguments) {
        identificationTable.openScope();

        program.block.accept(this, null);

        identificationTable.popScope();

        return Optional.empty();
    }

    @Override
    public Optional<Object> visit(Block block, Object arguments) {
        block.declarations.accept(this, null);
        block.statements.accept(this, null);

        return Optional.empty();
    }

    @Override
    public Optional<Object> visit(Declarations declarations, Object arguments) {
        for (var declaration : declarations.declarations) {
            declaration.accept(this, null);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Object> visit(Statements statements, Object arguments) {
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
        var declaration = (VariableDeclaration) variableDeclaration.id.accept(this, null);
        var id = declaration.getHashCode();

        identificationTable.enter(id, variableDeclaration);

        return Optional.empty();
    }

    @Override
    public Optional<Object> visit(IntegerLiteralExpression integerLiteralExpression, Object arguments) {
        integerLiteralExpression.accept(this, null);

        return Optional.of(new Type(true));
    }

    @Override
    public Optional<Object> visit(IntegerLiteral integerLiteral, Object arguments) {
        return Optional.empty();
    }

    @Override
    public Optional<Object> visit(Identifier identifier, Object arguments) {
        return Optional.of(identifier.spelling);
    }
}
