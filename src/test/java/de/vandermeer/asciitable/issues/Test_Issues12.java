/* Copyright 2017 Sven van der Meer <vdmeer.sven@mykolab.com>
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

package de.vandermeer.asciitable.issues;

import static de.vandermeer.asciithemes.TA_GridOptions.HAS_CONTENT_LEFT;
import static de.vandermeer.asciithemes.TA_GridOptions.HAS_CONTENT_MID;
import static de.vandermeer.asciithemes.TA_GridOptions.HAS_CONTENT_RIGHT;
import static de.vandermeer.asciithemes.TA_GridOptions.HAS_MID_BORDER_LEFT;
import static de.vandermeer.asciithemes.TA_GridOptions.HAS_MID_BORDER_RIGHT;
import static de.vandermeer.asciithemes.TA_GridOptions.HAS_MID_CONNECTOR;
import static de.vandermeer.asciithemes.TA_GridOptions.HAS_MID_LINE;

import org.junit.Test;

import de.vandermeer.asciitable.AsciiTable;

/**
 * Test for issues 12: https://github.com/vdmeer/asciitable/issues/12.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class Test_Issues12 {

	@Test
	public void test_Issue12(){
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow("cell", "cell");
		at.addRule();
		at.addRow("cell", "cell");
		at.addRule();
		at.addRow("cell", "cell");
		at.addRule();

		int gridTheme = 
				HAS_MID_CONNECTOR | HAS_MID_BORDER_LEFT | HAS_MID_BORDER_RIGHT | HAS_MID_LINE |
				HAS_CONTENT_LEFT | HAS_CONTENT_MID | HAS_CONTENT_RIGHT;
		at.getContext().setGridTheme(gridTheme);

		System.out.println(at.render());
	}
}
