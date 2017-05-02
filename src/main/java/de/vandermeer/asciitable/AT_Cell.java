/* Copyright 2017 Sven van der Meer <vdmeer.sven@mykolab.com>
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

package de.vandermeer.asciitable;

import de.vandermeer.skb.interfaces.document.IsTableCell;

/**
 * Cell of an {@link AT_Row}.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.3.2 build 170502 (02-May-17) for Java 1.8
 * @since      v0.3.0
 */
public class AT_Cell implements IsTableCell {

	/** The cell context. */
	protected AT_CellContext ctx;

	/** The content of the cell. */
	protected Object content;

	/**
	 * Creates a new cell with content and default context.
	 * @param content the cell content, can be null indicating column spans
	 */
	public AT_Cell(Object content){
		this(content, null);
	}

	/**
	 * Creates a new cell with content and context
	 * @param content cell content, can be null indicating column spans
	 * @param ctx cell context, default context created if null
	 */
	public AT_Cell(Object content, AT_CellContext ctx){
		if(ctx==null){
			this.ctx = new AT_CellContext();
		}
		else{
			this.ctx = ctx;
		}
		this.content = content;
	}

	@Override
	public Object getContent(){
		return this.content;
	}

	@Override
	public AT_CellContext getContext() {
		return this.ctx;
	}

	@Override
	public String toString(){
		if(this.content!=null){
			return this.content.toString();
		}
		else{
			return "null";
		}
	}
}
