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
 * ASCII and UTF-8 box drawing characters.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 150812 (12-Aug-15) for Java 1.7
 * @since      v0.0.1
 */
public enum V1_CharactersBoxDrawing implements V1_TablePair<Character, Character> {

	/** UTF-8 Light for down+horizontal = ┬ */
	LIGHT_DOWN_AND_HORIZONTAL			('\u252C', '┬', "UTF-8 Light for down+horizontal"),

	/** UTF-8 Light for down+left = ┐ */
	LIGHT_DOWN_AND_LEFT					('\u2510', '┐', "UTF-8 Light for down+left"),

	/** UTF-8 Light for down+right = ┌ */
	LIGHT_DOWN_AND_RIGHT				('\u250C', '┌', "UTF-8 Light for down+right"),

	/** UTF-8 Light for horizontal = ─ */
	LIGHT_HORIZONTAL					('\u2500', '─', "UTF-8 Light for horizontal"),

	/** UTF-8 Light for u[+horizontal = ┴ */
	LIGHT_UP_AND_HORIZONTAL				('\u2534', '┴', "UTF-8 Light for u[+horizontal"),

	/** UTF-8 Light for up+left = ┘ */
	LIGHT_UP_AND_LEFT					('\u2518', '┘', "UTF-8 Light for up+left"),

	/** UTF-8 Light for up+right = └ */
	LIGHT_UP_AND_RIGHT					('\u2514', '└', "UTF-8 Light for up+right"),

	/** UTF-8 Light for vertical = │ */
	LIGHT_VERTICAL						('\u2502', '│', "UTF-8 Light for vertical"),

	/** UTF-8 Light for vertical+horizontal = ┼ */
	LIGHT_VERTICAL_AND_HORIZONTAL		('\u253C', '┼', "UTF-8 Light for vertical+horizontal"),

	/** UTF-8 Light for vertical+left = ┤ */
	LIGHT_VERTICAL_AND_LEFT				('\u2524', '┤', "UTF-8 Light for vertical+left"),

	/** UTF-8 Light for vertical+right = ├ */
	LIGHT_VERTICAL_AND_RIGHT			('\u251C', '├', "UTF-8 Light for vertical+right"),


	/** UTF-8 Double for down+horizontal = ╦ */
	DOUBLE_DOWN_AND_HORIZONTAL				('\u2566', '╦', ""),

	/** UTF-8 Double for down+left = ╗ */
	DOUBLE_DOWN_AND_LEFT					('\u2557', '╗', "UTF-8 Double for down+left"),

	/** UTF-8 Double for down+right = ╔ */
	DOUBLE_DOWN_AND_RIGHT					('\u2554', '╔', "UTF-8 Double for down+right"),

	/** UTF-8 Double for horizontal = ═ */
	DOUBLE_HORIZONTAL						('\u2550', '═', "UTF-8 Double for horizontal"),

	/** UTF-8 Double for up+horizontal = ╩ */
	DOUBLE_UP_AND_HORIZONTAL				('\u2569', '╩', "UTF-8 Double for up+horizontal"),

	/** UTF-8 Double for up+left = ╝ */
	DOUBLE_UP_AND_LEFT						('\u255D', '╝', "UTF-8 Double for up+left"),

	/** UTF-8 Double for up+right = ╚ */
	DOUBLE_UP_AND_RIGHT						('\u255A', '╚', "UTF-8 Double for up+right"),

	/** UTF-8 Double for vertical = ║ */
	DOUBLE_VERTICAL							('\u2551', '║', "UTF-8 Double for vertical"),

	/** UTF-8 Double for vertical+horizontal = ╬ */
	DOUBLE_VERTICAL_AND_HORIZONTAL			('\u256C', '╬', "UTF-8 Double for vertical+horizontal"),

	/** UTF-8 Double for vertical+left = ╣ */
	DOUBLE_VERTICAL_AND_LEFT				('\u2563', '╣', "UTF-8 Double for vertical+left"),

	/** UTF-8 Double for vertical+right = ╠ */
	DOUBLE_VERTICAL_AND_RIGHT				('\u2560', '╠', "UTF-8 Double for vertical+right"),


	/** UTF-8 for double down and single right = ╓ */
	DOUBLE_LIGHT_DOWN_AND_RIGHT				('\u2553', '╓', "UTF-8 for double down and single right"),

