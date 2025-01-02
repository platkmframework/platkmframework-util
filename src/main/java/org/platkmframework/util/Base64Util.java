package org.platkmframework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

/**
 * Base64 Util 
 */
public class Base64Util{
	
	/**
	 * Base64Util
	 */
	  private Base64Util() {
	    throw new IllegalStateException("Base64Util class");
	  }

	/**
	 * decode  From String
	 * @param s to decode
	 * @return object decoded
	 * @throws IOException
	 * @throws ClassNotFoundException Class Not Found Exception
	 */
   public static Object decodeFromString( String s) throws IOException, ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
   }

   /**
    * encode To String
    * @param o object to encode
    * @return string encoded
    * @throws IOException IO Exception
    */
   public static String encodeToString(Object o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray()); 
   }

}
