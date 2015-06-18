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
package de.vandermeer.asciitable.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.text.WordUtils;

import de.vandermeer.asciitable.commons.ArrayTransformations;
import de.vandermeer.asciitable.commons.Table_ToStringStyle;

/**
 * An ASCII table with flexible column number, column width, wrapping, spanning and themes.
 * Using this class is quite simple. First get a new instance of the ASCII Table using one of the two static factory methods:
 * <ul>
 * 		<li>{@link #newTable(Integer, Integer)} for evenly distributed columns,
			for instance <code>AsciiTable at=AsciiTable.newTable(3, 76);</code> will create a new table with 3 columns with 24 characters width each.
			The remaining 4 characters (to table width of 76) are used to vertical lines.
 * 		</li>
 * 		<li>{@link #newTable(Integer[])} for preset column width,
			for instance <code>AsciiTable at=AsciiTable.newTable(new Integer[]{10, 20, 30});</code> will create a new table with 3 columns, where 
			column 1 has a width of 10 characters, column 2 has a width of 20 characters and column 3 has a width of 30 characters. The overall table
			has then a width of 64 characters (60 characters for the columns plus 4 characters for vertical lines).
		</li>
 * </ul>
 * 
 * <p>
 * Once the table is created {@link #addRow(Object...)} can be used to add rows to it. Finally, call {@link #render()} render the table into a string.
 * The table renders using two options: a padding character (default is ' ') and a theme (default is {@link StandardTableThemes#PLAIN_7BIT}.
 * Both options can be changed using {@link AsciiTable#setPaddingCharacter(char)} and {@link AsciiTable#setTheme(StandardTableThemes)}, respectively.
 * The theme needs to implement {@link TableTheme}.
 * A number of standard themes are defined in {@link StandardTableThemes}, including light, double and heavy UTF-8 character boxes and LaTeX style table themes.
 * Themes and padding characters are applied per render call, enabling a table to be rendered multiple times with different options.
 * </p>
 *
 * <p>
 * A simple example creating a table with 3 rows and three columns. The first row spans 3 columns, each column of the second row fits
 * into a single line, and the second and third column of the third row are broken into two lines:<br><br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>AsciiTable at=AsciiTable.newTable(3, 76);</code><br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at.addRow(null, null, "Table Heading");</code><br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at.addRow("first row (col1)", "with some information", "and more information");</code><br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)");</code><br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>System.out.println(at.render());</code><br>
 * <br>The output of this example will be:<br><pre>
 * +--------------------------------------------------------------------------+
 * |                              Table Heading                               |
 * +------------------------+------------------------+------------------------+
 * |first row (col1)        |with some information   |and more information    |
 * +------------------------+------------------------+------------------------+
 * |second row (col1)       |with some information   |and more information    |
 * |                        |(col2)                  |(col3)                  |
 * +------------------------+------------------------+------------------------+
 * </pre>
 *
 * <p>
 * Another example, this time using an <code>Integer[]</code> to initialise the table. The table again has 3 rows and 3 columns, but this time
 * column 1 is set to 10 characters, column 2 is set to 15 characters and column 3 is set to 20 characters. We now also set the theme to "light":<br><br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>Integer[] columns=new Integer[]{10, 15, 20};</code><br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>AsciiTable at=AsciiTable.newTable(columns);</code><br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at.addRow(null, null, "Table Heading");</code><br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at.addRow("row 1", "this is col 2", "and this is column 3");</code><br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>at.addRow("row 2", "some text for column 2", "and some text for column 3");</code><br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>System.out.println(at.render(new TableOptions().setRenderTheme(StandardTableThemes.LIGHT)));</code><br>
 * <br>The output of this example will be:<br>
 * <pre>
 * ┌───────────────────────────────────────────────┐
 * │                 Table Heading                 │
 * ├──────────┬───────────────┬────────────────────┤
 * │row 1     │this is col 2  │and this is column 3│
 * ├──────────┼───────────────┼────────────────────┤
 * │row 2     │some text for  │and some text for   │
 * │          │column 2       │column 3            │
 * └──────────┴───────────────┴────────────────────┘
 * </pre>
 * 
 * If you use the theme <code>StandardTableThemes.DOUBLE</code>, the output should look like this:
 * <pre>
 * ╔═══════════════════════════════════════════════╗
 * ║                 Table Heading                 ║
 * ╠══════════╦═══════════════╦════════════════════╣
 * ║row 1     ║this is col 2  ║and this is column 3║
 * ╠══════════╬═══════════════╬════════════════════╣
 * ║row 2     ║some text for  ║and some text for   ║
 * ║          ║column 2       ║column 3            ║
 * ╚══════════╩═══════════════╩════════════════════╝
 * </pre>
 * 
 * If you use the theme <code>StandardTableThemes.LIGHT_DOUBLE</code>, the output should look like this:
 * <pre>
 * ╒═══════════════════════════════════════════════╕
 * │                 Table Heading                 │
 * ╞══════════╤═══════════════╤════════════════════╡
 * │row 1     │this is col 2  │and this is column 3│
 * ╞══════════╪═══════════════╪════════════════════╡
 * │row 2     │some text for  │and some text for   │
 * │          │column 2       │column 3            │
 * ╘══════════╧═══════════════╧════════════════════╛
 * </pre>
 * 
 * If you use the theme <code>StandardTableThemes.DOUBLE_LIGHT</code>, the output should look like this:
 * <pre>
 * ╓───────────────────────────────────────────────╖
 * ║                 Table Heading                 ║
 * ╟──────────╥───────────────╥────────────────────╢
 * ║row 1     ║this is col 2  ║and this is column 3║
 * ╟──────────╫───────────────╫────────────────────╢
 * ║row 2     ║some text for  ║and some text for   ║
 * ║          ║column 2       ║column 3            ║
 * ╙──────────╨───────────────╨────────────────────╜
 * </pre>
 * 
 * The look and feel of themes with heavy characters can differ, depending on the font that is being used. Many console
 * fonts on windows do not show heavy box drawing characters as monotype or have varying width for whites paces when using
 * heavy character. The following shows a table using the standard heavy theme (<code>StandardTableThemes.HEAVY</code>):
 * <pre>
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃                 Table Heading                 ┃
 * ┣━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┫
 * ┃row 1     ┃this is col 2  ┃and this is column 3┃
 * ┣━━━━━━━━━━╋━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━━━━━━━┫
 * ┃row 2     ┃some text for  ┃and some text for   ┃
 * ┃          ┃column 2       ┃column 3            ┃
 * ┗━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┛
 * </pre>
 * 
 * There are also a number of LaTeX style themes pre-defined. For instance <code>StandardTableThemes.LATEX_LIGHT_TRIPLE_DASH</code>:
 * <pre>
 * ┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
 *                   Table Heading                  
 * ┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
 *  row 1      this is col 2   and this is column 3 
 * ┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
 *  row 2      some text for   and some text for    
 *             column 2        column 3             
 * ┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
 * </pre>
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3 build 150618 (18-Jun-15) for Java 1.8
 */
