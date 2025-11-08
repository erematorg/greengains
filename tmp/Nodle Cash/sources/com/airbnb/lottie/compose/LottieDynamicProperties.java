package com.airbnb.lottie.compose;

import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.PointF;
import android.graphics.Typeface;
import androidx.compose.runtime.internal.StabilityInferred;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.ScaleXY;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003¢\u0006\u0002\u0010\u0005B¿\u0001\b\u0000\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0003\u0012\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00040\u0003\u0012\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u0003\u0012\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00040\u0003\u0012\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00040\u0003\u0012\u0016\u0010\u0010\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u00040\u0003\u0012\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u0003\u0012\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u0003\u0012\u0012\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00040\u0003¢\u0006\u0002\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dJ\u0015\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001fR\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/airbnb/lottie/compose/LottieDynamicProperties;", "", "properties", "", "Lcom/airbnb/lottie/compose/LottieDynamicProperty;", "(Ljava/util/List;)V", "intProperties", "", "pointFProperties", "Landroid/graphics/PointF;", "floatProperties", "", "scaleProperties", "Lcom/airbnb/lottie/value/ScaleXY;", "colorFilterProperties", "Landroid/graphics/ColorFilter;", "intArrayProperties", "", "typefaceProperties", "Landroid/graphics/Typeface;", "bitmapProperties", "Landroid/graphics/Bitmap;", "charSequenceProperties", "", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "addTo", "", "drawable", "Lcom/airbnb/lottie/LottieDrawable;", "addTo$lottie_compose_release", "removeFrom", "removeFrom$lottie_compose_release", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLottieDynamicProperties.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LottieDynamicProperties.kt\ncom/airbnb/lottie/compose/LottieDynamicProperties\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,184:1\n766#2:185\n857#2,2:186\n766#2:188\n857#2,2:189\n766#2:191\n857#2,2:192\n766#2:194\n857#2,2:195\n766#2:197\n857#2,2:198\n766#2:200\n857#2,2:201\n766#2:203\n857#2,2:204\n766#2:206\n857#2,2:207\n766#2:209\n857#2,2:210\n1855#2,2:212\n1855#2,2:214\n1855#2,2:216\n1855#2,2:218\n1855#2,2:220\n1855#2,2:222\n1855#2,2:224\n1855#2,2:226\n1855#2,2:228\n1855#2,2:230\n1855#2,2:232\n1855#2,2:234\n1855#2,2:236\n1855#2,2:238\n1855#2,2:240\n1855#2,2:242\n1855#2,2:244\n1855#2,2:246\n*S KotlinDebug\n*F\n+ 1 LottieDynamicProperties.kt\ncom/airbnb/lottie/compose/LottieDynamicProperties\n*L\n108#1:185\n108#1:186,2\n109#1:188\n109#1:189,2\n110#1:191\n110#1:192,2\n111#1:194\n111#1:195,2\n112#1:197\n112#1:198,2\n113#1:200\n113#1:201,2\n114#1:203\n114#1:204,2\n115#1:206\n115#1:207,2\n116#1:209\n116#1:210,2\n120#1:212,2\n123#1:214,2\n126#1:216,2\n129#1:218,2\n132#1:220,2\n135#1:222,2\n138#1:224,2\n141#1:226,2\n144#1:228,2\n150#1:230,2\n153#1:232,2\n156#1:234,2\n159#1:236,2\n162#1:238,2\n165#1:240,2\n168#1:242,2\n171#1:244,2\n174#1:246,2\n*E\n"})
public final class LottieDynamicProperties {
    public static final int $stable = 8;
    @NotNull
    private final List<LottieDynamicProperty<Bitmap>> bitmapProperties;
    @NotNull
    private final List<LottieDynamicProperty<CharSequence>> charSequenceProperties;
    @NotNull
    private final List<LottieDynamicProperty<ColorFilter>> colorFilterProperties;
    @NotNull
    private final List<LottieDynamicProperty<Float>> floatProperties;
    @NotNull
    private final List<LottieDynamicProperty<Object[]>> intArrayProperties;
    @NotNull
    private final List<LottieDynamicProperty<Integer>> intProperties;
    @NotNull
    private final List<LottieDynamicProperty<PointF>> pointFProperties;
    @NotNull
    private final List<LottieDynamicProperty<ScaleXY>> scaleProperties;
    @NotNull
    private final List<LottieDynamicProperty<Typeface>> typefaceProperties;

