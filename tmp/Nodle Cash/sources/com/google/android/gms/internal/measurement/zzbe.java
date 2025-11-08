package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class zzbe {
    private static zzaf zza(zzaf zzaf, zzh zzh, zzal zzal, Boolean bool, Boolean bool2) {
        zzaf zzaf2 = new zzaf();
        Iterator<Integer> zzg = zzaf.zzg();
        while (zzg.hasNext()) {
            int intValue = zzg.next().intValue();
            if (zzaf.zzc(intValue)) {
                zzaq zza = zzal.zza(zzh, (List<zzaq>) Arrays.asList(new zzaq[]{zzaf.zza(intValue), new zzai(Double.valueOf((double) intValue)), zzaf}));
                if (zza.zzd().equals(bool)) {
                    return zzaf2;
                }
                if (bool2 == null || zza.zzd().equals(bool2)) {
                    zzaf2.zzb(intValue, zza);
                }
            }
        }
        return zzaf2;
    }

    private static zzaf zza(zzaf zzaf, zzh zzh, zzal zzal) {
        return zza(zzaf, zzh, zzal, (Boolean) null, (Boolean) null);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01e2, code lost:
        r1 = r15.zza(r3);
        r15.zzb(r3, (com.google.android.gms.internal.measurement.zzaq) null);
        r5 = 1;
        r6 = (r0 - 1) - r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01f1, code lost:
        if (r15.zzc(r6) == false) goto L_0x01fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01f3, code lost:
        r15.zzb(r3, r15.zza(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01fa, code lost:
        r15.zzb(r6, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01fe, code lost:
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01ff, code lost:
        r3 = r3 + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0201, code lost:
        return r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x020d, code lost:
        return zza(r22, r23, r24, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x020e, code lost:
        r15 = r22;
        r3 = r23;
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("slice", 2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x021c, code lost:
        if (r24.isEmpty() == false) goto L_0x0223;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0222, code lost:
        return r22.zzc();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0223, code lost:
        r4 = (double) r22.zzb();
        r6 = com.google.android.gms.internal.measurement.zzg.zza(r3.zza(r0.get(0)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0242, code lost:
        if (r6 >= 0.0d) goto L_0x024a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0244, code lost:
        r6 = java.lang.Math.max(r6 + r4, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x024a, code lost:
        r6 = java.lang.Math.min(r6, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0252, code lost:
        if (r24.size() != 2) goto L_0x027b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0254, code lost:
        r0 = com.google.android.gms.internal.measurement.zzg.zza(r3.zza(r0.get(1)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x026f, code lost:
        if (r0 >= 0.0d) goto L_0x0277;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0271, code lost:
        r4 = java.lang.Math.max(r4 + r0, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0277, code lost:
        r4 = java.lang.Math.min(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x027b, code lost:
        r0 = new com.google.android.gms.internal.measurement.zzaf();
        r1 = (int) r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0284, code lost:
        if (((double) r1) >= r4) goto L_0x0290;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0286, code lost:
        r0.zza(r15.zza(r1));
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0290, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0291, code lost:
        r15 = r22;
        com.google.android.gms.internal.measurement.zzg.zza("shift", 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x029d, code lost:
        if (r22.zzb() != 0) goto L_0x02a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02a1, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02a2, code lost:
        r0 = r15.zza(0);
        r15.zzb(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02a9, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x02aa, code lost:
        r15 = r22;
        r3 = r23;
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zza("every", 1, r0);
        r0 = r3.zza(r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x02c1, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzar) == false) goto L_0x02e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02c7, code lost:
        if (r22.zzb() == 0) goto L_0x02e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x02db, code lost:
        if (zza(r15, r3, (com.google.android.gms.internal.measurement.zzar) r0, java.lang.Boolean.FALSE, java.lang.Boolean.TRUE).zzb() == r22.zzb()) goto L_0x02e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x02df, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzi;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x02e2, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzh;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x02ea, code lost:
        throw new java.lang.IllegalArgumentException(r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x02eb, code lost:
        r15 = r22;
        r3 = r23;
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("sort", 1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x02fa, code lost:
        if (r22.zzb() < 2) goto L_0x0345;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x02fc, code lost:
        r1 = r22.zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x0304, code lost:
        if (r24.isEmpty() != false) goto L_0x0320;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0306, code lost:
        r0 = r3.zza(r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0313, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzal) == false) goto L_0x0318;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0315, code lost:
        r0 = (com.google.android.gms.internal.measurement.zzal) r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x031f, code lost:
        throw new java.lang.IllegalArgumentException("Comparator should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0320, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0321, code lost:
        java.util.Collections.sort(r1, new com.google.android.gms.internal.measurement.zzbh(r0, r3));
        r22.zzj();
        r0 = r1.iterator();
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0335, code lost:
        if (r0.hasNext() == false) goto L_0x0345;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0337, code lost:
        r15.zzb(r3, r0.next());
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0345, code lost:
        return r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0346, code lost:
        r15 = r22;
        r3 = r23;
        r0 = r24;
        r5 = r18;
        com.google.android.gms.internal.measurement.zzg.zza("some", 1, r0);
        r0 = r3.zza(r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0360, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzal) == false) goto L_0x03b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0366, code lost:
        if (r22.zzb() == 0) goto L_0x03b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0368, code lost:
        r0 = (com.google.android.gms.internal.measurement.zzal) r0;
        r4 = r22.zzg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0372, code lost:
        if (r4.hasNext() == false) goto L_0x03b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0374, code lost:
        r5 = r4.next().intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0382, code lost:
        if (r15.zzc(r5) == false) goto L_0x036e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x03ac, code lost:
        if (r0.zza(r3, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzaq[]{r15.zza(r5), new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r5)), r15})).zzd().booleanValue() == false) goto L_0x036e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x03b0, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzh;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x03b3, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzi;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x03b9, code lost:
        throw new java.lang.IllegalArgumentException(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x03ba, code lost:
        r15 = r22;
        r3 = r23;
        r0 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x03c4, code lost:
        if (r24.isEmpty() != false) goto L_0x03de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x03c6, code lost:
        r0 = r24.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x03ce, code lost:
        if (r0.hasNext() == false) goto L_0x03de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x03d0, code lost:
        r15.zza(r3.zza(r0.next()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x03ec, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r22.zzb()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x03ed, code lost:
        r15 = r22;
        r3 = r23;
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("join", 1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x03fb, code lost:
        if (r22.zzb() != 0) goto L_0x0400;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x03ff, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzj;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0404, code lost:
        if (r24.isEmpty() != false) goto L_0x0422;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x0406, code lost:
        r0 = r3.zza(r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0413, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzao) != false) goto L_0x041f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0417, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzax) == false) goto L_0x041a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x041a, code lost:
        r0 = r0.zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x041f, code lost:
        r0 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0422, code lost:
        r0 = ",";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x042d, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r15.zzb(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x042e, code lost:
        r15 = r22;
        com.google.android.gms.internal.measurement.zzg.zza("pop", 0, r24);
        r0 = r22.zzb();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x043a, code lost:
        if (r0 != 0) goto L_0x043f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x043e, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x043f, code lost:
        r0 = r0 - 1;
        r1 = r15.zza(r0);
        r15.zzb(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0448, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x0449, code lost:
        r15 = r22;
        r3 = r23;
        r0 = r24;
        r5 = r18;
        com.google.android.gms.internal.measurement.zzg.zza("map", 1, r0);
        r0 = r3.zza(r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0462, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzar) == false) goto L_0x0477;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0468, code lost:
        if (r22.zzb() != 0) goto L_0x0470;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x046f, code lost:
        return new com.google.android.gms.internal.measurement.zzaf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x0476, code lost:
        return zza(r15, r3, (com.google.android.gms.internal.measurement.zzar) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x047c, code lost:
        throw new java.lang.IllegalArgumentException(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x047d, code lost:
        r15 = r22;
        r3 = r23;
        r0 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0487, code lost:
        if (r24.isEmpty() != false) goto L_0x04fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0489, code lost:
        r1 = new com.google.android.gms.internal.measurement.zzaf();
        r0 = r24.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0496, code lost:
        if (r0.hasNext() == false) goto L_0x04b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x0498, code lost:
        r2 = r3.zza(r0.next());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x04a4, code lost:
        if ((r2 instanceof com.google.android.gms.internal.measurement.zzaj) != false) goto L_0x04aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x04a6, code lost:
        r1.zza(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x04b1, code lost:
        throw new java.lang.IllegalStateException("Argument evaluation failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x04b2, code lost:
        r0 = r1.zzb();
        r2 = r22.zzg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x04be, code lost:
        if (r2.hasNext() == false) goto L_0x04d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x04c0, code lost:
        r3 = r2.next();
        r1.zzb(r3.intValue() + r0, r15.zza(r3.intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x04d7, code lost:
        r22.zzj();
        r0 = r1.zzg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x04e2, code lost:
        if (r0.hasNext() == false) goto L_0x04fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x04e4, code lost:
        r2 = r0.next();
        r15.zzb(r2.intValue(), r1.zza(r2.intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0508, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r22.zzb()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x0509, code lost:
        r15 = r22;
        r3 = r23;
        r0 = r24;
        r4 = r16;
        com.google.android.gms.internal.measurement.zzg.zzc("lastIndexOf", 2, r0);
        r1 = com.google.android.gms.internal.measurement.zzaq.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x051a, code lost:
        if (r24.isEmpty() != false) goto L_0x0527;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x051c, code lost:
        r1 = r3.zza(r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x0527, code lost:
        r6 = (double) (r22.zzb() - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x0532, code lost:
        if (r24.size() <= 1) goto L_0x056e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0534, code lost:
        r0 = r3.zza(r0.get(1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x054a, code lost:
        if (java.lang.Double.isNaN(r0.zze().doubleValue()) == false) goto L_0x0556;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x054c, code lost:
        r2 = (double) (r22.zzb() - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x0552, code lost:
        r6 = r2;
        r2 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x0556, code lost:
        r2 = com.google.android.gms.internal.measurement.zzg.zza(r0.zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x0565, code lost:
        if (r6 >= 0.0d) goto L_0x0570;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0567, code lost:
        r6 = r6 + ((double) r22.zzb());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x056e, code lost:
        r2 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x0572, code lost:
        if (r6 >= r2) goto L_0x057a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x0579, code lost:
        return new com.google.android.gms.internal.measurement.zzai(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x057a, code lost:
        r0 = (int) java.lang.Math.min((double) r22.zzb(), r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x0584, code lost:
        if (r0 < 0) goto L_0x05a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x058a, code lost:
        if (r15.zzc(r0) == false) goto L_0x05a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x0594, code lost:
        if (com.google.android.gms.internal.measurement.zzg.zza(r15.zza(r0), r1) == false) goto L_0x05a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x05a0, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x05a1, code lost:
        r0 = r0 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x05a9, code lost:
        return new com.google.android.gms.internal.measurement.zzai(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x05aa, code lost:
        r6 = r22;
        r3 = r23;
        r0 = r24;
        r5 = r18;
        com.google.android.gms.internal.measurement.zzg.zza("forEach", 1, r0);
        r0 = r3.zza(r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x05c3, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzar) == false) goto L_0x05d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x05c9, code lost:
        if (r22.zza() != 0) goto L_0x05ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x05cd, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x05ce, code lost:
        zza(r6, r3, (com.google.android.gms.internal.measurement.zzar) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:0x05d5, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x05db, code lost:
        throw new java.lang.IllegalArgumentException(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:276:0x05dc, code lost:
        r6 = r22;
        r3 = r23;
        r0 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:277:0x05e7, code lost:
        if (r24.isEmpty() == false) goto L_0x05ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:279:0x05ee, code lost:
        return new com.google.android.gms.internal.measurement.zzaf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x05ef, code lost:
        r5 = (int) com.google.android.gms.internal.measurement.zzg.zza(r3.zza(r0.get(0)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:281:0x0607, code lost:
        if (r5 >= 0) goto L_0x0613;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:282:0x0609, code lost:
        r5 = java.lang.Math.max(0, r22.zzb() + r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:284:0x0617, code lost:
        if (r5 <= r22.zzb()) goto L_0x061d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:285:0x0619, code lost:
        r5 = r22.zzb();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x061d, code lost:
        r1 = r22.zzb();
        r7 = new com.google.android.gms.internal.measurement.zzaf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x062b, code lost:
        if (r24.size() <= 1) goto L_0x068e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x062d, code lost:
        r4 = java.lang.Math.max(0, (int) com.google.android.gms.internal.measurement.zzg.zza(r3.zza(r0.get(1)).zze().doubleValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:289:0x0649, code lost:
        if (r4 <= 0) goto L_0x0661;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x064b, code lost:
        r8 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x0652, code lost:
        if (r8 >= java.lang.Math.min(r1, r5 + r4)) goto L_0x0661;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x0654, code lost:
        r7.zza(r6.zza(r5));
        r6.zzb(r5);
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0665, code lost:
        if (r24.size() <= 2) goto L_0x068d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:296:0x0667, code lost:
        r1 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:298:0x066c, code lost:
        if (r1 >= r24.size()) goto L_0x068d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x066e, code lost:
        r4 = r3.zza(r0.get(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x003f, code lost:
        r3 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x067a, code lost:
        if ((r4 instanceof com.google.android.gms.internal.measurement.zzaj) != false) goto L_0x0685;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x067c, code lost:
        r6.zza((r5 + r1) - 2, r4);
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x068c, code lost:
        throw new java.lang.IllegalArgumentException("Failed to parse elements to add");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x068d, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:305:0x068e, code lost:
        if (r5 >= r1) goto L_0x069d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x0690, code lost:
        r7.zza(r6.zza(r5));
        r6.zzb(r5, (com.google.android.gms.internal.measurement.zzaq) null);
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x069d, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x06a9, code lost:
        return zza(r22, r23, r24, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x06aa, code lost:
        r6 = r22;
        r3 = r23;
        r0 = r24;
        r5 = r18;
        com.google.android.gms.internal.measurement.zzg.zza("filter", 1, r0);
        r0 = r3.zza(r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:0x06c4, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzar) == false) goto L_0x0702;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x06ca, code lost:
        if (r22.zza() != 0) goto L_0x06d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x06d1, code lost:
        return new com.google.android.gms.internal.measurement.zzaf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x06d2, code lost:
        r1 = (com.google.android.gms.internal.measurement.zzaf) r22.zzc();
        r0 = zza(r6, r3, (com.google.android.gms.internal.measurement.zzar) r0, (java.lang.Boolean) null, java.lang.Boolean.TRUE);
        r2 = new com.google.android.gms.internal.measurement.zzaf();
        r0 = r0.zzg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:318:0x06ed, code lost:
        if (r0.hasNext() == false) goto L_0x0701;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:319:0x06ef, code lost:
        r2.zza(r1.zza(r0.next().intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x0701, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x0707, code lost:
        throw new java.lang.IllegalArgumentException(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x0708, code lost:
        r6 = r22;
        r3 = r23;
        r0 = r24;
        r1 = (com.google.android.gms.internal.measurement.zzaf) r22.zzc();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x0718, code lost:
        if (r24.isEmpty() != false) goto L_0x0769;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x071a, code lost:
        r0 = r24.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:327:0x0722, code lost:
        if (r0.hasNext() == false) goto L_0x0769;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x0724, code lost:
        r2 = r3.zza(r0.next());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:329:0x0730, code lost:
        if ((r2 instanceof com.google.android.gms.internal.measurement.zzaj) != false) goto L_0x0761;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:330:0x0732, code lost:
        r4 = r1.zzb();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:331:0x0738, code lost:
        if ((r2 instanceof com.google.android.gms.internal.measurement.zzaf) == false) goto L_0x075d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x073a, code lost:
        r2 = (com.google.android.gms.internal.measurement.zzaf) r2;
        r5 = r2.zzg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:334:0x0744, code lost:
        if (r5.hasNext() == false) goto L_0x071e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:335:0x0746, code lost:
        r6 = r5.next();
        r1.zzb(r6.intValue() + r4, r2.zza(r6.intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x075d, code lost:
        r1.zzb(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:338:0x0768, code lost:
        throw new java.lang.IllegalStateException("Failed evaluation of arguments");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:339:0x0769, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x076a, code lost:
        r6 = r22;
        com.google.android.gms.internal.measurement.zzg.zza(r3, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x077c, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r22.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0041, code lost:
        r0 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x010e, code lost:
        r3 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x012a, code lost:
        switch(r0) {
            case 0: goto L_0x076a;
            case 1: goto L_0x0708;
            case 2: goto L_0x06aa;
            case 3: goto L_0x069e;
            case 4: goto L_0x05dc;
            case 5: goto L_0x05aa;
            case 6: goto L_0x0509;
            case 7: goto L_0x047d;
            case 8: goto L_0x0449;
            case 9: goto L_0x042e;
            case 10: goto L_0x03ed;
            case 11: goto L_0x03ba;
            case 12: goto L_0x0346;
            case 13: goto L_0x02eb;
            case 14: goto L_0x02aa;
            case 15: goto L_0x0291;
            case 16: goto L_0x020e;
            case 17: goto L_0x0202;
            case 18: goto L_0x01c8;
            case 19: goto L_0x0135;
            default: goto L_0x012d;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0134, code lost:
        throw new java.lang.IllegalArgumentException("Command not supported");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0135, code lost:
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("indexOf", 2, r0);
        r1 = com.google.android.gms.internal.measurement.zzaq.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0140, code lost:
        if (r24.isEmpty() != false) goto L_0x0150;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0142, code lost:
        r3 = r23;
        r1 = r3.zza(r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0150, code lost:
        r3 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0157, code lost:
        if (r24.size() <= 1) goto L_0x018f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0159, code lost:
        r2 = com.google.android.gms.internal.measurement.zzg.zza(r3.zza(r0.get(1)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0176, code lost:
        if (r2 < ((double) r22.zzb())) goto L_0x0180;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x017f, code lost:
        return new com.google.android.gms.internal.measurement.zzai(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0180, code lost:
        r4 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0186, code lost:
        if (r2 >= 0.0d) goto L_0x0193;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0188, code lost:
        r2 = r2 + ((double) r22.zzb());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x018f, code lost:
        r4 = r16;
        r2 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0193, code lost:
        r0 = r22.zzg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x019b, code lost:
        if (r0.hasNext() == false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x019d, code lost:
        r5 = r0.next().intValue();
        r6 = (double) r5;
        r15 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01ac, code lost:
        if (r6 < r2) goto L_0x0197;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01b6, code lost:
        if (com.google.android.gms.internal.measurement.zzg.zza(r15.zza(r5), r1) == false) goto L_0x0197;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01c1, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01c7, code lost:
        return new com.google.android.gms.internal.measurement.zzai(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01c8, code lost:
        r15 = r22;
        com.google.android.gms.internal.measurement.zzg.zza("reverse", 0, r24);
        r0 = r22.zzb();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01d5, code lost:
        if (r0 == 0) goto L_0x0201;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01d7, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01da, code lost:
        if (r3 >= (r0 / 2)) goto L_0x0201;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01e0, code lost:
        if (r15.zzc(r3) == false) goto L_0x01fe;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.measurement.zzaq zza(java.lang.String r21, com.google.android.gms.internal.measurement.zzaf r22, com.google.android.gms.internal.measurement.zzh r23, java.util.List<com.google.android.gms.internal.measurement.zzaq> r24) {
        /*
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            java.lang.String r4 = "indexOf"
            java.lang.String r5 = "reverse"
            java.lang.String r6 = "slice"
            java.lang.String r7 = "shift"
            java.lang.String r8 = "every"
            java.lang.String r9 = "sort"
            java.lang.String r10 = "some"
            java.lang.String r11 = "join"
            java.lang.String r12 = "pop"
            java.lang.String r13 = "map"
            java.lang.String r14 = "lastIndexOf"
            java.lang.String r15 = "forEach"
            java.lang.String r1 = "filter"
            java.lang.String r2 = "toString"
            r21.getClass()
            r16 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            java.lang.Double r3 = java.lang.Double.valueOf(r16)
            r16 = r3
            java.lang.String r3 = "Callback should be a method"
            r17 = r2
            r18 = r3
            r2 = 2
            r19 = -1
            int r20 = r21.hashCode()
            switch(r20) {
                case -1776922004: goto L_0x011f;
                case -1354795244: goto L_0x0111;
                case -1274492040: goto L_0x0105;
                case -934873754: goto L_0x00f7;
                case -895859076: goto L_0x00eb;
                case -678635926: goto L_0x00e1;
                case -467511597: goto L_0x00d7;
                case -277637751: goto L_0x00cb;
                case 107868: goto L_0x00c0;
                case 111185: goto L_0x00b6;
                case 3267882: goto L_0x00ac;
                case 3452698: goto L_0x009f;
                case 3536116: goto L_0x0094;
                case 3536286: goto L_0x0089;
                case 96891675: goto L_0x007e;
                case 109407362: goto L_0x0073;
                case 109526418: goto L_0x0068;
                case 965561430: goto L_0x005b;
                case 1099846370: goto L_0x0050;
                case 1943291465: goto L_0x0045;
                default: goto L_0x003f;
            }
        L_0x003f:
            r3 = r17
        L_0x0041:
            r0 = r19
            goto L_0x012a
        L_0x0045:
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x004c
            goto L_0x003f
        L_0x004c:
            r0 = 19
            goto L_0x010e
        L_0x0050:
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x0057
            goto L_0x003f
        L_0x0057:
            r0 = 18
            goto L_0x010e
        L_0x005b:
            java.lang.String r3 = "reduceRight"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0064
            goto L_0x003f
        L_0x0064:
            r0 = 17
            goto L_0x010e
        L_0x0068:
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L_0x006f
            goto L_0x003f
        L_0x006f:
            r0 = 16
            goto L_0x010e
        L_0x0073:
            boolean r0 = r0.equals(r7)
            if (r0 != 0) goto L_0x007a
            goto L_0x003f
        L_0x007a:
            r0 = 15
            goto L_0x010e
        L_0x007e:
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x0085
            goto L_0x003f
        L_0x0085:
            r0 = 14
            goto L_0x010e
        L_0x0089:
            boolean r0 = r0.equals(r9)
            if (r0 != 0) goto L_0x0090
            goto L_0x003f
        L_0x0090:
            r0 = 13
            goto L_0x010e
        L_0x0094:
            boolean r0 = r0.equals(r10)
            if (r0 != 0) goto L_0x009b
            goto L_0x003f
        L_0x009b:
            r0 = 12
            goto L_0x010e
        L_0x009f:
            java.lang.String r3 = "push"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x00a8
            goto L_0x003f
        L_0x00a8:
            r0 = 11
            goto L_0x010e
        L_0x00ac:
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x00b3
            goto L_0x003f
        L_0x00b3:
            r0 = 10
            goto L_0x010e
        L_0x00b6:
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00bd
            goto L_0x003f
        L_0x00bd:
            r0 = 9
            goto L_0x010e
        L_0x00c0:
            boolean r0 = r0.equals(r13)
            if (r0 != 0) goto L_0x00c8
            goto L_0x003f
        L_0x00c8:
            r0 = 8
            goto L_0x010e
        L_0x00cb:
            java.lang.String r3 = "unshift"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x00d5
            goto L_0x003f
        L_0x00d5:
            r0 = 7
            goto L_0x010e
        L_0x00d7:
            boolean r0 = r0.equals(r14)
            if (r0 != 0) goto L_0x00df
            goto L_0x003f
        L_0x00df:
            r0 = 6
            goto L_0x010e
        L_0x00e1:
            boolean r0 = r0.equals(r15)
            if (r0 != 0) goto L_0x00e9
            goto L_0x003f
        L_0x00e9:
            r0 = 5
            goto L_0x010e
        L_0x00eb:
            java.lang.String r3 = "splice"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x00f5
            goto L_0x003f
        L_0x00f5:
            r0 = 4
            goto L_0x010e
        L_0x00f7:
            java.lang.String r3 = "reduce"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0101
            goto L_0x003f
        L_0x0101:
            r3 = r17
            r0 = 3
            goto L_0x012a
        L_0x0105:
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x010d
            goto L_0x003f
        L_0x010d:
            r0 = r2
        L_0x010e:
            r3 = r17
            goto L_0x012a
        L_0x0111:
            java.lang.String r3 = "concat"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x011b
            goto L_0x003f
        L_0x011b:
            r3 = r17
            r0 = 1
            goto L_0x012a
        L_0x011f:
            r3 = r17
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0129
            goto L_0x0041
        L_0x0129:
            r0 = 0
        L_0x012a:
            switch(r0) {
                case 0: goto L_0x076a;
                case 1: goto L_0x0708;
                case 2: goto L_0x06aa;
                case 3: goto L_0x069e;
                case 4: goto L_0x05dc;
                case 5: goto L_0x05aa;
                case 6: goto L_0x0509;
                case 7: goto L_0x047d;
                case 8: goto L_0x0449;
                case 9: goto L_0x042e;
                case 10: goto L_0x03ed;
                case 11: goto L_0x03ba;
                case 12: goto L_0x0346;
                case 13: goto L_0x02eb;
                case 14: goto L_0x02aa;
                case 15: goto L_0x0291;
                case 16: goto L_0x020e;
                case 17: goto L_0x0202;
                case 18: goto L_0x01c8;
                case 19: goto L_0x0135;
                default: goto L_0x012d;
            }
        L_0x012d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Command not supported"
            r0.<init>(r1)
            throw r0
        L_0x0135:
            r0 = r24
            com.google.android.gms.internal.measurement.zzg.zzc(r4, r2, r0)
            com.google.android.gms.internal.measurement.zzaq r1 = com.google.android.gms.internal.measurement.zzaq.zzc
            boolean r2 = r24.isEmpty()
            if (r2 != 0) goto L_0x0150
            r2 = 0
            java.lang.Object r1 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            r3 = r23
            com.google.android.gms.internal.measurement.zzaq r1 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            goto L_0x0152
        L_0x0150:
            r3 = r23
        L_0x0152:
            int r2 = r24.size()
            r4 = 1
            if (r2 <= r4) goto L_0x018f
            java.lang.Object r0 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r2 = r0.doubleValue()
            double r2 = com.google.android.gms.internal.measurement.zzg.zza((double) r2)
            int r0 = r22.zzb()
            double r4 = (double) r0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 < 0) goto L_0x0180
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            r4 = r16
            r0.<init>(r4)
            return r0
        L_0x0180:
            r4 = r16
            r5 = 0
            int r0 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0193
            int r0 = r22.zzb()
            double r5 = (double) r0
            double r2 = r2 + r5
            goto L_0x0193
        L_0x018f:
            r4 = r16
            r2 = 0
        L_0x0193:
            java.util.Iterator r0 = r22.zzg()
        L_0x0197:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x01c2
            java.lang.Object r5 = r0.next()
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            double r6 = (double) r5
            int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            r15 = r22
            if (r8 < 0) goto L_0x0197
            com.google.android.gms.internal.measurement.zzaq r5 = r15.zza((int) r5)
            boolean r5 = com.google.android.gms.internal.measurement.zzg.zza(r5, r1)
            if (r5 == 0) goto L_0x0197
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            java.lang.Double r1 = java.lang.Double.valueOf(r6)
            r0.<init>(r1)
            return r0
        L_0x01c2:
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            r0.<init>(r4)
            return r0
        L_0x01c8:
            r15 = r22
            r0 = r24
            r1 = 0
            r4 = 0
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r5, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            int r0 = r22.zzb()
            if (r0 == 0) goto L_0x0201
            r3 = 0
        L_0x01d8:
            int r1 = r0 / 2
            if (r3 >= r1) goto L_0x0201
            boolean r1 = r15.zzc((int) r3)
            if (r1 == 0) goto L_0x01fe
            com.google.android.gms.internal.measurement.zzaq r1 = r15.zza((int) r3)
            r15.zzb(r3, r4)
            r5 = 1
            int r6 = r0 + -1
            int r6 = r6 - r3
            boolean r7 = r15.zzc((int) r6)
            if (r7 == 0) goto L_0x01fa
            com.google.android.gms.internal.measurement.zzaq r7 = r15.zza((int) r6)
            r15.zzb(r3, r7)
        L_0x01fa:
            r15.zzb(r6, r1)
            goto L_0x01ff
        L_0x01fe:
            r5 = 1
        L_0x01ff:
            int r3 = r3 + r5
            goto L_0x01d8
        L_0x0201:
            return r15
        L_0x0202:
            r15 = r22
            r3 = r23
            r0 = r24
            r1 = 0
            com.google.android.gms.internal.measurement.zzaq r0 = zza((com.google.android.gms.internal.measurement.zzaf) r15, (com.google.android.gms.internal.measurement.zzh) r3, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0, (boolean) r1)
            return r0
        L_0x020e:
            r15 = r22
            r3 = r23
            r0 = r24
            r1 = 0
            com.google.android.gms.internal.measurement.zzg.zzc(r6, r2, r0)
            boolean r4 = r24.isEmpty()
            if (r4 == 0) goto L_0x0223
            com.google.android.gms.internal.measurement.zzaq r0 = r22.zzc()
            return r0
        L_0x0223:
            int r4 = r22.zzb()
            double r4 = (double) r4
            java.lang.Object r1 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            java.lang.Double r1 = r1.zze()
            double r6 = r1.doubleValue()
            double r6 = com.google.android.gms.internal.measurement.zzg.zza((double) r6)
            r8 = 0
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 >= 0) goto L_0x024a
            double r6 = r6 + r4
            double r6 = java.lang.Math.max(r6, r8)
            goto L_0x024e
        L_0x024a:
            double r6 = java.lang.Math.min(r6, r4)
        L_0x024e:
            int r1 = r24.size()
            if (r1 != r2) goto L_0x027b
            r1 = 1
            java.lang.Object r0 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r0 = r0.doubleValue()
            double r0 = com.google.android.gms.internal.measurement.zzg.zza((double) r0)
            r2 = 0
            int r8 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r8 >= 0) goto L_0x0277
            double r4 = r4 + r0
            double r4 = java.lang.Math.max(r4, r2)
            goto L_0x027b
        L_0x0277:
            double r4 = java.lang.Math.min(r4, r0)
        L_0x027b:
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>()
            int r1 = (int) r6
        L_0x0281:
            double r2 = (double) r1
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x0290
            com.google.android.gms.internal.measurement.zzaq r2 = r15.zza((int) r1)
            r0.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            r2 = 1
            int r1 = r1 + r2
            goto L_0x0281
        L_0x0290:
            return r0
        L_0x0291:
            r15 = r22
            r0 = r24
            r1 = 0
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r7, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            int r0 = r22.zzb()
            if (r0 != 0) goto L_0x02a2
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            return r0
        L_0x02a2:
            com.google.android.gms.internal.measurement.zzaq r0 = r15.zza((int) r1)
            r15.zzb((int) r1)
            return r0
        L_0x02aa:
            r15 = r22
            r3 = r23
            r0 = r24
            r1 = 0
            r2 = 1
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r8, (int) r2, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            java.lang.Object r0 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzar
            if (r1 == 0) goto L_0x02e3
            int r1 = r22.zzb()
            if (r1 == 0) goto L_0x02e0
            com.google.android.gms.internal.measurement.zzar r0 = (com.google.android.gms.internal.measurement.zzar) r0
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            com.google.android.gms.internal.measurement.zzaf r0 = zza(r15, r3, r0, r1, r2)
            int r0 = r0.zzb()
            int r1 = r22.zzb()
            if (r0 == r1) goto L_0x02e0
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzi
            return r0
        L_0x02e0:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzh
            return r0
        L_0x02e3:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r5 = r18
            r0.<init>(r5)
            throw r0
        L_0x02eb:
            r15 = r22
            r3 = r23
            r0 = r24
            r1 = 1
            r4 = 0
            com.google.android.gms.internal.measurement.zzg.zzc(r9, r1, r0)
            int r1 = r22.zzb()
            if (r1 < r2) goto L_0x0345
            java.util.List r1 = r22.zzi()
            boolean r2 = r24.isEmpty()
            if (r2 != 0) goto L_0x0320
            r2 = 0
            java.lang.Object r0 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.measurement.zzal
            if (r2 == 0) goto L_0x0318
            com.google.android.gms.internal.measurement.zzal r0 = (com.google.android.gms.internal.measurement.zzal) r0
            goto L_0x0321
        L_0x0318:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Comparator should be a method"
            r0.<init>(r1)
            throw r0
        L_0x0320:
            r0 = r4
        L_0x0321:
            com.google.android.gms.internal.measurement.zzbh r2 = new com.google.android.gms.internal.measurement.zzbh
            r2.<init>(r0, r3)
            java.util.Collections.sort(r1, r2)
            r22.zzj()
            java.util.Iterator r0 = r1.iterator()
            r3 = 0
        L_0x0331:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0345
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            r4 = 1
            int r2 = r3 + 1
            r15.zzb(r3, r1)
            r3 = r2
            goto L_0x0331
        L_0x0345:
            return r15
        L_0x0346:
            r15 = r22
            r3 = r23
            r0 = r24
            r5 = r18
            r1 = 3
            r4 = 1
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r10, (int) r4, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            r4 = 0
            java.lang.Object r0 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            boolean r4 = r0 instanceof com.google.android.gms.internal.measurement.zzal
            if (r4 == 0) goto L_0x03b4
            int r4 = r22.zzb()
            if (r4 == 0) goto L_0x03b1
            com.google.android.gms.internal.measurement.zzal r0 = (com.google.android.gms.internal.measurement.zzal) r0
            java.util.Iterator r4 = r22.zzg()
        L_0x036e:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x03b1
            java.lang.Object r5 = r4.next()
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            boolean r6 = r15.zzc((int) r5)
            if (r6 == 0) goto L_0x036e
            com.google.android.gms.internal.measurement.zzaq r6 = r15.zza((int) r5)
            com.google.android.gms.internal.measurement.zzai r7 = new com.google.android.gms.internal.measurement.zzai
            double r8 = (double) r5
            java.lang.Double r5 = java.lang.Double.valueOf(r8)
            r7.<init>(r5)
            com.google.android.gms.internal.measurement.zzaq[] r5 = new com.google.android.gms.internal.measurement.zzaq[r1]
            r8 = 0
            r5[r8] = r6
            r6 = 1
            r5[r6] = r7
            r5[r2] = r15
            java.util.List r5 = java.util.Arrays.asList(r5)
            com.google.android.gms.internal.measurement.zzaq r5 = r0.zza((com.google.android.gms.internal.measurement.zzh) r3, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r5)
            java.lang.Boolean r5 = r5.zzd()
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x036e
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzh
            return r0
        L_0x03b1:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzi
            return r0
        L_0x03b4:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r5)
            throw r0
        L_0x03ba:
            r15 = r22
            r3 = r23
            r0 = r24
            boolean r1 = r24.isEmpty()
            if (r1 != 0) goto L_0x03de
            java.util.Iterator r0 = r24.iterator()
        L_0x03ca:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x03de
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            r15.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            goto L_0x03ca
        L_0x03de:
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            int r1 = r22.zzb()
            double r1 = (double) r1
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x03ed:
            r15 = r22
            r3 = r23
            r0 = r24
            r1 = 1
            com.google.android.gms.internal.measurement.zzg.zzc(r11, r1, r0)
            int r1 = r22.zzb()
            if (r1 != 0) goto L_0x0400
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzj
            return r0
        L_0x0400:
            boolean r1 = r24.isEmpty()
            if (r1 != 0) goto L_0x0422
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzao
            if (r1 != 0) goto L_0x041f
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzax
            if (r1 == 0) goto L_0x041a
            goto L_0x041f
        L_0x041a:
            java.lang.String r0 = r0.zzf()
            goto L_0x0424
        L_0x041f:
            java.lang.String r0 = ""
            goto L_0x0424
        L_0x0422:
            java.lang.String r0 = ","
        L_0x0424:
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r15.zzb((java.lang.String) r0)
            r1.<init>(r0)
            return r1
        L_0x042e:
            r15 = r22
            r0 = r24
            r1 = 0
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r12, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            int r0 = r22.zzb()
            if (r0 != 0) goto L_0x043f
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            return r0
        L_0x043f:
            r2 = 1
            int r0 = r0 - r2
            com.google.android.gms.internal.measurement.zzaq r1 = r15.zza((int) r0)
            r15.zzb((int) r0)
            return r1
        L_0x0449:
            r15 = r22
            r3 = r23
            r0 = r24
            r5 = r18
            r1 = 0
            r2 = 1
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r13, (int) r2, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            java.lang.Object r0 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzar
            if (r1 == 0) goto L_0x0477
            int r1 = r22.zzb()
            if (r1 != 0) goto L_0x0470
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>()
            return r0
        L_0x0470:
            com.google.android.gms.internal.measurement.zzar r0 = (com.google.android.gms.internal.measurement.zzar) r0
            com.google.android.gms.internal.measurement.zzaf r0 = zza(r15, r3, r0)
            return r0
        L_0x0477:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r5)
            throw r0
        L_0x047d:
            r15 = r22
            r3 = r23
            r0 = r24
            boolean r1 = r24.isEmpty()
            if (r1 != 0) goto L_0x04fa
            com.google.android.gms.internal.measurement.zzaf r1 = new com.google.android.gms.internal.measurement.zzaf
            r1.<init>()
            java.util.Iterator r0 = r24.iterator()
        L_0x0492:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x04b2
            java.lang.Object r2 = r0.next()
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.measurement.zzaj
            if (r4 != 0) goto L_0x04aa
            r1.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            goto L_0x0492
        L_0x04aa:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Argument evaluation failed"
            r0.<init>(r1)
            throw r0
        L_0x04b2:
            int r0 = r1.zzb()
            java.util.Iterator r2 = r22.zzg()
        L_0x04ba:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04d7
            java.lang.Object r3 = r2.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r4 = r3.intValue()
            int r4 = r4 + r0
            int r3 = r3.intValue()
            com.google.android.gms.internal.measurement.zzaq r3 = r15.zza((int) r3)
            r1.zzb(r4, r3)
            goto L_0x04ba
        L_0x04d7:
            r22.zzj()
            java.util.Iterator r0 = r1.zzg()
        L_0x04de:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x04fa
            java.lang.Object r2 = r0.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r3 = r2.intValue()
            int r2 = r2.intValue()
            com.google.android.gms.internal.measurement.zzaq r2 = r1.zza((int) r2)
            r15.zzb(r3, r2)
            goto L_0x04de
        L_0x04fa:
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            int r1 = r22.zzb()
            double r1 = (double) r1
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x0509:
            r15 = r22
            r3 = r23
            r0 = r24
            r4 = r16
            com.google.android.gms.internal.measurement.zzg.zzc(r14, r2, r0)
            com.google.android.gms.internal.measurement.zzaq r1 = com.google.android.gms.internal.measurement.zzaq.zzc
            boolean r2 = r24.isEmpty()
            if (r2 != 0) goto L_0x0527
            r2 = 0
            java.lang.Object r1 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r1)
        L_0x0527:
            int r2 = r22.zzb()
            r5 = 1
            int r2 = r2 - r5
            double r6 = (double) r2
            int r2 = r24.size()
            if (r2 <= r5) goto L_0x056e
            java.lang.Object r0 = r0.get(r5)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r2 = r0.zze()
            double r2 = r2.doubleValue()
            boolean r2 = java.lang.Double.isNaN(r2)
            if (r2 == 0) goto L_0x0556
            int r0 = r22.zzb()
            int r0 = r0 - r5
            double r2 = (double) r0
        L_0x0552:
            r6 = r2
            r2 = 0
            goto L_0x0563
        L_0x0556:
            java.lang.Double r0 = r0.zze()
            double r2 = r0.doubleValue()
            double r2 = com.google.android.gms.internal.measurement.zzg.zza((double) r2)
            goto L_0x0552
        L_0x0563:
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x0570
            int r0 = r22.zzb()
            double r8 = (double) r0
            double r6 = r6 + r8
            goto L_0x0570
        L_0x056e:
            r2 = 0
        L_0x0570:
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x057a
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            r0.<init>(r4)
            return r0
        L_0x057a:
            int r0 = r22.zzb()
            double r2 = (double) r0
            double r2 = java.lang.Math.min(r2, r6)
            int r0 = (int) r2
        L_0x0584:
            if (r0 < 0) goto L_0x05a4
            boolean r2 = r15.zzc((int) r0)
            if (r2 == 0) goto L_0x05a1
            com.google.android.gms.internal.measurement.zzaq r2 = r15.zza((int) r0)
            boolean r2 = com.google.android.gms.internal.measurement.zzg.zza(r2, r1)
            if (r2 == 0) goto L_0x05a1
            com.google.android.gms.internal.measurement.zzai r1 = new com.google.android.gms.internal.measurement.zzai
            double r2 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r2)
            r1.<init>(r0)
            return r1
        L_0x05a1:
            int r0 = r0 + -1
            goto L_0x0584
        L_0x05a4:
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            r0.<init>(r4)
            return r0
        L_0x05aa:
            r6 = r22
            r3 = r23
            r0 = r24
            r5 = r18
            r1 = 1
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r15, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzar
            if (r1 == 0) goto L_0x05d6
            int r1 = r22.zza()
            if (r1 != 0) goto L_0x05ce
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            return r0
        L_0x05ce:
            com.google.android.gms.internal.measurement.zzar r0 = (com.google.android.gms.internal.measurement.zzar) r0
            zza(r6, r3, r0)
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            return r0
        L_0x05d6:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r5)
            throw r0
        L_0x05dc:
            r6 = r22
            r3 = r23
            r0 = r24
            r4 = 0
            boolean r1 = r24.isEmpty()
            if (r1 == 0) goto L_0x05ef
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>()
            return r0
        L_0x05ef:
            r1 = 0
            java.lang.Object r5 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzaq r5 = (com.google.android.gms.internal.measurement.zzaq) r5
            com.google.android.gms.internal.measurement.zzaq r5 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r5)
            java.lang.Double r5 = r5.zze()
            double r7 = r5.doubleValue()
            double r7 = com.google.android.gms.internal.measurement.zzg.zza((double) r7)
            int r5 = (int) r7
            if (r5 >= 0) goto L_0x0613
            int r7 = r22.zzb()
            int r7 = r7 + r5
            int r5 = java.lang.Math.max(r1, r7)
            goto L_0x061d
        L_0x0613:
            int r1 = r22.zzb()
            if (r5 <= r1) goto L_0x061d
            int r5 = r22.zzb()
        L_0x061d:
            int r1 = r22.zzb()
            com.google.android.gms.internal.measurement.zzaf r7 = new com.google.android.gms.internal.measurement.zzaf
            r7.<init>()
            int r8 = r24.size()
            r9 = 1
            if (r8 <= r9) goto L_0x068e
            java.lang.Object r4 = r0.get(r9)
            com.google.android.gms.internal.measurement.zzaq r4 = (com.google.android.gms.internal.measurement.zzaq) r4
            com.google.android.gms.internal.measurement.zzaq r4 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r4)
            java.lang.Double r4 = r4.zze()
            double r8 = r4.doubleValue()
            double r8 = com.google.android.gms.internal.measurement.zzg.zza((double) r8)
            int r4 = (int) r8
            r8 = 0
            int r4 = java.lang.Math.max(r8, r4)
            if (r4 <= 0) goto L_0x0661
            r8 = r5
        L_0x064c:
            int r9 = r5 + r4
            int r9 = java.lang.Math.min(r1, r9)
            if (r8 >= r9) goto L_0x0661
            com.google.android.gms.internal.measurement.zzaq r9 = r6.zza((int) r5)
            r7.zza((com.google.android.gms.internal.measurement.zzaq) r9)
            r6.zzb((int) r5)
            r9 = 1
            int r8 = r8 + r9
            goto L_0x064c
        L_0x0661:
            int r1 = r24.size()
            if (r1 <= r2) goto L_0x068d
            r1 = r2
        L_0x0668:
            int r4 = r24.size()
            if (r1 >= r4) goto L_0x068d
            java.lang.Object r4 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzaq r4 = (com.google.android.gms.internal.measurement.zzaq) r4
            com.google.android.gms.internal.measurement.zzaq r4 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r4)
            boolean r8 = r4 instanceof com.google.android.gms.internal.measurement.zzaj
            if (r8 != 0) goto L_0x0685
            int r8 = r5 + r1
            int r8 = r8 - r2
            r6.zza((int) r8, (com.google.android.gms.internal.measurement.zzaq) r4)
            r4 = 1
            int r1 = r1 + r4
            goto L_0x0668
        L_0x0685:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Failed to parse elements to add"
            r0.<init>(r1)
            throw r0
        L_0x068d:
            return r7
        L_0x068e:
            if (r5 >= r1) goto L_0x069d
            com.google.android.gms.internal.measurement.zzaq r0 = r6.zza((int) r5)
            r7.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            r6.zzb(r5, r4)
            r2 = 1
            int r5 = r5 + r2
            goto L_0x068e
        L_0x069d:
            return r7
        L_0x069e:
            r6 = r22
            r3 = r23
            r0 = r24
            r2 = 1
            com.google.android.gms.internal.measurement.zzaq r0 = zza((com.google.android.gms.internal.measurement.zzaf) r6, (com.google.android.gms.internal.measurement.zzh) r3, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0, (boolean) r2)
            return r0
        L_0x06aa:
            r6 = r22
            r3 = r23
            r0 = r24
            r5 = r18
            r2 = 1
            r4 = 0
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r1, (int) r2, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzar
            if (r1 == 0) goto L_0x0702
            int r1 = r22.zza()
            if (r1 != 0) goto L_0x06d2
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>()
            return r0
        L_0x06d2:
            com.google.android.gms.internal.measurement.zzaq r1 = r22.zzc()
            com.google.android.gms.internal.measurement.zzaf r1 = (com.google.android.gms.internal.measurement.zzaf) r1
            com.google.android.gms.internal.measurement.zzar r0 = (com.google.android.gms.internal.measurement.zzar) r0
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            com.google.android.gms.internal.measurement.zzaf r0 = zza(r6, r3, r0, r4, r2)
            com.google.android.gms.internal.measurement.zzaf r2 = new com.google.android.gms.internal.measurement.zzaf
            r2.<init>()
            java.util.Iterator r0 = r0.zzg()
        L_0x06e9:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0701
            java.lang.Object r3 = r0.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            com.google.android.gms.internal.measurement.zzaq r3 = r1.zza((int) r3)
            r2.zza((com.google.android.gms.internal.measurement.zzaq) r3)
            goto L_0x06e9
        L_0x0701:
            return r2
        L_0x0702:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r5)
            throw r0
        L_0x0708:
            r6 = r22
            r3 = r23
            r0 = r24
            com.google.android.gms.internal.measurement.zzaq r1 = r22.zzc()
            com.google.android.gms.internal.measurement.zzaf r1 = (com.google.android.gms.internal.measurement.zzaf) r1
            boolean r2 = r24.isEmpty()
            if (r2 != 0) goto L_0x0769
            java.util.Iterator r0 = r24.iterator()
        L_0x071e:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0769
            java.lang.Object r2 = r0.next()
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.measurement.zzaj
            if (r4 != 0) goto L_0x0761
            int r4 = r1.zzb()
            boolean r5 = r2 instanceof com.google.android.gms.internal.measurement.zzaf
            if (r5 == 0) goto L_0x075d
            com.google.android.gms.internal.measurement.zzaf r2 = (com.google.android.gms.internal.measurement.zzaf) r2
            java.util.Iterator r5 = r2.zzg()
        L_0x0740:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x071e
            java.lang.Object r6 = r5.next()
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r7 = r6.intValue()
            int r7 = r7 + r4
            int r6 = r6.intValue()
            com.google.android.gms.internal.measurement.zzaq r6 = r2.zza((int) r6)
            r1.zzb(r7, r6)
            goto L_0x0740
        L_0x075d:
            r1.zzb(r4, r2)
            goto L_0x071e
        L_0x0761:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Failed evaluation of arguments"
            r0.<init>(r1)
            throw r0
        L_0x0769:
            return r1
        L_0x076a:
            r6 = r22
            r0 = r24
            r2 = r3
            r1 = 0
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r2, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            com.google.android.gms.internal.measurement.zzas r0 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r1 = r22.toString()
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbe.zza(java.lang.String, com.google.android.gms.internal.measurement.zzaf, com.google.android.gms.internal.measurement.zzh, java.util.List):com.google.android.gms.internal.measurement.zzaq");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0096 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.measurement.zzaq zza(com.google.android.gms.internal.measurement.zzaf r10, com.google.android.gms.internal.measurement.zzh r11, java.util.List<com.google.android.gms.internal.measurement.zzaq> r12, boolean r13) {
        /*
            java.lang.String r0 = "reduce"
            r1 = 1
            com.google.android.gms.internal.measurement.zzg.zzb((java.lang.String) r0, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r12)
            r2 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r0, r2, r12)
            r0 = 0
            java.lang.Object r3 = r12.get(r0)
            com.google.android.gms.internal.measurement.zzaq r3 = (com.google.android.gms.internal.measurement.zzaq) r3
            com.google.android.gms.internal.measurement.zzaq r3 = r11.zza((com.google.android.gms.internal.measurement.zzaq) r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzal
            if (r4 == 0) goto L_0x009f
            int r4 = r12.size()
            if (r4 != r2) goto L_0x0036
            java.lang.Object r12 = r12.get(r1)
            com.google.android.gms.internal.measurement.zzaq r12 = (com.google.android.gms.internal.measurement.zzaq) r12
            com.google.android.gms.internal.measurement.zzaq r12 = r11.zza((com.google.android.gms.internal.measurement.zzaq) r12)
            boolean r4 = r12 instanceof com.google.android.gms.internal.measurement.zzaj
            if (r4 != 0) goto L_0x002e
            goto L_0x003d
        L_0x002e:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r11 = "Failed to parse initial value"
            r10.<init>(r11)
            throw r10
        L_0x0036:
            int r12 = r10.zzb()
            if (r12 == 0) goto L_0x0097
            r12 = 0
        L_0x003d:
            com.google.android.gms.internal.measurement.zzal r3 = (com.google.android.gms.internal.measurement.zzal) r3
            int r4 = r10.zzb()
            if (r13 == 0) goto L_0x0047
            r5 = r0
            goto L_0x0049
        L_0x0047:
            int r5 = r4 + -1
        L_0x0049:
            if (r13 == 0) goto L_0x004d
            int r4 = r4 - r1
            goto L_0x004e
        L_0x004d:
            r4 = r0
        L_0x004e:
            if (r13 == 0) goto L_0x0052
            r13 = r1
            goto L_0x0053
        L_0x0052:
            r13 = -1
        L_0x0053:
            if (r12 != 0) goto L_0x005a
            com.google.android.gms.internal.measurement.zzaq r12 = r10.zza((int) r5)
            goto L_0x0094
        L_0x005a:
            int r6 = r4 - r5
            int r6 = r6 * r13
            if (r6 < 0) goto L_0x0096
            boolean r6 = r10.zzc((int) r5)
            if (r6 == 0) goto L_0x0094
            com.google.android.gms.internal.measurement.zzaq r6 = r10.zza((int) r5)
            com.google.android.gms.internal.measurement.zzai r7 = new com.google.android.gms.internal.measurement.zzai
            double r8 = (double) r5
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            r7.<init>(r8)
            r8 = 4
            com.google.android.gms.internal.measurement.zzaq[] r8 = new com.google.android.gms.internal.measurement.zzaq[r8]
            r8[r0] = r12
            r8[r1] = r6
            r8[r2] = r7
            r12 = 3
            r8[r12] = r10
            java.util.List r12 = java.util.Arrays.asList(r8)
            com.google.android.gms.internal.measurement.zzaq r12 = r3.zza((com.google.android.gms.internal.measurement.zzh) r11, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r12)
            boolean r6 = r12 instanceof com.google.android.gms.internal.measurement.zzaj
            if (r6 != 0) goto L_0x008c
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "Reduce operation failed"
            r10.<init>(r11)
            throw r10
        L_0x0094:
            int r5 = r5 + r13
            goto L_0x005a
        L_0x0096:
            return r12
        L_0x0097:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "Empty array with no initial value error"
            r10.<init>(r11)
            throw r10
        L_0x009f:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r11 = "Callback should be a method"
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbe.zza(com.google.android.gms.internal.measurement.zzaf, com.google.android.gms.internal.measurement.zzh, java.util.List, boolean):com.google.android.gms.internal.measurement.zzaq");
    }
}
