/* Copyright 2016 Sven van der Meer <vdmeer.sven@mykolab.com>
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

package de.vandermeer.asciitable.examples;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_AbsoluteEven;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;

/**
 * AsciiTable example for width: absolute width with even columns.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_07a_Width_AbsoluteEven implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "calculate column width: absolute even";
	}

	@Override
	public Object getLongDescription() {
		return
				"The default width calculator for a table uses the set table width and distributed all columns equally in it (including the required characters for the grid).\r\n" + 
				"For instance, a table with 3 columns and a width of 34 will result in a column width of 10 for each of the 3 columns:\r\n" + 
				"1 character for the left grid, \r\n" + 
				"1 character for the right grid, \r\n" + 
				"2 characters for the grid between the 3 columns, and\r\n" + 
				"3 columns and 30 characters remaining = 10 characters per column.\r\n" + 
				"This example creates a table and then renders the table with a width of 50, 30, and 20."
		;
	}

	@Override
	public String getName() {
		return "cwc-absolute";
	}

	@Override
	public String getSource(){
		return
				"AsciiTable at = new AsciiTable();\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"col1\", \"col2\", \"col3\");\r\n" + 
				"at.addRule();\r\n" + 
				"\r\n" + 
				"at.getRenderer().setCWC(new CWC_AbsoluteEven());\r\n" + 
				"System.out.println(at.render(50));\r\n" + 
				"System.out.println(at.render(30));\r\n" + 
				"System.out.println(at.render(20));"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("col1", "col2", "col3");
		at.addRule();

		at.getRenderer().setCWC(new CWC_AbsoluteEven());
		System.out.println(at.render(50));
		System.out.println(at.render(30));
		System.out.println(at.render(20));
		// end::example[]
	}
}
