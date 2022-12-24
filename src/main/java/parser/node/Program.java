package parser.node;

import parser.interfaces.Visitable;
import viewer.Visitor;

public class Program extends Node implements Visitable {
    public Block block;

    public Program(Block block) {
        this.block = block;
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
