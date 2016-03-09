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
 * V2 classes to render a table.
 *
 *<p>
 * Most of the classes and enumerates here should be used automatically by a V2 ASCII table.
 * The main external class is the {@link de.vandermeer.asciitable.v2.render.V2_TableRenderer}, which is used to render a V2 ASCII table.
 *</p>
 * 
 * 
 * <h3>The Table Renderer Interface</h3>
 * The {@link de.vandermeer.asciitable.v2.render.V2_TableRenderer} interface provides the base line for realizing a table renderer.
 * It defines a number of methods to set the behavior of a renderer:
 * <ul>
 * 		<li>Set the padding character for a renderer (character used to pad all content rows, each line of them) - {@link de.vandermeer.asciitable.v2.render.V2_TableRenderer#setPaddingChar(char)},</li>
 * 		<li>Set the table width being calculated by the given width object - {@link de.vandermeer.asciitable.v2.render.V2_TableRenderer#setWidth(de.vandermeer.asciitable.v2.render.V2_Width)},</li>
 * 		<li>Set the theme the renderer should use to render the table - {@link de.vandermeer.asciitable.v2.render.V2_TableRenderer#setTheme(de.vandermeer.asciitable.v2.themes.V2_TableTheme)}.</li>
 * </ul>
 * 
 * 
 * 
 * <h3>The ASCII Table Renderer Implementation</h3>
 * The class {@link de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer} implements a standard renderer.
 * It is using the theme {@link de.vandermeer.asciitable.v2.themes.V2_E_TableThemes#PLAIN_7BIT} as default theme.
 * 
 * 
 * 
 * <h3>Storing render information per row - {@link de.vandermeer.asciitable.v2.render.ProcessedRow}</h3>
 * <p>
 * 		To enable multiple renderer objects processing the same table each renderer stores all information of the rendering process in a new table (list of rows).
 * 		This new table contains {@link de.vandermeer.asciitable.v2.render.ProcessedRow} objects that contain the render-generated information.
 * 		The original table is not changed (except for adjustment of top and bottom rule row types).
 * 		So each renderer creates its own view of the original table.
 * </p>
 * 
 * <p>
 * 		A processed row contains a link to the original row (for access to original content and configuration)
 * 		plus the generated information for processed columns (wrapped lines, normalized, flipped content array) and border types.
 * </p>
 * 
 * 
 * 
 * <h3>Define a border position</h3>
 * The position of a border element in a table row is defined in {@link de.vandermeer.asciitable.v2.render.BorderPosition} as
 * <ul>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderPosition#LEFT} - a border on the left side of the row,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderPosition#RIGHT} - a border on the right side of the row,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderPosition#MIDDLE} - a border between left and right, in the middle.</li>
 * </ul>
 * 
 * This border position assists a renderer to pick the right character from a row theme.
 * 
 * 
 * 
 * <h3>Define a border type</h3>
 * The type of a border element in a table row is defined in {@link de.vandermeer.asciitable.v2.render.BorderType} as
 * <ul>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderType#UP} - a border that goes up (linked to the previous row) only,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderType#DOWN} - a border that goes down (linked to the next row) only,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderType#ALL} - a border that goes up and down (linked to the previous and to the next row),</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderType#CONTENT} - a border specific to a content row with special linkage to previous and next rows,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderType#NONE} - a border that goes neither up nor down nor is a content border, usually a blank character, used for column spanning.</li>
 * </ul>
 * 
 * This border type assists a renderer to pick the right character from a row theme.
 * 
 * 
 * 
 * <h3>A complete border description</h3>
 * <p>
 * 		Combining border type and border position describes a complete border.
 * 		With both characteristics, the renderer can pick the correct border character from a row or table theme.
 * 		Once the type and the position of the row are determined, border character can be retrieved from table and row themes. 
 * </p>
 * 
 * <p>
 * 		For instance, a border of type {@link de.vandermeer.asciitable.v2.render.BorderPosition#MIDDLE} and position {@link de.vandermeer.asciitable.v2.render.BorderType#ALL}
 * 		means to use {@code getMidBorderAll()} for a mid-rule row on a {@link de.vandermeer.asciitable.v2.themes.V2_RowTheme} to get the right character.
 * 		For the row theme {@link de.vandermeer.asciitable.v2.themes.V2_E_RowThemes#UTF_DOUBLE_MID} this character will be '╬'.
 * </p>
 * 
 * <p>
 * 		Similar, a border of type {@link de.vandermeer.asciitable.v2.render.BorderPosition#RIGHT} and position {@link de.vandermeer.asciitable.v2.render.BorderType#UP}
 * 		means to use {@code getMidBorderAll()} for a bottom-rule row.
 * 		For the row theme {@link de.vandermeer.asciitable.v2.themes.V2_E_RowThemes#UTF_DOUBLE_BOTTOM} this character will be '╝'.
 * </p>
 * 
 * 
 * 
 * <h3>Render Utilities</h3>
 * The class {@link de.vandermeer.asciitable.v2.render.RenderUtilities} implements a number of utility methods applicable to all (many) renderer implementations:
 * <ul>
 * 		<li>Create a content array for a row that wraps over-size lines, normalizes the row array, and finally flips it for easy rendering - {@link de.vandermeer.asciitable.v2.render.RenderUtilities#createContentArray(Object[], int[], int[])},</li>
 * 		<li>Calculate the border types for a bottom rule - {@link de.vandermeer.asciitable.v2.render.RenderUtilities#getBorderTypes_BottomRule(ProcessedRow, de.vandermeer.asciitable.v2.row.V2_Row, int)},</li>
 * 		<li>Calculate the border types for a content rule - {@link de.vandermeer.asciitable.v2.render.RenderUtilities#getBorderTypes_ContentRow(String[], de.vandermeer.asciitable.v2.row.ContentRow, int)},</li>
 * 		<li>Calculate the border types for a mid rule - {@link de.vandermeer.asciitable.v2.render.RenderUtilities#getBorderTypes_MidRule(ProcessedRow, ProcessedRow, de.vandermeer.asciitable.v2.row.V2_Row, int)},</li>
 * 		<li>Calculate the border types for a top rule - {@link de.vandermeer.asciitable.v2.render.RenderUtilities#getBorderTypes_TopRule(ProcessedRow, de.vandermeer.asciitable.v2.row.V2_Row, int)},</li>
 * 		<li>Determine the actual border character to be used from a row theme for a given border type and position - {@link de.vandermeer.asciitable.v2.render.RenderUtilities#getChar(BorderPosition, BorderType, de.vandermeer.asciitable.v2.themes.V2_RowTheme)}.</li>
 * </ul>
 * 
 * 
 * <h3>Table and column width</h3>
 *  <p>
 * 		The calculation of the overall table width and the width of individual columns is out-sourced into objects implementing the {@link de.vandermeer.asciitable.v2.render.V2_Width} interface.
 * 		This allows to define many different possible ways of calculating table/column width, and then hand it over to a renderer to build a rendered table.
 * </p>
 * 
 * 
 * <h4>Width Interface</h4>
 * The width interface defines the following methods:
 * <ul>
 * 		<li>get the width of individual columns of the table - requests the width object to calculate and return width of individual columns, delivered in an integer array. The input character is the default (i.e. minimum) padding for each column set for the renderer - {@link de.vandermeer.asciitable.v2.render.V2_Width#getColumnWidths(de.vandermeer.asciitable.v2.V2_AsciiTable)}</li>
 * </ul>
 * 
 * 
 * <h4>Calculate width using an absolute table width and equally distributed column width</h4>
 * {@link de.vandermeer.asciitable.v2.render.WidthAbsoluteEven} gets the width of the table and equally distributes the available space of the number of columns given by the renderer.
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
 * </ul>
 * 
 * 
 * <h4>Calculate width with a given, fixed width per column</h4>
 * {@link de.vandermeer.asciitable.v2.render.WidthFixedColumns} allows to add fixed column width, one by one.
 * The only calculation it does is adding the given width for the overall table width.
 * 
 * 
 * <h4>Calculate width using the longest word in a column with no further restrictions</h4>
 * <p>
 * 		{@link de.vandermeer.asciitable.v2.render.WidthLongestWord} calculates the widths of columns taking the longest word (continuous characters between whitespaces) without further restrictions.
 * 		Padding is automatically added, so the padding adds to the column width.
 * 		The overall table width is then the sum of all calculated column width.
 * </p>
 * 
 * The following example creates a table with two rows and two columns each and no padding:
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable(0);
	at.addRow("first", "information");
	at.addRow("second", "info");
 * }</pre>
 * 
 * Then we use this table to calculate the width using the longest word:
 * <pre>{@code
	V2_Width width = new WidthLongestWord();
	int[]  = width.getColumnWidths(at);
 * }</pre>
 * 
 * <p>
 * 		The column width calculated are 6 for the first column (longest word: second) and 11 for the second column (longest word: information).
 * 		If the table is created with the default padding of one, the values will change to 8 and 13.
 * </p>
 * 
 * The following example create a table with two rows and two columns each, and it applies different padding to individual columns.
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable(0);
	at.addRow("first", "information").setPadding(new int[]{2, 3});
	at.addRow("second", "info").setPadding(new int[]{3, 4});
 * }</pre>
 * 
 * <p>
 * 		The calculated width for the columns are now as follows:
 * 		12 for the first column (longest word: second plus padding of 3 twice) and
 * 		17 for the second column (longest word: information plus padding of 3 twice). 
 * </p>
 * 
 * 
 * <h4>Calculate width using the longest word in a column with minimum column width</h4>
 * <p>
 * 		{@link de.vandermeer.asciitable.v2.render.WidthLongestWordMinCol} calculates the widths of columns taking the longest word (continuous characters between whitespaces) but with a minimum column width.
 * 		Padding is automatically added, so the padding adds to the column width.
 * 		The overall table width is then the sum of all calculated column width.
 * </p>
 * 
 * The following example creates a table with two rows and two columns each and standard padding (1):
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRow("first", "information");
	at.addRow("second", "info");
 * }</pre>
 * 
 * Then we use this table to calculate the width using the longest word and minimum column width of 11:
 * <pre>{@code
	V2_Width width = new WidthLongestWordMinCol(11);
	int[]  = width.getColumnWidths(at);
 * }</pre>
 * 
 * <p>
 * 		The calculated width for the columns are now as follows:
 * 		11 for the first column (longest word: second plus padding of 1 twice = 8, but minimum column width is set to 11) and
 * 		13 for the second column (longest word: information plus padding of 1 twice). 
 * </p>
 * 
 * We can also define the minimum column width per column. Here, a value of -1 indicates to simply use the longest word and values above 3 (the absolute minimum colmn width) will be taken as minimum width for a particular column:
 * <pre>{@code
	V2_Width width = new WidthLongestWordMinCol(new int[]{-1,50});
	int[]  = width.getColumnWidths(at);
 * }</pre>
 * 
 * <p>
 * 		The calculated width for the columns are now as follows:
 * 		8 for the first column (longest word: second plus padding of 1 twice, no minimum width given) and
 * 		50 for the second column (longest word: information plus padding of 1 twice but minimum width set to 50). 
 * </p>
 * 
 * 
 * <h4>Calculate width using the longest word in a column with maximum column width</h4>
 * <p>
 * 		{@link de.vandermeer.asciitable.v2.render.WidthLongestWordMaxCol} calculates the widths of columns taking the longest word (continuous characters between whitespaces) but with a maximum column width.
 * 		Padding is automatically added, so the padding adds to the column width.
 * 		The overall table width is then the sum of all calculated column width.
 * </p>
 * 
 * The following example creates a table with two rows and two columns each and standard padding (1):
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRow("first", "information");
	at.addRow("second", "info");
 * }</pre>
 * 
 * Then we use this table to calculate the width using the longest word and maximum column width of 10:
 * <pre>{@code
	V2_Width width = new WidthLongestWordMaxCol(10)
	int[]  = width.getColumnWidths(at);
 * }</pre>
 * 
 * <p>
 * 		The calculated width for the columns are now as follows:
 * 		8 for the first column (longest word: second plus padding of 1 twice = 8) and
 * 		10 for the second column (longest word: information plus padding of 1 twice = 11, but maximum width set to 10). 
 * </p>
 * 
 * We can also define the maximum column width per column. Here, a value of -1 indicates to simply use the longest word and other values will be taken as maximum width for a particular column:
 * <pre>{@code
	V2_Width width = new WidthLongestWordMaxCol(new int[]{5,-1});
	int[]  = width.getColumnWidths(at);
 * }</pre>
 * 
 * <p>
 * 		The calculated width for the columns are now as follows:
 * 		5 for the first column (longest word: second plus padding of 1 twice, but maximum set to 5) and
 * 		13 for the second column (longest word: information plus padding of 1 twice, no maximum given). 
 * </p>
 * 
 * 
 * 
 * 
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.6-SNAPSHOT build 160306 (06-Mar-16) for Java 1.7
 */
package de.vandermeer.asciitable.v2.render;

