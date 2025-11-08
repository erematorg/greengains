package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzgh implements ObjectEncoder {
    static final zzgh zza = new zzgh();
    private static final FieldDescriptor zzb = a.g(1, FieldDescriptor.builder("modelType"));
    private static final FieldDescriptor zzc = a.g(2, FieldDescriptor.builder("isDownloaded"));
    private static final FieldDescriptor zzd = a.g(3, FieldDescriptor.builder("modelName"));

    private zzgh() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzmj zzmj = (zzmj) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzmj.zza());
        objectEncoderContext.add(zzc, (Object) zzmj.zzb());
        objectEncoderContext.add(zzd, (Object) null);
    }
}
