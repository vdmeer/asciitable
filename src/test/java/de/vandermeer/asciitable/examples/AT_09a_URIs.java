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

/**
 * AsciiTable example for URIs as content.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AT_09a_URIs implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "URIs as cell content";
	}

	@Override
	public Object getLongDescription() {
		return
				"Uniform Resource Identifiers (URIs) can be used in a table.\r\n" + 
				"No special rules are applied to them for line breaks.\r\n" + 
				"The renderer tries to put as many characters of a URI into a single line as possible.\r\n" + 
				"This example shows a table with various columns and URIs."
		;
	}

	@Override
	public String getName() {
		return "uri";
	}

	@Override
	public String getSource(){
		return
				"AsciiTable at = new AsciiTable();\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]\", \"scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(null, \"scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"abc://username:password@example.com:123/path/data?key=value#fragid1\", \"abc://username:password@example.com:123/path/data?key=value#fragid1\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(null, \"abc://username:password@example.com:123/path/data?key=value#fragid1\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"urn:example:mammal:monotreme:echidna\", \"urn:example:mammal:monotreme:echidna\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(null, \"urn:example:mammal:monotreme:echidna\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"http://www.example.com/test1/test2\", \"http://www.example.com/test1/test2\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(null, \"http://www.example.com/test1/test2\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(\"mailto:user1@example.com\", \"mailto:firstname.lastname@example.com\");\r\n" + 
				"at.addRule();\r\n" + 
				"at.addRow(null, \"mailto:firstname.lastname@example.com\");\r\n" + 
				"at.addRule();\r\n" + 
				"\r\n" + 
				"System.out.println(at.render(73));"
		;
	}

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
}
