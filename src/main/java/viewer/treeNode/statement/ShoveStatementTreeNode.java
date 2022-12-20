package viewer.treeNode.statement;

import parser.node.statement.ShoveStatement;
import viewer.treeNode.expression.ExpressionTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class ShoveStatementTreeNode extends DefaultMutableTreeNode {
    public ShoveStatementTreeNode(ShoveStatement node) {
        super(node);
    }
}
