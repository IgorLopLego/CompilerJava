package viewer.treeNode.statement;

import parser.node.statement.IfStatement;
import parser.node.statement.ScreamStatement;
import parser.node.statement.Statement;
import parser.node.statement.WhileStatement;

import javax.swing.tree.DefaultMutableTreeNode;

public class StatementTreeNode {
    public static DefaultMutableTreeNode get(Statement node) {
        if (node instanceof ScreamStatement statement) {
            return new ScreamStatementTreeNode(statement);
        }

        if (node instanceof IfStatement statement) {
            return new IfStatementTreeNode(statement);
        }

        if (node instanceof WhileStatement statement) {
            return new WhileStatementTreeNode(statement);
        }

        throw new RuntimeException("Unexpected node: " + node);
    }
}
