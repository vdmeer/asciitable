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
import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;

/**
 * AsciiTable example for width: fixed set column widths.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_07b_Width_Fixed implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "calculate column width: fixed column width";
	}

	@Override
	public Object getLongDescription() {
		return
				"This width calculator takes a fixed width per column.\r\n" + 
				"The width per column can be added to the calculator.\r\n" + 
				"This example creates a table and then renders the table with 3 different examples for fixed column width:\r\n" + 
				"first example: column 1 width 10, column 2 width 20, column 3 width 30;\r\n" + 
				"second example: column 1 width 5, column 2 width 10, column 3 width 15; and\r\n" + 
				"third example: column 1 width 3, column 2 width 5, column 3 width 7"
		;
	}

	@Override
	public String getName() {
		return "cwc-fixed";
	}

	@Override
	public String getSource(){
		return
				"AsciiTable at = new AsciiTable();\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"col1\", \"col2\", \"col3\");\r\n" + 
				"at.addRule();\r\n" + 
				"\r\n" + 
				"at.getRenderer().setCWC(new CWC_FixedWidth().add(10).add(20).add(30));\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getRenderer().setCWC(new CWC_FixedWidth().add(5).add(10).add(15));\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getRenderer().setCWC(new CWC_FixedWidth().add(3).add(5).add(7));\r\n" + 
				"System.out.println(at.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("col1", "col2", "col3");
		at.addRule();

		at.getRenderer().setCWC(new CWC_FixedWidth().add(10).add(20).add(30));
		System.out.println(at.render());

		at.getRenderer().setCWC(new CWC_FixedWidth().add(5).add(10).add(15));
		System.out.println(at.render());

		at.getRenderer().setCWC(new CWC_FixedWidth().add(3).add(5).add(7));
		System.out.println(at.render());
		// end::example[]
	}
}
