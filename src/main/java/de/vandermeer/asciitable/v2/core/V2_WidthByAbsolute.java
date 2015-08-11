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

/**
 * Utility to define the width a table for a table renderer using an absolute table width.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.8 build 150812 (12-Aug-15) for Java 1.7
 * @since      v0.0.5
 */
public class V2_WidthByAbsolute implements V2_Width {

	/** Width of the table. */
	int width;

	/**
	 * Returns a new table width calculator.
	 * Default width is set to 0;
	 */
	public V2_WidthByAbsolute(){
		this.width = 0;
	}

	/**
	 * Sets the absolute width of the table.
	 * @param width absolute table width as number of characters
	 * @return self to allow for chaining
	 */
	public V2_WidthByAbsolute setWidth(int width){
		if(width>=3){
			this.width = width;
		}
		return this;
	}

	@Override
	public int[] calculateWidth(int columnCount) {
		int[] ret = new int[columnCount+1];

		if(this.width<(columnCount*3 + columnCount + 1)){
			throw new IllegalArgumentException("wrong width argument: width must allow for borders");
		}

		ret[0] = this.width;

		int distribute = this.width-1-columnCount;			//this is to be distributed over columns
		int colmin = distribute/columnCount;				//this is minimum width of each column
		int leftover = distribute-colmin*columnCount;		//leftover

		for(int i=0; i<columnCount; i++){
			ret[i+1] = colmin;
			if(leftover!=0){
				ret[i+1] += 1;
				leftover -= 1;
			}
		}
		return ret;
	}
}
