package viewer.treeNode.declaration;

import parser.node.declaration.DeclarationVariableAssign;
import viewer.treeNode.expression.ExpressionTreeNode;
import viewer.treeNode.terminal.IdentifierTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class VariableDeclarationTreeNode extends DefaultMutableTreeNode {
    public VariableDeclarationTreeNode(DeclarationVariableAssign node) {
        super(node);

        super.add(new IdentifierTreeNode(node.id));

        node.expression.ifPresent(expression ->
                super.add(ExpressionTreeNode.get(expression))
        );
    }
}
