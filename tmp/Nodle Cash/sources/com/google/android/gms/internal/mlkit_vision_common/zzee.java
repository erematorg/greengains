package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzee implements ObjectEncoder {
    static final zzee zza = new zzee();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("imageFormat"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("originalImageSize"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("compressedImageSize"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("isOdmlImage"));

    private zzee() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzij zzij = (zzij) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
