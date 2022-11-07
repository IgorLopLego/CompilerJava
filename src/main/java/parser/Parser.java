package parser;

import scanner.Scanner;
import scanner.Token;
import scanner.TokenKind;

import static scanner.TokenKind.*;

public class Parser {
    private Scanner scanner;
    private Token currentToken;

   public Parser(Scanner scanner)
    {
        this.scanner = scanner;
        currentToken = scanner.scan();
    }

    public void parseProgram()
    {
        accept(START);
        declarationAndStatementsLoop();
        accept(END);
        if( currentToken.getKind() != NULL_TERMINATOR) {
            System.out.println("Tokens found after end of program");
        }
    }

    private void parseStatement(){
       switch (currentToken.getKind())
       {
           case SCREAM:
               accept(SCREAM);
               accept(LEFT_PARENTHESES);
               accept(STRING_LITERAL);
               accept(RIGHT_PARENTHESES);
               accept(DOLLAR);
               break;
           case FOR:
               accept(FOR);
               accept(LEFT_PARENTHESES);
               accept(NUMBER);
               accept(IDENTIFIER);
               accept(ASSIGN);
               accept(NUMBER_LITERAL);
               accept(COLON);
               accept(IDENTIFIER);
               acceptComparison();
               accept(NUMBER_LITERAL);
               accept(COLON);
               accept(IDENTIFIER);
               accept(OPERATOR);
               acceptIdentifierOrNumber();
               accept(RIGHT_PARENTHESES);
               accept(LEFT_PARENTHESES);
               accept(RIGHT_PARENTHESES);
               break;
           case SHOVE:
               accept(SHOVE);
               accept(LEFT_PARENTHESES);
               accept(RIGHT_PARENTHESES);
               accept(DOLLAR);
               break;
           case SWITCH:
               accept(SWITCH);
               accept(LEFT_PARENTHESES);
               accept(IDENTIFIER);
               accept(RIGHT_PARENTHESES);
               accept(COLON);
               accept(SWITCH_LEFT_PARENTHESES);
               while (currentToken.getKind() != SWITCH_RIGHT_PARENTHESES)
               {
                   acceptCase();
               }
               accept(SWITCH_RIGHT_PARENTHESES);
               accept(DOLLAR);
               break;
           case RETURN:
               accept(RETURN);
               acceptReturnArguments();
               accept(DOLLAR);
               break;

       }
    }

    private void parseAssignDeclaration(){
        if(currentToken.getKind() == NUMBER || currentToken.getKind() == STRING | currentToken.getKind() == BOOL)
        {
            oneDeclaration();

            while (currentToken.getKind() == COMMA)
            {
                accept(COMMA);
                oneDeclaration();
            }
            accept(DOLLAR);
        }
        else if(currentToken.getKind() == SEQUENCE)
        {
            accept(SEQUENCE);
            accept(IDENTIFIER);
            accept(ASSIGN);
            if(currentToken.getKind() == NUMBER)
            {
                accept(NUMBER);
            }
            else if(currentToken.getKind() == STRING)
            {
                accept(STRING);
            }
            else if (currentToken.getKind() == BOOL)
            {
                accept(BOOL);
            }
            else{
                accept(EXCEPTION);
            }
            accept(DOLLAR);
        }
    }

    private void parseDeclaration(){
      parseAssignDeclaration();
       if (currentToken.getKind() == FUNCTION)
       {
           accept(FUNCTION);
           acceptReturnFuncReturnType();
           accept(IDENTIFIER);
           accept(LEFT_PARENTHESES);
           while (currentToken.getKind() != RIGHT_PARENTHESES)
           {
              acceptArguments();
           }
           accept(RIGHT_PARENTHESES);
           accept(FUNCTION_LEFT_PARENTHESES);
           assignDeclarationsAndStatementsLoop();
           acceptReturnArguments();
           accept(FUNCTION_RIGHT_PARENTHESES);
       }
    }


    private void acceptReturnArguments(){
       if(currentToken.getKind() == IDENTIFIER || currentToken.getKind() == STRING_LITERAL || currentToken.getKind() == NUMBER_LITERAL || currentToken.getKind() == BOOL_LITERAL)
       {
           accept(currentToken.getKind());
       }
    }

    private void acceptArguments(){
       if(currentToken.getKind() == NUMBER || currentToken.getKind() == STRING || currentToken.getKind() == BOOL)
       {
           accept(currentToken.getKind());
           accept(IDENTIFIER);
           if(currentToken.getKind() != RIGHT_PARENTHESES) {
               accept(COMMA);
           }
       }
    }

    private void acceptCase(){
        accept(CASE);
        if(isLiteral(currentToken.getKind()))
        {
            accept(currentToken.getKind());
        }
        else{
            accept(EXCEPTION);
        }
        accept(COLON);
        assignDeclarationsAndStatementsLoop();


        accept(BREAK);
    }


