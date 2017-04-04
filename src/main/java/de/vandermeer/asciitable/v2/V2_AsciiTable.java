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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import de.vandermeer.asciitable.commons.ObjectToStringStyle;
import de.vandermeer.asciitable.commons.TableException;
import de.vandermeer.asciitable.v2.row.ContentRow;
import de.vandermeer.asciitable.v2.row.RuleRow;
import de.vandermeer.asciitable.v2.row.RuleRowStyle;
import de.vandermeer.asciitable.v2.row.RuleRowType;
import de.vandermeer.asciitable.v2.row.V2_Row;

/**
 * 2nd generation ASCII table with flexible column number, column width, wrapping, spanning and renderer with themes.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v1.0.1 build 170404 (04-Apr-17) for Java 1.7
 * @since      v0.0.5
 */
public class V2_AsciiTable {

	/** List of rows of the table. */
	List<V2_Row> table;

	/** Number of columns for the table. */
	int colNumber;

	/** Default padding for content rows. */
	int defaultPadding = 1;

	/**
	 * Creates a new table.
	 * The number of columns used in the first content row determines the number of columns for the table.
	 */
	public V2_AsciiTable(){
		this(-1);
	}

	/**
	 * Creates a new table with default padding to be used for columns in content rows.
	 * The standard value (if not set) is 1, meaning 1 character left and one character right.
	 * @param padding default padding
	 */
	public V2_AsciiTable (int padding){
		this.defaultPadding = (padding==-1)?1:padding;
		this.table = new LinkedList<V2_Row>();
	}

	/**
	 * Returns the table's default padding.
	 * @return default padding
	 */
	public int getDefaultPadding(){
		return this.defaultPadding;
	}

	/**
	 * Adds a rule row to the table.
	 * If the table has no rows specified, the rule will be a top rule. Otherwise it will be a mid rule.
	 * The renderer will automatically convert a final mid rule (last row of a table) into a bottom rule.
	 */
	public final void addRule(){
		if(this.table.size()==0){
			this.table.add(new RuleRow(RuleRowType.TOP));
		}
		else{
			this.table.add(new RuleRow(RuleRowType.MID));
		}
	}

	/**
	 * Adds a rule with strong (emphasized) style row to the table.
	 * If the table has no rows specified, the rule will be a top rule. Otherwise it will be a mid rule.
	 * The renderer will automatically convert a final mid rule (last row of a table) into a bottom rule.
	 */
	public final void addStrongRule(){
		if(this.table.size()==0){
			this.table.add(new RuleRow(RuleRowType.TOP, RuleRowStyle.STRONG));
		}
		else{
			this.table.add(new RuleRow(RuleRowType.MID, RuleRowStyle.STRONG));
		}
	}

	/**
	 * Returns the table.
	 * @return table
	 */
	public final List<V2_Row> getTable(){
		return this.table;
	}

	/**
	 * Adds a content row to the table.
	 * For the first content row added, the number of objects given here determines the number of columns in the table.
	 * For every subsequent content row, the array must have an entry for each column,
	 * i.e. the size of the array must be the same as the result of {@link #getColumnCount()}.
	 * @param columns content of the columns for the row
	 * @return the created row for further customization
	 * @throws NullPointerException if columns was null
	 * @throws TableException if parameter is null or does not have the correct size (more or less entries than columns defined for the table)
	 */
	public final ContentRow addRow(Collection<?> columns) throws NullPointerException, TableException {
		Validate.notNull(columns);
		return this.addRow(columns.toArray());
	}

	/**
	 * Adds a content row to the table.
	 * For the first content row added, the number of objects given here determines the number of columns in the table.
	 * For every subsequent content row, the array must have an entry for each column,
	 * i.e. the size of the array must be the same as the result of {@link #getColumnCount()}.
	 * @param columns content of the columns for the row
	 * @return the created row for further customization
	 * @throws TableException if parameter is null or does not have the correct size (more or less entries than columns defined for the table)
	 */
	public final ContentRow addRow(Object ...columns) throws TableException {
		ContentRow ret = new ContentRow(columns, this.defaultPadding);

		if(this.colNumber==0){
			this.colNumber=columns.length;
		}
		else{
			if(columns.length!=this.colNumber){
				throw new TableException("wrong columns argument", "wrong number of columns, expected " + this.colNumber + " received " + columns.length);
			}
		}

		this.table.add(ret);
		return ret;
	}

	/**
	 * Returns the number of columns set for the table.
	 * @return number of columns
	 */
	public final int getColumnCount(){
		return this.colNumber;
	}

	/**
	 * Returns a string with debug information.
	 * @return string with debug information about the table
	 */
	public String toString(){
		ToStringBuilder ret = new ToStringBuilder(this, ObjectToStringStyle.getStyle())
			.append("column count   ", this.colNumber)
			.append("table          ", this.table, false)
			.append("")
			.append("------------------------------------")
			.append("")
		;

		if(this.table!=null && this.table.size()>0){
			for(V2_Row row : this.table){
				ret.append(row.toString(4));
				ret.append("");
			}
		}

		ret.append("------------------------------------");
		return ret.toString();
	}

}
