package viewer;

import parser.node.Block;
import parser.node.Node;
import parser.node.Program;
import parser.node.declaration.Declarations;
import parser.node.declaration.FunctionDeclaration;
import parser.node.declaration.VariableDeclaration;
import parser.node.expression.*;
import parser.node.statement.IfStatement;
import parser.node.statement.ScreamStatement;
import parser.node.statement.Statements;
import parser.node.statement.WhileStatement;
import parser.node.terminal.*;
import viewer.treeNode.BlockTreeNode;
import viewer.treeNode.ProgramTreeNode;
import viewer.treeNode.declaration.DeclarationsTreeNode;
import viewer.treeNode.declaration.FunctionDeclarationTreeNode;
import viewer.treeNode.declaration.VariableDeclarationTreeNode;
import viewer.treeNode.expression.*;
import viewer.treeNode.statement.IfStatementTreeNode;
import viewer.treeNode.statement.ScreamStatementTreeNode;
import viewer.treeNode.statement.StatementsTreeNode;
import viewer.treeNode.statement.WhileStatementTreeNode;
import viewer.treeNode.terminal.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class ViewerAST extends JFrame {
    public ViewerAST(Node node) {
        super("Abstract Syntax Tree");

        var root = createTree(node);
        var tree = new JTree(root);

        var renderer = new DefaultTreeCellRenderer();
        renderer.setFont(new Font(
                tree.getFont().getName(),
                Font.PLAIN, 24
        ));

        tree.setCellRenderer(renderer);
        tree.setRowHeight(30);

        add(new JScrollPane(tree));

        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private DefaultMutableTreeNode createTree(Node node) {
        if (node == null) {
            return new DefaultMutableTreeNode("Null node");
        }

        if (node instanceof BinaryExpression binaryExpression) {
            return new BinaryExpressionTreeNode(binaryExpression);
        }

        if (node instanceof Block block) {
            return new BlockTreeNode(block);
        }

        if (node instanceof BooleanLiteral booleanLiteral) {
            return new BooleanLiteralTreeNode(booleanLiteral);
        }

        if (node instanceof BooleanLiteralExpression booleanLiteralExpression) {
            return new BooleanLiteralExpressionTreeNode(booleanLiteralExpression);
        }

        if (node instanceof Declarations declarations) {
            return new DeclarationsTreeNode(declarations);
        }

        if (node instanceof FunctionDeclaration functionDeclaration) {
            return new FunctionDeclarationTreeNode(functionDeclaration);
        }

        if (node instanceof Identifier identifier) {
            return new IdentifierTreeNode(identifier);
        }

        if (node instanceof IfStatement ifStatement) {
            return new IfStatementTreeNode(ifStatement);
        }

        if (node instanceof IntegerLiteral integerLiteral) {
            return new IntegerLiteralTreeNode(integerLiteral);
        }

        if (node instanceof IntegerLiteralExpression integerLiteralExpression) {
            return new IntegerLiteralExpressionTreeNode(integerLiteralExpression);
        }

        if (node instanceof Operator operator) {
            return new OperatorTreeNode(operator);
        }

        if (node instanceof Program program) {
            return new ProgramTreeNode(program);
        }

        if (node instanceof ScreamStatement screamStatement) {
            return new ScreamStatementTreeNode(screamStatement);
        }

        if (node instanceof Statements statements) {
            return new StatementsTreeNode(statements);
        }

        if (node instanceof StringLiteral stringLiteral) {
            return new StringLiteralTreeNode(stringLiteral);
        }

        if (node instanceof StringLiteralExpression stringLiteralExpression) {
            return new StringLiteralExpressionTreeNode(stringLiteralExpression);
        }

        if (node instanceof VariableDeclaration variableDeclaration) {
            return new VariableDeclarationTreeNode(variableDeclaration);
        }

        if (node instanceof VariableExpression variableDeclaration) {
            return new VariableExpressionTreeNode(variableDeclaration);
        }

        if (node instanceof WhileStatement whileStatement) {
            return new WhileStatementTreeNode(whileStatement);
        }

        throw new RuntimeException("Unknown tree node type: '" + node + "'.");
    }
}
