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
                return BOOLLITERAL;
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
                return LEFTPARAN;

            case ']':
                proceedToNextCharacter();
                return RIGHTPARAN;
            case '{':
                proceedToNextCharacter();
                return SWITCHLEFTPARAM;
            case '}':
                proceedToNextCharacter();
                return SWITCHRIGHTPARAM;
            case '(':
                proceedToNextCharacter();
                return FUNCTIONLEFTPARAM;
            case ')':
                proceedToNextCharacter();
                return FUNCTIONRIGHTPARAM;
            case '?':
                proceedToNextCharacter();
                return QUESTION;

            case ':':
                proceedToNextCharacter();
                return SEMICOLUMN;

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
                    return LESSOREQUAL;
                }
                return LESS;

            case '>':
                proceedToNextCharacter();
                if(currentChar == '=')
                {
                    proceedToNextCharacter();
                    return MOREOREQUAL;
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
                        return  STRINGLITERAL;
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
                return NULLTERMINANT;

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
        return NUMBERLITERAL;
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
