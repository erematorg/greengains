package android.support.v4.media.session;

import android.net.Uri;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.ULong;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

public final /* synthetic */ class a {
    public static void A(DrawContext drawContext, long j2) {
        drawContext.getCanvas().restore();
        drawContext.m5192setSizeuvyYCjk(j2);
    }

    public static void B(Function2 function2, int i3, Composer composer, int i4) {
        composer.updateRememberedValue(Integer.valueOf(i3));
        composer.apply(Integer.valueOf(i4), function2);
    }

    public static float C(float f2, float f3, float f4, float f5) {
        return ((f2 * f3) + f4) * f5;
    }

    public static int D(int i3, int i4, int i5, int i6) {
        return i3 + i4 + i5 + i6;
    }

    public static double a(double d2, double d3, double d4) {
        return (Math.sin(d2) * d3) + d4;
    }

    public static float b(float f2, float f3, float f4, float f5) {
        return ((f2 - f3) * f4) + f5;
    }

    public static int c(float f2, int i3, int i4) {
        return (Float.hashCode(f2) + i3) * i4;
    }

    public static int d(int i3, int i4, int i5, int i6) {
        return ((i3 - i4) / i5) + i6;
    }

    public static int e(int i3, ArrayList arrayList, int i4, int i5) {
        arrayList.add(Integer.valueOf(i3));
        return i4 + i5;
    }

    public static int f(boolean z2, int i3, int i4) {
        return (Boolean.hashCode(z2) + i3) * i4;
    }

    public static long g(long j2, long j3) {
        return ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2) * j3);
    }

    public static Object h(List list, int i3) {
        return list.get(list.size() - i3);
    }

    public static String i(char c3, String str, StringBuilder sb) {
        sb.append(c3);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, str);
        return sb2;
    }

    public static String j(int i3, StringBuilder sb) {
        sb.append(Integer.toHexString(i3));
        return sb.toString();
    }

    public static String k(long j2, String str, StringBuilder sb) {
        sb.append(j2);
        sb.append(str);
        return sb.toString();
    }

    public static String l(String str, Uri uri) {
        return str + uri;
    }

    public static String m(String str, String str2) {
        return str + str2;
    }

    public static String n(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String o(StringBuilder sb, float f2, char c3) {
        sb.append(f2);
        sb.append(c3);
        return sb.toString();
    }

    public static String p(StringBuilder sb, int i3, char c3) {
        sb.append(i3);
        sb.append(c3);
        return sb.toString();
    }

    public static String q(StringBuilder sb, long j2, char c3) {
        sb.append(j2);
        sb.append(c3);
        return sb.toString();
    }

    public static String r(StringBuilder sb, String str, String str2, String str3, String str4) {
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        return sb.toString();
    }

    public static String s(StringBuilder sb, boolean z2, String str) {
        sb.append(z2);
        sb.append(str);
        return sb.toString();
    }

    public static StringBuilder t(CharSequence charSequence, String str, CharSequence charSequence2, String str2, CharSequence charSequence3) {
        Intrinsics.checkNotNullParameter(charSequence, str);
        Intrinsics.checkNotNullParameter(charSequence2, str2);
        StringBuilder sb = new StringBuilder();
        sb.append(charSequence3);
        return sb;
    }

    public static StringBuilder u(CharSequence charSequence, String str, Function1 function1, String str2, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence, str);
        Intrinsics.checkNotNullParameter(function1, str2);
        StringBuilder sb = new StringBuilder();
        sb.append(charSequence2);
        return sb;
    }

    public static StringBuilder v(CharSequence charSequence, String str, Function2 function2, String str2, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence, str);
        Intrinsics.checkNotNullParameter(function2, str2);
        StringBuilder sb = new StringBuilder();
        sb.append(charSequence2);
        return sb;
    }

    public static StringBuilder w(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static StringBuilder x(String str, String str2, String str3, int i3, String str4) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(i3);
        sb.append(str4);
        return sb;
    }

    public static Function2 y(ComposeUiNode.Companion companion, Composer composer, MeasurePolicy measurePolicy, Composer composer2, CompositionLocalMap compositionLocalMap) {
        Updater.m4017setimpl(composer, measurePolicy, companion.getSetMeasurePolicy());
        Updater.m4017setimpl(composer2, compositionLocalMap, companion.getSetResolvedCompositionLocals());
        return companion.getSetCompositeKeyHash();
    }

    public static void z(int i3, int i4, Function1 function1) {
        function1.invoke(Integer.valueOf(i3 + i4));
    }
}
