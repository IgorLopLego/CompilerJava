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
                proceedToNextCharacter();
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

    private void proceedToNextCharacter() {
        currentSpelling.append(currentCharacter);
        currentCharacter = source.getSource();
    }

    private TokenKind scanToken() {
        if (isLetter(currentCharacter) || isAllowedCharacter(currentCharacter)) {
            proceedToNextCharacter();

            while (isLetter(currentCharacter) || isDigit(currentCharacter) || isAllowedCharacter(currentCharacter)) {
                proceedToNextCharacter();
            }

            if (isBoolean(this.currentSpelling.toString())) {
                return BOOL_LITERAL;
            }

            return IDENTIFIER;
        }

        if (isDigit(currentCharacter)) {
            proceedToNextCharacter();
            return checkForFloatingTokenKind();
        }

        if (currentCharacter == '-') {
            proceedToNextCharacter();

            if (!isDigit(currentCharacter)) {
                return EXCEPTION;
            }

            return checkForFloatingTokenKind();
        }

        if (currentCharacter == '#') {
            proceedToNextCharacter();
            return ASSIGN;
        }

        if (currentCharacter == ',') {
            proceedToNextCharacter();
            return COMMA;
        }

        if (currentCharacter == '$') {
            proceedToNextCharacter();
            return DOLLAR;
        }

        if (currentCharacter == '(') {
            proceedToNextCharacter();
            return FUNCTION_LEFT_PARENTHESES;
        }

        if (currentCharacter == ')') {
            proceedToNextCharacter();
            return FUNCTION_RIGHT_PARENTHESES;
        }

        if (currentCharacter == '[') {
            proceedToNextCharacter();
            return LEFT_PARENTHESES;
        }

        if (currentCharacter == ']') {
            proceedToNextCharacter();
            return RIGHT_PARENTHESES;
        }

        if (currentCharacter == '{') {
            proceedToNextCharacter();
            return SWITCH_LEFT_PARENTHESES;
        }

        if (currentCharacter == '}') {
            proceedToNextCharacter();
            return SWITCH_RIGHT_PARENTHESES;
        }

        if (currentCharacter == '?') {
            proceedToNextCharacter();
            return QUESTION;
        }

        if (currentCharacter == ':') {
            proceedToNextCharacter();
            return COLON;
        }

        if (currentCharacter == '<') {
            proceedToNextCharacter();

            if (currentCharacter == '-') {
                proceedToNextCharacter();
                return RETURN;
            }

            if (currentCharacter == '=') {
                proceedToNextCharacter();
                return LESS_OR_EQUAL;
            }

            return LESS;
        }

        if (currentCharacter == '>') {
            proceedToNextCharacter();

            if (currentCharacter == '=') {
                proceedToNextCharacter();
                return MORE_OR_EQUAL;
            }

            return MORE;
        }

        if (currentCharacter == '=') {
            proceedToNextCharacter();

            if (currentCharacter == '=') {
                proceedToNextCharacter();
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

                proceedToNextCharacter();
            }
        }

        if (currentCharacter == '&') {
            proceedToNextCharacter();
            return CASE;
        }

        if (currentCharacter == '@') {
            proceedToNextCharacter();
            return BREAK;
        }

        if (currentCharacter == '~') {
            proceedToNextCharacter();
            return DEFAULT;
        }

        if (currentCharacter == '\0') {
            proceedToNextCharacter();
            return NULL_TERMINATOR;
        }

        proceedToNextCharacter();
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

            proceedToNextCharacter();
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
            proceedToNextCharacter();

        if (currentCharacter == Source.newLine)
            proceedToNextCharacter();
    }
}
