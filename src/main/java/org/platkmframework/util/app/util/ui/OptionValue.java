/*******************************************************************************
 * Copyright(c) 2023 the original author Eduardo Iglesias Taylor.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	 https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * 	Eduardo Iglesias Taylor - initial API and implementation
 *******************************************************************************/
package org.platkmframework.util.app.util.ui;

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 * description: object value key
 */
public class OptionValue 
{ 
	/**
	 * description: id
	 */
	private Object id;
	
	/**
	 * description: text
	 */
	private Object text;
	
	/**
	 * description: constructor
	 */
	public OptionValue() {
		super();
	}

	
	/**
	 * description: constructor
	 * @param id: id
	 * @param text: text
	 */
	public OptionValue(Object id, Object text) {
		super();
		this.id = id;
		this.text = text;
	}
	/**
	 * description: id
	 * @return id
	 */
	public Object getId() {
		return id;
	}
	
	/**
	 * description: set id
	 * @param id: id
	 */
	public void setId(Object id) {
		this.id = id;
	}
	
	/**
	 * description:  object
	 * @return object
	 */
	public Object getText() {
		return text;
	}
	
	/**
	 * description:  set text
	 * @param text: text value
	 */
	public void setText(Object text) {
		this.text = text;
	}
	
	
	

}
