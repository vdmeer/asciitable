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

package de.vandermeer.asciitable.v2;

import org.apache.commons.lang3.text.StrBuilder;

/**
 * Interface for table themes.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4 build 150619 (19-Jun-15) for Java 1.8
 */
public interface TableTheme {

	/**
	 * Returns the row theme for a bottom rule.
	 * @return bottom rule theme
	 */
	RowTheme getBottom();

	/**
	 * Returns the row theme for a strong (emphasized) style bottom rule.
	 * @return bottom rule theme
	 */
	RowTheme getBottomStrong();

	/**
	 * Returns the row theme for a content row.
	 * @return content row theme
	 */
	RowTheme getContent();

	/**
	 * Returns the description of the table theme
	 * @return theme description
	 */
	Object getDescription();

	/**
	 * Returns the row theme for a mid rule.
	 * @return mid rule theme
	 */
	RowTheme getMid();

	/**
	 * Returns the row theme for a strong (emphasized) style mid rule.
	 * @return mid rule theme
	 */
	RowTheme getMidStrong();

	/**
	 * Returns the row theme for a top rule.
	 * @return top rule theme
	 */
	RowTheme getTop();

	/**
	 * Returns the row theme for a strong (emphasized) style top rule.
	 * @return top rule theme
	 */
	RowTheme getTopStrong();

	/**
	 * Returns a representation of the table theme useful for documentation.
	 * @return documentation representation of the table theme
	 */
	default StrBuilder toDoc(){
		StrBuilder ret = new StrBuilder(50);
		ret
			.append(getTop().toDoc())    .append("        ").append(getTopStrong().toDoc())        .appendNewLine()
			.append(getContent().toDoc()).append("        ").append(getContent().toDoc())          .appendNewLine()
			.append(getMid().toDoc())    .append("        ").append(getMidStrong().toDoc())        .appendNewLine()
			.append(getContent().toDoc()).append("        ").append(getContent().toDoc())          .appendNewLine()
			.append(getBottom().toDoc()) .append("        ").append(getBottomStrong().toDoc())     .appendNewLine()
		;
		return ret;
	}

	/**
	 * Tests a table theme.
	 * @param theme theme to be tested
	 * @throws IllegalArgumentException if any of the theme methods returns 0 or null
	 */
	static void testTheme(TableTheme theme){
		if(theme.getTop()==null){
			throw new IllegalArgumentException("table theme incomplete: no top row defined");
		}
		if(theme.getTopStrong()==null){
			throw new IllegalArgumentException("table theme incomplete: no top row strong defined");
		}
		if(theme.getMid()==null){
			throw new IllegalArgumentException("table theme incomplete: no mid row strong defined");
		}
		if(theme.getMidStrong()==null){
			throw new IllegalArgumentException("table theme incomplete: no mid row strong defined");
		}
		if(theme.getBottom()==null){
			throw new IllegalArgumentException("table theme incomplete: no bottom row strong defined");
		}
		if(theme.getBottomStrong()==null){
			throw new IllegalArgumentException("table theme incomplete: no bottom row strong defined");
		}

		if(theme.getContent()==null){
			throw new IllegalArgumentException("table theme incomplete: no content row defined");
		}

		if(theme.getDescription()==null){
			throw new IllegalArgumentException("table theme incomplete: no description defined");
		}
	}
}
