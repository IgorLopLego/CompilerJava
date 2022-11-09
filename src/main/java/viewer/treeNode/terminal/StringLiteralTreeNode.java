package viewer.treeNode.terminal;

import parserRefactor.nodes.terminal.StringLiteral;

import javax.swing.tree.DefaultMutableTreeNode;

public class StringLiteralTreeNode extends DefaultMutableTreeNode {
    public StringLiteralTreeNode(StringLiteral node) {
        super(node);
    }
}
