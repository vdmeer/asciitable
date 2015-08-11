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


/**
 * Builder for a table themes.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.7 build 150811 (11-Aug-15) for Java 1.7
 * @since      v0.0.5
 */
public class TableThemeBuilder {

	/** Theme for a bottom row. */
	private RowTheme bottom;

	/** Theme for a bottom row with strong style. */
	private RowTheme bottomStrong;

	/** Theme for a content row. */
	private RowTheme content;

	/** Theme description. */
	private String description;

	/** Theme for a mid row. */
	private RowTheme mid;

	/** Theme for a mid row with strong style. */
	private RowTheme midStrong;

	/** Theme for a top row. */
	private RowTheme top;

	/** Theme for a top row with strong style. */
	private RowTheme topStrong;

	/**
	 * Sets the bottom row theme.
	 * @param theme bottom row theme
	 * @return self to allow chaining
	 */
	public TableThemeBuilder setBottom(RowTheme theme){
		this.bottom = theme;
		return this;
	}

	/**
	 * Sets the bottom row theme for strong style.
	 * @param theme bottom row theme for strong style
	 * @return self to allow chaining
	 */
	public TableThemeBuilder setBottomStrong(RowTheme theme){
		this.bottomStrong = theme;
		return this;
	}

	/**
	 * Sets the content row theme.
	 * @param theme content row theme
	 * @return self to allow chaining
	 */
	public TableThemeBuilder setContent(RowTheme theme){
		this.content = theme;
		return this;
	}

	/**
	 * Sets the theme description
	 * @param description a descriptive text for the theme
	 * @return self to allow for chaining
	 */
	public TableThemeBuilder setDescription(String description){
		this.description = description;
		return this;
	}

	/**
	 * Sets the mid row theme.
	 * @param theme mid row theme
	 * @return self to allow chaining
	 */
	public TableThemeBuilder setMid(RowTheme theme){
		this.mid = theme;
		return this;
	}

	/**
	 * Sets the mid row theme for strong style.
	 * @param theme mid row theme for strong style
	 * @return self to allow chaining
	 */
	public TableThemeBuilder setMidStrong(RowTheme theme){
		this.midStrong = theme;
		return this;
	}

	/**
	 * Sets the top row theme.
	 * @param theme top row theme
	 * @return self to allow chaining
	 */
	public TableThemeBuilder setTop(RowTheme theme){
		this.top = theme;
		return this;
	}

	/**
	 * Sets the top row theme for strong style.
	 * @param theme top row theme for strong style
	 * @return self to allow chaining
	 */
	public TableThemeBuilder setTopStrong(RowTheme theme){
		this.topStrong = theme;
		return this;
	}

	/**
	 * Builds the final theme.
	 * @return final table theme
	 * @throws IllegalArgumentException if the theme is not valid
	 */
	public TableTheme build(){
		TableTheme ret = this.build(this.top, this.topStrong, this.mid, this.midStrong, this.bottom, this.bottomStrong, this.content, this.description);
		V2Validator.testTableTheme(ret);
		return ret;
	}

	/**
	 * Returns a new table theme with the set row themes.
	 * @param top theme for top row
	 * @param topStrong theme for top row for strong style
	 * @param mid theme for mid row
	 * @param midStrong theme for mid row for strong style
	 * @param bottom theme for bottom row
	 * @param bottomStrong theme for bottom row for strong style
	 * @param content theme for content row
	 * @param description descriptive text for the theme
	 * @return a new table theme
	 */
	private TableTheme build(final RowTheme top, final RowTheme topStrong, final RowTheme mid, final RowTheme midStrong, final RowTheme bottom, final RowTheme bottomStrong, final RowTheme content, final String description){
		return new AbstractTableTheme(top, topStrong, mid, midStrong, bottom, bottomStrong, content, description);
	}

}
