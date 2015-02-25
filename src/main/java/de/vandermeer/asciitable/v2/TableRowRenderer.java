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

import de.vandermeer.asciitable.commons.ArrayTransformations;
import de.vandermeer.asciitable.commons.TableException;
import de.vandermeer.asciitable.v2.themes.SimpleThemes;
import de.vandermeer.asciitable.v2.themes.ThemeRow;
import de.vandermeer.asciitable.v2.themes.ThemeSimple;
import de.vandermeer.asciitable.v2.themes.ThemeTB;

/**
 * New table row renderer.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 141210 (10-Dec-14) for Java 1.8
 */
public class TableRowRenderer {

	int columnCount;

	Integer[] columnWidth;

	char paddingChar = ' ';

	ThemeSimple ts = SimpleThemes.PLAIN_7BIT;

	ThemeTB tb = null;

	boolean[] borders;

	protected enum BorderPosition{
		LEFT, MIDDLE, RIGHT;
	}

	public TableRowRenderer(int columnCount, Integer tableWidth) throws TableException {
		this.columnCount = columnCount;
		this.columnWidth = new Integer[this.columnCount+1];

		if(tableWidth==null || tableWidth==0){
			throw new TableException("wrong table width argument", "table width is null or 0");
		}
		if(tableWidth<(this.columnCount*3+this.columnCount+1)){
			throw new TableException("wrong width argument", "width must allow for borders");
		}

		this.columnWidth[0] = tableWidth;

		int distribute = tableWidth-1-this.columnCount;			//this is to be distributed over columns
		int colmin = distribute/this.columnCount;				//this is minimum width of each column
		int leftover = distribute-colmin*this.columnCount;		//leftover

		for(int i=0; i<this.columnCount; i++){
			this.columnWidth[i+1] = colmin;
			if(leftover!=0){
				this.columnWidth[i+1] += 1;
				leftover -= 1;
			}
		}

		this.borders = new boolean[this.columnCount+1];
		for(int i=0; i<this.columnCount+1; i++){
			this.borders[i] = true;
		}
	}

	public TableRowRenderer(int columnCount, Integer[] columns) throws TableException {
		this.columnCount = columnCount;
		this.columnWidth = new Integer[this.columnCount+1];

		if(columns==null || columns.length==0){
			throw new TableException("wrong columns count", "columns array is null or 0");
		}
		if(columns.length<this.columnCount){
			throw new TableException("wrong columns array length", "columns array length must be the same as the columns used to initialise the table");
		}

		this.columnWidth = new Integer[columns.length+1];
		this.columnWidth[0] = 0;
		for(int i=0; i<columns.length; i++){
			if(columns[i]==null || columns[i]<3){
				throw new TableException("errors in columns array", "member " + i + " is either null or smaller than 3 (minimum cloumn width");
			}
			this.columnWidth[0] += columns[i];			//overall width in [0]
			this.columnWidth[i+1] = columns[i];			//shift index by one so that col1=[1]. col2=[2] etc.
		}
		this.columnWidth[0] += columns.length+1;		//for table grid, number of columns + 1

		this.borders = new boolean[this.columnCount+1];
		for(int i=0; i<this.columnCount+1; i++){
			this.borders[i] = true;
		}
	}

	public TableRowRenderer setPaddingChar(char pChar){
		this.paddingChar = pChar;
		return this;
	}

	public TableRowRenderer setTheme(ThemeSimple ts){
		if(ts!=null){
			this.ts = ts;
			this.tb = null;
		}
		return this;
	}

	public TableRowRenderer setBorders(boolean[] borders){
		if(borders!=null && borders.length==this.borders.length){
			this.borders = borders;
		}
		return this;
	}

	public TableRowRenderer setTheme(ThemeTB tb){
		if(tb!=null){
			this.ts = null;
			this.tb = tb;
		}
		return this;
	}

