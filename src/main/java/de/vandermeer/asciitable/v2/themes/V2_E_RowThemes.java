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
 * Standard row themes for tables.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.5 build 160301 (01-Mar-16) for Java 1.7
 * @since      v0.0.3
 */
public enum V2_E_RowThemes {

	/**
	 * Character ' ' for normal style, no borders.
	 */
	ASC7_BLANK						(' ', ' ', ' ', ' ', ' ', ' ', "character ' ' for a blank line"),

	/**
	 * Characters '+' and '-' for normal style.
	 * <pre>
	+-+-+-+-+
	 * </pre>
	 */
	ASC7_SIMPLE						('+', '+', '+', '+', '+', '-', "characters '+' and '-'"),

	/**
	 * Simple borders for content.
	 * <pre>
	| | | | |
	 * </pre>
	 */
	ASC7_SIMPLE_CONTENT				('|', '|', '|', '|', '|', ' ', "simple borders for content"),

	/**
	 * No borders for content using ' '.
	 */
	ASC7_SIMPLE_CONTENT_BLANK		(' ', ' ', ' ', ' ', ' ', ' ', "no (blank) borders for content"),

	/**
	 * Character '=' line.
	 * <pre>
	=========
	 * </pre>
	 */
	ASC7_LINE_EQUAL					('=', '=', '=', '=', '=', '=', "character '=' line"),

	/**
	 * Character '#' line.
	 * <pre>
	#########
	 * </pre>
	 */
	ASC7_LINE_HASH					('#', '#', '#', '#', '#', '#', "character '#' line"),

	/**
	 * Character '%' line.
	 * <pre>
	%%%%%%%%%
	 * </pre>
	 */
	ASC7_LINE_PERCENT				('%', '%', '%', '%', '%', '%', "character '%' line"),

	/**
	 * Character '&amp;' line.
	 * <pre>
	 * &amp;&amp;&amp;&amp;&amp;&amp;&amp;&amp;&amp;
	 * </pre>
	 */
	ASC7_LINE_AMPERSAND				('&', '&', '&', '&', '&', '&', "character '&' line"),

	/**
	 * Character '*' line.
	 * <pre>
	 * 	*********
	 * </pre>
	 */
	ASC7_LINE_STAR					('*', '*', '*', '*', '*', '*', "character '*' line"),

	/**
	 * Character '+' line.
	 * <pre>
	+++++++++
	 * </pre>
	 */
	ASC7_LINE_PLUS					('+', '+', '+', '+', '+', '+', "character '+' line"),

	/**
	 * Character '-' line.
	 * <pre>
	---------
	 * </pre>
	 */
	ASC7_LINE_MINUS					('-', '-', '-', '-', '-', '-', "character '-' line"),

	/**
	 * Character '&#64;' line.
	 * <pre>
	&#64;&#64;&#64;&#64;&#64;&#64;&#64;&#64;&#64;
	 * </pre>
	 */
	ASC7_LINE_AT					('@', '@', '@', '@', '@', '@', "character '@' line"),

	/**
	 * Character '^' line.
	 * <pre>
	^^^^^^^^^
	 * </pre>
	 */
	ASC7_LINE_CIRCUMFLEX			('^', '^', '^', '^', '^', '^', "character '^' line"),

	/**
	 * Character ':' line.
	 * <pre>
	::::::::::
	 * </pre>
	 */
	ASC7_LINE_COLON					(':', ':', ':', ':', ':', ':', "character ':' line"),

	/**
	 * Character '_' line.
	 * <pre>
	_________
	 * </pre>
	 */
	ASC7_LINE_UNDERSCORE			('_', '_', '_', '_', '_', '_', "character '_' line"),

	/**
	 * Character '~' line.
	 * <pre>
	~~~~~~~~~
	 * </pre>
	 */
	ASC7_LINE_TILDE					('~', '~', '~', '~', '~', '~', "character '~' line"),

	/**
	 * Character 'º' line.
	 * <pre>
	ººººººººº
	 * </pre>
	 */
	ASC7_LINE_SECTION				('º', 'º', 'º', 'º', 'º', 'º', "character 'º' line"),

	/**
	 * Character '.' line.
	 * <pre>
	.........
	 * </pre>
	 */
	ASC7_LINE_DOT					('.', '.', '.', '.', '.', '.', "character '.' line"),

