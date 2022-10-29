import scanner.Source;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        String projectPath =  System.getProperty("user.dir")+"/examples";
        JFileChooser fileChooser = new JFileChooser(projectPath);
        fileChooser.showOpenDialog( null );
        Source source = new Source(fileChooser.getSelectedFile().getAbsolutePath());
        char character = ' ';
        while (character != '\0'){
            character = source.getSource();
        }

    }
}