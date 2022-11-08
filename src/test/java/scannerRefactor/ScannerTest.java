package scannerRefactor;

import org.junit.jupiter.api.Test;
import scanner.Source;
import scanner.Token;
import utils.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static scanner.TokenKind.*;

import java.util.ArrayList;

class ScannerTest {
    private String getTestScopeExamplePath(String filePath) {
        return Path.getTestsDirWithPath("/scannerRefactor/examples/" + filePath);
    }

    @Test
    void shouldIgnoreComment() {
        var sourcePath = getTestScopeExamplePath("comment.stricty");
        var source = new Source(sourcePath);

        var scanner = new Scanner(source);
        var sourceTokens = scanner.scanSource();

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

        var scanner = new Scanner(source);
        var sourceTokens = scanner.scanSource();

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