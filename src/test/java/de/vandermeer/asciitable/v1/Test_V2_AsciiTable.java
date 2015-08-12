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

package de.vandermeer.asciitable.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.vandermeer.asciitable.v1.V1_AsciiTable;

/**
 * Tests for {@link V2_AsciiTable}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 150812 (12-Aug-15) for Java 1.7
 */
public class Test_V2_AsciiTable {

	@Test
	public void testInitColumns(){
		V1_AsciiTable at=new V1_AsciiTable();

		assertFalse(at._init(null));
		assertNull(at.getColumnAr());

		assertFalse(at._init(new Integer[]{}));
		assertNull(at.getColumnAr());

		assertFalse(at._init(new Integer[]{5, null}));
		assertNull(at.getColumnAr());

		assertFalse(at._init(new Integer[]{5, 0}));
		assertNull(at.getColumnAr());

		assertFalse(at._init(new Integer[]{5, 1}));
		assertNull(at.getColumnAr());

		assertFalse(at._init(new Integer[]{5, 2}));
		assertNull(at.getColumnAr());

		assertFalse(at._init(new Integer[]{null}));
		assertNull(at.getColumnAr());

		assertFalse(at._init(new Integer[]{0}));
		assertNull(at.getColumnAr());

		assertFalse(at._init(new Integer[]{1}));
		assertNull(at.getColumnAr());

		assertFalse(at._init(new Integer[]{2}));
		assertNull(at.getColumnAr());

		assertTrue(at._init(new Integer[]{3}));
		assertEquals(2, at.getColumnAr().length);
		assertEquals(at.getColumnAr()[0].intValue(), this.sum(at.getColumnAr()));
		assertEquals(5, at.getColumnAr()[0].intValue());
		assertEquals(3, at.getColumnAr()[1].intValue());

		assertTrue(at._init(new Integer[]{9, 8, 9}));
		assertEquals(4, at.getColumnAr().length);
		assertEquals(at.getColumnAr()[0].intValue(), this.sum(at.getColumnAr()));
		assertEquals(30, at.getColumnAr()[0].intValue());
		assertEquals( 9, at.getColumnAr()[1].intValue());
		assertEquals( 8, at.getColumnAr()[2].intValue());
		assertEquals( 9, at.getColumnAr()[3].intValue());

		assertTrue(at._init(new Integer[]{10, 10, 10}));
		assertEquals(4, at.getColumnAr().length);
		assertEquals(at.getColumnAr()[0].intValue(), this.sum(at.getColumnAr()));
		assertEquals(34, at.getColumnAr()[0].intValue());
		assertEquals(10, at.getColumnAr()[1].intValue());
		assertEquals(10, at.getColumnAr()[2].intValue());
		assertEquals(10, at.getColumnAr()[3].intValue());
	}

	@Test
	public void testInitColAndWidth(){
		V1_AsciiTable at=new V1_AsciiTable();

		assertFalse(at._init(null, null));
		assertNull(at.getColumnAr());

		assertFalse(at._init(100, null));
		assertNull(at.getColumnAr());

		assertFalse(at._init(null, 100));
		assertNull(at.getColumnAr());

		assertFalse(at._init(0, null));
		assertNull(at.getColumnAr());

		assertFalse(at._init(1, 1));
		assertNull(at.getColumnAr());

		assertFalse(at._init(1, 2));
		assertNull(at.getColumnAr());

		assertFalse(at._init(1, 3));
		assertNull(at.getColumnAr());

		assertFalse(at._init(1, 4));
		assertNull(at.getColumnAr());

		assertFalse(at._init(3, 12));
		assertNull(at.getColumnAr());

		assertFalse(at._init(100, 400));
		assertNull(at.getColumnAr());

		assertTrue(at._init(3, 13));
		assertEquals(4, at.getColumnAr().length);
		assertEquals(at.getColumnAr()[0].intValue(), this.sum(at.getColumnAr()));
		assertEquals(13, at.getColumnAr()[0].intValue());
		assertEquals( 3, at.getColumnAr()[1].intValue());
		assertEquals( 3, at.getColumnAr()[2].intValue());
		assertEquals( 3, at.getColumnAr()[3].intValue());

		assertTrue(at._init(3, 14));
		assertEquals(4, at.getColumnAr().length);
		assertEquals(at.getColumnAr()[0].intValue(), this.sum(at.getColumnAr()));
		assertEquals(14, at.getColumnAr()[0].intValue());
		assertEquals( 4, at.getColumnAr()[1].intValue());
		assertEquals( 3, at.getColumnAr()[2].intValue());
		assertEquals( 3, at.getColumnAr()[3].intValue());

		assertTrue(at._init(3, 15));
		assertEquals(4, at.getColumnAr().length);
		assertEquals(at.getColumnAr()[0].intValue(), this.sum(at.getColumnAr()));
		assertEquals(15, at.getColumnAr()[0].intValue());
		assertEquals( 4, at.getColumnAr()[1].intValue());
		assertEquals( 4, at.getColumnAr()[2].intValue());
		assertEquals( 3, at.getColumnAr()[3].intValue());

		assertTrue(at._init(3, 16));
		assertEquals(4, at.getColumnAr().length);
		assertEquals(at.getColumnAr()[0].intValue(), this.sum(at.getColumnAr()));
		assertEquals(16, at.getColumnAr()[0].intValue());
		assertEquals( 4, at.getColumnAr()[1].intValue());
		assertEquals( 4, at.getColumnAr()[2].intValue());
		assertEquals( 4, at.getColumnAr()[3].intValue());
	}

