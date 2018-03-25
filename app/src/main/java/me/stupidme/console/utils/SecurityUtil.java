package me.stupidme.console.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * Created by allen on 18-3-25.
 */

public class SecurityUtil {

    public static String encryptDES(String src, String key) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            KeySpec spec = new DESedeKeySpec(key.getBytes());
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(spec);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            return new String(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | InvalidKeySpecException
                | BadPaddingException | IllegalBlockSizeException e) {
            LoggerProxy.w(UserInfoManager.class.getCanonicalName(), "Error occurs when encrypt key.");
            return "";
        }
    }

    public static String decryptDES(String src, String key) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            KeySpec spec = new DESedeKeySpec(key.getBytes());
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(spec);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            return new String(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | BadPaddingException | IllegalBlockSizeException
                | InvalidKeyException | InvalidKeySpecException e) {
            LoggerProxy.w(UserInfoManager.class.getCanonicalName(), "Error occurs when decrypt key.");
            return "";
        }
    }
}
