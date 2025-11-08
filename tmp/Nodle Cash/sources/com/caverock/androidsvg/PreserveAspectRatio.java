package com.caverock.androidsvg;

import org.apache.commons.lang3.StringUtils;

public class PreserveAspectRatio {
    public static final PreserveAspectRatio BOTTOM;
    public static final PreserveAspectRatio END;
    public static final PreserveAspectRatio FULLSCREEN;
    public static final PreserveAspectRatio FULLSCREEN_START;
    public static final PreserveAspectRatio LETTERBOX;
    public static final PreserveAspectRatio START;
    public static final PreserveAspectRatio STRETCH = new PreserveAspectRatio(Alignment.none, (Scale) null);
    public static final PreserveAspectRatio TOP;
    public static final PreserveAspectRatio UNSCALED = new PreserveAspectRatio((Alignment) null, (Scale) null);
    private Alignment alignment;
    private Scale scale;

    public enum Alignment {
        none,
        xMinYMin,
        xMidYMin,
        xMaxYMin,
        xMinYMid,
        xMidYMid,
        xMaxYMid,
        xMinYMax,
        xMidYMax,
        xMaxYMax
    }

    public enum Scale {
        meet,
        slice
    }

    static {
        Alignment alignment2 = Alignment.xMidYMid;
        Scale scale2 = Scale.meet;
        LETTERBOX = new PreserveAspectRatio(alignment2, scale2);
        Alignment alignment3 = Alignment.xMinYMin;
        START = new PreserveAspectRatio(alignment3, scale2);
        END = new PreserveAspectRatio(Alignment.xMaxYMax, scale2);
        TOP = new PreserveAspectRatio(Alignment.xMidYMin, scale2);
        BOTTOM = new PreserveAspectRatio(Alignment.xMidYMax, scale2);
        Scale scale3 = Scale.slice;
        FULLSCREEN = new PreserveAspectRatio(alignment2, scale3);
        FULLSCREEN_START = new PreserveAspectRatio(alignment3, scale3);
    }

    public PreserveAspectRatio(Alignment alignment2, Scale scale2) {
        this.alignment = alignment2;
        this.scale = scale2;
    }

    public static PreserveAspectRatio of(String str) {
        try {
            return SVGParser.parsePreserveAspectRatio(str);
        } catch (SVGParseException e3) {
            throw new IllegalArgumentException(e3.getMessage());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PreserveAspectRatio preserveAspectRatio = (PreserveAspectRatio) obj;
        return this.alignment == preserveAspectRatio.alignment && this.scale == preserveAspectRatio.scale;
    }

    public Alignment getAlignment() {
        return this.alignment;
    }

    public Scale getScale() {
        return this.scale;
    }

    public String toString() {
        return this.alignment + StringUtils.SPACE + this.scale;
    }
}
