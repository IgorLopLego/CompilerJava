package viewer.treeNode.declaration;

import parser.node.declaration.Declaration;
import parser.node.declaration.Declarations;

import javax.swing.tree.DefaultMutableTreeNode;

public class DeclarationsTreeNode extends DefaultMutableTreeNode {
    public DeclarationsTreeNode(Declarations node) {
        super(node);
        for(Declaration assignVariableDeclaration: node.declarationVariableAssignList)
        {
            super.add(DeclarationTreeNode.get(assignVariableDeclaration));
        }
        for (Declaration functionDeclaration: node.declarationFunctionAssignList)
        {
            super.add(DeclarationTreeNode.get(functionDeclaration));
        }
    }
}