	/** UTF-8 for double down and single left = ╖ */
	DOUBLE_LIGHT_DOWN_AND_LEFT					('\u2556', '╖', "UTF-8 for double down and single left"),

	/** UTF-8 for double down and single horizontal = ╥ */
	DOUBLE_LIGHT_DOWN_AND_HORIZONTAL			('\u2565', '╥', "UTF-8 for double down and single horizontal"),

	/** UTF-8 for double up and single right = ╙ */
	DOUBLE_LIGHT_UP_AND_RIGHT					('\u2559', '╙', "UTF-8 for double up and single right"),

	/** UTF-8 for double up and single left = ╜ */
	DOUBLE_LIGHT_UP_AND_LEFT					('\u255C', '╜', "UTF-8 for double up and single left"),

	/** UTF-8 for double up and single horizontal = ╨ */
	DOUBLE_LIGHT_UP_AND_HORIZONTAL				('\u2568', '╨', "UTF-8 for double up and single horizontal"),

	/** UTF-8 for double vertical and single right = ╟ */
	DOUBLE_LIGHT_VERTICAL_AND_RIGHT			('\u255F', '╟', "UTF-8 for double vertical and single right"),

	/** UTF-8 for double vertical and single left = ╢ */
	DOUBLE_LIGHT_VERTICAL_AND_LEFT				('\u2562', '╢', "UTF-8 for double vertical and single left"),

	/** UTF-8 for double vertical and single horizontal = ╫ */
	DOUBLE_LIGHT_VERTICAL_AND_HORIZONTAL		('\u256B', '╫', "UTF-8 for double vertical and single horizontal"),


	/** UTF-8 Heavy for down+horizontal = ┳ */
	HEAVY_DOWN_AND_HORIZONTAL			('\u2533', '┳', "UTF-8 Heavy for down+horizontal"),

	/** UTF-8 Heavy for down+left = ┓ */
	HEAVY_DOWN_AND_LEFT					('\u2513', '┓', "UTF-8 Heavy for down+left"),

	/** UTF-8 Heavy for down+right = ┏ */
	HEAVY_DOWN_AND_RIGHT				('\u250F', '┏', "UTF-8 Heavy for down+right"),

	/** UTF-8 Heavy for horizontal = ━ */
	HEAVY_HORIZONTAL					('\u2501', '━', "UTF-8 Heavy for horizontal"),

	/** UTF-8 Heavy for up+horizontal = ┻ */
	HEAVY_UP_AND_HORIZONTAL			('\u253B', '┻', "UTF-8 Heavy for up+horizontal"),

	/** UTF-8 Heavy for up+left = ┛ */
	HEAVY_UP_AND_LEFT					('\u251B', '┛', "UTF-8 Heavy for up+left"),

	/** UTF-8 Heavy for up+right = ┗  */
	HEAVY_UP_AND_RIGHT					('\u2517', '┗', "UTF-8 Heavy for up+right"),

	/** UTF-8 Heavy for vertical = ┃ */
	HEAVY_VERTICAL						('\u2503', '┃', "UTF-8 Heavy for vertical"),

	/** UTF-8 Heavy for vertical+horizontal = ╋ */
	HEAVY_VERTICAL_AND_HORIZONTAL		('\u254B', '╋', "UTF-8 Heavy for vertical+horizontal"),

	/** UTF-8 Heavy for vertical+left = ┫ */
	HEAVY_VERTICAL_AND_LEFT			('\u252B', '┫', "UTF-8 Heavy for vertical+left"),

	/** UTF-8 Heavy for vertical+right = ┣ */
	HEAVY_VERTICAL_AND_RIGHT			('\u2523', '┣', "UTF-8 Heavy for vertical+right"),


	/** UTF-8 for down heavy and right light = ┎ */
	HEAVY_LIGHT_DOWN_AND_RIGHT			('\u250E', '┎', "UTF-8 for down heavy and right light"),

	/** UTF-8 for down heavy and left light = ┒ */
	HEAVY_LIGHT_DOWN_AND_LEFT			('\u2512', '┒', "UTF-8 for down heavy and left light"),

	/** UTF-8 for down heavy and horizontal light = ┰ */
	HEAVY_LIGHT_DOWN_AND_HORIZONTAL		('\u2530', '┰', "UTF-8 for down heavy and horizontal light"),

