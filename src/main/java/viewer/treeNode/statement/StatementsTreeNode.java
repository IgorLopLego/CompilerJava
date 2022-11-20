package viewer.treeNode.statement;

import parser.node.statement.Statement;
import parser.node.statement.Statements;

import javax.swing.tree.DefaultMutableTreeNode;

public class StatementsTreeNode extends DefaultMutableTreeNode {
    public StatementsTreeNode(Statements node) {
        super(node);

        for (Statement statement: node.statements) {
            super.add(StatementTreeNode.get(statement));
        }
    }
}
