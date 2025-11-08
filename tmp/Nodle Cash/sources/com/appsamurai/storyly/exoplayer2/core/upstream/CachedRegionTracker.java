package com.appsamurai.storyly.exoplayer2.core.upstream;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheSpan;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ChunkIndex;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public final class CachedRegionTracker implements Cache.Listener {
    public static final int CACHED_TO_END = -2;
    public static final int NOT_CACHED = -1;
    private static final String TAG = "CachedRegionTracker";
    private final Cache cache;
    private final String cacheKey;
    private final ChunkIndex chunkIndex;
    private final Region lookupRegion = new Region(0, 0);
    private final TreeSet<Region> regions = new TreeSet<>();

    public static class Region implements Comparable<Region> {
        public long endOffset;
        public int endOffsetIndex;
        public long startOffset;

        public Region(long j2, long j3) {
            this.startOffset = j2;
            this.endOffset = j3;
        }

        public int compareTo(Region region) {
            return Util.compareLong(this.startOffset, region.startOffset);
        }
    }

    public CachedRegionTracker(Cache cache2, String str, ChunkIndex chunkIndex2) {
        this.cache = cache2;
        this.cacheKey = str;
        this.chunkIndex = chunkIndex2;
        synchronized (this) {
            try {
                Iterator<CacheSpan> descendingIterator = cache2.addListener(str, this).descendingIterator();
                while (descendingIterator.hasNext()) {
                    mergeSpan(descendingIterator.next());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void mergeSpan(CacheSpan cacheSpan) {
        long j2 = cacheSpan.position;
        Region region = new Region(j2, cacheSpan.length + j2);
        Region floor = this.regions.floor(region);
        Region ceiling = this.regions.ceiling(region);
        boolean regionsConnect = regionsConnect(floor, region);
        if (regionsConnect(region, ceiling)) {
            if (regionsConnect) {
                floor.endOffset = ceiling.endOffset;
                floor.endOffsetIndex = ceiling.endOffsetIndex;
            } else {
                region.endOffset = ceiling.endOffset;
                region.endOffsetIndex = ceiling.endOffsetIndex;
                this.regions.add(region);
            }
            this.regions.remove(ceiling);
        } else if (regionsConnect) {
            floor.endOffset = region.endOffset;
            int i3 = floor.endOffsetIndex;
            while (true) {
                ChunkIndex chunkIndex2 = this.chunkIndex;
                if (i3 >= chunkIndex2.length - 1) {
                    break;
                }
                int i4 = i3 + 1;
                if (chunkIndex2.offsets[i4] > floor.endOffset) {
                    break;
                }
                i3 = i4;
            }
            floor.endOffsetIndex = i3;
        } else {
            int binarySearch = Arrays.binarySearch(this.chunkIndex.offsets, region.endOffset);
            if (binarySearch < 0) {
                binarySearch = (-binarySearch) - 2;
            }
            region.endOffsetIndex = binarySearch;
            this.regions.add(region);
        }
    }

    private boolean regionsConnect(@Nullable Region region, @Nullable Region region2) {
        return (region == null || region2 == null || region.endOffset != region2.startOffset) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getRegionEndTimeMs(long r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region r0 = r6.lookupRegion     // Catch:{ all -> 0x0034 }
            r0.startOffset = r7     // Catch:{ all -> 0x0034 }
            java.util.TreeSet<com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region> r1 = r6.regions     // Catch:{ all -> 0x0034 }
            java.lang.Object r0 = r1.floor(r0)     // Catch:{ all -> 0x0034 }
            com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region r0 = (com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker.Region) r0     // Catch:{ all -> 0x0034 }
            r1 = -1
            if (r0 == 0) goto L_0x0051
            long r2 = r0.endOffset     // Catch:{ all -> 0x0034 }
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 > 0) goto L_0x0051
            int r7 = r0.endOffsetIndex     // Catch:{ all -> 0x0034 }
            if (r7 != r1) goto L_0x001b
            goto L_0x0051
        L_0x001b:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.ChunkIndex r8 = r6.chunkIndex     // Catch:{ all -> 0x0034 }
            int r0 = r8.length     // Catch:{ all -> 0x0034 }
            int r0 = r0 + -1
            if (r7 != r0) goto L_0x0036
            long[] r0 = r8.offsets     // Catch:{ all -> 0x0034 }
            r0 = r0[r7]     // Catch:{ all -> 0x0034 }
            int[] r4 = r8.sizes     // Catch:{ all -> 0x0034 }
            r4 = r4[r7]     // Catch:{ all -> 0x0034 }
            long r4 = (long) r4
            long r0 = r0 + r4
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x0036
            monitor-exit(r6)
            r6 = -2
            return r6
        L_0x0034:
            r7 = move-exception
            goto L_0x0053
        L_0x0036:
            long[] r0 = r8.durationsUs     // Catch:{ all -> 0x0034 }
            r0 = r0[r7]     // Catch:{ all -> 0x0034 }
            long[] r4 = r8.offsets     // Catch:{ all -> 0x0034 }
            r4 = r4[r7]     // Catch:{ all -> 0x0034 }
            long r2 = r2 - r4
            long r0 = r0 * r2
            int[] r2 = r8.sizes     // Catch:{ all -> 0x0034 }
            r2 = r2[r7]     // Catch:{ all -> 0x0034 }
            long r2 = (long) r2     // Catch:{ all -> 0x0034 }
            long r0 = r0 / r2
            long[] r8 = r8.timesUs     // Catch:{ all -> 0x0034 }
            r7 = r8[r7]     // Catch:{ all -> 0x0034 }
            long r7 = r7 + r0
            r0 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 / r0
            int r7 = (int) r7
            monitor-exit(r6)
            return r7
        L_0x0051:
            monitor-exit(r6)
            return r1
        L_0x0053:
            monitor-exit(r6)     // Catch:{ all -> 0x0034 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker.getRegionEndTimeMs(long):int");
    }

    public synchronized void onSpanAdded(Cache cache2, CacheSpan cacheSpan) {
        mergeSpan(cacheSpan);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0062, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onSpanRemoved(com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache r7, com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheSpan r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region r7 = new com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region     // Catch:{ all -> 0x001e }
            long r0 = r8.position     // Catch:{ all -> 0x001e }
            long r2 = r8.length     // Catch:{ all -> 0x001e }
            long r2 = r2 + r0
            r7.<init>(r0, r2)     // Catch:{ all -> 0x001e }
            java.util.TreeSet<com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region> r8 = r6.regions     // Catch:{ all -> 0x001e }
            java.lang.Object r8 = r8.floor(r7)     // Catch:{ all -> 0x001e }
            com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region r8 = (com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker.Region) r8     // Catch:{ all -> 0x001e }
            if (r8 != 0) goto L_0x0020
            java.lang.String r7 = "CachedRegionTracker"
            java.lang.String r8 = "Removed a span we were not aware of"
            com.appsamurai.storyly.exoplayer2.common.util.Log.e(r7, r8)     // Catch:{ all -> 0x001e }
            monitor-exit(r6)
            return
        L_0x001e:
            r7 = move-exception
            goto L_0x0063
        L_0x0020:
            java.util.TreeSet<com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region> r0 = r6.regions     // Catch:{ all -> 0x001e }
            r0.remove(r8)     // Catch:{ all -> 0x001e }
            long r0 = r8.startOffset     // Catch:{ all -> 0x001e }
            long r2 = r7.startOffset     // Catch:{ all -> 0x001e }
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0048
            com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region r4 = new com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region     // Catch:{ all -> 0x001e }
            r4.<init>(r0, r2)     // Catch:{ all -> 0x001e }
            com.appsamurai.storyly.exoplayer2.extractor.extractor.ChunkIndex r0 = r6.chunkIndex     // Catch:{ all -> 0x001e }
            long[] r0 = r0.offsets     // Catch:{ all -> 0x001e }
            long r1 = r4.endOffset     // Catch:{ all -> 0x001e }
            int r0 = java.util.Arrays.binarySearch(r0, r1)     // Catch:{ all -> 0x001e }
            if (r0 >= 0) goto L_0x0041
            int r0 = -r0
            int r0 = r0 + -2
        L_0x0041:
            r4.endOffsetIndex = r0     // Catch:{ all -> 0x001e }
            java.util.TreeSet<com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region> r0 = r6.regions     // Catch:{ all -> 0x001e }
            r0.add(r4)     // Catch:{ all -> 0x001e }
        L_0x0048:
            long r0 = r8.endOffset     // Catch:{ all -> 0x001e }
            long r2 = r7.endOffset     // Catch:{ all -> 0x001e }
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 <= 0) goto L_0x0061
            com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region r7 = new com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region     // Catch:{ all -> 0x001e }
            r4 = 1
            long r2 = r2 + r4
            r7.<init>(r2, r0)     // Catch:{ all -> 0x001e }
            int r8 = r8.endOffsetIndex     // Catch:{ all -> 0x001e }
            r7.endOffsetIndex = r8     // Catch:{ all -> 0x001e }
            java.util.TreeSet<com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker$Region> r8 = r6.regions     // Catch:{ all -> 0x001e }
            r8.add(r7)     // Catch:{ all -> 0x001e }
        L_0x0061:
            monitor-exit(r6)
            return
        L_0x0063:
            monitor-exit(r6)     // Catch:{ all -> 0x001e }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.upstream.CachedRegionTracker.onSpanRemoved(com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.Cache, com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheSpan):void");
    }

    public void onSpanTouched(Cache cache2, CacheSpan cacheSpan, CacheSpan cacheSpan2) {
    }

    public void release() {
        this.cache.removeListener(this.cacheKey, this);
    }
}
