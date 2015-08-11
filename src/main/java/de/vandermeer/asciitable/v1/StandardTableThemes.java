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
 * Predefined render themes for the ASCII table.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.7 build 150811 (11-Aug-15) for Java 1.7
 * @since      v0.0.1
 */
public enum StandardTableThemes implements TableTheme {

	/**
	 * A theme for plain ASCII-7 boxes (basically the characters '-', '|' and '+').
	 * <pre>
	 * +----+----+----+
	 * |    |    |    |
	 * +----+----+----+
	 * |    |    |    |
	 * +----+----+----+
	 * </pre>
	 */
	PLAIN_7BIT(
			"plain ASCII-7 boxes (basically the characters '-', '|' and '+')",
			new char[]{'+', '+', '+', '-', '+', '+', '+', '|', '+', '+', '+'}
	),

	/**
	 * UTF-8 characters with double lines vertically and horizontally.
	 * <pre>
	 * ╔════╦════╦════╗
	 * ║    ║    ║    ║
	 * ╠════╬════╬════╣
	 * ║    ║    ║    ║
	 * ╚════╩════╩════╝
	 * </pre>
	 */
	DOUBLE(
			"UTF-8 characters with double lines vertically and horizontally",
			new char[]{
					CharactersBoxDrawing.DOUBLE_DOWN_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_DOWN_AND_LEFT.utf,
					CharactersBoxDrawing.DOUBLE_DOWN_AND_RIGHT.utf,
					CharactersBoxDrawing.DOUBLE_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_UP_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_UP_AND_LEFT.utf,
					CharactersBoxDrawing.DOUBLE_UP_AND_RIGHT.utf,
					CharactersBoxDrawing.DOUBLE_VERTICAL.utf,
					CharactersBoxDrawing.DOUBLE_VERTICAL_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_VERTICAL_AND_LEFT.utf,
					CharactersBoxDrawing.DOUBLE_VERTICAL_AND_RIGHT.utf
			}
	),

	/**
	 * UTF-8 characters with double lines vertically and light (single) lines horizontally.
	 * <pre>
	 * ╓────╥────╥────╖
	 * ║    ║    ║    ║
	 * ╟────╫────╫────╢
	 * ║    ║    ║    ║
	 * ╙────╨────╨────╜
	 * </pre>
	 */
	DOUBLE_LIGHT(
			"UTF-8 characters with double lines vertically and light (single) lines horizontally",
			new char[]{
					CharactersBoxDrawing.DOUBLE_LIGHT_DOWN_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_LIGHT_DOWN_AND_LEFT.utf,
					CharactersBoxDrawing.DOUBLE_LIGHT_DOWN_AND_RIGHT.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_LIGHT_UP_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_LIGHT_UP_AND_LEFT.utf,
					CharactersBoxDrawing.DOUBLE_LIGHT_UP_AND_RIGHT.utf,
					CharactersBoxDrawing.DOUBLE_VERTICAL.utf,
					CharactersBoxDrawing.DOUBLE_LIGHT_VERTICAL_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_LIGHT_VERTICAL_AND_LEFT.utf,
					CharactersBoxDrawing.DOUBLE_LIGHT_VERTICAL_AND_RIGHT.utf
			}
	),

	/**
	 * UTF-8 characters with heavy (thick) lines vertically and horizontally
	 * <pre>
	 * ┏━┳━┳━┓
	 * ┃    ┃    ┃    ┃
	 * ┣━╋━╋━┫
	 * ┃    ┃    ┃    ┃
	 * ┗━┻━┻━┛
	 * </pre>
	 */
	HEAVY(
			"UTF-8 characters with heavy (thick) lines vertically and horizontally",
			new char[]{
					CharactersBoxDrawing.HEAVY_DOWN_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_DOWN_AND_LEFT.utf,
					CharactersBoxDrawing.HEAVY_DOWN_AND_RIGHT.utf,
					CharactersBoxDrawing.HEAVY_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_UP_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_UP_AND_LEFT.utf,
					CharactersBoxDrawing.HEAVY_UP_AND_RIGHT.utf,
					CharactersBoxDrawing.HEAVY_VERTICAL.utf,
					CharactersBoxDrawing.HEAVY_VERTICAL_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_VERTICAL_AND_LEFT.utf,
					CharactersBoxDrawing.HEAVY_VERTICAL_AND_RIGHT.utf
			}
	),

