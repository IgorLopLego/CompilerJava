package viewer.treeNode.expression;

import parserRefactor.nodes.expression.IntegerLiteralExpression;
import viewer.treeNode.terminal.IntegerLiteralTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class IntegerLiteralExpressionTreeNode extends DefaultMutableTreeNode {
    public IntegerLiteralExpressionTreeNode(IntegerLiteralExpression node) {
        super(node);

        super.add(new IntegerLiteralTreeNode(node.literal));
    }
}
