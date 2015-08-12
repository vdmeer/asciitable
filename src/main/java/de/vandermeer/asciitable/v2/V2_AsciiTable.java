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

package de.vandermeer.asciitable.v2;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import de.vandermeer.asciitable.commons.TableException;
import de.vandermeer.asciitable.commons.ObjectToStringStyle;
import de.vandermeer.asciitable.v2.core.V2_E_RuleStyle;
import de.vandermeer.asciitable.v2.core.V2_E_RuleType;
import de.vandermeer.asciitable.v2.core.V2_TableRow;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

/**
 * 2nd generation ASCII table with flexible column number, column width, wrapping, spanning and renderer with themes.
 * 
 * <p>
 * 		The class allows to define a table with {@code n} columns.
 * 		Once a table is defined, rules and rows can be added.
 * 		A rule is a special row with border formatting characters (similar to a rule in a LaTeX table).
 * 		A row is a content row with content (in form of strings or objects with {@code toString()} method).
 * 		A row should have content for each of the columns defined for the table, e.g. if the table is set for 3 columns each row should define content for 3 columns.
 * </p>
 * 
 * <p>
 * 		Once a table is defined and filled, a renderer is used to render the table.
 * 		This render object is initialized with the table width and themes.
 * 		It will produce a rendered table, which can then be printed to the screen or other output that accept a string (such as a file).
 * </p>
 * 
 * <p>
 * 		A set of row themes and table themes are pre-defined. Additional themes can be easily defined and validated.
 * </p>
 * 
 * <p>
 * 		The standard usage is:
 * <ul>
 * 		<li>create a table for {@code n} columns</li>
 * 		<li>add rules and rows
 * 			<ul>
 * 				<li>a rule is a separator of rows using a normal row theme</li>
 * 				<li>a strong rule is a separator of rows using a strong row theme</li>
 * 				<li>a row is the actual content with objects per column (or spanning columns, as explained below)</li>
 * 			</ul>
 * 		</li>
 * 		<li>create a renderer and configure it</li>
 * 		<li>render the table and print it</li>
 * </ul>
 * 
 * <p>
 * 		A table, once created, can be rendered using any renderer.
 * 		Any renderer can render any created table multiple (any) times.
 * 		Furthermore, a renderer can re-render a table (if it has been changed) any time.
 * </p>
 * 
 * <b>A table with 1 column</b><br>
 * 		First get a new instance of the ASCII Table using the public constructor set for 1 column:
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable(1);
 * }</pre>
 * 
 * 		Next, fill the table.
 * 		The example here adds a strong rule followed by a content row, a rule, a content row, a rule, a content row, and a final rule.
 * <pre>{@code
	at.addRuleStrong();
	at.addRow("Table Heading");
	at.addRule();
	at.addRow("first row (col1)");
	at.addRule();
	at.addRow("second row (col1)");
	at.addRule();
 * }</pre>
 * 
 * 		Last, create a renderer object, configure it, render the table, and print it.
 * 		The example here uses a theme {@link V2_E_TableThemes#UTF_LIGHT} to render the table for an absolute width of 76 characters:
 * <pre>{@code
	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	┌──────────────────────────────────────────────────────────────────────────┐
	│Table Heading                                                             │
	├──────────────────────────────────────────────────────────────────────────┤
	│first row (col1)                                                          │
	├──────────────────────────────────────────────────────────────────────────┤
	│second row (col1)                                                         │
	└──────────────────────────────────────────────────────────────────────────┘
 * </pre>
 * 
 * 
 * <b>A table with 2 columns</b><br>
 * The following example creates a table with 2 columns:
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable(2);
	at.addRuleStrong();
	at.addRow(null,"Table Heading");
	at.addRule();
	at.addRow("first row (col1)", "with some information");
	at.addRule();
	at.addRow("second row (col1)", "with some information (col2)");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	┌──────────────────────────────────────────────────────────────────────────┐
	│                              Table Heading                               │
	├─────────────────────────────────────┬────────────────────────────────────┤
	│first row (col1)                     │with some information               │
	├─────────────────────────────────────┼────────────────────────────────────┤
	│second row (col1)                    │with some information (col2)        │
	└─────────────────────────────────────┴────────────────────────────────────┘
 * </pre>
 * 
 * 
 * <b>A table with 3 columns</b><br>
 * The following example creates a table with 3 columns:
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable(3);
	at.addRuleStrong();
	at.addRow(null, null, "Table Heading");
	at.addRule();
	at.addRow("first row (col1)", "with some information", "and more information");
	at.addRule();
	at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	┌──────────────────────────────────────────────────────────────────────────┐
	│                              Table Heading                               │
	├────────────────────────┬────────────────────────┬────────────────────────┤
	│first row (col1)        │with some information   │and more information    │
	├────────────────────────┼────────────────────────┼────────────────────────┤
	│second row (col1)       │with some information   │and more information    │
	│                        │(col2)                  │(col3)                  │
	└────────────────────────┴────────────────────────┴────────────────────────┘
 * </pre>
 * 
 * 
 * <b>A table with 4 columns</b><br>
 * The following example creates a table with 4 columns:
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable(4);
	at.addRuleStrong();
	at.addRow(null, null, null, "Table Heading");
	at.addRule();
	at.addRow("first row (col1)", "with some information", "and more information", "even more");
	at.addRule();
	at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)", "even more");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	┌──────────────────────────────────────────────────────────────────────────┐
	│                              Table Heading                               │
	├──────────────────┬──────────────────┬──────────────────┬─────────────────┤
	│first row (col1)  │with some         │and more          │even more        │
	│                  │information       │information       │                 │
	├──────────────────┼──────────────────┼──────────────────┼─────────────────┤
	│second row (col1) │with some         │and more          │even more        │
	│                  │information (col2)│information (col3)│                 │
	└──────────────────┴──────────────────┴──────────────────┴─────────────────┘
 * </pre>
 * 
 * 
 * <b>A table with 5 columns</b><br>
 * The following example creates a table with 5 columns:
 * <pre>{@code
	new V2_AsciiTable(5);
	at.addRuleStrong();
	at.addRow(null, null, null, null, "Table Heading");
	at.addRule();
	at.addRow("first row (col1)", "with some information", "and more information", "even more", "more");
	at.addRule();
	at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)", "even more", "more");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	┌──────────────────────────────────────────────────────────────────────────┐
	│                              Table Heading                               │
	├──────────────┬──────────────┬──────────────┬──────────────┬──────────────┤
	│first row     │with some     │and more      │even more     │more          │
	│(col1)        │information   │information   │              │              │
	├──────────────┼──────────────┼──────────────┼──────────────┼──────────────┤
	│second row    │with some     │and more      │even more     │more          │
	│(col1)        │information   │information   │              │              │
	│              │(col2)        │(col3)        │              │              │
	└──────────────┴──────────────┴──────────────┴──────────────┴──────────────┘
 * </pre>
 * 
 * 
 * <b>Column spanning</b><br>
 * Rows can span columns.
 * This is done by adding columns of +null+ content to a row followed by a column with content.
 * The column with content will span all previous rows with content +null+.
 * The following example creates a table with 5 columns and different column spanning (all to none columns):
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable(5);
	at.addRuleStrong();
	at.addRow(null, null, null, null, "span all 5 columns");
	at.addRule();
	at.addRow(null, null, null, "span 4 columns", "just 1 column");
	at.addRule();
	at.addRow(null, null, "span 3 columns", null, "span 2 columns");
	at.addRule();
	at.addRow(null, "span 2 columns", null, null, "span 3 columns");
	at.addRule();
	at.addRow("just 1 column", null, null, null, "span 4 columns");
	at.addRule();
	at.addRow("just 1 column", "just 1 column", "just 1 column", "just 1 column", "just 1 column");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	┌──────────────────────────────────────────────────────────────────────────┐
	│                            span all 5 columns                            │
	├───────────────────────────────────────────────────────────┬──────────────┤
	│                      span 4 columns                       │just 1 column │
	├────────────────────────────────────────────┬──────────────┴──────────────┤
	│               span 3 columns               │       span 2 columns        │
	├─────────────────────────────┬──────────────┴─────────────────────────────┤
	│       span 2 columns        │               span 3 columns               │
	├──────────────┬──────────────┴────────────────────────────────────────────┤
	│just 1 column │                      span 4 columns                       │
	├──────────────┼──────────────┬──────────────┬──────────────┬──────────────┤
	│just 1 column │just 1 column │just 1 column │just 1 column │just 1 column │
	└──────────────┴──────────────┴──────────────┴──────────────┴──────────────┘
 * </pre>
 * 
 * 
 * <b>Padding Character</b><br>
 * The table renderer can be set to use different padding characters.
 * A padding character is the character used to fill content rows (all their columns) up to the next border.
 * Using UTF-8 characters might not be result in the anticipated result.
 * The following example creates a table with 1 table rendered with the same renderer set for different padding characters:
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable(1);
	at.addRule();
	at.addRow("some text with padding");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
	rend.setPaddingChar('*');
	System.out.println(rend.render(at));
	rend.setPaddingChar('-');
	System.out.println(rend.render(at));
	rend.setPaddingChar('␣');
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	+--------------------------------------------------------------------------+
	|some text with padding                                                    |
	+--------------------------------------------------------------------------+

	+--------------------------------------------------------------------------+
	|some text with padding****************************************************|
	+--------------------------------------------------------------------------+

	+--------------------------------------------------------------------------+
	|some text with padding----------------------------------------------------|
	+--------------------------------------------------------------------------+

	+--------------------------------------------------------------------------+
	|some text with padding␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣|
	+--------------------------------------------------------------------------+
 * </pre>
 * 
 * 
 * <b>Table Theme</b><br>
 * The table renderer can be set to use different table themes.
 * A table theme defines all border characters for rules, strong rules and content rows.
 * The following example creates a table with 1 table rendered with the same renderer set for different table themes (using pre-defined themes):
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable(1);
	at.addRule();
	at.addRow("some column text");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	System.out.println(rend.render(at));
	rend.setTheme(V2_E_TableThemes.UTF_DOUBLE_LIGHT.get());
	System.out.println(rend.render(at));
	rend.setTheme(V2_E_TableThemes.UTF_DOUBLE.get());
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	+--------------------------------------------------------------------------+
	|some column text                                                          |
	+--------------------------------------------------------------------------+

	┌──────────────────────────────────────────────────────────────────────────┐
	│some column text                                                          │
	└──────────────────────────────────────────────────────────────────────────┘

	╓──────────────────────────────────────────────────────────────────────────╖
	║some column text                                                          ║
	╙──────────────────────────────────────────────────────────────────────────╜

	╔══════════════════════════════════════════════════════════════════════════╗
	║some column text                                                          ║
	╚══════════════════════════════════════════════════════════════════════════╝
 * </pre>
 * 
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 150812 (12-Aug-15) for Java 1.7
 * @since      v0.0.5
 */
