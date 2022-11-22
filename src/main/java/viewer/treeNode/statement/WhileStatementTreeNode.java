package viewer.treeNode.statement;

import parser.node.statement.WhileStatement;
import viewer.treeNode.BlockTreeNode;
import viewer.treeNode.expression.ExpressionTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class WhileStatementTreeNode extends DefaultMutableTreeNode {
    public WhileStatementTreeNode(WhileStatement node) {
        super(node);

        super.add(ExpressionTreeNode.get(node.expression));
        super.add(new BlockTreeNode(node.block));
    }
}
