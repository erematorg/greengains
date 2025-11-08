package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.Pairing;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.exceptions.InvalidNamespaceException;
import com.reown.sign.common.exceptions.InvalidPropertiesException;
import com.reown.sign.common.exceptions.MessagesKt;
import com.reown.sign.common.validator.SignValidator;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.ValidationError;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import com.reown.sign.storage.proposal.ProposalStorageRepository;
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

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0001\u0010\u000e\u001a\u00020\u000f2\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00112\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00112\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0014\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u000f0\u001cH@¢\u0006\u0002\u0010\u001eJJ\u0010\u001f\u001a\u00020\u000f2\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00112\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00112\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/reown/sign/engine/use_case/calls/ProposeSessionUseCase;", "Lcom/reown/sign/engine/use_case/calls/ProposeSessionUseCaseInterface;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "crypto", "Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;", "proposalStorageRepository", "Lcom/reown/sign/storage/proposal/ProposalStorageRepository;", "selfAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;Lcom/reown/sign/storage/proposal/ProposalStorageRepository;Lcom/reown/android/internal/common/model/AppMetaData;Lcom/reown/foundation/util/Logger;)V", "proposeSession", "", "requiredNamespaces", "", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Proposal;", "optionalNamespaces", "properties", "scopedProperties", "pairing", "Lcom/reown/android/internal/common/model/Pairing;", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/reown/android/internal/common/model/Pairing;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validate", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nProposeSessionUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProposeSessionUseCase.kt\ncom/reown/sign/engine/use_case/calls/ProposeSessionUseCase\n+ 2 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n*L\n1#1,116:1\n29#2,8:117\n29#2,8:125\n70#2,4:133\n*S KotlinDebug\n*F\n+ 1 ProposeSessionUseCase.kt\ncom/reown/sign/engine/use_case/calls/ProposeSessionUseCase\n*L\n84#1:117,8\n91#1:125,8\n98#1:133,4\n*E\n"})
public final class ProposeSessionUseCase implements ProposeSessionUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final KeyManagementRepository crypto;
    /* access modifiers changed from: private */
    @NotNull
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final ProposalStorageRepository proposalStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final AppMetaData selfAppMetaData;

    public ProposeSessionUseCase(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull KeyManagementRepository keyManagementRepository, @NotNull ProposalStorageRepository proposalStorageRepository2, @NotNull AppMetaData appMetaData, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(keyManagementRepository, "crypto");
        Intrinsics.checkNotNullParameter(proposalStorageRepository2, "proposalStorageRepository");
        Intrinsics.checkNotNullParameter(appMetaData, "selfAppMetaData");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.crypto = keyManagementRepository;
        this.proposalStorageRepository = proposalStorageRepository2;
        this.selfAppMetaData = appMetaData;
        this.logger = logger2;
    }

    /* access modifiers changed from: private */
    public final void validate(Map<String, EngineDO.Namespace.Proposal> map, Map<String, EngineDO.Namespace.Proposal> map2, Map<String, String> map3) {
        if (map != null) {
            SignValidator signValidator = SignValidator.INSTANCE;
            Map namespacesVORequired = EngineMapperKt.toNamespacesVORequired(map);
            if (!signValidator.areNamespacesKeysProperlyFormatted(namespacesVORequired)) {
                ValidationError.UnsupportedNamespaceKey unsupportedNamespaceKey = ValidationError.UnsupportedNamespaceKey.INSTANCE;
                Logger logger2 = this.logger;
                logger2.error("Failed to send a session proposal - required namespaces error: " + unsupportedNamespaceKey);
                throw new InvalidNamespaceException(unsupportedNamespaceKey.getMessage());
            } else if (!signValidator.areChainsDefined(namespacesVORequired)) {
                ValidationError.UnsupportedChains unsupportedChains = new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_UNDEFINED_MISSING_MESSAGE);
                Logger logger3 = this.logger;
                logger3.error("Failed to send a session proposal - required namespaces error: " + unsupportedChains);
                throw new InvalidNamespaceException(unsupportedChains.getMessage());
            } else if (!signValidator.areChainsNotEmpty(namespacesVORequired)) {
                ValidationError.UnsupportedChains unsupportedChains2 = new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_MISSING_MESSAGE);
                Logger logger4 = this.logger;
                logger4.error("Failed to send a session proposal - required namespaces error: " + unsupportedChains2);
                throw new InvalidNamespaceException(unsupportedChains2.getMessage());
            } else if (!signValidator.areChainIdsValid(namespacesVORequired)) {
                ValidationError.UnsupportedChains unsupportedChains3 = new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_CAIP_2_MESSAGE);
                Logger logger5 = this.logger;
                logger5.error("Failed to send a session proposal - required namespaces error: " + unsupportedChains3);
                throw new InvalidNamespaceException(unsupportedChains3.getMessage());
            } else if (!signValidator.areChainsInMatchingNamespace(namespacesVORequired)) {
                ValidationError.UnsupportedChains unsupportedChains4 = new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_WRONG_NAMESPACE_MESSAGE);
                Logger logger6 = this.logger;
                logger6.error("Failed to send a session proposal - required namespaces error: " + unsupportedChains4);
                throw new InvalidNamespaceException(unsupportedChains4.getMessage());
            }
        }
        if (map2 != null) {
            SignValidator signValidator2 = SignValidator.INSTANCE;
            Map namespacesVOOptional = EngineMapperKt.toNamespacesVOOptional(map2);
            if (!signValidator2.areNamespacesKeysProperlyFormatted(namespacesVOOptional)) {
                ValidationError.UnsupportedNamespaceKey unsupportedNamespaceKey2 = ValidationError.UnsupportedNamespaceKey.INSTANCE;
                Logger logger7 = this.logger;
                logger7.error("Failed to send a session proposal - optional namespaces error: " + unsupportedNamespaceKey2);
                throw new InvalidNamespaceException(unsupportedNamespaceKey2.getMessage());
            } else if (!signValidator2.areChainsDefined(namespacesVOOptional)) {
                ValidationError.UnsupportedChains unsupportedChains5 = new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_UNDEFINED_MISSING_MESSAGE);
                Logger logger8 = this.logger;
                logger8.error("Failed to send a session proposal - optional namespaces error: " + unsupportedChains5);
                throw new InvalidNamespaceException(unsupportedChains5.getMessage());
            } else if (!signValidator2.areChainsNotEmpty(namespacesVOOptional)) {
                ValidationError.UnsupportedChains unsupportedChains6 = new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_MISSING_MESSAGE);
                Logger logger9 = this.logger;
                logger9.error("Failed to send a session proposal - optional namespaces error: " + unsupportedChains6);
                throw new InvalidNamespaceException(unsupportedChains6.getMessage());
            } else if (!signValidator2.areChainIdsValid(namespacesVOOptional)) {
                ValidationError.UnsupportedChains unsupportedChains7 = new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_CAIP_2_MESSAGE);
                Logger logger10 = this.logger;
                logger10.error("Failed to send a session proposal - optional namespaces error: " + unsupportedChains7);
                throw new InvalidNamespaceException(unsupportedChains7.getMessage());
            } else if (!signValidator2.areChainsInMatchingNamespace(namespacesVOOptional)) {
                ValidationError.UnsupportedChains unsupportedChains8 = new ValidationError.UnsupportedChains(MessagesKt.NAMESPACE_CHAINS_WRONG_NAMESPACE_MESSAGE);
                Logger logger11 = this.logger;
                logger11.error("Failed to send a session proposal - optional namespaces error: " + unsupportedChains8);
                throw new InvalidNamespaceException(unsupportedChains8.getMessage());
            }
        }
        if (map3 != null) {
            SignValidator signValidator3 = SignValidator.INSTANCE;
            if (map3.isEmpty()) {
                ValidationError.InvalidSessionProperties invalidSessionProperties = ValidationError.InvalidSessionProperties.INSTANCE;
                Logger logger12 = this.logger;
                logger12.error("Failed to send a session proposal - session properties error: " + invalidSessionProperties);
                throw new InvalidPropertiesException(invalidSessionProperties.getMessage());
            }
        }
    }

    @Nullable
    public Object proposeSession(@Nullable Map<String, EngineDO.Namespace.Proposal> map, @Nullable Map<String, EngineDO.Namespace.Proposal> map2, @Nullable Map<String, String> map3, @Nullable Map<String, String> map4, @NotNull Pairing pairing, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new ProposeSessionUseCase$proposeSession$2(pairing, map, map2, this, map3, map4, function0, function1, (Continuation<? super ProposeSessionUseCase$proposeSession$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
