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
 * New table row interface.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4-SNAPSHOT build 150618 (18-Jun-15) for Java 1.8
 */
public interface TableRow {

	TableRowType getType();

	void setBorder(boolean ... borders);

	boolean[] getBorders();

	void setAdjustedBorders(AdjustedBorderType ... borders);

	AdjustedBorderType[] getAdjustedBorders();

	Object[] getColumns();

	void setAdjustedColumns(String[][] columns);

	String[][] getAdjustedColumns();

	static TableRow create(final TableRowType type){
		return TableRow.create(type, null);
	}

	static TableRow create(final TableRowType type, final Object[] cols){
		return new TableRow(){
			Object[] columns = (cols==null)?new Object[0]:cols;
			boolean[] borders;
			AdjustedBorderType[] adjustedBorders;
			String[][] adjustedColumns;

			@Override
			public TableRowType getType() {
				return type;
			}

			@Override
			public void setBorder(boolean... borders) {
				if(borders!=null){
					this.borders = borders;
				}
			}

			@Override
			public boolean[] getBorders() {
				return this.borders;
			}

			@Override
			public Object[] getColumns() {
				return this.columns;
			}

			@Override
			public void setAdjustedBorders(AdjustedBorderType... borders) {
				this.adjustedBorders = borders;
			}

			@Override
			public AdjustedBorderType[] getAdjustedBorders() {
				return this.adjustedBorders;
			}

			@Override
			public void setAdjustedColumns(String[][] columns) {
				this.adjustedColumns = columns;
			}

			@Override
			public String[][] getAdjustedColumns() {
				return this.adjustedColumns;
			}
		};
	}
}
