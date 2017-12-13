package de.vandermeer.asciitable;


import org.junit.Assert;
import org.junit.Test;

import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

public class Test_ColumnSpan {
	@Test
	public void test_redering_last_null(){
		
		final String expected = 
				"┌──────────────────────────┬───────────────────────────────────────────────────┐\n" +
				"│          text1           │                       text2                       │\n" +
				"├──────────────────────────┼─────────────────────────┬─────────────────────────┤\n" +
				"│          text0           │          text1          │          text2          │\n" +
				"└──────────────────────────┴─────────────────────────┴─────────────────────────┘";
		
		final AsciiTable at = new AsciiTable();    
	    at.addRule();		
		at.addRow("text1", "text2", null);		
	    at.addRule();
		at.addRow("text0", "text1", "text2");
	    at.addRule();
		at.setTextAlignment(TextAlignment.CENTER);
		Assert.assertEquals(expected, at.render());
		
		
	}
}
