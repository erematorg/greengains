package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Executable;
import com.apollographql.apollo3.api.ObjectType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007\u001a\n\u0010\u001e\u001a\u00020\u001f*\u00020 \u001a\u0010\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"*\u00020 \u001a\u0011\u0010$\u001a\u00020%*\u00020&H\u0007¢\u0006\u0002\b'\u001a\u0011\u0010(\u001a\u00020)*\u00020&H\u0007¢\u0006\u0002\b*\"\u0016\u0010\u0000\u001a\u00020\u00018\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003\"\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003\"\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003\"\u0016\u0010\u000b\u001a\u00020\u00018\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0003\"\u0016\u0010\r\u001a\u00020\u00018\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0003\"\u0016\u0010\u000f\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0003\"\u0016\u0010\u0011\u001a\u00020\u00018\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0003\"\u0016\u0010\u0013\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0003\"\u0016\u0010\u0015\u001a\u00020\u00018\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0003\"\u0016\u0010\u0017\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0003¨\u0006+"}, d2 = {"CompiledBooleanType", "Lcom/apollographql/apollo3/api/ScalarType;", "getCompiledBooleanType$annotations", "()V", "CompiledDirectiveType", "Lcom/apollographql/apollo3/api/ObjectType;", "getCompiledDirectiveType$annotations", "CompiledEnumValueType", "getCompiledEnumValueType$annotations", "CompiledFieldType", "getCompiledFieldType$annotations", "CompiledFloatType", "getCompiledFloatType$annotations", "CompiledIDType", "getCompiledIDType$annotations", "CompiledInputValueType", "getCompiledInputValueType$annotations", "CompiledIntType", "getCompiledIntType$annotations", "CompiledSchemaType", "getCompiledSchemaType$annotations", "CompiledStringType", "getCompiledStringType$annotations", "CompiledTypeType", "getCompiledTypeType$annotations", "resolveVariables", "", "value", "variables", "Lcom/apollographql/apollo3/api/Executable$Variables;", "isComposite", "", "Lcom/apollographql/apollo3/api/CompiledNamedType;", "keyFields", "", "", "list", "Lcom/apollographql/apollo3/api/CompiledListType;", "Lcom/apollographql/apollo3/api/CompiledType;", "-list", "notNull", "Lcom/apollographql/apollo3/api/CompiledNotNullType;", "-notNull", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nCompiledGraphQL.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CompiledGraphQL.kt\ncom/apollographql/apollo3/api/CompiledGraphQL\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,492:1\n453#2:493\n403#2:494\n1238#3,4:495\n1045#3:499\n1549#3:500\n1620#3,3:501\n*S KotlinDebug\n*F\n+ 1 CompiledGraphQL.kt\ncom/apollographql/apollo3/api/CompiledGraphQL\n*L\n401#1:493\n401#1:494\n401#1:495,4\n404#1:499\n409#1:500\n409#1:501,3\n*E\n"})
@JvmName(name = "CompiledGraphQL")
public final class CompiledGraphQL {
    @NotNull
    @JvmField
    public static final ScalarType CompiledBooleanType = new ScalarType("Boolean");
    @NotNull
    @JvmField
    public static final ObjectType CompiledDirectiveType = new ObjectType.Builder("__Directive").build();
    @NotNull
    @JvmField
    public static final ObjectType CompiledEnumValueType = new ObjectType.Builder("__EnumValue").build();
    @NotNull
    @JvmField
    public static final ObjectType CompiledFieldType = new ObjectType.Builder("__Field").build();
    @NotNull
    @JvmField
    public static final ScalarType CompiledFloatType = new ScalarType("Float");
    @NotNull
    @JvmField
    public static final ScalarType CompiledIDType = new ScalarType(SchemaSymbols.ATTVAL_ID);
    @NotNull
    @JvmField
    public static final ObjectType CompiledInputValueType = new ObjectType.Builder("__InputValue").build();
    @NotNull
    @JvmField
    public static final ScalarType CompiledIntType = new ScalarType("Int");
    @NotNull
    @JvmField
    public static final ObjectType CompiledSchemaType = new ObjectType.Builder("__Schema").build();
    @NotNull
    @JvmField
    public static final ScalarType CompiledStringType = new ScalarType("String");
    @NotNull
    @JvmField
    public static final ObjectType CompiledTypeType = new ObjectType.Builder("__Type").build();

    @NotNull
    @JvmName(name = "-list")
    /* renamed from: -list  reason: not valid java name */
    public static final CompiledListType m8210list(@NotNull CompiledType compiledType) {
        Intrinsics.checkNotNullParameter(compiledType, "<this>");
        return new CompiledListType(compiledType);
    }

    @NotNull
    @JvmName(name = "-notNull")
    /* renamed from: -notNull  reason: not valid java name */
    public static final CompiledNotNullType m8211notNull(@NotNull CompiledType compiledType) {
        Intrinsics.checkNotNullParameter(compiledType, "<this>");
        return new CompiledNotNullType(compiledType);
    }

    @Deprecated(message = "Use the generated CustomScalarType instead")
    public static /* synthetic */ void getCompiledBooleanType$annotations() {
    }

    public static /* synthetic */ void getCompiledDirectiveType$annotations() {
    }

    public static /* synthetic */ void getCompiledEnumValueType$annotations() {
    }

    public static /* synthetic */ void getCompiledFieldType$annotations() {
    }

    @Deprecated(message = "Use the generated CustomScalarType instead")
    public static /* synthetic */ void getCompiledFloatType$annotations() {
    }

    @Deprecated(message = "Use the generated CustomScalarType instead")
    public static /* synthetic */ void getCompiledIDType$annotations() {
    }

    public static /* synthetic */ void getCompiledInputValueType$annotations() {
    }

    @Deprecated(message = "Use the generated CustomScalarType instead")
    public static /* synthetic */ void getCompiledIntType$annotations() {
    }

    public static /* synthetic */ void getCompiledSchemaType$annotations() {
    }

    @Deprecated(message = "Use the generated CustomScalarType instead")
    public static /* synthetic */ void getCompiledStringType$annotations() {
    }

    public static /* synthetic */ void getCompiledTypeType$annotations() {
    }

    public static final boolean isComposite(@NotNull CompiledNamedType compiledNamedType) {
        Intrinsics.checkNotNullParameter(compiledNamedType, "<this>");
        if (compiledNamedType instanceof UnionType ? true : compiledNamedType instanceof InterfaceType) {
            return true;
        }
        return compiledNamedType instanceof ObjectType;
    }

    @NotNull
    public static final List<String> keyFields(@NotNull CompiledNamedType compiledNamedType) {
        Intrinsics.checkNotNullParameter(compiledNamedType, "<this>");
        return compiledNamedType instanceof InterfaceType ? ((InterfaceType) compiledNamedType).getKeyFields() : compiledNamedType instanceof ObjectType ? ((ObjectType) compiledNamedType).getKeyFields() : CollectionsKt.emptyList();
    }

    @Deprecated(message = "This shouldn't be part of the public API and will be removed in Apollo Kotlin 4. If you needed this, please open an issue.")
    @Nullable
    public static final Object resolveVariables(@Nullable Object obj, @NotNull Executable.Variables variables) {
        Intrinsics.checkNotNullParameter(variables, "variables");
        if (obj == null) {
            return null;
        }
        if (obj instanceof CompiledVariable) {
            return variables.getValueMap().get(((CompiledVariable) obj).getName());
        }
        if (obj instanceof Map) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
            Map map = (Map) obj;
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
            for (Map.Entry entry : map.entrySet()) {
                linkedHashMap.put(entry.getKey(), resolveVariables(entry.getValue(), variables));
            }
            return MapsKt.toMap(CollectionsKt.sortedWith(MapsKt.toList(linkedHashMap), new CompiledGraphQL$resolveVariables$$inlined$sortedBy$1()));
        } else if (!(obj instanceof List)) {
            return obj;
        } else {
            Iterable<Object> iterable = (Iterable) obj;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
            for (Object resolveVariables : iterable) {
                arrayList.add(resolveVariables(resolveVariables, variables));
            }
            return arrayList;
        }
    }
}
