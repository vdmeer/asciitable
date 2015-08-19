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
 * V2 classes to render a table.
 *
 *<p>
 * Most of the classes and enumerates here should be used automatically by a V2 ASCII table.
 * The main external class is the {@link de.vandermeer.asciitable.v2.render.V2_TableRenderer}, which is used to render a V2 ASCII table.
 *</p>
 * 
 * 
 * <h3>The Table Renderer Interface</h3>
 * The {@link de.vandermeer.asciitable.v2.render.V2_TableRenderer} interface provides the base line for realizing a table renderer.
 * It defines a number of methods to set the behavior of a renderer:
 * <ul>
 * 		<li>Set the padding character for a renderer (character used to pad all content rows, each line of them) - {@link de.vandermeer.asciitable.v2.render.V2_TableRenderer#setPaddingChar(char)},</li>
 * 		<li>Set the table width being calculated by the given width object - {@link de.vandermeer.asciitable.v2.render.V2_TableRenderer#setWidth(de.vandermeer.asciitable.v2.render.width.V2_Width)},</li>
 * 		<li>Set the theme the renderer should use to render the table - {@link de.vandermeer.asciitable.v2.render.V2_TableRenderer#setTheme(de.vandermeer.asciitable.v2.themes.V2_TableTheme)}.</li>
 * </ul>
 * 
 * 
 * 
 * <h3>The ASCII Table Renderer Implementation</h3>
 * The class {@link de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer} implements a standard renderer.
 * It is using the theme {@link de.vandermeer.asciitable.v2.themes.V2_E_TableThemes#PLAIN_7BIT} as default theme.
 * 
 * 
 * 
 * <h3>Storing render information per row - {@link de.vandermeer.asciitable.v2.render.ProcessedRow}</h3>
 * <p>
 * 		To enable multiple renderer objects processing the same table each renderer stores all information of the rendering process in a new table (list of rows).
 * 		This new table contains {@link de.vandermeer.asciitable.v2.render.ProcessedRow} objects that contain the render-generated information.
 * 		The original table is not changed (except for adjustment of top and bottom rule row types).
 * 		So each renderer creates its own view of the original table.
 * </p>
 * 
 * <p>
 * 		A processed row contains a link to the original row (for access to original content and configuration)
 * 		plus the generated information for processed columns (wrapped lines, normalized, flipped content array) and border types.
 * </p>
 * 
 * 
 * 
 * <h3>Define a border position</h3>
 * The position of a border element in a table row is defined in {@link de.vandermeer.asciitable.v2.render.BorderPosition} as
 * <ul>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderPosition#LEFT} - a border on the left side of the row,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderPosition#RIGHT} - a border on the right side of the row,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderPosition#MIDDLE} - a border between left and right, in the middle.</li>
 * </ul>
 * 
 * This border position assists a renderer to pick the right character from a row theme.
 * 
 * 
 * 
 * <h3>Define a border type</h3>
 * The type of a border element in a table row is defined in {@link de.vandermeer.asciitable.v2.render.BorderType} as
 * <ul>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderType#UP} - a border that goes up (linked to the previous row) only,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderType#DOWN} - a border that goes down (linked to the next row) only,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderType#ALL} - a border that goes up and down (linked to the previous and to the next row),</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderType#CONTENT} - a border specific to a content row with special linkage to previous and next rows,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.BorderType#NONE} - a border that goes neither up nor down nor is a content border, usually a blank character, used for column spanning.</li>
 * </ul>
 * 
 * This border type assists a renderer to pick the right character from a row theme.
 * 
 * 
 * 
 * <h3>A complete border description</h3>
 * <p>
 * 		Combining border type and border position describes a complete border.
 * 		With both characteristics, the renderer can pick the correct border character from a row or table theme.
 * 		Once the type and the position of the row are determined, border character can be retrieved from table and row themes. 
 * </p>
 * 
 * <p>
 * 		For instance, a border of type {@link de.vandermeer.asciitable.v2.render.BorderPosition#MIDDLE} and position {@link de.vandermeer.asciitable.v2.render.BorderType#ALL}
 * 		means to use {@code getMidBorderAll()} for a mid-rule row on a {@link de.vandermeer.asciitable.v2.themes.V2_RowTheme} to get the right character.
 * 		For the row theme {@link de.vandermeer.asciitable.v2.themes.V2_E_RowThemes#UTF_DOUBLE_MID} this character will be '╬'.
 * </p>
 * 
 * <p>
 * 		Similar, a border of type {@link de.vandermeer.asciitable.v2.render.BorderPosition#RIGHT} and position {@link de.vandermeer.asciitable.v2.render.BorderType#UP}
 * 		means to use {@code getMidBorderAll()} for a bottom-rule row.
 * 		For the row theme {@link de.vandermeer.asciitable.v2.themes.V2_E_RowThemes#UTF_DOUBLE_BOTTOM} this character will be '╝'.
 * </p>
 * 
 * 
 * 
 * <h3>Utilities</h3>
 * The class {@link de.vandermeer.asciitable.v2.render.V2_Utilities} implements a number of utility methods applicable to all (many) renderer implementations:
 * <ul>
 * 		<li>Create a content array for a row that wraps over-size lines, normalizes the row array, and finally flips it for easy rendering - {@link de.vandermeer.asciitable.v2.render.V2_Utilities#createContentArray(Object[], int[], int[])},</li>
 * 		<li>Calculate the border types for a bottom rule - {@link de.vandermeer.asciitable.v2.render.V2_Utilities#getBorderTypes_BottomRule(ProcessedRow, de.vandermeer.asciitable.v2.row.V2_Row, int)},</li>
 * 		<li>Calculate the border types for a content rule - {@link de.vandermeer.asciitable.v2.render.V2_Utilities#getBorderTypes_ContentRow(String[], de.vandermeer.asciitable.v2.row.ContentRow, int)},</li>
 * 		<li>Calculate the border types for a mid rule - {@link de.vandermeer.asciitable.v2.render.V2_Utilities#getBorderTypes_MidRule(ProcessedRow, ProcessedRow, de.vandermeer.asciitable.v2.row.V2_Row, int)},</li>
 * 		<li>Calculate the border types for a top rule - {@link de.vandermeer.asciitable.v2.render.V2_Utilities#getBorderTypes_TopRule(ProcessedRow, de.vandermeer.asciitable.v2.row.V2_Row, int)},</li>
 * 		<li>Determine the actual border character to be used from a row theme for a given border type and position - {@link de.vandermeer.asciitable.v2.render.V2_Utilities#getChar(BorderPosition, BorderType, de.vandermeer.asciitable.v2.themes.V2_RowTheme)}.</li>
 * </ul>
 * 
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 150814 (14-Aug-15) for Java 1.7
 */
package de.vandermeer.asciitable.v2.render;