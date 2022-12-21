package parser.node.terminal;

import parser.interfaces.HashCode;
import parser.interfaces.Visitable;
import viewer.Visitor;

public class Identifier extends Terminal implements HashCode, Visitable {
    public Identifier(String spelling) {
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

    @Override
    public String getHashCode() {
        return super.toString();
    }
}
