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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for column width class.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4 build 150619 (19-Jun-15) for Java 1.8
 */
public class Test_WidthByColumns {

	@Test
	public void testConstructor(){
		WidthByColumns cw = new WidthByColumns();
		assertEquals(1, cw.ar.length);
		assertEquals(0, cw.ar[0]);
	}

	@Test
	public void testAdd(){
		WidthByColumns cw = new WidthByColumns();

		cw.add(5);
		assertEquals(2, cw.ar.length);
		assertEquals(5, cw.ar[0]);
		assertEquals(5, cw.ar[1]);

		cw.add(3);
		assertEquals(3, cw.ar.length);
		assertEquals(8, cw.ar[0]);
		assertEquals(5, cw.ar[1]);
		assertEquals(3, cw.ar[2]);

		cw.add(1);//do not add anything smaller than 3
		assertEquals(3, cw.ar.length);
		assertEquals(8, cw.ar[0]);
		assertEquals(5, cw.ar[1]);
		assertEquals(3, cw.ar[2]);
	}
}
