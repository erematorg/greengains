package com.reown.utils;

import I.a;
import S0.e;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.type.SerializableJsonRpc;
import com.reown.android.internal.utils.Time;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.net.URI;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.Kind;
import org.koin.core.definition.KoinDefinition;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.QualifierKt;
import org.koin.core.qualifier.StringQualifier;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.ext.KClassExtKt;

@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\u000b\u001a\n\u0010\f\u001a\u00020\r*\u00020\u000e\u001a.\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0012\"\b\b\u0000\u0010\u0014*\u00020\u0015*\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\u001a4\u0010\u0018\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u00190\u0012*\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00012\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0013\u001aD\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u001c0\u0012\"\u0004\b\u0000\u0010\u0014*\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00140\u001e2\u0018\u0010\u001f\u001a\u0014\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\"0 \u001a\u0016\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u0001\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0004¨\u0006&"}, d2 = {"Empty", "", "Lkotlin/String$Companion;", "getEmpty", "(Lkotlin/jvm/internal/StringCompanionObject;)Ljava/lang/String;", "DefaultId", "", "Lkotlin/Int$Companion;", "getDefaultId", "(Lkotlin/jvm/internal/IntCompanionObject;)I", "extractTimestamp", "", "isSequenceValid", "", "Lcom/reown/android/internal/common/model/Expiry;", "HexPrefix", "getHexPrefix", "addSerializerEntry", "Lorg/koin/core/definition/KoinDefinition;", "Lkotlin/reflect/KClass;", "T", "Lcom/reown/android/internal/common/model/type/SerializableJsonRpc;", "Lorg/koin/core/module/Module;", "value", "addDeserializerEntry", "Lkotlin/Pair;", "key", "addJsonAdapter", "Lcom/reown/utils/JsonAdapterEntry;", "type", "Ljava/lang/Class;", "adapter", "Lkotlin/Function1;", "Lcom/squareup/moshi/Moshi;", "Lcom/squareup/moshi/JsonAdapter;", "compareDomains", "metadataUrl", "originUrl", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUtilFunctions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UtilFunctions.kt\ncom/reown/utils/UtilFunctionsKt\n+ 2 Module.kt\norg/koin/core/module/Module\n+ 3 Module.kt\norg/koin/core/module/ModuleKt\n+ 4 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,60:1\n105#2,6:61\n111#2,5:89\n105#2,6:94\n111#2,5:122\n105#2,6:127\n111#2,5:155\n196#3,7:67\n203#3:88\n196#3,7:100\n203#3:121\n196#3,7:133\n203#3:154\n115#4,14:74\n115#4,14:107\n115#4,14:140\n*S KotlinDebug\n*F\n+ 1 UtilFunctions.kt\ncom/reown/utils/UtilFunctionsKt\n*L\n36#1:61,6\n36#1:89,5\n39#1:94,6\n39#1:122,5\n48#1:127,6\n48#1:155,5\n36#1:67,7\n36#1:88\n39#1:100,7\n39#1:121\n48#1:133,7\n48#1:154\n36#1:74,14\n39#1:107,14\n48#1:140,14\n*E\n"})
public final class UtilFunctionsKt {
    @NotNull
    public static final KoinDefinition<Pair<String, KClass<?>>> addDeserializerEntry(@NotNull Module module, @NotNull String str, @NotNull KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(module, "<this>");
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(kClass, "value");
        String fullName = KClassExtKt.getFullName(kClass);
        StringQualifier named = QualifierKt.named(str + "_" + fullName);
        a aVar = new a(str, kClass, 6);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(Pair.class), named, aVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        return new KoinDefinition<>(module, u3);
    }

    /* access modifiers changed from: private */
    public static final Pair addDeserializerEntry$lambda$1(String str, KClass kClass, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return TuplesKt.to(str, kClass);
    }

    @NotNull
    public static final <T> KoinDefinition<JsonAdapterEntry<T>> addJsonAdapter(@NotNull Module module, @NotNull Class<T> cls, @NotNull Function1<? super Moshi, ? extends JsonAdapter<T>> function1) {
        Intrinsics.checkNotNullParameter(module, "<this>");
        Intrinsics.checkNotNullParameter(cls, "type");
        Intrinsics.checkNotNullParameter(function1, "adapter");
        JsonAdapterEntry jsonAdapterEntry = new JsonAdapterEntry(cls, function1);
        StringQualifier named = QualifierKt.named(String.valueOf(jsonAdapterEntry));
        e eVar = new e(jsonAdapterEntry, 11);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(JsonAdapterEntry.class), named, eVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        return new KoinDefinition<>(module, u3);
    }

    /* access modifiers changed from: private */
    public static final JsonAdapterEntry addJsonAdapter$lambda$2(JsonAdapterEntry jsonAdapterEntry, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return jsonAdapterEntry;
    }

    @NotNull
    public static final <T extends SerializableJsonRpc> KoinDefinition<KClass<T>> addSerializerEntry(@NotNull Module module, @NotNull KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(module, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "value");
        String fullName = KClassExtKt.getFullName(kClass);
        StringQualifier named = QualifierKt.named("key_" + fullName);
        e eVar = new e(kClass, 12);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(KClass.class), named, eVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        return new KoinDefinition<>(module, u3);
    }

    /* access modifiers changed from: private */
    public static final KClass addSerializerEntry$lambda$0(KClass kClass, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return kClass;
    }

    public static final /* synthetic */ boolean compareDomains(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "metadataUrl");
        Intrinsics.checkNotNullParameter(str2, "originUrl");
        try {
            String host = new URI(str).getHost();
            Intrinsics.checkNotNullExpressionValue(host, "getHost(...)");
            String M = StringsKt__StringsKt.removePrefix(host, (CharSequence) "www.");
            String host2 = new URI(str2).getHost();
            Intrinsics.checkNotNullExpressionValue(host2, "getHost(...)");
            return Intrinsics.areEqual((Object) M, (Object) StringsKt__StringsKt.removePrefix(host2, (CharSequence) "www."));
        } catch (Exception unused) {
            return false;
        }
    }

    public static final /* synthetic */ long extractTimestamp(long j2) {
        return j2 / ((long) 1000);
    }

    public static final /* synthetic */ boolean isSequenceValid(Expiry expiry) {
        Intrinsics.checkNotNullParameter(expiry, "<this>");
        return expiry.getSeconds() > Time.getCurrentTimeInSeconds();
    }
}
