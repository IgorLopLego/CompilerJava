package scanner;

import utils.Source;
import scanner.token.Token;
import scanner.token.TokenKind;

import static scanner.token.TokenKind.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Scanner {
    private char currentCharacter;
    private StringBuffer currentSpelling;
    private Source source;
    private List<Token> tokens;

    public Scanner() {}

    public void initScanner(Source source) {
        this.source = source;
        currentCharacter = source.getSource();
        currentSpelling = new StringBuffer();
        tokens = new ArrayList<>();
    }

    public List<Token> scan(Source source) {
        initScanner(source);

        while (currentCharacter != Source.EOT) {
            if (isWhiteSpace(currentCharacter)) {
                proceedToNextCharacterWithAppend();
                continue;
            }

            if (currentCharacter == '|') {
                skipCommentSegment();
                continue;
            }

            currentSpelling.setLength(0);
            var kind = scanToken();

            tokens.add(new Token(kind, currentSpelling.toString()));
        }

        return tokens;
    }

    public void printTokens() {
        for (var token : tokens) {
            System.out.println(token);
        }
    }

    private void proceedToNextCharacterWithAppend() {
        currentSpelling.append(currentCharacter);
        currentCharacter = source.getSource();
    }

    private void proceedToNextCharacter() {
        currentCharacter = source.getSource();
    }

    private TokenKind scanToken() {
        if (isLetter(currentCharacter) || isAllowedCharacter(currentCharacter)) {
            proceedToNextCharacterWithAppend();

            while (isLetter(currentCharacter) || isDigit(currentCharacter) || isAllowedCharacter(currentCharacter)) {
                proceedToNextCharacterWithAppend();
            }

            if (isBoolean(this.currentSpelling.toString())) {
                return BOOL_LITERAL;
            }

            return IDENTIFIER;
        }

        if (isDigit(currentCharacter)) {
            proceedToNextCharacterWithAppend();
            return checkForFloatingTokenKind();
        }

        if (currentCharacter == '-') {
            proceedToNextCharacterWithAppend();

            if (!isDigit(currentCharacter)) {
                return EXCEPTION;
            }

            return checkForFloatingTokenKind();
        }

        if (currentCharacter == '#') {
            proceedToNextCharacterWithAppend();
            return ASSIGN;
        }

        if (currentCharacter == ',') {
            proceedToNextCharacterWithAppend();
            return COMMA;
        }

        if (currentCharacter == '$') {
            proceedToNextCharacterWithAppend();
            return DOLLAR;
        }

        if (currentCharacter == '(') {
            proceedToNextCharacterWithAppend();
            return ROUND_LEFT_PARENTHESES;
        }

        if (currentCharacter == ')') {
            proceedToNextCharacterWithAppend();
            return ROUND_RIGHT_PARENTHESES;
        }

        if (currentCharacter == '[') {
            proceedToNextCharacterWithAppend();
            return SQUARE_LEFT_PARENTHESES;
        }

        if (currentCharacter == ']') {
            proceedToNextCharacterWithAppend();
            return SQUARE_RIGHT_PARENTHESES;
        }

        if (currentCharacter == '<') {
            proceedToNextCharacterWithAppend();

            if (currentCharacter == '-') {
                proceedToNextCharacterWithAppend();
                return RETURN;
            }

            if (currentCharacter == '=') {
                proceedToNextCharacterWithAppend();
                return LESS_OR_EQUAL;
            }

            return LESS;
        }

        if (currentCharacter == '>') {
            proceedToNextCharacterWithAppend();

            if (currentCharacter == '=') {
                proceedToNextCharacterWithAppend();
                return MORE_OR_EQUAL;
            }

            return MORE;
        }

        if (currentCharacter == '=') {
            proceedToNextCharacterWithAppend();

            if (currentCharacter == '=') {
                proceedToNextCharacterWithAppend();
                return EQUALS;
            }

            return EXCEPTION;
        }

        if (currentCharacter == '`') {
            proceedToNextCharacter();

            while (true) {
                if (currentCharacter == '\0') {
                    return EXCEPTION;
                }

                if (currentCharacter == '`') {
                    proceedToNextCharacter();
                    return STRING_LITERAL;
                }

                proceedToNextCharacterWithAppend();
            }
        }

        if (currentCharacter == '\0') {
            proceedToNextCharacterWithAppend();
            return NULL_TERMINATOR;
        }

        proceedToNextCharacterWithAppend();
        return EXCEPTION;
    }

    private TokenKind checkForFloatingTokenKind() {
        var isDotUsedAlready = false;

        while (isDigit(currentCharacter) || currentCharacter == '.') {
            if (currentCharacter == '.') {
                if (isDotUsedAlready) {
                    return EXCEPTION;
                }

                isDotUsedAlready = true;
            }

            proceedToNextCharacterWithAppend();
        }

        if (currentSpelling.toString().endsWith(".")) {
            return EXCEPTION;
        }

        return NUMBER_LITERAL;
    }

    private boolean isLetter(char character) {
        return Pattern.matches("[a-zA-Z]", Character.toString(character));
    }

    private boolean isBoolean(String string) {
        return string.equals("true") || string.equals("false");
    }

    private boolean isDigit(char character) {
        return Pattern.matches("\\d", Character.toString(character));
    }


    private boolean isWhiteSpace(char character) {
        return Pattern.matches("\\s", Character.toString(character));
    }

    private boolean isAllowedCharacter(char ch){
        return ch == '_' || ch == '$' || ch == '^';
    }

    private void skipCommentSegment() {
        while (currentCharacter != Source.newLine && currentCharacter != Source.EOT)
            proceedToNextCharacterWithAppend();

        if (currentCharacter == Source.newLine)
            proceedToNextCharacterWithAppend();
    }
}
