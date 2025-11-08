package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.utils.CoreValidator;
import com.reown.sign.common.validator.SignValidator;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0002¨\u0006\f"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/GetNamespacesFromReCaps;", "", "<init>", "()V", "invoke", "", "", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "chains", "", "methods", "Companion", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetNamespacesFromReCaps.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetNamespacesFromReCaps.kt\ncom/reown/sign/engine/use_case/calls/GetNamespacesFromReCaps\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,18:1\n1740#2,3:19\n1740#2,3:22\n*S KotlinDebug\n*F\n+ 1 GetNamespacesFromReCaps.kt\ncom/reown/sign/engine/use_case/calls/GetNamespacesFromReCaps\n*L\n9#1:19,3\n10#1:22,3\n*E\n"})
public final class GetNamespacesFromReCaps {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private static final String EIP155 = "eip155";

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/GetNamespacesFromReCaps$Companion;", "", "<init>", "()V", "EIP155", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @NotNull
    public final Map<String, Namespace.Proposal> invoke(@NotNull List<String> list, @NotNull List<String> list2) {
        Intrinsics.checkNotNullParameter(list, "chains");
        Intrinsics.checkNotNullParameter(list2, "methods");
        Iterable<String> iterable = list;
        boolean z2 = iterable instanceof Collection;
        if (!z2 || !((Collection) iterable).isEmpty()) {
            for (String isChainIdCAIP2Compliant : iterable) {
                if (!CoreValidator.INSTANCE.isChainIdCAIP2Compliant(isChainIdCAIP2Compliant)) {
                    throw new Exception("Chains are not CAIP-2 compliant");
                }
            }
        }
        if (!z2 || !((Collection) iterable).isEmpty()) {
            for (String namespaceKeyFromChainId$sign_release : iterable) {
                if (!Intrinsics.areEqual((Object) SignValidator.INSTANCE.getNamespaceKeyFromChainId$sign_release(namespaceKeyFromChainId$sign_release), (Object) EIP155)) {
                    throw new Exception("Only eip155 (EVM) is supported");
                }
            }
        }
        return MapsKt.mapOf(TuplesKt.to(SignValidator.INSTANCE.getNamespaceKeyFromChainId$sign_release((String) CollectionsKt.first(list)), new Namespace.Proposal(list2, list, CollectionsKt.listOf("chainChanged", "accountsChanged"))));
    }
}
