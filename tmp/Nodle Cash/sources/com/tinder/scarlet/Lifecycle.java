package com.tinder.scarlet;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reactivestreams.Publisher;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0007J!\u0010\u0003\u001a\u00020\u00002\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u0005\"\u00020\u0000H&¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/tinder/scarlet/Lifecycle;", "Lorg/reactivestreams/Publisher;", "Lcom/tinder/scarlet/Lifecycle$State;", "combineWith", "others", "", "([Lcom/tinder/scarlet/Lifecycle;)Lcom/tinder/scarlet/Lifecycle;", "State", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Lifecycle extends Publisher<State> {

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/tinder/scarlet/Lifecycle$State;", "", "()V", "Destroyed", "Started", "Stopped", "Lcom/tinder/scarlet/Lifecycle$State$Destroyed;", "Lcom/tinder/scarlet/Lifecycle$State$Started;", "Lcom/tinder/scarlet/Lifecycle$State$Stopped;", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class State {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tinder/scarlet/Lifecycle$State$Destroyed;", "Lcom/tinder/scarlet/Lifecycle$State;", "()V", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Destroyed extends State {
            @NotNull
            public static final Destroyed INSTANCE = new Destroyed();

            private Destroyed() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tinder/scarlet/Lifecycle$State$Started;", "Lcom/tinder/scarlet/Lifecycle$State;", "()V", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Started extends State {
            @NotNull
            public static final Started INSTANCE = new Started();

            private Started() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/tinder/scarlet/Lifecycle$State$Stopped;", "Lcom/tinder/scarlet/Lifecycle$State;", "()V", "AndAborted", "WithReason", "Lcom/tinder/scarlet/Lifecycle$State$Stopped$AndAborted;", "Lcom/tinder/scarlet/Lifecycle$State$Stopped$WithReason;", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static abstract class Stopped extends State {

            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tinder/scarlet/Lifecycle$State$Stopped$AndAborted;", "Lcom/tinder/scarlet/Lifecycle$State$Stopped;", "()V", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
            public static final class AndAborted extends Stopped {
                @NotNull
                public static final AndAborted INSTANCE = new AndAborted();

                private AndAborted() {
                    super((DefaultConstructorMarker) null);
                }
            }

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/tinder/scarlet/Lifecycle$State$Stopped$WithReason;", "Lcom/tinder/scarlet/Lifecycle$State$Stopped;", "shutdownReason", "Lcom/tinder/scarlet/ShutdownReason;", "(Lcom/tinder/scarlet/ShutdownReason;)V", "getShutdownReason", "()Lcom/tinder/scarlet/ShutdownReason;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
            public static final class WithReason extends Stopped {
                @NotNull
                private final ShutdownReason shutdownReason;

                public WithReason() {
                    this((ShutdownReason) null, 1, (DefaultConstructorMarker) null);
                }

                public static /* synthetic */ WithReason copy$default(WithReason withReason, ShutdownReason shutdownReason2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        shutdownReason2 = withReason.shutdownReason;
                    }
                    return withReason.copy(shutdownReason2);
                }

                @NotNull
                public final ShutdownReason component1() {
                    return this.shutdownReason;
                }

                @NotNull
                public final WithReason copy(@NotNull ShutdownReason shutdownReason2) {
                    Intrinsics.checkNotNullParameter(shutdownReason2, "shutdownReason");
                    return new WithReason(shutdownReason2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof WithReason) && Intrinsics.areEqual((Object) this.shutdownReason, (Object) ((WithReason) obj).shutdownReason);
                }

                @NotNull
                public final ShutdownReason getShutdownReason() {
                    return this.shutdownReason;
                }

                public int hashCode() {
                    return this.shutdownReason.hashCode();
                }

                @NotNull
                public String toString() {
                    return "WithReason(shutdownReason=" + this.shutdownReason + ')';
                }

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ WithReason(ShutdownReason shutdownReason2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i3 & 1) != 0 ? ShutdownReason.GRACEFUL : shutdownReason2);
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public WithReason(@NotNull ShutdownReason shutdownReason2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(shutdownReason2, "shutdownReason");
                    this.shutdownReason = shutdownReason2;
                }
            }

            public /* synthetic */ Stopped(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Stopped() {
                super((DefaultConstructorMarker) null);
            }
        }

        public /* synthetic */ State(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private State() {
        }
    }

    @NotNull
    Lifecycle combineWith(@NotNull Lifecycle... lifecycleArr);
}
