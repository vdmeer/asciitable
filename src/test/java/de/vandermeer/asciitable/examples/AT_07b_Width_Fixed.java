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
import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.skb.interfaces.StandardExampleAsCmd;

/**
 * AsciiTable example for width: fixed set column widths.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_07b_Width_Fixed implements StandardExampleAsCmd {

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

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiTable at = new AsciiTable();",
				"at.addRule();",
				"at.addRow(\"col1\", \"col2\", \"col3\");",
				"at.addRule();",
				"",
				"at.getRenderer().setCWC(new CWC_FixedWidth().add(10).add(20).add(30));",
				"System.out.println(at.render());",
				"",
				"at.getRenderer().setCWC(new CWC_FixedWidth().add(5).add(10).add(15));",
				"System.out.println(at.render());",
				"",
				"at.getRenderer().setCWC(new CWC_FixedWidth().add(3).add(5).add(7));",
				"System.out.println(at.render());",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}

	@Override
	public String getDescription() {
		return "calculate column width: fixed column width";
	}

	@Override
	public String getID() {
		return "cwc-fixed";
	}
}
