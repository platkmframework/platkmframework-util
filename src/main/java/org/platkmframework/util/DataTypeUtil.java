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
package org.platkmframework.util;

import org.apache.commons.lang3.StringUtils;

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
*/
public class DataTypeUtil {
	
	/**
	 * description: default constructor
	 */
	  private DataTypeUtil() {
	    throw new IllegalStateException("ClassToJsonUtil class");
	  }
	
	/**
	 * 
	 * convert to String Value
	 * @param value value to convert to string
	 * @param defaultValue default value
	 * @return
	 */
	public static String getStringValue(Object value, String defaultValue) {
		return (value != null && StringUtils.isNotBlank(value.toString()))?value.toString():defaultValue; 
	}
	
	/**
	 * description: convert to int value
	 * @param value: value
	 * @param defaultValue :default value
	 * @return int
	 */
	public static int getIntegerValue(Object value, int defaultValue) {
		if( value != null && StringUtils.isNotBlank(value.toString())) return Integer.valueOf(value.toString()); 
		return defaultValue;
	}
	/**
	 * description: convert to booelan 
	 * @param value: value
	 * @param defaultValue: default value
	 * @return boolean
	 */
	public static Boolean getBooleanValue(Object value, boolean defaultValue) {
		if(value != null && StringUtils.isNotBlank(value.toString())) return Boolean.valueOf(value.toString()); 
		return defaultValue;
	}

}