	/**
	 * Character '¤' line.
	 * <pre>
	¤¤¤¤¤¤¤¤¤
	 * </pre>
	 */
	ASC7_LINE_CURRENCY				('¤', '¤', '¤', '¤', '¤', '¤', "character '¤' line"),

	/**
	 * Character '±' line.
	 * <pre>
	±±±±±±±±±
	 * </pre>
	 */
	ASC7_LINE_PLUSMINUS				('±', '±', '±', '±', '±', '±', "character '±' line"),

	/**
	 * Character '÷' line.
	 * <pre>
	÷÷÷÷÷÷÷÷÷
	 * </pre>
	 */
	ASC7_LINE_DIVISION				('÷', '÷', '÷', '÷', '÷', '÷', "character '÷' line"),

	/**
	 * Character '×' line.
	 * <pre>
	×××××××××
	 * </pre>
	 */
	ASC7_LINE_MULT					('×', '×', '×', '×', '×', '×', "character '×' line"),

	/**
	 * Character '°' line.
	 * <pre>
	°°°°°°°°°
	 * </pre>
	 */
	ASC7_LINE_DEGREE				('°', '°', '°', '°', '°', '°', "character '°' line"),

	/**
	 * Character '█' line.
	 * <pre>
	█████████
	 * </pre>
	 */
	ASC7_LINE_FULL_BLOCK			('█', '█', '█', '█', '█', '█', "character '█' line"),

	/**
	 * Character '≈' line.
	 * <pre>
	≈≈≈≈≈≈≈≈≈
	 * </pre>
	 */
	ASC7_LINE_ALMOST_EQUAL			('≈', '≈', '≈', '≈', '≈', '≈', "character '≈' line"),

	/**
	 * Character '▄' line.
	 * <pre>
	▄▄▄▄▄▄▄▄▄
	 * </pre>
	 */
	ASC7_LINE_LOWER_HALF_BLOCK		('▄', '▄', '▄', '▄', '▄', '▄', "character '▄' line"),

	/**
	 * Character '▌' line.
	 * <pre>
	▌▌▌▌▌▌▌▌▌
	 * </pre>
	 */
	ASC7_LINE_LEFT_HALF_BLOCK		('▌', '▌', '▌', '▌', '▌', '▌', "character '▌' line"),

	/**
	 * Character '▐' line.
	 * <pre>
	▐▐▐▐▐▐▐▐▐
	 * </pre>
	 */
	ASC7_LINE_RIGHT_HALF_BLOCK		('▐', '▐', '▐', '▐', '▐', '▐', "character '▐' line"),

	/**
	 * Character '▀' line.
	 * <pre>
	▀▀▀▀▀▀▀▀▀
	 * </pre>
	 */
	ASC7_LINE_UPPER_HALF_BLOCK		('▀', '▀', '▀', '▀', '▀', '▀', "character '▀' line"),

	/**
	 * Character '«' line.
	 * <pre>
	«««««««««
	 * </pre>
	 */
	ASC7_LINE_LPDAQM				('«', '«', '«', '«', '«', '«', "character '«' line"),

	/**
	 * Character '»' line.
	 * <pre>
	»»»»»»»»»
	 * </pre>
	 */
	ASC7_LINE_RPDAQM				('»', '»', '»', '»', '»', '»', "character '»' line"),

	/**
	 * Character '░' line.
	 * <pre>
	░░░░░░░░░
	 * </pre>
	 */
	ASC7_LINE_LOW_DENSITY_DOTTED	('░', '░', '░', '░', '░', '░', "character '░' line"),

	/**
	 * Character '▒' line.
	 * <pre>
	▒▒▒▒▒▒▒▒▒
	 * </pre>
	 */
	ASC7_LINE_MEDIUM_DENSITY_DOTTED	('▒', '▒', '▒', '▒', '▒', '▒', "character '▒' line"),

	/**
	 * Character '▓' line.
	 * <pre>
	▓▓▓▓▓▓▓▓▓
	 * </pre>
	 */
	ASC7_LINE_HIGH_DENSITY_DOTTED	('▓', '▓', '▓', '▓', '▓', '▓', "character '▓' line"),

	/**
	 * Character '¯' line.
	 * <pre>
	¯¯¯¯¯¯¯¯¯
	 * </pre>
	 */
	ASC7_LINE_MACRON				('¯', '¯', '¯', '¯', '¯', '¯', "character '¯' line"),

