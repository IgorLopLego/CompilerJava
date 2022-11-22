package scanner.token;

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
    FUNCTION("exeFunc"),
    IF("inCase"),
    ELSE ("otherwise"),
    WHILE("till"),
    SCREAM("scream"),
    SHOVE("shove"),
    VOID("void"),
    BOOL("boolean"),
    STRING("string"),
    NUMBER("number"),
    ASSIGN("#"),
    COMMA(","),
    DOLLAR("$"),
    SQUARE_LEFT_PARENTHESES( "[" ),
    SQUARE_RIGHT_PARENTHESES( "]" ),
    ROUND_LEFT_PARENTHESES("("),
    ROUND_RIGHT_PARENTHESES(")"),
    RETURN("<-"),
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
