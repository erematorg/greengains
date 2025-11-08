package com.google.android.gms.internal.measurement;

import A.a;
import java.util.Iterator;

public final class zzas implements zzaq, Iterable<zzaq> {
    /* access modifiers changed from: private */
    public final String zza;

    public zzas(String str) {
        if (str != null) {
            this.zza = str;
            return;
        }
        throw new IllegalArgumentException("StringValue cannot be null.");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzas)) {
            return false;
        }
        return this.zza.equals(((zzas) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator<zzaq> iterator() {
        return new zzau(this);
    }

    public final String toString() {
        return a.l("\"", this.zza, "\"");
    }

    public final zzaq zzc() {
        return new zzas(this.zza);
    }

    public final Boolean zzd() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    public final Double zze() {
        if (this.zza.isEmpty()) {
            return Double.valueOf(0.0d);
        }
        try {
            return Double.valueOf(this.zza);
        } catch (NumberFormatException unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    public final String zzf() {
        return this.zza;
    }

    public final Iterator<zzaq> zzh() {
        return new zzav(this);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01c1, code lost:
        r3 = r23;
        r2 = r3.zza(r0.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01d7, code lost:
        if (r24.size() >= 2) goto L_0x01dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01d9, code lost:
        r14 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01dc, code lost:
        r14 = r3.zza(r0.get(1)).zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0202, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r1.indexOf(r2, (int) com.google.android.gms.internal.measurement.zzg.zza(r14))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0203, code lost:
        r1 = r21;
        r3 = r23;
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc(org.apache.xerces.impl.xs.SchemaSymbols.ATTVAL_REPLACE, 2, r0);
        r2 = com.google.android.gms.internal.measurement.zzaq.zzc;
        r4 = r2.zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0217, code lost:
        if (r24.isEmpty() != false) goto L_0x0239;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0219, code lost:
        r4 = r3.zza(r0.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x022d, code lost:
        if (r24.size() <= 1) goto L_0x0239;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x022f, code lost:
        r2 = r3.zza(r0.get(1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0239, code lost:
        r0 = r1.zza;
        r5 = r0.indexOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x023f, code lost:
        if (r5 >= 0) goto L_0x0242;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0241, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0244, code lost:
        if ((r2 instanceof com.google.android.gms.internal.measurement.zzal) == false) goto L_0x026c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0246, code lost:
        r9 = 0;
        r2 = ((com.google.android.gms.internal.measurement.zzal) r2).zza(r3, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzaq[]{new com.google.android.gms.internal.measurement.zzas(r4), new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r5)), r1}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x026c, code lost:
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0287, code lost:
        return new com.google.android.gms.internal.measurement.zzas(android.support.v4.media.session.a.n(r0.substring(r9, r5), r2.zzf(), r0.substring(r4.length() + r5)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0288, code lost:
        r3 = r23;
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("substring", 2, r0);
        r1 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0298, code lost:
        if (r24.isEmpty() != false) goto L_0x02b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x029a, code lost:
        r2 = (int) com.google.android.gms.internal.measurement.zzg.zza(r3.zza(r0.get(0)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02b3, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02b9, code lost:
        if (r24.size() <= 1) goto L_0x02d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02bb, code lost:
        r0 = (int) com.google.android.gms.internal.measurement.zzg.zza(r3.zza(r0.get(1)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02d4, code lost:
        r0 = r1.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02d9, code lost:
        r2 = java.lang.Math.min(java.lang.Math.max(r2, 0), r1.length());
        r0 = java.lang.Math.min(java.lang.Math.max(r0, 0), r1.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0302, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r1.substring(java.lang.Math.min(r2, r0), java.lang.Math.max(r2, r0)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0303, code lost:
        r1 = r21;
        r3 = r23;
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("split", 2, r0);
        r2 = r1.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0313, code lost:
        if (r2.length() != 0) goto L_0x0321;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0320, code lost:
        return new com.google.android.gms.internal.measurement.zzaf(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0321, code lost:
        r5 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x032b, code lost:
        if (r24.isEmpty() == false) goto L_0x0332;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x032d, code lost:
        r5.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0332, code lost:
        r1 = r3.zza(r0.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0345, code lost:
        if (r24.size() <= 1) goto L_0x035e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0347, code lost:
        r3 = com.google.android.gms.internal.measurement.zzg.zzc(r3.zza(r0.get(1)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x035e, code lost:
        r3 = androidx.collection.SieveCacheKt.NodeLinkMask;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0365, code lost:
        if (r3 != 0) goto L_0x036d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x036c, code lost:
        return new com.google.android.gms.internal.measurement.zzaf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x036d, code lost:
        r0 = r2.split(java.util.regex.Pattern.quote(r1), ((int) r3) + 1);
        r2 = r0.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x037d, code lost:
        if (r1.isEmpty() == false) goto L_0x0397;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0380, code lost:
        if (r0.length <= 0) goto L_0x0397;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0382, code lost:
        r9 = r0[0].isEmpty();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0391, code lost:
        if (r0[r0.length - 1].isEmpty() == false) goto L_0x0398;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0393, code lost:
        r2 = r0.length - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0397, code lost:
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x039c, code lost:
        if (((long) r0.length) <= r3) goto L_0x03a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x039e, code lost:
        r2 = r2 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x03a0, code lost:
        if (r9 >= r2) goto L_0x03af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x03a2, code lost:
        r5.add(new com.google.android.gms.internal.measurement.zzas(r0[r9]));
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x03b4, code lost:
        return new com.google.android.gms.internal.measurement.zzaf((java.util.List<com.google.android.gms.internal.measurement.zzaq>) r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x03b5, code lost:
        r3 = r23;
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("slice", 2, r0);
        r1 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03c5, code lost:
        if (r24.isEmpty() != false) goto L_0x03db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03c7, code lost:
        r4 = r3.zza(r0.get(0)).zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03db, code lost:
        r4 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03dd, code lost:
        r4 = com.google.android.gms.internal.measurement.zzg.zza(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03e5, code lost:
        if (r4 >= 0.0d) goto L_0x03f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03e7, code lost:
        r4 = java.lang.Math.max(((double) r1.length()) + r4, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x03f2, code lost:
        r4 = java.lang.Math.min(r4, (double) r1.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x03fb, code lost:
        r2 = (int) r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0401, code lost:
        if (r24.size() <= 1) goto L_0x0416;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0403, code lost:
        r3 = r3.zza(r0.get(1)).zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0416, code lost:
        r3 = (double) r1.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x041b, code lost:
        r3 = com.google.android.gms.internal.measurement.zzg.zza(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0423, code lost:
        if (r3 >= 0.0d) goto L_0x0430;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0425, code lost:
        r3 = java.lang.Math.max(((double) r1.length()) + r3, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0430, code lost:
        r3 = java.lang.Math.min(r3, (double) r1.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x044a, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r1.substring(r2, java.lang.Math.max(0, ((int) r3) - r2) + r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x044b, code lost:
        r3 = r23;
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("match", 1, r0);
        r1 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x045c, code lost:
        if (r24.size() > 0) goto L_0x0461;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x045e, code lost:
        r0 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0461, code lost:
        r0 = r3.zza(r0.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0470, code lost:
        r0 = java.util.regex.Pattern.compile(r0).matcher(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x047c, code lost:
        if (r0.find() == false) goto L_0x0493;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x0492, code lost:
        return new com.google.android.gms.internal.measurement.zzaf(new com.google.android.gms.internal.measurement.zzas(r0.group()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0495, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0496, code lost:
        com.google.android.gms.internal.measurement.zzg.zza("toUpperCase", 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x04a9, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.trim());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x04aa, code lost:
        com.google.android.gms.internal.measurement.zzg.zza("toUpperCase", 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x04bf, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.toUpperCase(java.util.Locale.ENGLISH));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x04c0, code lost:
        r0 = r24;
        r3 = r23;
        com.google.android.gms.internal.measurement.zzg.zzc("lastIndexOf", 2, r0);
        r1 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x04d1, code lost:
        if (r24.size() > 0) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x04d3, code lost:
        r2 = com.google.android.gms.internal.measurement.zzaq.zzc.zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x04da, code lost:
        r2 = r3.zza(r0.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x04ee, code lost:
        if (r24.size() >= 2) goto L_0x04f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x04f0, code lost:
        r3 = Double.NaN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x04f3, code lost:
        r3 = r3.zza(r0.get(1)).zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x050a, code lost:
        if (java.lang.Double.isNaN(r3) == false) goto L_0x050f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x050c, code lost:
        r3 = Double.POSITIVE_INFINITY;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x050f, code lost:
        r3 = com.google.android.gms.internal.measurement.zzg.zza(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x0522, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r1.lastIndexOf(r2, (int) r3)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0523, code lost:
        com.google.android.gms.internal.measurement.zzg.zza(r12, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0536, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.toUpperCase());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0537, code lost:
        r1 = r21;
        r3 = r23;
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc(com.google.firebase.analytics.FirebaseAnalytics.Event.SEARCH, 1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0546, code lost:
        if (r24.isEmpty() != false) goto L_0x0557;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0548, code lost:
        r0 = r3.zza(r0.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0557, code lost:
        r0 = com.google.android.gms.internal.measurement.zzaq.zzc.zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x055d, code lost:
        r0 = java.util.regex.Pattern.compile(r0).matcher(r1.zza);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x056b, code lost:
        if (r0.find() == false) goto L_0x057c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x057b, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r0.start()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0587, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0588, code lost:
        com.google.android.gms.internal.measurement.zzg.zza("toLowerCase", 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x059d, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.toLowerCase(java.util.Locale.ENGLISH));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x059e, code lost:
        r1 = r21;
        r3 = r23;
        r0 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x05a8, code lost:
        if (r24.isEmpty() == false) goto L_0x05ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x05aa, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x05ab, code lost:
        r2 = new java.lang.StringBuilder(r1.zza);
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x05b7, code lost:
        if (r9 >= r24.size()) goto L_0x05cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x05b9, code lost:
        r2.append(r3.zza(r0.get(r9)).zzf());
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x05d6, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r2.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x05d7, code lost:
        r1 = r21;
        r3 = r23;
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc(r6, 1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x05e5, code lost:
        if (r24.isEmpty() != false) goto L_0x0600;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x05e7, code lost:
        r9 = (int) com.google.android.gms.internal.measurement.zzg.zza(r3.zza(r0.get(0)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x0600, code lost:
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x0601, code lost:
        r0 = r1.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0603, code lost:
        if (r9 < 0) goto L_0x061a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0609, code lost:
        if (r9 < r0.length()) goto L_0x060c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x0619, code lost:
        return new com.google.android.gms.internal.measurement.zzas(java.lang.String.valueOf(r0.charAt(r9)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x061c, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzj;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x061d, code lost:
        com.google.android.gms.internal.measurement.zzg.zza(r7, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x0630, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.toLowerCase());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x0631, code lost:
        r1 = r21;
        com.google.android.gms.internal.measurement.zzg.zza(r14, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x0639, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x063a, code lost:
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zza(r15, 1, r0);
        r1 = r21.zza;
        r0 = r23.zza(r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x065b, code lost:
        if ("length".equals(r0.zzf()) == false) goto L_0x0660;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x065f, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzh;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0660, code lost:
        r2 = r0.zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x066e, code lost:
        if (r2 != java.lang.Math.floor(r2)) goto L_0x067c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x0670, code lost:
        r0 = (int) r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x0671, code lost:
        if (r0 < 0) goto L_0x067c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x0677, code lost:
        if (r0 >= r1.length()) goto L_0x067c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x067b, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzh;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x067e, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzi;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00bf, code lost:
        r6 = r16;
        r14 = r17;
        r15 = r18;
        r7 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c7, code lost:
        r1 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x014d, code lost:
        r6 = r16;
        r14 = r17;
        r15 = r18;
        r7 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x019d, code lost:
        switch(r1) {
            case 0: goto L_0x063a;
            case 1: goto L_0x0631;
            case 2: goto L_0x061d;
            case 3: goto L_0x05d7;
            case 4: goto L_0x059e;
            case 5: goto L_0x0588;
            case 6: goto L_0x0537;
            case 7: goto L_0x0523;
            case 8: goto L_0x04c0;
            case 9: goto L_0x04aa;
            case 10: goto L_0x0496;
            case 11: goto L_0x044b;
            case 12: goto L_0x03b5;
            case 13: goto L_0x0303;
            case 14: goto L_0x0288;
            case 15: goto L_0x0203;
            case 16: goto L_0x01a8;
            default: goto L_0x01a0;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01a7, code lost:
        throw new java.lang.IllegalArgumentException("Command not supported");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01a8, code lost:
        r0 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("indexOf", 2, r0);
        r1 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01b6, code lost:
        if (r24.size() > 0) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01b8, code lost:
        r2 = com.google.android.gms.internal.measurement.zzaq.zzc.zzf();
        r3 = r23;
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x018c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzaq zza(java.lang.String r22, com.google.android.gms.internal.measurement.zzh r23, java.util.List<com.google.android.gms.internal.measurement.zzaq> r24) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            java.lang.String r6 = "trim"
            java.lang.String r7 = "concat"
            java.lang.String r11 = "charAt"
            boolean r12 = r11.equals(r1)
            java.lang.String r13 = "toLocaleUpperCase"
            java.lang.String r14 = "toString"
            java.lang.String r15 = "toLocaleLowerCase"
            java.lang.String r5 = "toLowerCase"
            java.lang.String r4 = "substring"
            java.lang.String r10 = "split"
            java.lang.String r9 = "slice"
            java.lang.String r8 = "search"
            java.lang.String r2 = "replace"
            java.lang.String r0 = "match"
            java.lang.String r3 = "lastIndexOf"
            r16 = r11
            java.lang.String r11 = "indexOf"
            r17 = r6
            java.lang.String r6 = "hasOwnProperty"
            r18 = r13
            java.lang.String r13 = "toUpperCase"
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r7.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r6.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r11.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r3.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r0.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r2.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r8.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r9.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r10.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r4.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r5.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r15.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r14.equals(r1)
            if (r12 != 0) goto L_0x00ae
            boolean r12 = r13.equals(r1)
            if (r12 != 0) goto L_0x00ae
            r12 = r18
            boolean r18 = r12.equals(r1)
            if (r18 != 0) goto L_0x00a9
            r18 = r6
            r6 = r17
            boolean r17 = r6.equals(r1)
            if (r17 == 0) goto L_0x009d
            goto L_0x00b1
        L_0x009d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r2 = " is not a String function"
            java.lang.String r1 = android.support.v4.media.session.a.m(r1, r2)
            r0.<init>(r1)
            throw r0
        L_0x00a9:
            r18 = r6
            r6 = r17
            goto L_0x00b1
        L_0x00ae:
            r12 = r18
            goto L_0x00a9
        L_0x00b1:
            r22.getClass()
            r17 = r14
            r19 = r15
            int r20 = r22.hashCode()
            switch(r20) {
                case -1789698943: goto L_0x018c;
                case -1776922004: goto L_0x017a;
                case -1464939364: goto L_0x0168;
                case -1361633751: goto L_0x0156;
                case -1354795244: goto L_0x0144;
                case -1137582698: goto L_0x013a;
                case -906336856: goto L_0x0131;
                case -726908483: goto L_0x0128;
                case -467511597: goto L_0x011e;
                case -399551817: goto L_0x0114;
                case 3568674: goto L_0x010a;
                case 103668165: goto L_0x0100;
                case 109526418: goto L_0x00f6;
                case 109648666: goto L_0x00eb;
                case 530542161: goto L_0x00e0;
                case 1094496948: goto L_0x00d5;
                case 1943291465: goto L_0x00ca;
                default: goto L_0x00bf;
            }
        L_0x00bf:
            r6 = r16
            r14 = r17
            r15 = r18
            r7 = r19
        L_0x00c7:
            r1 = -1
            goto L_0x019d
        L_0x00ca:
            boolean r1 = r1.equals(r11)
            if (r1 != 0) goto L_0x00d1
            goto L_0x00bf
        L_0x00d1:
            r1 = 16
            goto L_0x014d
        L_0x00d5:
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00dc
            goto L_0x00bf
        L_0x00dc:
            r1 = 15
            goto L_0x014d
        L_0x00e0:
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x00e7
            goto L_0x00bf
        L_0x00e7:
            r1 = 14
            goto L_0x014d
        L_0x00eb:
            boolean r1 = r1.equals(r10)
            if (r1 != 0) goto L_0x00f2
            goto L_0x00bf
        L_0x00f2:
            r1 = 13
            goto L_0x014d
        L_0x00f6:
            boolean r1 = r1.equals(r9)
            if (r1 != 0) goto L_0x00fd
            goto L_0x00bf
        L_0x00fd:
            r1 = 12
            goto L_0x014d
        L_0x0100:
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0107
            goto L_0x00bf
        L_0x0107:
            r1 = 11
            goto L_0x014d
        L_0x010a:
            boolean r1 = r1.equals(r6)
            if (r1 != 0) goto L_0x0111
            goto L_0x00bf
        L_0x0111:
            r1 = 10
            goto L_0x014d
        L_0x0114:
            boolean r1 = r1.equals(r13)
            if (r1 != 0) goto L_0x011b
            goto L_0x00bf
        L_0x011b:
            r1 = 9
            goto L_0x014d
        L_0x011e:
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0125
            goto L_0x00bf
        L_0x0125:
            r1 = 8
            goto L_0x014d
        L_0x0128:
            boolean r1 = r1.equals(r12)
            if (r1 != 0) goto L_0x012f
            goto L_0x00bf
        L_0x012f:
            r1 = 7
            goto L_0x014d
        L_0x0131:
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L_0x0138
            goto L_0x00bf
        L_0x0138:
            r1 = 6
            goto L_0x014d
        L_0x013a:
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L_0x0142
            goto L_0x00bf
        L_0x0142:
            r1 = 5
            goto L_0x014d
        L_0x0144:
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x014c
            goto L_0x00bf
        L_0x014c:
            r1 = 4
        L_0x014d:
            r6 = r16
            r14 = r17
            r15 = r18
            r7 = r19
            goto L_0x019d
        L_0x0156:
            r6 = r16
            boolean r1 = r1.equals(r6)
            r14 = r17
            r15 = r18
            r7 = r19
            if (r1 != 0) goto L_0x0166
            goto L_0x00c7
        L_0x0166:
            r1 = 3
            goto L_0x019d
        L_0x0168:
            r6 = r16
            r7 = r19
            boolean r1 = r1.equals(r7)
            r14 = r17
            r15 = r18
            if (r1 != 0) goto L_0x0178
            goto L_0x00c7
        L_0x0178:
            r1 = 2
            goto L_0x019d
        L_0x017a:
            r6 = r16
            r14 = r17
            r7 = r19
            boolean r1 = r1.equals(r14)
            r15 = r18
            if (r1 != 0) goto L_0x018a
            goto L_0x00c7
        L_0x018a:
            r1 = 1
            goto L_0x019d
        L_0x018c:
            r6 = r16
            r14 = r17
            r15 = r18
            r7 = r19
            boolean r1 = r1.equals(r15)
            if (r1 != 0) goto L_0x019c
            goto L_0x00c7
        L_0x019c:
            r1 = 0
        L_0x019d:
            switch(r1) {
                case 0: goto L_0x063a;
                case 1: goto L_0x0631;
                case 2: goto L_0x061d;
                case 3: goto L_0x05d7;
                case 4: goto L_0x059e;
                case 5: goto L_0x0588;
                case 6: goto L_0x0537;
                case 7: goto L_0x0523;
                case 8: goto L_0x04c0;
                case 9: goto L_0x04aa;
                case 10: goto L_0x0496;
                case 11: goto L_0x044b;
                case 12: goto L_0x03b5;
                case 13: goto L_0x0303;
                case 14: goto L_0x0288;
                case 15: goto L_0x0203;
                case 16: goto L_0x01a8;
                default: goto L_0x01a0;
            }
        L_0x01a0:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Command not supported"
            r0.<init>(r1)
            throw r0
        L_0x01a8:
            r0 = r24
            r1 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r11, r1, r0)
            r1 = r21
            java.lang.String r1 = r1.zza
            int r2 = r24.size()
            if (r2 > 0) goto L_0x01c1
            com.google.android.gms.internal.measurement.zzaq r2 = com.google.android.gms.internal.measurement.zzaq.zzc
            java.lang.String r2 = r2.zzf()
            r3 = r23
            goto L_0x01d2
        L_0x01c1:
            r2 = 0
            java.lang.Object r2 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            r3 = r23
            com.google.android.gms.internal.measurement.zzaq r2 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.String r2 = r2.zzf()
        L_0x01d2:
            int r4 = r24.size()
            r5 = 2
            if (r4 >= r5) goto L_0x01dc
            r14 = 0
            goto L_0x01ef
        L_0x01dc:
            r4 = 1
            java.lang.Object r0 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r14 = r0.doubleValue()
        L_0x01ef:
            double r3 = com.google.android.gms.internal.measurement.zzg.zza((double) r14)
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            int r3 = (int) r3
            int r1 = r1.indexOf(r2, r3)
            double r1 = (double) r1
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x0203:
            r1 = r21
            r3 = r23
            r0 = r24
            r4 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r2, r4, r0)
            com.google.android.gms.internal.measurement.zzaq r2 = com.google.android.gms.internal.measurement.zzaq.zzc
            java.lang.String r4 = r2.zzf()
            boolean r5 = r24.isEmpty()
            if (r5 != 0) goto L_0x0239
            r5 = 0
            java.lang.Object r4 = r0.get(r5)
            com.google.android.gms.internal.measurement.zzaq r4 = (com.google.android.gms.internal.measurement.zzaq) r4
            com.google.android.gms.internal.measurement.zzaq r4 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r4)
            java.lang.String r4 = r4.zzf()
            int r5 = r24.size()
            r6 = 1
            if (r5 <= r6) goto L_0x0239
            java.lang.Object r0 = r0.get(r6)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r2 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
        L_0x0239:
            java.lang.String r0 = r1.zza
            int r5 = r0.indexOf(r4)
            if (r5 >= 0) goto L_0x0242
            return r1
        L_0x0242:
            boolean r6 = r2 instanceof com.google.android.gms.internal.measurement.zzal
            if (r6 == 0) goto L_0x026c
            com.google.android.gms.internal.measurement.zzal r2 = (com.google.android.gms.internal.measurement.zzal) r2
            com.google.android.gms.internal.measurement.zzas r6 = new com.google.android.gms.internal.measurement.zzas
            r6.<init>(r4)
            com.google.android.gms.internal.measurement.zzai r7 = new com.google.android.gms.internal.measurement.zzai
            double r8 = (double) r5
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            r7.<init>(r8)
            r8 = 3
            com.google.android.gms.internal.measurement.zzaq[] r8 = new com.google.android.gms.internal.measurement.zzaq[r8]
            r9 = 0
            r8[r9] = r6
            r6 = 1
            r8[r6] = r7
            r6 = 2
            r8[r6] = r1
            java.util.List r1 = java.util.Arrays.asList(r8)
            com.google.android.gms.internal.measurement.zzaq r2 = r2.zza((com.google.android.gms.internal.measurement.zzh) r3, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r1)
            goto L_0x026d
        L_0x026c:
            r9 = 0
        L_0x026d:
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r3 = r0.substring(r9, r5)
            java.lang.String r2 = r2.zzf()
            int r4 = r4.length()
            int r4 = r4 + r5
            java.lang.String r0 = r0.substring(r4)
            java.lang.String r0 = android.support.v4.media.session.a.n(r3, r2, r0)
            r1.<init>(r0)
            return r1
        L_0x0288:
            r1 = r21
            r3 = r23
            r0 = r24
            r2 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r4, r2, r0)
            java.lang.String r1 = r1.zza
            boolean r2 = r24.isEmpty()
            if (r2 != 0) goto L_0x02b3
            r2 = 0
            java.lang.Object r4 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzaq r4 = (com.google.android.gms.internal.measurement.zzaq) r4
            com.google.android.gms.internal.measurement.zzaq r2 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r4)
            java.lang.Double r2 = r2.zze()
            double r4 = r2.doubleValue()
            double r4 = com.google.android.gms.internal.measurement.zzg.zza((double) r4)
            int r2 = (int) r4
            goto L_0x02b4
        L_0x02b3:
            r2 = 0
        L_0x02b4:
            int r4 = r24.size()
            r5 = 1
            if (r4 <= r5) goto L_0x02d4
            java.lang.Object r0 = r0.get(r5)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r3 = r0.doubleValue()
            double r3 = com.google.android.gms.internal.measurement.zzg.zza((double) r3)
            int r0 = (int) r3
        L_0x02d2:
            r3 = 0
            goto L_0x02d9
        L_0x02d4:
            int r0 = r1.length()
            goto L_0x02d2
        L_0x02d9:
            int r2 = java.lang.Math.max(r2, r3)
            int r4 = r1.length()
            int r2 = java.lang.Math.min(r2, r4)
            int r0 = java.lang.Math.max(r0, r3)
            int r3 = r1.length()
            int r0 = java.lang.Math.min(r0, r3)
            com.google.android.gms.internal.measurement.zzas r3 = new com.google.android.gms.internal.measurement.zzas
            int r4 = java.lang.Math.min(r2, r0)
            int r0 = java.lang.Math.max(r2, r0)
            java.lang.String r0 = r1.substring(r4, r0)
            r3.<init>(r0)
            return r3
        L_0x0303:
            r1 = r21
            r3 = r23
            r0 = r24
            r2 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r10, r2, r0)
            java.lang.String r2 = r1.zza
            int r4 = r2.length()
            if (r4 != 0) goto L_0x0321
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r2 = 1
            com.google.android.gms.internal.measurement.zzaq[] r2 = new com.google.android.gms.internal.measurement.zzaq[r2]
            r4 = 0
            r2[r4] = r1
            r0.<init>((com.google.android.gms.internal.measurement.zzaq[]) r2)
            return r0
        L_0x0321:
            r4 = 0
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            boolean r6 = r24.isEmpty()
            if (r6 == 0) goto L_0x0332
            r5.add(r1)
            goto L_0x03af
        L_0x0332:
            java.lang.Object r1 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            java.lang.String r1 = r1.zzf()
            int r4 = r24.size()
            r6 = 1
            if (r4 <= r6) goto L_0x035e
            java.lang.Object r0 = r0.get(r6)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r3 = r0.doubleValue()
            long r3 = com.google.android.gms.internal.measurement.zzg.zzc(r3)
            goto L_0x0361
        L_0x035e:
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
        L_0x0361:
            r6 = 0
            int r0 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x036d
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>()
            return r0
        L_0x036d:
            java.lang.String r0 = java.util.regex.Pattern.quote(r1)
            int r6 = (int) r3
            r7 = 1
            int r6 = r6 + r7
            java.lang.String[] r0 = r2.split(r0, r6)
            int r2 = r0.length
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0397
            int r1 = r0.length
            if (r1 <= 0) goto L_0x0397
            r1 = 0
            r1 = r0[r1]
            boolean r9 = r1.isEmpty()
            int r1 = r0.length
            int r1 = r1 - r7
            r1 = r0[r1]
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0398
            int r1 = r0.length
            int r2 = r1 + -1
            goto L_0x0398
        L_0x0397:
            r9 = 0
        L_0x0398:
            int r1 = r0.length
            long r6 = (long) r1
            int r1 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x03a0
            r1 = -1
            int r2 = r2 + r1
        L_0x03a0:
            if (r9 >= r2) goto L_0x03af
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            r3 = r0[r9]
            r1.<init>(r3)
            r5.add(r1)
            r1 = 1
            int r9 = r9 + r1
            goto L_0x03a0
        L_0x03af:
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>((java.util.List<com.google.android.gms.internal.measurement.zzaq>) r5)
            return r0
        L_0x03b5:
            r1 = r21
            r3 = r23
            r0 = r24
            r2 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r9, r2, r0)
            java.lang.String r1 = r1.zza
            boolean r2 = r24.isEmpty()
            if (r2 != 0) goto L_0x03db
            r2 = 0
            java.lang.Object r4 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzaq r4 = (com.google.android.gms.internal.measurement.zzaq) r4
            com.google.android.gms.internal.measurement.zzaq r2 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r4)
            java.lang.Double r2 = r2.zze()
            double r4 = r2.doubleValue()
            goto L_0x03dd
        L_0x03db:
            r4 = 0
        L_0x03dd:
            double r4 = com.google.android.gms.internal.measurement.zzg.zza((double) r4)
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L_0x03f2
            int r2 = r1.length()
            double r8 = (double) r2
            double r8 = r8 + r4
            double r4 = java.lang.Math.max(r8, r6)
            goto L_0x03fb
        L_0x03f2:
            int r2 = r1.length()
            double r6 = (double) r2
            double r4 = java.lang.Math.min(r4, r6)
        L_0x03fb:
            int r2 = (int) r4
            int r4 = r24.size()
            r5 = 1
            if (r4 <= r5) goto L_0x0416
            java.lang.Object r0 = r0.get(r5)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r3 = r0.doubleValue()
            goto L_0x041b
        L_0x0416:
            int r0 = r1.length()
            double r3 = (double) r0
        L_0x041b:
            double r3 = com.google.android.gms.internal.measurement.zzg.zza((double) r3)
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0430
            int r0 = r1.length()
            double r7 = (double) r0
            double r7 = r7 + r3
            double r3 = java.lang.Math.max(r7, r5)
            goto L_0x0439
        L_0x0430:
            int r0 = r1.length()
            double r5 = (double) r0
            double r3 = java.lang.Math.min(r3, r5)
        L_0x0439:
            int r0 = (int) r3
            int r0 = r0 - r2
            r3 = 0
            int r0 = java.lang.Math.max(r3, r0)
            int r0 = r0 + r2
            com.google.android.gms.internal.measurement.zzas r3 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r1.substring(r2, r0)
            r3.<init>(r0)
            return r3
        L_0x044b:
            r1 = r21
            r3 = r23
            r2 = r0
            r4 = 1
            r0 = r24
            com.google.android.gms.internal.measurement.zzg.zzc(r2, r4, r0)
            java.lang.String r1 = r1.zza
            int r2 = r24.size()
            if (r2 > 0) goto L_0x0461
            java.lang.String r0 = ""
            goto L_0x0470
        L_0x0461:
            r2 = 0
            java.lang.Object r0 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.String r0 = r0.zzf()
        L_0x0470:
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            java.util.regex.Matcher r0 = r0.matcher(r1)
            boolean r1 = r0.find()
            if (r1 == 0) goto L_0x0493
            com.google.android.gms.internal.measurement.zzaf r1 = new com.google.android.gms.internal.measurement.zzaf
            com.google.android.gms.internal.measurement.zzas r2 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.group()
            r2.<init>(r0)
            r0 = 1
            com.google.android.gms.internal.measurement.zzaq[] r0 = new com.google.android.gms.internal.measurement.zzaq[r0]
            r3 = 0
            r0[r3] = r2
            r1.<init>((com.google.android.gms.internal.measurement.zzaq[]) r0)
            return r1
        L_0x0493:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzd
            return r0
        L_0x0496:
            r3 = 0
            r1 = r21
            r0 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r13, (int) r3, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            java.lang.String r0 = r1.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.trim()
            r1.<init>(r0)
            return r1
        L_0x04aa:
            r3 = 0
            r1 = r21
            r0 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r13, (int) r3, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            java.lang.String r0 = r1.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toUpperCase(r2)
            r1.<init>(r0)
            return r1
        L_0x04c0:
            r1 = r21
            r0 = r24
            r2 = r3
            r4 = 2
            r3 = r23
            com.google.android.gms.internal.measurement.zzg.zzc(r2, r4, r0)
            java.lang.String r1 = r1.zza
            int r2 = r24.size()
            if (r2 > 0) goto L_0x04da
            com.google.android.gms.internal.measurement.zzaq r2 = com.google.android.gms.internal.measurement.zzaq.zzc
            java.lang.String r2 = r2.zzf()
            goto L_0x04e9
        L_0x04da:
            r2 = 0
            java.lang.Object r2 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.String r2 = r2.zzf()
        L_0x04e9:
            int r4 = r24.size()
            r5 = 2
            if (r4 >= r5) goto L_0x04f3
            r3 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            goto L_0x0506
        L_0x04f3:
            r4 = 1
            java.lang.Object r0 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r3 = r0.doubleValue()
        L_0x0506:
            boolean r0 = java.lang.Double.isNaN(r3)
            if (r0 == 0) goto L_0x050f
            r3 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            goto L_0x0513
        L_0x050f:
            double r3 = com.google.android.gms.internal.measurement.zzg.zza((double) r3)
        L_0x0513:
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            int r3 = (int) r3
            int r1 = r1.lastIndexOf(r2, r3)
            double r1 = (double) r1
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x0523:
            r2 = 0
            r1 = r21
            r0 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r12, (int) r2, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            java.lang.String r0 = r1.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.toUpperCase()
            r1.<init>(r0)
            return r1
        L_0x0537:
            r1 = r21
            r3 = r23
            r0 = r24
            r2 = 0
            r4 = 1
            com.google.android.gms.internal.measurement.zzg.zzc(r8, r4, r0)
            boolean r4 = r24.isEmpty()
            if (r4 != 0) goto L_0x0557
            java.lang.Object r0 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.String r0 = r0.zzf()
            goto L_0x055d
        L_0x0557:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            java.lang.String r0 = r0.zzf()
        L_0x055d:
            java.lang.String r1 = r1.zza
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            java.util.regex.Matcher r0 = r0.matcher(r1)
            boolean r1 = r0.find()
            if (r1 == 0) goto L_0x057c
            com.google.android.gms.internal.measurement.zzai r1 = new com.google.android.gms.internal.measurement.zzai
            int r0 = r0.start()
            double r2 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r2)
            r1.<init>(r0)
            return r1
        L_0x057c:
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            r1 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x0588:
            r2 = 0
            r1 = r21
            r0 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r5, (int) r2, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            java.lang.String r0 = r1.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toLowerCase(r2)
            r1.<init>(r0)
            return r1
        L_0x059e:
            r1 = r21
            r3 = r23
            r0 = r24
            boolean r2 = r24.isEmpty()
            if (r2 == 0) goto L_0x05ab
            return r1
        L_0x05ab:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r1 = r1.zza
            r2.<init>(r1)
            r9 = 0
        L_0x05b3:
            int r1 = r24.size()
            if (r9 >= r1) goto L_0x05cd
            java.lang.Object r1 = r0.get(r9)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            java.lang.String r1 = r1.zzf()
            r2.append(r1)
            r4 = 1
            int r9 = r9 + r4
            goto L_0x05b3
        L_0x05cd:
            com.google.android.gms.internal.measurement.zzas r0 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            return r0
        L_0x05d7:
            r1 = r21
            r3 = r23
            r0 = r24
            r4 = 1
            com.google.android.gms.internal.measurement.zzg.zzc(r6, r4, r0)
            boolean r2 = r24.isEmpty()
            if (r2 != 0) goto L_0x0600
            r2 = 0
            java.lang.Object r0 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r2 = r0.doubleValue()
            double r2 = com.google.android.gms.internal.measurement.zzg.zza((double) r2)
            int r9 = (int) r2
            goto L_0x0601
        L_0x0600:
            r9 = 0
        L_0x0601:
            java.lang.String r0 = r1.zza
            if (r9 < 0) goto L_0x061a
            int r1 = r0.length()
            if (r9 < r1) goto L_0x060c
            goto L_0x061a
        L_0x060c:
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            char r0 = r0.charAt(r9)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r0)
            return r1
        L_0x061a:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzj
            return r0
        L_0x061d:
            r2 = 0
            r1 = r21
            r0 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r7, (int) r2, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            java.lang.String r0 = r1.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.toLowerCase()
            r1.<init>(r0)
            return r1
        L_0x0631:
            r2 = 0
            r1 = r21
            r0 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r14, (int) r2, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            return r1
        L_0x063a:
            r1 = r21
            r3 = r23
            r0 = r24
            r2 = 0
            r4 = 1
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r15, (int) r4, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r0)
            java.lang.String r1 = r1.zza
            java.lang.Object r0 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r3.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.String r2 = r0.zzf()
            java.lang.String r3 = "length"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0660
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzh
            return r0
        L_0x0660:
            java.lang.Double r0 = r0.zze()
            double r2 = r0.doubleValue()
            double r4 = java.lang.Math.floor(r2)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x067c
            int r0 = (int) r2
            if (r0 < 0) goto L_0x067c
            int r1 = r1.length()
            if (r0 >= r1) goto L_0x067c
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzh
            return r0
        L_0x067c:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzi
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzas.zza(java.lang.String, com.google.android.gms.internal.measurement.zzh, java.util.List):com.google.android.gms.internal.measurement.zzaq");
    }
}
