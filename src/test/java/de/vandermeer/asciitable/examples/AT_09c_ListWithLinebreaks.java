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
import de.vandermeer.skb.interfaces.StandardExample;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiTable example for a list using conditional line breaks.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0-SNAPSHOT build 170331 (31-Mar-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_09c_ListWithLinebreaks implements StandardExample {

	@Override
	public void showOutput(){
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("column with a list using conditional line breaks", " * list item one<br> * list item two<br> * list item three");
		at.addRule();
		at.setTextAlignment(TextAlignment.LEFT);
		at.getRenderer().setCWC(new CWC_FixedWidth().add(20).add(25));
		System.out.println(at.render(50));
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiTable at = new AsciiTable();",
				//TODO
				"System.out.println(at.render(50));",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}
}
