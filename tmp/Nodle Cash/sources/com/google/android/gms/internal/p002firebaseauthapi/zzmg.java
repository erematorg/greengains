package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.browser.trusted.c;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.common.base.Ascii;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Arrays;
import org.msgpack.core.MessagePack;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmg  reason: invalid package */
public final class zzmg {
    private static final byte[][] zza = {new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{MessagePack.Code.NEGFIXINT_PREFIX, -21, 122, 124, 59, 65, -72, -82, Ascii.SYN, 86, -29, -6, -15, CBORConstants.BYTE_ARRAY_INDEFINITE, -60, 106, MessagePack.Code.STR16, 9, -115, -21, -100, 50, -79, -3, -122, 98, 5, Ascii.SYN, 95, 73, -72, 0}, new byte[]{95, -100, -107, -68, -93, 80, -116, 36, -79, MessagePack.Code.INT8, -79, 85, -100, -125, ByteSourceJsonBootstrapper.UTF8_BOM_1, 91, 4, 68, 92, -60, 88, Ascii.FS, -114, -122, MessagePack.Code.FIXEXT16, 34, 78, MessagePack.Code.ARRAY32, MessagePack.Code.INT8, CBORConstants.BYTE_ARRAY_INDEFINITE, 17, 87}, new byte[]{-20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}, new byte[]{-19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}, new byte[]{-18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}};

