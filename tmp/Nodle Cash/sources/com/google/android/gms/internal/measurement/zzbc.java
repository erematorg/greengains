package com.google.android.gms.internal.measurement;

import androidx.browser.trusted.c;
import java.util.ArrayList;
import java.util.List;

public final class zzbc extends zzay {
    public zzbc() {
        this.zza.add(zzbv.APPLY);
        this.zza.add(zzbv.BLOCK);
        this.zza.add(zzbv.BREAK);
        this.zza.add(zzbv.CASE);
        this.zza.add(zzbv.DEFAULT);
        this.zza.add(zzbv.CONTINUE);
        this.zza.add(zzbv.DEFINE_FUNCTION);
        this.zza.add(zzbv.FN);
        this.zza.add(zzbv.IF);
        this.zza.add(zzbv.QUOTE);
        this.zza.add(zzbv.RETURN);
        this.zza.add(zzbv.SWITCH);
        this.zza.add(zzbv.TERNARY);
    }

    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        zzaq zzaq;
        int i3 = 0;
        switch (zzbf.zza[zzg.zza(str).ordinal()]) {
            case 1:
                zzg.zza(zzbv.APPLY, 3, list);
                zzaq zza = zzh.zza(list.get(0));
                String zzf = zzh.zza(list.get(1)).zzf();
                zzaq zza2 = zzh.zza(list.get(2));
                if (!(zza2 instanceof zzaf)) {
                    throw new IllegalArgumentException(c.a("Function arguments for Apply are not a list found ", zza2.getClass().getCanonicalName()));
                } else if (!zzf.isEmpty()) {
                    return zza.zza(zzf, zzh, ((zzaf) zza2).zzi());
                } else {
                    throw new IllegalArgumentException("Function name for apply is undefined");
                }
            case 2:
                return zzh.zza().zza(new zzaf(list));
            case 3:
                zzg.zza(zzbv.BREAK, 0, list);
                return zzaq.zzf;
            case 4:
            case 5:
                if (!list.isEmpty()) {
                    zzaq zza3 = zzh.zza(list.get(0));
                    if (zza3 instanceof zzaf) {
                        return zzh.zza((zzaf) zza3);
                    }
                }
                return zzaq.zzc;
            case 6:
                zzg.zza(zzbv.BREAK, 0, list);
                return zzaq.zze;
            case 7:
                zzg.zzb(zzbv.DEFINE_FUNCTION, 2, list);
                zzar zzar = (zzar) zza(zzh, list);
                if (zzar.zza() == null) {
                    zzh.zzc("", zzar);
                } else {
                    zzh.zzc(zzar.zza(), zzar);
                }
                return zzar;
            case 8:
                return zza(zzh, list);
            case 9:
                zzg.zzb(zzbv.IF, 2, list);
                zzaq zza4 = zzh.zza(list.get(0));
                zzaq zza5 = zzh.zza(list.get(1));
                zzaq zza6 = list.size() > 2 ? zzh.zza(list.get(2)) : null;
                zzaq zzaq2 = zzaq.zzc;
                if (zza4.zzd().booleanValue()) {
                    zzaq = zzh.zza((zzaf) zza5);
                } else {
                    zzaq = zza6 != null ? zzh.zza((zzaf) zza6) : zzaq2;
                }
                return zzaq instanceof zzaj ? zzaq : zzaq2;
            case 10:
                return new zzaf(list);
            case 11:
                if (list.isEmpty()) {
                    return zzaq.zzg;
                }
                zzg.zza(zzbv.RETURN, 1, list);
                return new zzaj("return", zzh.zza(list.get(0)));
            case 12:
                zzg.zza(zzbv.SWITCH, 3, list);
                zzaq zza7 = zzh.zza(list.get(0));
                zzaq zza8 = zzh.zza(list.get(1));
                zzaq zza9 = zzh.zza(list.get(2));
                if (!(zza8 instanceof zzaf)) {
                    throw new IllegalArgumentException("Malformed SWITCH statement, cases are not a list");
                } else if (zza9 instanceof zzaf) {
                    zzaf zzaf = (zzaf) zza8;
                    zzaf zzaf2 = (zzaf) zza9;
                    boolean z2 = false;
                    while (true) {
                        if (i3 < zzaf.zzb()) {
                            if (z2 || zza7.equals(zzh.zza(zzaf.zza(i3)))) {
                                zzaq zza10 = zzh.zza(zzaf2.zza(i3));
                                if (!(zza10 instanceof zzaj)) {
                                    z2 = true;
                                } else if (!((zzaj) zza10).zzb().equals("break")) {
                                    return zza10;
                                }
                            }
                            i3++;
                        } else if (zzaf.zzb() + 1 == zzaf2.zzb()) {
                            zzaq zza11 = zzh.zza(zzaf2.zza(zzaf.zzb()));
                            if (zza11 instanceof zzaj) {
                                String zzb = ((zzaj) zza11).zzb();
                                if (zzb.equals("return") || zzb.equals("continue")) {
                                    return zza11;
                                }
                            }
                        }
                    }
                    return zzaq.zzc;
                } else {
                    throw new IllegalArgumentException("Malformed SWITCH statement, case statements are not a list");
                }
            case 13:
                zzg.zza(zzbv.TERNARY, 3, list);
                if (zzh.zza(list.get(0)).zzd().booleanValue()) {
                    return zzh.zza(list.get(1));
                }
                return zzh.zza(list.get(2));
            default:
                return zza(str);
        }
    }

    private static zzaq zza(zzh zzh, List<zzaq> list) {
        zzg.zzb(zzbv.FN, 2, list);
        zzaq zza = zzh.zza(list.get(0));
        zzaq zza2 = zzh.zza(list.get(1));
        if (zza2 instanceof zzaf) {
            List<zzaq> zzi = ((zzaf) zza2).zzi();
            List<zzaq> arrayList = new ArrayList<>();
            if (list.size() > 2) {
                arrayList = list.subList(2, list.size());
            }
            return new zzar(zza.zzf(), zzi, arrayList, zzh);
        }
        throw new IllegalArgumentException(c.a("FN requires an ArrayValue of parameter names found ", zza2.getClass().getCanonicalName()));
    }
}
