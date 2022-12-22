package checker;

import exception.SemanticError;
import parser.node.Block;
import parser.node.Program;
import parser.node.declaration.Declarations;
import parser.node.declaration.FunctionDeclaration;
import parser.node.declaration.StructDeclaration;
import parser.node.declaration.VariableDeclaration;
import parser.node.expression.BinaryExpression;
import parser.node.expression.IntegerLiteralExpression;
import parser.node.expression.VariableExpression;
import parser.node.statement.ExpressionStatement;
import parser.node.statement.Statements;
import parser.node.terminal.Identifier;
import parser.node.terminal.IntegerLiteral;
import parser.node.terminal.Operator;
import viewer.Visitor;

import java.util.Optional;

import static scanner.token.TokenKind.ASSIGN;

public class CheckerVisitor implements Visitor {
    private final IdentificationTable identificationTable;

    public CheckerVisitor() {
        identificationTable = new IdentificationTable();
    }

    private String getHashCode(Object object) {
        return object.getClass().getName()+"@"+Integer.toHexString(object.hashCode());
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
        for (var statement : statements.statements) {
            statement.accept(this, null);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Object> visit(FunctionDeclaration functionDeclaration, Object arguments) {
        var identifierSpelling = (Optional<String>) functionDeclaration.name.accept(this, null);
        var id = identifierSpelling.get();

        identificationTable.enter(id, functionDeclaration);
        identificationTable.openScope();

        if (functionDeclaration.parameters.declarations.isEmpty()) {
            functionDeclaration.parameters.accept(this, null);
        }

        functionDeclaration.block.accept(this, null);

        if (functionDeclaration.returnExpression.isPresent()) {
            functionDeclaration.returnExpression.get().accept(this, null);
        }

        identificationTable.popScope();

        return Optional.empty();
    }

    @Override
    public Optional<Object> visit(StructDeclaration structDeclaration, Object arguments) {
        return Optional.empty();
    }

    @Override
    public Optional<Object> visit(VariableDeclaration variableDeclaration, Object arguments) {
        var identifierSpelling = (Optional<String>) variableDeclaration.id.accept(this, null);
        var id = identifierSpelling.get();

        identificationTable.enter(id, variableDeclaration);

        return Optional.empty();
    }

    @Override
    public Optional<Object> visit(IntegerLiteralExpression integerLiteralExpression, Object arguments) {
        integerLiteralExpression.literal.accept(this, null);

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

    @Override
    public Optional<Object> visit(VariableExpression variableExpression, Object arguments) {
        var identifierSpelling = (Optional<String>) variableExpression.name.accept(this, null);
        var id = identifierSpelling.get();

        var declaration = identificationTable.retrieve(id);

        if (declaration.isEmpty()) {
            throw new SemanticError("Id of '" + id + "' was not declared.");
        }

        if (!(declaration.get() instanceof VariableDeclaration)) {
            throw new SemanticError("Id of '" + id + "' is not a variable.");
        }

        variableExpression.declaration = (VariableDeclaration) declaration.get();

        return Optional.of(new Type(false));
    }

    @Override
    public Optional<Object> visit(BinaryExpression binaryExpression, Object arguments) {
        var leftType = (Optional<Type>) binaryExpression.leftOperand.accept(this, null);
        var rightType = (Optional<Type>) binaryExpression.rightOperand.accept(this, null);

        var operator = (Optional<Operator>) binaryExpression.operator.accept(this, null);

        if (operator.equals(ASSIGN) && leftType.get().isReadOnly) {
            throw new SemanticError("Left-hand side of assignment (#) must be a variable.");
        }

        return Optional.of(new Type(true));
    }

    @Override
    public Optional<Object> visit(Operator operator, Object arguments) {
        return Optional.of(operator.spelling);
    }

    @Override
    public Optional<Object> visit(ExpressionStatement expressionStatement, Object arguments) {
        expressionStatement.expression.accept(this, null);

        return Optional.empty();
    }
}