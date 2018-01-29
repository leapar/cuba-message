package com.company;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

//https://checksid.cuba-platform.com?sid=大萨达多&lid=c70c46d9ae520870d24a1bd8efe2c9f9

//XML CONFIG :check.sif_content

public class Main {
    private static String sid;


    public static void main(String[] args) {


/*
        license.init();
        String lic = license.genLicense();
        license.checkLicense(lic.getBytes());

*/



        Message.dealAllMessage("d:\\studio\\messages\\enc\\",false);


    }
}
