import scanner.Scanner;
import scanner.Source;
import scanner.Token;
import scanner.TokenKind;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        String projectPath =  System.getProperty("user.dir")+"/examples";
        System.out.println("Path: " + projectPath);
        JFileChooser fileChooser = new JFileChooser(projectPath);
        fileChooser.showOpenDialog( null );
        Source source = new Source(fileChooser.getSelectedFile().getAbsolutePath());
        Scanner scanner = new Scanner(source);
        Token token = scanner.scan();
        while (token.getKind() != TokenKind.NULLTERMINANT)
        {
            System.out.println( token.getKind() + " " + token.getSpelling());
            token = scanner.scan();
        }

    }
}