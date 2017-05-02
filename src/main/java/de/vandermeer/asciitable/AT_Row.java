/* Copyright 2017 Sven van der Meer <vdmeer.sven@mykolab.com>
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

package de.vandermeer.asciitable;

import java.util.LinkedList;

import org.apache.commons.lang3.Validate;

import de.vandermeer.skb.interfaces.document.IsTableRow;
import de.vandermeer.skb.interfaces.document.TableRowStyle;
import de.vandermeer.skb.interfaces.document.TableRowType;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import de.vandermeer.skb.interfaces.translators.CharacterTranslator;
import de.vandermeer.skb.interfaces.translators.HtmlElementTranslator;
import de.vandermeer.skb.interfaces.translators.TargetTranslator;

/**
 * Row of an {@link AsciiTable}.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.3.0
 */
public class AT_Row implements IsTableRow {

	/**
	 * Creates a new row with content with default cell context and a normal row style.
	 * @param content the content for the row, each member of the array represents the content for a cell in the row, must not be null but can contain null members
	 * @return a new row with content
	 * @throws NullPointerException if content was null
	 */
	public static AT_Row createContentRow(Object[] content){
		return AT_Row.createContentRow(content, TableRowStyle.NORMAL);
	}

	/**
	 * Creates a new row with content with given cell context and a normal row style.
	 * @param content the content for the row, each member of the array represents the content for a cell in the row, must not be null but can contain null members
	 * @param style the table row style, must not be null
	 * @return a new row with content
	 * @throws NullPointerException if content was null
	 */
	public static AT_Row createContentRow(Object[] content, TableRowStyle style){
		Validate.notNull(content);
		Validate.notNull(style);
		Validate.validState(style!=TableRowStyle.UNKNOWN);

		LinkedList<AT_Cell> cells = new LinkedList<AT_Cell>();
		for(Object o : content){
			cells.add(new AT_Cell(o));
		}

		return new AT_Row(){
			@Override
			public LinkedList<AT_Cell> getCells(){
				return cells;
			}

			@Override
			public TableRowStyle getStyle(){
				return style;
			}

			@Override
			public TableRowType getType(){
				return TableRowType.CONTENT;
			}
		};
	}

	/**
	 * Creates a new row representing a rule.
	 * @param type the type for the rule row, must not be null nor {@link TableRowType#CONTENT} nor {@link TableRowType#UNKNOWN}
	 * @param style the style for the rule row, must not be null nor {@link TableRowStyle#UNKNOWN}
	 * @return a new row representing a rule
	 * @throws NullPointerException if type or style where null
	 * @throws IllegalStateException if type or style where unknown or if type was {@link TableRowType#CONTENT}
	 */
	public static AT_Row createRule(TableRowType type, TableRowStyle style){
		Validate.notNull(type);
		Validate.validState(type!=TableRowType.UNKNOWN);
		Validate.validState(type!=TableRowType.CONTENT);
		Validate.notNull(style);
		Validate.validState(style!=TableRowStyle.UNKNOWN);

		return new AT_Row(){
			@Override
			public TableRowStyle getStyle(){
				return style;
			}

			@Override
			public TableRowType getType(){
				return type;
			}
		};
	}

	/** The row context. */
	protected AT_RowContext ctx = new AT_RowContext();

	@Override
	public LinkedList<AT_Cell> getCells(){
		return null;
	}

	@Override
	public AT_RowContext getContext() {
		return this.ctx;
	}