	/** UTF-8 for up heavy vertical and light horizontal = ┖ */
	HEAVY_LIGHT_UP_AND_RIGHT			('\u2516', '┖', "UTF-8 for up heavy and right light"),

	/** UTF-8 for up heavy vertical and light horizontal = ┚ */
	HEAVY_LIGHT_UP_AND_LEFT				('\u251A', '┚', "UTF-8 for up heavy and left light"),

	/** UTF-8 for up heavy vertical and light horizontal = ┸ */
	HEAVY_LIGHT_UP_AND_HORIZONTAL		('\u2538', '┸', "UTF-8 for up heavy and horizontal light"),

	/** UTF-8 for vertical heavy and right light = ┠ */
	HEAVY_LIGHT_VERTICAL_AND_RIGHT		('\u2520', '┠', "UTF-8 for vertical heavy and right light"),

	/** UTF-8 for vertical heavy and left light = ┨ */
	HEAVY_LIGHT_VERTICAL_AND_LEFT		('\u2528', '┨', "UTF-8 for vertical heavy and left light"),

	/** UTF-8 for vertical heavy and horizontal light = ╂ */
	HEAVY_LIGHT_VERTICAL_AND_HORIZONTAL	('\u2542', '╂', "UTF-8 for vertical heavy and horizontal light"),


	/** UTF-8 for light down and heavy right = ┍ */
	LIGHT_HEAVY_DOWN_AND_RIGHT			('\u250D', '┍', "UTF-8 for light down and heavy right"),

	/** UTF-8 for light down and heavy left = ┑ */
	LIGHT_HEAVY_DOWN_AND_LEFT			('\u2511', '┑', "UTF-8 for light down and heavy left"),

	/** UTF-8 for light down and heavy horizontal = ┯ */
	LIGHT_HEAVY_DOWN_AND_HORIZONTAL		('\u252F', '┯', "UTF-8 for light down and heavy horizontal"),

	/** UTF-8 for light up and heavy right = ┕ */
	LIGHT_HEAVY_UP_AND_RIGHT			('\u2515', '┕', "UTF-8 for light up and heavy right"),

	/** UTF-8 for light up and heavy left = ┙ */
	LIGHT_HEAVY_UP_AND_LEFT				('\u2519', '┙', "UTF-8 for light up and heavy left"),

	/** UTF-8 for light up and heavy horizontal = ┷ */
	LIGHT_HEAVY_UP_AND_HORIZONTAL		('\u2537', '┷', "UTF-8 for light up and heavy horizontal"),

	/** UTF-8 for light vertical and heavy right = ┝ */
	LIGHT_HEAVY_VERTICAL_AND_RIGHT		('\u251D', '┝', "UTF-8 for light vertical and heavy right"),

	/** UTF-8 for light vertical and heavy left = ┥ */
	LIGHT_HEAVY_VERTICAL_AND_LEFT		('\u2525', '┥', "UTF-8 for light vertical and heavy left"),

	/** UTF-8 for light vertical and heavy horizontal = ┿ */
	LIGHT_HEAVY_VERTICAL_AND_HORIZONTAL	('\u253F', '┿', "UTF-8 for light vertical and heavy horizontal"),


	/** UTF-8 for single down and double right = ╒ */
	LIGHT_DOUBLE_DOWN_AND_RIGHT					('\u2552', '╒', "UTF-8 for single down and double right"),

	/** UTF-8 for single down and double left = ╕ */
	LIGHT_DOUBLE_DOWN_AND_LEFT					('\u2555', '╕', "UTF-8 for single down and double left"),

	/** UTF-8 for single down and double horizontal = ╤ */
	LIGHT_DOUBLE_DOWN_AND_HORIZONTAL			('\u2564', '╤', "UTF-8 for single down and double horizontal"),

	/** UTF-8 Double for horizontal = ═ */
	LIGHT_DOUBLE_HORIZONTAL						('\u2550', '═', "UTF-8 Double for horizontal"),

	/** UTF-8 for single up and double right = ╘ */
	LIGHT_DOUBLE_UP_AND_RIGHT					('\u2558', '╘', "UTF-8 for single up and double right"),

	/** UTF-8 for single up and double left = ╛ */
	LIGHT_DOUBLE_UP_AND_LEFT					('\u255B', '╛', "UTF-8 for single up and double left"),

