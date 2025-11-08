package com.appsamurai.storyly.exoplayer2.extractor.text.webvtt;

import com.appsamurai.storyly.exoplayer2.extractor.text.webvtt.WebvttCueParser;
import java.util.Comparator;

public final /* synthetic */ class a implements Comparator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4623a;

    public /* synthetic */ a(int i3) {
        this.f4623a = i3;
    }

    public final int compare(Object obj, Object obj2) {
        switch (this.f4623a) {
            case 0:
                return Integer.compare(((WebvttCueParser.Element) obj).startTag.position, ((WebvttCueParser.Element) obj2).startTag.position);
            default:
                return Long.compare(((WebvttCueInfo) obj).startTimeUs, ((WebvttCueInfo) obj2).startTimeUs);
        }
    }
}
