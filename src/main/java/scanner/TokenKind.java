package scanner;

public enum TokenKind {
    IDENTIFIER,
    NUMBER_LITERAL,
    STRING_LITERAL,
    BOOL_LITERAL,
    OPERATOR,
    EXCEPTION,
    NULL_TERMINATOR,

    START("start"),
    END("end"),
    FOR("for"),
    SWITCH("switch"),
    FUNCTION("exeFunc"),
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
    LEFT_PARENTHESES( "[" ),
    RIGHT_PARENTHESES( "]" ),
    SWITCH_LEFT_PARENTHESES("{"),
    SWITCH_RIGHT_PARENTHESES("}"),
    FUNCTION_LEFT_PARENTHESES("("),
    FUNCTION_RIGHT_PARENTHESES(")"),
    RETURN("<-"),
    QUESTION("?"),
    COLON(":"),
    CASE("&"),
    BREAK("@"),
    DEFAULT("~"),
    LESS("<"),
    LESS_OR_EQUAL("<="),
    MORE(">"),
    MORE_OR_EQUAL(">="),
    EQUALS("==");

    private String spelling = null;
    TokenKind() {}
    TokenKind(String spelling) {
        this.spelling = spelling;
    }

    public String getSpelling() {
        return spelling;
    }
}
