package com.apollographql.apollo3.api;

import android.support.v4.media.session.a;
import com.reown.android.push.notifications.PushMessagingService;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0011\u0018\u00002\u00020\u0001:\u0001\u0019B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005\u0012\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t\u0012\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\b\u0010\u0018\u001a\u00020\u0003H\u0016R(\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t8FX\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R!\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R!\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013¨\u0006\u001a"}, d2 = {"Lcom/apollographql/apollo3/api/Error;", "", "message", "", "locations", "", "Lcom/apollographql/apollo3/api/Error$Location;", "path", "extensions", "", "nonStandardFields", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;)V", "customAttributes", "getCustomAttributes$annotations", "()V", "getCustomAttributes", "()Ljava/util/Map;", "getExtensions", "getLocations", "()Ljava/util/List;", "getMessage", "()Ljava/lang/String;", "getNonStandardFields", "getPath", "toString", "Location", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class Error {
    @Nullable
    private final Map<String, Object> extensions;
    @Nullable
    private final List<Location> locations;
    @NotNull
    private final String message;
    @Nullable
    private final Map<String, Object> nonStandardFields;
    @Nullable
    private final List<Object> path;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo3/api/Error$Location;", "", "line", "", "column", "(II)V", "getColumn", "()I", "getLine", "toString", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Location {
        private final int column;
        private final int line;

        public Location(int i3, int i4) {
            this.line = i3;
            this.column = i4;
        }

        public final int getColumn() {
            return this.column;
        }

        public final int getLine() {
            return this.line;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder("Location(line = ");
            sb.append(this.line);
            sb.append(", column = ");
            return a.p(sb, this.column, ')');
        }
    }

    public Error(@NotNull String str, @Nullable List<Location> list, @Nullable List<? extends Object> list2, @Nullable Map<String, ? extends Object> map, @Nullable Map<String, ? extends Object> map2) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
        this.message = str;
        this.locations = list;
        this.path = list2;
        this.extensions = map;
        this.nonStandardFields = map2;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Used for backward compatibility with 2.x", replaceWith = @ReplaceWith(expression = "nonStandardFields", imports = {}))
    public static /* synthetic */ void getCustomAttributes$annotations() {
    }

    @NotNull
    public final Map<String, Object> getCustomAttributes() {
        throw new IllegalStateException("Use nonStandardFields instead");
    }

    @Nullable
    public final Map<String, Object> getExtensions() {
        return this.extensions;
    }

    @Nullable
    public final List<Location> getLocations() {
        return this.locations;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public final Map<String, Object> getNonStandardFields() {
        return this.nonStandardFields;
    }

    @Nullable
    public final List<Object> getPath() {
        return this.path;
    }

    @NotNull
    public String toString() {
        return "Error(message = " + this.message + ", locations = " + this.locations + ", path=" + this.path + ", extensions = " + this.extensions + ", nonStandardFields = " + this.nonStandardFields + ')';
    }
}