	/** UTF-8 for single up and double horizontal = ╧ */
	LIGHT_DOUBLE_UP_AND_HORIZONTAL				('\u2567', '╧', "UTF-8 for single up and double horizontal"),

	/** UTF-8 Light for vertical = │ */
	LIGHT_DOUBLE_VERTICAL						('\u2502', '│', "UTF-8 Light for vertical"),

	/** UTF-8 for single vertical and double right = ╞ */
	LIGHT_DOUBLE_VERTICAL_AND_RIGHT				('\u255E', '╞', "UTF-8 for single vertical and double right"),

	/** UTF-8 for single vertical and double left = ╡ */
	LIGHT_DOUBLE_VERTICAL_AND_LEFT				('\u2561', '╡', "UTF-8 for single vertical and double left"),

	/** UTF-8 for single vertical and double horizontal = ╪ */
	LIGHT_DOUBLE_VERTICAL_AND_HORIZONTAL		('\u256A', '╪', "UTF-8 for single vertical and double horizontal"),


	/** UTF-8 Heavy for horizontal double dash = ╍ */
	HEAVY_HORIZONTAL_DOUBLE_DASH		('\u254D', '╍', "UTF-8 Heavy for horizontal double dash"),

	/** UTF-8 Heavy for horizontal double dash = ┉ */
	HEAVY_HORIZONTAL_QUADRUPLE_DASH		('\u2509', '┉', "UTF-8 Heavy for horizontal double dash"),

	/** UTF-8 Heavy for horizontal double dash = ┅ */
	HEAVY_HORIZONTAL_TRIPLE_DASH		('\u2505', '┅', "UTF-8 Heavy for horizontal double dash"),

	/** UTF-8 Heavy for vertical double dash = ╏ */
	HEAVY_VERTICAL_DOUBLE_DASH			('\u254F', '╏', "UTF-8 Heavy for vertical double dash"),

	/** UTF-8 Heavy for vertical double dash = ┋ */
	HEAVY_VERTICAL_QUADRUPLE_DASH		('\u250B', '┋', "UTF-8 Heavy for vertical double dash"),

	/** UTF-8 Heavy for vertical double dash = ┇ */
	HEAVY_VERTICAL_TRIPLE_DASH			('\u2507', '┇', "UTF-8 Heavy for vertical double dash"),


	/** UTF-8 Light for horizontal double dash = ╌ */
	LIGHT_HORIZONTAL_DOUBLE_DASH		('\u254C', '╌', "UTF-8 Light for horizontal double dash"),

	/** UTF-8 Light for horizontal double dash = ┈ */
	LIGHT_HORIZONTAL_QUADRUPLE_DASH		('\u2508', '┈', "UTF-8 Light for horizontal double dash"),

	/** UTF-8 Light for horizontal double dash = ┄ */
	LIGHT_HORIZONTAL_TRIPLE_DASH		('\u2504', '┄', "UTF-8 Light for horizontal double dash"),

	/** UTF-8 Light for vertical double dash = ╎ */
	LIGHT_VERTICAL_DOUBLE_DASH			('\u254E', '╎', "UTF-8 Light for vertical double dash"),

	/** UTF-8 Light for vertical double dash = ┊ */
	LIGHT_VERTICAL_QUADRUPLE_DASH		('\u250A', '┊', "UTF-8 Light for vertical double dash"),

	/** UTF-8 Light for vertical double dash = ┆ */
	LIGHT_VERTICAL_TRIPLE_DASH			('\u2506', '┆', "UTF-8 Light for vertical double dash"),


	/** UTF-8 visible space = ␣ */
	VISIBLE_SPACE						('␣', '␣', "UTF-8 visible space"),

	;

	/** The actual character, requires UTF-8 capabilities to use */
	public final char character;

	/** The character as an UTF escape */
	public final char utf;

	/** Description for the pair */
	private String description;

	/** Private constructor */
	private V1_CharactersBoxDrawing(char utf, char character, String description){
		this.utf = utf;
		this.character = character;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public Character left() {
		return this.character;
	}

	@Override
	public Character right() {
		return this.utf;
	}

	@Override
	public Character lhs() {
		return this.character;
	}

	@Override
	public Character rhs() {
		return this.utf;
	}

	@Override
	public String toString(){
		return this.getClass().getSimpleName()+"["+this.utf+", "+this.character+", "+this.description+"]";
	}

}
