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
import de.vandermeer.skb.interfaces.StandardExampleAsCmd;

/**
 * AsciiTable example for URIs as content.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0-SNAPSHOT build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_09a_URIs implements StandardExampleAsCmd {

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]", "scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]");
		at.addRule();
		at.addRow(null, "scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]");
		at.addRule();
		at.addRow("abc://username:password@example.com:123/path/data?key=value#fragid1", "abc://username:password@example.com:123/path/data?key=value#fragid1");
		at.addRule();
		at.addRow(null, "abc://username:password@example.com:123/path/data?key=value#fragid1");
		at.addRule();
		at.addRow("urn:example:mammal:monotreme:echidna", "urn:example:mammal:monotreme:echidna");
		at.addRule();
		at.addRow(null, "urn:example:mammal:monotreme:echidna");
		at.addRule();
		at.addRow("http://www.example.com/test1/test2", "http://www.example.com/test1/test2");
		at.addRule();
		at.addRow(null, "http://www.example.com/test1/test2");
		at.addRule();
		at.addRow("mailto:user1@example.com", "mailto:firstname.lastname@example.com");
		at.addRule();
		at.addRow(null, "mailto:firstname.lastname@example.com");
		at.addRule();

		System.out.println(at.render(73));
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiTable at = new AsciiTable();",
				"at.addRule();",
				"at.addRow(\"scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]\", \"scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]\");",
				"at.addRule();",
				"at.addRow(null, \"scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]\");",
				"at.addRule();",
				"at.addRow(\"abc://username:password@example.com:123/path/data?key=value#fragid1\", \"abc://username:password@example.com:123/path/data?key=value#fragid1\");",
				"at.addRule();",
				"at.addRow(null, \"abc://username:password@example.com:123/path/data?key=value#fragid1\");",
				"at.addRule();",
				"at.addRow(\"urn:example:mammal:monotreme:echidna\", \"urn:example:mammal:monotreme:echidna\");",
				"at.addRule();",
				"at.addRow(null, \"urn:example:mammal:monotreme:echidna\");",
				"at.addRule();",
				"at.addRow(\"http://www.example.com/test1/test2\", \"http://www.example.com/test1/test2\");",
				"at.addRule();",
				"at.addRow(null, \"http://www.example.com/test1/test2\");",
				"at.addRule();",
				"at.addRow(\"mailto:user1@example.com\", \"mailto:firstname.lastname@example.com\");",
				"at.addRule();",
				"at.addRow(null, \"mailto:firstname.lastname@example.com\");",
				"at.addRule();",
				"",
				"System.out.println(at.render(73));",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}

	@Override
	public String getDescription() {
		return "URIs as cell content";
	}

	@Override
	public String getID() {
		return "uri";
	}
}
