package com.google.android.gms.internal.mlkit_common;

import androidx.core.app.NotificationCompat;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzhc implements ObjectEncoder {
    static final zzhc zza = new zzhc();

    static {
        a.j(11, a.f(10, a.f(9, a.f(8, a.f(7, a.f(6, a.f(5, a.f(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder(NotificationCompat.CATEGORY_STATUS), "options"), "model"), "language"), "segmentationRequest"), "segmentationResult"), "aggregatedSegmentations"), "durationMs"), "nativeSegmentationException"), "downloadErrorCodes"), "domain"));
    }

    private zzhc() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzoa zzoa = (zzoa) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
