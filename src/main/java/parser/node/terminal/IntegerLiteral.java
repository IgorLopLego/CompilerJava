package parser.node.terminal;

import viewer.Visitor;

public class IntegerLiteral extends Terminal {
    public IntegerLiteral(String spelling) {
        this.spelling = spelling;
    }

    @Override
    public Object accept(Visitor visitor, Object arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + spelling;
    }
}
