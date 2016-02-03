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

import org.apache.commons.lang3.StringUtils;

import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.row.ContentRow;
import de.vandermeer.asciitable.v2.row.V2_Row;

/**
 * Utility methods for width calculation.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.4 build 160203 (03-Feb-16) for Java 1.7
 * @since      v0.2.1
 */
public abstract class WidthUtilities {

	/**
	 * Returns an array with the width of the longest word per column calculated from the given table.
	 * Default padding will be added per column.
	 * Padding for individual columns will be added if defined.
	 * @param table to use for calculation
	 * @return array with width of longest word for each column, null if input table was null
	 */
	public static int[] longestWord(V2_AsciiTable table){
		if(table==null){
			return null;
		}

		if(table.getTable().size()==0){
			return new int[0];
		}

		int[] ret = new int[table.getColumnCount()];

		for(V2_Row row : table.getTable()){
			if(row instanceof ContentRow){
				ContentRow crow = (ContentRow)row;
				for(int i=0; i<crow.getColumns().length; i++){
					if(crow.getColumns()[i]!=null){
						String[] ar = StringUtils.split(crow.getColumns()[i].toString());
						for(int k=0; k<ar.length; k++){
							int count = ar[k].length() + crow.getPadding()[i] + crow.getPadding()[i];
							if(count>ret[i]){
								ret[i] = count;
							}
						}
					}
				}
			}
		}

		return ret;
	}
}
