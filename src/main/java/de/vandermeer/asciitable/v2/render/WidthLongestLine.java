/* Copyright 2015 Sebastian Thomschke <sebthom@sourceforge.net> & Sven van der Meer <vdmeer.sven@mykolab.com>
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

import org.apache.commons.lang3.ArrayUtils;

import de.vandermeer.asciitable.commons.ArrayTransformations;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.row.ContentRow;
import de.vandermeer.asciitable.v2.row.V2_Row;

/**
 * Defines the width of table columns automatically based on the longest line in each column with optional minimum/maximum column width.
 *
 * @author     Sebastian Thomschke &lt;sebthom@sourceforge.net&gt;
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.6-SNAPSHOT build 160306 (06-Mar-16) for Java 1.7
 * @since      v0.2.2
 */
public class WidthLongestLine implements V2_Width {
	private int[] minWidths = new int[0];
	private int[] maxWidths = new int[0];

	/**
	 * Creates a new width object.
	 * @param minWidth minimum column width as number of characters
	 * @param maxWidth maximum column width as number of characters
	 * @return self to allow for chaining
	 */
	public WidthLongestLine add(final int minWidth, final int maxWidth) {
		this.minWidths = ArrayUtils.add(this.minWidths, minWidth);
		this.maxWidths = ArrayUtils.add(this.maxWidths, maxWidth);
		return this;
	}

	@Override
	public int[] getColumnWidths(final V2_AsciiTable table) {
		int cols = table.getColumnCount();
		int[] resultWidths = new int[cols];

		// apply min width settings
		System.arraycopy(minWidths, 0, resultWidths, 0, minWidths.length > cols ? cols : minWidths.length);

		// iterate over all rows
		for(V2_Row row : table.getTable()) {
			if(row instanceof ContentRow) {
				ContentRow crow = (ContentRow)row;
				Object[] cells = crow.getColumns();

				// iterate over all cells in the row
				for(int i=0; i<cells.length; i++) {
					String[] lines = ArrayTransformations.PROCESS_CONTENT(cells[i]);
					if(lines!=null){
						// measuring the width of each line within a cell
						for(String line : lines) {
							int lineWidth = line.length() + 2 * crow.getPadding()[i];
							if(lineWidth > resultWidths[i]) {
								int maxWidth = (maxWidths.length>i)?maxWidths[i]:0;
								if(maxWidth<1 || lineWidth<maxWidth){
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
		}
		return resultWidths;
	}
}
