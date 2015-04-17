package lockc.java.security.basics;

import java.security.MessageDigest;

public class MessageDigestBasics implements Constants {
    
    public static void main(String[] args) throws Exception {
    
        
        byte[] bytes = "Some hash function".getBytes();
        
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] md5_hash = md5.digest(bytes);
        
        MessageDigest sha2_256 = MessageDigest.getInstance("SHA-256");
        byte[] sha2_256_hash = sha2_256.digest(bytes);
        
        MessageDigest sha2_512 = MessageDigest.getInstance("SHA-512");
        byte[] sha2_512_hash = sha2_512.digest(bytes);
        
        
        
    }
    
}