	protected String[][] transformArray(Object[] columns){
		String[][] ar = new String[this.columnCount][];

		for(int i=0; i<columns.length; i++){
			Object o = columns[i];
			ar[i] = ArrayTransformations.WRAP_LINES(this.columnWidth[i+1], o);
		}
		
		ar = ArrayTransformations.NORMALISE_ARRAY(this.columnCount, ar);	//equal number of strings per column
		ar = ArrayTransformations.FLIP_ARRAY(ar);							//flip so that each normalised array row is a table column

		return ar;
	}

	protected final void fixBottomRule(List<TableRow> table){
		switch(table.get(table.size()-1).getType()){
			case MID_EMPH:
				table.remove(table.size()-1);
				table.add(TableRow.create(TableRowType.BOTTOM_EMPH));
				break;
			case MID:
				table.remove(table.size()-1);
				table.add(TableRow.create(TableRowType.BOTTOM));
				break;
			case CONTENT:
				table.add(TableRow.create(TableRowType.BOTTOM));
			default:
				break;
		}
	}

	protected final void fixTopRule(List<TableRow> table){
		switch(table.get(0).getType()){
			case MID_EMPH:
				table.remove(0);
				table.add(0, TableRow.create(TableRowType.TOP_EMPH));
				break;
			case MID:
				table.remove(0);
				table.add(0, TableRow.create(TableRowType.TOP));
				break;
			case CONTENT:
				table.add(0, TableRow.create(TableRowType.TOP));
			default:
				break;
		}
	}

	protected final void adjustColumns(List<TableRow> table){
		for(TableRow row : table){
			if(row.getType().equals(TableRowType.CONTENT)){
				row.setAdjustedColumns(this.transformArray(row.getColumns()));
			}
		}
	}

	protected final void adjustBordersContentRow(List<TableRow> table){
		for(TableRow row : table){
			if(row.getType().equals(TableRowType.CONTENT)){
				AdjustedBorderType[] adjustedBorders = new AdjustedBorderType[this.columnCount+1];
				//left side
				if(this.borders[0]==true){
					adjustedBorders[0]=AdjustedBorderType.CONTENT;
				}
				//in between
				String[] ar = row.getAdjustedColumns()[0];	//only need the first row here
				for(int i=0; i<ar.length; i++){
					String content = ar[i];
					if(content==null){
						if(i==ar.length-1){
							//a null in last column
							adjustedBorders[i+1] = AdjustedBorderType.NONE;
						}
						else{
							//standard null = span
							adjustedBorders[i+1] = AdjustedBorderType.NONE;
						}
					}
					else if("".equals(content)){
						//empty column finishes span
						if(this.borders[i+1]==true){
							adjustedBorders[i+1]=AdjustedBorderType.CONTENT;
						}
					}
					else{
						//all other columns finishing spands
						if(this.borders[i+1]==true){
							adjustedBorders[i+1]=AdjustedBorderType.CONTENT;
						}
					}
				}
				//right side
				if(this.borders[this.columnCount]==true){
					adjustedBorders[this.columnCount]=AdjustedBorderType.CONTENT;
				}
				row.setAdjustedBorders(adjustedBorders);
			}
		}
	}

	protected final void adjustTopRuleBorder(List<TableRow> table){
		switch(table.get(0).getType()){
			case TOP:
			case TOP_EMPH:
				AdjustedBorderType[] adjustedBorders = new AdjustedBorderType[this.borders.length];
				for(int i=0; i<adjustedBorders.length; i++){
					adjustedBorders[i] = AdjustedBorderType.NONE;
				}
				if(table.size()>1){
					AdjustedBorderType[] relAdj = table.get(1).getAdjustedBorders();
					if(relAdj!=null){
						for(int i=0; i<relAdj.length; i++){
							switch(relAdj[i]){
								case NONE:
									adjustedBorders[i] = AdjustedBorderType.NONE;
									break;
								case UP:
									adjustedBorders[i] = AdjustedBorderType.DOWN;
									break;
								case ALL:
									adjustedBorders[i] = AdjustedBorderType.DOWN;
									break;
								case DOWN:
									adjustedBorders[i] = AdjustedBorderType.NONE;
									break;
								case CONTENT:
									adjustedBorders[i] = AdjustedBorderType.DOWN;
									break;
							}
						}
					}
				}
				table.get(0).setAdjustedBorders(adjustedBorders);
				break;
			default:
				break;
		}
	}

