package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportScheduleCallback;

final class TransportImpl<T> implements Transport<T> {
    private final String name;
    private final Encoding payloadEncoding;
    private final Transformer<T, byte[]> transformer;
    private final TransportContext transportContext;
    private final TransportInternal transportInternal;

    public TransportImpl(TransportContext transportContext2, String str, Encoding encoding, Transformer<T, byte[]> transformer2, TransportInternal transportInternal2) {
        this.transportContext = transportContext2;
        this.name = str;
        this.payloadEncoding = encoding;
        this.transformer = transformer2;
        this.transportInternal = transportInternal2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$send$0(Exception exc) {
    }

    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    public void schedule(Event<T> event, TransportScheduleCallback transportScheduleCallback) {
        this.transportInternal.send(SendRequest.builder().setTransportContext(this.transportContext).setEvent(event).setTransportName(this.name).setTransformer(this.transformer).setEncoding(this.payloadEncoding).build(), transportScheduleCallback);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, com.google.android.datatransport.TransportScheduleCallback] */
    public void send(Event<T> event) {
        schedule(event, new Object());
    }
}
