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
 * Standard table themes.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.5 build 160301 (01-Mar-16) for Java 1.7
 * @since      v0.0.3
 */
public enum V2_E_TableThemes {

	/**
	 * A theme for plain ASCII-7 boxes (basically the characters '-', '|' and '+').
	 * <pre>
Normal           Example
                                 
                  h1   h2   h3   
                                 
                  c1   c2   c3   
                                 
                  c1   c2   c3   
                                 
                  c1   c2   c3   
                                 
	 * </pre>
	 */
	NO_BORDERS(
			V2_E_RowThemes.ASC7_BLANK,
			V2_E_RowThemes.ASC7_BLANK,
			V2_E_RowThemes.ASC7_BLANK,
			V2_E_RowThemes.ASC7_BLANK,
			"blank ASCII-7 boxes (basically the character ' ')"
	),

	/**
	 * A theme for plain ASCII-7 boxes (basically the characters '-', '|' and '+').
	 * <pre>
Normal           Example
+-+-+-+-+        +----+----+----+
| | | | |        |h1  |h2  |h3  |
+-+-+-+-+        +----+----+----+
| | | | |        |c1  |c2  |c3  |
+-+-+-+-+        +----+----+----+
                 |c1  |c2  |c3  |
                 +----+----+----+
                 |c1  |c2  |c3  |
                 +----+----+----+
	 * </pre>
	 */
	PLAIN_7BIT(
			V2_E_RowThemes.ASC7_SIMPLE,
			V2_E_RowThemes.ASC7_SIMPLE,
			V2_E_RowThemes.ASC7_SIMPLE,
			V2_E_RowThemes.ASC7_SIMPLE_CONTENT,
			"plain ASCII-7 boxes (basically the characters '-', '|' and '+')"
	),

	/**
	 * A theme for plain ASCII-7 boxes (basically the characters '-', '|' and '+') for normal rules and '=' lines for strong rules.
	 * <pre>
Normal           Strong           Example
+-+-+-+-+        =========        ================
| | | | |        | | | | |        |h1  |h2  |h3  |
+-+-+-+-+        =========        ================
| | | | |        | | | | |        |c1  |c2  |c3  |
+-+-+-+-+        =========        +----+----+----+
                                  |c1  |c2  |c3  |
                                  +----+----+----+
                                  |c1  |c2  |c3  |
                                  ================
	 * </pre>
	 */
	PLAIN_7BIT_STRONG(
			V2_E_RowThemes.ASC7_SIMPLE,
			V2_E_RowThemes.ASC7_LINE_EQUAL,
			V2_E_RowThemes.ASC7_SIMPLE,
			V2_E_RowThemes.ASC7_LINE_EQUAL,
			V2_E_RowThemes.ASC7_SIMPLE,
			V2_E_RowThemes.ASC7_LINE_EQUAL,
			V2_E_RowThemes.ASC7_SIMPLE_CONTENT,
			"plain ASCII-7 boxes (basically the characters '-', '|' and '+') for normal rules and '=' lines for strong rules"
	),

	/**
	 * A theme for ASCII-7 based LaTeX-style table theme without borders.
	 * <pre>
Normal           Strong           Example
─────────        ≡≡≡≡≡≡≡≡≡        ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡
                                   h1   h2   h3   
─────────        ═════════        ════════════════
                                   c1   c2   c3   
─────────        ≡≡≡≡≡≡≡≡≡        ────────────────
                                   c1   c2   c3   
                                  ────────────────
                                   c1   c2   c3   
                                  ≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡
	 * </pre>
	 */
	ASC7_LATEX_STYLE_STRONG(
			V2_E_RowThemes.UTF_LINE_LIGHT,
			V2_E_RowThemes.ASC7_LINE_CONGRUENCE,
			V2_E_RowThemes.UTF_LINE_LIGHT,
			V2_E_RowThemes.UTF_LINE_DOUBLE,
			V2_E_RowThemes.UTF_LINE_LIGHT,
			V2_E_RowThemes.ASC7_LINE_CONGRUENCE,
			V2_E_RowThemes.ASC7_SIMPLE_CONTENT_BLANK,
			"ASCII-7 based LaTeX-style table theme without borders"
	),

