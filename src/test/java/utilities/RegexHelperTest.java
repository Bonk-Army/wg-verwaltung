package utilities;

import static utilities.RegexHelper.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class RegexHelperTest {

    /**
     * Positive and negative tests for RegexHelper checkString()
     */
    @Test
    public void checkStringTest() {
        assertTrue(checkString("toCheck"));
        assertTrue(checkString("Exclamationmark!"));
        assertTrue(checkString("-"));
        assertTrue(checkString("_"));

        assertFalse(checkString("Semikolon;"));
        assertFalse(checkString("Questionmark?"));
        assertFalse(checkString("And&"));
        assertFalse(checkString("Percent%"));
        assertFalse(checkString("Slash/"));
        assertFalse(checkString("Escape\\"));
        assertFalse(checkString("'"));
        assertFalse(checkString("<"));
        assertFalse(checkString(">"));
        assertFalse(checkString("Space Test"));
    }

    /**
     * Positive and negative tests for RegexHelper checkText()
     */
    @Test
    public void checkTextTest() {
        assertTrue(checkText("toCheck"));
        assertTrue(checkText("Exclamationmark!"));
        assertTrue(checkText("Questionmark?"));
        assertTrue(checkText("And&"));
        assertTrue(checkText("Percent%"));
        assertTrue(checkText("Slash/"));
        assertTrue(checkText("Space Test"));
        assertTrue(checkText("-"));
        assertTrue(checkText("_"));

        assertFalse(checkText("Semikolon;"));
        assertFalse(checkText("Escape\\"));
        assertFalse(checkText("'"));
        assertFalse(checkText("<"));
        assertFalse(checkText(">"));
    }

    /**
     * Positive and negative tests for RegexHelper checkEmail()
     */
    @Test
    public void checkEmailTest() {
        assertTrue(checkEmail("toCheck@something.xy.de"));
        assertTrue(checkEmail("toCheck@something.de"));

        assertFalse(checkEmail("toChecksomething.de"));
        assertFalse(checkEmail("toCheck@somethingde"));
        assertFalse(checkEmail("toChecksomethingde"));
    }



}
