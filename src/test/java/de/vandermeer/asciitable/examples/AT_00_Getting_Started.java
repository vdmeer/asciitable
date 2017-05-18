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
public class AT_00_Getting_Started implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "a simple table with 2 rows and 3 columns";
	}

	@Override
	public Object getLongDescription() {
		return
				"This example shows how to create a table, add content (rows and cells), and render the table.\r\n" + 
				"It also demonstrates that all excessive white spaces (extra blanks, tabulators, new lines) will be removed automatically.\r\n" + 
				"Furthermore, the table will break lines automatically to the set width (in the example the default width of 80 characters).\r\n" + 
				"This automatic line break uses words (not characters)."
		;
	}

	@Override
	public String getName() {
		return "simple";
	}

	@Override
	public String getSource(){
		return
				"AsciiTable at = new AsciiTable();\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"row 1 col 1\", \"row 1 col 2\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"row 2 col 1\", \"row 2 col 2\");\r\n" + 
				"at.addRule();\r\n" + 
				"System.out.println(at.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("row 1 col 1", "row 1 col 2");
		at.addRule();
		at.addRow("row 2 col 1", "row 2 col 2");
		at.addRule();
		System.out.println(at.render());
		// end::example[]
	}
}
