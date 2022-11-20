package viewer.treeNode.terminal;

import parser.node.terminal.StringLiteral;

import javax.swing.tree.DefaultMutableTreeNode;

public class StringLiteralTreeNode extends DefaultMutableTreeNode {
    public StringLiteralTreeNode(StringLiteral node) {
        super(node);
    }
}
