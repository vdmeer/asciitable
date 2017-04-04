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
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.StandardExampleAsCmd;

/**
 * AsciiTable example for width: longest line.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0-SNAPSHOT build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_07c_LongestLine implements StandardExampleAsCmd {

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

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiTable at = new AsciiTable();",
				"at.addRule();",
				"at.addRow(\"0\", \"1\", \"22\", \"333\", \"4444\");",
				"at.addRule();",
				"CWC_LongestLine cwc = new CWC_LongestLine();",
				"",
				"at.getRenderer().setCWC(cwc);",
				"System.out.println(at.render());",
				"",
				"cwc.add(4, 0);",
				"System.out.println(at.render());",
				"",
				"cwc.add(6, 0).add(0, 0).add(0, 0).add(0, 2);",
				"System.out.println(at.render());",
				"",
				"at.addRow(\"0\", \"1\", \"22\", \"333<br>55555\", \"4444\");",
				"at.addRule();",
				"System.out.println(at.render());",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}

	@Override
	public String getDescription() {
		return "calculate column width: longes line";
	}

	@Override
	public String getID() {
		return "cwc-line";
	}
}
