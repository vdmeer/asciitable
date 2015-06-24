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

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;


/**
 * Renders a table.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4 build 150619 (19-Jun-15) for Java 1.8
 */
public class AsciiTableRenderer {

	/** Character used for padding in table columns. */
	char paddingChar;

	/** The theme for the table. */
	TableTheme theme;

	/** width of the table. */
	Width width;

	/** List of rows processed and ready to b rendered. */
	List<ProcessedRow> rows;

	/**
	 * Returns a new table row renderer.
	 * Default values are:
	 * <ul>
	 * 		<li>Padding character: blank (' ')</li>
	 * 		<li>Theme: plain 7 bit ASCII theme</li>
	 * </ul>
	 */
	public AsciiTableRenderer(){
		this.paddingChar = ' ';
		this.theme = E_TableThemes.PLAIN_7BIT.get();
		this.width = null;
		this.rows = new LinkedList<>();
	}

	public AsciiTableRenderer setWidth(Width width){
		if(width!=null){
			this.width = width;
		}
		return this;
	}

	/**
	 * Renders the given table and returns a list of string builders with the rendered rows.
	 * @param table table to be rendered
	 * @return linked list of string builders with rendered rows
	 */
	public AsciiTableRendered render(AsctiiTable table){
		//nothing to do
		if(table==null || table.getColumnCount()==0){
			throw new IllegalArgumentException("wrong table argument: table is null or has no columns");
		}

		//no width set for table, nothing we can do
		if(this.width==null){
			throw new IllegalArgumentException("wrong table width argument: no width set");
		}

		int[] cols = this.width.calculateWidth(table);

		boolean[] borders = new boolean[cols.length];
		for(int i=0; i<borders.length; i++){
			borders[i] = true;
		}

		//got width, now prepare all table information

		//start fixing the table top and bottom rules if they are set
		table.fixRules();

		//now create a list of processed table rows
		for(TableRow row : table.table){
			this.rows.add(new ProcessedRow(row, cols));
		}

		//now adjust borders for top and bottom rules
		this.rows.get(0).adjustTopRuleBorder((this.rows.size()>1)?this.rows.get(1):null);
		this.rows.get(this.rows.size()-1).adjustBottomRuleBorder((this.rows.size()>1)?this.rows.get(this.rows.size()-2):null);

		//and now adjust borders for all mid rules
		if(this.rows.size()>2){
			for(int r=1; r<this.rows.size()-1; r++){
				this.rows.get(r).adjustMidRuleBorder(this.rows.get(r-1), (r<this.rows.size()-2)?this.rows.get(r+1):null);
			}
		}

		List<StrBuilder> ret = new LinkedList<StrBuilder>();
		for(ProcessedRow row : this.rows){
			ret.add(this.renderRow(row, cols));
		}
		return new AsciiTableRendered(ret);
	}

