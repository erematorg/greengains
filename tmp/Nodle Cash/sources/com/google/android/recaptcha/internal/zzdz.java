package com.google.android.recaptcha.internal;

import android.support.v4.media.session.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

public final class zzdz implements zzdd {
    @NotNull
    public static final zzdz zza = new zzdz();

    private zzdz() {
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        if (zzpqArr.length == 2) {
            Object zza2 = zzcj.zzc().zza(zzpqArr[0]);
            if (true != Objects.nonNull(zza2)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Object zza3 = zzcj.zzc().zza(zzpqArr[1]);
                if (true != Objects.nonNull(zza3)) {
                    zza3 = null;
                }
                if (zza3 != null) {
                    zzcj.zzc().zzf(i3, zzb(zza2, zza3));
                    return;
                }
                throw new zzae(4, 5, (Throwable) null);
            }
            throw new zzae(4, 5, (Throwable) null);
        }
        throw new zzae(4, 3, (Throwable) null);
    }

    @NotNull
    public final Object zzb(@NotNull Object obj, @NotNull Object obj2) throws zzae {
        boolean z2 = obj instanceof Byte;
        if (z2 && (obj2 instanceof Byte)) {
            return Byte.valueOf((byte) (((Number) obj).byteValue() ^ ((Number) obj2).byteValue()));
        }
        boolean z3 = obj instanceof Short;
        if (z3 && (obj2 instanceof Short)) {
            return Short.valueOf((short) (((Number) obj).shortValue() ^ ((Number) obj2).shortValue()));
        }
        boolean z4 = obj instanceof Integer;
        if (z4 && (obj2 instanceof Integer)) {
            return Integer.valueOf(((Number) obj).intValue() ^ ((Number) obj2).intValue());
        }
        boolean z5 = obj instanceof Long;
        if (z5 && (obj2 instanceof Long)) {
            return Long.valueOf(((Number) obj).longValue() ^ ((Number) obj2).longValue());
        }
        int i3 = 0;
        if (obj instanceof String) {
            if (obj2 instanceof Byte) {
                byte[] bytes = ((String) obj).getBytes(Charsets.UTF_8);
                int length = bytes.length;
                ArrayList arrayList = new ArrayList(length);
                while (i3 < length) {
                    arrayList.add(Byte.valueOf((byte) (bytes[i3] ^ ((Number) obj2).byteValue())));
                    i3++;
                }
                return CollectionsKt.toByteArray(arrayList);
            } else if (obj2 instanceof Integer) {
                char[] charArray = ((String) obj).toCharArray();
                int length2 = charArray.length;
                ArrayList arrayList2 = new ArrayList(length2);
                while (i3 < length2) {
                    i3 = a.e(charArray[i3] ^ ((Number) obj2).intValue(), arrayList2, i3, 1);
                }
                return CollectionsKt.toIntArray(arrayList2);
            }
        }
        if (z2 && (obj2 instanceof byte[])) {
            ArrayList arrayList3 = new ArrayList(r8);
            for (byte byteValue : (byte[]) obj2) {
                arrayList3.add(Byte.valueOf((byte) (byteValue ^ ((Number) obj).byteValue())));
            }
            return arrayList3.toArray(new Byte[0]);
        } else if (z3 && (obj2 instanceof short[])) {
            ArrayList arrayList4 = new ArrayList(r8);
            for (short shortValue : (short[]) obj2) {
                arrayList4.add(Short.valueOf((short) (shortValue ^ ((Number) obj).shortValue())));
            }
            return arrayList4.toArray(new Short[0]);
        } else if (z4 && (obj2 instanceof int[])) {
            int[] iArr = (int[]) obj2;
            int length3 = iArr.length;
            ArrayList arrayList5 = new ArrayList(length3);
            int i4 = 0;
            while (i4 < length3) {
                i4 = a.e(iArr[i4] ^ ((Number) obj).intValue(), arrayList5, i4, 1);
            }
            return arrayList5.toArray(new Integer[0]);
        } else if (!z5 || !(obj2 instanceof long[])) {
            boolean z6 = obj instanceof byte[];
            if (!z6 || !(obj2 instanceof Byte)) {
                boolean z7 = obj instanceof short[];
                if (!z7 || !(obj2 instanceof Short)) {
                    boolean z8 = obj instanceof int[];
                    if (!z8 || !(obj2 instanceof Integer)) {
                        boolean z9 = obj instanceof long[];
                        if (z9 && (obj2 instanceof Long)) {
                            ArrayList arrayList6 = new ArrayList(r8);
                            for (long longValue : (long[]) obj) {
                                arrayList6.add(Long.valueOf(longValue ^ ((Number) obj2).longValue()));
                            }
                            return arrayList6.toArray(new Long[0]);
                        } else if (z6 && (obj2 instanceof byte[])) {
                            byte[] bArr = (byte[]) obj;
                            int length4 = bArr.length;
                            byte[] bArr2 = (byte[]) obj2;
                            zzdc.zza(this, length4, bArr2.length);
                            IntRange until = RangesKt.until(0, length4);
                            ArrayList arrayList7 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(until, 10));
                            Iterator it = until.iterator();
                            while (it.hasNext()) {
                                int nextInt = ((IntIterator) it).nextInt();
                                arrayList7.add(Byte.valueOf((byte) (bArr2[nextInt] ^ bArr[nextInt])));
                            }
                            return arrayList7.toArray(new Byte[0]);
                        } else if (z7 && (obj2 instanceof short[])) {
                            short[] sArr = (short[]) obj;
                            int length5 = sArr.length;
                            short[] sArr2 = (short[]) obj2;
                            zzdc.zza(this, length5, sArr2.length);
                            IntRange until2 = RangesKt.until(0, length5);
                            ArrayList arrayList8 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(until2, 10));
                            Iterator it2 = until2.iterator();
                            while (it2.hasNext()) {
                                int nextInt2 = ((IntIterator) it2).nextInt();
                                arrayList8.add(Short.valueOf((short) (sArr2[nextInt2] ^ sArr[nextInt2])));
                            }
                            return arrayList8.toArray(new Short[0]);
                        } else if (z8 && (obj2 instanceof int[])) {
                            int[] iArr2 = (int[]) obj;
                            int length6 = iArr2.length;
                            int[] iArr3 = (int[]) obj2;
                            zzdc.zza(this, length6, iArr3.length);
                            IntRange until3 = RangesKt.until(0, length6);
                            ArrayList arrayList9 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(until3, 10));
                            Iterator it3 = until3.iterator();
                            while (it3.hasNext()) {
                                int nextInt3 = ((IntIterator) it3).nextInt();
                                arrayList9.add(Integer.valueOf(iArr3[nextInt3] ^ iArr2[nextInt3]));
                            }
                            return arrayList9.toArray(new Integer[0]);
                        } else if (!z9 || !(obj2 instanceof long[])) {
                            throw new zzae(4, 5, (Throwable) null);
                        } else {
                            long[] jArr = (long[]) obj;
                            int length7 = jArr.length;
                            long[] jArr2 = (long[]) obj2;
                            zzdc.zza(this, length7, jArr2.length);
                            IntRange until4 = RangesKt.until(0, length7);
                            ArrayList arrayList10 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(until4, 10));
                            Iterator it4 = until4.iterator();
                            while (it4.hasNext()) {
                                int nextInt4 = ((IntIterator) it4).nextInt();
                                arrayList10.add(Long.valueOf(jArr[nextInt4] ^ jArr2[nextInt4]));
                            }
                            return arrayList10.toArray(new Long[0]);
                        }
                    } else {
                        int[] iArr4 = (int[]) obj;
                        int length8 = iArr4.length;
                        ArrayList arrayList11 = new ArrayList(length8);
                        int i5 = 0;
                        while (i5 < length8) {
                            i5 = a.e(iArr4[i5] ^ ((Number) obj2).intValue(), arrayList11, i5, 1);
                        }
                        return arrayList11.toArray(new Integer[0]);
                    }
                } else {
                    ArrayList arrayList12 = new ArrayList(r8);
                    for (short shortValue2 : (short[]) obj) {
                        arrayList12.add(Short.valueOf((short) (shortValue2 ^ ((Number) obj2).shortValue())));
                    }
                    return arrayList12.toArray(new Short[0]);
                }
            } else {
                ArrayList arrayList13 = new ArrayList(r8);
                for (byte byteValue2 : (byte[]) obj) {
                    arrayList13.add(Byte.valueOf((byte) (byteValue2 ^ ((Number) obj2).byteValue())));
                }
                return arrayList13.toArray(new Byte[0]);
            }
        } else {
            ArrayList arrayList14 = new ArrayList(r8);
            for (long longValue2 : (long[]) obj2) {
                arrayList14.add(Long.valueOf(longValue2 ^ ((Number) obj).longValue()));
            }
            return arrayList14.toArray(new Long[0]);
        }
    }
}
