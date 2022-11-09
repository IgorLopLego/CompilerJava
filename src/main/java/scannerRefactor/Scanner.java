package scannerRefactor;

import scanner.Source;
import scanner.Token;
import scanner.TokenKind;

import static scanner.TokenKind.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Scanner {
    private char currentCharacter;
    private final StringBuffer currentSpelling;
    private final Source source;
    private final List<Token> tokens;

    public Scanner(Source source) {
        this.source = source;

        currentCharacter = source.getSource();
        currentSpelling = new StringBuffer();
        tokens = new ArrayList<>();
    }

    public List<Token> scanSource() {
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
            return FUNCTION_LEFT_PARENTHESES;
        }

        if (currentCharacter == ')') {
            proceedToNextCharacterWithAppend();
            return FUNCTION_RIGHT_PARENTHESES;
        }

        if (currentCharacter == '[') {
            proceedToNextCharacterWithAppend();
            return LEFT_PARENTHESES;
        }

        if (currentCharacter == ']') {
            proceedToNextCharacterWithAppend();
            return RIGHT_PARENTHESES;
        }

        if (currentCharacter == '{') {
            proceedToNextCharacterWithAppend();
            return SWITCH_LEFT_PARENTHESES;
        }

        if (currentCharacter == '}') {
            proceedToNextCharacterWithAppend();
            return SWITCH_RIGHT_PARENTHESES;
        }

        if (currentCharacter == '?') {
            proceedToNextCharacterWithAppend();
            return QUESTION;
        }

        if (currentCharacter == ':') {
            proceedToNextCharacterWithAppend();
            return COLON;
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

        if (currentCharacter == '&') {
            proceedToNextCharacterWithAppend();
            return CASE;
        }

        if (currentCharacter == '@') {
            proceedToNextCharacterWithAppend();
            return BREAK;
        }

        if (currentCharacter == '~') {
            proceedToNextCharacterWithAppend();
            return DEFAULT;
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
