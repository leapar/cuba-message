package com.company;

import com.haulmont.studio.common.Messages;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Message {
    //C:\Program Files (x86)\CUBA Studio 6.7\lib\studio-backend-6.7.6.jar!\com\haulmont\studio\common\w.class

    protected static List<String> files = new ArrayList(Arrays.asList(
            "app_messages",
            "cd_messages",
            "dbfirst_messages",
            "dbupdates_messages",
            "ed_messages",
            "enumd_messages",
            "exception_messages",
            "functionality_messages",
            "history_messages",
            "listener_messages",
            "main_messages",
            "md_messages",
            "msg_messages",
            "pm_messages",
            "polymer_messages",
            "sd_messages",
            "serviced_messages",
            "settings_messages",
            "template_messages",
            "tl_messages",
            "util_messages"));

    public static String decMessage(String msg) {
        javax.crypto.spec.SecretKeySpec secretKeySpec = new SecretKeySpec("akunAm@tata".getBytes(), "Blowfish");
        try {
            Cipher var14 = javax.crypto.Cipher.getInstance("Blowfish");
            var14.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] var15 = org.apache.commons.codec.binary.Base64.decodeBase64(msg);
            if(var15 == null) {
                return "";
            }
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

    public static void sortAllMessage(String dir) {
        //C:\Program Files (x86)\CUBA Studio 6.7\lib\studio-backend-6.7.6.jar!\com\haulmont\studio\common\l.class
        for (String file :
                files) {
            java.util.ResourceBundle resourceBundle = null;
            try {
                resourceBundle = java.util.ResourceBundle.getBundle(file);
            } catch (Exception e) {

            }

            if (resourceBundle == null)
                continue;
            // Enumeration<String> keys = resourceBundle.getKeys();

            List<String> keys = Collections.list(resourceBundle.getKeys());
            Collections.sort(keys);

            File dstfile = new File(dir + "\\" + file + ".properties");
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

            for (String key :
                    keys) {

           /* }
            while (keys.hasMoreElements()){
                String key = keys.nextElement();*/
                String msg = "";

                msg = resourceBundle.getString(key);
                try {
                    msg = new String(msg.getBytes("ISO8859-1"), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println(key + " = " + msg);
                // 向文件写入内容
                try {
                    writer.write(key + "=" + msg + "\n");
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

    public static String getMessage(String fileName,String key) {
        boolean var2 = "true".equals(System.getProperty("runFromIde"));
        HashMap var3 = new HashMap();


        String var6 = "/com/haulmont/studio/common/messages/" + fileName+ ".properties";
        InputStream var7 = Messages.class.getResourceAsStream(var6);
        if (var7 == null) {
            throw new RuntimeException("Resource '" + var6 + "' not found");
        }
        BufferedReader var8 = new BufferedReader(new InputStreamReader(var7, Charset.forName("UTF-8")));
        Throwable var9 = null;


        Properties var10 = new Properties();
        try {
            var10.load(var8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String var12 = key;
        String var13;


       // for (Iterator var11 = var10.stringPropertyNames().iterator(); var11.hasNext(); var3.put(var12, var13)) {
       //     var12 = (String) var11.next();


            var13 = var10.getProperty(var12);
            if (!var2) {

                String msg = decMessage(var13);
                return msg;
              //  System.console().writer().print(msg);
            }
    //    }

    return "";
    }

    public static void dealAllMessage(String dir, boolean isDec) {
        //C:\Program Files (x86)\CUBA Studio 6.7\lib\studio-backend-6.7.6.jar!\com\haulmont\studio\common\l.class
        for (String file :
                files) {
            java.util.ResourceBundle resourceBundle = java.util.ResourceBundle.getBundle(file);
            // Enumeration<String> keys = resourceBundle.getKeys();

            List<String> keys = Collections.list(resourceBundle.getKeys());
            Collections.sort(keys);

            File dstfile = new File(dir + "\\" + file + ".properties");
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

            for (String key :
                    keys) {

                String msg = "";
                if (isDec) {
                    //msg = getMessage(file,key);
                    msg = decMessage(resourceBundle.getString(key));
                    msg = msg.replace("\n", "");



                } else {
                    try {
                        msg = resourceBundle.getString(key);
                        msg = new String(msg.getBytes("ISO8859-1"), "UTF-8");
                        msg = encMessage(msg);
                        msg = msg.replace("=", "\\=");

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                }

                //System.out.println(key+" = "+msg);
                // 向文件写入内容
                try {
                    writer.write(key + "=" + msg + "\n");
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
