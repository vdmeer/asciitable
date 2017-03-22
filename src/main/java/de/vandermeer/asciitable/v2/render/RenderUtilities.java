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

package de.vandermeer.asciitable.v2.render;

import de.vandermeer.asciitable.v2.row.ContentRow;
import de.vandermeer.asciitable.v2.row.RuleRow;
import de.vandermeer.asciitable.v2.row.V2_Row;
import de.vandermeer.asciitable.v2.themes.V2_RowTheme;
import de.vandermeer.skb.interfaces.transformers.Object_To_ColumnContentArray;
import de.vandermeer.skb.interfaces.transformers.arrays2d.Array2D_To_FlipArray;
import de.vandermeer.skb.interfaces.transformers.arrays2d.Array2D_To_NormalizedArray;

/**
 * Utilities for manipulating tables and table rows.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.6-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.5
 */
public abstract class RenderUtilities {

	/**
	 * Creates a content array, that is an array with columns.
	 * It will wrap lines and normalize the array.
	 * @param columns the original columns
	 * @param width the width of the columns
	 * @param padding column padding
	 * @return a fully processed and normalized content array
	 */
	public static final String[][] createContentArray(Object[] columns, int[] width, int[] padding){
		String[][] ret = new String[width.length][];

		int length = 0;
		for(int i=0; i<columns.length; i++){
			Object o = columns[i];
			if(o==null){
				length += width[i];
				continue;
			}
			else{
				length += width[i];
			}
			if(padding[i]>0){
				length = length - padding[i] * 2;
			}

			ret[i] = Object_To_ColumnContentArray.convert(o, length);
			length = 0;
		}

		//equal number of strings per column
		ret = Array2D_To_NormalizedArray.create(width.length).transform(ret);
		//flip so that each normalized array row is a table column
		ret = Array2D_To_FlipArray.create().transform(ret);
		return ret;
	}

	/**
	 * Returns the border types for a bottom rule, regardless of the actual type of the given row.
	 * @param prev the previous row in the table
	 * @param row the original row
	 * @param colNumbers number of columns in the table
	 * @return array of border types, null if none could be created
	 */
	public static final BorderType[] getBorderTypes_BottomRule(ProcessedRow prev, V2_Row row, int colNumbers){
		BorderType[] ret = null;
		if(!(row instanceof RuleRow)){
			return ret;
		}

		ret = new BorderType[colNumbers+1];
		for(int i=0; i<ret.length; i++){
			ret[i] = BorderType.NONE;
		}

		if(prev!=null){
			BorderType[] relAdj = prev.getBorderTypes();
			if(relAdj!=null){
				for(int i=0; i<relAdj.length; i++){
					switch(relAdj[i]){
						case NONE:
							ret[i] = BorderType.NONE;
							break;
						case UP:
							ret[i] = BorderType.NONE;
							break;
						case ALL:
							ret[i] = BorderType.UP;
							break;
						case DOWN:
							ret[i] = BorderType.UP;
							break;
						case CONTENT:
							ret[i] = BorderType.UP;
							break;
						
					}
				}
			}
		}
		return ret;
	}

	/**
	 * Creates the border types for a content row.
	 * @param ar an array of columns for the calculation
	 * @param original the original row
	 * @param colNumbers the number of columns in the table
	 * @return array of border types, null if none could be created
	 */
	public static final BorderType[] getBorderTypes_ContentRow(String[] ar, ContentRow original, int colNumbers){
		BorderType[] ret = null;

		ret = new BorderType[colNumbers+1];

		//left side
		ret[0] = BorderType.CONTENT;
		//right side
		ret[colNumbers] = BorderType.CONTENT;

		//in between
		for(int i=0; i<ar.length; i++){
			String content = ar[i];
			if(content==null){
				if(i==ar.length-1){
					//a null in last column
					ret[i+1] = BorderType.NONE;
				}
				else{
					//standard null = span
					ret[i+1] = BorderType.NONE;
				}
			}
			else if("".equals(content)){
				//empty column finishes span
				ret[i+1] = BorderType.CONTENT;
			}
			else{
				//all other columns finishing spans
				ret[i+1] = BorderType.CONTENT;
			}
		}
		return ret;
	}

