package parser.node.expression;

import parser.interfaces.Visitable;
import parser.node.terminal.IntegerLiteral;
import viewer.Visitor;

public class IntegerLiteralExpression extends Expression implements Visitable {
    public IntegerLiteral literal;

    public IntegerLiteralExpression(IntegerLiteral literal) {
        this.literal = literal;
    }

    @Override
    public Object accept(Visitor visitor, Object arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
