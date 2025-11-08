package com.reown.sign.di;

import A.a;
import com.reown.android.internal.common.crypto.codec.Codec;
import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.di.i;
import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.signing.cacao.CacaoVerifier;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.internal.common.storage.push_messages.PushMessagesRepository;
import com.reown.android.internal.common.storage.rpc.JsonRpcHistory;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.android.pairing.client.PairingInterface;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.android.pulse.domain.InsertTelemetryEventUseCase;
import com.reown.android.push.notifications.DecryptMessageUseCaseInterface;
import com.reown.foundation.util.Logger;
import com.reown.sign.engine.domain.wallet_service.WalletServiceFinder;
import com.reown.sign.engine.domain.wallet_service.WalletServiceRequester;
import com.reown.sign.engine.model.tvf.TVF;
import com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase;
import com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCaseInterface;
import com.reown.sign.engine.use_case.calls.ApproveSessionUseCase;
import com.reown.sign.engine.use_case.calls.ApproveSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.DecryptSignMessageUseCase;
import com.reown.sign.engine.use_case.calls.DisconnectSessionUseCase;
import com.reown.sign.engine.use_case.calls.DisconnectSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.EmitEventUseCase;
import com.reown.sign.engine.use_case.calls.EmitEventUseCaseInterface;
import com.reown.sign.engine.use_case.calls.ExtendSessionUseCase;
import com.reown.sign.engine.use_case.calls.ExtendSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.FormatAuthenticateMessageUseCase;
import com.reown.sign.engine.use_case.calls.FormatAuthenticateMessageUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetListOfVerifyContextsUseCase;
import com.reown.sign.engine.use_case.calls.GetListOfVerifyContextsUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetNamespacesFromReCaps;
import com.reown.sign.engine.use_case.calls.GetPairingForSessionAuthenticateUseCase;
import com.reown.sign.engine.use_case.calls.GetPairingsUseCase;
import com.reown.sign.engine.use_case.calls.GetPairingsUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetSessionProposalsUseCase;
import com.reown.sign.engine.use_case.calls.GetSessionProposalsUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetSessionsUseCase;
import com.reown.sign.engine.use_case.calls.GetSessionsUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetVerifyContextByIdUseCase;
import com.reown.sign.engine.use_case.calls.GetVerifyContextByIdUseCaseInterface;
import com.reown.sign.engine.use_case.calls.PairUseCase;
import com.reown.sign.engine.use_case.calls.PairUseCaseInterface;
import com.reown.sign.engine.use_case.calls.PingUseCase;
import com.reown.sign.engine.use_case.calls.PingUseCaseInterface;
import com.reown.sign.engine.use_case.calls.ProposeSessionUseCase;
import com.reown.sign.engine.use_case.calls.ProposeSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.RejectSessionAuthenticateUseCase;
import com.reown.sign.engine.use_case.calls.RejectSessionAuthenticateUseCaseInterface;
import com.reown.sign.engine.use_case.calls.RejectSessionUseCase;
import com.reown.sign.engine.use_case.calls.RejectSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCase;
import com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCaseInterface;
import com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase;
import com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCaseInterface;
import com.reown.sign.engine.use_case.calls.SessionRequestUseCase;
import com.reown.sign.engine.use_case.calls.SessionRequestUseCaseInterface;
import com.reown.sign.engine.use_case.calls.SessionUpdateUseCase;
import com.reown.sign.engine.use_case.calls.SessionUpdateUseCaseInterface;
import com.reown.sign.json_rpc.domain.GetPendingJsonRpcHistoryEntryByIdUseCase;
import com.reown.sign.json_rpc.domain.GetPendingRequestsUseCaseByTopic;
import com.reown.sign.json_rpc.domain.GetPendingRequestsUseCaseByTopicInterface;
import com.reown.sign.json_rpc.domain.GetPendingSessionAuthenticateRequest;
import com.reown.sign.json_rpc.domain.GetPendingSessionRequestByTopicUseCase;
import com.reown.sign.json_rpc.domain.GetPendingSessionRequestByTopicUseCaseInterface;
import com.reown.sign.storage.authenticate.AuthenticateResponseTopicRepository;
import com.reown.sign.storage.link_mode.LinkModeStorageRepository;
import com.reown.sign.storage.proposal.ProposalStorageRepository;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
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

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"callsModule", "Lorg/koin/core/module/Module;", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCallsModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CallsModule.kt\ncom/reown/sign/di/CallsModuleKt\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n+ 3 Module.kt\norg/koin/core/module/Module\n+ 4 Module.kt\norg/koin/core/module/ModuleKt\n+ 5 BeanDefinition.kt\norg/koin/core/definition/BeanDefinitionKt\n*L\n1#1,214:1\n138#2,5:215\n138#2,5:220\n138#2,5:225\n138#2,5:230\n138#2,5:235\n138#2,5:240\n138#2,5:245\n138#2,5:250\n138#2,5:255\n138#2,5:260\n138#2,5:265\n138#2,5:270\n138#2,5:275\n138#2,5:280\n138#2,5:285\n138#2,5:290\n138#2,5:295\n138#2,5:300\n138#2,5:305\n138#2,5:310\n138#2,5:315\n138#2,5:320\n138#2,5:325\n138#2,5:330\n138#2,5:335\n138#2,5:340\n138#2,5:345\n138#2,5:350\n138#2,5:355\n138#2,5:360\n138#2,5:365\n138#2,5:370\n138#2,5:375\n138#2,5:380\n138#2,5:385\n138#2,5:390\n138#2,5:395\n138#2,5:400\n138#2,5:405\n138#2,5:410\n138#2,5:415\n138#2,5:420\n138#2,5:425\n138#2,5:430\n138#2,5:435\n138#2,5:440\n138#2,5:445\n138#2,5:450\n138#2,5:455\n138#2,5:460\n138#2,5:465\n138#2,5:470\n138#2,5:475\n138#2,5:480\n138#2,5:485\n138#2,5:490\n138#2,5:495\n138#2,5:500\n138#2,5:505\n138#2,5:510\n138#2,5:515\n138#2,5:520\n138#2,5:525\n138#2,5:530\n138#2,5:535\n138#2,5:540\n138#2,5:545\n138#2,5:550\n138#2,5:555\n138#2,5:560\n138#2,5:565\n138#2,5:570\n138#2,5:575\n138#2,5:580\n138#2,5:585\n138#2,5:590\n138#2,5:595\n138#2,5:600\n138#2,5:605\n138#2,5:610\n138#2,5:615\n138#2,5:620\n138#2,5:625\n138#2,5:630\n138#2,5:635\n138#2,5:640\n138#2,5:645\n138#2,5:650\n138#2,5:655\n138#2,5:660\n105#3,6:665\n111#3,5:693\n105#3,6:698\n111#3,5:726\n105#3,6:731\n111#3,5:759\n105#3,6:764\n111#3,5:792\n105#3,6:797\n111#3,5:825\n105#3,6:830\n111#3,5:858\n105#3,6:863\n111#3,5:891\n105#3,6:896\n111#3,5:924\n105#3,6:929\n111#3,5:957\n105#3,6:962\n111#3,5:990\n105#3,6:995\n111#3,5:1023\n105#3,6:1028\n111#3,5:1056\n105#3,6:1061\n111#3,5:1089\n105#3,6:1094\n111#3,5:1122\n105#3,6:1127\n111#3,5:1155\n105#3,6:1160\n111#3,5:1188\n105#3,6:1193\n111#3,5:1221\n105#3,6:1226\n111#3,5:1254\n105#3,6:1259\n111#3,5:1287\n105#3,6:1292\n111#3,5:1320\n105#3,6:1325\n111#3,5:1353\n105#3,6:1358\n111#3,5:1386\n105#3,6:1391\n111#3,5:1419\n105#3,6:1424\n111#3,5:1452\n105#3,6:1457\n111#3,5:1485\n196#4,7:671\n203#4:692\n196#4,7:704\n203#4:725\n196#4,7:737\n203#4:758\n196#4,7:770\n203#4:791\n196#4,7:803\n203#4:824\n196#4,7:836\n203#4:857\n196#4,7:869\n203#4:890\n196#4,7:902\n203#4:923\n196#4,7:935\n203#4:956\n196#4,7:968\n203#4:989\n196#4,7:1001\n203#4:1022\n196#4,7:1034\n203#4:1055\n196#4,7:1067\n203#4:1088\n196#4,7:1100\n203#4:1121\n196#4,7:1133\n203#4:1154\n196#4,7:1166\n203#4:1187\n196#4,7:1199\n203#4:1220\n196#4,7:1232\n203#4:1253\n196#4,7:1265\n203#4:1286\n196#4,7:1298\n203#4:1319\n196#4,7:1331\n203#4:1352\n196#4,7:1364\n203#4:1385\n196#4,7:1397\n203#4:1418\n196#4,7:1430\n203#4:1451\n196#4,7:1463\n203#4:1484\n115#5,14:678\n115#5,14:711\n115#5,14:744\n115#5,14:777\n115#5,14:810\n115#5,14:843\n115#5,14:876\n115#5,14:909\n115#5,14:942\n115#5,14:975\n115#5,14:1008\n115#5,14:1041\n115#5,14:1074\n115#5,14:1107\n115#5,14:1140\n115#5,14:1173\n115#5,14:1206\n115#5,14:1239\n115#5,14:1272\n115#5,14:1305\n115#5,14:1338\n115#5,14:1371\n115#5,14:1404\n115#5,14:1437\n115#5,14:1470\n*S KotlinDebug\n*F\n+ 1 CallsModule.kt\ncom/reown/sign/di/CallsModuleKt\n*L\n62#1:215,5\n63#1:220,5\n64#1:225,5\n65#1:230,5\n66#1:235,5\n72#1:240,5\n73#1:245,5\n74#1:250,5\n75#1:255,5\n76#1:260,5\n77#1:265,5\n78#1:270,5\n79#1:275,5\n80#1:280,5\n81#1:285,5\n82#1:290,5\n83#1:295,5\n87#1:300,5\n91#1:305,5\n92#1:310,5\n93#1:315,5\n94#1:320,5\n95#1:325,5\n96#1:330,5\n97#1:335,5\n98#1:340,5\n99#1:345,5\n105#1:350,5\n106#1:355,5\n107#1:360,5\n108#1:365,5\n109#1:370,5\n110#1:375,5\n111#1:380,5\n112#1:385,5\n113#1:390,5\n114#1:395,5\n115#1:400,5\n116#1:405,5\n117#1:410,5\n123#1:415,5\n124#1:420,5\n125#1:425,5\n126#1:430,5\n127#1:435,5\n128#1:440,5\n129#1:445,5\n130#1:450,5\n136#1:455,5\n137#1:460,5\n138#1:465,5\n139#1:470,5\n143#1:475,5\n147#1:480,5\n148#1:485,5\n149#1:490,5\n150#1:495,5\n151#1:500,5\n152#1:505,5\n153#1:510,5\n154#1:515,5\n155#1:520,5\n156#1:525,5\n162#1:530,5\n163#1:535,5\n164#1:540,5\n165#1:545,5\n166#1:550,5\n167#1:555,5\n168#1:560,5\n169#1:565,5\n170#1:570,5\n171#1:575,5\n177#1:580,5\n178#1:585,5\n179#1:590,5\n180#1:595,5\n183#1:600,5\n187#1:605,5\n189#1:610,5\n191#1:615,5\n193#1:620,5\n195#1:625,5\n197#1:630,5\n199#1:635,5\n203#1:640,5\n205#1:645,5\n207#1:650,5\n209#1:655,5\n211#1:660,5\n60#1:665,6\n60#1:693,5\n70#1:698,6\n70#1:726,5\n87#1:731,6\n87#1:759,5\n89#1:764,6\n89#1:792,5\n103#1:797,6\n103#1:825,5\n121#1:830,6\n121#1:858,5\n134#1:863,6\n134#1:891,5\n143#1:896,6\n143#1:924,5\n145#1:929,6\n145#1:957,5\n160#1:962,6\n160#1:990,5\n175#1:995,6\n175#1:1023,5\n187#1:1028,6\n187#1:1056,5\n189#1:1061,6\n189#1:1089,5\n191#1:1094,6\n191#1:1122,5\n193#1:1127,6\n193#1:1155,5\n195#1:1160,6\n195#1:1188,5\n197#1:1193,6\n197#1:1221,5\n199#1:1226,6\n199#1:1254,5\n201#1:1259,6\n201#1:1287,5\n203#1:1292,6\n203#1:1320,5\n205#1:1325,6\n205#1:1353,5\n207#1:1358,6\n207#1:1386,5\n209#1:1391,6\n209#1:1419,5\n211#1:1424,6\n211#1:1452,5\n213#1:1457,6\n213#1:1485,5\n60#1:671,7\n60#1:692\n70#1:704,7\n70#1:725\n87#1:737,7\n87#1:758\n89#1:770,7\n89#1:791\n103#1:803,7\n103#1:824\n121#1:836,7\n121#1:857\n134#1:869,7\n134#1:890\n143#1:902,7\n143#1:923\n145#1:935,7\n145#1:956\n160#1:968,7\n160#1:989\n175#1:1001,7\n175#1:1022\n187#1:1034,7\n187#1:1055\n189#1:1067,7\n189#1:1088\n191#1:1100,7\n191#1:1121\n193#1:1133,7\n193#1:1154\n195#1:1166,7\n195#1:1187\n197#1:1199,7\n197#1:1220\n199#1:1232,7\n199#1:1253\n201#1:1265,7\n201#1:1286\n203#1:1298,7\n203#1:1319\n205#1:1331,7\n205#1:1352\n207#1:1364,7\n207#1:1385\n209#1:1397,7\n209#1:1418\n211#1:1430,7\n211#1:1451\n213#1:1463,7\n213#1:1484\n60#1:678,14\n70#1:711,14\n87#1:744,14\n89#1:777,14\n103#1:810,14\n121#1:843,14\n134#1:876,14\n143#1:909,14\n145#1:942,14\n160#1:975,14\n175#1:1008,14\n187#1:1041,14\n189#1:1074,14\n191#1:1107,14\n193#1:1140,14\n195#1:1173,14\n197#1:1206,14\n199#1:1239,14\n201#1:1272,14\n203#1:1305,14\n205#1:1338,14\n207#1:1371,14\n209#1:1404,14\n211#1:1437,14\n213#1:1470,14\n*E\n"})
public final class CallsModuleKt {
    /* access modifiers changed from: private */
    public static final Unit callsModule$lambda$25(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        i iVar = new i(28);
        ScopeRegistry.Companion companion = ScopeRegistry.Companion;
        StringQualifier rootScopeQualifier = companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        SingleInstanceFactory u3 = a.u(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(ProposeSessionUseCaseInterface.class), (Qualifier) null, iVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u3);
        }
        new KoinDefinition(module, u3);
        a aVar = new a(0);
        SingleInstanceFactory u4 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(SessionAuthenticateUseCaseInterface.class), (Qualifier) null, aVar, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u4);
        }
        new KoinDefinition(module, u4);
        a aVar2 = new a(6);
        SingleInstanceFactory u5 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(PairUseCaseInterface.class), (Qualifier) null, aVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u5);
        }
        new KoinDefinition(module, u5);
        a aVar3 = new a(7);
        SingleInstanceFactory u6 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ApproveSessionUseCaseInterface.class), (Qualifier) null, aVar3, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u6);
        }
        new KoinDefinition(module, u6);
        a aVar4 = new a(8);
        SingleInstanceFactory u7 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ApproveSessionAuthenticateUseCaseInterface.class), (Qualifier) null, aVar4, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u7);
        }
        new KoinDefinition(module, u7);
        a aVar5 = new a(10);
        SingleInstanceFactory u8 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(RejectSessionAuthenticateUseCaseInterface.class), (Qualifier) null, aVar5, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u8);
        }
        new KoinDefinition(module, u8);
        a aVar6 = new a(11);
        SingleInstanceFactory u9 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(RejectSessionUseCaseInterface.class), (Qualifier) null, aVar6, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u9);
        }
        new KoinDefinition(module, u9);
        a aVar7 = new a(12);
        SingleInstanceFactory u10 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(SessionUpdateUseCaseInterface.class), (Qualifier) null, aVar7, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u10);
        }
        new KoinDefinition(module, u10);
        a aVar8 = new a(13);
        SingleInstanceFactory u11 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(SessionRequestUseCaseInterface.class), (Qualifier) null, aVar8, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u11);
        }
        new KoinDefinition(module, u11);
        a aVar9 = new a(14);
        SingleInstanceFactory u12 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(RespondSessionRequestUseCaseInterface.class), (Qualifier) null, aVar9, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u12);
        }
        new KoinDefinition(module, u12);
        Qualifier named = QualifierKt.named(AndroidCommonDITags.DECRYPT_SIGN_MESSAGE);
        a aVar10 = new a(9);
        SingleInstanceFactory u13 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(DecryptMessageUseCaseInterface.class), named, aVar10, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u13);
        }
        new KoinDefinition(module, u13);
        a aVar11 = new a(15);
        SingleInstanceFactory u14 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(PingUseCaseInterface.class), (Qualifier) null, aVar11, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u14);
        }
        new KoinDefinition(module, u14);
        a aVar12 = new a(16);
        SingleInstanceFactory u15 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(EmitEventUseCaseInterface.class), (Qualifier) null, aVar12, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u15);
        }
        new KoinDefinition(module, u15);
        a aVar13 = new a(17);
        SingleInstanceFactory u16 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(ExtendSessionUseCaseInterface.class), (Qualifier) null, aVar13, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u16);
        }
        new KoinDefinition(module, u16);
        a aVar14 = new a(18);
        SingleInstanceFactory u17 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(DisconnectSessionUseCaseInterface.class), (Qualifier) null, aVar14, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u17);
        }
        new KoinDefinition(module, u17);
        a aVar15 = new a(19);
        SingleInstanceFactory u18 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetSessionsUseCaseInterface.class), (Qualifier) null, aVar15, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u18);
        }
        new KoinDefinition(module, u18);
        a aVar16 = new a(20);
        SingleInstanceFactory u19 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetPairingsUseCaseInterface.class), (Qualifier) null, aVar16, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u19);
        }
        new KoinDefinition(module, u19);
        a aVar17 = new a(21);
        SingleInstanceFactory u20 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetPairingForSessionAuthenticateUseCase.class), (Qualifier) null, aVar17, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u20);
        }
        new KoinDefinition(module, u20);
        a aVar18 = new a(22);
        SingleInstanceFactory u21 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetNamespacesFromReCaps.class), (Qualifier) null, aVar18, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u21);
        }
        new KoinDefinition(module, u21);
        i iVar2 = new i(29);
        SingleInstanceFactory u22 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetPendingRequestsUseCaseByTopicInterface.class), (Qualifier) null, iVar2, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u22);
        }
        new KoinDefinition(module, u22);
        a aVar19 = new a(1);
        SingleInstanceFactory u23 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetPendingSessionRequestByTopicUseCaseInterface.class), (Qualifier) null, aVar19, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u23);
        }
        new KoinDefinition(module, u23);
        a aVar20 = new a(2);
        SingleInstanceFactory u24 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetSessionProposalsUseCaseInterface.class), (Qualifier) null, aVar20, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u24);
        }
        new KoinDefinition(module, u24);
        a aVar21 = new a(3);
        SingleInstanceFactory u25 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetVerifyContextByIdUseCaseInterface.class), (Qualifier) null, aVar21, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u25);
        }
        new KoinDefinition(module, u25);
        a aVar22 = new a(4);
        SingleInstanceFactory u26 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(GetListOfVerifyContextsUseCaseInterface.class), (Qualifier) null, aVar22, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u26);
        }
        new KoinDefinition(module, u26);
        a aVar23 = new a(5);
        SingleInstanceFactory u27 = a.u(new BeanDefinition(companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(FormatAuthenticateMessageUseCaseInterface.class), (Qualifier) null, aVar23, kind, CollectionsKt.emptyList()), module);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(u27);
        }
        new KoinDefinition(module, u27);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final ProposeSessionUseCaseInterface callsModule$lambda$25$lambda$0(Scope scope, ParametersHolder parametersHolder) {
        return new ProposeSessionUseCase((RelayJsonRpcInteractorInterface) scope.get(a.t(scope, "$this$single", parametersHolder, "it", RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (KeyManagementRepository) scope.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ProposalStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(ProposalStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (AppMetaData) scope.get(Reflection.getOrCreateKotlinClass(AppMetaData.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final SessionAuthenticateUseCaseInterface callsModule$lambda$25$lambda$1(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new SessionAuthenticateUseCase((RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (KeyManagementRepository) scope.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (AppMetaData) scope.get(Reflection.getOrCreateKotlinClass(AppMetaData.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (AuthenticateResponseTopicRepository) scope.get(Reflection.getOrCreateKotlinClass(AuthenticateResponseTopicRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ProposeSessionUseCaseInterface) scope.get(Reflection.getOrCreateKotlinClass(ProposeSessionUseCaseInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (GetPairingForSessionAuthenticateUseCase) scope.get(Reflection.getOrCreateKotlinClass(GetPairingForSessionAuthenticateUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (GetNamespacesFromReCaps) scope.get(Reflection.getOrCreateKotlinClass(GetNamespacesFromReCaps.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (LinkModeJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(LinkModeJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (LinkModeStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(LinkModeStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(InsertEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.CLIENT_ID), (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final DecryptMessageUseCaseInterface callsModule$lambda$25$lambda$10(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        DecryptSignMessageUseCase decryptSignMessageUseCase = new DecryptSignMessageUseCase((Codec) scope.get(Reflection.getOrCreateKotlinClass(Codec.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (JsonRpcSerializer) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcSerializer.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (MetadataStorageRepositoryInterface) scope.get(Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (PushMessagesRepository) scope.get(Reflection.getOrCreateKotlinClass(PushMessagesRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
        ((Map) scope.get(Reflection.getOrCreateKotlinClass(Map.class), QualifierKt.named(AndroidCommonDITags.DECRYPT_USE_CASES), (Function0<? extends ParametersHolder>) null)).put(String.valueOf(Tags.SESSION_PROPOSE.getId()), decryptSignMessageUseCase);
        return decryptSignMessageUseCase;
    }

    /* access modifiers changed from: private */
    public static final PingUseCaseInterface callsModule$lambda$25$lambda$11(Scope scope, ParametersHolder parametersHolder) {
        return new PingUseCase((RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SessionStorageRepository) scope.get(a.t(scope, "$this$single", parametersHolder, "it", SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final EmitEventUseCaseInterface callsModule$lambda$25$lambda$12(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new EmitEventUseCase((RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final ExtendSessionUseCaseInterface callsModule$lambda$25$lambda$13(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new ExtendSessionUseCase((RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final DisconnectSessionUseCaseInterface callsModule$lambda$25$lambda$14(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new DisconnectSessionUseCase((RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetSessionsUseCaseInterface callsModule$lambda$25$lambda$15(Scope scope, ParametersHolder parametersHolder) {
        return new GetSessionsUseCase((MetadataStorageRepositoryInterface) scope.get(Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SessionStorageRepository) scope.get(a.t(scope, "$this$single", parametersHolder, "it", SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (AppMetaData) scope.get(Reflection.getOrCreateKotlinClass(AppMetaData.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetPairingsUseCaseInterface callsModule$lambda$25$lambda$16(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetPairingsUseCase((PairingInterface) scope.get(Reflection.getOrCreateKotlinClass(PairingInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetPairingForSessionAuthenticateUseCase callsModule$lambda$25$lambda$17(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetPairingForSessionAuthenticateUseCase((PairingInterface) scope.get(Reflection.getOrCreateKotlinClass(PairingInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetNamespacesFromReCaps callsModule$lambda$25$lambda$18(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetNamespacesFromReCaps();
    }

    /* access modifiers changed from: private */
    public static final GetPendingRequestsUseCaseByTopicInterface callsModule$lambda$25$lambda$19(Scope scope, ParametersHolder parametersHolder) {
        return new GetPendingRequestsUseCaseByTopic((JsonRpcHistory) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcHistory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (JsonRpcSerializer) scope.get(a.t(scope, "$this$single", parametersHolder, "it", JsonRpcSerializer.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final PairUseCaseInterface callsModule$lambda$25$lambda$2(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new PairUseCase((PairingInterface) scope.get(Reflection.getOrCreateKotlinClass(PairingInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetPendingSessionRequestByTopicUseCaseInterface callsModule$lambda$25$lambda$20(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetPendingSessionRequestByTopicUseCase((JsonRpcHistory) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcHistory.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (JsonRpcSerializer) scope.get(Reflection.getOrCreateKotlinClass(JsonRpcSerializer.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (MetadataStorageRepositoryInterface) scope.get(Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetSessionProposalsUseCaseInterface callsModule$lambda$25$lambda$21(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetSessionProposalsUseCase((ProposalStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(ProposalStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetVerifyContextByIdUseCaseInterface callsModule$lambda$25$lambda$22(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetVerifyContextByIdUseCase((VerifyContextStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(VerifyContextStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final GetListOfVerifyContextsUseCaseInterface callsModule$lambda$25$lambda$23(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new GetListOfVerifyContextsUseCase((VerifyContextStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(VerifyContextStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final FormatAuthenticateMessageUseCaseInterface callsModule$lambda$25$lambda$24(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new FormatAuthenticateMessageUseCase();
    }

    /* access modifiers changed from: private */
    public static final ApproveSessionUseCaseInterface callsModule$lambda$25$lambda$3(Scope scope, ParametersHolder parametersHolder) {
        KeyManagementRepository keyManagementRepository = (KeyManagementRepository) scope.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface = (RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        MetadataStorageRepositoryInterface metadataStorageRepositoryInterface = (MetadataStorageRepositoryInterface) scope.get(Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        return new ApproveSessionUseCase(relayJsonRpcInteractorInterface, keyManagementRepository, (SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ProposalStorageRepository) scope.get(a.t(scope, "$this$single", parametersHolder, "it", ProposalStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), metadataStorageRepositoryInterface, (VerifyContextStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(VerifyContextStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (AppMetaData) scope.get(Reflection.getOrCreateKotlinClass(AppMetaData.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertTelemetryEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(InsertTelemetryEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final ApproveSessionAuthenticateUseCaseInterface callsModule$lambda$25$lambda$4(Scope scope, ParametersHolder parametersHolder) {
        Scope scope2 = scope;
        ParametersHolder parametersHolder2 = parametersHolder;
        Qualifier named = QualifierKt.named(AndroidCommonDITags.LOGGER);
        VerifyContextStorageRepository verifyContextStorageRepository = (VerifyContextStorageRepository) scope2.get(Reflection.getOrCreateKotlinClass(VerifyContextStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        return new ApproveSessionAuthenticateUseCase((RelayJsonRpcInteractorInterface) scope2.get(a.t(scope2, "$this$single", parametersHolder2, "it", RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (GetPendingSessionAuthenticateRequest) scope2.get(Reflection.getOrCreateKotlinClass(GetPendingSessionAuthenticateRequest.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (KeyManagementRepository) scope2.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (CacaoVerifier) scope2.get(Reflection.getOrCreateKotlinClass(CacaoVerifier.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), verifyContextStorageRepository, (Logger) scope2.get(Reflection.getOrCreateKotlinClass(Logger.class), named, (Function0<? extends ParametersHolder>) null), (MetadataStorageRepositoryInterface) scope2.get(Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (AppMetaData) scope2.get(Reflection.getOrCreateKotlinClass(AppMetaData.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SessionStorageRepository) scope2.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertTelemetryEventUseCase) scope2.get(Reflection.getOrCreateKotlinClass(InsertTelemetryEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertEventUseCase) scope2.get(Reflection.getOrCreateKotlinClass(InsertEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (String) scope2.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.CLIENT_ID), (Function0<? extends ParametersHolder>) null), (LinkModeJsonRpcInteractorInterface) scope2.get(Reflection.getOrCreateKotlinClass(LinkModeJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final RejectSessionAuthenticateUseCaseInterface callsModule$lambda$25$lambda$5(Scope scope, ParametersHolder parametersHolder) {
        Qualifier named = QualifierKt.named(AndroidCommonDITags.LOGGER);
        VerifyContextStorageRepository verifyContextStorageRepository = (VerifyContextStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(VerifyContextStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        GetPendingSessionAuthenticateRequest getPendingSessionAuthenticateRequest = (GetPendingSessionAuthenticateRequest) scope.get(Reflection.getOrCreateKotlinClass(GetPendingSessionAuthenticateRequest.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractorInterface = (LinkModeJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(LinkModeJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        String str = (String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.CLIENT_ID), (Function0<? extends ParametersHolder>) null);
        return new RejectSessionAuthenticateUseCase((RelayJsonRpcInteractorInterface) scope.get(a.t(scope, "$this$single", parametersHolder, "it", RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), getPendingSessionAuthenticateRequest, (KeyManagementRepository) scope.get(Reflection.getOrCreateKotlinClass(KeyManagementRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), verifyContextStorageRepository, linkModeJsonRpcInteractorInterface, (InsertEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(InsertEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), str, (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), named, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final RejectSessionUseCaseInterface callsModule$lambda$25$lambda$6(Scope scope, ParametersHolder parametersHolder) {
        return new RejectSessionUseCase((VerifyContextStorageRepository) scope.get(a.t(scope, "$this$single", parametersHolder, "it", VerifyContextStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (ProposalStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(ProposalStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final SessionUpdateUseCaseInterface callsModule$lambda$25$lambda$7(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$single");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return new SessionUpdateUseCase((RelayJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final SessionRequestUseCaseInterface callsModule$lambda$25$lambda$8(Scope scope, ParametersHolder parametersHolder) {
        return new SessionRequestUseCase((SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (RelayJsonRpcInteractorInterface) scope.get(a.t(scope, "$this$single", parametersHolder, "it", RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (LinkModeJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(LinkModeJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (MetadataStorageRepositoryInterface) scope.get(Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(InsertEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.CLIENT_ID), (Function0<? extends ParametersHolder>) null), (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null), (TVF) scope.get(Reflection.getOrCreateKotlinClass(TVF.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (WalletServiceFinder) scope.get(Reflection.getOrCreateKotlinClass(WalletServiceFinder.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (WalletServiceRequester) scope.get(Reflection.getOrCreateKotlinClass(WalletServiceRequester.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* access modifiers changed from: private */
    public static final RespondSessionRequestUseCaseInterface callsModule$lambda$25$lambda$9(Scope scope, ParametersHolder parametersHolder) {
        SessionStorageRepository sessionStorageRepository = (SessionStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(SessionStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Logger logger = (Logger) scope.get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null);
        return new RespondSessionRequestUseCase((RelayJsonRpcInteractorInterface) scope.get(a.t(scope, "$this$single", parametersHolder, "it", RelayJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), sessionStorageRepository, (GetPendingJsonRpcHistoryEntryByIdUseCase) scope.get(Reflection.getOrCreateKotlinClass(GetPendingJsonRpcHistoryEntryByIdUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (LinkModeJsonRpcInteractorInterface) scope.get(Reflection.getOrCreateKotlinClass(LinkModeJsonRpcInteractorInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), logger, (VerifyContextStorageRepository) scope.get(Reflection.getOrCreateKotlinClass(VerifyContextStorageRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (MetadataStorageRepositoryInterface) scope.get(Reflection.getOrCreateKotlinClass(MetadataStorageRepositoryInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (InsertEventUseCase) scope.get(Reflection.getOrCreateKotlinClass(InsertEventUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null), (String) scope.get(Reflection.getOrCreateKotlinClass(String.class), QualifierKt.named(AndroidCommonDITags.CLIENT_ID), (Function0<? extends ParametersHolder>) null), (TVF) scope.get(Reflection.getOrCreateKotlinClass(TVF.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }
}
