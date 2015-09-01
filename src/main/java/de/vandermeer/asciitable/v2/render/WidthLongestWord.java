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
 * Defines the width of table columns using the longest word per column without further restrictions.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.2 build 150901 (01-Sep-15) for Java 1.7
 * @since      v0.2.1
 */
public class WidthLongestWord implements V2_Width {

	@Override
	public int[] getColumnWidths(V2_AsciiTable table) {
		if(table==null){
			return null;
		}
		return (table==null)?null:WidthUtilities.longestWord(table);
	}

}