public final class AsciiTable {

	/** Padding character, default is blank. */
	char padChar = ' ';

	/** Rendering theme, default is plain 7 bit */
	StandardTableThemes theme = StandardTableThemes.PLAIN_7BIT;

	/** Column array. Entry 0 holds the width of the table, each following entry <i>i</i> the width of column <i>i</i>. */
	private Integer[] columns;

	/** The actual table. Each row is identified by an integer, count starts at 1. Columns are then linked lists of strings */
	protected Map<Integer, String[][]> table;

	/**
	 * Returns a new instance of an ASCII Table.
	 * The available width will be evenly distributed over the number of columns. If space is left, the columns
	 * starting from 1 upwards will receive an extra character until the left space is consumed.
	 * The minimum width of a column is 3. This means that width must be at least (columns * 3 + columns + 1).
	 * @param columns number of columns for the table
	 * @param width maximum width of the table
	 * @return new instance or null in case the required width was insufficient for the number of columns
	 */
	public static AsciiTable newTable(Integer columns, Integer width){
		AsciiTable ret = new AsciiTable();
		if(ret._init(columns, width)==false){
			return null;
		}

		return ret;
	}

	/**
	 * Returns a new instance of an ASCII Table.
	 * The actual width of the table is the sum of the width of all columns plus the characters needed to separate columns.
	 * The latter is calculated as (number of columns + 1). Each entry in the column array will be processed and &lt;null&gt; will 
	 * be returned if the entry is &lt;null&gt; or if the requested width for a column is less than 3.
	 * @param columns array with information about the width of each column (entries of array) and number of columns (size of array)
	 * @return new instance or null if no columns given or a column was null or a column width was set to smaller than 3
	 */
	public static AsciiTable newTable(Integer[] columns){
		AsciiTable ret = new AsciiTable();
		if(ret._init(columns)==false){
			return null;
		}

		return ret;
	}

