package parser.node;

import parser.interfaces.Visitable;
import parser.node.declaration.Declarations;
import parser.node.statement.Statements;
import viewer.Visitor;

public class Block extends Node implements Visitable {
    public Declarations declarations;
    public Statements statements;

    public Block(Declarations declarations, Statements statements) {
        this.declarations = declarations;
        this.statements = statements;
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
