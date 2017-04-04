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

import org.apache.commons.lang3.Validate;

import de.vandermeer.skb.interfaces.document.IsTableCellContext;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import de.vandermeer.skb.interfaces.translators.CharacterTranslator;
import de.vandermeer.skb.interfaces.translators.HtmlElementTranslator;
import de.vandermeer.skb.interfaces.translators.TargetTranslator;

/**
 * Context (all configuration parameters) for an {@link AT_Cell}.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.3.0
 */
public class AT_CellContext implements IsTableCellContext {

	/** A simple character translator. */
	protected CharacterTranslator charTranslator;

	/** A translator for HTML elements. */
	protected HtmlElementTranslator htmlElementTranslator;

	/** The padding for the bottom of a text line. */
	protected int paddingBottom = 0;

	/** The padding character for the bottom of a text line. */
	protected Character paddingBottomChar = ' ';

	/** The padding for left side of a text line. */
	protected int paddingLeft = 0;

	/** The padding character for left side of a text line. */
	protected Character paddingLeftChar = ' ';

	/** The padding for right side of a text line. */
	protected int paddingRight = 0;

	/** The padding character for right side of a text line. */
	protected Character paddingRightChar = ' ';

	/** The padding for the top of a text line. */
	protected int paddingTop = 0;

	/** The padding character for the top of a text line. */
	protected Character paddingTopChar = ' ';

	/** A translator for a particular target. */
	protected TargetTranslator targetTranslator;

	/** Text alignment, default is {@link TextAlignment#JUSTIFIED_LEFT}. */
	protected TextAlignment textAlignment = TextAlignment.JUSTIFIED_LEFT;

	/**
	 * Returns the character translator
	 * @return character translator
	 */
	public CharacterTranslator getCharTranslator() {
		return this.charTranslator;
	}

	/**
	 * Returns the HTML entity translator.
	 * @return HTML entity
	 */
	public HtmlElementTranslator getHtmlElementTranslator() {
		return this.htmlElementTranslator;
	}

	/**
	 * Returns the bottom padding.
	 * @return bottom padding, 0 if none set
	 */
	public int getPaddingBottom() {
		return this.paddingBottom;
	}

	/**
	 * Returns the bottom padding character.
	 * @return bottom padding character
	 */
	public Character getPaddingBottomChar() {
		return this.paddingBottomChar;
	}

	/**
	 * Returns the left padding.
	 * @return left padding, 0 if none set
	 */
	public int getPaddingLeft() {
		return this.paddingLeft;
	}

	/**
	 * Returns the left padding character.
	 * @return left padding character
	 */
	public Character getPaddingLeftChar() {
		return this.paddingLeftChar;
	}

	/**
	 * Returns the right padding.
	 * @return right padding, 0 if none set
	 */
	public int getPaddingRight() {
		return this.paddingRight;
	}

	/**
	 * Returns the right padding character.
	 * @return right padding character
	 */
	public Character getPaddingRightChar() {
		return this.paddingRightChar;
	}

	/**
	 * Returns the top padding.
	 * @return top padding, 0 if none set
	 */
	public int getPaddingTop() {
		return this.paddingTop;
	}

	/**
	 * Returns the top padding character.
	 * @return top padding character
	 */
	public Character getPaddingTopChar() {
		return this.paddingTopChar;
	}

	/**
	 * Returns the target translator.
	 * @return target translator, null if not set
	 */
	public TargetTranslator getTargetTranslator() {
		return this.targetTranslator;
	}

	/**
	 * Returns the set text alignment.
	 * @return text alignment
	 */
	public TextAlignment getTextAlignment(){
		return this.textAlignment;
	}

	/**
	 * Sets the character translator.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param charTranslator translator
	 */
	public void setCharTranslator(CharacterTranslator charTranslator) {
		if(charTranslator!=null){
			this.charTranslator = charTranslator;
			this.htmlElementTranslator = null;
			this.targetTranslator = null;
		}
	}

	/**
	 * Sets the HTML entity translator.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param htmlElementTranslator translator
	 */
	public void setHtmlElementTranslator(HtmlElementTranslator htmlElementTranslator) {
		if(htmlElementTranslator!=null){
			this.htmlElementTranslator = htmlElementTranslator;
			this.charTranslator = null;
			this.targetTranslator = null;
		}
	}

