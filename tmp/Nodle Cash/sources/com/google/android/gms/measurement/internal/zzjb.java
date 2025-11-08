package com.google.android.gms.measurement.internal;

public enum zzjb {
    UNINITIALIZED("uninitialized"),
    POLICY("eu_consent_policy"),
    DENIED("denied"),
    GRANTED("granted");
    
    private final String zzf;

    private zzjb(String str) {
        this.zzf = str;
    }

    public final String toString() {
        return this.zzf;
    }
}
