package com.appsamurai.storyly.exoplayer2.common;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.BundleableUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Booleans;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Tracks implements Bundleable {
    public static final Bundleable.Creator<Tracks> CREATOR = new a(13);
    public static final Tracks EMPTY = new Tracks(ImmutableList.of());
    private static final int FIELD_TRACK_GROUPS = 0;
    private final ImmutableList<Group> groups;

    public static final class Group implements Bundleable {
        public static final Bundleable.Creator<Group> CREATOR = new a(14);
        private static final int FIELD_ADAPTIVE_SUPPORTED = 4;
        private static final int FIELD_TRACK_GROUP = 0;
        private static final int FIELD_TRACK_SELECTED = 3;
        private static final int FIELD_TRACK_SUPPORT = 1;
        private final boolean adaptiveSupported;
        public final int length;
        private final TrackGroup mediaTrackGroup;
        private final boolean[] trackSelected;
        private final int[] trackSupport;

        public Group(TrackGroup trackGroup, boolean z2, int[] iArr, boolean[] zArr) {
            int i3 = trackGroup.length;
            this.length = i3;
            boolean z3 = false;
            Assertions.checkArgument(i3 == iArr.length && i3 == zArr.length);
            this.mediaTrackGroup = trackGroup;
            if (z2 && i3 > 1) {
                z3 = true;
            }
            this.adaptiveSupported = z3;
            this.trackSupport = (int[]) iArr.clone();
            this.trackSelected = (boolean[]) zArr.clone();
        }

        private static String keyForField(int i3) {
            return Integer.toString(i3, 36);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Group.class != obj.getClass()) {
                return false;
            }
            Group group = (Group) obj;
            return this.adaptiveSupported == group.adaptiveSupported && this.mediaTrackGroup.equals(group.mediaTrackGroup) && Arrays.equals(this.trackSupport, group.trackSupport) && Arrays.equals(this.trackSelected, group.trackSelected);
        }

        public TrackGroup getMediaTrackGroup() {
            return this.mediaTrackGroup;
        }

        public Format getTrackFormat(int i3) {
            return this.mediaTrackGroup.getFormat(i3);
        }

        public int getTrackSupport(int i3) {
            return this.trackSupport[i3];
        }

        public int getType() {
            return this.mediaTrackGroup.type;
        }

        public int hashCode() {
            int hashCode = Arrays.hashCode(this.trackSupport);
            return Arrays.hashCode(this.trackSelected) + ((hashCode + (((this.mediaTrackGroup.hashCode() * 31) + (this.adaptiveSupported ? 1 : 0)) * 31)) * 31);
        }

        public boolean isAdaptiveSupported() {
            return this.adaptiveSupported;
        }

        public boolean isSelected() {
            return Booleans.contains(this.trackSelected, true);
        }

        public boolean isSupported() {
            return isSupported(false);
        }

        public boolean isTrackSelected(int i3) {
            return this.trackSelected[i3];
        }

        public boolean isTrackSupported(int i3) {
            return isTrackSupported(i3, false);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putBundle(keyForField(0), this.mediaTrackGroup.toBundle());
            bundle.putIntArray(keyForField(1), this.trackSupport);
            bundle.putBooleanArray(keyForField(3), this.trackSelected);
            bundle.putBoolean(keyForField(4), this.adaptiveSupported);
            return bundle;
        }

        public boolean isSupported(boolean z2) {
            for (int i3 = 0; i3 < this.trackSupport.length; i3++) {
                if (isTrackSupported(i3, z2)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isTrackSupported(int i3, boolean z2) {
            int i4 = this.trackSupport[i3];
            return i4 == 4 || (z2 && i4 == 3);
        }
    }

    public Tracks(List<Group> list) {
        this.groups = ImmutableList.copyOf(list);
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Tracks lambda$static$0(Bundle bundle) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(keyForField(0));
        return new Tracks(parcelableArrayList == null ? ImmutableList.of() : BundleableUtil.fromBundleList(Group.CREATOR, parcelableArrayList));
    }

    public boolean containsType(int i3) {
        for (int i4 = 0; i4 < this.groups.size(); i4++) {
            if (this.groups.get(i4).getType() == i3) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Tracks.class != obj.getClass()) {
            return false;
        }
        return this.groups.equals(((Tracks) obj).groups);
    }

    public ImmutableList<Group> getGroups() {
        return this.groups;
    }

    public int hashCode() {
        return this.groups.hashCode();
    }

    public boolean isEmpty() {
        return this.groups.isEmpty();
    }

    public boolean isTypeSelected(int i3) {
        for (int i4 = 0; i4 < this.groups.size(); i4++) {
            Group group = this.groups.get(i4);
            if (group.isSelected() && group.getType() == i3) {
                return true;
            }
        }
        return false;
    }

    public boolean isTypeSupported(int i3) {
        return isTypeSupported(i3, false);
    }

    @Deprecated
    public boolean isTypeSupportedOrEmpty(int i3) {
        return isTypeSupportedOrEmpty(i3, false);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(keyForField(0), BundleableUtil.toBundleArrayList(this.groups));
        return bundle;
    }

    public boolean isTypeSupported(int i3, boolean z2) {
        for (int i4 = 0; i4 < this.groups.size(); i4++) {
            if (this.groups.get(i4).getType() == i3 && this.groups.get(i4).isSupported(z2)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public boolean isTypeSupportedOrEmpty(int i3, boolean z2) {
        return !containsType(i3) || isTypeSupported(i3, z2);
    }
}
