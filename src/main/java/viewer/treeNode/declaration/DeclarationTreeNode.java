package viewer.treeNode.declaration;

import parser.node.declaration.Declaration;
import parser.node.declaration.DeclarationFunctionAssign;
import parser.node.declaration.DeclarationVariableAssign;

import javax.swing.tree.DefaultMutableTreeNode;

public class DeclarationTreeNode {
    public static DefaultMutableTreeNode get(Declaration node) {
        if (node instanceof DeclarationVariableAssign declaration) {
            return new VariableDeclarationTreeNode(declaration);
        }

        if (node instanceof DeclarationFunctionAssign declaration) {
            return new FunctionDeclarationTreeNode(declaration);
        }

        throw new RuntimeException("Unexpected node: " + node);
    }
}
