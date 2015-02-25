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
 * TB theme.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 141210 (10-Dec-14) for Java 1.8
 */
public interface ThemeTB extends ThemeSimple {

	ThemeRow getTopEmph();

	ThemeRow getMidEmph();

	ThemeRow getBottomEmph();

	ThemeRow getContent();

	default StrBuilder toDoc(){
		StrBuilder ret = new StrBuilder(50);
		ret
			.append(getTopEmph().toDoc()).appendNewLine()
			.append(getContent().toDoc()).appendNewLine()
			.append(getTopEmph().toDoc()).appendNewLine()
			.append(getContent().toDoc()).appendNewLine()
			.append(getMid().toDoc()).appendNewLine()
			.append(getContent().toDoc()).appendNewLine()
			.append(getBottom().toDoc()).appendNewLine()
		;
		return ret;
	}

	static ThemeTB create(final ThemeRow top, final ThemeRow topEmph, final ThemeRow mid, final ThemeRow midEmph, final ThemeRow bottom, final ThemeRow bottomEmph, final ThemeRow content, final String description){
		return new ThemeTB(){
			@Override
			public ThemeRow getTop() {
				return top;
			}

			@Override
			public ThemeRow getTopEmph() {
				return topEmph;
			}

			@Override
			public ThemeRow getMid() {
				return mid;
			}

			@Override
			public ThemeRow getMidEmph() {
				return midEmph;
			}

			@Override
			public ThemeRow getBottom() {
				return bottom;
			}

			@Override
			public ThemeRow getBottomEmph() {
				return bottomEmph;
			}

			@Override
			public ThemeRow getContent() {
				return content;
			}

			@Override
			public Object getDescription() {
				return description;
			}
		};
	}
}
