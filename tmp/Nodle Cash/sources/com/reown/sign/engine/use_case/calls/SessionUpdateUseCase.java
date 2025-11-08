package com.reown.sign.engine.use_case.calls;

import androidx.browser.trusted.c;
import com.reown.android.internal.common.exception.CannotFindSequenceForTopic;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.exceptions.InvalidNamespaceException;
import com.reown.sign.common.exceptions.MessagesKt;
import com.reown.sign.common.exceptions.NotSettledSessionException;
import com.reown.sign.common.exceptions.UnauthorizedPeerException;
import com.reown.sign.common.model.vo.sequence.SessionVO;
import com.reown.sign.common.validator.SignValidator;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.ValidationError;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJL\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000b0\u0014H@¢\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00100\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/SessionUpdateUseCase;", "Lcom/reown/sign/engine/use_case/calls/SessionUpdateUseCaseInterface;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "sessionStorageRepository", "Lcom/reown/sign/storage/sequence/SessionStorageRepository;", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/sign/storage/sequence/SessionStorageRepository;Lcom/reown/foundation/util/Logger;)V", "sessionUpdate", "", "topic", "", "namespaces", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "(Ljava/lang/String;Ljava/util/Map;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validate", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSessionUpdateUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionUpdateUseCase.kt\ncom/reown/sign/engine/use_case/calls/SessionUpdateUseCase\n+ 2 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n*L\n1#1,102:1\n44#2,13:103\n*S KotlinDebug\n*F\n+ 1 SessionUpdateUseCase.kt\ncom/reown/sign/engine/use_case/calls/SessionUpdateUseCase\n*L\n88#1:103,13\n*E\n"})
public final class SessionUpdateUseCase implements SessionUpdateUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final SessionStorageRepository sessionStorageRepository;

    public SessionUpdateUseCase(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull SessionStorageRepository sessionStorageRepository2, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(sessionStorageRepository2, "sessionStorageRepository");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.sessionStorageRepository = sessionStorageRepository2;
        this.logger = logger2;
    }

    /* access modifiers changed from: private */
    public final void validate(String str, Map<String, EngineDO.Namespace.Session> map) {
        if (this.sessionStorageRepository.isSessionValid(new Topic(str))) {
            SessionVO sessionWithoutMetadataByTopic = this.sessionStorageRepository.getSessionWithoutMetadataByTopic(new Topic(str));
            if (!sessionWithoutMetadataByTopic.isSelfController()) {
                this.logger.error("Sending session update error: unauthorized peer");
                throw new UnauthorizedPeerException(MessagesKt.UNAUTHORIZED_UPDATE_MESSAGE);
            } else if (sessionWithoutMetadataByTopic.isAcknowledged()) {
                SignValidator signValidator = SignValidator.INSTANCE;
                Map mapOfNamespacesVOSession = EngineMapperKt.toMapOfNamespacesVOSession(map);
                Map<String, Namespace.Proposal> requiredNamespaces = sessionWithoutMetadataByTopic.getRequiredNamespaces();
                if (mapOfNamespacesVOSession.isEmpty()) {
                    ValidationError.EmptyNamespaces emptyNamespaces = ValidationError.EmptyNamespaces.INSTANCE;
                    Logger logger2 = this.logger;
                    logger2.error("Sending session update error: invalid namespaces " + emptyNamespaces);
                    throw new InvalidNamespaceException(emptyNamespaces.getMessage());
                } else if (!signValidator.areNamespacesKeysProperlyFormatted(mapOfNamespacesVOSession)) {
                    ValidationError.UnsupportedNamespaceKey unsupportedNamespaceKey = ValidationError.UnsupportedNamespaceKey.INSTANCE;
                    Logger logger3 = this.logger;
                    logger3.error("Sending session update error: invalid namespaces " + unsupportedNamespaceKey);
                    throw new InvalidNamespaceException(unsupportedNamespaceKey.getMessage());
                } else if (!signValidator.areChainsNotEmpty(mapOfNamespacesVOSession)) {
                    ValidationError.UnsupportedChains unsupportedChains = new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_MISSING_MESSAGE);
                    Logger logger4 = this.logger;
                    logger4.error("Sending session update error: invalid namespaces " + unsupportedChains);
                    throw new InvalidNamespaceException(unsupportedChains.getMessage());
                } else if (!signValidator.areChainIdsValid(mapOfNamespacesVOSession)) {
                    ValidationError.UnsupportedChains unsupportedChains2 = new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_CAIP_2_MESSAGE);
                    Logger logger5 = this.logger;
                    logger5.error("Sending session update error: invalid namespaces " + unsupportedChains2);
                    throw new InvalidNamespaceException(unsupportedChains2.getMessage());
                } else if (!signValidator.areChainsInMatchingNamespace(mapOfNamespacesVOSession)) {
                    ValidationError.UnsupportedChains unsupportedChains3 = new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_WRONG_NAMESPACE_MESSAGE);
                    Logger logger6 = this.logger;
                    logger6.error("Sending session update error: invalid namespaces " + unsupportedChains3);
                    throw new InvalidNamespaceException(unsupportedChains3.getMessage());
                } else if (!signValidator.areAccountIdsValid(mapOfNamespacesVOSession)) {
                    ValidationError.UserRejectedChains userRejectedChains = new ValidationError.UserRejectedChains(MessagesKt.NAMESPACE_ACCOUNTS_CAIP_10_MESSAGE);
                    Logger logger7 = this.logger;
                    logger7.error("Sending session update error: invalid namespaces " + userRejectedChains);
                    throw new InvalidNamespaceException(userRejectedChains.getMessage());
                } else if (!signValidator.areAccountsInMatchingNamespaceAndChains(mapOfNamespacesVOSession)) {
                    ValidationError.UserRejectedChains userRejectedChains2 = new ValidationError.UserRejectedChains(MessagesKt.NAMESPACE_ACCOUNTS_WRONG_NAMESPACE_MESSAGE);
                    Logger logger8 = this.logger;
                    logger8.error("Sending session update error: invalid namespaces " + userRejectedChains2);
                    throw new InvalidNamespaceException(userRejectedChains2.getMessage());
                } else if (!signValidator.areAllNamespacesApproved(mapOfNamespacesVOSession.keySet(), requiredNamespaces.keySet())) {
                    ValidationError.UserRejected userRejected = ValidationError.UserRejected.INSTANCE;
                    Logger logger9 = this.logger;
                    logger9.error("Sending session update error: invalid namespaces " + userRejected);
                    throw new InvalidNamespaceException(userRejected.getMessage());
                } else if (!signValidator.areAllMethodsApproved(signValidator.allMethodsWithChains(mapOfNamespacesVOSession), signValidator.allMethodsWithChains(requiredNamespaces))) {
                    ValidationError.UserRejectedMethods userRejectedMethods = ValidationError.UserRejectedMethods.INSTANCE;
                    Logger logger10 = this.logger;
                    logger10.error("Sending session update error: invalid namespaces " + userRejectedMethods);
                    throw new InvalidNamespaceException(userRejectedMethods.getMessage());
                } else if (!signValidator.areAllEventsApproved(signValidator.allEventsWithChains(mapOfNamespacesVOSession), signValidator.allEventsWithChains(requiredNamespaces))) {
                    ValidationError.UserRejectedEvents userRejectedEvents = ValidationError.UserRejectedEvents.INSTANCE;
                    Logger logger11 = this.logger;
                    logger11.error("Sending session update error: invalid namespaces " + userRejectedEvents);
                    throw new InvalidNamespaceException(userRejectedEvents.getMessage());
                }
            } else {
                this.logger.error("Sending session update error: session is not acknowledged");
                throw new NotSettledSessionException(c.a(MessagesKt.SESSION_IS_NOT_ACKNOWLEDGED_MESSAGE, str));
            }
        } else {
            Logger logger12 = this.logger;
            logger12.error("Sending session update error: cannot find sequence for topic: " + str);
            throw new CannotFindSequenceForTopic(c.a("Cannot find sequence for given topic: ", str));
        }
    }

    @Nullable
    public Object sessionUpdate(@NotNull String str, @NotNull Map<String, EngineDO.Namespace.Session> map, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new SessionUpdateUseCase$sessionUpdate$2(this, str, map, function1, function0, (Continuation<? super SessionUpdateUseCase$sessionUpdate$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
