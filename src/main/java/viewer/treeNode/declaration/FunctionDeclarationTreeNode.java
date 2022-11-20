package viewer.treeNode.declaration;

import parser.node.declaration.FunctionDeclaration;
import viewer.treeNode.BlockTreeNode;
import viewer.treeNode.expression.ExpressionTreeNode;
import viewer.treeNode.terminal.IdentifierTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class FunctionDeclarationTreeNode extends DefaultMutableTreeNode {
    public FunctionDeclarationTreeNode(FunctionDeclaration node) {
        super(node);

        super.add(new IdentifierTreeNode(node.name));
        super.add(new DeclarationsTreeNode(node.parameters));
        super.add(new BlockTreeNode(node.block));
        super.add(ExpressionTreeNode.get(node.returnExpression));
    }
}
