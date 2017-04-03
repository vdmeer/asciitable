/* Copyright 2017 Sven van der Meer <vdmeer.sven@mykolab.com>
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

package de.vandermeer.asciitable;

import de.vandermeer.asciithemes.TA_Grid;
import de.vandermeer.asciithemes.TA_GridThemeOptions;
import de.vandermeer.asciithemes.TA_GridThemes;
import de.vandermeer.asciithemes.u8.U8_Grids;
import de.vandermeer.skb.interfaces.document.IsTableContext;

/**
 * Context for an {@link AsciiTable}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.0-SNAPSHOT build 170331 (31-Mar-17) for Java 1.8
 * @since      v0.3.0
 */
public class AT_Context implements IsTableContext {

	/** The character outside bottom frame. */
	protected Character frameBottomMarginChar = ' ';

	/** Margin outside bottom frame. */
	protected int frameBottomMargin = 0;

	/** The character for left frame margin. */
	protected Character frameLeftChar = ' ';

	/** Margin outside the left frame border. */
	protected int frameLeftMargin = 0;

	/** The character for right frame margin. */
	protected Character frameRightChar = ' ';

	/** Margin outside the right frame border. */
	protected int frameRightMargin = 0;

	/** The character outside top frame. */
	protected Character frameTopMarginChar = ' ';

	/** Margin outside top frame. */
	protected int frameTopMargin = 0;

	/** The width of the table, actual width depends on padding settings, default is `80`. */
	protected int width = 80;

	/** The grid (actual frame) for the table. */
	protected TA_Grid grid = U8_Grids.borderLight();

	/** The theme for the grid (which parts to use for drawing a frame). */
	protected int gridTheme = TA_GridThemes.FULL.get();

	/** Options for the grid, for instance show empty lines. */
	protected int gridThemeOptions = TA_GridThemeOptions.SHOW_EMPTY_ALL.get();

	/**
	 * Returns the bottom frame margin character.
	 * @return bottom frame margin character
	 */
	public Character getFrameBottomChar() {
		return this.frameBottomMarginChar;
	}

	/**
	 * Returns the bottom frame margin.
	 * @return bottom frame margin
	 */
	public int getFrameBottomMargin() {
		return this.frameBottomMargin;
	}

	/**
	 * Returns the left frame margin character.
	 * @return left frame margin character
	 */
	public Character getFrameLeftChar() {
		return this.frameLeftChar;
	}

	/**
	 * Returns the left frame margin.
	 * @return left frame margin
	 */
	public int getFrameLeftMargin() {
		return this.frameLeftMargin;
	}

	/**
	 * Returns the right frame margin character.
	 * @return right frame margin character
	 */
	public Character getFrameRightChar() {
		return this.frameRightChar;
	}

	/**
	 * Returns the right frame margin.
	 * @return right frame margin
	 */
	public int getFrameRightMargin() {
		return this.frameRightMargin;
	}

	/**
	 * Returns the top frame margin character.
	 * @return top frame margin character
	 */
	public Character getFrameTopChar() {
		return this.frameTopMarginChar;
	}

	/**
	 * Returns the top frame margin.
	 * @return top frame margin
	 */
	public int getFrameTopMargin() {
		return this.frameTopMargin;
	}