	/**
	 * A theme for ASCII-7 based LaTeX-style table theme without borders.
	 * <pre>
Normal           Strong           Example
─────────        ▀▀▀▀▀▀▀▀▀        ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
                                   h1   h2   h3   
─────────        ═════════        ════════════════
                                   c1   c2   c3   
─────────        ▓▓▓▓▓▓▓▓▓        ────────────────
                                   c1   c2   c3   
                                  ────────────────
                                   c1   c2   c3   
                                  ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
	 * </pre>
	 */
	ASC7_LATEX_STYLE_STRONG2(
			V2_E_RowThemes.UTF_LINE_LIGHT,
			V2_E_RowThemes.ASC7_LINE_UPPER_HALF_BLOCK,
			V2_E_RowThemes.UTF_LINE_LIGHT,
			V2_E_RowThemes.UTF_LINE_DOUBLE,
			V2_E_RowThemes.UTF_LINE_LIGHT,
			V2_E_RowThemes.ASC7_LINE_HIGH_DENSITY_DOTTED,
			V2_E_RowThemes.ASC7_SIMPLE_CONTENT_BLANK,
			"ASCII-7 based LaTeX-style table theme without borders"
	),

	/**
	 * A them with UTF-8 characters with double lines vertically and horizontally.
	 * <pre>
Normal           Example
╔═════╦═╗        ╔════╦════╦════╗
║ ║ ║ ║ ║        ║h1  ║h2  ║h3  ║
╠═╩═╬═╦═╣        ╠════╬════╬════╣
║ ║ ║ ║ ║        ║c1  ║c2  ║c3  ║
╚═╩═════╝        ╠════╬════╬════╣
                 ║c1  ║c2  ║c3  ║
                 ╠════╬════╬════╣
                 ║c1  ║c2  ║c3  ║
                 ╚════╩════╩════╝
	 * </pre>
	 */
	UTF_DOUBLE(
			V2_E_RowThemes.UTF_DOUBLE_TOP,
			V2_E_RowThemes.UTF_DOUBLE_MID,
			V2_E_RowThemes.UTF_DOUBLE_BOTTOM,
			V2_E_RowThemes.UTF_DOUBLE_CONTENT,
			"UTF-8 characters with double lines vertically and horizontally"
	),

	/**
	 * A them with UTF-8 double lines vertically and light (single) lines horizontally.
	 * <pre>
Normal           Example
╓─────╥─╖        ╓────╥────╥────╖
║ ║ ║ ║ ║        ║h1  ║h2  ║h3  ║
╟─╨─╫─╥─╢        ╟────╫────╫────╢
║ ║ ║ ║ ║        ║c1  ║c2  ║c3  ║
╙─╨─────╜        ╟────╫────╫────╢
                 ║c1  ║c2  ║c3  ║
                 ╟────╫────╫────╢
                 ║c1  ║c2  ║c3  ║
                 ╙────╨────╨────╜
	 * </pre>
	 */
	UTF_DOUBLE_LIGHT(
			V2_E_RowThemes.UTF_DOUBLE_LIGHT_TOP,
			V2_E_RowThemes.UTF_DOUBLE_LIGHT_MID,
			V2_E_RowThemes.UTF_DOUBLE_LIGHT_BOTTOM,
			V2_E_RowThemes.UTF_DOUBLE_LIGHT_CONTENT,
			"UTF-8 double lines vertically and light (single) lines horizontally"
	),

	/**
	 * A them with UTF-8 light (single) lines vertically and horizontally.
	 * <pre>
Normal           Example
┌─────┬─┐        ┌────┬────┬────┐
│ │ │ │ │        │h1  │h2  │h3  │
├─┴─┼─┬─┤        ├────┼────┼────┤
│ │ │ │ │        │c1  │c2  │c3  │
└─┴─────┘        ├────┼────┼────┤
                 │c1  │c2  │c3  │
                 ├────┼────┼────┤
                 │c1  │c2  │c3  │
                 └────┴────┴────┘
	 * </pre>
	 */
	UTF_LIGHT(
			V2_E_RowThemes.UTF_LIGHT_TOP,
			V2_E_RowThemes.UTF_LIGHT_MID,
			V2_E_RowThemes.UTF_LIGHT_BOTTOM,
			V2_E_RowThemes.UTF_LIGHT_CONTENT,
			"UTF-8 light (single) lines vertically and horizontally"
	),

	/**
	 * A them with UTF-8 light (single) lines vertically and double lines horizontally.
	 * <pre>
Normal           Example
╒═════╤═╕        ╒════╤════╤════╕
│ │ │ │ │        │h1  │h2  │h3  │
╞═╧═╪═╤═╡        ╞════╪════╪════╡
│ │ │ │ │        │c1  │c2  │c3  │
╘═╧═════╛        ╞════╪════╪════╡
                 │c1  │c2  │c3  │
                 ╞════╪════╪════╡
                 │c1  │c2  │c3  │
                 ╘════╧════╧════╛
	 * </pre>
	 */
	UTF_LIGHT_DOUBLE(
			V2_E_RowThemes.UTF_LIGHT_DOUBLE_TOP,
			V2_E_RowThemes.UTF_LIGHT_DOUBLE_MID,
			V2_E_RowThemes.UTF_LIGHT_DOUBLE_BOTTOM,
			V2_E_RowThemes.UTF_LIGHT_DOUBLE_CONTENT,
			"UTF-8 light (single) lines vertically and double lines horizontally"
	),

