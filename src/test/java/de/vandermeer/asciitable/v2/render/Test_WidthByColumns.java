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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.vandermeer.asciitable.v2.render.WidthFixedColumns;

/**
 * Tests for {@link WidthFixedColumns}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v1.0.0 build 160319 (19-Mar-16) for Java 1.7
 */
public class Test_WidthByColumns {

	@Test
	public void testConstructor(){
		WidthFixedColumns cw = new WidthFixedColumns();
		assertEquals(0, cw.ar.length);
	}

	@Test
	public void testAdd(){
		WidthFixedColumns cw = new WidthFixedColumns();

		cw.add(5);
		assertEquals(1, cw.ar.length);
		assertEquals(5, cw.ar[0]);

		cw.add(3);
		assertEquals(2, cw.ar.length);
		assertEquals(5, cw.ar[0]);
		assertEquals(3, cw.ar[1]);

		cw.add(1);//do not add anything smaller than 3
		assertEquals(2, cw.ar.length);
		assertEquals(5, cw.ar[0]);
		assertEquals(3, cw.ar[1]);
	}

}
