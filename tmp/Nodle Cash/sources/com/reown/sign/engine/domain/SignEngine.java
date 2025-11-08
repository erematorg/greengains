package com.reown.sign.engine.domain;

import androidx.compose.runtime.e;
import com.google.firebase.messaging.Constants;
import com.reown.android.Core;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.Pairing;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.WCResponse;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.android.internal.common.model.type.JsonRpcClientSync;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.android.pulse.domain.InsertTelemetryEventUseCase;
import com.reown.android.push.notifications.DecryptMessageUseCaseInterface;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.relay.WSSConnectionState;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.Request;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.common.model.vo.sequence.SessionVO;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCaseInterface;
import com.reown.sign.engine.use_case.calls.ApproveSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.DisconnectSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.EmitEventUseCaseInterface;
import com.reown.sign.engine.use_case.calls.ExtendSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.FormatAuthenticateMessageUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetListOfVerifyContextsUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetPairingsUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetPendingAuthenticateRequestUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetSessionProposalsUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetSessionsUseCaseInterface;
import com.reown.sign.engine.use_case.calls.GetVerifyContextByIdUseCaseInterface;
import com.reown.sign.engine.use_case.calls.PairUseCaseInterface;
import com.reown.sign.engine.use_case.calls.PingUseCaseInterface;
import com.reown.sign.engine.use_case.calls.ProposeSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.RejectSessionAuthenticateUseCaseInterface;
import com.reown.sign.engine.use_case.calls.RejectSessionUseCaseInterface;
import com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCaseInterface;
import com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCaseInterface;
import com.reown.sign.engine.use_case.calls.SessionRequestUseCaseInterface;
import com.reown.sign.engine.use_case.calls.SessionUpdateUseCaseInterface;
import com.reown.sign.engine.use_case.requests.OnPingUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionDeleteUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionEventUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionExtendUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionSettleUseCase;
import com.reown.sign.engine.use_case.requests.OnSessionUpdateUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionProposalResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionRequestResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionSettleResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionUpdateResponseUseCase;
import com.reown.sign.json_rpc.domain.DeleteRequestByIdUseCase;
import com.reown.sign.json_rpc.domain.GetPendingRequestsUseCaseByTopicInterface;
import com.reown.sign.json_rpc.domain.GetPendingSessionRequestByTopicUseCaseInterface;
import com.reown.sign.json_rpc.domain.GetPendingSessionRequests;
import com.reown.sign.json_rpc.model.JsonRpcMethod;
import com.reown.sign.storage.authenticate.AuthenticateResponseTopicRepository;
import com.reown.sign.storage.proposal.ProposalStorageRepository;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import com.reown.utils.UtilFunctionsKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000¬\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000b2\u00020\f2\u00020\r2\u00020\u000e2\u00020\u000f2\u00020\u00102\u00020\u00112\u00020\u00122\u00020\u00132\u00020\u00142\u00020\u00152\u00020\u00162\u00020\u00172\u00020\u00182\u00020\u0019:\u0002\u0002B\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u0013\u0012\u0006\u0010\u001d\u001a\u00020\u0015\u0012\u0006\u0010\u001e\u001a\u00020\u001f\u0012\u0006\u0010 \u001a\u00020\u0014\u0012\u0006\u0010!\u001a\u00020\"\u0012\u0006\u0010#\u001a\u00020$\u0012\u0006\u0010%\u001a\u00020&\u0012\u0006\u0010'\u001a\u00020(\u0012\u0006\u0010)\u001a\u00020*\u0012\u0006\u0010+\u001a\u00020,\u0012\u0006\u0010-\u001a\u00020.\u0012\u0006\u0010/\u001a\u000200\u0012\u0006\u00101\u001a\u00020\u0001\u0012\u0006\u00102\u001a\u00020\u0002\u0012\u0006\u00103\u001a\u00020\u0003\u0012\u0006\u00104\u001a\u00020\u0004\u0012\u0006\u00105\u001a\u00020\u0005\u0012\u0006\u00106\u001a\u00020\u0006\u0012\u0006\u00107\u001a\u00020\u0007\u0012\u0006\u00108\u001a\u00020\b\u0012\u0006\u00109\u001a\u00020\t\u0012\u0006\u0010:\u001a\u00020\n\u0012\u0006\u0010;\u001a\u00020\u000b\u0012\u0006\u0010<\u001a\u00020\f\u0012\u0006\u0010=\u001a\u00020\r\u0012\u0006\u0010>\u001a\u00020\u000e\u0012\u0006\u0010?\u001a\u00020\u000f\u0012\u0006\u0010@\u001a\u00020\u0010\u0012\u0006\u0010A\u001a\u00020\u0011\u0012\u0006\u0010B\u001a\u00020\u0012\u0012\u0006\u0010C\u001a\u00020\u0016\u0012\u0006\u0010D\u001a\u00020\u0017\u0012\u0006\u0010E\u001a\u00020\u0018\u0012\u0006\u0010F\u001a\u00020G\u0012\u0006\u0010H\u001a\u00020I\u0012\u0006\u0010J\u001a\u00020K\u0012\u0006\u0010L\u001a\u00020M\u0012\u0006\u0010N\u001a\u00020O\u0012\u0006\u0010P\u001a\u00020Q\u0012\u0006\u0010R\u001a\u00020S\u0012\u0006\u0010T\u001a\u00020U\u0012\u0006\u0010V\u001a\u00020W\u0012\u0006\u0010X\u001a\u00020Y\u0012\u0006\u0010Z\u001a\u00020[\u0012\u0006\u0010\\\u001a\u00020]\u0012\u0006\u0010^\u001a\u00020_\u0012\u0006\u0010`\u001a\u00020a\u0012\u0006\u0010b\u001a\u00020c\u0012\u0006\u0010d\u001a\u00020\u0019\u0012\u0006\u0010e\u001a\u00020f¢\u0006\u0004\bg\u0010hJ\u0006\u0010|\u001a\u00020}J\b\u0010~\u001a\u00020}H\u0002J\b\u0010\u001a\u00020}H\u0002J\t\u0010\u0001\u001a\u00020}H\u0002J\t\u0010\u0001\u001a\u00020jH\u0002J\t\u0010\u0001\u001a\u00020jH\u0002J\t\u0010\u0001\u001a\u00020jH\u0002J\t\u0010\u0001\u001a\u00020jH\u0002J\t\u0010\u0001\u001a\u00020}H\u0002J\t\u0010\u0001\u001a\u00020}H\u0002J\t\u0010\u0001\u001a\u00020}H\u0002J\t\u0010\u0001\u001a\u00020jH\u0002J\t\u0010\u0001\u001a\u00020}H\u0002J\t\u0010\u0001\u001a\u00020}H\u0002J\t\u0010\u0001\u001a\u00020}H\u0002J\u0010\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020}0\u0001H\u0002J\u0001\u0010\u0001\u001a\u00020}2\b\u0010\u0001\u001a\u00030\u00012\u0016\u0010\u0001\u001a\u0011\u0012\u0005\u0012\u00030\u0001\u0012\u0005\u0012\u00030\u00010\u00012\u0018\u0010\u0001\u001a\u0013\u0012\u0005\u0012\u00030\u0001\u0012\u0005\u0012\u00030\u0001\u0018\u00010\u00012\u0018\u0010\u0001\u001a\u0013\u0012\u0005\u0012\u00030\u0001\u0012\u0005\u0012\u00030\u0001\u0018\u00010\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010\u0001JR\u0010\u0001\u001a\u00020}2\b\u0010\u0001\u001a\u00030\u00012\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u00030¡\u00010 \u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010¢\u0001J\u0010£\u0001\u001a\u00020}2\b\u0010£\u0001\u001a\u00030¤\u00012\u0011\u0010¥\u0001\u001a\f\u0012\u0005\u0012\u00030\u0001\u0018\u00010 \u00012\n\u0010¦\u0001\u001a\u0005\u0018\u00010\u00012\n\u0010§\u0001\u001a\u0005\u0018\u00010¨\u00012\n\u0010©\u0001\u001a\u0005\u0018\u00010\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010ª\u0001JR\u0010«\u0001\u001a\u00020}2\b\u0010¬\u0001\u001a\u00030\u00012\b\u0010­\u0001\u001a\u00030\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030®\u0001\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010¯\u0001JA\u0010°\u0001\u001a\u00020}2\b\u0010¬\u0001\u001a\u00030\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010±\u0001J\u0014\u0010²\u0001\u001a\u00020}2\b\u0010³\u0001\u001a\u00030\u0001H\u0001JW\u0010´\u0001\u001a\u00020}2\b\u0010¬\u0001\u001a\u00030\u00012\b\u0010µ\u0001\u001a\u00030¶\u00012\n\u0010\u0001\u001a\u0005\u0018\u00010\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010·\u0001JA\u0010¸\u0001\u001a\u00020}2\b\u0010¬\u0001\u001a\u00030\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010±\u0001J%\u0010¹\u0001\u001a\u00030\u00012\b\u0010º\u0001\u001a\u00030»\u00012\b\u0010¼\u0001\u001a\u00030\u0001HA¢\u0006\u0003\u0010½\u0001J\u0018\u0010¾\u0001\u001a\n\u0012\u0005\u0012\u00030¿\u00010 \u0001HA¢\u0006\u0003\u0010À\u0001J\u0018\u0010Á\u0001\u001a\n\u0012\u0005\u0012\u00030Â\u00010 \u0001HA¢\u0006\u0003\u0010À\u0001J\u0018\u0010Ã\u0001\u001a\n\u0012\u0005\u0012\u00030Ä\u00010 \u0001HA¢\u0006\u0003\u0010À\u0001J\u001f\u0010Å\u0001\u001a\u0011\u0012\f\u0012\n\u0012\u0005\u0012\u00030Ç\u00010Æ\u00010 \u0001HA¢\u0006\u0003\u0010À\u0001J)\u0010È\u0001\u001a\u0011\u0012\f\u0012\n\u0012\u0005\u0012\u00030\u00010Æ\u00010 \u00012\b\u0010¬\u0001\u001a\u00030É\u0001HA¢\u0006\u0003\u0010Ê\u0001J!\u0010\u001e\u001a\n\u0012\u0005\u0012\u00030Ë\u00010 \u00012\b\u0010¬\u0001\u001a\u00030É\u0001HA¢\u0006\u0003\u0010Ê\u0001J\u0018\u0010Ì\u0001\u001a\n\u0012\u0005\u0012\u00030Í\u00010 \u0001HA¢\u0006\u0003\u0010À\u0001J\u001d\u0010Î\u0001\u001a\u0005\u0018\u00010Ä\u00012\b\u0010\u0001\u001a\u00030\u0001HA¢\u0006\u0003\u0010Ï\u0001JA\u0010Ð\u0001\u001a\u00020}2\b\u0010Ñ\u0001\u001a\u00030\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010±\u0001JU\u0010Ò\u0001\u001a\u00020}2\b\u0010¬\u0001\u001a\u00030\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u00012\b\u0010Ó\u0001\u001a\u00030Ô\u0001HA¢\u0006\u0006\bÕ\u0001\u0010Ö\u0001J©\u0001\u0010×\u0001\u001a\u00020}2\u0018\u0010Ø\u0001\u001a\u0013\u0012\u0005\u0012\u00030\u0001\u0012\u0005\u0012\u00030Ù\u0001\u0018\u00010\u00012\u0018\u0010Ú\u0001\u001a\u0013\u0012\u0005\u0012\u00030\u0001\u0012\u0005\u0012\u00030Ù\u0001\u0018\u00010\u00012\u0018\u0010Û\u0001\u001a\u0013\u0012\u0005\u0012\u00030\u0001\u0012\u0005\u0012\u00030\u0001\u0018\u00010\u00012\u0018\u0010\u0001\u001a\u0013\u0012\u0005\u0012\u00030\u0001\u0012\u0005\u0012\u00030\u0001\u0018\u00010\u00012\b\u0010Ü\u0001\u001a\u00030Ý\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010Þ\u0001JK\u0010ß\u0001\u001a\u00020}2\b\u0010\u0001\u001a\u00030\u00012\b\u0010à\u0001\u001a\u00030\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010á\u0001JK\u0010â\u0001\u001a\u00020}2\b\u0010\u0001\u001a\u00030\u00012\b\u0010à\u0001\u001a\u00030\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010ã\u0001JK\u0010ä\u0001\u001a\u00020}2\b\u0010¬\u0001\u001a\u00030\u00012\b\u0010å\u0001\u001a\u00030æ\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010ç\u0001JH\u0010è\u0001\u001a\u00020}2\b\u0010é\u0001\u001a\u00030ê\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010ë\u0001JY\u0010ì\u0001\u001a\u00020}2\b\u0010¬\u0001\u001a\u00030\u00012\u0016\u0010í\u0001\u001a\u0011\u0012\u0005\u0012\u00030\u0001\u0012\u0005\u0012\u00030\u00010\u00012\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020}0\u00012\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020}0\u0001HA¢\u0006\u0003\u0010î\u0001J6\u0010ï\u0001\u001a\u00020}2\f\u0010ð\u0001\u001a\u0007\u0012\u0002\b\u00030ñ\u00012\b\u0010¬\u0001\u001a\u00030É\u00012\b\u0010ò\u0001\u001a\u00030\u00012\b\u0010ó\u0001\u001a\u00030ô\u0001H\u0001J>\u0010õ\u0001\u001a\u00020}2\b\u0010¬\u0001\u001a\u00030É\u00012\b\u0010ö\u0001\u001a\u00030æ\u00012\b\u0010ò\u0001\u001a\u00030\u00012\n\u0010÷\u0001\u001a\u0005\u0018\u00010ø\u00012\b\u0010ó\u0001\u001a\u00030ô\u0001H\u0001R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020GX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020IX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020KX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020MX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020OX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020QX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020SX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020UX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020WX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020YX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020[X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020]X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020_X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u00020fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010i\u001a\u0004\u0018\u00010jX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010k\u001a\u0004\u0018\u00010jX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010l\u001a\u0004\u0018\u00010jX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010m\u001a\u0004\u0018\u00010jX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010n\u001a\u0004\u0018\u00010jX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010o\u001a\u0004\u0018\u00010jX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010p\u001a\b\u0012\u0004\u0012\u00020r0qX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010s\u001a\b\u0012\u0004\u0012\u00020r0t¢\u0006\b\n\u0000\u001a\u0004\bu\u0010vR\u0017\u0010w\u001a\b\u0012\u0004\u0012\u00020y0x¢\u0006\b\n\u0000\u001a\u0004\bz\u0010{R\u001b\u0010ù\u0001\u001a\t\u0012\u0005\u0012\u00030ú\u00010tX\u0005¢\u0006\u0007\u001a\u0005\bû\u0001\u0010vR\u001b\u0010ü\u0001\u001a\t\u0012\u0005\u0012\u00030ý\u00010tX\u0005¢\u0006\u0007\u001a\u0005\bþ\u0001\u0010vR\u001a\u0010ÿ\u0001\u001a\b\u0012\u0004\u0012\u00020r0tX\u0005¢\u0006\u0007\u001a\u0005\b\u0002\u0010vR\u001b\u0010\u0002\u001a\t\u0012\u0005\u0012\u00030ý\u00010tX\u0005¢\u0006\u0007\u001a\u0005\b\u0002\u0010vR\u001b\u0010\u0002\u001a\t\u0012\u0005\u0012\u00030\u00020tX\u0005¢\u0006\u0007\u001a\u0005\b\u0002\u0010vR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020r0tX\u0005¢\u0006\u0007\u001a\u0005\b\u0002\u0010v¨\u0006\u0002"}, d2 = {"Lcom/reown/sign/engine/domain/SignEngine;", "Lcom/reown/sign/engine/use_case/calls/ProposeSessionUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/SessionAuthenticateUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/PairUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/RejectSessionUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/ApproveSessionUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/ApproveSessionAuthenticateUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/RejectSessionAuthenticateUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/SessionUpdateUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/SessionRequestUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/RespondSessionRequestUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/PingUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/FormatAuthenticateMessageUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/EmitEventUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/ExtendSessionUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/DisconnectSessionUseCaseInterface;", "Lcom/reown/android/push/notifications/DecryptMessageUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/GetSessionsUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/GetPairingsUseCaseInterface;", "Lcom/reown/sign/json_rpc/domain/GetPendingRequestsUseCaseByTopicInterface;", "Lcom/reown/sign/engine/use_case/calls/GetPendingAuthenticateRequestUseCaseInterface;", "Lcom/reown/sign/json_rpc/domain/GetPendingSessionRequestByTopicUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/GetSessionProposalsUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/GetVerifyContextByIdUseCaseInterface;", "Lcom/reown/sign/engine/use_case/calls/GetListOfVerifyContextsUseCaseInterface;", "Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "getPendingRequestsByTopicUseCase", "getPendingSessionRequestByTopicUseCase", "getPendingSessionRequests", "Lcom/reown/sign/json_rpc/domain/GetPendingSessionRequests;", "getPendingAuthenticateRequestUseCase", "deleteRequestByIdUseCase", "Lcom/reown/sign/json_rpc/domain/DeleteRequestByIdUseCase;", "crypto", "Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;", "authenticateResponseTopicRepository", "Lcom/reown/sign/storage/authenticate/AuthenticateResponseTopicRepository;", "proposalStorageRepository", "Lcom/reown/sign/storage/proposal/ProposalStorageRepository;", "sessionStorageRepository", "Lcom/reown/sign/storage/sequence/SessionStorageRepository;", "metadataStorageRepository", "Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;", "pairingController", "Lcom/reown/android/pairing/handler/PairingControllerInterface;", "verifyContextStorageRepository", "Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;", "proposeSessionUseCase", "authenticateSessionUseCase", "pairUseCase", "rejectSessionUseCase", "approveSessionUseCase", "approveSessionAuthenticateUseCase", "rejectSessionAuthenticateUseCase", "sessionUpdateUseCase", "sessionRequestUseCase", "respondSessionRequestUseCase", "pingUseCase", "formatAuthenticateMessageUseCase", "emitEventUseCase", "extendSessionUseCase", "disconnectSessionUseCase", "decryptMessageUseCase", "getSessionsUseCase", "getPairingsUseCase", "getSessionProposalsUseCase", "getVerifyContextByIdUseCase", "getListOfVerifyContextsUseCase", "onSessionProposeUse", "Lcom/reown/sign/engine/use_case/requests/OnSessionProposalUseCase;", "onAuthenticateSessionUseCase", "Lcom/reown/sign/engine/use_case/requests/OnSessionAuthenticateUseCase;", "onSessionSettleUseCase", "Lcom/reown/sign/engine/use_case/requests/OnSessionSettleUseCase;", "onSessionRequestUseCase", "Lcom/reown/sign/engine/use_case/requests/OnSessionRequestUseCase;", "onSessionDeleteUseCase", "Lcom/reown/sign/engine/use_case/requests/OnSessionDeleteUseCase;", "onSessionEventUseCase", "Lcom/reown/sign/engine/use_case/requests/OnSessionEventUseCase;", "onSessionUpdateUseCase", "Lcom/reown/sign/engine/use_case/requests/OnSessionUpdateUseCase;", "onSessionExtendUseCase", "Lcom/reown/sign/engine/use_case/requests/OnSessionExtendUseCase;", "onPingUseCase", "Lcom/reown/sign/engine/use_case/requests/OnPingUseCase;", "onSessionProposalResponseUseCase", "Lcom/reown/sign/engine/use_case/responses/OnSessionProposalResponseUseCase;", "onSessionAuthenticateResponseUseCase", "Lcom/reown/sign/engine/use_case/responses/OnSessionAuthenticateResponseUseCase;", "onSessionSettleResponseUseCase", "Lcom/reown/sign/engine/use_case/responses/OnSessionSettleResponseUseCase;", "onSessionUpdateResponseUseCase", "Lcom/reown/sign/engine/use_case/responses/OnSessionUpdateResponseUseCase;", "onSessionRequestResponseUseCase", "Lcom/reown/sign/engine/use_case/responses/OnSessionRequestResponseUseCase;", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;", "linkModeJsonRpcInteractor", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/sign/json_rpc/domain/GetPendingRequestsUseCaseByTopicInterface;Lcom/reown/sign/json_rpc/domain/GetPendingSessionRequestByTopicUseCaseInterface;Lcom/reown/sign/json_rpc/domain/GetPendingSessionRequests;Lcom/reown/sign/engine/use_case/calls/GetPendingAuthenticateRequestUseCaseInterface;Lcom/reown/sign/json_rpc/domain/DeleteRequestByIdUseCase;Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;Lcom/reown/sign/storage/authenticate/AuthenticateResponseTopicRepository;Lcom/reown/sign/storage/proposal/ProposalStorageRepository;Lcom/reown/sign/storage/sequence/SessionStorageRepository;Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;Lcom/reown/android/pairing/handler/PairingControllerInterface;Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;Lcom/reown/sign/engine/use_case/calls/ProposeSessionUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/SessionAuthenticateUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/PairUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/RejectSessionUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/ApproveSessionUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/ApproveSessionAuthenticateUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/RejectSessionAuthenticateUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/SessionUpdateUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/SessionRequestUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/RespondSessionRequestUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/PingUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/FormatAuthenticateMessageUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/EmitEventUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/ExtendSessionUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/DisconnectSessionUseCaseInterface;Lcom/reown/android/push/notifications/DecryptMessageUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/GetSessionsUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/GetPairingsUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/GetSessionProposalsUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/GetVerifyContextByIdUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/GetListOfVerifyContextsUseCaseInterface;Lcom/reown/sign/engine/use_case/requests/OnSessionProposalUseCase;Lcom/reown/sign/engine/use_case/requests/OnSessionAuthenticateUseCase;Lcom/reown/sign/engine/use_case/requests/OnSessionSettleUseCase;Lcom/reown/sign/engine/use_case/requests/OnSessionRequestUseCase;Lcom/reown/sign/engine/use_case/requests/OnSessionDeleteUseCase;Lcom/reown/sign/engine/use_case/requests/OnSessionEventUseCase;Lcom/reown/sign/engine/use_case/requests/OnSessionUpdateUseCase;Lcom/reown/sign/engine/use_case/requests/OnSessionExtendUseCase;Lcom/reown/sign/engine/use_case/requests/OnPingUseCase;Lcom/reown/sign/engine/use_case/responses/OnSessionProposalResponseUseCase;Lcom/reown/sign/engine/use_case/responses/OnSessionAuthenticateResponseUseCase;Lcom/reown/sign/engine/use_case/responses/OnSessionSettleResponseUseCase;Lcom/reown/sign/engine/use_case/responses/OnSessionUpdateResponseUseCase;Lcom/reown/sign/engine/use_case/responses/OnSessionRequestResponseUseCase;Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;Lcom/reown/foundation/util/Logger;)V", "jsonRpcRequestsJob", "Lkotlinx/coroutines/Job;", "jsonRpcResponsesJob", "internalErrorsJob", "signEventsJob", "envelopeRequestsJob", "envelopeResponsesJob", "_engineEvent", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "engineEvent", "Lkotlinx/coroutines/flow/SharedFlow;", "getEngineEvent", "()Lkotlinx/coroutines/flow/SharedFlow;", "wssConnection", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/reown/android/relay/WSSConnectionState;", "getWssConnection", "()Lkotlinx/coroutines/flow/StateFlow;", "setup", "", "handleRelayRequestsAndResponses", "handleLinkModeResponses", "handleLinkModeRequests", "collectJsonRpcRequests", "collectJsonRpcResponses", "collectInternalErrors", "collectSignEvents", "resubscribeToSession", "resubscribeToPendingAuthenticateTopics", "setupSequenceExpiration", "propagatePendingSessionRequestsQueue", "sessionProposalExpiryWatcher", "sessionRequestsExpiryWatcher", "emitReceivedPendingRequestsWhilePairingOnTheSameURL", "repeatableFlow", "Lkotlinx/coroutines/flow/Flow;", "approve", "proposerPublicKey", "", "sessionNamespaces", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "sessionProperties", "scopedProperties", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "approveSessionAuthenticate", "id", "", "cacaos", "", "Lcom/reown/android/internal/common/signing/cacao/Cacao;", "(JLjava/util/List;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authenticate", "Lcom/reown/sign/engine/model/EngineDO$Authenticate;", "methods", "pairingTopic", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "walletAppLink", "(Lcom/reown/sign/engine/model/EngineDO$Authenticate;Ljava/util/List;Ljava/lang/String;Lcom/reown/android/internal/common/model/Expiry;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decryptNotification", "topic", "message", "Lcom/reown/android/Core$Model$Message;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disconnect", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatchEnvelope", "url", "emit", "event", "Lcom/reown/sign/engine/model/EngineDO$Event;", "(Ljava/lang/String;Lcom/reown/sign/engine/model/EngineDO$Event;Ljava/lang/Long;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "extend", "formatMessage", "payloadParams", "Lcom/reown/sign/engine/model/EngineDO$PayloadParams;", "iss", "(Lcom/reown/sign/engine/model/EngineDO$PayloadParams;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getListOfSettledPairings", "Lcom/reown/sign/engine/model/EngineDO$PairingSettle;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getListOfSettledSessions", "Lcom/reown/sign/engine/model/EngineDO$Session;", "getListOfVerifyContexts", "Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "getPendingAuthenticateRequests", "Lcom/reown/sign/common/model/Request;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;", "getPendingRequests", "Lcom/reown/foundation/common/model/Topic;", "(Lcom/reown/foundation/common/model/Topic;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/reown/sign/engine/model/EngineDO$SessionRequest;", "getSessionProposals", "Lcom/reown/sign/engine/model/EngineDO$SessionProposal;", "getVerifyContext", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pair", "uri", "ping", "timeout", "Lkotlin/time/Duration;", "ping-zkXUZaI", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "proposeSession", "requiredNamespaces", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Proposal;", "optionalNamespaces", "properties", "pairing", "Lcom/reown/android/internal/common/model/Pairing;", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/reown/android/internal/common/model/Pairing;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reject", "reason", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rejectSessionAuthenticate", "(JLjava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondSessionRequest", "jsonRpcResponse", "Lcom/reown/android/internal/common/JsonRpcResponse;", "(Ljava/lang/String;Lcom/reown/android/internal/common/JsonRpcResponse;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sessionRequest", "request", "Lcom/reown/sign/engine/model/EngineDO$Request;", "(Lcom/reown/sign/engine/model/EngineDO$Request;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sessionUpdate", "namespaces", "(Ljava/lang/String;Ljava/util/Map;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "triggerRequest", "payload", "Lcom/reown/android/internal/common/model/type/JsonRpcClientSync;", "appLink", "envelopeType", "Lcom/reown/android/internal/common/model/EnvelopeType;", "triggerResponse", "response", "participants", "Lcom/reown/android/internal/common/model/Participants;", "clientSyncJsonRpc", "Lcom/reown/android/internal/common/model/WCRequest;", "getClientSyncJsonRpc", "errors", "Lcom/reown/android/internal/common/model/SDKError;", "getErrors", "events", "getEvents", "internalErrors", "getInternalErrors", "peerResponse", "Lcom/reown/android/internal/common/model/WCResponse;", "getPeerResponse", "requestEvents", "getRequestEvents", "Companion", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSignEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SignEngine.kt\ncom/reown/sign/engine/domain/SignEngine\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,522:1\n17#2:523\n19#2:527\n17#2:528\n19#2:532\n17#2:533\n19#2:537\n17#2:538\n19#2:542\n46#3:524\n51#3:526\n46#3:529\n51#3:531\n46#3:534\n51#3:536\n46#3:539\n51#3:541\n105#4:525\n105#4:530\n105#4:535\n105#4:540\n3301#5,10:543\n1563#5:553\n1634#5,3:554\n2756#5:557\n1563#5:560\n1634#5,3:561\n1#6:558\n1#6:559\n*S KotlinDebug\n*F\n+ 1 SignEngine.kt\ncom/reown/sign/engine/domain/SignEngine\n*L\n242#1:523\n242#1:527\n255#1:528\n255#1:532\n267#1:533\n267#1:537\n284#1:538\n284#1:542\n242#1:524\n242#1:526\n255#1:529\n255#1:531\n267#1:534\n267#1:536\n284#1:539\n284#1:541\n242#1:525\n255#1:530\n267#1:535\n284#1:540\n329#1:543,10\n332#1:553\n332#1:554,3\n333#1:557\n338#1:560\n338#1:561,3\n333#1:558\n*E\n"})
public final class SignEngine implements ProposeSessionUseCaseInterface, SessionAuthenticateUseCaseInterface, PairUseCaseInterface, RejectSessionUseCaseInterface, ApproveSessionUseCaseInterface, ApproveSessionAuthenticateUseCaseInterface, RejectSessionAuthenticateUseCaseInterface, SessionUpdateUseCaseInterface, SessionRequestUseCaseInterface, RespondSessionRequestUseCaseInterface, PingUseCaseInterface, FormatAuthenticateMessageUseCaseInterface, EmitEventUseCaseInterface, ExtendSessionUseCaseInterface, DisconnectSessionUseCaseInterface, DecryptMessageUseCaseInterface, GetSessionsUseCaseInterface, GetPairingsUseCaseInterface, GetPendingRequestsUseCaseByTopicInterface, GetPendingAuthenticateRequestUseCaseInterface, GetPendingSessionRequestByTopicUseCaseInterface, GetSessionProposalsUseCaseInterface, GetVerifyContextByIdUseCaseInterface, GetListOfVerifyContextsUseCaseInterface, LinkModeJsonRpcInteractorInterface {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long WATCHER_INTERVAL = 30000;
    /* access modifiers changed from: private */
    @NotNull
    public final MutableSharedFlow<EngineEvent> _engineEvent;
    @NotNull
    private final ApproveSessionAuthenticateUseCaseInterface approveSessionAuthenticateUseCase;
    @NotNull
    private final ApproveSessionUseCaseInterface approveSessionUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final AuthenticateResponseTopicRepository authenticateResponseTopicRepository;
    @NotNull
    private final SessionAuthenticateUseCaseInterface authenticateSessionUseCase;
    @NotNull
    private final KeyManagementRepository crypto;
    @NotNull
    private final DecryptMessageUseCaseInterface decryptMessageUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final DeleteRequestByIdUseCase deleteRequestByIdUseCase;
    @NotNull
    private final DisconnectSessionUseCaseInterface disconnectSessionUseCase;
    @NotNull
    private final EmitEventUseCaseInterface emitEventUseCase;
    @NotNull
    private final SharedFlow<EngineEvent> engineEvent;
    @Nullable
    private Job envelopeRequestsJob;
    @Nullable
    private Job envelopeResponsesJob;
    @NotNull
    private final ExtendSessionUseCaseInterface extendSessionUseCase;
    @NotNull
    private final FormatAuthenticateMessageUseCaseInterface formatAuthenticateMessageUseCase;
    @NotNull
    private final GetListOfVerifyContextsUseCaseInterface getListOfVerifyContextsUseCase;
    @NotNull
    private final GetPairingsUseCaseInterface getPairingsUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final GetPendingAuthenticateRequestUseCaseInterface getPendingAuthenticateRequestUseCase;
    @NotNull
    private final GetPendingRequestsUseCaseByTopicInterface getPendingRequestsByTopicUseCase;
    @NotNull
    private final GetPendingSessionRequestByTopicUseCaseInterface getPendingSessionRequestByTopicUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final GetPendingSessionRequests getPendingSessionRequests;
    @NotNull
    private final GetSessionProposalsUseCaseInterface getSessionProposalsUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final GetSessionsUseCaseInterface getSessionsUseCase;
    @NotNull
    private final GetVerifyContextByIdUseCaseInterface getVerifyContextByIdUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final InsertTelemetryEventUseCase insertEventUseCase;
    @Nullable
    private Job internalErrorsJob;
    /* access modifiers changed from: private */
    @NotNull
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @Nullable
    public Job jsonRpcRequestsJob;
    /* access modifiers changed from: private */
    @Nullable
    public Job jsonRpcResponsesJob;
    @NotNull
    private final LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final MetadataStorageRepositoryInterface metadataStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionAuthenticateUseCase onAuthenticateSessionUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final OnPingUseCase onPingUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionAuthenticateResponseUseCase onSessionAuthenticateResponseUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionDeleteUseCase onSessionDeleteUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionEventUseCase onSessionEventUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionExtendUseCase onSessionExtendUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionProposalResponseUseCase onSessionProposalResponseUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionProposalUseCase onSessionProposeUse;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionRequestResponseUseCase onSessionRequestResponseUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionRequestUseCase onSessionRequestUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionSettleResponseUseCase onSessionSettleResponseUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionSettleUseCase onSessionSettleUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionUpdateResponseUseCase onSessionUpdateResponseUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final OnSessionUpdateUseCase onSessionUpdateUseCase;
    @NotNull
    private final PairUseCaseInterface pairUseCase;
    @NotNull
    private final PairingControllerInterface pairingController;
    @NotNull
    private final PingUseCaseInterface pingUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final ProposalStorageRepository proposalStorageRepository;
    @NotNull
    private final ProposeSessionUseCaseInterface proposeSessionUseCase;
    @NotNull
    private final RejectSessionAuthenticateUseCaseInterface rejectSessionAuthenticateUseCase;
    @NotNull
    private final RejectSessionUseCaseInterface rejectSessionUseCase;
    @NotNull
    private final RespondSessionRequestUseCaseInterface respondSessionRequestUseCase;
    @NotNull
    private final SessionRequestUseCaseInterface sessionRequestUseCase;
    @NotNull
    private final SessionStorageRepository sessionStorageRepository;
    @NotNull
    private final SessionUpdateUseCaseInterface sessionUpdateUseCase;
    @Nullable
    private Job signEventsJob;
    /* access modifiers changed from: private */
    @NotNull
    public final VerifyContextStorageRepository verifyContextStorageRepository;
    @NotNull
    private final StateFlow<WSSConnectionState> wssConnection;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/reown/sign/engine/domain/SignEngine$Companion;", "", "<init>", "()V", "WATCHER_INTERVAL", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public SignEngine(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull GetPendingRequestsUseCaseByTopicInterface getPendingRequestsUseCaseByTopicInterface, @NotNull GetPendingSessionRequestByTopicUseCaseInterface getPendingSessionRequestByTopicUseCaseInterface, @NotNull GetPendingSessionRequests getPendingSessionRequests2, @NotNull GetPendingAuthenticateRequestUseCaseInterface getPendingAuthenticateRequestUseCaseInterface, @NotNull DeleteRequestByIdUseCase deleteRequestByIdUseCase2, @NotNull KeyManagementRepository keyManagementRepository, @NotNull AuthenticateResponseTopicRepository authenticateResponseTopicRepository2, @NotNull ProposalStorageRepository proposalStorageRepository2, @NotNull SessionStorageRepository sessionStorageRepository2, @NotNull MetadataStorageRepositoryInterface metadataStorageRepositoryInterface, @NotNull PairingControllerInterface pairingControllerInterface, @NotNull VerifyContextStorageRepository verifyContextStorageRepository2, @NotNull ProposeSessionUseCaseInterface proposeSessionUseCaseInterface, @NotNull SessionAuthenticateUseCaseInterface sessionAuthenticateUseCaseInterface, @NotNull PairUseCaseInterface pairUseCaseInterface, @NotNull RejectSessionUseCaseInterface rejectSessionUseCaseInterface, @NotNull ApproveSessionUseCaseInterface approveSessionUseCaseInterface, @NotNull ApproveSessionAuthenticateUseCaseInterface approveSessionAuthenticateUseCaseInterface, @NotNull RejectSessionAuthenticateUseCaseInterface rejectSessionAuthenticateUseCaseInterface, @NotNull SessionUpdateUseCaseInterface sessionUpdateUseCaseInterface, @NotNull SessionRequestUseCaseInterface sessionRequestUseCaseInterface, @NotNull RespondSessionRequestUseCaseInterface respondSessionRequestUseCaseInterface, @NotNull PingUseCaseInterface pingUseCaseInterface, @NotNull FormatAuthenticateMessageUseCaseInterface formatAuthenticateMessageUseCaseInterface, @NotNull EmitEventUseCaseInterface emitEventUseCaseInterface, @NotNull ExtendSessionUseCaseInterface extendSessionUseCaseInterface, @NotNull DisconnectSessionUseCaseInterface disconnectSessionUseCaseInterface, @NotNull DecryptMessageUseCaseInterface decryptMessageUseCaseInterface, @NotNull GetSessionsUseCaseInterface getSessionsUseCaseInterface, @NotNull GetPairingsUseCaseInterface getPairingsUseCaseInterface, @NotNull GetSessionProposalsUseCaseInterface getSessionProposalsUseCaseInterface, @NotNull GetVerifyContextByIdUseCaseInterface getVerifyContextByIdUseCaseInterface, @NotNull GetListOfVerifyContextsUseCaseInterface getListOfVerifyContextsUseCaseInterface, @NotNull OnSessionProposalUseCase onSessionProposalUseCase, @NotNull OnSessionAuthenticateUseCase onSessionAuthenticateUseCase, @NotNull OnSessionSettleUseCase onSessionSettleUseCase2, @NotNull OnSessionRequestUseCase onSessionRequestUseCase2, @NotNull OnSessionDeleteUseCase onSessionDeleteUseCase2, @NotNull OnSessionEventUseCase onSessionEventUseCase2, @NotNull OnSessionUpdateUseCase onSessionUpdateUseCase2, @NotNull OnSessionExtendUseCase onSessionExtendUseCase2, @NotNull OnPingUseCase onPingUseCase2, @NotNull OnSessionProposalResponseUseCase onSessionProposalResponseUseCase2, @NotNull OnSessionAuthenticateResponseUseCase onSessionAuthenticateResponseUseCase2, @NotNull OnSessionSettleResponseUseCase onSessionSettleResponseUseCase2, @NotNull OnSessionUpdateResponseUseCase onSessionUpdateResponseUseCase2, @NotNull OnSessionRequestResponseUseCase onSessionRequestResponseUseCase2, @NotNull InsertTelemetryEventUseCase insertTelemetryEventUseCase, @NotNull LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractorInterface, @NotNull Logger logger2) {
        RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface2 = relayJsonRpcInteractorInterface;
        GetPendingRequestsUseCaseByTopicInterface getPendingRequestsUseCaseByTopicInterface2 = getPendingRequestsUseCaseByTopicInterface;
        GetPendingSessionRequestByTopicUseCaseInterface getPendingSessionRequestByTopicUseCaseInterface2 = getPendingSessionRequestByTopicUseCaseInterface;
        GetPendingSessionRequests getPendingSessionRequests3 = getPendingSessionRequests2;
        GetPendingAuthenticateRequestUseCaseInterface getPendingAuthenticateRequestUseCaseInterface2 = getPendingAuthenticateRequestUseCaseInterface;
        DeleteRequestByIdUseCase deleteRequestByIdUseCase3 = deleteRequestByIdUseCase2;
        KeyManagementRepository keyManagementRepository2 = keyManagementRepository;
        AuthenticateResponseTopicRepository authenticateResponseTopicRepository3 = authenticateResponseTopicRepository2;
        ProposalStorageRepository proposalStorageRepository3 = proposalStorageRepository2;
        SessionStorageRepository sessionStorageRepository3 = sessionStorageRepository2;
        MetadataStorageRepositoryInterface metadataStorageRepositoryInterface2 = metadataStorageRepositoryInterface;
        PairingControllerInterface pairingControllerInterface2 = pairingControllerInterface;
        VerifyContextStorageRepository verifyContextStorageRepository3 = verifyContextStorageRepository2;
        ProposeSessionUseCaseInterface proposeSessionUseCaseInterface2 = proposeSessionUseCaseInterface;
        PairUseCaseInterface pairUseCaseInterface2 = pairUseCaseInterface;
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface2, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(getPendingRequestsUseCaseByTopicInterface2, "getPendingRequestsByTopicUseCase");
        Intrinsics.checkNotNullParameter(getPendingSessionRequestByTopicUseCaseInterface2, "getPendingSessionRequestByTopicUseCase");
        Intrinsics.checkNotNullParameter(getPendingSessionRequests3, "getPendingSessionRequests");
        Intrinsics.checkNotNullParameter(getPendingAuthenticateRequestUseCaseInterface2, "getPendingAuthenticateRequestUseCase");
        Intrinsics.checkNotNullParameter(deleteRequestByIdUseCase3, "deleteRequestByIdUseCase");
        Intrinsics.checkNotNullParameter(keyManagementRepository2, "crypto");
        Intrinsics.checkNotNullParameter(authenticateResponseTopicRepository3, "authenticateResponseTopicRepository");
        Intrinsics.checkNotNullParameter(proposalStorageRepository3, "proposalStorageRepository");
        Intrinsics.checkNotNullParameter(sessionStorageRepository3, "sessionStorageRepository");
        Intrinsics.checkNotNullParameter(metadataStorageRepositoryInterface2, "metadataStorageRepository");
        Intrinsics.checkNotNullParameter(pairingControllerInterface2, "pairingController");
        Intrinsics.checkNotNullParameter(verifyContextStorageRepository3, "verifyContextStorageRepository");
        Intrinsics.checkNotNullParameter(proposeSessionUseCaseInterface2, "proposeSessionUseCase");
        Intrinsics.checkNotNullParameter(sessionAuthenticateUseCaseInterface, "authenticateSessionUseCase");
        Intrinsics.checkNotNullParameter(pairUseCaseInterface, "pairUseCase");
        Intrinsics.checkNotNullParameter(rejectSessionUseCaseInterface, "rejectSessionUseCase");
        Intrinsics.checkNotNullParameter(approveSessionUseCaseInterface, "approveSessionUseCase");
        Intrinsics.checkNotNullParameter(approveSessionAuthenticateUseCaseInterface, "approveSessionAuthenticateUseCase");
        Intrinsics.checkNotNullParameter(rejectSessionAuthenticateUseCaseInterface, "rejectSessionAuthenticateUseCase");
        Intrinsics.checkNotNullParameter(sessionUpdateUseCaseInterface, "sessionUpdateUseCase");
        Intrinsics.checkNotNullParameter(sessionRequestUseCaseInterface, "sessionRequestUseCase");
        Intrinsics.checkNotNullParameter(respondSessionRequestUseCaseInterface, "respondSessionRequestUseCase");
        Intrinsics.checkNotNullParameter(pingUseCaseInterface, "pingUseCase");
        Intrinsics.checkNotNullParameter(formatAuthenticateMessageUseCaseInterface, "formatAuthenticateMessageUseCase");
        Intrinsics.checkNotNullParameter(emitEventUseCaseInterface, "emitEventUseCase");
        Intrinsics.checkNotNullParameter(extendSessionUseCaseInterface, "extendSessionUseCase");
        Intrinsics.checkNotNullParameter(disconnectSessionUseCaseInterface, "disconnectSessionUseCase");
        Intrinsics.checkNotNullParameter(decryptMessageUseCaseInterface, "decryptMessageUseCase");
        Intrinsics.checkNotNullParameter(getSessionsUseCaseInterface, "getSessionsUseCase");
        Intrinsics.checkNotNullParameter(getPairingsUseCaseInterface, "getPairingsUseCase");
        Intrinsics.checkNotNullParameter(getSessionProposalsUseCaseInterface, "getSessionProposalsUseCase");
        Intrinsics.checkNotNullParameter(getVerifyContextByIdUseCaseInterface, "getVerifyContextByIdUseCase");
        Intrinsics.checkNotNullParameter(getListOfVerifyContextsUseCaseInterface, "getListOfVerifyContextsUseCase");
        Intrinsics.checkNotNullParameter(onSessionProposalUseCase, "onSessionProposeUse");
        Intrinsics.checkNotNullParameter(onSessionAuthenticateUseCase, "onAuthenticateSessionUseCase");
        Intrinsics.checkNotNullParameter(onSessionSettleUseCase2, "onSessionSettleUseCase");
        Intrinsics.checkNotNullParameter(onSessionRequestUseCase2, "onSessionRequestUseCase");
        Intrinsics.checkNotNullParameter(onSessionDeleteUseCase2, "onSessionDeleteUseCase");
        Intrinsics.checkNotNullParameter(onSessionEventUseCase2, "onSessionEventUseCase");
        Intrinsics.checkNotNullParameter(onSessionUpdateUseCase2, "onSessionUpdateUseCase");
        Intrinsics.checkNotNullParameter(onSessionExtendUseCase2, "onSessionExtendUseCase");
        Intrinsics.checkNotNullParameter(onPingUseCase2, "onPingUseCase");
        Intrinsics.checkNotNullParameter(onSessionProposalResponseUseCase2, "onSessionProposalResponseUseCase");
        Intrinsics.checkNotNullParameter(onSessionAuthenticateResponseUseCase2, "onSessionAuthenticateResponseUseCase");
        Intrinsics.checkNotNullParameter(onSessionSettleResponseUseCase2, "onSessionSettleResponseUseCase");
        Intrinsics.checkNotNullParameter(onSessionUpdateResponseUseCase2, "onSessionUpdateResponseUseCase");
        Intrinsics.checkNotNullParameter(onSessionRequestResponseUseCase2, "onSessionRequestResponseUseCase");
        Intrinsics.checkNotNullParameter(insertTelemetryEventUseCase, "insertEventUseCase");
        Intrinsics.checkNotNullParameter(linkModeJsonRpcInteractorInterface, "linkModeJsonRpcInteractor");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface2;
        this.getPendingRequestsByTopicUseCase = getPendingRequestsUseCaseByTopicInterface2;
        this.getPendingSessionRequestByTopicUseCase = getPendingSessionRequestByTopicUseCaseInterface2;
        this.getPendingSessionRequests = getPendingSessionRequests3;
        this.getPendingAuthenticateRequestUseCase = getPendingAuthenticateRequestUseCaseInterface2;
        this.deleteRequestByIdUseCase = deleteRequestByIdUseCase3;
        this.crypto = keyManagementRepository2;
        this.authenticateResponseTopicRepository = authenticateResponseTopicRepository3;
        this.proposalStorageRepository = proposalStorageRepository3;
        this.sessionStorageRepository = sessionStorageRepository3;
        this.metadataStorageRepository = metadataStorageRepositoryInterface2;
        this.pairingController = pairingControllerInterface2;
        this.verifyContextStorageRepository = verifyContextStorageRepository3;
        this.proposeSessionUseCase = proposeSessionUseCaseInterface2;
        this.authenticateSessionUseCase = sessionAuthenticateUseCaseInterface;
        this.pairUseCase = pairUseCaseInterface;
        this.rejectSessionUseCase = rejectSessionUseCaseInterface;
        this.approveSessionUseCase = approveSessionUseCaseInterface;
        this.approveSessionAuthenticateUseCase = approveSessionAuthenticateUseCaseInterface;
        this.rejectSessionAuthenticateUseCase = rejectSessionAuthenticateUseCaseInterface;
        this.sessionUpdateUseCase = sessionUpdateUseCaseInterface;
        this.sessionRequestUseCase = sessionRequestUseCaseInterface;
        this.respondSessionRequestUseCase = respondSessionRequestUseCaseInterface;
        this.pingUseCase = pingUseCaseInterface;
        this.formatAuthenticateMessageUseCase = formatAuthenticateMessageUseCaseInterface;
        this.emitEventUseCase = emitEventUseCaseInterface;
        this.extendSessionUseCase = extendSessionUseCaseInterface;
        this.disconnectSessionUseCase = disconnectSessionUseCaseInterface;
        this.decryptMessageUseCase = decryptMessageUseCaseInterface;
        this.getSessionsUseCase = getSessionsUseCaseInterface;
        this.getPairingsUseCase = getPairingsUseCaseInterface;
        this.getSessionProposalsUseCase = getSessionProposalsUseCaseInterface;
        this.getVerifyContextByIdUseCase = getVerifyContextByIdUseCaseInterface;
        this.getListOfVerifyContextsUseCase = getListOfVerifyContextsUseCaseInterface;
        this.onSessionProposeUse = onSessionProposalUseCase;
        this.onAuthenticateSessionUseCase = onSessionAuthenticateUseCase;
        this.onSessionSettleUseCase = onSessionSettleUseCase2;
        this.onSessionRequestUseCase = onSessionRequestUseCase2;
        this.onSessionDeleteUseCase = onSessionDeleteUseCase2;
        this.onSessionEventUseCase = onSessionEventUseCase2;
        this.onSessionUpdateUseCase = onSessionUpdateUseCase2;
        this.onSessionExtendUseCase = onSessionExtendUseCase2;
        this.onPingUseCase = onPingUseCase2;
        this.onSessionProposalResponseUseCase = onSessionProposalResponseUseCase2;
        this.onSessionAuthenticateResponseUseCase = onSessionAuthenticateResponseUseCase2;
        this.onSessionSettleResponseUseCase = onSessionSettleResponseUseCase2;
        this.onSessionUpdateResponseUseCase = onSessionUpdateResponseUseCase2;
        this.onSessionRequestResponseUseCase = onSessionRequestResponseUseCase2;
        this.insertEventUseCase = insertTelemetryEventUseCase;
        this.linkModeJsonRpcInteractor = linkModeJsonRpcInteractorInterface;
        this.logger = logger2;
        MutableSharedFlow<EngineEvent> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._engineEvent = MutableSharedFlow$default;
        this.engineEvent = FlowKt.asSharedFlow(MutableSharedFlow$default);
        this.wssConnection = relayJsonRpcInteractorInterface.getWssConnectionState();
        pairingControllerInterface2.register(JsonRpcMethod.WC_SESSION_PROPOSE, JsonRpcMethod.WC_SESSION_AUTHENTICATE, JsonRpcMethod.WC_SESSION_SETTLE, JsonRpcMethod.WC_SESSION_REQUEST, JsonRpcMethod.WC_SESSION_EVENT, JsonRpcMethod.WC_SESSION_DELETE, JsonRpcMethod.WC_SESSION_EXTEND, JsonRpcMethod.WC_SESSION_PING, JsonRpcMethod.WC_SESSION_UPDATE);
        setupSequenceExpiration();
        propagatePendingSessionRequestsQueue();
        emitReceivedPendingRequestsWhilePairingOnTheSameURL();
        sessionProposalExpiryWatcher();
        sessionRequestsExpiryWatcher();
    }

    private final Job collectInternalErrors() {
        return FlowKt.launchIn(FlowKt.onEach(FlowKt.merge((Flow<? extends T>[]) new Flow[]{this.jsonRpcInteractor.getInternalErrors(), this.linkModeJsonRpcInteractor.getInternalErrors(), this.pairingController.getFindWrongMethodsFlow(), this.sessionRequestUseCase.getErrors()}), new SignEngine$collectInternalErrors$1(this, (Continuation<? super SignEngine$collectInternalErrors$1>) null)), WalletConnectScopeKt.getScope());
    }

    /* access modifiers changed from: private */
    public final Job collectJsonRpcRequests() {
        return FlowKt.launchIn(FlowKt.onEach(new SignEngine$collectJsonRpcRequests$$inlined$filter$1(this.jsonRpcInteractor.getClientSyncJsonRpc()), new SignEngine$collectJsonRpcRequests$2(this, (Continuation<? super SignEngine$collectJsonRpcRequests$2>) null)), WalletConnectScopeKt.getScope());
    }

    /* access modifiers changed from: private */
    public final Job collectJsonRpcResponses() {
        return FlowKt.launchIn(FlowKt.onEach(new SignEngine$collectJsonRpcResponses$$inlined$filter$1(this.jsonRpcInteractor.getPeerResponse()), new SignEngine$collectJsonRpcResponses$2(this, (Continuation<? super SignEngine$collectJsonRpcResponses$2>) null)), WalletConnectScopeKt.getScope());
    }

    private final Job collectSignEvents() {
        return FlowKt.launchIn(FlowKt.onEach(FlowKt.merge((Flow<? extends T>[]) new Flow[]{this.respondSessionRequestUseCase.getEvents(), this.onSessionRequestUseCase.getEvents(), this.onSessionDeleteUseCase.getEvents(), this.onSessionProposeUse.getEvents(), this.onAuthenticateSessionUseCase.getEvents(), this.onSessionEventUseCase.getEvents(), this.onSessionSettleUseCase.getEvents(), this.onSessionUpdateUseCase.getEvents(), this.onSessionExtendUseCase.getEvents(), this.onSessionProposalResponseUseCase.getEvents(), this.onSessionSettleResponseUseCase.getEvents(), this.onSessionUpdateResponseUseCase.getEvents(), this.onSessionRequestResponseUseCase.getEvents(), this.onSessionAuthenticateResponseUseCase.getEvents(), this.sessionRequestUseCase.getRequestEvents()}), new SignEngine$collectSignEvents$1(this, (Continuation<? super SignEngine$collectSignEvents$1>) null)), WalletConnectScopeKt.getScope());
    }

    private final void emitReceivedPendingRequestsWhilePairingOnTheSameURL() {
        FlowKt.launchIn(FlowKt.onEach(this.pairingController.getStoredPairingFlow(), new SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1(this, (Continuation<? super SignEngine$emitReceivedPendingRequestsWhilePairingOnTheSameURL$1>) null)), WalletConnectScopeKt.getScope());
    }

    private final void handleLinkModeRequests() {
        if (this.envelopeRequestsJob == null) {
            this.envelopeRequestsJob = FlowKt.launchIn(FlowKt.onEach(new SignEngine$handleLinkModeRequests$$inlined$filter$1(this.linkModeJsonRpcInteractor.getClientSyncJsonRpc()), new SignEngine$handleLinkModeRequests$2(this, (Continuation<? super SignEngine$handleLinkModeRequests$2>) null)), WalletConnectScopeKt.getScope());
        }
    }

    private final void handleLinkModeResponses() {
        if (this.envelopeResponsesJob == null) {
            this.envelopeResponsesJob = FlowKt.launchIn(FlowKt.onEach(new SignEngine$handleLinkModeResponses$$inlined$filter$1(this.linkModeJsonRpcInteractor.getPeerResponse()), new SignEngine$handleLinkModeResponses$2(this, (Continuation<? super SignEngine$handleLinkModeResponses$2>) null)), WalletConnectScopeKt.getScope());
        }
    }

    private final void handleRelayRequestsAndResponses() {
        FlowKt.launchIn(FlowKt.onEach(this.jsonRpcInteractor.getOnResubscribe(), new SignEngine$handleRelayRequestsAndResponses$1(this, (Continuation<? super SignEngine$handleRelayRequestsAndResponses$1>) null)), WalletConnectScopeKt.getScope());
    }

    private final Job propagatePendingSessionRequestsQueue() {
        return BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignEngine$propagatePendingSessionRequestsQueue$1(this, (Continuation<? super SignEngine$propagatePendingSessionRequestsQueue$1>) null), 3, (Object) null);
    }

    private final Flow<Unit> repeatableFlow() {
        return FlowKt.flow(new SignEngine$repeatableFlow$1((Continuation<? super SignEngine$repeatableFlow$1>) null));
    }

    /* access modifiers changed from: private */
    public final void resubscribeToPendingAuthenticateTopics() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignEngine$resubscribeToPendingAuthenticateTopics$1(this, (Continuation<? super SignEngine$resubscribeToPendingAuthenticateTopics$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void resubscribeToSession() {
        Object obj;
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Object next : this.sessionStorageRepository.getListOfSessionVOsWithoutMetadata()) {
                if (!UtilFunctionsKt.isSequenceValid(((SessionVO) next).getExpiry())) {
                    arrayList.add(next);
                } else {
                    arrayList2.add(next);
                }
            }
            Pair pair = new Pair(arrayList, arrayList2);
            List list = (List) pair.component2();
            Iterable<SessionVO> iterable = (List) pair.component1();
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
            for (SessionVO topic : iterable) {
                arrayList3.add(topic.getTopic());
            }
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                Topic topic2 = (Topic) it.next();
                try {
                    Result.Companion companion = Result.Companion;
                    this.crypto.removeKeys(topic2.getValue());
                    obj = Result.m8979constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
                }
                Throwable r3 = Result.m8982exceptionOrNullimpl(obj);
                if (r3 != null) {
                    this.logger.error(r3);
                }
                this.sessionStorageRepository.deleteSession(topic2);
            }
            Iterable<SessionVO> iterable2 = list;
            ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable2, 10));
            for (SessionVO topic3 : iterable2) {
                arrayList4.add(topic3.getTopic().getValue());
            }
            RelayJsonRpcInteractorInterface.batchSubscribe$default(this.jsonRpcInteractor, arrayList4, (Function1) null, new a(this, 2), 2, (Object) null);
        } catch (Exception e3) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignEngine$resubscribeToSession$5(this, e3, (Continuation<? super SignEngine$resubscribeToSession$5>) null), 3, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public static final Unit resubscribeToSession$lambda$10(SignEngine signEngine, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignEngine$resubscribeToSession$4$1(signEngine, th, (Continuation<? super SignEngine$resubscribeToSession$4$1>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    private final void sessionProposalExpiryWatcher() {
        FlowKt.launchIn(FlowKt.onEach(repeatableFlow(), new SignEngine$sessionProposalExpiryWatcher$1(this, (Continuation<? super SignEngine$sessionProposalExpiryWatcher$1>) null)), WalletConnectScopeKt.getScope());
    }

    private final void sessionRequestsExpiryWatcher() {
        FlowKt.launchIn(FlowKt.onEach(repeatableFlow(), new SignEngine$sessionRequestsExpiryWatcher$1(this, (Continuation<? super SignEngine$sessionRequestsExpiryWatcher$1>) null)), WalletConnectScopeKt.getScope());
    }

    private final void setupSequenceExpiration() {
        try {
            this.sessionStorageRepository.setOnSessionExpired(new a(this, 1));
        } catch (Exception e3) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignEngine$setupSequenceExpiration$2(this, e3, (Continuation<? super SignEngine$setupSequenceExpiration$2>) null), 3, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public static final Unit setupSequenceExpiration$lambda$14(SignEngine signEngine, Topic topic) {
        Intrinsics.checkNotNullParameter(topic, "sessionTopic");
        RelayJsonRpcInteractorInterface.unsubscribe$default(signEngine.jsonRpcInteractor, topic, new e(signEngine, topic, 7), (Function1) null, 4, (Object) null);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit setupSequenceExpiration$lambda$14$lambda$13(SignEngine signEngine, Topic topic) {
        Object obj;
        try {
            Result.Companion companion = Result.Companion;
            signEngine.sessionStorageRepository.deleteSession(topic);
            signEngine.crypto.removeKeys(topic.getValue());
            obj = Result.m8979constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        Throwable r2 = Result.m8982exceptionOrNullimpl(obj);
        if (r2 != null) {
            signEngine.logger.error(r2);
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public Object approve(@NotNull String str, @NotNull Map<String, EngineDO.Namespace.Session> map, @Nullable Map<String, String> map2, @Nullable Map<String, String> map3, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return this.approveSessionUseCase.approve(str, map, map2, map3, function0, function1, continuation);
    }

    @Nullable
    public Object approveSessionAuthenticate(long j2, @NotNull List<Cacao> list, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return this.approveSessionAuthenticateUseCase.approveSessionAuthenticate(j2, list, function0, function1, continuation);
    }

    @Nullable
    public Object authenticate(@NotNull EngineDO.Authenticate authenticate, @Nullable List<String> list, @Nullable String str, @Nullable Expiry expiry, @Nullable String str2, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12, @NotNull Continuation<? super Unit> continuation) {
        return this.authenticateSessionUseCase.authenticate(authenticate, list, str, expiry, str2, function1, function12, continuation);
    }

    @Nullable
    public Object decryptNotification(@NotNull String str, @NotNull String str2, @NotNull Function1<? super Core.Model.Message, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12, @NotNull Continuation<? super Unit> continuation) {
        return this.decryptMessageUseCase.decryptNotification(str, str2, function1, function12, continuation);
    }

    @Nullable
    public Object disconnect(@NotNull String str, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return this.disconnectSessionUseCase.disconnect(str, function0, function1, continuation);
    }

    public void dispatchEnvelope(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "url");
        this.linkModeJsonRpcInteractor.dispatchEnvelope(str);
    }

    @Nullable
    public Object emit(@NotNull String str, @NotNull EngineDO.Event event, @Nullable Long l2, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return this.emitEventUseCase.emit(str, event, l2, function0, function1, continuation);
    }

    @Nullable
    public Object extend(@NotNull String str, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return this.extendSessionUseCase.extend(str, function0, function1, continuation);
    }

    @Nullable
    public Object formatMessage(@NotNull EngineDO.PayloadParams payloadParams, @NotNull String str, @NotNull Continuation<? super String> continuation) {
        return this.formatAuthenticateMessageUseCase.formatMessage(payloadParams, str, continuation);
    }

    @NotNull
    public SharedFlow<WCRequest> getClientSyncJsonRpc() {
        return this.linkModeJsonRpcInteractor.getClientSyncJsonRpc();
    }

    @NotNull
    public final SharedFlow<EngineEvent> getEngineEvent() {
        return this.engineEvent;
    }

    @NotNull
    public SharedFlow<SDKError> getErrors() {
        return this.sessionRequestUseCase.getErrors();
    }

    @NotNull
    public SharedFlow<EngineEvent> getEvents() {
        return this.respondSessionRequestUseCase.getEvents();
    }

    @NotNull
    public SharedFlow<SDKError> getInternalErrors() {
        return this.linkModeJsonRpcInteractor.getInternalErrors();
    }

    @Nullable
    public Object getListOfSettledPairings(@NotNull Continuation<? super List<EngineDO.PairingSettle>> continuation) {
        return this.getPairingsUseCase.getListOfSettledPairings(continuation);
    }

    @Nullable
    public Object getListOfSettledSessions(@NotNull Continuation<? super List<EngineDO.Session>> continuation) {
        return this.getSessionsUseCase.getListOfSettledSessions(continuation);
    }

    @Nullable
    public Object getListOfVerifyContexts(@NotNull Continuation<? super List<EngineDO.VerifyContext>> continuation) {
        return this.getListOfVerifyContextsUseCase.getListOfVerifyContexts(continuation);
    }

    @NotNull
    public SharedFlow<WCResponse> getPeerResponse() {
        return this.linkModeJsonRpcInteractor.getPeerResponse();
    }

    @Nullable
    public Object getPendingAuthenticateRequests(@NotNull Continuation<? super List<Request<SignParams.SessionAuthenticateParams>>> continuation) {
        return this.getPendingAuthenticateRequestUseCase.getPendingAuthenticateRequests(continuation);
    }

    @Nullable
    public Object getPendingRequests(@NotNull Topic topic, @NotNull Continuation<? super List<Request<String>>> continuation) {
        return this.getPendingRequestsByTopicUseCase.getPendingRequests(topic, continuation);
    }

    @Nullable
    public Object getPendingSessionRequests(@NotNull Topic topic, @NotNull Continuation<? super List<EngineDO.SessionRequest>> continuation) {
        return this.getPendingSessionRequestByTopicUseCase.getPendingSessionRequests(topic, continuation);
    }

    @NotNull
    public SharedFlow<EngineEvent> getRequestEvents() {
        return this.sessionRequestUseCase.getRequestEvents();
    }

    @Nullable
    public Object getSessionProposals(@NotNull Continuation<? super List<EngineDO.SessionProposal>> continuation) {
        return this.getSessionProposalsUseCase.getSessionProposals(continuation);
    }

    @Nullable
    public Object getVerifyContext(long j2, @NotNull Continuation<? super EngineDO.VerifyContext> continuation) {
        return this.getVerifyContextByIdUseCase.getVerifyContext(j2, continuation);
    }

    @NotNull
    public final StateFlow<WSSConnectionState> getWssConnection() {
        return this.wssConnection;
    }

    @Nullable
    public Object pair(@NotNull String str, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return this.pairUseCase.pair(str, function0, function1, continuation);
    }

    @Nullable
    /* renamed from: ping-zkXUZaI  reason: not valid java name */
    public Object m8881pingzkXUZaI(@NotNull String str, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12, long j2, @NotNull Continuation<? super Unit> continuation) {
        return this.pingUseCase.m8889pingzkXUZaI(str, function1, function12, j2, continuation);
    }

    @Nullable
    public Object proposeSession(@Nullable Map<String, EngineDO.Namespace.Proposal> map, @Nullable Map<String, EngineDO.Namespace.Proposal> map2, @Nullable Map<String, String> map3, @Nullable Map<String, String> map4, @NotNull Pairing pairing, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return this.proposeSessionUseCase.proposeSession(map, map2, map3, map4, pairing, function0, function1, continuation);
    }

    @Nullable
    public Object reject(@NotNull String str, @NotNull String str2, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return this.rejectSessionUseCase.reject(str, str2, function0, function1, continuation);
    }

    @Nullable
    public Object rejectSessionAuthenticate(long j2, @NotNull String str, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return this.rejectSessionAuthenticateUseCase.rejectSessionAuthenticate(j2, str, function0, function1, continuation);
    }

    @Nullable
    public Object respondSessionRequest(@NotNull String str, @NotNull JsonRpcResponse jsonRpcResponse, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return this.respondSessionRequestUseCase.respondSessionRequest(str, jsonRpcResponse, function0, function1, continuation);
    }

    @Nullable
    public Object sessionRequest(@NotNull EngineDO.Request request, @NotNull Function1<? super Long, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12, @NotNull Continuation<? super Unit> continuation) {
        return this.sessionRequestUseCase.sessionRequest(request, function1, function12, continuation);
    }

    @Nullable
    public Object sessionUpdate(@NotNull String str, @NotNull Map<String, EngineDO.Namespace.Session> map, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return this.sessionUpdateUseCase.sessionUpdate(str, map, function0, function1, continuation);
    }

    public final void setup() {
        handleLinkModeRequests();
        handleLinkModeResponses();
        if (this.signEventsJob == null) {
            this.signEventsJob = collectSignEvents();
        }
        if (this.internalErrorsJob == null) {
            this.internalErrorsJob = collectInternalErrors();
        }
        handleRelayRequestsAndResponses();
    }

    public void triggerRequest(@NotNull JsonRpcClientSync<?> jsonRpcClientSync, @NotNull Topic topic, @NotNull String str, @NotNull EnvelopeType envelopeType) {
        Intrinsics.checkNotNullParameter(jsonRpcClientSync, "payload");
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str, "appLink");
        Intrinsics.checkNotNullParameter(envelopeType, "envelopeType");
        this.linkModeJsonRpcInteractor.triggerRequest(jsonRpcClientSync, topic, str, envelopeType);
    }

    public void triggerResponse(@NotNull Topic topic, @NotNull JsonRpcResponse jsonRpcResponse, @NotNull String str, @Nullable Participants participants, @NotNull EnvelopeType envelopeType) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(jsonRpcResponse, "response");
        Intrinsics.checkNotNullParameter(str, "appLink");
        Intrinsics.checkNotNullParameter(envelopeType, "envelopeType");
        this.linkModeJsonRpcInteractor.triggerResponse(topic, jsonRpcResponse, str, participants, envelopeType);
    }
}
