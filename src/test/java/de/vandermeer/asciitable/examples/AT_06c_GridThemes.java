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
import de.vandermeer.asciithemes.TA_GridThemes;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;

/**
 * AsciiTable example for grid themes.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_06c_GridThemes implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "different themes for a grid";
	}

	@Override
	public Object getLongDescription() {
		return
				"Grids support different themes.\r\n" + 
				"A grid theme defines which grid characters from which position should be rendered.\r\n" + 
				"All other character will be rendered using a default character, usually blank.\r\n" + 
				"<br >/<br />" + 
				"This example creates a table and then renders it using different grid themes:\r\n" + 
				"full grid, no grid, corners only (top left, top right, bottom left, bottom right), corners and connectors (all 4 corners and every grid element that connects multiple grid lines),\r\n" + 
				"horizontal lines only (no corners, no connectors), vertical lines only  (no corners, no connectors), inside grid elements (lines and connectors),\r\n" + 
				"outside grid elements (lines and connectors and corners), left lines only, and top and bottom lines only."
		;
	}

	@Override
	public String getName() {
		return "grid-themes";
	}

	@Override
	public String getSource(){
		return
				"AsciiTable at = new AsciiTable();\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"rc 11\", \"rc 12\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"rc 21\", \"rc 22\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.getContext().setWidth(13);\r\n" + 
				"\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setGridTheme(TA_GridThemes.NONE);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setGridTheme(TA_GridThemes.CORNERS);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setGridTheme(TA_GridThemes.CC);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setGridTheme(TA_GridThemes.HORIZONTAL);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setGridTheme(TA_GridThemes.VERTICAL);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setGridTheme(TA_GridThemes.INSIDE);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setGridTheme(TA_GridThemes.OUTSIDE);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setGridTheme(TA_GridThemes.LEFT);\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setGridTheme(TA_GridThemes.TOPBOTTOM);\r\n" + 
				"System.out.println(at.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("rc 11", "rc 12");
		at.addRule();
		at.addRow("rc 21", "rc 22");
		at.addRule();
		at.getContext().setWidth(13);

		System.out.println(at.render());

		at.getContext().setGridTheme(TA_GridThemes.NONE);
		System.out.println(at.render());

		at.getContext().setGridTheme(TA_GridThemes.CORNERS);
		System.out.println(at.render());

		at.getContext().setGridTheme(TA_GridThemes.CC);
		System.out.println(at.render());

		at.getContext().setGridTheme(TA_GridThemes.HORIZONTAL);
		System.out.println(at.render());

		at.getContext().setGridTheme(TA_GridThemes.VERTICAL);
		System.out.println(at.render());

		at.getContext().setGridTheme(TA_GridThemes.INSIDE);
		System.out.println(at.render());

		at.getContext().setGridTheme(TA_GridThemes.OUTSIDE);
		System.out.println(at.render());

		at.getContext().setGridTheme(TA_GridThemes.LEFT);
		System.out.println(at.render());

		at.getContext().setGridTheme(TA_GridThemes.TOPBOTTOM);
		System.out.println(at.render());
		// end::example[]
	}
}
