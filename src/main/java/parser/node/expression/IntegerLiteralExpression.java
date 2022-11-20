package parser.node.expression;

import parser.node.terminal.IntegerLiteral;

public class IntegerLiteralExpression extends Expression {
    public IntegerLiteral literal;

    public IntegerLiteralExpression(IntegerLiteral literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
