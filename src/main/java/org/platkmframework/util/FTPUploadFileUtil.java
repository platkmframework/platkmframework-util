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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
*/
public class FTPUploadFileUtil {
 
	private static Logger logger = LoggerFactory.getLogger(FTPUploadFileUtil.class);
	
	/**
	 * description: constructor
	 */
	  private FTPUploadFileUtil() {
	    throw new IllegalStateException("ClassToJsonUtil class");
	  }

	/**
	 * description: send ftp file
	 * @param server: server
	 * @param port: port
	 * @param user: user
	 * @param pass: pass
	 * @param file:  file
	 * @param subFolder: sub folder
	 * @throws Exception error
	 */
	public static void send(String server, int port, String user, String pass, File file, String subFolder) throws Exception { 
		  
        try {
        	FTPClient ftpClient = new FTPClient();
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);  
            InputStream inputStream = new FileInputStream(file); 
            logger.info("Start uploading first file");
            
            if(StringUtils.isNotBlank(subFolder)) {
	            if(!ftpClient.changeWorkingDirectory(subFolder)){   
	                if(ftpClient.makeDirectory(subFolder)){   
	                    ftpClient.changeWorkingDirectory(subFolder);   
	                }else {    
	                	inputStream.close();
	                	throw new Exception("could not create subfolder");
	                }   
	            }  
            }
		    boolean done = ftpClient.storeFile(file.getName(), inputStream);
		    inputStream.close();
		    if (done) {
		    	logger.info("The first file is uploaded successfully.");
		    }
		  }catch (IOException ex) {
			  logger.info("Error: " + ex.getMessage());
	          ex.printStackTrace();
	      }
	}

}
