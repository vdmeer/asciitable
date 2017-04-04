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

import org.apache.commons.lang3.Validate;

/**
 * Calculates the width of table columns using an absolute table width evenly distributed over all columns.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.3.0
 */
public class CWC_AbsoluteEven implements AT_ColumnWidthCalculator {

	@Override
	public int[] calculateColumnWidths(LinkedList<AT_Row> rows, int colNumbers, int tableWidth) {
		Validate.notNull(rows);

		int colNumber = colNumbers;
		int[] ret = new int[colNumber];

		int content = colNumber*3;
//		if(table.getDefaultPadding()>0){
//			content = colNumber*table.getDefaultPadding() + colNumber;
//		}

		int borders = colNumber + 1;
		if(tableWidth < (content + borders)){
			throw new IllegalArgumentException("wrong width argument: width must allow for borders");
		}

		int distribute = tableWidth - 1 - colNumber;		//this is to be distributed over columns
		int colmin = distribute / colNumber;				//this is minimum width of each column (w/o leftover)
		int leftover = distribute - colmin * colNumber;		//leftover

		for(int i=0; i<colNumber; i++){
			ret[i] = colmin;
			if(leftover!=0){
				ret[i] += 1;
				leftover -= 1;
			}
		}
		return ret;
	}

}
