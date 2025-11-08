package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public abstract class zzcv {
    private static zzcv zza = new zzcy();

    public static synchronized zzcv zza() {
        zzcv zzcv;
        synchronized (zzcv.class) {
            zzcv = zza;
        }
        return zzcv;
    }

    public abstract URLConnection zza(URL url, String str) throws IOException;
}
