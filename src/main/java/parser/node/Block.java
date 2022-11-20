package parser.node;

import parser.node.declaration.Declarations;
import parser.node.statement.Statements;

public class Block extends Node {
    public Declarations declarations;
    public Statements statements;

    public Block(Declarations declarations, Statements statements) {
        this.declarations = declarations;
        this.statements = statements;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
