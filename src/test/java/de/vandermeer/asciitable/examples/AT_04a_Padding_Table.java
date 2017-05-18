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
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiTable example for padding behavior (table).
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_04a_Padding_Table implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "set padding for a whole table";
	}

	@Override
	public Object getLongDescription() {
		return
				"Inside a cell, the padding for top and bottom (above and below cell text) as well as left and right (horizontally before and after each line of text) can be set separately.\r\n" + 
				"The character used for the padding can be set as well.\r\n" + 
				"<br /><br />" + 
				"This example shows how to set the padding for the whole table.\r\n" + 
				"First, we create a table and add content (rows with cells and text).\r\n" + 
				"Then we set the padding and the padding character for the whole table.\r\n" + 
				"<br /><br />" +
				"Note: settings for the whole table only effect cells that have been already added."
		;
	}

	@Override
	public String getName() {
		return "padding-table";
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
				"\r\n" + 
				"at.setPaddingTopChar('v');\r\n" + 
				"at.setPaddingBottomChar('^');\r\n" + 
				"at.setPaddingLeftChar('>');\r\n" + 
				"at.setPaddingRightChar('<');\r\n" + 
				"at.setTextAlignment(TextAlignment.CENTER);\r\n" + 
				"at.setPadding(1);\r\n" + 
				"System.out.println(at.render(33));"
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

		at.setPaddingTopChar('v');
		at.setPaddingBottomChar('^');
		at.setPaddingLeftChar('>');
		at.setPaddingRightChar('<');
		at.setTextAlignment(TextAlignment.CENTER);
		at.setPadding(1);
		System.out.println(at.render(33));
		// end::example[]
	}
}
