package scannerRefactor;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import scanner.Source;
import scanner.Token;
import static scanner.TokenKind.*;

import java.util.ArrayList;

class ScannerTest {
    @Test
    void shouldIgnoreComment() {
        var filePath = System.getProperty("user.dir") + "/src/test/java/scannerRefactor/examples/comment.stricty";
        var source = new Source(filePath);

        var scanner = new Scanner(source);
        var sourceTokens = scanner.scanSource();

        var testTokens = new ArrayList<Token>() {
            {
                add(new Token(START, "start"));
                add(new Token(END, "end"));
            }
        };

        Assert.assertArrayEquals(testTokens.toArray(), sourceTokens.toArray());
    }

    @Test
    void shouldDeclareVariable() {
        var filePath = System.getProperty("user.dir") + "/src/test/java/scannerRefactor/examples/variableDeclaration.stricty";
        var source = new Source(filePath);

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

        Assert.assertArrayEquals(testTokens.toArray(), sourceTokens.toArray());
    }
}