package com.airbnb.lottie.compose;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.layout.ScaleFactor;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a­\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\n2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\n2\u0016\b\u0002\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010\u001d\u001aÝ\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u001e\u001a\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020\n2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\b\b\u0002\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010%\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\n2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\n2\u0016\b\u0002\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010&\u001a\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\n2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010'\u001a\"\u0010(\u001a\u00020)*\u00020*2\u0006\u0010+\u001a\u00020,H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010.\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006/²\u0006\f\u00100\u001a\u0004\u0018\u00010\u0011X\u0002²\u0006\n\u0010\u0004\u001a\u00020\u0006X\u0002"}, d2 = {"LottieAnimation", "", "composition", "Lcom/airbnb/lottie/LottieComposition;", "progress", "Lkotlin/Function0;", "", "modifier", "Landroidx/compose/ui/Modifier;", "outlineMasksAndMattes", "", "applyOpacityToLayers", "enableMergePaths", "renderMode", "Lcom/airbnb/lottie/RenderMode;", "maintainOriginalImageBounds", "dynamicProperties", "Lcom/airbnb/lottie/compose/LottieDynamicProperties;", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "clipToCompositionBounds", "fontMap", "", "", "Landroid/graphics/Typeface;", "asyncUpdates", "Lcom/airbnb/lottie/AsyncUpdates;", "(Lcom/airbnb/lottie/LottieComposition;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZZZLcom/airbnb/lottie/RenderMode;ZLcom/airbnb/lottie/compose/LottieDynamicProperties;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;ZLjava/util/Map;Lcom/airbnb/lottie/AsyncUpdates;Landroidx/compose/runtime/Composer;III)V", "isPlaying", "restartOnPlay", "clipSpec", "Lcom/airbnb/lottie/compose/LottieClipSpec;", "speed", "iterations", "", "reverseOnRepeat", "(Lcom/airbnb/lottie/LottieComposition;Landroidx/compose/ui/Modifier;ZZLcom/airbnb/lottie/compose/LottieClipSpec;FIZZZLcom/airbnb/lottie/RenderMode;ZZLcom/airbnb/lottie/compose/LottieDynamicProperties;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;ZLjava/util/Map;Lcom/airbnb/lottie/AsyncUpdates;Landroidx/compose/runtime/Composer;III)V", "(Lcom/airbnb/lottie/LottieComposition;FLandroidx/compose/ui/Modifier;ZZZLcom/airbnb/lottie/RenderMode;ZLcom/airbnb/lottie/compose/LottieDynamicProperties;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;ZLcom/airbnb/lottie/AsyncUpdates;Landroidx/compose/runtime/Composer;III)V", "times", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/ui/geometry/Size;", "scale", "Landroidx/compose/ui/layout/ScaleFactor;", "times-UQTWf7w", "(JJ)J", "lottie-compose_release", "setDynamicProperties"}, k = 2, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLottieAnimation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LottieAnimation.kt\ncom/airbnb/lottie/compose/LottieAnimationKt\n+ 2 Composables.kt\nandroidx/compose/runtime/ComposablesKt\n+ 3 Composer.kt\nandroidx/compose/runtime/ComposerKt\n+ 4 Dp.kt\nandroidx/compose/ui/unit/DpKt\n+ 5 SnapshotState.kt\nandroidx/compose/runtime/SnapshotStateKt__SnapshotStateKt\n*L\n1#1,233:1\n25#2:234\n25#2:241\n36#2:248\n36#2:256\n36#2:263\n1097#3,6:235\n1097#3,6:242\n1097#3,6:249\n1097#3,6:257\n1097#3,6:264\n174#4:255\n81#5:270\n107#5,2:271\n81#5:273\n*S KotlinDebug\n*F\n+ 1 LottieAnimation.kt\ncom/airbnb/lottie/compose/LottieAnimationKt\n*L\n92#1:234\n93#1:241\n94#1:248\n158#1:256\n214#1:263\n92#1:235,6\n93#1:242,6\n94#1:249,6\n158#1:257,6\n214#1:264,6\n101#1:255\n94#1:270\n94#1:271,2\n203#1:273\n*E\n"})
public final class LottieAnimationKt {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: androidx.compose.runtime.MutableState} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: androidx.compose.ui.Modifier$Companion} */
    /* JADX WARNING: Multi-variable type inference failed */
    @androidx.compose.runtime.ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @kotlin.jvm.JvmOverloads
    @androidx.compose.runtime.Composable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void LottieAnimation(@org.jetbrains.annotations.Nullable com.airbnb.lottie.LottieComposition r35, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<java.lang.Float> r36, @org.jetbrains.annotations.Nullable androidx.compose.ui.Modifier r37, boolean r38, boolean r39, boolean r40, @org.jetbrains.annotations.Nullable com.airbnb.lottie.RenderMode r41, boolean r42, @org.jetbrains.annotations.Nullable com.airbnb.lottie.compose.LottieDynamicProperties r43, @org.jetbrains.annotations.Nullable androidx.compose.ui.Alignment r44, @org.jetbrains.annotations.Nullable androidx.compose.ui.layout.ContentScale r45, boolean r46, @org.jetbrains.annotations.Nullable java.util.Map<java.lang.String, ? extends android.graphics.Typeface> r47, @org.jetbrains.annotations.Nullable com.airbnb.lottie.AsyncUpdates r48, @org.jetbrains.annotations.Nullable androidx.compose.runtime.Composer r49, int r50, int r51, int r52) {
        /*
            r15 = r35
            r14 = r50
            r13 = r52
            java.lang.String r0 = "progress"
            r12 = r36
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            r0 = -1070242582(0xffffffffc03564ea, float:-2.8342843)
            r1 = r49
            androidx.compose.runtime.Composer r11 = r1.startRestartGroup(r0)
            r1 = r13 & 4
            if (r1 == 0) goto L_0x001e
            androidx.compose.ui.Modifier$Companion r1 = androidx.compose.ui.Modifier.Companion
            r10 = r1
            goto L_0x0020
        L_0x001e:
            r10 = r37
        L_0x0020:
            r1 = r13 & 8
            r9 = 0
            if (r1 == 0) goto L_0x0028
            r17 = r9
            goto L_0x002a
        L_0x0028:
            r17 = r38
        L_0x002a:
            r1 = r13 & 16
            if (r1 == 0) goto L_0x0031
            r18 = r9
            goto L_0x0033
        L_0x0031:
            r18 = r39
        L_0x0033:
            r1 = r13 & 32
            if (r1 == 0) goto L_0x003a
            r19 = r9
            goto L_0x003c
        L_0x003a:
            r19 = r40
        L_0x003c:
            r1 = r13 & 64
            if (r1 == 0) goto L_0x0045
            com.airbnb.lottie.RenderMode r1 = com.airbnb.lottie.RenderMode.AUTOMATIC
            r20 = r1
            goto L_0x0047
        L_0x0045:
            r20 = r41
        L_0x0047:
            r1 = r13 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x004e
            r21 = r9
            goto L_0x0050
        L_0x004e:
            r21 = r42
        L_0x0050:
            r1 = r13 & 256(0x100, float:3.59E-43)
            r2 = 0
            if (r1 == 0) goto L_0x0058
            r22 = r2
            goto L_0x005a
        L_0x0058:
            r22 = r43
        L_0x005a:
            r1 = r13 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0067
            androidx.compose.ui.Alignment$Companion r1 = androidx.compose.ui.Alignment.Companion
            androidx.compose.ui.Alignment r1 = r1.getCenter()
            r23 = r1
            goto L_0x0069
        L_0x0067:
            r23 = r44
        L_0x0069:
            r1 = r13 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L_0x0076
            androidx.compose.ui.layout.ContentScale$Companion r1 = androidx.compose.ui.layout.ContentScale.Companion
            androidx.compose.ui.layout.ContentScale r1 = r1.getFit()
            r24 = r1
            goto L_0x0078
        L_0x0076:
            r24 = r45
        L_0x0078:
            r1 = r13 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x0080
            r1 = 1
            r25 = r1
            goto L_0x0082
        L_0x0080:
            r25 = r46
        L_0x0082:
            r1 = r13 & 4096(0x1000, float:5.74E-42)
            if (r1 == 0) goto L_0x0089
            r26 = r2
            goto L_0x008b
        L_0x0089:
            r26 = r47
        L_0x008b:
            r1 = r13 & 8192(0x2000, float:1.14794E-41)
            if (r1 == 0) goto L_0x0094
            com.airbnb.lottie.AsyncUpdates r1 = com.airbnb.lottie.AsyncUpdates.AUTOMATIC
            r27 = r1
            goto L_0x0096
        L_0x0094:
            r27 = r48
        L_0x0096:
            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r1 == 0) goto L_0x00a4
            java.lang.String r1 = "com.airbnb.lottie.compose.LottieAnimation (LottieAnimation.kt:90)"
            r8 = r51
            androidx.compose.runtime.ComposerKt.traceEventStart(r0, r14, r8, r1)
            goto L_0x00a6
        L_0x00a4:
            r8 = r51
        L_0x00a6:
            r0 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
            r11.startReplaceableGroup(r0)
            java.lang.Object r1 = r11.rememberedValue()
            androidx.compose.runtime.Composer$Companion r3 = androidx.compose.runtime.Composer.Companion
            java.lang.Object r4 = r3.getEmpty()
            if (r1 != r4) goto L_0x00c0
            com.airbnb.lottie.LottieDrawable r1 = new com.airbnb.lottie.LottieDrawable
            r1.<init>()
            r11.updateRememberedValue(r1)
        L_0x00c0:
            r11.endReplaceableGroup()
            r5 = r1
            com.airbnb.lottie.LottieDrawable r5 = (com.airbnb.lottie.LottieDrawable) r5
            r11.startReplaceableGroup(r0)
            java.lang.Object r0 = r11.rememberedValue()
            java.lang.Object r1 = r3.getEmpty()
            if (r0 != r1) goto L_0x00db
            android.graphics.Matrix r0 = new android.graphics.Matrix
            r0.<init>()
            r11.updateRememberedValue(r0)
        L_0x00db:
            r11.endReplaceableGroup()
            r4 = r0
            android.graphics.Matrix r4 = (android.graphics.Matrix) r4
            r0 = 1157296644(0x44faf204, float:2007.563)
            r11.startReplaceableGroup(r0)
            boolean r0 = r11.changed((java.lang.Object) r15)
            java.lang.Object r1 = r11.rememberedValue()
            if (r0 != 0) goto L_0x00f7
            java.lang.Object r0 = r3.getEmpty()
            if (r1 != r0) goto L_0x00ff
        L_0x00f7:
            r0 = 2
            androidx.compose.runtime.MutableState r1 = androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(r2, r2, r0, r2)
            r11.updateRememberedValue(r1)
        L_0x00ff:
            r11.endReplaceableGroup()
            r16 = r1
            androidx.compose.runtime.MutableState r16 = (androidx.compose.runtime.MutableState) r16
            r0 = 185151773(0xb09311d, float:2.642219E-32)
            r11.startReplaceableGroup(r0)
            if (r15 == 0) goto L_0x0117
            float r0 = r35.getDuration()
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x011c
        L_0x0117:
            r37 = r10
            r0 = r11
            goto L_0x01b9
        L_0x011c:
            r11.endReplaceableGroup()
            float r0 = com.airbnb.lottie.utils.Utils.dpScale()
            android.graphics.Rect r1 = r35.getBounds()
            int r1 = r1.width()
            float r1 = (float) r1
            float r1 = r1 / r0
            float r1 = androidx.compose.ui.unit.Dp.m7637constructorimpl(r1)
            android.graphics.Rect r2 = r35.getBounds()
            int r2 = r2.height()
            float r2 = (float) r2
            float r2 = r2 / r0
            float r0 = androidx.compose.ui.unit.Dp.m7637constructorimpl(r2)
            androidx.compose.ui.Modifier r7 = androidx.compose.foundation.layout.SizeKt.m913sizeVpY3zN4(r10, r1, r0)
            com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$2 r6 = new com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$2
            r0 = r6
            r1 = r35
            r2 = r24
            r3 = r23
            r28 = r6
            r6 = r19
            r29 = r7
            r7 = r20
            r8 = r27
            r9 = r26
            r37 = r10
            r10 = r22
            r30 = r11
            r11 = r17
            r12 = r18
            r13 = r21
            r14 = r25
            r15 = r36
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            r3 = r28
            r2 = r29
            r0 = r30
            r1 = 0
            androidx.compose.foundation.CanvasKt.Canvas(r2, r3, r0, r1)
            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r1 == 0) goto L_0x017e
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        L_0x017e:
            androidx.compose.runtime.ScopeUpdateScope r15 = r0.endRestartGroup()
            if (r15 != 0) goto L_0x0185
            goto L_0x01b8
        L_0x0185:
            com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$3 r14 = new com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$3
            r0 = r14
            r1 = r35
            r2 = r36
            r3 = r37
            r4 = r17
            r5 = r18
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r11 = r24
            r12 = r25
            r13 = r26
            r31 = r14
            r14 = r27
            r32 = r15
            r15 = r50
            r16 = r51
            r17 = r52
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            r1 = r31
            r0 = r32
            r0.updateScope(r1)
        L_0x01b8:
            return
        L_0x01b9:
            int r1 = r50 >> 6
            r1 = r1 & 14
            r3 = r37
            androidx.compose.foundation.layout.BoxKt.Box(r3, r0, r1)
            r0.endReplaceableGroup()
            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r1 == 0) goto L_0x01ce
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        L_0x01ce:
            androidx.compose.runtime.ScopeUpdateScope r15 = r0.endRestartGroup()
            if (r15 != 0) goto L_0x01d5
            goto L_0x0206
        L_0x01d5:
            com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$1 r14 = new com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$1
            r0 = r14
            r1 = r35
            r2 = r36
            r4 = r17
            r5 = r18
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r11 = r24
            r12 = r25
            r13 = r26
            r33 = r14
            r14 = r27
            r34 = r15
            r15 = r50
            r16 = r51
            r17 = r52
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            r1 = r33
            r0 = r34
            r0.updateScope(r1)
        L_0x0206:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.LottieAnimationKt.LottieAnimation(com.airbnb.lottie.LottieComposition, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, boolean, boolean, com.airbnb.lottie.RenderMode, boolean, com.airbnb.lottie.compose.LottieDynamicProperties, androidx.compose.ui.Alignment, androidx.compose.ui.layout.ContentScale, boolean, java.util.Map, com.airbnb.lottie.AsyncUpdates, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* access modifiers changed from: private */
    public static final LottieDynamicProperties LottieAnimation$lambda$3(MutableState<LottieDynamicProperties> mutableState) {
        return mutableState.getValue();
    }

    /* access modifiers changed from: private */
    public static final void LottieAnimation$lambda$4(MutableState<LottieDynamicProperties> mutableState, LottieDynamicProperties lottieDynamicProperties) {
        mutableState.setValue(lottieDynamicProperties);
    }

    /* access modifiers changed from: private */
    public static final float LottieAnimation$lambda$6(LottieAnimationState lottieAnimationState) {
        return ((Number) lottieAnimationState.getValue()).floatValue();
    }

    /* access modifiers changed from: private */
    /* renamed from: times-UQTWf7w  reason: not valid java name */
    public static final long m8157timesUQTWf7w(long j2, long j3) {
        return IntSizeKt.IntSize((int) (ScaleFactor.m6325getScaleXimpl(j3) * Size.m4446getWidthimpl(j2)), (int) (ScaleFactor.m6326getScaleYimpl(j3) * Size.m4443getHeightimpl(j2)));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: kotlin.jvm.functions.Function0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: androidx.compose.ui.Modifier$Companion} */
    /* JADX WARNING: Multi-variable type inference failed */
    @androidx.compose.runtime.ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @kotlin.Deprecated(message = "Pass progress as a lambda instead of a float. This overload will be removed in the next release.")
    @androidx.compose.runtime.Composable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void LottieAnimation(@org.jetbrains.annotations.Nullable com.airbnb.lottie.LottieComposition r38, @androidx.annotation.FloatRange(from = 0.0d, to = 1.0d) float r39, @org.jetbrains.annotations.Nullable androidx.compose.ui.Modifier r40, boolean r41, boolean r42, boolean r43, @org.jetbrains.annotations.Nullable com.airbnb.lottie.RenderMode r44, boolean r45, @org.jetbrains.annotations.Nullable com.airbnb.lottie.compose.LottieDynamicProperties r46, @org.jetbrains.annotations.Nullable androidx.compose.ui.Alignment r47, @org.jetbrains.annotations.Nullable androidx.compose.ui.layout.ContentScale r48, boolean r49, @org.jetbrains.annotations.Nullable com.airbnb.lottie.AsyncUpdates r50, @org.jetbrains.annotations.Nullable androidx.compose.runtime.Composer r51, int r52, int r53, int r54) {
        /*
            r14 = r52
            r15 = r53
            r13 = r54
            r0 = 627485782(0x2566ac56, float:2.000771E-16)
            r1 = r51
            androidx.compose.runtime.Composer r1 = r1.startRestartGroup(r0)
            r2 = r13 & 4
            if (r2 == 0) goto L_0x0017
            androidx.compose.ui.Modifier$Companion r2 = androidx.compose.ui.Modifier.Companion
            r3 = r2
            goto L_0x0019
        L_0x0017:
            r3 = r40
        L_0x0019:
            r2 = r13 & 8
            r4 = 0
            if (r2 == 0) goto L_0x0020
            r5 = r4
            goto L_0x0022
        L_0x0020:
            r5 = r41
        L_0x0022:
            r2 = r13 & 16
            if (r2 == 0) goto L_0x0028
            r6 = r4
            goto L_0x002a
        L_0x0028:
            r6 = r42
        L_0x002a:
            r2 = r13 & 32
            if (r2 == 0) goto L_0x0030
            r7 = r4
            goto L_0x0032
        L_0x0030:
            r7 = r43
        L_0x0032:
            r2 = r13 & 64
            if (r2 == 0) goto L_0x003a
            com.airbnb.lottie.RenderMode r2 = com.airbnb.lottie.RenderMode.AUTOMATIC
            r8 = r2
            goto L_0x003c
        L_0x003a:
            r8 = r44
        L_0x003c:
            r2 = r13 & 128(0x80, float:1.794E-43)
            if (r2 == 0) goto L_0x0042
            r9 = r4
            goto L_0x0044
        L_0x0042:
            r9 = r45
        L_0x0044:
            r2 = r13 & 256(0x100, float:3.59E-43)
            if (r2 == 0) goto L_0x004b
            r2 = 0
            r10 = r2
            goto L_0x004d
        L_0x004b:
            r10 = r46
        L_0x004d:
            r2 = r13 & 512(0x200, float:7.175E-43)
            if (r2 == 0) goto L_0x0059
            androidx.compose.ui.Alignment$Companion r2 = androidx.compose.ui.Alignment.Companion
            androidx.compose.ui.Alignment r2 = r2.getCenter()
            r11 = r2
            goto L_0x005b
        L_0x0059:
            r11 = r47
        L_0x005b:
            r2 = r13 & 1024(0x400, float:1.435E-42)
            if (r2 == 0) goto L_0x0067
            androidx.compose.ui.layout.ContentScale$Companion r2 = androidx.compose.ui.layout.ContentScale.Companion
            androidx.compose.ui.layout.ContentScale r2 = r2.getFit()
            r12 = r2
            goto L_0x0069
        L_0x0067:
            r12 = r48
        L_0x0069:
            r2 = r13 & 2048(0x800, float:2.87E-42)
            if (r2 == 0) goto L_0x0071
            r2 = 1
            r34 = r2
            goto L_0x0073
        L_0x0071:
            r34 = r49
        L_0x0073:
            r2 = r13 & 4096(0x1000, float:5.74E-42)
            if (r2 == 0) goto L_0x007c
            com.airbnb.lottie.AsyncUpdates r2 = com.airbnb.lottie.AsyncUpdates.AUTOMATIC
            r35 = r2
            goto L_0x007e
        L_0x007c:
            r35 = r50
        L_0x007e:
            boolean r2 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r2 == 0) goto L_0x0089
            java.lang.String r2 = "com.airbnb.lottie.compose.LottieAnimation (LottieAnimation.kt:154)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r0, r14, r15, r2)
        L_0x0089:
            java.lang.Float r0 = java.lang.Float.valueOf(r39)
            r2 = 1157296644(0x44faf204, float:2007.563)
            r1.startReplaceableGroup(r2)
            boolean r0 = r1.changed((java.lang.Object) r0)
            java.lang.Object r2 = r1.rememberedValue()
            if (r0 != 0) goto L_0x00a9
            androidx.compose.runtime.Composer$Companion r0 = androidx.compose.runtime.Composer.Companion
            java.lang.Object r0 = r0.getEmpty()
            if (r2 != r0) goto L_0x00a6
            goto L_0x00a9
        L_0x00a6:
            r4 = r39
            goto L_0x00b3
        L_0x00a9:
            com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$4$1 r2 = new com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$4$1
            r4 = r39
            r2.<init>(r4)
            r1.updateRememberedValue(r2)
        L_0x00b3:
            r1.endReplaceableGroup()
            r17 = r2
            kotlin.jvm.functions.Function0 r17 = (kotlin.jvm.functions.Function0) r17
            r0 = r14 & 896(0x380, float:1.256E-42)
            r2 = 134217736(0x8000008, float:3.8518636E-34)
            r0 = r0 | r2
            r2 = r14 & 7168(0x1c00, float:1.0045E-41)
            r0 = r0 | r2
            r2 = 57344(0xe000, float:8.0356E-41)
            r2 = r2 & r14
            r0 = r0 | r2
            r2 = 458752(0x70000, float:6.42848E-40)
            r2 = r2 & r14
            r0 = r0 | r2
            r2 = 3670016(0x380000, float:5.142788E-39)
            r2 = r2 & r14
            r0 = r0 | r2
            r2 = 29360128(0x1c00000, float:7.052966E-38)
            r2 = r2 & r14
            r0 = r0 | r2
            r2 = 1879048192(0x70000000, float:1.58456325E29)
            r2 = r2 & r14
            r31 = r0 | r2
            r0 = r15 & 126(0x7e, float:1.77E-43)
            int r2 = r15 << 3
            r2 = r2 & 7168(0x1c00, float:1.0045E-41)
            r32 = r0 | r2
            r33 = 4096(0x1000, float:5.74E-42)
            r28 = 0
            r16 = r38
            r18 = r3
            r19 = r5
            r20 = r6
            r21 = r7
            r22 = r8
            r23 = r9
            r24 = r10
            r25 = r11
            r26 = r12
            r27 = r34
            r29 = r35
            r30 = r1
            LottieAnimation(r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33)
            boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r0 == 0) goto L_0x010b
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        L_0x010b:
            androidx.compose.runtime.ScopeUpdateScope r2 = r1.endRestartGroup()
            if (r2 != 0) goto L_0x0112
            goto L_0x0139
        L_0x0112:
            com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$5 r1 = new com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$5
            r0 = r1
            r36 = r1
            r1 = r38
            r37 = r2
            r2 = r39
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r34
            r13 = r35
            r14 = r52
            r15 = r53
            r16 = r54
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            r1 = r36
            r0 = r37
            r0.updateScope(r1)
        L_0x0139:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.LottieAnimationKt.LottieAnimation(com.airbnb.lottie.LottieComposition, float, androidx.compose.ui.Modifier, boolean, boolean, boolean, com.airbnb.lottie.RenderMode, boolean, com.airbnb.lottie.compose.LottieDynamicProperties, androidx.compose.ui.Alignment, androidx.compose.ui.layout.ContentScale, boolean, com.airbnb.lottie.AsyncUpdates, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: kotlin.jvm.functions.Function0} */
    /* JADX WARNING: Multi-variable type inference failed */
    @androidx.compose.runtime.ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @kotlin.jvm.JvmOverloads
    @androidx.compose.runtime.Composable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void LottieAnimation(@org.jetbrains.annotations.Nullable com.airbnb.lottie.LottieComposition r32, @org.jetbrains.annotations.Nullable androidx.compose.ui.Modifier r33, boolean r34, boolean r35, @org.jetbrains.annotations.Nullable com.airbnb.lottie.compose.LottieClipSpec r36, float r37, int r38, boolean r39, boolean r40, boolean r41, @org.jetbrains.annotations.Nullable com.airbnb.lottie.RenderMode r42, boolean r43, boolean r44, @org.jetbrains.annotations.Nullable com.airbnb.lottie.compose.LottieDynamicProperties r45, @org.jetbrains.annotations.Nullable androidx.compose.ui.Alignment r46, @org.jetbrains.annotations.Nullable androidx.compose.ui.layout.ContentScale r47, boolean r48, @org.jetbrains.annotations.Nullable java.util.Map<java.lang.String, ? extends android.graphics.Typeface> r49, @org.jetbrains.annotations.Nullable com.airbnb.lottie.AsyncUpdates r50, @org.jetbrains.annotations.Nullable androidx.compose.runtime.Composer r51, int r52, int r53, int r54) {
        /*
            r15 = r52
            r14 = r53
            r13 = r54
            r0 = 1541656025(0x5be3cdd9, float:1.28242303E17)
            r1 = r51
            androidx.compose.runtime.Composer r1 = r1.startRestartGroup(r0)
            r2 = r13 & 2
            if (r2 == 0) goto L_0x0016
            androidx.compose.ui.Modifier$Companion r2 = androidx.compose.ui.Modifier.Companion
            goto L_0x0018
        L_0x0016:
            r2 = r33
        L_0x0018:
            r3 = r13 & 4
            if (r3 == 0) goto L_0x001e
            r3 = 1
            goto L_0x0020
        L_0x001e:
            r3 = r34
        L_0x0020:
            r5 = r13 & 8
            if (r5 == 0) goto L_0x0026
            r5 = 1
            goto L_0x0028
        L_0x0026:
            r5 = r35
        L_0x0028:
            r6 = r13 & 16
            r7 = 0
            if (r6 == 0) goto L_0x002f
            r6 = r7
            goto L_0x0031
        L_0x002f:
            r6 = r36
        L_0x0031:
            r8 = r13 & 32
            if (r8 == 0) goto L_0x0038
            r8 = 1065353216(0x3f800000, float:1.0)
            goto L_0x003a
        L_0x0038:
            r8 = r37
        L_0x003a:
            r9 = r13 & 64
            if (r9 == 0) goto L_0x0040
            r9 = 1
            goto L_0x0042
        L_0x0040:
            r9 = r38
        L_0x0042:
            r10 = r13 & 128(0x80, float:1.794E-43)
            r11 = 0
            if (r10 == 0) goto L_0x0049
            r10 = r11
            goto L_0x004b
        L_0x0049:
            r10 = r39
        L_0x004b:
            r12 = r13 & 256(0x100, float:3.59E-43)
            if (r12 == 0) goto L_0x0051
            r12 = r11
            goto L_0x0053
        L_0x0051:
            r12 = r40
        L_0x0053:
            r4 = r13 & 512(0x200, float:7.175E-43)
            if (r4 == 0) goto L_0x005a
            r16 = r11
            goto L_0x005c
        L_0x005a:
            r16 = r41
        L_0x005c:
            r4 = r13 & 1024(0x400, float:1.435E-42)
            if (r4 == 0) goto L_0x0065
            com.airbnb.lottie.RenderMode r4 = com.airbnb.lottie.RenderMode.AUTOMATIC
            r17 = r4
            goto L_0x0067
        L_0x0065:
            r17 = r42
        L_0x0067:
            r4 = r13 & 2048(0x800, float:2.87E-42)
            if (r4 == 0) goto L_0x006e
            r18 = r11
            goto L_0x0070
        L_0x006e:
            r18 = r43
        L_0x0070:
            r4 = r13 & 4096(0x1000, float:5.74E-42)
            if (r4 == 0) goto L_0x0077
            r19 = r11
            goto L_0x0079
        L_0x0077:
            r19 = r44
        L_0x0079:
            r4 = r13 & 8192(0x2000, float:1.14794E-41)
            if (r4 == 0) goto L_0x0080
            r20 = r7
            goto L_0x0082
        L_0x0080:
            r20 = r45
        L_0x0082:
            r4 = r13 & 16384(0x4000, float:2.2959E-41)
            if (r4 == 0) goto L_0x008f
            androidx.compose.ui.Alignment$Companion r4 = androidx.compose.ui.Alignment.Companion
            androidx.compose.ui.Alignment r4 = r4.getCenter()
            r21 = r4
            goto L_0x0091
        L_0x008f:
            r21 = r46
        L_0x0091:
            r4 = 32768(0x8000, float:4.5918E-41)
            r4 = r4 & r13
            if (r4 == 0) goto L_0x00a0
            androidx.compose.ui.layout.ContentScale$Companion r4 = androidx.compose.ui.layout.ContentScale.Companion
            androidx.compose.ui.layout.ContentScale r4 = r4.getFit()
            r22 = r4
            goto L_0x00a2
        L_0x00a0:
            r22 = r47
        L_0x00a2:
            r4 = 65536(0x10000, float:9.18355E-41)
            r4 = r4 & r13
            if (r4 == 0) goto L_0x00aa
            r23 = 1
            goto L_0x00ac
        L_0x00aa:
            r23 = r48
        L_0x00ac:
            r4 = 131072(0x20000, float:1.83671E-40)
            r4 = r4 & r13
            if (r4 == 0) goto L_0x00b4
            r24 = r7
            goto L_0x00b6
        L_0x00b4:
            r24 = r49
        L_0x00b6:
            r4 = 262144(0x40000, float:3.67342E-40)
            r4 = r4 & r13
            if (r4 == 0) goto L_0x00c0
            com.airbnb.lottie.AsyncUpdates r4 = com.airbnb.lottie.AsyncUpdates.AUTOMATIC
            r25 = r4
            goto L_0x00c2
        L_0x00c0:
            r25 = r50
        L_0x00c2:
            boolean r4 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r4 == 0) goto L_0x00cd
            java.lang.String r4 = "com.airbnb.lottie.compose.LottieAnimation (LottieAnimation.kt:201)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r0, r15, r14, r4)
        L_0x00cd:
            int r0 = r15 >> 3
            r4 = r0 & 112(0x70, float:1.57E-43)
            r4 = r4 | 8
            r0 = r0 & 896(0x380, float:1.256E-42)
            r0 = r0 | r4
            int r4 = r14 << 6
            r4 = r4 & 7168(0x1c00, float:1.0045E-41)
            r0 = r0 | r4
            r4 = 57344(0xe000, float:8.0356E-41)
            r7 = r15 & r4
            r0 = r0 | r7
            r7 = 458752(0x70000, float:6.42848E-40)
            r11 = r15 & r7
            r0 = r0 | r11
            r11 = 3670016(0x380000, float:5.142788E-39)
            r26 = r15 & r11
            r0 = r0 | r26
            r26 = 896(0x380, float:1.256E-42)
            r27 = 0
            r28 = 0
            r29 = 0
            r33 = r32
            r34 = r3
            r35 = r5
            r36 = r18
            r37 = r6
            r38 = r8
            r39 = r9
            r40 = r27
            r41 = r28
            r42 = r29
            r43 = r1
            r44 = r0
            r45 = r26
            com.airbnb.lottie.compose.LottieAnimationState r0 = com.airbnb.lottie.compose.AnimateLottieCompositionAsStateKt.animateLottieCompositionAsState(r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45)
            r11 = 1157296644(0x44faf204, float:2007.563)
            r1.startReplaceableGroup(r11)
            boolean r11 = r1.changed((java.lang.Object) r0)
            java.lang.Object r7 = r1.rememberedValue()
            if (r11 != 0) goto L_0x012a
            androidx.compose.runtime.Composer$Companion r11 = androidx.compose.runtime.Composer.Companion
            java.lang.Object r11 = r11.getEmpty()
            if (r7 != r11) goto L_0x0132
        L_0x012a:
            com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$6$1 r7 = new com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$6$1
            r7.<init>(r0)
            r1.updateRememberedValue(r7)
        L_0x0132:
            r1.endReplaceableGroup()
            r0 = r7
            kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
            r34 = r0
            int r0 = r15 << 3
            r0 = r0 & 896(0x380, float:1.256E-42)
            r7 = 134217736(0x8000008, float:3.8518636E-34)
            r0 = r0 | r7
            int r7 = r15 >> 12
            r11 = r7 & 7168(0x1c00, float:1.0045E-41)
            r0 = r0 | r11
            r4 = r4 & r7
            r0 = r0 | r4
            r4 = 458752(0x70000, float:6.42848E-40)
            r4 = r4 & r7
            r0 = r0 | r4
            int r4 = r14 << 18
            r7 = 3670016(0x380000, float:5.142788E-39)
            r4 = r4 & r7
            r0 = r0 | r4
            int r4 = r14 << 15
            r7 = 29360128(0x1c00000, float:7.052966E-38)
            r7 = r7 & r4
            r0 = r0 | r7
            r7 = 1879048192(0x70000000, float:1.58456325E29)
            r4 = r4 & r7
            r0 = r0 | r4
            r48 = r0
            int r0 = r14 >> 15
            r4 = r0 & 14
            r4 = r4 | 512(0x200, float:7.175E-43)
            r7 = r0 & 112(0x70, float:1.57E-43)
            r4 = r4 | r7
            r0 = r0 & 7168(0x1c00, float:1.0045E-41)
            r0 = r0 | r4
            r49 = r0
            r0 = 0
            r50 = r0
            r33 = r32
            r35 = r2
            r36 = r10
            r37 = r12
            r38 = r16
            r39 = r17
            r40 = r19
            r41 = r20
            r42 = r21
            r43 = r22
            r44 = r23
            r45 = r24
            r46 = r25
            r47 = r1
            LottieAnimation(r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50)
            boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r0 == 0) goto L_0x0198
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        L_0x0198:
            androidx.compose.runtime.ScopeUpdateScope r11 = r1.endRestartGroup()
            if (r11 != 0) goto L_0x019f
            goto L_0x01d6
        L_0x019f:
            com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$7 r7 = new com.airbnb.lottie.compose.LottieAnimationKt$LottieAnimation$7
            r0 = r7
            r1 = r32
            r4 = r5
            r5 = r6
            r6 = r8
            r8 = r7
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r9
            r9 = r12
            r12 = r10
            r10 = r16
            r30 = r11
            r11 = r17
            r31 = r12
            r12 = r18
            r13 = r19
            r14 = r20
            r15 = r21
            r16 = r22
            r17 = r23
            r18 = r24
            r19 = r25
            r20 = r52
            r21 = r53
            r22 = r54
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            r0 = r30
            r1 = r31
            r0.updateScope(r1)
        L_0x01d6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.LottieAnimationKt.LottieAnimation(com.airbnb.lottie.LottieComposition, androidx.compose.ui.Modifier, boolean, boolean, com.airbnb.lottie.compose.LottieClipSpec, float, int, boolean, boolean, boolean, com.airbnb.lottie.RenderMode, boolean, boolean, com.airbnb.lottie.compose.LottieDynamicProperties, androidx.compose.ui.Alignment, androidx.compose.ui.layout.ContentScale, boolean, java.util.Map, com.airbnb.lottie.AsyncUpdates, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
