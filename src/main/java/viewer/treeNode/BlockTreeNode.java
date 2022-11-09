package viewer.treeNode;

import parserRefactor.nodes.Block;
import viewer.treeNode.declaration.DeclarationsTreeNode;
import viewer.treeNode.statement.StatementsTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class BlockTreeNode extends DefaultMutableTreeNode {
    public BlockTreeNode(Block node) {
        super(node);

        super.add(new DeclarationsTreeNode(node.declarations));
        super.add(new StatementsTreeNode(node.statements));
    }
}
