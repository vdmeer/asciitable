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
 * Simple themes.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4-SNAPSHOT build 150618 (18-Jun-15) for Java 1.8
 */
public enum SimpleThemes implements ThemeSimple {
	/**
	 * A theme for plain ASCII-7 boxes (basically the characters '-', '|' and '+').
	 * <pre>
	 * +-+-+-+-+
	 * | | | | |
	 * +-+-+-+-+
	 * | | | | |
	 * +-+-+-+-+
	 * </pre>
	 */
	PLAIN_7BIT(
			StandardRows.ASC7_SIMPLE,
			StandardRows.ASC7_SIMPLE,
			StandardRows.ASC7_SIMPLE,
			StandardRows.ASC7_SIMPLE_CONTENT,
			"plain ASCII-7 boxes (basically the characters '-', '|' and '+')"
	),

	/**
	 * UTF-8 characters with double lines vertically and horizontally.
	 * <pre>
	 * ╔═════╦═╗
	 * ║ ║ ║ ║ ║
	 * ╠═╩═╬═╦═╣
	 * ║ ║ ║ ║ ║
	 * ╚═╩═════╝
	 * </pre>
	 */
	DOUBLE(
			StandardRows.UTF_DOUBLE_TOP,
			StandardRows.UTF_DOUBLE_MID,
			StandardRows.UTF_DOUBLE_BOTTOM,
			StandardRows.UTF_DOUBLE_CONTENT,
			"UTF-8 characters with double lines vertically and horizontally"
	),
	;

	ThemeRow top;

	ThemeRow mid;

	ThemeRow bottom;

	ThemeRow content;

	String description;

	SimpleThemes(ThemeRow top, ThemeRow mid, ThemeRow bottom, ThemeRow content, String description){
		this.top = top;
		this.mid = mid;
		this.bottom = bottom;

		this.content = content;
		this.description = description;
	}

	@Override
	public ThemeRow getTop() {
		return this.top;
	}

	@Override
	public ThemeRow getMid() {
		return this.mid;
	}

	@Override
	public ThemeRow getBottom() {
		return this.bottom;
	}

	@Override
	public ThemeRow getContent() {
		return this.content;
	}

	@Override
	public Object getDescription() {
		return this.description;
	}
}
