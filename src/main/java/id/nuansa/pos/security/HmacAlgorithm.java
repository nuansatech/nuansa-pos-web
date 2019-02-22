package id.nuansa.pos.security;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class HmacAlgorithm {

    public String HMAC_SHA256_SIGNATURE = "HmacSHA256";

    private String toHexString(byte[] bytes) {
        String result = "";
        try (Formatter formatter = new Formatter()) {
            for (byte b : bytes) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        } catch (Exception ex) {
            return result;
        }
    }

    public String generate(String plain, String key, String alg)
            throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), alg);
        Mac mac = Mac.getInstance(alg);
        mac.init(signingKey);
        return toHexString(mac.doFinal(plain.getBytes()));
    }
}
