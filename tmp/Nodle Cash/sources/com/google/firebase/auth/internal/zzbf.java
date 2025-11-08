package com.google.firebase.auth.internal;

import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.p002firebaseauthapi.zzxy;
import com.google.firebase.auth.GetTokenResult;
import java.util.HashMap;
import java.util.Map;

public final class zzbf {
    private static final Logger zza = new Logger("GetTokenResultFactory", new String[0]);

    public static GetTokenResult zza(String str) {
        Map map;
        try {
            map = zzbe.zza(str);
        } catch (zzxy e3) {
            zza.e("Error parsing token claims", e3, new Object[0]);
            map = new HashMap();
        }
        return new GetTokenResult(str, map);
    }
}
