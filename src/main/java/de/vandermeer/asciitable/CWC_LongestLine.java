/* Copyright 2017 Sven van der Meer <vdmeer.sven@mykolab.com>
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

import java.util.LinkedList;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

import de.vandermeer.skb.interfaces.document.TableRowType;
import de.vandermeer.skb.interfaces.transformers.Object_To_StrBuilder;
import de.vandermeer.skb.interfaces.transformers.String_To_ConditionalBreak;

/**
 * Calculates the width of table columns using the longest line in a column.
 * 
 * Note: this can produce strange results, especially when cell content implements render interfaces or very long lines are found.
 * In those cases use minimum and maximum column length.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0-SNAPSHOT build 170331 (31-Mar-17) for Java 1.8
 * @since      v0.3.0
 */
public class CWC_LongestLine implements AT_ColumnWidthCalculator {

	/** Minimum widths. */
	private int[] minWidths = new int[0];

	/** Maximum widths. */
	private int[] maxWidths = new int[0];

	/**
	 * Creates a new width object.
	 * @param minWidth minimum column width as number of characters
	 * @param maxWidth maximum column width as number of characters
	 * @return self to allow for chaining
	 */
	public CWC_LongestLine add(final int minWidth, final int maxWidth) {
		this.minWidths = ArrayUtils.add(this.minWidths, minWidth);
		this.maxWidths = ArrayUtils.add(this.maxWidths, maxWidth);
		return this;
	}

	@Override
	public int[] calculateColumnWidths(LinkedList<AT_Row> rows, int colNumbers, int tableWidth) {
		Validate.notNull(rows);

		int[] resultWidths = new int[colNumbers];

		// apply min width settings
		System.arraycopy(minWidths, 0, resultWidths, 0, minWidths.length > colNumbers ? colNumbers : minWidths.length);

		// iterate over all rows
		for(AT_Row row : rows) {
			if(row.getType()==TableRowType.CONTENT) {
				LinkedList<AT_Cell> cells = row.getCells();

				for(int i=0; i<cells.size(); i++) {
					if(cells.get(i).getContent()!=null){
						String[] lines = String_To_ConditionalBreak.convert(Object_To_StrBuilder.convert(cells.get(i).getContent()).toString());

						// measuring the width of each line within a cell
						for(String line : lines) {
							int lineWidth = line.length() + cells.get(i).getContext().getPaddingLeft() + cells.get(i).getContext().getPaddingRight();
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
