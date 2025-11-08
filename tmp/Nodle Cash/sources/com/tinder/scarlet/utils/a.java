package com.tinder.scarlet.utils;

import com.tinder.scarlet.websocket.okhttp.OkHttpWebSocket;
import io.reactivex.functions.Consumer;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Consumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7648a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function1 f7649b;

    public /* synthetic */ a(Function1 function1, int i3) {
        this.f7648a = i3;
        this.f7649b = function1;
    }

    public final void accept(Object obj) {
        int i3 = this.f7648a;
        Function1 function1 = this.f7649b;
        switch (i3) {
            case 0:
                FlowableStream.start$lambda$0(function1, obj);
                return;
            case 1:
                FlowableStream.start$lambda$1(function1, obj);
                return;
            case 2:
                OkHttpWebSocket.open$lambda$0(function1, obj);
                return;
            default:
                OkHttpWebSocket.open$lambda$1(function1, obj);
                return;
        }
    }
}
