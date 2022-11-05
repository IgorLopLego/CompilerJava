package scanner;

public enum TokenKind {
    IDENTIFIER,
    INTEGERLITERAL,
    STRINGLITERAL,
    BOOLLITERAL,
    OPERATOR,
    EXCEPTION,

    START("start"),
    END("end"),
    FOR("for"),
    SWITCH("switch"),
    EXEFUNC("exeFunc"),
    SCREAM("scream"),
    SHOVE("shove"),
    FOLLOWING("following"),
    VOID("void"),
    BOOL("boolean"),
    STRING("string"),
    NUMBER("number"),
    SEQUENCE("sequence"),
    ASSIGN("#"),
    COMMA(","),
    DOLLAR("$"),
    LEFTPARAN( "[" ),
    RIGHTPARAN( "]" ),
    RETURN("<-"),
    QUESTION("?"),
    SEMICOLUMN(":"),
    CASE("&"),
    BREAK("@"),
    DEFAULT("~"),


    LESS("<"),
    LESSOREQUAL("<="),
    MORE(">"),
    MOREOREQUAL(">="),
    EQUALS("=="),

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
