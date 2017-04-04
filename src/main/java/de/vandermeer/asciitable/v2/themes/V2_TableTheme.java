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
 * Interface for table themes.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v1.0.0 build 170331 (31-Mar-17) for Java 1.7
 * @since      v0.0.3
 */
public interface V2_TableTheme {

	/**
	 * Returns the row theme for a bottom rule.
	 * @return bottom rule theme
	 */
	V2_RowTheme getBottom();

	/**
	 * Returns the row theme for a strong (emphasized) style bottom rule.
	 * @return bottom rule theme
	 */
	V2_RowTheme getBottomStrong();

	/**
	 * Returns the row theme for a content row.
	 * @return content row theme
	 */
	V2_RowTheme getContent();

	/**
	 * Returns the description of the table theme
	 * @return theme description
	 */
	Object getDescription();

	/**
	 * Returns the row theme for a mid rule.
	 * @return mid rule theme
	 */
	V2_RowTheme getMid();

	/**
	 * Returns the row theme for a strong (emphasized) style mid rule.
	 * @return mid rule theme
	 */
	V2_RowTheme getMidStrong();

	/**
	 * Returns the row theme for a top rule.
	 * @return top rule theme
	 */
	V2_RowTheme getTop();

	/**
	 * Returns the row theme for a strong (emphasized) style top rule.
	 * @return top rule theme
	 */
	V2_RowTheme getTopStrong();

	/**
	 * Returns a representation of the table theme useful for documentation.
	 * @return documentation representation of the table theme
	 */
	StrBuilder toDoc();

}
