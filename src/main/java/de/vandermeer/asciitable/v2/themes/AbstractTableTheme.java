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

package de.vandermeer.asciitable.v2.themes;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;

/**
 * Abstract {@link TableTheme} implementation.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.7 build 150811 (11-Aug-15) for Java 1.7
 * @since      v0.0.8
 */
class AbstractTableTheme implements TableTheme {

	/** Theme for a bottom row. */
	private RowTheme bottom;

	/** Theme for a bottom row with strong style. */
	private RowTheme bottomStrong;

	/** Theme for a content row. */
	private RowTheme content;

	/** Theme description. */
	private String description;

	/** Theme for a mid row. */
	private RowTheme mid;

	/** Theme for a mid row with strong style. */
	private RowTheme midStrong;

	/** Theme for a top row. */
	private RowTheme top;

	/** Theme for a top row with strong style. */
	private RowTheme topStrong;

	/** A flag indicating if all strong row themes have been defined. */
	private boolean hasStrong;

	/**
	 * Returns a new table theme with the set row themes.
	 * @param top theme for top row, same will be used for strong top rules
	 * @param mid theme for mid row, same will be used for strong mid rules
	 * @param bottom theme for bottom row, same will be used strong bottom rules
	 * @param content theme for content row
	 * @param description descriptive text for the theme
	 * @throws IllegalArgumentException if any of the parameters is null or blank
	 */
	AbstractTableTheme(final RowTheme top, final RowTheme mid, final RowTheme bottom, final RowTheme content, final String description){
		this(top, top, mid, mid, bottom, bottom, content, description);
	}

	/**
	 * Returns a new table theme with the set row themes.
	 * @param top theme for top row
	 * @param topStrong theme for top row for strong style
	 * @param mid theme for mid row
	 * @param midStrong theme for mid row for strong style
	 * @param bottom theme for bottom row
	 * @param bottomStrong theme for bottom row for strong style
	 * @param content theme for content row
	 * @param description descriptive text for the theme
	 * @throws IllegalArgumentException if any of the parameters is null or blank
	 */
	AbstractTableTheme(final RowTheme top, final RowTheme topStrong, final RowTheme mid, final RowTheme midStrong, final RowTheme bottom, final RowTheme bottomStrong, final RowTheme content, final String description){
		if(top==null){
			throw new IllegalArgumentException("top row theme cannot be null");
		}
		if(topStrong==null){
			throw new IllegalArgumentException("top strong row theme cannot be null");
		}
		if(mid==null){
			throw new IllegalArgumentException("mid row theme cannot be null");
		}
		if(midStrong==null){
			throw new IllegalArgumentException("mid strong row theme cannot be null");
		}
		if(bottom==null){
			throw new IllegalArgumentException("bottom row theme cannot be null");
		}
		if(bottomStrong==null){
			throw new IllegalArgumentException("bottom strong row theme cannot be null");
		}
		if(content==null){
			throw new IllegalArgumentException("content row theme cannot be null");
		}
		if(StringUtils.isBlank(description)){
			throw new IllegalArgumentException("theme description cannot be null");
		}

		this.bottom = bottom;
		this.bottomStrong = bottomStrong;
		this.content = content;
		this.description = description;
		this.mid = mid;
		this.midStrong = midStrong;
		this.top = top;
		this.topStrong = topStrong;

		if(top.toDoc().equals(topStrong.toDoc()) && bottom.toDoc().equals(bottomStrong.toDoc()) && mid.toDoc().equals(midStrong.toDoc())){
			this.hasStrong = false;
		}
		else{
			this.hasStrong = true;
		}

	}

	@Override
	public RowTheme getTopStrong() {
		return this.topStrong;
	}

	@Override
	public RowTheme getTop() {
		return this.top;
	}

	@Override
	public RowTheme getMidStrong() {
		return this.midStrong;
	}

	@Override
	public RowTheme getMid() {
		return this.mid;
	}

	@Override
	public Object getDescription() {
		return this.description;
	}

	@Override
	public RowTheme getContent() {
		return this.content;
	}

	@Override
	public RowTheme getBottomStrong() {
		return this.bottomStrong;
	}

	@Override
	public RowTheme getBottom() {
		return this.bottom;
	}

