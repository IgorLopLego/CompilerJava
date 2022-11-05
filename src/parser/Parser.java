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
        DeclarationAndStatementloop();
        accept(END);
        if( currentToken.getKind() != NULLTERMINANT) {
            System.out.println("Tokens found after end of program");
        }
    }

    private void parseStatement(){
       switch (currentToken.getKind())
       {
           case SCREAM:
               accept(SCREAM);
               accept(LEFTPARAN);
               accept(STRINGLITERAL);
               accept(RIGHTPARAN);
               accept(DOLLAR);
               break;
           case FOR:
               accept(FOR);
               accept(LEFTPARAN);
               accept(NUMBER);
               accept(IDENTIFIER);
               accept(ASSIGN);
               accept(INTEGERLITERAL);
               accept(SEMICOLUMN);
               accept(IDENTIFIER);
               acceptComparison();
               accept(INTEGERLITERAL);
               accept(SEMICOLUMN);
               accept(IDENTIFIER);
               accept(OPERATOR);
               acceptIdentifierOrNumber();
               accept(RIGHTPARAN);
               accept(LEFTPARAN);
               accept(RIGHTPARAN);
               break;
           case SHOVE:
               accept(SHOVE);
               accept(LEFTPARAN);
               accept(RIGHTPARAN);
               accept(DOLLAR);
               break;
           case SWITCH:
               accept(SWITCH);
               accept(LEFTPARAN);
               accept(IDENTIFIER);
               accept(RIGHTPARAN);
               accept(SEMICOLUMN);
               accept(SWITCHLEFTPARAM);
               while (currentToken.getKind() != SWITCHRIGHTPARAM)
               {
                   acceptCase();
               }
               accept(SWITCHRIGHTPARAM);
               accept(DOLLAR);
       }
    }

    private void parseDeclaration(){
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
        else if (currentToken.getKind() == EXEFUNC)
       {
           accept(EXEFUNC);
           acceptReturnFuncReturnType();
           accept(IDENTIFIER);
           accept(LEFTPARAN);
           // to describe all possible solutions of the function
           accept(RETURN);
           accept(DOLLAR);
           accept(RIGHTPARAN);
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
        accept(SEMICOLUMN);
        DeclarationAndStatementloop();


        accept(BREAK);
    }

    private void DeclarationAndStatementloop() {
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
        return  tokenKind == BOOLLITERAL || tokenKind == STRINGLITERAL || tokenKind == INTEGERLITERAL;
    }
    private void acceptIdentifierOrNumber(){
       if(currentToken.getKind() == IDENTIFIER || currentToken.getKind() == INTEGERLITERAL)
       {
           accept(currentToken.getKind());
       }
       else {
           accept(EXCEPTION);
       }
    }

    private void acceptComparison()
    {
        if(currentToken.getKind() == LESS || currentToken.getKind() == LESSOREQUAL || currentToken.getKind() == EQUALS || currentToken.getKind() == MORE || currentToken.getKind() == MOREOREQUAL)
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
       return (acceptedType == NUMBER && (currentToken.getKind() == IDENTIFIER || currentToken.getKind() == INTEGERLITERAL)) || (acceptedType == BOOL && (currentToken.getKind() == IDENTIFIER || currentToken.getKind() == BOOLLITERAL)) || (acceptedType == STRING && (currentToken.getKind() == IDENTIFIER || currentToken.getKind() == STRINGLITERAL));
    }

    public boolean isCommaOrDollar(){
       return currentToken.getKind() == COMMA || currentToken.getKind() == DOLLAR;
    }

    public boolean isAStatement(){
        return isAnExpression() || currentToken.getKind() == SWITCH || currentToken.getKind() == FOR || currentToken.getKind() == SCREAM || currentToken.getKind() == SHOVE;
    }

    private boolean isADeclaration() {
        return currentToken.getKind() == BOOL || currentToken.getKind() == EXEFUNC || currentToken.getKind() == STRING || currentToken.getKind() == NUMBER || currentToken.getKind() == SEQUENCE;
    }

    private boolean isAnExpression(){
        return currentToken.getKind() == TokenKind.IDENTIFIER || currentToken.getKind() == TokenKind.INTEGERLITERAL || currentToken.getKind() == BOOLLITERAL || currentToken.getKind() == TokenKind.OPERATOR || currentToken.getKind() == TokenKind.STRINGLITERAL;
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
