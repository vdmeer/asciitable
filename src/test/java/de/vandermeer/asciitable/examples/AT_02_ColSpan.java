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
public class AT_02_ColSpan implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "table with different column span examples";
	}

	@Override
	public Object getLongDescription() {
		return
				"The table allows column spans, with some limitations.\r\n" + 
				"<br /><br />" + 
				"When adding text objects to a row, using `null` indicated a column span.\r\n" + 
				"Multiple `null` text objects signify multi-column spans.\r\n" + 
				"The final text object must be none-`null`.\r\n" + 
				"<br /><br />" +
				"This example shows a table with 5 columns and various column spans."
		;
	}

	@Override
	public String getName() {
		return "colspan";
	}

	@Override
	public String getSource(){
		return
				"AsciiTable at = new AsciiTable();\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(null, null, null, null, \"span all 5 columns\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(null, null, null, \"span 4 columns\", \"just 1 column\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(null, null, \"span 3 columns\", null, \"span 2 columns\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(null, \"span 2 columns\", null, null, \"span 3 columns\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"just 1 column\", null, null, null, \"span 4 columns\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"just 1 column\", \"just 1 column\", \"just 1 column\", \"just 1 column\", \"just 1 column\");\r\n" + 
				"at.addRule();\r\n" + 
				"System.out.println(at.render(71));"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
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
		System.out.println(at.render(71));
		// end::example[]
	}
}
