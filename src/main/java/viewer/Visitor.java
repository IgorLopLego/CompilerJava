package viewer;

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

    Optional<Object> visit(Identifier identifier, Object arguments);
}