    public LottieDynamicProperties(@NotNull List<LottieDynamicProperty<Integer>> list, @NotNull List<LottieDynamicProperty<PointF>> list2, @NotNull List<LottieDynamicProperty<Float>> list3, @NotNull List<LottieDynamicProperty<ScaleXY>> list4, @NotNull List<LottieDynamicProperty<ColorFilter>> list5, @NotNull List<LottieDynamicProperty<Object[]>> list6, @NotNull List<LottieDynamicProperty<Typeface>> list7, @NotNull List<LottieDynamicProperty<Bitmap>> list8, @NotNull List<LottieDynamicProperty<CharSequence>> list9) {
        Intrinsics.checkNotNullParameter(list, "intProperties");
        Intrinsics.checkNotNullParameter(list2, "pointFProperties");
        Intrinsics.checkNotNullParameter(list3, "floatProperties");
        Intrinsics.checkNotNullParameter(list4, "scaleProperties");
        Intrinsics.checkNotNullParameter(list5, "colorFilterProperties");
        Intrinsics.checkNotNullParameter(list6, "intArrayProperties");
        Intrinsics.checkNotNullParameter(list7, "typefaceProperties");
        Intrinsics.checkNotNullParameter(list8, "bitmapProperties");
        Intrinsics.checkNotNullParameter(list9, "charSequenceProperties");
        this.intProperties = list;
        this.pointFProperties = list2;
        this.floatProperties = list3;
        this.scaleProperties = list4;
        this.colorFilterProperties = list5;
        this.intArrayProperties = list6;
        this.typefaceProperties = list7;
        this.bitmapProperties = list8;
        this.charSequenceProperties = list9;
    }

