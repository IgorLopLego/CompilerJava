package parser.node.declaration;

import parser.node.Node;

import java.util.Vector;

public class Declarations extends Node {
    public Vector<Declaration> declarations = new Vector<>();

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
