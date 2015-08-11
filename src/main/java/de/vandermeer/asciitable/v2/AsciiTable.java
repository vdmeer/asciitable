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

import de.vandermeer.asciitable.commons.Table_ToStringStyle;
import de.vandermeer.asciitable.commons.TableException;
import de.vandermeer.asciitable.v2.core.E_RuleStyle;
import de.vandermeer.asciitable.v2.core.E_RuleType;
import de.vandermeer.asciitable.v2.core.TableRow;

/**
 * An ASCII table with flexible column number, column width, wrapping, spanning and renderer with themes.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.7 build 150811 (11-Aug-15) for Java 1.7
 * @since      v0.0.5
 */
public class AsciiTable {

	/** List of rows of the table. */
	List<TableRow> table;

	/** Number of columns for the table. */
	int columns;

	/**
	 * Creates a new table.
	 * @param columns number of columns for the table, cannot be 0
	 */
	public AsciiTable(int columns){
		this.table = new LinkedList<TableRow>();
		this.columns = columns;
	}

	/**
	 * Adds a rule row to the table.
	 * If the table has no rows specified, the rule will be a top rule. Otherwise it will be a mid rule.
	 * The renderer will automatically convert a final mid rule (last row of a table) into a bottom rule.
	 */
	public final void addRule(){
		if(this.table.size()==0){
			this.table.add(new TableRow(E_RuleType.TOP, this.columns));
		}
		else{
			this.table.add(new TableRow(E_RuleType.MID, this.columns));
		}
	}

	/**
	 * Adds a rule with strong (emphasized) style row to the table.
	 * If the table has no rows specified, the rule will be a top rule. Otherwise it will be a mid rule.
	 * The renderer will automatically convert a final mid rule (last row of a table) into a bottom rule.
	 */
	public final void addRuleStrong(){
		if(this.table.size()==0){
			this.table.add(new TableRow(E_RuleType.TOP, E_RuleStyle.STRONG, this.columns));
		}
		else{
			this.table.add(new TableRow(E_RuleType.MID, E_RuleStyle.STRONG, this.columns));
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
	public final TableRow addRow(Object ...columns) throws TableException {
		TableRow ret = new TableRow(columns, this.columns);
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
		if(this.table.get(0).getRuleType()!=null && !E_RuleType.TOP.equals(this.table.get(0).getRuleType())){
			this.table.get(0).setRuleType(E_RuleType.TOP);
		}
		if(E_RuleType.MID.equals(this.table.get(this.table.size()-1).getRuleType())){
			this.table.get(this.table.size()-1).setRuleType(E_RuleType.BOTTOM);
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
		ToStringBuilder ret = new ToStringBuilder(this, Table_ToStringStyle.TS_STYLE)
			.append("column count   ", this.columns)
//			.append("column widths  ", this.columnWidth, false)
//			.append("column widths  ", this.columnWidth)
//			.append("borders        ", this.borders, false)
//			.append("borders        ", this.borders)
			.append("------------------------------------")
			.append("table          ", this.table, false)
		;

//		if(this.table!=null&&this.table.size()>0){
//			for(Integer i:table.keySet()){
//				ret.append(String.format("  row(%3d)", i), this.table.get(i));
//			}
//		}

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
