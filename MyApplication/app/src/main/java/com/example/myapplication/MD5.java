package com.example.myapplication;

import java.security.MessageDigest;

public class MD5 {
    /**
     * Este metodo se encarga de codificar la contraseña para guardarla en la base de datos.
     * @param clear
     * @return
     * @throws Exception
     */
    public static String md5 (String clear) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(clear.getBytes());
        int size = b.length;
        StringBuffer h = new StringBuffer(size);
        for (int i = 0; i<size;i++){
            int u = b[i] & 255;
            if(u<16){
                h.append("0"+Integer.toHexString(u));
            }
            else {
                h.append(Integer.toHexString(u));
            }
        }
        return h.toString();
    }
}
