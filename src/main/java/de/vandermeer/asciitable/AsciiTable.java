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

import java.util.Collection;
import java.util.LinkedList;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.StrBuilder;

import de.vandermeer.skb.interfaces.document.IsTable;
import de.vandermeer.skb.interfaces.document.TableRowStyle;
import de.vandermeer.skb.interfaces.document.TableRowType;
import de.vandermeer.skb.interfaces.strategies.collections.list.ArrayListStrategy;
import de.vandermeer.skb.interfaces.transformers.ClusterElementTransformer;
import de.vandermeer.skb.interfaces.transformers.StrBuilder_To_String;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import de.vandermeer.skb.interfaces.translators.CharacterTranslator;
import de.vandermeer.skb.interfaces.translators.HtmlElementTranslator;
import de.vandermeer.skb.interfaces.translators.TargetTranslator;

/**
 * An ASCII table with some formatting options.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.3.0
 */
public class AsciiTable implements IsTable {

	/** The table context with optional settings for the table. */
	protected AT_Context ctx;

	/** The renderer for the paragraph, default is {@link AP_Renderer}. */
	protected AT_Renderer renderer = AT_Renderer.create();

	/** Rows of the table. */
	protected final LinkedList<AT_Row> rows = new LinkedList<>();

	/** Number of columns for the table. */
	protected int colNumber;

	/**
	 * Creates a new ASCII table with a default context.
	 */
	public AsciiTable(){
		this(null);
	}

	/**
	 * Creates a new ASCII table with given context.
	 * @param ctx the table context, default context created if null
	 */
	public AsciiTable(AT_Context ctx){
		if(ctx!=null){
			this.ctx = ctx;
		}
		else{
			this.ctx = new AT_Context();
		}
	}

	/**
	 * Adds a content row to the table.
	 * For the first content row added, the number of objects given here determines the number of columns in the table.
	 * For every subsequent content row, the array must have an entry for each column,
	 * i.e. the size of the array must be the same as the result of {@link #getColNumber()}.
	 * @param columns content of the columns for the row, must not be null
	 * @return the created row for further customization
	 * @throws {@link NullPointerException} if content was null
	 * @throws {@link AsciiTableException} if columns does not have the correct size (more or less entries than columns defined for the table)
	 */
	public final AT_Row addRow(Object ...columns){
		AT_Row ret = AT_Row.createContentRow(columns, TableRowStyle.NORMAL);

		if(this.colNumber==0){
			this.colNumber = columns.length;
		}
		else{
			if(columns.length!=this.colNumber){
				throw new AsciiTableException("wrong columns argument", "wrong number of columns, expected " + this.colNumber + " received " + columns.length);
			}
		}

		this.rows.add(ret);
		return ret;
	}

	/**
	 * Adds a rule row to the table using the default style {@link TableRowStyle#NORMAL}.
	 */
	public final void addRule(){
		this.rows.add(AT_Row.createRule(TableRowType.RULE, TableRowStyle.NORMAL));
	}

	/**
	 * Adds a rule row to the table with a given style.
	 * @param style the rule style, must not be null nor {@link TableRowStyle#UNKNOWN}
	 * @throws {@link NullPointerException} if style was null
	 * @throws {@link IllegalArgumentException} if style was {@link TableRowStyle#UNKNOWN}
	 */
	public final void addRule(TableRowStyle style){
		Validate.notNull(style);
		Validate.validState(style!=TableRowStyle.UNKNOWN, "cannot add a rule of unknown style");
		this.rows.add(AT_Row.createRule(TableRowType.RULE, style));
	}

	/**
	 * Adds a rule with strong (emphasized) style row to the table.
	 */
	public final void addStrongRule(){
		this.rows.add(AT_Row.createRule(TableRowType.RULE, TableRowStyle.STRONG));
	}

	/**
	 * Adds a rule with heavy (super emphasized) style row to the table.
	 */
	public final void addHeavyRule(){
		this.rows.add(AT_Row.createRule(TableRowType.RULE, TableRowStyle.HEAVY));
	}

	/**
	 * Adds a rule with light (less emphasized) style row to the table.
	 */
	public final void addLightRule(){
		this.rows.add(AT_Row.createRule(TableRowType.RULE, TableRowStyle.LIGHT));
	}

	/**
	 * Returns the set number of columns in the table.
	 * @return number of columns, if 0 no content row was added yet
	 */
	public int getColNumber() {
		return this.colNumber;
	}

	/**
	 * Returns the table context.
	 * @return context, null if not set
	 */
	public AT_Context getContext(){
		return this.ctx;
	}

	@Override
	public LinkedList<AT_Row> getRawContent(){
		return this.rows;
	}

	@Override
	public AT_Renderer getRenderer(){
		return this.renderer;
	}

	@Override
	public String render(){
		return new StrBuilder().appendWithSeparators(this.renderer.render(this.getRawContent(), this.getColNumber(), this.ctx), "\n").toString();
	}

	@Override
	public String render(int width){
		return new StrBuilder().appendWithSeparators(this.renderer.render(this.getRawContent(), this.getColNumber(), this.ctx, this.ctx.getTextWidth(width)), "\n").toString();
	}

	@Override
	public Collection<String> renderAsCollection(){
		return ClusterElementTransformer.create().transform(
				this.renderer.render(this.getRawContent(), this.getColNumber(), this.ctx),
				StrBuilder_To_String.create(),
				ArrayListStrategy.create()
		);
	}

	@Override
	public Collection<String> renderAsCollection(int width){
		return ClusterElementTransformer.create().transform(
				this.renderer.render(this.getRawContent(), this.getColNumber(), this.ctx, this.ctx.getTextWidth(width)),
				StrBuilder_To_String.create(),
				ArrayListStrategy.create()
		);
	}

