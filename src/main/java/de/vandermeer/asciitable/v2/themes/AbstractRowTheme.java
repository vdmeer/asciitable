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

import org.apache.commons.lang3.text.StrBuilder;

/**
 * Abstract {@link V2_RowTheme} implementation.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.6-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.8
 */
public class AbstractRowTheme implements V2_RowTheme {

	/** Left border character. */
	private char leftBorder;

	/** Mid-up border character. */
	private char midBorderUp;

	/** Mid-all border character. */
	private char midBorderAll;

	/** Mid-down border character. */
	private char midBorderDown;

	/** Right border character. */
	private char rightBorder;

	/** Mid border character. */
	private char mid;

	/** Description of the theme. */
	private String description;

	/**
	 * Creates a new row theme.
	 * @param right character right border
	 * @param left character left border
	 * @param mid character mid 
	 * @param midAll character mid and up and down
	 * @param midUp character mid and up
	 * @param midDown character mid and down
	 * @param description row theme description
	 * @throws TableException if any of the parameters is null or blank
	 */
	AbstractRowTheme(final char right, final char left, final char mid, final char midAll, final char midUp, final char midDown, final String description){
		this.leftBorder = left;
		this.midBorderUp = midUp;
		this.midBorderAll = midAll;
		this.midBorderDown = midDown;
		this.rightBorder = right;
		this.mid = mid;
		this.description = description;

		ThemeValidator.validateRowTheme(this);
	}

	@Override
	public char getLeftBorder() {
		return this.leftBorder;
	}

	@Override
	public char getMidBorderAll() {
		return midBorderAll;
	}

	@Override
	public char getMidBorderUp() {
		return this.midBorderUp;
	}

	@Override
	public char getMidBorderDown() {
		return this.midBorderDown;
	}

	@Override
	public char getRightBorder() {
		return this.rightBorder;
	}

	@Override
	public char getMid() {
		return this.mid;
	}

	@Override
	public Object getDescription() {
		return this.description;
	}

	@Override
	public StrBuilder toDoc() {
		StrBuilder ret = new StrBuilder(10);
		ret
			.append(getLeftBorder())
			.append(getMid())
			.append(getMidBorderUp())
			.append(getMid())
			.append(getMidBorderAll())
			.append(getMid())
			.append(getMidBorderDown())
			.append(getMid())
			.append(getRightBorder())
		;
		return ret;
	}

}