	@Test
	public void testAddRow(){
		V1_AsciiTable at;

		at=new V1_AsciiTable();
		assertEquals(-1, at.addRow((Object)null));			//no columns set, always -1

		at=V1_AsciiTable.newTable(3, 76);
		assertNotNull(at);

		assertEquals(0, at.addRow((Object)null));			//null not good
		assertEquals(0, at.addRow());						//empty array, not good
		assertEquals(0, at.addRow(new Object[]{}));			//empty array, not good
		assertEquals(0, at.addRow(""));						//less than col count
		assertEquals(0, at.addRow("", ""));					//less than col count
		assertEquals(0, at.addRow("", "", "", ""));			//more than col count
		assertEquals(0, at.addRow("", "", "", "", ""));		//more than col count

		assertEquals(1, at.addRow(null, null, null));		//count ok, null not a problem
		assertEquals(1, at.table.size());

		assertEquals(2, at.addRow("", "", ""));				//count ok, "" not a problem
		assertEquals(2, at.table.size());

		assertEquals(3, at.addRow("column 1 string, well in range", "column 2 string, well in range", "column 3 string, well in range"));
		assertEquals(3, at.table.size());
	}

//	@Test
//	public void testSetRowFormat(){
//		AsciiTable at;
//
//		at=new AsciiTable();
//		assertEquals(-1, at.setRowFormats(0, (Character)null));			//no columns set, always -1
//
//		at=AsciiTable.newTable(3, 76);
//		assertNotNull(at);
//
//		assertEquals(-1, at.setRowFormats(0, (Character)null));			//row key does not exist
//		assertEquals(-1, at.setRowFormats(1, (Character)null));			//row key does not exist
//		assertEquals(-1, at.setRowFormats(2, (Character)null));			//row key does not exist
//
//		//add some rows to play with (do assert the calls, doesn't hurt)
//		assertEquals(1, at.addRow(null, null, null));
//		assertEquals(2, at.addRow("", "", ""));
//		assertEquals(3, at.addRow("column 1 string, well in range", "column 2 string, well in range", "column 3 string, well in range"));
//
//		assertEquals(0, at.setRowFormats(1, (Character)null));			//row exists, but characters are not good (must be 3 in total)
//		assertEquals(0, at.setRowFormats(2));							//row exists, but characters are not good (must be 3 in total)
//		assertEquals(0, at.setRowFormats(3, new Character[]{}));		//row exists, but characters are not good (must be 3 in total)
//
//		assertEquals(0, at.setRowFormats(1, '1'));						//row exists, but characters are not good (must be 3 in total)
//		assertEquals(0, at.setRowFormats(1, '1', '2'));					//row exists, but characters are not good (must be 3 in total)
//		assertEquals(0, at.setRowFormats(2, '1', '2', '3', '4'));		//row exists, but characters are not good (must be 3 in total)
//		assertEquals(0, at.setRowFormats(2, '1', '2', '3', '4', '4'));	//row exists, but characters are not good (must be 3 in total)
//
//		assertEquals(1, at.setRowFormats(1, '1', '2', '3'));
//		assertEquals(2, at.setRowFormats(2, '1', '2', '3'));
//		assertEquals(3, at.setRowFormats(3, '1', '2', '3'));
//	}

	private int sum(Integer[] ar){
		if(ar==null){
			return 0;
		}
		int ret=0;
		for(int i=1;i<ar.length;i++){
			if(ar[i]!=null){
				ret+=ar[i];
			}
		}
		ret+=ar.length;		//no +1 because ar is 1 over #columns
		return ret;
	}

}
