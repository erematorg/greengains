package com.appsamurai.storyly.exoplayer2.hls;

import android.content.res.loader.ResourcesLoader;
import java.util.concurrent.Flow;
import org.reactivestreams.Processor;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public final /* synthetic */ class b {
    public static /* synthetic */ ResourcesLoader a() {
        return new ResourcesLoader();
    }

    public static /* bridge */ /* synthetic */ Flow.Processor f(Processor processor) {
        return (Flow.Processor) processor;
    }

    public static /* bridge */ /* synthetic */ Flow.Publisher g(Publisher publisher) {
        return (Flow.Publisher) publisher;
    }

    public static /* bridge */ /* synthetic */ Flow.Subscriber h(Subscriber subscriber) {
        return (Flow.Subscriber) subscriber;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: MethodInlineVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.visitors.MethodInlineVisitor.inlineMth(MethodInlineVisitor.java:57)
        	at jadx.core.dex.visitors.MethodInlineVisitor.visit(MethodInlineVisitor.java:47)
        */
    public static /* synthetic */ void i() {
        /*
            android.content.res.loader.ResourcesLoader r0 = new android.content.res.loader.ResourcesLoader
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.hls.b.i():void");
    }

    public static /* bridge */ /* synthetic */ boolean x(Processor processor) {
        return processor instanceof Flow.Processor;
    }

    public static /* bridge */ /* synthetic */ boolean y(Publisher publisher) {
        return publisher instanceof Flow.Publisher;
    }

    public static /* bridge */ /* synthetic */ boolean z(Subscriber subscriber) {
        return subscriber instanceof Flow.Subscriber;
    }
}
