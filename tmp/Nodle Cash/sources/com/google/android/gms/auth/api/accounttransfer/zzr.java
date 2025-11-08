package com.google.android.gms.auth.api.accounttransfer;

import android.os.Bundle;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import java.util.ArrayList;
import java.util.Collections;

public final class zzr implements Api.ApiOptions.Optional {
    public static final zzr zza;
    private final Bundle zzb;

    static {
        Bundle bundle = new Bundle();
        if (!bundle.containsKey("accountTypes")) {
            bundle.putStringArrayList("accountTypes", new ArrayList(0));
        }
        zza = new zzr(bundle, (zzq) null);
    }

    public /* synthetic */ zzr(Bundle bundle, zzq zzq) {
        this.zzb = bundle;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r6 instanceof com.google.android.gms.auth.api.accounttransfer.zzr
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.google.android.gms.auth.api.accounttransfer.zzr r6 = (com.google.android.gms.auth.api.accounttransfer.zzr) r6
            android.os.Bundle r5 = r5.zza()
            android.os.Bundle r6 = r6.zza()
            int r1 = r5.size()
            int r3 = r6.size()
            if (r1 == r3) goto L_0x001f
            return r2
        L_0x001f:
            java.util.Set r1 = r5.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0027:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0049
            java.lang.Object r3 = r1.next()
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = r6.containsKey(r3)
            if (r4 != 0) goto L_0x003a
            return r2
        L_0x003a:
            java.lang.Object r4 = r5.get(r3)
            java.lang.Object r3 = r6.get(r3)
            boolean r3 = com.google.android.gms.common.internal.Objects.equal(r4, r3)
            if (r3 != 0) goto L_0x0027
            return r2
        L_0x0049:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.accounttransfer.zzr.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Bundle zza2 = zza();
        int size = zza2.size();
        ArrayList arrayList = new ArrayList(size + size);
        ArrayList arrayList2 = new ArrayList(zza2.keySet());
        Collections.sort(arrayList2);
        int size2 = arrayList2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            String str = (String) arrayList2.get(i3);
            arrayList.add(str);
            arrayList.add(zza2.get(str));
        }
        return Objects.hashCode(arrayList);
    }

    public final Bundle zza() {
        return new Bundle(this.zzb);
    }
}
