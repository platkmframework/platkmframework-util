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
 
import java.io.InputStream;
import java.nio.charset.StandardCharsets; 
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils; 

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 * 	description: key value replace process
 */
public class KeyValueReplaceProcess {
	  
	
	/**
	 * description: constructor
	 */
	  private KeyValueReplaceProcess() {
	    throw new IllegalStateException("KeyValueReplaceProcess class");
	  }

	/**
	   * description: process string from map key value
	   * @param template: string
	   * @param map: map
	   * @return string result
	   * @throws Exception - error
	   */
	public static String process( String template, Map<String, Object> map) throws Exception {
		
		if(StringUtils.isBlank(template)) throw new Exception("No email template found");
		 
		InputStream inputStream = KeyValueReplaceProcess.class.getResourceAsStream(template);
		String txt = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		for (String key : map.keySet()) {
			txt = txt.replace("${" + key + "}", map.get(key).toString()); 
		}
		return txt;
		 
	}

}