public class V2_AsciiTable {

	/** List of rows of the table. */
	List<V2_TableRow> table;

	/** Number of columns for the table. */
	int columns;

	/**
	 * Creates a new table.
	 * @param columns number of columns for the table, cannot be 0
	 */
	public V2_AsciiTable(int columns){
		this.table = new LinkedList<V2_TableRow>();
		this.columns = columns;
	}

	/**
	 * Adds a rule row to the table.
	 * If the table has no rows specified, the rule will be a top rule. Otherwise it will be a mid rule.
	 * The renderer will automatically convert a final mid rule (last row of a table) into a bottom rule.
	 */
	public final void addRule(){
		if(this.table.size()==0){
			this.table.add(new V2_TableRow(V2_E_RuleType.TOP, this.columns));
		}
		else{
			this.table.add(new V2_TableRow(V2_E_RuleType.MID, this.columns));
		}
	}

	/**
	 * Adds a rule with strong (emphasized) style row to the table.
	 * If the table has no rows specified, the rule will be a top rule. Otherwise it will be a mid rule.
	 * The renderer will automatically convert a final mid rule (last row of a table) into a bottom rule.
	 */
	public final void addRuleStrong(){
		if(this.table.size()==0){
			this.table.add(new V2_TableRow(V2_E_RuleType.TOP, V2_E_RuleStyle.STRONG, this.columns));
		}
		else{
			this.table.add(new V2_TableRow(V2_E_RuleType.MID, V2_E_RuleStyle.STRONG, this.columns));
		}
	}

