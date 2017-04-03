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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import de.vandermeer.skb.interfaces.document.TableRowType;
import de.vandermeer.skb.interfaces.transformers.Object_To_StrBuilder;

/**
 * Calculates the width of table columns using the longest word in a column.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0-SNAPSHOT build 170331 (31-Mar-17) for Java 1.8
 * @since      v0.3.0
 */
public class CWC_LongestWord implements AT_ColumnWidthCalculator {

	@Override
	public int[] calculateColumnWidths(LinkedList<AT_Row> rows, int colNumbers, int tableWidth) {
		Validate.notNull(rows);
		return CWC_LongestWord.longestWord(rows, colNumbers);
	}

	/**
	 * Returns an array with the width of the longest word per column calculated from the given table.
	 * Default padding will be added per column.
	 * Padding for individual columns will be added if defined.
	 * @param rows the table rows for calculations
	 * @param colNumbers number of columns in the table
	 * @return array with width of longest word for each column, null if input table was null
	 */
	public static int[] longestWord(LinkedList<AT_Row> rows, int colNumbers){
		if(rows==null){
			return null;
		}

		if(rows.size()==0){
			return new int[0];
		}

		int[] ret = new int[colNumbers];

		for(AT_Row row : rows){
			if(row.getType()==TableRowType.CONTENT) {
				LinkedList<AT_Cell> cells = row.getCells();

				for(int i=0; i<cells.size(); i++) {
					if(cells.get(i).getContent()!=null){
						String[] ar = StringUtils.split(Object_To_StrBuilder.convert(cells.get(i).getContent()).toString());
						for(int k=0; k<ar.length; k++){
							int count = ar[k].length() + cells.get(i).getContext().getPaddingLeft() + cells.get(i).getContext().getPaddingRight();
							if(count>ret[i]){
								ret[i] = count;
							}
						}
					}
				}
			}
		}

		return ret;
	}
}
