package com.appsamurai.storyly.exoplayer2.extractor.extractor.jpeg;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.mp4.MotionPhotoMetadata;
import java.util.List;

final class MotionPhotoDescription {
    public final List<ContainerItem> items;
    public final long photoPresentationTimestampUs;

    public static final class ContainerItem {
        public final long length;
        public final String mime;
        public final long padding;
        public final String semantic;

        public ContainerItem(String str, String str2, long j2, long j3) {
            this.mime = str;
            this.semantic = str2;
            this.length = j2;
            this.padding = j3;
        }
    }

    public MotionPhotoDescription(long j2, List<ContainerItem> list) {
        this.photoPresentationTimestampUs = j2;
        this.items = list;
    }

    @Nullable
    public MotionPhotoMetadata getMotionPhotoMetadata(long j2) {
        long j3;
        if (this.items.size() < 2) {
            return null;
        }
        long j4 = j2;
        long j5 = -1;
        long j6 = -1;
        long j7 = -1;
        long j8 = -1;
        boolean z2 = false;
        for (int size = this.items.size() - 1; size >= 0; size--) {
            ContainerItem containerItem = this.items.get(size);
            boolean equals = MimeTypes.VIDEO_MP4.equals(containerItem.mime) | z2;
            if (size == 0) {
                j4 -= containerItem.padding;
                j3 = 0;
            } else {
                j3 = j4 - containerItem.length;
            }
            long j9 = j4;
            j4 = j3;
            long j10 = j9;
            if (!equals || j4 == j10) {
                z2 = equals;
            } else {
                j8 = j10 - j4;
                j7 = j4;
                z2 = false;
            }
            if (size == 0) {
                j5 = j4;
                j6 = j10;
            }
        }
        if (j7 == -1 || j8 == -1 || j5 == -1 || j6 == -1) {
            return null;
        }
        return new MotionPhotoMetadata(j5, j6, this.photoPresentationTimestampUs, j7, j8);
    }
}
