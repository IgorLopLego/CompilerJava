package viewer.treeNode;

import parserRefactor.nodes.Program;

import javax.swing.tree.DefaultMutableTreeNode;

public class ProgramTreeNode extends DefaultMutableTreeNode {
    public ProgramTreeNode(Program node) {
        super(node);

        super.add(new BlockTreeNode(node.block));
    }
}
