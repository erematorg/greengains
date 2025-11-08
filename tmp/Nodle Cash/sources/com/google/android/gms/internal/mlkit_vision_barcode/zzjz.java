package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzjz implements ObjectEncoder {
    static final zzjz zza = new zzjz();

    static {
        a.p(14, a.n(13, a.n(12, a.n(11, a.n(10, a.n(9, a.n(8, a.n(7, a.n(6, a.n(5, a.n(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder("source"), "appliedFilter"), "isAutoCaptureManuallyTriggered"), "isRotated"), "hasLowConfidenceProposedCorners"), "autoCaptureTriggerLatencyMs"), "galleryImportProcessingMs"), "imageWidth"), "imageHeight"), "proposedCorners"), "adjustedCorners"), "isShadowRemoved"), "numOfAppliedCleanUpStrokes"), "numOfAttemptedCleanUpStrokes"));
    }

    private zzjz() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzqa zzqa = (zzqa) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
