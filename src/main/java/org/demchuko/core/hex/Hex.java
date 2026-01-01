package org.demchuko.core.hex;


public class Hex {


    public static String encodeHexString(byte[] byteArray, int charAmount) {

        StringBuffer hexStringBuffer = new StringBuffer();
        for (int i = 0; i < charAmount; i++) {
            hexStringBuffer.append(byteToHex(byteArray[i]));
        }
        return hexStringBuffer.toString();

    }

    public static String encodeHexString(byte[] byteArray) {
        return encodeHexString(byteArray, byteArray.length);
    }

    public static String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

}
