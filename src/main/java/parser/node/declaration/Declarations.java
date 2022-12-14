package parser.node.declaration;

import parser.node.Node;
import parser.interfaces.Visitable;
import viewer.Visitor;

import java.util.Vector;

public class Declarations extends Node implements Visitable {
    public Vector<Declaration> declarations = new Vector<>();

    @Override
    public Object accept(Visitor visitor, Object arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