	/**
	 * Character '≡' line.
	 * <pre>
	≡≡≡≡≡≡≡≡≡
	 * </pre>
	 */
	ASC7_LINE_CONGRUENCE			('≡', '≡', '≡', '≡', '≡', '≡', "character '≡' line"),

	/**
	 * Character '‗' line.
	 * <pre>
	‗‗‗‗‗‗‗‗‗
	 * </pre>
	 */
	ASC7_LINE_UNDERLINE				('‗', '‗', '‗', '‗', '‗', '‗', "character '‗' line"),

	/**
	 * Character '¨' line.
	 * <pre>
	¨¨¨¨¨¨¨¨¨
	 * </pre>
	 */
	ASC7_LINE_DIARESIS				('¨', '¨', '¨', '¨', '¨', '¨', "character '¨' line"),

	/**
	 * Character '·' line.
	 * <pre>
	·········
	 * </pre>
	 */
	ASC7_LINE_MIDDLE_DOT			('·', '·', '·', '·', '·', '·', "character '·' line"),

	/**
	 * Character '■' line.
	 * <pre>
	■■■■■■■■■
	 * </pre>
	 */
	ASC7_LINE_BLACK_SQUARE			('■', '■', '■', '■', '■', '■', "character '■' line"),


	/**
	 * Character '∙' line.
	 * <pre>
	∙∙∙∙∙∙∙∙∙
	 * </pre>
	 */
	ASC7_LINE_BULLET_OP				('∙', '∙', '∙', '∙', '∙', '∙', "character '∙' line"),

	/**
	 * Character '∞' line.
	 * <pre>
	∞∞∞∞∞∞∞∞∞
	 * </pre>
	 */
	ASC7_LINE_INFINITY				('∞', '∞', '∞', '∞', '∞', '∞', "character '∞' line"),

	/**
	 * Character '≥' line.
	 * <pre>
	≥≥≥≥≥≥≥≥≥
	 * </pre>
	 */
	ASC7_LINE_GT					('≥', '≥', '≥', '≥', '≥', '≥', "character '≥' line"),

	/**
	 * Character '≤' line.
	 * <pre>
	≤≤≤≤≤≤≤≤≤
	 * </pre>
	 */
	ASC7_LINE_LT					('≤', '≤', '≤', '≤', '≤', '≤', "character '≤' line"),

	/**
	 * Character '␣' line.
	 * <pre>
	␣␣␣␣␣␣␣␣␣
	 * </pre>
	 */
	UTF8_LINE_VISIBLE_SPACE			('␣', '␣', '␣', '␣', '␣', '␣', "character '␣' line"),

	/**
	 * Character '═' line.
	 * <pre>
	═════════
	 * </pre>
	 */
	UTF_LINE_DOUBLE					('═', '═', '═', '═', '═', '═', "character '═' line"),

	/**
	 * Character '─' line.
	 * <pre>
	─────────
	 * </pre>
	 */
	UTF_LINE_LIGHT					('─', '─', '─', '─', '─', '─', "character '─' line"),

	/**
	 * Character '━' line.
	 * <pre>
	━━━━━━━━━
	 * </pre>
	 */
	UTF_LINE_HEAVY					('━', '━', '━', '━', '━', '━', "character '━' line"),

	/**
	 * Character '╌' line.
	 * <pre>
	╌╌╌╌╌╌╌╌╌
	 * </pre>
	 */
	UTF_LINE_DOUBLE_DASH			('╌', '╌', '╌', '╌', '╌', '╌', "character '╌' line"),

	/**
	 * Character '\u2508' line.
	 * <pre>
	┈┈┈┈┈┈┈┈┈
	 * </pre>
	 */
	UTF_LINE_QUADRUPLE_DASH			('\u2508', '\u2508', '\u2508', '\u2508', '\u2508', '\u2508', "character '\u2508' line"),

	/**
	 * Character '┄' line.
	 * <pre>
	┄┄┄┄┄┄┄┄┄
	 * </pre>
	 */
	UTF_LINE_TRIPLE_DASH			('┄', '┄', '┄', '┄', '┄', '┄', "character '┄' line"),

