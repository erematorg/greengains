package com.ionspin.kotlin.bignum.integer.util;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.UStringsKt;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\"\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a!\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\n"}, d2 = {"hexColumsPrint", "", "", "Lkotlin/UByte;", "chunk", "", "([Lkotlin/UByte;I)V", "Lkotlin/UByteArray;", "hexColumsPrint-rto03Yo", "([BI)V", "bignum"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class VariousUtilKt {
    public static final void hexColumsPrint(@NotNull UByte[] uByteArr, int i3) {
        Intrinsics.checkNotNullParameter(uByteArr, "<this>");
        ArrayList arrayList = new ArrayList(uByteArr.length);
        for (UByte r3 : uByteArr) {
            arrayList.add(StringsKt__StringsKt.padStart(UStringsKt.m10287toStringLxnNnR4(r3.m9047unboximpl(), 16), 2, '0'));
        }
        for (List n2 : CollectionsKt.chunked(arrayList, i3)) {
            System.out.println(CollectionsKt___CollectionsKt.joinToString$default(n2, StringUtils.SPACE, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, VariousUtilKt$hexColumsPrint$1$1.INSTANCE, 30, (Object) null));
        }
    }

    public static /* synthetic */ void hexColumsPrint$default(UByte[] uByteArr, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i3 = 16;
        }
        hexColumsPrint(uByteArr, i3);
    }

    /* renamed from: hexColumsPrint-rto03Yo  reason: not valid java name */
    public static final void m8687hexColumsPrintrto03Yo(@NotNull byte[] bArr, int i3) {
        Intrinsics.checkNotNullParameter(bArr, "$this$hexColumsPrint");
        ArrayList arrayList = new ArrayList(UByteArray.m9056getSizeimpl(bArr));
        for (byte r3 : bArr) {
            arrayList.add(StringsKt__StringsKt.padStart(UStringsKt.m10287toStringLxnNnR4(r3, 16), 2, '0'));
        }
        for (List n2 : CollectionsKt.chunked(arrayList, i3)) {
            System.out.println(CollectionsKt___CollectionsKt.joinToString$default(n2, StringUtils.SPACE, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, VariousUtilKt$hexColumsPrint$2$1.INSTANCE, 30, (Object) null));
        }
    }

    /* renamed from: hexColumsPrint-rto03Yo$default  reason: not valid java name */
    public static /* synthetic */ void m8688hexColumsPrintrto03Yo$default(byte[] bArr, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i3 = 16;
        }
        m8687hexColumsPrintrto03Yo(bArr, i3);
    }
}
