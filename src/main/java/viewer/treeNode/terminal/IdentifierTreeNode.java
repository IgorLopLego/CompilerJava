package viewer.treeNode.terminal;

import parserRefactor.nodes.terminal.Identifier;

import javax.swing.tree.DefaultMutableTreeNode;

public class IdentifierTreeNode extends DefaultMutableTreeNode {
    public IdentifierTreeNode(Identifier node) {
        super(node);
    }
}
