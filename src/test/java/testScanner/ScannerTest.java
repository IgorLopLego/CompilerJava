package testScanner;

import org.junit.jupiter.api.Test;
import scanner.Scanner;
import scanner.token.Token;
import utils.Path;
import utils.Source;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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

        assertArrayEquals(testTokens.toArray(), sourceTokens.toArray());
    }

    @Test
    void shouldDeclareVariable() {
        var sourcePath = getTestScopeExamplePath("variableDeclaration.stricty");
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
}