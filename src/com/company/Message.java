package com.company;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class Message {
    //C:\Program Files (x86)\CUBA Studio 6.7\lib\studio-backend-6.7.6.jar!\com\haulmont\studio\common\w.class
    protected static List<String> files = new ArrayList(Arrays.asList("app_messages",
            "dbupdates_messages", "ed_messages", "enumd_messages",
            "exception_messages", "cd_messages", "listener_messages",
            "tl_messages", "main_messages", "md_messages", "msg_messages",
            "pm_messages", "sd_messages", "serviced_messages", "settings_messages",
            "dbfirst_messages", "util_messages", "history_messages", "template_messages",
            "polymer_messages"));

    public static String decMessage(String msg) {
        javax.crypto.spec.SecretKeySpec secretKeySpec = new SecretKeySpec("akunAm@tata".getBytes(), "Blowfish");
        try {
            Cipher var14 = javax.crypto.Cipher.getInstance("Blowfish");
            var14.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] var15 = org.apache.commons.codec.binary.Base64.decodeBase64(msg);
            byte[] var16 = var14.doFinal(var15);
            //System.out.println(new String(var16, "UTF-8"));//ToolsPanel.cloudDeploy
            return new String(var16, "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String encMessage(String msg) {
        javax.crypto.spec.SecretKeySpec secretKeySpec = new SecretKeySpec("akunAm@tata".getBytes(), "Blowfish");
        try {
            Cipher var14 = javax.crypto.Cipher.getInstance("Blowfish");
            var14.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] var16 = var14.doFinal(msg.getBytes("UTF-8"));

            byte[] var15 = org.apache.commons.codec.binary.Base64.encodeBase64(var16);

            //System.out.println(new String(var16, "UTF-8"));//ToolsPanel.cloudDeploy
            return new String(var15, "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static  void dealAllMessage(String dir,boolean isDec) {
        //C:\Program Files (x86)\CUBA Studio 6.7\lib\studio-backend-6.7.6.jar!\com\haulmont\studio\common\l.class
        for (String file:
        files) {
            java.util.ResourceBundle resourceBundle = java.util.ResourceBundle.getBundle(file);
            Enumeration<String> keys = resourceBundle.getKeys();

            File dstfile = new File(dir + "\\"+file+".properties");
            // 创建文件
            try {
                dstfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // creates a FileWriter Object
            FileWriter writer = null;
            try {
                writer = new FileWriter(dstfile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (keys.hasMoreElements()){
                String key = keys.nextElement();
                String msg = "";
                if (isDec)
                    msg = decMessage(resourceBundle.getString(key));
                else {
                    try {
                        msg = resourceBundle.getString(key);
                        msg = new String(msg.getBytes("ISO8859-1"),"UTF-8");
                        msg = encMessage(msg);
                        msg = msg.replace("=","\\=");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                }

                //System.out.println(key+" = "+msg);
                // 向文件写入内容
                try {
                    writer.write(key+"="+msg+"\n");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
