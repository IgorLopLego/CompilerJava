import scannerRefactor.Scanner;
import scanner.Source;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        var fileChooser = new JFileChooser(getExamplesDirectoryPath());
        int fileChooserReturn = fileChooser.showOpenDialog(null);

        if (fileChooserReturn  == JFileChooser.APPROVE_OPTION) {
            Source source = new Source(fileChooser.getSelectedFile().getAbsolutePath());

            Scanner scanner = new Scanner(source);

            var tokens = scanner.scanSource();

            for (var token : tokens) {
                System.out.println(token);
            }
        } else {
            System.out.println("No file was selected. The program will close.");
        }
    }

    private static String getExamplesDirectoryPath() {
        return System.getProperty("user.dir") + "/src/main/java/examples";
    }
}