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
 * Calculator for the width of a table.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.3 build 150910 (10-Sep-15) for Java 1.7
 * @since      v0.0.5
 */
public interface V2_Width {

	/**
	 * Returns the width of each column in an array, column one being [0] and so on.
	 * @param table the table with all content, important for some width calculations
	 * @return column width array
	 */
	int[] getColumnWidths(V2_AsciiTable table);

}
