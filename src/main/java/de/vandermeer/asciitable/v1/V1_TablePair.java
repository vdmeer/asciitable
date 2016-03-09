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

package de.vandermeer.asciitable.v1;

/**
 * A pair of things, with a left and a right hand side (or left and right).
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.6-SNAPSHOT build 160306 (06-Mar-16) for Java 1.7
 */
public interface V1_TablePair<LHS, RHS> {

	/**
	 * Returns the value of the right hand side of the pair
	 * @return right hand side value
	 */
	RHS rhs();

	/**
	 * Returns the value of the right hand side of the pair
	 * @return right hand side value
	 */
	RHS right();

	/**
	 * Returns the value of the left hand side of the pair
	 * @return left had side value
	 */
	LHS lhs();

	/**
	 * Returns the value of the left hand side of the pair
	 * @return left had side value
	 */
	LHS left();

	/**
	 * Returns the description of an object
	 * @return description
	 */
	String getDescription();

}
