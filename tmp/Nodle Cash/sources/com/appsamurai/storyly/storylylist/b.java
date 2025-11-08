package com.appsamurai.storyly.storylylist;

import android.os.CountDownTimer;
import com.appsamurai.storyly.util.formatter.c;

public final class b extends CountDownTimer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ a f4700a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(a aVar, long j2) {
        super(j2, 1000);
        this.f4700a = aVar;
    }

    public void onFinish() {
        this.f4700a.f4679g.f4338e.setText(c.a(0));
    }

    public void onTick(long j2) {
        this.f4700a.f4679g.f4338e.setText(c.a((int) (j2 / ((long) 1000))));
    }
}
