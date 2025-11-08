package com.google.android.gms.internal.fido;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import javax.annotation.CheckForNull;

public final class zzbg extends zzbh implements NavigableMap {
    public static final /* synthetic */ int zzb = 0;
    private static final Comparator zzc;
    private static final zzbg zzd;
    /* access modifiers changed from: private */
    public final transient zzbu zze;
    /* access modifiers changed from: private */
    public final transient zzaz zzf;
    @CheckForNull
    private final transient zzbg zzg;

    static {
        zzbp zzbp = zzbp.zza;
        zzc = zzbp;
        zzbu zzq = zzbi.zzq(zzbp);
        int i3 = zzaz.zzd;
        zzd = new zzbg(zzq, zzbs.zza, (zzbg) null);
    }

    public zzbg(zzbu zzbu, zzaz zzaz, @CheckForNull zzbg zzbg) {
        this.zze = zzbu;
        this.zzf = zzaz;
        this.zzg = zzbg;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: java.util.Set} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: java.util.Set} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: java.util.Set} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.fido.zzbg zzf(java.util.Map r11) {
        /*
            java.util.Comparator r0 = zzc
            java.util.Comparator r1 = r11.comparator()
            r2 = 1
            if (r1 != 0) goto L_0x000b
            r1 = r2
            goto L_0x000f
        L_0x000b:
            boolean r1 = r0.equals(r1)
        L_0x000f:
            java.util.Set r11 = r11.entrySet()
            java.util.Map$Entry[] r3 = com.google.android.gms.internal.fido.zzba.zza
            boolean r4 = r11 instanceof java.util.Collection
            if (r4 != 0) goto L_0x0034
            java.util.Iterator r11 = r11.iterator()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r11.getClass()
        L_0x0025:
            boolean r5 = r11.hasNext()
            if (r5 == 0) goto L_0x0033
            java.lang.Object r5 = r11.next()
            r4.add(r5)
            goto L_0x0025
        L_0x0033:
            r11 = r4
        L_0x0034:
            java.lang.Object[] r11 = r11.toArray(r3)
            java.util.Map$Entry[] r11 = (java.util.Map.Entry[]) r11
            int r3 = r11.length
            if (r3 == 0) goto L_0x00ef
            r4 = 0
            r5 = 0
            if (r3 == r2) goto L_0x00ce
            java.lang.Object[] r6 = new java.lang.Object[r3]
            java.lang.Object[] r7 = new java.lang.Object[r3]
            if (r1 == 0) goto L_0x0060
        L_0x0047:
            if (r5 >= r3) goto L_0x00bb
            r1 = r11[r5]
            r1.getClass()
            java.lang.Object r2 = r1.getKey()
            java.lang.Object r1 = r1.getValue()
            com.google.android.gms.internal.fido.zzas.zza(r2, r1)
            r6[r5] = r2
            r7[r5] = r1
            int r5 = r5 + 1
            goto L_0x0047
        L_0x0060:
            com.google.android.gms.internal.fido.zzbd r1 = new com.google.android.gms.internal.fido.zzbd
            r1.<init>(r0)
            java.util.Arrays.sort(r11, r5, r3, r1)
            r1 = r11[r5]
            r1.getClass()
            java.lang.Object r8 = r1.getKey()
            r6[r5] = r8
            java.lang.Object r1 = r1.getValue()
            r7[r5] = r1
            r5 = r6[r5]
            com.google.android.gms.internal.fido.zzas.zza(r5, r1)
        L_0x007e:
            if (r2 >= r3) goto L_0x00bb
            int r1 = r2 + -1
            r1 = r11[r1]
            r1.getClass()
            r5 = r11[r2]
            r5.getClass()
            java.lang.Object r9 = r5.getKey()
            java.lang.Object r10 = r5.getValue()
            com.google.android.gms.internal.fido.zzas.zza(r9, r10)
            r6[r2] = r9
            r7[r2] = r10
            int r8 = r0.compare(r8, r9)
            if (r8 == 0) goto L_0x00a5
            int r2 = r2 + 1
            r8 = r9
            goto L_0x007e
        L_0x00a5:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r0 = java.lang.String.valueOf(r1)
            java.lang.String r1 = java.lang.String.valueOf(r5)
            java.lang.String r2 = "Multiple entries with same key: "
            java.lang.String r3 = " and "
            java.lang.String r0 = androidx.camera.camera2.internal.C0118y.f(r2, r0, r3, r1)
            r11.<init>(r0)
            throw r11
        L_0x00bb:
            com.google.android.gms.internal.fido.zzbg r11 = new com.google.android.gms.internal.fido.zzbg
            com.google.android.gms.internal.fido.zzbu r1 = new com.google.android.gms.internal.fido.zzbu
            com.google.android.gms.internal.fido.zzaz r2 = com.google.android.gms.internal.fido.zzaz.zzh(r6, r3)
            r1.<init>(r2, r0)
            com.google.android.gms.internal.fido.zzaz r0 = com.google.android.gms.internal.fido.zzaz.zzh(r7, r3)
            r11.<init>(r1, r0, r4)
            goto L_0x00f3
        L_0x00ce:
            r11 = r11[r5]
            r11.getClass()
            java.lang.Object r1 = r11.getKey()
            java.lang.Object r11 = r11.getValue()
            com.google.android.gms.internal.fido.zzbg r2 = new com.google.android.gms.internal.fido.zzbg
            com.google.android.gms.internal.fido.zzbu r3 = new com.google.android.gms.internal.fido.zzbu
            com.google.android.gms.internal.fido.zzaz r1 = com.google.android.gms.internal.fido.zzaz.zzj(r1)
            r3.<init>(r1, r0)
            com.google.android.gms.internal.fido.zzaz r11 = com.google.android.gms.internal.fido.zzaz.zzj(r11)
            r2.<init>(r3, r11, r4)
            r11 = r2
            goto L_0x00f3
        L_0x00ef:
            com.google.android.gms.internal.fido.zzbg r11 = zzg(r0)
        L_0x00f3:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.zzbg.zzf(java.util.Map):com.google.android.gms.internal.fido.zzbg");
    }

    public static zzbg zzg(Comparator comparator) {
        if (zzbp.zza.equals(comparator)) {
            return zzd;
        }
        zzbu zzq = zzbi.zzq(comparator);
        int i3 = zzaz.zzd;
        return new zzbg(zzq, zzbs.zza, (zzbg) null);
    }

    private final zzbg zzl(int i3, int i4) {
        if (i3 == 0) {
            if (i4 == this.zzf.size()) {
                return this;
            }
            i3 = 0;
        }
        return i3 == i4 ? zzg(this.zze.zza) : new zzbg(this.zze.zzu(i3, i4), this.zzf.subList(i3, i4), (zzbg) null);
    }

    @CheckForNull
    public final Map.Entry ceilingEntry(Object obj) {
        return tailMap(obj, true).firstEntry();
    }

    @CheckForNull
    public final Object ceilingKey(Object obj) {
        return zzbn.zza(ceilingEntry(obj));
    }

    public final Comparator comparator() {
        return this.zze.zza;
    }

    public final /* synthetic */ NavigableSet descendingKeySet() {
        return this.zze.descendingSet();
    }

    public final /* bridge */ /* synthetic */ NavigableMap descendingMap() {
        zzbg zzbg = this.zzg;
        if (zzbg != null) {
            return zzbg;
        }
        if (!isEmpty()) {
            return new zzbg((zzbu) this.zze.descendingSet(), this.zzf.zzf(), this);
        }
        Comparator comparator = this.zze.zza;
        return zzg((comparator instanceof zzbr ? (zzbr) comparator : new zzat(comparator)).zza());
    }

    public final /* bridge */ /* synthetic */ Set entrySet() {
        return entrySet();
    }

    @CheckForNull
    public final Map.Entry firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) entrySet().zzi().get(0);
    }

    public final Object firstKey() {
        return this.zze.first();
    }

    @CheckForNull
    public final Map.Entry floorEntry(Object obj) {
        return headMap(obj, true).lastEntry();
    }

    @CheckForNull
    public final Object floorKey(Object obj) {
        return zzbn.zza(floorEntry(obj));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        if (r4 < 0) goto L_0x0005;
     */
    @javax.annotation.CheckForNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.fido.zzbu r0 = r3.zze
            r1 = -1
            if (r4 != 0) goto L_0x0007
        L_0x0005:
            r4 = r1
            goto L_0x0012
        L_0x0007:
            com.google.android.gms.internal.fido.zzaz r2 = r0.zzd     // Catch:{ ClassCastException -> 0x0005 }
            java.util.Comparator r0 = r0.zza     // Catch:{ ClassCastException -> 0x0005 }
            int r4 = java.util.Collections.binarySearch(r2, r4, r0)     // Catch:{ ClassCastException -> 0x0005 }
            if (r4 >= 0) goto L_0x0012
            goto L_0x0005
        L_0x0012:
            if (r4 != r1) goto L_0x0016
            r3 = 0
            return r3
        L_0x0016:
            com.google.android.gms.internal.fido.zzaz r3 = r3.zzf
            java.lang.Object r3 = r3.get(r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.zzbg.get(java.lang.Object):java.lang.Object");
    }

    public final /* synthetic */ SortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    @CheckForNull
    public final Map.Entry higherEntry(Object obj) {
        return tailMap(obj, false).firstEntry();
    }

    @CheckForNull
    public final Object higherKey(Object obj) {
        return zzbn.zza(higherEntry(obj));
    }

    public final /* synthetic */ Set keySet() {
        return this.zze;
    }

    @CheckForNull
    public final Map.Entry lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) entrySet().zzi().get(this.zzf.size() - 1);
    }

    public final Object lastKey() {
        return this.zze.last();
    }

    @CheckForNull
    public final Map.Entry lowerEntry(Object obj) {
        return headMap(obj, false).lastEntry();
    }

    @CheckForNull
    public final Object lowerKey(Object obj) {
        return zzbn.zza(lowerEntry(obj));
    }

    public final /* synthetic */ NavigableSet navigableKeySet() {
        return this.zze;
    }

    @CheckForNull
    @Deprecated
    public final Map.Entry pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    @Deprecated
    public final Map.Entry pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        return this.zzf.size();
    }

    public final /* bridge */ /* synthetic */ SortedMap subMap(Object obj, Object obj2) {
        return subMap(obj, true, obj2, false);
    }

    public final /* synthetic */ SortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }

    public final /* synthetic */ Collection values() {
        return this.zzf;
    }

    public final zzav zza() {
        return this.zzf;
    }

    public final zzbc zzb() {
        return isEmpty() ? zzbt.zza : new zzbf(this);
    }

    public final /* synthetic */ zzbc zzd() {
        return this.zze;
    }

    /* renamed from: zzh */
    public final zzbg headMap(Object obj, boolean z2) {
        zzbu zzbu = this.zze;
        obj.getClass();
        return zzl(0, zzbu.zzs(obj, z2));
    }

    /* renamed from: zzi */
    public final zzbg subMap(Object obj, boolean z2, Object obj2, boolean z3) {
        obj.getClass();
        obj2.getClass();
        if (this.zze.zza.compare(obj, obj2) <= 0) {
            return headMap(obj2, z3).tailMap(obj, z2);
        }
        throw new IllegalArgumentException(zzaq.zza("expected fromKey <= toKey but %s > %s", obj, obj2));
    }

    /* renamed from: zzj */
    public final zzbg tailMap(Object obj, boolean z2) {
        zzbu zzbu = this.zze;
        obj.getClass();
        return zzl(zzbu.zzt(obj, z2), this.zzf.size());
    }
}
