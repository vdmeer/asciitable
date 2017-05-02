/* Copyright 2016 Sven van der Meer <vdmeer.sven@mykolab.com>
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

package de.vandermeer.asciitable.examples;

import org.junit.Test;

import de.vandermeer.skb.interfaces.StandardExampleRunner;

/**
 * Tests for ASCII Table for code used in documentation.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class Test_Examples implements StandardExampleRunner {

	@Test
	public void test_00_Getting_Started(){
		this.runExample(new AT_00_Getting_Started());
	}

	@Test
	public void test_00b_WidthBehavior(){
		this.runExample(new AT_00b_WidthBehavior());
	}

	@Test
	public void test_00c_AddColumn_ST(){
		this.runExample(new AT_00c_AddColumn_ST());
	}

	@Test
	public void test_00d_AddColumn_HasText(){
		this.runExample(new AT_00d_AddColumn_HasText());
	}

	@Test
	public void test_00e_AddColumn_HasTextCluster(){
		this.runExample(new AT_00e_AddColumn_HasTextCluster());
	}

	@Test
	public void test_00f_AddColumn_DoesRender(){
		this.runExample(new AT_00f_AddColumn_DoesRender());
	}

	@Test
	public void test_00g_AddColumn_DoesRenderToWidth(){
		this.runExample(new AT_00g_AddColumn_DoesRenderToWidth());
	}

	@Test
	public void test_00h_AddColumn_RendersToCluster(){
		this.runExample(new AT_00h_AddColumn_RendersToCluster());
	}

	@Test
	public void test_00i_AddColumn_RendersToClusterWidth(){
		this.runExample(new AT_00i_AddColumn_RendersToClusterWidth());
	}

	@Test
	public void test_01b_1Column(){
		this.runExample(new AT_01b_1Column());
	}

	@Test
	public void test_01c_2Columns(){
		this.runExample(new AT_01c_2Columns());
	}

	@Test
	public void test_01d_3Columns(){
		this.runExample(new AT_01d_3Columns());
	}

	@Test
	public void test_01e_4Columns(){
		this.runExample(new AT_01e_4Columns());
	}
	
	@Test
	public void test_01f_5Columns(){
		this.runExample(new AT_01f_5Columns());
	}

	@Test
	public void test_02_ColSpan(){
		this.runExample(new AT_02_ColSpan());
	}

	@Test
	public void test_03_AlignmentOptions(){
		this.runExample(new AT_03_AlignmentOptions());
	}

	@Test
	public void test_03a_AlignmentTable(){
		this.runExample(new AT_03a_AlignmentTable());
	}

	@Test
	public void test_03b_AlignmentRow(){
		this.runExample(new AT_03b_AlignmentRow());
	}

	@Test
	public void test_03c_AlignmentCell(){
		this.runExample(new AT_03c_AlignmentCell());
	}

	@Test
	public void test_04a_Padding_Table(){
		this.runExample(new AT_04a_Padding_Table());
	}

	@Test
	public void test_04b_Padding_Row(){
		this.runExample(new AT_04b_Padding_Row());
	}

	@Test
	public void test_04c_Padding_Cell(){
		this.runExample(new AT_04c_Padding_Cell());
	}

	@Test
	public void test_05_MarginBehavior(){
		this.runExample(new AT_05_MarginBehavior());
	}

	@Test
	public void test_06a_Grids(){
		this.runExample(new AT_06a_Grids());
	}

	@Test
	public void test_06b_GridRuleStyle(){
		this.runExample(new AT_06b_GridRuleStyle());
	}

	@Test
	public void test_06c_GridThemes(){
		this.runExample(new AT_06c_GridThemes());
	}

	@Test
	public void test_06d_NewGrid(){
		this.runExample(new AT_06d_NewGrid());
	}

	@Test
	public void test_07a_Width_AbsoluteEven(){
		this.runExample(new AT_07a_Width_AbsoluteEven());
	}

	@Test
	public void test_07b_Width_Fixed(){
		this.runExample(new AT_07b_Width_Fixed());
	}

	@Test
	public void test_07c_LongestLine(){
		this.runExample(new AT_07c_LongestLine());
	}

	@Test
	public void test_07d_LongestWord(){
		this.runExample(new AT_07d_LongestWord());
	}

	@Test
	public void test_07e_LongestWordMax(){
		this.runExample(new AT_07e_LongestWordMax());
	}

	@Test
	public void test_07f_LongestWordMin(){
		this.runExample(new AT_07f_LongestWordMin());
	}

	@Test
	public void test_08a_TargetTranslator_Latex(){
		this.runExample(new AT_08a_TargetTranslator_Latex());
	}

	@Test
	public void test_08b_TargetTranslator_HTML(){
		this.runExample(new AT_08b_TargetTranslator_HTML());
	}

	@Test
	public void test_09a_URIs(){
		this.runExample(new AT_09a_URIs());
	}

	@Test
	public void test_09b_ConditionalLinebreak(){
		this.runExample(new AT_09b_ConditionalLinebreak());
	}

	@Test
	public void test_09c_ListWithLinebreaks(){
		this.runExample(new AT_09c_ListWithLinebreaks());
	}

	@Test
	public void test_09d_AsciiList(){
		this.runExample(new AT_09d_AsciiList());
	}
}
