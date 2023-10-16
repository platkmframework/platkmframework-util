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
import java.util.ArrayList;
import java.util.List;

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 * Custom attribute
 */
public class Attribute implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * description: list of values
	 */
	private List<KeyValue<Object>> values;

	/**
	 * description: Constructor
	 */
	public Attribute() {
		
	}

	/**
	 * description:  list of keyvalue
	 * @return keyvalue list
	 */
	public List<KeyValue<Object>> getValues() {
		if(this.values == null) this.values = new ArrayList<>();
		return this.values;
	}

	/**
	 * description: set list of keyvalue
	 * @param values: list
	 */
	public void setValues(List<KeyValue<Object>> values) {
		this.values = values;
	}
	
	/**
	 * description: add key and object value
	 * @param key: key
	 * @param value: value object
	 */
	public void addValue(String key, Object value) {
		getValues() .add(new KeyValue<>(key, value));
	}
	
	/**
	 * description: get  KeyValue object by key
	 * @param key: key
	 * @return key value object
	 */
	public Object get(String key) { 
		return getValues() .stream()
				  .filter(kv -> key.equals(kv.getKey()))
				  .findAny()
				  .orElse(null); 
	}

}
