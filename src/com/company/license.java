package com.company;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class license {
    private static String N ;
    private static String E ;
    private static String D ;

    //C:\Users\WXH\.haulmont\studio\sif.dat
    public static void init() {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom secrand = new SecureRandom();
            secrand.setSeed("www.jmyida.com".getBytes());//初始化随机产生器
            // 密钥位数
            keyPairGen.initialize(1024,secrand);
            // 动态生成密钥对，这是当前最耗时的操作，一般要2s以上。
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 公钥
            PublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            // 私钥
            PrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            byte[] publicKeyData = publicKey.getEncoded();
            byte[] privateKeyData = publicKey.getEncoded();


            //获取N e
            //printPublicKeyInfo(PublicKey key)
            RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
            System.out.println("RSAPublicKey:");
            System.out.println("Modulus.length=" +
                    rsaPublicKey.getModulus().bitLength());
            System.out.println("Modulus=" +
                    rsaPublicKey.getModulus().toString());
            System.out.println("PublicExponent.length=" +
                    rsaPublicKey.getPublicExponent().bitLength());
            System.out.println("PublicExponent=" +
                    rsaPublicKey.getPublicExponent().toString());

            N = rsaPublicKey.getModulus().toString();
            E = rsaPublicKey.getPublicExponent().toString();


            //获取N D
            // 打印私钥信息 printPublicKeyInfo(PrivateKey key)
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
            System.out.println("RSAPrivateKey:");
            System.out.println("Modulus.length=" +
                    rsaPrivateKey.getModulus().bitLength());
            System.out.println("Modulus=" +
                    rsaPrivateKey.getModulus().toString());
            System.out.println("PrivateExponent.length=" +
                    rsaPrivateKey.getPrivateExponent().bitLength());
            System.out.println("PrivateExponent=" +
                    rsaPrivateKey.getPrivateExponent().toString());

            D = rsaPrivateKey.getPrivateExponent().toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public static void checkLicense(byte[] var1) {
        String key = "aYI7vYUcf/VstdERArJljSXtS3wWoSSh4wrMbID+D3wnc2N2QSTGLIhU6Ddpn6aKBoTdld4pgSfA\n" +
                "rIhEbjSG8MTbVXtQwTxmiCHhtSQEdDfK19eIKDYEy0mn22t5KWYqa9gkD2yk3tQ6eqiWHEhdXqwG\n" +
                "NLepXgOzKD1VChjBV99bPjxqxuVRj69SPWycsTi1okng/nOtP2mJ/rKLcifGRYAnnK0h4Blk11u1\n" +
                "vERvUm43UsDC/KWPs6WXphJ209krSNMxDjTYGbvz+3qCWdK4L1qxoNsIPvPcwpXjqyiAUJe3bZXM\n" +
                "J2JzWtOEUIbY5mZNZKx+dlmLFw4Jw/4OJSkz3g==";

        /*
sid=170704000693-YQOk9IJO1IFG
type=C
name=wang xiaohua
company=leapar
endDate=2018-07-04
expired=0
banned=0
        */
        //N  E
       // BigInteger var2 = new BigInteger("17369712262290647732768133445861332449863405383733306695896586821166245382729380222118948668590047591903813382253186640467063376463309880263824085810383552963627855603429835060435976633955217307266714318344160886538360012623239010786668755679438900124601074924850696725233212494777766999123952653273738958617798460338184668049410136792403729341479373919634041235053823478242208651592611582439749292909499663165109004083820192135244694907138372731716013807836312280426304459316963033144149631900633817073029029413556757588486052978078614048837784810650766996280232645714319416096306667876390555673421669667406990886847");
       // BigInteger var3 = new BigInteger("65537");

        //使用N、e值还原公钥
        RSAPublicKeySpec var18 = new java.security.spec.RSAPublicKeySpec(new BigInteger(N), new BigInteger(E));
        try {
            PublicKey var21 = KeyFactory.getInstance("RSA").generatePublic(var18);

            Cipher var23 = javax.crypto.Cipher.getInstance("RSA");
            var23.init(2, var21);

            byte[] var24 = Base64.decodeBase64(var1);
            byte[] var25 = var23.doFinal(var24);
            String var26 = new String((byte[]) ((byte[]) var25), StandardCharsets.UTF_8);

            System.out.println(var26);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public static String genLicense() {
        BigInteger var2 = new BigInteger("17369712262290647732768133445861332449863405383733306695896586821166245382729380222118948668590047591903813382253186640467063376463309880263824085810383552963627855603429835060435976633955217307266714318344160886538360012623239010786668755679438900124601074924850696725233212494777766999123952653273738958617798460338184668049410136792403729341479373919634041235053823478242208651592611582439749292909499663165109004083820192135244694907138372731716013807836312280426304459316963033144149631900633817073029029413556757588486052978078614048837784810650766996280232645714319416096306667876390555673421669667406990886847");
        BigInteger var3 = new BigInteger("65537");

        RSAPrivateKeySpec var18 = new java.security.spec.RSAPrivateKeySpec(new BigInteger(N), new BigInteger(D));
        try {
            PrivateKey var21 = KeyFactory.getInstance("RSA").generatePrivate(var18);

            Cipher var23 = javax.crypto.Cipher.getInstance("RSA");
            var23.init(Cipher.ENCRYPT_MODE, var21);

            String source = "sid=170704000693-YQOk9IJO1IFG\n" +
                    "type=C\n" +
                    "name=wang xiaohua\n" +
                    "company=leapar\n" +
                    "endDate=2018-07-04\n" +
                    "expired=0\n" +
                    "banned=0";


            byte[] var25 = var23.doFinal(source.getBytes());
            byte[] var24 = Base64.encodeBase64(var25);
            String var26 = new String((byte[]) ((byte[]) var24), StandardCharsets.UTF_8);

            System.out.println(var26);
            return var26;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String genLicenseByPublicKey() {
        BigInteger var2 = new BigInteger("17369712262290647732768133445861332449863405383733306695896586821166245382729380222118948668590047591903813382253186640467063376463309880263824085810383552963627855603429835060435976633955217307266714318344160886538360012623239010786668755679438900124601074924850696725233212494777766999123952653273738958617798460338184668049410136792403729341479373919634041235053823478242208651592611582439749292909499663165109004083820192135244694907138372731716013807836312280426304459316963033144149631900633817073029029413556757588486052978078614048837784810650766996280232645714319416096306667876390555673421669667406990886847");
        BigInteger var3 = new BigInteger("65537");

        RSAPublicKeySpec var18 = new java.security.spec.RSAPublicKeySpec(var2, var3);
        try {
            PublicKey var21 = KeyFactory.getInstance("RSA").generatePublic(var18);

            Cipher var23 = javax.crypto.Cipher.getInstance("RSA");
            var23.init(Cipher.ENCRYPT_MODE, var21);

            String source = "sid=170704000693-YQOk9IJO1IFG\n" +
                    "type=C\n" +
                    "name=wang xiaohua\n" +
                    "company=leapar\n" +
                    "endDate=2018-07-04\n" +
                    "expired=0\n" +
                    "banned=0";


            byte[] var25 = var23.doFinal(source.getBytes());
            byte[] var24 = Base64.encodeBase64(var25);
            String var26 = new String((byte[]) ((byte[]) var24), StandardCharsets.UTF_8);

            System.out.println(var26);
            return var26;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

}
