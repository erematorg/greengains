package com.appsamurai.storyly.exoplayer2.datasource.upstream.cache;

import android.support.v4.media.session.a;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import java.io.File;

public class CacheSpan implements Comparable<CacheSpan> {
    @Nullable
    public final File file;
    public final boolean isCached;
    public final String key;
    public final long lastTouchTimestamp;
    public final long length;
    public final long position;

    public CacheSpan(String str, long j2, long j3) {
        this(str, j2, j3, C.TIME_UNSET, (File) null);
    }

    public boolean isHoleSpan() {
        return !this.isCached;
    }

    public boolean isOpenEnded() {
        return this.length == -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(this.position);
        sb.append(", ");
        return a.k(this.length, "]", sb);
    }

    public CacheSpan(String str, long j2, long j3, long j4, @Nullable File file2) {
        this.key = str;
        this.position = j2;
        this.length = j3;
        this.isCached = file2 != null;
        this.file = file2;
        this.lastTouchTimestamp = j4;
    }

    public int compareTo(CacheSpan cacheSpan) {
        if (!this.key.equals(cacheSpan.key)) {
            return this.key.compareTo(cacheSpan.key);
        }
        int i3 = ((this.position - cacheSpan.position) > 0 ? 1 : ((this.position - cacheSpan.position) == 0 ? 0 : -1));
        if (i3 == 0) {
            return 0;
        }
        return i3 < 0 ? -1 : 1;
    }
}
