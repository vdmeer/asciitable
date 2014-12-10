package de.vandermeer.asciitable.v2.themes;

import org.apache.commons.lang3.text.StrBuilder;

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
