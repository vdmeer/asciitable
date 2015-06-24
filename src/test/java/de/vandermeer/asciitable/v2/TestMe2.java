package de.vandermeer.asciitable.v2;

import org.junit.Test;

public class TestMe2 {

	@Test
	public void testMe(){
		System.out.println(E_TableThemes.UTF_HEAVY.get().toDoc());
//		System.out.println(E_RowThemes.UTF_HEAVY_CONTENT.get().toDoc());

		AsctiiTable at = new AsctiiTable(3);
		at.addRuleStrong();
		at.addRow(null, null, "Table Heading");
		at.addRule();
		at.addRow("first row (col1)", "with some information", "and more information");
		at.addRule();
		at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)");
		at.addRule();

		AsciiTableRenderer rend = new AsciiTableRenderer();
		rend.setTheme(E_TableThemes.UTF_HEAVY.get());
		rend.setWidth(new WidthByAbsolute().setWidth(76));
		AsciiTableRendered rat = rend.render(at);

		System.out.println(rat);
	}
}
