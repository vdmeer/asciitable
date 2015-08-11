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
 * Factory for common table artifacts.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.7 build 150811 (11-Aug-15) for Java 1.7
 */
public abstract class V1_Factory {

	/**
	 * Returns a new Pair of given type with default description.
	 * @param <LHS> type for left hand site
	 * @param <RHS> type for right hand site
	 * @param rhs right hand side of the pair
	 * @param lhs left hand side of the pair
	 * @return new pair
	 */
	public static <LHS, RHS> V1_TablePair<LHS, RHS> createTablePair(LHS lhs, RHS rhs){
		return V1_Factory.createTablePair(lhs, rhs, "abstract IsPath implementation");
	}

	/**
	 * Returns a new Pair of given type with given description.
	 * @param <LHS> type for left hand site
	 * @param <RHS> type for right hand site
	 * @param rhs right hand side of the pair
	 * @param lhs left hand side of the pair
	 * @param description description of the pair
	 * @return new pair
	 */
	public static <LHS, RHS> V1_TablePair<LHS, RHS> createTablePair(final LHS lhs, final RHS rhs, final String description){
		return new V1_TablePair<LHS, RHS> (){
			@Override
			public RHS rhs() {
				return rhs;
			}

			@Override
			public RHS right() {
				return rhs;
			}

			@Override
			public LHS lhs() {
				return lhs;
			}

			@Override
			public LHS left() {
				return lhs;
			}

			@Override
			public String getDescription() {
				return description;
			}
		};
	}
}
