package de.vandermeer.asciitable.v2;

import org.junit.Test;

import de.vandermeer.asciitable.v2.core.WidthByAbsolute;
import de.vandermeer.asciitable.v2.themes.E_TableThemes;

public class TestMe2 {

	@Test
	public void testMe(){
		System.out.println(E_TableThemes.UTF_LIGHT.get().toDoc());
//		System.out.println(E_RowThemes.UTF_HEAVY_CONTENT.get().toDoc());

		AsciiTable at = new AsciiTable(3);
		at.addRuleStrong();
		at.addRow(null, null, "Table Heading");
		at.addRule();
		at.addRow("first row (col1)", "with some information", "and more information");
		at.addRule();
		at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)");
		at.addRule();

		AsciiTableRenderer rend = new AsciiTableRenderer();
		rend.setTheme(E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthByAbsolute().setWidth(76));
		RenderedAsciiTable rat = rend.render(at);

		System.out.println(rat);
	}
}
