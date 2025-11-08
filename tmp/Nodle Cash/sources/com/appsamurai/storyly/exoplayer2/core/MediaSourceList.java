package com.appsamurai.storyly.exoplayer2.core;

import android.os.Handler;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsCollector;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;
import com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo;
import com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaPeriod;
import com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.MediaLoadData;
import com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSourceEventListener;
import com.appsamurai.storyly.exoplayer2.core.source.ShuffleOrder;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TransferListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class MediaSourceList {
    private static final String TAG = "MediaSourceList";
    private final HashMap<MediaSourceHolder, MediaSourceAndListener> childSources;
    /* access modifiers changed from: private */
    public final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final Set<MediaSourceHolder> enabledMediaSourceHolders;
    private boolean isPrepared;
    private final IdentityHashMap<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod = new IdentityHashMap<>();
    private final Map<Object, MediaSourceHolder> mediaSourceByUid = new HashMap();
    /* access modifiers changed from: private */
    public final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private final List<MediaSourceHolder> mediaSourceHolders = new ArrayList();
    private final MediaSourceListInfoRefreshListener mediaSourceListInfoListener;
    @Nullable
    private TransferListener mediaTransferListener;
    private final PlayerId playerId;
    private ShuffleOrder shuffleOrder = new ShuffleOrder.DefaultShuffleOrder(0);

    public final class ForwardingEventListener implements MediaSourceEventListener, DrmSessionEventListener {
        private DrmSessionEventListener.EventDispatcher drmEventDispatcher;
        private final MediaSourceHolder id;
        private MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;

        public ForwardingEventListener(MediaSourceHolder mediaSourceHolder) {
            this.mediaSourceEventDispatcher = MediaSourceList.this.mediaSourceEventDispatcher;
            this.drmEventDispatcher = MediaSourceList.this.drmEventDispatcher;
            this.id = mediaSourceHolder;
        }

        private boolean maybeUpdateEventDispatcher(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            MediaSource.MediaPeriodId mediaPeriodId2;
            if (mediaPeriodId != null) {
                mediaPeriodId2 = MediaSourceList.getMediaPeriodIdForChildMediaPeriodId(this.id, mediaPeriodId);
                if (mediaPeriodId2 == null) {
                    return false;
                }
            } else {
                mediaPeriodId2 = null;
            }
            int access$300 = MediaSourceList.getWindowIndexForChildWindowIndex(this.id, i3);
            MediaSourceEventListener.EventDispatcher eventDispatcher = this.mediaSourceEventDispatcher;
            if (eventDispatcher.windowIndex != access$300 || !Util.areEqual(eventDispatcher.mediaPeriodId, mediaPeriodId2)) {
                this.mediaSourceEventDispatcher = MediaSourceList.this.mediaSourceEventDispatcher.withParameters(access$300, mediaPeriodId2, 0);
            }
            DrmSessionEventListener.EventDispatcher eventDispatcher2 = this.drmEventDispatcher;
            if (eventDispatcher2.windowIndex == access$300 && Util.areEqual(eventDispatcher2.mediaPeriodId, mediaPeriodId2)) {
                return true;
            }
            this.drmEventDispatcher = MediaSourceList.this.drmEventDispatcher.withParameters(access$300, mediaPeriodId2);
            return true;
        }

        public void onDownstreamFormatChanged(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
            if (maybeUpdateEventDispatcher(i3, mediaPeriodId)) {
                this.mediaSourceEventDispatcher.downstreamFormatChanged(mediaLoadData);
            }
        }

        public void onDrmKeysLoaded(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            if (maybeUpdateEventDispatcher(i3, mediaPeriodId)) {
                this.drmEventDispatcher.drmKeysLoaded();
            }
        }

        public void onDrmKeysRemoved(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            if (maybeUpdateEventDispatcher(i3, mediaPeriodId)) {
                this.drmEventDispatcher.drmKeysRemoved();
            }
        }

        public void onDrmKeysRestored(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            if (maybeUpdateEventDispatcher(i3, mediaPeriodId)) {
                this.drmEventDispatcher.drmKeysRestored();
            }
        }

        public void onDrmSessionAcquired(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, int i4) {
            if (maybeUpdateEventDispatcher(i3, mediaPeriodId)) {
                this.drmEventDispatcher.drmSessionAcquired(i4);
            }
        }

        public void onDrmSessionManagerError(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
            if (maybeUpdateEventDispatcher(i3, mediaPeriodId)) {
                this.drmEventDispatcher.drmSessionManagerError(exc);
            }
        }

        public void onDrmSessionReleased(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            if (maybeUpdateEventDispatcher(i3, mediaPeriodId)) {
                this.drmEventDispatcher.drmSessionReleased();
            }
        }

        public void onLoadCanceled(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            if (maybeUpdateEventDispatcher(i3, mediaPeriodId)) {
                this.mediaSourceEventDispatcher.loadCanceled(loadEventInfo, mediaLoadData);
            }
        }

        public void onLoadCompleted(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            if (maybeUpdateEventDispatcher(i3, mediaPeriodId)) {
                this.mediaSourceEventDispatcher.loadCompleted(loadEventInfo, mediaLoadData);
            }
        }

        public void onLoadError(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z2) {
            if (maybeUpdateEventDispatcher(i3, mediaPeriodId)) {
                this.mediaSourceEventDispatcher.loadError(loadEventInfo, mediaLoadData, iOException, z2);
            }
        }

        public void onLoadStarted(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            if (maybeUpdateEventDispatcher(i3, mediaPeriodId)) {
                this.mediaSourceEventDispatcher.loadStarted(loadEventInfo, mediaLoadData);
            }
        }

        public void onUpstreamDiscarded(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
            if (maybeUpdateEventDispatcher(i3, mediaPeriodId)) {
                this.mediaSourceEventDispatcher.upstreamDiscarded(mediaLoadData);
            }
        }
    }

    public static final class MediaSourceAndListener {
        public final MediaSource.MediaSourceCaller caller;
        public final ForwardingEventListener eventListener;
        public final MediaSource mediaSource;

        public MediaSourceAndListener(MediaSource mediaSource2, MediaSource.MediaSourceCaller mediaSourceCaller, ForwardingEventListener forwardingEventListener) {
            this.mediaSource = mediaSource2;
            this.caller = mediaSourceCaller;
            this.eventListener = forwardingEventListener;
        }
    }

    public static final class MediaSourceHolder implements MediaSourceInfoHolder {
        public final List<MediaSource.MediaPeriodId> activeMediaPeriodIds = new ArrayList();
        public int firstWindowIndexInChild;
        public boolean isRemoved;
        public final MaskingMediaSource mediaSource;
        public final Object uid = new Object();

        public MediaSourceHolder(MediaSource mediaSource2, boolean z2) {
            this.mediaSource = new MaskingMediaSource(mediaSource2, z2);
        }

        public Timeline getTimeline() {
            return this.mediaSource.getTimeline();
        }

        public Object getUid() {
            return this.uid;
        }

        public void reset(int i3) {
            this.firstWindowIndexInChild = i3;
            this.isRemoved = false;
            this.activeMediaPeriodIds.clear();
        }
    }

    public interface MediaSourceListInfoRefreshListener {
        void onPlaylistUpdateRequested();
    }

    public MediaSourceList(MediaSourceListInfoRefreshListener mediaSourceListInfoRefreshListener, AnalyticsCollector analyticsCollector, Handler handler, PlayerId playerId2) {
        this.playerId = playerId2;
        this.mediaSourceListInfoListener = mediaSourceListInfoRefreshListener;
        MediaSourceEventListener.EventDispatcher eventDispatcher = new MediaSourceEventListener.EventDispatcher();
        this.mediaSourceEventDispatcher = eventDispatcher;
        DrmSessionEventListener.EventDispatcher eventDispatcher2 = new DrmSessionEventListener.EventDispatcher();
        this.drmEventDispatcher = eventDispatcher2;
        this.childSources = new HashMap<>();
        this.enabledMediaSourceHolders = new HashSet();
        eventDispatcher.addEventListener(handler, analyticsCollector);
        eventDispatcher2.addEventListener(handler, analyticsCollector);
    }

    private void correctOffsets(int i3, int i4) {
        while (i3 < this.mediaSourceHolders.size()) {
            this.mediaSourceHolders.get(i3).firstWindowIndexInChild += i4;
            i3++;
        }
    }

    private void disableChildSource(MediaSourceHolder mediaSourceHolder) {
        MediaSourceAndListener mediaSourceAndListener = this.childSources.get(mediaSourceHolder);
        if (mediaSourceAndListener != null) {
            mediaSourceAndListener.mediaSource.disable(mediaSourceAndListener.caller);
        }
    }

    private void disableUnusedMediaSources() {
        Iterator<MediaSourceHolder> it = this.enabledMediaSourceHolders.iterator();
        while (it.hasNext()) {
            MediaSourceHolder next = it.next();
            if (next.activeMediaPeriodIds.isEmpty()) {
                disableChildSource(next);
                it.remove();
            }
        }
    }

    private void enableMediaSource(MediaSourceHolder mediaSourceHolder) {
        this.enabledMediaSourceHolders.add(mediaSourceHolder);
        MediaSourceAndListener mediaSourceAndListener = this.childSources.get(mediaSourceHolder);
        if (mediaSourceAndListener != null) {
            mediaSourceAndListener.mediaSource.enable(mediaSourceAndListener.caller);
        }
    }

    private static Object getChildPeriodUid(Object obj) {
        return AbstractConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(obj);
    }

    /* access modifiers changed from: private */
    @Nullable
    public static MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSourceHolder mediaSourceHolder, MediaSource.MediaPeriodId mediaPeriodId) {
        for (int i3 = 0; i3 < mediaSourceHolder.activeMediaPeriodIds.size(); i3++) {
            if (mediaSourceHolder.activeMediaPeriodIds.get(i3).windowSequenceNumber == mediaPeriodId.windowSequenceNumber) {
                return mediaPeriodId.copyWithPeriodUid(getPeriodUid(mediaSourceHolder, mediaPeriodId.periodUid));
            }
        }
        return null;
    }

    private static Object getMediaSourceHolderUid(Object obj) {
        return AbstractConcatenatedTimeline.getChildTimelineUidFromConcatenatedUid(obj);
    }

    private static Object getPeriodUid(MediaSourceHolder mediaSourceHolder, Object obj) {
        return AbstractConcatenatedTimeline.getConcatenatedUid(mediaSourceHolder.uid, obj);
    }

    /* access modifiers changed from: private */
    public static int getWindowIndexForChildWindowIndex(MediaSourceHolder mediaSourceHolder, int i3) {
        return i3 + mediaSourceHolder.firstWindowIndexInChild;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareChildSource$0(MediaSource mediaSource, Timeline timeline) {
        this.mediaSourceListInfoListener.onPlaylistUpdateRequested();
    }

    private void maybeReleaseChildSource(MediaSourceHolder mediaSourceHolder) {
        if (mediaSourceHolder.isRemoved && mediaSourceHolder.activeMediaPeriodIds.isEmpty()) {
            MediaSourceAndListener mediaSourceAndListener = (MediaSourceAndListener) Assertions.checkNotNull(this.childSources.remove(mediaSourceHolder));
            mediaSourceAndListener.mediaSource.releaseSource(mediaSourceAndListener.caller);
            mediaSourceAndListener.mediaSource.removeEventListener(mediaSourceAndListener.eventListener);
            mediaSourceAndListener.mediaSource.removeDrmEventListener(mediaSourceAndListener.eventListener);
            this.enabledMediaSourceHolders.remove(mediaSourceHolder);
        }
    }

    private void prepareChildSource(MediaSourceHolder mediaSourceHolder) {
        MaskingMediaSource maskingMediaSource = mediaSourceHolder.mediaSource;
        z zVar = new z(this);
        ForwardingEventListener forwardingEventListener = new ForwardingEventListener(mediaSourceHolder);
        this.childSources.put(mediaSourceHolder, new MediaSourceAndListener(maskingMediaSource, zVar, forwardingEventListener));
        maskingMediaSource.addEventListener(Util.createHandlerForCurrentOrMainLooper(), forwardingEventListener);
        maskingMediaSource.addDrmEventListener(Util.createHandlerForCurrentOrMainLooper(), forwardingEventListener);
        maskingMediaSource.prepareSource(zVar, this.mediaTransferListener, this.playerId);
    }

    private void removeMediaSourcesInternal(int i3, int i4) {
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            MediaSourceHolder remove = this.mediaSourceHolders.remove(i5);
            this.mediaSourceByUid.remove(remove.uid);
            correctOffsets(i5, -remove.mediaSource.getTimeline().getWindowCount());
            remove.isRemoved = true;
            if (this.isPrepared) {
                maybeReleaseChildSource(remove);
            }
        }
    }

    public Timeline addMediaSources(int i3, List<MediaSourceHolder> list, ShuffleOrder shuffleOrder2) {
        if (!list.isEmpty()) {
            this.shuffleOrder = shuffleOrder2;
            for (int i4 = i3; i4 < list.size() + i3; i4++) {
                MediaSourceHolder mediaSourceHolder = list.get(i4 - i3);
                if (i4 > 0) {
                    MediaSourceHolder mediaSourceHolder2 = this.mediaSourceHolders.get(i4 - 1);
                    Timeline timeline = mediaSourceHolder2.mediaSource.getTimeline();
                    mediaSourceHolder.reset(timeline.getWindowCount() + mediaSourceHolder2.firstWindowIndexInChild);
                } else {
                    mediaSourceHolder.reset(0);
                }
                correctOffsets(i4, mediaSourceHolder.mediaSource.getTimeline().getWindowCount());
                this.mediaSourceHolders.add(i4, mediaSourceHolder);
                this.mediaSourceByUid.put(mediaSourceHolder.uid, mediaSourceHolder);
                if (this.isPrepared) {
                    prepareChildSource(mediaSourceHolder);
                    if (this.mediaSourceByMediaPeriod.isEmpty()) {
                        this.enabledMediaSourceHolders.add(mediaSourceHolder);
                    } else {
                        disableChildSource(mediaSourceHolder);
                    }
                }
            }
        }
        return createTimeline();
    }

    public Timeline clear(@Nullable ShuffleOrder shuffleOrder2) {
        if (shuffleOrder2 == null) {
            shuffleOrder2 = this.shuffleOrder.cloneAndClear();
        }
        this.shuffleOrder = shuffleOrder2;
        removeMediaSourcesInternal(0, getSize());
        return createTimeline();
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        Object mediaSourceHolderUid = getMediaSourceHolderUid(mediaPeriodId.periodUid);
        MediaSource.MediaPeriodId copyWithPeriodUid = mediaPeriodId.copyWithPeriodUid(getChildPeriodUid(mediaPeriodId.periodUid));
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByUid.get(mediaSourceHolderUid));
        enableMediaSource(mediaSourceHolder);
        mediaSourceHolder.activeMediaPeriodIds.add(copyWithPeriodUid);
        MaskingMediaPeriod createPeriod = mediaSourceHolder.mediaSource.createPeriod(copyWithPeriodUid, allocator, j2);
        this.mediaSourceByMediaPeriod.put(createPeriod, mediaSourceHolder);
        disableUnusedMediaSources();
        return createPeriod;
    }

    public Timeline createTimeline() {
        if (this.mediaSourceHolders.isEmpty()) {
            return Timeline.EMPTY;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.mediaSourceHolders.size(); i4++) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i4);
            mediaSourceHolder.firstWindowIndexInChild = i3;
            i3 += mediaSourceHolder.mediaSource.getTimeline().getWindowCount();
        }
        return new PlaylistTimeline(this.mediaSourceHolders, this.shuffleOrder);
    }

    public int getSize() {
        return this.mediaSourceHolders.size();
    }

    public boolean isPrepared() {
        return this.isPrepared;
    }

    public Timeline moveMediaSource(int i3, int i4, ShuffleOrder shuffleOrder2) {
        return moveMediaSourceRange(i3, i3 + 1, i4, shuffleOrder2);
    }

    public Timeline moveMediaSourceRange(int i3, int i4, int i5, ShuffleOrder shuffleOrder2) {
        Assertions.checkArgument(i3 >= 0 && i3 <= i4 && i4 <= getSize() && i5 >= 0);
        this.shuffleOrder = shuffleOrder2;
        if (i3 == i4 || i3 == i5) {
            return createTimeline();
        }
        int min = Math.min(i3, i5);
        int max = Math.max(((i4 - i3) + i5) - 1, i4 - 1);
        int i6 = this.mediaSourceHolders.get(min).firstWindowIndexInChild;
        Util.moveItems(this.mediaSourceHolders, i3, i4, i5);
        while (min <= max) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(min);
            mediaSourceHolder.firstWindowIndexInChild = i6;
            i6 += mediaSourceHolder.mediaSource.getTimeline().getWindowCount();
            min++;
        }
        return createTimeline();
    }

    public void prepare(@Nullable TransferListener transferListener) {
        Assertions.checkState(!this.isPrepared);
        this.mediaTransferListener = transferListener;
        for (int i3 = 0; i3 < this.mediaSourceHolders.size(); i3++) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i3);
            prepareChildSource(mediaSourceHolder);
            this.enabledMediaSourceHolders.add(mediaSourceHolder);
        }
        this.isPrepared = true;
    }

    public void release() {
        for (MediaSourceAndListener next : this.childSources.values()) {
            try {
                next.mediaSource.releaseSource(next.caller);
            } catch (RuntimeException e3) {
                Log.e(TAG, "Failed to release child source.", e3);
            }
            next.mediaSource.removeEventListener(next.eventListener);
            next.mediaSource.removeDrmEventListener(next.eventListener);
        }
        this.childSources.clear();
        this.enabledMediaSourceHolders.clear();
        this.isPrepared = false;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByMediaPeriod.remove(mediaPeriod));
        mediaSourceHolder.mediaSource.releasePeriod(mediaPeriod);
        mediaSourceHolder.activeMediaPeriodIds.remove(((MaskingMediaPeriod) mediaPeriod).id);
        if (!this.mediaSourceByMediaPeriod.isEmpty()) {
            disableUnusedMediaSources();
        }
        maybeReleaseChildSource(mediaSourceHolder);
    }

    public Timeline removeMediaSourceRange(int i3, int i4, ShuffleOrder shuffleOrder2) {
        Assertions.checkArgument(i3 >= 0 && i3 <= i4 && i4 <= getSize());
        this.shuffleOrder = shuffleOrder2;
        removeMediaSourcesInternal(i3, i4);
        return createTimeline();
    }

    public Timeline setMediaSources(List<MediaSourceHolder> list, ShuffleOrder shuffleOrder2) {
        removeMediaSourcesInternal(0, this.mediaSourceHolders.size());
        return addMediaSources(this.mediaSourceHolders.size(), list, shuffleOrder2);
    }

    public Timeline setShuffleOrder(ShuffleOrder shuffleOrder2) {
        int size = getSize();
        if (shuffleOrder2.getLength() != size) {
            shuffleOrder2 = shuffleOrder2.cloneAndClear().cloneAndInsert(0, size);
        }
        this.shuffleOrder = shuffleOrder2;
        return createTimeline();
    }
}
