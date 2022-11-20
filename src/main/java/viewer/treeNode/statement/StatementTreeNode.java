package viewer.treeNode.statement;

import parser.node.statement.ScreamStatement;
import parser.node.statement.Statement;

import javax.swing.tree.DefaultMutableTreeNode;

public class StatementTreeNode {
    public static DefaultMutableTreeNode get(Statement node) {
        if (node instanceof ScreamStatement statement) {
            return new ScreamStatementTreeNode(statement);
        }

        throw new RuntimeException("Unexpected node: " + node);
    }
}
