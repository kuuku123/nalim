package one.nalim.example;

import one.nalim.Library;
import one.nalim.Link;
import one.nalim.Linker;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;

@Library("crypto")
@Library("crypto")
public class LibCrypto {

    public static byte[] sha256(byte[] data) {
        byte[] digest = new byte[32];
        SHA256(data, data.length, digest);
        return digest;
    }

    @Link
    private static native void SHA256(byte[] data, int len, byte[] digest);

    static {
        Linker.linkClass(LibCrypto.class);
    }

    public static void main(String[] args) {
        byte[] bytes = sha256("Hello, world".getBytes(StandardCharsets.UTF_8));
        for (byte aByte : bytes) {
            System.out.println(aByte & 0xFF);
        }
    }
}
