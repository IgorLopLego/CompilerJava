package parserRefactor.nodes;

import parserRefactor.nodes.declaration.Declarations;
import parserRefactor.nodes.statement.Statements;

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
