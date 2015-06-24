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
 * Interface for themes of table rows.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4 build 150619 (19-Jun-15) for Java 1.8
 */
public interface RowTheme {

	/**
	 * Returns the character for a left border.
	 * @return left border character
	 */
	char getLeftBorder();

	/**
	 * Returns the character for a mid border (top and bottom).
	 * @return mid border character (top and bottom)
	 */
	char getMidBorderAll();

	/**
	 * Returns the character for a mid border (top).
	 * @return mid border character (top)
	 */
	char getMidBorderUp();

	/**
	 * Returns the character for a mid border (bottom).
	 * @return mid border character (bottom)
	 */
	char getMidBorderDown();

	/**
	 * Returns the character for a right border.
	 * @return left right character
	 */
	char getRightBorder();

	/**
	 * Returns the character for a middle position (no border).
	 * @return middle position character
	 */
	char getMid();

	/**
	 * Returns a description of the row theme
	 * @return description
	 */
	Object getDescription();

	/**
	 * Returns a representation of the row theme useful for documentation
	 * @return documentation representation of the row theme
	 */
	default StrBuilder toDoc(){
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

	/**
	 * Tests a row theme.
	 * @param theme theme to be tested
	 * @throws IllegalArgumentException if any of the theme methods returns 0 or null
	 */
	static void testTheme(RowTheme theme){
		if(theme.getLeftBorder()==0){
			throw new IllegalArgumentException("row theme incomplete: no left border defined");
		}
		if(theme.getRightBorder()==0){
			throw new IllegalArgumentException("row theme incomplete: no right border defined");
		}
		if(theme.getMid()==0){
			throw new IllegalArgumentException("row theme incomplete: no mid character defined");
		}
		if(theme.getMidBorderAll()==0){
			throw new IllegalArgumentException("row theme incomplete: no mid all character defined");
		}
		if(theme.getMidBorderDown()==0){
			throw new IllegalArgumentException("row theme incomplete: no mid down character defined");
		}
		if(theme.getMidBorderUp()==0){
			throw new IllegalArgumentException("row theme incomplete: no mid up character defined");
		}
		if(theme.getDescription()==null){
			throw new IllegalArgumentException("row theme incomplete: no description defined");
		}
	}
}
