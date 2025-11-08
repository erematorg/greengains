package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.inject.Provider;

public final /* synthetic */ class zzwu implements Provider {
    public final /* synthetic */ TransportFactory zza;

    public /* synthetic */ zzwu(TransportFactory transportFactory) {
        this.zza = transportFactory;
    }

    public final Object get() {
        return this.zza.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("json"), new zzww());
    }
}
