package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzhb implements ObjectEncoder {
    static final zzhb zza = new zzhb();

    static {
        a.j(2, a.f(1, FieldDescriptor.builder("sampledSegmentations"), "actualCount"));
    }

    private zzhb() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zznr zznr = (zznr) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
