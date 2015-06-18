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

/**
 * New table implementation.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4-SNAPSHOT build 150618 (18-Jun-15) for Java 1.8
 */
public class Table {

	List<TableRow> table;

	int columnCount;

	boolean[] borders;

	public Table(int columnCount){
		this.table = new LinkedList<TableRow>();
		this.columnCount = columnCount;
		this.borders = new boolean[this.columnCount+1];
	}

	public final void addMidRule(){
		if(this.table.size()==0){
			this.table.add(TableRow.create(TableRowType.TOP));
		}
		else{
			this.table.add(TableRow.create(TableRowType.MID));
		}
	}

	public final int getColumnCount(){
		return this.columnCount;
	}

	public final void addRow(Object ...columns) throws TableException {
		if(columns==null){
			throw new TableException("wrong columns argument", "empty column");
		}
		if(columns.length!=this.columnCount){
			throw new TableException("wrong columns argument", "tried to add " + columns.length + " columns, expected " + this.columnCount + " columns");
		}
		this.table.add(TableRow.create(TableRowType.CONTENT, columns));
	}

	public final void addTopRule(){
		if(this.table.size()==0){
			this.table.add(TableRow.create(TableRowType.TOP_EMPH));
		}
		else{
			this.table.add(TableRow.create(TableRowType.MID_EMPH));
		}
	}

	public final void setRowBorders(boolean ... borders) throws TableException {
		if(borders==null){
			throw new TableException("wrong borders argument", "borders is null");
		}
		if(borders.length!=this.borders.length){
			throw new TableException("wrong borders argument", "tried to add " + borders.length + " borders, expected " + this.borders.length + " borders");
		}

		switch(table.get(table.size()).getType()){
			case CONTENT:
				table.get(table.size()).setBorder(borders);
				break;
			default:
				throw new TableException("cannot set borders", "last row is not a content row");
		}
	}

	public final void setTableBorders(boolean ... borders) throws TableException {
		if(borders==null){
			throw new TableException("wrong borders argument", "borders is null");
		}
		if(borders.length!=this.borders.length){
			throw new TableException("wrong borders argument", "tried to add " + borders.length + " borders, expected " + this.borders.length + " borders");
		}
		this.borders = borders;
	}

	public final void setTableBorders(boolean flag){
		this.borders = new boolean[this.columnCount+1];
		if(flag==true){
			for(int i=0; i<this.columnCount+1; i++){
				this.borders[i] = true;
			}
		}
	}

	/**
	 * Returns a string with debug information.
	 * @return string with debug information about the table
	 */
	public String toString(){
		ToStringBuilder ret = new ToStringBuilder(this, Table_ToStringStyle.TS_STYLE)
			.append("column count   ", this.columnCount)
//			.append("column widths  ", this.columnWidth, false)
//			.append("column widths  ", this.columnWidth)
			.append("borders        ", this.borders, false)
			.append("borders        ", this.borders)
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
}
