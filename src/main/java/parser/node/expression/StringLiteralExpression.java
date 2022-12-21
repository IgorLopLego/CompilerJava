package parser.node.expression;

import parser.node.terminal.StringLiteral;
import viewer.Visitor;

public class StringLiteralExpression extends Expression {
    public StringLiteral literal;

    public StringLiteralExpression(StringLiteral literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public Object accept(Visitor visitor, Object arguments) {
        return null;
    }
}
