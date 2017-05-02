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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Tests for {@link CWC_LongestLine}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class Test_CWC_LongestLine {

	@Test
	public void test_CodeForDoc(){
		AsciiTable at;
		int[] cols;
		CWC_LongestLine cwc;

		at = new AsciiTable();
		at.addRule();
		at.addRow("", "1", "22", "333", "4444");
		at.addRule();


		cwc = new CWC_LongestLine();
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), 74);
		assertEquals(5, cols.length);
		assertTrue(Arrays.equals(new int[] { 0, 1, 2, 3, 4 }, cols));


		cwc.add(2, 0);
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), 74);
		System.out.println(Arrays.toString(cols));
		assertEquals(5, cols.length);
		assertTrue(Arrays.equals(new int[] { 2, 1, 2, 3, 4 }, cols));


		cwc.add(2, 0);
		cwc.add(0, 0);
		cwc.add(0, 0);
		cwc.add(0, 2);
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), 74);
		System.out.println(Arrays.toString(cols));
		assertEquals(5, cols.length);
		assertTrue(Arrays.equals(new int[] { 2, 2, 2, 3, 2 }, cols));


		at.addRow("", "1", "22", "333\n4444", "4444");
		at.addRule();
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), 74);
		System.out.println(Arrays.toString(cols));
		assertEquals(5, cols.length);
		assertTrue(Arrays.equals(new int[] { 2, 2, 2, 4, 2 }, cols));
	}
}
