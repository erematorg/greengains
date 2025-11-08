package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzha implements ObjectEncoder {
    static final zzha zza = new zzha();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("sourceLanguage"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("targetLanguage"));

    private zzha() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlg zzlg = (zzlg) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
