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
package org.platkmframework.util.json;

import java.io.Serializable;

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 * description: key value object
 * @param <E> - model
 */
public class KeyValue<E> implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * description: key
	 */
	private String key;
	
	/**
	 *  description: value
	 */
	private E value;
	
	/**
	 * description: constructor
	 */
	public KeyValue() { 
	}
	
	/**
	 * description:constructor
	 * @param key: key
	 * @param value: value
	 */
	public KeyValue(String key, Object value) { 
	}

/**
 * description: key
 * @return key
 */
	public String getKey() {
		return key;
	}

	/**
	 * description: set key
	 * @param key: key
	 */
	public void setKey(String key) {
		this.key = key;
	}
/**
 * description: value
 * @return value
 */
	public E getValue() {
		return value;
	}

	/**
	 * description: set value
	 * @param value: value
	 */
	public void setValue(E value) {
		this.value = value;
	}
	
	

}
