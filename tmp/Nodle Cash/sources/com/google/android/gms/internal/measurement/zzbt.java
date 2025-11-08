package com.google.android.gms.internal.measurement;

import A.a;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import com.reown.android.pulse.model.ConnectionMethod;
import java.util.List;

public final class zzbt extends zzay {
    public zzbt() {
        this.zza.add(zzbv.ASSIGN);
        this.zza.add(zzbv.CONST);
        this.zza.add(zzbv.CREATE_ARRAY);
        this.zza.add(zzbv.CREATE_OBJECT);
        this.zza.add(zzbv.EXPRESSION_LIST);
        this.zza.add(zzbv.zzaf);
        this.zza.add(zzbv.GET_INDEX);
        this.zza.add(zzbv.GET_PROPERTY);
        this.zza.add(zzbv.NULL);
        this.zza.add(zzbv.SET_PROPERTY);
        this.zza.add(zzbv.TYPEOF);
        this.zza.add(zzbv.UNDEFINED);
        this.zza.add(zzbv.VAR);
    }

    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        String str2;
        int i3 = 0;
        switch (zzbs.zza[zzg.zza(str).ordinal()]) {
            case 1:
                zzg.zza(zzbv.ASSIGN, 2, list);
                zzaq zza = zzh.zza(list.get(0));
                if (!(zza instanceof zzas)) {
                    throw new IllegalArgumentException(c.a("Expected string for assign var. got ", zza.getClass().getCanonicalName()));
                } else if (zzh.zzb(zza.zzf())) {
                    zzaq zza2 = zzh.zza(list.get(1));
                    zzh.zzc(zza.zzf(), zza2);
                    return zza2;
                } else {
                    throw new IllegalArgumentException(c.a("Attempting to assign undefined value ", zza.zzf()));
                }
            case 2:
                zzg.zzb(zzbv.CONST, 2, list);
                if (list.size() % 2 == 0) {
                    while (i3 < list.size() - 1) {
                        zzaq zza3 = zzh.zza(list.get(i3));
                        if (zza3 instanceof zzas) {
                            zzh.zzb(zza3.zzf(), zzh.zza(list.get(i3 + 1)));
                            i3 += 2;
                        } else {
                            throw new IllegalArgumentException(c.a("Expected string for const name. got ", zza3.getClass().getCanonicalName()));
                        }
                    }
                    return zzaq.zzc;
                }
                throw new IllegalArgumentException(a.k("CONST requires an even number of arguments, found ", list.size()));
            case 3:
                if (list.isEmpty()) {
                    return new zzaf();
                }
                zzaf zzaf = new zzaf();
                for (zzaq zza4 : list) {
                    zzaq zza5 = zzh.zza(zza4);
                    if (!(zza5 instanceof zzaj)) {
                        zzaf.zzb(i3, zza5);
                        i3++;
                    } else {
                        throw new IllegalStateException("Failed to evaluate array element");
                    }
                }
                return zzaf;
            case 4:
                if (list.isEmpty()) {
                    return new zzap();
                }
                if (list.size() % 2 == 0) {
                    zzap zzap = new zzap();
                    while (i3 < list.size() - 1) {
                        zzaq zza6 = zzh.zza(list.get(i3));
                        zzaq zza7 = zzh.zza(list.get(i3 + 1));
                        if ((zza6 instanceof zzaj) || (zza7 instanceof zzaj)) {
                            throw new IllegalStateException("Failed to evaluate map entry");
                        }
                        zzap.zza(zza6.zzf(), zza7);
                        i3 += 2;
                    }
                    return zzap;
                }
                throw new IllegalArgumentException(a.k("CREATE_OBJECT requires an even number of arguments, found ", list.size()));
            case 5:
                zzg.zzb(zzbv.EXPRESSION_LIST, 1, list);
                zzaq zzaq = zzaq.zzc;
                while (i3 < list.size()) {
                    zzaq = zzh.zza(list.get(i3));
                    if (!(zzaq instanceof zzaj)) {
                        i3++;
                    } else {
                        throw new IllegalStateException("ControlValue cannot be in an expression list");
                    }
                }
                return zzaq;
            case 6:
                zzg.zza(zzbv.zzaf, 1, list);
                zzaq zza8 = zzh.zza(list.get(0));
                if (zza8 instanceof zzas) {
                    return zzh.zza(zza8.zzf());
                }
                throw new IllegalArgumentException(c.a("Expected string for get var. got ", zza8.getClass().getCanonicalName()));
            case 7:
            case 8:
                zzg.zza(zzbv.GET_PROPERTY, 2, list);
                zzaq zza9 = zzh.zza(list.get(0));
                zzaq zza10 = zzh.zza(list.get(1));
                if ((zza9 instanceof zzaf) && zzg.zzb(zza10)) {
                    return ((zzaf) zza9).zza(zza10.zze().intValue());
                }
                if (zza9 instanceof zzak) {
                    return ((zzak) zza9).zza(zza10.zzf());
                }
                if (zza9 instanceof zzas) {
                    if ("length".equals(zza10.zzf())) {
                        return new zzai(Double.valueOf((double) zza9.zzf().length()));
                    }
                    if (zzg.zzb(zza10) && zza10.zze().doubleValue() < ((double) zza9.zzf().length())) {
                        return new zzas(String.valueOf(zza9.zzf().charAt(zza10.zze().intValue())));
                    }
                }
                return zzaq.zzc;
            case 9:
                zzg.zza(zzbv.NULL, 0, list);
                return zzaq.zzd;
            case 10:
                zzg.zza(zzbv.SET_PROPERTY, 3, list);
                zzaq zza11 = zzh.zza(list.get(0));
                zzaq zza12 = zzh.zza(list.get(1));
                zzaq zza13 = zzh.zza(list.get(2));
                if (zza11 == zzaq.zzc || zza11 == zzaq.zzd) {
                    throw new IllegalStateException(C0118y.f("Can't set property ", zza12.zzf(), " of ", zza11.zzf()));
                }
                if ((zza11 instanceof zzaf) && (zza12 instanceof zzai)) {
                    ((zzaf) zza11).zzb(zza12.zze().intValue(), zza13);
                } else if (zza11 instanceof zzak) {
                    ((zzak) zza11).zza(zza12.zzf(), zza13);
                }
                return zza13;
            case 11:
                zzg.zza(zzbv.TYPEOF, 1, list);
                zzaq zza14 = zzh.zza(list.get(0));
                if (zza14 instanceof zzax) {
                    str2 = ConnectionMethod.UNDEFINED;
                } else if (zza14 instanceof zzag) {
                    str2 = "boolean";
                } else if (zza14 instanceof zzai) {
                    str2 = "number";
                } else if (zza14 instanceof zzas) {
                    str2 = "string";
                } else if (zza14 instanceof zzar) {
                    str2 = "function";
                } else if ((zza14 instanceof zzat) || (zza14 instanceof zzaj)) {
                    throw new IllegalArgumentException(String.format("Unsupported value type %s in typeof", new Object[]{zza14}));
                } else {
                    str2 = "object";
                }
                return new zzas(str2);
            case 12:
                zzg.zza(zzbv.UNDEFINED, 0, list);
                return zzaq.zzc;
            case 13:
                zzg.zzb(zzbv.VAR, 1, list);
                for (zzaq zza15 : list) {
                    zzaq zza16 = zzh.zza(zza15);
                    if (zza16 instanceof zzas) {
                        zzh.zza(zza16.zzf(), zzaq.zzc);
                    } else {
                        throw new IllegalArgumentException(c.a("Expected string for var name. got ", zza16.getClass().getCanonicalName()));
                    }
                }
                return zzaq.zzc;
            default:
                return zza(str);
        }
    }
}