	/**
	 * Character '╎' line.
	 * <pre>
	╎╎╎╎╎╎╎╎╎
	 * </pre>
	 */
	UTF_LINE_VERTICAL_DOUBLE_DASH	('╎', '╎', '╎', '╎', '╎', '╎', "character '╎' line"),

	/**
	 * Character '┊' line.
	 * <pre>
	┊┊┊┊┊┊┊┊┊
	 * </pre>
	 */
	UTF_LINE_VERTICAL_QUADRUPLE_DASH('┊', '┊', '┊', '┊', '┊', '┊', "character '┊' line"),

	/**
	 * Character '┆' line.
	 * <pre>
	┆┆┆┆┆┆┆┆┆ 
	 * </pre>
	 */
	UTF_LINE_VERTICAL_TRIPLE_DASH	('┆', '┆', '┆', '┆', '┆', '┆', "character '┆' line"),

	/**
	 * UTF-8 double characters for a top rule.
	 * <pre>
	╔═════╦═╗
	 * </pre>
	 */
	UTF_DOUBLE_TOP					('╔', '═', '═', '╦', '╗', '═', "UTF-8 double characters for a top rule"),

	/**
	 * UTF-8 double characters for a mid rule.
	 * <pre>
	╠═╩═╬═╦═╣
	 * </pre>
	 * */
	UTF_DOUBLE_MID					('╠', '╩', '╬', '╦', '╣', '═', "UTF-8 double characters for a mid rule"),

	/**
	 * UTF-8 double characters for a bottom rule.
	 * <pre>
	╚═╩═════╝
	 * </pre>
	 * */
	UTF_DOUBLE_BOTTOM				('╚', '╩', '═', '═', '╝', '═', "UTF-8 double characters for a bottom rule"),

	/**
	 * UTF-8 double characters for a content row.
	 * <pre>
	║ ║ ║ ║ ║
	 * </pre>
	 * */
	UTF_DOUBLE_CONTENT				('║', '║', '║', '║', '║', ' ', "UTF-8 double characters for a content row"),


	/**
	 * UTF-8 double lines vertically and light (single) lines horizontally for top rule.
	 * <pre>
	╓─────╥─╖
	 * </pre>
	 */
	UTF_DOUBLE_LIGHT_TOP			('╓', '─', '─', '╥', '╖', '─', "UTF-8 double lines vertically and light (single) lines horizontally for top rule"),

	/**
	 * UTF-8 double lines vertically and light (single) lines horizontally for mid rule.
	 * <pre>
	╟─╨─╫─╥─╢
	 * </pre>
	 */
	UTF_DOUBLE_LIGHT_MID			('╟', '╨', '╫', '╥', '╢', '─', "UTF-8 double lines vertically and light (single) lines horizontally for mid rule"),

	/**
	 * UTF-8 double lines vertically and light (single) lines horizontally for bottom rule.
	 * <pre>
	╙─╨─────╜
	 * </pre>
	 */
	UTF_DOUBLE_LIGHT_BOTTOM			('╙', '╨', '─', '─', '╜', '─', "UTF-8 double lines vertically and light (single) lines horizontally for bottom rule"),

	/**
	 * UTF-8 double lines vertically and light (single) lines horizontally for content row.
	 * <pre>
	║ ║ ║ ║ ║
	 * </pre>
	 */
	UTF_DOUBLE_LIGHT_CONTENT		('║', '║', '║', '║', '║', ' ', "UTF-8 double lines vertically and light (single) lines horizontally for content row"),

	/**
	 * UTF-8 light (single) lines vertically and horizontally for top rule.
	 * <pre>
	┌─────┬─┐
	 * </pre>
	 */
	UTF_LIGHT_TOP					('┌', '─', '─', '┬', '┐', '─', "UTF-8 light (single) lines vertically and horizontally for top rule"),

	/**
	 * UTF-8 light (single) lines vertically and horizontally for mid rule.
	 * <pre>
	├─┴─┼─┬─┤
	 * </pre>
	 */
	UTF_LIGHT_MID					('├', '┴', '┼', '┬', '┤', '─', "UTF-8 light (single) lines vertically and horizontally for mid rule"),

	/**
	 * UTF-8 light (single) lines vertically and horizontally for bottom rule.
	 * <pre>
	└─┴─────┘
	 * </pre>
	 */
	UTF_LIGHT_BOTTOM				('└', '┴', '─', '─', '┘', '─', "UTF-8 light (single) lines vertically and horizontally for bottom rule"),

