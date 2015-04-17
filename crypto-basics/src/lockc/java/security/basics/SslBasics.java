package lockc.java.security.basics;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509KeyManager;

public class SslBasics implements Constants {
    
    public static void main(String[] args) throws Exception {
    
        
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream is = new FileInputStream(new File("my-SHA512withRSA-keystore.jks"));
        keyStore.load(is, storepass);
        
        
        System.out.println(keyStore.getCertificate(alias));
        
        Certificate[] certChain = keyStore.getCertificateChain(alias);
        
        for(Certificate cert : certChain) {
            
            System.out.println();
            
        }
        
        /*
         * Key managers
         */
        KeyManagerFactory kmFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmFactory.init(keyStore, keypass);
        
        for(KeyManager km : kmFactory.getKeyManagers()) {
            
            X509KeyManager kmX509 = (X509KeyManager) km;
            
            X509Certificate[] x509CertChain = kmX509.getCertificateChain(alias);
            
            
            System.out.println(kmX509.getServerAliases("SHA512withRSA", null));
            
            
            for(X509Certificate x509Cert : x509CertChain) {
                
                
                System.out.println(x509Cert.getIssuerDN());
                System.out.println(x509Cert.getSubjectDN());
                
            }
            
        }
        
        
        /*
         * Trust managers
         */
        TrustManager trustManager;
        
                
    }
    
}
