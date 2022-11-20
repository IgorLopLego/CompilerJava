package viewer.treeNode.expression;

import parser.node.expression.BinaryExpression;
import viewer.treeNode.terminal.OperatorTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class BinaryExpressionTreeNode extends DefaultMutableTreeNode {
    public BinaryExpressionTreeNode(BinaryExpression node) {
        super(node);

        super.add(ExpressionTreeNode.get(node.leftOperand));
        super.add(new OperatorTreeNode(node.operator));
        super.add(ExpressionTreeNode.get(node.rightOperand));
    }
}