	/**
	 * Instantiates a table.
	 * Use {@link #newTable(Integer[])} or {@link #newTable(Integer, Integer)} to obtain an instance of the ASCII table.
	 */
	protected AsciiTable(){
		this.table=new HashMap<Integer, String[][]>(10);
//		this.rowFormat=new HashMap<Integer, Character[]>(10);
	}

	/**
	 * Sets the padding character, default is blank.
	 * @param padChar new padding character
	 * @return self for chaining
	 */
	public AsciiTable setPaddingCharacter(char padChar){
		this.padChar = padChar;
		return this;
	}

	/**
	 * Sets the rendering theme, default is plain 7 bit.
	 * @param theme new theme
	 * @return self for chaining
	 */
	public AsciiTable setTheme(StandardTableThemes theme){
		if(theme!=null){
			this.theme = theme;
		}
		return this;
	}

	/**
	 * Adds a new row to the table.
	 * For each column of the set column size an object must be provided. This object can be &lt;null&gt;.
	 * It is also allowed that the object's toString() method returns null. The table row will later be build using this information
	 * in the following way:
	 * <ul>
	 * 		<li>if the object for a column is null (or it's toString() method returns null), the column is assumed to be part of a column span.
	 *          This span is calculated from the first column set to null up to the next column that is set to either empty ("") or some content
	 *          (non-empty string). Column spans at the end of a row result in an empty row spanning all requested columns.
	 *          For example, for a 3-column row with equal column-width of 4 characters (plus vertical separators)
	 *          <ul>
	 *          	<li>(null, null, "text") will result in a row <code>"|␣␣␣␣␣text␣␣␣␣␣|"</code></li>
	 *          	<li>(null, "text", null) will result in a row <code>"|␣␣text␣␣␣|␣␣␣␣|"</code></li>
	 *          	<li>("text", null, null) will result in a row <code>"|text|␣␣␣␣␣␣␣␣␣|"</code></li>
	 *          	<li>(null, null, null)   will result in a row <code>"|␣␣␣␣␣␣␣␣␣␣␣␣␣␣|"</code></li>
	 *          </ul>
	 *      <li>if the object's toString() method results in an empty string (""), the column will be padded with the padding character</li>
	 *      <li>in any other cases the result of the object's toString() method is used as text for the column</li>
	 * </ul>
	 * @param columns text for the columns
	 * @return -1 if table has no columns, 0 if given columns are 0 or if number of given columns is not the number of the set columns, index of the added row in all other cases
	 */
	public int addRow(Object ...columns){
		if(this.columns==null){
			return -1;
		}
		if(columns==null || columns.length!=this.getColumnCount()){
			return 0;
		}

		String[][] ar = new String[this.getColumnCount()][];
		for(int i=0; i<columns.length; i++){
			Object o = columns[i];
			ar[i] = this.obj2At(o, i+1);
		}

		ar = ArrayTransformations.NORMALISE_ARRAY(this.getColumnCount(), ar);
		ar = ArrayTransformations.FLIP_ARRAY(ar);	//flip so that each normalised array row is a table column
		this.table.put(this.table.size()+1, ar);
		return this.table.size();
	}

