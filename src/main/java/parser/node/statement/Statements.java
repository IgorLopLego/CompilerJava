package parser.node.statement;

import parser.interfaces.Visitable;
import parser.node.Node;
import viewer.Visitor;

import java.util.Vector;

public class Statements extends Node implements Visitable {
    public Vector<Statement> statements = new Vector<>();

    @Override
    public Object accept(Visitor visitor, Object arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
