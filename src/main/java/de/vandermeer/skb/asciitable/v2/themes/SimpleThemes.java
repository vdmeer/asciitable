package de.vandermeer.skb.asciitable.v2.themes;


public enum SimpleThemes implements ThemeSimple {
	/**
	 * A theme for plain ASCII-7 boxes (basically the characters '-', '|' and '+').
	 * <pre>
	 * +-+-+-+-+
	 * | | | | |
	 * +-+-+-+-+
	 * | | | | |
	 * +-+-+-+-+
	 * </pre>
	 */
	PLAIN_7BIT(
			StandardRows.ASC7_SIMPLE,
			StandardRows.ASC7_SIMPLE,
			StandardRows.ASC7_SIMPLE,
			StandardRows.ASC7_SIMPLE_CONTENT,
			"plain ASCII-7 boxes (basically the characters '-', '|' and '+')"
	),

	/**
	 * UTF-8 characters with double lines vertically and horizontally.
	 * <pre>
	 * ╔═════╦═╗
	 * ║ ║ ║ ║ ║
	 * ╠═╩═╬═╦═╣
	 * ║ ║ ║ ║ ║
	 * ╚═╩═════╝
	 * </pre>
	 */
	DOUBLE(
			StandardRows.UTF_DOUBLE_TOP,
			StandardRows.UTF_DOUBLE_MID,
			StandardRows.UTF_DOUBLE_BOTTOM,
			StandardRows.UTF_DOUBLE_CONTENT,
			"UTF-8 characters with double lines vertically and horizontally"
	),
	;

	ThemeRow top;

	ThemeRow mid;

	ThemeRow bottom;

	ThemeRow content;

	String description;

	SimpleThemes(ThemeRow top, ThemeRow mid, ThemeRow bottom, ThemeRow content, String description){
		this.top = top;
		this.mid = mid;
		this.bottom = bottom;

		this.content = content;
		this.description = description;
	}

	@Override
	public ThemeRow getTop() {
		return this.top;
	}

	@Override
	public ThemeRow getMid() {
		return this.mid;
	}

	@Override
	public ThemeRow getBottom() {
		return this.bottom;
	}

	@Override
	public ThemeRow getContent() {
		return this.content;
	}

	@Override
	public Object getDescription() {
		return this.description;
	}
}
