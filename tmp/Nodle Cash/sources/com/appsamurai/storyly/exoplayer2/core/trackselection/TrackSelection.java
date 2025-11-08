package com.appsamurai.storyly.exoplayer2.core.trackselection;

import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface TrackSelection {
    public static final int TYPE_CUSTOM_BASE = 10000;
    public static final int TYPE_UNSET = 0;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    Format getFormat(int i3);

    int getIndexInTrackGroup(int i3);

    TrackGroup getTrackGroup();

    int getType();

    int indexOf(int i3);

    int indexOf(Format format);

    int length();
}
