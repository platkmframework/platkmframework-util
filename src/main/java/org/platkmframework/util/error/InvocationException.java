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
package org.platkmframework.util.error;
 
/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 * description:  invocation exception
 */
public class InvocationException extends Exception {
	
	private static final long serialVersionUID = 1423497353920839751L;

	/**
	 * description:  constructor
	 * @param message: error message
	 */
	public InvocationException(String message) {
		super(message); 
	}

	/**
	 * description: constructor
	 * @param cause: cause
	 */
	public InvocationException(Throwable cause) {
		super(cause);
	}
 
}
