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

package de.vandermeer.asciitable.v2.core;

import de.vandermeer.asciitable.commons.ArrayTransformations;

/**
 * A row processed for rendering.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.6 build 150721 (21-Jul-15) for Java 1.7
 * @since      v0.0.5
 */
public class ProcessedRow {

	/** The original table row with the original content. */
	TableRow original;

	/** Processed content of the row (if content row, null otherwise). */
	String[][] procColumns;

	/** Borders adjusted for content rows. */
	E_AdjustedBorderType[] adjustedBorders;

	/**
	 * Returns a new processed row
	 * @param row the original table row with content
	 * @param width the width calculated by {@link Width}
	 */
	public ProcessedRow(TableRow row, int[] width){
		if(row==null){
			throw new IllegalArgumentException("row cannot be null");
		}
		this.original = row;

		this._createContentArray(width);
		this._adjustBordersContentRow();
	}

	/**
	 * Returns the processed columns of this row.
	 * @return processed columns
	 */
	public final String[][] getProcessedColumns(){
		return this.procColumns;
	}

	/**
	 * Returns the adjusted orders array of this row
	 * @return adjusted border array
	 */
	public E_AdjustedBorderType[] getAdjustedBorders(){
		return this.adjustedBorders;
	}

	/**
	 * Returns the original row with the original definitions and content
	 * @return original row
	 */
	public TableRow getOriginalRow(){
		return this.original;
	}

	/**
	 * Creates a content array with wrapped lines from the original row content
	 * @param width the width calculated by {@link Width}
	 */
	private final void _createContentArray(int[] width){
		if(this.original.isContent()){
			this.procColumns = new String[width.length-1][];
			for(int i=0; i<this.original.columns.length; i++){
				Object o = this.original.columns[i];
				this.procColumns[i] = ArrayTransformations.WRAP_LINES(width[i+1], o);
			}
			//equal number of strings per column
			this.procColumns = ArrayTransformations.NORMALISE_ARRAY(width.length-1, this.procColumns);
			//flip so that each normalized array row is a table column
			this.procColumns = ArrayTransformations.FLIP_ARRAY(this.procColumns);
		}
		else{
			this.procColumns = null;
		}
	}

	/**
	 * Creates adjusted borders for a content row.
	 */
	private final void _adjustBordersContentRow(){
		if(this.original.isContent()){
			this.adjustedBorders = new E_AdjustedBorderType[this.original.columns.length+1];

			//left side
			if(this.original.borders[0]==true){
				this.adjustedBorders[0] = E_AdjustedBorderType.CONTENT;
			}

			//right side
			if(this.original.borders[this.original.columns.length]==true){
				this.adjustedBorders[this.original.columns.length] = E_AdjustedBorderType.CONTENT;
			}

			//in between
			String[] ar = this.procColumns[0];	//only need the first row here
			for(int i=0; i<ar.length; i++){
				String content = ar[i];
				if(content==null){
					if(i==ar.length-1){
						//a null in last column
						this.adjustedBorders[i+1] = E_AdjustedBorderType.NONE;
					}
					else{
						//standard null = span
						this.adjustedBorders[i+1] = E_AdjustedBorderType.NONE;
					}
				}
				else if("".equals(content)){
					//empty column finishes span
					if(this.original.borders[i+1]==true){
						this.adjustedBorders[i+1] = E_AdjustedBorderType.CONTENT;
					}
				}
				else{
					//all other columns finishing spans
					if(this.original.borders[i+1]==true){
						this.adjustedBorders[i+1] = E_AdjustedBorderType.CONTENT;
					}
				}
			}
		}
	}

	/**
	 * Adjusts the a row considered to be a top row.
	 * Use this only for the top row of a table.
	 * If the row is a rule and of type {@link E_RuleType#TOP} an adjusted border array will be created
	 * @param next the row following the top row
	 */
	public final void adjustTopRuleBorder(ProcessedRow next){
		if(this.original.isRule()){
			switch(this.original.ruleType){
				case TOP:
					this.adjustedBorders = new E_AdjustedBorderType[this.original.borders.length];
					for(int i=0; i<this.adjustedBorders.length; i++){
						this.adjustedBorders[i] = E_AdjustedBorderType.NONE;
					}

					if(next!=null){
						E_AdjustedBorderType[] relAdj = next.adjustedBorders;
						if(relAdj!=null){
							for(int i=0; i<relAdj.length; i++){
								switch(relAdj[i]){
									case NONE:
										this.adjustedBorders[i] = E_AdjustedBorderType.NONE;
										break;
									case UP:
										this.adjustedBorders[i] = E_AdjustedBorderType.DOWN;
										break;
									case ALL:
										this.adjustedBorders[i] = E_AdjustedBorderType.DOWN;
										break;
									case DOWN:
										this.adjustedBorders[i] = E_AdjustedBorderType.NONE;
										break;
									case CONTENT:
										this.adjustedBorders[i] = E_AdjustedBorderType.DOWN;
										break;
								}
							}
						}
					}
					break;
				default:
					break;
			}
		}
	}


