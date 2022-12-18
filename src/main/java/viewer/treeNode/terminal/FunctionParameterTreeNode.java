package viewer.treeNode.terminal;

import parser.node.terminal.DeclarationParameter;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Vector;

public class FunctionParameterTreeNode extends DefaultMutableTreeNode {
    public FunctionParameterTreeNode(Vector<DeclarationParameter> declarationParameters ){
        super(declarationParameters);
    }
}