	/**
	 * Returns a clone of the column array.
	 * @return clone of the column array or null if not set
	 */
	public Integer[] getColumnAr(){
		if(this.columns==null){
			return null;
		}
		return this.columns.clone();
	}

	/**
	 * Returns the number of columns set for the table.
	 * @return number of columns or null if no columns set
	 */
	public int getColumnCount(){
		if(this.columns==null){
			return 0;
		}
		return this.columns.length-1;
	}

	/**
	 * Returns the width of the table.
	 * @return 0 if no columns set, width of the table otherwise
	 */
	public int getWidth(){
		if(this.columns==null){
			return 0;
		}
		return this.columns[0];
	}

	/**
	 * Initialises columns.
	 * @param columns number of columns
	 * @param width requested width of the table
	 * @return true if init was ok, false otherwise (no init done)
	 * @see #newTable(Integer, Integer)
	 */
	protected final boolean _init(Integer columns, Integer width){
		if(columns==null || columns<1){
			return false;
		}
		if(width==null || width<(columns*3+columns+1)){
			return false;
		}

		this.columns = new Integer[columns+1];
		this.columns[0] = width;

		int distribute = width-1-columns;				//this is to be distributed over columns
		int colmin = distribute/columns;				//this is minimum width of each column
		int leftover = distribute-colmin*columns;		//leftover

		for(int i=0; i<columns; i++){
			this.columns[i+1] = colmin;
			if(leftover!=0){
				this.columns[i+1] += 1;
				leftover -= 1;
			}
		}
		return true;
	}

	/**
	 * Initialises columns.
	 * @param columns array with column width information (entries) and number of columns (array length)
	 * @return true if init was ok, false otherwise (no init done)
	 * @see #newTable(Integer[])
	 */
	protected final boolean _init(Integer[] columns){
		if(columns==null || columns.length<1){
			return false;
		}

		Integer[] col = new Integer[columns.length+1];
		col[0] = 0;
		for(int i=0; i<columns.length; i++){
			if(columns[i]==null || columns[i]<3){
				return false;
			}
			col[0] += columns[i];		//overall width in [0]
			col[i+1] = columns[i];		//shift index by one so that col1=[1]. col2=[2] etc.
		}
		col[0] += columns.length+1;		//for table grid, number of columns + 1

		this.columns = col;
		return true;
	}

	/**
	 * Returns a string array with wrapped lines.
	 * @param obj object containing a string (null and empty are allowed)
	 * @param colP index to the column array for column width
	 * @return null if object was null or toString() returns null, empty array if empty string, array with lines of wrappings otherwise
	 */
	protected final String[] obj2At(Object obj, int colP){
		if(obj==null || obj.toString()==null){
			return null;
		}
		if("".equals(obj)){
			return new String[]{};
		}
		return StringUtils.split(WordUtils.wrap(obj.toString(), this.columns[colP], "@@@", true), "@@@");
	}

	/**
	 * Returns a rendered table using given options for rendering.
	 * @return rendered table
	 */
	public String render(){
		StrBuilder ret = new StrBuilder(1000);
		List<StrBuilder> tab = this.renderTable(this.padChar, this.theme.getTheme());
		for(StrBuilder b : tab){
			ret.appendln(b);
		}
		return ret.toString();
	}

