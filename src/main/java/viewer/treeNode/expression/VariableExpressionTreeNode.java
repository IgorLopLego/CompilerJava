package viewer.treeNode.expression;

import parserRefactor.nodes.expression.VariableExpression;
import viewer.treeNode.terminal.IdentifierTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class VariableExpressionTreeNode extends DefaultMutableTreeNode {
    public VariableExpressionTreeNode(VariableExpression node) {
        super(node);

        super.add(new IdentifierTreeNode(node.name));
    }
}
