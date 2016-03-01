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

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.row.ContentRow;
import de.vandermeer.asciitable.v2.row.RuleRow;
import de.vandermeer.asciitable.v2.row.RuleRowType;
import de.vandermeer.asciitable.v2.row.V2_Row;
import de.vandermeer.asciitable.v2.themes.ThemeValidator;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;
import de.vandermeer.asciitable.v2.themes.V2_RowTheme;
import de.vandermeer.asciitable.v2.themes.V2_TableTheme;

/**
 * Renders a table.
 * 
 * Using the renderer is very simple. First, create and fill a table:
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRule();
	at.addRow("1-1", "1-2", "1-3");
	at.addRule();
	at.addRow("2-1", "2-2", "2-3");
	at.addRule();
 * }</pre>
 * 
 * Then create a renderer and set the table width,
 * set the theme to be used (if other than the default theme {@link V2_E_TableThemes#PLAIN_7BIT}) and
 * set the padding character to be used (if other than the default padding character ' '):
 * <pre>{@code
	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthAbsoluteEven().setWidth(25));
	rend.setPaddingChar('_');
 * }</pre>
 * 
 * Finally render the created table, resulting in a rendered table ready to be printed or written to a file:
 * <pre>{@code
	RenderedTable rt = rend.render(at);
	System.out.println(rt);
 * }</pre>
 * 
 * The example above will result in this table printed to standard out:
 * <pre style="line-height:17px">
	┌───────┬───────┬───────┐
	│_1-1___│_1-2___│_1-3___│
	├───────┼───────┼───────┤
	│_2-1___│_2-2___│_2-3___│
	└───────┴───────┴───────┘
 * </pre>
 * 
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.5-SNAPSHOT build 160301 (01-Mar-16) for Java 1.7
 * @since      v0.0.5
 */
public class V2_AsciiTableRenderer implements V2_TableRenderer {

	/** Character used for padding in table columns. */
	char paddingChar;

	/** The theme for the table. */
	V2_TableTheme theme;

	/** Width of the table. */
	V2_Width width;

	/** List of rows processed and ready to b rendered. */
	List<ProcessedRow> rows;

	/**
	 * Returns a new table row renderer.
	 * Default values are:
	 * <ul>
	 * 		<li>Padding character: blank (' ')</li>
	 * 		<li>Theme: plain 7 bit ASCII theme</li>
	 * </ul>
	 */
	public V2_AsciiTableRenderer(){
		this.paddingChar = ' ';
		this.theme = V2_E_TableThemes.PLAIN_7BIT.get();
		this.width = null;
		this.rows = new LinkedList<>();
	}

	@Override
	public V2_AsciiTableRenderer setWidth(V2_Width width){
		if(width!=null){
			this.width = width;
		}
		return this;
	}

