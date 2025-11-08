package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4;

import com.google.common.base.Function;
import com.google.common.escape.Escaper;

public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4619a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4620b;

    public /* synthetic */ a(Object obj, int i3) {
        this.f4619a = i3;
        this.f4620b = obj;
    }

    public final Object apply(Object obj) {
        int i3 = this.f4619a;
        Object obj2 = this.f4620b;
        switch (i3) {
            case 0:
                return ((FragmentedMp4Extractor) obj2).modifyTrack((Track) obj);
            default:
                return ((Escaper) obj2).escape((String) obj);
        }
    }
}