	protected final void adjustBottomRuleBorder(List<TableRow> table){
		switch(table.get(table.size()-1).getType()){
			case BOTTOM:
			case BOTTOM_EMPH:
				AdjustedBorderType[] adjustedBorders = new AdjustedBorderType[this.borders.length];
				for(int i=0; i<adjustedBorders.length; i++){
					adjustedBorders[i] = AdjustedBorderType.NONE;
				}
				if(table.size()>1){
					AdjustedBorderType[] relAdj = table.get(table.size()-2).getAdjustedBorders();
					if(relAdj!=null){
						for(int i=0; i<relAdj.length; i++){
							switch(relAdj[i]){
								case NONE:
									adjustedBorders[i] = AdjustedBorderType.NONE;
									break;
								case UP:
									adjustedBorders[i] = AdjustedBorderType.NONE;
									break;
								case ALL:
									adjustedBorders[i] = AdjustedBorderType.UP;
									break;
								case DOWN:
									adjustedBorders[i] = AdjustedBorderType.UP;
									break;
								case CONTENT:
									adjustedBorders[i] = AdjustedBorderType.UP;
									break;
							}
						}
					}
				}
				table.get(table.size()-1).setAdjustedBorders(adjustedBorders);
				break;
			default:
				break;
		}
	}

	protected final void adjustAllRuleBorderAbove(List<TableRow> table){
		if(table.size()>2){
			for(int r=1; r<table.size()-1; r++){
				switch(table.get(r).getType()){
					case MID:
					case MID_EMPH:
						AdjustedBorderType[] adjustedBorders = new AdjustedBorderType[this.borders.length];
						for(int i=0; i<adjustedBorders.length; i++){
							adjustedBorders[i] = AdjustedBorderType.NONE;
						}
						AdjustedBorderType[] relAdj = table.get(r-1).getAdjustedBorders();
						if(relAdj!=null){
							for(int i=0; i<relAdj.length; i++){
								switch(relAdj[i]){
									case NONE:
										adjustedBorders[i] = AdjustedBorderType.NONE;
										break;
									case UP:
										adjustedBorders[i] = AdjustedBorderType.NONE;
										break;
									case ALL:
										adjustedBorders[i] = AdjustedBorderType.UP;
										break;
									case DOWN:
										adjustedBorders[i] = AdjustedBorderType.UP;
										break;
									case CONTENT:
										adjustedBorders[i] = AdjustedBorderType.UP;
										break;
								}
							}
						}
						table.get(r).setAdjustedBorders(adjustedBorders);
						break;

					case BOTTOM:
					case BOTTOM_EMPH:
					case CONTENT:
					case TOP:
					case TOP_EMPH:
						break;
				}
			}
		}
	}

	protected final void adjustAllRuleBorderBelow(List<TableRow> table){
		if(table.size()>2){
			for(int r=1; r<table.size()-1; r++){
				switch(table.get(r).getType()){
					case MID:
					case MID_EMPH:
						AdjustedBorderType[] adjustedBorders = new AdjustedBorderType[this.borders.length];
						for(int i=0; i<adjustedBorders.length; i++){
							adjustedBorders[i] = AdjustedBorderType.NONE;
						}
						AdjustedBorderType[] relAdj = table.get(r+1).getAdjustedBorders();
						if(relAdj!=null){
							for(int i=0; i<relAdj.length; i++){
								switch(relAdj[i]){
									case NONE:
									case DOWN:
										break;
									case CONTENT:
									case ALL:
									case UP:
										if(table.get(r).getAdjustedBorders()[i]==AdjustedBorderType.UP){
											adjustedBorders[i] = AdjustedBorderType.ALL;
										}
										else{
											adjustedBorders[i] = AdjustedBorderType.DOWN;
										}
										break;
								}
							}
						}
						table.get(r).setAdjustedBorders(adjustedBorders);
						break;

					case BOTTOM:
					case BOTTOM_EMPH:
					case CONTENT:
					case TOP:
					case TOP_EMPH:
						break;
				}
			}
		}
	}

