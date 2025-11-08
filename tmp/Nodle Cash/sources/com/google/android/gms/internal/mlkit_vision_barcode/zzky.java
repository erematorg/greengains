package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzky implements ObjectEncoder {
    static final zzky zza = new zzky();

    static {
        a.p(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder("modelInfo"), "initialDownloadConditions"), "updateDownloadConditions"), "isModelUpdateEnabled"));
    }

    private zzky() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzrj zzrj = (zzrj) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