    public static void zza(long[] jArr, byte[] bArr, byte[] bArr2) throws InvalidKeyException {
        long[] jArr2 = jArr;
        byte[] bArr3 = bArr2;
        int i3 = 32;
        if (bArr3.length == 32) {
            byte[] copyOf = Arrays.copyOf(bArr3, bArr3.length);
            copyOf[31] = (byte) (copyOf[31] & Byte.MAX_VALUE);
            int i4 = 0;
            int i5 = 0;
            while (true) {
                byte[][] bArr4 = zza;
                if (i5 >= bArr4.length) {
                    long[] zza2 = zzml.zza(copyOf);
                    long[] jArr3 = new long[19];
                    long[] jArr4 = new long[19];
                    jArr4[0] = 1;
                    long[] jArr5 = new long[19];
                    jArr5[0] = 1;
                    long[] jArr6 = new long[19];
                    long[] jArr7 = new long[19];
                    long[] jArr8 = new long[19];
                    jArr8[0] = 1;
                    long[] jArr9 = new long[19];
                    long[] jArr10 = new long[19];
                    jArr10[0] = 1;
                    int i6 = 10;
                    System.arraycopy(zza2, 0, jArr3, 0, 10);
                    int i7 = 0;
                    while (i7 < i3) {
                        byte b3 = bArr[31 - i7] & 255;
                        while (i4 < 8) {
                            int i8 = (b3 >> (7 - i4)) & 1;
                            zza(jArr5, jArr3, i8);
                            zza(jArr6, jArr4, i8);
                            long[] copyOf2 = Arrays.copyOf(jArr5, i6);
                            byte b4 = b3;
                            long[] jArr11 = new long[19];
                            int i9 = i7;
                            long[] jArr12 = new long[19];
                            int i10 = i4;
                            long[] jArr13 = new long[19];
                            int i11 = i8;
                            long[] jArr14 = new long[19];
                            long[] jArr15 = jArr10;
                            long[] jArr16 = new long[19];
                            long[] jArr17 = new long[19];
                            zzml.zzd(jArr5, jArr6);
                            zzml.zzc(jArr6, copyOf2);
                            long[] copyOf3 = Arrays.copyOf(jArr3, 10);
                            zzml.zzd(jArr3, jArr4);
                            zzml.zzc(jArr4, copyOf3);
                            zzml.zzb(jArr13, jArr3, jArr6);
                            zzml.zzb(jArr14, jArr5, jArr4);
                            zzml.zzb(jArr13);
                            zzml.zza(jArr13);
                            zzml.zzb(jArr14);
                            zzml.zza(jArr14);
                            long[] jArr18 = jArr3;
                            System.arraycopy(jArr13, 0, copyOf3, 0, 10);
                            zzml.zzd(jArr13, jArr14);
                            zzml.zzc(jArr14, copyOf3);
                            zzml.zzb(jArr17, jArr13);
                            zzml.zzb(jArr16, jArr14);
                            zzml.zzb(jArr14, jArr16, zza2);
                            zzml.zzb(jArr14);
                            zzml.zza(jArr14);
                            System.arraycopy(jArr17, 0, jArr7, 0, 10);
                            System.arraycopy(jArr14, 0, jArr8, 0, 10);
                            zzml.zzb(jArr11, jArr5);
                            zzml.zzb(jArr12, jArr6);
                            zzml.zzb(jArr9, jArr11, jArr12);
                            zzml.zzb(jArr9);
                            zzml.zza(jArr9);
                            zzml.zzc(jArr12, jArr11);
                            long[] jArr19 = new long[19];
                            Arrays.fill(jArr19, 10, 18, 0);
                            zzml.zza(jArr19, jArr12, 121665);
                            zzml.zza(jArr19);
                            zzml.zzd(jArr19, jArr11);
                            long[] jArr20 = jArr15;
                            zzml.zzb(jArr20, jArr12, jArr19);
                            zzml.zzb(jArr20);
                            zzml.zza(jArr20);
                            int i12 = i11;
                            zza(jArr9, jArr7, i12);
                            zza(jArr20, jArr8, i12);
                            i4 = i10 + 1;
                            long[] jArr21 = jArr;
                            byte[] bArr5 = bArr2;
                            jArr3 = jArr7;
                            b3 = b4;
                            i7 = i9;
                            jArr7 = jArr18;
                            i6 = 10;
                            long[] jArr22 = jArr8;
                            jArr8 = jArr4;
                            jArr4 = jArr22;
                            long[] jArr23 = jArr9;
                            jArr9 = jArr5;
                            jArr5 = jArr23;
                            long[] jArr24 = jArr20;
                            jArr10 = jArr6;
                            jArr6 = jArr24;
                        }
                        long[] jArr25 = jArr3;
                        i7++;
                        long[] jArr26 = jArr;
                        byte[] bArr6 = bArr2;
                        i3 = 32;
                        i4 = 0;
                        i6 = 10;
                    }
                    int i13 = i6;
                    long[] jArr27 = new long[i13];
                    zzml.zza(jArr27, jArr6);
                    long[] jArr28 = jArr;
                    zzml.zza(jArr28, jArr5, jArr27);
                    long[] jArr29 = new long[i13];
                    long[] jArr30 = new long[i13];
                    long[] jArr31 = new long[11];
                    long[] jArr32 = new long[11];
                    long[] jArr33 = new long[11];
                    zzml.zza(jArr29, zza2, jArr28);
                    zzml.zzd(jArr30, zza2, jArr28);
                    long[] jArr34 = new long[i13];
                    jArr34[0] = 486662;
                    zzml.zzd(jArr32, jArr30, jArr34);
                    zzml.zza(jArr32, jArr32, jArr4);
                    zzml.zzd(jArr32, jArr3);
                    zzml.zza(jArr32, jArr32, jArr29);
                    zzml.zza(jArr32, jArr32, jArr3);
                    zzml.zza(jArr31, jArr32, 4);
                    zzml.zza(jArr31);
                    zzml.zza(jArr32, jArr29, jArr4);
                    zzml.zzc(jArr32, jArr32, jArr4);
                    zzml.zza(jArr33, jArr30, jArr3);
                    zzml.zzd(jArr32, jArr32, jArr33);
                    zzml.zzb(jArr32, jArr32);
                    if (!MessageDigest.isEqual(zzml.zzc(jArr31), zzml.zzc(jArr32))) {
                        throw new IllegalStateException(c.a("Arithmetic error in curve multiplication with the public key: ", zzxl.zza(bArr2)));
                    }
                    return;
                } else if (!MessageDigest.isEqual(bArr4[i5], copyOf)) {
                    i5++;
                } else {
                    throw new InvalidKeyException(c.a("Banned public key: ", zzxl.zza(bArr4[i5])));
                }
            }
        } else {
            throw new InvalidKeyException("Public key length is not 32-byte");
        }
    }

    private static void zza(long[] jArr, long[] jArr2, int i3) {
        int i4 = -i3;
        for (int i5 = 0; i5 < 10; i5++) {
            long j2 = jArr[i5];
            int i6 = (((int) j2) ^ ((int) jArr2[i5])) & i4;
            jArr[i5] = (long) (((int) j2) ^ i6);
            jArr2[i5] = (long) (((int) jArr2[i5]) ^ i6);
        }
    }
}
