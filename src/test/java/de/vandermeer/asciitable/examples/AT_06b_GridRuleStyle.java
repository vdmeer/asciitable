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
import de.vandermeer.asciithemes.a8.A8_Grids;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;

/**
 * AsciiTable example for grids with different rule styles.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_06b_GridRuleStyle implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "different styles for table rules from a grid";
	}

	@Override
	public Object getLongDescription() {
		return
				"Grids can support different styles for a rule (a horizontal line in the table).\r\n" + 
				"Currently defined styles for the table are:\r\n" + 
				"normal (a normal rule)\r\n" + 
				"light (a rule rendered using light characters (e.g. lighter than normal))\r\n" + 
				"strong (a rule rendered using strong characters (e.g. stronger than normal)), and \r\n" + 
				"heavy (a rule rendered using heavy characters (e.g. heavy than strong))\r\n" + 
				"This example creates a table with rules of different styles and applies a grid supporting all styles to the table."
		;
	}

	@Override
	public String getName() {
		return "grid-rule-style";
	}

	@Override
	public String getSource(){
		return
				"AsciiTable at = new AsciiTable();\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"rc 11\", \"rc 12\");\r\n" + 
				"at.addLightRule();\r\n" + 
				"at.addRow(\"rc 21\", \"rc 22\");\r\n" + 
				"at.addStrongRule();\r\n" + 
				"at.addRow(\"rc 31\", \"rc 32\");\r\n" + 
				"at.addHeavyRule();\r\n" + 
				"at.getContext().setWidth(13);\r\n" + 
				"\r\n" + 
				"at.getContext().setGrid(A8_Grids.lineDoubleBlocks());\r\n" + 
				"System.out.println(at.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("rc 11", "rc 12");
		at.addLightRule();
		at.addRow("rc 21", "rc 22");
		at.addStrongRule();
		at.addRow("rc 31", "rc 32");
		at.addHeavyRule();
		at.getContext().setWidth(13);

		at.getContext().setGrid(A8_Grids.lineDoubleBlocks());
		System.out.println(at.render());
		// end::example[]
	}
}