	/**
	 * Adds a content row to the table.
	 * The array must have an entry for each column,
	 * i.e. the size of the array must be the same as the result of {@link #getColumnCount()}.
	 * @param columns content of the columns for the row
	 * @return the created row for further customization
	 * @throws TableException if parameter is null or does not have the correct size (more or less entries than columns defined for the table)
	 */
	public final V2_TableRow addRow(Object ...columns) throws TableException {
		V2_TableRow ret = new V2_TableRow(columns, this.columns);
		this.table.add(ret);
		return ret;
	}

	/**
	 * Returns the number of columns set for the table.
	 * @return number of columns
	 */
	public final int getColumnCount(){
		return this.columns;
	}

	/**
	 * Fixes rules for top and bottom rules.
	 * A renderer should call this method before rendering a table to make sure a rule in the first row is a top rule and a rule in the last row is a bottom rule.
	 */
	public void fixRules(){
		if(this.table.get(0).getRuleType()!=null && !V2_E_RuleType.TOP.equals(this.table.get(0).getRuleType())){
			this.table.get(0).setRuleType(V2_E_RuleType.TOP);
		}
		if(V2_E_RuleType.MID.equals(this.table.get(this.table.size()-1).getRuleType())){
			this.table.get(this.table.size()-1).setRuleType(V2_E_RuleType.BOTTOM);
		}
	}

//	public final void setRowBorders(E_RowBorderTheme theme) throws TableException {
//		switch(table.get(table.size()).getType()){
//		case CONTENT:
//			table.get(table.size()).setBorder(borders);
//			break;
//		default:
//			throw new TableException("cannot set borders", "last row is not a content row");
//	}
//	}
//
//	public final void setRowBorders(boolean ... borders) throws TableException {
//		if(borders==null){
//			throw new TableException("wrong borders argument", "borders is null");
//		}
//		if(borders.length!=this.borders.length){
//			throw new TableException("wrong borders argument", "tried to add " + borders.length + " borders, expected " + this.borders.length + " borders");
//		}
//
//		switch(table.get(table.size()).getType()){
//			case CONTENT:
//				table.get(table.size()).setBorder(borders);
//				break;
//			default:
//				throw new TableException("cannot set borders", "last row is not a content row");
//		}
//	}
//
//	public final void setTableBorders(boolean ... borders) throws TableException {
//		if(borders==null){
//			throw new TableException("wrong borders argument", "borders is null");
//		}
//		if(borders.length!=this.borders.length){
//			throw new TableException("wrong borders argument", "tried to add " + borders.length + " borders, expected " + this.borders.length + " borders");
//		}
//		this.borders = borders;
//	}
//
//	public final void setTableBorders(boolean flag){
//		this.borders = new boolean[this.columnCount+1];
//		if(flag==true){
//			for(int i=0; i<this.columnCount+1; i++){
//				this.borders[i] = true;
//			}
//		}
//	}

	/**
	 * Returns a string with debug information.
	 * @return string with debug information about the table
	 */
	public String toString(){
		ToStringBuilder ret = new ToStringBuilder(this, ObjectToStringStyle.getStyle())
			.append("column count   ", this.columns)
			.append("table          ", this.table, false)
			.append("")
			.append("------------------------------------")
			.append("")
		;

		if(this.table!=null && this.table.size()>0){
			for(V2_TableRow row : this.table){
				ret.append(row.toString(4));
				ret.append("");
			}
		}

		ret.append("------------------------------------");
		return ret.toString();
	}

	public boolean validate(){
		//nothing to do
		if(this.getColumnCount()==0){
			throw new IllegalArgumentException("wrong table argument: table is null or has no columns");
		}
		return true;
	}

}
