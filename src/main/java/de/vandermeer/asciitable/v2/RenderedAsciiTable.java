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

import java.util.List;

import org.apache.commons.lang3.text.StrBuilder;

/**
 * A fully rendered table ready to be printed or written to files.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.7 build 150811 (11-Aug-15) for Java 1.7
 * @since      v0.0.5
 */
public class RenderedAsciiTable {

	/** List of rendered rows. */
	List<StrBuilder> rows;

	/**
	 * Returns a new rendered table initialized with a list of rendered rows.
	 * @param rows rendered rows
	 */
	public RenderedAsciiTable(List<StrBuilder> rows){
		this.rows = rows;
	}

	@Override
	public String toString(){
		String ret = new String();
		for(StrBuilder sb : this.rows){
			ret += sb.toString() + "\n";
		}
		return ret;
	}

	/**
	 * Returns the rendered table as a single string builder
	 * @return string builder with rendered table
	 */
	public StrBuilder toStrBuilder(){
		StrBuilder ret = new StrBuilder(100);
		for(StrBuilder sb : this.rows){
			ret.append(sb).appendNewLine();
		}
		return ret;
	}
}
