package com.google.crypto.tink.subtle;

import java.security.SecureRandom;

public final class Random {
    private static final ThreadLocal<SecureRandom> localRandom = new ThreadLocal<SecureRandom>() {
        public SecureRandom initialValue() {
            return Random.newDefaultSecureRandom();
        }
    };

    private Random() {
    }

    /* access modifiers changed from: private */
    public static SecureRandom newDefaultSecureRandom() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }

    public static byte[] randBytes(int i3) {
        byte[] bArr = new byte[i3];
        localRandom.get().nextBytes(bArr);
        return bArr;
    }

    public static final int randInt(int i3) {
        return localRandom.get().nextInt(i3);
    }

    public static final int randInt() {
        return localRandom.get().nextInt();
    }
}
