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
import de.vandermeer.asciithemes.a7.A7_Grids;
import de.vandermeer.asciithemes.a8.A8_Grids;
import de.vandermeer.asciithemes.u8.U8_Grids;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;

/**
 * AsciiTable example for grids.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_06a_Grids implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "adding a grid to a table";
	}

	@Override
	public Object getLongDescription() {
		return
				"The frame of a table is called grid in the implementation.\r\n" + 
				"The grid is based on the `TA_Grid` implementation in the package `ascii-utf-themes`.\r\n" + 
				"The package also provides a number of predefined grids using standard 7-Bit ASCII characters, standard 8-Bit ASCII characters, and UTF characters.\r\n" + 
				"<br /><br />" + 
				"This example creates a table and renders it (then using the default grid).\r\n" + 
				"Then we change the grid 4 times and render the table, resulting in different grids being used."
		;
	}

	@Override
	public String getName() {
		return "grid";
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
				"at.getContext().setGrid(A7_Grids.minusBarPlusEquals());\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setGrid(A8_Grids.lineDoubleBlocks());\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setGrid(U8_Grids.borderDoubleLight());\r\n" + 
				"System.out.println(at.render());\r\n" + 
				"\r\n" + 
				"at.getContext().setGrid(U8_Grids.borderDouble());\r\n" + 
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

		at.getContext().setGrid(A7_Grids.minusBarPlusEquals());
		System.out.println(at.render());

		at.getContext().setGrid(A8_Grids.lineDoubleBlocks());
		System.out.println(at.render());

		at.getContext().setGrid(U8_Grids.borderDoubleLight());
		System.out.println(at.render());

		at.getContext().setGrid(U8_Grids.borderDouble());
		System.out.println(at.render());
		// end::example[]
	}
}
