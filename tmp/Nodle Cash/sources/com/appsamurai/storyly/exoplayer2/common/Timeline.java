package com.appsamurai.storyly.exoplayer2.common;

import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.source.ads.AdPlaybackState;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.BundleUtil;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.InlineMe;
import java.util.ArrayList;
import jnr.ffi.provider.jffi.JNINativeInterface;

public abstract class Timeline implements Bundleable {
    public static final Bundleable.Creator<Timeline> CREATOR = new a(10);
    public static final Timeline EMPTY = new Timeline() {
        public int getIndexOfPeriod(Object obj) {
            return -1;
        }

        public Period getPeriod(int i3, Period period, boolean z2) {
            throw new IndexOutOfBoundsException();
        }

        public int getPeriodCount() {
            return 0;
        }

        public Object getUidOfPeriod(int i3) {
            throw new IndexOutOfBoundsException();
        }

        public Window getWindow(int i3, Window window, long j2) {
            throw new IndexOutOfBoundsException();
        }

        public int getWindowCount() {
            return 0;
        }
    };
    private static final int FIELD_PERIODS = 1;
    private static final int FIELD_SHUFFLED_WINDOW_INDICES = 2;
    private static final int FIELD_WINDOWS = 0;

    public static final class Period implements Bundleable {
        public static final Bundleable.Creator<Period> CREATOR = new a(11);
        private static final int FIELD_AD_PLAYBACK_STATE = 4;
        private static final int FIELD_DURATION_US = 1;
        private static final int FIELD_PLACEHOLDER = 3;
        private static final int FIELD_POSITION_IN_WINDOW_US = 2;
        private static final int FIELD_WINDOW_INDEX = 0;
        /* access modifiers changed from: private */
        public AdPlaybackState adPlaybackState = AdPlaybackState.NONE;
        public long durationUs;
        @Nullable
        public Object id;
        public boolean isPlaceholder;
        public long positionInWindowUs;
        @Nullable
        public Object uid;
        public int windowIndex;

        /* access modifiers changed from: private */
        public static Period fromBundle(Bundle bundle) {
            int i3 = bundle.getInt(keyForField(0), 0);
            long j2 = bundle.getLong(keyForField(1), C.TIME_UNSET);
            long j3 = bundle.getLong(keyForField(2), 0);
            boolean z2 = bundle.getBoolean(keyForField(3));
            Bundle bundle2 = bundle.getBundle(keyForField(4));
            AdPlaybackState fromBundle = bundle2 != null ? AdPlaybackState.CREATOR.fromBundle(bundle2) : AdPlaybackState.NONE;
            Period period = new Period();
            period.set((Object) null, (Object) null, i3, j2, j3, fromBundle, z2);
            return period;
        }

        private static String keyForField(int i3) {
            return Integer.toString(i3, 36);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null) {
                if (Period.class.equals(obj.getClass())) {
                    Period period = (Period) obj;
                    return Util.areEqual(this.id, period.id) && Util.areEqual(this.uid, period.uid) && this.windowIndex == period.windowIndex && this.durationUs == period.durationUs && this.positionInWindowUs == period.positionInWindowUs && this.isPlaceholder == period.isPlaceholder && Util.areEqual(this.adPlaybackState, period.adPlaybackState);
                }
            }
            return false;
        }

        public int getAdCountInAdGroup(int i3) {
            return this.adPlaybackState.getAdGroup(i3).count;
        }

        public long getAdDurationUs(int i3, int i4) {
            AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(i3);
            return adGroup.count != -1 ? adGroup.durationsUs[i4] : C.TIME_UNSET;
        }

        public int getAdGroupCount() {
            return this.adPlaybackState.adGroupCount;
        }

        public int getAdGroupIndexAfterPositionUs(long j2) {
            return this.adPlaybackState.getAdGroupIndexAfterPositionUs(j2, this.durationUs);
        }

        public int getAdGroupIndexForPositionUs(long j2) {
            return this.adPlaybackState.getAdGroupIndexForPositionUs(j2, this.durationUs);
        }

        public long getAdGroupTimeUs(int i3) {
            return this.adPlaybackState.getAdGroup(i3).timeUs;
        }

