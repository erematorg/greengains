package com.reown.android.utils.cacao;

import com.reown.android.cacao.SignatureInterface;
import com.reown.android.cacao.signature.ISignatureType;
import com.reown.android.cacao.signature.SignatureType;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.reown.android.internal.common.signing.eip191.EIP191Signer;
import com.reown.android.internal.common.signing.signature.SignatureKt;
import com.reown.android.push.notifications.PushMessagingService;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.full.KClassifiers;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.komputing.kbip44.BIP44Kt;
import org.web3j.utils.Numeric;

@Metadata(d1 = {"\u00006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001aF\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\n\b\u0001\u0010\u0003\u0018\u0001*\u0002H\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\b¢\u0006\u0002\u0010\u000b\u001aF\u0010\f\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\n\b\u0001\u0010\u0003\u0018\u0001*\u0002H\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\b¢\u0006\u0002\u0010\u000b\u001a;\u0010\u0000\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u0010\u001a;\u0010\f\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u0010\u001a\u001a\u0010\u0011\u001a\u00020\u0012\"\b\b\u0000\u0010\r*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\r0\u0013¨\u0006\u0014"}, d2 = {"sign", "CoreSignature", "Lcom/reown/android/cacao/SignatureInterface;", "SDKSignature", "Lcom/reown/android/utils/cacao/CacaoSignerInterface;", "message", "", "privateKey", "", "type", "Lcom/reown/android/cacao/signature/ISignatureType;", "(Lcom/reown/android/utils/cacao/CacaoSignerInterface;Ljava/lang/String;[BLcom/reown/android/cacao/signature/ISignatureType;)Lcom/reown/android/cacao/SignatureInterface;", "signHex", "T", "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;Ljava/lang/String;[BLcom/reown/android/cacao/signature/ISignatureType;)Lcom/reown/android/cacao/SignatureInterface;", "hasCorrectOrderedParametersInConstructor", "", "Lkotlin/reflect/KFunction;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCacaoSignerInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CacaoSignerInterface.kt\ncom/reown/android/utils/cacao/CacaoSignerUtil\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,89:1\n230#2,2:90\n230#2,2:92\n230#2,2:94\n230#2,2:96\n1#3:98\n*S KotlinDebug\n*F\n+ 1 CacaoSignerInterface.kt\ncom/reown/android/utils/cacao/CacaoSignerUtil\n*L\n28#1:90,2\n44#1:92,2\n55#1:94,2\n67#1:96,2\n*E\n"})
@JvmName(name = "CacaoSignerUtil")
public final class CacaoSignerUtil {
    public static final <T extends SignatureInterface> boolean hasCorrectOrderedParametersInConstructor(@NotNull KFunction<? extends T> kFunction) {
        Intrinsics.checkNotNullParameter(kFunction, "<this>");
        List<KParameter> parameters = kFunction.getParameters();
        if (parameters.size() != 3) {
            parameters = null;
        }
        if (parameters == null) {
            return false;
        }
        Class<String> cls = String.class;
        Class<?> cls2 = KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(cls), (List) null, false, (List) null, 5, (Object) null).getClass();
        Class<?> cls3 = KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(cls), (List) null, true, (List) null, 5, (Object) null).getClass();
        KParameter kParameter = (KParameter) CollectionsKt.getOrNull(parameters, 0);
        boolean z2 = kParameter != null && Intrinsics.areEqual((Object) kParameter.getType().getClass(), (Object) cls2) && CollectionsKt.contains(CollectionsKt.listOf("t", "arg0"), kParameter.getName());
        KParameter kParameter2 = (KParameter) CollectionsKt.getOrNull(parameters, 1);
        boolean z3 = kParameter2 != null && Intrinsics.areEqual((Object) kParameter2.getType().getClass(), (Object) cls2) && CollectionsKt.contains(CollectionsKt.listOf("s", "arg1"), kParameter2.getName());
        KParameter kParameter3 = (KParameter) CollectionsKt.getOrNull(parameters, 2);
        return z2 && z3 && (kParameter3 != null && Intrinsics.areEqual((Object) kParameter3.getType().getClass(), (Object) cls3) && CollectionsKt.contains(CollectionsKt.listOf(BIP44Kt.BIP44_PREFIX, "arg2"), kParameter3.getName()));
    }

    public static final /* synthetic */ <CoreSignature extends SignatureInterface, SDKSignature extends CoreSignature> CoreSignature sign(CacaoSignerInterface<CoreSignature> cacaoSignerInterface, String str, byte[] bArr, ISignatureType iSignatureType) {
        Intrinsics.checkNotNullParameter(cacaoSignerInterface, "<this>");
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullParameter(bArr, "privateKey");
        Intrinsics.checkNotNullParameter(iSignatureType, "type");
        String header = iSignatureType.getHeader();
        if (Intrinsics.areEqual((Object) header, (Object) SignatureType.EIP191.getHeader()) || Intrinsics.areEqual((Object) header, (Object) SignatureType.EIP1271.getHeader())) {
            String header2 = iSignatureType.getHeader();
            EIP191Signer eIP191Signer = EIP191Signer.INSTANCE;
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            Cacao.Signature signature = new Cacao.Signature(header2, SignatureKt.toHexSignature(eIP191Signer.sign(bytes, bArr)), (String) null, 4, (DefaultConstructorMarker) null);
            Intrinsics.reifiedOperationMarker(4, "SDKSignature");
            for (KFunction kFunction : Reflection.getOrCreateKotlinClass(SignatureInterface.class).getConstructors()) {
                if (hasCorrectOrderedParametersInConstructor(kFunction)) {
                    return (SignatureInterface) kFunction.call(signature.getT(), signature.getS(), signature.getM());
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }
        throw new Throwable("SignatureType not recognized");
    }

    public static final /* synthetic */ <CoreSignature extends SignatureInterface, SDKSignature extends CoreSignature> CoreSignature signHex(CacaoSignerInterface<CoreSignature> cacaoSignerInterface, String str, byte[] bArr, ISignatureType iSignatureType) {
        Intrinsics.checkNotNullParameter(cacaoSignerInterface, "<this>");
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullParameter(bArr, "privateKey");
        Intrinsics.checkNotNullParameter(iSignatureType, "type");
        String header = iSignatureType.getHeader();
        if (Intrinsics.areEqual((Object) header, (Object) SignatureType.EIP191.getHeader()) || Intrinsics.areEqual((Object) header, (Object) SignatureType.EIP1271.getHeader())) {
            String header2 = iSignatureType.getHeader();
            EIP191Signer eIP191Signer = EIP191Signer.INSTANCE;
            byte[] hexStringToByteArray = Numeric.hexStringToByteArray(str);
            Intrinsics.checkNotNullExpressionValue(hexStringToByteArray, "hexStringToByteArray(...)");
            Cacao.Signature signature = new Cacao.Signature(header2, SignatureKt.toHexSignature(eIP191Signer.sign(hexStringToByteArray, bArr)), (String) null, 4, (DefaultConstructorMarker) null);
            Intrinsics.reifiedOperationMarker(4, "SDKSignature");
            for (KFunction kFunction : Reflection.getOrCreateKotlinClass(SignatureInterface.class).getConstructors()) {
                if (hasCorrectOrderedParametersInConstructor(kFunction)) {
                    return (SignatureInterface) kFunction.call(signature.getT(), signature.getS(), signature.getM());
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }
        throw new Throwable("SignatureType not recognized");
    }

    @NotNull
    public static final <T extends SignatureInterface> T sign(@NotNull Class<T> cls, @NotNull String str, @NotNull byte[] bArr, @NotNull ISignatureType iSignatureType) {
        Intrinsics.checkNotNullParameter(cls, "clazz");
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullParameter(bArr, "privateKey");
        Intrinsics.checkNotNullParameter(iSignatureType, "type");
        String header = iSignatureType.getHeader();
        if (Intrinsics.areEqual((Object) header, (Object) SignatureType.EIP191.getHeader()) || Intrinsics.areEqual((Object) header, (Object) SignatureType.EIP1271.getHeader())) {
            String header2 = iSignatureType.getHeader();
            EIP191Signer eIP191Signer = EIP191Signer.INSTANCE;
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            Cacao.Signature signature = new Cacao.Signature(header2, SignatureKt.toHexSignature(eIP191Signer.sign(bytes, bArr)), (String) null, 4, (DefaultConstructorMarker) null);
            for (KFunction kFunction : JvmClassMappingKt.getKotlinClass(cls).getConstructors()) {
                if (hasCorrectOrderedParametersInConstructor(kFunction)) {
                    return (SignatureInterface) kFunction.call(signature.getT(), signature.getS(), signature.getM());
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }
        throw new Throwable("SignatureType not recognized");
    }

    @NotNull
    public static final <T extends SignatureInterface> T signHex(@NotNull Class<T> cls, @NotNull String str, @NotNull byte[] bArr, @NotNull ISignatureType iSignatureType) {
        Intrinsics.checkNotNullParameter(cls, "clazz");
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullParameter(bArr, "privateKey");
        Intrinsics.checkNotNullParameter(iSignatureType, "type");
        String header = iSignatureType.getHeader();
        if (Intrinsics.areEqual((Object) header, (Object) SignatureType.EIP191.getHeader()) || Intrinsics.areEqual((Object) header, (Object) SignatureType.EIP1271.getHeader())) {
            String header2 = iSignatureType.getHeader();
            EIP191Signer eIP191Signer = EIP191Signer.INSTANCE;
            byte[] hexStringToByteArray = Numeric.hexStringToByteArray(str);
            Intrinsics.checkNotNullExpressionValue(hexStringToByteArray, "hexStringToByteArray(...)");
            Cacao.Signature signature = new Cacao.Signature(header2, SignatureKt.toHexSignature(eIP191Signer.sign(hexStringToByteArray, bArr)), (String) null, 4, (DefaultConstructorMarker) null);
            for (KFunction kFunction : JvmClassMappingKt.getKotlinClass(cls).getConstructors()) {
                if (hasCorrectOrderedParametersInConstructor(kFunction)) {
                    return (SignatureInterface) kFunction.call(signature.getT(), signature.getS(), signature.getM());
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }
        throw new Throwable("SignatureType not recognized");
    }
}
