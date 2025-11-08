package com.reown.sign.storage.proposal;

import android.database.sqlite.SQLiteException;
import app.cash.sqldelight.async.coroutines.QueryExtensionsKt;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.reown.sign.common.model.vo.proposal.ProposalVO;
import com.reown.sign.storage.data.dao.optionalnamespaces.OptionalNamespaceDaoQueries;
import com.reown.sign.storage.data.dao.optionalnamespaces.b;
import com.reown.sign.storage.data.dao.proposal.ProposalDaoQueries;
import com.reown.sign.storage.data.dao.proposalnamespace.ProposalNamespaceDaoQueries;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000eJ\u0015\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012J\u0015\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0015J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u0017H@¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u001cJ\u0001\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00112\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00110\u00172\u0006\u0010%\u001a\u00020\u00112\b\u0010&\u001a\u0004\u0018\u00010\u00112\u0006\u0010'\u001a\u00020\u00112\u0014\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0018\u00010)2\u0006\u0010*\u001a\u00020\u00112\b\u0010+\u001a\u0004\u0018\u00010\u001f2\u0014\u0010,\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0018\u00010)H\u0002¢\u0006\u0002\u0010-J$\u0010.\u001a\u00020\u000b2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u0002000)2\u0006\u00101\u001a\u00020\u001fH\u0002J&\u00102\u001a\u00020\u000b2\u0014\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u000200\u0018\u00010)2\u0006\u00101\u001a\u00020\u001fH\u0002J\u001c\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u0002000)2\u0006\u00104\u001a\u00020\u001fH\u0002J\u001c\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u0002000)2\u0006\u00104\u001a\u00020\u001fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/reown/sign/storage/proposal/ProposalStorageRepository;", "", "proposalDaoQueries", "Lcom/reown/sign/storage/data/dao/proposal/ProposalDaoQueries;", "requiredNamespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDaoQueries;", "optionalNamespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDaoQueries;", "<init>", "(Lcom/reown/sign/storage/data/dao/proposal/ProposalDaoQueries;Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDaoQueries;Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDaoQueries;)V", "insertProposal", "", "proposal", "Lcom/reown/sign/common/model/vo/proposal/ProposalVO;", "insertProposal$sign_release", "getProposalByKey", "proposerPubKey", "", "getProposalByKey$sign_release", "getProposalByTopic", "topic", "getProposalByTopic$sign_release", "getProposals", "", "getProposals$sign_release", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProposal", "key", "deleteProposal$sign_release", "mapProposalDaoToProposalVO", "request_id", "", "pairingTopic", "name", "description", "url", "icons", "relay_protocol", "relay_data", "proposer_key", "properties", "", "redirect", "expiry", "scoped_properties", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;)Lcom/reown/sign/common/model/vo/proposal/ProposalVO;", "insertRequiredNamespace", "namespaces", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "proposalId", "insertOptionalNamespace", "getRequiredNamespaces", "id", "getOptionalNamespaces", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nProposalStorageRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProposalStorageRepository.kt\ncom/reown/sign/storage/proposal/ProposalStorageRepository\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,139:1\n216#2,2:140\n216#2,2:142\n*S KotlinDebug\n*F\n+ 1 ProposalStorageRepository.kt\ncom/reown/sign/storage/proposal/ProposalStorageRepository\n*L\n116#1:140,2\n123#1:142,2\n*E\n"})
public final class ProposalStorageRepository {
    /* access modifiers changed from: private */
    @NotNull
    public final OptionalNamespaceDaoQueries optionalNamespaceDaoQueries;
    /* access modifiers changed from: private */
    @NotNull
    public final ProposalDaoQueries proposalDaoQueries;
    /* access modifiers changed from: private */
    @NotNull
    public final ProposalNamespaceDaoQueries requiredNamespaceDaoQueries;

    public ProposalStorageRepository(@NotNull ProposalDaoQueries proposalDaoQueries2, @NotNull ProposalNamespaceDaoQueries proposalNamespaceDaoQueries, @NotNull OptionalNamespaceDaoQueries optionalNamespaceDaoQueries2) {
        Intrinsics.checkNotNullParameter(proposalDaoQueries2, "proposalDaoQueries");
        Intrinsics.checkNotNullParameter(proposalNamespaceDaoQueries, "requiredNamespaceDaoQueries");
        Intrinsics.checkNotNullParameter(optionalNamespaceDaoQueries2, "optionalNamespaceDaoQueries");
        this.proposalDaoQueries = proposalDaoQueries2;
        this.requiredNamespaceDaoQueries = proposalNamespaceDaoQueries;
        this.optionalNamespaceDaoQueries = optionalNamespaceDaoQueries2;
    }

