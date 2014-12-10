package de.vandermeer.asciitable.v2.themes;

import org.apache.commons.lang3.text.StrBuilder;

public interface ThemeSimple {

	ThemeRow getTop();

	ThemeRow getMid();

	ThemeRow getBottom();

	ThemeRow getContent();

	Object getDescription();

	default StrBuilder toDoc(){
		StrBuilder ret = new StrBuilder(50);
		ret
			.append(getTop().toDoc()).appendNewLine()
			.append(getContent().toDoc()).appendNewLine()
			.append(getMid().toDoc()).appendNewLine()
			.append(getContent().toDoc()).appendNewLine()
			.append(getBottom().toDoc()).appendNewLine()
		;
		return ret;
	}

	static ThemeSimple create(final ThemeRow top, final ThemeRow mid, final ThemeRow bottom, final ThemeRow content, final String description){
		return new ThemeSimple(){
			@Override
			public ThemeRow getTop() {
				return top;
			}

			@Override
			public ThemeRow getMid() {
				return mid;
			}

			@Override
			public ThemeRow getBottom() {
				return bottom;
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
