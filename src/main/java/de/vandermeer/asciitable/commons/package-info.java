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
 * Common artifacts for the ASCII tables.
 * 
 * 
 * <h3>Array Transformations</h3>
 * Collections of utility methods to manipulate (transform) arrays.
 * They solve a number of common problems when using multi-dimensional arrays (as the table implementations do for rendering, here 2-dimensional arrays):
 * <ul>
 * 		<li>a {@code toString()} method for 2-dimensional arrays (I didn't find anyone elsewhere) - {@link de.vandermeer.asciitable.commons.ArrayTransformations#ARRAY_TO_STRING(Object[][])}</li>
 * 		<li>a method to flip 2-dimensional arrays, making it possible to easily use loops e.g. for rendering - {@link de.vandermeer.asciitable.commons.ArrayTransformations#FLIP_ARRAY(String[][])}</li>
 * 		<li>a method to normalize 2-dimensional arrays, meaning to add cells with null or empty content so that all rows have the same number of columns - {@link de.vandermeer.asciitable.commons.ArrayTransformations#NORMALISE_ARRAY(int, String[][])}</li>
 * 		<li>a method to wrap text lines in order to maintain an overall column length - {@link de.vandermeer.asciitable.commons.ArrayTransformations#WRAP_LINES(int, Object)}</li>
 * </ul>
 * 
 * 
 * <h4>The Problem</h4>
 * <p>
 * 		Let's call a row a line in a table.
 * 		A table can have multiple (basically any number of ) rows.
 * 		Let's call a column a part of a row with special formatting.
 * 		A row can have one or more columns, with each row of a the same table having the same number of columns.
 * 		Let's call a cell a part of the table identified by row and column.
 * 		For instance the 2nd column of the 3rd row would be cell 3-2.
 * </p>
 * 
 * Filling a table with content means to add rows with their columns, one row at a time until all rows are added.
 * The tables here provide and {@code addRow()} method with variable argument, checking per table that the number of columns (objects in the argument) is equal per row added.
 * If all cells fit in the defined (or calculated) width of the columns and tables, nothing special is required.
 * Here, one can use lists or sets or arrays to represent the rows with their columns and easily render them.
 * For instance, the following code creates a table with all cells fitting in the calculated width (2 rows with 3 columns each and width of 19):
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRule();
	at.addRow("1-1", "1-2", "1-3");
	at.addRule();
	at.addRow("2-1", "2-2", "2-3");
	at.addRule();
 * }</pre>
 * With the rendered output of (using a renderer with the {@link de.vandermeer.asciitable.v2.themes.V2_E_TableThemes#UTF_LIGHT} theme):
 * <pre style="line-height:17px">
	┌─────┬─────┬─────┐
	│ 1-1 │ 1-2 │ 1-3 │
	├─────┼─────┼─────┤
	│ 2-1 │ 2-2 │ 2-3 │
	└─────┴─────┴─────┘
 * </pre>
 * 
 * However, if only one of the cells is "over-width" this simple approach will not work anymore.
 * For instance using the following code for the first row will create a problem:
 * <pre>{@code
	at.addRow("1-1", "1-2 text", "1-3");
 * }</pre>
 * 
 * The expected rendered table would need to wrap the over size cell 1-2 resulting in the following output:
 * <pre style="line-height:17px">
	┌─────┬─────┬─────┐
	│ 1-1 │ 1-2 │ 1-3 │
	│     │ tex │     │
	│     │ t   │     │
	├─────┼─────┼─────┤
	│ 2-1 │ 2-2 │ 2-3 │
	└─────┴─────┴─────┘
 * </pre>
 * 
 * This means to actually add (for the render process), multiple lines to cells 1-1 and 1-3 to match up with the new added lines in cell 2-2.
 * Simply using lists or sets or even arrays may (can) result in complicated code.
 * The approach taken in the ASCII table is to ignore this problem until a table is to be rendered.
 * Then the methods introduced above come in handy to calculate a 2-dimensional array solving the problem described here (and a few more).
 * 
 * 
 * <h4>The Solution - Wrap and Normalize</h4>
 * <p>
 * 		A table is filled as described above: row by row with each row having columns and the content of each column set by an object.
 * 		When filling a table, nothing is done beside creating an array of objects as columns per row added to the table.
 * </p>
 * 
 * <p>
 * 		A renderer then sees a table as a set of rows, each of which is an array of objects.
 * 		The objects are the content of cells, their respective {@code toString()} method being used to create the content.
 * 		The renderer also has the width of columns and the overall table width.
 * </p>
 * 
 * <p>
 * 		Per row, the renderer then creates a 2-dimensional array.
 * 		In the simple case that all cells fit in the given width, this array will have a width of 3 (3 columns per row) and a length of 1 (nothing done with the cell content) per row.
 * </p>
 * 
 * In the simple case (all cells fit) described above, the renderer will simply create the following content arrays for row 1 (top) and row 2 (bottom): 
 * <pre>
	[0][0]: 1-1
	[1][0]: 1-2
	[2][0]: 1-3

	[0][0]: 2-1
	[1][0]: 2-2
	[2][0]: 2-3

 * </pre>
 * 
 * <p>
 * 		For the second example above, with an over-size column,
 * 		the renderer will need to first wrap the contents of cell 1-2
 * 		and then normalize the content array of row 1 adding the new lines in cell 1-1 and cell 1-3
 * 		to match the new created lines for the wrapped lines in cell 1-2.
 * </p>
 * 
 * <p>
 * 		To do that, the renderer uses the two array transformation methods
 * 		{@link de.vandermeer.asciitable.commons.ArrayTransformations#WRAP_LINES(int, Object)} to wrap the lines in cell 1-2 and
 * 		{@link de.vandermeer.asciitable.commons.ArrayTransformations#NORMALISE_ARRAY(int, String[][])} to normalize the cells 1-1 and 1-3.
 * </p>
 * 
 * The content arrays for row 1 and 2 look then as shown below.
 * Row 1 has added lines for cell 1-1 and 1-3 and wrapped lines for cell 1-2 (the content of {@code 0} in those added lines means empty line).
 * Row 2 is the same as for the simple example.
 * <pre>
	[0][0]: 1-1
	[0][1]: 0
	[0][2]: 0
	[1][0]: 1-2
	[1][1]: tex
	[1][2]: t
	[2][0]: 1-3
	[2][1]: 0
	[2][2]: 0

	[0][0]: 2-1
	[1][0]: 2-2
	[2][0]: 2-3
 * </pre>
 * 
 * 
 * <h4>The Solution - Flip</h4>
 * <p>
 * 		Once the renderer has created a content array per row with wrapped lines and normalized cells, the next problem is to create the actual output.
 * 		As can be seen in the last example above (wrapped lines for cell 1-2), using the originally created array is not very efficient,
 * 		because the renderer will need complicated nested loops to reach all cells.
 * </p>
 * 
 * <p>
 * 		However, if we flip the content array (do a horizontal and vertical transformation), we will get a content array that has per first dimension all cells to be printed.
 * 		This is what {@link de.vandermeer.asciitable.commons.ArrayTransformations#FLIP_ARRAY(String[][])} does.
 * </p>
 * 
 * For the simple example above, created content array looked like this:
 * <pre>
	[0][0]: 1-1
	[1][0]: 1-2
	[2][0]: 1-3

	[0][0]: 2-1
	[1][0]: 2-2
	[2][0]: 2-3
 * </pre>
 * 
 * Flipping these arrays will result in the following:
 * <pre>
	[0][0]: 1-1
	[0][1]: 1-2
	[0][2]: 1-3

	[0][0]: 2-1
	[0][1]: 2-2
	[0][2]: 2-3
 * </pre>
 * 
 * <p>
 * 		Now, it is a simple loop throw the arrays first dimension for all printable lines, and a simple loop through the arrays second dimension for all printable cells.
 * </p>
 * 
 * Similar for the over-size example above. The original content arrays looked like this:
 * <pre>
	[0][0]: 1-1
	[0][1]: 0
	[0][2]: 0
	[1][0]: 1-2
	[1][1]: tex
	[1][2]: t
	[2][0]: 1-3
	[2][1]: 0
	[2][2]: 0

	[0][0]: 2-1
	[1][0]: 2-2
	[2][0]: 2-3
 * </pre>
 * 
 * Fliping the arrays will result in the following arrays for the renderer:
 * <pre>
	[0][0]: 1-1
	[0][1]: 1-2
	[0][2]: 1-3
	[1][0]: 0
	[1][1]: tex
	[1][2]: 0
	[2][0]: 0
	[2][1]: t
	[2][2]: 0

	[0][0]: 2-1
	[0][1]: 2-2
	[0][2]: 2-3
 * </pre>
 * 
 * 
 * <h4>Complete solution - creating a content array</h4>
 * Taking version 2 and the over-size example above, a complete solution is implemented in {@link de.vandermeer.asciitable.v2.render.RenderUtilities#createContentArray(Object[], int[], int[])}.
 * The method receives the original content array of a row plus an integer array with column width and an integer array with padding per column (V2 introduced padding for columns).
 * The following code shows how a renderer would call this method:
 * <pre>{@code
	String[][] ar;
	ar = V2_Utilities.createContentArray(new Object[]{"1-1", "1-2 text", "1-3"}, new int[]{5, 5, 5}, new int[]{1, 1, 1});
 * }</pre>
 * 
 * The method then creates a return array:
 * <pre>{@code
	String[][] ret = new String[width.length][];
 * }</pre>
 * 
 * Per entry in the given content array, it then determins if lines have to be wrapped (content of {@code null} means column span):
 * <pre>{@code
	int length = 0;
	for(int i=0; i<columns.length; i++){
		Object o = columns[i];
		if(o==null){
			length += width[i];
			continue;
		}
		else{
			length += width[i];
		}
		if(padding[i]>0){
			length = length - padding[i]*2;
		}
		ret[i] = ArrayTransformations.WRAP_LINES(length, o);
		length = 0;
	}
 * }</pre>
 * 
 * Once the line wrapping is done, the resulting array can be normalized:
 * <pre>{@code
	ret = ArrayTransformations.NORMALISE_ARRAY(width.length, ret);
 * }</pre>
 * 
 * And then finally flipped and returned:
 * <pre>{@code
	ret = ArrayTransformations.FLIP_ARRAY(ret);
	return ret;
 * }</pre>
 * 
 * 
 * 
 * <h3>A ToString Style</h3>
 * A style definition helping to simplify {@code toString()} methods using {@link org.apache.commons.lang3.builder.StandardToStringStyle}.
 * 
 * 
 * 
 * <h3>A Table Exception</h3>
 * {@link de.vandermeer.asciitable.commons.TableException} implements a runtime exception.
 * This is used in the tables for instance for initialization substituting for more common runtime or illegal argument exceptions.
 * 
 * 
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.2 build 150827 (27-Aug-15) for Java 1.7
 */
package de.vandermeer.asciitable.commons;

