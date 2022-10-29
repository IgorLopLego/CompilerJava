package scanner;

public class Token {
    private TokenKind kind;
    private String spelling;

    public Token(TokenKind kind, String spelling)
    {
        this.kind = kind;
        this.spelling = spelling;

        if(kind == TokenKind.IDENTIFIER)
        {

        }
        for( TokenKind token: KEYWORDS )
            if( spelling.equals( token.getSpelling() ) ) {
                this.kind = token;
                break;
            }
    }
    private static final TokenKind[] KEYWORDS = {TokenKind.START, TokenKind.END, TokenKind.FOR, TokenKind.SWITCH, TokenKind.TERNARY, TokenKind.EXEFUNC, TokenKind.ANNOUNCE, TokenKind.FOLLOWING};

    public TokenKind getKind() {
        return kind;
    }

    public String getSpelling() {
        return spelling;
    }

    private static final String ASSIGNOPS[] =
            {
                    "=>",
            };
    private static final String ADDOPS[] =
            {
                    "add",
                    "substract",
            };
    private static final String MULOPS[] =
            {
                    "multiplication",
                    "division",
                    "module",
            };

}
