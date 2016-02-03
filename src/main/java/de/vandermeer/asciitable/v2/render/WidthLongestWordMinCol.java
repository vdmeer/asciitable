/* Copyright 2014 Sven van der Meer <vdmeer.sven@mykolab.com>
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

import de.vandermeer.asciitable.v2.V2_AsciiTable;

/**
 * Defines the width of table columns using the longest word per column but with a minimum column length.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.4 build 160203 (03-Feb-16) for Java 1.7
 * @since      v0.2.1
 */
public class WidthLongestWordMinCol implements V2_Width {

	/** The minimum width of all columns. */
	protected int min;

	/** The minimum width per individual column. */
	protected int[] minAr;

	/**
	 * Creates a new width object.
	 * @param min the minimum width of each column, cannot be smaller than 3
	 * @throws IllegalArgumentException if the parameter was less than 3
	 */
	public WidthLongestWordMinCol(int min){
		if(min<3){
			throw new IllegalArgumentException("minimum column width cannot be smaller than 3");
		}
		this.min = min;
	}

	/**
	 * Creates a new width object
	 * @param minAr the minimum width per individual column, entries of -1 will be ignored and the longest word being used
	 * @throws IllegalArgumentException if the array had width of less than 3 or was null
	 */
	public WidthLongestWordMinCol(int[] minAr){
		if(minAr==null){
			throw new IllegalArgumentException("minimum array cannot be null");
		}
		for(int m : minAr){
			if(m!=-1 && m<3){
				throw new IllegalArgumentException("array contains minimum column width smaller than 3");
			}
		}
		this.minAr = minAr;
	}

	@Override
	public int[] getColumnWidths(V2_AsciiTable table) {
		if(table==null){
			return null;
		}
		if(this.minAr!=null && this.minAr.length!=table.getColumnCount()){
			throw new IllegalArgumentException("minAr length is not the same as rows in the table");
		}

		int[] ret = WidthUtilities.longestWord(table);
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
