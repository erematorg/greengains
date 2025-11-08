package com.reown.foundation.di;

import com.reown.foundation.crypto.data.repository.ClientIdJwtRepository;
import com.reown.sign.di.c;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.Kind;
import org.koin.core.definition.KoinDefinition;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.dsl.ModuleDSLKt;
import u1.a;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"cryptoModule", "Lorg/koin/core/module/Module;", "foundation"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFoundationCryptoModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FoundationCryptoModule.kt\ncom/reown/foundation/di/FoundationCryptoModuleKt\n+ 2 Module.kt\norg/koin/core/module/Module\n+ 3 Module.kt\norg/koin/core/module/ModuleKt\n+ 4 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,19:1\n105#2,6:20\n111#2,5:48\n196#3,7:26\n203#3:47\n115#4,14:33\n*S KotlinDebug\n*F\n+ 1 FoundationCryptoModule.kt\ncom/reown/foundation/di/FoundationCryptoModuleKt\n*L\n13#1:20,6\n13#1:48,5\n13#1:26,7\n13#1:47\n13#1:33,14\n*E\n"})
public final /* synthetic */ class FoundationCryptoModuleKt {
    @NotNull
    public static final Module cryptoModule() {
        return ModuleDSLKt.module$default(false, new a(1), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Unit cryptoModule$lambda$1(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        c cVar = new c(16);
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ClientIdJwtRepository.class), (Qualifier) null, cVar, Kind.Singleton, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final ClientIdJwtRepository cryptoModule$lambda$1$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new FoundationCryptoModuleKt$cryptoModule$1$1$1();
    }
}
