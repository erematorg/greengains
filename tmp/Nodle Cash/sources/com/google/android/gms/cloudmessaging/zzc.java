package com.google.android.gms.cloudmessaging;

import android.util.Log;
import java.util.Objects;

public final class zzc extends ClassLoader {
    public final Class loadClass(String str, boolean z2) throws ClassNotFoundException {
        if (!Objects.equals(str, "com.google.android.gms.iid.MessengerCompat")) {
            return super.loadClass(str, z2);
        }
        if (!Log.isLoggable("CloudMessengerCompat", 3)) {
            return zzd.class;
        }
        Log.d("CloudMessengerCompat", "Using renamed FirebaseIidMessengerCompat class");
        return zzd.class;
    }
}
