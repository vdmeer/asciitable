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

package de.vandermeer.asciitable.commons;

import org.junit.Test;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.RenderUtilities;
import de.vandermeer.asciitable.v2.render.WidthAbsoluteEven;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

/**
 * Tests for code used in commons JavaDoc.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.6-SNAPSHOT build 160304 (04-Mar-16) for Java 1.7
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
		rend.setWidth(new WidthAbsoluteEven(19));

		RenderedTable rt = rend.render(at);
		System.out.println(rt);
	}

	@Test
	public void test_TableWithoutArrayNeedAsArray(){
		//disable the flip array in V2_Utils for correct output
		String[][] ar;
		ar = RenderUtilities.createContentArray(new Object[]{"1-1", "1-2", "1-3"}, new int[]{5, 5, 5}, new int[]{1, 1, 1});
		System.out.println(ArrayTransformations.ARRAY_TO_STRING(ar));

		ar = RenderUtilities.createContentArray(new Object[]{"2-1", "2-2", "2-3"}, new int[]{5, 5, 5}, new int[]{1, 1, 1});
		System.out.println(ArrayTransformations.ARRAY_TO_STRING(ar));
	}

	@Test
	public void test_TableWithArrayNeed(){
		V2_AsciiTable at = new V2_AsciiTable();

		at.addRule();
		at.addRow("1-1", "1-2 text", "1-3");
		at.addRule();
		at.addRow("2-1", "2-2", "2-3");
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new WidthAbsoluteEven(19));

		RenderedTable rt = rend.render(at);
		System.out.println(rt);
	}

	@Test
	public void test_TableWithArrayNeedAsArray(){
		//disable the flip array in V2_Utils for correct output
		String[][] ar;
		ar = RenderUtilities.createContentArray(new Object[]{"1-1", "1-2 text", "1-3"}, new int[]{5, 5, 5}, new int[]{1, 1, 1});
		System.out.println(ArrayTransformations.ARRAY_TO_STRING(ar));

		ar = RenderUtilities.createContentArray(new Object[]{"2-1", "2-2", "2-3"}, new int[]{5, 5, 5}, new int[]{1, 1, 1});
		System.out.println(ArrayTransformations.ARRAY_TO_STRING(ar));
	}

}
