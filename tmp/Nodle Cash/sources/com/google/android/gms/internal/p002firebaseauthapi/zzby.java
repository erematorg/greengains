package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import com.google.android.gms.internal.p002firebaseauthapi.zzvg;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzby  reason: invalid package */
public final class zzby {
    private final zzvg zza;
    private final List<zzca> zzb;
    private final zzrk zzc;

    private zzby(zzvg zzvg, List<zzca> list) {
        this.zza = zzvg;
        this.zzb = list;
        this.zzc = zzrk.zza;
    }

    private static zzbu zza(zzvg.zza zza2) throws GeneralSecurityException {
        zzou zza3 = zzou.zza(zza2.zzb().zzf(), zza2.zzb().zze(), zza2.zzb().zzb(), zza2.zzf(), zza2.zzf() == zzvs.RAW ? null : Integer.valueOf(zza2.zza()));
        zzns zza4 = zzns.zza();
        zzcn zza5 = zzcn.zza();
        if (!zza4.zzb(zza3)) {
            return new zzna(zza3, zza5);
        }
        return zza4.zza(zza3, zza5);
    }

    public final String toString() {
        return zzcs.zza(this.zza).toString();
    }

    public final zzvg zzb() {
        return this.zza;
    }

    public final zzvh zzc() {
        return zzcs.zza(this.zza);
    }