	/**
	 * UTF-8 light (single) lines vertically and horizontally for content row.
	 * <pre>
	│ │ │ │ │
	 * </pre>
	 */
	UTF_LIGHT_CONTENT				('│', '│', '│', '│', '│', ' ', "UTF-8 light (single) lines vertically and horizontally for content row"),


	/**
	 * UTF-8 light (single) lines vertically and double lines horizontally for top rule.
	 * <pre>
	╒═════╤═╕
	 * </pre>
	 */
	UTF_LIGHT_DOUBLE_TOP			('╒', '═', '═', '╤', '╕', '═', "UTF-8 light (single) lines vertically and double lines horizontally for top rule"),

	/**
	 * UTF-8 light (single) lines vertically and double lines horizontally for mid rule.
	 * <pre>
	╞═╧═╪═╤═╡
	 * </pre>
	 */
	UTF_LIGHT_DOUBLE_MID			('╞', '╧', '╪', '╤', '╡', '═', "UTF-8 light (single) lines vertically and double lines horizontally for mid rule"),
	/**
	 * UTF-8 light (single) lines vertically and double lines horizontally for bottom rule.
	 * <pre>
	╘═╧═════╛
	 * </pre>
	 */
	UTF_LIGHT_DOUBLE_BOTTOM			('╘', '╧', '═', '═', '╛', '═', "UTF-8 light (single) lines vertically and double lines horizontally for bottom rule"),

	/**
	 * UTF-8 light (single) lines vertically and double lines horizontally for content row.
	 * <pre>
	│ │ │ │ │
	 * </pre>
	 */
	UTF_LIGHT_DOUBLE_CONTENT		('│', '│', '│', '│', '│', ' ', "UTF-8 light (single) lines vertically and double lines horizontally for content row"),

	/**
	 * UTF-8 with heavy (thick) lines vertically and horizontally for top rule.
	 * <pre>
	┏━━━━━┳━┓
	 * </pre>
	 */
	UTF_HEAVY_TOP					('┏', '━', '━', '┳', '┓', '━', "UTF-8 with heavy (thick) lines vertically and horizontally for top rule"),

	/**
	 * UTF-8 with heavy (thick) lines vertically and horizontally for mid rule.
	 * <pre>
	┣━┻━╋━┳━┫
	 * </pre>
	 */
	UTF_HEAVY_MID					('┣', '┻', '╋', '┳', '┫', '━', "UTF-8 with heavy (thick) lines vertically and horizontally for mid rule"),

	/**
	 * UTF-8 with heavy (thick) lines vertically and horizontally for bottom rule.
	 * <pre>
	┗━┻━━━━━┛
	 * </pre>
	 */
	UTF_HEAVY_BOTTOM				('┗', '┻', '━', '━', '┛', '━', "UTF-8 with heavy (thick) lines vertically and horizontally for bottom rule"),

	/**
	 * UTF-8 with heavy (thick) lines vertically and horizontally for content row.
	 * <pre>
	┃ ┃ ┃ ┃ ┃
	 * </pre>
	 */
	UTF_HEAVY_CONTENT				('┃', '┃', '┃', '┃', '┃', '\u2003', "UTF-8 with heavy (thick) lines vertically and horizontally for content row"),

	;

	/** Local builder. */
	V2_RowThemeBuilder builder = new V2_RowThemeBuilder();

	/**
	 * Returns a row theme.
	 * @param leftBorder left border character
	 * @param midBorderUp mid up border character
	 * @param midBorderAll mid all border character
	 * @param midBorderDown mid down border character
	 * @param rightBorder right border character
	 * @param mid mid character
	 * @param description a description
	 */
	private V2_E_RowThemes(char leftBorder, char midBorderUp, char midBorderAll, char midBorderDown, char rightBorder, char mid, String description){
		this.builder
			.setDescription(description)
			.setLeftBorder(leftBorder)
			.setRightBorder(rightBorder)
			.setMid(mid)
			.setMidBorderAll(midBorderAll)
			.setMidBorderDown(midBorderDown)
			.setMidBorderUp(midBorderUp)
		;
		ThemeValidator.validateRowTheme(this.builder.build());
	}

	/**
	 * Returns the actual theme.
	 * @return the theme
	 */
	public V2_RowTheme get(){
		return this.builder.build();
	}

}
