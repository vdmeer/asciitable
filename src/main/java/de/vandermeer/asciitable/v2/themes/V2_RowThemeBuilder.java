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

import de.vandermeer.asciitable.commons.TableException;


/**
 * Builder for a row themes.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 150812 (12-Aug-15) for Java 1.7
 * @since      v0.0.3
 */
public class V2_RowThemeBuilder {

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
	public V2_RowThemeBuilder setLeftBorder(char c){
		this.leftBorder = c;
		return this;
	}

	/**
	 * Sets the mid-up border character.
	 * @param c mid-up border character
	 * @return self to allow chaining
	 */
	public V2_RowThemeBuilder setMidBorderUp(char c){
		this.midBorderUp = c;
		return this;
	}

	/**
	 * Sets the mid-down border character.
	 * @param c mid-down border character
	 * @return self to allow chaining
	 */
	public V2_RowThemeBuilder setMidBorderDown(char c){
		this.midBorderDown = c;
		return this;
	}

	/**
	 * Sets the mid-all border character.
	 * @param c mid-all border character
	 * @return self to allow chaining
	 */
	public V2_RowThemeBuilder setMidBorderAll(char c){
		this.midBorderAll = c;
		return this;
	}

	/**
	 * Sets the mid border character.
	 * @param c mid border character
	 * @return self to allow chaining
	 */
	public V2_RowThemeBuilder setMid(char c){
		this.mid = c;
		return this;
	}

	/**
	 * Sets the description for the theme.
	 * @param description theme description
	 * @return self to allow chaining
	 */
	public V2_RowThemeBuilder setDescription(String description){
		this.description = description;
		return this;
	}

	/**
	 * Sets the right border character.
	 * @param c right border character
	 * @return self to allow chaining
	 */
	public V2_RowThemeBuilder setRightBorder(char c){
		this.rightBorder = c;
		return this;
	}

	/**
	 * Returns a new row theme object.
	 * @return new row theme object
	 * @throws TableException if any of the parameters is null or blank
	 */
	public V2_RowTheme build() {
		return new V2_AbstractRowTheme(this.rightBorder, this.leftBorder, this.mid, this.midBorderAll, this.midBorderUp, this.midBorderDown, this.description);
	}

}
