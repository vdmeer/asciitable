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

package de.vandermeer.asciitable.v2.row;

import org.apache.commons.lang3.builder.ToStringBuilder;

import de.vandermeer.asciitable.commons.ObjectToStringStyle;

/**
 * A table row representing a rule.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 150814 (14-Aug-15) for Java 1.7
 * @since      v0.2.0
 */
public class RuleRow implements V2_Row {

	/** Type of a rule row, will be null for a content row. */
	private RuleRowType ruleType;

	/** Style of a rule row, will be null for a content row. */
	private RuleRowStyle ruleStyle;

	/**
	 * Returns a new table row of a particular rule type without content.
	 * The rule style is set to normal.
	 * @param type rule type of the row
	 */
	public RuleRow(RuleRowType type){
		this(type, RuleRowStyle.NORMAL);
	}

	/**
	 * Returns a new table row of a particular rule type and style without content.
	 * @param type rule type of the row
	 * @param style rule style
	 */
	public RuleRow(RuleRowType type, RuleRowStyle style){
		this.ruleType = type;
		this.ruleStyle = style;

		if(type==null){
			throw new IllegalArgumentException("row type for rule cannot be null");
		}
		if(style==null){
			throw new IllegalArgumentException("row style for rule cannot be null");
		}
	}

	/**
	 * Returns the rule style of the row.
	 * @return rule style, null if not set, i.e. for content rows
	 */
	public RuleRowStyle getRuleStyle(){
		return this.ruleStyle;
	}

	/**
	 * Sets the rule type if the row is a rule row and the new type is not null.
	 * @param type new rule type
	 */
	public void setRuleType(RuleRowType type){
		if(type!=null){
			this.ruleType = type;
		}
	}

	/**
	 * Sets the rule style if the row is a rule row and the new style is not null.
	 * @param style new rule style
	 */
	public void setRuleStyle(RuleRowStyle style){
		if(style!=null){
			this.ruleStyle = style;
		}
	}

	/**
	 * Returns the rule type of the row.
	 * @return rule type, null if not set, i.e. for content rows
	 */
	public RuleRowType getRuleType(){
		return this.ruleType;
	}

	@Override
	public String toString(int indent){
		ToStringBuilder ret = new ToStringBuilder(this, ObjectToStringStyle.getStyle(indent));
		ret.append("row type       ", "rule type " + this.ruleType + " style " + this.ruleStyle);

		return ret.toString();
	}

	@Override
	public String toString(){
		return this.toString(0);
	}
}
