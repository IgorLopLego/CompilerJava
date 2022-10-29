package scanner;


public class Scanner {

    private Source source;
    private char currentChar;
    private StringBuffer currentSpelling = new StringBuffer();

    public Scanner(Source source) {

        this.source = source;
        currentChar = source.getSource();
    }

    private boolean isLetter(char ch) {
        return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private boolean isAllowedCharacter(char ch){
        return ch == '_' || ch == '$' || ch == '^';
    }





}
