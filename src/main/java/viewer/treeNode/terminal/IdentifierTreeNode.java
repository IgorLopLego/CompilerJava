package viewer.treeNode.terminal;

import parser.node.terminal.Identifier;

import javax.swing.tree.DefaultMutableTreeNode;

public class IdentifierTreeNode extends DefaultMutableTreeNode {
    public IdentifierTreeNode(Identifier node) {
        super(node);
    }
}