	public final void prepareTable(List<TableRow> table){
		this.fixTopRule(table);
		this.fixBottomRule(table);
		this.adjustColumns(table);

		this.adjustBordersContentRow(table);
		this.adjustTopRuleBorder(table);
		this.adjustBottomRuleBorder(table);
		this.adjustAllRuleBorderAbove(table);
		this.adjustAllRuleBorderBelow(table);
	}

	protected char getChar(BorderPosition pos, AdjustedBorderType type, ThemeRow tr){
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

	public StrBuilder renderRow(TableRow row){
		StrBuilder ret=new StrBuilder(100);

		String[][] columns = row.getAdjustedColumns();
		AdjustedBorderType[] borders = row.getAdjustedBorders();
		if(columns!=null){
			//it's a rule row
			for(int i=0; i<columns.length; i++){
				ThemeRow tr = this.ts.getContent();
				if(i!=0){
					ret.appendNewLine();
				}
				int span = 0;
				for(int k=0; k<borders.length; k++){
					if(borders[k]!=AdjustedBorderType.NONE){
						if(k==0){
							ret.append(this.getChar(BorderPosition.LEFT, borders[k], tr));
						}
						else if(k==borders.length-1){
							ret.append(this.getChar(BorderPosition.RIGHT, borders[k], tr));
						}
						else{
							ret.append(this.getChar(BorderPosition.MIDDLE, borders[k], tr));
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
										width += this.columnWidth[s+1];
									}
									//add the separator characters (span) plus the one for this column
									width += span;
									//add the current column width
									width += this.columnWidth[i+1];
									//centre content in the new column
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
									ret.appendFixedWidthPadRight("", this.columnWidth[s+1], this.paddingChar);
								}
								ret.appendFixedWidthPadRight("", span, this.paddingChar);
								span = 0;
								//now add the empty column
								ret.appendFixedWidthPadRight(columns[i][k], this.columnWidth[i+1], this.paddingChar);
							}
							else{
								int width = 0;
								//add the span column width
								for(int s=0; s<span; s++){
									width += this.columnWidth[s+1];
								}
								//add the separator characters (span) plus the one for this column
								width += span;
								//add the current column width
								width += this.columnWidth[i+1];
								//centre content in the new column
								ret.append(StringUtils.center(columns[i][k], width, this.paddingChar));
								span = 0;
//								ret.appendFixedWidthPadRight(columns[i][k], this.columnWidth[i+1], this.paddingChar);
							}
						}
						else{
							ret.appendFixedWidthPadRight(columns[i][k], this.columnWidth[i+1], this.paddingChar);
						}
					}
				}
			}
		}
		else{
			ThemeRow tr = null;
			if(row.getType().equals(TableRowType.TOP)){
				tr=this.ts.getTop();
			}
			else if(row.getType().equals(TableRowType.BOTTOM)){
				tr=this.ts.getBottom();
			}
			else{
				tr=this.ts.getMid();
			}
			for(int k=0; k<borders.length; k++){
				if(k==0){
					ret.append(this.getChar(BorderPosition.LEFT, borders[k], tr));
				}
				else if(k==borders.length-1){
					ret.append(this.getChar(BorderPosition.RIGHT, borders[k], tr));
				}
				else{
					ret.append(this.getChar(BorderPosition.MIDDLE, borders[k], tr));
				}

				if(k+1<this.columnWidth.length){
					ret.appendPadding(this.columnWidth[k+1], tr.getMid());
				}
			}
		}
		return ret;
	}

	public List<StrBuilder> render(List<TableRow> table){
		this.prepareTable(table);

		List<StrBuilder> ret = new LinkedList<StrBuilder>();
		for(TableRow row : table){
			ret.add(this.renderRow(row));
		}
		return ret;
	}
}
