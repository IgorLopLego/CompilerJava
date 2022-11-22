package viewer.treeNode;

import parser.node.Block;
import viewer.treeNode.declaration.DeclarationsTreeNode;
import viewer.treeNode.statement.StatementsTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class BlockTreeNode extends DefaultMutableTreeNode {
    public BlockTreeNode(Block node) {
        super(node != null ? node : Block.class.getSimpleName());

        if (node != null) {
            super.add(new DeclarationsTreeNode(node.declarations));
            super.add(new StatementsTreeNode(node.statements));
        }
    }
}
