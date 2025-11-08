package com.apollographql.apollo3.exception;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import com.apollographql.apollo3.annotations.ApolloExperimental;
import com.apollographql.apollo3.annotations.ApolloInternal;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/apollographql/apollo3/exception/CacheMissException;", "Lcom/apollographql/apollo3/exception/ApolloException;", "key", "", "fieldName", "(Ljava/lang/String;Ljava/lang/String;)V", "stale", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getFieldName", "()Ljava/lang/String;", "getKey", "getStale$annotations", "()V", "getStale", "()Z", "Companion", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class CacheMissException extends ApolloException {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @Nullable
    private final String fieldName;
    @NotNull
    private final String key;
    private final boolean stale;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007J)\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo3/exception/CacheMissException$Companion;", "", "()V", "message", "", "key", "fieldName", "stale", "", "message$apollo_api", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        @Deprecated(message = "Use CacheMissException.message instead")
        public final String message(@Nullable String str, @Nullable String str2) {
            return message$apollo_api(str, str2, false);
        }

        @NotNull
        public final String message$apollo_api(@Nullable String str, @Nullable String str2, boolean z2) {
            if (str2 == null) {
                return a.l("Object '", str, "' not found");
            }
            if (z2) {
                return C0118y.g("Field '", str2, "' on object '", str, "' is stale");
            }
            return "Object '" + str + "' has no field named '" + str2 + '\'';
        }

        private Companion() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CacheMissException(String str, String str2, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? false : z2);
    }

    @ApolloExperimental
    public static /* synthetic */ void getStale$annotations() {
    }

    @Nullable
    public final String getFieldName() {
        return this.fieldName;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    public final boolean getStale() {
        return this.stale;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @ApolloInternal
    public CacheMissException(@NotNull String str, @Nullable String str2, boolean z2) {
        super(Companion.message$apollo_api(str, str2, z2), (Throwable) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        this.key = str;
        this.fieldName = str2;
        this.stale = z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CacheMissException(@NotNull String str, @Nullable String str2) {
        this(str, str2, false);
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
    }
}
