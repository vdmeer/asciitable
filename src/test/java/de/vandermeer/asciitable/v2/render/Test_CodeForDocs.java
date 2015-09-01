/* Copyright 2014 Sven van der Meer <vdmeer.sven@mykolab.com>
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

package de.vandermeer.asciitable.v2.render;

import org.junit.Test;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

/**
 * Tests for code used in render JavaDoc.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.2 build 150901 (01-Sep-15) for Java 1.7
 */
public class Test_CodeForDocs {

	@Test
	public void test_TableWithoutArrayNeed(){
		V2_AsciiTable at = new V2_AsciiTable();

		at.addRule();
		at.addRow("1-1", "1-2", "1-3");
		at.addRule();
		at.addRow("2-1", "2-2", "2-3");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthAbsoluteEven(25));
		rend.setPaddingChar('_');

		RenderedTable rt = rend.render(at);
		System.out.println(rt);
	}

}
