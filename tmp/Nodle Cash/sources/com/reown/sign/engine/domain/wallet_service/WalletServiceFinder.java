package com.reown.sign.engine.domain.wallet_service;

import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.sequence.SessionVO;
import com.reown.sign.engine.model.EngineDO;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ.\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/reown/sign/engine/domain/wallet_service/WalletServiceFinder;", "", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/foundation/util/Logger;)V", "findMatchingWalletService", "Ljava/net/URL;", "request", "Lcom/reown/sign/engine/model/EngineDO$Request;", "session", "Lcom/reown/sign/common/model/vo/sequence/SessionVO;", "findWalletService", "method", "", "scopedProperties", "", "key", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWalletServiceFinder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WalletServiceFinder.kt\ncom/reown/sign/engine/domain/wallet_service/WalletServiceFinder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,68:1\n1#2:69\n*E\n"})
public final class WalletServiceFinder {
    @NotNull
    private final Logger logger;

    public WalletServiceFinder(@NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.logger = logger2;
    }

    private final URL findWalletService(String str, Map<String, String> map, String str2) {
        String str3 = map.get(str2);
        if (str3 == null) {
            return null;
        }
        Charset charset = Charsets.UTF_8;
        byte[] bytes = str3.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        try {
            JSONArray optJSONArray = new JSONObject(new String(bytes, charset)).optJSONArray("walletService");
            if (optJSONArray == null) {
                return null;
            }
            int length = optJSONArray.length();
            for (int i3 = 0; i3 < length; i3++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString("url");
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("methods");
                    if (optJSONArray2 != null) {
                        ArrayList arrayList = new ArrayList();
                        int length2 = optJSONArray2.length();
                        for (int i4 = 0; i4 < length2; i4++) {
                            String optString2 = optJSONArray2.optString(i4);
                            Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                            arrayList.add(optString2);
                        }
                        if (arrayList.contains(str)) {
                            Intrinsics.checkNotNull(optString);
                            if (optString.length() > 0) {
                                try {
                                    return new URL(optString);
                                } catch (Exception unused) {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
            return null;
        } catch (Exception e3) {
            this.logger.error("Failed to parse scopedProperties JSON: " + e3);
        }
    }

    @Nullable
    public final URL findMatchingWalletService(@NotNull EngineDO.Request request, @NotNull SessionVO sessionVO) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(sessionVO, "session");
        try {
            Map<String, String> scopedProperties = sessionVO.getScopedProperties();
            if (scopedProperties == null) {
                return null;
            }
            URL findWalletService = findWalletService(request.getMethod(), scopedProperties, request.getChainId());
            if (findWalletService != null) {
                return findWalletService;
            }
            URL findWalletService2 = findWalletService(request.getMethod(), scopedProperties, (String) CollectionsKt.first(StringsKt__StringsKt.split$default((CharSequence) request.getChainId(), new String[]{":"}, false, 0, 6, (Object) null)));
            if (findWalletService2 != null) {
                return findWalletService2;
            }
            return null;
        } catch (Exception unused) {
        }
    }
}
