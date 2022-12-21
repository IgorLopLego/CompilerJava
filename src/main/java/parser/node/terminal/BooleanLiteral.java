package parser.node.terminal;

import viewer.Visitor;

public class BooleanLiteral extends Terminal {
    public BooleanLiteral(String spelling) {
        this.spelling = spelling;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + spelling;
    }

    @Override
    public Object accept(Visitor visitor, Object arguments) {
        return null;
    }
}
