package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.core.app.NotificationCompat;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzli implements ObjectEncoder {
    static final zzli zza = new zzli();

    static {
        a.p(11, a.n(10, a.n(9, a.n(8, a.n(7, a.n(6, a.n(5, a.n(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder(NotificationCompat.CATEGORY_STATUS), "options"), "model"), "language"), "segmentationRequest"), "segmentationResult"), "aggregatedSegmentations"), "durationMs"), "nativeSegmentationException"), "downloadErrorCodes"), "domain"));
    }

    private zzli() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzse zzse = (zzse) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