	/**
	 * Sets the table renderer.
	 * @param renderer new renderer, ignored if null
	 * @return this to allow chaining
	 */
	public AsciiTable setRenderer(AT_Renderer renderer){
		if(renderer!=null){
			this.renderer = renderer;
		}
		return this;
	}

	@Override
	public StrBuilder toLog(){
		StrBuilder ret = new StrBuilder();
		ret
			.append("AsciiTable: ")
			.append("#rows=").append(this.rows.size())
			.append(", #columns=").append(this.getColNumber())
			.append(", w=").append(this.ctx.getWidth())
			.append(", tw=").append(this.ctx.getTextWidth())
			.appendNewLine()
		;
		return ret;
	}

	/**
	 * Sets all padding for all cells in the table to the same value.
	 * @param padding new padding for top, bottom, left, and right, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AsciiTable setPadding(int padding){
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPadding(padding);
			}
		}
		return this;
	}

	/**
	 * Sets the bottom padding for all cells in the table.
	 * @param paddingBottom new padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AsciiTable setPaddingBottom(int paddingBottom) {
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPaddingBottom(paddingBottom);
			}
		}
		return this;
	}

	/**
	 * Sets the bottom padding character for all cells in the table.
	 * @param paddingBottomChar new padding character, ignored if null
	 * @return this to allow chaining
	 */
	public AsciiTable setPaddingBottomChar(Character paddingBottomChar) {
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPaddingBottomChar(paddingBottomChar);
			}
		}
		return this;
	}

	/**
	 * Sets the left padding for all cells in the table.
	 * @param paddingLeft new padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AsciiTable setPaddingLeft(int paddingLeft) {
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPaddingLeft(paddingLeft);
			}
		}
		return this;
	}

	/**
	 * Sets the left padding character for all cells in the table.
	 * @param paddingLeftChar new padding character, ignored if null
	 * @return this to allow chaining
	 */
	public AsciiTable setPaddingLeftChar(Character paddingLeftChar) {
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPaddingLeftChar(paddingLeftChar);
			}
		}
		return this;
	}

	/**
	 * Sets left and right padding for all cells in the table.
	 * @param padding new padding for left and right, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AsciiTable setPaddingLeftRight(int padding){
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPaddingLeftRight(padding);
			}
		}
		return this;
	}

	/**
	 * Sets left and right padding for all cells in the table (only if both values are not smaller than 0).
	 * @param paddingLeft new left padding, ignored if smaller than 0
	 * @param paddingRight new right padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AsciiTable setPaddingLeftRight(int paddingLeft, int paddingRight){
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPaddingLeftRight(paddingLeft, paddingRight);
			}
		}
		return this;
	}

	/**
	 * Sets the right padding for all cells in the table.
	 * @param paddingRight new padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AsciiTable setPaddingRight(int paddingRight) {
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPaddingRight(paddingRight);
			}
		}
		return this;
	}

	/**
	 * Sets the right padding character for all cells in the table.
	 * @param paddingRightChar new padding character, ignored if null
	 * @return this to allow chaining
	 */
	public AsciiTable setPaddingRightChar(Character paddingRightChar) {
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPaddingRightChar(paddingRightChar);
			}
		}
		return this;
	}

	/**
	 * Sets the top padding for all cells in the table.
	 * @param paddingTop new padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AsciiTable setPaddingTop(int paddingTop) {
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPaddingTop(paddingTop);
			}
		}
		return this;
	}

	/**
	 * Sets top and bottom padding for all cells in the table.
	 * @param padding new padding for top and bottom, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AsciiTable setPaddingTopBottom(int padding){
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPaddingTopBottom(padding);
			}
		}
		return this;
	}

	/**
	 * Sets top and bottom padding for all cells in the table (only if both values are not smaller than 0).
	 * @param paddingTop new top padding, ignored if smaller than 0
	 * @param paddingBottom new bottom padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AsciiTable setPaddingTopBottom(int paddingTop, int paddingBottom){
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPaddingTopBottom(paddingTop, paddingBottom);
			}
		}
		return this;
	}

	/**
	 * Sets the top padding character for all cells in the table.
	 * @param paddingTopChar new padding character, ignored if null
	 * @return this to allow chaining
	 */
	public AsciiTable setPaddingTopChar(Character paddingTopChar) {
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setPaddingTopChar(paddingTopChar);
			}
		}
		return this;
	}

	/**
	 * Sets the text alignment for all cells in the table.
	 * @param textAlignment new text alignment
	 * @throws NullPointerException if the argument was null
	 * @return this to allow chaining
	 * @throws {@link NullPointerException} if the argument was null
	 */
	public AsciiTable setTextAlignment(TextAlignment textAlignment){
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setTextAlignment(textAlignment);
			}
		}
		return this;
	}

	/**
	 * Sets the target translator for all cells in the table.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param targetTranslator translator
	 * @return this to allow chaining
	 */
	public AsciiTable setTargetTranslator(TargetTranslator targetTranslator) {
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setTargetTranslator(targetTranslator);
			}
		}
		return this;
	}

	/**
	 * Sets the HTML entity translator for all cells in the table.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param htmlElementTranslator translator
	 * @return this to allow chaining
	 */
	public AsciiTable setHtmlElementTranslator(HtmlElementTranslator htmlElementTranslator) {
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setHtmlElementTranslator(htmlElementTranslator);
			}
		}
		return this;
	}

	/**
	 * Sets the character translator for all cells in the table.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param charTranslator translator
	 * @return this to allow chaining
	 */
	public AsciiTable setCharTranslator(CharacterTranslator charTranslator) {
		for(AT_Row row : this.rows){
			if(row.getType()==TableRowType.CONTENT){
				row.setCharTranslator(charTranslator);
			}
		}
		return this;
	}
}
