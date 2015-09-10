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

import org.apache.commons.lang3.ArrayUtils;

import de.vandermeer.asciitable.v2.V2_AsciiTable;

/**
 * Defines the width of table columns using a fixed width for each column.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.3 build 150910 (10-Sep-15) for Java 1.7
 * @since      v0.0.5
 */
public class WidthFixedColumns implements V2_Width{

	/** Column width array. */
	int[] ar;

	/**
	 * Creates a new width object.
	 * Default internal array is set to size 1 (1 column) of width 0.
	 */
	public WidthFixedColumns(){
		this.ar = new int[0];
	}

	/**
	 * Adds a column with the column width
	 * @param width column width in number of characters
	 * @return self to allow for chaining
	 */
	public WidthFixedColumns add(int width){
		if(width>=3){
			this.ar = ArrayUtils.add(this.ar, width);
		}
		return this;
	}

	@Override
	public int[] getColumnWidths(V2_AsciiTable table) {
		if(table==null){
			return null;
		}

		if(table.getColumnCount() != (this.ar.length)){
			throw new IllegalArgumentException("wrong columns array length: columns array length must be the same as the columns used to initialise the table");
		}
		int[] ret = ArrayUtils.addAll(new int[0], this.ar);
		return ret;
	}

}