	/**
	 * Sets all padding to the same value.
	 * @param padding new padding for top, bottom, left, and right, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_CellContext setPadding(int padding){
		if(padding>-1){
			this.paddingTop = padding;
			this.paddingBottom = padding;
			this.paddingLeft = padding;
			this.paddingRight = padding;
		}
		return this;
	}

	/**
	 * Sets the bottom padding.
	 * @param paddingBottom new padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_CellContext setPaddingBottom(int paddingBottom) {
		if(paddingBottom>-1){
			this.paddingBottom = paddingBottom;
		}
		return this;
	}

	/**
	 * Sets the bottom padding character.
	 * @param paddingBottomChar new padding character, ignored if null
	 * @return this to allow chaining
	 */
	public AT_CellContext setPaddingBottomChar(Character paddingBottomChar) {
		if(paddingBottomChar!=null){
			this.paddingBottomChar = paddingBottomChar;
		}
		return this;
	}

	/**
	 * Sets the left padding.
	 * @param paddingLeft new padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_CellContext setPaddingLeft(int paddingLeft) {
		if(paddingLeft>-1){
			this.paddingLeft = paddingLeft;
		}
		return this;
	}

	/**
	 * Sets the left padding character.
	 * @param paddingLeftChar new padding character, ignored if null
	 * @return this to allow chaining
	 */
	public AT_CellContext setPaddingLeftChar(Character paddingLeftChar) {
		if(paddingLeftChar!=null){
			this.paddingLeftChar = paddingLeftChar;
		}
		return this;
	}

	/**
	 * Sets left and right padding.
	 * @param padding new padding for left and right, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_CellContext setPaddingLeftRight(int padding){
		if(padding>-1){
			this.paddingLeft = padding;
			this.paddingRight = padding;
		}
		return this;
	}

	/**
	 * Sets left and right padding (only if both values are not smaller than 0).
	 * @param paddingLeft new left padding, ignored if smaller than 0
	 * @param paddingRight new right padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_CellContext setPaddingLeftRight(int paddingLeft, int paddingRight){
		if(paddingLeft>-1 && paddingRight>-1){
			this.paddingLeft = paddingLeft;
			this.paddingRight = paddingRight;
		}
		return this;
	}

	/**
	 * Sets the right padding.
	 * @param paddingRight new padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_CellContext setPaddingRight(int paddingRight) {
		if(paddingRight>-1){
			this.paddingRight = paddingRight;
		}
		return this;
	}

	/**
	 * Sets the right padding character.
	 * @param paddingRightChar new padding character, ignored if null
	 * @return this to allow chaining
	 */
	public AT_CellContext setPaddingRightChar(Character paddingRightChar) {
		if(paddingRightChar!=null){
			this.paddingRightChar = paddingRightChar;
		}
		return this;
	}

	/**
	 * Sets the top padding.
	 * @param paddingTop new padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_CellContext setPaddingTop(int paddingTop) {
		if(paddingTop>-1){
			this.paddingTop = paddingTop;
		}
		return this;
	}

	/**
	 * Sets top and bottom padding.
	 * @param padding new padding for top and bottom, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_CellContext setPaddingTopBottom(int padding){
		if(padding>-1){
			this.paddingTop = padding;
			this.paddingBottom = padding;
		}
		return this;
	}

	/**
	 * Sets top and bottom padding (only if both values are not smaller than 0).
	 * @param paddingTop new top padding, ignored if smaller than 0
	 * @param paddingBottom new bottom padding, ignored if smaller than 0
	 * @return this to allow chaining
	 */
	public AT_CellContext setPaddingTopBottom(int paddingTop, int paddingBottom){
		if(paddingTop>-1 && paddingBottom>-1){
			this.paddingTop = paddingTop;
			this.paddingBottom = paddingBottom;
		}
		return this;
	}

	/**
	 * Sets the top padding character.
	 * @param paddingTopChar new padding character, ignored if null
	 * @return this to allow chaining
	 */
	public AT_CellContext setPaddingTopChar(Character paddingTopChar) {
		if(paddingTopChar!=null){
			this.paddingTopChar = paddingTopChar;
		}
		return this;
	}

	/**
	 * Sets the target translator.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param targetTranslator translator
	 */
	public void setTargetTranslator(TargetTranslator targetTranslator) {
		if(targetTranslator!=null){
			this.targetTranslator = targetTranslator;
			this.charTranslator = null;
			this.htmlElementTranslator = null;
		}
	}

	/**
	 * Sets the text alignment.
	 * @param textAlignment new text alignment
	 * @throws NullPointerException if the argument was null
	 * @return this to allow chaining
	 * @throws {@link NullPointerException} if the argument was null
	 */
	public AT_CellContext setTextAlignment(TextAlignment textAlignment){
		Validate.notNull(textAlignment);
		this.textAlignment = textAlignment;
		return this;
	}

}
