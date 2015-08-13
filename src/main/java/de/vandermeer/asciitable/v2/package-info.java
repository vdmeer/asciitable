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

/**
 * Second generation of an ASCII table.
 * 
 * <br><h3>Features</h3>
 * This table implementation provides the following features:
 * <ul>
 * 		<li>flexible columns - the number of columns in the first content row determine the columns the table supports</li>
 * 		<li>various methods to define column width - overall table width with evenly distributed columns, fixed column width, others can be added if required</li>
 * 		<li>text alignment in columns - left, right, center and two version of justified, configurable per column</li>
 * 		<li>automated text wrapping in columns with automated adjustment of adjacent columns</li>
 * 		<li>padding of columns - number of padding characters before and after text in columns</li>
 * 		<li>spanning columns - a column spanning multiple columns</li>
 * 		<li>renderes to render - a table is rendered by a separate object, called the renderer, with a standard renderer provided</li>
 * 		<li>table row themes - allow to define a theme for a table for for content rows and several rule types, large set of standard themes are defined</li>
 * 		<li>table themes - allow to define a theme for a complete table, some standard themes are provided</li>
 * 		<li>one content row - a standard row for content with alignment and padding</li>
 * 		<li>rule rows with 2 different types - rules are horizontal delimiters, e.g. lines. Rules can be top (first in the table), mid (anywhere in the middle) or bottom (last in the table). Rules can be normal or strong (e.g. extra thick line).</li>
 * </ul>
 * 
 * 
 * <br><h3>Concepts and Realization</h3>
 * <p>
 * 		The main concepts are: table, row, renderer, theme, and rendered table.
 * </p>
 * 
 * <h4>Table</h4>
 * <p>
 * 		A table is a collection of rows.
 * 		The package provides a single implementation of the table {@link de.vandermeer.asciitable.v2.V2_AsciiTable}.
 * 		The implementation allows to add rows and set some general configuration, such as default padding.
 * </p>
 * 
 * <h4>Row</h4>
 * <p>
 * 		A row is either a rule (horizontal delimiter) or a content row (then with columns of content).
 * 		The terminology used here is rule for a rule row and row for a content row.
 * </p>
 * <p>
 * 		The row package defines an interface for a row {@link de.vandermeer.asciitable.v2.row.V2_Row}.
 * 		It also provides two implementations: {@link de.vandermeer.asciitable.v2.row.ContentRow} for content rows
 * 		and {@link de.vandermeer.asciitable.v2.row.RuleRow} for rules.
 * 		Both of those row classes are supported by the provided renderer.
 * 		Other row classes can be added if required, but then special renderers need to proivded as well.
 * </p>
 * 
 * <h4>Renderer</h4>
 * <p>
 * 		A renderer is taking a finalized (filled) table producing a rendered version of it.
 * 		The render package defines a general interface {@link de.vandermeer.asciitable.v2.render.V2_TableRenderer}
 * 		and a default implementation {@link de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer}.
 * 		Other renderers can be created by implementing the interface.
 * </p>
 * <p>
 * 		A renderer is taking a {@link de.vandermeer.asciitable.v2.render.width.V2_Width} object for calculating the width of columns.
 * 		This object is defined as an interface with several implementations provided for different ways of defining column width.
 * 		More of those width object can be defined as required.
 * </p>
 * <p>
 * 		A table can be rendered multiple times by any render object, i.e. the underlying original table will not be changed by the renderer.
 * 		The only change realized is a rewrite of the row type of the top and bottom rows if they are rules, to make sure they are marked as top and bottom rules.
 * 		The renderer is building a list of so called processed rows {@link de.vandermeer.asciitable.v2.render.ProcessedRow}, which old all render-specific settings.
 * </p>
 * <p>
 * 		This feature, render a table any time with any renderer and re-render a table with the same renderer, allows to change settings such as width, themes, padding character.
 * 		It also allows to render a table for different output, such as HTML or LaTeX, of such renderers where provided.
 * </p>
 * 
 * <h4>Theme</h4>
 * <p>
 * 		A theme is either a table theme or a row theme used by the renderer to generate the output.
 * 		The theme package defines both as interfaces: {@link de.vandermeer.asciitable.v2.themes.V2_TableTheme} and {@link de.vandermeer.asciitable.v2.themes.V2_RowTheme}.
 * 		It also provides builders to generate theme objects: {@link de.vandermeer.asciitable.v2.themes.V2_TableThemeBuilder} and {@link de.vandermeer.asciitable.v2.themes.V2_RowThemeBuilder}.
 * 		The builders allow for an easy creation of new themes as required.
 * </p>
 * <p>
 * 		The themes are supported by underlying abstract implementation of their respective interfaces: {@link de.vandermeer.asciitable.v2.themes.AbstractTableTheme} and {@link de.vandermeer.asciitable.v2.themes.AbstractRowTheme}.
 * </p>
 * <p>
 * 		A large number of row themes are defined in {@link de.vandermeer.asciitable.v2.themes.V2_E_RowThemes}, ready to be used.
 * 		A number of table themes are defined in {@link de.vandermeer.asciitable.v2.themes.V2_E_TableThemes}, ready to be used.
 * 		These two enumerates also demonstrate how to define a theme.
 * </p>
 * 
 * <h4>Rendered Table</h4>
 * <p>
 * 		A rendered table is the output of a renderer. This final table can then be printed or written to a file.
 * 		The package implements the rendered table in {@link de.vandermeer.asciitable.v2.RenderedTable}.
 * 		It is essentially a list of {@link org.apache.commons.lang3.text.StrBuilder} objects with an overwritten toString method.
 * </p>
 * 
 * 
 * <br><h3>Standard Usage - create and render a simple table</h3>
 * <p>
 * 		The standard usage is:
 * 		<ul>
 * 			<li>create a table</li>
 * 			<li>add rules and rows</li>
 * 			<li>create a renderer and configure it</li>
 * 			<li>render the table</li>
 * 			<li>use the finally rendered table, e.g. print it to a console or write it to a file</li>
 * 		</ul>
 * 
 * <h4>Create a table</h4>
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
 * }</pre>
 * 
 * <h4>Add content and rule rows</h4>
 * We add a combination of rows and rules and a final rule.
 * <pre>{@code
	at.addRule();
	at.addRow("first row (col1)", "with some information (col2)");
	at.addRule();
	at.addRow("second row (col1)", "with some information (col2)");
	at.addRule();
 * }</pre>
 * 
 * <h4>Create a renderer and configure it</h4>
 * We create the standard renderer and configure it to use the provided table theme {@link de.vandermeer.asciitable.v2.themes.V2_E_TableThemes#UTF_LIGHT}.
 * The we add a width to the renderer using {@link de.vandermeer.asciitable.v2.render.width.V2_WidthByAbsolute} for a table width of 76 characters.
 * <pre>{@code
	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
 * }</pre>
 * 
 * <h4>Render the table</h4>
 * <pre>{@code
	RenderedTable rt = rend.render(at);
 * }</pre>
 * 
 * <h4>Use the finally rendered table</h4>
 * Simply print it to standard out.
 * <pre>{@code
	System.out.println(rt);
 * }</pre>
 * 
 * The will result in the following table being printed to the console:
 * <pre style="line-height:17px">
	┌─────────────────────────────────────┬────────────────────────────────────┐
	│ first row (col1)                    │ with some information (col2)       │
	├─────────────────────────────────────┼────────────────────────────────────┤
	│ second row (col1)                   │ with some information (col2)       │
	└─────────────────────────────────────┴────────────────────────────────────┘
 * </pre>
 * 
 * 
 * <br><h3>Examples for tables with 1 to 5 columns</h3>
 * The following examples show how to create tables with 1 to 5 columns.
 * Each of the tables uses the feature of spanning: if columns for a row are set to {@code null} (not simply empty),
 * they will be treated as columns the next non-null column should span.
 * 
 * <h4>A table with 1 column</h4>
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRule();
	at.addRow(null,"Table Heading");
	at.addRule();
	at.addRow("first row (col1)", "with some information (col2)");
	at.addRule();
	at.addRow("second row (col1)", "with some information (col2)");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	RenderedTable rt = rend.render(at);
	System.out.println(rt);
 * }</pre>
 * 
 * Will print the following table to the standard output:
 * <pre style="line-height:17px">
	┌──────────────────────────────────────────────────────────────────────────┐
	│ Table Heading                                                            │
	├──────────────────────────────────────────────────────────────────────────┤
	│ first row (col1)                                                         │
	├──────────────────────────────────────────────────────────────────────────┤
	│ second row (col1)                                                        │
	└──────────────────────────────────────────────────────────────────────────┘
 * </pre>
 * 
 * <h4>A table with 2 columns</h4>
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRule();
	at.addRow(null,"Table Heading");
	at.addRule();
	at.addRow("first row (col1)", "with some information");
	at.addRule();
	at.addRow("second row (col1)", "with some information (col2)");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	RenderedTable rt = rend.render(at);
	System.out.println(rt);
 * }</pre>
 * 
 * Will print the following table to the standard output:
 * <pre style="line-height:17px">
	┌──────────────────────────────────────────────────────────────────────────┐
	│ Table Heading                                                            │
	├─────────────────────────────────────┬────────────────────────────────────┤
	│ first row (col1)                    │ with some information (col2)       │
	├─────────────────────────────────────┼────────────────────────────────────┤
	│ second row (col1)                   │ with some information (col2)       │
	└─────────────────────────────────────┴────────────────────────────────────┘
 * </pre>
 * 
 * <h4>A table with 3 columns</h4>
 * The following example creates a table with 3 columns:
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRule();
	at.addRow(null, null, "Table Heading");
	at.addRule();
	at.addRow("first row (col1)", "with some information", "and more information");
	at.addRule();
	at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	RenderedTable rt = rend.render(at);
	System.out.println(rt);
 * }</pre>
 * 
 * Will print the following table to the standard output:
 * <pre style="line-height:17px">
	┌──────────────────────────────────────────────────────────────────────────┐
	│ Table Heading                                                            │
	├────────────────────────┬────────────────────────┬────────────────────────┤
	│ first row (col1)       │ with some information  │ and more information   │
	├────────────────────────┼────────────────────────┼────────────────────────┤
	│ second row (col1)      │ with some information  │ and more information   │
	│                        │ (col2)                 │ (col3)                 │
	└────────────────────────┴────────────────────────┴────────────────────────┘
 * </pre>
 * 
 * <h4>A table with 4 columns</h4>
 * The following example creates a table with 4 columns:
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRule();
	at.addRow(null, null, null, "Table Heading");
	at.addRule();
	at.addRow("first row (col1)", "with some information", "and more information", "even more");
	at.addRule();
	at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)", "even more");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * Will print the following table to the standard output:
 * <pre style="line-height:17px">
	┌──────────────────────────────────────────────────────────────────────────┐
	│ Table Heading                                                            │
	├──────────────────┬──────────────────┬──────────────────┬─────────────────┤
	│ first row (col1) │ with some        │ and more         │ even more       │
	│                  │ information      │ information      │                 │
	├──────────────────┼──────────────────┼──────────────────┼─────────────────┤
	│ second row       │ with some        │ and more         │ even more       │
	│ (col1)           │ information      │ information      │                 │
	│                  │ (col2)           │ (col3)           │                 │
	└──────────────────┴──────────────────┴──────────────────┴─────────────────┘
 * </pre>
 * 
 * <h4>A table with 5 columns</h4>
 * The following example creates a table with 5 columns:
 * <pre>{@code
	new V2_AsciiTable();
	at.addRule();
	at.addRow(null, null, null, null, "Table Heading");
	at.addRule();
	at.addRow("first row (col1)", "with some information", "and more information", "even more", "more");
	at.addRule();
	at.addRow("second row (col1)", "with some information (col2)", "and more information (col3)", "even more", "more");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * Will print the following table to the standard output:
 * <pre style="line-height:17px">
	┌──────────────────────────────────────────────────────────────────────────┐
	│ Table Heading                                                            │
	├──────────────┬──────────────┬──────────────┬──────────────┬──────────────┤
	│ first row    │ with some    │ and more     │ even more    │ more         │
	│ (col1)       │ information  │ information  │              │              │
	├──────────────┼──────────────┼──────────────┼──────────────┼──────────────┤
	│ second row   │ with some    │ and more     │ even more    │ more         │
	│ (col1)       │ information  │ information  │              │              │
	│              │ (col2)       │ (col3)       │              │              │
	└──────────────┴──────────────┴──────────────┴──────────────┴──────────────┘
 * </pre>
 * 
 * 
 * <br><h3>Text alignment in columns</h3>
 * Text in columns can be aligned left, right, centered, justified with last line left bound, or justified with last line right bound.
 * The first example shows left, right, and centered.
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRule();
	at.addRow("left", "right", "center").setAlignment(new char[]{'l', 'r', 'c'});
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(80));
	RenderedTable rt = rend.render(at);
	System.out.println(rt);
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	┌──────────────────────────┬─────────────────────────┬─────────────────────────┐
	│ left                     │                   right │         center          │
	└──────────────────────────┴─────────────────────────┴─────────────────────────┘
 * </pre>
 * 
 * The second example shows justified text.
 * The first row justifies the text and has the last line left bound.
 * The second row justifies the text and has the last line right bound.
 * <pre>{@code
		V2_AsciiTable at = new V2_AsciiTable();
		at.addRule();
		at.addRow(new LoremIpsum().getWords()).setAlignment(new char[]{'j'});
		at.addRule();
		at.addRow(new LoremIpsum().getWords()).setAlignment(new char[]{'t'});
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
		rend.setWidth(new V2_WidthByAbsolute().setWidth(60));
		RenderedTable rt = rend.render(at);
		System.out.println(rt);
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	┌──────────────────────────────────────────────────────────┐
	│ Lorem ipsum dolor sit amet, consetetur sadipscing elitr, │
	│ sed  diam  nonumy  eirmod  tempor  invidunt ut labore et │
	│ dolore  magna  aliquyam erat, sed diam voluptua. At vero │
	│ eos  et  accusam  et justo duo dolores et ea rebum. Stet │
	│ clita  kasd gubergren, no sea takimata sanctus est Lorem │
	│ ipsum dolor sit amet.                                    │
	├──────────────────────────────────────────────────────────┤
	│ Lorem ipsum dolor sit amet, consetetur sadipscing elitr, │
	│ sed  diam  nonumy  eirmod  tempor  invidunt ut labore et │
	│ dolore  magna  aliquyam erat, sed diam voluptua. At vero │
	│ eos  et  accusam  et justo duo dolores et ea rebum. Stet │
	│ clita  kasd gubergren, no sea takimata sanctus est Lorem │
	│                                    ipsum dolor sit amet. │
	└──────────────────────────────────────────────────────────┘

 * </pre>
 * <p>
 * 		The example above is using the <a href="http://search.maven.org/#artifactdetails%7Cde.sven-jacobs%7Cloremipsum%7C1.0%7Cjar">loremipsum</a> package to generate the text.
 * </p>
 * 
 * 
 * <br><h3>Column padding</h3>
 * <p>
 * 		Text in rows can be padded.
 * 		Padding means to add padding character before and after the text in the row (the same for both, so it is symetrical).
 * 		The {@link de.vandermeer.asciitable.v2.V2_AsciiTable} uses a default padding of 1.
 * </p>
 * <p>
 * 		The default padding of the table can be changed using {@link de.vandermeer.asciitable.v2.V2_AsciiTable#setDefaultPadding(int)}.
 * 		The padding of individual rows can be set (overwriting default padding) using {@link de.vandermeer.asciitable.v2.row.ContentRow#setPadding(int[])}.
 * 		Increased padding will impact the wrapping of text in columns since it reduces the available space for text in a column.
 * </p>
 * 
 * This example shows 5 rows with different padding and how it impacts line wrapping:
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRule();
	at.addRow("padding 0", "padding 1", "padding 2", "padding 3", "padding 4").setPadding(new int[]{0, 1, 2, 3, 4});
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	RenderedTable rt = rend.render(at);
	System.out.println(rt);
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	┌──────────────┬──────────────┬──────────────┬──────────────┬──────────────┐
	│padding 0     │ padding 1    │  padding 2   │   padding    │    paddin    │
	│              │              │              │   3          │    g 4       │
	└──────────────┴──────────────┴──────────────┴──────────────┴──────────────┘
 * </pre>
 * 
 * 
 * <br><h3>Column spanning</h3>
 * Rows can span columns.
 * This is done by adding columns of {@code null} content to a row followed by a column with content.
 * The column with content will span all previous rows with content {@code null}.
 * The following example creates a table with 5 columns and different column spanning (all to none columns):
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRule();
	at.addRow(null, null, null, null, "span all 5 columns");
	at.addRule();
	at.addRow(null, null, null, "span 4 columns", "just 1 column");
	at.addRule();
	at.addRow(null, null, "span 3 columns", null, "span 2 columns");
	at.addRule();
	at.addRow(null, "span 2 columns", null, null, "span 3 columns");
	at.addRule();
	at.addRow("just 1 column", null, null, null, "span 4 columns");
	at.addRule();
	at.addRow("just 1 column", "just 1 column", "just 1 column", "just 1 column", "just 1 column");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	┌───────────────────────────────────────────────────────────────────────────────┐
	│ span all 5 columns                                                            │
	├───────────────────────────────────────────────────────────────┬───────────────┤
	│ span 4 columns                                                │ just 1 column │
	├───────────────────────────────────────────────┬───────────────┴───────────────┤
	│ span 3 columns                                │ span 2 columns                │
	├───────────────────────────────┬───────────────┴───────────────────────────────┤
	│ span 2 columns                │ span 3 columns                                │
	├───────────────┬───────────────┴───────────────────────────────────────────────┤
	│ just 1 column │ span 4 columns                                                │
	├───────────────┼───────────────┬───────────────┬───────────────┬───────────────┤
	│ just 1 column │ just 1 column │ just 1 column │ just 1 column │ just 1 column │
	└───────────────┴───────────────┴───────────────┴───────────────┴───────────────┘
 * </pre>
 * 
 * 
 * <br><h3>Padding Character</h3>
 * The table renderer can be set to use different padding characters.
 * A padding character is the character used to fill content rows (all their columns) up to the next border.
 * Using UTF-8 characters might not be result in the anticipated result.
 * The following example creates a table with 1 table rendered with the same renderer set for different padding characters:
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRule();
	at.addRow("some text with padding");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
	rend.setPaddingChar('*');
	System.out.println(rend.render(at));
	rend.setPaddingChar('-');
	System.out.println(rend.render(at));
	rend.setPaddingChar('␣');
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	+--------------------------------------------------------------------------+
	| some text with padding                                                   |
	+--------------------------------------------------------------------------+

	+--------------------------------------------------------------------------+
	| some text with padding***************************************************|
	+--------------------------------------------------------------------------+

	+--------------------------------------------------------------------------+
	| some text with padding---------------------------------------------------|
	+--------------------------------------------------------------------------+

	+--------------------------------------------------------------------------+
	| some text with padding␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣|
	+--------------------------------------------------------------------------+
 * </pre>
 * 
 * 
 * <br><h3>Table Theme</h3>
 * The table renderer can be set to use different table themes.
 * A table theme defines all border characters for rules, strong rules and content rows.
 * The following example creates a table with 1 table rendered with the same renderer set for different table themes (using pre-defined themes):
 * <pre>{@code
	V2_AsciiTable at = new V2_AsciiTable();
	at.addRule();
	at.addRow("some column text");
	at.addRule();

	V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
	rend.setWidth(new V2_WidthByAbsolute().setWidth(76));
	System.out.println(rend.render(at));
	rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
	System.out.println(rend.render(at));
	rend.setTheme(V2_E_TableThemes.UTF_DOUBLE_LIGHT.get());
	System.out.println(rend.render(at));
	rend.setTheme(V2_E_TableThemes.UTF_DOUBLE.get());
	System.out.println(rend.render(at));
 * }</pre>
 * 
 * The output of this example will be:
 * <pre style="line-height:17px">
	+--------------------------------------------------------------------------+
	| some column text                                                         |
	+--------------------------------------------------------------------------+

	┌──────────────────────────────────────────────────────────────────────────┐
	│ some column text                                                         │
	└──────────────────────────────────────────────────────────────────────────┘

	╓──────────────────────────────────────────────────────────────────────────╖
	║ some column text                                                         ║
	╙──────────────────────────────────────────────────────────────────────────╜

	╔══════════════════════════════════════════════════════════════════════════╗
	║ some column text                                                         ║
	╚══════════════════════════════════════════════════════════════════════════╝
 * </pre>
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.2 build 150812 (12-Aug-15) for Java 1.7
 */
package de.vandermeer.asciitable.v2;