	/**
	 * UTF-8 characters with heavy (thick) lines vertically and horizontally.
	 */
	HEAVY_LIGHT(
			"UTF-8 characters with heavy (thick) lines vertically and horizontally",
			new char[]{
					CharactersBoxDrawing.HEAVY_LIGHT_DOWN_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_LIGHT_DOWN_AND_LEFT.utf,
					CharactersBoxDrawing.HEAVY_LIGHT_DOWN_AND_RIGHT.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_LIGHT_UP_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_LIGHT_UP_AND_LEFT.utf,
					CharactersBoxDrawing.HEAVY_LIGHT_UP_AND_RIGHT.utf,
					CharactersBoxDrawing.HEAVY_VERTICAL.utf,
					CharactersBoxDrawing.HEAVY_LIGHT_VERTICAL_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_LIGHT_VERTICAL_AND_LEFT.utf,
					CharactersBoxDrawing.HEAVY_LIGHT_VERTICAL_AND_RIGHT.utf
			}
	),

	/**
	 * UTF-8 characters with light (single) lines vertically and horizontally.
	 * <pre>
	 * ┌────┬────┬────┐
	 * │    │    │    │
	 * ├────┼────┼────┤
	 * │    │    │    │
	 * └────┴────┴────┘
	 * </pre>
	 */
	LIGHT(
			"UTF-8 characters with light (single) lines vertically and horizontally",
			new char[]{
					CharactersBoxDrawing.LIGHT_DOWN_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_DOWN_AND_LEFT.utf,
					CharactersBoxDrawing.LIGHT_DOWN_AND_RIGHT.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_UP_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_UP_AND_LEFT.utf,
					CharactersBoxDrawing.LIGHT_UP_AND_RIGHT.utf,
					CharactersBoxDrawing.LIGHT_VERTICAL.utf,
					CharactersBoxDrawing.LIGHT_VERTICAL_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_VERTICAL_AND_LEFT.utf,
					CharactersBoxDrawing.LIGHT_VERTICAL_AND_RIGHT.utf
			}
	),

	/**
	 * UTF-8 characters with light (single) lines vertically and double lines horizontally
	 * <pre>
	 * ╒════╤════╤════╕
	 * │    │    │    │
	 * ╞════╪════╪════╡
	 * │    │    │    │
	 * ╘════╧════╧════╛
	 * </pre>
	 */
	LIGHT_DOUBLE(
			"UTF-8 characters with light (single) lines vertically and double lines horizontally",
			new char[]{
					CharactersBoxDrawing.LIGHT_DOUBLE_DOWN_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_DOUBLE_DOWN_AND_LEFT.utf,
					CharactersBoxDrawing.LIGHT_DOUBLE_DOWN_AND_RIGHT.utf,
					CharactersBoxDrawing.DOUBLE_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_DOUBLE_UP_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_DOUBLE_UP_AND_LEFT.utf,
					CharactersBoxDrawing.LIGHT_DOUBLE_UP_AND_RIGHT.utf,
					CharactersBoxDrawing.LIGHT_VERTICAL.utf,
					CharactersBoxDrawing.LIGHT_DOUBLE_VERTICAL_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_DOUBLE_VERTICAL_AND_LEFT.utf,
					CharactersBoxDrawing.LIGHT_DOUBLE_VERTICAL_AND_RIGHT.utf
			}
	),

	/**
	 * UTF-8 characters with light (single) lines vertically and heavy lines horizontally
	 */
	LIGHT_HEAVY(
			"UTF-8 characters with light (single) lines vertically and heavy lines horizontally",
			new char[]{
					CharactersBoxDrawing.LIGHT_HEAVY_DOWN_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_HEAVY_DOWN_AND_LEFT.utf,
					CharactersBoxDrawing.LIGHT_HEAVY_DOWN_AND_RIGHT.utf,
					CharactersBoxDrawing.HEAVY_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_HEAVY_UP_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_HEAVY_UP_AND_LEFT.utf,
					CharactersBoxDrawing.LIGHT_HEAVY_UP_AND_RIGHT.utf,
					CharactersBoxDrawing.LIGHT_VERTICAL.utf,
					CharactersBoxDrawing.LIGHT_HEAVY_VERTICAL_AND_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_HEAVY_VERTICAL_AND_LEFT.utf,
					CharactersBoxDrawing.LIGHT_HEAVY_VERTICAL_AND_RIGHT.utf
			}
	),

