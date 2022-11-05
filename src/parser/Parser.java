package parser;

import scanner.Scanner;
import scanner.Token;
import scanner.TokenKind;

import static scanner.Source.EOT;
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
        }
        accept(END);
        if( currentToken.getKind() != NULLTERMINANT) {
            System.out.println("Tokens found after end of program");
        }
    }

    private void parseDeclaration(){
        switch (currentToken.getKind())
        {
            case NUMBER:
                accept(NUMBER);
                accept(IDENTIFIER);
                accept(ASSIGN);
                accept(INTEGERLITERAL);
                accept(DOLLAR);
                break;
            case STRING:
                accept(STRING);
                accept(IDENTIFIER);
                accept(ASSIGN);
                accept(STRINGLITERAL);
                accept(DOLLAR);
                break;
            case BOOL:
                accept(BOOL);
                accept(IDENTIFIER);
                accept(ASSIGN);
                if(currentToken.getKind() == TRUE)
                {
                    accept(TRUE);
                }
                else if(currentToken.getKind() == FALSE)
                {
                    accept(FALSE);
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
                accept(IDENTIFIER);
                accept(LEFTPARAN);
                // to describe all possible solutions of the function
                accept(RETURN);
                accept(DOLLAR);
                accept(RIGHTPARAN);
                break;
        }
    }


    private void parseDeclarations(){
        while(isADeclaration())
        {
            switch (currentToken.getKind())
            {
                case NUMBER:
                    accept(NUMBER);
                    accept(IDENTIFIER);
                    accept(DOLLAR);
                    break;
            }
        }
    }



    public boolean isAStatement(){
        return isAnExpression() || currentToken.getKind() == SWITCH || currentToken.getKind() == FOR || currentToken.getKind() == TERNARY || currentToken.getKind() == SCREAM || currentToken.getKind() == SHOVE;
    }

    private boolean isADeclaration() {
        return currentToken.getKind() == BOOL || currentToken.getKind() == EXEFUNC || currentToken.getKind() == STRING || currentToken.getKind() == NUMBER || currentToken.getKind() == SEQUENCE;
    }

    private boolean isAnExpression(){
        return currentToken.getKind() == TokenKind.IDENTIFIER || currentToken.getKind() == TokenKind.INTEGERLITERAL || currentToken.getKind() == TokenKind.TRUE || currentToken.getKind() == TokenKind.FALSE || currentToken.getKind() == TokenKind.OPERATOR || currentToken.getKind() == TokenKind.STRINGLITERAL;
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