        public long getAdResumePositionUs() {
            return this.adPlaybackState.adResumePositionUs;
        }

        public int getAdState(int i3, int i4) {
            AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(i3);
            if (adGroup.count != -1) {
                return adGroup.states[i4];
            }
            return 0;
        }

        @Nullable
        public Object getAdsId() {
            return this.adPlaybackState.adsId;
        }

        public long getContentResumeOffsetUs(int i3) {
            return this.adPlaybackState.getAdGroup(i3).contentResumeOffsetUs;
        }

        public long getDurationMs() {
            return Util.usToMs(this.durationUs);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public int getFirstAdIndexToPlay(int i3) {
            return this.adPlaybackState.getAdGroup(i3).getFirstAdIndexToPlay();
        }

        public int getNextAdIndexToPlay(int i3, int i4) {
            return this.adPlaybackState.getAdGroup(i3).getNextAdIndexToPlay(i4);
        }

        public long getPositionInWindowMs() {
            return Util.usToMs(this.positionInWindowUs);
        }

        public long getPositionInWindowUs() {
            return this.positionInWindowUs;
        }

        public int getRemovedAdGroupCount() {
            return this.adPlaybackState.removedAdGroupCount;
        }

        public boolean hasPlayedAdGroup(int i3) {
            return !this.adPlaybackState.getAdGroup(i3).hasUnplayedAds();
        }

        public int hashCode() {
            Object obj = this.id;
            int i3 = 0;
            int hashCode = (JNINativeInterface.MonitorEnter + (obj == null ? 0 : obj.hashCode())) * 31;
            Object obj2 = this.uid;
            if (obj2 != null) {
                i3 = obj2.hashCode();
            }
            long j2 = this.durationUs;
            long j3 = this.positionInWindowUs;
            return this.adPlaybackState.hashCode() + ((((((((((hashCode + i3) * 31) + this.windowIndex) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + (this.isPlaceholder ? 1 : 0)) * 31);
        }

        public boolean isServerSideInsertedAdGroup(int i3) {
            return this.adPlaybackState.getAdGroup(i3).isServerSideInserted;
        }

        public Period set(@Nullable Object obj, @Nullable Object obj2, int i3, long j2, long j3) {
            return set(obj, obj2, i3, j2, j3, AdPlaybackState.NONE, false);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putInt(keyForField(0), this.windowIndex);
            bundle.putLong(keyForField(1), this.durationUs);
            bundle.putLong(keyForField(2), this.positionInWindowUs);
            bundle.putBoolean(keyForField(3), this.isPlaceholder);
            bundle.putBundle(keyForField(4), this.adPlaybackState.toBundle());
            return bundle;
        }

        public Period set(@Nullable Object obj, @Nullable Object obj2, int i3, long j2, long j3, AdPlaybackState adPlaybackState2, boolean z2) {
            this.id = obj;
            this.uid = obj2;
            this.windowIndex = i3;
            this.durationUs = j2;
            this.positionInWindowUs = j3;
            this.adPlaybackState = adPlaybackState2;
            this.isPlaceholder = z2;
            return this;
        }
    }

    public static final class RemotableTimeline extends Timeline {
        private final ImmutableList<Period> periods;
        private final int[] shuffledWindowIndices;
        private final int[] windowIndicesInShuffled;
        private final ImmutableList<Window> windows;

        public RemotableTimeline(ImmutableList<Window> immutableList, ImmutableList<Period> immutableList2, int[] iArr) {
            Assertions.checkArgument(immutableList.size() == iArr.length);
            this.windows = immutableList;
            this.periods = immutableList2;
            this.shuffledWindowIndices = iArr;
            this.windowIndicesInShuffled = new int[iArr.length];
            for (int i3 = 0; i3 < iArr.length; i3++) {
                this.windowIndicesInShuffled[iArr[i3]] = i3;
            }
        }

        public int getFirstWindowIndex(boolean z2) {
            if (isEmpty()) {
                return -1;
            }
            if (z2) {
                return this.shuffledWindowIndices[0];
            }
            return 0;
        }

        public int getIndexOfPeriod(Object obj) {
            throw new UnsupportedOperationException();
        }

        public int getLastWindowIndex(boolean z2) {
            if (isEmpty()) {
                return -1;
            }
            return z2 ? this.shuffledWindowIndices[getWindowCount() - 1] : getWindowCount() - 1;
        }

        public int getNextWindowIndex(int i3, int i4, boolean z2) {
            if (i4 == 1) {
                return i3;
            }
            if (i3 != getLastWindowIndex(z2)) {
                return z2 ? this.shuffledWindowIndices[this.windowIndicesInShuffled[i3] + 1] : i3 + 1;
            }
            if (i4 == 2) {
                return getFirstWindowIndex(z2);
            }
            return -1;
        }

        public Period getPeriod(int i3, Period period, boolean z2) {
            Period period2 = this.periods.get(i3);
            period.set(period2.id, period2.uid, period2.windowIndex, period2.durationUs, period2.positionInWindowUs, period2.adPlaybackState, period2.isPlaceholder);
            return period;
        }

        public int getPeriodCount() {
            return this.periods.size();
        }

        public int getPreviousWindowIndex(int i3, int i4, boolean z2) {
            if (i4 == 1) {
                return i3;
            }
            if (i3 != getFirstWindowIndex(z2)) {
                return z2 ? this.shuffledWindowIndices[this.windowIndicesInShuffled[i3] - 1] : i3 - 1;
            }
            if (i4 == 2) {
                return getLastWindowIndex(z2);
            }
            return -1;
        }

        public Object getUidOfPeriod(int i3) {
            throw new UnsupportedOperationException();
        }

        public Window getWindow(int i3, Window window, long j2) {
            Window window2 = window;
            Window window3 = this.windows.get(i3);
            Object obj = window3.uid;
            MediaItem mediaItem = window3.mediaItem;
            Window window4 = window3;
            long j3 = window4.durationUs;
            int i4 = window4.firstPeriodIndex;
            int i5 = window4.lastPeriodIndex;
            long j4 = window4.positionInFirstPeriodUs;
            Window window5 = window4;
            Window window6 = window;
            window6.set(obj, mediaItem, window3.manifest, window3.presentationStartTimeMs, window3.windowStartTimeMs, window3.elapsedRealtimeEpochOffsetMs, window3.isSeekable, window3.isDynamic, window3.liveConfiguration, window3.defaultPositionUs, j3, i4, i5, j4);
            Window window7 = window;
            window7.isPlaceholder = window5.isPlaceholder;
            return window7;
        }

        public int getWindowCount() {
            return this.windows.size();
        }
    }

    /* access modifiers changed from: private */
    public static Timeline fromBundle(Bundle bundle) {
        ImmutableList<Window> fromBundleListRetriever = fromBundleListRetriever(Window.CREATOR, BundleUtil.getBinder(bundle, keyForField(0)));
        ImmutableList<Period> fromBundleListRetriever2 = fromBundleListRetriever(Period.CREATOR, BundleUtil.getBinder(bundle, keyForField(1)));
        int[] intArray = bundle.getIntArray(keyForField(2));
        if (intArray == null) {
            intArray = generateUnshuffledIndices(fromBundleListRetriever.size());
        }
        return new RemotableTimeline(fromBundleListRetriever, fromBundleListRetriever2, intArray);
    }

    private static <T extends Bundleable> ImmutableList<T> fromBundleListRetriever(Bundleable.Creator<T> creator, @Nullable IBinder iBinder) {
        if (iBinder == null) {
            return ImmutableList.of();
        }
        ImmutableList.Builder builder = new ImmutableList.Builder();
        ImmutableList<Bundle> list = BundleListRetriever.getList(iBinder);
        for (int i3 = 0; i3 < list.size(); i3++) {
            builder.add((Object) creator.fromBundle(list.get(i3)));
        }
        return builder.build();
    }

    private static int[] generateUnshuffledIndices(int i3) {
        int[] iArr = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i4] = i4;
        }
        return iArr;
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Timeline)) {
            return false;
        }
        Timeline timeline = (Timeline) obj;
        if (timeline.getWindowCount() != getWindowCount() || timeline.getPeriodCount() != getPeriodCount()) {
            return false;
        }
        Window window = new Window();
        Period period = new Period();
        Window window2 = new Window();
        Period period2 = new Period();
        for (int i3 = 0; i3 < getWindowCount(); i3++) {
            if (!getWindow(i3, window).equals(timeline.getWindow(i3, window2))) {
                return false;
            }
        }
        for (int i4 = 0; i4 < getPeriodCount(); i4++) {
            if (!getPeriod(i4, period, true).equals(timeline.getPeriod(i4, period2, true))) {
                return false;
            }
        }
        return true;
    }

    public int getFirstWindowIndex(boolean z2) {
        return isEmpty() ? -1 : 0;
    }

    public abstract int getIndexOfPeriod(Object obj);

    public int getLastWindowIndex(boolean z2) {
        if (isEmpty()) {
            return -1;
        }
        return getWindowCount() - 1;
    }

    public final int getNextPeriodIndex(int i3, Period period, Window window, int i4, boolean z2) {
        int i5 = getPeriod(i3, period).windowIndex;
        if (getWindow(i5, window).lastPeriodIndex != i3) {
            return i3 + 1;
        }
        int nextWindowIndex = getNextWindowIndex(i5, i4, z2);
        if (nextWindowIndex == -1) {
            return -1;
        }
        return getWindow(nextWindowIndex, window).firstPeriodIndex;
    }

    public int getNextWindowIndex(int i3, int i4, boolean z2) {
        if (i4 != 0) {
            if (i4 == 1) {
                return i3;
            }
            if (i4 == 2) {
                return i3 == getLastWindowIndex(z2) ? getFirstWindowIndex(z2) : i3 + 1;
            }
            throw new IllegalStateException();
        } else if (i3 == getLastWindowIndex(z2)) {
            return -1;
        } else {
            return i3 + 1;
        }
    }

    public final Period getPeriod(int i3, Period period) {
        return getPeriod(i3, period, false);
    }

    public abstract Period getPeriod(int i3, Period period, boolean z2);

    public Period getPeriodByUid(Object obj, Period period) {
        return getPeriod(getIndexOfPeriod(obj), period, true);
    }

    public abstract int getPeriodCount();

    @InlineMe(replacement = "this.getPeriodPositionUs(window, period, windowIndex, windowPositionUs)")
    @Deprecated
    public final Pair<Object, Long> getPeriodPosition(Window window, Period period, int i3, long j2) {
        return getPeriodPositionUs(window, period, i3, j2);
    }

    public final Pair<Object, Long> getPeriodPositionUs(Window window, Period period, int i3, long j2) {
        return (Pair) Assertions.checkNotNull(getPeriodPositionUs(window, period, i3, j2, 0));
    }

    public int getPreviousWindowIndex(int i3, int i4, boolean z2) {
        if (i4 != 0) {
            if (i4 == 1) {
                return i3;
            }
            if (i4 == 2) {
                return i3 == getFirstWindowIndex(z2) ? getLastWindowIndex(z2) : i3 - 1;
            }
            throw new IllegalStateException();
        } else if (i3 == getFirstWindowIndex(z2)) {
            return -1;
        } else {
            return i3 - 1;
        }
    }

    public abstract Object getUidOfPeriod(int i3);

    public final Window getWindow(int i3, Window window) {
        return getWindow(i3, window, 0);
    }

    public abstract Window getWindow(int i3, Window window, long j2);

    public abstract int getWindowCount();

    public int hashCode() {
        Window window = new Window();
        Period period = new Period();
        int windowCount = getWindowCount() + JNINativeInterface.MonitorEnter;
        for (int i3 = 0; i3 < getWindowCount(); i3++) {
            windowCount = (windowCount * 31) + getWindow(i3, window).hashCode();
        }
        int periodCount = getPeriodCount() + (windowCount * 31);
        for (int i4 = 0; i4 < getPeriodCount(); i4++) {
            periodCount = (periodCount * 31) + getPeriod(i4, period, true).hashCode();
        }
        return periodCount;
    }

    public final boolean isEmpty() {
        return getWindowCount() == 0;
    }

    public final boolean isLastPeriod(int i3, Period period, Window window, int i4, boolean z2) {
        return getNextPeriodIndex(i3, period, window, i4, z2) == -1;
    }

    public final Bundle toBundle(boolean z2) {
        ArrayList arrayList = new ArrayList();
        int windowCount = getWindowCount();
        Window window = new Window();
        for (int i3 = 0; i3 < windowCount; i3++) {
            arrayList.add(getWindow(i3, window, 0).toBundle(z2));
        }
        ArrayList arrayList2 = new ArrayList();
        int periodCount = getPeriodCount();
        Period period = new Period();
        for (int i4 = 0; i4 < periodCount; i4++) {
            arrayList2.add(getPeriod(i4, period, false).toBundle());
        }
        int[] iArr = new int[windowCount];
        if (windowCount > 0) {
            iArr[0] = getFirstWindowIndex(true);
        }
        for (int i5 = 1; i5 < windowCount; i5++) {
            iArr[i5] = getNextWindowIndex(iArr[i5 - 1], 0, true);
        }
        Bundle bundle = new Bundle();
        BundleUtil.putBinder(bundle, keyForField(0), new BundleListRetriever(arrayList));
        BundleUtil.putBinder(bundle, keyForField(1), new BundleListRetriever(arrayList2));
        bundle.putIntArray(keyForField(2), iArr);
        return bundle;
    }

    @InlineMe(replacement = "this.getPeriodPositionUs(window, period, windowIndex, windowPositionUs, defaultPositionProjectionUs)")
    @Nullable
    @Deprecated
    public final Pair<Object, Long> getPeriodPosition(Window window, Period period, int i3, long j2, long j3) {
        return getPeriodPositionUs(window, period, i3, j2, j3);
    }

    @Nullable
    public final Pair<Object, Long> getPeriodPositionUs(Window window, Period period, int i3, long j2, long j3) {
        Assertions.checkIndex(i3, 0, getWindowCount());
        getWindow(i3, window, j3);
        if (j2 == C.TIME_UNSET) {
            j2 = window.getDefaultPositionUs();
            if (j2 == C.TIME_UNSET) {
                return null;
            }
        }
        int i4 = window.firstPeriodIndex;
        getPeriod(i4, period);
        while (i4 < window.lastPeriodIndex && period.positionInWindowUs != j2) {
            int i5 = i4 + 1;
            if (getPeriod(i5, period).positionInWindowUs > j2) {
                break;
            }
            i4 = i5;
        }
        getPeriod(i4, period, true);
        long j4 = j2 - period.positionInWindowUs;
        long j5 = period.durationUs;
        if (j5 != C.TIME_UNSET) {
            j4 = Math.min(j4, j5 - 1);
        }
        return Pair.create(Assertions.checkNotNull(period.uid), Long.valueOf(Math.max(0, j4)));
    }

    public static final class Window implements Bundleable {
        public static final Bundleable.Creator<Window> CREATOR = new a(12);
        private static final MediaItem EMPTY_MEDIA_ITEM = new MediaItem.Builder().setMediaId("com.google.android.exoplayer2.Timeline").setUri(Uri.EMPTY).build();
        private static final Object FAKE_WINDOW_UID = new Object();
        private static final int FIELD_DEFAULT_POSITION_US = 9;
        private static final int FIELD_DURATION_US = 10;
        private static final int FIELD_ELAPSED_REALTIME_EPOCH_OFFSET_MS = 4;
        private static final int FIELD_FIRST_PERIOD_INDEX = 11;
        private static final int FIELD_IS_DYNAMIC = 6;
        private static final int FIELD_IS_PLACEHOLDER = 8;
        private static final int FIELD_IS_SEEKABLE = 5;
        private static final int FIELD_LAST_PERIOD_INDEX = 12;
        private static final int FIELD_LIVE_CONFIGURATION = 7;
        private static final int FIELD_MEDIA_ITEM = 1;
        private static final int FIELD_POSITION_IN_FIRST_PERIOD_US = 13;
        private static final int FIELD_PRESENTATION_START_TIME_MS = 2;
        private static final int FIELD_WINDOW_START_TIME_MS = 3;
        public static final Object SINGLE_WINDOW_UID = new Object();
        public long defaultPositionUs;
        public long durationUs;
        public long elapsedRealtimeEpochOffsetMs;
        public int firstPeriodIndex;
        public boolean isDynamic;
        @Deprecated
        public boolean isLive;
        public boolean isPlaceholder;
        public boolean isSeekable;
        public int lastPeriodIndex;
        @Nullable
        public MediaItem.LiveConfiguration liveConfiguration;
        @Nullable
        public Object manifest;
        public MediaItem mediaItem = EMPTY_MEDIA_ITEM;
        public long positionInFirstPeriodUs;
        public long presentationStartTimeMs;
        @Deprecated
        @Nullable
        public Object tag;
        public Object uid = SINGLE_WINDOW_UID;
        public long windowStartTimeMs;

        /* access modifiers changed from: private */
        public static Window fromBundle(Bundle bundle) {
            Bundle bundle2 = bundle;
            Bundle bundle3 = bundle2.getBundle(keyForField(1));
            MediaItem.LiveConfiguration liveConfiguration2 = null;
            MediaItem fromBundle = bundle3 != null ? MediaItem.CREATOR.fromBundle(bundle3) : null;
            long j2 = bundle2.getLong(keyForField(2), C.TIME_UNSET);
            long j3 = bundle2.getLong(keyForField(3), C.TIME_UNSET);
            long j4 = bundle2.getLong(keyForField(4), C.TIME_UNSET);
            boolean z2 = bundle2.getBoolean(keyForField(5), false);
            boolean z3 = bundle2.getBoolean(keyForField(6), false);
            Bundle bundle4 = bundle2.getBundle(keyForField(7));
            if (bundle4 != null) {
                liveConfiguration2 = MediaItem.LiveConfiguration.CREATOR.fromBundle(bundle4);
            }
            boolean z4 = bundle2.getBoolean(keyForField(8), false);
            long j5 = bundle2.getLong(keyForField(9), 0);
            long j6 = bundle2.getLong(keyForField(10), C.TIME_UNSET);
            int i3 = bundle2.getInt(keyForField(11), 0);
            int i4 = bundle2.getInt(keyForField(12), 0);
            long j7 = bundle2.getLong(keyForField(13), 0);
            Window window = r0;
            Window window2 = new Window();
            window.set(FAKE_WINDOW_UID, fromBundle, (Object) null, j2, j3, j4, z2, z3, liveConfiguration2, j5, j6, i3, i4, j7);
            window2.isPlaceholder = z4;
            return window2;
        }

        private static String keyForField(int i3) {
            return Integer.toString(i3, 36);
        }

        /* access modifiers changed from: private */
        public final Bundle toBundle(boolean z2) {
            Bundle bundle = new Bundle();
            bundle.putBundle(keyForField(1), (z2 ? MediaItem.EMPTY : this.mediaItem).toBundle());
            bundle.putLong(keyForField(2), this.presentationStartTimeMs);
            bundle.putLong(keyForField(3), this.windowStartTimeMs);
            bundle.putLong(keyForField(4), this.elapsedRealtimeEpochOffsetMs);
            bundle.putBoolean(keyForField(5), this.isSeekable);
            bundle.putBoolean(keyForField(6), this.isDynamic);
            MediaItem.LiveConfiguration liveConfiguration2 = this.liveConfiguration;
            if (liveConfiguration2 != null) {
                bundle.putBundle(keyForField(7), liveConfiguration2.toBundle());
            }
            bundle.putBoolean(keyForField(8), this.isPlaceholder);
            bundle.putLong(keyForField(9), this.defaultPositionUs);
            bundle.putLong(keyForField(10), this.durationUs);
            bundle.putInt(keyForField(11), this.firstPeriodIndex);
            bundle.putInt(keyForField(12), this.lastPeriodIndex);
            bundle.putLong(keyForField(13), this.positionInFirstPeriodUs);
            return bundle;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null) {
                if (Window.class.equals(obj.getClass())) {
                    Window window = (Window) obj;
                    return Util.areEqual(this.uid, window.uid) && Util.areEqual(this.mediaItem, window.mediaItem) && Util.areEqual(this.manifest, window.manifest) && Util.areEqual(this.liveConfiguration, window.liveConfiguration) && this.presentationStartTimeMs == window.presentationStartTimeMs && this.windowStartTimeMs == window.windowStartTimeMs && this.elapsedRealtimeEpochOffsetMs == window.elapsedRealtimeEpochOffsetMs && this.isSeekable == window.isSeekable && this.isDynamic == window.isDynamic && this.isPlaceholder == window.isPlaceholder && this.defaultPositionUs == window.defaultPositionUs && this.durationUs == window.durationUs && this.firstPeriodIndex == window.firstPeriodIndex && this.lastPeriodIndex == window.lastPeriodIndex && this.positionInFirstPeriodUs == window.positionInFirstPeriodUs;
                }
            }
            return false;
        }

        public long getCurrentUnixTimeMs() {
            return Util.getNowUnixTimeMs(this.elapsedRealtimeEpochOffsetMs);
        }

        public long getDefaultPositionMs() {
            return Util.usToMs(this.defaultPositionUs);
        }

        public long getDefaultPositionUs() {
            return this.defaultPositionUs;
        }

        public long getDurationMs() {
            return Util.usToMs(this.durationUs);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public long getPositionInFirstPeriodMs() {
            return Util.usToMs(this.positionInFirstPeriodUs);
        }

        public long getPositionInFirstPeriodUs() {
            return this.positionInFirstPeriodUs;
        }

        public int hashCode() {
            int hashCode = (this.mediaItem.hashCode() + ((this.uid.hashCode() + JNINativeInterface.MonitorEnter) * 31)) * 31;
            Object obj = this.manifest;
            int i3 = 0;
            int hashCode2 = (hashCode + (obj == null ? 0 : obj.hashCode())) * 31;
            MediaItem.LiveConfiguration liveConfiguration2 = this.liveConfiguration;
            if (liveConfiguration2 != null) {
                i3 = liveConfiguration2.hashCode();
            }
            long j2 = this.presentationStartTimeMs;
            long j3 = this.windowStartTimeMs;
            long j4 = this.elapsedRealtimeEpochOffsetMs;
            long j5 = this.defaultPositionUs;
            long j6 = this.durationUs;
            long j7 = this.positionInFirstPeriodUs;
            return ((((((((((((((((((((((hashCode2 + i3) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) (j4 ^ (j4 >>> 32)))) * 31) + (this.isSeekable ? 1 : 0)) * 31) + (this.isDynamic ? 1 : 0)) * 31) + (this.isPlaceholder ? 1 : 0)) * 31) + ((int) (j5 ^ (j5 >>> 32)))) * 31) + ((int) (j6 ^ (j6 >>> 32)))) * 31) + this.firstPeriodIndex) * 31) + this.lastPeriodIndex) * 31) + ((int) (j7 ^ (j7 >>> 32)));
        }

        public boolean isLive() {
            Assertions.checkState(this.isLive == (this.liveConfiguration != null));
            return this.liveConfiguration != null;
        }

        public Window set(Object obj, @Nullable MediaItem mediaItem2, @Nullable Object obj2, long j2, long j3, long j4, boolean z2, boolean z3, @Nullable MediaItem.LiveConfiguration liveConfiguration2, long j5, long j6, int i3, int i4, long j7) {
            MediaItem.LocalConfiguration localConfiguration;
            MediaItem mediaItem3 = mediaItem2;
            MediaItem.LiveConfiguration liveConfiguration3 = liveConfiguration2;
            this.uid = obj;
            this.mediaItem = mediaItem3 != null ? mediaItem3 : EMPTY_MEDIA_ITEM;
            this.tag = (mediaItem3 == null || (localConfiguration = mediaItem3.localConfiguration) == null) ? null : localConfiguration.tag;
            this.manifest = obj2;
            this.presentationStartTimeMs = j2;
            this.windowStartTimeMs = j3;
            this.elapsedRealtimeEpochOffsetMs = j4;
            this.isSeekable = z2;
            this.isDynamic = z3;
            this.isLive = liveConfiguration3 != null;
            this.liveConfiguration = liveConfiguration3;
            this.defaultPositionUs = j5;
            this.durationUs = j6;
            this.firstPeriodIndex = i3;
            this.lastPeriodIndex = i4;
            this.positionInFirstPeriodUs = j7;
            this.isPlaceholder = false;
            return this;
        }

        public Bundle toBundle() {
            return toBundle(false);
        }
    }

    public final Bundle toBundle() {
        return toBundle(false);
    }
}
