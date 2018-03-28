package me.stupidme.console.utils;

import java.io.UnsupportedEncodingException;
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
import javax.crypto.spec.DESKeySpec;

/**
 * Created by allen on 18-3-25.
 */

public class SecurityUtil {

    public static String encryptDES(String src, String key) {
        try {
            Cipher cipher = Cipher.getInstance("DES/CFB/PKCS5Padding");
            KeySpec spec = new DESKeySpec(key.getBytes("UTF-8"));
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(spec);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            return new String(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | InvalidKeySpecException
                | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException e) {
            e.printStackTrace();
            LoggerProxy.e(UserInfoManager.class.getCanonicalName(),
                    "Error occurs when encrypt key.\n" + e.getMessage());
            return "";
        }
    }

    public static String decryptDES(String src, String key) {
        try {
            Cipher cipher = Cipher.getInstance("DES/CFB/PKCS5Padding");
            KeySpec spec = new DESKeySpec(key.getBytes("UTF-8"));
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(spec);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            return new String(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | BadPaddingException | IllegalBlockSizeException
                | InvalidKeyException | InvalidKeySpecException | UnsupportedEncodingException e) {
            LoggerProxy.w(UserInfoManager.class.getCanonicalName(),
                    "Error occurs when decrypt key.\n" + e.getMessage());
            return "";
        }
    }
}
