package com.reown.android.internal.common.signing.eip1271;

import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import androidx.compose.ui.autofill.a;
import com.reown.android.internal.common.signing.signature.Signature;
import com.reown.android.internal.common.signing.signature.SignatureKt;
import com.reown.util.UtilFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;
import timber.log.Timber;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0002J\f\u0010\u000f\u001a\u00020\u0005*\u00020\u0005H\u0002J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0002J&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0005J&\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0005J(\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/reown/android/internal/common/signing/eip1271/EIP1271Verifier;", "", "<init>", "()V", "isValidSignatureHash", "", "method", "dynamicTypeOffset", "dynamicTypeLength", "mediaTypeString", "rpcUrlPrefix", "hexPrefix", "getValidResponse", "id", "", "prefixWithRpcUrl", "createBody", "Lokhttp3/RequestBody;", "to", "data", "verify", "", "signature", "Lcom/reown/android/internal/common/signing/signature/Signature;", "originalMessage", "address", "projectId", "verifyHex", "hexMessage", "messageHash", "getResponseResult", "payload", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EIP1271Verifier {
    @NotNull
    public static final EIP1271Verifier INSTANCE = new EIP1271Verifier();
    @NotNull
    private static final String dynamicTypeLength = "0000000000000000000000000000000000000000000000000000000000000041";
    @NotNull
    private static final String dynamicTypeOffset = "0000000000000000000000000000000000000000000000000000000000000040";
    @NotNull
    private static final String hexPrefix = "0x";
    @NotNull
    private static final String isValidSignatureHash = "0x1626ba7e";
    @NotNull
    private static final String mediaTypeString = "application/json; charset=utf-8";
    @NotNull
    private static final String method = "eth_call";
    @NotNull
    private static final String rpcUrlPrefix = "https://rpc.walletconnect.com/v1/?chainId=eip155:1&projectId=";

    private EIP1271Verifier() {
    }

    private final RequestBody createBody(String str, String str2, long j2) {
        MediaType mediaType = MediaType.Companion.get(mediaTypeString);
        StringBuilder l2 = C0118y.l("{\n                |\"method\" : \"eth_call\",\n                |\"params\" : [{\"to\":\"", str, "\", \"data\":\"", str2, "\"}, \"latest\"],\n                |\"id\":");
        l2.append(j2);
        l2.append(", \"jsonrpc\":\"2.0\"\n                |}");
        return RequestBody.Companion.create(StringsKt__IndentKt.trimMargin$default(l2.toString(), (String) null, 1, (Object) null), mediaType);
    }

    private final String getResponseResult(String str) {
        Object obj = new JSONObject(StringsKt.trimIndent(str)).get("result");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        return (String) obj;
    }

    private final String getValidResponse(long j2) {
        return a.j("{\"jsonrpc\":\"2.0\",\"id\":", j2, ",\"result\":\"0x1626ba7e00000000000000000000000000000000000000000000000000000000\"}");
    }

    private final String prefixWithRpcUrl(String str) {
        return c.a(rpcUrlPrefix, str);
    }

    public final boolean verify(@NotNull Signature signature, @NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(str, "originalMessage");
        Intrinsics.checkNotNullParameter(str2, Address.TYPE_NAME);
        Intrinsics.checkNotNullParameter(str3, "projectId");
        try {
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            byte[] ethereumMessageHash = Sign.getEthereumMessageHash(bytes);
            Intrinsics.checkNotNullExpressionValue(ethereumMessageHash, "getEthereumMessageHash(...)");
            return verify(UtilFunctionsKt.bytesToHex(ethereumMessageHash), signature, str3, str2);
        } catch (Exception e3) {
            Timber.Forest.e(e3);
            return false;
        }
    }

    public final boolean verifyHex(@NotNull Signature signature, @NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(str, "hexMessage");
        Intrinsics.checkNotNullParameter(str2, Address.TYPE_NAME);
        Intrinsics.checkNotNullParameter(str3, "projectId");
        try {
            byte[] ethereumMessageHash = Sign.getEthereumMessageHash(Numeric.hexStringToByteArray(str));
            Intrinsics.checkNotNullExpressionValue(ethereumMessageHash, "getEthereumMessageHash(...)");
            return verify(UtilFunctionsKt.bytesToHex(ethereumMessageHash), signature, str3, str2);
        } catch (Exception e3) {
            Timber.Forest.e(e3);
            return false;
        }
    }

    private final boolean verify(String str, Signature signature, String str2, String str3) {
        String string;
        String f2 = C0118y.f(isValidSignatureHash, str, "00000000000000000000000000000000000000000000000000000000000000400000000000000000000000000000000000000000000000000000000000000041", StringsKt__StringsKt.removePrefix(SignatureKt.toHexSignature(signature), (CharSequence) "0x"));
        long generateId = UtilFunctionsKt.generateId();
        ResponseBody body = new OkHttpClient().newCall(new Request.Builder().url(prefixWithRpcUrl(str2)).post(createBody(str3, f2, generateId)).build()).execute().body();
        if (body == null || (string = body.string()) == null) {
            throw new Exception("Response body is null");
        }
        return Intrinsics.areEqual((Object) getResponseResult(string), (Object) getResponseResult(getValidResponse(generateId)));
    }
}