	@Override
	public int getTextWidth(int width) {
		int ret = width - this.getFrameLeftMargin() - this.getFrameRightMargin();
		return ret;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	/**
	 * Sets the bottom frame margin character.
	 * @param frameBottom character
	 * @return this to allow chaining
	 */
	public AT_Context setFrameBottomChar(Character frameBottom) {
		if(frameBottom!=null){
			this.frameBottomMarginChar = frameBottom;
		}
		return this;
	}

	/**
	 * Sets the bottom frame margin
	 * @param frameBottom margin
	 * @return this to allow chaining
	 */
	public AT_Context setFrameBottomMargin(int frameBottom) {
		if(frameBottom>-1){
			this.frameBottomMargin = frameBottom;
		}
		return this;
	}

	/**
	 * Sets the left frame margin character.
	 * @param frameLeft character
	 * @return this to allow chaining
	 */
	public AT_Context setFrameLeftChar(Character frameLeft) {
		if(frameLeft!=null){
			this.frameLeftChar = frameLeft;
		}
		return this;
	}

	/**
	 * Sets the left frame margin
	 * @param frameLeft margin
	 * @return this to allow chaining
	 */
	public AT_Context setFrameLeftMargin(int frameLeft) {
		if(frameLeft>-1){
			this.frameLeftMargin = frameLeft;
		}
		return this;
	}

	/**
	 * Sets the left and right frame margin character.
	 * @param frameChar character
	 * @return this to allow chaining
	 */
	public AT_Context setFrameLeftRightChar(Character frameChar){
		if(frameChar!=null){
			this.frameLeftChar = frameChar;
			this.frameRightChar = frameChar;
		}
		return this;
	}

	/**
	 * Sets the left and right frame margin character.
	 * @param frameLeft character
	 * @param frameRight character
	 * @return this to allow chaining
	 */
	public AT_Context setFrameLeftRightChar(Character frameLeft, Character frameRight){
		if(frameLeft!=null && frameRight!=null){
			this.frameLeftChar = frameLeft;
			this.frameRightChar = frameRight;
		}
		return this;
	}

	/**
	 * Sets the left and right frame margin.
	 * @param frameMargin margin
	 * @return this to allow chaining
	 */
	public AT_Context setFrameLeftRightMargin(int frameMargin){
		if(frameMargin>-1){
			this.frameLeftMargin = frameMargin;
			this.frameRightMargin = frameMargin;
		}
		return this;
	}

	/**
	 * Sets the left and right frame margin.
	 * @param frameLeft margin
	 * @param frameRight margin
	 * @return this to allow chaining
	 */
	public AT_Context setFrameLeftRightMargin(int frameLeft, int frameRight){
		if(frameRight>-1 && frameLeft>-1){
			this.frameLeftMargin = frameLeft;
			this.frameRightMargin = frameRight;
		}
		return this;
	}

	/**
	 * Sets the right frame margin character.
	 * @param frameRight character
	 * @return this to allow chaining
	 */
	public AT_Context setFrameRightChar(Character frameRight) {
		if(frameRight!=null){
			this.frameRightChar = frameRight;
		}
		return this;
	}

	/**
	 * Sets the right frame margin
	 * @param frameRight margin
	 * @return this to allow chaining
	 */
	public AT_Context setFrameRightMargin(int frameRight) {
		if(frameRight>-1){
			this.frameRightMargin = frameRight;
		}
		return this;
	}

	/**
	 * Sets the top and bottom frame margin.
	 * @param frameMargin margin
	 * @return this to allow chaining
	 */
	public AT_Context setFrameTopBottomMargin(int frameMargin){
		if(frameMargin>-1){
			this.frameTopMargin = frameMargin;
			this.frameBottomMargin = frameMargin;
		}
		return this;
	}

	/**
	 * Sets the top and bottom frame margin.
	 * @param frameTop margin
	 * @param frameBottom margin
	 * @return this to allow chaining
	 */
	public AT_Context setFrameTopBottomMargin(int frameTop, int frameBottom){
		if(frameTop>-1 && frameBottom>-1){
			this.frameTopMargin = frameTop;
			this.frameBottomMargin = frameBottom;
		}
		return this;
	}

	/**
	 * Sets the top frame margin character.
	 * @param frameTop character
	 * @return this to allow chaining
	 */
	public AT_Context setFrameTopChar(Character frameTop) {
		if(frameTop!=null){
			this.frameTopMarginChar = frameTop;
		}
		return this;
	}

	/**
	 * Sets the top frame margin
	 * @param frameTop margin
	 * @return this to allow chaining
	 */
	public AT_Context setFrameTopMargin(int frameTop) {
		if(frameTop>-1){
			this.frameTopMargin = frameTop;
		}
		return this;
	}

	/**
	 * Sets the table width.
	 * @param width new width
	 * @return this to allow chaining
	 */
	public AT_Context setWidth(int width) {
		this.width = width;
		return this;
	}

	/**
	 * Sets the grid used to render the table.
	 * @param grid new grid, ignored if null
	 * @return self to allow for chaining
	 */
	public AT_Context setGrid(TA_Grid grid){
		if(grid!=null){
			this.grid = grid;
		}
		return this;
	}

	/**
	 * Returns the grid.
	 * @return table grid
	 */
	public TA_Grid getGrid(){
		return this.grid;
	}

	/**
	 * Sets the grid theme.
	 * @param theme new grid theme, ignored if null
	 * @return self to allow for chaining
	 */
	public AT_Context setGridTheme(TA_GridThemes theme){
		if(theme!=null){
			this.setGridTheme(theme.get());
		}
		return this;
	}

	/**
	 * Sets the grid theme.
	 * @param theme new grid theme, must be positive integer
	 * @return self to allow for chaining
	 */
	public AT_Context setGridTheme(int theme) {
		if(theme>=0){
			this.gridTheme = theme;
		}
		return this;
	}

	/**
	 * Sets options for the grid theme.
	 * @param options new grid theme options, ignored if null
	 * @return self to allow for chaining
	 */
	public AT_Context setGridThemeOptions(TA_GridThemeOptions options){
		if(options!=null){
			this.setGridThemeOptions(options.get());
		}
		return this;
	}

	/**
	 * Sets options for the grid theme.
	 * @param options new grid theme options, must be positive integer
	 * @return self to allow for chaining
	 */
	public AT_Context setGridThemeOptions(int options) {
		if(options>=0){
			this.gridThemeOptions = options;
		}
		return this;
	}

	/**
	 * Returns the grid theme.
	 * @return grid theme
	 */
	public int getGridTheme() {
		return this.gridTheme;
	}

	/**
	 * Returns the grid theme options.
	 * @return grid theme options
	 */
	
	public int getGridThemeOptions() {
		return this.gridThemeOptions;
	}
}
