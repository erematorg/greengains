package com.google.android.recaptcha.internal;

public final class zzgb {
    private static final long[][] zza = {new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{Long.MAX_VALUE, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};

    public static long zza(long j2, long j3) {
        boolean z2 = false;
        boolean z3 = (j2 ^ j3) < 0;
        long j4 = j2 + j3;
        if ((j2 ^ j4) >= 0) {
            z2 = true;
        }
        zzgc.zza(z3 | z2, "checkedAdd", j2, j3);
        return j4;
    }

    public static long zzb(long j2, long j3) {
        boolean z2 = false;
        boolean z3 = (1 ^ j2) >= 0;
        long j4 = -1 + j2;
        if ((j2 ^ j4) >= 0) {
            z2 = true;
        }
        zzgc.zza(z3 | z2, "checkedSubtract", j2, 1);
        return j4;
    }
}