	@Override
	public RenderedTable render(V2_AsciiTable table){
		this.rows.clear();
		//nothing to do
		if(table==null || table.getColumnCount()==0){
			throw new IllegalArgumentException("wrong table argument: table is null or has no columns");
		}

		//no width set for table, nothing we can do
		if(this.width==null){
			throw new IllegalArgumentException("wrong table width argument: no width set");
		}

		int[] cols = this.width.getColumnWidths(table);

		//got width, now prepare all table information

		//now create a list of processed table rows
		for(V2_Row row : table.getTable()){
			ProcessedRow pr = new ProcessedRow(row, cols, table.getColumnCount());
			if(row instanceof ContentRow){
				ContentRow crow = (ContentRow)row;
				String[][] procColumns = RenderUtilities.createContentArray(crow.getColumns(), cols, crow.getPadding());
				pr.setProcessedColumns(procColumns);
				pr.setBorderTypes(RenderUtilities.getBorderTypes_ContentRow(procColumns[0], crow, table.getColumnCount()));
			}
			this.rows.add(pr);
		}

		//now adjust borders for top rule
		BorderType[] array = RenderUtilities.getBorderTypes_TopRule((this.rows.size()>1)?this.rows.get(1):null, this.rows.get(0).getOriginalRow(), table.getColumnCount());
		this.rows.get(0).setBorderTypes(array);

		//adjust border for bottom rule
		array = RenderUtilities.getBorderTypes_BottomRule((this.rows.size()>1)?this.rows.get(this.rows.size()-2):null, this.rows.get(this.rows.size()-1).getOriginalRow(), table.getColumnCount());
		this.rows.get(this.rows.size()-1).setBorderTypes(array);

		//and now adjust borders for all mid rules
		if(this.rows.size()>2){
			for(int r=1; r<this.rows.size()-1; r++){
				array = RenderUtilities.getBorderTypes_MidRule(this.rows.get(r-1), (r<this.rows.size()-2)?this.rows.get(r+1):null, this.rows.get(r).getOriginalRow(), table.getColumnCount());
				this.rows.get(r).setBorderTypes(array);
			}
		}

		List<StrBuilder> ret = new LinkedList<StrBuilder>();
		for(int i=0; i<this.rows.size(); i++){
			V2_Row original = this.rows.get(i).getOriginalRow();
			if(original instanceof ContentRow){
				ret.add(this.renderContentRow(this.rows.get(i), cols));
			}
			else if(original instanceof RuleRow){
				if(i==0){
					ret.add(this.renderRuleRow(this.rows.get(i), cols, RuleRowType.TOP));
				}
				else if(i==(this.rows.size()-1)){
					ret.add(this.renderRuleRow(this.rows.get(i), cols, RuleRowType.BOTTOM));
				}
				else{
					ret.add(this.renderRuleRow(this.rows.get(i), cols, null));
				}
			}
			else{
				System.err.println("ERROR in renderering");//TODO
			}
		}

//		for(ProcessedRow row : this.rows){
//			V2_Row original = row.getOriginalRow();
//			if(original instanceof ContentRow){
//				ret.add(this.renderContentRow(row, cols));
//			}
//			else if(original instanceof RuleRow){
//				ret.add(this.renderRuleRow(row, cols, null));
//			}
//			else{
//				System.err.println("ERROR in renderering");//TODO
//			}
//		}

		return new RenderedTable(ret);
	}

	/**
	 * Renders a content row.
	 * @param row processed row to render
	 * @param cols columns calculated by {@link V2_Width}
	 * @return a string builder with the rendered strings (if lines are wrapped) of the rendered row
	 */
	protected final StrBuilder renderContentRow(ProcessedRow row, int[] cols){
		StrBuilder ret = new StrBuilder(100);

		V2_RowTheme rt = null;
		String[][] columns = row.getProcessedColumns();
		BorderType[] borders = row.getBorderTypes();
		char[] alignment = ((ContentRow)row.getOriginalRow()).getAlignment();
		int[] padding = ((ContentRow)row.getOriginalRow()).getPadding();

		for(int i=0; i<columns.length; i++){
			rt = this.theme.getContent();
			if(i!=0){
				ret.appendNewLine();
			}
			int span = 0;
			for(int k=0; k<borders.length; k++){
				if(borders[k]!=BorderType.NONE){
					if(k==0){
						ret.append(RenderUtilities.getChar(BorderPosition.LEFT, borders[k], rt));
					}
					else if(k==borders.length-1){
						ret.append(RenderUtilities.getChar(BorderPosition.RIGHT, borders[k], rt));
					}
					else{
						ret.append(RenderUtilities.getChar(BorderPosition.MIDDLE, borders[k], rt));
					}
				}

				if(k<columns[i].length){
					if(ArrayUtils.contains(columns[i], null)){
						if(columns[i][k]==null){
							if(k==columns[i].length-1){
								//a null in last column, so calculate the span
								int width = 0;
								//add the span column width
								for(int s=0; s<span; s++){
									width += cols[s];
								}
								//add the separator characters (span) plus the one for this column
								width += span;
								//add the current column width
								width += cols[k];
								//Center content in the new column
								ret.appendFixedWidthPadRight("", width, this.paddingChar);
							}
							else{
								span += 1;
							}
						}
						else if("".equals(columns[i][k])){
							//we have an empty column, so
							//first finish the spans
							for(int s=0; s<span; s++){
								ret.appendFixedWidthPadRight("", cols[s], this.paddingChar);
							}
							ret.appendFixedWidthPadRight("", span, this.paddingChar);
							span = 0;
							//now add the empty column
							ret.appendFixedWidthPadRight(columns[i][k], cols[k], this.paddingChar);
						}
						else{
							int width = 0;
							//add the span column width
							for(int s=0; s<span; s++){
								width += cols[s];
							}
							//add the separator characters (span) plus the one for this column
							width += span;
							//add the current column width
							width += cols[k];
							//add row with proper alignment
							this.appendWithAlignment(alignment[k], ret, columns[i][k], width, this.paddingChar, padding[k], (i==(columns.length)));
							span = 0;
						}
					}
					else{
						this.appendWithAlignment(alignment[k], ret, columns[i][k], cols[k], this.paddingChar, padding[k], (i==(columns.length-1)));
					}
				}
			}
		}
		return ret;
	}

