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
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
 
/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 * description: io object
 */
public class ModelIO {
	
/**
 * description: constructor
 */
	public ModelIO() {
		super(); 
	}

	/**
	 * description: read model from file
	 * @param <E> : model
	 * @param file: file
	 * @param class1: class
	 * @return model object
	 * @throws JAXBException - error
	 */
	public static <E> E readModel(File file, Class<E> class1) throws JAXBException  
	{ 
		return readModel(file, class1, "UTF-8");
	}
	
	/**
	 * description: read model
	 * @param <E>: model
	 * @param file: file
	 * @param class1: class
	 * @param charsetName: chartset
	 * @return model object
	 * @throws JAXBException - error
	 */
	public static <E> E readModel(File file, Class<E> class1, String charsetName) throws JAXBException  
	{ 
	 	
		try {
 
			InputStream inputStream = new FileInputStream(file);
			Reader reader = new InputStreamReader(inputStream, charsetName);
			
			JAXBContext ctx = JAXBContext.newInstance(class1);
			Unmarshaller u = ctx.createUnmarshaller(); 
			return class1.cast(u.unmarshal(reader));
					
		} catch (FileNotFoundException | UnsupportedEncodingException e) { 
			e.printStackTrace();
			throw new JAXBException(e.getMessage());
		} 
			 
	}
	
	/**
	 * description:  read model from inputsream
	 * @param <E> : model
	 * @param inputStream: input stream
	 * @param class1: class
	 * @return model object
	 * @throws JAXBException - error
	 */
	public static <E> E readModel(InputStream inputStream, Class<E> class1) throws JAXBException  
	{ 
		return readModel(inputStream, class1, "UTF-8");
	}
	
	/**
	 * description:  read model
	 * @param <E> model
	 * @param inputStream: input stream
	 * @param class1: class
	 * @param charsetName: charset name
	 * @return model
	 * @throws JAXBException - error
	 */
	public static <E> E readModel(InputStream inputStream, Class<E> class1, String charsetName) throws JAXBException  
	{ 
	 	
		try {
  
			Reader reader = new InputStreamReader(inputStream, charsetName);
			
			JAXBContext ctx = JAXBContext.newInstance(class1);
			Unmarshaller u = ctx.createUnmarshaller(); 
			return class1.cast(u.unmarshal(reader));
					
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
			throw new JAXBException(e.getMessage());
		} 
			 
	}

	/**
	 * description:  write model
	 * @param <E>: model
	 * @param file: file
	 * @param model: model
	 * @param class1: class
	 * @throws JAXBException - error
	 */
	public static <E> void  writeModel(File file, E model, Class<E> class1) throws JAXBException 
	{  
		JAXBContext ctx = JAXBContext.newInstance(class1);
		Marshaller m = ctx.createMarshaller();
		m.marshal(model, file); 
	}

}
