package viewer.treeNode.statement;

import parserRefactor.nodes.statement.Statement;
import parserRefactor.nodes.statement.Statements;

import javax.swing.tree.DefaultMutableTreeNode;

public class StatementsTreeNode extends DefaultMutableTreeNode {
    public StatementsTreeNode(Statements node) {
        super(node);

        for (Statement statement: node.statements) {
            super.add(StatementTreeNode.get(statement));
        }
    }
}