    private void assignDeclarationsAndStatementsLoop(){
        while (isAssignDeclaration() || isAStatement())
        {
            if(isAssignDeclaration())
            {
                while (isAssignDeclaration())
                {
                   parseAssignDeclaration();
                }
            }
            if(isAStatement())
            {
                while (isAStatement())
                {
                    parseStatement();
                }
            }

        }
    }
    private void declarationAndStatementsLoop() {
        while (isADeclaration() || isAStatement())
        {
            if(isADeclaration())
            {
                while (isADeclaration())
                {
                    parseDeclaration();
                }
            }
            if(isAStatement())
            {
                while (isAStatement())
                {
                    parseStatement();
                }
            }

        }
    }


    private boolean isLiteral(TokenKind tokenKind)
    {
        return  tokenKind == BOOL_LITERAL || tokenKind == STRING_LITERAL || tokenKind == NUMBER_LITERAL;
    }
    private void acceptIdentifierOrNumber(){
       if(currentToken.getKind() == IDENTIFIER || currentToken.getKind() == NUMBER_LITERAL)
       {
           accept(currentToken.getKind());
       }
       else {
           accept(EXCEPTION);
       }
    }

    private void acceptComparison()
    {
        if(currentToken.getKind() == LESS || currentToken.getKind() == LESS_OR_EQUAL || currentToken.getKind() == EQUALS || currentToken.getKind() == MORE || currentToken.getKind() == MORE_OR_EQUAL)
        {
            accept(currentToken.getKind());
        }
        else {
            accept(EXCEPTION);
        }
    }

    private void acceptReturnFuncReturnType()
    {
        if(currentToken.getKind() == VOID || currentToken.getKind() == BOOL || currentToken.getKind() == STRING || currentToken.getKind() == NUMBER)
        {
            accept(currentToken.getKind());
        }
        else {
            accept(EXCEPTION);
        }

    }

    private void oneDeclaration() {
       TokenKind acceptedType = currentToken.getKind();
       if(acceptedType == NUMBER || acceptedType == BOOL || acceptedType == STRING)
       {
           accept(acceptedType);
       }
        accept(IDENTIFIER);
        accept(ASSIGN);
        while(true) {
            if (isValidDeclarationToTheTypeVariable(acceptedType)) {
                accept(currentToken.getKind());
                if(isCommaOrDollar())
                {
                    break;
                }
                accept(OPERATOR);
            }
            else if (acceptedType == IDENTIFIER)
            {
                accept(currentToken.getKind());
                if(isCommaOrDollar())
                {
                    break;
                }
                accept(OPERATOR);
            }
            else {
                accept(EXCEPTION);
                break;
            }
        }
    }

    public boolean isValidDeclarationToTheTypeVariable(TokenKind acceptedType){
       return (acceptedType == NUMBER && (currentToken.getKind() == IDENTIFIER || currentToken.getKind() == NUMBER_LITERAL)) || (acceptedType == BOOL && (currentToken.getKind() == IDENTIFIER || currentToken.getKind() == BOOL_LITERAL)) || (acceptedType == STRING && (currentToken.getKind() == IDENTIFIER || currentToken.getKind() == STRING_LITERAL));
    }

    public boolean isCommaOrDollar(){
       return currentToken.getKind() == COMMA || currentToken.getKind() == DOLLAR;
    }

    public boolean isAStatement(){
        return isAnExpression() || currentToken.getKind() == RETURN || currentToken.getKind() == SWITCH || currentToken.getKind() == FOR || currentToken.getKind() == SCREAM || currentToken.getKind() == SHOVE;
    }

    private boolean isADeclaration() {
        return isAssignDeclaration() || currentToken.getKind() == FUNCTION;
    }

    private boolean isAssignDeclaration(){
       return currentToken.getKind() == BOOL || currentToken.getKind() == STRING || currentToken.getKind() == NUMBER || currentToken.getKind() == SEQUENCE;
    }

    private boolean isAnExpression(){
        return currentToken.getKind() == TokenKind.IDENTIFIER || currentToken.getKind() == TokenKind.NUMBER_LITERAL || currentToken.getKind() == BOOL_LITERAL || currentToken.getKind() == TokenKind.OPERATOR || currentToken.getKind() == TokenKind.STRING_LITERAL;
    }


    private void accept( TokenKind expected )
    {
        if( currentToken.getKind() == expected )
        {
            System.out.println("Token successfully accepted " + currentToken.getKind());
            currentToken = scanner.scan();
        }
        else
            System.out.println( "Expected token of kind " + expected + " and actually got " + currentToken.getKind() );
    }
}
