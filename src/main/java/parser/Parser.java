package parser;

import parser.node.Block;
import parser.node.Node;
import parser.node.Program;
import parser.node.terminal.DeclarationParameter;
import parser.node.declaration.Declarations;
import parser.node.declaration.DeclarationFunctionAssign;
import parser.node.declaration.DeclarationVariableAssign;
import parser.node.expression.*;
import parser.node.statement.*;
import parser.node.terminal.*;
import scanner.token.Token;
import scanner.token.TokenKind;

import static scanner.token.TokenKind.*;

import java.util.List;
import java.util.Vector;

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
            {
                    if (isVariableDeclaration()) {
                        while (currentToken.getKind() != DOLLAR) {
                            block.declarations.declarationVariableAssignList.add(parseDeclarationVariableAssign());
                            if(currentToken.getKind() != DOLLAR)
                            {
                               consume(COMMA);
                            }
                        }
                        consume(DOLLAR);
                    }
                    if(isFunctionDeclaration())
                    {
                        block.declarations.declarationFunctionAssignList.add(parseDeclarationFunctionAssign());
                    }
            }

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


    public DeclarationVariableAssign parseDeclarationVariableAssign(){
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

            return new DeclarationVariableAssign(id, literalExpression);
        }

        throw new RuntimeException("The identifier type " + variableType.getSpelling() + " is not matching the value type: " +  currentToken.getSpelling());
    }

    public DeclarationFunctionAssign parseDeclarationFunctionAssign(){
        boolean isVoid = false;
        // Consume function keyword
        consume(FUNCTION);

        // Consume function type
        var functionType = currentToken;

        if(functionType.getKind() == VOID)
        {
            isVoid = true;
        }
        consume(functionType.getKind());

        // Consume function identifier
        var name = parseIdentifier();

        // Consume parameters left parenthesis
        consume(SQUARE_LEFT_PARENTHESES);

        Vector<DeclarationParameter> declarationParameters = new Vector<>();

        // Check if function has arguments
        if (!isExpected(SQUARE_RIGHT_PARENTHESES))
        {
            declarationParameters.addAll(parseFunctionParameters());
        }

        // Consume parameters right parenthesis
        consume(SQUARE_RIGHT_PARENTHESES);

        // Consume function block left parenthesis
        consume(ROUND_LEFT_PARENTHESES);

        // Consume function body
        var block = parseBlock();
        if(isVoid)
        {
            consume(RETURN);
            consume(DOLLAR);
            consume(ROUND_RIGHT_PARENTHESES);
            consume(DOLLAR);
        }



        // Return expression
//        var returnExpression = parseExpression();

        // Consume semicolon


        // Consume function block right parenthesis


        return new DeclarationFunctionAssign(
                name,
                functionType.getSpelling(),
                declarationParameters,
                block
//                returnExpression
        );


//        throw new RuntimeException("Unexpected type of declaration. Received: '" + currentToken.getKind() + "'.");
    }



    private Identifier parseIdentifier() {
        if (isExpected(IDENTIFIER)) {
            var id = new Identifier(currentToken.getSpelling());

            next();

            return id;
        }

        throw new RuntimeException("Identifier expected.");
    }

    private Vector<DeclarationParameter> parseFunctionParameters() {
        var declarationParameters = new Vector<DeclarationParameter>();
        while (currentToken.getKind() != SQUARE_RIGHT_PARENTHESES)
        {
            var parameterType = currentToken.getKind();
            if(parameterType != TokenKind.BOOL && parameterType != TokenKind.STRING && parameterType != TokenKind.NUMBER)
            {
                throw new RuntimeException("Parameter of the function cannot be equal to the type: " + parameterType);
            }
            consume(parameterType);
            var identifier = currentToken;
            consume(identifier.getKind());
            declarationParameters.add(new DeclarationParameter(new Identifier(identifier.getSpelling()), parameterType));
            if(currentToken.getKind() != SQUARE_RIGHT_PARENTHESES)
            {
                consume(COMMA);
            }
        }

        return declarationParameters;
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