    private final Map<String, Namespace.Proposal> getOptionalNamespaces(long j2) {
        return MapsKt.toMap(this.optionalNamespaceDaoQueries.getOptionalNamespaces(j2, new b(3)).executeAsList());
    }

    /* access modifiers changed from: private */
    public static final Pair getOptionalNamespaces$lambda$4(String str, List list, List list2, List list3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "methods");
        Intrinsics.checkNotNullParameter(list3, "events");
        return TuplesKt.to(str, new Namespace.Proposal(list2, list, list3));
    }

    private final Map<String, Namespace.Proposal> getRequiredNamespaces(long j2) {
        return MapsKt.toMap(this.requiredNamespaceDaoQueries.getProposalNamespaces(j2, new b(2)).executeAsList());
    }

    /* access modifiers changed from: private */
    public static final Pair getRequiredNamespaces$lambda$3(String str, List list, List list2, List list3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "methods");
        Intrinsics.checkNotNullParameter(list3, "events");
        return TuplesKt.to(str, new Namespace.Proposal(list2, list, list3));
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
    public final ProposalVO mapProposalDaoToProposalVO(long j2, String str, String str2, String str3, String str4, List<String> list, String str5, String str6, String str7, Map<String, String> map, String str8, Long l2, Map<String, String> map2) {
        return new ProposalVO(j2, new Topic(str), str2, str3, str4, list, str8, getRequiredNamespaces(j2), getOptionalNamespaces(j2), map, map2, str7, str5, str6, l2 != null ? new Expiry(l2.longValue()) : null);
    }

    public final /* synthetic */ void deleteProposal$sign_release(String str) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new ProposalStorageRepository$deleteProposal$1(this, str, (Continuation<? super ProposalStorageRepository$deleteProposal$1>) null), 3, (Object) null);
    }

    public final /* synthetic */ ProposalVO getProposalByKey$sign_release(String str) throws SQLiteException {
        Intrinsics.checkNotNullParameter(str, "proposerPubKey");
        return (ProposalVO) this.proposalDaoQueries.getProposalByKey(str, new ProposalStorageRepository$getProposalByKey$1(this)).executeAsOne();
    }

    public final /* synthetic */ ProposalVO getProposalByTopic$sign_release(String str) throws SQLiteException {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return (ProposalVO) this.proposalDaoQueries.getProposalByPairingTopic(str, new ProposalStorageRepository$getProposalByTopic$1(this)).executeAsOne();
    }

    public final /* synthetic */ Object getProposals$sign_release(Continuation continuation) throws SQLiteException {
        return QueryExtensionsKt.awaitAsList(this.proposalDaoQueries.getListOfProposalDaos(new ProposalStorageRepository$getProposals$2(this)), continuation);
    }

    public final /* synthetic */ void insertProposal$sign_release(ProposalVO proposalVO) throws SQLiteException {
        Intrinsics.checkNotNullParameter(proposalVO, "proposal");
        ProposalDaoQueries proposalDaoQueries2 = this.proposalDaoQueries;
        Long valueOf = Long.valueOf(proposalVO.getRequestId());
        String value = proposalVO.getPairingTopic().getValue();
        String name = proposalVO.getName();
        String description = proposalVO.getDescription();
        String url = proposalVO.getUrl();
        List<String> icons = proposalVO.getIcons();
        String relayProtocol = proposalVO.getRelayProtocol();
        String relayData = proposalVO.getRelayData();
        String proposerPublicKey = proposalVO.getProposerPublicKey();
        Map<String, String> properties = proposalVO.getProperties();
        String redirect = proposalVO.getRedirect();
        Expiry expiry = proposalVO.getExpiry();
        proposalDaoQueries2.insertOrAbortSession(valueOf, value, name, description, url, icons, relayProtocol, relayData, proposerPublicKey, properties, redirect, expiry != null ? Long.valueOf(expiry.getSeconds()) : null, proposalVO.getScopedProperties());
        insertRequiredNamespace(proposalVO.getRequiredNamespaces(), proposalVO.getRequestId());
        insertOptionalNamespace(proposalVO.getOptionalNamespaces(), proposalVO.getRequestId());
    }
}
