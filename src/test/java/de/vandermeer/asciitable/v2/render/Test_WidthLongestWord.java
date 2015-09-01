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

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import de.svenjacobs.loremipsum.LoremIpsum;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_Width;
import de.vandermeer.asciitable.v2.render.WidthLongestWord;

/**
 * Tests for {@link WidthLongestWord}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.2 build 150901 (01-Sep-15) for Java 1.7
 */
public class Test_WidthLongestWord {

	@Test
	public void test_CodeForDoc(){
		V2_AsciiTable at;
		int[] cols;
		V2_Width width;

		at = new V2_AsciiTable(0);
		at.addRule();
		at.addRow("first", "information");
		at.addRule();
		at.addRow("second", "info");
		at.addRule();
		width = new WidthLongestWord();
		cols = width.getColumnWidths(at);
		assertEquals(2, cols.length);
		assertEquals(6,  cols[0]);		// longest word: second (6)
		assertEquals(11, cols[1]);		// longest word: information (11)
		System.out.println(ArrayUtils.toString(cols));

		at = new V2_AsciiTable(1);
		at.addRule();
		at.addRow("first", "information");
		at.addRule();
		at.addRow("second", "info");
		at.addRule();
		width = new WidthLongestWord();
		cols = width.getColumnWidths(at);
		assertEquals(2, cols.length);
		assertEquals(8,  cols[0]);		// longest word: second (6) + padding 1*2
		assertEquals(13, cols[1]);		// longest word: information (11) + padding 1*2
		System.out.println(ArrayUtils.toString(cols));


		at = new V2_AsciiTable(0);
		at.addRule();
		at.addRow("first", "information").setPadding(new int[]{2, 3});
		at.addRule();
		at.addRow("second", "info").setPadding(new int[]{3, 4});
		at.addRule();
		width = new WidthLongestWord();
		cols = width.getColumnWidths(at);
		assertEquals(2, cols.length);
		assertEquals(12,  cols[0]);		// longest word: second (6) + padding 3*2
		assertEquals(17, cols[1]);		// longest word: information (11) + padding 3*2
		System.out.println(ArrayUtils.toString(cols));


		at = new V2_AsciiTable();
		at.addRow(new LoremIpsum().getWords());
		width = new WidthLongestWord();
		cols = width.getColumnWidths(at);
		assertEquals(1, cols.length);
		assertEquals(12,  cols[0]);		// longest word: sadipscing (10) + padding 1
		System.out.println(ArrayUtils.toString(cols));
	}
}
