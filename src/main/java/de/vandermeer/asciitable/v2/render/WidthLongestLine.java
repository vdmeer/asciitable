/* Copyright 2015 Sebastian Thomschke <sebthom@sourceforge.net>
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

import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;

import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.row.ContentRow;
import de.vandermeer.asciitable.v2.row.V2_Row;

/**
 * Utility to define the width of table columns automatically based on the longest line in each column.
 *
 * @author     Sebastian Thomschke &lt;sebthom@sourceforge.net&gt;
 * @version    v0.2.2 build 150827 (27-Aug-15) for Java 1.7
 * @since      v0.2.2
 */
public class WidthLongestLine implements V2_Width {
	private static final Pattern NEW_LINE = Pattern.compile("[\\r?\\n]+");
	private static final String[] EMPTY_CELL = new String[] { "" };

	private int[] minWidths = new int[0];
	private int[] maxWidths = new int[0];

	/**
	 * Adds a column with the the given minimum/maximum column width.
	 *
	 * @param minWidth minimum column width in number of characters
	 * @param maxWidth maximum column width in number of characters
	 * @return self to allow for chaining
	 */
	public WidthLongestLine add(final int minWidth, final int maxWidth) {
		this.minWidths = ArrayUtils.add(this.minWidths, minWidth);
		this.maxWidths = ArrayUtils.add(this.maxWidths, maxWidth);
		return this;
	}

	@Override
	public int[] getColumnWidths(final V2_AsciiTable table) {
		final int cols = table.getColumnCount();
		final int[] resultWidths = new int[cols];

		// apply min width settings
		System.arraycopy(minWidths, 0, resultWidths, 0, minWidths.length > cols ? cols : minWidths.length);

		// iterate over all rows
		for (final V2_Row row : table.getTable()) {
			if (row instanceof ContentRow) {
				final ContentRow crow = (ContentRow) row;
				final Object[] cells = crow.getColumns();

				// iterate over all cells in the row
				for (int i = 0; i < cells.length; i++) {
					final String[] lines = cells[i] == null ? EMPTY_CELL : NEW_LINE.split(cells[i].toString());
					// measuring the width of each line within a cell
					for (final String line : lines) {
						final int lineWidth = line.length() + 2 * crow.getPadding()[i];
						if (lineWidth > resultWidths[i]) {
							final int maxWidth = maxWidths.length > i ? maxWidths[i] : 0;
							if (maxWidth < 1 || lineWidth < maxWidth) {
								resultWidths[i] = lineWidth;
							}
							else {
								resultWidths[i] = maxWidth;
							}
						}
					}
				}
			}
		}
		return resultWidths;
	}
}
