package parserRefactor.nodes.expression;

import parserRefactor.nodes.terminal.StringLiteral;

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
