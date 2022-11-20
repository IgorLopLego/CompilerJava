package viewer.treeNode.terminal;

import parser.node.terminal.Operator;

import javax.swing.tree.DefaultMutableTreeNode;

public class OperatorTreeNode extends DefaultMutableTreeNode {
    public OperatorTreeNode(Operator node) {
        super(node);
    }
}
