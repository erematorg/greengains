package com.google.android.recaptcha.internal;

import A.a;
import com.google.android.recaptcha.internal.zzge;
import com.google.android.recaptcha.internal.zzgf;
import java.io.IOException;

public abstract class zzgf<MessageType extends zzgf<MessageType, BuilderType>, BuilderType extends zzge<MessageType, BuilderType>> implements zzke {
    protected int zza = 0;

    /* JADX WARNING: type inference failed for: r5v0, types: [java.util.Collection, java.lang.Object, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void zzc(java.lang.Iterable r5, java.util.List r6) {
        /*
            byte[] r0 = com.google.android.recaptcha.internal.zzjc.zzd
            r5.getClass()
            boolean r0 = r5 instanceof com.google.android.recaptcha.internal.zzjm
            java.lang.String r1 = " is null."
            java.lang.String r2 = "Element at index "
            if (r0 == 0) goto L_0x0055
            com.google.android.recaptcha.internal.zzjm r5 = (com.google.android.recaptcha.internal.zzjm) r5
            java.util.List r5 = r5.zzh()
            r0 = r6
            com.google.android.recaptcha.internal.zzjm r0 = (com.google.android.recaptcha.internal.zzjm) r0
            int r6 = r6.size()
            java.util.Iterator r5 = r5.iterator()
        L_0x001e:
            boolean r3 = r5.hasNext()
            if (r3 == 0) goto L_0x009f
            java.lang.Object r3 = r5.next()
            if (r3 != 0) goto L_0x0045
            int r5 = r0.size()
            int r5 = r5 - r6
            java.lang.String r5 = androidx.camera.camera2.internal.C0118y.c(r5, r2, r1)
            int r1 = r0.size()
        L_0x0037:
            int r1 = r1 + -1
            if (r1 < r6) goto L_0x003f
            r0.remove(r1)
            goto L_0x0037
        L_0x003f:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            r6.<init>(r5)
            throw r6
        L_0x0045:
            boolean r4 = r3 instanceof com.google.android.recaptcha.internal.zzgw
            if (r4 == 0) goto L_0x004f
            com.google.android.recaptcha.internal.zzgw r3 = (com.google.android.recaptcha.internal.zzgw) r3
            r0.zzi(r3)
            goto L_0x001e
        L_0x004f:
            java.lang.String r3 = (java.lang.String) r3
            r0.add(r3)
            goto L_0x001e
        L_0x0055:
            boolean r0 = r5 instanceof com.google.android.recaptcha.internal.zzkm
            if (r0 != 0) goto L_0x00a0
            boolean r0 = r6 instanceof java.util.ArrayList
            if (r0 == 0) goto L_0x006c
            r0 = r6
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r3 = r6.size()
            int r4 = r5.size()
            int r4 = r4 + r3
            r0.ensureCapacity(r4)
        L_0x006c:
            int r0 = r6.size()
            java.util.Iterator r5 = r5.iterator()
        L_0x0074:
            boolean r3 = r5.hasNext()
            if (r3 == 0) goto L_0x009f
            java.lang.Object r3 = r5.next()
            if (r3 != 0) goto L_0x009b
            int r5 = r6.size()
            int r5 = r5 - r0
            java.lang.String r5 = androidx.camera.camera2.internal.C0118y.c(r5, r2, r1)
            int r1 = r6.size()
        L_0x008d:
            int r1 = r1 + -1
            if (r1 < r0) goto L_0x0095
            r6.remove(r1)
            goto L_0x008d
        L_0x0095:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            r6.<init>(r5)
            throw r6
        L_0x009b:
            r6.add(r3)
            goto L_0x0074
        L_0x009f:
            return
        L_0x00a0:
            r6.addAll(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzgf.zzc(java.lang.Iterable, java.util.List):void");
    }

    public int zza(zzkr zzkr) {
        throw null;
    }

    public final zzgw zzb() {
        try {
            int zzn = zzn();
            zzgw zzgw = zzgw.zzb;
            byte[] bArr = new byte[zzn];
            zzhh zzA = zzhh.zzA(bArr, 0, zzn);
            zze(zzA);
            zzA.zzB();
            return new zzgt(bArr);
        } catch (IOException e3) {
            throw new RuntimeException(a.l("Serializing ", getClass().getName(), " to a ByteString threw an IOException (should never happen)."), e3);
        }
    }

    public final byte[] zzd() {
        try {
            int zzn = zzn();
            byte[] bArr = new byte[zzn];
            zzhh zzA = zzhh.zzA(bArr, 0, zzn);
            zze(zzA);
            zzA.zzB();
            return bArr;
        } catch (IOException e3) {
            throw new RuntimeException(a.l("Serializing ", getClass().getName(), " to a byte array threw an IOException (should never happen)."), e3);
        }
    }
}
