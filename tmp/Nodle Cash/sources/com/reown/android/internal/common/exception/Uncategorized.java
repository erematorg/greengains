package com.reown.android.internal.common.exception;

import A.a;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import com.google.firebase.messaging.Constants;
import com.reown.android.internal.common.model.type.Error;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/internal/common/exception/Uncategorized;", "Lcom/reown/android/internal/common/model/type/Error;", "<init>", "()V", "NoMatchingTopic", "GenericError", "Lcom/reown/android/internal/common/exception/Uncategorized$GenericError;", "Lcom/reown/android/internal/common/exception/Uncategorized$NoMatchingTopic;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class Uncategorized implements Error {

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/reown/android/internal/common/exception/Uncategorized$GenericError;", "Lcom/reown/android/internal/common/exception/Uncategorized;", "error", "", "<init>", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "message", "getMessage", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class GenericError extends Uncategorized {
        private final int code = 1302;
        @NotNull
        private final String error;
        @NotNull
        private final String message;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GenericError(@NotNull String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
            this.error = str;
            this.message = c.a("Generic error: ", str);
        }

        public static /* synthetic */ GenericError copy$default(GenericError genericError, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = genericError.error;
            }
            return genericError.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.error;
        }

        @NotNull
        public final GenericError copy(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
            return new GenericError(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof GenericError) && Intrinsics.areEqual((Object) this.error, (Object) ((GenericError) obj).error);
        }

        public int getCode() {
            return this.code;
        }

        @NotNull
        public final String getError() {
            return this.error;
        }

        @NotNull
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return this.error.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("GenericError(error=", this.error, ")");
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\rHÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0014\u0010\f\u001a\u00020\rXD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/reown/android/internal/common/exception/Uncategorized$NoMatchingTopic;", "Lcom/reown/android/internal/common/exception/Uncategorized;", "sequence", "", "topic", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getSequence", "()Ljava/lang/String;", "getTopic", "message", "getMessage", "code", "", "getCode", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class NoMatchingTopic extends Uncategorized {
        private final int code = 1301;
        @NotNull
        private final String message;
        @NotNull
        private final String sequence;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public NoMatchingTopic(@NotNull String str, @NotNull String str2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "sequence");
            Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
            this.sequence = str;
            this.topic = str2;
            this.message = C0118y.f("No matching ", str, " with topic: ", str2);
        }

        public static /* synthetic */ NoMatchingTopic copy$default(NoMatchingTopic noMatchingTopic, String str, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = noMatchingTopic.sequence;
            }
            if ((i3 & 2) != 0) {
                str2 = noMatchingTopic.topic;
            }
            return noMatchingTopic.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.sequence;
        }

        @NotNull
        public final String component2() {
            return this.topic;
        }

        @NotNull
        public final NoMatchingTopic copy(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, "sequence");
            Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
            return new NoMatchingTopic(str, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof NoMatchingTopic)) {
                return false;
            }
            NoMatchingTopic noMatchingTopic = (NoMatchingTopic) obj;
            return Intrinsics.areEqual((Object) this.sequence, (Object) noMatchingTopic.sequence) && Intrinsics.areEqual((Object) this.topic, (Object) noMatchingTopic.topic);
        }

        public int getCode() {
            return this.code;
        }

        @NotNull
        public String getMessage() {
            return this.message;
        }

        @NotNull
        public final String getSequence() {
            return this.sequence;
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public int hashCode() {
            return this.topic.hashCode() + (this.sequence.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return C0118y.g("NoMatchingTopic(sequence=", this.sequence, ", topic=", this.topic, ")");
        }
    }

    public /* synthetic */ Uncategorized(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private Uncategorized() {
    }
}
