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
 * Standard rows.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 141210 (10-Dec-14) for Java 1.8
 */
public enum StandardRows implements ThemeRow {
	ASC7_SIMPLE				('+', '+', '+', '+', '+', '-', "characters '+' and '-'"),
	ASC7_SIMPLE_CONTENT		('|', '|', '|', '|', '|', ' ', "simple for content"),

	UTF_DOUBLE_TOP		('╔', '═', '═', '╦', '╗', '═', "UTF double characters, top row"),
	UTF_DOUBLE_MID		('╠', '╩', '╬', '╦', '╣', '═', "UTF double characters, mid row"),
	UTF_DOUBLE_BOTTOM	('╚', '╩', '═', '═', '╝', '═', "UTF double characters, bottom row"),
	UTF_DOUBLE_CONTENT	('║', '║', '║', '║', '║', ' ', "UTF double characters, content row"),

	;

	char leftBorder;

	char midBorderUp;

	char midBorderAll;

	char midBorderDown;

	char rightBorder;

	char mid;

	String description;

	StandardRows(char leftBorder, char midBorderUp, char midBorderAll, char midBorderDown, char rightBorder, char mid, String description){
		this.leftBorder = leftBorder;
		this.midBorderUp = midBorderUp;
		this.midBorderAll = midBorderAll;
		this.midBorderDown = midBorderDown;
		this.rightBorder = rightBorder;
		this.mid = mid;
		this.description = description;
	}

	@Override
	public Object getDescription() {
		return this.description;
	}

	@Override
	public char getLeftBorder() {
		return this.leftBorder;
	}

	@Override
	public char getMidBorderAll() {
		return this.midBorderAll;
	}

	@Override
	public char getMidBorderUp() {
		return this.midBorderUp;
	}

	@Override
	public char getMidBorderDown() {
		return this.midBorderDown;
	}

	@Override
	public char getRightBorder() {
		return this.rightBorder;
	}

	@Override
	public char getMid() {
		return this.mid;
	}
}
