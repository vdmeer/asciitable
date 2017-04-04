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
import org.junit.Test;

/**
 * Tests for {@link ArrayTransformations}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v1.0.1 build 170404 (04-Apr-17) for Java 1.7
 * @since      v0.2.2
 */
public class Test_ArrayTransformations {

	@Test
	public void test_LineFeed(){
		String lf;
		String[] ar;

//		lf = "paragraph 1\n\n\nparagraph 2";
//		lfRepl = StringUtils.replacePattern(lf, "\\r\\n|\\r|\\n", "<br>");
//		lfRepl = StringUtils.replace(lfRepl, "<br>", "<br/>");
//
//		StrTokenizer tok = new StrTokenizer(lfRepl, "<br/>").setIgnoreEmptyTokens(false);
//		ar = tok.getTokenArray();
//
//		System.err.println(lfRepl);
//		System.err.println(ArrayUtils.toString(ar));

		lf = "paragraph 1\nparagraph 2";
		ar = ArrayTransformations.WRAP_LINES(100, ArrayTransformations.PROCESS_CONTENT(lf));
		System.err.println(ArrayUtils.toString(ar));

		lf = "paragraph 1\n\nparagraph 2";
		ar = ArrayTransformations.WRAP_LINES(100, ArrayTransformations.PROCESS_CONTENT(lf));
		System.err.println(ArrayUtils.toString(ar));

		lf = "paragraph 1\nparagraph 2";
		ar = ArrayTransformations.WRAP_LINES(5, ArrayTransformations.PROCESS_CONTENT(lf));
		System.err.println(ArrayUtils.toString(ar));

		lf = "paragraph 1\n\nparagraph 2";
		ar = ArrayTransformations.WRAP_LINES(5, ArrayTransformations.PROCESS_CONTENT(lf));
		System.err.println(ArrayUtils.toString(ar));
	}
}
