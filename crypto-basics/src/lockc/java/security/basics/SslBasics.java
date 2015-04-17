package lockc.java.security.basics;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.Date;


/**
 * 
 * @author lockc
 * 
 * keytool -genkeypair 
 *         -dname "cn=Chris Lock, ou=Clockworks, o=Clock Enterprise, c=UK" 
 *         -alias Clockworks 
 *         -keypass pa55word 
 *         -keystore mykeystore.jks 
 *         -storepass p4ssword 
 *         -validity 3560 
 *         -storetype JKS
 * 
 * keytool -genkeypair 
 *         -keyalg RSA 
 *         -sigalg SHA512withRSA
 *         -dname "cn=Chris Lock, ou=Clockworks, o=Clock Enterprise, c=UK" 
 *         -alias Clockworks 
 *         -keypass pa55word 
 *         -keystore my-rsa-keystore.jks 
 *         -storepass p4ssword 
 *         -validity 3560 
 *         -storetype JKS
 *
 * keytool -exportcert -file mycert.cer -alias Clockworks -keystore mykeystore.jks -storepass p4ssword -rfc 
 * 
 * keytool -printcert -file mycert.cer
 *
 */
public class SslBasics {
    
    public static void main(String[] args) throws Exception {
    
        
        String alias = "Clockworks";
        char[] storepass = { 'p', '4', 's', 's', 'w', 'o', 'r', 'd' };
        char[] keypass = { 'p', 'a', '5', '5', 'w', 'o', 'r', 'd' };
        
        /*
         * Create a key store object and load our actual key store file into it
         */
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream is = new FileInputStream(new File("my-SHA512withRSA-keystore.jks"));
//        InputStream is = new FileInputStream(new File("my-rsa-keystore.jks"));
//        InputStream is = new FileInputStream(new File("my-default-keystore.jks"));
        keyStore.load(is, storepass);
        
        Certificate cert = keyStore.getCertificate(alias);
        System.out.println(cert);
        
        String temp = keyStore.getCertificateAlias(cert);
        System.out.println(temp);
        
        Date creationDate = keyStore.getCreationDate(alias);
        System.out.println(creationDate);
        
        /*
         * Returns the private key in this situation
         */
        Key key = keyStore.getKey(alias, keypass);
        System.out.println(key.getFormat());
        System.out.println(key.getAlgorithm());
        System.out.println(key);
        
        /*
         * Same as above really in this situation
         */
        KeyStore.ProtectionParameter protParam =
                new KeyStore.PasswordProtection(keypass);
        PrivateKeyEntry pkEntry = (PrivateKeyEntry) keyStore.getEntry(alias, protParam);
        Certificate cert2 = pkEntry.getCertificate();
//        System.out.println(cert2);  // same as cert above
        
        
        PrivateKey privateKey = pkEntry.getPrivateKey();
        System.out.println(privateKey.getFormat());
        System.out.println(privateKey.getAlgorithm());
        System.out.println(privateKey);
                
    }
    
}
