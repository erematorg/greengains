package com.reown.sign.storage.sequence;

import android.database.sqlite.SQLiteException;
import androidx.compose.animation.core.a;
import androidx.compose.runtime.e;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.sdk.storage.data.dao.p;
import com.reown.foundation.common.model.PublicKey;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.reown.sign.common.model.vo.sequence.SessionVO;
import com.reown.sign.engine.SessionRequestQueueKt;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.storage.data.dao.namespace.NamespaceDaoQueries;
import com.reown.sign.storage.data.dao.optionalnamespaces.OptionalNamespaceDaoQueries;
import com.reown.sign.storage.data.dao.proposalnamespace.ProposalNamespaceDaoQueries;
import com.reown.sign.storage.data.dao.session.SessionDaoQueries;
import com.reown.sign.storage.data.dao.temp.TempNamespaceDaoQueries;
import com.reown.sign.storage.data.dao.temp.b;
import com.reown.utils.UtilFunctionsKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\u0010J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0013\u001a\u00020\u0010J\u000e\u0010 \u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0010J\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001a2\u0006\u0010#\u001a\u00020\u0010J\u0016\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020'J\u000e\u0010(\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0010J\u0016\u0010)\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010*\u001a\u00020'J*\u0010+\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\"2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020.0-2\u0006\u0010&\u001a\u00020'J\u001a\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020.0-2\u0006\u0010&\u001a\u00020'J*\u00100\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\"2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020.0-2\u0006\u00101\u001a\u00020'J\u0016\u00102\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\"2\u0006\u00103\u001a\u00020'J\u0016\u00104\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\"2\u0006\u00103\u001a\u00020'J\u000e\u00105\u001a\u00020\u00142\u0006\u0010&\u001a\u00020'J\u000e\u00106\u001a\u00020\u00142\u0006\u0010&\u001a\u00020'J\u000e\u00107\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\"J\u000e\u00108\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0010J,\u00109\u001a\u00020\u00142\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020.0-2\u0006\u0010:\u001a\u00020'2\u0006\u0010&\u001a\u00020'H\u0002J$\u0010;\u001a\u00020\u00142\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020<0-2\u0006\u0010:\u001a\u00020'H\u0002J&\u0010=\u001a\u00020\u00142\u0014\u0010,\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020<\u0018\u00010-2\u0006\u0010:\u001a\u00020'H\u0002J&\u0010>\u001a\u00020\u001d2\u0006\u0010?\u001a\u00020'2\u0006\u0010\u0013\u001a\u00020\u00102\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00140AH\u0002J\u0001\u0010B\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020'2\u0006\u0010\u0013\u001a\u00020\"2\u0006\u0010?\u001a\u00020'2\u0006\u0010D\u001a\u00020\"2\b\u0010E\u001a\u0004\u0018\u00010\"2\b\u0010F\u001a\u0004\u0018\u00010\"2\u0006\u0010G\u001a\u00020\"2\b\u0010H\u001a\u0004\u0018\u00010\"2\u0006\u0010I\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\"2\u0014\u0010J\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\"\u0018\u00010-2\b\u0010K\u001a\u0004\u0018\u00010L2\u0014\u0010M\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\"\u0018\u00010-H\u0002J\u001c\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020<0-2\u0006\u0010C\u001a\u00020'H\u0002J\u001c\u0010O\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020<0-2\u0006\u0010C\u001a\u00020'H\u0002J\u001c\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020.0-2\u0006\u0010C\u001a\u00020'H\u0002J^\u0010Q\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020.0R2\u0006\u0010:\u001a\u00020'2\u0006\u0010S\u001a\u00020\"2\u000e\u0010T\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u001a2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020\"0\u001a2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020\"0\u001a2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\"0\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R5\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006X"}, d2 = {"Lcom/reown/sign/storage/sequence/SessionStorageRepository;", "", "sessionDaoQueries", "Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries;", "namespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/namespace/NamespaceDaoQueries;", "requiredNamespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDaoQueries;", "optionalNamespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDaoQueries;", "tempNamespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDaoQueries;", "<init>", "(Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries;Lcom/reown/sign/storage/data/dao/namespace/NamespaceDaoQueries;Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDaoQueries;Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDaoQueries;Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDaoQueries;)V", "onSessionExpired", "Lkotlin/Function1;", "Lcom/reown/foundation/common/model/Topic;", "Lkotlin/ParameterName;", "name", "topic", "", "getOnSessionExpired", "()Lkotlin/jvm/functions/Function1;", "setOnSessionExpired", "(Lkotlin/jvm/functions/Function1;)V", "getListOfSessionVOsWithoutMetadata", "", "Lcom/reown/sign/common/model/vo/sequence/SessionVO;", "isSessionValid", "", "getSessionExpiryByTopic", "Lcom/reown/android/internal/common/model/Expiry;", "getSessionWithoutMetadataByTopic", "getAllSessionTopicsByPairingTopic", "", "pairingTopic", "insertSession", "session", "requestId", "", "acknowledgeSession", "extendSession", "expiryInSeconds", "insertTempNamespaces", "namespaces", "", "Lcom/reown/android/internal/common/model/Namespace$Session;", "getTempNamespaces", "deleteNamespaceAndInsertNewNamespace", "requestID", "isUpdatedNamespaceValid", "timestamp", "isUpdatedNamespaceResponseValid", "markUnAckNamespaceAcknowledged", "deleteTempNamespacesByRequestId", "deleteTempNamespacesByTopic", "deleteSession", "insertNamespace", "sessionId", "insertRequiredNamespace", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "insertOptionalNamespace", "verifyExpiry", "expiry", "deleteSequence", "Lkotlin/Function0;", "mapSessionDaoToSessionVO", "id", "relay_protocol", "relay_data", "controller_key", "self_participant", "peer_participant", "is_acknowledged", "properties", "transportType", "Lcom/reown/android/internal/common/model/TransportType;", "scoped_properties", "getRequiredNamespaces", "getOptionalNamespaces", "getSessionNamespaces", "mapTempNamespaceToNamespaceVO", "Lkotlin/Pair;", "key", "chains", "accounts", "methods", "events", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSessionStorageRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionStorageRepository.kt\ncom/reown/sign/storage/sequence/SessionStorageRepository\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,283:1\n216#2,2:284\n216#2,2:292\n216#2,2:294\n216#2,2:296\n216#2,2:298\n1193#3,2:286\n1267#3,4:288\n*S KotlinDebug\n*F\n+ 1 SessionStorageRepository.kt\ncom/reown/sign/storage/sequence/SessionStorageRepository\n*L\n111#1:284,2\n143#1:292,2\n185#1:294,2\n192#1:296,2\n199#1:298,2\n129#1:286,2\n129#1:288,4\n*E\n"})
public final class SessionStorageRepository {
    @NotNull
    private final NamespaceDaoQueries namespaceDaoQueries;
    private /* synthetic */ Function1<? super Topic, Unit> onSessionExpired = new b(2);
    @NotNull
    private final OptionalNamespaceDaoQueries optionalNamespaceDaoQueries;
    @NotNull
    private final ProposalNamespaceDaoQueries requiredNamespaceDaoQueries;
    @NotNull
    private final SessionDaoQueries sessionDaoQueries;
    @NotNull
    private final TempNamespaceDaoQueries tempNamespaceDaoQueries;

