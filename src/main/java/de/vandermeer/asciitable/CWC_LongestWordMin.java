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

import org.apache.commons.lang3.Validate;

/**
 * Calculates the width of table columns using the longest word in a column with minimum column width settings.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.3.0
 */
public class CWC_LongestWordMin implements AT_ColumnWidthCalculator {

	/** The minimum width of all columns. */
	protected int min;

	/** The minimum width per individual column. */
	protected int[] minAr;

	/**
	 * Creates a new width object.
	 * @param min the minimum width of each column, cannot be smaller than 3
	 * @throws IllegalArgumentException if the parameter was less than 3
	 */
	public CWC_LongestWordMin(int min){
		Validate.validState(min>=3, "minimum column width cannot be smaller than 3");
		this.min = min;
	}

	/**
	 * Creates a new width object
	 * @param minAr the minimum width per individual column, entries of -1 will be ignored and the longest word being used
	 * @throws IllegalArgumentException if the array had width of less than 3 or was null
	 */
	public CWC_LongestWordMin(int[] minAr){
		Validate.notNull(minAr);
		for(int m : minAr){
			if(m!=-1 && m<3){
				throw new IllegalArgumentException("array contains minimum column width smaller than 3");
			}
		}
		this.minAr = minAr;
	}

	@Override
	public int[] calculateColumnWidths(LinkedList<AT_Row> rows, int colNumbers, int tableWidth) {
		Validate.notNull(rows);

		if(this.minAr!=null && this.minAr.length!=colNumbers){
			throw new IllegalArgumentException("minAr length is not the same as rows in the table");
		}

		int[] ret = CWC_LongestWord.longestWord(rows, colNumbers);
		for(int i=0; i<ret.length; i++){
			if(this.min!=0){
				if(ret[i]<this.min){
					ret[i] = this.min;
				}
			}
			else if(this.minAr!=null){
				if(this.minAr[i]!=-1){
					if(ret[i]<this.minAr[i]){
						ret[i] = this.minAr[i];
					}
				}
			}
		}
		return ret;
	}

}