	/**
	 * A them with UTF-8 light (single) lines for normal rules and UTF-8 double lines for strong rules.
	 * <pre>
Normal           Strong           Example
┌─────┬─┐        ╒═════╤═╕        ╒════╤════╤════╕
│ │ │ │ │        │ │ │ │ │        │h1  │h2  │h3  │
├─┴─┼─┬─┤        ╞═╧═╪═╤═╡        ╞════╪════╪════╡
│ │ │ │ │        │ │ │ │ │        │c1  │c2  │c3  │
└─┴─────┘        ╘═╧═════╛        ├────┼────┼────┤
                                  │c1  │c2  │c3  │
                                  ├────┼────┼────┤
                                  │c1  │c2  │c3  │
                                  ╘════╧════╧════╛
	 * </pre>
	 */
	UTF_STRONG_DOUBLE(
			V2_E_RowThemes.UTF_LIGHT_TOP,
			V2_E_RowThemes.UTF_LIGHT_DOUBLE_TOP,
			V2_E_RowThemes.UTF_LIGHT_MID,
			V2_E_RowThemes.UTF_LIGHT_DOUBLE_MID,
			V2_E_RowThemes.UTF_LIGHT_BOTTOM,
			V2_E_RowThemes.UTF_LIGHT_DOUBLE_BOTTOM,
			V2_E_RowThemes.UTF_LIGHT_CONTENT,
			"UTF-8 light (single) lines for normal rules and UTF-8 double lines for strong rules"
	),

	/**
	 * A them with UTF-8 with heavy (thick) lines vertically and horizontally.
	 * <pre>
Normal           Example
┏━━━━━┳━┓        ┏━━━━┳━━━━┳━━━━┓
┃ ┃ ┃ ┃ ┃        ┃h1  ┃h2  ┃h3  ┃
┣━┻━╋━┳━┫        ┣━━━━╋━━━━╋━━━━┫
┃ ┃ ┃ ┃ ┃        ┃c1  ┃c2  ┃c3  ┃
┗━┻━━━━━┛        ┣━━━━╋━━━━╋━━━━┫
                 ┃c1  ┃c2  ┃c3  ┃
                 ┣━━━━╋━━━━╋━━━━┫
                 ┃c1  ┃c2  ┃c3  ┃
                 ┗━━━━┻━━━━┻━━━━┛
	 * </pre>
	 */
	UTF_HEAVY(
			V2_E_RowThemes.UTF_HEAVY_TOP,
			V2_E_RowThemes.UTF_HEAVY_MID,
			V2_E_RowThemes.UTF_HEAVY_BOTTOM,
			V2_E_RowThemes.UTF_HEAVY_CONTENT,
			"UTF-8 with heavy (thick) lines vertically and horizontally"
	),

	;

	/** Local theme. */
	V2_TableTheme theme;

	/**
	 * Creates a new table theme.
	 * @param top top rule theme (same for strong)
	 * @param mid mid rule theme (same for strong)
	 * @param bottom bottom rule theme (same for strong)
	 * @param content content theme
	 * @param description a description
	 */
	V2_E_TableThemes(V2_E_RowThemes top, V2_E_RowThemes mid, V2_E_RowThemes bottom, V2_E_RowThemes content, String description){
		this.theme = new AbstractTableTheme(top.get(), mid.get(), bottom.get(), content.get(), description);
		ThemeValidator.validateTableTheme(this.theme);
	}

	/**
	 * Creates a new table theme.
	 * @param top top rule theme
	 * @param topStrong top strong rule theme
	 * @param mid mid rule theme
	 * @param midStrong mid strong rule theme
	 * @param bottom bottom rule theme
	 * @param bottomStrong bottom strong rule theme
	 * @param content content theme
	 * @param description a description
	 */
	V2_E_TableThemes(V2_E_RowThemes top, V2_E_RowThemes topStrong, V2_E_RowThemes mid, V2_E_RowThemes midStrong, V2_E_RowThemes bottom, V2_E_RowThemes bottomStrong, V2_E_RowThemes content, String description){
		this.theme = new AbstractTableTheme(top.get(), topStrong.get(), mid.get(), midStrong.get(), bottom.get(), bottomStrong.get(), content.get(), description);
		ThemeValidator.validateTableTheme(this.theme);
	}

	/**
	 * Returns the actual theme.
	 * @return the actual theme
	 */
	public V2_TableTheme get(){
		return this.theme;
	}

}
