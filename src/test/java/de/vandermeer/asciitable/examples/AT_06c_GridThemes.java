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
	public String getName() {
		return "grid-themes";
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiTable at = new AsciiTable();",
				"at.addRule();",
				"at.addRow(\"rc 11\", \"rc 12\");",
				"at.addRule();",
				"at.addRow(\"rc 21\", \"rc 22\");",
				"at.addRule();",
				"at.getContext().setWidth(13);",
				"",
				"System.out.println(at.render());",
				"",
				"at.getContext().setGridTheme(TA_GridThemes.NONE);",
				"System.out.println(at.render());",
				"",
				"at.getContext().setGridTheme(TA_GridThemes.CORNERS);",
				"System.out.println(at.render());",
				"",
				"at.getContext().setGridTheme(TA_GridThemes.CC);",
				"System.out.println(at.render());",
				"",
				"at.getContext().setGridTheme(TA_GridThemes.HORIZONTAL);",
				"System.out.println(at.render());",
				"",
				"at.getContext().setGridTheme(TA_GridThemes.VERTICAL);",
				"System.out.println(at.render());",
				"",
				"at.getContext().setGridTheme(TA_GridThemes.INSIDE);",
				"System.out.println(at.render());",
				"",
				"at.getContext().setGridTheme(TA_GridThemes.OUTSIDE);",
				"System.out.println(at.render());",
				"",
				"at.getContext().setGridTheme(TA_GridThemes.LEFT);",
				"System.out.println(at.render());",
				"",
				"at.getContext().setGridTheme(TA_GridThemes.TOPBOTTOM);",
				"System.out.println(at.render());",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
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
