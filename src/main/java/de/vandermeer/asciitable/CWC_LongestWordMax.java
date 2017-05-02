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
 * Calculates the width of table columns using the longest word in a column with maximum column width settings.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.3.0
 */
public class CWC_LongestWordMax implements AT_ColumnWidthCalculator {

	/** The maximum width of all columns. */
	protected int max;

	/** The maximum width per individual column. */
	protected int[] maxAr;

	/**
	 * Creates a new width object.
	 * @param max the maximum width of each column, cannot be smaller than 3
	 * @throws IllegalArgumentException if the parameter was less than 3
	 */
	public CWC_LongestWordMax(int max){
		Validate.validState(max>=3, "maximum column width cannot be smaller than 3");
		this.max = max;
	}

	/**
	 * Creates a new width object.
	 * @param maxAr the maximum width per individual column, entries of -1 will be ignored and the longest word being used
	 * @throws IllegalArgumentException if the array had width of less than 3 or was null
	 */
	public CWC_LongestWordMax(int[] maxAr){
		Validate.notNull(maxAr);
		for(int m : maxAr){
			if(m!=-1 && m<3){
				throw new IllegalArgumentException("array contains maximum column width smaller than 3");
			}
		}
		this.maxAr = maxAr;
	}

	@Override
	public int[] calculateColumnWidths(LinkedList<AT_Row> rows, int colNumbers, int tableWidth) {
		Validate.notNull(rows);

		if(this.maxAr!=null && this.maxAr.length!=colNumbers){
			throw new IllegalArgumentException("maxAr length is not the same as rows in the table");
		}

		int[] ret = CWC_LongestWord.longestWord(rows, colNumbers);
		for(int i=0; i<ret.length; i++){
			if(this.max!=0){
				if(ret[i]>this.max){
					ret[i] = this.max;
				}
			}
			else if(this.maxAr!=null){
				if(this.maxAr[i]!=-1){
					if(ret[i]>this.maxAr[i]){
						ret[i] = this.maxAr[i];
					}
				}
			}
		}
		return ret;
	}

}
