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

import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.WidthAbsoluteEven;

/**
 * Tests for {@link WidthAbsoluteEven}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.2 build 150827 (27-Aug-15) for Java 1.7
 */
public class Test_WidthAbsoluteEven {

	@Test
	public void test_CodeForDoc(){
		WidthAbsoluteEven w;
		int[] cols;
		V2_AsciiTable at;

		w = new WidthAbsoluteEven(74);
		at = new V2_AsciiTable();
		at.addRow("one");
		cols = w.getColumnWidths(at);
		assertEquals(1, cols.length);
		assertEquals(72, cols[0]);
		System.out.println(ArrayUtils.toString(cols));


		w = new WidthAbsoluteEven(74);
		at = new V2_AsciiTable();
		at.addRow("one", "two");
		cols = w.getColumnWidths(at);
		assertEquals(2, cols.length);
		assertEquals(36, cols[0]);
		assertEquals(35, cols[1]);
		System.out.println(ArrayUtils.toString(cols));


		w = new WidthAbsoluteEven(74);
		at = new V2_AsciiTable();
		at.addRow("one", "two", "three");
		cols = w.getColumnWidths(at);
		assertEquals(3, cols.length);
		assertEquals(24, cols[0]);
		assertEquals(23, cols[1]);
		assertEquals(23, cols[2]);
		System.out.println(ArrayUtils.toString(cols));


		w = new WidthAbsoluteEven(74);
		at = new V2_AsciiTable();
		at.addRow("one", "two", "three", "four");
		cols = w.getColumnWidths(at);
		assertEquals(4, cols.length);
		assertEquals(18, cols[0]);
		assertEquals(17, cols[1]);
		assertEquals(17, cols[2]);
		assertEquals(17, cols[3]);
		System.out.println(ArrayUtils.toString(cols));


		w = new WidthAbsoluteEven(74);
		at = new V2_AsciiTable();
		at.addRow("one", "two", "three", "four", "five");
		cols = w.getColumnWidths(at);
		assertEquals(5, cols.length);
		assertEquals(14, cols[0]);
		assertEquals(14, cols[1]);
		assertEquals(14, cols[2]);
		assertEquals(13, cols[3]);
		assertEquals(13, cols[4]);
		System.out.println(ArrayUtils.toString(cols));


		w = new WidthAbsoluteEven(79);
		at = new V2_AsciiTable();
		at.addRow("one", "two", "three", "four", "five");
		cols = w.getColumnWidths(at);
		assertEquals(5, cols.length);
		assertEquals(15, cols[0]);
		assertEquals(15, cols[1]);
		assertEquals(15, cols[2]);
		assertEquals(14, cols[3]);
		assertEquals(14, cols[4]);
		System.out.println(ArrayUtils.toString(cols));
	}
}
