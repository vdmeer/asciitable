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

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import de.svenjacobs.loremipsum.LoremIpsum;

/**
 * Tests for {@link CWC_LongestWord}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0-SNAPSHOT build 170331 (31-Mar-17) for Java 1.8
 * @since      v0.0.3
 */
public class Test_CWC_LongestWord {

	@Test
	public void test_CodeForDoc(){
		AsciiTable at;
		int[] cols;
		CWC_LongestWord cwc = new CWC_LongestWord();

		at = new AsciiTable();
		at.addRule();
		at.addRow("first", "information");
		at.addRule();
		at.addRow("second", "info");
		at.addRule();
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), at.getContext());

		assertEquals(2, cols.length);
		assertEquals(6,  cols[0]);		// longest word: second (6)
		assertEquals(11, cols[1]);		// longest word: information (11)
		System.out.println(ArrayUtils.toString(cols));

		at = new AsciiTable();
		at.addRule();
		at.addRow("first", "information");
		at.addRule();
		at.addRow("second", "info");
		at.addRule();
		at.setPaddingLeftRight(1);
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), at.getContext());
		assertEquals(2, cols.length);
		assertEquals(8,  cols[0]);		// longest word: second (6) + padding 1*2
		assertEquals(13, cols[1]);		// longest word: information (11) + padding 1*2
		System.out.println(ArrayUtils.toString(cols));


		at = new AsciiTable();
		at.addRule();
		AT_Row row = at.addRow("first", "information");
		row.getCells().get(0).getContext().setPaddingLeftRight(2);
		row.getCells().get(1).getContext().setPaddingLeftRight(3);
		at.addRule();
		row = at.addRow("second", "info");
		row.getCells().get(0).getContext().setPaddingLeftRight(3);
		row.getCells().get(1).getContext().setPaddingLeftRight(4);
		at.addRule();
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), at.getContext());
		assertEquals(2, cols.length);
		assertEquals(12,  cols[0]);		// longest word: second (6) + padding 3*2
		assertEquals(17, cols[1]);		// longest word: information (11) + padding 3*2
		System.out.println(ArrayUtils.toString(cols));


		at = new AsciiTable();
		at.addRow(new LoremIpsum().getWords());
		at.setPaddingLeftRight(1);
		cols = cwc.calculateColumnWidths(at.getRawContent(), at.getColNumber(), at.getContext());
		assertEquals(1, cols.length);
		assertEquals(12,  cols[0]);		// longest word: sadipscing (10) + padding 1
		System.out.println(ArrayUtils.toString(cols));
	}


	@Test
	public void test_Statics(){
		AsciiTable at;
		int[] cols;

		at = new AsciiTable();
		at.addRule();
		at.addRow("first row (col1)", "with some information (col2)");
		at.addRule();
		at.addRow("second row (col1)", "with some information (col2)");
		at.addRule();
		at.setPaddingLeftRight(1);

		cols = CWC_LongestWord.longestWord(at.getRawContent(), at.getColNumber());
		assertEquals(2, cols.length);
		assertEquals(8,  cols[0]);		// longest word: second (6) + padding
		assertEquals(13, cols[1]);		// longest word: information (11) + padding
		System.out.println(ArrayUtils.toString(cols));

		at = new AsciiTable();
		at.addRow(new LoremIpsum().getWords());
		at.setPaddingLeftRight(1);
		cols = CWC_LongestWord.longestWord(at.getRawContent(), at.getColNumber());
		assertEquals(1, cols.length);
		assertEquals(12,  cols[0]);		// longest word: sadipscing (10) + padding
		System.out.println(ArrayUtils.toString(cols));
	}
}
