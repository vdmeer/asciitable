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
 * Theme row.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4-SNAPSHOT build 150618 (18-Jun-15) for Java 1.8
 */
public interface ThemeRow {

	char getLeftBorder();

	char getMidBorderAll();

	char getMidBorderUp();

	char getMidBorderDown();

	char getRightBorder();

	char getMid();

	Object getDescription();

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

	static ThemeRow create(final char leftBorder, final char midBorderUp, final char midBorderAll, final char midBorderDown, final char rightBorder, final char mid){
		return ThemeRow.create(leftBorder, midBorderUp, midBorderAll, midBorderDown, rightBorder, mid, null);
	}

	static ThemeRow create(final char leftBorder, final char midBorderUp, final char midBorderAll, final char midBorderDown, final char rightBorder, final char mid, final String description){
		return new ThemeRow(){
			@Override
			public char getLeftBorder() {
				return leftBorder;
			}

			@Override
			public char getMidBorderUp() {
				return midBorderUp;
			}

			@Override
			public char getMidBorderAll() {
				return midBorderAll;
			}

			@Override
			public char getMidBorderDown() {
				return midBorderDown;
			}

			@Override
			public char getRightBorder() {
				return rightBorder;
			}

			@Override
			public char getMid() {
				return mid;
			}

			@Override
			public String getDescription(){
				return (description==null)?"created anonymously":description;
			}
		};
	}
}
