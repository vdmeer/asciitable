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

package de.vandermeer.asciitable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.text.StrTokenizer;
import org.apache.commons.lang3.tuple.Pair;

import de.vandermeer.asciithemes.TA_GridConfig;
import de.vandermeer.skb.interfaces.document.IsTableRenderer;
import de.vandermeer.skb.interfaces.render.DoesRenderToWidth;
import de.vandermeer.skb.interfaces.render.RendersToClusterWidth;
import de.vandermeer.skb.interfaces.strategies.collections.list.ArrayListStrategy;
import de.vandermeer.skb.interfaces.transformers.ClusterElementTransformer;
import de.vandermeer.skb.interfaces.transformers.Object_To_StrBuilder;
import de.vandermeer.skb.interfaces.transformers.StrBuilder_To_String;
import de.vandermeer.skb.interfaces.transformers.arrays2d.Array2D_To_FlipArray;
import de.vandermeer.skb.interfaces.transformers.arrays2d.Array2D_To_NormalizedArray;
import de.vandermeer.skb.interfaces.transformers.textformat.TextFormat;
import de.vandermeer.skb.interfaces.transformers.textformat.Text_To_FormattedText;

/**
 * Standard renderer for {@link AsciiTable}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.3.0
 */
public interface AT_Renderer extends IsTableRenderer {

	/**
	 * Creates a new renderer.
	 * @return new renderer
	 */
	static AT_Renderer create(){
		return new AT_Renderer(){
			AT_ColumnWidthCalculator cwc = new CWC_AbsoluteEven();
			String lineSeparator = null;

			@Override
			public AT_ColumnWidthCalculator getCWC(){
				return this.cwc;
			}

			@Override
			public String getLineSeparator() {
				return this.lineSeparator;
			}

			@Override
			public AT_Renderer setCWC(AT_ColumnWidthCalculator cwc) {
				if(cwc!=null){
					this.cwc = cwc;
				}
				return this;
			}

			@Override
			public AT_Renderer setLineSeparator(String separator) {
				if(!StringUtils.isBlank(separator)){
					this.lineSeparator = separator;
				}
				return this;
			}
		};
	}

	/**
	 * Returns the column width calculator for the rendered.
	 * @return column width calculator, null if not set
	 */
	default AT_ColumnWidthCalculator getCWC(){
		return null;
	}

	/**
	 * Returns the current set line separator.
	 * @return the line separator, null if none set
	 */
	String getLineSeparator();

	/**
	 * Renders an {@link AsciiTable}.
	 * @param rows table rows to render, cannot be null
	 * @param colNumbers number of columns in the table
	 * @param ctx context of the original table with relevant settings, cannot be null
	 * @return a single string with the rendered table
	 * @throws {@link NullPointerException} if rows or context where null
	 */
	default String render(LinkedList<AT_Row> rows, int colNumbers, AT_Context ctx){
		Validate.notNull(rows);
		Validate.notNull(ctx);
		return this.render(rows, colNumbers, ctx, ctx.getWidth());
	}

	/**
	 * Renders an {@link AsciiTable}.
	 * @param rows table rows to render, cannot be null
	 * @param colNumbers number of columns in the table
	 * @param ctx context of the original table with relevant settings, cannot be null
	 * @param width maximum line width, excluding any extra padding
	 * @return a single string with the rendered table
	 * @throws {@link NullPointerException} if rows or context where null
	 */
	default String render(LinkedList<AT_Row> rows, int colNumbers, AT_Context ctx, int width){
		Validate.notNull(rows);
		Validate.notNull(ctx);

		Collection<StrBuilder> coll = this.renderAsCollection(rows, colNumbers, ctx, width);
		String fileSeparator = this.getLineSeparator();
		if(fileSeparator==null){
			fileSeparator = ctx.getLineSeparator();
		}
		if(fileSeparator==null){
			fileSeparator = System.lineSeparator();
		}
		return new StrBuilder().appendWithSeparators(coll, fileSeparator).build();
	}

	/**
	 * Renders an {@link AsciiTable}.
	 * @param rows table rows to render, cannot be null
	 * @param colNumbers number of columns in the table
	 * @param ctx context of the original table with relevant settings, cannot be null
	 * @return collection of lines, each as a {@link StrBuilder}
	 * @throws {@link NullPointerException} if rows or context where null
	 */
	default Collection<StrBuilder> renderAsCollection(LinkedList<AT_Row> rows, int colNumbers, AT_Context ctx){
		Validate.notNull(rows);
		Validate.notNull(ctx);
		return this.renderAsCollection(rows, colNumbers, ctx, ctx.getWidth());
	}

