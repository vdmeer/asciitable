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
 * 		<li>{@link de.vandermeer.asciitable.v2.render.V2_TableRenderer#setPaddingChar(char)} - sets the padding character for a renderer. This character is being used to pad all content rows (each line of them).</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.V2_TableRenderer#setWidth(de.vandermeer.asciitable.v2.render.width.V2_Width)} - sets the table width being calculated by the given width object.</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.V2_TableRenderer#setTheme(de.vandermeer.asciitable.v2.themes.V2_TableTheme)} - set the theme the renderer should use to render the table.</li>
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
 * 		Once the type and the position of the row are determined, border charaters can be retrieved from table and row themes. 
 * </p>
 * 
 * <p>
 * 		For instance, a border of type {@link de.vandermeer.asciitable.v2.render.BorderPosition#MIDDLE} and position {@link de.vandermeer.asciitable.v2.render.BorderType#ALL}
 * 		means to use {@code getMidBorderAll()} for a mid-rule row on a {@link de.vandermeer.asciitable.v2.themes.V2_RowTheme} to get the right character.
 * 		For the row theme {@link de.vandermeer.asciitable.v2.themes.V2_E_RowThemes#UTF_DOUBLE_MID} this character will be '╬'.
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
 * 		<li>{@link de.vandermeer.asciitable.v2.render.V2_Utilities#createContentArray(Object[], int[], int[])} - creates a content array for a row that wraps over-size lines, normalizes the row array, and finally flips it for easy rendering,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.V2_Utilities#fixTableRules(de.vandermeer.asciitable.v2.V2_AsciiTable)} - does set the right type for the top and bottom rule of a table,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.V2_Utilities#getBorderTypes_BottomRule(ProcessedRow, de.vandermeer.asciitable.v2.row.V2_Row, int)} - returns the border types for a bottom rule,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.V2_Utilities#getBorderTypes_ContentRow(String[], de.vandermeer.asciitable.v2.row.ContentRow, int)} - returns the border types for a content rule,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.V2_Utilities#getBorderTypes_MidRule(ProcessedRow, ProcessedRow, de.vandermeer.asciitable.v2.row.V2_Row, int)} - returns the border types for a mid rule,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.V2_Utilities#getBorderTypes_TopRule(ProcessedRow, de.vandermeer.asciitable.v2.row.V2_Row, int)} - returns the border types for a top rule,</li>
 * 		<li>{@link de.vandermeer.asciitable.v2.render.V2_Utilities#getChar(BorderPosition, BorderType, de.vandermeer.asciitable.v2.themes.V2_RowTheme)} - returns the actual border character to be used from a row theme for a given border type and position.</li>
 * </ul>
 * 
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 150814 (14-Aug-15) for Java 1.7
 */
package de.vandermeer.asciitable.v2.render;