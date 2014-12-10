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
import org.apache.commons.lang3.text.StrBuilder;

/**
 * ToString style for the AsciiTable.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 141210 (10-Dec-14) for Java 1.8
 */
public abstract class Table_ToStringStyle {

	/**
	 * Returns the toString() style.
	 * @return common style for toString() methods
	 */
	public static final ToStringStyle configure(){
		StandardToStringStyle ret = new StandardToStringStyle();

		ret.setUseShortClassName(true); 		//don't like long class names
		ret.setFieldNameValueSeparator(" = "); 	// some spaces help readability
		ret.setArrayContentDetail(true); 		// arrays w/ details
		ret.setDefaultFullDetail(true);

		ret.setContentStart("[");
		ret.setFieldSeparator(SystemUtils.LINE_SEPARATOR + "  ");
		ret.setFieldSeparatorAtStart(true);
		ret.setContentEnd(SystemUtils.LINE_SEPARATOR + "]");
		return ret;
	}

	/** Standard toString() style */
	public static final ToStringStyle TS_STYLE = Table_ToStringStyle.configure();

	/**
	 * Returns a builder using parent class, class and value.
	 * @param parent parent class for the string
	 * @param clazz current class for the string
	 * @param values values for the string, printed comma separated
	 * @return a StrBuilder combining the inputs
	 */
	public static StrBuilder parentKV(Class<?> parent, Class<?> clazz, Object ... values){
		StrBuilder ret=new StrBuilder(50)
			.append(parent.getSimpleName())
			.append('(')
			.append(clazz.getSimpleName())
			.append(')')
			.append(": ")
			.appendWithSeparators(values, ", ");
		;
		return ret;
	}

	/**
	 * Returns a builder using parent class, class and value.
	 * @param parent parent class for the string
	 * @param clazz current class for the string
	 * @param value value for the string, printed comma separated
	 * @return a StrBuilder combining the inputs
	 */
	public static StrBuilder parentKV(Class<?> parent, Class<?> clazz, Object value){
		return Table_ToStringStyle.parentKV(parent, clazz, new Object[]{value});
	}

	/**
	 * Returns a builder using the class and the value
	 * @param clazz current class for the string
	 * @param values values for the string, printed comma separated
	 * @return a StrBuilder combining the inputs
	 */
	public static StrBuilder kv(Class<?> clazz, Object ... values){
		StrBuilder ret=new StrBuilder(50)
			.append(clazz.getSimpleName())
			.append('[')
			.appendWithSeparators(values, ", ")
			.append(']')
		;
		return ret;
	}

	/**
	 * Returns a builder using the class and the value
	 * @param clazz current class for the string
	 * @param value value for the string, printed comma separated
	 * @return a StrBuilder combining the inputs
	 */
	public static StrBuilder kv(Class<?> clazz, Object value){
		return Table_ToStringStyle.kv(clazz, new Object[]{value});
	}
}
