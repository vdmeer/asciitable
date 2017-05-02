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

/**
 * Calculates the width of columns for an {@link AsciiTable}.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.3.0
 */
public interface AT_ColumnWidthCalculator {

	/**
	 * Returns the width of each column in an array, column one being [0] and so on.
	 * @param rows the table rows with rules and content
	 * @param colNumbers number of columns in the table
	 * @param ctx the original table context, the required width is taken from this context
	 * @returnan array with the width for each column
	 */
	default int[] calculateColumnWidths(LinkedList<AT_Row> rows, int colNumbers, AT_Context ctx){
		return this.calculateColumnWidths(rows, colNumbers, ctx.getTextWidth());
	}

	/**
	 * Returns the width of each column in an array, column one being [0] and so on.
	 * @param rows the table rows with rules and content
	 * @param colNumbers number of columns in the table
	 * @param tableWidth required overall table width
	 * @return an array with the width for each column
	 */
	int[] calculateColumnWidths(LinkedList<AT_Row> rows, int colNumbers, int tableWidth);
}
