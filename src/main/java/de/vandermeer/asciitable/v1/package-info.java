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
 * Original version of an ASCII (and UTF-8) Table, code is frozen (only bug fixes).
 * 
 * The class allows to define a table with {@code n} columns.
 * Width set either as overall table width with equally distributed column widths or as fixed width per column.
 * A table can be rendered using a table theme.
 * A set of themes is pre-defined, additional themes can be defined as needed.
 * 
 * <h3>Using the ASCII Table V1</h3>
 * 
 * First get a new instance of the ASCII Table using one of the two static factory methods:
 * <ul>
 * 		<li>
 * 			A table with an overall table width and evenly distributed column widths: {@code newTable(Integer, Integer)}
 * 			<br>
 * 			for instance {@code V1_AsciiTable at = V1_AsciiTable.newTable(3, 76);} will create a new table with 3 columns with 24 characters width each.
 * 			The remaining 4 characters (to table width of 76) are used for vertical lines.
 * 		</li>
 * 		<li>
 * 			A table with {@code n} columns and a fixed width set per columns: {@code newTable(Integer[])}
 * 			<br>
 * 			for instance {@code V1_AsciiTable at = V1_AsciiTable.newTable(new Integer[]{10, 20, 30});} will create a new table with 3 columns, where column 1 has a width of 10 characters, column 2 has a width of 20 characters and column 3 has a width of 30 characters.
 * 			The overall table has then a width of 64 characters (60 characters for the columns plus 4 characters for vertical lines).
 * 		</li>
 * </ul>
 * 
 * Once the table is created {@code addRow(Object…​}) can be used to add rows to it.
 * Finally, call {@code render()} render the table into a string.
 * 
 * To change the appearance of the rendered table use {@code setTheme()} and {@code setPaddingCharacter()}.
 * The table theme determines the borders of the table.
 * The default theme is {@link de.vandermeer.asciitable.v1.V1_StandardTableThemes#PLAIN_7BIT}.
 * A number of themes are provided in {@link de.vandermeer.asciitable.v1.V1_StandardTableThemes}, including light, double and heavy UTF-8 character boxes and LaTeX style table themes.
 * Individual themes can be created by creating a class implementing the {@link de.vandermeer.asciitable.v1.V1_TableTheme} interface.
 * The padding character is used to pad content lines in table rows.
 * The default is blank (␣). Themes and padding characters are applied per render call, enabling a table to be rendered multiple times with different options.
 * 
 * 
 * <h3>A simple example with overall width</h3>
 * A simple example creating a table with 3 rows and three columns. The first row spans 3 columns, each column of the second row fits into a single line, and the second and third column of the third row are broken into two lines:
 * <pre>{@code
	V1_AsciiTable at = V1_AsciiTable.newTable(3, 76);
	at.addRow(null, null, "Table Heading");
	at.addRow("first row (col1)", "with some information", "and more information");
	at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)");
	System.out.println(at.render());
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	+--------------------------------------------------------------------------+
	|                              Table Heading                               |
	+------------------------+------------------------+------------------------+
	|first row (col1)        |with some information   |and more information    |
	+------------------------+------------------------+------------------------+
	|second row (col1)       |with some information   |and more information    |
	|                        |(col2)                  |(col3)                  |
	+------------------------+------------------------+------------------------+

 * </pre>
 * 
 * 
 * <h3>An example with fixed columns</h3>
 * Another example, this time using an {@code Integer[]} to initialize the table.
 * The table again has 3 rows and 3 columns, but this time column 1 is set to 10 characters, column 2 is set to 15 characters and column 3 is set to 20 characters:
 * <pre>{@code
	Integer[] columns = new Integer[]{10, 15, 20};
	V1_AsciiTable at = V1_AsciiTable.newTable(columns);
	at.addRow(null, null, "Table Heading");
	at.addRow("row 1", "this is col 2", "and this is column 3");
	at.addRow("row 2", "some text for column 2", "and some text for column 3");
	System.out.println(at.render());
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	+-----------------------------------------------+
	|                 Table Heading                 |
	+----------+---------------+--------------------+
	|row 1     |this is col 2  |and this is column 3|
	+----------+---------------+--------------------+
	|row 2     |some text for  |and some text for   |
	|          |column 2       |column 3            |
	+----------+---------------+--------------------+
 * </pre>
 * 
 * 
 * <h3>Examples with different table themes</h3>
 * The table theme can be changed any time before rendering the table.
 * Take the example above, we can change the theme to {@link de.vandermeer.asciitable.v1.V1_StandardTableThemes#LIGHT} and then render the table:
 * <pre>{@code
	at.setTheme(V1_StandardTableThemes.LIGHT);
	at.render();
	System.out.println(at.render());
 * }</pre>
 * 
 * The output will now be:
 * <pre style="line-height:17px">
	┌───────────────────────────────────────────────┐
	│                 Table Heading                 │
	├──────────┬───────────────┬────────────────────┤
	│row 1     │this is col 2  │and this is column 3│
	├──────────┼───────────────┼────────────────────┤
	│row 2     │some text for  │and some text for   │
	│          │column 2       │column 3            │
	└──────────┴───────────────┴────────────────────┘
 * </pre>
 * 
 * 
 * If we use the theme {@link de.vandermeer.asciitable.v1.V1_StandardTableThemes#DOUBLE}, the output should look like this:
 * <pre>{@code
	at.setTheme(V1_StandardTableThemes.DOUBLE);
	at.render();
	System.out.println(at.render());
 * }</pre>
 * 
 * The output will now be:
 * <pre style="line-height:17px">
	╔═══════════════════════════════════════════════╗
	║                 Table Heading                 ║
	╠══════════╦═══════════════╦════════════════════╣
	║row 1     ║this is col 2  ║and this is column 3║
	╠══════════╬═══════════════╬════════════════════╣
	║row 2     ║some text for  ║and some text for   ║
	║          ║column 2       ║column 3            ║
	╚══════════╩═══════════════╩════════════════════╝
 * </pre>
 * 
 * 
 * If we use the theme {@link de.vandermeer.asciitable.v1.V1_StandardTableThemes#LIGHT_DOUBLE}, the output should look like this:
 * <pre>{@code
	at.setTheme(V1_StandardTableThemes.LIGHT_DOUBLE);
	at.render();
	System.out.println(at.render());
 * }</pre>
 * 
 * The output will now be:
 * <pre style="line-height:17px">
	╒═══════════════════════════════════════════════╕
	│                 Table Heading                 │
	╞══════════╤═══════════════╤════════════════════╡
	│row 1     │this is col 2  │and this is column 3│
	╞══════════╪═══════════════╪════════════════════╡
	│row 2     │some text for  │and some text for   │
	│          │column 2       │column 3            │
	╘══════════╧═══════════════╧════════════════════╛
 * </pre>
 * 
 * 
 * If we use the theme {@link de.vandermeer.asciitable.v1.V1_StandardTableThemes#DOUBLE_LIGHT}, the output should look like this:
 * <pre>{@code
	at.setTheme(V1_StandardTableThemes.DOUBLE_LIGHT);
	at.render();
	System.out.println(at.render());
 * }</pre>
 * 
 * The output will now be:
 * <pre style="line-height:17px">
	╓───────────────────────────────────────────────╖
	║                 Table Heading                 ║
	╟──────────╥───────────────╥────────────────────╢
	║row 1     ║this is col 2  ║and this is column 3║
	╟──────────╫───────────────╫────────────────────╢
	║row 2     ║some text for  ║and some text for   ║
	║          ║column 2       ║column 3            ║
	╙──────────╨───────────────╨────────────────────╜
 * </pre>
 * 
 * 
 * The look and feel of themes with heavy characters can differ, depending on the font that is being used.
 * Many console fonts on windows do not show heavy box drawing characters as monotype or have varying width for whitespaces when using heavy character.
 * The following shows a table using the standard heavy theme ({@link de.vandermeer.asciitable.v1.V1_StandardTableThemes#HEAVY}):
 * <pre style="line-height:17px">
	┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
	┃                 Table Heading                 ┃
	┣━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┫
	┃row 1     ┃this is col 2  ┃and this is column 3┃
	┣━━━━━━━━━━╋━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━━━━━━━┫
	┃row 2     ┃some text for  ┃and some text for   ┃
	┃          ┃column 2       ┃column 3            ┃
	┗━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┛
 * </pre>
 * 
 * 
 * There are also a number of LaTeX style themes pre-defined.
 * For instance {@link de.vandermeer.asciitable.v1.V1_StandardTableThemes#LATEX_LIGHT_TRIPLE_DASH}, which unfortunately does not render easily to HTML:
 * <pre style="line-height:17px">
	┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
	                  Table Heading                  
	┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
	 row 1      this is col 2   and this is column 3 
	┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
	 row 2      some text for   and some text for    
	            column 2        column 3             
	┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
 * </pre>
 * 
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.3 build 160203 (03-Feb-16) for Java 1.7
 */
package de.vandermeer.asciitable.v1;