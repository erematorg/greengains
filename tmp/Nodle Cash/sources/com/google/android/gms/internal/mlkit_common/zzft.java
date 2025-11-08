package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzft implements ObjectEncoder {
    static final zzft zza = new zzft();

    static {
        a.j(14, a.f(13, a.f(12, a.f(11, a.f(10, a.f(9, a.f(8, a.f(7, a.f(6, a.f(5, a.f(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder("source"), "appliedFilter"), "isAutoCaptureManuallyTriggered"), "isRotated"), "hasLowConfidenceProposedCorners"), "autoCaptureTriggerLatencyMs"), "galleryImportProcessingMs"), "imageWidth"), "imageHeight"), "proposedCorners"), "adjustedCorners"), "isShadowRemoved"), "numOfAppliedCleanUpStrokes"), "numOfAttemptedCleanUpStrokes"));
    }

    private zzft() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlw zzlw = (zzlw) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
