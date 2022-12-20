package viewer.treeNode.declaration;

import parser.node.declaration.Declaration;
import parser.node.declaration.FunctionDeclaration;
import parser.node.declaration.StructDeclaration;
import parser.node.declaration.VariableDeclaration;

import javax.swing.tree.DefaultMutableTreeNode;

public class DeclarationTreeNode {
    public static DefaultMutableTreeNode get(Declaration node) {
        if (node instanceof VariableDeclaration declaration) {
            return new VariableDeclarationTreeNode(declaration);
        }

        if (node instanceof FunctionDeclaration declaration) {
            return new FunctionDeclarationTreeNode(declaration);
        }

        if(node instanceof StructDeclaration structDeclaration)
        {
            return new StructureTreeNode(structDeclaration);
        }

        throw new RuntimeException("Unexpected node: " + node);
    }
}
