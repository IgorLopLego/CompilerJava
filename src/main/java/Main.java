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

            scanner.scanSource();
            scanner.printTokens();
        } else {
            System.out.println("No file was selected. The program will close.");
        }
    }
}