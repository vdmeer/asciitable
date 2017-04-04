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

import java.util.Collection;

import org.apache.commons.lang3.text.StrBuilder;
import org.stringtemplate.v4.ST;

import de.svenjacobs.loremipsum.LoremIpsum;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.render.RendersToClusterWidth;
import de.vandermeer.skb.interfaces.strategies.collections.list.ArrayListStrategy;
import de.vandermeer.skb.interfaces.transformers.ClusterElementTransformer;
import de.vandermeer.skb.interfaces.transformers.StrBuilder_To_String;
import de.vandermeer.skb.interfaces.transformers.textformat.Text_To_FormattedText;

/**
 * AsciiTable example demonstrating that {@link ST} objects are automatically added as text.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.1 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_00i_AddColumn_RendersToClusterWidth implements StandardExampleAsCmd {

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
		class ObjectRendersToClusterWidth implements RendersToClusterWidth{
			@Override
			public Collection<String> renderAsCollection(int width) {
				return ClusterElementTransformer.create().transform(
						Text_To_FormattedText.justified(new LoremIpsum().getWords(30), width),
						StrBuilder_To_String.create(),
						ArrayListStrategy.create()
				);
			}
		}

		at.addRule();
		at.addRow(new ObjectRendersToClusterWidth());
		at.addRule();
		System.out.println(at.render());
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiTable at = new AsciiTable();",
				"class ObjectRendersToClusterWidth implements RendersToClusterWidth{",
				"	@Override",
				"	public Collection<String> renderAsCollection(int width) {",
				"		return ClusterElementTransformer.create().transform(",
				"				Text_To_FormattedText.left(new LoremIpsum().getWords(30), width),",
				"				StrBuilder_To_String.create(),",
				"				ArrayListStrategy.create()",
				"		);",
				"	}",
				"}",
				"",
				"at.addRule();",
				"at.addRow(new ObjectRendersToClusterWidth());",
				"at.addRule();",
				"System.out.println(at.render());",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}
}
