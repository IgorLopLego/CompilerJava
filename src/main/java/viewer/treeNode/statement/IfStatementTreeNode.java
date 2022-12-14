package viewer.treeNode.statement;

import parser.node.statement.IfStatement;
import viewer.treeNode.BlockTreeNode;
import viewer.treeNode.expression.ExpressionTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class IfStatementTreeNode extends DefaultMutableTreeNode {
    public IfStatementTreeNode(IfStatement node) {
        super(node);

        super.add(ExpressionTreeNode.get(node.expression));
        super.add(new BlockTreeNode(node.ifSection));
        super.add(new BlockTreeNode(node.elseSection));
    }
}
