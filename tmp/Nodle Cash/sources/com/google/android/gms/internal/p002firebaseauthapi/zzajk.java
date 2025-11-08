package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajk  reason: invalid package */
public class zzajk extends IOException {
    private zzakp zza = null;
    private boolean zzb;

    public zzajk(IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    public static zzajn zza() {
        return new zzajn("Protocol message tag had invalid wire type.");
    }

    public static zzajk zzb() {
        return new zzajk("Protocol message end-group tag did not match expected tag.");
    }

    public static zzajk zzc() {
        return new zzajk("Protocol message contained an invalid tag (zero).");
    }

    public static zzajk zzd() {
        return new zzajk("Protocol message had invalid UTF-8.");
    }

    public static zzajk zze() {
        return new zzajk("CodedInputStream encountered a malformed varint.");
    }

    public static zzajk zzf() {
        return new zzajk("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static zzajk zzg() {
        return new zzajk("Failed to parse the message.");
    }

    public static zzajk zzh() {
        return new zzajk("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    public static zzajk zzi() {
        return new zzajk("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final void zzj() {
        this.zzb = true;
    }

    public final boolean zzk() {
        return this.zzb;
    }

    public final zzajk zza(zzakp zzakp) {
        this.zza = zzakp;
        return this;
    }

    public zzajk(String str) {
        super(str);
    }
}
