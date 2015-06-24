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

package de.vandermeer.asciitable.v2;

/**
 * Calculator for table width.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4 build 150619 (19-Jun-15) for Java 1.8
 */
public interface Width {

	/**
	 * Returns an array with calculated width of table columns.
	 * The array length is the number of table columns + 1.
	 * The first entry in the table is the overall table width.
	 * Each following entry is the width of the respective column.
	 * @param table incoming table for calculations
	 * @return array with overall table width and width of individual columns
	 */
	public int[] calculateWidth(AsctiiTable table);
}
