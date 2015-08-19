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

/**
 * V2 classes for setting and calculating table and column widths.
 * 
 * <p>
 * 		The calculation of the overall table width and the width of individual columns is out-sourced into objects implementing the {@link de.vandermeer.asciitable.v2.render.width.V2_Width} interface.
 * 		This allows to define many different possible ways of calculating table/column width, and then hand it over to a renderer to build a rendered table.
 * </p>
 * 
 * 
 * 
 * <h3>Width Interface</h3>
 * The width interface defines the following methods:
 * <ul>
 * 		<li>get the width of individual columns of the table - requests the width object to calculate and return width of individual columns, delivered in an integer array. The input character is the default (i.e. minimum) padding for each column set for the renderer - {@link de.vandermeer.asciitable.v2.render.width.V2_Width#getColumnWidths(int, java.util.List)}</li>
 * </ul>
 * 
 * 
 * 
 * <h3>Calculate width using an absolute table width and equally distributed column width</h3>
 * {@link de.vandermeer.asciitable.v2.render.width.V2_WidthAbsoluteEven} gets the width of the table and equally distributes the available space of the number of columns given by the renderer.
 * 
 * The distribution (characters per column) is the table width minus 1 (last border) minus number of columns (for all other borders).
 * The minimum (ideal) column width is the distribution divided by the number of columns.
 * Potential leftover is the distribution minus minimum column width multiplied by number of columns:
 * <pre>{@code
	int distribute = this.width-1-this.colNumber;
	int colmin = distribute/this.colNumber;
	int leftover = distribute-colmin*this.colNumber;
 * }</pre>
 * 
 * The calculation of the column width then is to give each column the minimum width and for as long as there are leftover characters one additional character.
 * <pre>{@code
	for(int i=0; i<this.colNumber; i++){
		ret[i] = colmin;
		if(leftover!=0){
			ret[i] += 1;
			leftover -= 1;
		}
	}
 * }</pre>
 * 
 * Here are a few examples of calculated column width for tables, all with a padding of 0:
 * <ul>
 * 		<li>table width 74, 1 column:  {@code 72}</li>
 * 		<li>table width 74, 2 columns: {@code 36,35}</li>
 * 		<li>table width 74, 3 columns: {@code 24,23,23}</li>
 * 		<li>table width 74, 4 columns: {@code 18,17,17,17}</li>
 * 		<li>table width 74, 5 columns: {@code 14,14,14,13,13}</li>
 *		<li>table width 79, 5 columns: {@code 15,15,15,14,14}</li>
 * 		<li></li>
 * </ul>
 * 
 * 
 * 
 * <h3>Calculate width with a given, fixed width per column</h3>
 * {@link de.vandermeer.asciitable.v2.render.width.V2_WidthFixedColumns} allows to add fixed column width, one by one.
 * The only calculation it does is adding the given width for the overall table width.
 * 
 * 
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 150814 (14-Aug-15) for Java 1.7
 */
package de.vandermeer.asciitable.v2.render.width;
