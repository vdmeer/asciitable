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

import de.vandermeer.asciilist.checklist.Checklist;
import de.vandermeer.asciilist.enumerate.EnumerateList;
import de.vandermeer.asciilist.itemize.ItemizeList;
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
public class AT_09d_AsciiList implements StandardExample {

	@Override
	public void showOutput(){
		ItemizeList il = new ItemizeList();
		il.addItem("il 1 item 1 some text");
		il.addItem("il 1 item 2 some text");
		ItemizeList il2 = new ItemizeList();
		il2.addItem("il 2 item 1 text");
		il2.addItem("il 2 item 2 text");
//		il.addItem(il2);
//		il.setPreLabelIndent(0);
//		il.setListStyle(NestedItemizeStyles.ALL_STAR_INCREMENTAL);

		EnumerateList el = new EnumerateList();
		el.addItem("el 1 item 1 some text");
		el.addItem("el 1 item 2 some text");
		EnumerateList el2 = new EnumerateList();
		el2.addItem("el 2 item 1 text");
		el2.addItem("el 2 item 2 text");
//		el.addItem(el2);
//		el.setPreLabelIndent(0);
//		el.setListStyle(NestedEnumerateStyles.all_alpha_ascii);

		Checklist cl = new Checklist();
		cl.addItem("cl 1 item 1 some text", false);
		cl.addItem("cl 1 item 2 some text", true);
//		cl.setPreLabelIndent(0);
//		cl.setListStyle(NestedCheckStyles.ALL_SQUARE_BRACKET_BLANK_X);
System.err.println(il.render(25));
System.err.println(el.render(25));
System.err.println(cl.render(27));
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow(il, el, cl).setPaddingLeftRight(1);
		at.addRule();
		at.addRow(il, el, cl).setPaddingLeftRight(3);
		at.addRule();
		at.setTextAlignment(TextAlignment.LEFT);
		at.getRenderer().setCWC(new CWC_FixedWidth().add(25).add(25).add(27));
//		System.out.println(at.render());
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiTable at = new AsciiTable();",
				//TODO
				"System.out.println(at.render());",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}
}
