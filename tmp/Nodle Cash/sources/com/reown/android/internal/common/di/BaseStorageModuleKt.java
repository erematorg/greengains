package com.reown.android.internal.common.di;

import S0.d;
import app.cash.sqldelight.ColumnAdapter;
import app.cash.sqldelight.EnumColumnAdapter;
import app.cash.sqldelight.db.SqlDriver;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.reown.android.di.AndroidBuildVariantDITags;
import com.reown.android.di.a;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.model.AppMetaDataType;
import com.reown.android.internal.common.model.TelemetryEnabled;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.internal.common.model.Validation;
import com.reown.android.internal.common.storage.events.EventsRepository;
import com.reown.android.internal.common.storage.identity.IdentitiesStorageRepository;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepository;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.internal.common.storage.pairing.PairingStorageRepository;
import com.reown.android.internal.common.storage.pairing.PairingStorageRepositoryInterface;
import com.reown.android.internal.common.storage.push_messages.PushMessagesRepository;
import com.reown.android.internal.common.storage.rpc.JsonRpcHistory;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.android.sdk.core.AndroidCoreDatabase;
import com.reown.android.sdk.storage.data.dao.EventDao;
import com.reown.android.sdk.storage.data.dao.EventQueries;
import com.reown.android.sdk.storage.data.dao.IdentitiesQueries;
import com.reown.android.sdk.storage.data.dao.JsonRpcHistoryDao;
import com.reown.android.sdk.storage.data.dao.JsonRpcHistoryQueries;
import com.reown.android.sdk.storage.data.dao.MetaData;
import com.reown.android.sdk.storage.data.dao.MetaDataQueries;
import com.reown.android.sdk.storage.data.dao.PairingQueries;
import com.reown.android.sdk.storage.data.dao.PushMessageQueries;
import com.reown.android.sdk.storage.data.dao.VerifyContext;
import com.reown.android.sdk.storage.data.dao.VerifyContextQueries;
import com.reown.android.sdk.storage.data.dao.VerifyPublicKeyQueries;
import com.reown.foundation.util.Logger;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.Kind;
import org.koin.core.definition.KoinDefinition;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.QualifierKt;
import org.koin.core.qualifier.StringQualifier;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.dsl.ModuleDSLKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003¨\u0006\u0005"}, d2 = {"baseStorageModule", "Lorg/koin/core/module/Module;", "storagePrefix", "", "packageName", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBaseStorageModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseStorageModule.kt\ncom/reown/android/internal/common/di/BaseStorageModuleKt\n+ 2 EnumColumnAdapter.kt\napp/cash/sqldelight/EnumColumnAdapterKt\n+ 3 Scope.kt\norg/koin/core/scope/Scope\n+ 4 Module.kt\norg/koin/core/module/Module\n+ 5 Module.kt\norg/koin/core/module/ModuleKt\n+ 6 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,140:1\n30#2:141\n30#2:142\n30#2:143\n138#3,5:144\n138#3,5:149\n138#3,5:154\n138#3,5:159\n138#3,5:164\n138#3,5:169\n138#3,5:174\n138#3,5:179\n138#3,5:184\n138#3,5:189\n138#3,5:194\n138#3,5:199\n138#3,5:204\n138#3,5:209\n138#3,5:214\n138#3,5:219\n138#3,5:224\n138#3,5:229\n138#3,5:234\n138#3,5:239\n138#3,5:244\n138#3,5:249\n105#4,6:254\n111#4,5:282\n105#4,6:287\n111#4,5:315\n105#4,6:320\n111#4,5:348\n105#4,6:353\n111#4,5:381\n105#4,6:386\n111#4,5:414\n105#4,6:419\n111#4,5:447\n105#4,6:452\n111#4,5:480\n105#4,6:485\n111#4,5:513\n105#4,6:518\n111#4,5:546\n105#4,6:551\n111#4,5:579\n105#4,6:584\n111#4,5:612\n105#4,6:617\n111#4,5:645\n105#4,6:650\n111#4,5:678\n105#4,6:683\n111#4,5:711\n105#4,6:716\n111#4,5:744\n105#4,6:749\n111#4,5:777\n105#4,6:782\n111#4,5:810\n105#4,6:815\n111#4,5:843\n105#4,6:848\n111#4,5:876\n105#4,6:881\n111#4,5:909\n105#4,6:914\n111#4,5:942\n105#4,6:947\n111#4,5:975\n196#5,7:260\n203#5:281\n196#5,7:293\n203#5:314\n196#5,7:326\n203#5:347\n196#5,7:359\n203#5:380\n196#5,7:392\n203#5:413\n196#5,7:425\n203#5:446\n196#5,7:458\n203#5:479\n196#5,7:491\n203#5:512\n196#5,7:524\n203#5:545\n196#5,7:557\n203#5:578\n196#5,7:590\n203#5:611\n196#5,7:623\n203#5:644\n196#5,7:656\n203#5:677\n196#5,7:689\n203#5:710\n196#5,7:722\n203#5:743\n196#5,7:755\n203#5:776\n196#5,7:788\n203#5:809\n196#5,7:821\n203#5:842\n196#5,7:854\n203#5:875\n196#5,7:887\n203#5:908\n196#5,7:920\n203#5:941\n196#5,7:953\n203#5:974\n115#6,14:267\n115#6,14:300\n115#6,14:333\n115#6,14:366\n115#6,14:399\n115#6,14:432\n115#6,14:465\n115#6,14:498\n115#6,14:531\n115#6,14:564\n115#6,14:597\n115#6,14:630\n115#6,14:663\n115#6,14:696\n115#6,14:729\n115#6,14:762\n115#6,14:795\n115#6,14:828\n115#6,14:861\n115#6,14:894\n115#6,14:927\n115#6,14:960\n*S KotlinDebug\n*F\n+ 1 BaseStorageModule.kt\ncom/reown/android/internal/common/di/BaseStorageModuleKt\n*L\n67#1:141\n69#1:142\n71#1:143\n74#1:144,5\n76#1:149,5\n77#1:154,5\n80#1:159,5\n83#1:164,5\n86#1:169,5\n104#1:174,5\n109#1:179,5\n111#1:184,5\n113#1:189,5\n115#1:194,5\n117#1:199,5\n119#1:204,5\n121#1:209,5\n123#1:214,5\n125#1:219,5\n127#1:224,5\n129#1:229,5\n131#1:234,5\n133#1:239,5\n135#1:244,5\n137#1:249,5\n32#1:254,6\n32#1:282,5\n45#1:287,6\n45#1:315,5\n67#1:320,6\n67#1:348,5\n69#1:353,6\n69#1:381,5\n71#1:386,6\n71#1:414,5\n90#1:419,6\n90#1:447,5\n109#1:452,6\n109#1:480,5\n111#1:485,6\n111#1:513,5\n113#1:518,6\n113#1:546,5\n115#1:551,6\n115#1:579,5\n117#1:584,6\n117#1:612,5\n119#1:617,6\n119#1:645,5\n121#1:650,6\n121#1:678,5\n123#1:683,6\n123#1:711,5\n125#1:716,6\n125#1:744,5\n127#1:749,6\n127#1:777,5\n129#1:782,6\n129#1:810,5\n131#1:815,6\n131#1:843,5\n133#1:848,6\n133#1:876,5\n135#1:881,6\n135#1:909,5\n137#1:914,6\n137#1:942,5\n139#1:947,6\n139#1:975,5\n32#1:260,7\n32#1:281\n45#1:293,7\n45#1:314\n67#1:326,7\n67#1:347\n69#1:359,7\n69#1:380\n71#1:392,7\n71#1:413\n90#1:425,7\n90#1:446\n109#1:458,7\n109#1:479\n111#1:491,7\n111#1:512\n113#1:524,7\n113#1:545\n115#1:557,7\n115#1:578\n117#1:590,7\n117#1:611\n119#1:623,7\n119#1:644\n121#1:656,7\n121#1:677\n123#1:689,7\n123#1:710\n125#1:722,7\n125#1:743\n127#1:755,7\n127#1:776\n129#1:788,7\n129#1:809\n131#1:821,7\n131#1:842\n133#1:854,7\n133#1:875\n135#1:887,7\n135#1:908\n137#1:920,7\n137#1:941\n139#1:953,7\n139#1:974\n32#1:267,14\n45#1:300,14\n67#1:333,14\n69#1:366,14\n71#1:399,14\n90#1:432,14\n109#1:465,14\n111#1:498,14\n113#1:531,14\n115#1:564,14\n117#1:597,14\n119#1:630,14\n121#1:663,14\n123#1:696,14\n125#1:729,14\n127#1:762,14\n129#1:795,14\n131#1:828,14\n133#1:861,14\n135#1:894,14\n137#1:927,14\n139#1:960,14\n*E\n"})
public final class BaseStorageModuleKt {
    @NotNull
    public static final Module baseStorageModule(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "storagePrefix");
        Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        return ModuleDSLKt.module$default(false, new a(str2, str, 1), 1, (Object) null);
    }

    public static /* synthetic */ Module baseStorageModule$default(String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>");
        }
        return baseStorageModule(str, str2);
    }

    /* access modifiers changed from: private */
    public static final Unit baseStorageModule$lambda$23(String str, String str2, Module module) {
        Module module2 = module;
        Intrinsics.checkNotNullParameter(module2, "$this$module");
        Qualifier named = QualifierKt.named(AndroidCommonDITags.COLUMN_ADAPTER_LIST);
        R1.a aVar = new R1.a(15);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        Class<ColumnAdapter> cls = ColumnAdapter.class;
        SingleInstanceFactory u3 = A.a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(cls), named, aVar, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module2, u3);
        Qualifier named2 = QualifierKt.named(AndroidCommonDITags.COLUMN_ADAPTER_MAP);
        R1.a aVar2 = new R1.a(17);
        SingleInstanceFactory u4 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(cls), named2, aVar2, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module2, u4);
        Qualifier named3 = QualifierKt.named(AndroidCommonDITags.COLUMN_ADAPTER_APPMETADATATYPE);
        R1.a aVar3 = new R1.a(18);
        SingleInstanceFactory u5 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(cls), named3, aVar3, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module2, u5);
        Qualifier named4 = QualifierKt.named(AndroidCommonDITags.COLUMN_ADAPTER_TRANSPORT_TYPE);
        R1.a aVar4 = new R1.a(19);
        SingleInstanceFactory u6 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(cls), named4, aVar4, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module2, u6);
        Qualifier named5 = QualifierKt.named(AndroidCommonDITags.COLUMN_ADAPTER_VALIDATION);
        R1.a aVar5 = new R1.a(20);
        SingleInstanceFactory u7 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(cls), named5, aVar5, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module2, u7);
        Qualifier named6 = QualifierKt.named(AndroidBuildVariantDITags.ANDROID_CORE_DATABASE);
        R1.a aVar6 = new R1.a(21);
        SingleInstanceFactory u8 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(AndroidCoreDatabase.class), named6, aVar6, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u8);
        }
        new KoinDefinition(module2, u8);
        R1.a aVar7 = new R1.a(22);
        SingleInstanceFactory u9 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(JsonRpcHistoryQueries.class), (Qualifier) null, aVar7, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u9);
        }
        new KoinDefinition(module2, u9);
        R1.a aVar8 = new R1.a(23);
        SingleInstanceFactory u10 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(PairingQueries.class), (Qualifier) null, aVar8, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u10);
        }
        new KoinDefinition(module2, u10);
        R1.a aVar9 = new R1.a(25);
        SingleInstanceFactory u11 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(MetaDataQueries.class), (Qualifier) null, aVar9, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u11);
        }
        new KoinDefinition(module2, u11);
        R1.a aVar10 = new R1.a(26);
        SingleInstanceFactory u12 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(IdentitiesQueries.class), (Qualifier) null, aVar10, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u12);
        }
        new KoinDefinition(module2, u12);
        R1.a aVar11 = new R1.a(24);
        SingleInstanceFactory u13 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(VerifyContextQueries.class), (Qualifier) null, aVar11, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u13);
        }
        new KoinDefinition(module2, u13);
        R1.a aVar12 = new R1.a(27);
        SingleInstanceFactory u14 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(PushMessageQueries.class), (Qualifier) null, aVar12, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u14);
        }
        new KoinDefinition(module2, u14);
        R1.a aVar13 = new R1.a(28);
        SingleInstanceFactory u15 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(EventQueries.class), (Qualifier) null, aVar13, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u15);
        }
        new KoinDefinition(module2, u15);
        R1.a aVar14 = new R1.a(29);
        SingleInstanceFactory u16 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(VerifyPublicKeyQueries.class), (Qualifier) null, aVar14, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u16);
        }
        new KoinDefinition(module2, u16);
        c cVar = new c(0);
        SingleInstanceFactory u17 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, cVar, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u17);
        }
        new KoinDefinition(module2, u17);
        c cVar2 = new c(1);
        SingleInstanceFactory u18 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(PairingStorageRepositoryInterface.class), (Qualifier) null, cVar2, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u18);
        }
        new KoinDefinition(module2, u18);
        c cVar3 = new c(2);
        SingleInstanceFactory u19 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(JsonRpcHistory.class), (Qualifier) null, cVar3, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u19);
        }
        new KoinDefinition(module2, u19);
        c cVar4 = new c(3);
        SingleInstanceFactory u20 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(IdentitiesStorageRepository.class), (Qualifier) null, cVar4, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u20);
        }
        new KoinDefinition(module2, u20);
        c cVar5 = new c(4);
        SingleInstanceFactory u21 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(VerifyContextStorageRepository.class), (Qualifier) null, cVar5, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u21);
        }
        new KoinDefinition(module2, u21);
        R1.a aVar15 = new R1.a(16);
        SingleInstanceFactory u22 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(PushMessagesRepository.class), (Qualifier) null, aVar15, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u22);
        }
        new KoinDefinition(module2, u22);
        d dVar = new d(str, 2);
        SingleInstanceFactory u23 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(EventsRepository.class), (Qualifier) null, dVar, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u23);
        }
        new KoinDefinition(module2, u23);
        d dVar2 = new d(str2, 3);
        SingleInstanceFactory u24 = A.a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(DatabaseConfig.class), (Qualifier) null, dVar2, kind, CollectionsKt.emptyList()), module2);
        if (module.get_createdAtStart()) {
            module2.prepareForCreationAtStart(u24);
        }
        new KoinDefinition(module2, u24);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final AndroidCoreDatabase baseStorageModule$lambda$23$createCoreDB(Scope scope) {
        AndroidCoreDatabase.Companion companion = AndroidCoreDatabase.Companion;
        Qualifier named = QualifierKt.named(AndroidBuildVariantDITags.ANDROID_CORE_DATABASE_DRIVER);
        AndroidCommonDITags androidCommonDITags = AndroidCommonDITags.COLUMN_ADAPTER_LIST;
        Class<ColumnAdapter> cls = ColumnAdapter.class;
        MetaData.Adapter adapter = new MetaData.Adapter((ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(androidCommonDITags), (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(AndroidCommonDITags.COLUMN_ADAPTER_APPMETADATATYPE), (Function0<? extends ParametersHolder>) null));
        VerifyContext.Adapter adapter2 = new VerifyContext.Adapter((ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(AndroidCommonDITags.COLUMN_ADAPTER_VALIDATION), (Function0<? extends ParametersHolder>) null));
        return companion.invoke((SqlDriver) scope.get(Reflection.getOrCreateKotlinClass(SqlDriver.class), named, (Function0<? extends ParametersHolder>) null), new EventDao.Adapter((ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(androidCommonDITags), (Function0<? extends ParametersHolder>) null)), new JsonRpcHistoryDao.Adapter((ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(AndroidCommonDITags.COLUMN_ADAPTER_TRANSPORT_TYPE), (Function0<? extends ParametersHolder>) null)), adapter, adapter2);
    }

    /* access modifiers changed from: private */
    public static final ColumnAdapter baseStorageModule$lambda$23$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new BaseStorageModuleKt$baseStorageModule$1$1$1();
    }

    /* access modifiers changed from: private */
    public static final ColumnAdapter baseStorageModule$lambda$23$lambda$1(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new BaseStorageModuleKt$baseStorageModule$1$2$1();
    }

    /* access modifiers changed from: private */
    public static final IdentitiesQueries baseStorageModule$lambda$23$lambda$10(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return ((AndroidCoreDatabase) scope.get(Reflection.getOrCreateKotlinClass(AndroidCoreDatabase.class), QualifierKt.named(AndroidBuildVariantDITags.ANDROID_CORE_DATABASE), (Function0<? extends ParametersHolder>) null)).getIdentitiesQueries();
    }

    /* access modifiers changed from: private */
    public static final VerifyContextQueries baseStorageModule$lambda$23$lambda$11(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return ((AndroidCoreDatabase) scope.get(Reflection.getOrCreateKotlinClass(AndroidCoreDatabase.class), QualifierKt.named(AndroidBuildVariantDITags.ANDROID_CORE_DATABASE), (Function0<? extends ParametersHolder>) null)).getVerifyContextQueries();
    }

    /* access modifiers changed from: private */
    public static final PushMessageQueries baseStorageModule$lambda$23$lambda$12(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return ((AndroidCoreDatabase) scope.get(Reflection.getOrCreateKotlinClass(AndroidCoreDatabase.class), QualifierKt.named(AndroidBuildVariantDITags.ANDROID_CORE_DATABASE), (Function0<? extends ParametersHolder>) null)).getPushMessageQueries();
    }

    /* access modifiers changed from: private */
    public static final EventQueries baseStorageModule$lambda$23$lambda$13(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return ((AndroidCoreDatabase) scope.get(Reflection.getOrCreateKotlinClass(AndroidCoreDatabase.class), QualifierKt.named(AndroidBuildVariantDITags.ANDROID_CORE_DATABASE), (Function0<? extends ParametersHolder>) null)).getEventQueries();
    }

    /* access modifiers changed from: private */
    public static final VerifyPublicKeyQueries baseStorageModule$lambda$23$lambda$14(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return ((AndroidCoreDatabase) scope.get(Reflection.getOrCreateKotlinClass(AndroidCoreDatabase.class), QualifierKt.named(AndroidBuildVariantDITags.ANDROID_CORE_DATABASE), (Function0<? extends ParametersHolder>) null)).getVerifyPublicKeyQueries();
    }

    /* access modifiers changed from: private */
    public static final MetadataStorageRepositoryInterface baseStorageModule$lambda$23$lambda$15(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new MetadataStorageRepository((MetaDataQueries) scope.get(Reflection.getOrCreateKotlinClass(MetaDataQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final PairingStorageRepositoryInterface baseStorageModule$lambda$23$lambda$16(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new PairingStorageRepository((PairingQueries) scope.get(Reflection.getOrCreateKotlinClass(PairingQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final JsonRpcHistory baseStorageModule$lambda$23$lambda$17(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new JsonRpcHistory((JsonRpcHistoryQueries) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcHistoryQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final IdentitiesStorageRepository baseStorageModule$lambda$23$lambda$18(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new IdentitiesStorageRepository((IdentitiesQueries) scope.get(Reflection.getOrCreateKotlinClass(IdentitiesQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Moshi.Builder) scope.get(Reflection.getOrCreateKotlinClass(Moshi.Builder.class), QualifierKt.named(AndroidCommonDITags.MOSHI), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final VerifyContextStorageRepository baseStorageModule$lambda$23$lambda$19(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new VerifyContextStorageRepository((VerifyContextQueries) scope.get(Reflection.getOrCreateKotlinClass(VerifyContextQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final ColumnAdapter baseStorageModule$lambda$23$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new EnumColumnAdapter(AppMetaDataType.values());
    }

    /* access modifiers changed from: private */
    public static final PushMessagesRepository baseStorageModule$lambda$23$lambda$20(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new PushMessagesRepository((PushMessageQueries) scope.get(Reflection.getOrCreateKotlinClass(PushMessageQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final EventsRepository baseStorageModule$lambda$23$lambda$21(String str, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new EventsRepository((EventQueries) scope.get(Reflection.getOrCreateKotlinClass(EventQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), str, ((TelemetryEnabled) scope.get(Reflection.getOrCreateKotlinClass(TelemetryEnabled.class), QualifierKt.named(AndroidCommonDITags.TELEMETRY_ENABLED), (Function0<? extends ParametersHolder>) null)).m8791unboximpl(), (CoroutineDispatcher) null, 8, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final DatabaseConfig baseStorageModule$lambda$23$lambda$22(String str, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new DatabaseConfig(str);
    }

    /* access modifiers changed from: private */
    public static final ColumnAdapter baseStorageModule$lambda$23$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new EnumColumnAdapter(TransportType.values());
    }

    /* access modifiers changed from: private */
    public static final ColumnAdapter baseStorageModule$lambda$23$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new EnumColumnAdapter(Validation.values());
    }

    /* access modifiers changed from: private */
    public static final AndroidCoreDatabase baseStorageModule$lambda$23$lambda$6(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        try {
            AndroidCoreDatabase baseStorageModule$lambda$23$createCoreDB = baseStorageModule$lambda$23$createCoreDB(scope);
            Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new BaseStorageModuleKt$baseStorageModule$1$6$1$1(baseStorageModule$lambda$23$createCoreDB, scope, (Continuation<? super BaseStorageModuleKt$baseStorageModule$1$6$1$1>) null), 3, (Object) null);
            return baseStorageModule$lambda$23$createCoreDB;
        } catch (Exception unused2) {
            DatabaseConfigKt.deleteDatabase(scope, ((DatabaseConfig) scope.get(Reflection.getOrCreateKotlinClass(DatabaseConfig.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getANDROID_CORE_DB_NAME());
            return baseStorageModule$lambda$23$createCoreDB(scope);
        }
    }

    /* access modifiers changed from: private */
    public static final JsonRpcHistoryQueries baseStorageModule$lambda$23$lambda$7(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return ((AndroidCoreDatabase) scope.get(Reflection.getOrCreateKotlinClass(AndroidCoreDatabase.class), QualifierKt.named(AndroidBuildVariantDITags.ANDROID_CORE_DATABASE), (Function0<? extends ParametersHolder>) null)).getJsonRpcHistoryQueries();
    }

    /* access modifiers changed from: private */
    public static final PairingQueries baseStorageModule$lambda$23$lambda$8(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return ((AndroidCoreDatabase) scope.get(Reflection.getOrCreateKotlinClass(AndroidCoreDatabase.class), QualifierKt.named(AndroidBuildVariantDITags.ANDROID_CORE_DATABASE), (Function0<? extends ParametersHolder>) null)).getPairingQueries();
    }

    /* access modifiers changed from: private */
    public static final MetaDataQueries baseStorageModule$lambda$23$lambda$9(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return ((AndroidCoreDatabase) scope.get(Reflection.getOrCreateKotlinClass(AndroidCoreDatabase.class), QualifierKt.named(AndroidBuildVariantDITags.ANDROID_CORE_DATABASE), (Function0<? extends ParametersHolder>) null)).getMetaDataQueries();
    }
}
