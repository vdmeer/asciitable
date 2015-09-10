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
 * Defines the width of table columns using an absolute table width evenly distributed over all columns.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.3 build 150910 (10-Sep-15) for Java 1.7
 * @since      v0.0.5
 */
public class WidthAbsoluteEven implements V2_Width {

	/** Width of the table. */
	protected int width;

	/**
	 * Creates a new width object.
	 * @param width absolute table width as number of characters
	 */
	public WidthAbsoluteEven(int width){
		if(width>=3){
			this.width = width;
		}
	}

	@Override
	public int[] getColumnWidths(V2_AsciiTable table) {
		if(table==null){
			return null;
		}

		int colNumber = table.getColumnCount();
		int[] ret = new int[colNumber];

		int content = 0;
		if(table.getDefaultPadding()>0){
			content = colNumber*table.getDefaultPadding() + colNumber;
		}
		else{
			content = colNumber*3;
		}
		int borders = colNumber + 1;
		if(this.width<(content + borders)){
			throw new IllegalArgumentException("wrong width argument: width must allow for borders");
		}

		int distribute = this.width-1-colNumber;		//this is to be distributed over columns
		int colmin = distribute/colNumber;				//this is minimum width of each column (w/o leftover)
		int leftover = distribute-colmin*colNumber;	//leftover

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
