package com.tinder.scarlet;

import androidx.compose.runtime.b;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/tinder/scarlet/Deserialization;", "T", "", "()V", "Error", "Success", "Lcom/tinder/scarlet/Deserialization$Error;", "Lcom/tinder/scarlet/Deserialization$Success;", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class Deserialization<T> {

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004HÆ\u0003J\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/tinder/scarlet/Deserialization$Error;", "T", "Lcom/tinder/scarlet/Deserialization;", "throwable", "", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Error<T> extends Deserialization<T> {
        @NotNull
        private final Throwable throwable;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Error(@NotNull Throwable th) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(th, "throwable");
            this.throwable = th;
        }

        public static /* synthetic */ Error copy$default(Error error, Throwable th, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                th = error.throwable;
            }
            return error.copy(th);
        }

        @NotNull
        public final Throwable component1() {
            return this.throwable;
        }

        @NotNull
        public final Error<T> copy(@NotNull Throwable th) {
            Intrinsics.checkNotNullParameter(th, "throwable");
            return new Error<>(th);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Error) && Intrinsics.areEqual((Object) this.throwable, (Object) ((Error) obj).throwable);
        }

        @NotNull
        public final Throwable getThrowable() {
            return this.throwable;
        }

        public int hashCode() {
            return this.throwable.hashCode();
        }

        @NotNull
        public String toString() {
            return "Error(throwable=" + this.throwable + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/tinder/scarlet/Deserialization$Success;", "T", "Lcom/tinder/scarlet/Deserialization;", "value", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/tinder/scarlet/Deserialization$Success;", "equals", "", "other", "", "hashCode", "", "toString", "", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Success<T> extends Deserialization<T> {
        private final T value;

        public Success(T t2) {
            super((DefaultConstructorMarker) null);
            this.value = t2;
        }

        public static /* synthetic */ Success copy$default(Success success, T t2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                t2 = success.value;
            }
            return success.copy(t2);
        }

        public final T component1() {
            return this.value;
        }

        @NotNull
        public final Success<T> copy(T t2) {
            return new Success<>(t2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Success) && Intrinsics.areEqual((Object) this.value, (Object) ((Success) obj).value);
        }

        public final T getValue() {
            return this.value;
        }

        public int hashCode() {
            T t2 = this.value;
            if (t2 == null) {
                return 0;
            }
            return t2.hashCode();
        }

        @NotNull
        public String toString() {
            return b.c(new StringBuilder("Success(value="), this.value, ')');
        }
    }

    public /* synthetic */ Deserialization(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private Deserialization() {
    }
}
