package com.airbnb.lottie.compose;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.media.session.a;
import android.util.Base64;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import androidx.compose.runtime.MutableState;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.LottieTask;
import com.airbnb.lottie.compose.LottieCompositionSpec;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.fasterxml.jackson.core.JsonPointer;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Dispatchers;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\u001a3\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00012\u0006\u0010\t\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a+\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001aG\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\f\u001a\u0004\u0018\u00010\u00012\b\u0010\b\u001a\u0004\u0018\u00010\u00012\u0006\u0010\t\u001a\u00020\u00012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a2\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0015\u001a\u00020\u0016H\u0002\u001a\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\u0002\u001a\"\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0002\u001a*\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\b\u001a\u0004\u0018\u00010\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0002\u001a\u0001\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u00012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00012H\b\u0002\u0010 \u001aB\b\u0001\u0012\u0013\u0012\u00110\"¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0013\u0012\u00110&¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b('\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160(\u0012\u0006\u0012\u0004\u0018\u00010)0!H\u0007ø\u0001\u0000¢\u0006\u0002\u0010*\u001a\u001a\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u0001H\u0002\u001a!\u0010/\u001a\u0002H0\"\u0004\b\u0000\u00100*\b\u0012\u0004\u0012\u0002H00\u0014H@ø\u0001\u0000¢\u0006\u0002\u00101\u001a\f\u00102\u001a\u00020\u0001*\u00020\u0001H\u0002\u001a\u0010\u00103\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u00064²\u0006\n\u00105\u001a\u000206X\u0002"}, d2 = {"DefaultCacheKey", "", "loadFontsFromAssets", "", "context", "Landroid/content/Context;", "composition", "Lcom/airbnb/lottie/LottieComposition;", "fontAssetsFolder", "fontFileExtension", "(Landroid/content/Context;Lcom/airbnb/lottie/LottieComposition;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadImagesFromAssets", "imageAssetsFolder", "(Landroid/content/Context;Lcom/airbnb/lottie/LottieComposition;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lottieComposition", "spec", "Lcom/airbnb/lottie/compose/LottieCompositionSpec;", "cacheKey", "(Landroid/content/Context;Lcom/airbnb/lottie/compose/LottieCompositionSpec;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lottieTask", "Lcom/airbnb/lottie/LottieTask;", "isWarmingCache", "", "maybeDecodeBase64Image", "asset", "Lcom/airbnb/lottie/LottieImageAsset;", "maybeLoadImageFromAsset", "maybeLoadTypefaceFromAssets", "font", "Lcom/airbnb/lottie/model/Font;", "rememberLottieComposition", "Lcom/airbnb/lottie/compose/LottieCompositionResult;", "onRetry", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "failCount", "", "previousException", "Lkotlin/coroutines/Continuation;", "", "(Lcom/airbnb/lottie/compose/LottieCompositionSpec;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Lcom/airbnb/lottie/compose/LottieCompositionResult;", "typefaceForStyle", "Landroid/graphics/Typeface;", "typeface", "style", "await", "T", "(Lcom/airbnb/lottie/LottieTask;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ensureLeadingPeriod", "ensureTrailingSlash", "lottie-compose_release", "result", "Lcom/airbnb/lottie/compose/LottieCompositionResultImpl;"}, k = 2, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nrememberLottieComposition.kt\nKotlin\n*S Kotlin\n*F\n+ 1 rememberLottieComposition.kt\ncom/airbnb/lottie/compose/RememberLottieCompositionKt\n+ 2 CompositionLocal.kt\nandroidx/compose/runtime/CompositionLocal\n+ 3 Composables.kt\nandroidx/compose/runtime/ComposablesKt\n+ 4 Composer.kt\nandroidx/compose/runtime/ComposerKt\n+ 5 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 6 SnapshotState.kt\nandroidx/compose/runtime/SnapshotStateKt__SnapshotStateKt\n*L\n1#1,313:1\n76#2:314\n36#3:315\n50#3:322\n49#3:323\n1097#4,6:316\n1097#4,6:324\n314#5,11:330\n81#6:341\n*S KotlinDebug\n*F\n+ 1 rememberLottieComposition.kt\ncom/airbnb/lottie/compose/RememberLottieCompositionKt\n*L\n83#1:314\n84#1:315\n87#1:322\n87#1:323\n84#1:316,6\n87#1:324,6\n190#1:330,11\n84#1:341\n*E\n"})
public final class RememberLottieCompositionKt {
    @NotNull
    private static final String DefaultCacheKey = "__LottieInternalDefaultCacheKey__";

