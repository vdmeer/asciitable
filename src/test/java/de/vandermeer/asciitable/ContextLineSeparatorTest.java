package de.vandermeer.asciitable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContextLineSeparatorTest {

    private static final String ROW_CHAR = "│";
    private static final String EXAMPLE_COL_NAME = "Col";
    private static final AT_ColumnWidthCalculator CWC = new CWC_FixedWidth().add(EXAMPLE_COL_NAME.length());
    private static final String DEFAULT_LINE_SEPARATOR = System.lineSeparator();

    private AT_Context context;

    private AsciiTable underTest;

    @Before
    public void setUp() {
        underTest = createUnderTest();
    }

    @Test
    public void shouldUseSystemLineSeparatorPerDefault() {
        assertRendererUsesLineSeparator(DEFAULT_LINE_SEPARATOR);
    }

    @Test
    public void mustNotAcceptEmptySeparator() {
        setupCustomLineSeparator("");

        assertRendererUsesLineSeparator(DEFAULT_LINE_SEPARATOR);
    }

    @Test
    public void mustNotAcceptMultipleSpacesAsSeparator() {
        setupCustomLineSeparator("   ");

        assertRendererUsesLineSeparator(DEFAULT_LINE_SEPARATOR);
    }

    @Test
    public void mustNotAcceptNullAsSeparator() {
        setupCustomLineSeparator(null);

        assertRendererUsesLineSeparator(DEFAULT_LINE_SEPARATOR);
    }

    @Test
    public void mustAcceptNonEmptyStringAsSeparator() {
        assertContextAcceptsCustomLineSeparator("SEP");
    }

    @Test
    public void mustAcceptNewLineAsSeparator() {
        assertContextAcceptsCustomLineSeparator("\n");
    }

    /*
     * ##### Start private helper #####
     */

    private AsciiTable createUnderTest() {
        context = new AT_Context();
        AsciiTable table = new AsciiTable(context);
        table.getRenderer().setCWC(CWC);

        table.addRow(EXAMPLE_COL_NAME);
        table.addRow(EXAMPLE_COL_NAME);

        return table;
    }

    private void setupCustomLineSeparator(String customLineSeparator) {
        context.setLineSeparator(customLineSeparator);
    }

    private void assertContextAcceptsCustomLineSeparator(String customLineSeparator) {
        setupCustomLineSeparator(customLineSeparator);

        assertRendererUsesLineSeparator(customLineSeparator);
    }

    private void assertRendererUsesLineSeparator(String expectedLineSeparator) {
        String actualResult = underTest.render();
        String expectedRow = ROW_CHAR + EXAMPLE_COL_NAME + ROW_CHAR;
        Assert.assertEquals(expectedRow + expectedLineSeparator + expectedRow, actualResult);
    }
}
