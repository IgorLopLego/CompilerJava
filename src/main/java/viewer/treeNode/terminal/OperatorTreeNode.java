package viewer.treeNode.terminal;

import parserRefactor.nodes.terminal.Operator;

import javax.swing.tree.DefaultMutableTreeNode;

public class OperatorTreeNode extends DefaultMutableTreeNode {
    public OperatorTreeNode(Operator node) {
        super(node);
    }
}