	/**
	 * Renders an {@link AsciiTable}.
	 * @param rows table rows to render, cannot be null
	 * @param colNumbers number of columns in the table
	 * @param ctx context of the original table with relevant settings, cannot be null
	 * @param width maximum line width, excluding any extra padding
	 * @return collection of lines, each as a {@link StrBuilder}
	 * @throws {@link NullPointerException} if rows or context where null
	 */
	default Collection<StrBuilder> renderAsCollection(LinkedList<AT_Row> rows, int colNumbers, AT_Context ctx, int width){
		Validate.notNull(rows);
		Validate.notNull(ctx);

		ArrayList<Object> table = new ArrayList<>();
		int[] colWidth = this.getCWC().calculateColumnWidths(rows, colNumbers, ctx.getTextWidth(width));

		for(AT_Row row : rows){
			int ruleset = 0;
			switch(row.getStyle()){
				case NORMAL:
					ruleset = TA_GridConfig.RULESET_NORMAL;
					break;
				case STRONG:
					ruleset = TA_GridConfig.RULESET_STRONG;
					break;
				case LIGHT:
					ruleset = TA_GridConfig.RULESET_LIGHT;
					break;
				case HEAVY:
					ruleset = TA_GridConfig.RULESET_HEAVY;
					break;
				case UNKNOWN:
					throw new AsciiTableException("AT_Renderer: cannot render unknown row style", "table row style set to 'unknown'");
				default:
					throw new AsciiTableException("AT_Renderer: cannot render unknown row style", "table row style not specified or type not processed");
			}

			switch(row.getType()){
				case RULE:
					table.add(ruleset);
					break;
				case CONTENT:
					String[][] cAr = new String[colNumbers][];
					LinkedList<AT_Cell> cells = row.getCells();
					if(cells==null){
						throw new AsciiTableException("cannot render table", "row content (cells) was null");
					}

					int length = 0;
					for(int i=0; i<cells.size(); i++){
						length += colWidth[i];

						Object content = cells.get(i).getContent();
						if(content==null){
							length++;
							continue;
						}

						int realWidth = length;
						length -= cells.get(i).getContext().getPaddingLeft();
						length -= cells.get(i).getContext().getPaddingRight();

						if(content instanceof RendersToClusterWidth){
							cAr[i] = ((RendersToClusterWidth)content).renderAsArray(length);
						}
						if(content instanceof DoesRenderToWidth){
							cAr[i] = new StrTokenizer(((DoesRenderToWidth)content).render(length))
									.setDelimiterChar('\n')
									.setIgnoreEmptyTokens(false)
									.getTokenArray()
							;
						}
						else{
							//create text from cell object
							String text = Object_To_StrBuilder.convert(content)
									.toString()
									.replaceAll("\\s+", " ")
							;

							//check for translators, use what is available
							if(cells.get(i).getContext().getTargetTranslator()!=null){
								if(cells.get(i).getContext().getTargetTranslator().getCombinedTranslator()!=null){
									text = cells.get(i).getContext().getTargetTranslator().getCombinedTranslator().translate(text);
								}
							}
							else if(cells.get(i).getContext().getHtmlElementTranslator()!=null){
								text = cells.get(i).getContext().getHtmlElementTranslator().translateHtmlElements(text);
							}
							else if(cells.get(i).getContext().getCharTranslator()!=null){
								text = cells.get(i).getContext().getCharTranslator().translateCharacters(text);
							}

							Collection<StrBuilder> csb = Text_To_FormattedText.create(
									length,
									cells.get(i).getContext().getTextAlignment().getMapping(),
									TextFormat.NONE.getMapping(),
									null, null, null, 0, 0, null, 0, 0, null)
									.transform(text)
							;
							for (StrBuilder sb : csb){
								sb.insert(0, new StrBuilder().appendPadding(cells.get(i).getContext().getPaddingLeft(), cells.get(i).getContext().getPaddingLeftChar()));
								sb.appendPadding(cells.get(i).getContext().getPaddingRight(), cells.get(i).getContext().getPaddingRightChar());
							}
							for(int k=0; k<cells.get(i).getContext().getPaddingTop(); k++){
								((ArrayList<StrBuilder>)csb).add(0, new StrBuilder().appendPadding(realWidth, cells.get(i).getContext().getPaddingTopChar()));
							}
							for(int k=0; k<cells.get(i).getContext().getPaddingBottom(); k++){
								((ArrayList<StrBuilder>)csb).add(new StrBuilder().appendPadding(realWidth, cells.get(i).getContext().getPaddingBottomChar()));
							}

							cAr[i] = ClusterElementTransformer.create().transform(
									csb,
									StrBuilder_To_String.create(),
									ArrayListStrategy.create()
							).toArray(new String[0]);
						}
						length = 0;
					}
					cAr = Array2D_To_NormalizedArray.create(colNumbers).transform(cAr);
					cAr = Array2D_To_FlipArray.create().transform(cAr);
					table.add(Pair.of(ruleset, cAr));
					break;
				case UNKNOWN:
					throw new AsciiTableException("AT_Renderer: cannot render unknown row type", "table row type set to 'unknown'");
				default:
					throw new AsciiTableException("AT_Renderer: cannot render unknown row type", "table row type not specified or type not processed");
			}
		}

		ArrayList<StrBuilder> ret = ctx.getGrid().addGrid(table, ctx.getGridTheme() | ctx.getGridThemeOptions());
		int max = ret.get(0).length() + ctx.getFrameLeftMargin() + ctx.getFrameRightMargin();
		for (StrBuilder sb : ret){
			sb.insert(0, new StrBuilder().appendPadding(ctx.getFrameLeftMargin(), ctx.getFrameLeftChar()));
			sb.appendPadding(ctx.getFrameRightMargin(), ctx.getFrameRightChar());
		}
		for(int k=0; k<ctx.getFrameTopMargin(); k++){
			ret.add(0, new StrBuilder().appendPadding(max, ctx.getFrameTopChar()));
		}
		for(int k=0; k<ctx.getFrameBottomMargin(); k++){
			ret.add(new StrBuilder().appendPadding(max, ctx.getFrameBottomChar()));
		}

		return ret;
	}

	/**
	 * Sets the column width calculator for the rendered.
	 * @param cwc new calculator, ignored if null
	 * @return self to allow for chaining
	 */
	AT_Renderer setCWC(AT_ColumnWidthCalculator cwc);

	/**
	 * Sets a new line separator for the renderer, overwriting any separator a table defines.
	 * @param separator the new separator, ignored if blank
	 * @return self to allow chaining
	 */
	AT_Renderer setLineSeparator(String separator);
}
