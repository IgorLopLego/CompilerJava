import checker.CheckerVisitor;
import encoder.Encoder;
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
            String sourceName = fileChooser.getSelectedFile().getAbsolutePath();
            var source = new Source(fileChooser.getSelectedFile().getAbsolutePath());

            var scanner = new Scanner();

            var tokens = scanner.scan(source);
            scanner.printTokens();

            var parser = new Parser();
            var programNode = (Program) parser.parse(tokens);

            new ViewerAST(programNode);

            new CheckerVisitor().check(programNode);
            Encoder encoder = new Encoder();
            encoder.encode(programNode);

            String targetName;
            if (sourceName.endsWith(".txt")) {
                targetName = sourceName.substring(0, sourceName.length() - 4) + ".tam";
            } else {
                targetName = sourceName + ".tam";
            }
            encoder.saveTargetProgram(targetName);
        } else {
            System.out.println("No file was selected. The program will close.");
        }
    }
}