	@Override
	public StrBuilder toDoc() {
		StrBuilder ret = new StrBuilder(50);
		String space = "        ";

		if(this.hasStrong){
			ret.append("Normal           Strong           Example").appendNewLine();
		}
		else{
			ret.append("Normal           Example").appendNewLine();
		}

		if(this.hasStrong){
			ret.append(this.getTop().toDoc()).append(space).append(this.getTopStrong().toDoc());
		}
		else{
			ret.append(this.getTop().toDoc());
		}
		ret.append(space);
		ret.append(this.topStrong.getLeftBorder()).append(this.topStrong.getMid()).append(this.topStrong.getMid()).append(this.topStrong.getMid()).append(this.topStrong.getMid()).append(this.topStrong.getMidBorderDown()).append(this.topStrong.getMid()).append(this.topStrong.getMid()).append(this.topStrong.getMid()).append(this.topStrong.getMid()).append(this.topStrong.getMidBorderDown()).append(this.topStrong.getMid()).append(this.topStrong.getMid()).append(this.topStrong.getMid()).append(this.topStrong.getMid()).append(this.topStrong.getRightBorder());
		ret.appendNewLine();

		if(this.hasStrong){
			ret.append(this.getContent().toDoc()).append(space).append(this.getContent().toDoc());
		}
		else{
			ret.append(this.getContent().toDoc());
		}
		ret.append(space);
		ret.append(this.content.getLeftBorder()).append("h1  ").append(this.content.getMidBorderDown()).append("h2  ").append(this.content.getMidBorderDown()).append("h3  ").append(this.content.getRightBorder());
		ret.appendNewLine();

		if(this.hasStrong){
			ret.append(this.getMid().toDoc()).append(space).append(this.getMidStrong().toDoc());
		}
		else{
			ret.append(this.getMid().toDoc());
		}
		ret.append(space);
		ret.append(this.midStrong.getLeftBorder()).append(this.midStrong.getMid()).append(this.midStrong.getMid()).append(this.midStrong.getMid()).append(this.midStrong.getMid()).append(this.midStrong.getMidBorderAll()).append(this.midStrong.getMid()).append(this.midStrong.getMid()).append(this.midStrong.getMid()).append(this.midStrong.getMid()).append(this.midStrong.getMidBorderAll()).append(this.midStrong.getMid()).append(this.midStrong.getMid()).append(this.midStrong.getMid()).append(this.midStrong.getMid()).append(this.midStrong.getRightBorder());
		ret.appendNewLine();


		if(this.hasStrong){
			ret.append(this.getContent().toDoc()).append(space).append(this.getContent().toDoc());
		}
		else{
			ret.append(this.getContent().toDoc());
		}
		ret.append(space);
		ret.append(this.content.getLeftBorder()).append("c1  ").append(this.content.getMidBorderDown()).append("c2  ").append(this.content.getMidBorderDown()).append("c3  ").append(this.content.getRightBorder());
		ret.appendNewLine();

		if(this.hasStrong){
			ret.append(this.getBottom().toDoc()) .append(space).append(this.getBottomStrong().toDoc());
		}
		else{
			ret.append(this.getBottom().toDoc());
		}
		ret.append(space);
		ret.append(this.mid.getLeftBorder()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMidBorderAll()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMidBorderAll()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getRightBorder());
		ret.appendNewLine();

		if(this.hasStrong){
			ret.append(space).append(space).append(" ");
		}
		ret.append(space).append(space).append(" ");
		ret.append(this.content.getLeftBorder()).append("c1  ").append(this.content.getMidBorderDown()).append("c2  ").append(this.content.getMidBorderDown()).append("c3  ").append(this.content.getRightBorder());
		ret.appendNewLine();

		if(this.hasStrong){
			ret.append(space).append(space).append(" ");
		}
		ret.append(space).append(space).append(" ");
		ret.append(this.mid.getLeftBorder()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMidBorderAll()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMidBorderAll()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getMid()).append(this.mid.getRightBorder());
		ret.appendNewLine();

		if(this.hasStrong){
			ret.append(space).append(space).append(" ");
		}
		ret.append(space).append(space).append(" ");
		ret.append(this.content.getLeftBorder()).append("c1  ").append(this.content.getMidBorderDown()).append("c2  ").append(this.content.getMidBorderDown()).append("c3  ").append(this.content.getRightBorder());
		ret.appendNewLine();

		if(this.hasStrong){
			ret.append(space).append(space).append(" ");
		}
		ret.append(space).append(space).append(" ");
		ret.append(this.bottomStrong.getLeftBorder()).append(this.bottomStrong.getMid()).append(this.bottomStrong.getMid()).append(this.bottomStrong.getMid()).append(this.bottomStrong.getMid()).append(this.bottomStrong.getMidBorderUp()).append(this.bottomStrong.getMid()).append(this.bottomStrong.getMid()).append(this.bottomStrong.getMid()).append(this.bottomStrong.getMid()).append(this.bottomStrong.getMidBorderUp()).append(this.bottomStrong.getMid()).append(this.bottomStrong.getMid()).append(this.bottomStrong.getMid()).append(this.bottomStrong.getMid()).append(this.bottomStrong.getRightBorder());
		ret.appendNewLine();
		;

		return ret;
	}
}