	/**
	 * Renders a middle rule, that is a row separating two content rows.
	 * @param top content row above the middle rule
	 * @param bot content row below the middle rule
	 * @param theme row theme
	 * @return line for the middle rule
	 */
	protected final StrBuilder renderMiddleRule(String[][] top, String[][] bot, char[] theme){
		StrBuilder ret = new StrBuilder(100);
		ret.append(theme[TableTheme.VERTICAL_AND_RIGHT]);
		String[] topSt = top[top.length-1];
		String[] botSt = bot[0];
		for(int i=0; i<topSt.length; i++){		//both rows must be normalised, pick one for loop
			ret.appendPadding(this.columns[i+1], theme[TableTheme.HORIZONTAL]);
			if(i<topSt.length-1){
				if(topSt[i]==null && botSt[i]==null){
					ret.append(theme[TableTheme.HORIZONTAL]);
				}
				else if(topSt[i]==null){
					ret.append(theme[TableTheme.DOWN_AND_HORIZONTAL]);
				}
				else if(botSt[i]==null){
					ret.append(theme[TableTheme.UP_AND_HORIZONTAL]);
				}
				else{
					ret.append(theme[TableTheme.VERTICAL_AND_HORIZONTAL]);
				}
			}
		}
		ret.append(theme[TableTheme.VERTICAL_AND_LEFT]);
		return ret;
	}

	/**
	 * Renders the bottom rule of the table.
	 * @param row last content row of the table
	 * @return rendered bottom row
	 */

	/**
	 * Renders the bottom rule of the table.
	 * @param row last content row of the table
	 * @param theme row theme
	 * @return rendered bottom row
	 */
	protected final StrBuilder renderBottomRule(String[][] row, char[] theme){
		StrBuilder ret = new StrBuilder(100);
		ret.append(theme[TableTheme.UP_AND_RIGHT]);
		String[] ar = row[row.length-1];
		for(int i=0; i<ar.length; i++){
			ret.appendPadding(this.columns[i+1], theme[TableTheme.HORIZONTAL]);
			if(i<ar.length-1){
				if(ar[i]==null){
					ret.append(theme[TableTheme.HORIZONTAL]);
				}
				else{
					ret.append(theme[TableTheme.UP_AND_HORIZONTAL]);
				}
			}
		}
		ret.append(theme[TableTheme.UP_AND_LEFT]);
		return ret;
	}

	/**
	 * Renders rows of content with correct separators.
	 * @param row array with all lines for all columns of a single row
	 * @param padChar padding character
	 * @param theme row theme
	 * @return rendered lines for the row, empty if nothing was to be rendered
	 */
	protected final StrBuilder renderRow(String[][] row, char padChar, char[] theme){
		StrBuilder ret = new StrBuilder(100);
		ret.setNullText("");
		for(int k=0; k<row.length; k++){
			String[] ar = row[k];
			ret.append(theme[TableTheme.VERTICAL]);
			//if null is in array we need to render column spans
			if(ArrayUtils.contains(ar, null)){
				int span = 0;
				for(int i=0; i<ar.length; i++){
					String content = ar[i];
					if(content==null){
						if(i==ar.length-1){
							//a null in last column, so calculate the span)
							int width = 0;
							//add the span column width
							for(k=0; k<span; k++){
								width += this.columns[k+1];
							}
							//add the separator characters (span) plus the one for this column
							width += span;
							//add the current column width
							width += this.columns[i+1];
							//centre content in the new column
							ret.appendFixedWidthPadRight("", width, padChar);
						}
						else{
							span += 1;
							continue;	
						}
					}
					else if("".equals(content)){
						//we have an empty column, so
						//first finish the spans
						for(k=0; k<span; k++){
							ret.appendFixedWidthPadRight("", this.columns[k+1], padChar);
						}
						ret.appendFixedWidthPadRight("", span, padChar);
						ret.append(theme[TableTheme.VERTICAL]);
						span = 0;
						//now add the empty column
						ret.appendFixedWidthPadRight(content, this.columns[i+1], padChar);
						if(i<ar.length-1){
							ret.append(theme[TableTheme.VERTICAL]);
						}
					}
					else{
						int width = 0;
						//add the span column width
						for(k=0; k<span; k++){
							width += this.columns[k+1];
						}
						//add the separator characters (span) plus the one for this column
						width += span;
						//add the current column width
						width += this.columns[i+1];
						//centre content in the new column
						ret.append(StringUtils.center(content, width, padChar));
						if(i<ar.length-1){
							ret.append(theme[TableTheme.VERTICAL]);
						}
						span = 0;
					}
				}
			}
			else{
				for(int i=0; i<ar.length; i++){
					String content = ar[i];
					ret.appendFixedWidthPadRight(content, this.columns[i+1], padChar);
					if(i<ar.length-1){
						ret.append(theme[TableTheme.VERTICAL]);
					}
				}
			}
			ret.append(theme[TableTheme.VERTICAL]);
			if(k<row.length-1){
				ret.append('\n');
			}
		}

		return ret;
	}

