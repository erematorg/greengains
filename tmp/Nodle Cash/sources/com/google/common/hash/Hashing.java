package com.google.common.hash;

import android.support.v4.media.session.a;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import io.nodle.cash.substrate.SubstrateHelper;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import javax.annotation.CheckForNull;
import javax.crypto.spec.SecretKeySpec;

@ElementTypesAreNonnullByDefault
public final class Hashing {
    static final int GOOD_FAST_HASH_SEED = ((int) System.currentTimeMillis());

    @Immutable
    public enum ChecksumType implements ImmutableSupplier<Checksum> {
        CRC_32("Hashing.crc32()") {
            public Checksum get() {
                return new CRC32();
            }
        },
        ADLER_32("Hashing.adler32()") {
            public Checksum get() {
                return new Adler32();
            }
        };
        
        public final HashFunction hashFunction;

        private ChecksumType(String str) {
            this.hashFunction = new ChecksumHashFunction(this, 32, str);
        }
    }

    public static final class ConcatenatedHashFunction extends AbstractCompositeHashFunction {
        public int bits() {
            int i3 = 0;
            for (HashFunction bits : this.functions) {
                i3 += bits.bits();
            }
            return i3;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof ConcatenatedHashFunction) {
                return Arrays.equals(this.functions, ((ConcatenatedHashFunction) obj).functions);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.functions);
        }

        public HashCode makeHash(Hasher[] hasherArr) {
            byte[] bArr = new byte[(bits() / 8)];
            int i3 = 0;
            for (Hasher hash : hasherArr) {
                HashCode hash2 = hash.hash();
                i3 += hash2.writeBytesTo(bArr, i3, hash2.bits() / 8);
            }
            return HashCode.fromBytesNoCopy(bArr);
        }

