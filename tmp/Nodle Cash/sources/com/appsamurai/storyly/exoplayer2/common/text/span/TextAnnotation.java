package com.appsamurai.storyly.exoplayer2.common.text.span;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class TextAnnotation {
    public static final int POSITION_AFTER = 2;
    public static final int POSITION_BEFORE = 1;
    public static final int POSITION_UNKNOWN = -1;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Position {
    }

    private TextAnnotation() {
    }
}
