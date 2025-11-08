package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzcl implements ObjectEncoder {
    static final zzcl zza = new zzcl();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("errorCode"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("imageInfo"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("isColdCall"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("options"));

    private zzcl() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzbg zzbg = (zzbg) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
