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
import de.vandermeer.asciitable.CWC_LongestWordMax;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;

/**
 * AsciiTable example for width: longest word with maximum column width settings.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_07e_LongestWordMax implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "calculate column width: longest word, maximum width";
	}

	@Override
	public Object getLongDescription() {
		return
				"This width calculator takes the longest word in each column and sets the column width to it.\r\n" + 
				"It can be further configured to a maximum column width.\r\n" + 
				"This example shows a few examples for the behavior of this width calculator for a table with 2 columns:\r\n" + 
				"first: maximum width set to 8 for all columns, and\r\n" + 
				"second: maximum width of first column set to 4, maximum width of second column removed."
		;
	}

	@Override
	public String getName() {
		return "cwc-word-max";
	}

	@Override
	public String getSource(){
		return
				"AsciiTable at = new AsciiTable();\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"first\", \"information\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"second\", \"info\");\r\n" + 
				"at.addRule();\r\n" + 
				"\r\n" + 
				"at.getRenderer().setCWC(new CWC_LongestWordMax(8));\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getRenderer().setCWC(new CWC_LongestWordMax(new int[]{4,-1}));\r\n" + 
				"System.out.println(at.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("first", "information");
		at.addRule();
		at.addRow("second", "info");
		at.addRule();

		at.getRenderer().setCWC(new CWC_LongestWordMax(8));
		System.out.println(at.render());

		at.getRenderer().setCWC(new CWC_LongestWordMax(new int[]{4,-1}));
		System.out.println(at.render());
		// end::example[]
	}
}
