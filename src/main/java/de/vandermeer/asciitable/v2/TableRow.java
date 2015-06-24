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

import de.vandermeer.asciitable.commons.TableException;

/**
 * A table row, either a rule or a row with content for columns.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4 build 150619 (19-Jun-15) for Java 1.8
 */
public class TableRow {

	/** Array with content if the row is a content row, null otherwise. */
	Object[] columns;

	/** Array with indicators if the row has to set borders or not. */
	boolean[] borders;

	/** Type of a rule row, will be null for a content row. */
	E_RuleType ruleType;

	/** Style of a rule row, will be null for a content row. */
	E_RuleStyle ruleStyle;

	/**
	 * Returns a new table row of a particular rule type without content.
	 * The rule style is set to normal.
	 * @param type rule type of the row
	 * @param columnCount number of columns for the row
	 */
	public TableRow(E_RuleType type, int columnCount){
		this(type, E_RuleStyle.NORMAL, columnCount);
	}

	/**
	 * Returns a new table row of a particular rule type and style without content.
	 * @param type rule type of the row
	 * @param style rule style
	 * @param columnCount number of columns for the row
	 */
	public TableRow(E_RuleType type, E_RuleStyle style, int columnCount){
		this.ruleType = type;
		this.ruleStyle = style;
		this.columns = null;

		this.borders = new boolean[columnCount+1];
		for(int i=0; i<columnCount+1; i++){
			this.borders[i] = true;
		}

		if(type==null){
			throw new IllegalArgumentException("row type for rule cannot be null");
		}
		if(style==null){
			throw new IllegalArgumentException("row style for rule cannot be null");
		}
	}

	/**
	 * Returns a new content table row
	 * @param cols content in form of columns
	 * @param columnCount column count
	 * @throws TableException if columns and count do not add up
	 */
	public TableRow(Object[] cols, int columnCount) throws TableException {
		if(cols==null){
			throw new TableException("wrong columns argument", "empty column array");
		}
		if(cols.length!=columnCount){
			throw new TableException("wrong columns argument", "tried to add " + cols.length + " columns, expected " + columns + " columns");
		}

		this.ruleType = null;
		this.ruleStyle = null;
		this.columns = cols;

		this.borders = new boolean[cols.length+1];
		for(int i=0; i<columnCount+1; i++){
			this.borders[i] = true;
		}
	}

	/**
	 * Tests if the row is a rule.
	 * @return true if it is a rule, false otherwise
	 */
	public boolean isRule(){
		if(this.ruleType!=null){
			return true;
		}
		return false;
	}

	/**
	 * Tests if the row has content meaning it is not a rule.
	 * @return true if it has content, false otherwise
	 */
	public boolean isContent(){
		return !this.isRule();
	}

	/**
	 * Returns the rule type of the row.
	 * @return rule type, null if not set, i.e. for content rows
	 */
	public E_RuleType getRuleType(){
		return this.ruleType;
	}

	/**
	 * Returns the rule style of the row.
	 * @return rule style, null if not set, i.e. for content rows
	 */
	public E_RuleStyle getRuleStyle(){
		return this.ruleStyle;
	}
}
