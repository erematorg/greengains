package com.phrase.android.sdk.repo;

import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/phrase/android/sdk/repo/PhraseApiResult;", "", "()V", "Data", "Error", "NotModified", "Lcom/phrase/android/sdk/repo/PhraseApiResult$Data;", "Lcom/phrase/android/sdk/repo/PhraseApiResult$Error;", "Lcom/phrase/android/sdk/repo/PhraseApiResult$NotModified;", "sdk_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class PhraseApiResult {

    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\u0013\u0010\u0005\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0002HÆ\u0001J\t\u0010\u0006\u001a\u00020\u0002HÖ\u0001J\t\u0010\b\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tHÖ\u0003R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/phrase/android/sdk/repo/PhraseApiResult$Data;", "Lcom/phrase/android/sdk/repo/PhraseApiResult;", "", "component1", "version", "copy", "toString", "", "hashCode", "", "other", "", "equals", "a", "Ljava/lang/String;", "getVersion", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "sdk_release"}, k = 1, mv = {1, 7, 1})
    public static final class Data extends PhraseApiResult {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final String f7275a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Data(@NotNull String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "version");
            this.f7275a = str;
        }

        public static /* synthetic */ Data copy$default(Data data, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = data.f7275a;
            }
            return data.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.f7275a;
        }

        @NotNull
        public final Data copy(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "version");
            return new Data(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Data) && Intrinsics.areEqual((Object) this.f7275a, (Object) ((Data) obj).f7275a);
        }

        @NotNull
        public final String getVersion() {
            return this.f7275a;
        }

        public int hashCode() {
            return this.f7275a.hashCode();
        }

        @NotNull
        public String toString() {
            return a.o(')', this.f7275a, new StringBuilder("Data(version="));
        }
    }

    @Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\u0013\u0010\u0005\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0002HÆ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\t\u0010\t\u001a\u00020\bHÖ\u0001J\u0013\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nHÖ\u0003R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/phrase/android/sdk/repo/PhraseApiResult$Error;", "Lcom/phrase/android/sdk/repo/PhraseApiResult;", "", "component1", "e", "copy", "", "toString", "", "hashCode", "", "other", "", "equals", "a", "Ljava/lang/Throwable;", "getE", "()Ljava/lang/Throwable;", "<init>", "(Ljava/lang/Throwable;)V", "sdk_release"}, k = 1, mv = {1, 7, 1})
    public static final class Error extends PhraseApiResult {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final Throwable f7276a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Error(@NotNull Throwable th) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(th, "e");
            this.f7276a = th;
        }

        public static /* synthetic */ Error copy$default(Error error, Throwable th, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                th = error.f7276a;
            }
            return error.copy(th);
        }

        @NotNull
        public final Throwable component1() {
            return this.f7276a;
        }

        @NotNull
        public final Error copy(@NotNull Throwable th) {
            Intrinsics.checkNotNullParameter(th, "e");
            return new Error(th);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Error) && Intrinsics.areEqual((Object) this.f7276a, (Object) ((Error) obj).f7276a);
        }

        @NotNull
        public final Throwable getE() {
            return this.f7276a;
        }

        public int hashCode() {
            return this.f7276a.hashCode();
        }

        @NotNull
        public String toString() {
            return "Error(e=" + this.f7276a + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/phrase/android/sdk/repo/PhraseApiResult$NotModified;", "Lcom/phrase/android/sdk/repo/PhraseApiResult;", "()V", "sdk_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class NotModified extends PhraseApiResult {
        @NotNull
        public static final NotModified INSTANCE = new NotModified();

        public NotModified() {
            super((DefaultConstructorMarker) null);
        }
    }

    public /* synthetic */ PhraseApiResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public PhraseApiResult() {
    }
}
