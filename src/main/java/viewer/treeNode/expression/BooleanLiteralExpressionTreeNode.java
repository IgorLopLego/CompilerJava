package viewer.treeNode.expression;

import parser.node.expression.BooleanLiteralExpression;
import viewer.treeNode.terminal.BooleanLiteralTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class BooleanLiteralExpressionTreeNode extends DefaultMutableTreeNode {
    public BooleanLiteralExpressionTreeNode(BooleanLiteralExpression node) {
        super(node);

        super.add(new BooleanLiteralTreeNode(node.literal));
    }
}
