package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzgn;
import com.google.android.gms.internal.measurement.zzlc;
import com.google.android.gms.internal.measurement.zzpd;
import com.google.android.gms.internal.measurement.zzqb;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class zzv extends zznr {
    private String zza;
    private Set<Integer> zzb;
    private Map<Integer, zzx> zzc;
    private Long zzd;
    private Long zze;

    public zzv(zznv zznv) {
        super(zznv);
    }

    private final zzx zza(Integer num) {
        if (this.zzc.containsKey(num)) {
            return this.zzc.get(num);
        }
        zzx zzx = new zzx(this, this.zza);
        this.zzc.put(num, zzx);
        return zzx;
    }

    @NonNull
    private final List<zzgn.zzd> zzu() {
        ArrayList arrayList = new ArrayList();
        Set<Integer> keySet = this.zzc.keySet();
        keySet.removeAll(this.zzb);
        for (Integer next : keySet) {
            int intValue = next.intValue();
            zzx zzx = this.zzc.get(next);
            Preconditions.checkNotNull(zzx);
            zzgn.zzd zza2 = zzx.zza(intValue);
            arrayList.add(zza2);
            zzam zzh = zzh();
            String str = this.zza;
            zzgn.zzm zzd2 = zza2.zzd();
            zzh.zzal();
            zzh.zzt();
            Preconditions.checkNotEmpty(str);
            Preconditions.checkNotNull(zzd2);
            byte[] zzca = zzd2.zzca();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", next);
            contentValues.put("current_results", zzca);
            try {
                if (zzh.e_().insertWithOnConflict("audience_filter_values", (String) null, contentValues, 5) == -1) {
                    zzh.zzj().zzg().zza("Failed to insert filter results (got -1). appId", zzgi.zza(str));
                }
            } catch (SQLiteException e3) {
                zzh.zzj().zzg().zza("Error storing filter results. appId", zzgi.zza(str), e3);
            }
        }
        return arrayList;
    }

    public final boolean zzc() {
        return false;
    }

    @WorkerThread
    public final List<zzgn.zzd> zza(String str, List<zzgn.zzf> list, List<zzgn.zzo> list2, Long l2, Long l3) {
        return zza(str, list, list2, l2, l3, false);
    }

    @WorkerThread
    public final List<zzgn.zzd> zza(String str, List<zzgn.zzf> list, List<zzgn.zzo> list2, Long l2, Long l3, boolean z2) {
        boolean z3;
        ArrayMap arrayMap;
        List<zzfn.zzb> list3;
        Iterator it;
        Map<Integer, zzgn.zzm> map;
        Iterator<zzgn.zzn> it2;
        Iterator<Integer> it3;
        Map<Integer, List<Integer>> map2;
        List<zzgn.zzf> list4 = list;
        List<zzgn.zzo> list5 = list2;
        boolean z4 = z2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        this.zza = str;
        this.zzb = new HashSet();
        this.zzc = new ArrayMap();
        this.zzd = l2;
        this.zze = l3;
        Iterator<zzgn.zzf> it4 = list.iterator();
        while (true) {
            if (it4.hasNext()) {
                if ("_s".equals(it4.next().zzg())) {
                    z3 = true;
                    break;
                }
            } else {
                z3 = false;
                break;
            }
        }
        boolean z5 = zzqb.zza() && zze().zzf(this.zza, zzbj.zzbo);
        boolean z6 = zzqb.zza() && zze().zzf(this.zza, zzbj.zzbn);
        if (z3) {
            zzam zzh = zzh();
            String str2 = this.zza;
            zzh.zzal();
            zzh.zzt();
            Preconditions.checkNotEmpty(str2);
            ContentValues contentValues = new ContentValues();
            contentValues.put("current_session_count", 0);
            try {
                zzh.e_().update("events", contentValues, "app_id = ?", new String[]{str2});
            } catch (SQLiteException e3) {
                zzh.zzj().zzg().zza("Error resetting session-scoped event counts. appId", zzgi.zza(str2), e3);
            }
        }
        Map<Integer, List<zzfn.zzb>> emptyMap = Collections.emptyMap();
        if (z6 && z5) {
            emptyMap = zzh().zzn(this.zza);
        }
        Map<Integer, zzgn.zzm> zzm = zzh().zzm(this.zza);
        if (!zzm.isEmpty()) {
            HashSet hashSet = new HashSet(zzm.keySet());
            if (z3) {
                String str3 = this.zza;
                Map<Integer, List<Integer>> zzo = zzh().zzo(this.zza);
                Preconditions.checkNotEmpty(str3);
                Preconditions.checkNotNull(zzm);
                ArrayMap arrayMap2 = new ArrayMap();
                if (!zzm.isEmpty()) {
                    Iterator<Integer> it5 = zzm.keySet().iterator();
                    while (it5.hasNext()) {
                        Integer next = it5.next();
                        next.intValue();
                        zzgn.zzm zzm2 = zzm.get(next);
                        List list6 = zzo.get(next);
                        if (list6 == null || list6.isEmpty()) {
                            map2 = zzo;
                            it3 = it5;
                            arrayMap2.put(next, zzm2);
                        } else {
                            List<Long> zza2 = g_().zza(zzm2.zzi(), (List<Integer>) list6);
                            if (!zza2.isEmpty()) {
                                zzgn.zzm.zza zzb2 = ((zzgn.zzm.zza) zzm2.zzcd()).zzb().zzb(zza2);
                                zzb2.zzd().zzd(g_().zza(zzm2.zzk(), (List<Integer>) list6));
                                ArrayList arrayList = new ArrayList();
                                for (zzgn.zze next2 : zzm2.zzh()) {
                                    Map<Integer, List<Integer>> map3 = zzo;
                                    Iterator<Integer> it6 = it5;
                                    if (!list6.contains(Integer.valueOf(next2.zza()))) {
                                        arrayList.add(next2);
                                    }
                                    zzo = map3;
                                    it5 = it6;
                                }
                                map2 = zzo;
                                it3 = it5;
                                zzb2.zza().zza(arrayList);
                                ArrayList arrayList2 = new ArrayList();
                                for (zzgn.zzn next3 : zzm2.zzj()) {
                                    if (!list6.contains(Integer.valueOf(next3.zzb()))) {
                                        arrayList2.add(next3);
                                    }
                                }
                                zzb2.zzc().zzc(arrayList2);
                                arrayMap2.put(next, (zzgn.zzm) ((zzlc) zzb2.zzai()));
                            }
                        }
                        zzo = map2;
                        it5 = it3;
                    }
                }
                arrayMap = arrayMap2;
            } else {
                arrayMap = zzm;
            }
            Iterator it7 = hashSet.iterator();
            while (it7.hasNext()) {
                Integer num = (Integer) it7.next();
                num.intValue();
                zzgn.zzm zzm3 = arrayMap.get(num);
                BitSet bitSet = new BitSet();
                BitSet bitSet2 = new BitSet();
                ArrayMap arrayMap3 = new ArrayMap();
                if (!(zzm3 == null || zzm3.zza() == 0)) {
                    for (zzgn.zze next4 : zzm3.zzh()) {
                        if (next4.zzf()) {
                            arrayMap3.put(Integer.valueOf(next4.zza()), next4.zze() ? Long.valueOf(next4.zzb()) : null);
                        }
                    }
                }
                ArrayMap arrayMap4 = new ArrayMap();
                if (!(zzm3 == null || zzm3.zzc() == 0)) {
                    Iterator<zzgn.zzn> it8 = zzm3.zzj().iterator();
                    while (it8.hasNext()) {
                        zzgn.zzn next5 = it8.next();
                        if (!next5.zzf() || next5.zza() <= 0) {
                            it2 = it8;
                            map = arrayMap;
                        } else {
                            it2 = it8;
                            map = arrayMap;
                            arrayMap4.put(Integer.valueOf(next5.zzb()), Long.valueOf(next5.zza(next5.zza() - 1)));
                        }
                        it8 = it2;
                        arrayMap = map;
                    }
                }
                Map<Integer, zzgn.zzm> map4 = arrayMap;
                if (zzm3 != null) {
                    int i3 = 0;
                    while (i3 < (zzm3.zzd() << 6)) {
                        if (zzol.zza(zzm3.zzk(), i3)) {
                            it = it7;
                            zzj().zzp().zza("Filter already evaluated. audience ID, filter ID", num, Integer.valueOf(i3));
                            bitSet2.set(i3);
                            if (zzol.zza(zzm3.zzi(), i3)) {
                                bitSet.set(i3);
                                i3++;
                                it7 = it;
                            }
                        } else {
                            it = it7;
                        }
                        arrayMap3.remove(Integer.valueOf(i3));
                        i3++;
                        it7 = it;
                    }
                }
                Iterator it9 = it7;
                zzgn.zzm zzm4 = zzm.get(num);
                if (!(!z6 || !z5 || (list3 = emptyMap.get(num)) == null || this.zze == null || this.zzd == null)) {
                    for (zzfn.zzb zzb3 : list3) {
                        int zzb4 = zzb3.zzb();
                        long longValue = this.zze.longValue() / 1000;
                        if (zzb3.zzi()) {
                            longValue = this.zzd.longValue() / 1000;
                        }
                        if (arrayMap3.containsKey(Integer.valueOf(zzb4))) {
                            arrayMap3.put(Integer.valueOf(zzb4), Long.valueOf(longValue));
                        }
                        if (arrayMap4.containsKey(Integer.valueOf(zzb4))) {
                            arrayMap4.put(Integer.valueOf(zzb4), Long.valueOf(longValue));
                        }
                    }
                }
                this.zzc.put(num, new zzx(this, this.zza, zzm4, bitSet, bitSet2, arrayMap3, arrayMap4));
                it7 = it9;
                zzm = zzm;
                arrayMap = map4;
            }
        }
        if (!zzpd.zza() || !zze().zzf((String) null, zzbj.zzdc)) {
            zza(list4, true);
            zza(list5);
            return zzu();
        }
        zza(list4, z4);
        if (z4) {
            return new ArrayList();
        }
        zza(list5);
        return zzu();
    }

    private final void zza(List<zzgn.zzf> list, boolean z2) {
        zzbd zzbd;
        zzz zzz;
        Integer num;
        Map<Integer, List<zzfn.zzb>> map;
        long j2;
        if (!list.isEmpty()) {
            String str = null;
            zzz zzz2 = new zzz(this);
            ArrayMap arrayMap = new ArrayMap();
            for (zzgn.zzf next : list) {
                zzgn.zzf zza2 = zzz2.zza(this.zza, next);
                if (zza2 != null) {
                    zzam zzh = zzh();
                    String str2 = this.zza;
                    String zzg = zza2.zzg();
                    zzbd zzd2 = zzh.zzd(str2, next.zzg());
                    if (zzd2 == null) {
                        zzh.zzj().zzu().zza("Event aggregate wasn't created during raw event logging. appId, event", zzgi.zza(str2), zzh.zzi().zza(zzg));
                        zzbd = new zzbd(str2, next.zzg(), 1, 1, 1, next.zzd(), 0, (Long) null, (Long) null, (Long) null, (Boolean) null);
                    } else {
                        zzbd = new zzbd(zzd2.zza, zzd2.zzb, zzd2.zzc + 1, zzd2.zzd + 1, zzd2.zze + 1, zzd2.zzf, zzd2.zzg, zzd2.zzh, zzd2.zzi, zzd2.zzj, zzd2.zzk);
                    }
                    zzbd zzbd2 = zzbd;
                    zzh().zza(zzbd2);
                    if (!zzpd.zza() || !zze().zzf(str, zzbj.zzdc) || !z2) {
                        long j3 = zzbd2.zzc;
                        String zzg2 = zza2.zzg();
                        Map<Integer, List<zzfn.zzb>> map2 = (Map) arrayMap.get(zzg2);
                        if (map2 == null) {
                            map2 = zzh().zzf(this.zza, zzg2);
                            arrayMap.put(zzg2, map2);
                        }
                        Map<Integer, List<zzfn.zzb>> map3 = map2;
                        Iterator<Integer> it = map3.keySet().iterator();
                        while (it.hasNext()) {
                            Integer next2 = it.next();
                            int intValue = next2.intValue();
                            if (this.zzb.contains(next2)) {
                                zzj().zzp().zza("Skipping failed audience ID", next2);
                            } else {
                                Iterator it2 = map3.get(next2).iterator();
                                boolean z3 = true;
                                while (true) {
                                    if (!it2.hasNext()) {
                                        zzz = zzz2;
                                        num = next2;
                                        map = map3;
                                        j2 = j3;
                                        break;
                                    }
                                    zzfn.zzb zzb2 = (zzfn.zzb) it2.next();
                                    zzab zzab = new zzab(this, this.zza, intValue, zzb2);
                                    zzab zzab2 = zzab;
                                    zzz = zzz2;
                                    num = next2;
                                    int i3 = intValue;
                                    map = map3;
                                    j2 = j3;
                                    z3 = zzab.zza(this.zzd, this.zze, zza2, j3, zzbd2, zza(intValue, zzb2.zzb()));
                                    if (!z3) {
                                        this.zzb.add(num);
                                        break;
                                    }
                                    zza(num).zza((zzaa) zzab2);
                                    next2 = num;
                                    zzz2 = zzz;
                                    intValue = i3;
                                    map3 = map;
                                    j3 = j2;
                                }
                                if (!z3) {
                                    this.zzb.add(num);
                                }
                                zzz2 = zzz;
                                map3 = map;
                                j3 = j2;
                                str = null;
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x010d, code lost:
        if (r7.zzi() == false) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x010f, code lost:
        r9 = java.lang.Integer.valueOf(r7.zza());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0117, code lost:
        r5.zza("Invalid property filter ID. appId, id", r6, java.lang.String.valueOf(r9));
        r7 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(java.util.List<com.google.android.gms.internal.measurement.zzgn.zzo> r14) {
        /*
            r13 = this;
            boolean r0 = r14.isEmpty()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            java.util.Iterator r14 = r14.iterator()
        L_0x0010:
            boolean r1 = r14.hasNext()
            if (r1 == 0) goto L_0x012a
            java.lang.Object r1 = r14.next()
            com.google.android.gms.internal.measurement.zzgn$zzo r1 = (com.google.android.gms.internal.measurement.zzgn.zzo) r1
            java.lang.String r2 = r1.zzg()
            java.lang.Object r3 = r0.get(r2)
            java.util.Map r3 = (java.util.Map) r3
            if (r3 != 0) goto L_0x0035
            com.google.android.gms.measurement.internal.zzam r3 = r13.zzh()
            java.lang.String r4 = r13.zza
            java.util.Map r3 = r3.zzg(r4, r2)
            r0.put(r2, r3)
        L_0x0035:
            java.util.Set r2 = r3.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x003d:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0010
            java.lang.Object r4 = r2.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r5 = r4.intValue()
            java.util.Set<java.lang.Integer> r6 = r13.zzb
            boolean r6 = r6.contains(r4)
            if (r6 == 0) goto L_0x0063
            com.google.android.gms.measurement.internal.zzgi r1 = r13.zzj()
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzp()
            java.lang.String r2 = "Skipping failed audience ID"
            r1.zza(r2, r4)
            goto L_0x0010
        L_0x0063:
            java.lang.Object r6 = r3.get(r4)
            java.util.List r6 = (java.util.List) r6
            java.util.Iterator r6 = r6.iterator()
            r7 = 1
        L_0x006e:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x0121
            java.lang.Object r7 = r6.next()
            com.google.android.gms.internal.measurement.zzfn$zze r7 = (com.google.android.gms.internal.measurement.zzfn.zze) r7
            com.google.android.gms.measurement.internal.zzgi r8 = r13.zzj()
            r9 = 2
            boolean r8 = r8.zza((int) r9)
            r9 = 0
            if (r8 == 0) goto L_0x00c4
            com.google.android.gms.measurement.internal.zzgi r8 = r13.zzj()
            com.google.android.gms.measurement.internal.zzgk r8 = r8.zzp()
            boolean r10 = r7.zzi()
            if (r10 == 0) goto L_0x009d
            int r10 = r7.zza()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            goto L_0x009e
        L_0x009d:
            r10 = r9
        L_0x009e:
            com.google.android.gms.measurement.internal.zzgh r11 = r13.zzi()
            java.lang.String r12 = r7.zze()
            java.lang.String r11 = r11.zzc(r12)
            java.lang.String r12 = "Evaluating filter. audience, filter, property"
            r8.zza(r12, r4, r10, r11)
            com.google.android.gms.measurement.internal.zzgi r8 = r13.zzj()
            com.google.android.gms.measurement.internal.zzgk r8 = r8.zzp()
            com.google.android.gms.measurement.internal.zzol r10 = r13.g_()
            java.lang.String r10 = r10.zza((com.google.android.gms.internal.measurement.zzfn.zze) r7)
            java.lang.String r11 = "Filter definition"
            r8.zza(r11, r10)
        L_0x00c4:
            boolean r8 = r7.zzi()
            if (r8 == 0) goto L_0x00fb
            int r8 = r7.zza()
            r10 = 256(0x100, float:3.59E-43)
            if (r8 <= r10) goto L_0x00d3
            goto L_0x00fb
        L_0x00d3:
            com.google.android.gms.measurement.internal.zzad r8 = new com.google.android.gms.measurement.internal.zzad
            java.lang.String r9 = r13.zza
            r8.<init>(r13, r9, r5, r7)
            java.lang.Long r9 = r13.zzd
            java.lang.Long r10 = r13.zze
            int r7 = r7.zza()
            boolean r7 = r13.zza((int) r5, (int) r7)
            boolean r7 = r8.zza(r9, r10, r1, r7)
            if (r7 == 0) goto L_0x00f5
            com.google.android.gms.measurement.internal.zzx r9 = r13.zza((java.lang.Integer) r4)
            r9.zza((com.google.android.gms.measurement.internal.zzaa) r8)
            goto L_0x006e
        L_0x00f5:
            java.util.Set<java.lang.Integer> r5 = r13.zzb
            r5.add(r4)
            goto L_0x0121
        L_0x00fb:
            com.google.android.gms.measurement.internal.zzgi r5 = r13.zzj()
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzu()
            java.lang.String r6 = r13.zza
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r6)
            boolean r8 = r7.zzi()
            if (r8 == 0) goto L_0x0117
            int r7 = r7.zza()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r7)
        L_0x0117:
            java.lang.String r7 = java.lang.String.valueOf(r9)
            java.lang.String r8 = "Invalid property filter ID. appId, id"
            r5.zza(r8, r6, r7)
            r7 = 0
        L_0x0121:
            if (r7 != 0) goto L_0x003d
            java.util.Set<java.lang.Integer> r5 = r13.zzb
            r5.add(r4)
            goto L_0x003d
        L_0x012a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzv.zza(java.util.List):void");
    }

    private final boolean zza(int i3, int i4) {
        zzx zzx = this.zzc.get(Integer.valueOf(i3));
        if (zzx == null) {
            return false;
        }
        return zzx.zzd.get(i4);
    }
}
