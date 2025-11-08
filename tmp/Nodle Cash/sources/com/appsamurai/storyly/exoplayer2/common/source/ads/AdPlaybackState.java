package com.appsamurai.storyly.exoplayer2.common.source.ads;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.CheckResult;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.a;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;

public final class AdPlaybackState implements Bundleable {
    public static final int AD_STATE_AVAILABLE = 1;
    public static final int AD_STATE_ERROR = 4;
    public static final int AD_STATE_PLAYED = 3;
    public static final int AD_STATE_SKIPPED = 2;
    public static final int AD_STATE_UNAVAILABLE = 0;
    public static final Bundleable.Creator<AdPlaybackState> CREATOR = new a(17);
    private static final int FIELD_AD_GROUPS = 1;
    private static final int FIELD_AD_RESUME_POSITION_US = 2;
    private static final int FIELD_CONTENT_DURATION_US = 3;
    private static final int FIELD_REMOVED_AD_GROUP_COUNT = 4;
    public static final AdPlaybackState NONE = new AdPlaybackState((Object) null, new AdGroup[0], 0, C.TIME_UNSET, 0);
    private static final AdGroup REMOVED_AD_GROUP = new AdGroup(0).withAdCount(0);
    public final int adGroupCount;
    private final AdGroup[] adGroups;
    public final long adResumePositionUs;
    @Nullable
    public final Object adsId;
    public final long contentDurationUs;
    public final int removedAdGroupCount;

    public static final class AdGroup implements Bundleable {
        public static final Bundleable.Creator<AdGroup> CREATOR = new a(18);
        private static final int FIELD_CONTENT_RESUME_OFFSET_US = 5;
        private static final int FIELD_COUNT = 1;
        private static final int FIELD_DURATIONS_US = 4;
        private static final int FIELD_IS_SERVER_SIDE_INSERTED = 6;
        private static final int FIELD_STATES = 3;
        private static final int FIELD_TIME_US = 0;
        private static final int FIELD_URIS = 2;
        public final long contentResumeOffsetUs;
        public final int count;
        public final long[] durationsUs;
        public final boolean isServerSideInserted;
        public final int[] states;
        public final long timeUs;
        public final Uri[] uris;

        @CheckResult
        private static long[] copyDurationsUsWithSpaceForAdCount(long[] jArr, int i3) {
            int length = jArr.length;
            int max = Math.max(i3, length);
            long[] copyOf = Arrays.copyOf(jArr, max);
            Arrays.fill(copyOf, length, max, C.TIME_UNSET);
            return copyOf;
        }

        @CheckResult
        private static int[] copyStatesWithSpaceForAdCount(int[] iArr, int i3) {
            int length = iArr.length;
            int max = Math.max(i3, length);
            int[] copyOf = Arrays.copyOf(iArr, max);
            Arrays.fill(copyOf, length, max, 0);
            return copyOf;
        }

        /* access modifiers changed from: private */
        public static AdGroup fromBundle(Bundle bundle) {
            long j2 = bundle.getLong(keyForField(0));
            int i3 = bundle.getInt(keyForField(1), -1);
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(keyForField(2));
            int[] intArray = bundle.getIntArray(keyForField(3));
            long[] longArray = bundle.getLongArray(keyForField(4));
            long j3 = bundle.getLong(keyForField(5));
            boolean z2 = bundle.getBoolean(keyForField(6));
            if (intArray == null) {
                intArray = new int[0];
            }
            return new AdGroup(j2, i3, intArray, parcelableArrayList == null ? new Uri[0] : (Uri[]) parcelableArrayList.toArray(new Uri[0]), longArray == null ? new long[0] : longArray, j3, z2);
        }