	/**
	 * Renders the table.
	 * @param padChar padding character
	 * @param theme row theme
	 * @return list of lines for the table, empty if nothing was there to be rendered
	 */
	protected final List<StrBuilder> renderTable(char padChar, char[] theme){
		List<StrBuilder> ret = new ArrayList<StrBuilder>();
		if(this.table.size()>0){
			ret.add(this.renderTopRule(this.table.get(1), theme));
			if(this.table.size()>1){	//if more than one row do all but the last row
				for(int i=1; i<table.size(); i++){
					ret.add(this.renderRow(this.table.get(i), padChar, theme));
					ret.add(this.renderMiddleRule(this.table.get(i), this.table.get(i+1), theme));
				}
			}
			ret.add(this.renderRow(this.table.get(this.table.size()), padChar, theme));		//final row (or only row)
			ret.add(this.renderBottomRule(this.table.get(this.table.size()), theme));
		}
		return ret;
	}

	/**
	 * Renders the top rule of the table.
	 * @param row first content row of the table
	 * @param theme row theme
	 * @return rendered top row
	 */
	protected final StrBuilder renderTopRule(String[][] row, char[] theme){
		StrBuilder ret = new StrBuilder(100);
		ret.append(theme[TableTheme.DOWN_AND_RIGHT]);
		String[] ar = row[0];
		for(int i=0; i<ar.length; i++){
			ret.appendPadding(this.columns[i+1], theme[TableTheme.HORIZONTAL]);
			if(i<ar.length-1){
				if(ar[i]==null){
					ret.append(theme[TableTheme.HORIZONTAL]);
				}
				else{
					ret.append(theme[TableTheme.DOWN_AND_HORIZONTAL]);
				}
			}
		}
		ret.append(theme[TableTheme.DOWN_AND_LEFT]);
		return ret;
	}

//	/**
//	 * Sets the format for the columns of the indexed row.
//	 * Format only relates to text alignment with the following options:
//	 * <ul>
//	 *		<li>l = align left (default)</li>
//	 *		<li>r = align right</li>
//	 *		<li>c = centre</li>
//	 *		<li>null = use the default or ignore if column is set to span other columns</li>
//	 * </ul>
//	 * The argument <code>characters</code> must have the same length as columns set.
//	 * @param row index for the row
//	 * @param characters array of formatting instructions
//	 * @return index of row with format, -1 if no columns set or row index does not exist, 0 if problem with format characters (no formats given, wrong count)
//	 */
//	public int setRowFormats(int row, Character ...characters){
//		if(this.columns==null){
//			return -1;
//		}
//		if(!this.table.containsKey(row)){
//			return -1;
//		}
//		if(characters==null||characters.length!=this.getColumnCount()){
//			return 0;
//		}
//
//		this.rowFormat.put(row, characters);
//		return row;
//	}

	/**
	 * Returns a string with debug information.
	 * @return string with debug information about the table
	 */
	public String toString(){
		ToStringBuilder ret=new ToStringBuilder(this, Table_ToStringStyle.TS_STYLE)
			.append("columns   ", this.columns, false)
			.append("columns   ", this.columns)
			.append("------------------------------------")
			.append("table     ", this.table, false)
		;

		if(this.table!=null&&this.table.size()>0){
			for(Integer i:table.keySet()){
				ret.append(String.format("  row(%3d)", i), this.table.get(i));
			}
		}

		ret.append("------------------------------------");
		return ret.toString();
	}
}
