package kr.co.apfactory.storesolution.global.encryption;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

@Component
public class Base64Encrypt {
    private static final String SEC_KEY = "gohfo8Gu8gt834FHu3gfgw8Gfgf";

    public String decrypt(String str) throws UnsupportedEncodingException {
        if (str != null && !str.equals("")) {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decodedBytes1 = decoder.decode(str);
            String settingInfo = new String(decodedBytes1);
            return URLDecoder.decode(settingInfo, "UTF-8");
        }

        return null;
    }

    public String encrypt(String str) throws UnsupportedEncodingException {
        if (str != null && !str.equals("")) {
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] targetBytes = str.getBytes("UTF-8");
            return encoder.encodeToString(targetBytes);
        }

        return null;
    }

    public Long parseSequenceForDecryption(String str) throws UnsupportedEncodingException {
        String decoded = decrypt(str);
        if (!decoded.contains(SEC_KEY)) {
            throw new UnsupportedEncodingException();
        }

        return Long.parseLong(decoded.replace(SEC_KEY, ""));
    }

    public String parseSequenceForEncryption(Long seq) throws UnsupportedEncodingException {
        return encrypt(SEC_KEY + seq);
    }

    public String parseStringForEncryption(String str) throws UnsupportedEncodingException {
        return encrypt(SEC_KEY + str);
    }
}
