package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzcf implements ObjectEncoder {
    static final zzcf zza = new zzcf();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("errorCode"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("imageInfo"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("isColdCall"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("detectorOptions"));

    private zzcf() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzba zzba = (zzba) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
