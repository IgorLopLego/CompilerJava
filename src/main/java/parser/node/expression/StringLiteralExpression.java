package parser.node.expression;

import parser.node.terminal.StringLiteral;

public class StringLiteralExpression extends Expression {
    public StringLiteral literal;

    public StringLiteralExpression(StringLiteral literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
