package com.reown.android.push.network.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.push.notifications.PushMessagingService;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001:\u0002\u001a\u001bB5\u0012\u0010\b\u0001\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\bHÆ\u0003J7\u0010\u0013\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\bHÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/reown/android/push/network/model/PushResponse;", "", "errors", "", "Lcom/reown/android/push/network/model/PushResponse$Error;", "fields", "Lcom/reown/android/push/network/model/PushResponse$Field;", "status", "", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getErrors", "()Ljava/util/List;", "getFields", "getStatus", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Error", "Field", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushResponse {
    @Nullable
    private final List<Error> errors;
    @Nullable
    private final List<Field> fields;
    @NotNull
    private final String status;

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/android/push/network/model/PushResponse$Error;", "", "message", "", "name", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Error {
        @NotNull
        private final String message;
        @NotNull
        private final String name;

        public Error(@NotNull @Json(name = "message") String str, @NotNull @Json(name = "name") String str2) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            Intrinsics.checkNotNullParameter(str2, "name");
            this.message = str;
            this.name = str2;
        }

        public static /* synthetic */ Error copy$default(Error error, String str, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = error.message;
            }
            if ((i3 & 2) != 0) {
                str2 = error.name;
            }
            return error.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.message;
        }

        @NotNull
        public final String component2() {
            return this.name;
        }

        @NotNull
        public final Error copy(@NotNull @Json(name = "message") String str, @NotNull @Json(name = "name") String str2) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            Intrinsics.checkNotNullParameter(str2, "name");
            return new Error(str, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Error)) {
                return false;
            }
            Error error = (Error) obj;
            return Intrinsics.areEqual((Object) this.message, (Object) error.message) && Intrinsics.areEqual((Object) this.name, (Object) error.name);
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        public int hashCode() {
            return this.name.hashCode() + (this.message.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return C0118y.g("Error(message=", this.message, ", name=", this.name, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/reown/android/push/network/model/PushResponse$Field;", "", "description", "", "field", "location", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getField", "getLocation", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Field {
        @NotNull
        private final String description;
        @NotNull
        private final String field;
        @NotNull
        private final String location;

        public Field(@NotNull @Json(name = "description") String str, @NotNull @Json(name = "field") String str2, @NotNull @Json(name = "location") String str3) {
            Intrinsics.checkNotNullParameter(str, "description");
            Intrinsics.checkNotNullParameter(str2, "field");
            Intrinsics.checkNotNullParameter(str3, FirebaseAnalytics.Param.LOCATION);
            this.description = str;
            this.field = str2;
            this.location = str3;
        }

        public static /* synthetic */ Field copy$default(Field field2, String str, String str2, String str3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = field2.description;
            }
            if ((i3 & 2) != 0) {
                str2 = field2.field;
            }
            if ((i3 & 4) != 0) {
                str3 = field2.location;
            }
            return field2.copy(str, str2, str3);
        }

        @NotNull
        public final String component1() {
            return this.description;
        }

        @NotNull
        public final String component2() {
            return this.field;
        }

        @NotNull
        public final String component3() {
            return this.location;
        }

        @NotNull
        public final Field copy(@NotNull @Json(name = "description") String str, @NotNull @Json(name = "field") String str2, @NotNull @Json(name = "location") String str3) {
            Intrinsics.checkNotNullParameter(str, "description");
            Intrinsics.checkNotNullParameter(str2, "field");
            Intrinsics.checkNotNullParameter(str3, FirebaseAnalytics.Param.LOCATION);
            return new Field(str, str2, str3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Field)) {
                return false;
            }
            Field field2 = (Field) obj;
            return Intrinsics.areEqual((Object) this.description, (Object) field2.description) && Intrinsics.areEqual((Object) this.field, (Object) field2.field) && Intrinsics.areEqual((Object) this.location, (Object) field2.location);
        }

        @NotNull
        public final String getDescription() {
            return this.description;
        }

        @NotNull
        public final String getField() {
            return this.field;
        }

        @NotNull
        public final String getLocation() {
            return this.location;
        }

        public int hashCode() {
            return this.location.hashCode() + a.i(this.field, this.description.hashCode() * 31, 31);
        }

        @NotNull
        public String toString() {
            String str = this.description;
            String str2 = this.field;
            return A.a.n(C0118y.l("Field(description=", str, ", field=", str2, ", location="), this.location, ")");
        }
    }

    public PushResponse(@Nullable @Json(name = "errors") List<Error> list, @Nullable @Json(name = "fields") List<Field> list2, @NotNull @Json(name = "status") String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
        this.errors = list;
        this.fields = list2;
        this.status = str;
    }

    public static /* synthetic */ PushResponse copy$default(PushResponse pushResponse, List<Error> list, List<Field> list2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            list = pushResponse.errors;
        }
        if ((i3 & 2) != 0) {
            list2 = pushResponse.fields;
        }
        if ((i3 & 4) != 0) {
            str = pushResponse.status;
        }
        return pushResponse.copy(list, list2, str);
    }

    @Nullable
    public final List<Error> component1() {
        return this.errors;
    }

    @Nullable
    public final List<Field> component2() {
        return this.fields;
    }

    @NotNull
    public final String component3() {
        return this.status;
    }

    @NotNull
    public final PushResponse copy(@Nullable @Json(name = "errors") List<Error> list, @Nullable @Json(name = "fields") List<Field> list2, @NotNull @Json(name = "status") String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
        return new PushResponse(list, list2, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PushResponse)) {
            return false;
        }
        PushResponse pushResponse = (PushResponse) obj;
        return Intrinsics.areEqual((Object) this.errors, (Object) pushResponse.errors) && Intrinsics.areEqual((Object) this.fields, (Object) pushResponse.fields) && Intrinsics.areEqual((Object) this.status, (Object) pushResponse.status);
    }

    @Nullable
    public final List<Error> getErrors() {
        return this.errors;
    }

    @Nullable
    public final List<Field> getFields() {
        return this.fields;
    }

    @NotNull
    public final String getStatus() {
        return this.status;
    }

    public int hashCode() {
        List<Error> list = this.errors;
        int i3 = 0;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<Field> list2 = this.fields;
        if (list2 != null) {
            i3 = list2.hashCode();
        }
        return this.status.hashCode() + ((hashCode + i3) * 31);
    }

    @NotNull
    public String toString() {
        List<Error> list = this.errors;
        List<Field> list2 = this.fields;
        String str = this.status;
        StringBuilder sb = new StringBuilder("PushResponse(errors=");
        sb.append(list);
        sb.append(", fields=");
        sb.append(list2);
        sb.append(", status=");
        return A.a.n(sb, str, ")");
    }
}
