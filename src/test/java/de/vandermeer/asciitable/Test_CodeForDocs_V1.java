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

package de.vandermeer.asciitable;

import org.junit.Test;

import de.vandermeer.asciitable.v1.V1_AsciiTable;
import de.vandermeer.asciitable.v1.V1_StandardTableThemes;

/**
 * Tests for ASCII table V1 for code used in documentation.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1-SNAPSHOT build 150812 (12-Aug-15) for Java 1.7
 */
public class Test_CodeForDocs_V1 {

	@Test
	public void test_Readme_Example1(){
		V1_AsciiTable at = V1_AsciiTable.newTable(3, 76);
		at.addRow(null, null, "Table Heading");
		at.addRow("first row (col1)", "with some information", "and more information");
		at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)");
//		System.err.println(at.render());
	}

	@Test
	public void test_Readme_Example2(){
		Integer[] columns = new Integer[]{10, 15, 20};
		V1_AsciiTable at = V1_AsciiTable.newTable(columns);
		at.addRow(null, null, "Table Heading");
		at.addRow("row 1", "this is col 2", "and this is column 3");
		at.addRow("row 2", "some text for column 2", "and some text for column 3");

		at.setTheme(V1_StandardTableThemes.LIGHT);
		at.render();
//		System.err.println(at.render());

		at.setTheme(V1_StandardTableThemes.LIGHT);
		at.render();
//		System.err.println(at.render());

		at.setTheme(V1_StandardTableThemes.DOUBLE);
		at.render();
//		System.err.println(at.render());

		at.setTheme(V1_StandardTableThemes.LIGHT_DOUBLE);
		at.render();
//		System.err.println(at.render());

		at.setTheme(V1_StandardTableThemes.DOUBLE_LIGHT);
		at.render();
//		System.err.println(at.render());

		at.setTheme(V1_StandardTableThemes.HEAVY);
		at.render();
//		System.err.println(at.render());

		at.setTheme(V1_StandardTableThemes.LATEX_LIGHT_TRIPLE_DASH);
		at.render();
//		System.err.println(at.render());
	}
}
