package com.google.firebase;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RequiresApi;
import java.time.Instant;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \"2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\"B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB\u000f\b\u0017\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0011\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0000H\u0002J\b\u0010\u0014\u001a\u00020\u0006H\u0016J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0006H\u0016J\u0006\u0010\u0019\u001a\u00020\tJ\b\u0010\u001a\u001a\u00020\fH\u0007J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0006H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006#"}, d2 = {"Lcom/google/firebase/Timestamp;", "", "Landroid/os/Parcelable;", "seconds", "", "nanoseconds", "", "(JI)V", "date", "Ljava/util/Date;", "(Ljava/util/Date;)V", "time", "Ljava/time/Instant;", "(Ljava/time/Instant;)V", "getNanoseconds", "()I", "getSeconds", "()J", "compareTo", "other", "describeContents", "equals", "", "", "hashCode", "toDate", "toInstant", "toString", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "Companion", "com.google.firebase-firebase-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Timestamp implements Comparable<Timestamp>, Parcelable {
    @NotNull
    @JvmField
    public static final Parcelable.Creator<Timestamp> CREATOR = new Timestamp$Companion$CREATOR$1();
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final int nanoseconds;
    private final long seconds;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0007J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0\u000e*\u00020\u000fH\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/google/firebase/Timestamp$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/google/firebase/Timestamp;", "now", "validateRange", "", "seconds", "", "nanoseconds", "", "toPreciseTime", "Lkotlin/Pair;", "Ljava/util/Date;", "com.google.firebase-firebase-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final Pair<Long, Integer> toPreciseTime(Date date) {
            long j2 = (long) 1000;
            long time = date.getTime() / j2;
            int time2 = (int) ((date.getTime() % j2) * ((long) 1000000));
            return time2 < 0 ? TuplesKt.to(Long.valueOf(time - 1), Integer.valueOf(time2 + 1000000000)) : TuplesKt.to(Long.valueOf(time), Integer.valueOf(time2));
        }

        /* access modifiers changed from: private */
        public final void validateRange(long j2, int i3) {
            if (i3 < 0 || i3 >= 1000000000) {
                throw new IllegalArgumentException(a.k("Timestamp nanoseconds out of range: ", i3).toString());
            } else if (-62135596800L > j2 || j2 >= 253402300800L) {
                throw new IllegalArgumentException(androidx.compose.animation.core.a.s("Timestamp seconds out of range: ", j2).toString());
            }
        }

        @JvmStatic
        @NotNull
        public final Timestamp now() {
            return new Timestamp(new Date());
        }

        private Companion() {
        }
    }

    public Timestamp(long j2, int i3) {
        Companion.validateRange(j2, i3);
        this.seconds = j2;
        this.nanoseconds = i3;
    }

    @JvmStatic
    @NotNull
    public static final Timestamp now() {
        return Companion.now();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        return obj == this || ((obj instanceof Timestamp) && compareTo((Timestamp) obj) == 0);
    }

    public final int getNanoseconds() {
        return this.nanoseconds;
    }

    public final long getSeconds() {
        return this.seconds;
    }

    public int hashCode() {
        long j2 = this.seconds;
        return (((((int) j2) * 1369) + ((int) (j2 >> 32))) * 37) + this.nanoseconds;
    }

    @NotNull
    public final Date toDate() {
        return new Date((this.seconds * ((long) 1000)) + ((long) (this.nanoseconds / 1000000)));
    }

    @RequiresApi(26)
    @NotNull
    public final Instant toInstant() {
        Instant ofEpochSecond = Instant.ofEpochSecond(this.seconds, (long) this.nanoseconds);
        Intrinsics.checkNotNullExpressionValue(ofEpochSecond, "ofEpochSecond(seconds, nanoseconds.toLong())");
        return ofEpochSecond;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("Timestamp(seconds=");
        sb.append(this.seconds);
        sb.append(", nanoseconds=");
        return android.support.v4.media.session.a.p(sb, this.nanoseconds, ')');
    }

    public void writeToParcel(@NotNull Parcel parcel, int i3) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeLong(this.seconds);
        parcel.writeInt(this.nanoseconds);
    }

    public int compareTo(@NotNull Timestamp timestamp) {
        Intrinsics.checkNotNullParameter(timestamp, "other");
        return ComparisonsKt__ComparisonsKt.compareValuesBy(this, timestamp, (Function1<? super T, ? extends Comparable<?>>[]) new Function1[]{Timestamp$compareTo$1.INSTANCE, Timestamp$compareTo$2.INSTANCE});
    }

    public Timestamp(@NotNull Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        Companion companion = Companion;
        Pair access$toPreciseTime = companion.toPreciseTime(date);
        long longValue = ((Number) access$toPreciseTime.component1()).longValue();
        int intValue = ((Number) access$toPreciseTime.component2()).intValue();
        companion.validateRange(longValue, intValue);
        this.seconds = longValue;
        this.nanoseconds = intValue;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @RequiresApi(26)
    public Timestamp(@NotNull Instant instant) {
        this(instant.getEpochSecond(), instant.getNano());
        Intrinsics.checkNotNullParameter(instant, SchemaSymbols.ATTVAL_TIME);
    }
}
