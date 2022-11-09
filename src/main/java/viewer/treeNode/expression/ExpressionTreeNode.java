package viewer.treeNode.expression;

import parserRefactor.nodes.expression.*;

import javax.swing.tree.DefaultMutableTreeNode;

public class ExpressionTreeNode {
    public static DefaultMutableTreeNode get(Expression node) {
        if (node instanceof BinaryExpression expression) {
            return new BinaryExpressionTreeNode(expression);
        }

        if (node instanceof BooleanLiteralExpression expression) {
            return new BooleanLiteralExpressionTreeNode(expression);
        }

        if (node instanceof IntegerLiteralExpression expression) {
            return new IntegerLiteralExpressionTreeNode(expression);
        }

        if (node instanceof StringLiteralExpression expression) {
            return new StringLiteralExpressionTreeNode(expression);
        }

        throw new RuntimeException("Unexpected node: " + node);
    }
}
