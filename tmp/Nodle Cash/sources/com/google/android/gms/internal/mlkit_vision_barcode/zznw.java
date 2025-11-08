package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.core.app.NotificationCompat;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zznw implements ObjectEncoder {
    static final zznw zza = new zznw();

    static {
        a.p(8, a.n(7, a.n(6, a.n(5, a.n(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder("inferenceCommonLogEvent"), "options"), "inputLength"), "outputLength"), "loadDictionaryErrorCode"), "translateResultStatusCode"), NotificationCompat.CATEGORY_STATUS), "downloadHttpResponseCode"));
    }

    private zznw() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzuk zzuk = (zzuk) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
