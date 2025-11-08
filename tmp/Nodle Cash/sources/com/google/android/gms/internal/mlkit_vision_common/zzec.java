package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzec implements ObjectEncoder {
    static final zzec zza = new zzec();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("renderer"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("vendor"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("version"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("maxImages"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("maxSsbo"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("workGroupSizes"));
    private static final FieldDescriptor zzh = a.q(7, FieldDescriptor.builder("errorCode"));

    private zzec() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzli zzli = (zzli) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
