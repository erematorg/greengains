package com.ionspin.kotlin.bignum.integer.base32;

import androidx.camera.core.impl.i;
import androidx.collection.SieveCacheKt;
import com.google.firebase.crashlytics.internal.a;
import com.ionspin.kotlin.bignum.Endianness;
import com.ionspin.kotlin.bignum.integer.BigInteger;
import com.ionspin.kotlin.bignum.integer.BigInteger32ArithmeticInterface;
import com.ionspin.kotlin.bignum.integer.Quadruple;
import com.ionspin.kotlin.bignum.integer.Sign;
import com.ionspin.kotlin.bignum.integer.util.DigitUtilKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.UCollectionsKt;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.comparisons.UComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.UStringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b%\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\bU\n\u0002\u0010 \n\u0002\b\u0018\bÀ\u0002\u0018\u00002\u00020\u0001:\u0002¯\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b/\u00100J%\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b4\u00100J)\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004062\u0006\u00102\u001a\u00020\u0004H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b7\u00108J/\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004062\u0006\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b<\u0010=J/\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004062\u0006\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b?\u0010=J&\u0010@\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bA\u00100J%\u0010B\u001a\u00020C2\u0006\u00102\u001a\u00020\u00042\u0006\u0010D\u001a\u00020EH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bF\u0010GJ\u001b\u0010H\u001a\u00020\"2\u0006\u0010I\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bJ\u0010KJ\u001d\u0010H\u001a\u00020\"2\u0006\u0010I\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bL\u0010MJ=\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"062\u0006\u00102\u001a\u00020\u00042\u0012\u0010O\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"06H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bP\u0010QJ%\u0010R\u001a\u00020\"2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bS\u0010TJ\u001b\u0010U\u001a\u00020\"2\u0006\u0010V\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bW\u0010MJ'\u0010X\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004062\u0006\u0010Y\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bZ\u00108J#\u0010[\u001a\u00020\u00042\u0006\u0010\\\u001a\u00020\u00042\u0006\u0010]\u001a\u00020\"ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b^\u0010_J1\u0010`\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004062\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\ba\u0010=J%\u0010b\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bc\u00100J+\u0010d\u001a\u00020\u00042\u0006\u0010e\u001a\u00020\u00042\u0006\u0010f\u001a\u00020\"2\u0006\u0010I\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bg\u0010hJ \u0010i\u001a\u00020\u00042\u0006\u0010j\u001a\u00020kH\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bl\u0010mJ\u001f\u0010n\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020o062\u0006\u0010p\u001a\u00020qH\u0016ø\u0001\u0000J \u0010r\u001a\u00020\u00042\u0006\u0010s\u001a\u00020\"H\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bt\u0010uJ \u0010v\u001a\u00020\u00042\u0006\u0010w\u001a\u00020EH\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bx\u0010yJ \u0010z\u001a\u00020\u00042\u0006\u0010{\u001a\u00020|H\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b}\u0010~J \u0010\u001a\u00020\u00042\b\u0010\u0001\u001a\u00030\u0001H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010mJ-\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020o062\u0007\u0010p\u001a\u00030\u0001H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J \u0010\u0001\u001a\u00020\u00042\u0007\u0010\u0001\u001a\u00020\u0016H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010uJ \u0010\u0001\u001a\u00020\u00042\u0007\u0010\u0001\u001a\u00020\u001bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010yJ!\u0010\u0001\u001a\u00020\u00042\b\u0010\u0001\u001a\u00030\u0001H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010~J'\u0010\u0001\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u00100J'\u0010\u0001\u001a\u00020\u00042\u0007\u0010\u0001\u001a\u00020\u00042\u0007\u0010\u0001\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u00100J&\u0010\u0001\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J%\u0010\u0001\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010_J'\u0010\u0001\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u00100J'\u0010\u0001\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u00100J)\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"062\u0006\u00102\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u00108J;\u0010\u0001\u001a\u0015\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"0 \u00012\u0007\u0010¡\u0001\u001a\u00020\u00042\u0007\u0010¢\u0001\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b£\u0001\u0010¤\u0001J \u0010¥\u0001\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b¦\u0001\u0010§\u0001J \u0010¨\u0001\u001a\u00020E2\u0006\u00102\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b©\u0001\u0010ª\u0001J\u001f\u0010«\u0001\u001a\u00020\"2\u0006\u0010I\u001a\u00020\u0016H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b¬\u0001\u0010KJ\u001d\u0010­\u0001\u001a\u00020\"2\u0006\u0010I\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b®\u0001\u0010KJ.\u0010¯\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020o062\u000e\u0010°\u0001\u001a\t\u0012\u0004\u0012\u00020k0±\u0001H\u0003ø\u0001\u0000¢\u0006\u0003\u0010²\u0001J!\u0010¯\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020o062\u0007\u0010°\u0001\u001a\u00020qH\u0003ø\u0001\u0000J9\u0010³\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020o062\u000f\u0010´\u0001\u001a\n\u0012\u0005\u0012\u00030\u00010±\u00012\b\u0010µ\u0001\u001a\u00030¶\u0001H\u0003ø\u0001\u0000¢\u0006\u0003\u0010·\u0001J8\u0010³\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020o062\b\u0010´\u0001\u001a\u00030\u00012\b\u0010µ\u0001\u001a\u00030¶\u0001H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b¸\u0001\u0010¹\u0001J0\u0010º\u0001\u001a\t\u0012\u0004\u0012\u00020k0±\u00012\u0006\u00102\u001a\u00020\u00042\u0007\u0010»\u0001\u001a\u00020oH\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b¼\u0001\u0010½\u0001J'\u0010¾\u0001\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b¿\u0001\u00100J-\u0010À\u0001\u001a\u00020\u00042\b\u0010Á\u0001\u001a\u00030Â\u00012\u0006\u0010\u0015\u001a\u00020\"H\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0006\bÃ\u0001\u0010Ä\u0001J)\u0010Å\u0001\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0007\u0010Æ\u0001\u001a\u00020EH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÇ\u0001\u0010È\u0001J-\u0010É\u0001\u001a\u00020\u00042\u0006\u0010e\u001a\u00020\u00042\u0006\u0010f\u001a\u00020\"2\u0006\u0010I\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÊ\u0001\u0010hJ*\u0010O\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004062\u0006\u00102\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bË\u0001\u00108J3\u0010Ì\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004062\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÍ\u0001\u0010=J*\u0010Î\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"062\u0006\u00102\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÏ\u0001\u0010Ð\u0001J\u001e\u0010Ñ\u0001\u001a\u00020\u00042\u0006\u0010V\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÒ\u0001\u0010§\u0001J+\u0010Ó\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004062\u0006\u00102\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÔ\u0001\u00108J1\u0010Õ\u0001\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u0010D\u001a\u00020E2\u0007\u0010Ö\u0001\u001a\u00020CH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b×\u0001\u0010Ø\u0001J(\u0010Ù\u0001\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0007\u0010Ú\u0001\u001a\u00020\"H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÛ\u0001\u0010_J(\u0010Ü\u0001\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0007\u0010Ú\u0001\u001a\u00020\"H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÝ\u0001\u0010_J\u0019\u0010Þ\u0001\u001a\u00020\t2\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0002J\u0019\u0010ß\u0001\u001a\u00020\t2\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0002J\u0019\u0010à\u0001\u001a\u00020\t2\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0002J\u0019\u0010á\u0001\u001a\u00020\t2\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0002J\u0019\u0010â\u0001\u001a\u00020\t2\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0002J+\u0010ã\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004062\u0006\u00102\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bä\u0001\u00108J \u0010å\u0001\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u0004H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bæ\u0001\u0010§\u0001J'\u0010ç\u0001\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bè\u0001\u00100J \u0010é\u0001\u001a\u00020q2\u0006\u00102\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bê\u0001\u0010ë\u0001J)\u0010ì\u0001\u001a\u00030Â\u00012\u0006\u00102\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\"H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bí\u0001\u0010î\u0001J!\u0010ï\u0001\u001a\u00030\u00012\u0006\u00102\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bð\u0001\u0010ë\u0001J2\u0010ñ\u0001\u001a\n\u0012\u0005\u0012\u00030\u00010±\u00012\u0006\u00102\u001a\u00020\u00042\b\u0010µ\u0001\u001a\u00030¶\u0001H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bò\u0001\u0010ó\u0001J+\u0010ô\u0001\u001a\u00030\u00012\u0006\u00102\u001a\u00020\u00042\b\u0010µ\u0001\u001a\u00030¶\u0001H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bõ\u0001\u0010ö\u0001J\u001e\u0010÷\u0001\u001a\u00020\u001b2\u0006\u00102\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bø\u0001\u0010ª\u0001J \u0010ù\u0001\u001a\u00030Â\u00012\u0007\u0010ú\u0001\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bû\u0001\u0010ü\u0001J'\u0010ý\u0001\u001a\u00020\u00042\u0007\u0010þ\u0001\u001a\u00020\u00042\u0007\u0010ÿ\u0001\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u00100J\u001d\u0010\u0002\u001a\u00020\"2\u0006\u0010I\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u0010KJ\u001f\u0010\u0002\u001a\u00020\"2\u0006\u0010I\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u0010MJ'\u0010\u0002\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u00100J$\u00101\u001a\u00020\t*\u00020\t2\u0006\u00102\u001a\u00020\u0004H\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0002\u0010\u0002J&\u0010\u0002\u001a\u00020\"*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0002\u0010\u0002J%\u0010\u0002\u001a\u00020\"*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u0010TJ\u001d\u0010\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010\u0002\u001a\u00020\tH\u0002¢\u0006\u0003\b\u0002J%\u0010\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u0010_J%\u0010\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u00100J1\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000406*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0004H\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u0010=J!\u0010\u0002\u001a\t\u0012\u0004\u0012\u00020k0±\u0001*\t\u0012\u0004\u0012\u00020k0±\u0001H\u0002¢\u0006\u0003\u0010\u0002J&\u0010\u0002\u001a\n\u0012\u0005\u0012\u00030\u00010±\u0001*\n\u0012\u0005\u0012\u00030\u00010±\u0001H\u0002ø\u0001\u0000¢\u0006\u0003\u0010\u0002J\u001e\u0010\u0002\u001a\u00030\u0001*\u00030\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0002\u0010\u0002J\u001b\u0010\u0002\u001a\t\u0012\u0004\u0012\u00020k0\u0002*\t\u0012\u0004\u0012\u00020k0\u0002H\u0002J\u001d\u0010\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010\u0002\u001a\u00020\tH\u0002¢\u0006\u0003\b\u0002J%\u0010\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u0010_J%\u0010\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u00100J\u001d\u0010\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010\u0002\u001a\u00020\tH\u0002¢\u0006\u0003\b\u0002J%\u0010\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u0010_J%\u0010\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b \u0002\u00100J\u001d\u0010¡\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010\u0002\u001a\u00020\tH\u0002¢\u0006\u0003\b¢\u0002J%\u0010¡\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b£\u0002\u0010_J%\u0010¡\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b¤\u0002\u00100J\u001d\u0010¥\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010Ú\u0001\u001a\u00020\"H\u0004¢\u0006\u0003\b¦\u0002J%\u0010¥\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010Ú\u0001\u001a\u00020\"H\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b§\u0002\u0010_J\u001d\u0010¨\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010Ú\u0001\u001a\u00020\"H\u0004¢\u0006\u0003\b©\u0002J%\u0010¨\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010Ú\u0001\u001a\u00020\"H\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bª\u0002\u0010_J\u001d\u0010«\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010\u0002\u001a\u00020\tH\u0002¢\u0006\u0003\b¬\u0002J%\u0010«\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b­\u0002\u0010_J%\u0010«\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b®\u0002\u00100R\u001f\u0010\u0003\u001a\u00020\u0004X\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001f\u0010\f\u001a\u00020\u0004X\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u001f\u0010\u000e\u001a\u00020\u0004X\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006R\u001f\u0010\u0010\u001a\u00020\u0004X\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u001f\u0010\u0015\u001a\u00020\u0016XDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u001f\u0010\u001a\u001a\u00020\u001bXDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u001f\u0010\u001f\u001a\u00020\u0016XDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b \u0010\u0018R\u0014\u0010!\u001a\u00020\"XD¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u000e\u0010$\u001a\u00020\"XT¢\u0006\u0002\n\u0000R\u001f\u0010%\u001a\u00020\u001bXDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b&\u0010\u001dR\u001f\u0010'\u001a\u00020\u001bXDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b(\u0010\u001dR\u000e\u0010)\u001a\u00020\"XT¢\u0006\u0002\n\u0000R\u0014\u0010*\u001a\u00020\"XD¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0018\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006°\u0002"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/base32/BigInteger32Arithmetic;", "Lcom/ionspin/kotlin/bignum/integer/BigInteger32ArithmeticInterface;", "()V", "ONE", "Lkotlin/UIntArray;", "getONE--hP7Qyg", "()[I", "[I", "SIGNED_POSITIVE_TWO", "Lcom/ionspin/kotlin/bignum/integer/base32/BigInteger32Arithmetic$SignedUIntArray;", "getSIGNED_POSITIVE_TWO", "()Lcom/ionspin/kotlin/bignum/integer/base32/BigInteger32Arithmetic$SignedUIntArray;", "TEN", "getTEN--hP7Qyg", "TWO", "getTWO--hP7Qyg", "ZERO", "getZERO--hP7Qyg", "_emitIntArray", "", "get_emitIntArray", "base", "Lkotlin/UInt;", "getBase-pVg5ArA", "()I", "I", "baseMask", "Lkotlin/ULong;", "getBaseMask-s-VKNKU", "()J", "J", "baseMaskInt", "getBaseMaskInt-pVg5ArA", "basePowerOfTwo", "", "getBasePowerOfTwo", "karatsubaThreshold", "lowerMask", "getLowerMask-s-VKNKU", "overflowMask", "getOverflowMask-s-VKNKU", "toomCookThreshold", "wordSizeInBits", "getWordSizeInBits", "add", "first", "second", "add-0-0sMy4", "([I[I)[I", "and", "operand", "mask", "and-0-0sMy4", "basecaseSqrt", "Lkotlin/Pair;", "basecaseSqrt--ajY-9A$bignum", "([I)Lkotlin/Pair;", "basicDivide", "unnormalizedDividend", "unnormalizedDivisor", "basicDivide-Ynv0uTE", "([I[I)Lkotlin/Pair;", "basicDivide2", "basicDivide2-Ynv0uTE", "binaryGcd", "binaryGcd-0-0sMy4", "bitAt", "", "position", "", "bitAt-LpG4sQ0", "([IJ)Z", "bitLength", "value", "bitLength-WZ4Q5Ns", "(I)I", "bitLength--ajY-9A", "([I)I", "checkReciprocal", "reciprocal", "checkReciprocal-LpG4sQ0", "([ILkotlin/Pair;)Lkotlin/Pair;", "compare", "compare-Ynv0uTE", "([I[I)I", "countLeadingZeroWords", "bigInteger", "countLeadingZeroWords--ajY-9A", "d1ReciprocalRecursiveWordVersion", "a", "d1ReciprocalRecursiveWordVersion--ajY-9A", "denormalize", "remainderNormalized", "normalizationShift", "denormalize-Wj2uyrI", "([II)[I", "divide", "divide-Ynv0uTE", "euclideanGcd", "euclideanGcd-0-0sMy4", "extendUIntArray", "original", "numberOfWords", "extendUIntArray-9fY048w", "([III)[I", "fromByte", "byte", "", "fromByte-g_c56RQ", "(B)[I", "fromByteArray", "Lcom/ionspin/kotlin/bignum/integer/Sign;", "source", "", "fromInt", "int", "fromInt-g_c56RQ", "(I)[I", "fromLong", "long", "fromLong-g_c56RQ", "(J)[I", "fromShort", "short", "", "fromShort-g_c56RQ", "(S)[I", "fromUByte", "uByte", "Lkotlin/UByte;", "fromUByte-W6sApTE", "fromUByteArray", "Lkotlin/UByteArray;", "fromUByteArray-GBYM_sE", "([B)Lkotlin/Pair;", "fromUInt", "uInt", "fromUInt-Ezf8eIQ", "fromULong", "uLong", "fromULong-owt3UmA", "fromUShort", "uShort", "Lkotlin/UShort;", "fromUShort-y3OBVxU", "gcd", "gcd-0-0sMy4", "karatsubaMultiply", "firstUnsigned", "secondUnsigned", "karatsubaMultiply-0-0sMy4", "multiply", "multiply-FwZOn3I", "(II)[I", "multiply-FE_7wA8", "multiply-0-0sMy4", "multiplyNoKaratsuba", "multiplyNoKaratsuba-0-0sMy4$bignum", "normalize", "normalize--ajY-9A", "Lkotlin/Triple;", "dividend", "divisor", "normalize-Ynv0uTE", "([I[I)Lkotlin/Triple;", "not", "not-hkIa6DI", "([I)[I", "numberOfDecimalDigits", "numberOfDecimalDigits--ajY-9A", "([I)J", "numberOfLeadingZerosInAWord", "numberOfLeadingZerosInAWord-WZ4Q5Ns", "numberOfTrailingZerosInAWord", "numberOfTrailingZerosInAWord-WZ4Q5Ns", "oldFromByteArray", "byteArray", "", "([Ljava/lang/Byte;)Lkotlin/Pair;", "oldFromUByteArray", "uByteArray", "endianness", "Lcom/ionspin/kotlin/bignum/Endianness;", "([Lkotlin/UByte;Lcom/ionspin/kotlin/bignum/Endianness;)Lkotlin/Pair;", "oldFromUByteArray-rto03Yo", "([BLcom/ionspin/kotlin/bignum/Endianness;)Lkotlin/Pair;", "oldToByteArray", "sign", "oldToByteArray-LpG4sQ0", "([ILcom/ionspin/kotlin/bignum/integer/Sign;)[Ljava/lang/Byte;", "or", "or-0-0sMy4", "parseForBase", "number", "", "parseForBase-g-PCqec", "(Ljava/lang/String;I)[I", "pow", "exponent", "pow-Wj2uyrI", "([IJ)[I", "prependULongArray", "prependULongArray-9fY048w", "reciprocal--ajY-9A", "reciprocalDivision", "reciprocalDivision-Ynv0uTE$bignum", "reciprocalSingleWord", "reciprocalSingleWord-WZ4Q5Ns", "(I)Lkotlin/Pair;", "removeLeadingZeros", "removeLeadingZeros-hkIa6DI", "reqursiveSqrt", "reqursiveSqrt--ajY-9A", "setBitAt", "bit", "setBitAt-WiAKJ7k", "([IJZ)[I", "shiftLeft", "places", "shiftLeft-Wj2uyrI", "shiftRight", "shiftRight-Wj2uyrI", "signedAdd", "signedDivide", "signedMultiply", "signedRemainder", "signedSubtract", "sqrt", "sqrt--ajY-9A", "sqrtInt", "sqrtInt-hkIa6DI$bignum", "subtract", "subtract-0-0sMy4", "toByteArray", "toByteArray--ajY-9A", "([I)[B", "toString", "toString-LpG4sQ0", "([II)Ljava/lang/String;", "toUByteArray", "toUByteArray-CMMTdXw", "toUIntArrayRepresentedAsTypedUByteArray", "toUIntArrayRepresentedAsTypedUByteArray-LpG4sQ0", "([ILcom/ionspin/kotlin/bignum/Endianness;)[Lkotlin/UByte;", "toUIntArrayRepresentedAsUByteArray", "toUIntArrayRepresentedAsUByteArray-1NjfPbc", "([ILcom/ionspin/kotlin/bignum/Endianness;)[B", "toULongExact", "toULongExact-q22ZNjw", "toUnsignedIntArrayCodeFormat", "array", "toUnsignedIntArrayCodeFormat--ajY-9A", "([I)Ljava/lang/String;", "toomCook3Multiply", "firstUnchecked", "secondUnchecked", "toomCook3Multiply-0-0sMy4", "trailingZeroBits", "trailingZeroBits-WZ4Q5Ns", "trailingZeroBits--ajY-9A", "xor", "xor-0-0sMy4", "and-wZx4R44$bignum", "(Lcom/ionspin/kotlin/bignum/integer/base32/BigInteger32Arithmetic$SignedUIntArray;[I)Lcom/ionspin/kotlin/bignum/integer/base32/BigInteger32Arithmetic$SignedUIntArray;", "compareTo", "other", "compareTo-RLbJYCw$bignum", "([II)I", "compareTo-Ynv0uTE$bignum", "div", "div$bignum", "div-FE_7wA8$bignum", "div-0-0sMy4$bignum", "divrem", "divrem-Ynv0uTE$bignum", "dropLeadingZeros", "([Ljava/lang/Byte;)[Ljava/lang/Byte;", "([Lkotlin/UByte;)[Lkotlin/UByte;", "dropLeadingZeros-IyW4Rww", "([B)[B", "", "minus", "minus$bignum", "minus-FE_7wA8$bignum", "minus-0-0sMy4$bignum", "plus", "plus$bignum", "plus-FE_7wA8$bignum", "plus-0-0sMy4$bignum", "rem", "rem$bignum", "rem-FE_7wA8$bignum", "rem-0-0sMy4$bignum", "shl", "shl$bignum", "shl-Wj2uyrI$bignum", "shr", "shr$bignum", "shr-Wj2uyrI$bignum", "times", "times$bignum", "times-FE_7wA8$bignum", "times-0-0sMy4$bignum", "SignedUIntArray", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BigInteger32Arithmetic implements BigInteger32ArithmeticInterface {
    @NotNull
    public static final BigInteger32Arithmetic INSTANCE;
    @NotNull
    private static final int[] ONE = UIntArray.m9129constructorimpl(new int[]{1});
    @NotNull
    private static final SignedUIntArray SIGNED_POSITIVE_TWO;
    @NotNull
    private static final int[] TEN = UIntArray.m9129constructorimpl(new int[]{10});
    @NotNull
    private static final int[] TWO = UIntArray.m9129constructorimpl(new int[]{2});
    @NotNull
    private static final int[] ZERO = UIntArray.m9128constructorimpl(0);
    @NotNull
    private static final int[] _emitIntArray = new int[0];
    private static final int base = -1;
    private static final long baseMask = 4294967295L;
    private static final int baseMaskInt = -1;
    private static final int basePowerOfTwo = 32;
    public static final int karatsubaThreshold = 60;
    private static final long lowerMask = 65535;
    private static final long overflowMask = 4294967296L;
    public static final int toomCookThreshold = 15000;
    private static final int wordSizeInBits = 32;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0018\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u0019\u0010\f\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\r\u0010\nJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J*\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/base32/BigInteger32Arithmetic$SignedUIntArray;", "", "unsignedValue", "Lkotlin/UIntArray;", "sign", "", "([IZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSign", "()Z", "getUnsignedValue--hP7Qyg", "()[I", "[I", "component1", "component1--hP7Qyg", "component2", "copy", "copy-LpG4sQ0", "([IZ)Lcom/ionspin/kotlin/bignum/integer/base32/BigInteger32Arithmetic$SignedUIntArray;", "equals", "other", "hashCode", "", "toString", "", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class SignedUIntArray {
        private final boolean sign;
        @NotNull
        private final int[] unsignedValue;

        public /* synthetic */ SignedUIntArray(int[] iArr, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
            this(iArr, z2);
        }

        /* renamed from: copy-LpG4sQ0$default  reason: not valid java name */
        public static /* synthetic */ SignedUIntArray m8522copyLpG4sQ0$default(SignedUIntArray signedUIntArray, int[] iArr, boolean z2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                iArr = signedUIntArray.unsignedValue;
            }
            if ((i3 & 2) != 0) {
                z2 = signedUIntArray.sign;
            }
            return signedUIntArray.m8524copyLpG4sQ0(iArr, z2);
        }

        @NotNull
        /* renamed from: component1--hP7Qyg  reason: not valid java name */
        public final int[] m8523component1hP7Qyg() {
            return this.unsignedValue;
        }

        public final boolean component2() {
            return this.sign;
        }

        @NotNull
        /* renamed from: copy-LpG4sQ0  reason: not valid java name */
        public final SignedUIntArray m8524copyLpG4sQ0(@NotNull int[] iArr, boolean z2) {
            Intrinsics.checkNotNullParameter(iArr, "unsignedValue");
            return new SignedUIntArray(iArr, z2, (DefaultConstructorMarker) null);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SignedUIntArray)) {
                return false;
            }
            SignedUIntArray signedUIntArray = (SignedUIntArray) obj;
            return UIntArray.m9133equalsimpl0(this.unsignedValue, signedUIntArray.unsignedValue) && this.sign == signedUIntArray.sign;
        }

        public final boolean getSign() {
            return this.sign;
        }

        @NotNull
        /* renamed from: getUnsignedValue--hP7Qyg  reason: not valid java name */
        public final int[] m8525getUnsignedValuehP7Qyg() {
            return this.unsignedValue;
        }

        public int hashCode() {
            int r02 = UIntArray.m9136hashCodeimpl(this.unsignedValue) * 31;
            boolean z2 = this.sign;
            if (z2) {
                z2 = true;
            }
            return r02 + (z2 ? 1 : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder("SignedUIntArray(unsignedValue=");
            sb.append(UIntArray.m9140toStringimpl(this.unsignedValue));
            sb.append(", sign=");
            return i.c(sb, this.sign, ')');
        }

        private SignedUIntArray(int[] iArr, boolean z2) {
            this.unsignedValue = iArr;
            this.sign = z2;
        }
    }

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Sign.values().length];
            iArr[Sign.ZERO.ordinal()] = 1;
            iArr[Sign.POSITIVE.ordinal()] = 2;
            iArr[Sign.NEGATIVE.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Endianness.values().length];
            iArr2[Endianness.BIG.ordinal()] = 1;
            iArr2[Endianness.LITTLE.ordinal()] = 2;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static {
        BigInteger32Arithmetic bigInteger32Arithmetic = new BigInteger32Arithmetic();
        INSTANCE = bigInteger32Arithmetic;
        SIGNED_POSITIVE_TWO = new SignedUIntArray(bigInteger32Arithmetic.m8474getTWOhP7Qyg(), true, (DefaultConstructorMarker) null);
    }

    private BigInteger32Arithmetic() {
    }

    /* renamed from: binaryGcd-0-0sMy4  reason: not valid java name */
    private final int[] m8430binaryGcd00sMy4(int[] iArr, int[] iArr2) {
        while (!UArraysKt.m9544contentEqualsKJPZfPQ(iArr, iArr2)) {
            if (UArraysKt.m9544contentEqualsKJPZfPQ(iArr, m8475getZEROhP7Qyg())) {
                return iArr2;
            }
            if (UArraysKt.m9544contentEqualsKJPZfPQ(iArr2, m8475getZEROhP7Qyg())) {
                return iArr;
            }
            if (UArraysKt.m9544contentEqualsKJPZfPQ(m8438and00sMy4(iArr, m8471getONEhP7Qyg()), m8475getZEROhP7Qyg())) {
                if (UArraysKt.m9544contentEqualsKJPZfPQ(m8438and00sMy4(iArr2, m8471getONEhP7Qyg()), m8475getZEROhP7Qyg())) {
                    return m8504shlWj2uyrI$bignum(m8430binaryGcd00sMy4(m8505shrWj2uyrI$bignum(iArr, 1), m8505shrWj2uyrI$bignum(iArr2, 1)), 1);
                }
                iArr = m8505shrWj2uyrI$bignum(iArr, 1);
            } else if (UArraysKt.m9544contentEqualsKJPZfPQ(m8438and00sMy4(iArr2, m8471getONEhP7Qyg()), m8475getZEROhP7Qyg())) {
                iArr2 = m8505shrWj2uyrI$bignum(iArr2, 1);
            } else if (m8446compareYnv0uTE(iArr, iArr2) == 1) {
                iArr = m8505shrWj2uyrI$bignum(m8508subtract00sMy4(iArr, iArr2), 1);
            } else {
                int[] r3 = m8505shrWj2uyrI$bignum(m8508subtract00sMy4(iArr2, iArr), 1);
                iArr2 = iArr;
                iArr = r3;
            }
        }
        return iArr;
    }

    /* renamed from: checkReciprocal-LpG4sQ0  reason: not valid java name */
    private final Pair<UIntArray, Integer> m8431checkReciprocalLpG4sQ0(int[] iArr, Pair<UIntArray, Integer> pair) {
        return !UIntArray.m9133equalsimpl0(m8505shrWj2uyrI$bignum(m8509times00sMy4$bignum(iArr, pair.getFirst().m9143unboximpl()), pair.getSecond().intValue()), m8471getONEhP7Qyg()) ? new Pair<>(pair.getFirst(), Integer.valueOf(pair.getSecond().intValue() - 1)) : new Pair<>(pair.getFirst(), pair.getSecond());
    }

    private final List<Byte> dropLeadingZeros(List<Byte> list) {
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (Object next : list) {
            if (z2) {
                arrayList.add(next);
            } else if (((Number) next).byteValue() != 0) {
                arrayList.add(next);
                z2 = true;
            }
        }
        return arrayList;
    }

    /* renamed from: dropLeadingZeros-IyW4Rww  reason: not valid java name */
    private final byte[] m8432dropLeadingZerosIyW4Rww(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (byte b3 : bArr) {
            if (z2) {
                arrayList.add(UByte.m8991boximpl(b3));
            } else if (b3 != UByte.m8997constructorimpl((byte) 0)) {
                arrayList.add(UByte.m8991boximpl(b3));
                z2 = true;
            }
        }
        return UCollectionsKt.toUByteArray(arrayList);
    }

    /* renamed from: euclideanGcd-0-0sMy4  reason: not valid java name */
    private final int[] m8433euclideanGcd00sMy4(int[] iArr, int[] iArr2) {
        while (true) {
            int[] iArr3 = iArr2;
            int[] iArr4 = iArr;
            iArr = iArr3;
            if (UIntArray.m9133equalsimpl0(iArr, m8475getZEROhP7Qyg())) {
                return iArr4;
            }
            iArr2 = m8498rem00sMy4$bignum(iArr4, iArr);
        }
    }

    @Deprecated(message = "Old byte conversion API")
    private final Pair<UIntArray, Sign> oldFromByteArray(Byte[] bArr) {
        Sign sign;
        List<UInt> emptyList;
        int byteValue = (bArr[0].byteValue() >>> 7) & 1;
        List<List> chunked = CollectionsKt.chunked(CollectionsKt.reversed(ArraysKt.toList((T[]) bArr)), 4);
        if (byteValue == 0) {
            sign = Sign.POSITIVE;
        } else if (byteValue == 1) {
            sign = Sign.NEGATIVE;
        } else {
            throw new RuntimeException("Invalid sign value when converting from byte array");
        }
        int i3 = WhenMappings.$EnumSwitchMapping$0[sign.ordinal()];
        if (i3 == 1) {
            throw new RuntimeException("Bug in fromByteArray, sign shouldn't ever be zero at this point.");
        } else if (i3 == 2) {
            ArrayList arrayList = new ArrayList();
            for (List list : chunked) {
                int i4 = 0;
                int i5 = 0;
                for (Object next : CollectionsKt.reversed(list)) {
                    int i6 = i5 + 1;
                    if (i5 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    i4 = a.a(UInt.m9074constructorimpl(UInt.m9074constructorimpl(((Number) next).byteValue()) & 255) << (((list.size() - 1) * 8) - (i5 * 8)), i4);
                    i5 = i6;
                }
                int size = (4 - list.size()) * 8;
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, UIntArray.m9127boximpl(new int[]{UInt.m9074constructorimpl(UInt.m9074constructorimpl(i4 << size) >>> size)}));
            }
            int[] uIntArray = UCollectionsKt.toUIntArray(arrayList);
            if (UArraysKt.m9544contentEqualsKJPZfPQ(uIntArray, m8475getZEROhP7Qyg())) {
                return new Pair<>(UIntArray.m9127boximpl(m8475getZEROhP7Qyg()), Sign.ZERO);
            }
            int lastIndex = ArraysKt.getLastIndex(uIntArray);
            while (true) {
                if (lastIndex >= 0) {
                    if (UIntArray.m9134getpVg5ArA(uIntArray, lastIndex) != 0) {
                        emptyList = UArraysKt.m10109takeqFRl0hI(uIntArray, lastIndex + 1);
                        break;
                    }
                    lastIndex--;
                } else {
                    emptyList = CollectionsKt.emptyList();
                    break;
                }
            }
            return new Pair<>(UIntArray.m9127boximpl(m8500removeLeadingZeroshkIa6DI(UCollectionsKt.toUIntArray(emptyList))), sign);
        } else if (i3 == 3) {
            ArrayList arrayList2 = new ArrayList();
            for (List list2 : chunked) {
                int i7 = 0;
                int i8 = 0;
                for (Object next2 : CollectionsKt.reversed(list2)) {
                    int i9 = i8 + 1;
                    if (i8 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    i7 = a.a(UInt.m9074constructorimpl(((Number) next2).byteValue()) << (((list2.size() - 1) * 8) - (i8 * 8)), i7);
                    i8 = i9;
                }
                CollectionsKt__MutableCollectionsKt.addAll(arrayList2, UIntArray.m9127boximpl(new int[]{i7}));
            }
            int[] uIntArray2 = UCollectionsKt.toUIntArray(arrayList2);
            int[] r2 = m8478minusFE_7wA8$bignum(uIntArray2, 1);
            ArrayList arrayList3 = new ArrayList(UIntArray.m9135getSizeimpl(r2));
            for (int i10 : r2) {
                arrayList3.add(UInt.m9068boximpl(UInt.m9074constructorimpl(~i10)));
            }
            int[] uIntArray3 = UCollectionsKt.toUIntArray(arrayList3);
            if (UArraysKt.m9544contentEqualsKJPZfPQ(uIntArray2, m8475getZEROhP7Qyg())) {
                return new Pair<>(UIntArray.m9127boximpl(m8475getZEROhP7Qyg()), Sign.ZERO);
            }
            return new Pair<>(UIntArray.m9127boximpl(m8500removeLeadingZeroshkIa6DI(uIntArray3)), sign);
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    @Deprecated(message = "Old byte conversion API")
    private final Pair<UIntArray, Sign> oldFromUByteArray(UByte[] uByteArr, Endianness endianness) {
        List<List> list;
        List<UInt> emptyList;
        int i3 = WhenMappings.$EnumSwitchMapping$1[endianness.ordinal()];
        if (i3 == 1) {
            list = CollectionsKt.chunked(CollectionsKt.reversed(ArraysKt.toList((T[]) uByteArr)), 4);
        } else if (i3 == 2) {
            list = CollectionsKt.chunked(ArraysKt.toList((T[]) uByteArr), 4);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Sign sign = Sign.POSITIVE;
        ArrayList arrayList = new ArrayList();
        for (List list2 : list) {
            int i4 = 0;
            int i5 = 0;
            for (Object next : CollectionsKt.reversed(list2)) {
                int i6 = i5 + 1;
                if (i5 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                i4 = a.a(UInt.m9074constructorimpl(UInt.m9074constructorimpl(((UByte) next).m9047unboximpl() & 255) & 255) << (((list2.size() - 1) * 8) - (i5 * 8)), i4);
                i5 = i6;
            }
            int size = (4 - list2.size()) * 8;
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, UIntArray.m9127boximpl(new int[]{UInt.m9074constructorimpl(UInt.m9074constructorimpl(i4 << size) >>> size)}));
        }
        int[] uIntArray = UCollectionsKt.toUIntArray(arrayList);
        if (UArraysKt.m9544contentEqualsKJPZfPQ(uIntArray, m8475getZEROhP7Qyg())) {
            return new Pair<>(UIntArray.m9127boximpl(m8475getZEROhP7Qyg()), Sign.ZERO);
        }
        int lastIndex = ArraysKt.getLastIndex(uIntArray);
        while (true) {
            if (lastIndex >= 0) {
                if (UIntArray.m9134getpVg5ArA(uIntArray, lastIndex) != 0) {
                    emptyList = UArraysKt.m10109takeqFRl0hI(uIntArray, lastIndex + 1);
                    break;
                }
                lastIndex--;
            } else {
                emptyList = CollectionsKt.emptyList();
                break;
            }
        }
        return new Pair<>(UIntArray.m9127boximpl(m8500removeLeadingZeroshkIa6DI(UCollectionsKt.toUIntArray(emptyList))), sign);
    }

    @Deprecated(message = "Old byte conversion API")
    /* renamed from: oldFromUByteArray-rto03Yo  reason: not valid java name */
    private final Pair<UIntArray, Sign> m8434oldFromUByteArrayrto03Yo(byte[] bArr, Endianness endianness) {
        List<List> list;
        List<UInt> emptyList;
        int i3 = WhenMappings.$EnumSwitchMapping$1[endianness.ordinal()];
        if (i3 == 1) {
            list = CollectionsKt.chunked(CollectionsKt.reversed(CollectionsKt.toList(UByteArray.m9048boximpl(bArr))), 4);
        } else if (i3 == 2) {
            list = CollectionsKt.chunked(CollectionsKt.toList(UByteArray.m9048boximpl(bArr)), 4);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Sign sign = Sign.POSITIVE;
        ArrayList arrayList = new ArrayList();
        for (List list2 : list) {
            int i4 = 0;
            int i5 = 0;
            for (Object next : CollectionsKt.reversed(list2)) {
                int i6 = i5 + 1;
                if (i5 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                i4 = a.a(UInt.m9074constructorimpl(UInt.m9074constructorimpl(((UByte) next).m9047unboximpl() & 255) & 255) << (((list2.size() - 1) * 8) - (i5 * 8)), i4);
                i5 = i6;
            }
            int size = (4 - list2.size()) * 8;
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, UIntArray.m9127boximpl(new int[]{UInt.m9074constructorimpl(UInt.m9074constructorimpl(i4 << size) >>> size)}));
        }
        int[] uIntArray = UCollectionsKt.toUIntArray(arrayList);
        if (UArraysKt.m9544contentEqualsKJPZfPQ(uIntArray, m8475getZEROhP7Qyg())) {
            return new Pair<>(UIntArray.m9127boximpl(m8475getZEROhP7Qyg()), Sign.ZERO);
        }
        int lastIndex = ArraysKt.getLastIndex(uIntArray);
        while (true) {
            if (lastIndex >= 0) {
                if (UIntArray.m9134getpVg5ArA(uIntArray, lastIndex) != 0) {
                    emptyList = UArraysKt.m10109takeqFRl0hI(uIntArray, lastIndex + 1);
                    break;
                }
                lastIndex--;
            } else {
                emptyList = CollectionsKt.emptyList();
                break;
            }
        }
        return new Pair<>(UIntArray.m9127boximpl(m8500removeLeadingZeroshkIa6DI(UCollectionsKt.toUIntArray(emptyList))), sign);
    }

    @Deprecated(message = "Old byte conversion API")
    /* renamed from: oldToByteArray-LpG4sQ0  reason: not valid java name */
    private final Byte[] m8435oldToByteArrayLpG4sQ0(int[] iArr, Sign sign) {
        List list;
        if (UIntArray.m9137isEmptyimpl(iArr)) {
            return new Byte[0];
        }
        byte[] copyOf = Arrays.copyOf(new byte[]{1}, 1);
        Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
        UByteArray.m9050constructorimpl(copyOf);
        int r12 = m8444bitLengthajY9A(iArr);
        int i3 = WhenMappings.$EnumSwitchMapping$0[sign.ordinal()];
        if (i3 == 1) {
            list = CollectionsKt.emptyList();
        } else if (i3 == 2) {
            ArrayList arrayList = new ArrayList();
            for (int i4 : iArr) {
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, CollectionsKt.listOf(Byte.valueOf((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(i4 >>> 24) & 255)), Byte.valueOf((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(i4 >>> 16) & 255)), Byte.valueOf((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(i4 >>> 8) & 255)), Byte.valueOf((byte) UInt.m9074constructorimpl(i4 & 255))));
            }
            list = CollectionsKt.flatten(CollectionsKt.reversed(CollectionsKt.chunked(CollectionsKt.takeLast(arrayList, (UIntArray.m9135getSizeimpl(iArr) * 4) + 1), 4)));
            if (r12 % 8 == 0) {
                list = CollectionsKt.plus(CollectionsKt.listOf((byte) 0), list);
            }
        } else if (i3 == 3) {
            ArrayList arrayList2 = new ArrayList(UIntArray.m9135getSizeimpl(iArr));
            for (int i5 : iArr) {
                arrayList2.add(UInt.m9068boximpl(UInt.m9074constructorimpl(~i5)));
            }
            int[] r10 = m8492plusFE_7wA8$bignum(UCollectionsKt.toUIntArray(arrayList2), 1);
            ArrayList arrayList3 = new ArrayList();
            for (int i6 : r10) {
                CollectionsKt__MutableCollectionsKt.addAll(arrayList3, CollectionsKt.listOf(Byte.valueOf((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(i6 >>> 24) & 255)), Byte.valueOf((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(i6 >>> 16) & 255)), Byte.valueOf((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(i6 >>> 8) & 255)), Byte.valueOf((byte) UInt.m9074constructorimpl(i6 & 255))));
            }
            list = CollectionsKt.flatten(CollectionsKt.reversed(CollectionsKt.chunked(CollectionsKt.takeLast(arrayList3, (UIntArray.m9135getSizeimpl(iArr) * 4) + 1), 4)));
            if (r12 % 8 == 0) {
                list = CollectionsKt.plus(CollectionsKt.listOf((byte) -1), list);
            }
            ArrayList arrayList4 = new ArrayList();
            for (Object next : list) {
                if (((Number) next).byteValue() != -1) {
                    break;
                }
                arrayList4.add(next);
            }
            int size = arrayList4.size();
            if (size > 1) {
                list = list.subList(size - 1, list.size());
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Object[] array = list.toArray(new Byte[0]);
        if (array != null) {
            return (Byte[]) array;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* renamed from: reqursiveSqrt--ajY-9A  reason: not valid java name */
    private final Pair<UIntArray, UIntArray> m8436reqursiveSqrtajY9A(int[] iArr) {
        int r02 = UIntArray.m9135getSizeimpl(iArr);
        int floor = (int) Math.floor(((double) (r02 - 1)) / ((double) 4));
        if (floor == 0) {
            return m8440basecaseSqrtajY9A$bignum(iArr);
        }
        int i3 = r02 / 4;
        int i4 = r02 % 4;
        int i5 = floor * 32;
        int i6 = r02 - ((i3 * 3) + i4);
        int i7 = r02 - ((i3 * 2) + i4);
        int[] r3 = UIntArray.m9129constructorimpl(ArraysKt.copyOfRange(iArr, i6, i7));
        int[] r4 = UIntArray.m9129constructorimpl(ArraysKt.copyOfRange(iArr, 0, i6));
        Pair<UIntArray, UIntArray> r7 = m8436reqursiveSqrtajY9A(UIntArray.m9129constructorimpl(ArraysKt.copyOfRange(iArr, i7, r02)));
        int[] r03 = r7.component1().m9143unboximpl();
        Pair<UIntArray, UIntArray> r72 = m8442basicDivide2Ynv0uTE(m8491plus00sMy4$bignum(m8504shlWj2uyrI$bignum(r7.component2().m9143unboximpl(), i5), r3), m8504shlWj2uyrI$bignum(r03, 1));
        int[] r2 = r72.component1().m9143unboximpl();
        int[] r73 = r72.component2().m9143unboximpl();
        return new Pair<>(UIntArray.m9127boximpl(m8491plus00sMy4$bignum(m8504shlWj2uyrI$bignum(r03, i5), r2)), UIntArray.m9127boximpl(m8477minus00sMy4$bignum(m8491plus00sMy4$bignum(m8504shlWj2uyrI$bignum(r73, i5), r4), m8509times00sMy4$bignum(r2, r2))));
    }

    private final SignedUIntArray signedAdd(SignedUIntArray signedUIntArray, SignedUIntArray signedUIntArray2) {
        return signedUIntArray.getSign() ^ signedUIntArray2.getSign() ? m8448compareToYnv0uTE$bignum(signedUIntArray.m8525getUnsignedValuehP7Qyg(), signedUIntArray2.m8525getUnsignedValuehP7Qyg()) > 0 ? new SignedUIntArray(m8477minus00sMy4$bignum(signedUIntArray.m8525getUnsignedValuehP7Qyg(), signedUIntArray2.m8525getUnsignedValuehP7Qyg()), signedUIntArray.getSign(), (DefaultConstructorMarker) null) : new SignedUIntArray(m8477minus00sMy4$bignum(signedUIntArray2.m8525getUnsignedValuehP7Qyg(), signedUIntArray.m8525getUnsignedValuehP7Qyg()), signedUIntArray2.getSign(), (DefaultConstructorMarker) null) : new SignedUIntArray(m8491plus00sMy4$bignum(signedUIntArray.m8525getUnsignedValuehP7Qyg(), signedUIntArray2.m8525getUnsignedValuehP7Qyg()), signedUIntArray.getSign(), (DefaultConstructorMarker) null);
    }

    private final SignedUIntArray signedDivide(SignedUIntArray signedUIntArray, SignedUIntArray signedUIntArray2) {
        return new SignedUIntArray(m8452div00sMy4$bignum(signedUIntArray.m8525getUnsignedValuehP7Qyg(), signedUIntArray2.m8525getUnsignedValuehP7Qyg()), !(signedUIntArray.getSign() ^ signedUIntArray2.getSign()), (DefaultConstructorMarker) null);
    }

    private final SignedUIntArray signedMultiply(SignedUIntArray signedUIntArray, SignedUIntArray signedUIntArray2) {
        return new SignedUIntArray(m8509times00sMy4$bignum(signedUIntArray.m8525getUnsignedValuehP7Qyg(), signedUIntArray2.m8525getUnsignedValuehP7Qyg()), !(signedUIntArray.getSign() ^ signedUIntArray2.getSign()), (DefaultConstructorMarker) null);
    }

    private final SignedUIntArray signedRemainder(SignedUIntArray signedUIntArray, SignedUIntArray signedUIntArray2) {
        return new SignedUIntArray(m8498rem00sMy4$bignum(signedUIntArray.m8525getUnsignedValuehP7Qyg(), signedUIntArray2.m8525getUnsignedValuehP7Qyg()), !(signedUIntArray.getSign() ^ signedUIntArray2.getSign()), (DefaultConstructorMarker) null);
    }

    private final SignedUIntArray signedSubtract(SignedUIntArray signedUIntArray, SignedUIntArray signedUIntArray2) {
        return signedAdd(signedUIntArray, SignedUIntArray.m8522copyLpG4sQ0$default(signedUIntArray2, (int[]) null, !signedUIntArray2.getSign(), 1, (Object) null));
    }

    @NotNull
    /* renamed from: add-0-0sMy4  reason: not valid java name */
    public int[] m8437add00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2) {
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        Intrinsics.checkNotNullParameter(iArr3, "first");
        Intrinsics.checkNotNullParameter(iArr4, "second");
        if (UIntArray.m9135getSizeimpl(iArr) == 1 && UIntArray.m9134getpVg5ArA(iArr3, 0) == 0) {
            return iArr4;
        }
        if (UIntArray.m9135getSizeimpl(iArr2) == 1 && UIntArray.m9134getpVg5ArA(iArr4, 0) == 0) {
            return iArr3;
        }
        Quadruple quadruple = UIntArray.m9135getSizeimpl(iArr) > UIntArray.m9135getSizeimpl(iArr2) ? new Quadruple(Integer.valueOf(UIntArray.m9135getSizeimpl(iArr)), Integer.valueOf(UIntArray.m9135getSizeimpl(iArr2)), UIntArray.m9127boximpl(iArr), UIntArray.m9127boximpl(iArr2)) : new Quadruple(Integer.valueOf(UIntArray.m9135getSizeimpl(iArr2)), Integer.valueOf(UIntArray.m9135getSizeimpl(iArr)), UIntArray.m9127boximpl(iArr2), UIntArray.m9127boximpl(iArr));
        int intValue = ((Number) quadruple.component1()).intValue();
        int intValue2 = ((Number) quadruple.component2()).intValue();
        int[] r5 = ((UIntArray) quadruple.component3()).m9143unboximpl();
        int[] r2 = ((UIntArray) quadruple.component4()).m9143unboximpl();
        int i3 = intValue + 1;
        int[] iArr5 = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            iArr5[i4] = 0;
        }
        int[] r6 = UIntArray.m9129constructorimpl(iArr5);
        int i5 = 0;
        long j2 = 0;
        while (i5 < intValue2) {
            long d2 = a.d(4294967295L & ((long) UIntArray.m9134getpVg5ArA(r2, i5)), a.d(((long) UIntArray.m9134getpVg5ArA(r5, i5)) & 4294967295L, j2));
            UIntArray.m9139setVXSXFK8(r6, i5, UInt.m9074constructorimpl((int) ULong.m9153constructorimpl(m8468getBaseMasksVKNKU() & d2)));
            j2 = ULong.m9153constructorimpl(d2 >>> getBasePowerOfTwo());
            i5++;
        }
        while (j2 != 0) {
            if (i5 == intValue) {
                UIntArray.m9139setVXSXFK8(r6, intValue, UInt.m9074constructorimpl((int) j2));
                return r6;
            }
            long d3 = a.d(((long) UIntArray.m9134getpVg5ArA(r5, i5)) & 4294967295L, j2);
            UIntArray.m9139setVXSXFK8(r6, i5, UInt.m9074constructorimpl((int) ULong.m9153constructorimpl(m8468getBaseMasksVKNKU() & d3)));
            j2 = ULong.m9153constructorimpl(d3 >>> getBasePowerOfTwo());
            i5++;
        }
        while (i5 < intValue) {
            UIntArray.m9139setVXSXFK8(r6, i5, UIntArray.m9134getpVg5ArA(r5, i5));
            i5++;
        }
        return UIntArray.m9134getpVg5ArA(r6, UIntArray.m9135getSizeimpl(r6) - 1) == 0 ? UIntArray.m9129constructorimpl(ArraysKt.copyOfRange(r6, 0, UIntArray.m9135getSizeimpl(r6) - 1)) : r6;
    }

    @NotNull
    /* renamed from: and-0-0sMy4  reason: not valid java name */
    public int[] m8438and00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        Intrinsics.checkNotNullParameter(iArr2, "mask");
        int r02 = UIntArray.m9135getSizeimpl(iArr);
        int[] iArr3 = new int[r02];
        int i3 = 0;
        while (i3 < r02) {
            iArr3[i3] = i3 < UIntArray.m9135getSizeimpl(iArr2) ? UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i3) & UIntArray.m9134getpVg5ArA(iArr2, i3)) : 0;
            i3++;
        }
        return m8500removeLeadingZeroshkIa6DI(UIntArray.m9129constructorimpl(iArr3));
    }

    @NotNull
    /* renamed from: and-wZx4R44$bignum  reason: not valid java name */
    public final SignedUIntArray m8439andwZx4R44$bignum(@NotNull SignedUIntArray signedUIntArray, @NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(signedUIntArray, "$receiver");
        Intrinsics.checkNotNullParameter(iArr, "operand");
        return new SignedUIntArray(m8438and00sMy4(signedUIntArray.m8525getUnsignedValuehP7Qyg(), iArr), signedUIntArray.getSign(), (DefaultConstructorMarker) null);
    }

    @NotNull
    /* renamed from: basecaseSqrt--ajY-9A$bignum  reason: not valid java name */
    public final Pair<UIntArray, UIntArray> m8440basecaseSqrtajY9A$bignum(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        int[] r02 = m8507sqrtInthkIa6DI$bignum(iArr);
        return new Pair<>(UIntArray.m9127boximpl(r02), UIntArray.m9127boximpl(m8477minus00sMy4$bignum(iArr, m8509times00sMy4$bignum(r02, r02))));
    }

    @NotNull
    /* renamed from: basicDivide-Ynv0uTE  reason: not valid java name */
    public final Pair<UIntArray, UIntArray> m8441basicDivideYnv0uTE(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "unnormalizedDividend");
        Intrinsics.checkNotNullParameter(iArr2, "unnormalizedDivisor");
        if (m8448compareToYnv0uTE$bignum(iArr2, iArr) > 0) {
            return new Pair<>(UIntArray.m9127boximpl(m8475getZEROhP7Qyg()), UIntArray.m9127boximpl(iArr));
        }
        if (UIntArray.m9135getSizeimpl(iArr2) == 1 && UIntArray.m9135getSizeimpl(iArr) == 1) {
            return new Pair<>(UIntArray.m9127boximpl(m8500removeLeadingZeroshkIa6DI(new int[]{Integer.divideUnsigned(UIntArray.m9134getpVg5ArA(iArr, 0), UIntArray.m9134getpVg5ArA(iArr2, 0))})), UIntArray.m9127boximpl(m8500removeLeadingZeroshkIa6DI(new int[]{Integer.remainderUnsigned(UIntArray.m9134getpVg5ArA(iArr, 0), UIntArray.m9134getpVg5ArA(iArr2, 0))})));
        }
        if (m8444bitLengthajY9A(iArr) - m8444bitLengthajY9A(iArr2) == 0) {
            return new Pair<>(UIntArray.m9127boximpl(new int[]{1}), UIntArray.m9127boximpl(m8477minus00sMy4$bignum(iArr, iArr2)));
        }
        Triple<UIntArray, UIntArray, Integer> r13 = m8484normalizeYnv0uTE(iArr, iArr2);
        int[] r14 = r13.component1().m9143unboximpl();
        int[] r02 = r13.component2().m9143unboximpl();
        int intValue = r13.component3().intValue();
        int r2 = UIntArray.m9135getSizeimpl(r14);
        int r3 = UIntArray.m9135getSizeimpl(r02);
        int i3 = r2 - r3;
        int[] r4 = UIntArray.m9128constructorimpl(i3);
        int[] r5 = m8504shlWj2uyrI$bignum(r02, getBasePowerOfTwo() * i3);
        if (m8448compareToYnv0uTE$bignum(r14, r5) >= 0) {
            r4 = UIntArray.m9128constructorimpl(i3 + 1);
            UIntArray.m9139setVXSXFK8(r4, i3, 1);
            r14 = m8477minus00sMy4$bignum(r14, r5);
        }
        int i4 = i3 - 1;
        if (i4 >= 0) {
            while (true) {
                int i5 = i4 - 1;
                int i6 = r3 + i4;
                long r6 = i6 < UIntArray.m9135getSizeimpl(r14) ? UnsignedKt.m9332ulongDivideeb3DHEI(a.d(((long) UIntArray.m9134getpVg5ArA(r14, i6 - 1)) & 4294967295L, ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(r14, i6)) & 4294967295L) << getBasePowerOfTwo())), ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(r02, r3 - 1)) & 4294967295L)) : i6 == UIntArray.m9135getSizeimpl(r14) ? ULong.m9153constructorimpl(((long) Integer.divideUnsigned(UIntArray.m9134getpVg5ArA(r14, i6 - 1), UIntArray.m9134getpVg5ArA(r02, r3 - 1))) & 4294967295L) : 0;
                UIntArray.m9139setVXSXFK8(r4, i4, Long.compareUnsigned(r6, ULong.m9153constructorimpl(ULong.m9153constructorimpl(4294967295L & ((long) m8467getBasepVg5ArA())) - 1)) < 0 ? UInt.m9074constructorimpl((int) r6) : UInt.m9074constructorimpl(m8467getBasepVg5ArA() - 1));
                int[] r62 = m8504shlWj2uyrI$bignum(m8510timesFE_7wA8$bignum(r02, UIntArray.m9134getpVg5ArA(r4, i4)), getBasePowerOfTwo() * i4);
                while (m8448compareToYnv0uTE$bignum(r62, r14) > 0) {
                    UIntArray.m9139setVXSXFK8(r4, i4, UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(r4, i4) - 1));
                    r62 = m8504shlWj2uyrI$bignum(m8510timesFE_7wA8$bignum(r02, UIntArray.m9134getpVg5ArA(r4, i4)), getBasePowerOfTwo() * i4);
                }
                r14 = m8477minus00sMy4$bignum(r14, r62);
                if (i5 < 0) {
                    break;
                }
                i4 = i5;
            }
        }
        while (m8448compareToYnv0uTE$bignum(r14, r02) >= 0) {
            r4 = m8492plusFE_7wA8$bignum(r4, 1);
            r14 = m8477minus00sMy4$bignum(r14, r02);
        }
        return new Pair<>(UIntArray.m9127boximpl(m8500removeLeadingZeroshkIa6DI(r4)), UIntArray.m9127boximpl(m8451denormalizeWj2uyrI(r14, intValue)));
    }

    @NotNull
    /* renamed from: basicDivide2-Ynv0uTE  reason: not valid java name */
    public final Pair<UIntArray, UIntArray> m8442basicDivide2Ynv0uTE(@NotNull int[] iArr, @NotNull int[] iArr2) {
        int[] iArr3;
        Intrinsics.checkNotNullParameter(iArr, "unnormalizedDividend");
        Intrinsics.checkNotNullParameter(iArr2, "unnormalizedDivisor");
        Triple<UIntArray, UIntArray, Integer> r13 = m8484normalizeYnv0uTE(iArr, iArr2);
        int[] r14 = r13.component1().m9143unboximpl();
        int[] r02 = r13.component2().m9143unboximpl();
        int intValue = r13.component3().intValue();
        int r12 = UIntArray.m9135getSizeimpl(r14) - UIntArray.m9135getSizeimpl(r02);
        int[] r2 = m8504shlWj2uyrI$bignum(r02, wordSizeInBits * r12);
        int i3 = r12 + 1;
        int[] iArr4 = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            iArr4[i4] = 0;
        }
        int[] r3 = UIntArray.m9129constructorimpl(iArr4);
        if (m8448compareToYnv0uTE$bignum(r14, r2) > 0) {
            UIntArray.m9139setVXSXFK8(r3, r12, 1);
            r14 = m8477minus00sMy4$bignum(r14, r2);
        }
        m8475getZEROhP7Qyg();
        m8475getZEROhP7Qyg();
        m8475getZEROhP7Qyg();
        int i5 = r12 - 1;
        if (i5 >= 0) {
            while (true) {
                int i6 = i5 - 1;
                UIntArray.m9139setVXSXFK8(r3, i5, UInt.m9074constructorimpl((int) UComparisonsKt.m10180minOfeb3DHEI(UnsignedKt.m9332ulongDivideeb3DHEI(m8516toULongExactq22ZNjw(UIntArray.m9129constructorimpl(ArraysKt.copyOfRange(iArr3, UIntArray.m9135getSizeimpl(r02) - 1, UIntArray.m9135getSizeimpl(r02) + 1))), ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(r02, UIntArray.m9135getSizeimpl(r02) - 1)) & 4294967295L)), m8468getBaseMasksVKNKU())));
                int i7 = wordSizeInBits;
                int[] r6 = m8504shlWj2uyrI$bignum(r02, i5 * i7);
                int[] r4 = m8504shlWj2uyrI$bignum(m8510timesFE_7wA8$bignum(r02, UIntArray.m9134getpVg5ArA(r3, i5)), i7 * i5);
                if (m8448compareToYnv0uTE$bignum(r4, iArr3) > 0) {
                    int[] r7 = m8477minus00sMy4$bignum(r4, iArr3);
                    while (m8448compareToYnv0uTE$bignum(r7, r4) > 0) {
                        UIntArray.m9139setVXSXFK8(r3, i5, UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(r3, i5) - 1));
                        r7 = m8477minus00sMy4$bignum(r7, r6);
                    }
                    iArr3 = m8504shlWj2uyrI$bignum(m8477minus00sMy4$bignum(iArr3, m8510timesFE_7wA8$bignum(r02, UIntArray.m9134getpVg5ArA(r3, i5))), i5 * wordSizeInBits);
                } else {
                    iArr3 = m8477minus00sMy4$bignum(iArr3, r4);
                }
                if (i6 < 0) {
                    break;
                }
                i5 = i6;
            }
        }
        return new Pair<>(UIntArray.m9127boximpl(m8500removeLeadingZeroshkIa6DI(r3)), UIntArray.m9127boximpl(m8451denormalizeWj2uyrI(iArr3, intValue)));
    }

    /* renamed from: bitAt-LpG4sQ0  reason: not valid java name */
    public boolean m8443bitAtLpG4sQ0(@NotNull int[] iArr, long j2) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        long j3 = (long) 63;
        long j4 = j2 / j3;
        if (j4 <= SieveCacheKt.NodeLinkMask) {
            return j4 < ((long) UIntArray.m9135getSizeimpl(iArr)) && UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, (int) j4) & UInt.m9074constructorimpl(1 << ((int) (j2 % j3)))) == 1;
        }
        throw new RuntimeException("Invalid bit index, too large, cannot access word (Word position > Int.MAX_VALUE");
    }

    /* renamed from: bitLength--ajY-9A  reason: not valid java name */
    public int m8444bitLengthajY9A(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "value");
        if (UIntArray.m9137isEmptyimpl(iArr)) {
            return 0;
        }
        return (getBasePowerOfTwo() * (UIntArray.m9135getSizeimpl(iArr) - 1)) + m8445bitLengthWZ4Q5Ns(UIntArray.m9134getpVg5ArA(iArr, UIntArray.m9135getSizeimpl(iArr) - 1));
    }

    /* renamed from: bitLength-WZ4Q5Ns  reason: not valid java name */
    public final int m8445bitLengthWZ4Q5Ns(int i3) {
        return getBasePowerOfTwo() - m8487numberOfLeadingZerosInAWordWZ4Q5Ns(i3);
    }

    /* renamed from: compare-Ynv0uTE  reason: not valid java name */
    public int m8446compareYnv0uTE(@NotNull int[] iArr, @NotNull int[] iArr2) {
        boolean z2;
        boolean z3;
        Intrinsics.checkNotNullParameter(iArr, "first");
        Intrinsics.checkNotNullParameter(iArr2, "second");
        int r02 = UIntArray.m9135getSizeimpl(iArr) - m8449countLeadingZeroWordsajY9A(iArr);
        int r12 = UIntArray.m9135getSizeimpl(iArr2) - m8449countLeadingZeroWordsajY9A(iArr2);
        if (r02 > r12) {
            return 1;
        }
        if (r12 > r02) {
            return -1;
        }
        int i3 = r02 - 1;
        while (true) {
            if (i3 < 0) {
                z2 = true;
                z3 = false;
                break;
            } else if (Integer.compareUnsigned(UIntArray.m9134getpVg5ArA(iArr, i3), UIntArray.m9134getpVg5ArA(iArr2, i3)) > 0) {
                z3 = true;
                z2 = false;
                break;
            } else if (Integer.compareUnsigned(UIntArray.m9134getpVg5ArA(iArr, i3), UIntArray.m9134getpVg5ArA(iArr2, i3)) < 0) {
                z2 = false;
                z3 = false;
                break;
            } else {
                i3--;
            }
        }
        if (z2) {
            return 0;
        }
        return z3 ? 1 : -1;
    }

    /* renamed from: compareTo-RLbJYCw$bignum  reason: not valid java name */
    public final int m8447compareToRLbJYCw$bignum(@NotNull int[] iArr, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        return m8446compareYnv0uTE(iArr, new int[]{i3});
    }

    /* renamed from: compareTo-Ynv0uTE$bignum  reason: not valid java name */
    public final int m8448compareToYnv0uTE$bignum(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        Intrinsics.checkNotNullParameter(iArr2, "other");
        return m8446compareYnv0uTE(iArr, iArr2);
    }

    /* renamed from: countLeadingZeroWords--ajY-9A  reason: not valid java name */
    public final int m8449countLeadingZeroWordsajY9A(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "bigInteger");
        int r12 = UIntArray.m9135getSizeimpl(iArr) - 1;
        if (r12 <= 0) {
            return 0;
        }
        int i3 = UIntArray.m9134getpVg5ArA(iArr, r12);
        while (i3 == 0 && r12 > 0) {
            r12--;
            i3 = UIntArray.m9134getpVg5ArA(iArr, r12);
        }
        if (UIntArray.m9134getpVg5ArA(iArr, r12) == 0) {
            r12--;
        }
        return (UIntArray.m9135getSizeimpl(iArr) - r12) - 1;
    }

    @NotNull
    /* renamed from: d1ReciprocalRecursiveWordVersion--ajY-9A  reason: not valid java name */
    public final Pair<UIntArray, UIntArray> m8450d1ReciprocalRecursiveWordVersionajY9A(@NotNull int[] iArr) {
        int[] iArr2;
        Intrinsics.checkNotNullParameter(iArr, "a");
        int r02 = UIntArray.m9135getSizeimpl(iArr);
        int i3 = r02 - 1;
        if (i3 <= 2) {
            if (i3 == 0) {
                i3 = 1;
            }
            int[] r03 = m8504shlWj2uyrI$bignum(m8471getONEhP7Qyg(), i3 * 2 * wordSizeInBits);
            int[] r12 = m8452div00sMy4$bignum(r03, iArr);
            return new Pair<>(UIntArray.m9127boximpl(r12), UIntArray.m9127boximpl(m8477minus00sMy4$bignum(r03, m8509times00sMy4$bignum(r12, iArr))));
        }
        int floor = (int) Math.floor(((double) (r02 - 2)) / ((double) 2));
        int i4 = i3 - floor;
        int[] r2 = UIntArray.m9129constructorimpl(ArraysKt.copyOfRange(iArr, (UIntArray.m9135getSizeimpl(iArr) - i4) - 1, UIntArray.m9135getSizeimpl(iArr)));
        int[] r3 = UIntArray.m9129constructorimpl(ArraysKt.copyOfRange(iArr, 0, floor));
        Pair<UIntArray, UIntArray> r22 = m8450d1ReciprocalRecursiveWordVersionajY9A(r2);
        int[] r4 = r22.component1().m9143unboximpl();
        int[] r23 = r22.component2().m9143unboximpl();
        int[] r32 = m8509times00sMy4$bignum(r3, r4);
        int i5 = wordSizeInBits;
        int[] r24 = m8504shlWj2uyrI$bignum(r23, floor * i5);
        if (m8448compareToYnv0uTE$bignum(r24, r32) >= 0) {
            iArr2 = m8477minus00sMy4$bignum(r24, r32);
        } else {
            r4 = m8477minus00sMy4$bignum(r4, m8471getONEhP7Qyg());
            iArr2 = m8477minus00sMy4$bignum(m8491plus00sMy4$bignum(r24, iArr), r32);
        }
        int[] r13 = m8505shrWj2uyrI$bignum(m8509times00sMy4$bignum(r4, m8505shrWj2uyrI$bignum(iArr2, i4 * i5)), i4 * i5);
        int[] r33 = m8491plus00sMy4$bignum(m8504shlWj2uyrI$bignum(r4, floor * i5), r13);
        int[] r04 = m8477minus00sMy4$bignum(m8504shlWj2uyrI$bignum(iArr2, floor * i5), m8509times00sMy4$bignum(iArr, r13));
        if (m8448compareToYnv0uTE$bignum(r04, iArr) >= 0) {
            r33 = m8491plus00sMy4$bignum(r33, m8471getONEhP7Qyg());
            r04 = m8477minus00sMy4$bignum(r04, iArr);
            if (m8448compareToYnv0uTE$bignum(r04, iArr) >= 0) {
                r33 = m8491plus00sMy4$bignum(r33, m8471getONEhP7Qyg());
                r04 = m8477minus00sMy4$bignum(r04, iArr);
            }
        }
        return new Pair<>(UIntArray.m9127boximpl(r33), UIntArray.m9127boximpl(r04));
    }

    @NotNull
    /* renamed from: denormalize-Wj2uyrI  reason: not valid java name */
    public final int[] m8451denormalizeWj2uyrI(@NotNull int[] iArr, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "remainderNormalized");
        return m8505shrWj2uyrI$bignum(iArr, i3);
    }

    @NotNull
    public final SignedUIntArray div$bignum(@NotNull SignedUIntArray signedUIntArray, @NotNull SignedUIntArray signedUIntArray2) {
        Intrinsics.checkNotNullParameter(signedUIntArray, "<this>");
        Intrinsics.checkNotNullParameter(signedUIntArray2, "other");
        return signedDivide(signedUIntArray, signedUIntArray2);
    }

    @NotNull
    /* renamed from: div-0-0sMy4$bignum  reason: not valid java name */
    public final int[] m8452div00sMy4$bignum(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        Intrinsics.checkNotNullParameter(iArr2, "other");
        return m8454divideYnv0uTE(iArr, iArr2).getFirst().m9143unboximpl();
    }

    @NotNull
    /* renamed from: div-FE_7wA8$bignum  reason: not valid java name */
    public final int[] m8453divFE_7wA8$bignum(@NotNull int[] iArr, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        return m8454divideYnv0uTE(iArr, new int[]{i3}).getFirst().m9143unboximpl();
    }

    @NotNull
    /* renamed from: divide-Ynv0uTE  reason: not valid java name */
    public Pair<UIntArray, UIntArray> m8454divideYnv0uTE(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "first");
        Intrinsics.checkNotNullParameter(iArr2, "second");
        return m8441basicDivideYnv0uTE(iArr, iArr2);
    }

    @NotNull
    /* renamed from: divrem-Ynv0uTE$bignum  reason: not valid java name */
    public final Pair<UIntArray, UIntArray> m8455divremYnv0uTE$bignum(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        Intrinsics.checkNotNullParameter(iArr2, "other");
        return m8454divideYnv0uTE(iArr, iArr2);
    }

    @NotNull
    /* renamed from: extendUIntArray-9fY048w  reason: not valid java name */
    public final int[] m8456extendUIntArray9fY048w(@NotNull int[] iArr, int i3, int i4) {
        Intrinsics.checkNotNullParameter(iArr, "original");
        int r2 = UIntArray.m9135getSizeimpl(iArr) + i3;
        int[] iArr2 = new int[r2];
        int i5 = 0;
        while (i5 < r2) {
            iArr2[i5] = i5 < UIntArray.m9135getSizeimpl(iArr) ? UIntArray.m9134getpVg5ArA(iArr, i5) : i4;
            i5++;
        }
        return UIntArray.m9129constructorimpl(iArr2);
    }

    @NotNull
    /* renamed from: fromByte-g_c56RQ  reason: not valid java name */
    public int[] m8457fromByteg_c56RQ(byte b3) {
        return new int[]{UInt.m9074constructorimpl(Math.abs(b3))};
    }

    @NotNull
    public Pair<UIntArray, Sign> fromByteArray(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "source");
        throw new NotImplementedError("An operation is not implemented: not implemented yet");
    }

    @NotNull
    /* renamed from: fromInt-g_c56RQ  reason: not valid java name */
    public int[] m8458fromIntg_c56RQ(int i3) {
        return new int[]{UInt.m9074constructorimpl(Math.abs(i3))};
    }

    @NotNull
    /* renamed from: fromLong-g_c56RQ  reason: not valid java name */
    public int[] m8459fromLongg_c56RQ(long j2) {
        return new int[]{UInt.m9074constructorimpl((int) ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2) & -4294967296L) >>> 32)), UInt.m9074constructorimpl((int) Math.abs(j2))};
    }

    @NotNull
    /* renamed from: fromShort-g_c56RQ  reason: not valid java name */
    public int[] m8460fromShortg_c56RQ(short s3) {
        return new int[]{UInt.m9074constructorimpl(Math.abs(s3))};
    }

    @NotNull
    /* renamed from: fromUByte-W6sApTE  reason: not valid java name */
    public int[] m8461fromUByteW6sApTE(byte b3) {
        return new int[]{UInt.m9074constructorimpl(b3 & 255)};
    }

    @NotNull
    /* renamed from: fromUByteArray-GBYM_sE  reason: not valid java name */
    public Pair<UIntArray, Sign> m8462fromUByteArrayGBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "source");
        throw new NotImplementedError("An operation is not implemented: not implemented yet");
    }

    @NotNull
    /* renamed from: fromUInt-Ezf8eIQ  reason: not valid java name */
    public int[] m8463fromUIntEzf8eIQ(int i3) {
        return new int[]{i3};
    }

    @NotNull
    /* renamed from: fromULong-owt3UmA  reason: not valid java name */
    public int[] m8464fromULongowt3UmA(long j2) {
        return new int[]{UInt.m9074constructorimpl((int) ULong.m9153constructorimpl(ULong.m9153constructorimpl(-4294967296L & j2) >>> 32)), UInt.m9074constructorimpl((int) j2)};
    }

    @NotNull
    /* renamed from: fromUShort-y3OBVxU  reason: not valid java name */
    public int[] m8465fromUShorty3OBVxU(short s3) {
        return new int[]{UInt.m9074constructorimpl(65535 & s3)};
    }

    @NotNull
    /* renamed from: gcd-0-0sMy4  reason: not valid java name */
    public int[] m8466gcd00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "first");
        Intrinsics.checkNotNullParameter(iArr2, "second");
        return (UIntArray.m9135getSizeimpl(iArr) > 150 || UIntArray.m9135getSizeimpl(iArr2) > 150) ? m8433euclideanGcd00sMy4(iArr, iArr2) : m8430binaryGcd00sMy4(iArr, iArr2);
    }

    /* renamed from: getBase-pVg5ArA  reason: not valid java name */
    public final int m8467getBasepVg5ArA() {
        return base;
    }

    /* renamed from: getBaseMask-s-VKNKU  reason: not valid java name */
    public final long m8468getBaseMasksVKNKU() {
        return baseMask;
    }

    /* renamed from: getBaseMaskInt-pVg5ArA  reason: not valid java name */
    public final int m8469getBaseMaskIntpVg5ArA() {
        return baseMaskInt;
    }

    public int getBasePowerOfTwo() {
        return basePowerOfTwo;
    }

    /* renamed from: getLowerMask-s-VKNKU  reason: not valid java name */
    public final long m8470getLowerMasksVKNKU() {
        return lowerMask;
    }

    @NotNull
    /* renamed from: getONE--hP7Qyg  reason: not valid java name */
    public int[] m8471getONEhP7Qyg() {
        return ONE;
    }

    /* renamed from: getOverflowMask-s-VKNKU  reason: not valid java name */
    public final long m8472getOverflowMasksVKNKU() {
        return overflowMask;
    }

    @NotNull
    public final SignedUIntArray getSIGNED_POSITIVE_TWO() {
        return SIGNED_POSITIVE_TWO;
    }

    @NotNull
    /* renamed from: getTEN--hP7Qyg  reason: not valid java name */
    public int[] m8473getTENhP7Qyg() {
        return TEN;
    }

    @NotNull
    /* renamed from: getTWO--hP7Qyg  reason: not valid java name */
    public int[] m8474getTWOhP7Qyg() {
        return TWO;
    }

    public final int getWordSizeInBits() {
        return wordSizeInBits;
    }

    @NotNull
    /* renamed from: getZERO--hP7Qyg  reason: not valid java name */
    public int[] m8475getZEROhP7Qyg() {
        return ZERO;
    }

    @NotNull
    public int[] get_emitIntArray() {
        return _emitIntArray;
    }

    @NotNull
    /* renamed from: karatsubaMultiply-0-0sMy4  reason: not valid java name */
    public final int[] m8476karatsubaMultiply00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "firstUnsigned");
        Intrinsics.checkNotNullParameter(iArr2, "secondUnsigned");
        SignedUIntArray signedUIntArray = new SignedUIntArray(iArr, true, (DefaultConstructorMarker) null);
        SignedUIntArray signedUIntArray2 = new SignedUIntArray(iArr2, true, (DefaultConstructorMarker) null);
        int max = (Math.max(UIntArray.m9135getSizeimpl(signedUIntArray.m8525getUnsignedValuehP7Qyg()), UIntArray.m9135getSizeimpl(signedUIntArray2.m8525getUnsignedValuehP7Qyg())) + 1) / 2;
        int[] r12 = m8471getONEhP7Qyg();
        int i3 = wordSizeInBits;
        int[] r13 = m8508subtract00sMy4(m8504shlWj2uyrI$bignum(r12, max * i3), m8471getONEhP7Qyg());
        SignedUIntArray r3 = m8439andwZx4R44$bignum(signedUIntArray, r13);
        SignedUIntArray shr$bignum = shr$bignum(signedUIntArray, max * i3);
        SignedUIntArray r14 = m8439andwZx4R44$bignum(signedUIntArray2, r13);
        SignedUIntArray shr$bignum2 = shr$bignum(signedUIntArray2, max * i3);
        SignedUIntArray times$bignum = times$bignum(shr$bignum, shr$bignum2);
        SignedUIntArray times$bignum2 = times$bignum(r3, r14);
        return plus$bignum(plus$bignum(shl$bignum(times$bignum, i3 * 2 * max), shl$bignum(minus$bignum(minus$bignum(times$bignum(plus$bignum(shr$bignum, r3), plus$bignum(shr$bignum2, r14)), times$bignum), times$bignum2), i3 * max)), times$bignum2).m8525getUnsignedValuehP7Qyg();
    }

    @NotNull
    public final SignedUIntArray minus$bignum(@NotNull SignedUIntArray signedUIntArray, @NotNull SignedUIntArray signedUIntArray2) {
        Intrinsics.checkNotNullParameter(signedUIntArray, "<this>");
        Intrinsics.checkNotNullParameter(signedUIntArray2, "other");
        return signedSubtract(signedUIntArray, signedUIntArray2);
    }

    @NotNull
    /* renamed from: minus-0-0sMy4$bignum  reason: not valid java name */
    public final int[] m8477minus00sMy4$bignum(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        Intrinsics.checkNotNullParameter(iArr2, "other");
        return m8508subtract00sMy4(iArr, iArr2);
    }

    @NotNull
    /* renamed from: minus-FE_7wA8$bignum  reason: not valid java name */
    public final int[] m8478minusFE_7wA8$bignum(@NotNull int[] iArr, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        return m8508subtract00sMy4(iArr, new int[]{i3});
    }

    @NotNull
    /* renamed from: multiply-0-0sMy4  reason: not valid java name */
    public int[] m8479multiply00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "first");
        Intrinsics.checkNotNullParameter(iArr2, "second");
        if (UIntArray.m9133equalsimpl0(iArr, m8475getZEROhP7Qyg()) || UIntArray.m9133equalsimpl0(iArr2, m8475getZEROhP7Qyg())) {
            return m8475getZEROhP7Qyg();
        }
        if (UIntArray.m9135getSizeimpl(iArr) >= 60 || UIntArray.m9135getSizeimpl(iArr2) == 60) {
            return m8476karatsubaMultiply00sMy4(iArr, iArr2);
        }
        int[] r02 = m8475getZEROhP7Qyg();
        int length = iArr2.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            int i5 = iArr2[i3];
            BigInteger32Arithmetic bigInteger32Arithmetic = INSTANCE;
            r02 = bigInteger32Arithmetic.m8491plus00sMy4$bignum(r02, bigInteger32Arithmetic.m8504shlWj2uyrI$bignum(bigInteger32Arithmetic.m8480multiplyFE_7wA8(iArr, i5), bigInteger32Arithmetic.getBasePowerOfTwo() * i4));
            i3++;
            i4++;
        }
        return m8500removeLeadingZeroshkIa6DI(r02);
    }

    @NotNull
    /* renamed from: multiply-FE_7wA8  reason: not valid java name */
    public final int[] m8480multiplyFE_7wA8(@NotNull int[] iArr, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "first");
        int[] r02 = UIntArray.m9128constructorimpl(UIntArray.m9135getSizeimpl(iArr) + 1);
        int r12 = UIntArray.m9135getSizeimpl(iArr);
        if (r12 > 0) {
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                long g2 = android.support.v4.media.session.a.g(((long) i3) & 4294967295L, ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr, i4)) & 4294967295L));
                long d2 = a.d(4294967295L & ((long) UInt.m9074constructorimpl((int) ULong.m9153constructorimpl(m8468getBaseMasksVKNKU() & g2))), ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(r02, i4)) & 4294967295L));
                UIntArray.m9139setVXSXFK8(r02, i4, UInt.m9074constructorimpl((int) ULong.m9153constructorimpl(m8468getBaseMasksVKNKU() & d2)));
                long r6 = ULong.m9153constructorimpl(d2 >>> getBasePowerOfTwo());
                UIntArray.m9139setVXSXFK8(r02, i5, UInt.m9074constructorimpl(UInt.m9074constructorimpl((int) r6) + UInt.m9074constructorimpl((int) ULong.m9153constructorimpl(g2 >>> getBasePowerOfTwo()))));
                if (i5 >= r12) {
                    break;
                }
                i4 = i5;
            }
        }
        return m8500removeLeadingZeroshkIa6DI(r02);
    }

    @NotNull
    /* renamed from: multiply-FwZOn3I  reason: not valid java name */
    public final int[] m8481multiplyFwZOn3I(int i3, int i4) {
        int r12 = UInt.m9074constructorimpl(i3 * i4);
        return m8500removeLeadingZeroshkIa6DI(new int[]{r12, UInt.m9074constructorimpl(r12 >>> getBasePowerOfTwo())});
    }

    @NotNull
    /* renamed from: multiplyNoKaratsuba-0-0sMy4$bignum  reason: not valid java name */
    public final int[] m8482multiplyNoKaratsuba00sMy4$bignum(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "first");
        Intrinsics.checkNotNullParameter(iArr2, "second");
        if (UIntArray.m9133equalsimpl0(iArr, m8475getZEROhP7Qyg()) || UIntArray.m9133equalsimpl0(iArr2, m8475getZEROhP7Qyg())) {
            return m8475getZEROhP7Qyg();
        }
        if (UIntArray.m9135getSizeimpl(iArr) >= 60 || UIntArray.m9135getSizeimpl(iArr2) == 60) {
            return m8476karatsubaMultiply00sMy4(iArr, iArr2);
        }
        int[] r02 = m8475getZEROhP7Qyg();
        int length = iArr2.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            int i5 = iArr2[i3];
            BigInteger32Arithmetic bigInteger32Arithmetic = INSTANCE;
            r02 = bigInteger32Arithmetic.m8491plus00sMy4$bignum(r02, bigInteger32Arithmetic.m8504shlWj2uyrI$bignum(bigInteger32Arithmetic.m8480multiplyFE_7wA8(iArr, i5), bigInteger32Arithmetic.getBasePowerOfTwo() * i4));
            i3++;
            i4++;
        }
        return m8500removeLeadingZeroshkIa6DI(r02);
    }

    @NotNull
    /* renamed from: normalize--ajY-9A  reason: not valid java name */
    public final Pair<UIntArray, Integer> m8483normalizeajY9A(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        int r02 = m8487numberOfLeadingZerosInAWordWZ4Q5Ns(UIntArray.m9134getpVg5ArA(iArr, UIntArray.m9135getSizeimpl(iArr) - 1));
        return new Pair<>(UIntArray.m9127boximpl(m8504shlWj2uyrI$bignum(iArr, r02)), Integer.valueOf(r02));
    }

    @NotNull
    /* renamed from: normalize-Ynv0uTE  reason: not valid java name */
    public final Triple<UIntArray, UIntArray, Integer> m8484normalizeYnv0uTE(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "dividend");
        Intrinsics.checkNotNullParameter(iArr2, "divisor");
        int r02 = m8487numberOfLeadingZerosInAWordWZ4Q5Ns(UIntArray.m9134getpVg5ArA(iArr2, UIntArray.m9135getSizeimpl(iArr2) - 1));
        return new Triple<>(UIntArray.m9127boximpl(m8504shlWj2uyrI$bignum(iArr, r02)), UIntArray.m9127boximpl(m8504shlWj2uyrI$bignum(iArr2, r02)), Integer.valueOf(r02));
    }

    @NotNull
    /* renamed from: not-hkIa6DI  reason: not valid java name */
    public int[] m8485nothkIa6DI(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        int r02 = UIntArray.m9135getSizeimpl(iArr);
        int[] iArr2 = new int[r02];
        for (int i3 = 0; i3 < r02; i3++) {
            iArr2[i3] = UInt.m9074constructorimpl(~UIntArray.m9134getpVg5ArA(iArr, i3));
        }
        return m8500removeLeadingZeroshkIa6DI(UIntArray.m9129constructorimpl(iArr2));
    }

    /* renamed from: numberOfDecimalDigits--ajY-9A  reason: not valid java name */
    public long m8486numberOfDecimalDigitsajY9A(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        double ceil = Math.ceil(BigInteger.Companion.getLOG_10_OF_2() * ((double) (m8444bitLengthajY9A(iArr) - 1)));
        int[] r7 = m8452div00sMy4$bignum(iArr, m8493powWj2uyrI(m8473getTENhP7Qyg(), (long) ceil));
        long j2 = 0;
        while (m8446compareYnv0uTE(r7, m8475getZEROhP7Qyg()) != 0) {
            r7 = m8452div00sMy4$bignum(r7, m8473getTENhP7Qyg());
            j2++;
        }
        return j2 + ((long) ((int) ceil));
    }

    /* renamed from: numberOfLeadingZerosInAWord-WZ4Q5Ns  reason: not valid java name */
    public int m8487numberOfLeadingZerosInAWordWZ4Q5Ns(int i3) {
        int basePowerOfTwo2 = getBasePowerOfTwo();
        int r02 = UInt.m9074constructorimpl(i3 >>> 16);
        if (r02 != 0) {
            basePowerOfTwo2 -= 16;
            i3 = r02;
        }
        int r03 = UInt.m9074constructorimpl(i3 >>> 8);
        if (r03 != 0) {
            basePowerOfTwo2 -= 8;
            i3 = r03;
        }
        int r04 = UInt.m9074constructorimpl(i3 >>> 4);
        if (r04 != 0) {
            basePowerOfTwo2 -= 4;
            i3 = r04;
        }
        int r05 = UInt.m9074constructorimpl(i3 >>> 2);
        if (r05 != 0) {
            basePowerOfTwo2 -= 2;
            i3 = r05;
        }
        return UInt.m9074constructorimpl(i3 >>> 1) != 0 ? basePowerOfTwo2 - 2 : basePowerOfTwo2 - i3;
    }

    /* renamed from: numberOfTrailingZerosInAWord-WZ4Q5Ns  reason: not valid java name */
    public final int m8488numberOfTrailingZerosInAWordWZ4Q5Ns(int i3) {
        int i4;
        int r02 = UInt.m9074constructorimpl(UInt.m9074constructorimpl(i3 << 16) & m8469getBaseMaskIntpVg5ArA());
        if (r02 != 0) {
            int i5 = r02;
            i4 = 16;
            i3 = i5;
        } else {
            i4 = 32;
        }
        int r12 = UInt.m9074constructorimpl(UInt.m9074constructorimpl(i3 << 8) & m8469getBaseMaskIntpVg5ArA());
        if (r12 != 0) {
            i4 -= 8;
            i3 = r12;
        }
        int r13 = UInt.m9074constructorimpl(UInt.m9074constructorimpl(i3 << 4) & m8469getBaseMaskIntpVg5ArA());
        if (r13 != 0) {
            i4 -= 4;
            i3 = r13;
        }
        int r14 = UInt.m9074constructorimpl(UInt.m9074constructorimpl(i3 << 2) & m8469getBaseMaskIntpVg5ArA());
        if (r14 != 0) {
            i4 -= 2;
            i3 = r14;
        }
        return UInt.m9074constructorimpl(m8469getBaseMaskIntpVg5ArA() & UInt.m9074constructorimpl(i3 << 1)) != 0 ? i4 - 2 : i4 - i3;
    }

    @NotNull
    /* renamed from: or-0-0sMy4  reason: not valid java name */
    public int[] m8489or00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        Intrinsics.checkNotNullParameter(iArr2, "mask");
        int r02 = UIntArray.m9135getSizeimpl(iArr);
        int[] iArr3 = new int[r02];
        int i3 = 0;
        while (i3 < r02) {
            iArr3[i3] = i3 < UIntArray.m9135getSizeimpl(iArr2) ? UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i3) | UIntArray.m9134getpVg5ArA(iArr2, i3)) : UIntArray.m9134getpVg5ArA(iArr, i3);
            i3++;
        }
        return m8500removeLeadingZeroshkIa6DI(UIntArray.m9129constructorimpl(iArr3));
    }

    @NotNull
    /* renamed from: parseForBase-g-PCqec  reason: not valid java name */
    public int[] m8490parseForBasegPCqec(@NotNull String str, int i3) {
        Intrinsics.checkNotNullParameter(str, "number");
        int[] r4 = m8475getZEROhP7Qyg();
        for (int i4 = 0; i4 < str.length(); i4++) {
            char charAt = str.charAt(i4);
            BigInteger32Arithmetic bigInteger32Arithmetic = INSTANCE;
            r4 = bigInteger32Arithmetic.m8492plusFE_7wA8$bignum(bigInteger32Arithmetic.m8510timesFE_7wA8$bignum(r4, UInt.m9074constructorimpl(i3)), UInt.m9074constructorimpl(DigitUtilKt.toDigit(charAt, i3)));
        }
        return r4;
    }

    @NotNull
    public final SignedUIntArray plus$bignum(@NotNull SignedUIntArray signedUIntArray, @NotNull SignedUIntArray signedUIntArray2) {
        Intrinsics.checkNotNullParameter(signedUIntArray, "<this>");
        Intrinsics.checkNotNullParameter(signedUIntArray2, "other");
        return signedAdd(signedUIntArray, signedUIntArray2);
    }

    @NotNull
    /* renamed from: plus-0-0sMy4$bignum  reason: not valid java name */
    public final int[] m8491plus00sMy4$bignum(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        Intrinsics.checkNotNullParameter(iArr2, "other");
        return m8437add00sMy4(iArr, iArr2);
    }

    @NotNull
    /* renamed from: plus-FE_7wA8$bignum  reason: not valid java name */
    public final int[] m8492plusFE_7wA8$bignum(@NotNull int[] iArr, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        return m8437add00sMy4(iArr, new int[]{i3});
    }

    @NotNull
    /* renamed from: pow-Wj2uyrI  reason: not valid java name */
    public int[] m8493powWj2uyrI(@NotNull int[] iArr, long j2) {
        Intrinsics.checkNotNullParameter(iArr, TtmlNode.RUBY_BASE);
        if (j2 == 0) {
            return m8471getONEhP7Qyg();
        }
        if (j2 == 1) {
            return iArr;
        }
        int[] r4 = m8471getONEhP7Qyg();
        while (j2 > 1) {
            long j3 = (long) 2;
            if (j2 % j3 == 0) {
                iArr = m8509times00sMy4$bignum(iArr, iArr);
                j2 /= j3;
            } else {
                r4 = m8509times00sMy4$bignum(iArr, r4);
                iArr = m8509times00sMy4$bignum(iArr, iArr);
                j2 = (j2 - 1) / j3;
            }
        }
        return m8509times00sMy4$bignum(r4, iArr);
    }

    @NotNull
    /* renamed from: prependULongArray-9fY048w  reason: not valid java name */
    public final int[] m8494prependULongArray9fY048w(@NotNull int[] iArr, int i3, int i4) {
        Intrinsics.checkNotNullParameter(iArr, "original");
        int r3 = UIntArray.m9135getSizeimpl(iArr) + i3;
        int[] iArr2 = new int[r3];
        int i5 = 0;
        while (i5 < r3) {
            iArr2[i5] = i5 < i3 ? i4 : UIntArray.m9134getpVg5ArA(iArr, i5 - i3);
            i5++;
        }
        return UIntArray.m9129constructorimpl(iArr2);
    }

    @NotNull
    /* renamed from: reciprocal--ajY-9A  reason: not valid java name */
    public Pair<UIntArray, UIntArray> m8495reciprocalajY9A(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        return m8450d1ReciprocalRecursiveWordVersionajY9A(iArr);
    }

    @NotNull
    /* renamed from: reciprocalDivision-Ynv0uTE$bignum  reason: not valid java name */
    public final Pair<UIntArray, UIntArray> m8496reciprocalDivisionYnv0uTE$bignum(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "first");
        Intrinsics.checkNotNullParameter(iArr2, "second");
        int r02 = (UIntArray.m9135getSizeimpl(iArr) - UIntArray.m9135getSizeimpl(iArr2)) * 2;
        int[] r12 = m8450d1ReciprocalRecursiveWordVersionajY9A(m8504shlWj2uyrI$bignum(iArr2, wordSizeInBits * r02)).getFirst().m9143unboximpl();
        int[] r2 = m8509times00sMy4$bignum(iArr, r12);
        if (m8447compareToRLbJYCw$bignum(r2, 0) == 0) {
            return new Pair<>(UIntArray.m9127boximpl(m8475getZEROhP7Qyg()), UIntArray.m9127boximpl(iArr));
        }
        int i3 = 1;
        if (UIntArray.m9135getSizeimpl(r2) != 1) {
            if (UnsignedKt.ulongCompare(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(r2, UIntArray.m9135getSizeimpl(r2) - UIntArray.m9135getSizeimpl(iArr2))) & 4294967295L), m8468getBaseMasksVKNKU()) >= 0) {
                int r4 = UIntArray.m9135getSizeimpl(r2);
                int[] iArr3 = new int[r4];
                int i4 = 0;
                while (i4 < r4) {
                    iArr3[i4] = i4 == UIntArray.m9135getSizeimpl(r2) - 1 ? UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(r2, UIntArray.m9135getSizeimpl(r2) - 1) + 1) : 0;
                    i4++;
                }
                r2 = UIntArray.m9129constructorimpl(iArr3);
            }
        } else if (m8447compareToRLbJYCw$bignum(r2, UInt.m9074constructorimpl(m8469getBaseMaskIntpVg5ArA() - 1)) >= 0) {
            r2 = m8491plus00sMy4$bignum(r2, m8471getONEhP7Qyg());
        }
        int r3 = (UIntArray.m9135getSizeimpl(r2) - (UIntArray.m9135getSizeimpl(r12) * 2)) + r02;
        if (r3 != 0) {
            i3 = r3;
        }
        int[] r03 = UIntArray.m9129constructorimpl(ArraysKt.copyOfRange(r2, UIntArray.m9135getSizeimpl(r2) - i3, UIntArray.m9135getSizeimpl(r2)));
        return new Pair<>(UIntArray.m9127boximpl(r03), UIntArray.m9127boximpl(m8477minus00sMy4$bignum(iArr, m8509times00sMy4$bignum(r03, iArr2))));
    }

    @NotNull
    /* renamed from: reciprocalSingleWord-WZ4Q5Ns  reason: not valid java name */
    public final Pair<UIntArray, Integer> m8497reciprocalSingleWordWZ4Q5Ns(int i3) {
        int r02 = m8445bitLengthWZ4Q5Ns(i3);
        int i4 = r02 * 4;
        if (r02 * 2 <= 63) {
            return m8431checkReciprocalLpG4sQ0(new int[]{i3}, new Pair(UIntArray.m9127boximpl(new int[]{UInt.m9074constructorimpl((int) UnsignedKt.m9332ulongDivideeb3DHEI(ULong.m9153constructorimpl(1 << i4), ULong.m9153constructorimpl(((long) i3) & 4294967295L)))}), Integer.valueOf(i4)));
        }
        return m8431checkReciprocalLpG4sQ0(new int[]{i3}, new Pair(UIntArray.m9127boximpl(m8453divFE_7wA8$bignum(m8504shlWj2uyrI$bignum(m8471getONEhP7Qyg(), i4), i3)), Integer.valueOf(i4)));
    }

    @NotNull
    public final SignedUIntArray rem$bignum(@NotNull SignedUIntArray signedUIntArray, @NotNull SignedUIntArray signedUIntArray2) {
        Intrinsics.checkNotNullParameter(signedUIntArray, "<this>");
        Intrinsics.checkNotNullParameter(signedUIntArray2, "other");
        return signedRemainder(signedUIntArray, signedUIntArray2);
    }

    @NotNull
    /* renamed from: rem-0-0sMy4$bignum  reason: not valid java name */
    public final int[] m8498rem00sMy4$bignum(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        Intrinsics.checkNotNullParameter(iArr2, "other");
        return m8454divideYnv0uTE(iArr, iArr2).getSecond().m9143unboximpl();
    }

    @NotNull
    /* renamed from: rem-FE_7wA8$bignum  reason: not valid java name */
    public final int[] m8499remFE_7wA8$bignum(@NotNull int[] iArr, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        return m8454divideYnv0uTE(iArr, new int[]{i3}).getSecond().m9143unboximpl();
    }

    @NotNull
    /* renamed from: removeLeadingZeros-hkIa6DI  reason: not valid java name */
    public final int[] m8500removeLeadingZeroshkIa6DI(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "bigInteger");
        int length = iArr.length - 1;
        while (true) {
            if (length < 0) {
                length = -1;
                break;
            } else if (UInt.m9074constructorimpl(iArr[length]) != 0) {
                break;
            } else {
                length--;
            }
        }
        int i3 = length + 1;
        return (i3 == -1 || i3 == 0) ? m8475getZEROhP7Qyg() : UIntArray.m9129constructorimpl(ArraysKt.copyOfRange(iArr, 0, i3));
    }

    @NotNull
    /* renamed from: setBitAt-WiAKJ7k  reason: not valid java name */
    public int[] m8501setBitAtWiAKJ7k(@NotNull int[] iArr, long j2, boolean z2) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        long j3 = (long) 63;
        long j4 = j2 / j3;
        if (j4 > SieveCacheKt.NodeLinkMask) {
            throw new RuntimeException("Invalid bit index, too large, cannot access word (Word position > Int.MAX_VALUE");
        } else if (j4 < ((long) UIntArray.m9135getSizeimpl(iArr))) {
            int r6 = UInt.m9074constructorimpl(1 << ((int) (j2 % j3)));
            int r8 = UIntArray.m9135getSizeimpl(iArr);
            int[] iArr2 = new int[r8];
            int i3 = 0;
            while (i3 < r8) {
                iArr2[i3] = i3 == ((int) j4) ? z2 ? UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i3) | r6) : UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i3) ^ r6) : UIntArray.m9134getpVg5ArA(iArr, i3);
                i3++;
            }
            return UIntArray.m9129constructorimpl(iArr2);
        } else {
            StringBuilder u3 = androidx.compose.animation.core.a.u("Invalid position, addressed word ", j4, " larger than number of words ");
            u3.append(UIntArray.m9135getSizeimpl(iArr));
            throw new IndexOutOfBoundsException(u3.toString());
        }
    }

    @NotNull
    /* renamed from: shiftLeft-Wj2uyrI  reason: not valid java name */
    public int[] m8502shiftLeftWj2uyrI(@NotNull int[] iArr, int i3) {
        int i4;
        Intrinsics.checkNotNullParameter(iArr, "operand");
        if (UIntArray.m9137isEmptyimpl(iArr) || i3 == 0) {
            return iArr;
        }
        int r02 = UIntArray.m9135getSizeimpl(iArr);
        int r12 = m8487numberOfLeadingZerosInAWordWZ4Q5Ns(UIntArray.m9134getpVg5ArA(iArr, UIntArray.m9135getSizeimpl(iArr) - 1));
        int basePowerOfTwo2 = i3 / getBasePowerOfTwo();
        int basePowerOfTwo3 = i3 % getBasePowerOfTwo();
        int i5 = basePowerOfTwo3 > r12 ? basePowerOfTwo2 + 1 : basePowerOfTwo2;
        if (basePowerOfTwo3 == 0) {
            int r11 = UIntArray.m9135getSizeimpl(iArr) + i5;
            int[] iArr2 = new int[r11];
            int i6 = 0;
            while (i6 < r11) {
                iArr2[i6] = (i6 < 0 || i6 >= basePowerOfTwo2) ? UIntArray.m9134getpVg5ArA(iArr, i6 - basePowerOfTwo2) : 0;
                i6++;
            }
            return UIntArray.m9129constructorimpl(iArr2);
        }
        int r3 = UIntArray.m9135getSizeimpl(iArr) + i5;
        int[] iArr3 = new int[r3];
        for (int i7 = 0; i7 < r3; i7++) {
            if (i7 >= 0 && i7 < basePowerOfTwo2) {
                i4 = 0;
            } else if (i7 == basePowerOfTwo2) {
                i4 = UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i7 - basePowerOfTwo2) << basePowerOfTwo3);
            } else {
                int i8 = basePowerOfTwo2 + 1;
                if (i7 < r02 + basePowerOfTwo2 && i8 <= i7) {
                    int i9 = i7 - basePowerOfTwo2;
                    i4 = UInt.m9074constructorimpl(UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i9 - 1) >>> (INSTANCE.getBasePowerOfTwo() - basePowerOfTwo3)) | UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i9) << basePowerOfTwo3));
                } else if (i7 == (r02 + i5) - 1) {
                    i4 = UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i7 - i5) >>> (INSTANCE.getBasePowerOfTwo() - basePowerOfTwo3));
                } else {
                    throw new RuntimeException(Intrinsics.stringPlus("Invalid case ", Integer.valueOf(i7)));
                }
            }
            iArr3[i7] = i4;
        }
        return UIntArray.m9129constructorimpl(iArr3);
    }

    @NotNull
    /* renamed from: shiftRight-Wj2uyrI  reason: not valid java name */
    public int[] m8503shiftRightWj2uyrI(@NotNull int[] iArr, int i3) {
        int i4;
        Intrinsics.checkNotNullParameter(iArr, "operand");
        if (UIntArray.m9137isEmptyimpl(iArr) || i3 == 0) {
            return iArr;
        }
        int basePowerOfTwo2 = i3 % getBasePowerOfTwo();
        int basePowerOfTwo3 = i3 / getBasePowerOfTwo();
        if (basePowerOfTwo3 >= UIntArray.m9135getSizeimpl(iArr)) {
            return m8475getZEROhP7Qyg();
        }
        if (basePowerOfTwo2 == 0) {
            return UIntArray.m9129constructorimpl(ArraysKt.copyOfRange(iArr, basePowerOfTwo3, UIntArray.m9135getSizeimpl(iArr)));
        }
        if (UIntArray.m9135getSizeimpl(iArr) > 1 && UIntArray.m9135getSizeimpl(iArr) - basePowerOfTwo3 == 1) {
            return new int[]{UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, UIntArray.m9135getSizeimpl(iArr) - 1) >>> basePowerOfTwo2)};
        }
        int r12 = UIntArray.m9135getSizeimpl(iArr) - basePowerOfTwo3;
        int[] iArr2 = new int[r12];
        for (int i5 = 0; i5 < r12; i5++) {
            if (i5 >= 0 && i5 < (UIntArray.m9135getSizeimpl(iArr) - 1) - basePowerOfTwo3) {
                int i6 = i5 + basePowerOfTwo3;
                i4 = UInt.m9074constructorimpl(UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i6 + 1) << (INSTANCE.getBasePowerOfTwo() - basePowerOfTwo2)) | UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i6) >>> basePowerOfTwo2));
            } else if (i5 == (UIntArray.m9135getSizeimpl(iArr) - 1) - basePowerOfTwo3) {
                i4 = UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i5 + basePowerOfTwo3) >>> basePowerOfTwo2);
            } else {
                throw new RuntimeException(Intrinsics.stringPlus("Invalid case ", Integer.valueOf(i5)));
            }
            iArr2[i5] = i4;
        }
        return m8500removeLeadingZeroshkIa6DI(UIntArray.m9129constructorimpl(iArr2));
    }

    @NotNull
    public final SignedUIntArray shl$bignum(@NotNull SignedUIntArray signedUIntArray, int i3) {
        Intrinsics.checkNotNullParameter(signedUIntArray, "<this>");
        return new SignedUIntArray(m8504shlWj2uyrI$bignum(signedUIntArray.m8525getUnsignedValuehP7Qyg(), i3), signedUIntArray.getSign(), (DefaultConstructorMarker) null);
    }

    @NotNull
    /* renamed from: shl-Wj2uyrI$bignum  reason: not valid java name */
    public final int[] m8504shlWj2uyrI$bignum(@NotNull int[] iArr, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        return m8502shiftLeftWj2uyrI(iArr, i3);
    }

    @NotNull
    public final SignedUIntArray shr$bignum(@NotNull SignedUIntArray signedUIntArray, int i3) {
        Intrinsics.checkNotNullParameter(signedUIntArray, "<this>");
        return new SignedUIntArray(m8505shrWj2uyrI$bignum(signedUIntArray.m8525getUnsignedValuehP7Qyg(), i3), signedUIntArray.getSign(), (DefaultConstructorMarker) null);
    }

    @NotNull
    /* renamed from: shr-Wj2uyrI$bignum  reason: not valid java name */
    public final int[] m8505shrWj2uyrI$bignum(@NotNull int[] iArr, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        return m8503shiftRightWj2uyrI(iArr, i3);
    }

    @NotNull
    /* renamed from: sqrt--ajY-9A  reason: not valid java name */
    public Pair<UIntArray, UIntArray> m8506sqrtajY9A(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        return m8436reqursiveSqrtajY9A(iArr);
    }

    @NotNull
    /* renamed from: sqrtInt-hkIa6DI$bignum  reason: not valid java name */
    public final int[] m8507sqrtInthkIa6DI$bignum(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        int[] iArr2 = iArr;
        while (true) {
            int[] r12 = m8505shrWj2uyrI$bignum(m8491plus00sMy4$bignum(iArr2, m8442basicDivide2Ynv0uTE(iArr, iArr2).getFirst().m9143unboximpl()), 1);
            if (m8448compareToYnv0uTE$bignum(r12, iArr2) >= 0) {
                return iArr2;
            }
            iArr2 = r12;
        }
    }

    @NotNull
    /* renamed from: subtract-0-0sMy4  reason: not valid java name */
    public int[] m8508subtract00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2) {
        int[] iArr3 = iArr2;
        Intrinsics.checkNotNullParameter(iArr, "first");
        Intrinsics.checkNotNullParameter(iArr3, "second");
        int[] r2 = m8500removeLeadingZeroshkIa6DI(iArr);
        int[] r12 = m8500removeLeadingZeroshkIa6DI(iArr3);
        Quadruple quadruple = m8446compareYnv0uTE(r2, r12) == 1 ? new Quadruple(Integer.valueOf(UIntArray.m9135getSizeimpl(r2)), Integer.valueOf(UIntArray.m9135getSizeimpl(r12)), UIntArray.m9127boximpl(r2), UIntArray.m9127boximpl(r12)) : new Quadruple(Integer.valueOf(UIntArray.m9135getSizeimpl(r12)), Integer.valueOf(UIntArray.m9135getSizeimpl(r2)), UIntArray.m9127boximpl(r12), UIntArray.m9127boximpl(r2));
        int intValue = ((Number) quadruple.component1()).intValue();
        int intValue2 = ((Number) quadruple.component2()).intValue();
        int[] r5 = ((UIntArray) quadruple.component3()).m9143unboximpl();
        int[] r3 = ((UIntArray) quadruple.component4()).m9143unboximpl();
        int i3 = intValue + 1;
        int[] iArr4 = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            iArr4[i4] = 0;
        }
        int[] r6 = UIntArray.m9129constructorimpl(iArr4);
        int i5 = 0;
        long j2 = 0;
        while (i5 < intValue2) {
            if (i5 >= UIntArray.m9135getSizeimpl(r5)) {
                System.out.println("Breakpoint");
            }
            if (i5 >= UIntArray.m9135getSizeimpl(r3)) {
                System.out.println("Breakpoint");
            }
            long r4 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(r5, i5)) & 4294967295L) - ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(r3, i5)) & 4294967295L)) - j2);
            UIntArray.m9139setVXSXFK8(r6, i5, UInt.m9074constructorimpl((int) r4));
            j2 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(r4 & m8472getOverflowMasksVKNKU()) >>> wordSizeInBits);
            i5++;
            r5 = r5;
        }
        int[] iArr5 = r5;
        while (j2 != 0) {
            long r8 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr5, i5)) & 4294967295L) - j2);
            UIntArray.m9139setVXSXFK8(r6, i5, UInt.m9074constructorimpl(UInt.m9074constructorimpl((int) r8) & m8469getBaseMaskIntpVg5ArA()));
            j2 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(r8 & m8472getOverflowMasksVKNKU()) >>> wordSizeInBits);
            i5++;
        }
        int[] iArr6 = iArr5;
        while (i5 < intValue) {
            UIntArray.m9139setVXSXFK8(r6, i5, UIntArray.m9134getpVg5ArA(iArr6, i5));
            i5++;
        }
        ArrayList arrayList = new ArrayList();
        for (int i6 : r6) {
            if (i6 == 0) {
                arrayList.add(UInt.m9068boximpl(i6));
            }
        }
        if (arrayList.isEmpty()) {
            return m8475getZEROhP7Qyg();
        }
        int i7 = -1;
        int length = r6.length - 1;
        while (true) {
            if (length < 0) {
                break;
            } else if (UInt.m9074constructorimpl(r6[length]) != 0) {
                i7 = length;
                break;
            } else {
                length--;
            }
        }
        return UIntArray.m9129constructorimpl(ArraysKt.copyOfRange(r6, 0, i7 + 1));
    }

    @NotNull
    public final SignedUIntArray times$bignum(@NotNull SignedUIntArray signedUIntArray, @NotNull SignedUIntArray signedUIntArray2) {
        Intrinsics.checkNotNullParameter(signedUIntArray, "<this>");
        Intrinsics.checkNotNullParameter(signedUIntArray2, "other");
        return signedMultiply(signedUIntArray, signedUIntArray2);
    }

    @NotNull
    /* renamed from: times-0-0sMy4$bignum  reason: not valid java name */
    public final int[] m8509times00sMy4$bignum(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        Intrinsics.checkNotNullParameter(iArr2, "other");
        return m8479multiply00sMy4(iArr, iArr2);
    }

    @NotNull
    /* renamed from: times-FE_7wA8$bignum  reason: not valid java name */
    public final int[] m8510timesFE_7wA8$bignum(@NotNull int[] iArr, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        return m8480multiplyFE_7wA8(iArr, i3);
    }

    @NotNull
    /* renamed from: toByteArray--ajY-9A  reason: not valid java name */
    public byte[] m8511toByteArrayajY9A(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        throw new NotImplementedError("An operation is not implemented: not implemented yet");
    }

    @NotNull
    /* renamed from: toString-LpG4sQ0  reason: not valid java name */
    public String m8512toStringLpG4sQ0(@NotNull int[] iArr, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
        int[] r5 = UIntArray.m9129constructorimpl(copyOf);
        int[] iArr2 = {UInt.m9074constructorimpl(i3)};
        StringBuilder sb = new StringBuilder();
        while (!UIntArray.m9133equalsimpl0(r5, m8475getZEROhP7Qyg())) {
            Pair<UIntArray, UIntArray> r52 = m8455divremYnv0uTE$bignum(r5, iArr2);
            if (UIntArray.m9137isEmptyimpl(r52.getSecond().m9143unboximpl())) {
                sb.append(0);
            } else {
                sb.append(UStringsKt.m10288toStringV7xB4Y4(UIntArray.m9134getpVg5ArA(r52.getSecond().m9143unboximpl(), 0), i3));
            }
            r5 = r52.getFirst().m9143unboximpl();
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "stringBuilder.toString()");
        return StringsKt___StringsKt.reversed((CharSequence) sb2).toString();
    }

    @NotNull
    /* renamed from: toUByteArray-CMMTdXw  reason: not valid java name */
    public byte[] m8513toUByteArrayCMMTdXw(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        throw new NotImplementedError("An operation is not implemented: not implemented yet");
    }

    @NotNull
    /* renamed from: toUIntArrayRepresentedAsTypedUByteArray-LpG4sQ0  reason: not valid java name */
    public UByte[] m8514toUIntArrayRepresentedAsTypedUByteArrayLpG4sQ0(@NotNull int[] iArr, @NotNull Endianness endianness) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(iArr, "operand");
        Intrinsics.checkNotNullParameter(endianness, "endianness");
        int i3 = WhenMappings.$EnumSwitchMapping$1[endianness.ordinal()];
        if (i3 == 1) {
            arrayList = new ArrayList();
            int i4 = 0;
            for (UInt r4 : UArraysKt.m9987reversedajY9A(iArr)) {
                int r42 = r4.m9126unboximpl();
                int r5 = i4 == UIntArray.m9135getSizeimpl(iArr) - 1 ? INSTANCE.m8487numberOfLeadingZerosInAWordWZ4Q5Ns(r42) / 8 : 0;
                i4++;
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, CollectionsKt.drop(CollectionsKt.listOf(UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(r42 >>> 24) & 255))), UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(r42 >>> 16) & 255))), UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(r42 >>> 8) & 255))), UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(r42 & 255)))), r5));
            }
        } else if (i3 == 2) {
            arrayList = new ArrayList();
            int i5 = 0;
            for (UInt r43 : UArraysKt.m9987reversedajY9A(iArr)) {
                int r44 = r43.m9126unboximpl();
                int r52 = i5 == UIntArray.m9135getSizeimpl(iArr) - 1 ? INSTANCE.m8487numberOfLeadingZerosInAWordWZ4Q5Ns(r44) / 8 : 0;
                i5++;
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, CollectionsKt.dropLast(CollectionsKt.listOf(UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(r44 & 255))), UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(r44 >>> 8) & 255))), UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(r44 >>> 16) & 255))), UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(r44 >>> 24) & 255)))), r52));
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Object[] array = arrayList.toArray(new UByte[0]);
        if (array != null) {
            return dropLeadingZeros((UByte[]) array);
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @NotNull
    /* renamed from: toUIntArrayRepresentedAsUByteArray-1NjfPbc  reason: not valid java name */
    public byte[] m8515toUIntArrayRepresentedAsUByteArray1NjfPbc(@NotNull int[] iArr, @NotNull Endianness endianness) {
        Collection collection;
        Intrinsics.checkNotNullParameter(iArr, "operand");
        Intrinsics.checkNotNullParameter(endianness, "endianness");
        int i3 = WhenMappings.$EnumSwitchMapping$1[endianness.ordinal()];
        if (i3 == 1) {
            ArrayList arrayList = new ArrayList();
            int i4 = 0;
            for (UInt r3 : UArraysKt.m9987reversedajY9A(iArr)) {
                int r32 = r3.m9126unboximpl();
                int r4 = i4 == UIntArray.m9135getSizeimpl(iArr) - 1 ? INSTANCE.m8487numberOfLeadingZerosInAWordWZ4Q5Ns(r32) / 8 : 0;
                i4++;
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, CollectionsKt.drop(CollectionsKt.listOf(UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(r32 >>> 24) & 255))), UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(r32 >>> 16) & 255))), UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(r32 >>> 8) & 255))), UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(r32 & 255)))), r4));
            }
            collection = arrayList;
        } else if (i3 == 2) {
            ArrayList arrayList2 = new ArrayList();
            for (int i5 : iArr) {
                ArrayList arrayList3 = new ArrayList();
                int i6 = 0;
                for (UInt r6 : UArraysKt.m9987reversedajY9A(iArr)) {
                    int r62 = r6.m9126unboximpl();
                    int r7 = i6 == UIntArray.m9135getSizeimpl(iArr) - 1 ? INSTANCE.m8487numberOfLeadingZerosInAWordWZ4Q5Ns(r62) / 8 : 0;
                    i6++;
                    CollectionsKt__MutableCollectionsKt.addAll(arrayList3, CollectionsKt.dropLast(CollectionsKt.listOf(UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(r62 & 255))), UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(r62 >>> 8) & 255))), UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(r62 >>> 16) & 255))), UByte.m8991boximpl(UByte.m8997constructorimpl((byte) UInt.m9074constructorimpl(UInt.m9074constructorimpl(r62 >>> 24) & 255)))), r7));
                }
                CollectionsKt__MutableCollectionsKt.addAll(arrayList2, arrayList3);
            }
            collection = UByteArray.m9048boximpl(UCollectionsKt.toUByteArray(arrayList2));
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return UCollectionsKt.toUByteArray(collection);
    }

    /* renamed from: toULongExact-q22ZNjw  reason: not valid java name */
    public final long m8516toULongExactq22ZNjw(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        if (UIntArray.m9135getSizeimpl(iArr) <= 2) {
            int r7 = UIntArray.m9135getSizeimpl(iArr) - 1;
            long j2 = 0;
            if (r7 >= 0) {
                while (true) {
                    int i3 = r7 - 1;
                    j2 = a.d(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr, r7)) & 4294967295L) << (r7 * wordSizeInBits), j2);
                    if (i3 < 0) {
                        break;
                    }
                    r7 = i3;
                }
            }
            return j2;
        }
        throw new ArithmeticException(Intrinsics.stringPlus("Exact conversion not possible, operand size ", Integer.valueOf(UIntArray.m9135getSizeimpl(iArr))));
    }

    @NotNull
    /* renamed from: toUnsignedIntArrayCodeFormat--ajY-9A  reason: not valid java name */
    public final String m8517toUnsignedIntArrayCodeFormatajY9A(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "array");
        return CollectionsKt___CollectionsKt.joinToString$default(UIntArray.m9127boximpl(iArr), ", ", "uintArrayOf(", ")", 0, (CharSequence) null, BigInteger32Arithmetic$toUnsignedIntArrayCodeFormat$1.INSTANCE, 24, (Object) null);
    }

    @NotNull
    /* renamed from: toomCook3Multiply-0-0sMy4  reason: not valid java name */
    public final int[] m8518toomCook3Multiply00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Collection collection;
        Collection collection2;
        Intrinsics.checkNotNullParameter(iArr, "firstUnchecked");
        Intrinsics.checkNotNullParameter(iArr2, "secondUnchecked");
        if (UIntArray.m9135getSizeimpl(iArr) % 3 != 0) {
            UIntArray r02 = UIntArray.m9127boximpl(iArr);
            int r3 = (((UIntArray.m9135getSizeimpl(iArr) + 2) / 3) * 3) - UIntArray.m9135getSizeimpl(iArr);
            int[] iArr3 = new int[r3];
            for (int i3 = 0; i3 < r3; i3++) {
                iArr3[i3] = 0;
            }
            collection = CollectionsKt.plus(r02, UIntArray.m9127boximpl(UIntArray.m9129constructorimpl(iArr3)));
        } else {
            collection = UIntArray.m9127boximpl(iArr);
        }
        int[] uIntArray = UCollectionsKt.toUIntArray(collection);
        if (UIntArray.m9135getSizeimpl(iArr2) % 3 != 0) {
            UIntArray r03 = UIntArray.m9127boximpl(iArr2);
            int r32 = (((UIntArray.m9135getSizeimpl(iArr2) + 2) / 3) * 3) - UIntArray.m9135getSizeimpl(iArr2);
            int[] iArr4 = new int[r32];
            for (int i4 = 0; i4 < r32; i4++) {
                iArr4[i4] = 0;
            }
            collection2 = CollectionsKt.plus(r03, UIntArray.m9127boximpl(UIntArray.m9129constructorimpl(iArr4)));
        } else {
            collection2 = UIntArray.m9127boximpl(iArr2);
        }
        int[] uIntArray2 = UCollectionsKt.toUIntArray(collection2);
        int r04 = UIntArray.m9135getSizeimpl(uIntArray);
        int r33 = UIntArray.m9135getSizeimpl(uIntArray2);
        Pair pair = r04 > r33 ? new Pair(UIntArray.m9127boximpl(uIntArray), UIntArray.m9127boximpl(m8456extendUIntArray9fY048w(uIntArray2, r04 - r33, 0))) : r04 < r33 ? new Pair(UIntArray.m9127boximpl(m8456extendUIntArray9fY048w(uIntArray, r33 - r04, 0)), UIntArray.m9127boximpl(uIntArray2)) : new Pair(UIntArray.m9127boximpl(uIntArray), UIntArray.m9127boximpl(uIntArray2));
        int[] r05 = ((UIntArray) pair.component1()).m9143unboximpl();
        int[] r34 = ((UIntArray) pair.component2()).m9143unboximpl();
        int max = (Math.max(UIntArray.m9135getSizeimpl(uIntArray), UIntArray.m9135getSizeimpl(uIntArray2)) + 2) / 3;
        SignedUIntArray signedUIntArray = new SignedUIntArray(UCollectionsKt.toUIntArray(UArraysKt.m10050slicetAntMlw(r05, RangesKt.until(0, max))), true, (DefaultConstructorMarker) null);
        int i5 = max * 2;
        SignedUIntArray signedUIntArray2 = new SignedUIntArray(UCollectionsKt.toUIntArray(UArraysKt.m10050slicetAntMlw(r05, RangesKt.until(max, i5))), true, (DefaultConstructorMarker) null);
        int i6 = max * 3;
        SignedUIntArray signedUIntArray3 = new SignedUIntArray(UCollectionsKt.toUIntArray(UArraysKt.m10050slicetAntMlw(r05, RangesKt.until(i5, i6))), true, (DefaultConstructorMarker) null);
        SignedUIntArray signedUIntArray4 = new SignedUIntArray(UCollectionsKt.toUIntArray(UArraysKt.m10050slicetAntMlw(r34, RangesKt.until(0, max))), true, (DefaultConstructorMarker) null);
        SignedUIntArray signedUIntArray5 = new SignedUIntArray(UCollectionsKt.toUIntArray(UArraysKt.m10050slicetAntMlw(r34, RangesKt.until(max, i5))), true, (DefaultConstructorMarker) null);
        SignedUIntArray signedUIntArray6 = new SignedUIntArray(UCollectionsKt.toUIntArray(UArraysKt.m10050slicetAntMlw(r34, RangesKt.until(i5, i6))), true, (DefaultConstructorMarker) null);
        SignedUIntArray plus$bignum = plus$bignum(signedUIntArray, signedUIntArray3);
        SignedUIntArray plus$bignum2 = plus$bignum(plus$bignum, signedUIntArray2);
        SignedUIntArray minus$bignum = minus$bignum(plus$bignum, signedUIntArray2);
        SignedUIntArray plus$bignum3 = plus$bignum(minus$bignum, signedUIntArray3);
        SignedUIntArray signedUIntArray7 = SIGNED_POSITIVE_TWO;
        SignedUIntArray minus$bignum2 = minus$bignum(times$bignum(plus$bignum3, signedUIntArray7), signedUIntArray);
        SignedUIntArray plus$bignum4 = plus$bignum(signedUIntArray4, signedUIntArray6);
        SignedUIntArray plus$bignum5 = plus$bignum(plus$bignum4, signedUIntArray5);
        SignedUIntArray minus$bignum3 = minus$bignum(plus$bignum4, signedUIntArray5);
        SignedUIntArray minus$bignum4 = minus$bignum(times$bignum(plus$bignum(minus$bignum3, signedUIntArray6), signedUIntArray7), signedUIntArray4);
        SignedUIntArray times$bignum = times$bignum(signedUIntArray, signedUIntArray4);
        SignedUIntArray times$bignum2 = times$bignum(plus$bignum2, plus$bignum5);
        SignedUIntArray times$bignum3 = times$bignum(minus$bignum, minus$bignum3);
        SignedUIntArray times$bignum4 = times$bignum(minus$bignum2, minus$bignum4);
        SignedUIntArray times$bignum5 = times$bignum(signedUIntArray3, signedUIntArray6);
        SignedUIntArray div$bignum = div$bignum(minus$bignum(times$bignum4, times$bignum2), new SignedUIntArray(new int[]{3}, true, (DefaultConstructorMarker) null));
        SignedUIntArray shr$bignum = shr$bignum(minus$bignum(times$bignum2, times$bignum3), 1);
        SignedUIntArray minus$bignum5 = minus$bignum(times$bignum3, times$bignum);
        SignedUIntArray plus$bignum6 = plus$bignum(shr$bignum(minus$bignum(minus$bignum5, div$bignum), 1), times$bignum(signedUIntArray7, times$bignum5));
        SignedUIntArray minus$bignum6 = minus$bignum(plus$bignum(minus$bignum5, shr$bignum), times$bignum5);
        SignedUIntArray minus$bignum7 = minus$bignum(shr$bignum, plus$bignum6);
        int i7 = max * wordSizeInBits;
        return plus$bignum(plus$bignum(plus$bignum(plus$bignum(times$bignum, shl$bignum(minus$bignum7, i7)), shl$bignum(minus$bignum6, i7 * 2)), shl$bignum(plus$bignum6, i7 * 3)), shl$bignum(times$bignum5, i7 * 4)).m8525getUnsignedValuehP7Qyg();
    }

    /* renamed from: trailingZeroBits--ajY-9A  reason: not valid java name */
    public int m8519trailingZeroBitsajY9A(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "value");
        if (UArraysKt.m9544contentEqualsKJPZfPQ(iArr, m8475getZEROhP7Qyg())) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 : iArr) {
            if (i3 != 0) {
                break;
            }
            arrayList.add(UInt.m9068boximpl(i3));
        }
        int size = arrayList.size();
        if (size == UIntArray.m9135getSizeimpl(iArr)) {
            return 0;
        }
        return (size * 63) + m8520trailingZeroBitsWZ4Q5Ns(UIntArray.m9134getpVg5ArA(iArr, size));
    }

    /* renamed from: trailingZeroBits-WZ4Q5Ns  reason: not valid java name */
    public final int m8520trailingZeroBitsWZ4Q5Ns(int i3) {
        return m8488numberOfTrailingZerosInAWordWZ4Q5Ns(i3);
    }

    @NotNull
    /* renamed from: xor-0-0sMy4  reason: not valid java name */
    public int[] m8521xor00sMy4(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.checkNotNullParameter(iArr, "operand");
        Intrinsics.checkNotNullParameter(iArr2, "mask");
        int r02 = UIntArray.m9135getSizeimpl(iArr);
        int[] iArr3 = new int[r02];
        int i3 = 0;
        while (i3 < r02) {
            iArr3[i3] = i3 < UIntArray.m9135getSizeimpl(iArr2) ? UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i3) ^ UIntArray.m9134getpVg5ArA(iArr2, i3)) : UInt.m9074constructorimpl(UIntArray.m9134getpVg5ArA(iArr, i3));
            i3++;
        }
        return m8500removeLeadingZeroshkIa6DI(UIntArray.m9129constructorimpl(iArr3));
    }

    private final Byte[] dropLeadingZeros(Byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (Byte b3 : bArr) {
            if (z2) {
                arrayList.add(b3);
            } else if (b3.byteValue() != 0) {
                arrayList.add(b3);
                z2 = true;
            }
        }
        Object[] array = arrayList.toArray(new Byte[0]);
        if (array != null) {
            return (Byte[]) array;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final UByte[] dropLeadingZeros(UByte[] uByteArr) {
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (UByte uByte : uByteArr) {
            if (z2) {
                arrayList.add(uByte);
            } else if (uByte.m9047unboximpl() != UByte.m8997constructorimpl((byte) 0)) {
                arrayList.add(uByte);
                z2 = true;
            }
        }
        Object[] array = arrayList.toArray(new UByte[0]);
        if (array != null) {
            return (UByte[]) array;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @Deprecated(message = "Old byte conversion API")
    private final Pair<UIntArray, Sign> oldFromByteArray(byte[] bArr) {
        Sign sign;
        List<UInt> emptyList;
        int i3 = (bArr[0] >>> 7) & 1;
        List<List> chunked = CollectionsKt.chunked(CollectionsKt.reversed(ArraysKt.toList(bArr)), 4);
        if (i3 == 0) {
            sign = Sign.POSITIVE;
        } else if (i3 == 1) {
            sign = Sign.NEGATIVE;
        } else {
            throw new RuntimeException("Invalid sign value when converting from byte array");
        }
        int i4 = WhenMappings.$EnumSwitchMapping$0[sign.ordinal()];
        if (i4 == 1) {
            throw new RuntimeException("Bug in fromByteArray, sign shouldn't ever be zero at this point.");
        } else if (i4 == 2) {
            ArrayList arrayList = new ArrayList();
            for (List list : chunked) {
                int i5 = 0;
                int i6 = 0;
                for (Object next : CollectionsKt.reversed(list)) {
                    int i7 = i6 + 1;
                    if (i6 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    i5 = a.a(UInt.m9074constructorimpl(UInt.m9074constructorimpl(((Number) next).byteValue()) & 255) << (((list.size() - 1) * 8) - (i6 * 8)), i5);
                    i6 = i7;
                }
                int size = (4 - list.size()) * 8;
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, UIntArray.m9127boximpl(new int[]{UInt.m9074constructorimpl(UInt.m9074constructorimpl(i5 << size) >>> size)}));
            }
            int[] uIntArray = UCollectionsKt.toUIntArray(arrayList);
            if (UArraysKt.m9544contentEqualsKJPZfPQ(uIntArray, m8475getZEROhP7Qyg())) {
                return new Pair<>(UIntArray.m9127boximpl(m8475getZEROhP7Qyg()), Sign.ZERO);
            }
            int lastIndex = ArraysKt.getLastIndex(uIntArray);
            while (true) {
                if (lastIndex >= 0) {
                    if (UIntArray.m9134getpVg5ArA(uIntArray, lastIndex) != 0) {
                        emptyList = UArraysKt.m10109takeqFRl0hI(uIntArray, lastIndex + 1);
                        break;
                    }
                    lastIndex--;
                } else {
                    emptyList = CollectionsKt.emptyList();
                    break;
                }
            }
            return new Pair<>(UIntArray.m9127boximpl(m8500removeLeadingZeroshkIa6DI(UCollectionsKt.toUIntArray(emptyList))), sign);
        } else if (i4 == 3) {
            ArrayList arrayList2 = new ArrayList();
            for (List list2 : chunked) {
                int i8 = 0;
                int i9 = 0;
                for (Object next2 : CollectionsKt.reversed(list2)) {
                    int i10 = i9 + 1;
                    if (i9 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    i8 = a.a(UInt.m9074constructorimpl(((Number) next2).byteValue()) << (((list2.size() - 1) * 8) - (i9 * 8)), i8);
                    i9 = i10;
                }
                CollectionsKt__MutableCollectionsKt.addAll(arrayList2, UIntArray.m9127boximpl(new int[]{i8}));
            }
            int[] uIntArray2 = UCollectionsKt.toUIntArray(arrayList2);
            int[] r2 = m8478minusFE_7wA8$bignum(uIntArray2, 1);
            ArrayList arrayList3 = new ArrayList(UIntArray.m9135getSizeimpl(r2));
            for (int i11 : r2) {
                arrayList3.add(UInt.m9068boximpl(UInt.m9074constructorimpl(~i11)));
            }
            int[] uIntArray3 = UCollectionsKt.toUIntArray(arrayList3);
            if (UArraysKt.m9544contentEqualsKJPZfPQ(uIntArray2, m8475getZEROhP7Qyg())) {
                return new Pair<>(UIntArray.m9127boximpl(m8475getZEROhP7Qyg()), Sign.ZERO);
            }
            return new Pair<>(UIntArray.m9127boximpl(m8500removeLeadingZeroshkIa6DI(uIntArray3)), sign);
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }
}
