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

package de.vandermeer.asciitable;

import org.apache.commons.lang3.text.StrBuilder;

import de.vandermeer.skb.interfaces.StandardExample;

/**
 * AsciiTable example for width: longest word.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.3
 */
public class AT_07d_LongestWord implements StandardExample {

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
		at.addRule();
		AT_Row row1 = at.addRow("first", "information");
		at.addRule();
		AT_Row row2 = at.addRow("second", "info");
		at.addRule();

		at.getRenderer().setCWC(new CWC_LongestWord());
		System.out.println(at.render());

		at.setPaddingLeftRight(1);
		System.out.println(at.render());

		at.setPaddingLeftRight(0);
		row1.getCells().get(0).getContext().setPaddingLeftRight(2);
		row1.getCells().get(1).getContext().setPaddingLeftRight(3);
		row2.getCells().get(0).getContext().setPaddingLeftRight(3);
		row2.getCells().get(1).getContext().setPaddingLeftRight(4);
		System.out.println(at.render());
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiTable at = new AsciiTable();",
				"at.addRule();",
				"AT_Row row1 = at.addRow(\"first\", \"information\");",
				"at.addRule();",
				"AT_Row row2 = at.addRow(\"second\", \"info\");",
				"at.addRule();",
				"",
				"at.getRenderer().setCWC(new CWC_LongestWord());",
				"System.out.println(at.render());",
				"",
				"at.setPaddingLeftRight(1);",
				"System.out.println(at.render());",
				"",
				"at.setPaddingLeftRight(0);",
				"row1.getCells().get(0).getContext().setPaddingLeftRight(2);",
				"row1.getCells().get(1).getContext().setPaddingLeftRight(3);",
				"row2.getCells().get(0).getContext().setPaddingLeftRight(3);",
				"row2.getCells().get(1).getContext().setPaddingLeftRight(4);",
				"System.out.println(at.render());",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}
}
