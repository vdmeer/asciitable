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
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.text.StrTokenizer;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Collections array transformation methods.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.6-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public abstract class ArrayTransformations {

	/**
	 * Takes a 2 dimensional array and returns a string representation in table form.
	 * @param <T> type of the input array
	 * @param ar the array to be transformed
	 * @return a string representation of the array
	 */
	public static final <T> StrBuilder ARRAY_TO_STRING(T[][] ar){
		StrBuilder ret = new StrBuilder(50);
		for(int row=0; row<ar.length; row++){ //TODO not null save
			if(ar[row]==null){
				ret.append("[").append(row).appendln("]: null");
			}
			else if(ar[row].length==0){
				ret.append("[").append(row).appendln("]: 0");
			}
			else{
				for(int col=0; col<ar[row].length; col++){
					ret.append("[").append(row).append("][").append(col).append("]: ");
					if(ar[row][col]==null){
						ret.appendln("null");
					}
					else if("".equals(ar[row][col])){
						ret.appendln("0");
					}
					else{
						ret.appendln(ar[row][col]);
					}
				}
			}
		}
		return ret;
	}


	/**
	 * Flips an array of arrays (a table).
	 * For each cell in the table the row and column indexes are 'turned over', that is for instance the cell [1][2] becomes [2][1].
	 * Consider an input table of
	 * <pre>
	 * row 1: a1, b1, c1
	 * row 2: a2, b2, c2
	 * row 3: a3, b3, c3
	 * </pre>
	 * 
	 * The transformed table will be
	 * <pre>
	 * row 1: a1, a2, a3
	 * row 2: b1, b2, b3
	 * row 3: c1, c2, c3
	 * </pre>
	 * 
	 * @param ar input array which will be flipped
	 * @return flipped array, null if input was null
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
	 * Normalizes an array of strings.
	 * @param length number of columns in the transformed string array
	 * @param ar input array which will be normalized
	 * @return a normalized array
	 */
	public static final String[][] NORMALISE_ARRAY(final int length, String[][] ar){
		int width = 0;
		//get the length of the longest array, use that as width in normalization
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
	 * Takes an object (used as a string) and returns a string array with all processed lines.
	 * The process is as follows:
	 * (1) replace all line breaks (CR LF, CR, LF) into HTML4 line break entity (&lt;br&gt;).
	 * (2) replace all HTML4 line break entities to HTML5 entities (as in self-closing &lt;br/&gt; entity).
	 * (3) use a tokenizer to process the resulting string (not ignoring empty tokens, since they mark required line breaks).
	 * (4) return the array of the tokenizer
	 * 
	 * As a result, a string containing 1 line break will be converted into 2 paragraphs (array length 2):
	 * <pre>{@code
	String: "paragraph 1\nparagraph 2"
	Array:  {paragraph 1,paragraph 2}
	 * }</pre>
	 * A string containing 2 line breaks will be converted into 3 strings (first paragraph, additional line break, second paragraph):
	 * <pre>{@code
	String: "paragraph 1\n\nparagraph 2"
	Array: {paragraph 1,,paragraph 2}
	 * }</pre>
	 * 
	 * @param content the content to process
	 * @return null if content was null, empty array if content was an empty string, string array with lines otherwise
	 */
	public static final String[] PROCESS_CONTENT(Object content){
		if(content==null || content.toString()==null){
			return null;
		}
		if("".equals(content)){
			return new String[]{""};
		}

		String lfRep = StringUtils.replacePattern(content.toString(), "\\r\\n|\\r|\\n", "<br>");
		lfRep = StringUtils.replace(lfRep, "<br>", "<br/>");
		StrTokenizer tok = new StrTokenizer(lfRep, "<br/>").setIgnoreEmptyTokens(false);
		return tok.getTokenArray();
	}

	/**
	 * Takes an content array (of strings) and returns a string array with wrapped lines of max length using {@link #WRAP_LINES(int, Object)}.
	 * The wrapping is done using StringUtils and WordUtils so that words are not broken into characters.
	 * @param length max length of a string in the returned array
	 * @param ar array with one line per array entry, null and empty objects are allowed
	 * @return string array with wrapped strings: null of input object was null or its toString() returned null, empty array if empty string, array with lines of wrappings otherwise
	 */
	public static final String[] WRAP_LINES(final int length, String[] ar){
		if(ar==null){
			return null;
		}

		String[] ret = new String[0];
		for(int i=0; i<ar.length; i++){
			if(StringUtils.isBlank(ar[i])){
				ret = ArrayUtils.add(ret, ar[i]);
				continue;
			}
			ret = ArrayUtils.addAll(ret, WRAP_LINES(length, ar[i]));
		}
		return ret;
	}

	/**
	 * Takes an object (used as a string) and returns a string array with wrapped lines of max length.
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
		return StringUtils.split(WordUtils.wrap(obj.toString(), length, "\n", true), "\n");
	}

}
