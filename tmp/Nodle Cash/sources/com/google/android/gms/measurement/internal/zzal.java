package com.google.android.gms.measurement.internal;

enum zzal {
    UNSET('0'),
    REMOTE_DEFAULT('1'),
    REMOTE_DELEGATION('2'),
    MANIFEST('3'),
    INITIALIZATION('4'),
    API('5'),
    CHILD_ACCOUNT('6'),
    TCF('7'),
    REMOTE_ENFORCED_DEFAULT('8'),
    FAILSAFE('9');
    
    /* access modifiers changed from: private */
    public final char zzl;

    private zzal(char c3) {
        this.zzl = c3;
    }

    public static zzal zza(char c3) {
        for (zzal zzal : values()) {
            if (zzal.zzl == c3) {
                return zzal;
            }
        }
        return UNSET;
    }
}
