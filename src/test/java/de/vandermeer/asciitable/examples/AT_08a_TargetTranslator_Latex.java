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
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import de.vandermeer.translation.targets.Text2Latex;

/**
 * AsciiTable example for target translation (LaTeX).
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_08a_TargetTranslator_Latex implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "translate cell content for LaTeX";
	}

	@Override
	public Object getLongDescription() {
		return
				"This example uses a translator for the target LaTeX.\r\n" + 
				"This means that all characters in the text that require special translation for being used in a LaTeX document will be translated into that representation (depending on the capabilities of the translator of course).\r\n" + 
				"<br /><br />" + 
				"The code below creates a table with two columns using the same text with special characters for translation.\r\n" + 
				"The left column in the table is rendered without translation, the right column is rendered with translation."
		;
	}

	@Override
	public String getName() {
		return "target-latex";
	}

	@Override
	public String getSource(){
		return
				"String text = \"A sentence with some normal text, not specific to LaTeX. \" +\r\n" + 
				"	\"Now for some characters that require conversion: # % &. \" +\r\n" + 
				"	\"And some more: © § ¤. \" +\r\n" + 
				"	\"And even more: È É Ê Ë. \" +\r\n" + 
				"	\"And some arrows as well: ← ↑ → ↓ ↔\"\r\n" + 
				";\r\n" + 
				"\r\n" + 
				"AsciiTable at = new AsciiTable();\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(text, text).getCells().get(1).getContext().setTargetTranslator(new Text2Latex());\r\n" + 
				"at.addRule();\r\n" + 
				"at.setTextAlignment(TextAlignment.LEFT);\r\n" + 
				"\r\n" + 
				"System.out.println(at.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		String text = "A sentence with some normal text, not specific to LaTeX. " +
			"Now for some characters that require conversion: # % &. " +
			"And some more: © § ¤. " +
			"And even more: È É Ê Ë. " +
			"And some arrows as well: ← ↑ → ↓ ↔"
		;

		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow(text, text).getCells().get(1).getContext().setTargetTranslator(new Text2Latex());
		at.addRule();
		at.setTextAlignment(TextAlignment.LEFT);

		System.out.println(at.render());
		// end::example[]
	}
}
