/*
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

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

/**
 * Tests for {@link WidthLongestLine}.
 *
 * @author     Sebastian Thomschke &lt;sebthom@sourceforge.net&gt;
 * @version    v0.2.2 build 150819 (19-Aug-15) for Java 1.7
 * @since      v0.2.1
 */
public class Test_WidthLongestLine {

	@Test
	public void test_WidthLongestLine() {
		final V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.PLAIN_7BIT.get());

		V2_AsciiTable at;
		int[] cols;
		WidthLongestLine w;

		at = new V2_AsciiTable();
		final int padd = 2 * at.getDefaultPadding();
		at.addRule();
		at.addRow("", "1", "22", "333", "4444");
		at.addRule();
		w = new WidthLongestLine();
		cols = w.getColumnWidths(at);

		System.out.println(at.getDefaultPadding());
		System.out.println(rend.setWidth(w).render(at));
		assertEquals(5, cols.length);
		assertTrue(Arrays.equals(new int[] { padd + 0, padd + 1, padd + 2, padd + 3, padd + 4 }, cols));

		w.add(padd + 2, 0);
		cols = w.getColumnWidths(at);
		System.out.println(Arrays.toString(cols));
		System.out.println(rend.setWidth(w).render(at));
		assertEquals(5, cols.length);
		assertTrue(Arrays.equals(new int[] { padd + 2, padd + 1, padd + 2, padd + 3, padd + 4 }, cols));

		w.add(padd + 2, 0);
		w.add(0, 0);
		w.add(0, 0);
		w.add(0, padd + 2);
		cols = w.getColumnWidths(at);
		System.out.println(Arrays.toString(cols));
		System.out.println(rend.setWidth(w).render(at));
		assertEquals(5, cols.length);
		assertTrue(Arrays.equals(new int[] { padd + 2, padd + 2, padd + 2, padd + 3, padd + 2 }, cols));

		at.addRow("", "1", "22", "333\n4444", "4444");
		cols = w.getColumnWidths(at);
		System.out.println(Arrays.toString(cols));
		System.out.println(rend.setWidth(w).render(at));
		assertEquals(5, cols.length);
		assertTrue(Arrays.equals(new int[] { padd + 2, padd + 2, padd + 2, padd + 4, padd + 2 }, cols));
	}
}
