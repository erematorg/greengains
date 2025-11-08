package com.tinder.scarlet.utils;

import com.tinder.scarlet.Stream;
import io.reactivex.functions.Action;

public final /* synthetic */ class b implements Action {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Stream.Observer f7650a;

    public /* synthetic */ b(Stream.Observer observer) {
        this.f7650a = observer;
    }

    public final void run() {
        this.f7650a.onComplete();
    }
}
