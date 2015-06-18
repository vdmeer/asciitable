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
 * Advanced themes.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4-SNAPSHOT build 150618 (18-Jun-15) for Java 1.8
 */
public enum TBThemes implements ThemeTB {

	/**
	 * LaTeX style ASCII-7 theme with top and mid rules.
	 * <pre>
	 * ================        ================
	 * |    |    |    |                        
	 * ================        ================
	 * |    |    |    |                        
	 * +----+----+----+        ----------------
	 * |    |    |    |                        
	 * +----+----+----+        ----------------
	 * </pre>
	 */
	LATEX_7BIT_EMPH (
			ThemeRow.create('+', '+', '+', '+', '+', '-'),
			ThemeRow.create('=', '=', '=', '=', '=', '='),

			ThemeRow.create('+', '+', '+', '+', '+', '-'),
			ThemeRow.create('=', '=', '=', '=', '=', '='),

			ThemeRow.create('+', '+', '+', '+', '+', '-'),
			ThemeRow.create('=', '=', '=', '=', '=', '='),

			ThemeRow.create('|', '|', '|', '|', '|', ' '),
			"LaTeX style ASCII-7 theme with top and mid rules"
	),
	;

	ThemeRow top;

	ThemeRow topEmph;

	ThemeRow mid;

	ThemeRow midEmph;

	ThemeRow bottom;

	ThemeRow bottomEmph;

	ThemeRow content;

	String description;

	TBThemes(ThemeRow top, ThemeRow topEmph, ThemeRow mid, ThemeRow midEmph, ThemeRow bottom, ThemeRow bottomEmph, ThemeRow content, String description){
		this.top = top;
		this.topEmph = topEmph;

		this.mid = mid;
		this.midEmph = midEmph;

		this.bottom = bottom;
		this.bottomEmph = bottomEmph;

		this.content = content;
		this.description = description;
	}

	@Override
	public ThemeRow getTop() {
		return this.top;
	}

	@Override
	public ThemeRow getTopEmph() {
		return this.topEmph;
	}

	@Override
	public ThemeRow getMid() {
		return this.mid;
	}

	@Override
	public ThemeRow getMidEmph() {
		return this.midEmph;
	}

	@Override
	public ThemeRow getBottom() {
		return this.bottom;
	}

	@Override
	public ThemeRow getBottomEmph() {
		return this.bottomEmph;
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
