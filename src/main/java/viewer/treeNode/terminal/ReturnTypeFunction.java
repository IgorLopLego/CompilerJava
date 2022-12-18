package viewer.treeNode.terminal;

import parser.node.terminal.DeclarationParameter;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Vector;

public class ReturnTypeFunction extends DefaultMutableTreeNode {
    public ReturnTypeFunction(String returnType ){
        super("Return type "+ returnType);
    }
}
