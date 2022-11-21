package testScanner;

import org.junit.jupiter.api.Test;
import scanner.Scanner;
import scanner.token.Token;
import utils.Path;
import utils.Source;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;
import static scanner.token.TokenKind.*;

import java.util.ArrayList;
import java.util.List;

class ScannerTest {
    private String getTestScopeExamplePath(String filePath) {
        return Path.getTestsDirWithPath("/testScanner/examples/" + filePath);
    }

    private List<Token> getTokensFromSource(Source source) {
        var scanner = new Scanner();

        return scanner.scan(source);
    }




    @Test
    void shouldIgnoreComment() {
        var sourcePath = getTestScopeExamplePath("comment.stricty");
        var source = new Source(sourcePath);

        var sourceTokens = getTokensFromSource(source);

        var testTokens = new ArrayList<Token>() {
            {
                add(new Token(START, "start"));
                add(new Token(END, "end"));
            }
        };
        assertEquals(2, sourceTokens.toArray().length);
        assertArrayEquals(testTokens.toArray(), sourceTokens.toArray());
    }

    @Test
    void shouldNotReadAnythingAfterMultipleCommentSign(){
        var sourcePath = getTestScopeExamplePath("multipleLineComment.stricty");
        var source = new Source(sourcePath);

        var sourceTokens = getTokensFromSource(source);

        var testTokens = new ArrayList<Token>() {
            {
                add(new Token(START, "start"));
                add(new Token(END, "end"));
            }
        };
        assertEquals(2, sourceTokens.toArray().length);
        assertArrayEquals(testTokens.toArray(), sourceTokens.toArray());
    }



    @Test
    void shouldDeclareVariable() {
        var sourcePath = getTestScopeExamplePath("numberDeclaration.stricty");
        var source = new Source(sourcePath);

        var sourceTokens = getTokensFromSource(source);

        var testTokens = new ArrayList<Token>() {
            {
                add(new Token(START, "start"));
                add(new Token(NUMBER, "number"));
                add(new Token(IDENTIFIER, "numberFour"));
                add(new Token(ASSIGN, "#"));
                add(new Token(NUMBER_LITERAL, "4"));
                add(new Token(DOLLAR, "$"));
                add(new Token(END, "end"));
            }
        };

        assertArrayEquals(testTokens.toArray(), sourceTokens.toArray());
    }

    @Test
    void shouldDeclareBooleanVariable() {
        var sourcePath = getTestScopeExamplePath("booleanCorrectDeclaration.stricty");
        var source = new Source(sourcePath);

        var sourceTokens = getTokensFromSource(source);

        var testTokens = new ArrayList<Token>() {
            {
                add(new Token(START, "start"));
                add(new Token(BOOL, "boolean"));
                add(new Token(IDENTIFIER, "isTrue"));
                add(new Token(ASSIGN, "#"));
                add(new Token(BOOL_LITERAL, "true"));
                add(new Token(DOLLAR, "$"));
                add(new Token(END, "end"));
            }
        };

        assertArrayEquals(testTokens.toArray(), sourceTokens.toArray());
    }

    @Test
    void shouldDeclareStringVariable() {
        var sourcePath = getTestScopeExamplePath("stringCorrectDeclaration.stricty");
        var source = new Source(sourcePath);

        var sourceTokens = getTokensFromSource(source);

        var testTokens = new ArrayList<Token>() {
            {
                add(new Token(START, "start"));
                add(new Token(STRING, "string"));
                add(new Token(IDENTIFIER, "igorsName"));
                add(new Token(ASSIGN, "#"));
                add(new Token(STRING_LITERAL, "igorsName"));
                add(new Token(DOLLAR, "$"));
                add(new Token(END, "end"));
            }
        };

        assertArrayEquals(testTokens.toArray(), sourceTokens.toArray());
    }

    @Test
    void shouldDetectScream(){
        var sourcePath = getTestScopeExamplePath("scream.stricty");
        var source = new Source(sourcePath);

        var sourceTokens = getTokensFromSource(source);

        var testTokens = new ArrayList<Token>() {
            {
                add(new Token(START, "start"));
                add(new Token(SCREAM, "scream"));
                add(new Token(LEFT_PARENTHESES, "["));
                add(new Token(STRING_LITERAL, "hello world"));
                add(new Token(RIGHT_PARENTHESES, "]"));
                add(new Token(DOLLAR, "$"));
                add(new Token(END, "end"));
            }
        };

        assertArrayEquals(testTokens.toArray(), sourceTokens.toArray());
    }

    @Test
    void shouldDetectShove(){
        var sourcePath = getTestScopeExamplePath("shove.stricty");
        var source = new Source(sourcePath);

        var sourceTokens = getTokensFromSource(source);

        var testTokens = new ArrayList<Token>() {
            {
                add(new Token(START, "start"));
                add(new Token(SHOVE, "shove"));
                add(new Token(LEFT_PARENTHESES, "["));
                add(new Token(RIGHT_PARENTHESES, "]"));
                add(new Token(DOLLAR, "$"));
                add(new Token(END, "end"));
            }
        };

        assertArrayEquals(testTokens.toArray(), sourceTokens.toArray());
    }

