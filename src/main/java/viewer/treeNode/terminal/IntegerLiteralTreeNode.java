package viewer.treeNode.terminal;

import parser.node.terminal.IntegerLiteral;

import javax.swing.tree.DefaultMutableTreeNode;

public class IntegerLiteralTreeNode extends DefaultMutableTreeNode {
    public IntegerLiteralTreeNode(IntegerLiteral node) {
        super(node);
    }
}
