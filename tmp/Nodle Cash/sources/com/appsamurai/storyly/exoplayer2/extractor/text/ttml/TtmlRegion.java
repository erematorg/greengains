package com.appsamurai.storyly.exoplayer2.extractor.text.ttml;

final class TtmlRegion {
    public final float height;
    public final String id;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final float position;
    public final float textSize;
    public final int textSizeType;
    public final int verticalType;
    public final float width;

    public TtmlRegion(String str) {
        this(str, -3.4028235E38f, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, Integer.MIN_VALUE, -3.4028235E38f, Integer.MIN_VALUE);
    }

    public TtmlRegion(String str, float f2, float f3, int i3, int i4, float f4, float f5, int i5, float f6, int i6) {
        this.id = str;
        this.position = f2;
        this.line = f3;
        this.lineType = i3;
        this.lineAnchor = i4;
        this.width = f4;
        this.height = f5;
        this.textSizeType = i5;
        this.textSize = f6;
        this.verticalType = i6;
    }
}