    public SessionStorageRepository(@NotNull SessionDaoQueries sessionDaoQueries2, @NotNull NamespaceDaoQueries namespaceDaoQueries2, @NotNull ProposalNamespaceDaoQueries proposalNamespaceDaoQueries, @NotNull OptionalNamespaceDaoQueries optionalNamespaceDaoQueries2, @NotNull TempNamespaceDaoQueries tempNamespaceDaoQueries2) {
        Intrinsics.checkNotNullParameter(sessionDaoQueries2, "sessionDaoQueries");
        Intrinsics.checkNotNullParameter(namespaceDaoQueries2, "namespaceDaoQueries");
        Intrinsics.checkNotNullParameter(proposalNamespaceDaoQueries, "requiredNamespaceDaoQueries");
        Intrinsics.checkNotNullParameter(optionalNamespaceDaoQueries2, "optionalNamespaceDaoQueries");
        Intrinsics.checkNotNullParameter(tempNamespaceDaoQueries2, "tempNamespaceDaoQueries");
        this.sessionDaoQueries = sessionDaoQueries2;
        this.namespaceDaoQueries = namespaceDaoQueries2;
        this.requiredNamespaceDaoQueries = proposalNamespaceDaoQueries;
        this.optionalNamespaceDaoQueries = optionalNamespaceDaoQueries2;
        this.tempNamespaceDaoQueries = tempNamespaceDaoQueries2;
    }

