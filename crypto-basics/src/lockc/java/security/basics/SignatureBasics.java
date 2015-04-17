package lockc.java.security.basics;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.cert.Certificate;

public class SignatureBasics {
    
    private static KeyStore keyStore;
    private static PrivateKey privateKey;
    private static PublicKey publicKey;
    
    private static String keyStoreFile = "my-SHA512withRSA-keystore.jks";
    private static String alias = "Clockworks";
    private static char[] storepass = { 'p', '4', 's', 's', 'w', 'o', 'r', 'd' };
    private static char[] keypass = { 'p', 'a', '5', '5', 'w', 'o', 'r', 'd' };
    
    public static void main(String[] args) throws Exception {
    
        initCrypto();
        
        byte[] data = "Signatures".getBytes();
        
        /*
         * Prepare the Signature class and sign some data
         */
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        byte[] signed = signature.sign();
        
        
        /*
         * Prepare the signature and verify the signed data against 
         * the original data
         */
        signature.initVerify(publicKey);
        signature.update(data);
//        signature.update((byte) 0x8); // will make verify fail
        boolean verified = signature.verify(signed);
        
        
        System.out.println("Data is verified: " + verified);
        
    }
    
    
    
    private static void initCrypto() throws Exception {
        
        keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream is = new FileInputStream(new File(keyStoreFile));
        keyStore.load(is, storepass);
                
        /*
         * Same as above really in this situation
         */
        KeyStore.ProtectionParameter protParam =
                new KeyStore.PasswordProtection(keypass);
        PrivateKeyEntry pkEntry = (PrivateKeyEntry) keyStore.getEntry(alias, protParam);
        privateKey = pkEntry.getPrivateKey();
        
        Certificate certificate = keyStore.getCertificate(alias);
        publicKey = certificate.getPublicKey();
    }
}
