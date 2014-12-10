package de.vandermeer.skb.asciitable.v2.themes;

import org.apache.commons.lang3.text.StrBuilder;

public interface ThemeRow {

	char getLeftBorder();

	char getMidBorderAll();

	char getMidBorderUp();

	char getMidBorderDown();

	char getRightBorder();

	char getMid();

	Object getDescription();

	default StrBuilder toDoc(){
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

	static ThemeRow create(final char leftBorder, final char midBorderUp, final char midBorderAll, final char midBorderDown, final char rightBorder, final char mid){
		return ThemeRow.create(leftBorder, midBorderUp, midBorderAll, midBorderDown, rightBorder, mid, null);
	}

	static ThemeRow create(final char leftBorder, final char midBorderUp, final char midBorderAll, final char midBorderDown, final char rightBorder, final char mid, final String description){
		return new ThemeRow(){
			@Override
			public char getLeftBorder() {
				return leftBorder;
			}

			@Override
			public char getMidBorderUp() {
				return midBorderUp;
			}

			@Override
			public char getMidBorderAll() {
				return midBorderAll;
			}

			@Override
			public char getMidBorderDown() {
				return midBorderDown;
			}

			@Override
			public char getRightBorder() {
				return rightBorder;
			}

			@Override
			public char getMid() {
				return mid;
			}

			@Override
			public String getDescription(){
				return (description==null)?"created anonymously":description;
			}
		};
	}
}
