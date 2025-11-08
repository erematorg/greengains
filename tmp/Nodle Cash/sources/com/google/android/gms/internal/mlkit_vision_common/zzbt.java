package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzbt implements ObjectEncoder {
    static final zzbt zza = new zzbt();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("durationMs"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("handledErrors"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("partiallyHandledErrors"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("unhandledErrors"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("modelNamespace"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("delegateFilter"));
    private static final FieldDescriptor zzh = a.q(7, FieldDescriptor.builder("httpResponseCode"));

    private zzbt() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzap zzap = (zzap) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