	/**
	 * Sets the character translator for all cells in the row.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param charTranslator translator
	 * @return this to allow chaining
	 */
	public AT_Row setCharTranslator(CharacterTranslator charTranslator) {
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setCharTranslator(charTranslator);
			}
		}
		return this;
	}

	/**
	 * Sets the HTML entity translator for all cells in the row.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param htmlElementTranslator translator
	 * @return this to allow chaining
	 */
	public AT_Row setHtmlElementTranslator(HtmlElementTranslator htmlElementTranslator) {
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setHtmlElementTranslator(htmlElementTranslator);
			}
		}
		return this;
	}

	/**
	 * Sets all padding for all cells in the row to the same value.
	 * @param padding new padding for top, bottom, left, and right, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_Row setPadding(int padding){
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPadding(padding);
			}
		}
		return this;
	}

	/**
	 * Sets the bottom padding for all cells in the row.
	 * @param paddingBottom new padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_Row setPaddingBottom(int paddingBottom) {
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPaddingBottom(paddingBottom);
			}
		}
		return this;
	}

	/**
	 * Sets the bottom padding character for all cells in the row.
	 * @param paddingBottomChar new padding character, ignored if null
	 * @return this to allow chaining
	 */
	public AT_Row setPaddingBottomChar(Character paddingBottomChar) {
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPaddingBottomChar(paddingBottomChar);
			}
		}
		return this;
	}

	/**
	 * Sets the left padding for all cells in the row.
	 * @param paddingLeft new padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_Row setPaddingLeft(int paddingLeft) {
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPaddingLeft(paddingLeft);
			}
		}
		return this;
	}

	/**
	 * Sets the left padding character for all cells in the row.
	 * @param paddingLeftChar new padding character, ignored if null
	 * @return this to allow chaining
	 */
	public AT_Row setPaddingLeftChar(Character paddingLeftChar) {
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPaddingLeftChar(paddingLeftChar);
			}
		}
		return this;
	}

	/**
	 * Sets left and right padding for all cells in the row.
	 * @param padding new padding for left and right, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_Row setPaddingLeftRight(int padding){
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPaddingLeftRight(padding);
			}
		}
		return this;
	}

	/**
	 * Sets left and right padding for all cells in the row (only if both values are not smaller than 0).
	 * @param paddingLeft new left padding, ignored if smaller than 0
	 * @param paddingRight new right padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_Row setPaddingLeftRight(int paddingLeft, int paddingRight){
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPaddingLeftRight(paddingLeft, paddingRight);
			}
		}
		return this;
	}

	/**
	 * Sets the right padding for all cells in the row.
	 * @param paddingRight new padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_Row setPaddingRight(int paddingRight) {
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPaddingRight(paddingRight);
			}
		}
		return this;
	}

	/**
	 * Sets the right padding character for all cells in the row.
	 * @param paddingRightChar new padding character, ignored if null
	 * @return this to allow chaining
	 */
	public AT_Row setPaddingRightChar(Character paddingRightChar) {
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPaddingRightChar(paddingRightChar);
			}
		}
		return this;
	}

	/**
	 * Sets the top padding for all cells in the row.
	 * @param paddingTop new padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_Row setPaddingTop(int paddingTop) {
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPaddingTop(paddingTop);
			}
		}
		return this;
	}

	/**
	 * Sets top and bottom padding for all cells in the row.
	 * @param padding new padding for top and bottom, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_Row setPaddingTopBottom(int padding){
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPaddingTopBottom(padding);
			}
		}
		return this;
	}

	/**
	 * Sets top and bottom padding for all cells in the row (only if both values are not smaller than 0).
	 * @param paddingTop new top padding, ignored if smaller than 0
	 * @param paddingBottom new bottom padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_Row setPaddingTopBottom(int paddingTop, int paddingBottom){
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPaddingTopBottom(paddingTop, paddingBottom);
			}
		}
		return this;
	}

	/**
	 * Sets the top padding character for all cells in the row.
	 * @param paddingTopChar new padding character, ignored if null
	 * @return this to allow chaining
	 */
	public AT_Row setPaddingTopChar(Character paddingTopChar) {
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setPaddingTopChar(paddingTopChar);
			}
		}
		return this;
	}

	/**
	 * Sets the target translator for all cells in the row.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param targetTranslator translator
	 * @return this to allow chaining
	 */
	public AT_Row setTargetTranslator(TargetTranslator targetTranslator) {
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setTargetTranslator(targetTranslator);
			}
		}
		return this;
	}

	/**
	 * Sets the text alignment for all cells in the row.
	 * @param textAlignment new text alignment
	 * @return this to allow chaining
	 * @throws NullPointerException if the argument was null
	 */
	public AT_Row setTextAlignment(TextAlignment textAlignment){
		if(this.hasCells()){
			for(AT_Cell cell : this.getCells()){
				cell.getContext().setTextAlignment(textAlignment);
			}
		}
		return this;
	}
}
