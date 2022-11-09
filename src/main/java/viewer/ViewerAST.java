package viewer;

import parserRefactor.nodes.Block;
import parserRefactor.nodes.Node;
import parserRefactor.nodes.Program;
import parserRefactor.nodes.declaration.Declarations;
import parserRefactor.nodes.declaration.FunctionDeclaration;
import parserRefactor.nodes.declaration.VariableDeclaration;
import parserRefactor.nodes.expression.*;
import parserRefactor.nodes.statement.ScreamStatement;
import parserRefactor.nodes.statement.Statements;
import parserRefactor.nodes.terminal.*;
import viewer.treeNode.BlockTreeNode;
import viewer.treeNode.ProgramTreeNode;
import viewer.treeNode.declaration.DeclarationsTreeNode;
import viewer.treeNode.declaration.FunctionDeclarationTreeNode;
import viewer.treeNode.declaration.VariableDeclarationTreeNode;
import viewer.treeNode.expression.*;
import viewer.treeNode.statement.ScreamStatementTreeNode;
import viewer.treeNode.statement.StatementsTreeNode;
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
            return new DefaultMutableTreeNode("** null **");
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

        throw new RuntimeException("Unknown tree node type: '" + node + "'.");
    }
}
