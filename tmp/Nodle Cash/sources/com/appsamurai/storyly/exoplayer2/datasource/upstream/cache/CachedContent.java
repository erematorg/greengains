package com.appsamurai.storyly.exoplayer2.datasource.upstream.cache;

import androidx.annotation.Nullable;
import androidx.compose.animation.core.a;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

final class CachedContent {
    private static final String TAG = "CachedContent";
    private final TreeSet<SimpleCacheSpan> cachedSpans;
    public final int id;
    public final String key;
    private final ArrayList<Range> lockedRanges;
    private DefaultContentMetadata metadata;

    public static final class Range {
        public final long length;
        public final long position;

        public Range(long j2, long j3) {
            this.position = j2;
            this.length = j3;
        }

        public boolean contains(long j2, long j3) {
            long j4 = this.length;
            if (j4 == -1) {
                return j2 >= this.position;
            }
            if (j3 == -1) {
                return false;
            }
            long j5 = this.position;
            return j5 <= j2 && j2 + j3 <= j5 + j4;
        }

        public boolean intersects(long j2, long j3) {
            long j4 = this.position;
            if (j4 > j2) {
                return j3 == -1 || j2 + j3 > j4;
            }
            long j5 = this.length;
            return j5 == -1 || j4 + j5 > j2;
        }
    }

    public CachedContent(int i3, String str) {
        this(i3, str, DefaultContentMetadata.EMPTY);
    }

    public void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.cachedSpans.add(simpleCacheSpan);
    }

    public boolean applyMetadataMutations(ContentMetadataMutations contentMetadataMutations) {
        DefaultContentMetadata defaultContentMetadata = this.metadata;
        DefaultContentMetadata copyWithMutationsApplied = defaultContentMetadata.copyWithMutationsApplied(contentMetadataMutations);
        this.metadata = copyWithMutationsApplied;
        return !copyWithMutationsApplied.equals(defaultContentMetadata);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CachedContent.class != obj.getClass()) {
            return false;
        }
        CachedContent cachedContent = (CachedContent) obj;
        return this.id == cachedContent.id && this.key.equals(cachedContent.key) && this.cachedSpans.equals(cachedContent.cachedSpans) && this.metadata.equals(cachedContent.metadata);
    }

    public long getCachedBytesLength(long j2, long j3) {
        boolean z2 = true;
        Assertions.checkArgument(j2 >= 0);
        if (j3 < 0) {
            z2 = false;
        }
        Assertions.checkArgument(z2);
        SimpleCacheSpan span = getSpan(j2, j3);
        long j4 = Long.MAX_VALUE;
        if (span.isHoleSpan()) {
            if (!span.isOpenEnded()) {
                j4 = span.length;
            }
            return -Math.min(j4, j3);
        }
        long j5 = j2 + j3;
        if (j5 >= 0) {
            j4 = j5;
        }
        long j6 = span.position + span.length;
        if (j6 < j4) {
            for (SimpleCacheSpan next : this.cachedSpans.tailSet(span, false)) {
                long j7 = next.position;
                if (j7 <= j6) {
                    j6 = Math.max(j6, j7 + next.length);
                    if (j6 >= j4) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return Math.min(j6 - j2, j3);
    }

    public DefaultContentMetadata getMetadata() {
        return this.metadata;
    }

    public SimpleCacheSpan getSpan(long j2, long j3) {
        SimpleCacheSpan createLookup = SimpleCacheSpan.createLookup(this.key, j2);
        SimpleCacheSpan floor = this.cachedSpans.floor(createLookup);
        if (floor != null && floor.position + floor.length > j2) {
            return floor;
        }
        SimpleCacheSpan ceiling = this.cachedSpans.ceiling(createLookup);
        if (ceiling != null) {
            long j4 = ceiling.position - j2;
            j3 = j3 == -1 ? j4 : Math.min(j4, j3);
        }
        return SimpleCacheSpan.createHole(this.key, j2, j3);
    }

    public TreeSet<SimpleCacheSpan> getSpans() {
        return this.cachedSpans;
    }

    public int hashCode() {
        return this.metadata.hashCode() + a.i(this.key, this.id * 31, 31);
    }

    public boolean isEmpty() {
        return this.cachedSpans.isEmpty();
    }

    public boolean isFullyLocked(long j2, long j3) {
        for (int i3 = 0; i3 < this.lockedRanges.size(); i3++) {
            if (this.lockedRanges.get(i3).contains(j2, j3)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFullyUnlocked() {
        return this.lockedRanges.isEmpty();
    }

    public boolean lockRange(long j2, long j3) {
        for (int i3 = 0; i3 < this.lockedRanges.size(); i3++) {
            if (this.lockedRanges.get(i3).intersects(j2, j3)) {
                return false;
            }
        }
        this.lockedRanges.add(new Range(j2, j3));
        return true;
    }

    public boolean removeSpan(CacheSpan cacheSpan) {
        if (!this.cachedSpans.remove(cacheSpan)) {
            return false;
        }
        File file = cacheSpan.file;
        if (file == null) {
            return true;
        }
        file.delete();
        return true;
    }

    public SimpleCacheSpan setLastTouchTimestamp(SimpleCacheSpan simpleCacheSpan, long j2, boolean z2) {
        Assertions.checkState(this.cachedSpans.remove(simpleCacheSpan));
        File file = (File) Assertions.checkNotNull(simpleCacheSpan.file);
        if (z2) {
            File cacheFile = SimpleCacheSpan.getCacheFile((File) Assertions.checkNotNull(file.getParentFile()), this.id, simpleCacheSpan.position, j2);
            if (file.renameTo(cacheFile)) {
                file = cacheFile;
            } else {
                Log.w(TAG, "Failed to rename " + file + " to " + cacheFile);
            }
        }
        SimpleCacheSpan copyWithFileAndLastTouchTimestamp = simpleCacheSpan.copyWithFileAndLastTouchTimestamp(file, j2);
        this.cachedSpans.add(copyWithFileAndLastTouchTimestamp);
        return copyWithFileAndLastTouchTimestamp;
    }

    public void unlockRange(long j2) {
        for (int i3 = 0; i3 < this.lockedRanges.size(); i3++) {
            if (this.lockedRanges.get(i3).position == j2) {
                this.lockedRanges.remove(i3);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public CachedContent(int i3, String str, DefaultContentMetadata defaultContentMetadata) {
        this.id = i3;
        this.key = str;
        this.metadata = defaultContentMetadata;
        this.cachedSpans = new TreeSet<>();
        this.lockedRanges = new ArrayList<>();
    }
}