        private ConcatenatedHashFunction(HashFunction... hashFunctionArr) {
            super(hashFunctionArr);
            for (HashFunction hashFunction : hashFunctionArr) {
                Preconditions.checkArgument(hashFunction.bits() % 8 == 0, "the number of bits (%s) in hashFunction (%s) must be divisible by 8", hashFunction.bits(), (Object) hashFunction);
            }
        }
    }

    public static final class LinearCongruentialGenerator {
        private long state;

        public LinearCongruentialGenerator(long j2) {
            this.state = j2;
        }

        public double nextDouble() {
            long j2 = (this.state * 2862933555777941757L) + 1;
            this.state = j2;
            return ((double) (((int) (j2 >>> 33)) + 1)) / 2.147483648E9d;
        }
    }

    public static class Md5Holder {
        static final HashFunction MD5 = new MessageDigestHashFunction("MD5", "Hashing.md5()");

        private Md5Holder() {
        }
    }

    public static class Sha1Holder {
        static final HashFunction SHA_1 = new MessageDigestHashFunction("SHA-1", "Hashing.sha1()");

        private Sha1Holder() {
        }
    }

    public static class Sha256Holder {
        static final HashFunction SHA_256 = new MessageDigestHashFunction("SHA-256", "Hashing.sha256()");

        private Sha256Holder() {
        }
    }

    public static class Sha384Holder {
        static final HashFunction SHA_384 = new MessageDigestHashFunction("SHA-384", "Hashing.sha384()");

        private Sha384Holder() {
        }
    }

    public static class Sha512Holder {
        static final HashFunction SHA_512 = new MessageDigestHashFunction("SHA-512", "Hashing.sha512()");

        private Sha512Holder() {
        }
    }

    private Hashing() {
    }

    public static HashFunction adler32() {
        return ChecksumType.ADLER_32.hashFunction;
    }

    public static int checkPositiveAndMakeMultipleOf32(int i3) {
        Preconditions.checkArgument(i3 > 0, "Number of bits must be positive");
        return (i3 + 31) & -32;
    }

    public static HashCode combineOrdered(Iterable<HashCode> iterable) {
        Iterator<HashCode> it = iterable.iterator();
        Preconditions.checkArgument(it.hasNext(), "Must be at least 1 hash code to combine.");
        int bits = it.next().bits() / 8;
        byte[] bArr = new byte[bits];
        for (HashCode asBytes : iterable) {
            byte[] asBytes2 = asBytes.asBytes();
            Preconditions.checkArgument(asBytes2.length == bits, "All hashcodes must have the same bit length.");
            for (int i3 = 0; i3 < asBytes2.length; i3++) {
                bArr[i3] = (byte) ((bArr[i3] * SubstrateHelper.NODLE_SUBSTRATE_ID) ^ asBytes2[i3]);
            }
        }
        return HashCode.fromBytesNoCopy(bArr);
    }

    public static HashCode combineUnordered(Iterable<HashCode> iterable) {
        Iterator<HashCode> it = iterable.iterator();
        Preconditions.checkArgument(it.hasNext(), "Must be at least 1 hash code to combine.");
        int bits = it.next().bits() / 8;
        byte[] bArr = new byte[bits];
        for (HashCode asBytes : iterable) {
            byte[] asBytes2 = asBytes.asBytes();
            Preconditions.checkArgument(asBytes2.length == bits, "All hashcodes must have the same bit length.");
            for (int i3 = 0; i3 < asBytes2.length; i3++) {
                bArr[i3] = (byte) (bArr[i3] + asBytes2[i3]);
            }
        }
        return HashCode.fromBytesNoCopy(bArr);
    }

    public static HashFunction concatenating(HashFunction hashFunction, HashFunction hashFunction2, HashFunction... hashFunctionArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(hashFunction);
        arrayList.add(hashFunction2);
        Collections.addAll(arrayList, hashFunctionArr);
        return new ConcatenatedHashFunction((HashFunction[]) arrayList.toArray(new HashFunction[0]));
    }

    public static int consistentHash(HashCode hashCode, int i3) {
        return consistentHash(hashCode.padToLong(), i3);
    }

    public static HashFunction crc32() {
        return ChecksumType.CRC_32.hashFunction;
    }

    public static HashFunction crc32c() {
        return Crc32cHashFunction.CRC_32_C;
    }

    public static HashFunction farmHashFingerprint64() {
        return FarmHashFingerprint64.FARMHASH_FINGERPRINT_64;
    }

    public static HashFunction fingerprint2011() {
        return Fingerprint2011.FINGERPRINT_2011;
    }

    public static HashFunction goodFastHash(int i3) {
        int checkPositiveAndMakeMultipleOf32 = checkPositiveAndMakeMultipleOf32(i3);
        if (checkPositiveAndMakeMultipleOf32 == 32) {
            return Murmur3_32HashFunction.GOOD_FAST_HASH_32;
        }
        if (checkPositiveAndMakeMultipleOf32 <= 128) {
            return Murmur3_128HashFunction.GOOD_FAST_HASH_128;
        }
        int i4 = (checkPositiveAndMakeMultipleOf32 + 127) / 128;
        HashFunction[] hashFunctionArr = new HashFunction[i4];
        hashFunctionArr[0] = Murmur3_128HashFunction.GOOD_FAST_HASH_128;
        int i5 = GOOD_FAST_HASH_SEED;
        for (int i6 = 1; i6 < i4; i6++) {
            i5 += 1500450271;
            hashFunctionArr[i6] = murmur3_128(i5);
        }
        return new ConcatenatedHashFunction(hashFunctionArr);
    }

    public static HashFunction hmacMd5(Key key) {
        return new MacHashFunction("HmacMD5", key, hmacToString("hmacMd5", key));
    }

    public static HashFunction hmacSha1(Key key) {
        return new MacHashFunction("HmacSHA1", key, hmacToString("hmacSha1", key));
    }

    public static HashFunction hmacSha256(Key key) {
        return new MacHashFunction("HmacSHA256", key, hmacToString("hmacSha256", key));
    }

    public static HashFunction hmacSha512(Key key) {
        return new MacHashFunction("HmacSHA512", key, hmacToString("hmacSha512", key));
    }

    private static String hmacToString(String str, Key key) {
        StringBuilder w2 = a.w("Hashing.", str, "(Key[algorithm=");
        w2.append(key.getAlgorithm());
        w2.append(", format=");
        w2.append(key.getFormat());
        w2.append("])");
        return w2.toString();
    }

    @Deprecated
    public static HashFunction md5() {
        return Md5Holder.MD5;
    }

    public static HashFunction murmur3_128(int i3) {
        return new Murmur3_128HashFunction(i3);
    }

    @Deprecated
    public static HashFunction murmur3_32(int i3) {
        return new Murmur3_32HashFunction(i3, false);
    }

    public static HashFunction murmur3_32_fixed(int i3) {
        return new Murmur3_32HashFunction(i3, true);
    }

    @Deprecated
    public static HashFunction sha1() {
        return Sha1Holder.SHA_1;
    }

    public static HashFunction sha256() {
        return Sha256Holder.SHA_256;
    }

    public static HashFunction sha384() {
        return Sha384Holder.SHA_384;
    }

    public static HashFunction sha512() {
        return Sha512Holder.SHA_512;
    }

    public static HashFunction sipHash24() {
        return SipHashFunction.SIP_HASH_24;
    }

    public static int consistentHash(long j2, int i3) {
        int i4 = 0;
        Preconditions.checkArgument(i3 > 0, "buckets must be positive: %s", i3);
        LinearCongruentialGenerator linearCongruentialGenerator = new LinearCongruentialGenerator(j2);
        while (true) {
            int nextDouble = (int) (((double) (i4 + 1)) / linearCongruentialGenerator.nextDouble());
            if (nextDouble < 0 || nextDouble >= i3) {
                return i4;
            }
            i4 = nextDouble;
        }
        return i4;
    }

    public static HashFunction hmacMd5(byte[] bArr) {
        return hmacMd5((Key) new SecretKeySpec((byte[]) Preconditions.checkNotNull(bArr), "HmacMD5"));
    }

    public static HashFunction hmacSha1(byte[] bArr) {
        return hmacSha1((Key) new SecretKeySpec((byte[]) Preconditions.checkNotNull(bArr), "HmacSHA1"));
    }

    public static HashFunction hmacSha256(byte[] bArr) {
        return hmacSha256((Key) new SecretKeySpec((byte[]) Preconditions.checkNotNull(bArr), "HmacSHA256"));
    }

    public static HashFunction hmacSha512(byte[] bArr) {
        return hmacSha512((Key) new SecretKeySpec((byte[]) Preconditions.checkNotNull(bArr), "HmacSHA512"));
    }

    public static HashFunction murmur3_128() {
        return Murmur3_128HashFunction.MURMUR3_128;
    }

    @Deprecated
    public static HashFunction murmur3_32() {
        return Murmur3_32HashFunction.MURMUR3_32;
    }

    public static HashFunction murmur3_32_fixed() {
        return Murmur3_32HashFunction.MURMUR3_32_FIXED;
    }

    public static HashFunction sipHash24(long j2, long j3) {
        return new SipHashFunction(2, 4, j2, j3);
    }

    public static HashFunction concatenating(Iterable<HashFunction> iterable) {
        Preconditions.checkNotNull(iterable);
        ArrayList arrayList = new ArrayList();
        for (HashFunction add : iterable) {
            arrayList.add(add);
        }
        Preconditions.checkArgument(!arrayList.isEmpty(), "number of hash functions (%s) must be > 0", arrayList.size());
        return new ConcatenatedHashFunction((HashFunction[]) arrayList.toArray(new HashFunction[0]));
    }
}