	private void appendWithAlignment(char alignment, StrBuilder sb, String str, int width, char paddingChar, int padding, boolean isLastLine){
		if(padding>0){
			width = width - padding*2;
		}
		for(int i=0; i<padding; i++){
			sb.append(paddingChar);
		}

		if('l'==alignment){
			sb.appendFixedWidthPadRight(str, width, paddingChar);
		}
		else if('r'==alignment){
			sb.appendFixedWidthPadLeft(str, width, paddingChar);
		}
		else if('c'==alignment){
			sb.append(StringUtils.center(str, width, paddingChar));
		}
		else if('j'==alignment || 't'==alignment){
			if(isLastLine){
				//nothing needed if this is the last line of a wrapped text
				if('j'==alignment){
					sb.appendFixedWidthPadRight(str, width, paddingChar);
				}
				else{
					//must be 't' now
					sb.appendFixedWidthPadLeft(str, width, paddingChar);
				}
			}
			else{
				String[] ar = StringUtils.split(str);
				int length = 0;
				for(String s : ar){
					length += s.length();
				}

				//first spaces to distributed (even)
				int first = ((width - length) / (ar.length-1)) * (ar.length-1);
				for(int i=0; i<ar.length-1; i++){
					if(first!=0){
						ar[i] += " ";
						first--;
					}
				}

				//second space to distributed (leftovers, as many as there are)
				int second = (width - length) % (ar.length-1);
				for(int i=0; i<ar.length-1; i++){
					if(second!=0){
						ar[i] += " ";
						second--;
					}
				}
				sb.append(StringUtils.join(ar));
			}
		}
		else{
			System.err.println("ERROR RENDER ALIGNMENT");//TODO
		}

		for(int i=0; i<padding; i++){
			sb.append(paddingChar);
		}
	}

	/**
	 * Renders a rule row.
	 * @param row processed row to render
	 * @param cols columns calculated by {@link V2_Width}
	 * @param assumeType is a rule row type the methods has to assume, regardless of the actual set type in the original row
	 * @return a string builder with the rendered string of the rendered row
	 */
	protected final StrBuilder renderRuleRow(ProcessedRow row, int[] cols, RuleRowType assumeType){
		StrBuilder ret = new StrBuilder(100);
		V2_RowTheme rt = null;
		BorderType[] borders = row.getBorderTypes();

		RuleRow original = (RuleRow)row.getOriginalRow();
		RuleRowType type = (assumeType==null)?original.getRuleType():assumeType;

		switch(type){
			case BOTTOM:
				switch(original.getRuleStyle()){
					case NORMAL:
						rt = this.theme.getBottom();
						break;
					case STRONG:
						rt = this.theme.getBottomStrong();
						break;
				}
				break;
			case MID:
				switch(original.getRuleStyle()){
					case NORMAL:
						rt = this.theme.getMid();
						break;
					case STRONG:
						rt = this.theme.getMidStrong();
						break;
				}
				break;
			case TOP:
				switch(original.getRuleStyle()){
					case NORMAL:
						rt = this.theme.getTop();
						break;
					case STRONG:
						rt = this.theme.getTopStrong();
						break;
				}
				break;
		}

		for(int k=0; k<borders.length; k++){
			if(k==0){
				ret.append(RenderUtilities.getChar(BorderPosition.LEFT, borders[k], rt));
			}
			else if(k==borders.length-1){
				ret.append(RenderUtilities.getChar(BorderPosition.RIGHT, borders[k], rt));
			}
			else{
				ret.append(RenderUtilities.getChar(BorderPosition.MIDDLE, borders[k], rt));
			}

			if(k<cols.length){
				ret.appendPadding(cols[k], rt.getMid());
			}
		}

		return ret;
	}

	@Override
	public V2_AsciiTableRenderer setPaddingChar(char pChar){
		this.paddingChar = pChar;
		return this;
	}

	@Override
	public V2_AsciiTableRenderer setTheme(V2_TableTheme theme){
		if(theme!=null){
			ThemeValidator.validateTableTheme(theme);
			this.theme = theme;
		}
		return this;
	}

}
