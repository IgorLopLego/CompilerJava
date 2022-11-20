package parser.node.statement;

import parser.node.Node;

import java.util.Vector;

public class Statements extends Node {
    public Vector<Statement> statements = new Vector<>();

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
