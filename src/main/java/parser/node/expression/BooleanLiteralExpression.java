package parser.node.expression;

import parser.node.terminal.BooleanLiteral;

public class BooleanLiteralExpression extends Expression {
    public BooleanLiteral literal;

    public BooleanLiteralExpression(BooleanLiteral literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
