package HelperClasses;

/**
 * Created by KAM on 27/01/2016.
 */

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class AES_security_implementations {

    private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',
                    'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

    public static String encrypt(String Data) throws Exception {
        System.out.println("Security#protocol#inialised#encryption ");
        System.out.println("data#to#encrypt"+Data);


        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes("UTF-8"));
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        System.out.println("encrypted#data#encrypt "+encryptedValue);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        System.out.println("Security#protocol#inialised#decryption ");
        System.out.println("*********************ENCRYPTED DATA************************* ");
        System.out.println("data#to#dencrypt "+encryptedData);

        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.decode(encryptedData,Base64.DEFAULT);
        byte[] decValue = c.doFinal(decoded);

        String decryptedValue=new String(decValue);
        System.out.println("*********************DECRYPTED DATA************************* ");
        System.out.println("decrypted#data#decrypt "+decryptedValue);


        return decryptedValue;
    }
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

}


