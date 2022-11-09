package viewer.treeNode.terminal;

import parserRefactor.nodes.terminal.IntegerLiteral;

import javax.swing.tree.DefaultMutableTreeNode;

public class IntegerLiteralTreeNode extends DefaultMutableTreeNode {
    public IntegerLiteralTreeNode(IntegerLiteral node) {
        super(node);
    }
}
