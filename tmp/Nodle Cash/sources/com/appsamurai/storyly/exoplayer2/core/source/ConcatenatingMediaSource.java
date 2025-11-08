package com.appsamurai.storyly.exoplayer2.core.source;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.AbstractConcatenatedTimeline;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.ShuffleOrder;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TransferListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ConcatenatingMediaSource extends CompositeMediaSource<MediaSourceHolder> {
    /* access modifiers changed from: private */
    public static final MediaItem EMPTY_MEDIA_ITEM = new MediaItem.Builder().setUri(Uri.EMPTY).build();
    private static final int MSG_ADD = 0;
    private static final int MSG_MOVE = 2;
    private static final int MSG_ON_COMPLETION = 5;
    private static final int MSG_REMOVE = 1;
    private static final int MSG_SET_SHUFFLE_ORDER = 3;
    private static final int MSG_UPDATE_TIMELINE = 4;
    private final Set<MediaSourceHolder> enabledMediaSourceHolders;
    private final boolean isAtomic;
    private final IdentityHashMap<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod;
    private final Map<Object, MediaSourceHolder> mediaSourceByUid;
    private final List<MediaSourceHolder> mediaSourceHolders;
    @GuardedBy("this")
    private final List<MediaSourceHolder> mediaSourcesPublic;
    private Set<HandlerAndRunnable> nextTimelineUpdateOnCompletionActions;
    @GuardedBy("this")
    private final Set<HandlerAndRunnable> pendingOnCompletionActions;
    @GuardedBy("this")
    @Nullable
    private Handler playbackThreadHandler;
    private ShuffleOrder shuffleOrder;
    private boolean timelineUpdateScheduled;
    private final boolean useLazyPreparation;

    public static final class ConcatenatedTimeline extends AbstractConcatenatedTimeline {
        private final HashMap<Object, Integer> childIndexByUid = new HashMap<>();
        private final int[] firstPeriodInChildIndices;
        private final int[] firstWindowInChildIndices;
        private final int periodCount;
        private final Timeline[] timelines;
        private final Object[] uids;
        private final int windowCount;

        public ConcatenatedTimeline(Collection<MediaSourceHolder> collection, ShuffleOrder shuffleOrder, boolean z2) {
            super(z2, shuffleOrder);
            int size = collection.size();
            this.firstPeriodInChildIndices = new int[size];
            this.firstWindowInChildIndices = new int[size];
            this.timelines = new Timeline[size];
            this.uids = new Object[size];
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            for (MediaSourceHolder next : collection) {
                this.timelines[i5] = next.mediaSource.getTimeline();
                this.firstWindowInChildIndices[i5] = i3;
                this.firstPeriodInChildIndices[i5] = i4;
                i3 += this.timelines[i5].getWindowCount();
                i4 += this.timelines[i5].getPeriodCount();
                Object[] objArr = this.uids;
                Object obj = next.uid;
                objArr[i5] = obj;
                this.childIndexByUid.put(obj, Integer.valueOf(i5));
                i5++;
            }
            this.windowCount = i3;
            this.periodCount = i4;
        }

        public int getChildIndexByChildUid(Object obj) {
            Integer num = this.childIndexByUid.get(obj);
            if (num == null) {
                return -1;
            }
            return num.intValue();
        }

        public int getChildIndexByPeriodIndex(int i3) {
            return Util.binarySearchFloor(this.firstPeriodInChildIndices, i3 + 1, false, false);
        }

        public int getChildIndexByWindowIndex(int i3) {
            return Util.binarySearchFloor(this.firstWindowInChildIndices, i3 + 1, false, false);
        }

        public Object getChildUidByChildIndex(int i3) {
            return this.uids[i3];
        }

        public int getFirstPeriodIndexByChildIndex(int i3) {
            return this.firstPeriodInChildIndices[i3];
        }

        public int getFirstWindowIndexByChildIndex(int i3) {
            return this.firstWindowInChildIndices[i3];
        }

        public int getPeriodCount() {
            return this.periodCount;
        }

        public Timeline getTimelineByChildIndex(int i3) {
            return this.timelines[i3];
        }

        public int getWindowCount() {
            return this.windowCount;
        }
    }

    public static final class FakeMediaSource extends BaseMediaSource {
        private FakeMediaSource() {
        }

        public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
            throw new UnsupportedOperationException();
        }

        public MediaItem getMediaItem() {
            return ConcatenatingMediaSource.EMPTY_MEDIA_ITEM;
        }

        public void maybeThrowSourceInfoRefreshError() {
        }

        public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        }

        public void releasePeriod(MediaPeriod mediaPeriod) {
        }

        public void releaseSourceInternal() {
        }
    }

    public static final class HandlerAndRunnable {
        private final Handler handler;
        private final Runnable runnable;

        public HandlerAndRunnable(Handler handler2, Runnable runnable2) {
            this.handler = handler2;
            this.runnable = runnable2;
        }

        public void dispatch() {
            this.handler.post(this.runnable);
        }
    }

    public static final class MediaSourceHolder {
        public final List<MediaSource.MediaPeriodId> activeMediaPeriodIds = new ArrayList();
        public int childIndex;
        public int firstWindowIndexInChild;
        public boolean isRemoved;
        public final MaskingMediaSource mediaSource;
        public final Object uid = new Object();

        public MediaSourceHolder(MediaSource mediaSource2, boolean z2) {
            this.mediaSource = new MaskingMediaSource(mediaSource2, z2);
        }

        public void reset(int i3, int i4) {
            this.childIndex = i3;
            this.firstWindowIndexInChild = i4;
            this.isRemoved = false;
            this.activeMediaPeriodIds.clear();
        }
    }

    public static final class MessageData<T> {
        public final T customData;
        public final int index;
        @Nullable
        public final HandlerAndRunnable onCompletionAction;

        public MessageData(int i3, T t2, @Nullable HandlerAndRunnable handlerAndRunnable) {
            this.index = i3;
            this.customData = t2;
            this.onCompletionAction = handlerAndRunnable;
        }
    }

    public ConcatenatingMediaSource(MediaSource... mediaSourceArr) {
        this(false, mediaSourceArr);
    }

    private void addMediaSourceInternal(int i3, MediaSourceHolder mediaSourceHolder) {
        if (i3 > 0) {
            MediaSourceHolder mediaSourceHolder2 = this.mediaSourceHolders.get(i3 - 1);
            Timeline timeline = mediaSourceHolder2.mediaSource.getTimeline();
            mediaSourceHolder.reset(i3, timeline.getWindowCount() + mediaSourceHolder2.firstWindowIndexInChild);
        } else {
            mediaSourceHolder.reset(i3, 0);
        }
        correctOffsets(i3, 1, mediaSourceHolder.mediaSource.getTimeline().getWindowCount());
        this.mediaSourceHolders.add(i3, mediaSourceHolder);
        this.mediaSourceByUid.put(mediaSourceHolder.uid, mediaSourceHolder);
        prepareChildSource(mediaSourceHolder, mediaSourceHolder.mediaSource);
        if (!isEnabled() || !this.mediaSourceByMediaPeriod.isEmpty()) {
            disableChildSource(mediaSourceHolder);
        } else {
            this.enabledMediaSourceHolders.add(mediaSourceHolder);
        }
    }

    private void addMediaSourcesInternal(int i3, Collection<MediaSourceHolder> collection) {
        for (MediaSourceHolder addMediaSourceInternal : collection) {
            addMediaSourceInternal(i3, addMediaSourceInternal);
            i3++;
        }
    }

    @GuardedBy("this")
    private void addPublicMediaSources(int i3, Collection<MediaSource> collection, @Nullable Handler handler, @Nullable Runnable runnable) {
        boolean z2 = true;
        if ((handler == null) != (runnable == null)) {
            z2 = false;
        }
        Assertions.checkArgument(z2);
        Handler handler2 = this.playbackThreadHandler;
        for (MediaSource checkNotNull : collection) {
            Assertions.checkNotNull(checkNotNull);
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (MediaSource mediaSourceHolder : collection) {
            arrayList.add(new MediaSourceHolder(mediaSourceHolder, this.useLazyPreparation));
        }
        this.mediaSourcesPublic.addAll(i3, arrayList);
        if (handler2 != null && !collection.isEmpty()) {
            handler2.obtainMessage(0, new MessageData(i3, arrayList, createOnCompletionAction(handler, runnable))).sendToTarget();
        } else if (runnable != null && handler != null) {
            handler.post(runnable);
        }
    }

    private void correctOffsets(int i3, int i4, int i5) {
        while (i3 < this.mediaSourceHolders.size()) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i3);
            mediaSourceHolder.childIndex += i4;
            mediaSourceHolder.firstWindowIndexInChild += i5;
            i3++;
        }
    }

    @GuardedBy("this")
    @Nullable
    private HandlerAndRunnable createOnCompletionAction(@Nullable Handler handler, @Nullable Runnable runnable) {
        if (handler == null || runnable == null) {
            return null;
        }
        HandlerAndRunnable handlerAndRunnable = new HandlerAndRunnable(handler, runnable);
        this.pendingOnCompletionActions.add(handlerAndRunnable);
        return handlerAndRunnable;
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

    private synchronized void dispatchOnCompletionActions(Set<HandlerAndRunnable> set) {
        try {
            for (HandlerAndRunnable dispatch : set) {
                dispatch.dispatch();
            }
            this.pendingOnCompletionActions.removeAll(set);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private void enableMediaSource(MediaSourceHolder mediaSourceHolder) {
        this.enabledMediaSourceHolders.add(mediaSourceHolder);
        enableChildSource(mediaSourceHolder);
    }

    private static Object getChildPeriodUid(Object obj) {
        return AbstractConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(obj);
    }

    private static Object getMediaSourceHolderUid(Object obj) {
        return AbstractConcatenatedTimeline.getChildTimelineUidFromConcatenatedUid(obj);
    }

    private static Object getPeriodUid(MediaSourceHolder mediaSourceHolder, Object obj) {
        return AbstractConcatenatedTimeline.getConcatenatedUid(mediaSourceHolder.uid, obj);
    }

    private Handler getPlaybackThreadHandlerOnPlaybackThread() {
        return (Handler) Assertions.checkNotNull(this.playbackThreadHandler);
    }

    /* access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        int i3 = message.what;
        if (i3 == 0) {
            MessageData messageData = (MessageData) Util.castNonNull(message.obj);
            this.shuffleOrder = this.shuffleOrder.cloneAndInsert(messageData.index, ((Collection) messageData.customData).size());
            addMediaSourcesInternal(messageData.index, (Collection) messageData.customData);
            scheduleTimelineUpdate(messageData.onCompletionAction);
        } else if (i3 == 1) {
            MessageData messageData2 = (MessageData) Util.castNonNull(message.obj);
            int i4 = messageData2.index;
            int intValue = ((Integer) messageData2.customData).intValue();
            if (i4 == 0 && intValue == this.shuffleOrder.getLength()) {
                this.shuffleOrder = this.shuffleOrder.cloneAndClear();
            } else {
                this.shuffleOrder = this.shuffleOrder.cloneAndRemove(i4, intValue);
            }
            for (int i5 = intValue - 1; i5 >= i4; i5--) {
                removeMediaSourceInternal(i5);
            }
            scheduleTimelineUpdate(messageData2.onCompletionAction);
        } else if (i3 == 2) {
            MessageData messageData3 = (MessageData) Util.castNonNull(message.obj);
            ShuffleOrder shuffleOrder2 = this.shuffleOrder;
            int i6 = messageData3.index;
            ShuffleOrder cloneAndRemove = shuffleOrder2.cloneAndRemove(i6, i6 + 1);
            this.shuffleOrder = cloneAndRemove;
            this.shuffleOrder = cloneAndRemove.cloneAndInsert(((Integer) messageData3.customData).intValue(), 1);
            moveMediaSourceInternal(messageData3.index, ((Integer) messageData3.customData).intValue());
            scheduleTimelineUpdate(messageData3.onCompletionAction);
        } else if (i3 == 3) {
            MessageData messageData4 = (MessageData) Util.castNonNull(message.obj);
            this.shuffleOrder = (ShuffleOrder) messageData4.customData;
            scheduleTimelineUpdate(messageData4.onCompletionAction);
        } else if (i3 == 4) {
            updateTimelineAndScheduleOnCompletionActions();
        } else if (i3 == 5) {
            dispatchOnCompletionActions((Set) Util.castNonNull(message.obj));
        } else {
            throw new IllegalStateException();
        }
        return true;
    }

    private void maybeReleaseChildSource(MediaSourceHolder mediaSourceHolder) {
        if (mediaSourceHolder.isRemoved && mediaSourceHolder.activeMediaPeriodIds.isEmpty()) {
            this.enabledMediaSourceHolders.remove(mediaSourceHolder);
            releaseChildSource(mediaSourceHolder);
        }
    }

    private void moveMediaSourceInternal(int i3, int i4) {
        int min = Math.min(i3, i4);
        int max = Math.max(i3, i4);
        int i5 = this.mediaSourceHolders.get(min).firstWindowIndexInChild;
        List<MediaSourceHolder> list = this.mediaSourceHolders;
        list.add(i4, list.remove(i3));
        while (min <= max) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(min);
            mediaSourceHolder.childIndex = min;
            mediaSourceHolder.firstWindowIndexInChild = i5;
            i5 += mediaSourceHolder.mediaSource.getTimeline().getWindowCount();
            min++;
        }
    }

    @GuardedBy("this")
    private void movePublicMediaSource(int i3, int i4, @Nullable Handler handler, @Nullable Runnable runnable) {
        boolean z2 = false;
        if ((handler == null) == (runnable == null)) {
            z2 = true;
        }
        Assertions.checkArgument(z2);
        Handler handler2 = this.playbackThreadHandler;
        List<MediaSourceHolder> list = this.mediaSourcesPublic;
        list.add(i4, list.remove(i3));
        if (handler2 != null) {
            handler2.obtainMessage(2, new MessageData(i3, Integer.valueOf(i4), createOnCompletionAction(handler, runnable))).sendToTarget();
        } else if (runnable != null && handler != null) {
            handler.post(runnable);
        }
    }

    private void removeMediaSourceInternal(int i3) {
        MediaSourceHolder remove = this.mediaSourceHolders.remove(i3);
        this.mediaSourceByUid.remove(remove.uid);
        correctOffsets(i3, -1, -remove.mediaSource.getTimeline().getWindowCount());
        remove.isRemoved = true;
        maybeReleaseChildSource(remove);
    }

    @GuardedBy("this")
    private void removePublicMediaSources(int i3, int i4, @Nullable Handler handler, @Nullable Runnable runnable) {
        boolean z2 = false;
        if ((handler == null) == (runnable == null)) {
            z2 = true;
        }
        Assertions.checkArgument(z2);
        Handler handler2 = this.playbackThreadHandler;
        Util.removeRange(this.mediaSourcesPublic, i3, i4);
        if (handler2 != null) {
            handler2.obtainMessage(1, new MessageData(i3, Integer.valueOf(i4), createOnCompletionAction(handler, runnable))).sendToTarget();
        } else if (runnable != null && handler != null) {
            handler.post(runnable);
        }
    }

    private void scheduleTimelineUpdate() {
        scheduleTimelineUpdate((HandlerAndRunnable) null);
    }

    @GuardedBy("this")
    private void setPublicShuffleOrder(ShuffleOrder shuffleOrder2, @Nullable Handler handler, @Nullable Runnable runnable) {
        boolean z2 = true;
        if ((handler == null) != (runnable == null)) {
            z2 = false;
        }
        Assertions.checkArgument(z2);
        Handler handler2 = this.playbackThreadHandler;
        if (handler2 != null) {
            int size = getSize();
            if (shuffleOrder2.getLength() != size) {
                shuffleOrder2 = shuffleOrder2.cloneAndClear().cloneAndInsert(0, size);
            }
            handler2.obtainMessage(3, new MessageData(0, shuffleOrder2, createOnCompletionAction(handler, runnable))).sendToTarget();
            return;
        }
        if (shuffleOrder2.getLength() > 0) {
            shuffleOrder2 = shuffleOrder2.cloneAndClear();
        }
        this.shuffleOrder = shuffleOrder2;
        if (runnable != null && handler != null) {
            handler.post(runnable);
        }
    }

    private void updateMediaSourceInternal(MediaSourceHolder mediaSourceHolder, Timeline timeline) {
        int windowCount;
        if (mediaSourceHolder.childIndex + 1 < this.mediaSourceHolders.size() && (windowCount = timeline.getWindowCount() - (this.mediaSourceHolders.get(mediaSourceHolder.childIndex + 1).firstWindowIndexInChild - mediaSourceHolder.firstWindowIndexInChild)) != 0) {
            correctOffsets(mediaSourceHolder.childIndex + 1, 0, windowCount);
        }
        scheduleTimelineUpdate();
    }

    private void updateTimelineAndScheduleOnCompletionActions() {
        this.timelineUpdateScheduled = false;
        Set<HandlerAndRunnable> set = this.nextTimelineUpdateOnCompletionActions;
        this.nextTimelineUpdateOnCompletionActions = new HashSet();
        refreshSourceInfo(new ConcatenatedTimeline(this.mediaSourceHolders, this.shuffleOrder, this.isAtomic));
        getPlaybackThreadHandlerOnPlaybackThread().obtainMessage(5, set).sendToTarget();
    }

    public synchronized void addMediaSource(MediaSource mediaSource) {
        addMediaSource(this.mediaSourcesPublic.size(), mediaSource);
    }

    public synchronized void addMediaSources(Collection<MediaSource> collection) {
        addPublicMediaSources(this.mediaSourcesPublic.size(), collection, (Handler) null, (Runnable) null);
    }

    public synchronized void clear() {
        removeMediaSourceRange(0, getSize());
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        Object mediaSourceHolderUid = getMediaSourceHolderUid(mediaPeriodId.periodUid);
        MediaSource.MediaPeriodId copyWithPeriodUid = mediaPeriodId.copyWithPeriodUid(getChildPeriodUid(mediaPeriodId.periodUid));
        MediaSourceHolder mediaSourceHolder = this.mediaSourceByUid.get(mediaSourceHolderUid);
        if (mediaSourceHolder == null) {
            mediaSourceHolder = new MediaSourceHolder(new FakeMediaSource(), this.useLazyPreparation);
            mediaSourceHolder.isRemoved = true;
            prepareChildSource(mediaSourceHolder, mediaSourceHolder.mediaSource);
        }
        enableMediaSource(mediaSourceHolder);
        mediaSourceHolder.activeMediaPeriodIds.add(copyWithPeriodUid);
        MaskingMediaPeriod createPeriod = mediaSourceHolder.mediaSource.createPeriod(copyWithPeriodUid, allocator, j2);
        this.mediaSourceByMediaPeriod.put(createPeriod, mediaSourceHolder);
        disableUnusedMediaSources();
        return createPeriod;
    }

    public void disableInternal() {
        super.disableInternal();
        this.enabledMediaSourceHolders.clear();
    }

    public void enableInternal() {
    }

    public synchronized Timeline getInitialTimeline() {
        try {
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return new ConcatenatedTimeline(this.mediaSourcesPublic, this.shuffleOrder.getLength() != this.mediaSourcesPublic.size() ? this.shuffleOrder.cloneAndClear().cloneAndInsert(0, this.mediaSourcesPublic.size()) : this.shuffleOrder, this.isAtomic);
    }

    public MediaItem getMediaItem() {
        return EMPTY_MEDIA_ITEM;
    }

    public synchronized MediaSource getMediaSource(int i3) {
        return this.mediaSourcesPublic.get(i3).mediaSource;
    }

    public synchronized int getSize() {
        return this.mediaSourcesPublic.size();
    }

    public boolean isSingleWindow() {
        return false;
    }

    public synchronized void moveMediaSource(int i3, int i4) {
        movePublicMediaSource(i3, i4, (Handler) null, (Runnable) null);
    }

    public synchronized void prepareSourceInternal(@Nullable TransferListener transferListener) {
        try {
            super.prepareSourceInternal(transferListener);
            this.playbackThreadHandler = new Handler(new b(this));
            if (this.mediaSourcesPublic.isEmpty()) {
                updateTimelineAndScheduleOnCompletionActions();
            } else {
                this.shuffleOrder = this.shuffleOrder.cloneAndInsert(0, this.mediaSourcesPublic.size());
                addMediaSourcesInternal(0, this.mediaSourcesPublic);
                scheduleTimelineUpdate();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
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

    public synchronized void releaseSourceInternal() {
        try {
            super.releaseSourceInternal();
            this.mediaSourceHolders.clear();
            this.enabledMediaSourceHolders.clear();
            this.mediaSourceByUid.clear();
            this.shuffleOrder = this.shuffleOrder.cloneAndClear();
            Handler handler = this.playbackThreadHandler;
            if (handler != null) {
                handler.removeCallbacksAndMessages((Object) null);
                this.playbackThreadHandler = null;
            }
            this.timelineUpdateScheduled = false;
            this.nextTimelineUpdateOnCompletionActions.clear();
            dispatchOnCompletionActions(this.pendingOnCompletionActions);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized MediaSource removeMediaSource(int i3) {
        MediaSource mediaSource;
        mediaSource = getMediaSource(i3);
        removePublicMediaSources(i3, i3 + 1, (Handler) null, (Runnable) null);
        return mediaSource;
    }

    public synchronized void removeMediaSourceRange(int i3, int i4) {
        removePublicMediaSources(i3, i4, (Handler) null, (Runnable) null);
    }

    public synchronized void setShuffleOrder(ShuffleOrder shuffleOrder2) {
        setPublicShuffleOrder(shuffleOrder2, (Handler) null, (Runnable) null);
    }

    public ConcatenatingMediaSource(boolean z2, MediaSource... mediaSourceArr) {
        this(z2, new ShuffleOrder.DefaultShuffleOrder(0), mediaSourceArr);
    }

    private void scheduleTimelineUpdate(@Nullable HandlerAndRunnable handlerAndRunnable) {
        if (!this.timelineUpdateScheduled) {
            getPlaybackThreadHandlerOnPlaybackThread().obtainMessage(4).sendToTarget();
            this.timelineUpdateScheduled = true;
        }
        if (handlerAndRunnable != null) {
            this.nextTimelineUpdateOnCompletionActions.add(handlerAndRunnable);
        }
    }

    @Nullable
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSourceHolder mediaSourceHolder, MediaSource.MediaPeriodId mediaPeriodId) {
        for (int i3 = 0; i3 < mediaSourceHolder.activeMediaPeriodIds.size(); i3++) {
            if (mediaSourceHolder.activeMediaPeriodIds.get(i3).windowSequenceNumber == mediaPeriodId.windowSequenceNumber) {
                return mediaPeriodId.copyWithPeriodUid(getPeriodUid(mediaSourceHolder, mediaPeriodId.periodUid));
            }
        }
        return null;
    }

    public int getWindowIndexForChildWindowIndex(MediaSourceHolder mediaSourceHolder, int i3) {
        return i3 + mediaSourceHolder.firstWindowIndexInChild;
    }

    public void onChildSourceInfoRefreshed(MediaSourceHolder mediaSourceHolder, MediaSource mediaSource, Timeline timeline) {
        updateMediaSourceInternal(mediaSourceHolder, timeline);
    }

    public ConcatenatingMediaSource(boolean z2, ShuffleOrder shuffleOrder2, MediaSource... mediaSourceArr) {
        this(z2, false, shuffleOrder2, mediaSourceArr);
    }

    public synchronized void addMediaSource(MediaSource mediaSource, Handler handler, Runnable runnable) {
        addMediaSource(this.mediaSourcesPublic.size(), mediaSource, handler, runnable);
    }

    public synchronized void clear(Handler handler, Runnable runnable) {
        removeMediaSourceRange(0, getSize(), handler, runnable);
    }

    public synchronized void moveMediaSource(int i3, int i4, Handler handler, Runnable runnable) {
        movePublicMediaSource(i3, i4, handler, runnable);
    }

    public synchronized void removeMediaSourceRange(int i3, int i4, Handler handler, Runnable runnable) {
        removePublicMediaSources(i3, i4, handler, runnable);
    }

    public synchronized void setShuffleOrder(ShuffleOrder shuffleOrder2, Handler handler, Runnable runnable) {
        setPublicShuffleOrder(shuffleOrder2, handler, runnable);
    }

    public ConcatenatingMediaSource(boolean z2, boolean z3, ShuffleOrder shuffleOrder2, MediaSource... mediaSourceArr) {
        for (MediaSource checkNotNull : mediaSourceArr) {
            Assertions.checkNotNull(checkNotNull);
        }
        this.shuffleOrder = shuffleOrder2.getLength() > 0 ? shuffleOrder2.cloneAndClear() : shuffleOrder2;
        this.mediaSourceByMediaPeriod = new IdentityHashMap<>();
        this.mediaSourceByUid = new HashMap();
        this.mediaSourcesPublic = new ArrayList();
        this.mediaSourceHolders = new ArrayList();
        this.nextTimelineUpdateOnCompletionActions = new HashSet();
        this.pendingOnCompletionActions = new HashSet();
        this.enabledMediaSourceHolders = new HashSet();
        this.isAtomic = z2;
        this.useLazyPreparation = z3;
        addMediaSources(Arrays.asList(mediaSourceArr));
    }

    public synchronized MediaSource removeMediaSource(int i3, Handler handler, Runnable runnable) {
        MediaSource mediaSource;
        mediaSource = getMediaSource(i3);
        removePublicMediaSources(i3, i3 + 1, handler, runnable);
        return mediaSource;
    }

    public synchronized void addMediaSource(int i3, MediaSource mediaSource) {
        addPublicMediaSources(i3, Collections.singletonList(mediaSource), (Handler) null, (Runnable) null);
    }

    public synchronized void addMediaSources(Collection<MediaSource> collection, Handler handler, Runnable runnable) {
        addPublicMediaSources(this.mediaSourcesPublic.size(), collection, handler, runnable);
    }

    public synchronized void addMediaSources(int i3, Collection<MediaSource> collection) {
        addPublicMediaSources(i3, collection, (Handler) null, (Runnable) null);
    }

    public synchronized void addMediaSource(int i3, MediaSource mediaSource, Handler handler, Runnable runnable) {
        addPublicMediaSources(i3, Collections.singletonList(mediaSource), handler, runnable);
    }

    public synchronized void addMediaSources(int i3, Collection<MediaSource> collection, Handler handler, Runnable runnable) {
        addPublicMediaSources(i3, collection, handler, runnable);
    }
}
