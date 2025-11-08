package com.airbnb.lottie.compose;

import android.support.v4.media.session.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.compose.runtime.internal.StabilityInferred;
import com.airbnb.lottie.LottieComposition;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\n\u000b\f\rB\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H ¢\u0006\u0002\b\u0007J\u0015\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H ¢\u0006\u0002\b\t\u0001\u0004\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/airbnb/lottie/compose/LottieClipSpec;", "", "()V", "getMaxProgress", "", "composition", "Lcom/airbnb/lottie/LottieComposition;", "getMaxProgress$lottie_compose_release", "getMinProgress", "getMinProgress$lottie_compose_release", "Frame", "Marker", "Markers", "Progress", "Lcom/airbnb/lottie/compose/LottieClipSpec$Frame;", "Lcom/airbnb/lottie/compose/LottieClipSpec$Marker;", "Lcom/airbnb/lottie/compose/LottieClipSpec$Markers;", "Lcom/airbnb/lottie/compose/LottieClipSpec$Progress;", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class LottieClipSpec {
    public static final int $stable = 0;

    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J0\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\u0015\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0010¢\u0006\u0002\b\u001bJ\u0015\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0010¢\u0006\u0002\b\u001dJ\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0012\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000e\u0010\u000b¨\u0006!"}, d2 = {"Lcom/airbnb/lottie/compose/LottieClipSpec$Frame;", "Lcom/airbnb/lottie/compose/LottieClipSpec;", "min", "", "max", "maxInclusive", "", "(Ljava/lang/Integer;Ljava/lang/Integer;Z)V", "actualMaxFrame", "Ljava/lang/Integer;", "getMax", "()Ljava/lang/Integer;", "getMaxInclusive", "()Z", "getMin", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Z)Lcom/airbnb/lottie/compose/LottieClipSpec$Frame;", "equals", "other", "", "getMaxProgress", "", "composition", "Lcom/airbnb/lottie/LottieComposition;", "getMaxProgress$lottie_compose_release", "getMinProgress", "getMinProgress$lottie_compose_release", "hashCode", "toString", "", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Frame extends LottieClipSpec {
        public static final int $stable = 0;
        @Nullable
        private final Integer actualMaxFrame;
        @Nullable
        private final Integer max;
        private final boolean maxInclusive;
        @Nullable
        private final Integer min;

        public Frame() {
            this((Integer) null, (Integer) null, false, 7, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Frame copy$default(Frame frame, Integer num, Integer num2, boolean z2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                num = frame.min;
            }
            if ((i3 & 2) != 0) {
                num2 = frame.max;
            }
            if ((i3 & 4) != 0) {
                z2 = frame.maxInclusive;
            }
            return frame.copy(num, num2, z2);
        }

        @Nullable
        public final Integer component1() {
            return this.min;
        }

        @Nullable
        public final Integer component2() {
            return this.max;
        }

        public final boolean component3() {
            return this.maxInclusive;
        }

        @NotNull
        public final Frame copy(@Nullable Integer num, @Nullable Integer num2, boolean z2) {
            return new Frame(num, num2, z2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Frame)) {
                return false;
            }
            Frame frame = (Frame) obj;
            return Intrinsics.areEqual((Object) this.min, (Object) frame.min) && Intrinsics.areEqual((Object) this.max, (Object) frame.max) && this.maxInclusive == frame.maxInclusive;
        }

        @Nullable
        public final Integer getMax() {
            return this.max;
        }

        public final boolean getMaxInclusive() {
            return this.maxInclusive;
        }

        public float getMaxProgress$lottie_compose_release(@NotNull LottieComposition lottieComposition) {
            Intrinsics.checkNotNullParameter(lottieComposition, "composition");
            Integer num = this.actualMaxFrame;
            if (num == null) {
                return 1.0f;
            }
            return RangesKt.coerceIn(((float) num.intValue()) / lottieComposition.getEndFrame(), 0.0f, 1.0f);
        }

        @Nullable
        public final Integer getMin() {
            return this.min;
        }

        public float getMinProgress$lottie_compose_release(@NotNull LottieComposition lottieComposition) {
            Intrinsics.checkNotNullParameter(lottieComposition, "composition");
            Integer num = this.min;
            if (num == null) {
                return 0.0f;
            }
            return RangesKt.coerceIn(((float) num.intValue()) / lottieComposition.getEndFrame(), 0.0f, 1.0f);
        }

        public int hashCode() {
            Integer num = this.min;
            int i3 = 0;
            int hashCode = (num == null ? 0 : num.hashCode()) * 31;
            Integer num2 = this.max;
            if (num2 != null) {
                i3 = num2.hashCode();
            }
            int i4 = (hashCode + i3) * 31;
            boolean z2 = this.maxInclusive;
            if (z2) {
                z2 = true;
            }
            return i4 + (z2 ? 1 : 0);
        }

        @NotNull
        public String toString() {
            Integer num = this.min;
            Integer num2 = this.max;
            boolean z2 = this.maxInclusive;
            StringBuilder sb = new StringBuilder("Frame(min=");
            sb.append(num);
            sb.append(", max=");
            sb.append(num2);
            sb.append(", maxInclusive=");
            return a.s(sb, z2, ")");
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Frame(Integer num, Integer num2, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? null : num, (i3 & 2) != 0 ? null : num2, (i3 & 4) != 0 ? true : z2);
        }

        public Frame(@Nullable Integer num, @Nullable Integer num2, boolean z2) {
            super((DefaultConstructorMarker) null);
            this.min = num;
            this.max = num2;
            this.maxInclusive = z2;
            if (num2 == null) {
                num2 = null;
            } else if (!z2) {
                num2 = Integer.valueOf(num2.intValue() - 1);
            }
            this.actualMaxFrame = num2;
        }
    }

    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u0011J\u0015\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u0013J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcom/airbnb/lottie/compose/LottieClipSpec$Marker;", "Lcom/airbnb/lottie/compose/LottieClipSpec;", "marker", "", "(Ljava/lang/String;)V", "getMarker", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getMaxProgress", "", "composition", "Lcom/airbnb/lottie/LottieComposition;", "getMaxProgress$lottie_compose_release", "getMinProgress", "getMinProgress$lottie_compose_release", "hashCode", "", "toString", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Marker extends LottieClipSpec {
        public static final int $stable = 0;
        @NotNull
        private final String marker;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Marker(@NotNull String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "marker");
            this.marker = str;
        }

        public static /* synthetic */ Marker copy$default(Marker marker2, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = marker2.marker;
            }
            return marker2.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.marker;
        }

        @NotNull
        public final Marker copy(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "marker");
            return new Marker(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Marker) && Intrinsics.areEqual((Object) this.marker, (Object) ((Marker) obj).marker);
        }

        @NotNull
        public final String getMarker() {
            return this.marker;
        }

        public float getMaxProgress$lottie_compose_release(@NotNull LottieComposition lottieComposition) {
            Intrinsics.checkNotNullParameter(lottieComposition, "composition");
            com.airbnb.lottie.model.Marker marker2 = lottieComposition.getMarker(this.marker);
            if (marker2 == null) {
                return 1.0f;
            }
            return RangesKt.coerceIn((marker2.startFrame + marker2.durationFrames) / lottieComposition.getEndFrame(), 0.0f, 1.0f);
        }

        public float getMinProgress$lottie_compose_release(@NotNull LottieComposition lottieComposition) {
            Intrinsics.checkNotNullParameter(lottieComposition, "composition");
            com.airbnb.lottie.model.Marker marker2 = lottieComposition.getMarker(this.marker);
            return RangesKt.coerceIn((marker2 != null ? marker2.startFrame : 0.0f) / lottieComposition.getEndFrame(), 0.0f, 1.0f);
        }

        public int hashCode() {
            return this.marker.hashCode();
        }

        @NotNull
        public String toString() {
            return A.a.l("Marker(marker=", this.marker, ")");
        }
    }

    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J+\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0010¢\u0006\u0002\b\u0018J\u0015\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0010¢\u0006\u0002\b\u001aJ\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u001e"}, d2 = {"Lcom/airbnb/lottie/compose/LottieClipSpec$Markers;", "Lcom/airbnb/lottie/compose/LottieClipSpec;", "min", "", "max", "maxInclusive", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getMax", "()Ljava/lang/String;", "getMaxInclusive", "()Z", "getMin", "component1", "component2", "component3", "copy", "equals", "other", "", "getMaxProgress", "", "composition", "Lcom/airbnb/lottie/LottieComposition;", "getMaxProgress$lottie_compose_release", "getMinProgress", "getMinProgress$lottie_compose_release", "hashCode", "", "toString", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Markers extends LottieClipSpec {
        public static final int $stable = 0;
        @Nullable
        private final String max;
        private final boolean maxInclusive;
        @Nullable
        private final String min;

        public Markers() {
            this((String) null, (String) null, false, 7, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Markers copy$default(Markers markers, String str, String str2, boolean z2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = markers.min;
            }
            if ((i3 & 2) != 0) {
                str2 = markers.max;
            }
            if ((i3 & 4) != 0) {
                z2 = markers.maxInclusive;
            }
            return markers.copy(str, str2, z2);
        }

        @Nullable
        public final String component1() {
            return this.min;
        }

        @Nullable
        public final String component2() {
            return this.max;
        }

        public final boolean component3() {
            return this.maxInclusive;
        }

        @NotNull
        public final Markers copy(@Nullable String str, @Nullable String str2, boolean z2) {
            return new Markers(str, str2, z2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Markers)) {
                return false;
            }
            Markers markers = (Markers) obj;
            return Intrinsics.areEqual((Object) this.min, (Object) markers.min) && Intrinsics.areEqual((Object) this.max, (Object) markers.max) && this.maxInclusive == markers.maxInclusive;
        }

        @Nullable
        public final String getMax() {
            return this.max;
        }

        public final boolean getMaxInclusive() {
            return this.maxInclusive;
        }

        public float getMaxProgress$lottie_compose_release(@NotNull LottieComposition lottieComposition) {
            Intrinsics.checkNotNullParameter(lottieComposition, "composition");
            String str = this.max;
            if (str == null) {
                return 1.0f;
            }
            int i3 = this.maxInclusive ? 0 : -1;
            com.airbnb.lottie.model.Marker marker = lottieComposition.getMarker(str);
            return RangesKt.coerceIn((marker != null ? marker.startFrame + ((float) i3) : 0.0f) / lottieComposition.getEndFrame(), 0.0f, 1.0f);
        }

        @Nullable
        public final String getMin() {
            return this.min;
        }

        public float getMinProgress$lottie_compose_release(@NotNull LottieComposition lottieComposition) {
            Intrinsics.checkNotNullParameter(lottieComposition, "composition");
            String str = this.min;
            if (str == null) {
                return 0.0f;
            }
            com.airbnb.lottie.model.Marker marker = lottieComposition.getMarker(str);
            return RangesKt.coerceIn((marker != null ? marker.startFrame : 0.0f) / lottieComposition.getEndFrame(), 0.0f, 1.0f);
        }

        public int hashCode() {
            String str = this.min;
            int i3 = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.max;
            if (str2 != null) {
                i3 = str2.hashCode();
            }
            int i4 = (hashCode + i3) * 31;
            boolean z2 = this.maxInclusive;
            if (z2) {
                z2 = true;
            }
            return i4 + (z2 ? 1 : 0);
        }

        @NotNull
        public String toString() {
            String str = this.min;
            String str2 = this.max;
            return a.s(C0118y.l("Markers(min=", str, ", max=", str2, ", maxInclusive="), this.maxInclusive, ")");
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Markers(String str, String str2, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? true : z2);
        }

        public Markers(@Nullable String str, @Nullable String str2, boolean z2) {
            super((DefaultConstructorMarker) null);
            this.min = str;
            this.max = str2;
            this.maxInclusive = z2;
        }
    }

    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\u0015\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0010¢\u0006\u0002\b\u0013J\u0015\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0010¢\u0006\u0002\b\u0015J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u001a"}, d2 = {"Lcom/airbnb/lottie/compose/LottieClipSpec$Progress;", "Lcom/airbnb/lottie/compose/LottieClipSpec;", "min", "", "max", "(FF)V", "getMax", "()F", "getMin", "component1", "component2", "copy", "equals", "", "other", "", "getMaxProgress", "composition", "Lcom/airbnb/lottie/LottieComposition;", "getMaxProgress$lottie_compose_release", "getMinProgress", "getMinProgress$lottie_compose_release", "hashCode", "", "toString", "", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Progress extends LottieClipSpec {
        public static final int $stable = 0;
        private final float max;
        private final float min;

        public Progress() {
            this(0.0f, 0.0f, 3, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Progress copy$default(Progress progress, float f2, float f3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                f2 = progress.min;
            }
            if ((i3 & 2) != 0) {
                f3 = progress.max;
            }
            return progress.copy(f2, f3);
        }

        public final float component1() {
            return this.min;
        }

        public final float component2() {
            return this.max;
        }

        @NotNull
        public final Progress copy(float f2, float f3) {
            return new Progress(f2, f3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Progress)) {
                return false;
            }
            Progress progress = (Progress) obj;
            return Float.compare(this.min, progress.min) == 0 && Float.compare(this.max, progress.max) == 0;
        }

        public final float getMax() {
            return this.max;
        }

        public float getMaxProgress$lottie_compose_release(@NotNull LottieComposition lottieComposition) {
            Intrinsics.checkNotNullParameter(lottieComposition, "composition");
            return this.max;
        }

        public final float getMin() {
            return this.min;
        }

        public float getMinProgress$lottie_compose_release(@NotNull LottieComposition lottieComposition) {
            Intrinsics.checkNotNullParameter(lottieComposition, "composition");
            return this.min;
        }

        public int hashCode() {
            return Float.hashCode(this.max) + (Float.hashCode(this.min) * 31);
        }

        @NotNull
        public String toString() {
            float f2 = this.min;
            float f3 = this.max;
            return "Progress(min=" + f2 + ", max=" + f3 + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Progress(float f2, float f3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 0.0f : f2, (i3 & 2) != 0 ? 1.0f : f3);
        }

        public Progress(float f2, float f3) {
            super((DefaultConstructorMarker) null);
            this.min = f2;
            this.max = f3;
        }
    }

    public /* synthetic */ LottieClipSpec(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract float getMaxProgress$lottie_compose_release(@NotNull LottieComposition lottieComposition);

    public abstract float getMinProgress$lottie_compose_release(@NotNull LottieComposition lottieComposition);

    private LottieClipSpec() {
    }
}
