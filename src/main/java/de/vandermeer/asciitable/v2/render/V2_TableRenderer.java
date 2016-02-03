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

package de.vandermeer.asciitable.v2.render;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.themes.V2_TableTheme;

/**
 * Table renderer interface.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.3 build 160203 (03-Feb-16) for Java 1.7
 * @since      v0.2.0
 */
public interface V2_TableRenderer {

	/**
	 * Renders the given table and returns a list of string builders with the rendered rows.
	 * @param table table to be rendered
	 * @return linked list of string builders with rendered rows
	 */
	RenderedTable render(V2_AsciiTable table);

	/**
	 * Sets the width for the rendered.
	 * @param width new width
	 * @return self to allow for chaining
	 */
	V2_TableRenderer setWidth(V2_Width width);

	/**
	 * Sets the padding character.
	 * @param pChar new padding character
	 * @return self to allow for chaining
	 */
	V2_TableRenderer setPaddingChar(char pChar);

	/**
	 * Sets and tests the theme for the table.
	 * @param theme new theme for the table
	 * @return self to allow for chaining
	 * @throws IllegalArgumentException if the theme is not valid
	 */
	V2_TableRenderer setTheme(V2_TableTheme theme);

}
