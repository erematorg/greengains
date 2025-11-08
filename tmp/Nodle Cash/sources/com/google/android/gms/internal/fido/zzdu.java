package com.google.android.gms.internal.fido;

import androidx.collection.SieveCacheKt;
import androidx.compose.animation.core.a;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.common.primitives.SignedBytes;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.msgpack.core.MessagePack;

public final class zzdu implements Closeable {
    private final InputStream zza;
    private zzdt zzb;
    private final byte[] zzc = new byte[8];
    private final zzdv zzd = zzdv.zza();

    public zzdu(InputStream inputStream) {
        this.zza = inputStream;
    }

    private final long zzh() throws IOException {
        if (this.zzb.zza() < 24) {
            long zza2 = (long) this.zzb.zza();
            this.zzb = null;
            return zza2;
        } else if (this.zzb.zza() == 24) {
            int read = this.zza.read();
            if (read != -1) {
                this.zzb = null;
                return ((long) read) & 255;
            }
            throw new EOFException();
        } else if (this.zzb.zza() == 25) {
            zzk(this.zzc, 2);
            byte[] bArr = this.zzc;
            return ((((long) bArr[0]) & 255) << 8) | (((long) bArr[1]) & 255);
        } else if (this.zzb.zza() == 26) {
            zzk(this.zzc, 4);
            byte[] bArr2 = this.zzc;
            return ((((long) bArr2[0]) & 255) << 24) | ((((long) bArr2[1]) & 255) << 16) | ((((long) bArr2[2]) & 255) << 8) | (((long) bArr2[3]) & 255);
        } else if (this.zzb.zza() == 27) {
            zzk(this.zzc, 8);
            byte[] bArr3 = this.zzc;
            long j2 = (long) bArr3[2];
            long j3 = (long) bArr3[3];
            long j4 = (long) bArr3[4];
            long j5 = (long) bArr3[5];
            return ((((long) bArr3[0]) & 255) << 56) | ((((long) bArr3[1]) & 255) << 48) | ((j2 & 255) << 40) | ((j3 & 255) << 32) | ((j4 & 255) << 24) | ((j5 & 255) << 16) | ((((long) bArr3[6]) & 255) << 8) | (((long) bArr3[7]) & 255);
        } else {
            throw new IOException(a.r("invalid additional information ", this.zzb.zza(), " for major type ", this.zzb.zzc()));
        }
    }

    private final void zzi() throws IOException {
        zzd();
        if (this.zzb.zza() == 31) {
            throw new IllegalStateException(A.a.k("expected definite length but found ", this.zzb.zza()));
        }
    }

    private final void zzj(byte b3) throws IOException {
        zzd();
        if (this.zzb.zzb() != b3) {
            throw new IllegalStateException(a.r("expected major type ", (b3 >> 5) & 7, " but found ", this.zzb.zzc()));
        }
    }

    private final void zzk(byte[] bArr, int i3) throws IOException {
        int i4 = 0;
        while (i4 != i3) {
            int read = this.zza.read(bArr, i4, i3 - i4);
            if (read != -1) {
                i4 += read;
            } else {
                throw new EOFException();
            }
        }
        this.zzb = null;
    }

    private final byte[] zzl() throws IOException {
        zzi();
        long zzh = zzh();
        if (zzh < 0 || zzh > SieveCacheKt.NodeLinkMask) {
            throw new UnsupportedOperationException("the maximum supported byte/text string length is 2147483647 bytes");
        } else if (((long) this.zza.available()) >= zzh) {
            int i3 = (int) zzh;
            byte[] bArr = new byte[i3];
            zzk(bArr, i3);
            return bArr;
        } else {
            throw new EOFException();
        }
    }

    public final void close() throws IOException {
        this.zza.close();
        this.zzd.zzb();
    }

    public final long zza() throws IOException {
        zzj(Byte.MIN_VALUE);
        zzi();
        long zzh = zzh();
        int i3 = (zzh > 0 ? 1 : (zzh == 0 ? 0 : -1));
        if (i3 >= 0) {
            if (i3 > 0) {
                this.zzd.zzg(zzh);
            }
            return zzh;
        }
        throw new UnsupportedOperationException("the maximum supported array length is 9223372036854775807");
    }

    public final long zzb() throws IOException {
        boolean z2;
        zzd();
        if (this.zzb.zzb() == 0) {
            z2 = true;
        } else if (this.zzb.zzb() == 32) {
            z2 = false;
        } else {
            throw new IllegalStateException(A.a.k("expected major type 0 or 1 but found ", this.zzb.zzc()));
        }
        long zzh = zzh();
        if (zzh >= 0) {
            return z2 ? zzh : ~zzh;
        }
        throw new UnsupportedOperationException("the maximum supported unsigned/negative integer is 9223372036854775807");
    }

    public final long zzc() throws IOException {
        zzj(MessagePack.Code.FIXSTR_PREFIX);
        zzi();
        long zzh = zzh();
        int i3 = (zzh > 0 ? 1 : (zzh == 0 ? 0 : -1));
        if (i3 < 0 || zzh > 4611686018427387903L) {
            throw new UnsupportedOperationException("the maximum supported map length is 4611686018427387903L");
        }
        if (i3 > 0) {
            this.zzd.zzg(zzh + zzh);
        }
        return zzh;
    }

    public final zzdt zzd() throws IOException {
        if (this.zzb == null) {
            int read = this.zza.read();
            if (read == -1) {
                this.zzd.zzb();
                return null;
            }
            zzdt zzdt = new zzdt(read);
            this.zzb = zzdt;
            byte zzb2 = zzdt.zzb();
            if (!(zzb2 == Byte.MIN_VALUE || zzb2 == -96 || zzb2 == -64)) {
                if (zzb2 != -32) {
                    if (!(zzb2 == 0 || zzb2 == 32)) {
                        if (zzb2 == 64) {
                            this.zzd.zze(-1);
                        } else if (zzb2 == 96) {
                            this.zzd.zze(-2);
                        } else {
                            throw new IllegalStateException(A.a.k("invalid major type: ", this.zzb.zzc()));
                        }
                        this.zzd.zzf();
                    }
                } else if (this.zzb.zza() == 31) {
                    this.zzd.zzc();
                }
            }
            this.zzd.zzd();
            this.zzd.zzf();
        }
        return this.zzb;
    }

    public final String zze() throws IOException {
        zzj(CBORConstants.BYTE_EMPTY_STRING);
        return new String(zzl(), StandardCharsets.UTF_8);
    }

    public final boolean zzf() throws IOException {
        zzj(MessagePack.Code.NEGFIXINT_PREFIX);
        if (this.zzb.zza() <= 24) {
            int zzh = (int) zzh();
            if (zzh == 20) {
                return false;
            }
            if (zzh == 21) {
                return true;
            }
            throw new IllegalStateException("expected FALSE or TRUE");
        }
        throw new IllegalStateException("expected simple value");
    }

    public final byte[] zzg() throws IOException {
        zzj(SignedBytes.MAX_POWER_OF_TWO);
        return zzl();
    }
}
