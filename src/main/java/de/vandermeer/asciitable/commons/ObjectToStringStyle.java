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

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.builder.StandardToStringStyle;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A ToStringStyle for objects.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v1.0.0 build 170331 (31-Mar-17) for Java 1.7
 * @since      v0.0.1
 */
public abstract class ObjectToStringStyle {

	/**
	 * Returns a standard toString() style.
	 * @return common style for toString() methods
	 */
	public static final ToStringStyle getStyle(){
		return ObjectToStringStyle.getStyle(0);
	}

	/**
	 * Returns a standard toString() style.
	 * @param indent indentation in number of characters, useful for nested operations
	 * @return common style for toString() methods
	 */
	public static final ToStringStyle getStyle(int indent){
		StandardToStringStyle ret = new StandardToStringStyle();

		ret.setUseShortClassName(true); 		//don't like long class names
		ret.setFieldNameValueSeparator(" = "); 	// some spaces help readability
		ret.setArrayContentDetail(true); 		// arrays w/ details
		ret.setDefaultFullDetail(true);

		String indentation = "";
		for(int i=0; i<= indent; i++){
			indentation += " ";
		}
		ret.setContentStart("[");
		ret.setFieldSeparator(SystemUtils.LINE_SEPARATOR + indentation + "    ");
		ret.setFieldSeparatorAtStart(true);
		ret.setContentEnd(SystemUtils.LINE_SEPARATOR + indentation + "]");

		return ret;
	}

}
