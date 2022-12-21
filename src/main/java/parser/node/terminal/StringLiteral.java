package parser.node.terminal;

import viewer.Visitor;

public class StringLiteral extends Terminal {
    public StringLiteral(String spelling) {
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
