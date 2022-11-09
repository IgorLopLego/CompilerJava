package viewer;

import parserRefactor.nodes.Block;
import parserRefactor.nodes.Node;
import parserRefactor.nodes.Program;
import parserRefactor.nodes.declaration.Declaration;
import parserRefactor.nodes.declaration.Declarations;
import parserRefactor.nodes.declaration.VariableDeclaration;
import parserRefactor.nodes.expression.BinaryExpression;
import parserRefactor.nodes.expression.BooleanLiteralExpression;
import parserRefactor.nodes.expression.IntegerLiteralExpression;
import parserRefactor.nodes.expression.StringLiteralExpression;
import parserRefactor.nodes.statement.ScreamStatement;
import parserRefactor.nodes.statement.Statement;
import parserRefactor.nodes.statement.Statements;
import parserRefactor.nodes.terminal.*;

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
        var treeNode = new DefaultMutableTreeNode("** Unknown node **");

        if (node == null)
            treeNode.setUserObject("** NULL **");
        else if (node instanceof Program) {
            treeNode.setUserObject("Program");
            treeNode.add(createTree(((Program) node).block));
        } else if (node instanceof Block) {
            treeNode.setUserObject("Block");
            treeNode.add(createTree(((Block) node).declarations));
            treeNode.add(createTree(((Block) node).statements));
        } else if (node instanceof Declarations) {
            treeNode.setUserObject("Declarations");

            for (Declaration declaration: ((Declarations) node).declarations) {
                treeNode.add(createTree(declaration));
            }
        } else if (node instanceof Statements) {
            treeNode.setUserObject("Statements");

            for (Statement statement: ((Statements) node).statements) {
                treeNode.add(createTree(statement));
            }
        } else if (node instanceof VariableDeclaration) {
            treeNode.setUserObject("VariableDeclaration");
            treeNode.add(createTree(((VariableDeclaration) node).id));
            treeNode.add(createTree(((VariableDeclaration) node).expression));
        } else if (node instanceof Identifier) {
            treeNode.setUserObject("Identifier "  + ((Identifier) node).spelling);
        } else if (node instanceof BooleanLiteralExpression) {
            treeNode.setUserObject("BooleanLiteralExpression");
            treeNode.add(createTree(((BooleanLiteralExpression) node).literal));
        } else if (node instanceof BooleanLiteral) {
            treeNode.setUserObject("BooleanLiteral " + ((BooleanLiteral) node).spelling);
        } else if (node instanceof IntegerLiteralExpression) {
            treeNode.setUserObject("IntegerLiteralExpression");
            treeNode.add(createTree(((IntegerLiteralExpression) node).literal));
        } else if (node instanceof IntegerLiteral) {
            treeNode.setUserObject("IntegerLiteral " + ((IntegerLiteral) node).spelling);
        } else if (node instanceof StringLiteralExpression) {
            treeNode.setUserObject("StringLiteralExpression");
            treeNode.add(createTree(((StringLiteralExpression) node).literal));
        } else if (node instanceof StringLiteral) {
            treeNode.setUserObject("StringLiteral " + ((StringLiteral) node).spelling);
        } else if (node instanceof BinaryExpression) {
            treeNode.setUserObject( "BinaryExpression" );
            treeNode.add(createTree(((BinaryExpression) node).leftOperand));
            treeNode.add(createTree(((BinaryExpression) node).operator));
            treeNode.add(createTree(((BinaryExpression) node).rightOperand));
        } else if (node instanceof Operator) {
            treeNode.setUserObject("Operator " + ((Operator) node).spelling);
        } else if (node instanceof ScreamStatement) {
            treeNode.setUserObject("ScreamStatement");
            treeNode.add(createTree(((ScreamStatement) node).expression));
        } else {
            System.out.println("Unknown tree node type: '" + node + "'.");
        }

        return treeNode;
    }
}
