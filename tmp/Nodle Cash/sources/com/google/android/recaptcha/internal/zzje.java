package com.google.android.recaptcha.internal;

import java.io.IOException;

public class zzje extends IOException {
    private zzke zza = null;
    private boolean zzb;

    public zzje(IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    public static zzjd zza() {
        return new zzjd("Protocol message tag had invalid wire type.");
    }

    public static zzje zzb() {
        return new zzje("Protocol message end-group tag did not match expected tag.");
    }

    public static zzje zzc() {
        return new zzje("Protocol message contained an invalid tag (zero).");
    }

    public static zzje zzd() {
        return new zzje("Protocol message had invalid UTF-8.");
    }

    public static zzje zze() {
        return new zzje("CodedInputStream encountered a malformed varint.");
    }

    public static zzje zzf() {
        return new zzje("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static zzje zzg() {
        return new zzje("Failed to parse the message.");
    }

    public static zzje zzi() {
        return new zzje("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    public static zzje zzj() {
        return new zzje("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzje zzh(zzke zzke) {
        this.zza = zzke;
        return this;
    }

    public final void zzk() {
        this.zzb = true;
    }

    public final boolean zzl() {
        return this.zzb;
    }

    public zzje(String str) {
        super(str);
    }
}