    private static List<zzca> zzb(zzvg zzvg) {
        ArrayList arrayList = new ArrayList(zzvg.zza());
        for (zzvg.zza next : zzvg.zze()) {
            int zza2 = next.zza();
            try {
                arrayList.add(new zzca(zza(next), zza(next.zzc()), zza2, zza2 == zzvg.zzb()));
            } catch (GeneralSecurityException unused) {
                arrayList.add((Object) null);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static void zzc(zzvg zzvg) throws GeneralSecurityException {
        if (zzvg == null || zzvg.zza() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    private zzby(zzvg zzvg, List<zzca> list, zzrk zzrk) {
        this.zza = zzvg;
        this.zzb = list;
        this.zzc = zzrk;
    }

    private static zzbw zza(zzva zzva) throws GeneralSecurityException {
        int i3 = zzbx.zza[zzva.ordinal()];
        if (i3 == 1) {
            return zzbw.zza;
        }
        if (i3 == 2) {
            return zzbw.zzb;
        }
        if (i3 == 3) {
            return zzbw.zzc;
        }
        throw new GeneralSecurityException("Unknown key status");
    }

    public static final zzby zza(zzvg zzvg) throws GeneralSecurityException {
        zzc(zzvg);
        return new zzby(zzvg, zzb(zzvg));
    }

    public final zzby zza() throws GeneralSecurityException {
        zzca zzca;
        zzvg.zza zza2;
        zzva zzva;
        if (this.zza != null) {
            zzvg.zzb zzc2 = zzvg.zzc();
            ArrayList arrayList = new ArrayList(this.zzb.size());
            int i3 = 0;
            for (zzca next : this.zzb) {
                if (next == null || !(next.zzb() instanceof zzck)) {
                    zzvg.zza zza3 = this.zza.zza(i3);
                    zzuw zzb2 = zza3.zzb();
                    if (zzb2.zzb() == zzuw.zzb.ASYMMETRIC_PRIVATE) {
                        zza2 = (zzvg.zza) ((zzaje) ((zzvg.zza.C0058zza) zza3.zzn()).zza(zzco.zza(zzb2.zzf(), zzb2.zze())).zzf());
                        try {
                            zzbu zza4 = zza(zza2);
                            int zza5 = zza2.zza();
                            zzca = new zzca(zza4, zza(zza2.zzc()), zza5, zza5 == this.zza.zzb());
                        } catch (GeneralSecurityException unused) {
                            zzca = null;
                        }
                    } else {
                        throw new GeneralSecurityException("The keyset contains a non-private key");
                    }
                } else {
                    zzbu zzb3 = ((zzck) next.zzb()).zzb();
                    zzca = new zzca(zzb3, next.zzc(), next.zza(), next.zzd());
                    zzbw zzc3 = next.zzc();
                    int zza6 = next.zza();
                    zzou zzou = (zzou) zzns.zza().zza(zzb3, zzou.class, zzcn.zza());
                    Integer zze = zzou.zze();
                    if (zze == null || zze.intValue() == zza6) {
                        if (zzbw.zza.equals(zzc3)) {
                            zzva = zzva.ENABLED;
                        } else if (zzbw.zzb.equals(zzc3)) {
                            zzva = zzva.DISABLED;
                        } else if (zzbw.zzc.equals(zzc3)) {
                            zzva = zzva.DESTROYED;
                        } else {
                            throw new IllegalStateException("Unknown key status");
                        }
                        zza2 = (zzvg.zza) ((zzaje) zzvg.zza.zzd().zza(zzuw.zza().zza(zzou.zzf()).zza(zzou.zzd()).zza(zzou.zza())).zza(zzva).zza(zza6).zza(zzou.zzc()).zzf());
                    } else {
                        throw new GeneralSecurityException("Wrong ID set for key with ID requirement");
                    }
                }
                zzc2.zza(zza2);
                arrayList.add(zzca);
                i3++;
            }
            zzc2.zza(this.zza.zzb());
            return new zzby((zzvg) ((zzaje) zzc2.zzf()), arrayList, this.zzc);
        }
        throw new GeneralSecurityException("cleartext keyset is not available");
    }

    public static final zzby zza(zzcb zzcb, zzbh zzbh) throws GeneralSecurityException, IOException {
        byte[] bArr = new byte[0];
        zztx zza2 = zzcb.zza();
        if (zza2 != null && zza2.zzc().zzb() != 0) {
            return zza(zza(zza2, zzbh, bArr));
        }
        throw new GeneralSecurityException("empty keyset");
    }

    private static zztx zza(zzvg zzvg, zzbh zzbh, byte[] bArr) throws GeneralSecurityException {
        byte[] zzb2 = zzbh.zzb(zzvg.a_(), bArr);
        try {
            if (zzvg.zza(zzbh.zza(zzb2, bArr), zzaiq.zza()).equals(zzvg)) {
                return (zztx) ((zzaje) zztx.zza().zza(zzaho.zza(zzb2)).zza(zzcs.zza(zzvg)).zzf());
            }
            throw new GeneralSecurityException("cannot encrypt keyset");
        } catch (zzajk unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    private static zzvg zza(zztx zztx, zzbh zzbh, byte[] bArr) throws GeneralSecurityException {
        try {
            zzvg zza2 = zzvg.zza(zzbh.zza(zztx.zzc().zzf(), bArr), zzaiq.zza());
            zzc(zza2);
            return zza2;
        } catch (zzajk unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    public final <P> P zza(Class<P> cls) throws GeneralSecurityException {
        zzoy zza2 = zzoy.zza();
        if (zza2 != null) {
            Class<?> zza3 = zza2.zza(cls);
            if (zza3 != null) {
                return zza((zzmo) zza2, cls, zza3);
            }
            throw new GeneralSecurityException("No wrapper found for ".concat(cls.getName()));
        }
        throw new GeneralSecurityException("Currently only subclasses of InternalConfiguration are accepted");
    }

    private final <B, P> P zza(zzmo zzmo, Class<P> cls, Class<B> cls2) throws GeneralSecurityException {
        zzcs.zzb(this.zza);
        zzon<P> zza2 = zzoo.zza(cls2);
        zza2.zza(this.zzc);
        for (int i3 = 0; i3 < this.zzb.size(); i3++) {
            zzvg.zza zza3 = this.zza.zza(i3);
            if (zza3.zzc().equals(zzva.ENABLED)) {
                zzca zzca = this.zzb.get(i3);
                if (zzca != null) {
                    zzbu zzb2 = zzca.zzb();
                    try {
                        Object zza4 = zzmo.zza(zzb2, cls2);
                        if (zza3.zza() == this.zza.zzb()) {
                            zza2.zzb(zza4, zzb2, zza3);
                        } else {
                            zza2.zza(zza4, zzb2, zza3);
                        }
                    } catch (GeneralSecurityException e3) {
                        throw new GeneralSecurityException(C0118y.g("Unable to get primitive ", String.valueOf(cls2), " for key of type ", zza3.zzb().zzf(), ", see https://developers.google.com/tink/faq/registration_errors"), e3);
                    }
                } else {
                    String zzf = zza3.zzb().zzf();
                    throw new GeneralSecurityException("Key parsing of key with index " + i3 + " and type_url " + zzf + " failed, unable to get primitive");
                }
            }
        }
        return zzmo.zza(zza2.zza(), cls);
    }

    public final void zza(zzce zzce, zzbh zzbh) throws GeneralSecurityException, IOException {
        zzce.zza(zza(this.zza, zzbh, new byte[0]));
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.p002firebaseauthapi.zzce r5) throws java.security.GeneralSecurityException, java.io.IOException {
        /*
            r4 = this;
            com.google.android.gms.internal.firebase-auth-api.zzvg r0 = r4.zza
            java.util.List r0 = r0.zze()
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005d
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.firebase-auth-api.zzvg$zza r1 = (com.google.android.gms.internal.p002firebaseauthapi.zzvg.zza) r1
            com.google.android.gms.internal.firebase-auth-api.zzuw r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzuw$zzb r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzuw$zzb r3 = com.google.android.gms.internal.p002firebaseauthapi.zzuw.zzb.UNKNOWN_KEYMATERIAL
            if (r2 == r3) goto L_0x003b
            com.google.android.gms.internal.firebase-auth-api.zzuw r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzuw$zzb r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzuw$zzb r3 = com.google.android.gms.internal.p002firebaseauthapi.zzuw.zzb.SYMMETRIC
            if (r2 == r3) goto L_0x003b
            com.google.android.gms.internal.firebase-auth-api.zzuw r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzuw$zzb r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzuw$zzb r3 = com.google.android.gms.internal.p002firebaseauthapi.zzuw.zzb.ASYMMETRIC_PRIVATE
            if (r2 == r3) goto L_0x003b
            goto L_0x000a
        L_0x003b:
            java.security.GeneralSecurityException r4 = new java.security.GeneralSecurityException
            com.google.android.gms.internal.firebase-auth-api.zzuw r5 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzuw$zzb r5 = r5.zzb()
            java.lang.String r5 = r5.name()
            com.google.android.gms.internal.firebase-auth-api.zzuw r0 = r1.zzb()
            java.lang.String r0 = r0.zzf()
            java.lang.String r1 = "keyset contains key material of type "
            java.lang.String r2 = " for type url "
            java.lang.String r5 = androidx.camera.camera2.internal.C0118y.f(r1, r5, r2, r0)
            r4.<init>(r5)
            throw r4
        L_0x005d:
            com.google.android.gms.internal.firebase-auth-api.zzvg r4 = r4.zza
            r5.zza((com.google.android.gms.internal.p002firebaseauthapi.zzvg) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzby.zza(com.google.android.gms.internal.firebase-auth-api.zzce):void");
    }
}
