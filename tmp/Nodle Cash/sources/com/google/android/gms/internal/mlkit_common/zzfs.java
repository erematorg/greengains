package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzfs implements ObjectEncoder {
    static final zzfs zza = new zzfs();

    static {
        a.j(15, a.f(14, a.f(13, a.f(12, a.f(11, a.f(10, a.f(9, a.f(8, a.f(7, a.f(6, a.f(5, a.f(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder("initialImageUriCount"), "defaultCaptureMode"), "flashModeChangeAllowed"), "galleryImportAllowed"), "multiPageAllowed"), "filterAllowed"), "targetResolutionWidth"), "targetResolutionHeight"), "resultFormats"), "pageEditListenerSet"), "shadowRemovalAllowed"), "stainRemovalAllowed"), "enableAllNewFeaturesByDefault"), "pageLimitMax"), "enableGalleryImportAutoTransform"));
    }

    private zzfs() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlv zzlv = (zzlv) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
