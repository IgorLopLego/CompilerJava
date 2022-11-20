package viewer.treeNode.terminal;

import parser.node.terminal.BooleanLiteral;

import javax.swing.tree.DefaultMutableTreeNode;

public class BooleanLiteralTreeNode extends DefaultMutableTreeNode {
    public BooleanLiteralTreeNode(BooleanLiteral node) {
        super(node);
    }
}
