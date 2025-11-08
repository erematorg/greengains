package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecAdapter;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Striped;

public final /* synthetic */ class b implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4515a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f4516b;

    public /* synthetic */ b(int i3, int i4) {
        this.f4515a = i4;
        this.f4516b = i3;
    }

    public final Object get() {
        int i3 = this.f4515a;
        int i4 = this.f4516b;
        switch (i3) {
            case 0:
                return AsynchronousMediaCodecAdapter.Factory.lambda$new$0(i4);
            case 1:
                return AsynchronousMediaCodecAdapter.Factory.lambda$new$1(i4);
            case 2:
                return Striped.lambda$semaphore$1(i4);
            default:
                return Striped.lambda$lazyWeakSemaphore$2(i4);
        }
    }
}
