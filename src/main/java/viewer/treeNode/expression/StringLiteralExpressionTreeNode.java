package viewer.treeNode.expression;

import parser.node.expression.StringLiteralExpression;
import viewer.treeNode.terminal.StringLiteralTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class StringLiteralExpressionTreeNode extends DefaultMutableTreeNode {
    public StringLiteralExpressionTreeNode(StringLiteralExpression node) {
        super(node);

        super.add(new StringLiteralTreeNode(node.literal));
    }
}
