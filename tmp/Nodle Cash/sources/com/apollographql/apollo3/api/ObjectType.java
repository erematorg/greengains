package com.apollographql.apollo3.api;

import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B/\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bB9\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\nJ\u0006\u0010\u000f\u001a\u00020\u0010R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo3/api/ObjectType;", "Lcom/apollographql/apollo3/api/CompiledNamedType;", "name", "", "keyFields", "", "implements", "Lcom/apollographql/apollo3/api/InterfaceType;", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "embeddedFields", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getEmbeddedFields", "()Ljava/util/List;", "getImplements", "getKeyFields", "newBuilder", "Lcom/apollographql/apollo3/api/ObjectType$Builder;", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ObjectType extends CompiledNamedType {
    @NotNull
    private final List<String> embeddedFields;
    @NotNull

    /* renamed from: implements  reason: not valid java name */
    private final List<InterfaceType> f65implements;
    @NotNull
    private final List<String> keyFields;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ObjectType(@NotNull String str, @NotNull List<String> list, @NotNull List<InterfaceType> list2, @NotNull List<String> list3) {
        super(str, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(list, "keyFields");
        Intrinsics.checkNotNullParameter(list2, "implements");
        Intrinsics.checkNotNullParameter(list3, "embeddedFields");
        this.keyFields = list;
        this.f65implements = list2;
        this.embeddedFields = list3;
    }

    @NotNull
    public final List<String> getEmbeddedFields() {
        return this.embeddedFields;
    }

    @NotNull
    public final List<InterfaceType> getImplements() {
        return this.f65implements;
    }

    @NotNull
    public final List<String> getKeyFields() {
        return this.keyFields;
    }

    @NotNull
    public final Builder newBuilder() {
        return new Builder(this);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u000f\u001a\u00020\u0003J\u0014\u0010\b\u001a\u00020\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tJ\u0014\u0010\u0010\u001a\u00020\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\tJ\u0014\u0010\f\u001a\u00020\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\tR\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/apollographql/apollo3/api/ObjectType$Builder;", "", "objectType", "Lcom/apollographql/apollo3/api/ObjectType;", "(Lcom/apollographql/apollo3/api/ObjectType;)V", "name", "", "(Ljava/lang/String;)V", "embeddedFields", "", "implements", "Lcom/apollographql/apollo3/api/InterfaceType;", "keyFields", "getName$apollo_api", "()Ljava/lang/String;", "build", "interfaces", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        @NotNull
        private List<String> embeddedFields;
        @NotNull

        /* renamed from: implements  reason: not valid java name */
        private List<InterfaceType> f66implements;
        @NotNull
        private List<String> keyFields;
        @NotNull
        private final String name;

        public Builder(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            this.name = str;
            this.keyFields = CollectionsKt.emptyList();
            this.f66implements = CollectionsKt.emptyList();
            this.embeddedFields = CollectionsKt.emptyList();
        }

        @NotNull
        public final ObjectType build() {
            return new ObjectType(this.name, this.keyFields, this.f66implements, this.embeddedFields);
        }

        @NotNull
        public final Builder embeddedFields(@NotNull List<String> list) {
            Intrinsics.checkNotNullParameter(list, "embeddedFields");
            this.embeddedFields = list;
            return this;
        }

        @NotNull
        public final String getName$apollo_api() {
            return this.name;
        }

        @NotNull
        public final Builder interfaces(@NotNull List<InterfaceType> list) {
            Intrinsics.checkNotNullParameter(list, "implements");
            this.f66implements = list;
            return this;
        }

        @NotNull
        public final Builder keyFields(@NotNull List<String> list) {
            Intrinsics.checkNotNullParameter(list, "keyFields");
            this.keyFields = list;
            return this;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Builder(@NotNull ObjectType objectType) {
            this(objectType.getName());
            Intrinsics.checkNotNullParameter(objectType, "objectType");
            this.keyFields = objectType.getKeyFields();
            this.f66implements = objectType.getImplements();
            this.embeddedFields = objectType.getEmbeddedFields();
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ObjectType(String str, List list, List list2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? CollectionsKt.emptyList() : list, (i3 & 4) != 0 ? CollectionsKt.emptyList() : list2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "Use the Builder instead", replaceWith = @ReplaceWith(expression = "ObjectType.Builder().keyFields(keyFields).implements(implements).build()", imports = {}))
    public ObjectType(@NotNull String str, @NotNull List<String> list, @NotNull List<InterfaceType> list2) {
        this(str, list, list2, CollectionsKt.emptyList());
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(list, "keyFields");
        Intrinsics.checkNotNullParameter(list2, "implements");
    }
}
