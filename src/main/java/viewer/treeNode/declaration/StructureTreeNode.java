package viewer.treeNode.declaration;

import parser.node.declaration.StructDeclaration;
import viewer.treeNode.terminal.IdentifierTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class StructureTreeNode extends DefaultMutableTreeNode {
    public StructureTreeNode(StructDeclaration node)
    {
        super(node);
        super.add(new IdentifierTreeNode(node.name));
        super.add(new DeclarationsTreeNode(node.parameters));
    }
}