	/**
	 * LaTeX style plain plain ASCII-7 boxes (basically the characters '-' and ' ').
	 * <pre>
	 * ----------------
	 *                 
	 * ----------------
	 *                 
	 * ----------------
	 * </pre>
	 */
	LATEX_7BIT(
			"LaTeX style plain ASCII-7 boxes (basically the characters '-' and ' ')",
			new char[]{'-', '-', '-', '-', '-', '-', '-', ' ', '-', '-', '-'}
	),

	/**
	 * LaTeX style UTF-8 characters with double lines horizontally.
	 * <pre>
	 * ════════════════
	 *                 
	 * ════════════════
	 *                 
	 * ════════════════
	 * </pre>
	 */
	LATEX_DOUBLE(
			"LaTeX style UTF-8 characters with double lines horizontally",
			new char[]{
					CharactersBoxDrawing.DOUBLE_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_HORIZONTAL.utf,
					' ',
					CharactersBoxDrawing.DOUBLE_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_HORIZONTAL.utf,
					CharactersBoxDrawing.DOUBLE_HORIZONTAL.utf,
			}
	),

	/**
	 * LaTeX style  UTF-8 characters with heavy (thick) lines horizontally
	 * <pre>
	 * ━━━━━━━
	 *             
	 * ━━━━━━━
	 *             
	 * ━━━━━━━
	 * </pre>
	 */
	LATEX_HEAVY(
			"LaTeX style UTF-8 characters with heavy (thick) lines horizontally",
			new char[]{
					CharactersBoxDrawing.HEAVY_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_HORIZONTAL.utf,
					' ',
					CharactersBoxDrawing.HEAVY_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_HORIZONTAL.utf,
					CharactersBoxDrawing.HEAVY_HORIZONTAL.utf
			}
	),

	/**
	 * LaTeX style UTF-8 characters with light (single) lines horizontally.
	 * <pre>
	 * ────────────────
	 *                 
	 * ────────────────
	 *                 
	 * ────────────────
	 * </pre>
	 */
	LATEX_LIGHT(
			"LaTeX style UTF-8 characters with light (single) lines horizontally",
			new char[]{
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf,
					' ',
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL.utf
			}
	),

	/**
	 * LaTeX style UTF-8 characters with light double dash lines horizontally.
	 * <pre>
	 * ────────────────
	 *                 
	 * ────────────────
	 *                 
	 * ────────────────
	 * </pre>
	 */
	LATEX_LIGHT_DOUBLE_DASH(
			"LaTeX style UTF-8 characters with light double dash lines horizontally",
			new char[]{
					CharactersBoxDrawing.LIGHT_HORIZONTAL_DOUBLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_DOUBLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_DOUBLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_DOUBLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_DOUBLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_DOUBLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_DOUBLE_DASH.utf,
					' ',
					CharactersBoxDrawing.LIGHT_HORIZONTAL_DOUBLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_DOUBLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_DOUBLE_DASH.utf
			}
	),

	/**
	 * LaTeX style UTF-8 characters with light triple dash lines horizontally.
	 * <pre>
	 * ────────────────
	 *                 
	 * ────────────────
	 *                 
	 * ────────────────
	 * </pre>
	 */
	LATEX_LIGHT_TRIPLE_DASH(
			"LaTeX style UTF-8 characters with light triple dash lines horizontally",
			new char[]{
					CharactersBoxDrawing.LIGHT_HORIZONTAL_TRIPLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_TRIPLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_TRIPLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_TRIPLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_TRIPLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_TRIPLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_TRIPLE_DASH.utf,
					' ',
					CharactersBoxDrawing.LIGHT_HORIZONTAL_TRIPLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_TRIPLE_DASH.utf,
					CharactersBoxDrawing.LIGHT_HORIZONTAL_TRIPLE_DASH.utf
			}
	),

	;

	/** Option description */
	String description;

	/** Option value, the actual theme */
	char[] theme;

	StandardTableThemes(String description, char[] theme){
		this.description=description;
		this.theme=theme;
	}

	@Override
	public Object getDescription() {
		return this.description;
	}

	@Override
	public char[] getTheme() {
		return this.theme;
	}
}
