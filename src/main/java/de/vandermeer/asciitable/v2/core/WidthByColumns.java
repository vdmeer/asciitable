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

package de.vandermeer.asciitable.v2.core;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Utility to define the width of columns for a table renderer using width for individual columns.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.7 build 150811 (11-Aug-15) for Java 1.7
 * @since      v0.0.5
 */
public class WidthByColumns implements Width {

	/** Column width array. */
	int[] ar;

	/**
	 * Returns a new table width calculator.
	 * Default internal array is set to size 1 (1 column) of width 0.
	 */
	public WidthByColumns(){
		this.ar = new int[1];
	}

	/**
	 * Adds a column with the column width
	 * @param width column width in number of characters
	 * @return self to allow for chaining
	 */
	public WidthByColumns add(int width){
		if(width>=3){
			this.ar = ArrayUtils.add(this.ar, width);
		}
		this.ar[0] = this.getWidthSum();
		return this;
	}

	/**
	 * Returns the sum of all set columns.
	 * @return sum of all set columns
	 */
	public int getWidthSum(){
		int ret=0;
		if(this.ar.length>1){
			for (int i=1; i<this.ar.length; i++){
				ret += this.ar[i];
			}
		}
		return ret;
	}

	/**
	 * Returns the currently set column array.
	 * @return column array
	 */
	public int[] getArray(){
		return this.ar;
	}

	@Override
	public int[] calculateWidth(int columnCount) {
		if(columnCount != (this.ar.length-1)){
			throw new IllegalArgumentException("wrong columns array length: columns array length must be the same as the columns used to initialise the table");
		}

		int[] ret = ArrayUtils.addAll(new int[0], this.ar);
//		ret[0] = count+1;	//for table grid, number of columns + 1

		return ret;
	}
}
