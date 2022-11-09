package viewer.treeNode.declaration;

import parserRefactor.nodes.declaration.Declaration;
import parserRefactor.nodes.declaration.FunctionDeclaration;
import parserRefactor.nodes.declaration.VariableDeclaration;

import javax.swing.tree.DefaultMutableTreeNode;

public class DeclarationTreeNode {
    public static DefaultMutableTreeNode get(Declaration node) {
        if (node instanceof VariableDeclaration declaration) {
            return new VariableDeclarationTreeNode(declaration);
        }

        if (node instanceof FunctionDeclaration declaration) {
            return new FunctionDeclarationTreeNode(declaration);
        }

        throw new RuntimeException("Unexpected node: " + node);
    }
}
