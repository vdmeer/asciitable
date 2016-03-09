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
 * Defines the width of table columns using the longest word per column but with a maximum column length.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.6-SNAPSHOT build 160306 (06-Mar-16) for Java 1.7
 * @since      v0.2.1
 */
public class WidthLongestWordMaxCol implements V2_Width {

	/** The maximum width of all columns. */
	protected int max;

	/** The maximum width per individual column. */
	protected int[] maxAr;

	/**
	 * Creates a new width object.
	 * @param max the maximum width of each column, cannot be smaller than 3
	 * @throws IllegalArgumentException if the parameter was less than 3
	 */
	public WidthLongestWordMaxCol(int max){
		if(max<3){
			throw new IllegalArgumentException("maximum column width cannot be smaller than 3");
		}
		this.max = max;
	}

	/**
	 * Creates a new width object
	 * @param maxAr the maximum width per individual column, entries of -1 will be ignored and the longest word being used
	 * @throws IllegalArgumentException if the array had width of less than 3 or was null
	 */
	public WidthLongestWordMaxCol(int[] maxAr){
		if(maxAr==null){
			throw new IllegalArgumentException("maximum array cannot be null");
		}
		for(int m : maxAr){
			if(m!=-1 && m<3){
				throw new IllegalArgumentException("array contains maximum column width smaller than 3");
			}
		}
		this.maxAr = maxAr;
	}

	@Override
	public int[] getColumnWidths(V2_AsciiTable table) {
		if(table==null){
			return null;
		}
		if(this.maxAr!=null && this.maxAr.length!=table.getColumnCount()){
			throw new IllegalArgumentException("maxAr length is not the same as rows in the table");
		}

		int[] ret = WidthUtilities.longestWord(table);
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
