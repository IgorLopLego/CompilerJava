package viewer.treeNode.statement;

import parserRefactor.nodes.statement.ScreamStatement;
import viewer.treeNode.expression.ExpressionTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class ScreamStatementTreeNode extends DefaultMutableTreeNode {
    public ScreamStatementTreeNode(ScreamStatement node) {
        super(node);

        super.add(ExpressionTreeNode.get(node.expression));
    }
}
