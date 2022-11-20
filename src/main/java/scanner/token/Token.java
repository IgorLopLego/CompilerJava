package scanner.token;

import java.util.Objects;

import static scanner.token.TokenKind.OPERATOR;

public class Token {
    private TokenKind kind;
    private String spelling;
    private static final String additionOperator[] = {"add", "subtract"};
    private static final String multiplicationOperators[] = {"multiplication", "division", "module"};
    private static final TokenKind[] KEYWORDS = {TokenKind.DOLLAR, TokenKind.START, TokenKind.END, TokenKind.FOR, TokenKind.SWITCH, TokenKind.FUNCTION, TokenKind.SCREAM, TokenKind.FOLLOWING, TokenKind.BOOL, TokenKind.STRING, TokenKind.NUMBER, TokenKind.SHOVE, TokenKind.SEQUENCE, TokenKind.VOID};


    public Token(TokenKind kind, String spelling)
    {
        this.kind = kind;
        this.spelling = spelling;

        if (kind == TokenKind.IDENTIFIER) {
            if(IsOperator())
            {
                this.kind = OPERATOR;
                return;
            }
            TokenKind tokenKind = IsAKeyword();
            if(tokenKind != null)
            {
                this.kind = tokenKind;
            }
        }
    }

    private boolean IsOperator() {
         return  containsOperator(spelling, additionOperator) || containsOperator(spelling, multiplicationOperators);
    }

    private TokenKind IsAKeyword(){
        for (TokenKind tokenKind : KEYWORDS) {
            if (spelling.equals(tokenKind.getSpelling())) {
               return tokenKind;
            }
        }
        return null;
    }

    public boolean isAddOperator()
    {
        if( kind == OPERATOR )
            return containsOperator( spelling, additionOperator);
        else
            return false;
    }

    private boolean containsOperator( String spelling, String operations[] ) {

        for (String operation : operations)
            if (spelling.equals(operation))
                return true;
        return false;
    }

    public TokenKind getKind() {
        return kind;
    }

    public String getSpelling() {
        return spelling;
    }

    @Override
    public String toString() {
        if (this.spelling.isEmpty() || this.spelling.equals("\n")) {
            return "Token { kind: " + kind + " }";
        }

        return "Token { kind: " + kind + ", spelling: '" + spelling + "' }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;
        return kind == token.kind && spelling.equals(token.spelling);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, spelling);
    }
}
