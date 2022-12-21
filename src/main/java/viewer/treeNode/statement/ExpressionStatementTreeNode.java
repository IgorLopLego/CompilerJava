package viewer.treeNode.statement;

import parser.node.statement.ExpressionStatement;
import viewer.treeNode.expression.ExpressionTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class ExpressionStatementTreeNode extends DefaultMutableTreeNode {
    public ExpressionStatementTreeNode(ExpressionStatement node) {
        super(node);

        super.add(ExpressionTreeNode.get(node.expression));
    }
}