    public final void addTo$lottie_compose_release(@NotNull LottieDrawable lottieDrawable) {
        Intrinsics.checkNotNullParameter(lottieDrawable, "drawable");
        for (LottieDynamicProperty lottieDynamicProperty : this.intProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty.getKeyPath$lottie_compose_release(), lottieDynamicProperty.getProperty$lottie_compose_release(), LottieDynamicPropertiesKt.toValueCallback(lottieDynamicProperty.getCallback$lottie_compose_release()));
        }
        for (LottieDynamicProperty lottieDynamicProperty2 : this.pointFProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty2.getKeyPath$lottie_compose_release(), lottieDynamicProperty2.getProperty$lottie_compose_release(), LottieDynamicPropertiesKt.toValueCallback(lottieDynamicProperty2.getCallback$lottie_compose_release()));
        }
        for (LottieDynamicProperty lottieDynamicProperty3 : this.floatProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty3.getKeyPath$lottie_compose_release(), lottieDynamicProperty3.getProperty$lottie_compose_release(), LottieDynamicPropertiesKt.toValueCallback(lottieDynamicProperty3.getCallback$lottie_compose_release()));
        }
        for (LottieDynamicProperty lottieDynamicProperty4 : this.scaleProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty4.getKeyPath$lottie_compose_release(), lottieDynamicProperty4.getProperty$lottie_compose_release(), LottieDynamicPropertiesKt.toValueCallback(lottieDynamicProperty4.getCallback$lottie_compose_release()));
        }
        for (LottieDynamicProperty lottieDynamicProperty5 : this.colorFilterProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty5.getKeyPath$lottie_compose_release(), lottieDynamicProperty5.getProperty$lottie_compose_release(), LottieDynamicPropertiesKt.toValueCallback(lottieDynamicProperty5.getCallback$lottie_compose_release()));
        }
        for (LottieDynamicProperty lottieDynamicProperty6 : this.intArrayProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty6.getKeyPath$lottie_compose_release(), lottieDynamicProperty6.getProperty$lottie_compose_release(), LottieDynamicPropertiesKt.toValueCallback(lottieDynamicProperty6.getCallback$lottie_compose_release()));
        }
        for (LottieDynamicProperty lottieDynamicProperty7 : this.typefaceProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty7.getKeyPath$lottie_compose_release(), lottieDynamicProperty7.getProperty$lottie_compose_release(), LottieDynamicPropertiesKt.toValueCallback(lottieDynamicProperty7.getCallback$lottie_compose_release()));
        }
        for (LottieDynamicProperty lottieDynamicProperty8 : this.bitmapProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty8.getKeyPath$lottie_compose_release(), lottieDynamicProperty8.getProperty$lottie_compose_release(), LottieDynamicPropertiesKt.toValueCallback(lottieDynamicProperty8.getCallback$lottie_compose_release()));
        }
        for (LottieDynamicProperty lottieDynamicProperty9 : this.charSequenceProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty9.getKeyPath$lottie_compose_release(), lottieDynamicProperty9.getProperty$lottie_compose_release(), LottieDynamicPropertiesKt.toValueCallback(lottieDynamicProperty9.getCallback$lottie_compose_release()));
        }
    }

    public final void removeFrom$lottie_compose_release(@NotNull LottieDrawable lottieDrawable) {
        Intrinsics.checkNotNullParameter(lottieDrawable, "drawable");
        for (LottieDynamicProperty lottieDynamicProperty : this.intProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty.getKeyPath$lottie_compose_release(), lottieDynamicProperty.getProperty$lottie_compose_release(), (LottieValueCallback) null);
        }
        for (LottieDynamicProperty lottieDynamicProperty2 : this.pointFProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty2.getKeyPath$lottie_compose_release(), lottieDynamicProperty2.getProperty$lottie_compose_release(), (LottieValueCallback) null);
        }
        for (LottieDynamicProperty lottieDynamicProperty3 : this.floatProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty3.getKeyPath$lottie_compose_release(), lottieDynamicProperty3.getProperty$lottie_compose_release(), (LottieValueCallback) null);
        }
        for (LottieDynamicProperty lottieDynamicProperty4 : this.scaleProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty4.getKeyPath$lottie_compose_release(), lottieDynamicProperty4.getProperty$lottie_compose_release(), (LottieValueCallback) null);
        }
        for (LottieDynamicProperty lottieDynamicProperty5 : this.colorFilterProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty5.getKeyPath$lottie_compose_release(), lottieDynamicProperty5.getProperty$lottie_compose_release(), (LottieValueCallback) null);
        }
        for (LottieDynamicProperty lottieDynamicProperty6 : this.intArrayProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty6.getKeyPath$lottie_compose_release(), lottieDynamicProperty6.getProperty$lottie_compose_release(), (LottieValueCallback) null);
        }
        for (LottieDynamicProperty lottieDynamicProperty7 : this.typefaceProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty7.getKeyPath$lottie_compose_release(), lottieDynamicProperty7.getProperty$lottie_compose_release(), (LottieValueCallback) null);
        }
        for (LottieDynamicProperty lottieDynamicProperty8 : this.bitmapProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty8.getKeyPath$lottie_compose_release(), lottieDynamicProperty8.getProperty$lottie_compose_release(), (LottieValueCallback) null);
        }
        for (LottieDynamicProperty lottieDynamicProperty9 : this.charSequenceProperties) {
            lottieDrawable.addValueCallback(lottieDynamicProperty9.getKeyPath$lottie_compose_release(), lottieDynamicProperty9.getProperty$lottie_compose_release(), (LottieValueCallback) null);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LottieDynamicProperties(@org.jetbrains.annotations.NotNull java.util.List<? extends com.airbnb.lottie.compose.LottieDynamicProperty<?>> r12) {
        /*
            r11 = this;
            java.lang.String r0 = "properties"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r0 = r12.iterator()
        L_0x0010:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0029
            java.lang.Object r2 = r0.next()
            r3 = r2
            com.airbnb.lottie.compose.LottieDynamicProperty r3 = (com.airbnb.lottie.compose.LottieDynamicProperty) r3
            java.lang.Object r3 = r3.getProperty$lottie_compose_release()
            boolean r3 = r3 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x0010
            r1.add(r2)
            goto L_0x0010
        L_0x0029:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r0 = r12.iterator()
        L_0x0032:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x004b
            java.lang.Object r3 = r0.next()
            r4 = r3
            com.airbnb.lottie.compose.LottieDynamicProperty r4 = (com.airbnb.lottie.compose.LottieDynamicProperty) r4
            java.lang.Object r4 = r4.getProperty$lottie_compose_release()
            boolean r4 = r4 instanceof android.graphics.PointF
            if (r4 == 0) goto L_0x0032
            r2.add(r3)
            goto L_0x0032
        L_0x004b:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r0 = r12.iterator()
        L_0x0054:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x006d
            java.lang.Object r4 = r0.next()
            r5 = r4
            com.airbnb.lottie.compose.LottieDynamicProperty r5 = (com.airbnb.lottie.compose.LottieDynamicProperty) r5
            java.lang.Object r5 = r5.getProperty$lottie_compose_release()
            boolean r5 = r5 instanceof java.lang.Float
            if (r5 == 0) goto L_0x0054
            r3.add(r4)
            goto L_0x0054
        L_0x006d:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r0 = r12.iterator()
        L_0x0076:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x008f
            java.lang.Object r5 = r0.next()
            r6 = r5
            com.airbnb.lottie.compose.LottieDynamicProperty r6 = (com.airbnb.lottie.compose.LottieDynamicProperty) r6
            java.lang.Object r6 = r6.getProperty$lottie_compose_release()
            boolean r6 = r6 instanceof com.airbnb.lottie.value.ScaleXY
            if (r6 == 0) goto L_0x0076
            r4.add(r5)
            goto L_0x0076
        L_0x008f:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r0 = r12.iterator()
        L_0x0098:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L_0x00b1
            java.lang.Object r6 = r0.next()
            r7 = r6
            com.airbnb.lottie.compose.LottieDynamicProperty r7 = (com.airbnb.lottie.compose.LottieDynamicProperty) r7
            java.lang.Object r7 = r7.getProperty$lottie_compose_release()
            boolean r7 = r7 instanceof android.graphics.ColorFilter
            if (r7 == 0) goto L_0x0098
            r5.add(r6)
            goto L_0x0098
        L_0x00b1:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r0 = r12.iterator()
        L_0x00ba:
            boolean r7 = r0.hasNext()
            if (r7 == 0) goto L_0x00d3
            java.lang.Object r7 = r0.next()
            r8 = r7
            com.airbnb.lottie.compose.LottieDynamicProperty r8 = (com.airbnb.lottie.compose.LottieDynamicProperty) r8
            java.lang.Object r8 = r8.getProperty$lottie_compose_release()
            boolean r8 = r8 instanceof java.lang.Object[]
            if (r8 == 0) goto L_0x00ba
            r6.add(r7)
            goto L_0x00ba
        L_0x00d3:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r0 = r12.iterator()
        L_0x00dc:
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L_0x00f5
            java.lang.Object r8 = r0.next()
            r9 = r8
            com.airbnb.lottie.compose.LottieDynamicProperty r9 = (com.airbnb.lottie.compose.LottieDynamicProperty) r9
            java.lang.Object r9 = r9.getProperty$lottie_compose_release()
            boolean r9 = r9 instanceof android.graphics.Typeface
            if (r9 == 0) goto L_0x00dc
            r7.add(r8)
            goto L_0x00dc
        L_0x00f5:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Iterator r0 = r12.iterator()
        L_0x00fe:
            boolean r9 = r0.hasNext()
            if (r9 == 0) goto L_0x0117
            java.lang.Object r9 = r0.next()
            r10 = r9
            com.airbnb.lottie.compose.LottieDynamicProperty r10 = (com.airbnb.lottie.compose.LottieDynamicProperty) r10
            java.lang.Object r10 = r10.getProperty$lottie_compose_release()
            boolean r10 = r10 instanceof android.graphics.Bitmap
            if (r10 == 0) goto L_0x00fe
            r8.add(r9)
            goto L_0x00fe
        L_0x0117:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Iterator r12 = r12.iterator()
        L_0x0120:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x0139
            java.lang.Object r0 = r12.next()
            r10 = r0
            com.airbnb.lottie.compose.LottieDynamicProperty r10 = (com.airbnb.lottie.compose.LottieDynamicProperty) r10
            java.lang.Object r10 = r10.getProperty$lottie_compose_release()
            boolean r10 = r10 instanceof java.lang.CharSequence
            if (r10 == 0) goto L_0x0120
            r9.add(r0)
            goto L_0x0120
        L_0x0139:
            r0 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.LottieDynamicProperties.<init>(java.util.List):void");
    }
}
