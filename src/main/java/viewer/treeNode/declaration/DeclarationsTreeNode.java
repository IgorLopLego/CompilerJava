package viewer.treeNode.declaration;

import parser.node.declaration.Declaration;
import parser.node.declaration.Declarations;

import javax.swing.tree.DefaultMutableTreeNode;

public class DeclarationsTreeNode extends DefaultMutableTreeNode {
    public DeclarationsTreeNode(Declarations node) {
        super(node);

        for (Declaration declaration: node.declarations) {
            super.add(DeclarationTreeNode.get(declaration));
        }
    }
}
