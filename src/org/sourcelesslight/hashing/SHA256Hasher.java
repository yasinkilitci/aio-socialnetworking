package org.sourcelesslight.hashing;

import java.security.MessageDigest;

public class SHA256Hasher {

	/* Encrypt the string with SHA-256 and return the hashcode */
	public String encrypt(String password)
	{ 
        try{
				MessageDigest md = MessageDigest.getInstance("SHA-256");
		        md.update(password.getBytes());
		 
		        byte byteData[] = md.digest();
		 
		        //convert the byte to hex format method 1
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < byteData.length; i++) {
		         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		        }
		 
		        //convert the byte to hex format method 2
		        StringBuffer hexString = new StringBuffer();
		    	for (int i=0;i<byteData.length;i++) {
		    		String hex=Integer.toHexString(0xff & byteData[i]);
		   	     	if(hex.length()==1) hexString.append('0');
		   	     	hexString.append(hex);
		    	}
		    	return hexString.toString();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	return null;
        }
	}
	
}
