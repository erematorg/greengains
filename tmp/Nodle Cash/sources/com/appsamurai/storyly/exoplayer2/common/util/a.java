package com.appsamurai.storyly.exoplayer2.common.util;

import java.util.concurrent.ThreadFactory;

public final /* synthetic */ class a implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f4388a;

    public /* synthetic */ a(String str) {
        this.f4388a = str;
    }

    public final Thread newThread(Runnable runnable) {
        return Util.lambda$newSingleThreadExecutor$0(this.f4388a, runnable);
    }
}
