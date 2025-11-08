package com.reown.sign.di;

import androidx.compose.runtime.Composer;
import com.reown.foundation.di.FoundationCommonModuleKt;
import com.reown.foundation.di.FoundationCryptoModuleKt;
import com.reown.foundation.di.FoundationNetworkModuleKt;
import io.nodle.cash.ui.feature.chat.ComposableSingletons$ConversationDetailScreenKt;
import io.nodle.cash.ui.feature.chat.ComposableSingletons$ConversationListScreenKt;
import io.nodle.cash.ui.feature.chat.messages.ComposableSingletons$MessageComposerViewKt;
import kotlin.coroutines.CombinedContext;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.debug.internal.ConcurrentWeakMap;
import kotlinx.coroutines.flow.internal.SafeCollector;
import kotlinx.coroutines.reactive.PublishKt;
import org.koin.androidx.fragment.koin.KoinApplicationExtKt;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.scope.Scope;
import uniffi.xmtpv3.FfiConversation;
import uniffi.xmtpv3.UniffiRustCallStatus;

public final /* synthetic */ class c implements Function2 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7477a;

    public /* synthetic */ c(int i3) {
        this.f7477a = i3;
    }

    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7477a) {
            case 0:
                return StorageModuleKt.storageModule$lambda$14$lambda$6((Scope) obj, (ParametersHolder) obj2);
            case 1:
                return ComposableSingletons$ConversationDetailScreenKt.lambda__934613534$lambda$2((Composer) obj, ((Integer) obj2).intValue());
            case 2:
                return ComposableSingletons$ConversationListScreenKt.lambda__443112939$lambda$1((Composer) obj, ((Integer) obj2).intValue());
            case 3:
                return ComposableSingletons$MessageComposerViewKt.lambda__1221274981$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 4:
                return KoinApplicationExtKt.fragmentFactoryModule$lambda$1$lambda$0((Scope) obj, (ParametersHolder) obj2);
            case 5:
                return CombinedContext.toString$lambda$0((String) obj, (CoroutineContext.Element) obj2);
            case 6:
                return CoroutineContext.DefaultImpls.plus$lambda$0((CoroutineContext) obj, (CoroutineContext.Element) obj2);
            case 7:
                return Boolean.valueOf(CoroutineContextKt.hasCopyableElements$lambda$0(((Boolean) obj).booleanValue(), (CoroutineContext.Element) obj2));
            case 8:
                return CoroutineContextKt.foldCopies$lambda$2((CoroutineContext) obj, (CoroutineContext.Element) obj2);
            case 9:
                return ConcurrentWeakMap._get_keys_$lambda$0(obj, obj2);
            case 10:
                return ConcurrentWeakMap._get_entries_$lambda$1(obj, obj2);
            case 11:
                return Integer.valueOf(SafeCollector.collectContextSize$lambda$0(((Integer) obj).intValue(), (CoroutineContext.Element) obj2));
            case 12:
                return PublishKt.DEFAULT_HANDLER$lambda$2((Throwable) obj, (CoroutineContext) obj2);
            case 13:
                return FoundationCommonModuleKt.foundationCommonModule$lambda$4$lambda$0((Scope) obj, (ParametersHolder) obj2);
            case 14:
                return FoundationCommonModuleKt.foundationCommonModule$lambda$4$lambda$2((Scope) obj, (ParametersHolder) obj2);
            case 15:
                return FoundationCommonModuleKt.foundationCommonModule$lambda$4$lambda$3((Scope) obj, (ParametersHolder) obj2);
            case 16:
                return FoundationCryptoModuleKt.cryptoModule$lambda$1$lambda$0((Scope) obj, (ParametersHolder) obj2);
            case 17:
                return FoundationNetworkModuleKt.networkModule$lambda$9$lambda$2(40000, (Scope) obj, (ParametersHolder) obj2);
            case 18:
                return FoundationNetworkModuleKt.networkModule$lambda$9$lambda$3((Scope) obj, (ParametersHolder) obj2);
            case 19:
                return FoundationNetworkModuleKt.networkModule$lambda$9$lambda$4((Scope) obj, (ParametersHolder) obj2);
            case 20:
                return FoundationNetworkModuleKt.networkModule$lambda$9$lambda$5(5, (Scope) obj, (ParametersHolder) obj2);
            case 21:
                return FoundationNetworkModuleKt.networkModule$lambda$9$lambda$7((Scope) obj, (ParametersHolder) obj2);
            case 22:
                return FoundationNetworkModuleKt.networkModule$lambda$9$lambda$8((Scope) obj, (ParametersHolder) obj2);
            case 23:
                return FfiConversation.addSuperAdmin$lambda$18(((Long) obj).longValue(), (UniffiRustCallStatus) obj2);
            case 24:
                return FfiConversation.publishMessages$lambda$97(((Long) obj).longValue(), (UniffiRustCallStatus) obj2);
            case 25:
                return FfiConversation.processStreamedConversationMessage$lambda$92(((Long) obj).longValue(), (UniffiRustCallStatus) obj2);
            case 26:
                return FfiConversation.listMembers$lambda$85(((Long) obj).longValue(), (UniffiRustCallStatus) obj2);
            case 27:
                return FfiConversation.updateGroupImageUrlSquare$lambda$163(((Long) obj).longValue(), (UniffiRustCallStatus) obj2);
            case 28:
                return FfiConversation.removeConversationMessageDisappearingSettings$lambda$107(((Long) obj).longValue(), (UniffiRustCallStatus) obj2);
            default:
                return FfiConversation.updatePermissionPolicy$lambda$173(((Long) obj).longValue(), (UniffiRustCallStatus) obj2);
        }
    }
}
