package com.reown.sign.di;

import A.a;
import S0.d;
import app.cash.sqldelight.ColumnAdapter;
import app.cash.sqldelight.db.SqlDriver;
import com.reown.android.di.CoreStorageModuleKt;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.di.DatabaseConfigKt;
import com.reown.sign.SignDatabase;
import com.reown.sign.storage.authenticate.AuthenticateResponseTopicRepository;
import com.reown.sign.storage.data.dao.authenticatereponse.AuthenticateResponseTopicDaoQueries;
import com.reown.sign.storage.data.dao.linkmode.LinkModeDaoQueries;
import com.reown.sign.storage.data.dao.namespace.NamespaceDao;
import com.reown.sign.storage.data.dao.namespace.NamespaceDaoQueries;
import com.reown.sign.storage.data.dao.optionalnamespaces.OptionalNamespaceDao;
import com.reown.sign.storage.data.dao.optionalnamespaces.OptionalNamespaceDaoQueries;
import com.reown.sign.storage.data.dao.proposal.ProposalDao;
import com.reown.sign.storage.data.dao.proposal.ProposalDaoQueries;
import com.reown.sign.storage.data.dao.proposalnamespace.ProposalNamespaceDao;
import com.reown.sign.storage.data.dao.proposalnamespace.ProposalNamespaceDaoQueries;
import com.reown.sign.storage.data.dao.session.SessionDao;
import com.reown.sign.storage.data.dao.session.SessionDaoQueries;
import com.reown.sign.storage.data.dao.temp.TempNamespaceDao;
import com.reown.sign.storage.data.dao.temp.TempNamespaceDaoQueries;
import com.reown.sign.storage.link_mode.LinkModeStorageRepository;
import com.reown.sign.storage.proposal.ProposalStorageRepository;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
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

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"storageModule", "Lorg/koin/core/module/Module;", "dbName", "", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStorageModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StorageModule.kt\ncom/reown/sign/di/StorageModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,142:1\n138#2,5:143\n138#2,5:148\n138#2,5:153\n138#2,5:158\n138#2,5:163\n138#2,5:168\n138#2,5:173\n138#2,5:178\n138#2,5:183\n138#2,5:188\n138#2,5:193\n138#2,5:198\n138#2,5:203\n138#2,5:208\n138#2,5:213\n138#2,5:218\n138#2,5:223\n138#2,5:228\n138#2,5:233\n138#2,5:238\n138#2,5:243\n138#2,5:248\n138#2,5:253\n138#2,5:258\n138#2,5:263\n138#2,5:268\n138#2,5:273\n138#2,5:278\n138#2,5:283\n138#2,5:288\n138#2,5:293\n138#2,5:298\n138#2,5:303\n138#2,5:308\n138#2,5:313\n138#2,5:318\n138#2,5:323\n138#2,5:328\n138#2,5:333\n105#3,6:338\n111#3,5:366\n105#3,6:371\n111#3,5:399\n105#3,6:404\n111#3,5:432\n105#3,6:437\n111#3,5:465\n105#3,6:470\n111#3,5:498\n105#3,6:503\n111#3,5:531\n105#3,6:536\n111#3,5:564\n105#3,6:569\n111#3,5:597\n105#3,6:602\n111#3,5:630\n105#3,6:635\n111#3,5:663\n105#3,6:668\n111#3,5:696\n105#3,6:701\n111#3,5:729\n105#3,6:734\n111#3,5:762\n196#4,7:344\n203#4:365\n196#4,7:377\n203#4:398\n196#4,7:410\n203#4:431\n196#4,7:443\n203#4:464\n196#4,7:476\n203#4:497\n196#4,7:509\n203#4:530\n196#4,7:542\n203#4:563\n196#4,7:575\n203#4:596\n196#4,7:608\n203#4:629\n196#4,7:641\n203#4:662\n196#4,7:674\n203#4:695\n196#4,7:707\n203#4:728\n196#4,7:740\n203#4:761\n115#5,14:351\n115#5,14:384\n115#5,14:417\n115#5,14:450\n115#5,14:483\n115#5,14:516\n115#5,14:549\n115#5,14:582\n115#5,14:615\n115#5,14:648\n115#5,14:681\n115#5,14:714\n115#5,14:747\n*S KotlinDebug\n*F\n+ 1 StorageModule.kt\ncom/reown/sign/di/StorageModuleKt\n*L\n31#1:143,5\n33#1:148,5\n34#1:153,5\n35#1:158,5\n36#1:163,5\n39#1:168,5\n40#1:173,5\n41#1:178,5\n42#1:183,5\n45#1:188,5\n46#1:193,5\n47#1:198,5\n50#1:203,5\n51#1:208,5\n52#1:213,5\n55#1:218,5\n56#1:223,5\n57#1:228,5\n60#1:233,5\n61#1:238,5\n62#1:243,5\n85#1:248,5\n89#1:253,5\n93#1:258,5\n97#1:263,5\n101#1:268,5\n105#1:273,5\n109#1:278,5\n113#1:283,5\n118#1:288,5\n119#1:293,5\n120#1:298,5\n121#1:303,5\n122#1:308,5\n128#1:313,5\n129#1:318,5\n130#1:323,5\n135#1:328,5\n139#1:333,5\n66#1:338,6\n66#1:366,5\n84#1:371,6\n84#1:399,5\n88#1:404,6\n88#1:432,5\n92#1:437,6\n92#1:465,5\n96#1:470,6\n96#1:498,5\n100#1:503,6\n100#1:531,5\n104#1:536,6\n104#1:564,5\n108#1:569,6\n108#1:597,5\n112#1:602,6\n112#1:630,5\n116#1:635,6\n116#1:663,5\n126#1:668,6\n126#1:696,5\n134#1:701,6\n134#1:729,5\n138#1:734,6\n138#1:762,5\n66#1:344,7\n66#1:365\n84#1:377,7\n84#1:398\n88#1:410,7\n88#1:431\n92#1:443,7\n92#1:464\n96#1:476,7\n96#1:497\n100#1:509,7\n100#1:530\n104#1:542,7\n104#1:563\n108#1:575,7\n108#1:596\n112#1:608,7\n112#1:629\n116#1:641,7\n116#1:662\n126#1:674,7\n126#1:695\n134#1:707,7\n134#1:728\n138#1:740,7\n138#1:761\n66#1:351,14\n84#1:384,14\n88#1:417,14\n92#1:450,14\n96#1:483,14\n100#1:516,14\n104#1:549,14\n108#1:582,14\n112#1:615,14\n116#1:648,14\n126#1:681,14\n134#1:714,14\n138#1:747,14\n*E\n"})
public final /* synthetic */ class StorageModuleKt {
    /* access modifiers changed from: private */
    public static final Unit storageModule$lambda$14(String str, Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        module.includes(CoreStorageModuleKt.sdkBaseStorageModule(SignDatabase.Companion.getSchema(), str));
        d dVar = new d(str, 9);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(SignDatabase.class), (Qualifier) null, dVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        b bVar = new b(26);
        SingleInstanceFactory u4 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(SessionDaoQueries.class), (Qualifier) null, bVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        b bVar2 = new b(27);
        SingleInstanceFactory u5 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(NamespaceDaoQueries.class), (Qualifier) null, bVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        b bVar3 = new b(28);
        SingleInstanceFactory u6 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(TempNamespaceDaoQueries.class), (Qualifier) null, bVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module, u6);
        b bVar4 = new b(29);
        SingleInstanceFactory u7 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ProposalNamespaceDaoQueries.class), (Qualifier) null, bVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module, u7);
        c cVar = new c(0);
        SingleInstanceFactory u8 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(OptionalNamespaceDaoQueries.class), (Qualifier) null, cVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u8);
        }
        new KoinDefinition(module, u8);
        b bVar5 = new b(19);
        SingleInstanceFactory u9 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ProposalDaoQueries.class), (Qualifier) null, bVar5, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u9);
        }
        new KoinDefinition(module, u9);
        b bVar6 = new b(20);
        SingleInstanceFactory u10 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(AuthenticateResponseTopicDaoQueries.class), (Qualifier) null, bVar6, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u10);
        }
        new KoinDefinition(module, u10);
        b bVar7 = new b(21);
        SingleInstanceFactory u11 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(LinkModeDaoQueries.class), (Qualifier) null, bVar7, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u11);
        }
        new KoinDefinition(module, u11);
        b bVar8 = new b(22);
        SingleInstanceFactory u12 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, bVar8, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u12);
        }
        new KoinDefinition(module, u12);
        b bVar9 = new b(23);
        SingleInstanceFactory u13 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ProposalStorageRepository.class), (Qualifier) null, bVar9, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u13);
        }
        new KoinDefinition(module, u13);
        b bVar10 = new b(24);
        SingleInstanceFactory u14 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(AuthenticateResponseTopicRepository.class), (Qualifier) null, bVar10, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u14);
        }
        new KoinDefinition(module, u14);
        b bVar11 = new b(25);
        SingleInstanceFactory u15 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(LinkModeStorageRepository.class), (Qualifier) null, bVar11, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u15);
        }
        new KoinDefinition(module, u15);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final SignDatabase storageModule$lambda$14$createSignDB(Scope scope, String str) {
        SignDatabase.Companion companion = SignDatabase.Companion;
        StringQualifier named = QualifierKt.named(str);
        AndroidCommonDITags androidCommonDITags = AndroidCommonDITags.COLUMN_ADAPTER_LIST;
        Qualifier named2 = QualifierKt.named(androidCommonDITags);
        Class<ColumnAdapter> cls = ColumnAdapter.class;
        Qualifier named3 = QualifierKt.named(androidCommonDITags);
        Qualifier named4 = QualifierKt.named(androidCommonDITags);
        NamespaceDao.Adapter adapter = new NamespaceDao.Adapter((ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(androidCommonDITags), (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), named2, (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), named3, (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), named4, (Function0<? extends ParametersHolder>) null));
        Qualifier named5 = QualifierKt.named(androidCommonDITags);
        Qualifier named6 = QualifierKt.named(androidCommonDITags);
        Qualifier named7 = QualifierKt.named(androidCommonDITags);
        TempNamespaceDao.Adapter adapter2 = new TempNamespaceDao.Adapter((ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(androidCommonDITags), (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), named5, (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), named6, (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), named7, (Function0<? extends ParametersHolder>) null));
        ProposalNamespaceDao.Adapter adapter3 = new ProposalNamespaceDao.Adapter((ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(androidCommonDITags), (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(androidCommonDITags), (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(androidCommonDITags), (Function0<? extends ParametersHolder>) null));
        OptionalNamespaceDao.Adapter adapter4 = new OptionalNamespaceDao.Adapter((ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(androidCommonDITags), (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(androidCommonDITags), (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(androidCommonDITags), (Function0<? extends ParametersHolder>) null));
        AndroidCommonDITags androidCommonDITags2 = AndroidCommonDITags.COLUMN_ADAPTER_MAP;
        Qualifier named8 = QualifierKt.named(androidCommonDITags2);
        Qualifier named9 = QualifierKt.named(androidCommonDITags2);
        SessionDao.Adapter adapter5 = new SessionDao.Adapter((ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), named8, (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(AndroidCommonDITags.COLUMN_ADAPTER_TRANSPORT_TYPE), (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), named9, (Function0<? extends ParametersHolder>) null));
        Qualifier named10 = QualifierKt.named(androidCommonDITags2);
        Qualifier named11 = QualifierKt.named(androidCommonDITags2);
        return companion.invoke((SqlDriver) scope.get(Reflection.getOrCreateKotlinClass(SqlDriver.class), named, (Function0<? extends ParametersHolder>) null), adapter, adapter4, new ProposalDao.Adapter((ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), QualifierKt.named(androidCommonDITags), (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), named10, (Function0<? extends ParametersHolder>) null), (ColumnAdapter) scope.get(Reflection.getOrCreateKotlinClass(cls), named11, (Function0<? extends ParametersHolder>) null)), adapter3, adapter5, adapter2);
    }

    /* access modifiers changed from: private */
    public static final SignDatabase storageModule$lambda$14$lambda$1(String str, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        try {
            SignDatabase storageModule$lambda$14$createSignDB = storageModule$lambda$14$createSignDB(scope, str);
            Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new StorageModuleKt$storageModule$1$1$1$1(storageModule$lambda$14$createSignDB, scope, str, (Continuation<? super StorageModuleKt$storageModule$1$1$1$1>) null), 3, (Object) null);
            return storageModule$lambda$14$createSignDB;
        } catch (Exception unused2) {
            DatabaseConfigKt.deleteDatabase(scope, str);
            return storageModule$lambda$14$createSignDB(scope, str);
        }
    }

    /* access modifiers changed from: private */
    public static final SessionStorageRepository storageModule$lambda$14$lambda$10(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new SessionStorageRepository((SessionDaoQueries) scope.get(Reflection.getOrCreateKotlinClass(SessionDaoQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (NamespaceDaoQueries) scope.get(Reflection.getOrCreateKotlinClass(NamespaceDaoQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ProposalNamespaceDaoQueries) scope.get(Reflection.getOrCreateKotlinClass(ProposalNamespaceDaoQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (OptionalNamespaceDaoQueries) scope.get(Reflection.getOrCreateKotlinClass(OptionalNamespaceDaoQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (TempNamespaceDaoQueries) scope.get(Reflection.getOrCreateKotlinClass(TempNamespaceDaoQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final ProposalStorageRepository storageModule$lambda$14$lambda$11(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new ProposalStorageRepository((ProposalDaoQueries) scope.get(Reflection.getOrCreateKotlinClass(ProposalDaoQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ProposalNamespaceDaoQueries) scope.get(Reflection.getOrCreateKotlinClass(ProposalNamespaceDaoQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (OptionalNamespaceDaoQueries) scope.get(Reflection.getOrCreateKotlinClass(OptionalNamespaceDaoQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final AuthenticateResponseTopicRepository storageModule$lambda$14$lambda$12(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new AuthenticateResponseTopicRepository((AuthenticateResponseTopicDaoQueries) scope.get(Reflection.getOrCreateKotlinClass(AuthenticateResponseTopicDaoQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final LinkModeStorageRepository storageModule$lambda$14$lambda$13(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new LinkModeStorageRepository((LinkModeDaoQueries) scope.get(Reflection.getOrCreateKotlinClass(LinkModeDaoQueries.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final SessionDaoQueries storageModule$lambda$14$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        return ((SignDatabase) scope.get(a.t(scope, "$this$single", parametersHolder, "it", SignDatabase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getSessionDaoQueries();
    }

    /* access modifiers changed from: private */
    public static final NamespaceDaoQueries storageModule$lambda$14$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        return ((SignDatabase) scope.get(a.t(scope, "$this$single", parametersHolder, "it", SignDatabase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getNamespaceDaoQueries();
    }

    /* access modifiers changed from: private */
    public static final TempNamespaceDaoQueries storageModule$lambda$14$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        return ((SignDatabase) scope.get(a.t(scope, "$this$single", parametersHolder, "it", SignDatabase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getTempNamespaceDaoQueries();
    }

    /* access modifiers changed from: private */
    public static final ProposalNamespaceDaoQueries storageModule$lambda$14$lambda$5(Scope scope, ParametersHolder parametersHolder) {
        return ((SignDatabase) scope.get(a.t(scope, "$this$single", parametersHolder, "it", SignDatabase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getProposalNamespaceDaoQueries();
    }

    /* access modifiers changed from: private */
    public static final OptionalNamespaceDaoQueries storageModule$lambda$14$lambda$6(Scope scope, ParametersHolder parametersHolder) {
        return ((SignDatabase) scope.get(a.t(scope, "$this$single", parametersHolder, "it", SignDatabase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getOptionalNamespaceDaoQueries();
    }

    /* access modifiers changed from: private */
    public static final ProposalDaoQueries storageModule$lambda$14$lambda$7(Scope scope, ParametersHolder parametersHolder) {
        return ((SignDatabase) scope.get(a.t(scope, "$this$single", parametersHolder, "it", SignDatabase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getProposalDaoQueries();
    }

    /* access modifiers changed from: private */
    public static final AuthenticateResponseTopicDaoQueries storageModule$lambda$14$lambda$8(Scope scope, ParametersHolder parametersHolder) {
        return ((SignDatabase) scope.get(a.t(scope, "$this$single", parametersHolder, "it", SignDatabase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getAuthenticateResponseTopicDaoQueries();
    }

    /* access modifiers changed from: private */
    public static final LinkModeDaoQueries storageModule$lambda$14$lambda$9(Scope scope, ParametersHolder parametersHolder) {
        return ((SignDatabase) scope.get(a.t(scope, "$this$single", parametersHolder, "it", SignDatabase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getLinkModeDaoQueries();
    }
}
