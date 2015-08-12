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
 * Builder for a table themes.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.2 build 150812 (12-Aug-15) for Java 1.7
 * @since      v0.0.5
 */
public class V2_TableThemeBuilder {

	/** Theme for a bottom row. */
	private V2_RowTheme bottom;

	/** Theme for a bottom row with strong style. */
	private V2_RowTheme bottomStrong;

	/** Theme for a content row. */
	private V2_RowTheme content;

	/** Theme description. */
	private String description;

	/** Theme for a mid row. */
	private V2_RowTheme mid;

	/** Theme for a mid row with strong style. */
	private V2_RowTheme midStrong;

	/** Theme for a top row. */
	private V2_RowTheme top;

	/** Theme for a top row with strong style. */
	private V2_RowTheme topStrong;

	/**
	 * Sets the bottom row theme.
	 * @param theme bottom row theme
	 * @return self to allow chaining
	 */
	public V2_TableThemeBuilder setBottom(V2_RowTheme theme){
		this.bottom = theme;
		return this;
	}

	/**
	 * Sets the bottom row theme for strong style.
	 * @param theme bottom row theme for strong style
	 * @return self to allow chaining
	 */
	public V2_TableThemeBuilder setBottomStrong(V2_RowTheme theme){
		this.bottomStrong = theme;
		return this;
	}

	/**
	 * Sets the content row theme.
	 * @param theme content row theme
	 * @return self to allow chaining
	 */
	public V2_TableThemeBuilder setContent(V2_RowTheme theme){
		this.content = theme;
		return this;
	}

	/**
	 * Sets the theme description
	 * @param description a descriptive text for the theme
	 * @return self to allow for chaining
	 */
	public V2_TableThemeBuilder setDescription(String description){
		this.description = description;
		return this;
	}

	/**
	 * Sets the mid row theme.
	 * @param theme mid row theme
	 * @return self to allow chaining
	 */
	public V2_TableThemeBuilder setMid(V2_RowTheme theme){
		this.mid = theme;
		return this;
	}

	/**
	 * Sets the mid row theme for strong style.
	 * @param theme mid row theme for strong style
	 * @return self to allow chaining
	 */
	public V2_TableThemeBuilder setMidStrong(V2_RowTheme theme){
		this.midStrong = theme;
		return this;
	}

	/**
	 * Sets the top row theme.
	 * @param theme top row theme
	 * @return self to allow chaining
	 */
	public V2_TableThemeBuilder setTop(V2_RowTheme theme){
		this.top = theme;
		return this;
	}

	/**
	 * Sets the top row theme for strong style.
	 * @param theme top row theme for strong style
	 * @return self to allow chaining
	 */
	public V2_TableThemeBuilder setTopStrong(V2_RowTheme theme){
		this.topStrong = theme;
		return this;
	}

	/**
	 * Returns a new table theme object.
	 * @return final table theme
	 * @throws TableException if any of the parameters is null or blank
	 */
	public V2_TableTheme build(){
		return new V2_AbstractTableTheme(this.top, this.topStrong, this.mid, this.midStrong, this.bottom, this.bottomStrong, this.content, this.description);
	}

}