        private static String keyForField(int i3) {
            return Integer.toString(i3, 36);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || AdGroup.class != obj.getClass()) {
                return false;
            }
            AdGroup adGroup = (AdGroup) obj;
            return this.timeUs == adGroup.timeUs && this.count == adGroup.count && Arrays.equals(this.uris, adGroup.uris) && Arrays.equals(this.states, adGroup.states) && Arrays.equals(this.durationsUs, adGroup.durationsUs) && this.contentResumeOffsetUs == adGroup.contentResumeOffsetUs && this.isServerSideInserted == adGroup.isServerSideInserted;
        }

        public int getFirstAdIndexToPlay() {
            return getNextAdIndexToPlay(-1);
        }

        public int getNextAdIndexToPlay(@IntRange(from = -1) int i3) {
            int i4;
            int i5 = i3 + 1;
            while (true) {
                int[] iArr = this.states;
                if (i5 >= iArr.length || this.isServerSideInserted || (i4 = iArr[i5]) == 0 || i4 == 1) {
                    return i5;
                }
                i5++;
            }
            return i5;
        }

        public boolean hasUnplayedAds() {
            if (this.count == -1) {
                return true;
            }
            for (int i3 = 0; i3 < this.count; i3++) {
                int i4 = this.states[i3];
                if (i4 == 0 || i4 == 1) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            long j2 = this.timeUs;
            int hashCode = Arrays.hashCode(this.states);
            int hashCode2 = Arrays.hashCode(this.durationsUs);
            long j3 = this.contentResumeOffsetUs;
            return ((((hashCode2 + ((hashCode + (((((this.count * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + Arrays.hashCode(this.uris)) * 31)) * 31)) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + (this.isServerSideInserted ? 1 : 0);
        }

        public boolean shouldPlayAdGroup() {
            return this.count == -1 || getFirstAdIndexToPlay() < this.count;
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putLong(keyForField(0), this.timeUs);
            bundle.putInt(keyForField(1), this.count);
            bundle.putParcelableArrayList(keyForField(2), new ArrayList(Arrays.asList(this.uris)));
            bundle.putIntArray(keyForField(3), this.states);
            bundle.putLongArray(keyForField(4), this.durationsUs);
            bundle.putLong(keyForField(5), this.contentResumeOffsetUs);
            bundle.putBoolean(keyForField(6), this.isServerSideInserted);
            return bundle;
        }

        @CheckResult
        public AdGroup withAdCount(int i3) {
            int[] copyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i3);
            long[] copyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(this.durationsUs, i3);
            return new AdGroup(this.timeUs, i3, copyStatesWithSpaceForAdCount, (Uri[]) Arrays.copyOf(this.uris, i3), copyDurationsUsWithSpaceForAdCount, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        @CheckResult
        public AdGroup withAdDurationsUs(long[] jArr) {
            int length = jArr.length;
            Uri[] uriArr = this.uris;
            if (length < uriArr.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, uriArr.length);
            } else if (this.count != -1 && jArr.length > uriArr.length) {
                jArr = Arrays.copyOf(jArr, uriArr.length);
            }
            return new AdGroup(this.timeUs, this.count, this.states, this.uris, jArr, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        @CheckResult
        public AdGroup withAdState(int i3, @IntRange(from = 0) int i4) {
            int i5 = i3;
            int i6 = i4;
            int i7 = this.count;
            boolean z2 = false;
            Assertions.checkArgument(i7 == -1 || i6 < i7);
            int[] copyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i6 + 1);
            int i8 = copyStatesWithSpaceForAdCount[i6];
            if (i8 == 0 || i8 == 1 || i8 == i5) {
                z2 = true;
            }
            Assertions.checkArgument(z2);
            long[] jArr = this.durationsUs;
            if (jArr.length != copyStatesWithSpaceForAdCount.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, copyStatesWithSpaceForAdCount.length);
            }
            long[] jArr2 = jArr;
            Uri[] uriArr = this.uris;
            if (uriArr.length != copyStatesWithSpaceForAdCount.length) {
                uriArr = (Uri[]) Arrays.copyOf(uriArr, copyStatesWithSpaceForAdCount.length);
            }
            Uri[] uriArr2 = uriArr;
            copyStatesWithSpaceForAdCount[i6] = i5;
            return new AdGroup(this.timeUs, this.count, copyStatesWithSpaceForAdCount, uriArr2, jArr2, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        @CheckResult
        public AdGroup withAdUri(Uri uri, @IntRange(from = 0) int i3) {
            int[] copyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i3 + 1);
            long[] jArr = this.durationsUs;
            if (jArr.length != copyStatesWithSpaceForAdCount.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, copyStatesWithSpaceForAdCount.length);
            }
            long[] jArr2 = jArr;
            Uri[] uriArr = (Uri[]) Arrays.copyOf(this.uris, copyStatesWithSpaceForAdCount.length);
            uriArr[i3] = uri;
            copyStatesWithSpaceForAdCount[i3] = 1;
            return new AdGroup(this.timeUs, this.count, copyStatesWithSpaceForAdCount, uriArr, jArr2, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        @CheckResult
        public AdGroup withAllAdsReset() {
            if (this.count == -1) {
                return this;
            }
            int[] iArr = this.states;
            int length = iArr.length;
            int[] copyOf = Arrays.copyOf(iArr, length);
            for (int i3 = 0; i3 < length; i3++) {
                int i4 = copyOf[i3];
                if (i4 == 3 || i4 == 2 || i4 == 4) {
                    copyOf[i3] = this.uris[i3] == null ? 0 : 1;
                }
            }
            return new AdGroup(this.timeUs, length, copyOf, this.uris, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        @CheckResult
        public AdGroup withAllAdsSkipped() {
            if (this.count == -1) {
                return new AdGroup(this.timeUs, 0, new int[0], new Uri[0], new long[0], this.contentResumeOffsetUs, this.isServerSideInserted);
            }
            int[] iArr = this.states;
            int length = iArr.length;
            int[] copyOf = Arrays.copyOf(iArr, length);
            for (int i3 = 0; i3 < length; i3++) {
                int i4 = copyOf[i3];
                if (i4 == 1 || i4 == 0) {
                    copyOf[i3] = 2;
                }
            }
            return new AdGroup(this.timeUs, length, copyOf, this.uris, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        @CheckResult
        public AdGroup withContentResumeOffsetUs(long j2) {
            return new AdGroup(this.timeUs, this.count, this.states, this.uris, this.durationsUs, j2, this.isServerSideInserted);
        }

        @CheckResult
        public AdGroup withIsServerSideInserted(boolean z2) {
            return new AdGroup(this.timeUs, this.count, this.states, this.uris, this.durationsUs, this.contentResumeOffsetUs, z2);
        }

        @CheckResult
        public AdGroup withTimeUs(long j2) {
            return new AdGroup(j2, this.count, this.states, this.uris, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup(long j2) {
            this(j2, -1, new int[0], new Uri[0], new long[0], 0, false);
        }

        private AdGroup(long j2, int i3, int[] iArr, Uri[] uriArr, long[] jArr, long j3, boolean z2) {
            Assertions.checkArgument(iArr.length == uriArr.length);
            this.timeUs = j2;
            this.count = i3;
            this.states = iArr;
            this.uris = uriArr;
            this.durationsUs = jArr;
            this.contentResumeOffsetUs = j3;
            this.isServerSideInserted = z2;
        }
    }

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AdState {
    }

    public AdPlaybackState(Object obj, long... jArr) {
        this(obj, createEmptyAdGroups(jArr), 0, C.TIME_UNSET, 0);
    }

    private static AdGroup[] createEmptyAdGroups(long[] jArr) {
        int length = jArr.length;
        AdGroup[] adGroupArr = new AdGroup[length];
        for (int i3 = 0; i3 < length; i3++) {
            adGroupArr[i3] = new AdGroup(jArr[i3]);
        }
        return adGroupArr;
    }

    public static AdPlaybackState fromAdPlaybackState(Object obj, AdPlaybackState adPlaybackState) {
        AdPlaybackState adPlaybackState2 = adPlaybackState;
        int i3 = adPlaybackState2.adGroupCount - adPlaybackState2.removedAdGroupCount;
        AdGroup[] adGroupArr = new AdGroup[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            AdGroup adGroup = adPlaybackState2.adGroups[i4];
            long j2 = adGroup.timeUs;
            int i5 = adGroup.count;
            int[] iArr = adGroup.states;
            int[] copyOf = Arrays.copyOf(iArr, iArr.length);
            Uri[] uriArr = adGroup.uris;
            long[] jArr = adGroup.durationsUs;
            adGroupArr[i4] = new AdGroup(j2, i5, copyOf, (Uri[]) Arrays.copyOf(uriArr, uriArr.length), Arrays.copyOf(jArr, jArr.length), adGroup.contentResumeOffsetUs, adGroup.isServerSideInserted);
        }
        return new AdPlaybackState(obj, adGroupArr, adPlaybackState2.adResumePositionUs, adPlaybackState2.contentDurationUs, adPlaybackState2.removedAdGroupCount);
    }

    /* access modifiers changed from: private */
    public static AdPlaybackState fromBundle(Bundle bundle) {
        AdGroup[] adGroupArr;
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(keyForField(1));
        if (parcelableArrayList == null) {
            adGroupArr = new AdGroup[0];
        } else {
            AdGroup[] adGroupArr2 = new AdGroup[parcelableArrayList.size()];
            for (int i3 = 0; i3 < parcelableArrayList.size(); i3++) {
                adGroupArr2[i3] = AdGroup.CREATOR.fromBundle((Bundle) parcelableArrayList.get(i3));
            }
            adGroupArr = adGroupArr2;
        }
        return new AdPlaybackState((Object) null, adGroupArr, bundle.getLong(keyForField(2), 0), bundle.getLong(keyForField(3), C.TIME_UNSET), bundle.getInt(keyForField(4)));
    }

    private boolean isPositionBeforeAdGroup(long j2, long j3, int i3) {
        if (j2 == Long.MIN_VALUE) {
            return false;
        }
        long j4 = getAdGroup(i3).timeUs;
        return j4 == Long.MIN_VALUE ? j3 == C.TIME_UNSET || j2 < j3 : j2 < j4;
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AdPlaybackState.class != obj.getClass()) {
            return false;
        }
        AdPlaybackState adPlaybackState = (AdPlaybackState) obj;
        return Util.areEqual(this.adsId, adPlaybackState.adsId) && this.adGroupCount == adPlaybackState.adGroupCount && this.adResumePositionUs == adPlaybackState.adResumePositionUs && this.contentDurationUs == adPlaybackState.contentDurationUs && this.removedAdGroupCount == adPlaybackState.removedAdGroupCount && Arrays.equals(this.adGroups, adPlaybackState.adGroups);
    }

    public AdGroup getAdGroup(@IntRange(from = 0) int i3) {
        int i4 = this.removedAdGroupCount;
        return i3 < i4 ? REMOVED_AD_GROUP : this.adGroups[i3 - i4];
    }

    public int getAdGroupIndexAfterPositionUs(long j2, long j3) {
        if (j2 == Long.MIN_VALUE) {
            return -1;
        }
        if (j3 != C.TIME_UNSET && j2 >= j3) {
            return -1;
        }
        int i3 = this.removedAdGroupCount;
        while (i3 < this.adGroupCount && ((getAdGroup(i3).timeUs != Long.MIN_VALUE && getAdGroup(i3).timeUs <= j2) || !getAdGroup(i3).shouldPlayAdGroup())) {
            i3++;
        }
        if (i3 < this.adGroupCount) {
            return i3;
        }
        return -1;
    }

    public int getAdGroupIndexForPositionUs(long j2, long j3) {
        int i3 = this.adGroupCount - 1;
        while (i3 >= 0 && isPositionBeforeAdGroup(j2, j3, i3)) {
            i3--;
        }
        if (i3 < 0 || !getAdGroup(i3).hasUnplayedAds()) {
            return -1;
        }
        return i3;
    }

    public int hashCode() {
        int i3 = this.adGroupCount * 31;
        Object obj = this.adsId;
        return ((((((((i3 + (obj == null ? 0 : obj.hashCode())) * 31) + ((int) this.adResumePositionUs)) * 31) + ((int) this.contentDurationUs)) * 31) + this.removedAdGroupCount) * 31) + Arrays.hashCode(this.adGroups);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r2 = getAdGroup(r3);
        r3 = r2.count;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isAdInErrorState(@androidx.annotation.IntRange(from = 0) int r3, @androidx.annotation.IntRange(from = 0) int r4) {
        /*
            r2 = this;
            int r0 = r2.adGroupCount
            r1 = 0
            if (r3 < r0) goto L_0x0006
            return r1
        L_0x0006:
            com.appsamurai.storyly.exoplayer2.common.source.ads.AdPlaybackState$AdGroup r2 = r2.getAdGroup(r3)
            int r3 = r2.count
            r0 = -1
            if (r3 == r0) goto L_0x001a
            if (r4 < r3) goto L_0x0012
            goto L_0x001a
        L_0x0012:
            int[] r2 = r2.states
            r2 = r2[r4]
            r3 = 4
            if (r2 != r3) goto L_0x001a
            r1 = 1
        L_0x001a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.source.ads.AdPlaybackState.isAdInErrorState(int, int):boolean");
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList();
        for (AdGroup bundle2 : this.adGroups) {
            arrayList.add(bundle2.toBundle());
        }
        bundle.putParcelableArrayList(keyForField(1), arrayList);
        bundle.putLong(keyForField(2), this.adResumePositionUs);
        bundle.putLong(keyForField(3), this.contentDurationUs);
        bundle.putInt(keyForField(4), this.removedAdGroupCount);
        return bundle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AdPlaybackState(adsId=");
        sb.append(this.adsId);
        sb.append(", adResumePositionUs=");
        sb.append(this.adResumePositionUs);
        sb.append(", adGroups=[");
        for (int i3 = 0; i3 < this.adGroups.length; i3++) {
            sb.append("adGroup(timeUs=");
            sb.append(this.adGroups[i3].timeUs);
            sb.append(", ads=[");
            for (int i4 = 0; i4 < this.adGroups[i3].states.length; i4++) {
                sb.append("ad(state=");
                int i5 = this.adGroups[i3].states[i4];
                if (i5 == 0) {
                    sb.append('_');
                } else if (i5 == 1) {
                    sb.append('R');
                } else if (i5 == 2) {
                    sb.append('S');
                } else if (i5 == 3) {
                    sb.append('P');
                } else if (i5 != 4) {
                    sb.append('?');
                } else {
                    sb.append('!');
                }
                sb.append(", durationUs=");
                sb.append(this.adGroups[i3].durationsUs[i4]);
                sb.append(')');
                if (i4 < this.adGroups[i3].states.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("])");
            if (i3 < this.adGroups.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("])");
        return sb.toString();
    }

    @CheckResult
    public AdPlaybackState withAdCount(@IntRange(from = 0) int i3, @IntRange(from = 1) int i4) {
        Assertions.checkArgument(i4 > 0);
        int i5 = i3 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        if (adGroupArr[i5].count == i4) {
            return this;
        }
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i5] = this.adGroups[i5].withAdCount(i4);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withAdDurationsUs(long[][] jArr) {
        Assertions.checkState(this.removedAdGroupCount == 0);
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        for (int i3 = 0; i3 < this.adGroupCount; i3++) {
            adGroupArr2[i3] = adGroupArr2[i3].withAdDurationsUs(jArr[i3]);
        }
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withAdGroupTimeUs(@IntRange(from = 0) int i3, long j2) {
        int i4 = i3 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i4] = this.adGroups[i4].withTimeUs(j2);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withAdLoadError(@IntRange(from = 0) int i3, @IntRange(from = 0) int i4) {
        int i5 = i3 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i5] = adGroupArr2[i5].withAdState(4, i4);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withAdResumePositionUs(long j2) {
        if (this.adResumePositionUs == j2) {
            return this;
        }
        return new AdPlaybackState(this.adsId, this.adGroups, j2, this.contentDurationUs, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withAdUri(@IntRange(from = 0) int i3, @IntRange(from = 0) int i4, Uri uri) {
        int i5 = i3 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i5] = adGroupArr2[i5].withAdUri(uri, i4);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withContentDurationUs(long j2) {
        if (this.contentDurationUs == j2) {
            return this;
        }
        return new AdPlaybackState(this.adsId, this.adGroups, this.adResumePositionUs, j2, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withContentResumeOffsetUs(@IntRange(from = 0) int i3, long j2) {
        int i4 = i3 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        if (adGroupArr[i4].contentResumeOffsetUs == j2) {
            return this;
        }
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i4] = adGroupArr2[i4].withContentResumeOffsetUs(j2);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withIsServerSideInserted(@IntRange(from = 0) int i3, boolean z2) {
        int i4 = i3 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        if (adGroupArr[i4].isServerSideInserted == z2) {
            return this;
        }
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i4] = adGroupArr2[i4].withIsServerSideInserted(z2);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withNewAdGroup(@IntRange(from = 0) int i3, long j2) {
        int i4 = i3 - this.removedAdGroupCount;
        AdGroup adGroup = new AdGroup(j2);
        AdGroup[] adGroupArr = (AdGroup[]) Util.nullSafeArrayAppend(this.adGroups, adGroup);
        System.arraycopy(adGroupArr, i4, adGroupArr, i4 + 1, this.adGroups.length - i4);
        adGroupArr[i4] = adGroup;
        return new AdPlaybackState(this.adsId, adGroupArr, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withPlayedAd(@IntRange(from = 0) int i3, @IntRange(from = 0) int i4) {
        int i5 = i3 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i5] = adGroupArr2[i5].withAdState(3, i4);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withRemovedAdGroupCount(@IntRange(from = 0) int i3) {
        int i4 = this.removedAdGroupCount;
        if (i4 == i3) {
            return this;
        }
        Assertions.checkArgument(i3 > i4);
        int i5 = this.adGroupCount - i3;
        AdGroup[] adGroupArr = new AdGroup[i5];
        System.arraycopy(this.adGroups, i3 - this.removedAdGroupCount, adGroupArr, 0, i5);
        return new AdPlaybackState(this.adsId, adGroupArr, this.adResumePositionUs, this.contentDurationUs, i3);
    }

    @CheckResult
    public AdPlaybackState withResetAdGroup(@IntRange(from = 0) int i3) {
        int i4 = i3 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i4] = adGroupArr2[i4].withAllAdsReset();
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withSkippedAd(@IntRange(from = 0) int i3, @IntRange(from = 0) int i4) {
        int i5 = i3 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i5] = adGroupArr2[i5].withAdState(2, i4);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @CheckResult
    public AdPlaybackState withSkippedAdGroup(@IntRange(from = 0) int i3) {
        int i4 = i3 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i4] = adGroupArr2[i4].withAllAdsSkipped();
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    private AdPlaybackState(@Nullable Object obj, AdGroup[] adGroupArr, long j2, long j3, int i3) {
        this.adsId = obj;
        this.adResumePositionUs = j2;
        this.contentDurationUs = j3;
        this.adGroupCount = adGroupArr.length + i3;
        this.adGroups = adGroupArr;
        this.removedAdGroupCount = i3;
    }

    @CheckResult
    public AdPlaybackState withAdDurationsUs(@IntRange(from = 0) int i3, long... jArr) {
        int i4 = i3 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i4] = adGroupArr2[i4].withAdDurationsUs(jArr);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }
}
