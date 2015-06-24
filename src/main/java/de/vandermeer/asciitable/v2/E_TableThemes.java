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

/**
 * Standard table themes.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4 build 150619 (19-Jun-15) for Java 1.8
 */
public enum E_TableThemes {

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
			E_RowThemes.ASC7_SIMPLE,
			E_RowThemes.ASC7_SIMPLE,
			E_RowThemes.ASC7_SIMPLE,
			E_RowThemes.ASC7_SIMPLE_CONTENT,
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
	UTF_DOUBLE(
			E_RowThemes.UTF_DOUBLE_TOP,
			E_RowThemes.UTF_DOUBLE_MID,
			E_RowThemes.UTF_DOUBLE_BOTTOM,
			E_RowThemes.UTF_DOUBLE_CONTENT,
			"UTF-8 characters with double lines vertically and horizontally"
	),

	/**
	 * UTF-8 double lines vertically and light (single) lines horizontally.
	 * <pre>
	 * ╓─────╥─╖
	 * ║ ║ ║ ║ ║
	 * ╟─╨─╫─╥─╢
	 * ║ ║ ║ ║ ║
	 * ╙─╨─────╜
	 * </pre>
	 */
	UTF_DOUBLE_LIGHT(
			E_RowThemes.UTF_DOUBLE_LIGHT_TOP,
			E_RowThemes.UTF_DOUBLE_LIGHT_MID,
			E_RowThemes.UTF_DOUBLE_LIGHT_BOTTOM,
			E_RowThemes.UTF_DOUBLE_LIGHT_CONTENT,
			"UTF-8 double lines vertically and light (single) lines horizontally"
	),

	/**
	 * UTF-8 light (single) lines vertically and horizontally.
	 * <pre>
	 * ┌─────┬─┐
	 * │ │ │ │ │
	 * ├─┴─┼─┬─┤
	 * │ │ │ │ │
	 * └─┴─────┘
	 * </pre>
	 */
	UTF_LIGHT(
			E_RowThemes.UTF_LIGHT_TOP,
			E_RowThemes.UTF_LIGHT_MID,
			E_RowThemes.UTF_LIGHT_BOTTOM,
			E_RowThemes.UTF_LIGHT_CONTENT,
			"UTF-8 light (single) lines vertically and horizontally"
	),

	/**
	 * UTF-8 light (single) lines vertically and double lines horizontally.
	 * <pre>
	 * ╒═════╤═╕
	 * │ │ │ │ │
	 * ╞═╧═╪═╤═╡
	 * │ │ │ │ │
	 * ╘═╧═════╛
	 * </pre>
	 */
	UTF_LIGHT_DOUBLE(
			E_RowThemes.UTF_LIGHT_DOUBLE_TOP,
			E_RowThemes.UTF_LIGHT_DOUBLE_MID,
			E_RowThemes.UTF_LIGHT_DOUBLE_BOTTOM,
			E_RowThemes.UTF_LIGHT_DOUBLE_CONTENT,
			"UTF-8 light (single) lines vertically and double lines horizontally"
	),

	/**
	 * UTF-8 with heavy (thick) lines vertically and horizontally.
	 * <pre>
	 * ┏━━━━━┳━┓
	 * ┃ ┃ ┃ ┃ ┃
	 * ┣━┻━╋━┳━┫
	 * ┃ ┃ ┃ ┃ ┃
	 * ┗━┻━━━━━┛
	 * </pre>
	 */
	UTF_HEAVY(
			E_RowThemes.UTF_HEAVY_TOP,
			E_RowThemes.UTF_HEAVY_MID,
			E_RowThemes.UTF_HEAVY_BOTTOM,
			E_RowThemes.UTF_HEAVY_CONTENT,
			"UTF-8 with heavy (thick) lines vertically and horizontally"
	),

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
	LATEX_7BIT_STRONG (
			E_RowThemes.ASC7_SIMPLE,
			E_RowThemes.ASC7_LINE_EQUAL,
			E_RowThemes.ASC7_SIMPLE,
			E_RowThemes.ASC7_LINE_EQUAL,
			E_RowThemes.ASC7_SIMPLE,
			E_RowThemes.ASC7_LINE_EQUAL,
			E_RowThemes.ASC7_SIMPLE_CONTENT,
			"LaTeX style ASCII-7 theme with top and mid rules"
	),

	;

	/** Local builder. */
	TableThemeBuilder builder = new TableThemeBuilder();

	private E_TableThemes(E_RowThemes top, E_RowThemes mid, E_RowThemes bottom, E_RowThemes content, String description){
		this.builder
			.setDescription(description)
			.setContent(content.get())
			.setTop(top.get())
			.setTopStrong(top.get())
			.setMid(mid.get())
			.setMidStrong(mid.get())
			.setBottom(bottom.get())
			.setBottomStrong(bottom.get())
		;
		TableTheme.testTheme(this.builder.build());
	}

	private E_TableThemes(E_RowThemes top, E_RowThemes topStrong, E_RowThemes mid, E_RowThemes midStrong, E_RowThemes bottom, E_RowThemes bottomStrong, E_RowThemes content, String description){
		this.builder
			.setDescription(description)
			.setContent(content.get())
			.setTop(top.get())
			.setTopStrong(topStrong.get())
			.setMid(mid.get())
			.setMidStrong(midStrong.get())
			.setBottom(bottom.get())
			.setBottomStrong(bottomStrong.get())
		;
		TableTheme.testTheme(this.builder.build());
	}

	public TableTheme get(){
		return this.builder.build();
	}
}
