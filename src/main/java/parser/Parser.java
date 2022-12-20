package parser;

import parser.node.Block;
import parser.node.Node;
import parser.node.Program;
import parser.node.declaration.Declaration;
import parser.node.declaration.Declarations;
import parser.node.declaration.FunctionDeclaration;
import parser.node.declaration.VariableDeclaration;
import parser.node.expression.*;
import parser.node.statement.*;
import parser.node.terminal.*;
import scanner.token.Token;
import scanner.token.TokenKind;

import static scanner.token.TokenKind.*;

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

    public Node parse(List<Token> tokens) {
        initParser(tokens);

        consume(START);

        var block = parseBlock();

        consume(END);

        return new Program(block);
    }

    private Block parseBlock() {
        var block = new Block(
                new Declarations(),
                new Statements()
        );

        while (!isExpected(RETURN) && !isExpected(END) &&
                (isDeclaration() || isStatement())
        ) {
            if (isDeclaration())
                block.declarations.declarations.addAll(
                        parseDeclarations().declarations
                );

            if (isStatement())
                block.statements.statements.addAll(
                        parseStatements().statements
                );
        }

        return block;
    }

    private Statements parseStatements() {
        var statements = new Statements();

        while (isExpected(SQUARE_LEFT_PARENTHESES) || isExpected(SCREAM) || isExpected(IF) || isExpected(WHILE))
            statements.statements.add(parseOneStatement());

        if (statements.statements.isEmpty())
            throw new RuntimeException("Cannot parse the '" + currentToken.getKind() + "' token.");

        return statements;
    }

    private Statement parseOneStatement() {
        if (isExpected(SCREAM)) {
            consume(SCREAM);

            var screamExpression = parseExpression();

            consume(DOLLAR);

            return new ScreamStatement(screamExpression);
        }

        if (isExpected(IF)) {
            consume(IF);

            var ifExpression = parseExpression();

            consume(ROUND_LEFT_PARENTHESES);

            var ifBlock = parseBlock();

            consume(ROUND_RIGHT_PARENTHESES);

            Block elseBlock = null;

            if (isExpected(ELSE)) {
                consume(ELSE);

                consume(ROUND_LEFT_PARENTHESES);

                elseBlock = parseBlock();

                consume(ROUND_RIGHT_PARENTHESES);
            }

            return new IfStatement(ifExpression, ifBlock, elseBlock);
        }

        if (isExpected(WHILE)) {
            consume(WHILE);

            var expression = parseExpression();

            consume(ROUND_LEFT_PARENTHESES);

            var block = parseBlock();

            consume(ROUND_RIGHT_PARENTHESES);

            return new WhileStatement(expression, block);
        }

        throw new RuntimeException("Only scream statement is supported for now.");
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

            if (isExpected(ASSIGN)) {
                // Consume the assign symbol
                consume(ASSIGN);

                // Consume the value
                if (isLiteralMatchingType(variableType)) {
                    var literalExpression = parseExpression();

                    // Consume the end-of-declaration symbol
                    consume(DOLLAR);

                    return new VariableDeclaration(id, literalExpression);
                }

                throw new RuntimeException("The identifier type is not matching the value type.");
            }

            return new VariableDeclaration(id);
        }

        if (isFunctionDeclaration()) {
            // Consume function keyword
            consume(FUNCTION);

            // Consume function type
            var functionType = currentToken.getKind();
            consume(functionType);

            // Consume function identifier
            var name = parseIdentifier();

            // Consume parameters left parenthesis
            consume(SQUARE_LEFT_PARENTHESES);

            Declarations parameters;

            // Check if function has arguments
            if (isExpected(SQUARE_RIGHT_PARENTHESES)) parameters = new Declarations();
            else parameters = parseIdentifierList();

            // Consume parameters right parenthesis
            consume(SQUARE_RIGHT_PARENTHESES);

            // Consume function block left parenthesis
            consume(ROUND_LEFT_PARENTHESES);

            // Consume function body
            var block = parseBlock();

            consume(RETURN);

            // Return expression
            Expression returnExpression = null;

            if (functionType != VOID) returnExpression = parseExpression();

            // Consume semicolon
            consume(DOLLAR);

            // Consume function block right parenthesis
            consume(ROUND_RIGHT_PARENTHESES);

            return new FunctionDeclaration(
                    name,
                    parameters,
                    block,
                    returnExpression
            );
        }

        throw new RuntimeException("Unexpected type of declaration. Received: '" + currentToken.getKind() + "'.");
    }

    private Identifier parseIdentifier() {
        if (isExpected(IDENTIFIER)) {
            var id = new Identifier(currentToken.getSpelling());

            next();

            return id;
        }

        throw new RuntimeException("Identifier expected.");
    }

    private Declarations parseIdentifierList() {
        var declarations = new Declarations();

        declarations.declarations.add(parseOneDeclaration());

        while (isExpected(COMMA)) {
            consume(COMMA);

            declarations.declarations.add(parseOneDeclaration());
        }

        return declarations;
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
        if (isExpected(IDENTIFIER)) {
            var id = parseIdentifier();

            return new VariableExpression(id);
        }

        if (isExpected(BOOL_LITERAL)) {
            var booleanLiteral = parseBooleanLiteral();
            return new BooleanLiteralExpression(booleanLiteral);
        }

        if (isExpected(SQUARE_LEFT_PARENTHESES)) {
            consume(SQUARE_LEFT_PARENTHESES);

            var expression = parseExpression();

            consume(SQUARE_RIGHT_PARENTHESES);

            return expression;
        }

        if (isExpected(NUMBER_LITERAL)) {
            var integerLiteral = parseIntegerLiteral();
            return new IntegerLiteralExpression(integerLiteral);
        }

        if (isExpected(STRING_LITERAL)) {
            var stringLiteral = parseStringLiteral();
            return new StringLiteralExpression(stringLiteral);
        }

        if (isExpected(OPERATOR)) {
            var operator = parseOperator();
            var expression = parsePrimary();

            return new UnaryExpression(operator, expression);
        }

        throw new RuntimeException("Unsupported parsing type. Currently not supporting parsing the '" + currentToken.getKind() + "' type.");
    }

    private BooleanLiteral parseBooleanLiteral() {
        if (isExpected(BOOL_LITERAL)) {
            var booleanLiteral = new BooleanLiteral(currentToken.getSpelling());

            next();

            return booleanLiteral;
        }

        throw new RuntimeException("Boolean literal expected.");
    }

    private IntegerLiteral parseIntegerLiteral() {
        if (isExpected(NUMBER_LITERAL)) {
            var integerLiteral = new IntegerLiteral(currentToken.getSpelling());

            next();

            return integerLiteral;
        }

        throw new RuntimeException("Integer literal expected.");
    }

    private StringLiteral parseStringLiteral() {
        if (isExpected(STRING_LITERAL)) {
            var stringLiteral = new StringLiteral(currentToken.getSpelling());

            next();

            return stringLiteral;
        }

        throw new RuntimeException("String literal expected.");
    }

    private Operator parseOperator() {
        if (isExpected(OPERATOR)) {
            var operator = new Operator(currentToken.getSpelling());

            next();

            return operator;
        }

        throw new RuntimeException("Operator expected.");
    }

    private boolean isStatement() {
        return isExpression() ||
                isExpected(RETURN) ||
                isExpected(WHILE) ||
                isExpected(IF) ||
                isExpected(ELSE) ||
                isExpected(SCREAM) ||
                isExpected(SHOVE);
    }

    private boolean isExpression() {
        return isExpected(IDENTIFIER) ||
                isExpected(NUMBER_LITERAL) ||
                isExpected(BOOL_LITERAL) ||
                isExpected(OPERATOR) ||
                isExpected(STRING_LITERAL);
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
                isExpected(NUMBER);
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
