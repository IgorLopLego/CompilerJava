package parser.node.terminal;

import viewer.Visitor;

public class Operator extends Terminal {
    public Operator(String spelling) {
        this.spelling = spelling;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + spelling;
    }

    @Override
    public Object accept(Visitor visitor, Object arguments) {
        return visitor.visit(this, null);
    }
}
