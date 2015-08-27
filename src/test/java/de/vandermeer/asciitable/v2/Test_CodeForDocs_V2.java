/* Copyright 2014 Sven van der Meer <vdmeer.sven@mykolab.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.vandermeer.asciitable.v2;

import org.junit.Test;

import de.svenjacobs.loremipsum.LoremIpsum;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthAbsoluteEven;
import de.vandermeer.asciitable.v2.render.WidthFixedColumns;
import de.vandermeer.asciitable.v2.render.WidthLongestWord;
import de.vandermeer.asciitable.v2.render.WidthLongestWordMaxCol;
import de.vandermeer.asciitable.v2.render.WidthLongestWordMinCol;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

/**
 * Tests for ASCII table V2 for code used in documentation.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.2 build 150827 (27-Aug-15) for Java 1.7
 */
public class Test_CodeForDocs_V2 {

	@Test
	public void test_Example_SimpleTable(){
		V2_AsciiTable at = new V2_AsciiTable();

		at.addRule();
		at.addRow("first row (col1)", "with some information (col2)");
		at.addRule();
		at.addRow("second row (col1)", "with some information (col2)");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthAbsoluteEven(76));

		RenderedTable rt = rend.render(at);
		System.out.println("simple table");
		System.out.println(rt);
	}

	@Test
	public void test_Example_1Column(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow("Table Heading");
		at.addRule();
		at.addRow("first row (col1)");
		at.addRule();
		at.addRow("second row (col1)");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthAbsoluteEven(76));
		System.out.println("table 1 columns");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_2Columns(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow(null,"Table Heading");
		at.addRule();
		at.addRow("first row (col1)", "with some information (col2)");
		at.addRule();
		at.addRow("second row (col1)", "with some information (col2)");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthAbsoluteEven(76));
		System.out.println("table 2 columns");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_3Columns(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow(null, null, "Table Heading");
		at.addRule();
		at.addRow("first row (col1)", "with some information", "and more information");
		at.addRule();
		at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthAbsoluteEven(76));
		System.out.println("table 4 columns");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_4Columns(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow(null, null, null, "Table Heading");
		at.addRule();
		at.addRow("first row (col1)", "with some information", "and more information", "even more");
		at.addRule();
		at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)", "even more");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthAbsoluteEven(76));
		System.out.println("table 4 columns");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_5Columns(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow(null, null, null, null, "Table Heading");
		at.addRule();
		at.addRow("first row (col1)", "with some information", "and more information", "even more", "more");
		at.addRule();
		at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)", "even more", "more");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthAbsoluteEven(76));
		System.out.println("table 5 columns");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_ColSpanning(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow(null, null, null, null, "span all 5 columns");
		at.addRule();
		at.addRow(null, null, null, "span 4 columns", "just 1 column");
		at.addRule();
		at.addRow(null, null, "span 3 columns", null, "span 2 columns");
		at.addRule();
		at.addRow(null, "span 2 columns", null, null, "span 3 columns");
		at.addRule();
		at.addRow("just 1 column", null, null, null, "span 4 columns");
		at.addRule();
		at.addRow("just 1 column", "just 1 column", "just 1 column", "just 1 column", "just 1 column");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthAbsoluteEven(81));
		System.out.println("col spanning 1");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_PaddingChar(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow("some text with padding");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setWidth(new WidthAbsoluteEven(76));
		System.out.println("padding char 1");
		System.out.println(rend.render(at));
		rend.setPaddingChar('*');
		System.out.println("padding char 2");
		System.out.println(rend.render(at));
		rend.setPaddingChar('-');
		System.out.println("padding char 3");
		System.out.println(rend.render(at));
		rend.setPaddingChar('␣');
		System.out.println("padding char 4");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_TableThemes(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow("some column text");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setWidth(new WidthAbsoluteEven(76));
		System.out.println(rend.render(at));

		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		System.out.println("themes 1");
		System.out.println(rend.render(at));

		rend.setTheme(V2_E_TableThemes.UTF_DOUBLE_LIGHT.get());
		System.out.println("themes 2");
		System.out.println(rend.render(at));

		rend.setTheme(V2_E_TableThemes.UTF_DOUBLE.get());
		System.out.println("themes 3");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_Alignment_Std(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow("left", "right", "center").setAlignment(new char[]{'l', 'r', 'c'});
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthAbsoluteEven(80));
		System.out.println("align standard");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_Alignment_Justified(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow(new LoremIpsum().getWords()).setAlignment(new char[]{'j'});
		at.addRule();
		at.addRow(new LoremIpsum().getWords()).setAlignment(new char[]{'t'});
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthAbsoluteEven(60));
		System.out.println("align justified");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_Padding(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow("padding 0", "padding 1", "padding 2", "padding 3", "padding 4").setPadding(new int[]{0, 1, 2, 3, 4});
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthAbsoluteEven(76));
		System.out.println("padding");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_RuleStyle(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addStrongRule();
		at.addRow("col1", "col2", "col3");
		at.addStrongRule();
		at.addRow("col1", "col2", "col3");
		at.addRule();
		at.addRow("col1", "col2", "col3");
		at.addStrongRule();
		at.addRow("col1", "col2", "col3");
		at.addRule();
		at.addRow("col1", "col2", "col3");
		at.addStrongRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setWidth(new WidthAbsoluteEven(76));
		rend.setTheme(V2_E_TableThemes.ASC7_LATEX_STYLE_STRONG.get());
		System.out.println("rule style 1");
		System.out.println(rend.render(at));

		rend.setTheme(V2_E_TableThemes.ASC7_LATEX_STYLE_STRONG2.get());
		System.out.println("rule style 2");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_WidthAbsoluteEven(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow("col1", "col2", "col3");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());

		rend.setWidth(new WidthAbsoluteEven(50));
		System.out.println("absolute even 1");
		System.out.println(rend.render(at));

		rend.setWidth(new WidthAbsoluteEven(30));
		System.out.println("absolute even 2");
		System.out.println(rend.render(at));

		rend.setWidth(new WidthAbsoluteEven(20));
		System.out.println("absolute even 3");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_WidthFixedColumns(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow("col1", "col2", "col3");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());

		rend.setWidth(new WidthFixedColumns().add(10).add(20).add(30));
		System.out.println("fixed columns 1");
		System.out.println(rend.render(at));

		rend.setWidth(new WidthFixedColumns().add(5).add(10).add(15));
		System.out.println("fixed columns 2");
		System.out.println(rend.render(at));

		rend.setWidth(new WidthFixedColumns().add(3).add(5).add(7));
		System.out.println("fixed columns 3");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_WidthLongestWord(){
		V2_AsciiTable at = new V2_AsciiTable(0);
		at.addRule();
		at.addRow("first", "information");
		at.addRule();
		at.addRow("second", "info");
		at.addRule();
		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthLongestWord());
		System.out.println("longest word 1");
		System.out.println(rend.render(at));


		at = new V2_AsciiTable(1);
		at.addRule();
		at.addRow("first", "information");
		at.addRule();
		at.addRow("second", "info");
		at.addRule();
		rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthLongestWord());
		System.out.println("longest word 2");
		System.out.println(rend.render(at));


		at = new V2_AsciiTable(0);
		at.addRule();
		at.addRow("first", "information").setPadding(new int[]{2, 3});
		at.addRule();
		at.addRow("second", "info").setPadding(new int[]{3, 4});
		at.addRule();
		rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthLongestWord());
		System.out.println("longest word 3 - w/padding");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_WidthLongestWordMinCol(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow("first", "information");
		at.addRule();
		at.addRow("second", "info");
		at.addRule();
		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthLongestWordMinCol(11));
		System.out.println("longest word mincol 1");
		System.out.println(rend.render(at));

		at = new V2_AsciiTable();
		at.addRule();
		at.addRow("first", "information");
		at.addRule();
		at.addRow("second", "info");
		at.addRule();
		rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthLongestWordMinCol(new int[]{-1,50}));
		System.out.println("longest word mincol 2");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_WidthLongestWordMaxCol(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow("first", "information");
		at.addRule();
		at.addRow("second", "info");
		at.addRule();
		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthLongestWordMaxCol(10));
		System.out.println("longest word maxcol 1");
		System.out.println(rend.render(at));

		at = new V2_AsciiTable();
		at.addRule();
		at.addRow("first", "information");
		at.addRule();
		at.addRow("second", "info");
		at.addRule();
		rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthLongestWordMaxCol(new int[]{5,-1}));
		System.out.println("longest word maxcol 2");
		System.out.println(rend.render(at));
	}

	@Test
	public void test_Example_CondLineBreak(){
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow("request", "status", "causes");
		at.addRule();
		at.addRow("1", "invalid", "* size limit exceeded\n* invalid characters found");
		at.addRule();
		at.addRow("2", "valid", "* all requirements satisfied");
		at.addRule();
		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.PLAIN_7BIT.get());
		rend.setWidth(new WidthFixedColumns().add(10).add(10).add(40));

		System.out.println("conditional line break");
		System.out.println(rend.render(at));
	}
}