	/**
	 * Returns the border types for a mid rule, regardless of the actual type of the given row.
	 * @param prev previous row in the table
	 * @param next next row in the table
	 * @param row the original row
	 * @param colNumbers number of columns in the table
	 * @return array of border types, null if none could be created
	 */
	public static final BorderType[] getBorderTypes_MidRule(ProcessedRow prev, ProcessedRow next, V2_Row row, int colNumbers){
		BorderType[] ret = null;
		if(!(row instanceof RuleRow)){
			return ret;
		}

		ret = new BorderType[colNumbers+1];
		for(int i=0; i<ret.length; i++){
			ret[i] = BorderType.NONE;
		}

		//first set everything against the previous row (if any)
		if(prev!=null){
			BorderType[] relAdj = prev.getBorderTypes();
			if(relAdj!=null){
				for(int i=0; i<relAdj.length; i++){
					switch(relAdj[i]){
						case NONE:
							ret[i] = BorderType.NONE;
							break;
						case UP:
							ret[i] = BorderType.NONE;
							break;
						case ALL:
							ret[i] = BorderType.UP;
							break;
						case DOWN:
							ret[i] = BorderType.UP;
							break;
						case CONTENT:
							ret[i] = BorderType.UP;
							break;
					}
				}
			}
		}

		//next set anything against the next row (if any)
		if(next!=null){
			BorderType[] relAdj = next.getBorderTypes();
			if(relAdj!=null){
				for(int i=0; i<relAdj.length; i++){
					switch(relAdj[i]){
						case NONE:
						case DOWN:
							break;
						case CONTENT:
						case ALL:
						case UP:
							if(ret[i]==BorderType.UP){
								ret[i] = BorderType.ALL;
							}
							else{
								ret[i] = BorderType.DOWN;
							}
							break;
					}
				}
			}
		}
		return ret;
	}

	/**
	 * Returns the border types for a top rule, regardless of the actual type of the given row.
	 * @param next the next row in the table
	 * @param row the original row
	 * @param colNumbers number of columns in the table
	 * @return array of border types, null if none could be created
	 */
	public static final BorderType[] getBorderTypes_TopRule(ProcessedRow next, V2_Row row, int colNumbers){
		BorderType[] ret = null;
		if(!(row instanceof RuleRow)){
			return ret;
		}

		ret = new BorderType[colNumbers+1];
		for(int i=0; i<ret.length; i++){
			ret[i] = BorderType.NONE;
		}

		if(next!=null){
			BorderType[] relAdj = next.getBorderTypes();
			if(relAdj!=null){
				for(int i=0; i<relAdj.length; i++){
					switch(relAdj[i]){
						case NONE:
							ret[i] = BorderType.NONE;
							break;
						case UP:
							ret[i] = BorderType.DOWN;
							break;
						case ALL:
							ret[i] = BorderType.DOWN;
							break;
						case DOWN:
							ret[i] = BorderType.NONE;
							break;
						case CONTENT:
							ret[i] = BorderType.DOWN;
							break;
					}
				}
			}
		}
		return ret;
	}

	/**
	 * Returns a border character for given position and type from a theme.
	 * @param pos position of the character: left, middle or right
	 * @param type type of the character: all, content, down, up, none
	 * @param tr theme for the character
	 * @return the retrieved character from the theme
	 */
	public static char getChar(BorderPosition pos, BorderType type, V2_RowTheme tr){
		switch(type){
			case ALL:
				switch(pos){
					case LEFT:		return tr.getLeftBorder();
					case MIDDLE:	return tr.getMidBorderAll();
					case RIGHT:		return tr.getRightBorder();
				}
				break;
			case CONTENT:
				switch(pos){
					case LEFT:		return tr.getLeftBorder();
					case MIDDLE:	return tr.getMidBorderAll();
					case RIGHT:		return tr.getRightBorder();
				}
				break;
			case DOWN:
				switch(pos){
					case LEFT:		return tr.getLeftBorder();
					case MIDDLE:	return tr.getMidBorderDown();
					case RIGHT:		return tr.getRightBorder();
				}
				break;
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
}
