package viewer.treeNode.statement;

import parser.node.statement.*;

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

        if (node instanceof ExpressionStatement statement) {
            return new ExpressionStatementTreeNode(statement);
        }

        if (node instanceof ShoveStatement statement)
        {
            return new ShoveStatementTreeNode(statement);
        }

        throw new RuntimeException("Unexpected node: " + node);
    }
}
