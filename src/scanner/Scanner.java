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
            return IDENTIFIER;
        }
        else if(isDigit(currentChar) || currentChar == '-')
        {
            proceedToNextCharacter();
            if(!isDigit(currentChar))
            {
                return EXCEPTION;
            }
            proceedToNextCharacter();
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
            return INTEGERLITERAL;


        }
        switch (currentChar) {
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
            case '\0':
                return NULLTERMINANT;

            default:
                proceedToNextCharacter();
                return EXCEPTION;


        }

    }

//    private TokenKind scanToken()
//    {
//        if( isLetter( currentChar ) || isAllowedCharacter(currentChar) ) {
//            proceedToNextCharacter();
//            while( isLetter( currentChar ) || isDigit( currentChar ) || isAllowedCharacter(currentChar))
//            {
//                proceedToNextCharacter();
//            }
//            return IDENTIFIER;
//        }
//        else if( isDigit( currentChar ) ) {
//            proceedToNextCharacter();
//            while( isDigit( currentChar ) ) {
//                proceedToNextCharacter();
//            }
//
//            return INTEGERLITERAL;
//
//        } switch( currentChar ) {
//        case '+': case '-': case '*': case '/': case '%':
//            proceedToNextCharacter();
//            return OPERATOR;
//
//        case ':':
//            proceedToNextCharacter();
//            if( currentChar == '=' ) {
//                proceedToNextCharacter();
//                return OPERATOR;
//            } else
//                return ERROR;
//
//        case ',':
//            proceedToNextCharacter();
//            return COMMA;
//
//        case ';':
//            proceedToNextCharacter();
//            return SEMICOLON;
//
//        case '(':
//            proceedToNextCharacter();
//            return LEFTPARAN;
//
//        case ')':
//            proceedToNextCharacter();
//            return RIGHTPARAN;
//
//        case SourceFile.EOT:
//            return EOT;
//
//        default:
//            proceedToNextCharacter();
//            return ERROR;
//    }
//    }

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
