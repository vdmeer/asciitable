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

package de.vandermeer.asciitable;

import static org.junit.Assert.*;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * Tests for {@link CWC_AbsoluteEven}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0-SNAPSHOT build 170331 (31-Mar-17) for Java 1.8
 * @since      v0.0.3
 */
public class Test_CWC_AbsoluteEven {

	@Test
	public void test_CodeForDoc(){
		CWC_AbsoluteEven cwc = new CWC_AbsoluteEven();
		int[] cols;
		AsciiTable at;

		at = new AsciiTable();
		at.addRow("one");
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), 74);
		assertEquals(1, cols.length);
		assertEquals(72, cols[0]);
		System.out.println(ArrayUtils.toString(cols));


		at = new AsciiTable();
		at.addRow("one", "two");
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), 74);
		assertEquals(2, cols.length);
		assertEquals(36, cols[0]);
		assertEquals(35, cols[1]);
		System.out.println(ArrayUtils.toString(cols));


		at = new AsciiTable();
		at.addRow("one", "two", "three");
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), 74);
		assertEquals(3, cols.length);
		assertEquals(24, cols[0]);
		assertEquals(23, cols[1]);
		assertEquals(23, cols[2]);
		System.out.println(ArrayUtils.toString(cols));


		at = new AsciiTable();
		at.addRow("one", "two", "three", "four");
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), 74);
		assertEquals(4, cols.length);
		assertEquals(18, cols[0]);
		assertEquals(17, cols[1]);
		assertEquals(17, cols[2]);
		assertEquals(17, cols[3]);
		System.out.println(ArrayUtils.toString(cols));


		at = new AsciiTable();
		at.addRow("one", "two", "three", "four", "five");
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), 74);
		assertEquals(5, cols.length);
		assertEquals(14, cols[0]);
		assertEquals(14, cols[1]);
		assertEquals(14, cols[2]);
		assertEquals(13, cols[3]);
		assertEquals(13, cols[4]);
		System.out.println(ArrayUtils.toString(cols));


		at = new AsciiTable();
		at.addRow("one", "two", "three", "four", "five");
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), 79);
		assertEquals(5, cols.length);
		assertEquals(15, cols[0]);
		assertEquals(15, cols[1]);
		assertEquals(15, cols[2]);
		assertEquals(14, cols[3]);
		assertEquals(14, cols[4]);
		System.out.println(ArrayUtils.toString(cols));
	}
}
