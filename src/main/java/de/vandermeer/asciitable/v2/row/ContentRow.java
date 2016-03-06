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

package de.vandermeer.asciitable.v2.row;

import org.apache.commons.lang3.builder.ToStringBuilder;

import de.vandermeer.asciitable.commons.ObjectToStringStyle;
import de.vandermeer.asciitable.commons.TableException;

/**
 * A table row representing a content row.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.6-SNAPSHOT build 160304 (04-Mar-16) for Java 1.7
 * @since      v0.2.0
 */
public class ContentRow implements V2_Row {

	/** Array with content if the row is a content row, null otherwise. */
	private Object[] columns;

	/** Array with advice on the alignment of text in a column. */
	private char[] alignment;

	/** Array with advice on padding for each column - padding left/right. */
	private int[] padding;

	/**
	 * Returns a new content table row
	 * @param columns content in form of columns
	 * @param padding the default padding as number of padding characters left and right
	 * @throws TableException if columns are blank
	 */
	public ContentRow(Object[] columns, int padding) throws TableException {
		if(columns==null){
			throw new TableException("wrong columns argument", "empty column array");
		}
		this.columns = columns;

		this.alignment = new char[this.columns.length];
		for(int i=0; i<columns.length; i++){
			this.alignment[i] = 'l';
		}

		this.padding = new int[this.columns.length];
		for(int i=0; i<columns.length; i++){
			this.padding[i] = padding;
		}
	}

	/**
	 * Sets the alignment of text per column for the row.
	 * Use 'l' for left, 'r' for right and 'c' for center alignment. Use 'j' for justified text left bound and 't' for justified text right bound (bounding for the last line). Use ' ' if the original alignment should not be overwritten.
	 * All other characters are not valid.
	 * @param alignment new alignment of columns
	 * @return true on success, false on error (array null or of wrong length or with invalid characters)
	 */
	public boolean setAlignment(char[] alignment){
		if(alignment==null){
			return false;
		}
		if(alignment.length!=this.alignment.length){
			return false;
		}

		for(int i=0; i<alignment.length; i++){
			if(alignment[i]!='l' && alignment[i]!='r' && alignment[i]!='c' && alignment[i]!='j' && alignment[i]!='t' && alignment[i]!=' '){
				return false;
			}
		}

		for(int i=0; i<alignment.length; i++){
			if(alignment[i]!=' '){
				this.alignment[i] = alignment[i];
			}
		}
		return true;
	}

	/**
	 * Sets the left and right padding per column for the row.
	 * Use -1 if the originally set padding should be maintained.
	 * @param padding new alignment array
	 * @return true on success, false on error (array null or of wrong length)
	 */
	public boolean setPadding(int[] padding){
		if(padding==null){
			return false;
		}
		if(padding.length!=this.padding.length){
			return false;
		}

		for(int i=0; i<padding.length; i++){
			if(padding[i]!=-1){
				this.padding[i] = padding[i];
			}
		}
		return true;
	}

	/**
	 * Returns the set alignment for columns.
	 * @return column alignment
	 */
	public char[] getAlignment(){
		return this.alignment;
	}

	/**
	 * Returns the set padding for columns.
	 * @return column padding
	 */
	public int[] getPadding(){
		return this.padding;
	}

	/**
	 * Returns the columns of the row.
	 * @return row columns
	 */
	public Object[] getColumns(){
		return this.columns;
	}

	@Override
	public String toString(int indent){
		ToStringBuilder ret = new ToStringBuilder(this, ObjectToStringStyle.getStyle(indent));
		ret.append("row type       ", "content with " + this.columns.length + " column(s)");
		ret.append("columns        ", this.columns, false);
		ret.append("columns        ", this.columns);

		return ret.toString();
	}

	@Override
	public String toString(){
		return this.toString(0);
	}
}
