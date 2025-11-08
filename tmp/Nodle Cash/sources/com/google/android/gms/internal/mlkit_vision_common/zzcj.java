package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzcj implements ObjectEncoder {
    static final zzcj zza = new zzcj();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("errorCode"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("isColdCall"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("imageInfo"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("detectorOptions"));

    private zzcj() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzbe zzbe = (zzbe) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
