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

import de.vandermeer.asciitable.AT_Cell;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiTable example for padding behavior (individual cell).
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_04c_Padding_Cell implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "set padding for a specific cell";
	}

	@Override
	public Object getLongDescription() {
		return
				"The padding of text can be set for individual table cells.\r\n" + 
				"This example creates a table, adds text objects, and then changes the padding for the last cell in second row leaving all other cells to the default (no padding)."
		;
	}

	@Override
	public String getName() {
		return "padding-cell";
	}

	@Override
	public String getSource(){
		return
				"AsciiTable at = new AsciiTable();\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"row 1 col 1\", \"row 1 col 2\");\r\n" + 
				"at.addRule();\r\n" + 
				"AT_Cell cell = at.addRow(\"row 2 col 1\", \"row 2 col 2\").getCells().get(1);\r\n" + 
				"at.addRule();\r\n" + 
				"\r\n" + 
				"cell.getContext().setPaddingTopChar('v');\r\n" + 
				"cell.getContext().setPaddingBottomChar('^');\r\n" + 
				"cell.getContext().setPaddingLeftChar('>');\r\n" + 
				"cell.getContext().setPaddingRightChar('<');\r\n" + 
				"cell.getContext().setTextAlignment(TextAlignment.CENTER);\r\n" + 
				"cell.getContext().setPadding(1);\r\n" + 
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
		AT_Cell cell = at.addRow("row 2 col 1", "row 2 col 2").getCells().get(1);
		at.addRule();

		cell.getContext().setPaddingTopChar('v');
		cell.getContext().setPaddingBottomChar('^');
		cell.getContext().setPaddingLeftChar('>');
		cell.getContext().setPaddingRightChar('<');
		cell.getContext().setTextAlignment(TextAlignment.CENTER);
		cell.getContext().setPadding(1);
		System.out.println(at.render(33));
		// end::example[]
	}
}
