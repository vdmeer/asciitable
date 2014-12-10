package de.vandermeer.asciitable.v2.themes;

public enum StandardRows implements ThemeRow {
	ASC7_SIMPLE				('+', '+', '+', '+', '+', '-', "characters '+' and '-'"),
	ASC7_SIMPLE_CONTENT		('|', '|', '|', '|', '|', ' ', "simple for content"),

	UTF_DOUBLE_TOP		('╔', '═', '═', '╦', '╗', '═', "UTF double characters, top row"),
	UTF_DOUBLE_MID		('╠', '╩', '╬', '╦', '╣', '═', "UTF double characters, mid row"),
	UTF_DOUBLE_BOTTOM	('╚', '╩', '═', '═', '╝', '═', "UTF double characters, bottom row"),
	UTF_DOUBLE_CONTENT	('║', '║', '║', '║', '║', ' ', "UTF double characters, content row"),

	;

	char leftBorder;

	char midBorderUp;

	char midBorderAll;

	char midBorderDown;

	char rightBorder;

	char mid;

	String description;

	StandardRows(char leftBorder, char midBorderUp, char midBorderAll, char midBorderDown, char rightBorder, char mid, String description){
		this.leftBorder = leftBorder;
		this.midBorderUp = midBorderUp;
		this.midBorderAll = midBorderAll;
		this.midBorderDown = midBorderDown;
		this.rightBorder = rightBorder;
		this.mid = mid;
		this.description = description;
	}

	@Override
	public Object getDescription() {
		return this.description;
	}

	@Override
	public char getLeftBorder() {
		return this.leftBorder;
	}

	@Override
	public char getMidBorderAll() {
		return this.midBorderAll;
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
}
