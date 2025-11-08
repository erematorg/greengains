package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.List;

final class zzfr extends zzfm {
    final /* synthetic */ Iterable zza;
    final /* synthetic */ int zzb;

    public zzfr(Iterable iterable, int i3) {
        this.zza = iterable;
        this.zzb = i3;
    }

    public final Iterator iterator() {
        Iterable iterable = this.zza;
        if (iterable instanceof List) {
            List list = (List) iterable;
            return list.subList(Math.min(list.size(), this.zzb), list.size()).iterator();
        }
        int i3 = this.zzb;
        Iterator it = iterable.iterator();
        it.getClass();
        zzff.zzb(i3 >= 0, "numberToAdvance must be nonnegative");
        for (int i4 = 0; i4 < i3 && it.hasNext(); i4++) {
            it.next();
        }
        return new zzfq(this, it);
    }
}
