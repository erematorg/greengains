package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzcz implements ObjectEncoder {
    static final zzcz zza = new zzcz();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("cameraSource"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("eventType"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("requestedPreviewHeight"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("requestedPreviewWidth"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("actualPreviewHeight"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("actualPreviewWidth"));

    private zzcz() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzhg zzhg = (zzhg) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
