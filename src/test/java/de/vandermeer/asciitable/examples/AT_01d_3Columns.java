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
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;

/**
 * AsciiTable example for a simple table as getting started example.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_01d_3Columns implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "table with 3 columns";
	}

	@Override
	public Object getLongDescription() {
		return
				"The number of columns of a table is automatically set by the first content row added to the table.\r\n" + 
				"If the first row has 1 text object, then the table has 1 column.\r\n" + 
				"If the first row has 2 text objects, then the table has 2 columns, and so forth.\r\n" + 
				"<br /><br />" +
				"This example show this behavior for 3 columns."
		;
	}

	@Override
	public String getName() {
		return "3cols";
	}

	@Override
	public String getSource(){
		return
				"AsciiTable at = new AsciiTable();\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"first row (col1)\", \"some information (col2)\", \"more info (col3)\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"second row (col1)\", \"some information (col2)\", \"more info (col3)\");\r\n" + 
				"at.addRule();\r\n" + 
				"System.out.println(at.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("first row (col1)", "some information (col2)", "more info (col3)");
		at.addRule();
		at.addRow("second row (col1)", "some information (col2)", "more info (col3)");
		at.addRule();
		System.out.println(at.render());
		// end::example[]
	}
}
