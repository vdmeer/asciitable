package de.vandermeer.asciitable.v2;

import java.util.List;

import org.apache.commons.lang3.text.StrBuilder;
import org.junit.Test;

import de.vandermeer.asciitable.v2.Table;
import de.vandermeer.asciitable.v2.TableRowRenderer;
import de.vandermeer.asciitable.v2.themes.SimpleThemes;

public class TestMe {

	@Test
	public void testMe(){
//		System.out.println(SimpleThemes.PLAIN_7BIT.toDoc());
//		System.out.println(SimpleThemes.DOUBLE.toDoc());


		Table at=new Table(3);
		at.addMidRule();
		at.addRow(null, null, "Table Heading");
		at.addMidRule();
		at.addRow("first row (col1)", "with some information", "and more information");
		at.addMidRule();
		at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)");
		at.addMidRule();

//		System.err.println(at);

		TableRowRenderer rend = new TableRowRenderer(at.getColumnCount(), 76);
		rend.setTheme(SimpleThemes.DOUBLE);
		List<StrBuilder> list=rend.render(at.table);

		for(StrBuilder sb : list){
			System.out.println(sb.toString());
		}




		//System.err.println(at.render());

//		at.toList(76);
//		System.err.println(at);
//
//		Integer[] columns=new Integer[]{10, 15, 20};
//		at.toList(columns);

//		System.err.println(at);
	}
}