    /* access modifiers changed from: private */
    public static final <T> Object await(LottieTask<T> lottieTask, Continuation<? super T> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        lottieTask.addListener(new RememberLottieCompositionKt$await$2$1(cancellableContinuationImpl)).addFailureListener(new RememberLottieCompositionKt$await$2$2(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* access modifiers changed from: private */
    public static final String ensureLeadingPeriod(String str) {
        return (!StringsKt.isBlank(str) && !StringsKt__StringsJVMKt.startsWith$default(str, JwtUtilsKt.JWT_DELIMITER, false, 2, (Object) null)) ? c.a(JwtUtilsKt.JWT_DELIMITER, str) : str;
    }

    /* access modifiers changed from: private */
    public static final String ensureTrailingSlash(String str) {
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        return StringsKt__StringsKt.endsWith$default((CharSequence) str, (char) JsonPointer.SEPARATOR, false, 2, (Object) null) ? str : str.concat("/");
    }

    /* access modifiers changed from: private */
    public static final Object loadFontsFromAssets(Context context, LottieComposition lottieComposition, String str, String str2, Continuation<? super Unit> continuation) {
        if (lottieComposition.getFonts().isEmpty()) {
            return Unit.INSTANCE;
        }
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new RememberLottieCompositionKt$loadFontsFromAssets$2(lottieComposition, context, str, str2, (Continuation<? super RememberLottieCompositionKt$loadFontsFromAssets$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Object loadImagesFromAssets(Context context, LottieComposition lottieComposition, String str, Continuation<? super Unit> continuation) {
        if (!lottieComposition.hasImages()) {
            return Unit.INSTANCE;
        }
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new RememberLottieCompositionKt$loadImagesFromAssets$2(lottieComposition, context, str, (Continuation<? super RememberLottieCompositionKt$loadImagesFromAssets$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.lang.String} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0094 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00aa A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object lottieComposition(android.content.Context r6, com.airbnb.lottie.compose.LottieCompositionSpec r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, kotlin.coroutines.Continuation<? super com.airbnb.lottie.LottieComposition> r12) {
        /*
            boolean r0 = r12 instanceof com.airbnb.lottie.compose.RememberLottieCompositionKt$lottieComposition$1
            if (r0 == 0) goto L_0x0013
            r0 = r12
            com.airbnb.lottie.compose.RememberLottieCompositionKt$lottieComposition$1 r0 = (com.airbnb.lottie.compose.RememberLottieCompositionKt$lottieComposition$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.airbnb.lottie.compose.RememberLottieCompositionKt$lottieComposition$1 r0 = new com.airbnb.lottie.compose.RememberLottieCompositionKt$lottieComposition$1
            r0.<init>(r12)
        L_0x0018:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0067
            if (r2 == r5) goto L_0x0050
            if (r2 == r4) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            java.lang.Object r6 = r0.L$0
            com.airbnb.lottie.LottieComposition r6 = (com.airbnb.lottie.LottieComposition) r6
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00ab
        L_0x0034:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003c:
            java.lang.Object r6 = r0.L$3
            com.airbnb.lottie.LottieComposition r6 = (com.airbnb.lottie.LottieComposition) r6
            java.lang.Object r7 = r0.L$2
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r0.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r9 = r0.L$0
            android.content.Context r9 = (android.content.Context) r9
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0099
        L_0x0050:
            java.lang.Object r6 = r0.L$3
            r10 = r6
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r6 = r0.L$2
            r9 = r6
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r6 = r0.L$1
            r8 = r6
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r6 = r0.L$0
            android.content.Context r6 = (android.content.Context) r6
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0082
        L_0x0067:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = 0
            com.airbnb.lottie.LottieTask r11 = lottieTask(r6, r7, r11, r12)
            if (r11 == 0) goto L_0x00ac
            r0.L$0 = r6
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r10
            r0.label = r5
            java.lang.Object r12 = await(r11, r0)
            if (r12 != r1) goto L_0x0082
            return r1
        L_0x0082:
            com.airbnb.lottie.LottieComposition r12 = (com.airbnb.lottie.LottieComposition) r12
            r0.L$0 = r6
            r0.L$1 = r9
            r0.L$2 = r10
            r0.L$3 = r12
            r0.label = r4
            java.lang.Object r7 = loadImagesFromAssets(r6, r12, r8, r0)
            if (r7 != r1) goto L_0x0095
            return r1
        L_0x0095:
            r8 = r9
            r7 = r10
            r9 = r6
            r6 = r12
        L_0x0099:
            r0.L$0 = r6
            r10 = 0
            r0.L$1 = r10
            r0.L$2 = r10
            r0.L$3 = r10
            r0.label = r3
            java.lang.Object r7 = loadFontsFromAssets(r9, r6, r8, r7, r0)
            if (r7 != r1) goto L_0x00ab
            return r1
        L_0x00ab:
            return r6
        L_0x00ac:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r8 = "Unable to create parsing task for "
            r6.<init>(r8)
            r6.append(r7)
            java.lang.String r7 = "."
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.RememberLottieCompositionKt.lottieComposition(android.content.Context, com.airbnb.lottie.compose.LottieCompositionSpec, java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final LottieTask<LottieComposition> lottieTask(Context context, LottieCompositionSpec lottieCompositionSpec, String str, boolean z2) {
        if (lottieCompositionSpec instanceof LottieCompositionSpec.RawRes) {
            return Intrinsics.areEqual((Object) str, (Object) DefaultCacheKey) ? LottieCompositionFactory.fromRawRes(context, ((LottieCompositionSpec.RawRes) lottieCompositionSpec).m8192unboximpl()) : LottieCompositionFactory.fromRawRes(context, ((LottieCompositionSpec.RawRes) lottieCompositionSpec).m8192unboximpl(), str);
        }
        if (lottieCompositionSpec instanceof LottieCompositionSpec.Url) {
            return Intrinsics.areEqual((Object) str, (Object) DefaultCacheKey) ? LottieCompositionFactory.fromUrl(context, ((LottieCompositionSpec.Url) lottieCompositionSpec).m8199unboximpl()) : LottieCompositionFactory.fromUrl(context, ((LottieCompositionSpec.Url) lottieCompositionSpec).m8199unboximpl(), str);
        }
        if (lottieCompositionSpec instanceof LottieCompositionSpec.File) {
            if (z2) {
                return null;
            }
            LottieCompositionSpec.File file = (LottieCompositionSpec.File) lottieCompositionSpec;
            FileInputStream fileInputStream = new FileInputStream(file.m8178unboximpl());
            if (StringsKt__StringsJVMKt.endsWith$default(file.m8178unboximpl(), "zip", false, 2, (Object) null)) {
                ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
                if (Intrinsics.areEqual((Object) str, (Object) DefaultCacheKey)) {
                    str = file.m8178unboximpl();
                }
                return LottieCompositionFactory.fromZipStream(zipInputStream, str);
            }
            if (Intrinsics.areEqual((Object) str, (Object) DefaultCacheKey)) {
                str = file.m8178unboximpl();
            }
            return LottieCompositionFactory.fromJsonInputStream(fileInputStream, str);
        } else if (lottieCompositionSpec instanceof LottieCompositionSpec.Asset) {
            return Intrinsics.areEqual((Object) str, (Object) DefaultCacheKey) ? LottieCompositionFactory.fromAsset(context, ((LottieCompositionSpec.Asset) lottieCompositionSpec).m8164unboximpl()) : LottieCompositionFactory.fromAsset(context, ((LottieCompositionSpec.Asset) lottieCompositionSpec).m8164unboximpl(), str);
        } else {
            if (lottieCompositionSpec instanceof LottieCompositionSpec.JsonString) {
                if (Intrinsics.areEqual((Object) str, (Object) DefaultCacheKey)) {
                    str = String.valueOf(((LottieCompositionSpec.JsonString) lottieCompositionSpec).m8185unboximpl().hashCode());
                }
                return LottieCompositionFactory.fromJsonString(((LottieCompositionSpec.JsonString) lottieCompositionSpec).m8185unboximpl(), str);
            } else if (lottieCompositionSpec instanceof LottieCompositionSpec.ContentProvider) {
                LottieCompositionSpec.ContentProvider contentProvider = (LottieCompositionSpec.ContentProvider) lottieCompositionSpec;
                InputStream openInputStream = context.getContentResolver().openInputStream(contentProvider.m8171unboximpl());
                if (Intrinsics.areEqual((Object) str, (Object) DefaultCacheKey)) {
                    str = contentProvider.m8171unboximpl().toString();
                }
                return LottieCompositionFactory.fromJsonInputStream(openInputStream, str);
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void maybeDecodeBase64Image(LottieImageAsset lottieImageAsset) {
        if (lottieImageAsset.getBitmap() == null) {
            String fileName = lottieImageAsset.getFileName();
            Intrinsics.checkNotNull(fileName);
            if (StringsKt__StringsJVMKt.startsWith$default(fileName, "data:", false, 2, (Object) null) && StringsKt__StringsKt.indexOf$default((CharSequence) fileName, "base64,", 0, false, 6, (Object) null) > 0) {
                try {
                    String substring = fileName.substring(StringsKt__StringsKt.indexOf$default((CharSequence) fileName, (char) AbstractJsonLexerKt.COMMA, 0, false, 6, (Object) null) + 1);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                    byte[] decode = Base64.decode(substring, 0);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inScaled = true;
                    options.inDensity = 160;
                    lottieImageAsset.setBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length, options));
                } catch (IllegalArgumentException e3) {
                    Logger.warning("data URL did not have correct base64 format.", e3);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void maybeLoadImageFromAsset(Context context, LottieImageAsset lottieImageAsset, String str) {
        if (lottieImageAsset.getBitmap() == null && str != null) {
            String fileName = lottieImageAsset.getFileName();
            try {
                AssetManager assets = context.getAssets();
                InputStream open = assets.open(str + fileName);
                Intrinsics.checkNotNull(open);
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inScaled = true;
                    options.inDensity = 160;
                    lottieImageAsset.setBitmap(Utils.resizeBitmapIfNeeded(BitmapFactory.decodeStream(open, (Rect) null, options), lottieImageAsset.getWidth(), lottieImageAsset.getHeight()));
                } catch (IllegalArgumentException e3) {
                    Logger.warning("Unable to decode image.", e3);
                }
            } catch (IOException e4) {
                Logger.warning("Unable to open asset.", e4);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void maybeLoadTypefaceFromAssets(Context context, Font font, String str, String str2) {
        String n2 = a.n(str, font.getFamily(), str2);
        try {
            Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), n2);
            try {
                Intrinsics.checkNotNull(createFromAsset);
                String style = font.getStyle();
                Intrinsics.checkNotNullExpressionValue(style, "getStyle(...)");
                font.setTypeface(typefaceForStyle(createFromAsset, style));
            } catch (Exception e3) {
                Logger.error(C0118y.g("Failed to create ", font.getFamily(), " typeface with style=", font.getStyle(), "!"), e3);
            }
        } catch (Exception e4) {
            Logger.error("Failed to find typeface in assets with path " + n2 + JwtUtilsKt.JWT_DELIMITER, e4);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: androidx.compose.runtime.MutableState} */
    /* JADX WARNING: Multi-variable type inference failed */
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmOverloads
    @androidx.compose.runtime.Composable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.airbnb.lottie.compose.LottieCompositionResult rememberLottieComposition(@org.jetbrains.annotations.NotNull com.airbnb.lottie.compose.LottieCompositionSpec r17, @org.jetbrains.annotations.Nullable java.lang.String r18, @org.jetbrains.annotations.Nullable java.lang.String r19, @org.jetbrains.annotations.Nullable java.lang.String r20, @org.jetbrains.annotations.Nullable java.lang.String r21, @org.jetbrains.annotations.Nullable kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super java.lang.Throwable, ? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r22, @org.jetbrains.annotations.Nullable androidx.compose.runtime.Composer r23, int r24, int r25) {
        /*
            r10 = r17
            r11 = r23
            r0 = r24
            java.lang.String r1 = "spec"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r1)
            r1 = -1248473602(0xffffffffb595cdfe, float:-1.1161317E-6)
            r11.startReplaceableGroup(r1)
            r2 = r25 & 2
            r3 = 0
            if (r2 == 0) goto L_0x0018
            r4 = r3
            goto L_0x001a
        L_0x0018:
            r4 = r18
        L_0x001a:
            r2 = r25 & 4
            if (r2 == 0) goto L_0x0022
            java.lang.String r2 = "fonts/"
            r5 = r2
            goto L_0x0024
        L_0x0022:
            r5 = r19
        L_0x0024:
            r2 = r25 & 8
            if (r2 == 0) goto L_0x002c
            java.lang.String r2 = ".ttf"
            r6 = r2
            goto L_0x002e
        L_0x002c:
            r6 = r20
        L_0x002e:
            r2 = r25 & 16
            if (r2 == 0) goto L_0x0036
            java.lang.String r2 = "__LottieInternalDefaultCacheKey__"
            r12 = r2
            goto L_0x0038
        L_0x0036:
            r12 = r21
        L_0x0038:
            r2 = r25 & 32
            if (r2 == 0) goto L_0x0042
            com.airbnb.lottie.compose.RememberLottieCompositionKt$rememberLottieComposition$1 r2 = new com.airbnb.lottie.compose.RememberLottieCompositionKt$rememberLottieComposition$1
            r2.<init>(r3)
            goto L_0x0044
        L_0x0042:
            r2 = r22
        L_0x0044:
            boolean r7 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r7 == 0) goto L_0x0050
            r7 = -1
            java.lang.String r8 = "com.airbnb.lottie.compose.rememberLottieComposition (rememberLottieComposition.kt:81)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r1, r0, r7, r8)
        L_0x0050:
            androidx.compose.runtime.ProvidableCompositionLocal r1 = androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.getLocalContext()
            java.lang.Object r1 = r11.consume(r1)
            r7 = r1
            android.content.Context r7 = (android.content.Context) r7
            r13 = r0 & 14
            r1 = 1157296644(0x44faf204, float:2007.563)
            r11.startReplaceableGroup(r1)
            boolean r1 = r11.changed((java.lang.Object) r10)
            java.lang.Object r8 = r23.rememberedValue()
            if (r1 != 0) goto L_0x0075
            androidx.compose.runtime.Composer$Companion r1 = androidx.compose.runtime.Composer.Companion
            java.lang.Object r1 = r1.getEmpty()
            if (r8 != r1) goto L_0x0082
        L_0x0075:
            com.airbnb.lottie.compose.LottieCompositionResultImpl r1 = new com.airbnb.lottie.compose.LottieCompositionResultImpl
            r1.<init>()
            r8 = 2
            androidx.compose.runtime.MutableState r8 = androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(r1, r3, r8, r3)
            r11.updateRememberedValue(r8)
        L_0x0082:
            r23.endReplaceableGroup()
            r14 = r8
            androidx.compose.runtime.MutableState r14 = (androidx.compose.runtime.MutableState) r14
            int r0 = r0 >> 9
            r15 = r0 & 112(0x70, float:1.57E-43)
            r0 = 511388516(0x1e7b2b64, float:1.3296802E-20)
            r11.startReplaceableGroup(r0)
            boolean r0 = r11.changed((java.lang.Object) r10)
            boolean r1 = r11.changed((java.lang.Object) r12)
            r0 = r0 | r1
            java.lang.Object r1 = r23.rememberedValue()
            if (r0 != 0) goto L_0x00a9
            androidx.compose.runtime.Composer$Companion r0 = androidx.compose.runtime.Composer.Companion
            java.lang.Object r0 = r0.getEmpty()
            if (r1 != r0) goto L_0x00b1
        L_0x00a9:
            r0 = 1
            com.airbnb.lottie.LottieTask r0 = lottieTask(r7, r10, r12, r0)
            r11.updateRememberedValue(r0)
        L_0x00b1:
            r23.endReplaceableGroup()
            com.airbnb.lottie.compose.RememberLottieCompositionKt$rememberLottieComposition$3 r9 = new com.airbnb.lottie.compose.RememberLottieCompositionKt$rememberLottieComposition$3
            r16 = 0
            r0 = r9
            r1 = r2
            r2 = r7
            r3 = r17
            r7 = r12
            r8 = r14
            r18 = r14
            r14 = r9
            r9 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r0 = r13 | 512(0x200, float:7.175E-43)
            r0 = r0 | r15
            androidx.compose.runtime.EffectsKt.LaunchedEffect(r10, r12, r14, r11, r0)
            com.airbnb.lottie.compose.LottieCompositionResultImpl r0 = rememberLottieComposition$lambda$1(r18)
            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r1 == 0) goto L_0x00da
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        L_0x00da:
            r23.endReplaceableGroup()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.RememberLottieCompositionKt.rememberLottieComposition(com.airbnb.lottie.compose.LottieCompositionSpec, java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):com.airbnb.lottie.compose.LottieCompositionResult");
    }

    /* access modifiers changed from: private */
    public static final LottieCompositionResultImpl rememberLottieComposition$lambda$1(MutableState<LottieCompositionResultImpl> mutableState) {
        return mutableState.getValue();
    }

    private static final Typeface typefaceForStyle(Typeface typeface, String str) {
        boolean s3 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "Italic", false, 2, (Object) null);
        boolean s4 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "Bold", false, 2, (Object) null);
        int i3 = (!s3 || !s4) ? s3 ? 2 : s4 ? 1 : 0 : 3;
        return typeface.getStyle() == i3 ? typeface : Typeface.create(typeface, i3);
    }
}
