import parserRefactor.Parser;
import scannerRefactor.Scanner;
import scanner.Source;
import utils.Path;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        var fileChooser = new JFileChooser(Path.EXAMPLES_DIR);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            var source = new Source(fileChooser.getSelectedFile().getAbsolutePath());
            var scanner = new Scanner(source);

            var tokens = scanner.scanSource();
            scanner.printTokens();

            var parser = new Parser();
            parser.parse(tokens);
        } else {
            System.out.println("No file was selected. The program will close.");
        }
    }
}