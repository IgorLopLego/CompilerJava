package testParser;
import org.junit.jupiter.api.Test;
import parser.Parser;
import scanner.Scanner;
import scanner.token.Token;
import utils.Path;
import utils.Source;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ParserTest {
    private String getTestScopeExamplePath(String filePath) {
        return Path.getTestsDirWithPath("/testParser/examples/" + filePath);
    }


    @Test
    void checkAnEmptyProgram()
    {
        List<Token> tokens = getTokens("emptyPgorgam.stricty");
        var parser = new Parser();
        assertDoesNotThrow(() -> parser.parse(tokens));

    }

    @Test
    void checkIfTheProgramMissesStart()
    {
        List<Token> tokens = getTokens("noStart.stricty");
        var parser = new Parser();
        assertThrows(RuntimeException.class, () -> parser.parse(tokens));
    }

    @Test
    void checkCorrectNumberDeclaration()
    {
        List<Token> tokens = getTokens("correctNumberAssignDeclaration.stricty");
        var parser = new Parser();
        assertDoesNotThrow(() -> parser.parse(tokens));
    }

    private List<Token> getTokens(String filePath) {
        var sourcePath = getTestScopeExamplePath(filePath);
        var source = new Source(sourcePath);
        var scanner = new Scanner();
        return scanner.scan(source);
    }

    @Test
    void checkIncorrectNumberDeclaration()
    {
        List<Token> tokens = getTokens("incorrectNumberAssignDeclaration.stricty");
        var parser = new Parser();
        assertThrows(RuntimeException.class, () -> parser.parse(tokens));
    }

    @Test
    void checkIncorrectNumberDeclarationWhenAssignedNotNumberLiteral()
    {
        List<Token> tokens = getTokens("incorrectNumberAssignDeclarationNotNumberLiteral.stricty");
        var parser = new Parser();
        assertThrows(RuntimeException.class, () -> parser.parse(tokens));
    }

    @Test
    void checkCorrectBooleanDeclaration(){
        List<Token> tokens = getTokens("booleanCorrectDeclaration.stricty");
        var parser = new Parser();
        assertDoesNotThrow(() -> parser.parse(tokens));
    }

    @Test
    void checkIncorrectBooleanDeclarationNoAssign(){
        List<Token> tokens = getTokens("booleanIncorrectDeclarationNoAssign.stricty");
        var parser = new Parser();
        assertThrows(RuntimeException.class, () -> parser.parse(tokens));
    }

    @Test
    void checkIncorrectBooleanDeclarationIncorrectLiteral(){
        List<Token> tokens = getTokens("booleanIncorrectDeclarationAssignedNotBooleanLiteral.stricty");
        var parser = new Parser();
        assertThrows(RuntimeException.class, () -> parser.parse(tokens));
    }

    @Test
    void checkCorrectStringDeclaration(){
        List<Token> tokens = getTokens("stringCorrectDeclaration.stricty");
        var parser = new Parser();
        assertDoesNotThrow(() -> parser.parse(tokens));
    }

    @Test
    void checkIncorrectStringAssigning(){
        List<Token> tokens = getTokens("stringIncorrectAssigningContent.stricty");
        var parser = new Parser();
        assertThrows(RuntimeException.class, () -> parser.parse(tokens));
    }

    //TODO Found a bug, if the datatype is incorrect the compiler is running infinitely
    //So sstring igor # 'dsa' $ fwould lead to the freeze of the program
//    @Test
//    void checkTypoInStringDeclaration(){
//        List<Token> tokens = getTokens("stringTypoInTypeDeclaration.stricty");
//        var parser = new Parser();
//        assertThrows(RuntimeException.class, () -> {
//            parser.parse(tokens);
//        });
//    }

    @Test
    void checkScreamIsCorrect(){
        List<Token> tokens = getTokens("scream.stricty");
        var parser = new Parser();
        assertDoesNotThrow(() -> parser.parse(tokens));
    }

    @Test
    void checkDeclarationWithout$(){
        List<Token> tokens = getTokens("declarationWithout$InTheEnd.stricty");
        var parser = new Parser();
        assertThrows(RuntimeException.class, () -> parser.parse(tokens));
    }

    @Test
    void checkTheFunctionDeclaration(){
        List<Token> tokens = getTokens("correctFunctionDeclaration.stricty");
        var parser = new Parser();
        assertThrows(RuntimeException.class, () -> parser.parse(tokens));
    }

    @Test
    void checkTheFunctionWithoutReturnStatement(){
        List<Token> tokens = getTokens("functionWithoutReturnStatement.stricty");
        var parser = new Parser();
        assertThrows(RuntimeException.class, () -> parser.parse(tokens));
    }

    @Test
    void checkTheFullyFunctioningFunction(){
        List<Token> tokens = getTokens("functionWithDeclarationsAnd StatementsInside.stricty");
        var parser = new Parser();
        assertDoesNotThrow(() -> parser.parse(tokens));
    }











}
