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
 * AsciiTable example: width behavior.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_00b_WidthBehavior implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "shows the behavior for table width settings";
	}

	@Override
	public Object getLongDescription() {
		return
				"The width of a table can be set via its context.\r\n" + 
				"This example creates a table and then sets the width and shows the output.\r\n" + 
				"The first width is 50 (line 8), the second 40 (line 11), and the third 30 characters (line 14)."
		;
	}

	@Override
	public String getName() {
		return "width";
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
				"at.getContext().setWidth(50);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setWidth(40);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setWidth(30);\r\n" + 
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

		at.getContext().setWidth(50);
		System.out.println(at.render());

		at.getContext().setWidth(40);
		System.out.println(at.render());

		at.getContext().setWidth(30);
		System.out.println(at.render());

		// end::example[]
	}
}
