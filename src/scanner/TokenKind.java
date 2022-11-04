package scanner;

public enum TokenKind {
    IDENTIFIER,
    INTEGERLITERAL,
    OPERATOR,
    EXCEPTION,

    START("start"),
    END("end"),
    FOR("for"),
    SWITCH("switch"),
    TERNARY("ternary"),
    EXEFUNC("exeFunc"),
    SCREAM("scream"),
    SHOVE("shove"),
    FOLLOWING("following"),
    BOOL("boolean"),
    STRING("string"),
    NUMBER("number"),
    SEQUENCE("sequence"),
    COMMA(","),
    DOLLAR("$"),
    LEFTPARAN( "[" ),
    RIGHTPARAN( "]" ),
    RETURN("<-"),
    QUESTION("?"),
    SEMICOLUMN(":"),


    LESS("<"),
    LESSOREQUAL("<="),
    MORE(">"),
    MOREOREQUAL(">="),

    NULLTERMINANT;





    private String spelling = null;
    TokenKind(){}
    TokenKind( String spelling )
    {
        this.spelling = spelling;
    }

    public String getSpelling()
    {
        return spelling;
    }
}
