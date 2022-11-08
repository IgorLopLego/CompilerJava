package scanner;

import static scanner.TokenKind.*;

public class Scanner {

    private Source source;
    private char currentChar;
    private StringBuffer currentSpelling = new StringBuffer();

    public Scanner(Source source) {

        this.source = source;
        currentChar = source.getSource();
    }

    private void proceedToNextCharacter()
    {
        currentSpelling.append( currentChar );
        currentChar = source.getSource();
    }

    public Token scan()
    {
        while (currentChar == '|' || currentChar == '\n' || currentChar == '\r' || currentChar == '\t' || currentChar == ' ')
        {
            scanSeparator();
        }
        currentSpelling = new StringBuffer("");
        TokenKind kind = scanToken();
        return new Token( kind, currentSpelling.toString() );
    }

    private TokenKind scanToken(){
        if(isLetter(currentChar) || isAllowedCharacter(currentChar)){
            proceedToNextCharacter();
            while (isLetter(currentChar) || isDigit(currentChar) || isAllowedCharacter(currentChar))
            {
                proceedToNextCharacter();
            }
            if(this.currentSpelling.toString().equals("true") || this.currentSpelling.toString().equals("false"))
            {
                return BOOL_LITERAL;
            }
            return IDENTIFIER;
        }
        else if (isDigit(currentChar))
        {
            proceedToNextCharacter();
            return checkForFloatingTokenKind();
        }
        else if(currentChar == '-')
        {
            proceedToNextCharacter();
            if(!isDigit(currentChar))
            {
                return EXCEPTION;
            }
            else
            {
                return checkForFloatingTokenKind();
            }
        }
        switch (currentChar) {
            case '#':
                proceedToNextCharacter();
                return ASSIGN;
            case ',':
                proceedToNextCharacter();
                return COMMA;

            case '$':
                proceedToNextCharacter();
                return DOLLAR;

            case '[':
                proceedToNextCharacter();
                return LEFT_PARENTHESES;

            case ']':
                proceedToNextCharacter();
                return RIGHT_PARENTHESES;
            case '{':
                proceedToNextCharacter();
                return SWITCH_LEFT_PARENTHESES;
            case '}':
                proceedToNextCharacter();
                return SWITCH_RIGHT_PARENTHESES;
            case '(':
                proceedToNextCharacter();
                return FUNCTION_LEFT_PARENTHESES;
            case ')':
                proceedToNextCharacter();
                return FUNCTION_RIGHT_PARENTHESES;
            case '?':
                proceedToNextCharacter();
                return QUESTION;

            case ':':
                proceedToNextCharacter();
                return COLON;

            case '<':
                proceedToNextCharacter();
                if(currentChar == '-')
                {
                    proceedToNextCharacter();
                    return RETURN;
                }
                if(currentChar == '=')
                {
                    proceedToNextCharacter();
                    return LESS_OR_EQUAL;
                }
                return LESS;

            case '>':
                proceedToNextCharacter();
                if(currentChar == '=')
                {
                    proceedToNextCharacter();
                    return MORE_OR_EQUAL;
                }
                return MORE;

            case '=':
                proceedToNextCharacter();
                if(currentChar == '=')
                {
                    proceedToNextCharacter();
                    return EQUALS;
                }
                return EXCEPTION;

            case '`':
                proceedToNextCharacter();
                while(true)
                {
                    if(currentChar == '\0')
                    {
                        return EXCEPTION;
                    }
                    else if(currentChar == '`')
                    {
                        proceedToNextCharacter();
                        return STRING_LITERAL;
                    }
                    proceedToNextCharacter();
                }
            case '&':
                proceedToNextCharacter();
                return CASE;
            case '@':
                proceedToNextCharacter();
                return BREAK;
            case '~':
                proceedToNextCharacter();
                return DEFAULT;
            case '\0':
                return NULL_TERMINATOR;

            default:
                proceedToNextCharacter();
                return EXCEPTION;


        }
    }

    private TokenKind checkForFloatingTokenKind() {
        boolean isDotUsedAlready = false;
        while (isDigit(currentChar) || currentChar == '.')
        {
            if(currentChar == '.')
            {
                if(isDotUsedAlready)
                {
                    return EXCEPTION;
                }
                isDotUsedAlready = true;
            }
            proceedToNextCharacter();
        }
        if(currentSpelling.toString().endsWith("."))
        {
            return EXCEPTION;
        }
        return NUMBER_LITERAL;
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

    private void scanSeparator()
    {
        switch(currentChar) {
            case '|':
                proceedToNextCharacter();
                while( currentChar != Source.newLine && currentChar != Source.EOT )
                    proceedToNextCharacter();

                if( currentChar == Source.newLine)
                    proceedToNextCharacter();
                break;

            case ' ': case '\n': case '\r': case '\t':
                proceedToNextCharacter();
                break;
        }
    }







}