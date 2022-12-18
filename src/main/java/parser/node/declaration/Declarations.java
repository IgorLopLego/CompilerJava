package parser.node.declaration;

import parser.node.Node;

import java.util.Vector;

public class Declarations extends Node {
    public Vector<DeclarationVariableAssign> declarationVariableAssignList = new Vector<>();
    public Vector<DeclarationFunctionAssign> declarationFunctionAssignList = new Vector<>();


    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
