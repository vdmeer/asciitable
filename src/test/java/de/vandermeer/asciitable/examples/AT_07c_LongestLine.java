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
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;

/**
 * AsciiTable example for width: longest line.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_07c_LongestLine implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "calculate column width: longes line";
	}

	@Override
	public Object getLongDescription() {
		return
				"This width calculator takes the longest line in each column and sets the column width to it.\r\n" + 
				"The calculator can be configured to use a minimum and maximum length for particular columns.\r\n" + 
				"This example shows a few examples for the behavior of this width calculator for a table with 5 columns and text of different length per column:\r\n" + 
				"first: the table without padding, \r\n" + 
				"second: calculator using a minimum length of 4 for the first column, \r\n" + 
				"third: calculator now also using a minimum width of 6 for the second column and a maximum width of 2 for the last column, and\r\n" + 
				"fourth: same calculator but now with an additional row in the table using a conditional line break."
		;
	}

	@Override
	public String getName() {
		return "cwc-line";
	}

	@Override
	public String getSource(){
		return
				"AsciiTable at = new AsciiTable();\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"0\", \"1\", \"22\", \"333\", \"4444\");\r\n" + 
				"at.addRule();\r\n" + 
				"CWC_LongestLine cwc = new CWC_LongestLine();\r\n" + 
				"\r\n" + 
				"at.getRenderer().setCWC(cwc);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"cwc.add(4, 0);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"cwc.add(6, 0).add(0, 0).add(0, 0).add(0, 2);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.addRow(\"0\", \"1\", \"22\", \"333<br>55555\", \"4444\");\r\n" + 
				"at.addRule();\r\n" + 
				"System.out.println(at.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("0", "1", "22", "333", "4444");
		at.addRule();
		CWC_LongestLine cwc = new CWC_LongestLine();

		at.getRenderer().setCWC(cwc);
		System.out.println(at.render());

		cwc.add(4, 0);
		System.out.println(at.render());

		cwc.add(6, 0).add(0, 0).add(0, 0).add(0, 2);
		System.out.println(at.render());

		at.addRow("0", "1", "22", "333<br>55555", "4444");
		at.addRule();
		System.out.println(at.render());
		// end::example[]
	}
}
