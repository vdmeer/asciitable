package de.vandermeer.asciitable;

import org.junit.Assert;
import org.junit.Test;

public class TestNullValueInRow {

  @Test
  public void testNullRowDefaultPlaceholder() {
    AsciiTable at = createDemoTable(new AT_Context());
    final String expected =
        "┌───────────────────────────────────────┬──────────────────────────────────────┐\n" +
        "│row 1 col 1                            │row 1 col 2                           │\n" +
        "├───────────────────────────────────────┼──────────────────────────────────────┤\n" +
        "│row 2 col 1                            │                                      │\n" +
        "└───────────────────────────────────────┴──────────────────────────────────────┘";

    Assert.assertEquals(expected, at.render());
  }

  @Test
  public void testNullRowCustomPlaceholder() {
    AsciiTable at = createDemoTable(new AT_Context().setNullValuePlaceholder("NULL"));
    final String expected =
        "┌───────────────────────────────────────┬──────────────────────────────────────┐\n" +
        "│row 1 col 1                            │row 1 col 2                           │\n" +
        "├───────────────────────────────────────┼──────────────────────────────────────┤\n" +
        "│row 2 col 1                            │NULL                                  │\n" +
        "└───────────────────────────────────────┴──────────────────────────────────────┘";

    Assert.assertEquals(expected, at.render());
  }

  private AsciiTable createDemoTable(final AT_Context context) {
    AsciiTable at = new AsciiTable(context);
    at.addRule();
    at.addRow("row 1 col 1", "row 1 col 2");
    at.addRule();
    at.addRow("row 2 col 1", null);
    at.addRule();
    return at;
  }
}
