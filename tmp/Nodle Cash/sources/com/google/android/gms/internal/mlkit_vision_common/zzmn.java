package com.google.android.gms.internal.mlkit_vision_common;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.inject.Provider;

public final /* synthetic */ class zzmn implements Provider {
    public final /* synthetic */ TransportFactory zza;

    public /* synthetic */ zzmn(TransportFactory transportFactory) {
        this.zza = transportFactory;
    }

    public final Object get() {
        return this.zza.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), zzml.zza);
    }
}
