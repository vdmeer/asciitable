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
 * Validator for ASCII Table v2 artifacts.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 150812 (12-Aug-15) for Java 1.7
 * @since      v0.0.5
 */
public abstract class V2_Validator {

	/**
	 * Tests a table theme.
	 * @param theme theme to be tested
	 * @throws TableException if any of the theme methods returns 0 or null
	 */
	public static void testTableTheme(V2_TableTheme theme){
		if(theme.getTop()==null){
			throw new TableException("table theme incomplete", "no top row defined");
		}
		if(theme.getTopStrong()==null){
			throw new TableException("table theme incomplete", "no top row strong defined");
		}
		if(theme.getMid()==null){
			throw new TableException("table theme incomplete", "no mid row strong defined");
		}
		if(theme.getMidStrong()==null){
			throw new TableException("table theme incomplete", "no mid row strong defined");
		}
		if(theme.getBottom()==null){
			throw new TableException("table theme incomplete", "no bottom row strong defined");
		}
		if(theme.getBottomStrong()==null){
			throw new TableException("table theme incomplete", "no bottom row strong defined");
		}

		if(theme.getContent()==null){
			throw new TableException("table theme incomplete", "no content row defined");
		}

		if(theme.getDescription()==null){
			throw new TableException("table theme incomplete", "no description defined");
		}
	}

	/**
	 * Tests a row theme.
	 * @param theme theme to be tested
	 * @throws TableException if any of the theme methods returns 0 or null
	 */
	public static void testRowTheme(V2_RowTheme theme){
		if(theme.getLeftBorder()==0){
			throw new TableException("row theme incomplete", "no left border defined");
		}
		if(theme.getRightBorder()==0){
			throw new TableException("row theme incomplete", "no right border defined");
		}
		if(theme.getMid()==0){
			throw new TableException("row theme incomplete", "no mid character defined");
		}
		if(theme.getMidBorderAll()==0){
			throw new TableException("row theme incomplete", "no mid all character defined");
		}
		if(theme.getMidBorderDown()==0){
			throw new TableException("row theme incomplete", "no mid down character defined");
		}
		if(theme.getMidBorderUp()==0){
			throw new TableException("row theme incomplete", "no mid up character defined");
		}
		if(theme.getDescription()==null){
			throw new TableException("row theme incomplete", "no description defined");
		}
	}

}
