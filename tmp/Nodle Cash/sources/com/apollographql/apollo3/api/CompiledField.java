package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Executable;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001 BK\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0002\u0010\rJ\u000e\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000fR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006!"}, d2 = {"Lcom/apollographql/apollo3/api/CompiledField;", "Lcom/apollographql/apollo3/api/CompiledSelection;", "name", "", "type", "Lcom/apollographql/apollo3/api/CompiledType;", "alias", "condition", "", "Lcom/apollographql/apollo3/api/CompiledCondition;", "arguments", "Lcom/apollographql/apollo3/api/CompiledArgument;", "selections", "(Ljava/lang/String;Lcom/apollographql/apollo3/api/CompiledType;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getAlias", "()Ljava/lang/String;", "getArguments", "()Ljava/util/List;", "getCondition", "getName", "responseName", "getResponseName", "getSelections", "getType", "()Lcom/apollographql/apollo3/api/CompiledType;", "nameWithArguments", "variables", "Lcom/apollographql/apollo3/api/Executable$Variables;", "newBuilder", "Lcom/apollographql/apollo3/api/CompiledField$Builder;", "resolveArgument", "", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nCompiledGraphQL.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CompiledGraphQL.kt\ncom/apollographql/apollo3/api/CompiledField\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,492:1\n288#2,2:493\n1747#2,3:495\n819#2:498\n847#2,2:499\n1194#2,2:501\n1222#2,4:503\n1238#2,4:509\n453#3:507\n403#3:508\n*S KotlinDebug\n*F\n+ 1 CompiledGraphQL.kt\ncom/apollographql/apollo3/api/CompiledField\n*L\n41#1:493,2\n50#1:495,3\n52#1:498\n52#1:499,2\n59#1:501,2\n59#1:503,4\n59#1:509,4\n59#1:507\n59#1:508\n*E\n"})
public final class CompiledField extends CompiledSelection {
    @Nullable
    private final String alias;
    @NotNull
    private final List<CompiledArgument> arguments;
    @NotNull
    private final List<CompiledCondition> condition;
    @NotNull
    private final String name;
    @NotNull
    private final List<CompiledSelection> selections;
    @NotNull
    private final CompiledType type;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CompiledField(@NotNull String str, @NotNull CompiledType compiledType, @Nullable String str2, @NotNull List<CompiledCondition> list, @NotNull List<CompiledArgument> list2, @NotNull List<? extends CompiledSelection> list3) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(compiledType, "type");
        Intrinsics.checkNotNullParameter(list, "condition");
        Intrinsics.checkNotNullParameter(list2, "arguments");
        Intrinsics.checkNotNullParameter(list3, "selections");
        this.name = str;
        this.type = compiledType;
        this.alias = str2;
        this.condition = list;
        this.arguments = list2;
        this.selections = list3;
    }

    @Nullable
    public final String getAlias() {
        return this.alias;
    }

    @NotNull
    public final List<CompiledArgument> getArguments() {
        return this.arguments;
    }

    @NotNull
    public final List<CompiledCondition> getCondition() {
        return this.condition;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getResponseName() {
        String str = this.alias;
        return str == null ? this.name : str;
    }

    @NotNull
    public final List<CompiledSelection> getSelections() {
        return this.selections;
    }

    @NotNull
    public final CompiledType getType() {
        return this.type;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005d  */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String nameWithArguments(@org.jetbrains.annotations.NotNull com.apollographql.apollo3.api.Executable.Variables r5) {
        /*
            r4 = this;
            java.lang.String r0 = "variables"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.util.List<com.apollographql.apollo3.api.CompiledArgument> r0 = r4.arguments
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r1 = r0 instanceof java.util.Collection
            if (r1 == 0) goto L_0x0018
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0018
            goto L_0x0052
        L_0x0018:
            java.util.Iterator r0 = r0.iterator()
        L_0x001c:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0052
            java.lang.Object r1 = r0.next()
            com.apollographql.apollo3.api.CompiledArgument r1 = (com.apollographql.apollo3.api.CompiledArgument) r1
            boolean r1 = r1.isPagination()
            if (r1 == 0) goto L_0x001c
            java.util.List<com.apollographql.apollo3.api.CompiledArgument> r0 = r4.arguments
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x003b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0054
            java.lang.Object r2 = r0.next()
            r3 = r2
            com.apollographql.apollo3.api.CompiledArgument r3 = (com.apollographql.apollo3.api.CompiledArgument) r3
            boolean r3 = r3.isPagination()
            if (r3 != 0) goto L_0x003b
            r1.add(r2)
            goto L_0x003b
        L_0x0052:
            java.util.List<com.apollographql.apollo3.api.CompiledArgument> r1 = r4.arguments
        L_0x0054:
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L_0x005d
            java.lang.String r4 = r4.name
            return r4
        L_0x005d:
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            r0 = 16
            int r0 = androidx.compose.animation.core.a.h(r1, r0)
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>(r0)
            java.util.Iterator r0 = r1.iterator()
        L_0x006e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0083
            java.lang.Object r1 = r0.next()
            r3 = r1
            com.apollographql.apollo3.api.CompiledArgument r3 = (com.apollographql.apollo3.api.CompiledArgument) r3
            java.lang.String r3 = r3.getName()
            r2.put(r3, r1)
            goto L_0x006e
        L_0x0083:
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            int r1 = r2.size()
            int r1 = kotlin.collections.MapsKt.mapCapacity(r1)
            r0.<init>(r1)
            java.util.Set r1 = r2.entrySet()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x009a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00b8
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Object r2 = r2.getValue()
            com.apollographql.apollo3.api.CompiledArgument r2 = (com.apollographql.apollo3.api.CompiledArgument) r2
            java.lang.Object r2 = r2.getValue()
            r0.put(r3, r2)
            goto L_0x009a
        L_0x00b8:
            java.lang.Object r5 = com.apollographql.apollo3.api.CompiledGraphQL.resolveVariables(r0, r5)
            okio.Buffer r0 = new okio.Buffer     // Catch:{ Exception -> 0x00ee }
            r0.<init>()     // Catch:{ Exception -> 0x00ee }
            com.apollographql.apollo3.api.json.BufferedSinkJsonWriter r1 = new com.apollographql.apollo3.api.json.BufferedSinkJsonWriter     // Catch:{ Exception -> 0x00ee }
            r2 = 2
            r3 = 0
            r1.<init>(r0, r3, r2, r3)     // Catch:{ Exception -> 0x00ee }
            com.apollographql.apollo3.api.json.JsonWriters.writeAny(r1, r5)     // Catch:{ Exception -> 0x00ee }
            r1.close()     // Catch:{ Exception -> 0x00ee }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ee }
            r5.<init>()     // Catch:{ Exception -> 0x00ee }
            java.lang.String r4 = r4.name     // Catch:{ Exception -> 0x00ee }
            r5.append(r4)     // Catch:{ Exception -> 0x00ee }
            r4 = 40
            r5.append(r4)     // Catch:{ Exception -> 0x00ee }
            java.lang.String r4 = r0.readUtf8()     // Catch:{ Exception -> 0x00ee }
            r5.append(r4)     // Catch:{ Exception -> 0x00ee }
            r4 = 41
            r5.append(r4)     // Catch:{ Exception -> 0x00ee }
            java.lang.String r4 = r5.toString()     // Catch:{ Exception -> 0x00ee }
            return r4
        L_0x00ee:
            r4 = move-exception
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.CompiledField.nameWithArguments(com.apollographql.apollo3.api.Executable$Variables):java.lang.String");
    }

    @NotNull
    public final Builder newBuilder() {
        return new Builder(this);
    }

    @Nullable
    public final Object resolveArgument(@NotNull String str, @NotNull Executable.Variables variables) {
        Object obj;
        Object obj2;
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(variables, "variables");
        Iterator it = this.arguments.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it.next();
            if (Intrinsics.areEqual((Object) ((CompiledArgument) obj2).getName(), (Object) str)) {
                break;
            }
        }
        CompiledArgument compiledArgument = (CompiledArgument) obj2;
        if (compiledArgument != null) {
            obj = compiledArgument.getValue();
        }
        return CompiledGraphQL.resolveVariables(obj, variables);
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u0006J\u0014\u0010\u000b\u001a\u00020\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0006\u0010\u0016\u001a\u00020\u0003J\u0014\u0010\u000e\u001a\u00020\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fJ\u0014\u0010\u0012\u001a\u00020\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\fR\u0010\u0010\n\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\fX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/apollographql/apollo3/api/CompiledField$Builder;", "", "compiledField", "Lcom/apollographql/apollo3/api/CompiledField;", "(Lcom/apollographql/apollo3/api/CompiledField;)V", "name", "", "type", "Lcom/apollographql/apollo3/api/CompiledType;", "(Ljava/lang/String;Lcom/apollographql/apollo3/api/CompiledType;)V", "alias", "arguments", "", "Lcom/apollographql/apollo3/api/CompiledArgument;", "condition", "Lcom/apollographql/apollo3/api/CompiledCondition;", "getName", "()Ljava/lang/String;", "selections", "Lcom/apollographql/apollo3/api/CompiledSelection;", "getType", "()Lcom/apollographql/apollo3/api/CompiledType;", "build", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        @Nullable
        private String alias;
        @NotNull
        private List<CompiledArgument> arguments;
        @NotNull
        private List<CompiledCondition> condition;
        @NotNull
        private final String name;
        @NotNull
        private List<? extends CompiledSelection> selections;
        @NotNull
        private final CompiledType type;

        public Builder(@NotNull String str, @NotNull CompiledType compiledType) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(compiledType, "type");
            this.name = str;
            this.type = compiledType;
            this.condition = CollectionsKt.emptyList();
            this.arguments = CollectionsKt.emptyList();
            this.selections = CollectionsKt.emptyList();
        }

        @NotNull
        public final Builder alias(@Nullable String str) {
            this.alias = str;
            return this;
        }

        @NotNull
        public final Builder arguments(@NotNull List<CompiledArgument> list) {
            Intrinsics.checkNotNullParameter(list, "arguments");
            this.arguments = list;
            return this;
        }

        @NotNull
        public final CompiledField build() {
            return new CompiledField(this.name, this.type, this.alias, this.condition, this.arguments, this.selections);
        }

        @NotNull
        public final Builder condition(@NotNull List<CompiledCondition> list) {
            Intrinsics.checkNotNullParameter(list, "condition");
            this.condition = list;
            return this;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final CompiledType getType() {
            return this.type;
        }

        @NotNull
        public final Builder selections(@NotNull List<? extends CompiledSelection> list) {
            Intrinsics.checkNotNullParameter(list, "selections");
            this.selections = list;
            return this;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Builder(@NotNull CompiledField compiledField) {
            this(compiledField.getName(), compiledField.getType());
            Intrinsics.checkNotNullParameter(compiledField, "compiledField");
            this.alias = compiledField.getAlias();
            this.condition = compiledField.getCondition();
            this.arguments = compiledField.getArguments();
            this.selections = compiledField.getSelections();
        }
    }
}