    @Test
    void shouldDetectFunction(){
        var sourcePath = getTestScopeExamplePath("functionDeclaration.stricty");
        var source = new Source(sourcePath);

        var sourceTokens = getTokensFromSource(source);

        var testTokens = new ArrayList<Token>() {
            {
                add(new Token(START, "start"));
                add(new Token(FUNCTION, "exeFunc"));
                add(new Token(BOOL, "boolean"));
                add(new Token(IDENTIFIER, "igorsFunc"));
                add(new Token(LEFT_PARENTHESES, "["));
                add(new Token(NUMBER, "number"));
                add(new Token(IDENTIFIER, "one"));
                add(new Token(COMMA, ","));
                add(new Token(BOOL, "boolean"));
                add(new Token(IDENTIFIER, "two"));
                add(new Token(COMMA, ","));
                add(new Token(STRING, "string"));
                add(new Token(IDENTIFIER, "three"));
                add(new Token(RIGHT_PARENTHESES, "]"));
                add(new Token(FUNCTION_LEFT_PARENTHESES, "("));
                add(new Token(RETURN, "<-"));
                add(new Token(BOOL_LITERAL, "true"));
                add(new Token(DOLLAR, "$"));
                add(new Token(FUNCTION_RIGHT_PARENTHESES, ")"));
                add(new Token(END, "end"));
            }
        };

        assertArrayEquals(testTokens.toArray(), sourceTokens.toArray());
    }

    @Test
    void shouldThrowAnErrorOnAssigning(){
        var sourcePath = getTestScopeExamplePath("incorrectTokenOfAssigning.stricty");
        var source = new Source(sourcePath);

        assertTrue(hasException(source));
    }

    @Test
    void shouldDetectIfElse(){
        var sourcePath = getTestScopeExamplePath("conditionIfElse.stricty");
        var source = new Source(sourcePath);

        var sourceTokens = getTokensFromSource(source);
        var testTokens = new ArrayList<Token>() {
            {
                add(new Token(START, "start"));
                add(new Token(NUMBER, "number"));
                add(new Token(IDENTIFIER, "amount"));
                add(new Token(ASSIGN, "#"));
                add(new Token(NUMBER_LITERAL, "9"));
                add(new Token(DOLLAR, "$"));
                add(new Token(IF, "inCase"));
                add(new Token(LEFT_PARENTHESES, "["));
                add(new Token(IDENTIFIER, "amount"));
                add(new Token(EQUALS, "=="));
                add(new Token(NUMBER_LITERAL, "9"));
                add(new Token(RIGHT_PARENTHESES, "]"));
                add(new Token(FUNCTION_LEFT_PARENTHESES, "("));
                add(new Token(SCREAM, "scream"));
                add(new Token(LEFT_PARENTHESES, "["));
                add(new Token(STRING_LITERAL, "lala"));
                add(new Token(RIGHT_PARENTHESES, "]"));
                add(new Token(DOLLAR, "$"));
                add(new Token(FUNCTION_RIGHT_PARENTHESES, ")"));
                add(new Token(ELSE, "otherwise"));
                add(new Token(IF, "inCase"));
                add(new Token(LEFT_PARENTHESES, "["));
                add(new Token(IDENTIFIER, "amount"));
                add(new Token(EQUALS, "=="));
                add(new Token(NUMBER_LITERAL, "8"));
                add(new Token(RIGHT_PARENTHESES, "]"));
                add(new Token(FUNCTION_LEFT_PARENTHESES, "("));
                add(new Token(SCREAM, "scream"));
                add(new Token(LEFT_PARENTHESES, "["));
                add(new Token(STRING_LITERAL, "haha"));
                add(new Token(RIGHT_PARENTHESES, "]"));
                add(new Token(DOLLAR, "$"));
                add(new Token(FUNCTION_RIGHT_PARENTHESES, ")"));
                add(new Token(ELSE, "otherwise"));
                add(new Token(FUNCTION_LEFT_PARENTHESES, "("));
                add(new Token(SCREAM, "scream"));
                add(new Token(LEFT_PARENTHESES, "["));
                add(new Token(STRING_LITERAL, "igor"));
                add(new Token(RIGHT_PARENTHESES, "]"));
                add(new Token(DOLLAR, "$"));
                add(new Token(FUNCTION_RIGHT_PARENTHESES, ")"));
                add(new Token(END, "end"));
            }
        };

        assertArrayEquals(testTokens.toArray(), sourceTokens.toArray());
    }


     boolean hasException(Source source)
     {
         var sourceTokens = getTokensFromSource(source);

         boolean hasException = false;
         for (Token token: sourceTokens) {
             if (token.getKind() == EXCEPTION) {
                 hasException = true;
                 break;
             }
         }
         return hasException;
     }


    }