    /* access modifiers changed from: private */
    public static final boolean deleteSession$lambda$9(Topic topic, EngineDO.SessionRequestEvent sessionRequestEvent) {
        return Intrinsics.areEqual((Object) sessionRequestEvent.getRequest().getTopic(), (Object) topic.getValue());
    }

    private final Map<String, Namespace.Proposal> getOptionalNamespaces(long j2) {
        return MapsKt.toMap(this.optionalNamespaceDaoQueries.getOptionalNamespaces(j2, new com.reown.sign.storage.data.dao.optionalnamespaces.b(4)).executeAsList());
    }

    /* access modifiers changed from: private */
    public static final Pair getOptionalNamespaces$lambda$14(String str, List list, List list2, List list3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "methods");
        Intrinsics.checkNotNullParameter(list3, "events");
        return TuplesKt.to(str, new Namespace.Proposal(list2, list, list3));
    }

    private final Map<String, Namespace.Proposal> getRequiredNamespaces(long j2) {
        return MapsKt.toMap(this.requiredNamespaceDaoQueries.getProposalNamespaces(j2, new com.reown.sign.storage.data.dao.optionalnamespaces.b(5)).executeAsList());
    }

    /* access modifiers changed from: private */
    public static final Pair getRequiredNamespaces$lambda$13(String str, List list, List list2, List list3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "methods");
        Intrinsics.checkNotNullParameter(list3, "events");
        return TuplesKt.to(str, new Namespace.Proposal(list2, list, list3));
    }

    private final Map<String, Namespace.Session> getSessionNamespaces(long j2) {
        return MapsKt.toMap(this.namespaceDaoQueries.getNamespaces(j2, new p(3)).executeAsList());
    }

    /* access modifiers changed from: private */
    public static final Pair getSessionNamespaces$lambda$15(String str, List list, List list2, List list3, List list4) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "accounts");
        Intrinsics.checkNotNullParameter(list3, "methods");
        Intrinsics.checkNotNullParameter(list4, "events");
        return TuplesKt.to(str, new Namespace.Session(list, list2, list3, list4));
    }

    private final void insertNamespace(Map<String, Namespace.Session> map, long j2, long j3) throws SQLiteException {
        for (Map.Entry next : map.entrySet()) {
            Namespace.Session session = (Namespace.Session) next.getValue();
            this.namespaceDaoQueries.insertOrAbortNamespace(j2, (String) next.getKey(), session.getChains(), session.getAccounts(), session.getMethods(), session.getEvents(), j3);
        }
    }

    private final void insertOptionalNamespace(Map<String, Namespace.Proposal> map, long j2) throws SQLiteException {
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                Namespace.Proposal proposal = (Namespace.Proposal) next.getValue();
                this.optionalNamespaceDaoQueries.insertOrAbortOptionalNamespace(j2, (String) next.getKey(), proposal.getChains(), proposal.getMethods(), proposal.getEvents());
            }
        }
    }

    private final void insertRequiredNamespace(Map<String, Namespace.Proposal> map, long j2) throws SQLiteException {
        for (Map.Entry next : map.entrySet()) {
            Namespace.Proposal proposal = (Namespace.Proposal) next.getValue();
            this.requiredNamespaceDaoQueries.insertOrAbortProposalNamespace(j2, (String) next.getKey(), proposal.getChains(), proposal.getMethods(), proposal.getEvents());
        }
    }

    /* access modifiers changed from: private */
    public static final Unit isSessionValid$lambda$2$lambda$1(SessionStorageRepository sessionStorageRepository, Topic topic) {
        sessionStorageRepository.sessionDaoQueries.deleteSession(topic.getValue());
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final SessionVO mapSessionDaoToSessionVO(long j2, String str, long j3, String str2, String str3, String str4, String str5, String str6, boolean z2, String str7, Map<String, String> map, TransportType transportType, Map<String, String> map2) {
        return new SessionVO(new Topic(str), new Expiry(j3), str2, str3, PublicKey.m8856constructorimpl(str4 == null ? Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>") : str4), PublicKey.m8856constructorimpl(str5), (AppMetaData) null, PublicKey.m8856constructorimpl(str6 == null ? Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>") : str6), (AppMetaData) null, getSessionNamespaces(j2), getRequiredNamespaces(j2), getOptionalNamespaces(j2), map, map2, z2, str7, transportType, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public final Pair<String, Namespace.Session> mapTempNamespaceToNamespaceVO(long j2, String str, List<String> list, List<String> list2, List<String> list3, List<String> list4) {
        return TuplesKt.to(str, new Namespace.Session(list, list2, list3, list4));
    }

    /* access modifiers changed from: private */
    public static final Unit onSessionExpired$lambda$0(Topic topic) {
        Intrinsics.checkNotNullParameter(topic, "it");
        return Unit.INSTANCE;
    }

    private final boolean verifyExpiry(long j2, Topic topic, Function0<Unit> function0) {
        if (UtilFunctionsKt.isSequenceValid(new Expiry(j2))) {
            return true;
        }
        function0.invoke();
        this.onSessionExpired.invoke(topic);
        return false;
    }

    public final /* synthetic */ void acknowledgeSession(Topic topic) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        this.sessionDaoQueries.acknowledgeSession(true, topic.getValue());
    }

    public final /* synthetic */ void deleteNamespaceAndInsertNewNamespace(String str, Map map, long j2) throws SQLiteException {
        String str2 = str;
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(map, "namespaces");
        long longValue = this.sessionDaoQueries.getSessionIdByTopic(str2).executeAsOne().longValue();
        this.namespaceDaoQueries.deleteNamespacesByTopic(str2);
        for (Map.Entry entry : map.entrySet()) {
            Namespace.Session session = (Namespace.Session) entry.getValue();
            this.namespaceDaoQueries.insertOrAbortNamespace(longValue, (String) entry.getKey(), session.getChains(), session.getAccounts(), session.getMethods(), session.getEvents(), j2);
        }
    }

    public final /* synthetic */ void deleteSession(Topic topic) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        CollectionsKt__MutableCollectionsKt.removeAll(SessionRequestQueueKt.getSessionRequestEventsQueue(), new D1.b(topic, 15));
        this.namespaceDaoQueries.deleteNamespacesByTopic(topic.getValue());
        this.requiredNamespaceDaoQueries.deleteProposalNamespacesByTopic(topic.getValue());
        this.optionalNamespaceDaoQueries.deleteOptionalNamespacesByTopic(topic.getValue());
        this.tempNamespaceDaoQueries.deleteTempNamespacesByTopic(topic.getValue());
        this.sessionDaoQueries.deleteSession(topic.getValue());
    }

    public final /* synthetic */ void deleteTempNamespacesByRequestId(long j2) {
        this.tempNamespaceDaoQueries.deleteTempNamespacesByRequestId(j2);
    }

    public final /* synthetic */ void deleteTempNamespacesByTopic(String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        this.tempNamespaceDaoQueries.deleteTempNamespacesByTopic(str);
    }

    public final /* synthetic */ void extendSession(Topic topic, long j2) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        this.sessionDaoQueries.updateSessionExpiry(j2, topic.getValue());
    }

    public final /* synthetic */ List getAllSessionTopicsByPairingTopic(Topic topic) {
        Intrinsics.checkNotNullParameter(topic, "pairingTopic");
        return this.sessionDaoQueries.getAllSessionTopicsByPairingTopic(topic.getValue()).executeAsList();
    }

    public final /* synthetic */ List getListOfSessionVOsWithoutMetadata() {
        return this.sessionDaoQueries.getListOfSessionDaos(new SessionStorageRepository$getListOfSessionVOsWithoutMetadata$1(this)).executeAsList();
    }

    @NotNull
    public final Function1<Topic, Unit> getOnSessionExpired() {
        return this.onSessionExpired;
    }

    public final /* synthetic */ Expiry getSessionExpiryByTopic(Topic topic) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Long executeAsOneOrNull = this.sessionDaoQueries.getExpiry(topic.getValue()).executeAsOneOrNull();
        if (executeAsOneOrNull != null) {
            return new Expiry(executeAsOneOrNull.longValue());
        }
        return null;
    }

    public final /* synthetic */ SessionVO getSessionWithoutMetadataByTopic(Topic topic) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        return (SessionVO) this.sessionDaoQueries.getSessionByTopic(topic.getValue(), new SessionStorageRepository$getSessionWithoutMetadataByTopic$1(this)).executeAsOne();
    }

    public final /* synthetic */ Map getTempNamespaces(long j2) {
        Iterable<Pair> executeAsList = this.tempNamespaceDaoQueries.getTempNamespacesByRequestId(j2, new SessionStorageRepository$getTempNamespaces$1(this)).executeAsList();
        LinkedHashMap linkedHashMap = new LinkedHashMap(a.h(executeAsList, 16));
        for (Pair pair : executeAsList) {
            Pair pair2 = TuplesKt.to((String) pair.component1(), (Namespace.Session) pair.component2());
            linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
        }
        return linkedHashMap;
    }

    public final synchronized /* synthetic */ void insertSession(SessionVO sessionVO, long j2) throws SQLiteException {
        synchronized (this) {
            Intrinsics.checkNotNullParameter(sessionVO, "session");
            SessionDaoQueries sessionDaoQueries2 = this.sessionDaoQueries;
            String value = sessionVO.getTopic().getValue();
            String pairingTopic = sessionVO.getPairingTopic();
            long seconds = sessionVO.getExpiry().getSeconds();
            String r17 = sessionVO.m8878getSelfPublicKeyuN_RPug();
            String relayProtocol = sessionVO.getRelayProtocol();
            String r02 = sessionVO.m8876getControllerKeyeO6xuyU();
            String str = r02 == null ? null : r02;
            String r03 = sessionVO.m8877getPeerPublicKeyeO6xuyU();
            sessionDaoQueries2.insertOrAbortSession(value, pairingTopic, seconds, relayProtocol, sessionVO.getRelayData(), str, r17, r03 == null ? null : r03, sessionVO.isAcknowledged(), sessionVO.getProperties(), sessionVO.getTransportType(), sessionVO.getScopedProperties());
            long longValue = this.sessionDaoQueries.lastInsertedRow().executeAsOne().longValue();
            insertNamespace(sessionVO.getSessionNamespaces(), longValue, j2);
            insertRequiredNamespace(sessionVO.getRequiredNamespaces(), longValue);
            insertOptionalNamespace(sessionVO.getOptionalNamespaces(), longValue);
        }
    }

    public final /* synthetic */ void insertTempNamespaces(String str, Map map, long j2) throws SQLiteException {
        String str2 = str;
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(map, "namespaces");
        long longValue = this.sessionDaoQueries.getSessionIdByTopic(str2).executeAsOne().longValue();
        for (Map.Entry entry : map.entrySet()) {
            Namespace.Session session = (Namespace.Session) entry.getValue();
            TempNamespaceDaoQueries tempNamespaceDaoQueries2 = this.tempNamespaceDaoQueries;
            List<String> chains = session.getChains();
            List<String> accounts = session.getAccounts();
            List<String> methods = session.getMethods();
            List<String> events = session.getEvents();
            tempNamespaceDaoQueries2.insertOrAbortNamespace(longValue, str, (String) entry.getKey(), chains, accounts, methods, events, j2);
        }
    }

    public final /* synthetic */ boolean isSessionValid(Topic topic) {
        Long executeAsOneOrNull;
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        if (this.sessionDaoQueries.hasTopic(topic.getValue()).executeAsOneOrNull() == null || (executeAsOneOrNull = this.sessionDaoQueries.getExpiry(topic.getValue()).executeAsOneOrNull()) == null) {
            return false;
        }
        return verifyExpiry(executeAsOneOrNull.longValue(), topic, new e(this, topic, 8));
    }

    public final /* synthetic */ boolean isUpdatedNamespaceResponseValid(String str, long j2) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Boolean executeAsOneOrNull = this.tempNamespaceDaoQueries.isUpdateNamespaceRequestValid(str, j2).executeAsOneOrNull();
        if (executeAsOneOrNull != null) {
            return executeAsOneOrNull.booleanValue();
        }
        return false;
    }

    public final /* synthetic */ boolean isUpdatedNamespaceValid(String str, long j2) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Boolean executeAsOneOrNull = this.namespaceDaoQueries.isUpdateNamespaceRequestValid(j2, str).executeAsOneOrNull();
        if (executeAsOneOrNull != null) {
            return executeAsOneOrNull.booleanValue();
        }
        return false;
    }

    public final /* synthetic */ void markUnAckNamespaceAcknowledged(long j2) {
        this.tempNamespaceDaoQueries.markNamespaceAcknowledged(j2);
    }

    public final void setOnSessionExpired(@NotNull Function1<? super Topic, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onSessionExpired = function1;
    }
}
