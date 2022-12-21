import checker.CheckerVisitor;
import parser.Parser;
import parser.node.Program;
import scanner.Scanner;
import utils.Source;
import utils.Path;
import viewer.ViewerAST;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        var fileChooser = new JFileChooser(Path.EXAMPLES_DIR);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            var source = new Source(fileChooser.getSelectedFile().getAbsolutePath());

            var scanner = new Scanner();

            var tokens = scanner.scan(source);
            scanner.printTokens();

            var parser = new Parser();
            var programNode = (Program) parser.parse(tokens);

            new ViewerAST(programNode);

            // new CheckerVisitor().check(programNode);
        } else {
            System.out.println("No file was selected. The program will close.");
        }
    }
}