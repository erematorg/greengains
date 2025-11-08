package com.reown.android.pairing.model.mapper;

import com.reown.android.Core;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.Pairing;
import com.reown.foundation.common.model.Topic;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0001\u001a\f\u0010\u0000\u001a\u00020\u0003*\u00020\u0004H\u0001\u001a\n\u0010\u0005\u001a\u00020\u0004*\u00020\u0003\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0000\u001a\u000e\u0010\u0000\u001a\u00020\b*\u0004\u0018\u00010\u0007H\u0000¨\u0006\t"}, d2 = {"toCore", "Lcom/reown/android/Core$Model$DeletedPairing;", "Lcom/reown/android/pairing/engine/model/EngineDO$PairingDelete;", "Lcom/reown/android/Core$Model$Pairing;", "Lcom/reown/android/internal/common/model/Pairing;", "toPairing", "toAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "Lcom/reown/android/Core$Model$AppMetaData;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class PairingMapperKt {
    public static final /* synthetic */ Pairing toPairing(Core.Model.Pairing pairing) {
        Intrinsics.checkNotNullParameter(pairing, "<this>");
        Topic topic = new Topic(pairing.getTopic());
        Expiry expiry = new Expiry(pairing.getExpiry());
        Core.Model.AppMetaData peerAppMetaData = pairing.getPeerAppMetaData();
        return new Pairing(topic, expiry, peerAppMetaData != null ? Intrinsics.checkNotNullParameter(peerAppMetaData, "<this>") : null, pairing.getRelayProtocol(), pairing.getRelayData(), pairing.getUri(), false, pairing.getRegisteredMethods(), 64, (DefaultConstructorMarker) null);
    }

    @Deprecated(message = "This mapper has been deprecated. It will be removed soon.")
    public static final /* synthetic */ Core.Model.Pairing toCore(Pairing pairing) {
        Intrinsics.checkNotNullParameter(pairing, "<this>");
        String value = pairing.getTopic().getValue();
        long seconds = pairing.getExpiry().getSeconds();
        AppMetaData peerAppMetaData = pairing.getPeerAppMetaData();
        Core.Model.AppMetaData core = peerAppMetaData != null ? toCore(peerAppMetaData) : null;
        String relayProtocol = pairing.getRelayProtocol();
        String relayData = pairing.getRelayData();
        String uri = pairing.getUri();
        String methods = pairing.getMethods();
        if (methods == null) {
            methods = Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>");
        }
        return new Core.Model.Pairing(value, seconds, core, relayProtocol, relayData, uri, true, methods);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        r12 = r12.getRedirect();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ com.reown.android.Core.Model.AppMetaData toCore(com.reown.android.internal.common.model.AppMetaData r12) {
        /*
            com.reown.android.Core$Model$AppMetaData r11 = new com.reown.android.Core$Model$AppMetaData
            if (r12 == 0) goto L_0x000d
            java.lang.String r0 = r12.getName()
            if (r0 != 0) goto L_0x000b
            goto L_0x000d
        L_0x000b:
            r1 = r0
            goto L_0x0014
        L_0x000d:
            kotlin.jvm.internal.StringCompanionObject r0 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, "<this>")
            goto L_0x000b
        L_0x0014:
            if (r12 == 0) goto L_0x001f
            java.lang.String r0 = r12.getDescription()
            if (r0 != 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r2 = r0
            goto L_0x0026
        L_0x001f:
            kotlin.jvm.internal.StringCompanionObject r0 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, "<this>")
            goto L_0x001d
        L_0x0026:
            if (r12 == 0) goto L_0x0031
            java.lang.String r0 = r12.getUrl()
            if (r0 != 0) goto L_0x002f
            goto L_0x0031
        L_0x002f:
            r3 = r0
            goto L_0x0038
        L_0x0031:
            kotlin.jvm.internal.StringCompanionObject r0 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, "<this>")
            goto L_0x002f
        L_0x0038:
            if (r12 == 0) goto L_0x0043
            java.util.List r0 = r12.getIcons()
            if (r0 != 0) goto L_0x0041
            goto L_0x0043
        L_0x0041:
            r4 = r0
            goto L_0x0048
        L_0x0043:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            goto L_0x0041
        L_0x0048:
            if (r12 == 0) goto L_0x0056
            com.reown.android.internal.common.model.Redirect r12 = r12.getRedirect()
            if (r12 == 0) goto L_0x0056
            java.lang.String r12 = r12.getNative()
        L_0x0054:
            r5 = r12
            goto L_0x0058
        L_0x0056:
            r12 = 0
            goto L_0x0054
        L_0x0058:
            r9 = 224(0xe0, float:3.14E-43)
            r10 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r0 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.pairing.model.mapper.PairingMapperKt.toCore(com.reown.android.internal.common.model.AppMetaData):com.reown.android.Core$Model$AppMetaData");
    }
}
