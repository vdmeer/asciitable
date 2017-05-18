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
 * AsciiTable example for margins (outside the frame).
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_05_MarginBehavior implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "example for margins";
	}

	@Override
	public Object getLongDescription() {
		return
				"The implementation allows to add margins before and after the table as well as left and right of each line (outside the grid).\r\n" + 
				"For each margin, the number and the character can be set.\r\n" + 
				"The number states the margin to be added (number of lines for top/bottom, number of characters for left/rigth).\r\n" + 
				"<br /><br />" + 
				"The following code creates a table and then adds one line top margin, 2 line bottom margin, 3 characters left margin, and 4 characters right margin.\r\n" + 
				"Each setting uses a different margin character."
		;
	}

	@Override
	public String getName() {
		return "margin";
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
				"at.getContext().setFrameTopChar('v');\r\n" + 
				"at.getContext().setFrameBottomChar('^');\r\n" + 
				"at.getContext().setFrameLeftChar('>');\r\n" + 
				"at.getContext().setFrameRightChar('<');\r\n" + 
				"\r\n" + 
				"at.getContext().setFrameTopMargin(1);\r\n" + 
				"at.getContext().setFrameBottomMargin(2);\r\n" + 
				"at.getContext().setFrameLeftMargin(3);\r\n" + 
				"at.getContext().setFrameRightMargin(4);\r\n" + 
				"\r\n" + 
				"System.out.println(at.render(39));"
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

		at.getContext().setFrameTopChar('v');
		at.getContext().setFrameBottomChar('^');
		at.getContext().setFrameLeftChar('>');
		at.getContext().setFrameRightChar('<');

		at.getContext().setFrameTopMargin(1);
		at.getContext().setFrameBottomMargin(2);
		at.getContext().setFrameLeftMargin(3);
		at.getContext().setFrameRightMargin(4);

		System.out.println(at.render(39));
		// end::example[]
	}
}
