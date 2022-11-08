package parserRefactor;

import parserRefactor.nodes.Block;
import parserRefactor.nodes.Program;
import parserRefactor.nodes.declaration.Declaration;
import parserRefactor.nodes.declaration.Declarations;
import parserRefactor.nodes.declaration.VariableDeclaration;
import parserRefactor.nodes.expression.BinaryExpression;
import parserRefactor.nodes.expression.Expression;
import parserRefactor.nodes.expression.IntegerLiteralExpression;
import parserRefactor.nodes.expression.UnaryExpression;
import parserRefactor.nodes.statement.Statements;
import parserRefactor.nodes.terminal.Identifier;
import parserRefactor.nodes.terminal.IntegerLiteral;
import parserRefactor.nodes.terminal.Operator;
import scanner.Token;
import scanner.TokenKind;

import static scanner.TokenKind.*;

import java.util.List;

public class Parser {
    private Token currentToken;
    private int currentTokenIndex;
    private List<Token> tokens;

    private void initParser(List<Token> tokens) {
        currentTokenIndex = 0;
        currentToken = tokens.get(0);
        this.tokens = tokens;
    }

    public Program parse(List<Token> tokens) {
        initParser(tokens);

        consume(START);
        var declarations = parseDeclarations();
        consume(END);

        return new Program(new Block(declarations, new Statements()));
    }

    private Declarations parseDeclarations() {
        var declarations = new Declarations();

        while (isDeclaration()) {
            declarations.declarations.add(parseOneDeclaration());
        }

        return declarations;
    }

    private Declaration parseOneDeclaration() {
        if (isVariableDeclaration()) {
            var variableType = currentToken.getKind();
            // Consume the type of variable
            consume(variableType);

            // Consume and get the identifier
            var id = parseIdentifier();

            // Consume the assign symbol
            consume(ASSIGN);

            // Consume the value
            if (isLiteralMatchingType(variableType)) {
                var literalExpression = parseExpression();

//                var integerLiteral = parseIntegerLiteral();
//                var integerLiteralExpression = new IntegerLiteralExpression(integerLiteral);

                // Consume the end-of-declaration symbol
                consume(DOLLAR);

                return new VariableDeclaration(id, literalExpression);
            }

            throw new RuntimeException("The identifier type is not matching the value type.");
        }

        throw new RuntimeException("Only variable declaration is supported for now.");
    }

    private Identifier parseIdentifier() {
        if (isExpected(IDENTIFIER)) {
            var id = new Identifier(currentToken.getSpelling());

            next();

            return id;
        }

        throw new RuntimeException("Identifier expected.");
    }

    private Expression parseExpression() {
        var expression = parsePrimary();

        while (isExpected(OPERATOR)) {
            var operator = parseOperator();
            var temporary = parsePrimary();

            expression = new BinaryExpression(operator, expression, temporary);
        }

        return expression;
    }

    private Expression parsePrimary() {
        if (isExpected(NUMBER_LITERAL)) {
            var integerLiteral = parseIntegerLiteral();
            return new IntegerLiteralExpression(integerLiteral);
        }

        if (isExpected(OPERATOR)) {
            var operator = parseOperator();
            var expression = parsePrimary();

            return new UnaryExpression(operator, expression);
        }

        throw new RuntimeException("Unsupported parsing type. Currently not supporting parsing the '" + currentToken.getKind() + "' type.");
    }

    private IntegerLiteral parseIntegerLiteral() {
        if (isExpected(NUMBER_LITERAL)) {
            var integerLiteral = new IntegerLiteral(currentToken.getSpelling());

            next();

            return integerLiteral;
        }

        throw new RuntimeException("Integer literal expected.");
    }

    private Operator parseOperator() {
        if (isExpected(OPERATOR)) {
            var operator = new Operator(currentToken.getSpelling());

            next();

            return operator;
        }

        throw new RuntimeException("Operator expected.");
    }

    private boolean isDeclaration() {
        return isVariableDeclaration() ||
                isFunctionDeclaration();
    }

    private boolean isFunctionDeclaration() {
        return isExpected(FUNCTION);
    }

    private boolean isVariableDeclaration() {
        return isExpected(BOOL) ||
                isExpected(STRING) ||
                isExpected(NUMBER) ||
                isExpected(SEQUENCE);
    }

    private boolean isLiteralMatchingType(TokenKind type) {
        if (type == NUMBER && isExpected(NUMBER_LITERAL))
            return true;
        else if (type == STRING && isExpected(STRING_LITERAL))
            return true;
        else
            return type == BOOL && isExpected(BOOL_LITERAL);
    }

    private void consume(TokenKind expected) {
        if (isExpected(expected)) {
            if (currentTokenIndex + 1 != tokens.size())
                next();
        } else {
            throw new RuntimeException("Tried to consume an unexpected token. Expected '" + expected + "', but received '" + currentToken.getKind() + "'.");
        }
    }

    private void next() {
        currentToken = this.tokens.get(++currentTokenIndex);
    }

    private boolean isExpected(TokenKind expected) {
        return expected == currentToken.getKind();
    }
}
