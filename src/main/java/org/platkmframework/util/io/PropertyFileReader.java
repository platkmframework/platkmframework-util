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
package org.platkmframework.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
 
/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 * description: property file reader
 */
public class PropertyFileReader 
{

	/**
	 * description: constructor
	 */
	public PropertyFileReader() {
		super();
	}

	/**
	 * description:  read as  properties
	 * @param file file
	 * @return properties
	 * @throws IOException - error
	 */
	public Properties readAsProperties(File file) throws IOException
	{ 
		return readAsProperties(new Properties(), file);
	}
	
	/**
	 * description:  read as properties
	 * @param properties: properties
	 * @param file: file
	 * @return properties
	 * @throws IOException - error
	 */
	public Properties readAsProperties(Properties properties, File file) throws IOException
	{ 
		if(file.exists() && file.isFile()) {
			FileInputStream in = new FileInputStream(file);
			properties.load(in);
			in.close(); 
		}
		return properties;
	}
	
	/**
	 * description:  read as map
	 * @param file: file
	 * @return map
	 * @throws IOException - error
	 */
	public Map<String,String > readAsMap(File file) throws IOException
	{
		Map<String, String> map = new HashMap<>();
		
		if(file.exists() && file.isFile())
		{
			List<?> fileContent = FileUtils.readLines(file, "UTF-8");
			for (int i = 0; i < fileContent.size(); i++) 
			{
				String line  = (String)fileContent.get(i);
				String[] keyvalue = line.split("=",2);
				map.put(keyvalue[0], keyvalue[1]);
			}
		}	
		
		return map;
		
	}

}
