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

import de.svenjacobs.loremipsum.LoremIpsum;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiTable example for text alignment options.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_03_AlignmentOptions implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "different text alignment";
	}

	@Override
	public String getName() {
		return "alignment";
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiTable at = new AsciiTable();",
				"String text = new LoremIpsum().getWords(19);",
				"AT_Row row;",
				"at.addRule();",
				"row = at.addRow(text, text, text);",
				"row.getCells().get(0).getContext().setTextAlignment(TextAlignment.JUSTIFIED_LEFT);",
				"row.getCells().get(1).getContext().setTextAlignment(TextAlignment.JUSTIFIED);",
				"row.getCells().get(2).getContext().setTextAlignment(TextAlignment.JUSTIFIED_RIGHT);",
				"at.addRule();",
				"row = at.addRow(text, text, text);",
				"row.getCells().get(0).getContext().setTextAlignment(TextAlignment.LEFT);",
				"row.getCells().get(1).getContext().setTextAlignment(TextAlignment.CENTER);",
				"row.getCells().get(2).getContext().setTextAlignment(TextAlignment.RIGHT);",
				"at.addRule();",
				"System.out.println(at.render(79));",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
		String text = new LoremIpsum().getWords(19);
		AT_Row row;
		at.addRule();
		row = at.addRow(text, text, text);
		row.getCells().get(0).getContext().setTextAlignment(TextAlignment.JUSTIFIED_LEFT);
		row.getCells().get(1).getContext().setTextAlignment(TextAlignment.JUSTIFIED);
		row.getCells().get(2).getContext().setTextAlignment(TextAlignment.JUSTIFIED_RIGHT);
		at.addRule();
		row = at.addRow(text, text, text);
		row.getCells().get(0).getContext().setTextAlignment(TextAlignment.LEFT);
		row.getCells().get(1).getContext().setTextAlignment(TextAlignment.CENTER);
		row.getCells().get(2).getContext().setTextAlignment(TextAlignment.RIGHT);
		at.addRule();
		System.out.println(at.render(79));
		// end::example[]
	}
}
