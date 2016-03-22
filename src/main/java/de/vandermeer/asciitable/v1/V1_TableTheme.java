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

package de.vandermeer.asciitable.v1;

/**
 * Interface for themes of table borders.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.6-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public interface V1_TableTheme {

	/**
	 * Returns the table border characters as an array.
	 * The characters in the array have the following order:
	 *  * Each theme method returns an array with the following order for characters:
	 * <ul>
	 * 		<li>0  - DOWN_AND_HORIZONTAL</li>
	 * 		<li>1  - DOWN_AND_LEFT</li>
	 * 		<li>2  - DOWN_AND_RIGHT</li>
	 * 		<li>3  - HORIZONTAL</li>
	 * 		<li>4  - UP_AND_HORIZONTAL</li>
	 * 		<li>5  - UP_AND_LEFT</li>
	 * 		<li>6  - UP_AND_RIGHT</li>
	 * 		<li>7  - VERTICAL</li>
	 * 		<li>8  - VERTICAL_AND_HORIZONTAL</li>
	 * 		<li>9  - VERTICAL_AND_LEFT</li>
	 * 		<li>10 - VERTICAL_AND_RIGHT</li>
	 * </ul>
	 * @return array with table border characters
	 */
	char[] getTheme();

	/**
	 * The theme's description.
	 * @return theme description
	 */
	Object getDescription();

	/** Constant to access a down+horizontal character from theme array. */
	static final int DOWN_AND_HORIZONTAL = 0;

	/** Constant to access a down+left character from theme array. */
	static final int DOWN_AND_LEFT = 1;

	/** Constant to access a down+right character from theme array. */
	static final int DOWN_AND_RIGHT = 2;

	/** Constant to access a horizontal character from theme array. */
	static final int HORIZONTAL=  3;

	/** Constant to access a up+horizontal character from theme array. */
	static final int UP_AND_HORIZONTAL = 4;

	/** Constant to access a up+left character from theme array. */
	static final int UP_AND_LEFT = 5;

	/** Constant to access a up+right character from theme array. */
	static final int UP_AND_RIGHT = 6;

	/** Constant to access a vertical character from theme array. */
	static final int VERTICAL = 7;

	/** Constant to access a vertical+horizontal character from theme array. */
	static final int VERTICAL_AND_HORIZONTAL = 8;

	/** Constant to access a vertical+left character from theme array. */
	static final int VERTICAL_AND_LEFT = 9;

	/** Constant to access a vertical+right character from theme array. */
	static final int VERTICAL_AND_RIGHT = 10;

}