	/**
	 * Renders a single row of a table.
	 * @param row row to be rendered, must be fully processed
	 * @param cols columns calculated by {@link Width}
	 * @return a string builder with the rendered strings (if lines are wrapped) of the rendered row
	 */
	protected final StrBuilder renderRow(ProcessedRow row, int[] cols){
		StrBuilder ret = new StrBuilder(100);

		RowTheme rt = null;
		String[][] columns = row.procColumns;

		E_AdjustedBorderType[] borders = row.adjustedBorders;
		if(row.original.isContent()){
			//it's a content row
			for(int i=0; i<columns.length; i++){
				rt = this.theme.getContent();
				if(i!=0){
					ret.appendNewLine();
				}
				int span = 0;
				for(int k=0; k<borders.length; k++){
					if(borders[k]!=E_AdjustedBorderType.NONE){
						if(k==0){
							ret.append(this.getChar(E_BorderPosition.LEFT, borders[k], rt));
						}
						else if(k==borders.length-1){
							ret.append(this.getChar(E_BorderPosition.RIGHT, borders[k], rt));
						}
						else{
							ret.append(this.getChar(E_BorderPosition.MIDDLE, borders[k], rt));
						}
					}

					if(k<columns[i].length){
						if(ArrayUtils.contains(columns[i], null)){
							if(columns[i][k]==null){
								if(k==columns[i].length-1){
									//a null in last column, so calculate the span)
									int width = 0;
									//add the span column width
									for(int s=0; s<span; s++){
										width += cols[s+1];
									}
									//add the separator characters (span) plus the one for this column
									width += span;
									//add the current column width
									width += cols[i+1];
									//Center content in the new column
									ret.appendFixedWidthPadRight("", width, this.paddingChar);
								}
								else{
									span += 1;
								}
							}
							else if("".equals(columns[i][k])){
								//we have an empty column, so
								//first finish the spans
								for(int s=0; s<span; s++){
									ret.appendFixedWidthPadRight("", cols[s+1], this.paddingChar);
								}
								ret.appendFixedWidthPadRight("", span, this.paddingChar);
								span = 0;
								//now add the empty column
								ret.appendFixedWidthPadRight(columns[i][k], cols[i+1], this.paddingChar);
							}
							else{
								int width = 0;
								//add the span column width
								for(int s=0; s<span; s++){
									width += cols[s+1];
								}
								//add the separator characters (span) plus the one for this column
								width += span;
								//add the current column width
								width += cols[i+1];
								//center content in the new column
								ret.append(StringUtils.center(columns[i][k], width, this.paddingChar));
								span = 0;
//								ret.appendFixedWidthPadRight(columns[i][k], cols[i+1], this.paddingChar);
							}
						}
						else{
							ret.appendFixedWidthPadRight(columns[i][k], cols[i+1], this.paddingChar);
						}
					}
				}
			}
		}
		else{
			switch(row.original.getRuleType()){
				case BOTTOM:
					switch(row.original.getRuleStyle()){
						case NORMAL:
							rt = this.theme.getBottom();
							break;
						case STRONG:
							rt = this.theme.getBottomStrong();
							break;
					}
					break;
				case MID:
					switch(row.original.getRuleStyle()){
						case NORMAL:
							rt = this.theme.getMid();
							break;
						case STRONG:
							rt = this.theme.getMidStrong();
							break;
					}
					break;
				case TOP:
					switch(row.original.getRuleStyle()){
						case NORMAL:
							rt = this.theme.getTop();
							break;
						case STRONG:
							rt = this.theme.getTopStrong();
							break;
					}
					break;
			}

			for(int k=0; k<borders.length; k++){
				if(k==0){
					ret.append(this.getChar(E_BorderPosition.LEFT, borders[k], rt));
				}
				else if(k==borders.length-1){
					ret.append(this.getChar(E_BorderPosition.RIGHT, borders[k], rt));
				}
				else{
					ret.append(this.getChar(E_BorderPosition.MIDDLE, borders[k], rt));
				}

				if(k+1<cols.length){
					ret.appendPadding(cols[k+1], rt.getMid());
				}
			}
		}
		return ret;
	}

	/**
	 * Returns a border character for given position and type from a theme
	 * @param pos position of the character: left, middle or right
	 * @param type type of the character: all, content, down, up, none
	 * @param tr theme for the character
	 * @return the retrieved character from the theme
	 */
	private char getChar(E_BorderPosition pos, E_AdjustedBorderType type, RowTheme tr){
		switch(type){
			case ALL:
				switch(pos){
					case LEFT:		return tr.getLeftBorder();
					case MIDDLE:	return tr.getMidBorderAll();
					case RIGHT:		return tr.getRightBorder();
				}
			case CONTENT:
				switch(pos){
					case LEFT:		return tr.getLeftBorder();
					case MIDDLE:	return tr.getMidBorderAll();
					case RIGHT:		return tr.getRightBorder();
				}
			case DOWN:
				switch(pos){
					case LEFT:		return tr.getLeftBorder();
					case MIDDLE:	return tr.getMidBorderDown();
					case RIGHT:		return tr.getRightBorder();
				}
			case NONE:
				return tr.getMid();
			case UP:
				switch(pos){
					case LEFT:		return tr.getLeftBorder();
					case MIDDLE:	return tr.getMidBorderUp();
					case RIGHT:		return tr.getRightBorder();
				}
				break;
		}
		return 'X';
	}

	/**
	 * Sets the padding character.
	 * @param pChar new padding character
	 * @return self to allow for chaining
	 */
	public AsciiTableRenderer setPaddingChar(char pChar){
		this.paddingChar = pChar;
		return this;
	}

	/**
	 * Sets and tests the theme for the table.
	 * @param theme new theme for the table
	 * @return self to allow for chaining
	 * @throws IllegalArgumentException if the theme is not valid
	 */
	public AsciiTableRenderer setTheme(TableTheme theme){
		if(theme!=null){
			TableTheme.testTheme(theme);
			this.theme = theme;
		}
		return this;
	}
}
