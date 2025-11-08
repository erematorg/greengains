package com.google.android.gms.internal.measurement;

import java.io.IOException;

public class zzlk extends IOException {
    public zzlk(String str) {
        super(str);
    }

    public static zzln zza() {
        return new zzln("Protocol message tag had invalid wire type.");
    }

    public static zzlk zzb() {
        return new zzlk("Protocol message end-group tag did not match expected tag.");
    }

    public static zzlk zzc() {
        return new zzlk("Protocol message contained an invalid tag (zero).");
    }

    public static zzlk zzd() {
        return new zzlk("Protocol message had invalid UTF-8.");
    }

    public static zzlk zze() {
        return new zzlk("CodedInputStream encountered a malformed varint.");
    }

    public static zzlk zzf() {
        return new zzlk("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static zzlk zzg() {
        return new zzlk("Failed to parse the message.");
    }

    public static zzlk zzh() {
        return new zzlk("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
    }

    public static zzlk zzi() {
        return new zzlk("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }
}
