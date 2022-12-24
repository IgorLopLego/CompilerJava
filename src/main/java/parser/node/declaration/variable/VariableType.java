package parser.node.declaration.variable;

import scanner.token.TokenKind;

public enum VariableType {
    BOOLEAN,
    INTEGER,
    STRING;

    public static VariableType getType(TokenKind tokenKind) {
        if (tokenKind == TokenKind.BOOL) return BOOLEAN;

        if (tokenKind == TokenKind.NUMBER) return INTEGER;

        if (tokenKind == TokenKind.STRING) return STRING;

        throw new RuntimeException(
                "Unsupported token kind: '" + tokenKind.toString() + "' for variable type assignment."
        );
    }

}
