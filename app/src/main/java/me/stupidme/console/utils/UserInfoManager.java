package me.stupidme.console.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;
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

public class UserInfoManager {

    private static WeakReference<Context> mContextRef;

    private static volatile UserInfoManager sInstance;

    private SharedPreferences mSharedPreference;

    private String mKey;

    private UserInfoManager() {
        mSharedPreference = mContextRef.get().getSharedPreferences("user_info", Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        mContextRef = new WeakReference<>(context.getApplicationContext());
    }

    public static UserInfoManager getInstance() {
        if (mContextRef.get() == null) {
            LoggerProxy.e(UserInfoManager.class.getCanonicalName(), "Context is null. Make sure " +
                    "you have init it before you get an instance.");
            return null;
        }
        if (sInstance == null) {
            synchronized (UserInfoManager.class) {
                if (sInstance == null) {
                    sInstance = new UserInfoManager();
                }
            }
        }
        return sInstance;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getUserName() {
        String s = mSharedPreference.getString("user_name", "");
        return decryptDES(s);
    }

    public String getPassword() {
        String s = mSharedPreference.getString("password", "");
        return decryptDES(s);
    }

    public void setUserName(String name) {
        String s = encryptDES(name);
        mSharedPreference.edit().putString("user_name", s).apply();
    }

    public void setPassword(String password) {
        String s = encryptDES(password);
        mSharedPreference.edit().putString("password", s).apply();
    }

    private String encryptDES(String src) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            KeySpec spec = new DESedeKeySpec(mKey.getBytes());
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

    private String decryptDES(String src) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            KeySpec spec = new DESedeKeySpec(mKey.getBytes());
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
