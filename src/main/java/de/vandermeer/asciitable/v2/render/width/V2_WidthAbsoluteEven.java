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

package de.vandermeer.asciitable.v2.render.width;

/**
 * Utility to define the width of table columns using an absolute table width evenly distributed over all columns.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 150814 (14-Aug-15) for Java 1.7
 * @since      v0.0.5
 */
public class V2_WidthAbsoluteEven extends AbstractWidth {

	/**
	 * Sets the absolute width of the table.
	 * @param width absolute table width as number of characters
	 * @return self to allow for chaining
	 */
	public V2_WidthAbsoluteEven setWidth(int width){
		if(width>=3){
			this.width = width;
		}
		return this;
	}

	@Override
	public int[] getColumnWidths(int padding) {
		int[] ret = new int[this.colNumber];

		int content = 0;
		if(padding>0){
			content = this.colNumber*padding + this.colNumber;
		}
		else{
			content = this.colNumber*3;
		}
		int borders = this.colNumber + 1;
		if(this.width<(content + borders)){
			throw new IllegalArgumentException("wrong width argument: width must allow for borders");
		}

		int distribute = this.width-1-this.colNumber;		//this is to be distributed over columns
		int colmin = distribute/this.colNumber;				//this is minimum width of each column
		int leftover = distribute-colmin*this.colNumber;	//leftover

		for(int i=0; i<this.colNumber; i++){
			ret[i] = colmin;
			if(leftover!=0){
				ret[i] += 1;
				leftover -= 1;
			}
		}
		return ret;
	}

}
