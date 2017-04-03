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

import org.apache.commons.lang3.text.StrBuilder;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.TA_Grid;
import de.vandermeer.asciithemes.TA_GridConfig;
import de.vandermeer.skb.interfaces.StandardExample;

/**
 * AsciiTable example for grids.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0-SNAPSHOT build 170331 (31-Mar-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_06d_NewGrid implements StandardExample {

	@Override
	public void showOutput(){
		// tag::example[]
		TA_Grid myGrid = TA_Grid.create("grid using UTF-8 light border characters")
				.addCharacterMap(TA_GridConfig.RULESET_NORMAL, ' ', '#', '&', '#', '#', '%', '%', '+', '+', '+', '#', '%')
		;

		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("rc 11", "rc 12");
		at.addRule();
		at.addRow("rc 21", "rc 22");
		at.addRule();
		at.getContext().setWidth(13);

		at.getContext().setGrid(myGrid);
		System.out.println(at.render());
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"TA_Grid myGrid = TA_Grid.create(\"grid using UTF-8 light border characters\")",
				"		.addCharacterMap(TA_GridConfig.RULESET_NORMAL, ' ', '#', '&', '#', '#', '%', '%', '+', '+', '+', '#', '%')",
				";",
				"",
				"AsciiTable at = new AsciiTable();",
				"at.addRule();",
				"at.addRow(\"rc 11\", \"rc 12\");",
				"at.addRule();",
				"at.addRow(\"rc 21\", \"rc 22\");",
				"at.addRule();",
				"at.getContext().setWidth(13);",
				"",
				"at.getContext().setGrid(myGrid);",
				"System.out.println(at.render());",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}
}
