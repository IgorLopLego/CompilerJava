package parser;

import scanner.Scanner;
import scanner.Token;
import scanner.TokenKind;

import java.util.Currency;

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
               break;

       }
    }

    private void parseDeclaration(){
        switch (currentToken.getKind())
        {
            case NUMBER:
                accept(NUMBER);
                continueEnumeration(NUMBER);
               while (currentToken.getKind() == COMMA)
               {
                   accept(COMMA);
                  continueEnumeration(NUMBER);
               }
               accept(DOLLAR);
               break;
            case STRING:
                accept(STRING);
                continueEnumeration(STRING);
                while (currentToken.getKind() == COMMA){
                    accept(COMMA);
                    continueEnumeration(STRING);
                }
                accept(DOLLAR);
                break;
            case BOOL:
                accept(BOOL);
                continueEnumeration(BOOL);
                while (currentToken.getKind() == COMMA)
                {
                    accept(COMMA);
                    continueEnumeration(BOOL);
                }
                accept(DOLLAR);
                break;
            case SEQUENCE:
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
                break;
            case EXEFUNC:
                accept(EXEFUNC);
                acceptReturnFuncReturnType();
                accept(IDENTIFIER);
                accept(LEFTPARAN);
                // to describe all possible solutions of the function
                accept(RETURN);
                accept(DOLLAR);
                accept(RIGHTPARAN);
                break;
        }
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

    private void continueEnumeration(TokenKind tokenKind) {
        accept(IDENTIFIER);
        accept(ASSIGN);
        if (tokenKind == NUMBER) {
            accept(INTEGERLITERAL);
        } else if (tokenKind == STRING) {
            accept(STRINGLITERAL);
        } else if (tokenKind == BOOL) {
            accept(BOOLLITERAL);
        }
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
