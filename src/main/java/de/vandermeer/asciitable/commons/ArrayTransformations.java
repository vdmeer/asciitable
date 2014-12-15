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

package de.vandermeer.asciitable.commons;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Collections of methods to transform arrays.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 141210 (10-Dec-14) for Java 1.8
 */
public abstract class ArrayTransformations {

	/**
	 * Flips a table (array of arrays).
	 * For each cell in the table the row and column indexes are 'turned over', that is for instance the cell [1][2] becomes [2][1].
	 * Consider an input table of
	 * <pre>
	 * row 1: a1, b1, c1
	 * row 2: a2, b2, c2
	 * row 3: a3, b3, c3
	 * </pre>
	 * the transformer will return a table of
	 * <pre>
	 * row 1: a1, a2, a3
	 * row 2: b1, b2, b3
	 * row 3: c1, c2, c3
	 * </pre>
	 * A null table as input results in a null table in the output.
	 * @param ar input array which will be flipped
	 * @return flipped array
	 */
	public static final String[][] FLIP_ARRAY(String[][] ar){
		if(ar==null){
			return null;
		}
		String[][] ret=new String[ar[0].length][ar.length];

		for(int i=0; i<ar[0].length; i++){
			for(int k=0; k<ar.length; k++){
				ret[i][k] = ar[k][i];
			}
		}
		return ret;
	}


	/**
	 * Normalises an array of strings.
	 * @param length number of columns in the transformed string array
	 * @param ar input array which will be normalised
	 * @return a normalised array
	 */
	public static final String[][] NORMALISE_ARRAY(final int length, String[][] ar){
		int width = 0;
		//get the length of the longest array, use that as width in normalisation
		for(int row=0; row<ar.length; row++){ //TODO not null safe
			width = Math.max(width, ArrayUtils.getLength(ar[row]));
		}
		if(width==0){
			width = 1;
		}
		String[][] ret = new String[length][width];

		for(int row=0; row<ar.length; row++){ //not null safe
			if(ar[row]==null){
				for(int i=0; i<width; i++){
					ret[row][i] = null;
				}
			}
			else if(ar[row].length==0){
				for(int i=0; i<width; i++){
					ret[row][i] = "";
				}
			}
			else{
				for(int col=0; col<ar[row].length; col++){
					ret[row][col] = ar[row][col];
				}
				if(ar[row].length<width){
					for(int i=ar[row].length; i<width; i++){
						ret[row][i] = "";
					}
				}
			}
		}
		return ret;
	}

	/**
	 * Takes an objects and returns a string array with wrapped lines of max length.
	 * The wrapping is done using StringUtils and WordUtils so that words are not broken into characters.
	 * @param length max length of a string in the returned array
	 * @param obj input object, null and empty objects are allowed
	 * @return string array with wrapped strings: null of input object was null or its toString() returned null, empty array if empty string, array with lines of wrappings otherwise
	 */
	public static final String[] WRAP_LINES(final int length, Object obj){
		if(obj==null || obj.toString()==null){
			return null;
		}
		if("".equals(obj)){
			return new String[]{};
		}
		return StringUtils.split(WordUtils.wrap(obj.toString(), length, "@@@", true), "@@@");
	}
}
