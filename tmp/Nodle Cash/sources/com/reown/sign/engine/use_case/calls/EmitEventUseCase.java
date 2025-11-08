package com.reown.sign.engine.use_case.calls;

import androidx.browser.trusted.c;
import com.reown.android.internal.common.exception.CannotFindSequenceForTopic;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.utils.CoreValidator;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.exceptions.InvalidEventException;
import com.reown.sign.common.exceptions.MessagesKt;
import com.reown.sign.common.exceptions.UnauthorizedEventException;
import com.reown.sign.common.exceptions.UnauthorizedPeerException;
import com.reown.sign.common.model.vo.sequence.SessionVO;
import com.reown.sign.common.validator.SignValidator;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.ValidationError;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import java.util.List;
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

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJJ\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000b0\u0015H@¢\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/EmitEventUseCase;", "Lcom/reown/sign/engine/use_case/calls/EmitEventUseCaseInterface;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "sessionStorageRepository", "Lcom/reown/sign/storage/sequence/SessionStorageRepository;", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/sign/storage/sequence/SessionStorageRepository;Lcom/reown/foundation/util/Logger;)V", "emit", "", "topic", "", "event", "Lcom/reown/sign/engine/model/EngineDO$Event;", "id", "", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "(Ljava/lang/String;Lcom/reown/sign/engine/model/EngineDO$Event;Ljava/lang/Long;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validate", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEmitEventUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EmitEventUseCase.kt\ncom/reown/sign/engine/use_case/calls/EmitEventUseCase\n+ 2 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n*L\n1#1,84:1\n118#2,4:85\n99#2,6:89\n*S KotlinDebug\n*F\n+ 1 EmitEventUseCase.kt\ncom/reown/sign/engine/use_case/calls/EmitEventUseCase\n*L\n69#1:85,4\n75#1:89,6\n*E\n"})
public final class EmitEventUseCase implements EmitEventUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    @NotNull
    private final SessionStorageRepository sessionStorageRepository;

    public EmitEventUseCase(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull SessionStorageRepository sessionStorageRepository2, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(sessionStorageRepository2, "sessionStorageRepository");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.sessionStorageRepository = sessionStorageRepository2;
        this.logger = logger2;
    }

    /* access modifiers changed from: private */
    public final void validate(String str, EngineDO.Event event) {
        if (this.sessionStorageRepository.isSessionValid(new Topic(str))) {
            SessionVO sessionWithoutMetadataByTopic = this.sessionStorageRepository.getSessionWithoutMetadataByTopic(new Topic(str));
            if (sessionWithoutMetadataByTopic.isSelfController()) {
                SignValidator signValidator = SignValidator.INSTANCE;
                if (event.getData().length() == 0 || event.getName().length() == 0 || event.getChainId().length() == 0 || !CoreValidator.INSTANCE.isChainIdCAIP2Compliant(event.getChainId())) {
                    ValidationError.InvalidEvent invalidEvent = ValidationError.InvalidEvent.INSTANCE;
                    Logger logger2 = this.logger;
                    logger2.error("Emit - invalid event: " + str);
                    throw new InvalidEventException(invalidEvent.getMessage());
                }
                Map<String, Namespace.Session> sessionNamespaces = sessionWithoutMetadataByTopic.getSessionNamespaces();
                SignValidator signValidator2 = SignValidator.INSTANCE;
                String chainId = event.getChainId();
                String name = event.getName();
                Map access$allEventsWithChains = signValidator2.allEventsWithChains(sessionNamespaces);
                if (access$allEventsWithChains.get(name) != null) {
                    Object obj = access$allEventsWithChains.get(name);
                    Intrinsics.checkNotNull(obj);
                    if (((List) obj).contains(chainId)) {
                        return;
                    }
                }
                ValidationError.UnauthorizedEvent unauthorizedEvent = ValidationError.UnauthorizedEvent.INSTANCE;
                Logger logger3 = this.logger;
                logger3.error("Emit - unauthorized event: " + str);
                throw new UnauthorizedEventException(unauthorizedEvent.getMessage());
            }
            Logger logger4 = this.logger;
            logger4.error("Emit - unauthorized peer: " + str);
            throw new UnauthorizedPeerException(MessagesKt.UNAUTHORIZED_EMIT_MESSAGE);
        }
        Logger logger5 = this.logger;
        logger5.error("Emit - cannot find sequence for topic: " + str);
        throw new CannotFindSequenceForTopic(c.a("Cannot find sequence for given topic: ", str));
    }

    @Nullable
    public Object emit(@NotNull String str, @NotNull EngineDO.Event event, @Nullable Long l2, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new EmitEventUseCase$emit$2(this, str, event, l2, function0, function1, (Continuation<? super EmitEventUseCase$emit$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
