package de.vandermeer.asciitable.v2;

import org.junit.Test;

import de.vandermeer.asciitable.v2.core.WidthByAbsolute;
import de.vandermeer.asciitable.v2.themes.E_TableThemes;

public class TestMe2 {

	@Test
	public void testMe(){
//		System.out.println(E_TableThemes.UTF_LIGHT.get().toDoc());
////		System.out.println(E_RowThemes.UTF_HEAVY_CONTENT.get().toDoc());

		AsciiTableRenderer rend = new AsciiTableRenderer();
		rend.setTheme(E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthByAbsolute().setWidth(73));

		AsciiTable at = null;

		at = new AsciiTable(1);
		at.addRuleStrong();
		at.addRow("Table Heading");
		at.addRule();
		at.addRow("first row (col1)");
		at.addRule();
		at.addRow("second row (col1)");
		at.addRule();
		System.out.println(rend.render(at));

		at = new AsciiTable(2);
		at.addRuleStrong();
		at.addRow(null,"Table Heading");
		at.addRule();
		at.addRow("first row (col1)", "with some information");
		at.addRule();
		at.addRow("second row (col1)", "with some information (col2)");
		at.addRule();
		System.out.println(rend.render(at));

		at = new AsciiTable(3);
		at.addRuleStrong();
		at.addRow(null, null, "Table Heading");
		at.addRule();
		at.addRow("first row (col1)", "with some information", "and more information");
		at.addRule();
		at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)");
		at.addRule();
		System.out.println(rend.render(at));

		at = new AsciiTable(4);
		at.addRuleStrong();
		at.addRow(null, null, null, "Table Heading");
		at.addRule();
		at.addRow("first row (col1)", "with some information", "and more information", "even more");
		at.addRule();
		at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)", "even more");
		at.addRule();
		System.out.println(rend.render(at));

		at = new AsciiTable(5);
		at.addRuleStrong();
		at.addRow(null, null, null, null, "Table Heading");
		at.addRule();
		at.addRow("first row (col1)", "with some information", "and more information", "even more", "more");
		at.addRule();
		at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)", "even more", "more");
		at.addRule();
		System.out.println(rend.render(at));

//		for(E_RowThemes t : E_RowThemes.values()){
//			System.out.println(t.get().toDoc());
//		}
//
//		for(E_TableThemes t : E_TableThemes.values()){
//			System.out.println(t.get().toDoc());
//		}
	}
}
