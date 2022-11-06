import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void twoPlusTwoShouldEqualFour() {
        var calculator = new Calculator();
        assertEquals(4, calculator.add(2, 2));
    }
}