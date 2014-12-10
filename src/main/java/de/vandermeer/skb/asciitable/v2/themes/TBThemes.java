package de.vandermeer.skb.asciitable.v2.themes;


public enum TBThemes implements ThemeTB {

	/**
	 * LaTeX style ASCII-7 theme with top and mid rules.
	 * <pre>
	 * ================        ================
	 * |    |    |    |                        
	 * ================        ================
	 * |    |    |    |                        
	 * +----+----+----+        ----------------
	 * |    |    |    |                        
	 * +----+----+----+        ----------------
	 * </pre>
	 */
	LATEX_7BIT_EMPH (
			ThemeRow.create('+', '+', '+', '+', '+', '-'),
			ThemeRow.create('=', '=', '=', '=', '=', '='),

			ThemeRow.create('+', '+', '+', '+', '+', '-'),
			ThemeRow.create('=', '=', '=', '=', '=', '='),

			ThemeRow.create('+', '+', '+', '+', '+', '-'),
			ThemeRow.create('=', '=', '=', '=', '=', '='),

			ThemeRow.create('|', '|', '|', '|', '|', ' '),
			"LaTeX style ASCII-7 theme with top and mid rules"
	),
	;

	ThemeRow top;

	ThemeRow topEmph;

	ThemeRow mid;

	ThemeRow midEmph;

	ThemeRow bottom;

	ThemeRow bottomEmph;

	ThemeRow content;

	String description;

	TBThemes(ThemeRow top, ThemeRow topEmph, ThemeRow mid, ThemeRow midEmph, ThemeRow bottom, ThemeRow bottomEmph, ThemeRow content, String description){
		this.top = top;
		this.topEmph = topEmph;

		this.mid = mid;
		this.midEmph = midEmph;

		this.bottom = bottom;
		this.bottomEmph = bottomEmph;

		this.content = content;
		this.description = description;
	}

	@Override
	public ThemeRow getTop() {
		return this.top;
	}

	@Override
	public ThemeRow getTopEmph() {
		return this.topEmph;
	}

	@Override
	public ThemeRow getMid() {
		return this.mid;
	}

	@Override
	public ThemeRow getMidEmph() {
		return this.midEmph;
	}

	@Override
	public ThemeRow getBottom() {
		return this.bottom;
	}

	@Override
	public ThemeRow getBottomEmph() {
		return this.bottomEmph;
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
