package com.airbnb.lottie.compose;

import androidx.compose.runtime.Composable;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.sun.jna.Callback;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0004*\u0001\u0015\u001a)\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\"\u0006\u0012\u0002\b\u00030\u0004H\u0007¢\u0006\u0002\u0010\u0005\u001a=\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0004\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u0002H\u00072\u0006\u0010\t\u001a\u0002H\u00072\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0003\"\u00020\u000bH\u0007¢\u0006\u0002\u0010\f\u001a^\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0004\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u0002H\u00072\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0003\"\u00020\u000b2'\u0010\r\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00070\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u0002H\u00070\u000eH\u0007¢\u0006\u0002\u0010\u0013\u001a>\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0015\"\u0004\b\u0000\u0010\u0007*#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00070\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u0002H\u00070\u000eH\u0002¢\u0006\u0002\u0010\u0016¨\u0006\u0017²\u00061\u0010\u0018\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00070\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u0002H\u00070\u000e\"\u0004\b\u0000\u0010\u0007X\u0002"}, d2 = {"rememberLottieDynamicProperties", "Lcom/airbnb/lottie/compose/LottieDynamicProperties;", "properties", "", "Lcom/airbnb/lottie/compose/LottieDynamicProperty;", "([Lcom/airbnb/lottie/compose/LottieDynamicProperty;Landroidx/compose/runtime/Composer;I)Lcom/airbnb/lottie/compose/LottieDynamicProperties;", "rememberLottieDynamicProperty", "T", "property", "value", "keyPath", "", "(Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Lcom/airbnb/lottie/compose/LottieDynamicProperty;", "callback", "Lkotlin/Function1;", "Lcom/airbnb/lottie/value/LottieFrameInfo;", "Lkotlin/ParameterName;", "name", "frameInfo", "(Ljava/lang/Object;[Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Lcom/airbnb/lottie/compose/LottieDynamicProperty;", "toValueCallback", "com/airbnb/lottie/compose/LottieDynamicPropertiesKt$toValueCallback$1", "(Lkotlin/jvm/functions/Function1;)Lcom/airbnb/lottie/compose/LottieDynamicPropertiesKt$toValueCallback$1;", "lottie-compose_release", "callbackState"}, k = 2, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLottieDynamicProperties.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LottieDynamicProperties.kt\ncom/airbnb/lottie/compose/LottieDynamicPropertiesKt\n+ 2 Composables.kt\nandroidx/compose/runtime/ComposablesKt\n+ 3 Composer.kt\nandroidx/compose/runtime/ComposerKt\n+ 4 SnapshotState.kt\nandroidx/compose/runtime/SnapshotStateKt__SnapshotStateKt\n*L\n1#1,184:1\n36#2:185\n36#2:192\n67#2,3:199\n66#2:202\n36#2:209\n50#2:216\n49#2:217\n1097#3,6:186\n1097#3,6:193\n1097#3,6:203\n1097#3,6:210\n1097#3,6:218\n81#4:224\n*S KotlinDebug\n*F\n+ 1 LottieDynamicProperties.kt\ncom/airbnb/lottie/compose/LottieDynamicPropertiesKt\n*L\n28#1:185\n47#1:192\n48#1:199,3\n48#1:202\n70#1:209\n72#1:216\n72#1:217\n28#1:186,6\n47#1:193,6\n48#1:203,6\n70#1:210,6\n72#1:218,6\n71#1:224\n*E\n"})
public final class LottieDynamicPropertiesKt {
    @NotNull
    @Composable
    public static final LottieDynamicProperties rememberLottieDynamicProperties(@NotNull LottieDynamicProperty<?>[] lottieDynamicPropertyArr, @Nullable Composer composer, int i3) {
        Intrinsics.checkNotNullParameter(lottieDynamicPropertyArr, StringLookupFactory.KEY_PROPERTIES);
        composer.startReplaceableGroup(-395574495);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-395574495, i3, -1, "com.airbnb.lottie.compose.rememberLottieDynamicProperties (LottieDynamicProperties.kt:26)");
        }
        Integer valueOf = Integer.valueOf(Arrays.hashCode(lottieDynamicPropertyArr));
        composer.startReplaceableGroup(1157296644);
        boolean changed = composer.changed((Object) valueOf);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.getEmpty()) {
            rememberedValue = new LottieDynamicProperties(ArraysKt.toList((T[]) lottieDynamicPropertyArr));
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        LottieDynamicProperties lottieDynamicProperties = (LottieDynamicProperties) rememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return lottieDynamicProperties;
    }

    @NotNull
    @Composable
    public static final <T> LottieDynamicProperty<T> rememberLottieDynamicProperty(T t2, T t3, @NotNull String[] strArr, @Nullable Composer composer, int i3) {
        Intrinsics.checkNotNullParameter(strArr, "keyPath");
        composer.startReplaceableGroup(-1788530187);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1788530187, i3, -1, "com.airbnb.lottie.compose.rememberLottieDynamicProperty (LottieDynamicProperties.kt:45)");
        }
        composer.startReplaceableGroup(1157296644);
        boolean changed = composer.changed((Object) strArr);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.getEmpty()) {
            rememberedValue = new KeyPath((String[]) Arrays.copyOf(strArr, strArr.length));
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        KeyPath keyPath = (KeyPath) rememberedValue;
        composer.startReplaceableGroup(1618982084);
        boolean changed2 = composer.changed((Object) keyPath) | composer.changed((Object) t2) | composer.changed((Object) t3);
        Object rememberedValue2 = composer.rememberedValue();
        if (changed2 || rememberedValue2 == Composer.Companion.getEmpty()) {
            rememberedValue2 = new LottieDynamicProperty(t2, keyPath, t3);
            composer.updateRememberedValue(rememberedValue2);
        }
        composer.endReplaceableGroup();
        LottieDynamicProperty<T> lottieDynamicProperty = (LottieDynamicProperty) rememberedValue2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return lottieDynamicProperty;
    }

    /* access modifiers changed from: private */
    public static final <T> Function1<LottieFrameInfo<T>, T> rememberLottieDynamicProperty$lambda$4(State<? extends Function1<? super LottieFrameInfo<T>, ? extends T>> state) {
        return (Function1) state.getValue();
    }

    /* access modifiers changed from: private */
    public static final LottieDynamicPropertiesKt$toValueCallback$1 toValueCallback(Function1 function1) {
        return new LottieDynamicPropertiesKt$toValueCallback$1(function1);
    }

    @NotNull
    @Composable
    public static final <T> LottieDynamicProperty<T> rememberLottieDynamicProperty(T t2, @NotNull String[] strArr, @NotNull Function1<? super LottieFrameInfo<T>, ? extends T> function1, @Nullable Composer composer, int i3) {
        Intrinsics.checkNotNullParameter(strArr, "keyPath");
        Intrinsics.checkNotNullParameter(function1, Callback.METHOD_NAME);
        composer.startReplaceableGroup(1331897370);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1331897370, i3, -1, "com.airbnb.lottie.compose.rememberLottieDynamicProperty (LottieDynamicProperties.kt:68)");
        }
        Integer valueOf = Integer.valueOf(Arrays.hashCode(strArr));
        composer.startReplaceableGroup(1157296644);
        boolean changed = composer.changed((Object) valueOf);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.getEmpty()) {
            rememberedValue = new KeyPath((String[]) Arrays.copyOf(strArr, strArr.length));
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        KeyPath keyPath = (KeyPath) rememberedValue;
        State<T> rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composer, (i3 >> 6) & 14);
        composer.startReplaceableGroup(511388516);
        boolean changed2 = composer.changed((Object) keyPath) | composer.changed((Object) t2);
        Object rememberedValue2 = composer.rememberedValue();
        if (changed2 || rememberedValue2 == Composer.Companion.getEmpty()) {
            rememberedValue2 = new LottieDynamicProperty(t2, keyPath, new LottieDynamicPropertiesKt$rememberLottieDynamicProperty$2$1(rememberUpdatedState));
            composer.updateRememberedValue(rememberedValue2);
        }
        composer.endReplaceableGroup();
        LottieDynamicProperty<T> lottieDynamicProperty = (LottieDynamicProperty) rememberedValue2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return lottieDynamicProperty;
    }
}
