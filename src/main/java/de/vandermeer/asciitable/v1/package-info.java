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
 * An implementation of an ASCII Table with several box character templates.
 * 
 * <p>
 * To see the UTF box drawing characters one will need to have a console (our output window) with UTF-8 support.
 * In a Windows shell (cmd) that should work using a supporting the code page (chcp 65001).
 * On Cygwin, terminal programs such as mintty have UTF support.
 * For both, Lucida Console supports all characters.
 * Apple and Unix systems are usually better in UTF support, please refer to your documentation.
 * In any case, the JVM needs to be set to UTF encoding before the main method is called, "-Dfile.encoding=UTF-8" should do the trick.
 * </p>
 * 
 * <p>
 * When editing the source file, please note that the encoding should be UTF-8, otherwise characters might get lost.
 * In Eclipse set either the properties of the file, the project or the whole workspace to UTF-8 encoding. This will also
 * set the Eclipse console (in Juno and Keppler at least) to print proper UTF characters via <code>System.out</code>,
 * so no special <code>PrintWriter</code> is required here.
 * </p>
 * 
 * <p>
 * When compiling the source file, <code>javac</code> might need a reminder to use UTF encoding.
 * The option "-encoding UTF-8" should do the trick here.
 * </p>
 * 
 * <p>
 * Finally, <code>Javadoc</code> needs to be set to UTF-8 encoding when generating API documentation. The ANT task for <code>Javadoc</code> has three
 * encoding options ("encoding" for source, "docencoding" for output and "charset" for output). Set them all to UTF-8.
 * </p>
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 141210 (10-Dec-14) for Java 1.8
 */
package de.vandermeer.asciitable.v1;