	/**
	 * Adjusts the a row considered to be a bottom row.
	 * Use this only for the bottom row of a table.
	 * If the row is a rule and of type {@link E_RuleType#BOTTOM} an adjusted border array will be created
	 * @param prev the row previous the bottom row
	 */
	public final void adjustBottomRuleBorder(ProcessedRow prev){
		if(this.original.isRule()){
			switch(this.original.ruleType){
				case BOTTOM:
					this.adjustedBorders = new E_AdjustedBorderType[this.original.borders.length];
					for(int i=0; i<adjustedBorders.length; i++){
						adjustedBorders[i] = E_AdjustedBorderType.NONE;
					}

					if(prev!=null){
						E_AdjustedBorderType[] relAdj = prev.adjustedBorders;
						if(relAdj!=null){
							for(int i=0; i<relAdj.length; i++){
								switch(relAdj[i]){
									case NONE:
										this.adjustedBorders[i] = E_AdjustedBorderType.NONE;
										break;
									case UP:
										this.adjustedBorders[i] = E_AdjustedBorderType.NONE;
										break;
									case ALL:
										this.adjustedBorders[i] = E_AdjustedBorderType.UP;
										break;
									case DOWN:
										this.adjustedBorders[i] = E_AdjustedBorderType.UP;
										break;
									case CONTENT:
										this.adjustedBorders[i] = E_AdjustedBorderType.UP;
										break;
								}
							}
						}
					}
					break;
				default:
					break;
			}
		}
	}

	/**
	 * Adjusts the borders for mid rule rows.
	 * @param prev the row previous to the rule row (null if not applicable)
	 * @param next the row next to the rule row (null if not applicable)
	 */
	public final void adjustMidRuleBorder(ProcessedRow prev, ProcessedRow next){
		if(this.original.isRule()){
			switch(this.original.ruleType){
				case MID:
					this.adjustedBorders = new E_AdjustedBorderType[this.original.borders.length];
					for(int i=0; i<this.adjustedBorders.length; i++){
						this.adjustedBorders[i] = E_AdjustedBorderType.NONE;
					}

					//first set everything against the previous row (if any)
					if(prev!=null){
						E_AdjustedBorderType[] relAdj = prev.adjustedBorders;
						if(relAdj!=null){
							for(int i=0; i<relAdj.length; i++){
								switch(relAdj[i]){
									case NONE:
										this.adjustedBorders[i] = E_AdjustedBorderType.NONE;
										break;
									case UP:
										this.adjustedBorders[i] = E_AdjustedBorderType.NONE;
										break;
									case ALL:
										this.adjustedBorders[i] = E_AdjustedBorderType.UP;
										break;
									case DOWN:
										this.adjustedBorders[i] = E_AdjustedBorderType.UP;
										break;
									case CONTENT:
										this.adjustedBorders[i] = E_AdjustedBorderType.UP;
										break;
								}
							}
						}
					}

					//next set anything against the next row (if any)
					if(next!=null){
						E_AdjustedBorderType[] relAdj = next.adjustedBorders;
						if(relAdj!=null){
							for(int i=0; i<relAdj.length; i++){
								switch(relAdj[i]){
									case NONE:
									case DOWN:
										break;
									case CONTENT:
									case ALL:
									case UP:
										if(this.adjustedBorders[i]==E_AdjustedBorderType.UP){
											this.adjustedBorders[i] = E_AdjustedBorderType.ALL;
										}
										else{
											this.adjustedBorders[i] = E_AdjustedBorderType.DOWN;
										}
										break;
								}
							}
						}
					}
					break;
				default:
					break;
			}
		}
	}
}
