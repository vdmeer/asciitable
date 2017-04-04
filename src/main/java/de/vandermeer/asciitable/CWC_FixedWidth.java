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

/**
 * Calculates the width of table columns using a fixed width for each column.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.3.0
 */
public class CWC_FixedWidth implements AT_ColumnWidthCalculator {

	/** Column width array. */
	int[] ar;

	/**
	 * Creates a new calculator object.
	 * Default internal array is set to size 1 (1 column) of width 0.
	 */
	public CWC_FixedWidth(){
		this.ar = new int[0];
	}

	/**
	 * Adds a column with the column width.
	 * @param width column width in number of characters, ignored if smaller than 3
	 * @return self to allow for chaining
	 */
	public CWC_FixedWidth add(int width){
		if(width>=3){
			this.ar = ArrayUtils.add(this.ar, width);
		}
		return this;
	}

	@Override
	public int[] calculateColumnWidths(LinkedList<AT_Row> rows, int colNumbers, int tableWidth) {
		Validate.notNull(rows);

		if(colNumbers != (this.ar.length)){
			throw new AsciiTableException(this.getClass().getSimpleName() + ": wrong length of columns array", "columns array length must be the same as the columns used to initialize the table, expeced <" + colNumbers + "> found <" + this.ar.length + ">");
		}
		int[] ret = ArrayUtils.addAll(new int[0], this.ar);
		return ret;
	}

}
