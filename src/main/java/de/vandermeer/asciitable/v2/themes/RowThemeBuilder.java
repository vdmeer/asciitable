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

package de.vandermeer.asciitable.v2.themes;

import org.apache.commons.lang3.text.StrBuilder;

/**
 * Builder for a row themes.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.6 build 150721 (21-Jul-15) for Java 1.7
 * @since      v0.0.3
 */
public class RowThemeBuilder {

	/** Left border character. */
	private char leftBorder;

	/** Mid-up border character. */
	private char midBorderUp;

	/** Mid-all border character. */
	private char midBorderAll;

	/** Mid-down border character. */
	private char midBorderDown;

	/** Right border character. */
	private char rightBorder;

	/** Mid border character. */
	private char mid;

	/** Description of the theme. */
	private String description;

	/**
	 * Sets the left border character.
	 * @param c left border character
	 * @return self to allow chaining
	 */
	public RowThemeBuilder setLeftBorder(char c){
		this.leftBorder = c;
		return this;
	}

	/**
	 * Sets the mid-up border character.
	 * @param c mid-up border character
	 * @return self to allow chaining
	 */
	public RowThemeBuilder setMidBorderUp(char c){
		this.midBorderUp = c;
		return this;
	}

	/**
	 * Sets the mid-down border character.
	 * @param c mid-down border character
	 * @return self to allow chaining
	 */
	public RowThemeBuilder setMidBorderDown(char c){
		this.midBorderDown = c;
		return this;
	}

	/**
	 * Sets the mid-all border character.
	 * @param c mid-all border character
	 * @return self to allow chaining
	 */
	public RowThemeBuilder setMidBorderAll(char c){
		this.midBorderAll = c;
		return this;
	}

	/**
	 * Sets the mid border character.
	 * @param c mid border character
	 * @return self to allow chaining
	 */
	public RowThemeBuilder setMid(char c){
		this.mid = c;
		return this;
	}

	/**
	 * Sets the description for the theme.
	 * @param description theme description
	 * @return self to allow chaining
	 */
	public RowThemeBuilder setDescription(String description){
		this.description = description;
		return this;
	}

	/**
	 * Sets the right border character.
	 * @param c right border character
	 * @return self to allow chaining
	 */
	public RowThemeBuilder setRightBorder(char c){
		this.rightBorder = c;
		return this;
	}

	/**
	 * Returns a new row theme object.
	 * @return new row theme object
	 */
	public RowTheme build() {
		return this.create(this.rightBorder, this.leftBorder, this.mid, this.midBorderAll, this.midBorderUp, this.midBorderDown, this.description);
	}

	/**
	 * Creates a new row theme object without any error tests.
	 * @param right character right border
	 * @param left character left border
	 * @param mid character mid 
	 * @param midAll character mid and up and down
	 * @param midUp character mid and up
	 * @param midDown character mid and down
	 * @param description row theme description
	 * @return
	 */
	private RowTheme create(final char right, final char left, final char mid, final char midAll, final char midUp, final char midDown, final String description){
		return new RowTheme() {
			@Override public char getRightBorder() {return right;}
			@Override public char getMidBorderUp() {return midUp;}
			@Override public char getMidBorderDown() {return midDown;}
			@Override public char getMidBorderAll() {return midAll;}
			@Override public char getMid() {return mid;}
			@Override public char getLeftBorder() {return left;}
			@Override public Object getDescription() {return description;}
			@Override public StrBuilder toDoc(){
				StrBuilder ret = new StrBuilder(10);
				ret
					.append(getLeftBorder())
					.append(getMid())
					.append(getMidBorderUp())
					.append(getMid())
					.append(getMidBorderAll())
					.append(getMid())
					.append(getMidBorderDown())
					.append(getMid())
					.append(getRightBorder())
				;
				return ret;
			}
		};
	}
}
