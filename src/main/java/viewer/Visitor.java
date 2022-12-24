package viewer;

import parser.node.Block;
import parser.node.Program;
import parser.node.declaration.Declarations;
import parser.node.declaration.FunctionDeclaration;
import parser.node.declaration.StructDeclaration;
import parser.node.declaration.variable.VariableDeclaration;
import parser.node.expression.*;
import parser.node.statement.ExpressionStatement;
import parser.node.statement.Statements;
import parser.node.terminal.*;

import java.util.Optional;

public interface Visitor {
    Optional<Object> visit(Program program, Object arguments);

    Optional<Object> visit(Block block, Object arguments);

    Optional<Object> visit(Declarations declarations, Object arguments);

    Optional<Object> visit(Statements statements, Object arguments);

    Optional<Object> visit(FunctionDeclaration functionDeclaration, Object arguments);

    Optional<Object> visit(StructDeclaration structDeclaration, Object arguments);

    Optional<Object> visit(VariableDeclaration variableDeclaration, Object arguments);

    Optional<Object> visit(IntegerLiteralExpression integerLiteralExpression, Object arguments);

    Optional<Object> visit(IntegerLiteral integerLiteral, Object arguments);

    Optional<Object> visit(StringLiteralExpression stringLiteralExpression, Object arguments);

    Optional<Object> visit(StringLiteral stringLiteral, Object arguments);

    Optional<Object> visit(BooleanLiteralExpression booleanLiteralExpression, Object arguments);

    Optional<Object> visit(BooleanLiteral booleanLiteral, Object arguments);

    Optional<Object> visit(Identifier identifier, Object arguments);

    Optional<Object> visit(VariableExpression variableExpression, Object arguments);

    Optional<Object> visit(BinaryExpression binaryExpression, Object arguments);

    Optional<Object> visit(Operator operator, Object arguments);

    Optional<Object> visit(ExpressionStatement expressionStatement, Object arguments);
}
