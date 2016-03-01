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

import de.vandermeer.asciitable.v2.row.V2_Row;

/**
 * A row processed for rendering.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.5 build 160301 (01-Mar-16) for Java 1.7
 * @since      v0.0.5
 */
public class ProcessedRow {

	/** The original table row with the original content. */
	private V2_Row original;

	/** Processed content of the row (if content row, null otherwise). */
	private String[][] procColumns;

	/** Border types. */
	private BorderType[] borderTypes;

	/**
	 * Returns a new processed row
	 * @param row the original table row with content
	 * @param width the width calculated by {@link V2_Width}
	 * @param colNumber number of columns from the table
	 */
	public ProcessedRow(V2_Row row, int[] width, int colNumber){
		if(row==null){
			throw new IllegalArgumentException("row cannot be null");
		}
		this.original = row;
	}

	/**
	 * Sets the processed columns of this row.
	 * @param ar new processed columns
	 */
	public final void setProcessedColumns(String[][] ar){
		this.procColumns = ar;
	}

	/**
	 * Returns the processed columns of this row.
	 * @return processed columns
	 */
	public final String[][] getProcessedColumns(){
		return this.procColumns;
	}

	/**
	 * Sets the adjusted orders array of this row.
	 * @param ar new adjusted border array
	 */
	public void getBorderTypes(BorderType[] ar){
		this.borderTypes = ar;
	}

	/**
	 * Returns the adjusted orders array of this row.
	 * @return adjusted border array
	 */
	public BorderType[] getBorderTypes(){
		return this.borderTypes;
	}

	/**
	 * Sets the adjusted orders array of this row.
	 * @param array border array
	 */
	public void setBorderTypes(BorderType[] array){
		if(array!=null){
			this.borderTypes = array;
		}
	}

	/**
	 * Returns the original row with the original definitions and content.
	 * @return original row
	 */
	public V2_Row getOriginalRow(){
		return this.original;
	}

}
