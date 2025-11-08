package com.ionspin.kotlin.bignum.integer.base63;

import A.a;
import androidx.camera.core.impl.i;
import androidx.collection.SieveCacheKt;
import com.ionspin.kotlin.bignum.integer.BigInteger;
import com.ionspin.kotlin.bignum.integer.BigIntegerList63Arithmetic;
import com.ionspin.kotlin.bignum.integer.Quadruple;
import com.ionspin.kotlin.bignum.integer.base32.BigInteger32Arithmetic;
import com.ionspin.kotlin.bignum.integer.base63.array.BigInteger63Arithmetic;
import com.ionspin.kotlin.bignum.integer.util.DigitUtilKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.collections.CollectionsKt;
import kotlin.collections.UCollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.UStringsKt;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\bC\bÀ\u0002\u0018\u00002\u00020\u0001:\u0002Î\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J-\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J=\u0010/\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004002\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004ø\u0001\u0000J+\u00103\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004ø\u0001\u0000J6\u00104\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004002\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000ø\u0001\u0000¢\u0006\u0002\b5J.\u00106\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0010ø\u0001\u0000J!\u00107\u001a\u0002082\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u00109\u001a\u00020:H\u0016ø\u0001\u0000J\u001b\u0010;\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b=\u0010>J\u0019\u0010;\u001a\u00020\u00172\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J'\u0010?\u001a\u00020\u00172\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J#\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010-\u001a\u00020AH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bB\u0010CJ$\u0010D\u001a\u00020A2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bE\u0010FJ\u001d\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004ø\u0001\u0000J/\u0010H\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004002\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004ø\u0001\u0000J%\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010L\u001a\u00020\u0017ø\u0001\u0000J?\u0010M\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004002\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J-\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002ø\u0001\u0000J7\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010Q\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bR\u0010SJ\u0019\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010U\u001a\u00020VH\u0016ø\u0001\u0000J\u0019\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010X\u001a\u00020YH\u0016ø\u0001\u0000J\u0019\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010[\u001a\u00020\u0017H\u0016ø\u0001\u0000J\u0019\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010]\u001a\u00020:H\u0016ø\u0001\u0000J\u0019\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010_\u001a\u00020`H\u0016ø\u0001\u0000J#\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010b\u001a\u00020cH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bd\u0010eJ#\u0010f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010X\u001a\u00020gH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bh\u0010iJ#\u0010j\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010k\u001a\u00020lH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bm\u0010nJ#\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010p\u001a\u00020\u0005H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bq\u0010rJ#\u0010s\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010t\u001a\u00020uH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bv\u0010wJ-\u0010x\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J+\u0010y\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004ø\u0001\u0000J)\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b{\u0010|J/\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010+\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b}\u0010~J-\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J)\u0010\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u0017002\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004ø\u0001\u0000JF\u0010\u001a!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00170\u00012\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004ø\u0001\u0000J \u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J\u001a\u0010\u0001\u001a\u00020:2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J\u001f\u0010\u0001\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\u0005H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010>J\u001d\u0010\u0001\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010>J.\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J%\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020\u0017H\u0016ø\u0001\u0000J*\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0007\u0010\u0001\u001a\u00020:H\u0016ø\u0001\u0000J9\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010Q\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010SJ2\u0010\u0001\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004002\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J\u001f\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004ø\u0001\u0000J2\u0010\u0001\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004002\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002ø\u0001\u0000J1\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u00109\u001a\u00020:2\u0007\u0010\u0001\u001a\u000208H\u0016ø\u0001\u0000J)\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0007\u0010\u0001\u001a\u00020\u0017H\u0016ø\u0001\u0000J)\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0007\u0010\u0001\u001a\u00020\u0017H\u0016ø\u0001\u0000J\u0019\u0010\u0001\u001a\u00020\t2\u0006\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\tH\u0002J\u0019\u0010\u0001\u001a\u00020\t2\u0006\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\tH\u0002J\u0019\u0010\u0001\u001a\u00020\t2\u0006\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\tH\u0002J\u0019\u0010\u0001\u001a\u00020\t2\u0006\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\tH\u0002J\u0019\u0010\u0001\u001a\u00020\t2\u0006\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\tH\u0002J2\u0010 \u0001\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004002\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J&\u0010¡\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000ø\u0001\u0000¢\u0006\u0003\b¢\u0001J.\u0010£\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J\u001a\u0010¤\u0001\u001a\u00020Y2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J$\u0010¥\u0001\u001a\u00030\u00012\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0007\u0010\u0001\u001a\u00020\u0017H\u0016ø\u0001\u0000J)\u0010¦\u0001\u001a\u00020g2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0006\b§\u0001\u0010¨\u0001J.\u0010©\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\r\u0010ª\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\r\u0010«\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004ø\u0001\u0000J\u001d\u0010¬\u0001\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b­\u0001\u0010>J\u001a\u0010¬\u0001\u001a\u00020\u00172\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J.\u0010®\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016ø\u0001\u0000J$\u0010,\u001a\u00020\t*\u00020\t2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0004ø\u0001\u0000¢\u0006\u0003\b¯\u0001J,\u0010°\u0001\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0007\u0010±\u0001\u001a\u00020\u0005H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b²\u0001\u0010³\u0001J,\u0010°\u0001\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00050\u00042\r\u0010±\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002ø\u0001\u0000¢\u0006\u0003\b´\u0001J\u001d\u0010µ\u0001\u001a\u00020\t*\u00020\t2\u0007\u0010±\u0001\u001a\u00020\tH\u0002¢\u0006\u0003\b¶\u0001J1\u0010µ\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0007\u0010±\u0001\u001a\u00020\u0005H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b·\u0001\u0010~J2\u0010µ\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\r\u0010±\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002ø\u0001\u0000¢\u0006\u0003\b¶\u0001JD\u0010¸\u0001\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000400*\b\u0012\u0004\u0012\u00020\u00050\u00042\r\u0010±\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0004ø\u0001\u0000¢\u0006\u0003\b¹\u0001J!\u0010º\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u00020AH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b»\u0001\u0010CJ\u001d\u0010¼\u0001\u001a\u00020\t*\u00020\t2\u0007\u0010±\u0001\u001a\u00020\tH\u0002¢\u0006\u0003\b½\u0001J1\u0010¼\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0007\u0010±\u0001\u001a\u00020\u0005H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b¾\u0001\u0010~J2\u0010¼\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\r\u0010±\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002ø\u0001\u0000¢\u0006\u0003\b½\u0001J\u001d\u0010¿\u0001\u001a\u00020\t*\u00020\t2\u0007\u0010±\u0001\u001a\u00020\tH\u0002¢\u0006\u0003\bÀ\u0001J1\u0010¿\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0007\u0010±\u0001\u001a\u00020\u0005H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÁ\u0001\u0010~J2\u0010¿\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\r\u0010±\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002ø\u0001\u0000¢\u0006\u0003\bÀ\u0001J\u001d\u0010Â\u0001\u001a\u00020\t*\u00020\t2\u0007\u0010±\u0001\u001a\u00020\tH\u0002¢\u0006\u0003\bÃ\u0001J1\u0010Â\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0007\u0010±\u0001\u001a\u00020\u0005H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÄ\u0001\u0010~J2\u0010Â\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\r\u0010±\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002ø\u0001\u0000¢\u0006\u0003\bÃ\u0001J\u001d\u0010Å\u0001\u001a\u00020\t*\u00020\t2\u0007\u0010\u0001\u001a\u00020\u0017H\u0004¢\u0006\u0003\bÆ\u0001J,\u0010Å\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0007\u0010\u0001\u001a\u00020\u0017H\u0004ø\u0001\u0000¢\u0006\u0003\bÆ\u0001J\u001d\u0010Ç\u0001\u001a\u00020\t*\u00020\t2\u0007\u0010\u0001\u001a\u00020\u0017H\u0004¢\u0006\u0003\bÈ\u0001J,\u0010Ç\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0007\u0010\u0001\u001a\u00020\u0017H\u0004ø\u0001\u0000¢\u0006\u0003\bÈ\u0001J\u001d\u0010É\u0001\u001a\u00020\t*\u00020\t2\u0007\u0010±\u0001\u001a\u00020\tH\u0002¢\u0006\u0003\bÊ\u0001J1\u0010É\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0007\u0010±\u0001\u001a\u00020\u0005H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bË\u0001\u0010~J2\u0010É\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\r\u0010±\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002ø\u0001\u0000¢\u0006\u0003\bÊ\u0001J$\u0010Ì\u0001\u001a\u00020A*\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0005\bÍ\u0001\u0010FR\u001d\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u001f\u0010\u0012\u001a\u00020\u0005XDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u0017XD¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001f\u0010\u001a\u001a\u00020\u0005XDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u001b\u0010\u0014R\u000e\u0010\u001c\u001a\u00020\u0017XT¢\u0006\u0002\n\u0000R\u001f\u0010\u001d\u001a\u00020\u0005XDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u001e\u0010\u0014R\u001f\u0010\u001f\u001a\u00020\u0005XDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b \u0010\u0014R\"\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\"ø\u0001\u0000¢\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u000e\u0010&\u001a\u00020\u0017XT¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\u00020\u0017XD¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0019\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Ï\u0001"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/base63/BigInteger63LinkedListArithmetic;", "Lcom/ionspin/kotlin/bignum/integer/BigIntegerList63Arithmetic;", "()V", "ONE", "", "Lkotlin/ULong;", "getONE", "()Ljava/util/List;", "SIGNED_POSITIVE_TWO", "Lcom/ionspin/kotlin/bignum/integer/base63/BigInteger63LinkedListArithmetic$SignedULongArray;", "getSIGNED_POSITIVE_TWO", "()Lcom/ionspin/kotlin/bignum/integer/base63/BigInteger63LinkedListArithmetic$SignedULongArray;", "TEN", "getTEN", "TWO", "getTWO", "ZERO", "getZERO", "baseMask", "getBaseMask-s-VKNKU", "()J", "J", "basePowerOfTwo", "", "getBasePowerOfTwo", "()I", "highMask", "getHighMask-s-VKNKU", "karatsubaThreshold", "lowMask", "getLowMask-s-VKNKU", "overflowMask", "getOverflowMask-s-VKNKU", "powersOf10", "", "getPowersOf10", "()[Ljava/util/List;", "[Ljava/util/List;", "toomCookThreshold", "wordSizeInBits", "getWordSizeInBits", "add", "first", "second", "and", "operand", "mask", "baseDivide", "Lkotlin/Pair;", "unnormalizedDividend", "unnormalizedDivisor", "basecaseMultiply", "basecaseSqrt", "basecaseSqrt$bignum", "binaryGcd", "bitAt", "", "position", "", "bitLength", "value", "bitLength-VKZWuLQ", "(J)I", "compare", "convertFrom32BitRepresentation", "Lkotlin/UIntArray;", "convertFrom32BitRepresentation--ajY-9A$bignum", "([I)Ljava/util/List;", "convertTo32BitRepresentation", "convertTo32BitRepresentation-g_c56RQ", "(Ljava/util/List;)[I", "convertTo64BitRepresentation", "d1ReciprocalRecursiveWordVersion", "a", "denormalize", "remainderNormalized", "normalizationShift", "divide", "euclideanGcd", "extendULongArray", "original", "numberOfWords", "extendULongArray-aPcrCvc", "(Ljava/util/List;IJ)Ljava/util/List;", "fromByte", "byte", "", "fromByteArray", "source", "", "fromInt", "int", "fromLong", "long", "fromShort", "short", "", "fromUByte", "uByte", "Lkotlin/UByte;", "fromUByte-7apg3OU", "(B)Ljava/util/List;", "fromUByteArray", "Lkotlin/UByteArray;", "fromUByteArray-GBYM_sE", "([B)Ljava/util/List;", "fromUInt", "uInt", "Lkotlin/UInt;", "fromUInt-WZ4Q5Ns", "(I)Ljava/util/List;", "fromULong", "uLong", "fromULong-VKZWuLQ", "(J)Ljava/util/List;", "fromUShort", "uShort", "Lkotlin/UShort;", "fromUShort-xj2QHRw", "(S)Ljava/util/List;", "gcd", "karatsubaMultiply", "multiply", "multiply-PWzV0Is", "(JJ)Ljava/util/List;", "multiply-2TYgG_w", "(Ljava/util/List;J)Ljava/util/List;", "normalize", "Lkotlin/Triple;", "dividend", "divisor", "not", "numberOfDecimalDigits", "numberOfLeadingZerosInAWord", "numberOfLeadingZerosInAWord-VKZWuLQ", "numberOfTrailingZerosInAWord", "numberOfTrailingZerosInAWord-VKZWuLQ", "or", "parseForBase", "number", "", "base", "pow", "exponent", "prependULongArray", "prependULongArray-aPcrCvc", "reciprocal", "removeLeadingZeros", "bigInteger", "reqursiveSqrt", "setBitAt", "bit", "shiftLeft", "places", "shiftRight", "signedAdd", "signedDivide", "signedMultiply", "signedRemainder", "signedSubtract", "sqrt", "sqrtInt", "sqrtInt$bignum", "subtract", "toByteArray", "toString", "toUByteArray", "toUByteArray-NTtOWj4", "(Ljava/util/List;)[B", "toomCook3Multiply", "firstUnchecked", "secondUnchecked", "trailingZeroBits", "trailingZeroBits-VKZWuLQ", "xor", "and$bignum", "compareTo", "other", "compareTo-2TYgG_w$bignum", "(Ljava/util/List;J)I", "compareTo$bignum", "div", "div$bignum", "div-2TYgG_w$bignum", "divrem", "divrem$bignum", "from32Bit", "from32Bit--ajY-9A$bignum", "minus", "minus$bignum", "minus-2TYgG_w$bignum", "plus", "plus$bignum", "plus-2TYgG_w$bignum", "rem", "rem$bignum", "rem-2TYgG_w$bignum", "shl", "shl$bignum", "shr", "shr$bignum", "times", "times$bignum", "times-2TYgG_w$bignum", "to32Bit", "to32Bit-g_c56RQ$bignum", "SignedULongArray", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BigInteger63LinkedListArithmetic implements BigIntegerList63Arithmetic {
    @NotNull
    public static final BigInteger63LinkedListArithmetic INSTANCE;
    @NotNull
    private static final List<ULong> ONE = CollectionsKt.listOf(ULong.m9147boximpl(1));
    @NotNull
    private static final SignedULongArray SIGNED_POSITIVE_TWO;
    @NotNull
    private static final List<ULong> TEN = CollectionsKt.listOf(ULong.m9147boximpl(10));
    @NotNull
    private static final List<ULong> TWO = CollectionsKt.listOf(ULong.m9147boximpl(2));
    @NotNull
    private static final List<ULong> ZERO = CollectionsKt.listOf(ULong.m9147boximpl(0));
    private static final long baseMask = Long.MAX_VALUE;
    private static final int basePowerOfTwo = 63;
    private static final long highMask = 9223372032559808512L;
    public static final int karatsubaThreshold = 120;
    private static final long lowMask = 4294967295L;
    private static final long overflowMask = Long.MIN_VALUE;
    @NotNull
    private static final List<ULong>[] powersOf10;
    public static final int toomCookThreshold = 15000;
    private static final int wordSizeInBits = 63;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001e\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0012\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003ø\u0001\u0000J\t\u0010\r\u001a\u00020\u0006HÆ\u0003J&\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001ø\u0001\u0000J\u0013\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/base63/BigInteger63LinkedListArithmetic$SignedULongArray;", "", "unsignedValue", "", "Lkotlin/ULong;", "sign", "", "(Ljava/util/List;Z)V", "getSign", "()Z", "getUnsignedValue", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class SignedULongArray {
        private final boolean sign;
        @NotNull
        private final List<ULong> unsignedValue;

        public SignedULongArray(@NotNull List<ULong> list, boolean z2) {
            Intrinsics.checkNotNullParameter(list, "unsignedValue");
            this.unsignedValue = list;
            this.sign = z2;
        }

        public static /* synthetic */ SignedULongArray copy$default(SignedULongArray signedULongArray, List<ULong> list, boolean z2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                list = signedULongArray.unsignedValue;
            }
            if ((i3 & 2) != 0) {
                z2 = signedULongArray.sign;
            }
            return signedULongArray.copy(list, z2);
        }

        @NotNull
        public final List<ULong> component1() {
            return this.unsignedValue;
        }

        public final boolean component2() {
            return this.sign;
        }

        @NotNull
        public final SignedULongArray copy(@NotNull List<ULong> list, boolean z2) {
            Intrinsics.checkNotNullParameter(list, "unsignedValue");
            return new SignedULongArray(list, z2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SignedULongArray)) {
                return false;
            }
            SignedULongArray signedULongArray = (SignedULongArray) obj;
            return Intrinsics.areEqual((Object) this.unsignedValue, (Object) signedULongArray.unsignedValue) && this.sign == signedULongArray.sign;
        }

        public final boolean getSign() {
            return this.sign;
        }

        @NotNull
        public final List<ULong> getUnsignedValue() {
            return this.unsignedValue;
        }

        public int hashCode() {
            int hashCode = this.unsignedValue.hashCode() * 31;
            boolean z2 = this.sign;
            if (z2) {
                z2 = true;
            }
            return hashCode + (z2 ? 1 : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder("SignedULongArray(unsignedValue=");
            sb.append(this.unsignedValue);
            sb.append(", sign=");
            return i.c(sb, this.sign, ')');
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v80, resolved type: java.util.List<kotlin.ULong>[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            com.ionspin.kotlin.bignum.integer.base63.BigInteger63LinkedListArithmetic r0 = new com.ionspin.kotlin.bignum.integer.base63.BigInteger63LinkedListArithmetic
            r0.<init>()
            INSTANCE = r0
            r1 = 0
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r1)
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            ZERO = r3
            r3 = 1
            kotlin.ULong r5 = kotlin.ULong.m9147boximpl(r3)
            java.util.List r5 = kotlin.collections.CollectionsKt.listOf(r5)
            ONE = r5
            r5 = 2
            kotlin.ULong r5 = kotlin.ULong.m9147boximpl(r5)
            java.util.List r5 = kotlin.collections.CollectionsKt.listOf(r5)
            TWO = r5
            r5 = 10
            kotlin.ULong r7 = kotlin.ULong.m9147boximpl(r5)
            java.util.List r7 = kotlin.collections.CollectionsKt.listOf(r7)
            TEN = r7
            r7 = 63
            basePowerOfTwo = r7
            wordSizeInBits = r7
            r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            baseMask = r8
            r8 = 4294967295(0xffffffff, double:2.1219957905E-314)
            lowMask = r8
            r8 = 9223372032559808512(0x7fffffff00000000, double:NaN)
            highMask = r8
            r8 = -9223372036854775808
            overflowMask = r8
            com.ionspin.kotlin.bignum.integer.base63.BigInteger63LinkedListArithmetic$SignedULongArray r8 = new com.ionspin.kotlin.bignum.integer.base63.BigInteger63LinkedListArithmetic$SignedULongArray
            java.util.List r0 = r0.getTWO()
            r9 = 1
            r8.<init>(r0, r9)
            SIGNED_POSITIVE_TWO = r8
            kotlin.ULong r0 = kotlin.ULong.m9147boximpl(r3)
            java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r0)
            kotlin.ULong r8 = kotlin.ULong.m9147boximpl(r5)
            java.util.List r8 = kotlin.collections.CollectionsKt.listOf(r8)
            r10 = 100
            kotlin.ULong r10 = kotlin.ULong.m9147boximpl(r10)
            java.util.List r10 = kotlin.collections.CollectionsKt.listOf(r10)
            r11 = 1000(0x3e8, double:4.94E-321)
            kotlin.ULong r11 = kotlin.ULong.m9147boximpl(r11)
            java.util.List r11 = kotlin.collections.CollectionsKt.listOf(r11)
            r12 = 10000(0x2710, double:4.9407E-320)
            kotlin.ULong r12 = kotlin.ULong.m9147boximpl(r12)
            java.util.List r12 = kotlin.collections.CollectionsKt.listOf(r12)
            r13 = 100000(0x186a0, double:4.94066E-319)
            kotlin.ULong r13 = kotlin.ULong.m9147boximpl(r13)
            java.util.List r13 = kotlin.collections.CollectionsKt.listOf(r13)
            r14 = 1000000(0xf4240, double:4.940656E-318)
            kotlin.ULong r14 = kotlin.ULong.m9147boximpl(r14)
            java.util.List r14 = kotlin.collections.CollectionsKt.listOf(r14)
            r15 = 10000000(0x989680, double:4.9406565E-317)
            kotlin.ULong r15 = kotlin.ULong.m9147boximpl(r15)
            java.util.List r15 = kotlin.collections.CollectionsKt.listOf(r15)
            r16 = 100000000(0x5f5e100, double:4.94065646E-316)
            kotlin.ULong r16 = kotlin.ULong.m9147boximpl(r16)
            java.util.List r16 = kotlin.collections.CollectionsKt.listOf(r16)
            r17 = 1000000000(0x3b9aca00, double:4.94065646E-315)
            kotlin.ULong r17 = kotlin.ULong.m9147boximpl(r17)
            java.util.List r17 = kotlin.collections.CollectionsKt.listOf(r17)
            r18 = 10000000000(0x2540be400, double:4.9406564584E-314)
            kotlin.ULong r18 = kotlin.ULong.m9147boximpl(r18)
            java.util.List r18 = kotlin.collections.CollectionsKt.listOf(r18)
            r19 = 100000000000(0x174876e800, double:4.9406564584E-313)
            kotlin.ULong r19 = kotlin.ULong.m9147boximpl(r19)
            java.util.List r19 = kotlin.collections.CollectionsKt.listOf(r19)
            r20 = 1000000000000(0xe8d4a51000, double:4.94065645841E-312)
            kotlin.ULong r20 = kotlin.ULong.m9147boximpl(r20)
            java.util.List r20 = kotlin.collections.CollectionsKt.listOf(r20)
            r21 = 10000000000000(0x9184e72a000, double:4.9406564584125E-311)
            kotlin.ULong r21 = kotlin.ULong.m9147boximpl(r21)
            java.util.List r21 = kotlin.collections.CollectionsKt.listOf(r21)
            r22 = 100000000000000(0x5af3107a4000, double:4.94065645841247E-310)
            kotlin.ULong r22 = kotlin.ULong.m9147boximpl(r22)
            java.util.List r22 = kotlin.collections.CollectionsKt.listOf(r22)
            r23 = 1000000000000000(0x38d7ea4c68000, double:4.940656458412465E-309)
            kotlin.ULong r23 = kotlin.ULong.m9147boximpl(r23)
            java.util.List r23 = kotlin.collections.CollectionsKt.listOf(r23)
            r24 = 10000000000000000(0x2386f26fc10000, double:5.431165199810528E-308)
            kotlin.ULong r24 = kotlin.ULong.m9147boximpl(r24)
            java.util.List r24 = kotlin.collections.CollectionsKt.listOf(r24)
            r25 = 100000000000000000(0x16345785d8a0000, double:5.620395787888205E-302)
            kotlin.ULong r25 = kotlin.ULong.m9147boximpl(r25)
            java.util.List r25 = kotlin.collections.CollectionsKt.listOf(r25)
            r26 = 1000000000000000000(0xde0b6b3a7640000, double:7.832953389245686E-242)
            kotlin.ULong r26 = kotlin.ULong.m9147boximpl(r26)
            java.util.List r26 = kotlin.collections.CollectionsKt.listOf(r26)
            r27 = 776627963145224192(0xac7230489e80000, double:9.630676049668687E-257)
            kotlin.ULong r7 = kotlin.ULong.m9147boximpl(r27)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            kotlin.ULong[] r7 = new kotlin.ULong[]{r7, r9}
            java.util.List r7 = kotlin.collections.CollectionsKt.listOf(r7)
            r29 = 7766279631452241920(0x6bc75e2d63100000, double:1.5364778099321232E211)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong r5 = kotlin.ULong.m9147boximpl(r5)
            kotlin.ULong[] r5 = new kotlin.ULong[]{r9, r5}
            java.util.List r5 = kotlin.collections.CollectionsKt.listOf(r5)
            r29 = 3875820019684212736(0x35c9adc5dea00000, double:1.3726678355276356E-49)
            kotlin.ULong r6 = kotlin.ULong.m9147boximpl(r29)
            r29 = 108(0x6c, double:5.34E-322)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r6 = new kotlin.ULong[]{r6, r9}
            java.util.List r6 = kotlin.collections.CollectionsKt.listOf(r6)
            r29 = 1864712049423024128(0x19e0c9bab2400000, double:4.938689790935458E-184)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1084(0x43c, double:5.356E-321)
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r9, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r29 = 200376420520689664(0x2c7e14af6800000, double:2.921120656231127E-295)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 10842(0x2a5a, double:5.3567E-320)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r2, r9}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r29 = 2003764205206896640(0x1bcecceda1000000, double:9.729047140076612E-175)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 108420(0x1a784, double:5.35666E-319)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 1590897978359414784(0x161401484a000000, double:2.5522409936306356E-202)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1084202(0x108b2a, double:5.35667E-318)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r4, r9}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 6685607746739372032(0x5cc80cd2e4000000, double:8.950035900659082E138)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 10842021(0xa56fa5, double:5.35667E-317)
            r28 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r9, r4}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 2292473209410289664(0x1fd0803ce8000000, double:1.922961773682622E-155)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 108420217(0x6765c79, double:5.35667045E-316)
            r33 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r9, r4}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 4477988020393345024(0x3e25026110000000, double:2.4458036707031283E-9)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1084202172(0x409f9cbc, double:5.356670463E-315)
            r34 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r9, r4}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 7886392056514347008(0x6d7217caa0000000, double:1.5967094556710666E219)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 10842021724(0x2863c1f5c, double:5.3566704653E-314)
            r35 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r9, r4}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 5076944270305263616(0x4674edea40000000, double:2.6531110215779044E31)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 108420217248(0x193e5939a0, double:5.3566704657E-313)
            r36 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r9, r4}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 4652582518778757120(0x40914b2680000000, double:1106.78759765625)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1084202172485(0xfc6f7c4045, double:5.356670465713E-312)
            r37 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r9, r4}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 408965003513692160(0x5acef8100000000, double:2.4907261861039802E-281)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 10842021724855(0x9dc5ada82b7, double:5.3566704657153E-311)
            r38 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r9, r4}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 4089650035136921600(0x38c15b0a00000000, double:2.611383659225751E-35)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 108420217248550(0x629b8c891b26, double:5.3566704657153E-310)
            r39 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r9, r4}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 4003012203950112768(0x378d8e6400000000, double:4.241108625317677E-41)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1084202172485504(0x3da137d5b0f80, double:5.35667046571533E-309)
            r40 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r9, r4}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 3136633892082024448(0x2b878fe800000000, double:5.3862443665208984E-99)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 10842021724855044(0x2684c2e58e9b04, double:6.263193214416264E-308)
            r41 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r9, r4}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 3696222810255917056(0x334b9f1000000000, double:1.342875060065033E-61)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 108420217248550443(0x1812f9cf7920e2b, double:2.0048822709153022E-301)
            r42 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r9, r4}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 68739955140067328(0xf436a000000000, double:4.605569126031131E-304)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1084202172485504434(0xf0bdc21abb48db2, double:3.4227311763916286E-236)
            r43 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r9, r4}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 687399551400673280(0x98a224000000000, double:1.0374237242657688E-262)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1618649688000268532(0x16769950b50d88f4, double:1.845227682443793E-200)
            r44 = r4
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r45 = r3
            r29 = 1
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r9, r4, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 6873995514006732800(0x5f65568000000000, double:3.492362538209507E151)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 6963124843147909512(0x60a1fd2712875988, double:3.087250095192827E157)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 11
            r46 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 4176350882083897344(0x39f5610000000000, double:1.6864983337010147E-29)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 5067644173495664471(0x4653e386b9497f57, double:6.303004224215534E30)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 117(0x75, double:5.8E-322)
            r47 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 4870020673419870208(0x4395ca0000000000, double:3.9251685502300979E17)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 4559581550682765674(0x3f46e3433cdef96a, double:6.984785375079687E-4)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1175(0x497, double:5.805E-321)
            r48 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 2583346549924823040(0x23d9e40000000000, double:5.565760355119333E-136)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 8702327359408553513(0x78c4e0a060b5be29, double:5.647073876125523E273)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 11754(0x2dea, double:5.8072E-320)
            r49 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 7386721425538678784(0x6682e80000000000, double:6.426769713082882E185)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 4012925262392552860(0x37b0c643c7196d9c, double:1.9256149148987207E-40)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 117549(0x1cb2d, double:5.8077E-319)
            r50 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 80237960548581376(0x11d100000000000, double:2.6487279211669725E-303)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 3235764476506425376(0x2ce7bea5c6fe4820, double:2.2766608468766692E-92)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1175494(0x11efc6, double:5.80771E-318)
            r51 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 802379605485813760(0xb22a00000000000, double:4.961693839576875E-255)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 4687528654499926336(0x410d7279c5eed140, double:241231.22164691426)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 11754943(0xb35dbf, double:5.8077135E-317)
            r52 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 8023796054858137600(0x6f5a400000000000, double:2.4874023390437994E228)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 758426360725384320(0xa8678c1bb542c80, double:5.846147534630533E-258)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 117549435(0x701a97b, double:5.80771375E-316)
            r53 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 6450984253743169536(0x5986800000000000, double:1.8592199122225742E123)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 7584263607253843208(0x6940b7915149bd08, double:9.996939907451855E198)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1175494350(0x46109ece, double:5.80771375E-315)
            r54 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 9169610316303040512(0x7f41000000000000, double:9.32641091694927E304)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 2055659777700225622(0x1c872bad2ce16256, double:2.9978504617414007E-171)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 11754943508(0x2bca63414, double:5.807713756E-314)
            r55 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 8685754831337422848(0x788a000000000000, double:4.395410499048735E272)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 2109853703292704613(0x1d47b4c3c0cdd765, double:1.2562982503465785E-167)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 117549435082(0x1b5e7e08ca, double:5.8077137562E-313)
            r56 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 3847199981681246208(0x3564000000000000, double:1.6704779438076223E-51)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 2651792959217494523(0x24cd0fa5880a69fb, double:2.0471244468367962E-131)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1175494350822(0x111b0ec57e6, double:5.807713756216E-312)
            r57 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 1578511669393358848(0x15e8000000000000, double:3.827402861221466E-203)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 8071185518465393618(0x70029c77506823d2, double:3.61177723381497E231)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 11754943508222(0xab0e93b6efe, double:5.807713756217E-311)
            r58 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 6561744657078812672(0x5b10000000000000, double:4.436271510593304E130)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 6924878889815729717(0x601a1ca924116635, double:8.75260222958729E154)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 117549435082228(0x6ae91c5255f4, double:5.80771375621747E-310)
            r59 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 1053842312804696064(0xea0000000000000, double:3.0713790748582522E-238)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 4685184640173866521(0x41051e9b68adfe19, double:173011.4261131145)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1175494350822287(0x42d1b1b375b8f, double:5.8077137562175E-309)
            r60 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 1315051091192184832(0x1240000000000000, double:8.852647460508905E-221)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 734986217464786171(0xa33321216cbecfb, double:1.560576913993344E-259)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 11754943508222875(0x29c30f1029939b, double:7.165279795420604E-308)
            r61 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 3927138875067072512(0x3680000000000000, double:3.5032461608120427E-46)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 7349862174647861711(0x65fff4b4e3f741cf, double:2.1216230884749607E183)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 117549435082228750(0x1a19e96a19fc40e, double:8.22181260782101E-301)
            r62 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 2377900603251621888(0x2100000000000000, double:9.775796363198735E-150)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 8935017488495186458(0x7bff8f10e7a8921a, double:1.9221932477596162E289)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1175494350822287507(0x105031e2503da893, double:4.17253997859856E-230)
            r63 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r4, r9, r3}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 5332261958806667264(0x4a00000000000000, double:2.9230032746618058E48)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 6339826553258882310(0x57fb96a90c95b506, double:6.794018232566095E115)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 2531571471368099271(0x2321f2d7226895c7, double:1.884006856172441E-139)
            r64 = r3
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r65 = r2
            r29 = 1
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r4, r9, r3, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 7205759403792793600(0x6400000000000000, double:4.9466080294620907E173)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r3)
            r29 = 8058033311460168257(0x6fd3e29a7dd91241, double:4.823763432468237E230)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 6868970639971441100(0x5f537c675815d9cc, double:1.594628298840195E151)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 12
            r66 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r3, r4, r9, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 7493989779944505344(0x6800000000000000, double:9.12488123524439E192)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r3)
            r29 = 6793356819763476113(0x5e46da08ea7ab691, double:1.4267451361878197E146)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 4126102141730980352(0x3942dc0970da8200, double:7.264422497221743E-33)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 127(0x7f, double:6.27E-322)
            r67 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r3, r4, r9, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r3)
            r29 = 3369963939651330482(0x2ec4845928cb21b2, double:2.1122472847342677E-83)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 4367533269890700295(0x3c9c985e68891407, double:9.920949671841561E-17)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1274(0x4fa, double:6.294E-321)
            r68 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r3, r4, r9, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r3)
            r29 = 6029523285948977397(0x53ad2b7b97ef50f5, double:1.2169224871971135E95)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 6781844551487899721(0x5e1df3b0155ac849, double:2.3375579447860497E145)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 12744(0x31c8, double:6.2964E-320)
            r69 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r3, r4, r9, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 4611686018427387904(0x4000000000000000, double:2.0)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r3)
            r29 = 4955000638361119124(0x44c3b2d3ef592994, double:1.860475102850802E23)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 3254841256895566560(0x2d2b84e0d58bd2e0, double:4.22167551543035E-91)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 127447(0x1f1d7, double:6.2967E-319)
            r70 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r3, r4, r9, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 3433146199337312205(0x2fa4fc47597b9fcd, double:3.539726597379421E-79)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 4878296458391338181(0x43b330c857763cc5, double:1.3828253635741463E18)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1274473(0x137269, double:6.296733E-318)
            r71 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 6661345882808794626(0x5c71dac97ed43e02, double:2.0763906616365838E137)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 2666104399639502773(0x24ffe7d36a9e5fb5, double:1.7979946467023043E-130)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 12744735(0xc2781f, double:6.2967357E-317)
            r72 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 2049854570104515604(0x1c728bdef44a6c14, double:1.1997794465424666E-171)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 8214299922685476121(0x71ff0e422a2fbd19, double:1.2942489778116204E241)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 127447352(0x798b138, double:6.29673583E-316)
            r73 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 2051801627335604424(0x1c7976b58ae838c8, double:1.6472674588270472E-171)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 8356022932016554748(0x73f68e95a5dd62fc, double:4.037543272568465E250)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1274473528(0x4bf6ec38, double:6.296735867E-315)
            r74 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 2071272199646492624(0x1cbea3176d1237d0, double:3.1710988464540895E-170)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 549880988472565210(0x7a191d87aa5ddda, double:6.495588495492375E-272)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 12744735289(0x2f7a53a39, double:6.2967358716E-314)
            r75 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 2265977922755374624(0x1f725eea42b62e20, double:3.345102605801856E-157)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 5498809884725652102(0x4c4fb274ca7aaa86, double:3.9793177489298764E59)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 127447352890(0x1dac74463a, double:6.29673587164E-313)
            r76 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 4213035153844194624(0x3a77b5269b1dcd40, double:4.787715797472845E-27)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 8871238662982641982(0x7b1cf88fe8caa93e, double:1.0770071595393444E285)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1274473528905(0x128bc8abe49, double:6.29673587166E-312)
            r77 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 5236863391022843008(0x48ad13820f2a0480, double:1.2664472676843338E42)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 5702038298133437552(0x4f21b59f17ea9c70, double:1.5644952670108906E73)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 12744735289059(0xb975d6b6ee3, double:6.2967358716647E-311)
            r78 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 6251773725954551040(0x56c2c31497a42d00, double:8.812686671216631E109)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1680150760205720677(0x17511836ef2a1c65, double:2.2868700134153347E-196)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 127447352890596(0x73e9a63254e4, double:6.29673587166496E-310)
            r79 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 7177505038416855552(0x639b9ecdec69c200, double:6.671237518986213E171)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 7578135565202430968(0x692af22557a51bf8, double:4.0284647548429336E198)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1274473528905961(0x487207df750e9, double:6.29673587166496E-309)
            r80 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 7211446126185124864(0x6414340b3c219400, double:1.2492223543260161E174)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1994379357186103223(0x1bad75756c7317b7, double:2.3263118207215284E-175)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 12744735289059618(0x2d4744eba92922, double:8.143324026315529E-308)
            r81 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 7550857003867817984(0x68ca0870594fc800, double:6.081221312248367E196)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1497049498151480621(0x14c696963c7eed2d, double:1.3741533890052604E-208)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 127447352890596182(0x1c4c8b1349b9b56, double:3.879381612419023E-300)
            r82 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 1721593743839973376(0x17e454637d1dd000, double:1.3924594299866634E-193)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 5747122944660030410(0x4fc1e1de5cf543ca, double:1.617678741864829E76)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1274473528905961821(0x11afd6ec0e14115d, double:1.7203626570521288E-223)
            r83 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r9, r3, r4, r2}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r3)
            r29 = 7992565401544957952(0x6eeb4be2e32a2000, double:2.02075017145062E226)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 2130997225471649253(0x1d92d2afa194a5e5, double:3.1920601723419465E-166)
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r29)
            r29 = 3521363252204842408(0x30de65388cc8ada8, double:2.6879974096483214E-73)
            r84 = r2
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r85 = r1
            r29 = 1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r9, r3, r4, r2, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 6138677720611373056(0x5530f6dcdfa54000, double:2.374732444930742E102)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 2863228181006940922(0x27bc3adc4fce7afa, double:2.798668190123271E-117)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 7543516411484096658(0x68aff4357fd6c892, double:1.8660858859870987E196)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 13
            r86 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 6046544984985075712(0x53e9a4a0bc748000, double:1.7116650436912463E96)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 962165699505081802(0xd5a4c9b1e10cdca, double:2.407280840566033E-244)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1648187820002760119(0x16df8a16fe63d5b7, double:1.648149038704015E-198)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 138(0x8a, double:6.8E-322)
            r87 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 5125217628722102272(0x47206e475c8d0000, double:4.265673431778844E34)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 398284958196042218(0x586fe0f2ca809ea, double:4.9478752070630445E-282)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 7258506163172825383(0x64bb64e5efe65927, double:1.7345029252754246E177)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1381(0x565, double:6.823E-321)
            r88 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 5135316102947143680(0x47444ec99d820000, double:2.108878774535073E35)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 3982849581960422185(0x3745ec97be906329, double:1.9662294008985233E-42)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 8021457373744823174(0x6f51f0fb5eff7b86, double:1.700088387913026E228)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 13817(0x35f9, double:6.8265E-320)
            r89 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 5236300845197557760(0x48ab13e027140000, double:1.179397517552653E42)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 2935007672185118623(0x28bb3ded71a3df9f, double:1.7699422411387122E-112)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 6427597442610025280(0x593369d1b5fad340, double:5.013013663875794E121)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 138178(0x21bc2, double:6.8269E-319)
            r90 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 6246148267701698560(0x56aec6c186c80000, double:3.6139831793033865E109)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1679960611286858811(0x17506b467066bc3b, double:2.1964973869182257E-196)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 8935742204971597955(0x7c0222311bcc4083, double:2.2089656781502676E289)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1381786(0x15159a, double:6.82693E-318)
            r91 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 7121250455888330752(0x62d3c38f43d00000, double:1.165441157473913E168)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 7576234076013812308(0x692430c064035a54, double:3.0185116038372355E198)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 6347073718022997279(0x581555eb15fa851f, double:2.1016654185840593E116)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 13817869(0xd2d80d, double:6.8269344E-317)
            r92 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 6648900300899876864(0x5c45a398a6200000, double:3.1456196068788506E136)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1975364465299916623(0x1b69e783e821874f, double:1.2785136290163822E-176)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 8130504959101317950(0x70d55b2edbc9333e, double:3.3951602341327187E235)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 138178696(0x83c7088, double:6.82693467E-316)
            r93 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 1925398751015337984(0x1ab863f67d400000, double:5.877910197639903E-180)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1306900579289614621(0x12230b27114f491d, double:2.6341557222717156E-221)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 7518073296174973038(0x68558fd495dc006e, double:3.934984136344021E194)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1381786968(0x525c6558, double:6.82693471E-315)
            r94 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 807243436443828224(0xb33e7a0e4800000, double:1.060526362034433E-254)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 3845633756041370404(0x355e6f86ad18db24, double:1.271051998355193E-51)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1393756666911523917(0x13579e4dda98044d, double:1.7128255611010282E-215)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 13817869688(0x3379bf578, double:6.8269347116E-314)
            r95 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 8072434364438282240(0x70070c48ed000000, double:4.472802248393586E231)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1562849412994600808(0x15b05b42c2f88f68, double:3.260565982836121E-204)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 4714194632260463366(0x416c2f0a89f02b06, double:1.477640431056739E7)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 138178696881(0x202c1796b1, double:6.8269347116E-313)
            r96 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 6937367349544615936(0x60467ad942000000, double:6.02811762061067E155)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 6405122093091232280(0x58e3909b9db59a18, double:1.578793588272849E120)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1025086138330754621(0xe39d6696361ae3d, double:3.874848480988581E-240)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1381786968815(0x141b8ebe2ef, double:6.826934711626E-312)
            r97 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 4810069237462728704(0x42c0cc7c94000000, double:3.6940898893824E13)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 8710988709783667959(0x78e3a614291804f7, double:2.1258904767084085E274)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1027489346452770408(0xe42601de1d0ce68, double:5.511522552781337E-240)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 13817869688151(0xc913936dd57, double:6.8269347116265E-311)
            r98 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 1983832190353408000(0x1b87fcddc8000000, double:4.73567864475425E-176)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 4099538766143697323(0x38e47cc99af031ab, double:1.2330436203034343E-34)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1051521427672928281(0xe97c12ad2281019, double:2.27997679460902E-238)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 138178696881511(0x7dac3c24a567, double:6.82693471162656E-310)
            r99 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 1391577829824528384(0x134fe0a9d0000000, double:1.155895608081348E-215)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 4101899514017870000(0x38ecdfe00d61f0b0, double:1.7378215089045465E-34)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1291842239874507006(0x11ed8bac3590a0fe, double:2.5542651310704095E-222)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1381786968815111(0x4e8ba596e7607, double:6.82693471162656E-309)
            r100 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 4692406261390508032(0x411ec6a220000000, double:504232.53125)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 4125506992759596769(0x3940bec085d366e1, double:6.449987595225347E-33)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 3695050361890294256(0x334774ba17a649f0, double:1.140365825911763E-61)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 13817869688151111(0x3117477e509c47, double:9.50714797844864E-308)
            r101 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 807202429631201280(0xb33c25540000000, double:1.0527642501422671E-254)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 4361581780176864463(0x3c8773853a4204cf, double:4.068143676335176E-17)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 57015471483839332(0xca8f44ec7ee364, double:7.564438114359377E-305)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 138178696881511114(0x1eae8caef261aca, double:2.0090811291146131E-299)
            r102 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r4 = kotlin.ULong.m9147boximpl(r2)
            r29 = 8072024296312012800(0x7005975480000000, double:4.190078099808083E231)
            kotlin.ULong r9 = kotlin.ULong.m9147boximpl(r29)
            r29 = 6722329654349541398(0x5d4a833446943016, double:2.525799855962935E141)
            kotlin.ULong r2 = kotlin.ULong.m9147boximpl(r29)
            r29 = 570154714838393324(0x7e998b13cf4e1ec, double:1.5140970691064286E-270)
            kotlin.ULong r3 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1381786968815111140(0x132d17ed577d0be4, double:2.6373549228577066E-216)
            r103 = r1
            kotlin.ULong r1 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r1 = new kotlin.ULong[]{r4, r9, r2, r3, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r2 = 0
            kotlin.ULong r104 = kotlin.ULong.m9147boximpl(r2)
            r2 = 6933266668281921536(0x6037e94d00000000, double:3.2059853576721705E155)
            kotlin.ULong r105 = kotlin.ULong.m9147boximpl(r2)
            r2 = 2659692285511983332(0x24e9200ac1c9e0e4, double:7.079470924681507E-131)
            kotlin.ULong r106 = kotlin.ULong.m9147boximpl(r2)
            r2 = 5701547148383933247(0x4f1ff6ec6190d33f, double:1.4119115357930939E73)
            kotlin.ULong r107 = kotlin.ULong.m9147boximpl(r2)
            r2 = 4594497651296335592(0x3fc2ef456ae276e8, double:0.14792697638488694)
            kotlin.ULong r108 = kotlin.ULong.m9147boximpl(r2)
            r2 = 1
            kotlin.ULong r109 = kotlin.ULong.m9147boximpl(r2)
            kotlin.ULong[] r2 = new kotlin.ULong[]{r104, r105, r106, r107, r108, r109}
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            r3 = 0
            kotlin.ULong r104 = kotlin.ULong.m9147boximpl(r3)
            r3 = 4769062424835784704(0x422f1d0200000000, double:6.6815328256E10)
            kotlin.ULong r105 = kotlin.ULong.m9147boximpl(r3)
            r3 = 8150178781410281711(0x711b406b91e2c8ef, double:6.9318518860200825E236)
            kotlin.ULong r106 = kotlin.ULong.m9147boximpl(r3)
            r3 = 1675239262710677624(0x173fa53bcfa84078, double:1.0583615079427085E-196)
            kotlin.ULong r107 = kotlin.ULong.m9147boximpl(r3)
            r3 = 9051488365544252694(0x7d9d58b62cd8a516, double:1.1995338150958334E297)
            kotlin.ULong r108 = kotlin.ULong.m9147boximpl(r3)
            r3 = 14
            kotlin.ULong r109 = kotlin.ULong.m9147boximpl(r3)
            kotlin.ULong[] r3 = new kotlin.ULong[]{r104, r105, r106, r107, r108, r109}
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r29 = 0
            kotlin.ULong r104 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1573764064083968000(0x15d7221400000000, double:1.8445783496095422E-203)
            kotlin.ULong r105 = kotlin.ULong.m9147boximpl(r29)
            r29 = 7714811519264610651(0x6b108433b2dbd95b, double:5.302647796927154E207)
            kotlin.ULong r106 = kotlin.ULong.m9147boximpl(r29)
            r29 = 7529020590252000440(0x687c74561c9284b8, double:2.0771470089240372E195)
            kotlin.ULong r107 = kotlin.ULong.m9147boximpl(r29)
            r29 = 7504535323749544669(0x6825771dc07672dd, double:4.89670731084896E193)
            kotlin.ULong r108 = kotlin.ULong.m9147boximpl(r29)
            r29 = 149(0x95, double:7.36E-322)
            kotlin.ULong r109 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r4 = new kotlin.ULong[]{r104, r105, r106, r107, r108, r109}
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            r29 = 0
            kotlin.ULong r104 = kotlin.ULong.m9147boximpl(r29)
            r29 = 6514268603984904192(0x5a6754c800000000, double:3.1586741564727856E127)
            kotlin.ULong r105 = kotlin.ULong.m9147boximpl(r29)
            r29 = 3361138897807900047(0x2ea52a04fc967d8f, double:5.447181111320938E-84)
            kotlin.ULong r106 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1503229607681797944(0x14dc8b5d1db92f38, double:3.472991892016393E-208)
            kotlin.ULong r107 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1258376942657240234(0x1176a72984a07caa, double:1.5299897892964827E-224)
            kotlin.ULong r108 = kotlin.ULong.m9147boximpl(r29)
            r29 = 1498(0x5da, double:7.4E-321)
            kotlin.ULong r109 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r9 = new kotlin.ULong[]{r104, r105, r106, r107, r108, r109}
            java.util.List r9 = kotlin.collections.CollectionsKt.listOf(r9)
            r29 = 0
            kotlin.ULong r104 = kotlin.ULong.m9147boximpl(r29)
            r29 = 579081781865611264(0x8094fd000000000, double:5.989028393809214E-270)
            kotlin.ULong r105 = kotlin.ULong.m9147boximpl(r29)
            r29 = 5941272867514673053(0x5273a431dde0e79d, double:1.5628988169878478E89)
            kotlin.ULong r106 = kotlin.ULong.m9147boximpl(r29)
            r29 = 5808924039963203635(0x509d71a3293bd833, double:2.1819969410743316E80)
            kotlin.ULong r107 = kotlin.ULong.m9147boximpl(r29)
            r29 = 3360397389717626533(0x2ea2879f2e44dea5, double:4.769153593147798E-84)
            kotlin.ULong r108 = kotlin.ULong.m9147boximpl(r29)
            r29 = 14981(0x3a85, double:7.4016E-320)
            kotlin.ULong r109 = kotlin.ULong.m9147boximpl(r29)
            kotlin.ULong[] r29 = new kotlin.ULong[]{r104, r105, r106, r107, r108, r109}
            java.util.List r29 = kotlin.collections.CollectionsKt.listOf(r29)
            r30 = 0
            kotlin.ULong r104 = kotlin.ULong.m9147boximpl(r30)
            r30 = 5790817818656112640(0x505d1e2000000000, double:1.3486386049782962E79)
            kotlin.ULong r105 = kotlin.ULong.m9147boximpl(r30)
            r30 = 4072496454018075682(0x388469f2aac90c22, double:1.91971007995681E-36)
            kotlin.ULong r106 = kotlin.ULong.m9147boximpl(r30)
            r30 = 2749008178503381508(0x2626705f9c567204, double:6.629709688338621E-125)
            kotlin.ULong r107 = kotlin.ULong.m9147boximpl(r30)
            r30 = 5933857786611937912(0x52594c37ceb0b278, double:5.032458897278154E88)
            kotlin.ULong r108 = kotlin.ULong.m9147boximpl(r30)
            r30 = 149813(0x24935, double:7.40175E-319)
            kotlin.ULong r109 = kotlin.ULong.m9147boximpl(r30)
            kotlin.ULong[] r30 = new kotlin.ULong[]{r104, r105, r106, r107, r108, r109}
            java.util.List r30 = kotlin.collections.CollectionsKt.listOf(r30)
            r31 = r9
            r9 = 101(0x65, float:1.42E-43)
            java.util.List[] r9 = new java.util.List[r9]
            r32 = 0
            r9[r32] = r0
            r0 = 1
            r9[r0] = r8
            r0 = 2
            r9[r0] = r10
            r0 = 3
            r9[r0] = r11
            r0 = 4
            r9[r0] = r12
            r0 = 5
            r9[r0] = r13
            r0 = 6
            r9[r0] = r14
            r0 = 7
            r9[r0] = r15
            r0 = 8
            r9[r0] = r16
            r0 = 9
            r9[r0] = r17
            r0 = 10
            r9[r0] = r18
            r0 = 11
            r9[r0] = r19
            r0 = 12
            r9[r0] = r20
            r0 = 13
            r9[r0] = r21
            r0 = 14
            r9[r0] = r22
            r0 = 15
            r9[r0] = r23
            r0 = 16
            r9[r0] = r24
            r0 = 17
            r9[r0] = r25
            r0 = 18
            r9[r0] = r26
            r0 = 19
            r9[r0] = r7
            r0 = 20
            r9[r0] = r5
            r0 = 21
            r9[r0] = r6
            r0 = 22
            r9[r0] = r85
            r0 = 23
            r9[r0] = r65
            r0 = 24
            r9[r0] = r45
            r0 = 25
            r9[r0] = r28
            r0 = 26
            r9[r0] = r33
            r0 = 27
            r9[r0] = r34
            r0 = 28
            r9[r0] = r35
            r0 = 29
            r9[r0] = r36
            r0 = 30
            r9[r0] = r37
            r0 = 31
            r9[r0] = r38
            r0 = 32
            r9[r0] = r39
            r0 = 33
            r9[r0] = r40
            r0 = 34
            r9[r0] = r41
            r0 = 35
            r9[r0] = r42
            r0 = 36
            r9[r0] = r43
            r0 = 37
            r9[r0] = r44
            r0 = 38
            r9[r0] = r46
            r0 = 39
            r9[r0] = r47
            r0 = 40
            r9[r0] = r48
            r0 = 41
            r9[r0] = r49
            r0 = 42
            r9[r0] = r50
            r0 = 43
            r9[r0] = r51
            r0 = 44
            r9[r0] = r52
            r0 = 45
            r9[r0] = r53
            r0 = 46
            r9[r0] = r54
            r0 = 47
            r9[r0] = r55
            r0 = 48
            r9[r0] = r56
            r0 = 49
            r9[r0] = r57
            r0 = 50
            r9[r0] = r58
            r0 = 51
            r9[r0] = r59
            r0 = 52
            r9[r0] = r60
            r0 = 53
            r9[r0] = r61
            r0 = 54
            r9[r0] = r62
            r0 = 55
            r9[r0] = r63
            r0 = 56
            r9[r0] = r64
            r0 = 57
            r9[r0] = r66
            r0 = 58
            r9[r0] = r67
            r0 = 59
            r9[r0] = r68
            r0 = 60
            r9[r0] = r69
            r0 = 61
            r9[r0] = r70
            r0 = 62
            r9[r0] = r71
            r0 = 63
            r9[r0] = r72
            r0 = 64
            r9[r0] = r73
            r0 = 65
            r9[r0] = r74
            r0 = 66
            r9[r0] = r75
            r0 = 67
            r9[r0] = r76
            r0 = 68
            r9[r0] = r77
            r0 = 69
            r9[r0] = r78
            r0 = 70
            r9[r0] = r79
            r0 = 71
            r9[r0] = r80
            r0 = 72
            r9[r0] = r81
            r0 = 73
            r9[r0] = r82
            r0 = 74
            r9[r0] = r83
            r0 = 75
            r9[r0] = r84
            r0 = 76
            r9[r0] = r86
            r0 = 77
            r9[r0] = r87
            r0 = 78
            r9[r0] = r88
            r0 = 79
            r9[r0] = r89
            r0 = 80
            r9[r0] = r90
            r0 = 81
            r9[r0] = r91
            r0 = 82
            r9[r0] = r92
            r0 = 83
            r9[r0] = r93
            r0 = 84
            r9[r0] = r94
            r0 = 85
            r9[r0] = r95
            r0 = 86
            r9[r0] = r96
            r0 = 87
            r9[r0] = r97
            r0 = 88
            r9[r0] = r98
            r0 = 89
            r9[r0] = r99
            r0 = 90
            r9[r0] = r100
            r0 = 91
            r9[r0] = r101
            r0 = 92
            r9[r0] = r102
            r0 = 93
            r9[r0] = r103
            r0 = 94
            r9[r0] = r1
            r0 = 95
            r9[r0] = r2
            r0 = 96
            r9[r0] = r3
            r0 = 97
            r9[r0] = r4
            r0 = 98
            r9[r0] = r31
            r0 = 99
            r9[r0] = r29
            r0 = 100
            r9[r0] = r30
            powersOf10 = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ionspin.kotlin.bignum.integer.base63.BigInteger63LinkedListArithmetic.<clinit>():void");
    }

    private BigInteger63LinkedListArithmetic() {
    }

    private final List<ULong> binaryGcd(List<ULong> list, List<ULong> list2) {
        while (!Intrinsics.areEqual((Object) list, (Object) list2)) {
            if (Intrinsics.areEqual((Object) list, (Object) getZERO())) {
                return list2;
            }
            if (Intrinsics.areEqual((Object) list2, (Object) getZERO())) {
                return list;
            }
            if (Intrinsics.areEqual((Object) and(list, getONE()), (Object) getZERO())) {
                if (Intrinsics.areEqual((Object) and(list2, getONE()), (Object) getZERO())) {
                    return shl$bignum(binaryGcd(shr$bignum(list, 1), shr$bignum(list2, 1)), 1);
                }
                list = shr$bignum(list, 1);
            } else if (Intrinsics.areEqual((Object) and(list2, getONE()), (Object) getZERO())) {
                list2 = shr$bignum(list2, 1);
            } else if (compare(list, list2) == 1) {
                list = shr$bignum(subtract(list, list2), 1);
            } else {
                List<ULong> shr$bignum = shr$bignum(subtract(list2, list), 1);
                list2 = list;
                list = shr$bignum;
            }
        }
        return list;
    }

    private final List<ULong> euclideanGcd(List<ULong> list, List<ULong> list2) {
        while (true) {
            List<ULong> list3 = list2;
            List<ULong> list4 = list;
            list = list3;
            if (Intrinsics.areEqual((Object) list, (Object) getZERO())) {
                return list4;
            }
            list2 = rem$bignum(list4, list);
        }
    }

    private final Pair<List<ULong>, List<ULong>> reqursiveSqrt(List<ULong> list) {
        int size = list.size();
        int floor = (int) Math.floor(((double) (size - 1)) / ((double) 4));
        if (floor == 0) {
            return basecaseSqrt$bignum(list);
        }
        int i3 = size / 4;
        int i4 = size % 4;
        int i5 = floor * 63;
        int i6 = size - ((i3 * 3) + i4);
        int i7 = size - ((i3 * 2) + i4);
        List<ULong> subList = list.subList(i6, i7);
        List<ULong> subList2 = list.subList(0, i6);
        Pair<List<ULong>, List<ULong>> reqursiveSqrt = reqursiveSqrt(list.subList(i7, size));
        List component1 = reqursiveSqrt.component1();
        Pair<List<ULong>, List<ULong>> divrem$bignum = divrem$bignum(plus$bignum(shl$bignum(reqursiveSqrt.component2(), i5), subList), shl$bignum((List<ULong>) component1, 1));
        List component12 = divrem$bignum.component1();
        return new Pair<>(plus$bignum(shl$bignum((List<ULong>) component1, i5), (List<ULong>) component12), minus$bignum(plus$bignum(shl$bignum(divrem$bignum.component2(), i5), subList2), times$bignum((List<ULong>) component12, (List<ULong>) component12)));
    }

    private final SignedULongArray signedAdd(SignedULongArray signedULongArray, SignedULongArray signedULongArray2) {
        return signedULongArray.getSign() ^ signedULongArray2.getSign() ? compareTo$bignum(signedULongArray.getUnsignedValue(), signedULongArray2.getUnsignedValue()) > 0 ? new SignedULongArray(minus$bignum(signedULongArray.getUnsignedValue(), signedULongArray2.getUnsignedValue()), signedULongArray.getSign()) : new SignedULongArray(minus$bignum(signedULongArray2.getUnsignedValue(), signedULongArray.getUnsignedValue()), signedULongArray2.getSign()) : new SignedULongArray(plus$bignum(signedULongArray.getUnsignedValue(), signedULongArray2.getUnsignedValue()), signedULongArray.getSign());
    }

    private final SignedULongArray signedDivide(SignedULongArray signedULongArray, SignedULongArray signedULongArray2) {
        return new SignedULongArray(div$bignum(signedULongArray.getUnsignedValue(), signedULongArray2.getUnsignedValue()), !(signedULongArray.getSign() ^ signedULongArray2.getSign()));
    }

    private final SignedULongArray signedMultiply(SignedULongArray signedULongArray, SignedULongArray signedULongArray2) {
        return new SignedULongArray(times$bignum(signedULongArray.getUnsignedValue(), signedULongArray2.getUnsignedValue()), !(signedULongArray.getSign() ^ signedULongArray2.getSign()));
    }

    private final SignedULongArray signedRemainder(SignedULongArray signedULongArray, SignedULongArray signedULongArray2) {
        return new SignedULongArray(rem$bignum(signedULongArray.getUnsignedValue(), signedULongArray2.getUnsignedValue()), !(signedULongArray.getSign() ^ signedULongArray2.getSign()));
    }

    private final SignedULongArray signedSubtract(SignedULongArray signedULongArray, SignedULongArray signedULongArray2) {
        return signedAdd(signedULongArray, SignedULongArray.copy$default(signedULongArray2, (List) null, !signedULongArray2.getSign(), 1, (Object) null));
    }

    @NotNull
    public List<ULong> add(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "first");
        Intrinsics.checkNotNullParameter(list2, "second");
        if (list.size() == 1 && list.get(0).m9205unboximpl() == 0) {
            return list2;
        }
        if (list2.size() == 1 && list2.get(0).m9205unboximpl() == 0) {
            return list;
        }
        Quadruple quadruple = list.size() > list2.size() ? new Quadruple(Integer.valueOf(list.size()), Integer.valueOf(list2.size()), list, list2) : new Quadruple(Integer.valueOf(list2.size()), Integer.valueOf(list.size()), list2, list);
        int intValue = ((Number) quadruple.component1()).intValue();
        int intValue2 = ((Number) quadruple.component2()).intValue();
        List list3 = (List) quadruple.component3();
        List list4 = (List) quadruple.component4();
        int i3 = intValue + 1;
        ArrayList arrayList = new ArrayList(i3);
        for (int i4 = 0; i4 < i3; i4 = a.b(0, arrayList, i4, 1)) {
        }
        int i5 = 0;
        long j2 = 0;
        while (i5 < intValue2) {
            long e3 = a.e((ULong) list4.get(i5), a.e((ULong) list3.get(i5), j2));
            arrayList.set(i5, ULong.m9147boximpl(ULong.m9153constructorimpl(m8539getBaseMasksVKNKU() & e3)));
            j2 = ULong.m9153constructorimpl(e3 >>> 63);
            i5++;
        }
        while (j2 != 0) {
            if (i5 == intValue) {
                arrayList.set(intValue, ULong.m9147boximpl(j2));
                return removeLeadingZeros(arrayList);
            }
            long e4 = a.e((ULong) list3.get(i5), j2);
            arrayList.set(i5, ULong.m9147boximpl(ULong.m9153constructorimpl(m8539getBaseMasksVKNKU() & e4)));
            j2 = ULong.m9153constructorimpl(e4 >>> 63);
            i5++;
        }
        while (i5 < intValue) {
            arrayList.set(i5, list3.get(i5));
            i5++;
        }
        int i6 = (((ULong) arrayList.get(arrayList.size() - 1)).m9205unboximpl() > 0 ? 1 : (((ULong) arrayList.get(arrayList.size() - 1)).m9205unboximpl() == 0 ? 0 : -1));
        List list5 = arrayList;
        if (i6 == 0) {
            list5 = arrayList.subList(0, arrayList.size() - 1);
        }
        return removeLeadingZeros(list5);
    }

    @NotNull
    public List<ULong> and(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "operand");
        Intrinsics.checkNotNullParameter(list2, "mask");
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        int i3 = 0;
        while (i3 < size) {
            i3 = a.b(i3 < list2.size() ? ULong.m9153constructorimpl(list.get(i3).m9205unboximpl() & list2.get(i3).m9205unboximpl()) : 0, arrayList, i3, 1);
        }
        return removeLeadingZeros(arrayList);
    }

    @NotNull
    public final SignedULongArray and$bignum(@NotNull SignedULongArray signedULongArray, @NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        Intrinsics.checkNotNullParameter(list, "operand");
        return new SignedULongArray(and(signedULongArray.getUnsignedValue(), list), signedULongArray.getSign());
    }

    @NotNull
    public final Pair<List<ULong>, List<ULong>> baseDivide(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        List<ULong> list3 = list;
        List<ULong> list4 = list2;
        Intrinsics.checkNotNullParameter(list3, "unnormalizedDividend");
        Intrinsics.checkNotNullParameter(list4, "unnormalizedDivisor");
        if (compareTo$bignum(list4, list3) > 0) {
            return new Pair<>(getZERO(), list3);
        }
        if (list2.size() == 1 && list.size() == 1) {
            return new Pair<>(removeLeadingZeros(CollectionsKt.listOf(ULong.m9147boximpl(Long.divideUnsigned(list3.get(0).m9205unboximpl(), list4.get(0).m9205unboximpl())))), removeLeadingZeros(CollectionsKt.listOf(ULong.m9147boximpl(Long.remainderUnsigned(list3.get(0).m9205unboximpl(), list4.get(0).m9205unboximpl())))));
        }
        if (bitLength(list) - bitLength(list4) == 0) {
            return new Pair<>(getONE(), minus$bignum(list, list2));
        }
        Triple<List<ULong>, List<ULong>, Integer> normalize = normalize(list, list2);
        List<ULong> component1 = normalize.component1();
        List component2 = normalize.component2();
        int intValue = normalize.component3().intValue();
        int size = component1.size();
        int size2 = component2.size();
        int i3 = size - size2;
        ArrayList arrayList = new ArrayList(i3);
        for (int i4 = 0; i4 < i3; i4 = a.b(0, arrayList, i4, 1)) {
        }
        List<ULong> shl$bignum = shl$bignum((List<ULong>) component2, getBasePowerOfTwo() * i3);
        long j2 = 1;
        List list5 = arrayList;
        if (compareTo$bignum(component1, shl$bignum) >= 0) {
            int i5 = i3 + 1;
            ArrayList arrayList2 = new ArrayList(i5);
            for (int i6 = 0; i6 < i5; i6 = a.b(0, arrayList2, i6, 1)) {
            }
            arrayList2.set(i3, ULong.m9147boximpl(1));
            component1 = minus$bignum(component1, shl$bignum);
            list5 = arrayList2;
        }
        int i7 = i3 - 1;
        if (i7 >= 0) {
            while (true) {
                int i8 = i7 - 1;
                int i9 = size2 + i7;
                List<ULong> r9 = m8533from32BitajY9A$bignum(BigInteger32Arithmetic.INSTANCE.m8454divideYnv0uTE(m8552to32Bitg_c56RQ$bignum(i9 < component1.size() ? m8548plus2TYgG_w$bignum(shl$bignum((List<ULong>) CollectionsKt.listOf(component1.get(i9)), getBasePowerOfTwo()), component1.get(i9 - 1).m9205unboximpl()) : i9 == component1.size() ? CollectionsKt.listOf(component1.get(i9 - 1)) : getZERO()), m8552to32Bitg_c56RQ$bignum(CollectionsKt.listOf(component2.get(size2 - 1)))).getFirst().m9143unboximpl());
                list5.set(i7, ULong.m9147boximpl(m8528compareTo2TYgG_w$bignum(r9, ULong.m9153constructorimpl(m8539getBaseMasksVKNKU() - j2)) < 0 ? r9.get(0).m9205unboximpl() : m8539getBaseMasksVKNKU()));
                List<ULong> shl$bignum2 = shl$bignum(m8551times2TYgG_w$bignum(component2, ((ULong) list5.get(i7)).m9205unboximpl()), getBasePowerOfTwo() * i7);
                while (compareTo$bignum(shl$bignum2, component1) > 0) {
                    list5.set(i7, ULong.m9147boximpl(ULong.m9153constructorimpl(((ULong) list5.get(i7)).m9205unboximpl() - ULong.m9153constructorimpl(((long) 1) & 4294967295L))));
                    shl$bignum2 = shl$bignum(m8551times2TYgG_w$bignum(component2, ((ULong) list5.get(i7)).m9205unboximpl()), getBasePowerOfTwo() * i7);
                }
                component1 = minus$bignum(component1, shl$bignum2);
                if (i8 < 0) {
                    break;
                }
                i7 = i8;
                j2 = 1;
            }
        }
        List list6 = list5;
        while (compareTo$bignum(component1, component2) >= 0) {
            List mutableList = CollectionsKt.toMutableList(m8548plus2TYgG_w$bignum(list6, 1));
            component1 = minus$bignum(component1, (List<ULong>) component2);
            list6 = mutableList;
        }
        return new Pair<>(removeLeadingZeros(list6), denormalize(component1, intValue));
    }

    @NotNull
    public final List<ULong> basecaseMultiply(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "first");
        Intrinsics.checkNotNullParameter(list2, "second");
        List<ULong> emptyList = CollectionsKt.emptyList();
        int i3 = 0;
        for (Object next : list2) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            long r3 = ((ULong) next).m9205unboximpl();
            BigInteger63LinkedListArithmetic bigInteger63LinkedListArithmetic = INSTANCE;
            emptyList = bigInteger63LinkedListArithmetic.plus$bignum(emptyList, bigInteger63LinkedListArithmetic.shl$bignum(bigInteger63LinkedListArithmetic.m8544multiply2TYgG_w(list, r3), bigInteger63LinkedListArithmetic.getBasePowerOfTwo() * i3));
            i3 = i4;
        }
        return emptyList;
    }

    @NotNull
    public final Pair<List<ULong>, List<ULong>> basecaseSqrt$bignum(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "operand");
        List<ULong> sqrtInt$bignum = sqrtInt$bignum(list);
        return new Pair<>(sqrtInt$bignum, minus$bignum(list, times$bignum(sqrtInt$bignum, sqrtInt$bignum)));
    }

    public boolean bitAt(@NotNull List<ULong> list, long j2) {
        Intrinsics.checkNotNullParameter(list, "operand");
        long j3 = (long) 63;
        long j4 = j2 / j3;
        if (j4 <= SieveCacheKt.NodeLinkMask) {
            return j4 < ((long) list.size()) && ULong.m9153constructorimpl(list.get((int) j4).m9205unboximpl() & ULong.m9153constructorimpl(1 << ((int) (j2 % j3)))) == 1;
        }
        throw new RuntimeException("Invalid bit index, too large, cannot access word (Word position > Int.MAX_VALUE");
    }

    public int bitLength(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "value");
        return ((list.size() - 1) * 63) + m8527bitLengthVKZWuLQ(list.get(list.size() - 1).m9205unboximpl());
    }

    /* renamed from: bitLength-VKZWuLQ  reason: not valid java name */
    public final int m8527bitLengthVKZWuLQ(long j2) {
        return 63 - m8546numberOfLeadingZerosInAWordVKZWuLQ(j2);
    }

    public int compare(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        boolean z2;
        boolean z3;
        Intrinsics.checkNotNullParameter(list, "first");
        Intrinsics.checkNotNullParameter(list2, "second");
        if (list.size() > list2.size()) {
            return 1;
        }
        if (list2.size() > list.size()) {
            return -1;
        }
        int size = list.size() - 1;
        while (true) {
            if (size < 0) {
                z2 = false;
                z3 = true;
                break;
            } else if (Long.compareUnsigned(list.get(size).m9205unboximpl(), list2.get(size).m9205unboximpl()) > 0) {
                z3 = false;
                z2 = true;
                break;
            } else if (Long.compareUnsigned(list.get(size).m9205unboximpl(), list2.get(size).m9205unboximpl()) < 0) {
                z3 = false;
                z2 = false;
                break;
            } else {
                size--;
            }
        }
        if (z3) {
            return 0;
        }
        return z2 ? 1 : -1;
    }

    public final int compareTo$bignum(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(list2, "other");
        return compare(list, list2);
    }

    /* renamed from: compareTo-2TYgG_w$bignum  reason: not valid java name */
    public final int m8528compareTo2TYgG_w$bignum(@NotNull List<ULong> list, long j2) {
        Intrinsics.checkNotNullParameter(list, "$receiver");
        return compare(list, CollectionsKt.listOf(ULong.m9147boximpl(j2)));
    }

    @NotNull
    /* renamed from: convertFrom32BitRepresentation--ajY-9A$bignum  reason: not valid java name */
    public final List<ULong> m8529convertFrom32BitRepresentationajY9A$bignum(@NotNull int[] iArr) {
        int[] iArr2 = iArr;
        Intrinsics.checkNotNullParameter(iArr2, "operand");
        if (UIntArray.m9135getSizeimpl(iArr) == 0) {
            return getZERO();
        }
        if (UIntArray.m9135getSizeimpl(iArr) == 1) {
            return CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 0)) & 4294967295L)));
        }
        int r12 = BigInteger32Arithmetic.INSTANCE.m8444bitLengthajY9A(iArr2);
        int i3 = r12 % 63 == 0 ? r12 / 63 : (r12 / 63) + 1;
        ArrayList arrayList = new ArrayList(i3);
        for (int i4 = 0; i4 < i3; i4 = a.b(0, arrayList, i4, 1)) {
        }
        if (i3 > 0) {
            int i5 = 0;
            while (true) {
                int i6 = i5 + 1;
                int i7 = i5 % 32;
                int i8 = (i5 * 2) - (i5 / 32);
                if (i3 == 2) {
                    arrayList.set(0, ULong.m9147boximpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 0)) & 4294967295L) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 1)) & 4294967295L) << 32) & m8540getHighMasksVKNKU()))));
                    arrayList.set(i5, ULong.m9147boximpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 1)) & 4294967295L) >>> 31) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 2)) & 4294967295L) << 1)) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 3)) & 4294967295L) << 33))));
                } else if (i5 == 0) {
                    arrayList.set(i5, ULong.m9147boximpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 0)) & 4294967295L) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 1)) & 4294967295L) << 32) & m8540getHighMasksVKNKU()))));
                } else if (1 <= i5 && i5 < i3 - 1) {
                    arrayList.set(i5, ULong.m9147boximpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i8 + 1)) & 4294967295L) << (i7 + 32)) & m8540getHighMasksVKNKU()) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i8 - 1)) & 4294967295L) >>> (32 - i7)) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i8)) & 4294967295L) << i7)))));
                } else if (i5 == i3 - 1) {
                    if (i8 < UIntArray.m9135getSizeimpl(iArr)) {
                        arrayList.set(i5, ULong.m9147boximpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i8)) & 4294967295L) << i7) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i8 - 1)) & 4294967295L) >>> (32 - i7)))));
                    } else {
                        arrayList.set(i5, ULong.m9147boximpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i8 - 1)) & 4294967295L) >>> (32 - i7))));
                    }
                }
                if (i6 >= i3) {
                    break;
                }
                i5 = i6;
            }
        }
        return arrayList;
    }

    @NotNull
    /* renamed from: convertTo32BitRepresentation-g_c56RQ  reason: not valid java name */
    public final int[] m8530convertTo32BitRepresentationg_c56RQ(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "operand");
        List<ULong> convertTo64BitRepresentation = convertTo64BitRepresentation(list);
        int[] r11 = UIntArray.m9128constructorimpl(convertTo64BitRepresentation.size() * 2);
        int size = convertTo64BitRepresentation.size();
        if (size > 0) {
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                int i5 = i3 * 2;
                UIntArray.m9139setVXSXFK8(r11, i5, UInt.m9074constructorimpl((int) ULong.m9153constructorimpl(convertTo64BitRepresentation.get(i3).m9205unboximpl() & ULong.m9153constructorimpl(((long) BigInteger32Arithmetic.INSTANCE.m8467getBasepVg5ArA()) & 4294967295L))));
                UIntArray.m9139setVXSXFK8(r11, i5 + 1, UInt.m9074constructorimpl((int) ULong.m9153constructorimpl(convertTo64BitRepresentation.get(i3).m9205unboximpl() >>> 32)));
                if (i4 >= size) {
                    break;
                }
                i3 = i4;
            }
        }
        return BigInteger32Arithmetic.INSTANCE.m8500removeLeadingZeroshkIa6DI(r11);
    }

    @NotNull
    public final List<ULong> convertTo64BitRepresentation(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "operand");
        if (Intrinsics.areEqual((Object) list, (Object) getZERO())) {
            return getZERO();
        }
        int bitLength = bitLength(list);
        int i3 = bitLength % 64 == 0 ? bitLength / 64 : (bitLength / 64) + 1;
        ArrayList arrayList = new ArrayList(i3);
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5 = a.b(0, arrayList, i5, 1)) {
        }
        if (i3 > 0) {
            while (true) {
                int i6 = i4 + 1;
                int i7 = i4 % 63;
                int i8 = (i4 / 63) + i4;
                int i9 = i8 + 1;
                if (i9 < list.size()) {
                    arrayList.set(i4, ULong.m9147boximpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(list.get(i9).m9205unboximpl() << (63 - i7)) | ULong.m9153constructorimpl(list.get(i8).m9205unboximpl() >>> i7))));
                } else {
                    arrayList.set(i4, ULong.m9147boximpl(ULong.m9153constructorimpl(list.get(i8).m9205unboximpl() >>> i7)));
                }
                if (i6 >= i3) {
                    break;
                }
                i4 = i6;
            }
        }
        return arrayList;
    }

    @NotNull
    public final Pair<List<ULong>, List<ULong>> d1ReciprocalRecursiveWordVersion(@NotNull List<ULong> list) {
        List<ULong> list2;
        Intrinsics.checkNotNullParameter(list, "a");
        int size = list.size();
        int i3 = size - 1;
        if (i3 <= 2) {
            if (i3 == 0) {
                i3 = 1;
            }
            List<ULong> shl$bignum = shl$bignum(getONE(), i3 * 2 * wordSizeInBits);
            List<ULong> div$bignum = div$bignum(shl$bignum, list);
            return new Pair<>(div$bignum, minus$bignum(shl$bignum, times$bignum(div$bignum, list)));
        }
        int floor = (int) Math.floor(((double) (size - 2)) / ((double) 2));
        int i4 = i3 - floor;
        List<ULong> subList = list.subList((list.size() - i4) - 1, list.size());
        List<ULong> subList2 = list.subList(0, floor);
        Pair<List<ULong>, List<ULong>> d1ReciprocalRecursiveWordVersion = d1ReciprocalRecursiveWordVersion(subList);
        List<ULong> component1 = d1ReciprocalRecursiveWordVersion.component1();
        List<ULong> times$bignum = times$bignum(subList2, component1);
        int i5 = wordSizeInBits;
        List<ULong> shl$bignum2 = shl$bignum(d1ReciprocalRecursiveWordVersion.component2(), floor * i5);
        if (compareTo$bignum(shl$bignum2, times$bignum) >= 0) {
            list2 = minus$bignum(shl$bignum2, times$bignum);
        } else {
            component1 = minus$bignum(component1, getONE());
            list2 = minus$bignum(plus$bignum(shl$bignum2, list), times$bignum);
        }
        List<ULong> shr$bignum = shr$bignum(times$bignum(component1, shr$bignum(list2, i4 * i5)), i4 * i5);
        List<ULong> plus$bignum = plus$bignum(shl$bignum(component1, floor * i5), shr$bignum);
        List<ULong> minus$bignum = minus$bignum(shl$bignum(list2, floor * i5), times$bignum(list, shr$bignum));
        if (compareTo$bignum(minus$bignum, list) >= 0) {
            plus$bignum = plus$bignum(plus$bignum, getONE());
            minus$bignum = minus$bignum(minus$bignum, list);
            if (compareTo$bignum(minus$bignum, list) >= 0) {
                plus$bignum = plus$bignum(plus$bignum, getONE());
                minus$bignum = minus$bignum(minus$bignum, list);
            }
        }
        return new Pair<>(plus$bignum, minus$bignum);
    }

    @NotNull
    public final List<ULong> denormalize(@NotNull List<ULong> list, int i3) {
        Intrinsics.checkNotNullParameter(list, "remainderNormalized");
        return shr$bignum(list, i3);
    }

    @NotNull
    public final SignedULongArray div$bignum(@NotNull SignedULongArray signedULongArray, @NotNull SignedULongArray signedULongArray2) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        Intrinsics.checkNotNullParameter(signedULongArray2, "other");
        return signedDivide(signedULongArray, signedULongArray2);
    }

    @NotNull
    /* renamed from: div-2TYgG_w$bignum  reason: not valid java name */
    public final List<ULong> m8531div2TYgG_w$bignum(@NotNull List<ULong> list, long j2) {
        Intrinsics.checkNotNullParameter(list, "$receiver");
        return baseDivide(list, CollectionsKt.listOf(ULong.m9147boximpl(j2))).getFirst();
    }

    @NotNull
    public Pair<List<ULong>, List<ULong>> divide(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "first");
        Intrinsics.checkNotNullParameter(list2, "second");
        return baseDivide(list, list2);
    }

    @NotNull
    public final Pair<List<ULong>, List<ULong>> divrem$bignum(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(list2, "other");
        return baseDivide(list, list2);
    }

    @NotNull
    /* renamed from: extendULongArray-aPcrCvc  reason: not valid java name */
    public final List<ULong> m8532extendULongArrayaPcrCvc(@NotNull List<ULong> list, int i3, long j2) {
        Intrinsics.checkNotNullParameter(list, "original");
        int size = list.size() + i3;
        ArrayList arrayList = new ArrayList(size);
        int i4 = 0;
        while (i4 < size) {
            i4 = a.b(i4 < list.size() ? list.get(i4).m9205unboximpl() : j2, arrayList, i4, 1);
        }
        return arrayList;
    }

    @NotNull
    /* renamed from: from32Bit--ajY-9A$bignum  reason: not valid java name */
    public final List<ULong> m8533from32BitajY9A$bignum(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        return m8529convertFrom32BitRepresentationajY9A$bignum(iArr);
    }

    @NotNull
    public List<ULong> fromByte(byte b3) {
        return CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl((long) Math.abs(b3))));
    }

    @NotNull
    public List<ULong> fromByteArray(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "source");
        return CollectionsKt.toList(ULongArray.m9206boximpl(BigInteger63Arithmetic.INSTANCE.m8604fromByteArrayDHQ6RzY(bArr)));
    }

    @NotNull
    public List<ULong> fromInt(int i3) {
        return CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl((long) Math.abs(i3))));
    }

    @NotNull
    public List<ULong> fromLong(long j2) {
        return j2 == Long.MIN_VALUE ? CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(Long.MAX_VALUE) + 1))) : CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(Math.abs(j2)) & BigInteger63Arithmetic.INSTANCE.m8614getBaseMasksVKNKU())));
    }

    @NotNull
    public List<ULong> fromShort(short s3) {
        return CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl((long) Math.abs(s3))));
    }

    @NotNull
    /* renamed from: fromUByte-7apg3OU  reason: not valid java name */
    public List<ULong> m8534fromUByte7apg3OU(byte b3) {
        return CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl(((long) b3) & 255)));
    }

    @NotNull
    /* renamed from: fromUByteArray-GBYM_sE  reason: not valid java name */
    public List<ULong> m8535fromUByteArrayGBYM_sE(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "source");
        return CollectionsKt.toList(ULongArray.m9206boximpl(BigInteger63Arithmetic.INSTANCE.m8609fromUByteArrayS4JqeA(bArr)));
    }

    @NotNull
    /* renamed from: fromUInt-WZ4Q5Ns  reason: not valid java name */
    public List<ULong> m8536fromUIntWZ4Q5Ns(int i3) {
        return CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl(((long) i3) & 4294967295L)));
    }

    @NotNull
    /* renamed from: fromULong-VKZWuLQ  reason: not valid java name */
    public List<ULong> m8537fromULongVKZWuLQ(long j2) {
        BigInteger63Arithmetic bigInteger63Arithmetic = BigInteger63Arithmetic.INSTANCE;
        return ULong.m9153constructorimpl(bigInteger63Arithmetic.m8619getOverflowMasksVKNKU() & j2) != 0 ? CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl(j2 & bigInteger63Arithmetic.m8614getBaseMasksVKNKU())), ULong.m9147boximpl(1)) : CollectionsKt.listOf(ULong.m9147boximpl(j2));
    }

    @NotNull
    /* renamed from: fromUShort-xj2QHRw  reason: not valid java name */
    public List<ULong> m8538fromUShortxj2QHRw(short s3) {
        return CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl(((long) s3) & 65535)));
    }

    @NotNull
    public List<ULong> gcd(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "first");
        Intrinsics.checkNotNullParameter(list2, "second");
        return (list.size() > 150 || list2.size() > 150) ? euclideanGcd(list, list2) : binaryGcd(list, list2);
    }

    /* renamed from: getBaseMask-s-VKNKU  reason: not valid java name */
    public final long m8539getBaseMasksVKNKU() {
        return baseMask;
    }

    public int getBasePowerOfTwo() {
        return basePowerOfTwo;
    }

    /* renamed from: getHighMask-s-VKNKU  reason: not valid java name */
    public final long m8540getHighMasksVKNKU() {
        return highMask;
    }

    /* renamed from: getLowMask-s-VKNKU  reason: not valid java name */
    public final long m8541getLowMasksVKNKU() {
        return lowMask;
    }

    @NotNull
    public List<ULong> getONE() {
        return ONE;
    }

    /* renamed from: getOverflowMask-s-VKNKU  reason: not valid java name */
    public final long m8542getOverflowMasksVKNKU() {
        return overflowMask;
    }

    @NotNull
    public final List<ULong>[] getPowersOf10() {
        return powersOf10;
    }

    @NotNull
    public final SignedULongArray getSIGNED_POSITIVE_TWO() {
        return SIGNED_POSITIVE_TWO;
    }

    @NotNull
    public List<ULong> getTEN() {
        return TEN;
    }

    @NotNull
    public List<ULong> getTWO() {
        return TWO;
    }

    public final int getWordSizeInBits() {
        return wordSizeInBits;
    }

    @NotNull
    public List<ULong> getZERO() {
        return ZERO;
    }

    @NotNull
    public final List<ULong> karatsubaMultiply(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "first");
        Intrinsics.checkNotNullParameter(list2, "second");
        int max = (Math.max(list.size(), list2.size()) + 1) / 2;
        List<ULong> one = getONE();
        int i3 = wordSizeInBits;
        List<ULong> r12 = m8543minus2TYgG_w$bignum(shl$bignum(one, max * i3), 1);
        List<ULong> and = and(list, r12);
        List<ULong> shr$bignum = shr$bignum(list, max * i3);
        List<ULong> and2 = and(list2, r12);
        List<ULong> shr$bignum2 = shr$bignum(list2, max * i3);
        List<ULong> times$bignum = times$bignum(shr$bignum, shr$bignum2);
        List<ULong> times$bignum2 = times$bignum(and, and2);
        return plus$bignum(plus$bignum(shl$bignum(times$bignum, max * 126), shl$bignum(minus$bignum(minus$bignum(times$bignum(plus$bignum(shr$bignum, and), plus$bignum(shr$bignum2, and2)), times$bignum), times$bignum2), i3 * max)), times$bignum2);
    }

    @NotNull
    public final SignedULongArray minus$bignum(@NotNull SignedULongArray signedULongArray, @NotNull SignedULongArray signedULongArray2) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        Intrinsics.checkNotNullParameter(signedULongArray2, "other");
        return signedSubtract(signedULongArray, signedULongArray2);
    }

    @NotNull
    /* renamed from: minus-2TYgG_w$bignum  reason: not valid java name */
    public final List<ULong> m8543minus2TYgG_w$bignum(@NotNull List<ULong> list, long j2) {
        Intrinsics.checkNotNullParameter(list, "$receiver");
        return subtract(list, CollectionsKt.listOf(ULong.m9147boximpl(j2)));
    }

    @NotNull
    public List<ULong> multiply(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "first");
        Intrinsics.checkNotNullParameter(list2, "second");
        return (Intrinsics.areEqual((Object) list, (Object) getZERO()) || Intrinsics.areEqual((Object) list2, (Object) getZERO())) ? getZERO() : ((list.size() >= 120 || list2.size() >= 120) && (list.size() <= 15000 || list2.size() < 15000)) ? karatsubaMultiply(list, list2) : (list.size() < 15000 || list2.size() < 15000) ? removeLeadingZeros(basecaseMultiply(list, list2)) : toomCook3Multiply(list, list2);
    }

    @NotNull
    /* renamed from: multiply-2TYgG_w  reason: not valid java name */
    public final List<ULong> m8544multiply2TYgG_w(@NotNull List<ULong> list, long j2) {
        long j3;
        List<ULong> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "first");
        long r12 = ULong.m9153constructorimpl(j2 & m8541getLowMasksVKNKU());
        long r4 = ULong.m9153constructorimpl(j2 >>> 32);
        int size = list.size() + 1;
        ArrayList arrayList = new ArrayList(size);
        int i3 = 0;
        int i4 = 0;
        while (true) {
            j3 = 0;
            if (i4 >= size) {
                break;
            }
            i4 = a.b(0, arrayList, i4, 1);
        }
        int i5 = 0;
        long j4 = 0;
        while (i3 < list.size()) {
            long r15 = ULong.m9153constructorimpl(list2.get(i3).m9205unboximpl() & m8541getLowMasksVKNKU());
            long r17 = ULong.m9153constructorimpl(list2.get(i3).m9205unboximpl() >>> 32);
            i3++;
            long r19 = ULong.m9153constructorimpl(r15 * r12);
            long r11 = ULong.m9153constructorimpl(r19 >>> 63);
            long d2 = com.google.firebase.crashlytics.internal.a.d(r19 & m8539getBaseMasksVKNKU(), j4);
            long d3 = com.google.firebase.crashlytics.internal.a.d(d2 >>> 63, r11);
            long r7 = ULong.m9153constructorimpl(d2 & m8539getBaseMasksVKNKU());
            long r13 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(r12 * r17) + ULong.m9153constructorimpl(r15 * r4));
            long d4 = com.google.firebase.crashlytics.internal.a.d(r13 >>> 31, d3);
            long d5 = com.google.firebase.crashlytics.internal.a.d(ULong.m9153constructorimpl(r13 << 32) & m8539getBaseMasksVKNKU(), r7);
            long d6 = com.google.firebase.crashlytics.internal.a.d(d5 >>> 63, d4);
            ULong r2 = ULong.m9147boximpl(ULong.m9153constructorimpl(d5 & m8539getBaseMasksVKNKU()));
            ArrayList arrayList2 = arrayList;
            arrayList2.set(i5, r2);
            j4 = com.google.firebase.crashlytics.internal.a.d(ULong.m9153constructorimpl(r17 * r4) << 1, d6);
            i5++;
            list2 = list;
            arrayList = arrayList2;
            j3 = 0;
            r12 = r12;
        }
        ArrayList arrayList3 = arrayList;
        if (j4 != j3) {
            arrayList3.set(i5, ULong.m9147boximpl(j4));
        }
        return removeLeadingZeros(arrayList3);
    }

    @NotNull
    /* renamed from: multiply-PWzV0Is  reason: not valid java name */
    public final List<ULong> m8545multiplyPWzV0Is(long j2, long j3) {
        long r02 = ULong.m9153constructorimpl(j2 & m8541getLowMasksVKNKU());
        long r3 = ULong.m9153constructorimpl(j2 >>> 32);
        long r5 = ULong.m9153constructorimpl(j3 & m8541getLowMasksVKNKU());
        long r7 = ULong.m9153constructorimpl(j3 >>> 32);
        long r9 = ULong.m9153constructorimpl(r02 * r5);
        long r12 = ULong.m9153constructorimpl(r9 >>> 63);
        long r92 = ULong.m9153constructorimpl(r9 & m8539getBaseMasksVKNKU());
        long r03 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(r5 * r3) + ULong.m9153constructorimpl(r02 * r7));
        long d2 = com.google.firebase.crashlytics.internal.a.d(r03 >>> 31, r12);
        long d3 = com.google.firebase.crashlytics.internal.a.d(ULong.m9153constructorimpl(r03 << 32) & m8539getBaseMasksVKNKU(), r92);
        return removeLeadingZeros(CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl(d3 & m8539getBaseMasksVKNKU())), ULong.m9147boximpl(com.google.firebase.crashlytics.internal.a.d(ULong.m9153constructorimpl(r3 * r7) << 1, com.google.firebase.crashlytics.internal.a.d(d3 >>> 63, d2)))));
    }

    @NotNull
    public final Triple<List<ULong>, List<ULong>, Integer> normalize(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "dividend");
        Intrinsics.checkNotNullParameter(list2, "divisor");
        int r02 = m8546numberOfLeadingZerosInAWordVKZWuLQ(list2.get(list2.size() - 1).m9205unboximpl());
        return new Triple<>(shl$bignum(list, r02), shl$bignum(list2, r02), Integer.valueOf(r02));
    }

    @NotNull
    public List<ULong> not(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "operand");
        int r02 = m8546numberOfLeadingZerosInAWordVKZWuLQ(list.get(list.size() - 1).m9205unboximpl());
        long r03 = ULong.m9153constructorimpl(~ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(1 << (r02 + 1)) - ULong.m9153constructorimpl(((long) 1) & 4294967295L)) << (getBasePowerOfTwo() - r02)));
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        int i3 = 0;
        while (i3 < size) {
            i3 = a.b(i3 < list.size() + -2 ? ULong.m9153constructorimpl(ULong.m9153constructorimpl(~list.get(i3).m9205unboximpl()) & INSTANCE.m8539getBaseMasksVKNKU()) : ULong.m9153constructorimpl(ULong.m9153constructorimpl(~list.get(i3).m9205unboximpl()) & r03), arrayList, i3, 1);
        }
        return arrayList;
    }

    public long numberOfDecimalDigits(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "operand");
        double ceil = Math.ceil(BigInteger.Companion.getLOG_10_OF_2() * ((double) (bitLength(list) - 1)));
        List<ULong> div$bignum = div$bignum(list, pow(getTEN(), (long) ceil));
        long j2 = 0;
        while (compare(div$bignum, getZERO()) != 0) {
            div$bignum = div$bignum(div$bignum, getTEN());
            j2++;
        }
        return j2 + ((long) ((int) ceil));
    }

    /* renamed from: numberOfLeadingZerosInAWord-VKZWuLQ  reason: not valid java name */
    public int m8546numberOfLeadingZerosInAWordVKZWuLQ(long j2) {
        int i3;
        long r02 = ULong.m9153constructorimpl(j2 >>> 32);
        if (r02 != 0) {
            i3 = 31;
            j2 = r02;
        } else {
            i3 = 63;
        }
        long r03 = ULong.m9153constructorimpl(j2 >>> 16);
        if (r03 != 0) {
            i3 -= 16;
            j2 = r03;
        }
        long r04 = ULong.m9153constructorimpl(j2 >>> 8);
        if (r04 != 0) {
            i3 -= 8;
            j2 = r04;
        }
        long r05 = ULong.m9153constructorimpl(j2 >>> 4);
        if (r05 != 0) {
            i3 -= 4;
            j2 = r05;
        }
        long r4 = ULong.m9153constructorimpl(j2 >>> 2);
        if (r4 != 0) {
            i3 -= 2;
            j2 = r4;
        }
        return ULong.m9153constructorimpl(j2 >>> 1) != 0 ? i3 - 2 : i3 - ((int) j2);
    }

    /* renamed from: numberOfTrailingZerosInAWord-VKZWuLQ  reason: not valid java name */
    public final int m8547numberOfTrailingZerosInAWordVKZWuLQ(long j2) {
        int i3;
        long r02 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 << 32) & m8539getBaseMasksVKNKU());
        if (r02 != 0) {
            long j3 = r02;
            i3 = 31;
            j2 = j3;
        } else {
            i3 = 63;
        }
        long r4 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 << 16) & m8539getBaseMasksVKNKU());
        if (r4 != 0) {
            i3 -= 16;
            j2 = r4;
        }
        long r42 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 << 8) & m8539getBaseMasksVKNKU());
        if (r42 != 0) {
            i3 -= 8;
            j2 = r42;
        }
        long r43 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 << 4) & m8539getBaseMasksVKNKU());
        if (r43 != 0) {
            i3 -= 4;
            j2 = r43;
        }
        long r44 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 << 2) & m8539getBaseMasksVKNKU());
        if (r44 != 0) {
            i3 -= 2;
            j2 = r44;
        }
        return ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 << 1) & m8539getBaseMasksVKNKU()) != 0 ? i3 - 2 : i3 - ((int) j2);
    }

    @NotNull
    public List<ULong> or(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "operand");
        Intrinsics.checkNotNullParameter(list2, "mask");
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        int i3 = 0;
        while (i3 < size) {
            i3 = a.b(i3 < list2.size() ? ULong.m9153constructorimpl(list.get(i3).m9205unboximpl() | list2.get(i3).m9205unboximpl()) : list.get(i3).m9205unboximpl(), arrayList, i3, 1);
        }
        return removeLeadingZeros(arrayList);
    }

    @NotNull
    public List<ULong> parseForBase(@NotNull String str, int i3) {
        Intrinsics.checkNotNullParameter(str, "number");
        List<ULong> zero = getZERO();
        String lowerCase = str.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
        for (int i4 = 0; i4 < lowerCase.length(); i4++) {
            char charAt = lowerCase.charAt(i4);
            BigInteger63LinkedListArithmetic bigInteger63LinkedListArithmetic = INSTANCE;
            zero = bigInteger63LinkedListArithmetic.m8548plus2TYgG_w$bignum(bigInteger63LinkedListArithmetic.m8551times2TYgG_w$bignum(zero, ULong.m9153constructorimpl((long) i3)), ULong.m9153constructorimpl((long) DigitUtilKt.toDigit(charAt, i3)));
        }
        return removeLeadingZeros(zero);
    }

    @NotNull
    public final SignedULongArray plus$bignum(@NotNull SignedULongArray signedULongArray, @NotNull SignedULongArray signedULongArray2) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        Intrinsics.checkNotNullParameter(signedULongArray2, "other");
        return signedAdd(signedULongArray, signedULongArray2);
    }

    @NotNull
    /* renamed from: plus-2TYgG_w$bignum  reason: not valid java name */
    public final List<ULong> m8548plus2TYgG_w$bignum(@NotNull List<ULong> list, long j2) {
        Intrinsics.checkNotNullParameter(list, "$receiver");
        return add(list, CollectionsKt.listOf(ULong.m9147boximpl(j2)));
    }

    @NotNull
    public List<ULong> pow(@NotNull List<ULong> list, long j2) {
        Intrinsics.checkNotNullParameter(list, TtmlNode.RUBY_BASE);
        if (j2 == 0) {
            return getONE();
        }
        if (j2 == 1) {
            return list;
        }
        if (list.size() == 1 && list.get(0).m9205unboximpl() == 10) {
            List<ULong>[] listArr = powersOf10;
            if (j2 < ((long) listArr.length)) {
                return listArr[(int) j2];
            }
        }
        List<ULong> one = getONE();
        while (j2 > 1) {
            long j3 = (long) 2;
            if (j2 % j3 == 0) {
                list = times$bignum(list, list);
                j2 /= j3;
            } else {
                one = times$bignum(list, one);
                list = times$bignum(list, list);
                j2 = (j2 - 1) / j3;
            }
        }
        return times$bignum(one, list);
    }

    @NotNull
    /* renamed from: prependULongArray-aPcrCvc  reason: not valid java name */
    public final List<ULong> m8549prependULongArrayaPcrCvc(@NotNull List<ULong> list, int i3, long j2) {
        Intrinsics.checkNotNullParameter(list, "original");
        int size = list.size() + i3;
        ArrayList arrayList = new ArrayList(size);
        int i4 = 0;
        while (i4 < size) {
            i4 = a.b(i4 < i3 ? j2 : list.get(i4 - i3).m9205unboximpl(), arrayList, i4, 1);
        }
        return arrayList;
    }

    @NotNull
    public Pair<List<ULong>, List<ULong>> reciprocal(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "operand");
        return d1ReciprocalRecursiveWordVersion(list);
    }

    @NotNull
    public final SignedULongArray rem$bignum(@NotNull SignedULongArray signedULongArray, @NotNull SignedULongArray signedULongArray2) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        Intrinsics.checkNotNullParameter(signedULongArray2, "other");
        return signedRemainder(signedULongArray, signedULongArray2);
    }

    @NotNull
    /* renamed from: rem-2TYgG_w$bignum  reason: not valid java name */
    public final List<ULong> m8550rem2TYgG_w$bignum(@NotNull List<ULong> list, long j2) {
        Intrinsics.checkNotNullParameter(list, "$receiver");
        return baseDivide(list, CollectionsKt.listOf(ULong.m9147boximpl(j2))).getSecond();
    }

    @NotNull
    public final List<ULong> removeLeadingZeros(@NotNull List<ULong> list) {
        int i3;
        Intrinsics.checkNotNullParameter(list, "bigInteger");
        ListIterator<ULong> listIterator = list.listIterator(list.size());
        while (true) {
            if (listIterator.hasPrevious()) {
                if (listIterator.previous().m9205unboximpl() != 0) {
                    i3 = listIterator.nextIndex();
                    break;
                }
            } else {
                i3 = -1;
                break;
            }
        }
        int i4 = i3 + 1;
        return (i4 == -1 || i4 == 0) ? getZERO() : list.subList(0, i4);
    }

    @NotNull
    public List<ULong> setBitAt(@NotNull List<ULong> list, long j2, boolean z2) {
        Intrinsics.checkNotNullParameter(list, "operand");
        long j3 = (long) 63;
        long j4 = j2 / j3;
        if (j4 > SieveCacheKt.NodeLinkMask) {
            throw new RuntimeException("Invalid bit index, too large, cannot access word (Word position > Int.MAX_VALUE");
        } else if (j4 < ((long) list.size())) {
            long r9 = ULong.m9153constructorimpl(1 << ((int) (j2 % j3)));
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            int i3 = 0;
            while (i3 < size) {
                i3 = a.b(i3 == ((int) j4) ? z2 ? ULong.m9153constructorimpl(list.get(i3).m9205unboximpl() | r9) : ULong.m9153constructorimpl(list.get(i3).m9205unboximpl() ^ r9) : list.get(i3).m9205unboximpl(), arrayList, i3, 1);
            }
            return arrayList;
        } else {
            StringBuilder u3 = androidx.compose.animation.core.a.u("Invalid position, addressed word ", j4, " larger than number of words ");
            u3.append(list.size());
            throw new IndexOutOfBoundsException(u3.toString());
        }
    }

    @NotNull
    public List<ULong> shiftLeft(@NotNull List<ULong> list, int i3) {
        long j2;
        List<ULong> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "operand");
        if (Intrinsics.areEqual((Object) list2, (Object) getZERO()) || list.isEmpty() || i3 == 0) {
            return list2;
        }
        int size = list.size();
        int r3 = m8546numberOfLeadingZerosInAWordVKZWuLQ(((ULong) android.support.v4.media.session.a.h(list2, 1)).m9205unboximpl());
        int basePowerOfTwo2 = i3 / getBasePowerOfTwo();
        int basePowerOfTwo3 = i3 % getBasePowerOfTwo();
        int i4 = basePowerOfTwo3 > r3 ? basePowerOfTwo2 + 1 : basePowerOfTwo2;
        int i5 = 0;
        if (basePowerOfTwo3 == 0) {
            int size2 = list.size() + i4;
            ArrayList arrayList = new ArrayList(size2);
            while (i5 < size2) {
                i5 = a.b((i5 < 0 || i5 >= basePowerOfTwo2) ? list2.get(i5 - basePowerOfTwo2).m9205unboximpl() : 0, arrayList, i5, 1);
            }
            return arrayList;
        }
        int size3 = list.size() + i4;
        ArrayList arrayList2 = new ArrayList(size3);
        while (i5 < size3) {
            if (i5 >= 0 && i5 < basePowerOfTwo2) {
                j2 = 0;
            } else if (i5 == basePowerOfTwo2) {
                j2 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(list2.get(i5 - basePowerOfTwo2).m9205unboximpl() << basePowerOfTwo3) & INSTANCE.m8539getBaseMasksVKNKU());
            } else {
                int i6 = basePowerOfTwo2 + 1;
                if (i5 < size + basePowerOfTwo2 && i6 <= i5) {
                    int i7 = i5 - basePowerOfTwo2;
                    long r12 = ULong.m9153constructorimpl(list2.get(i7).m9205unboximpl() << basePowerOfTwo3);
                    BigInteger63LinkedListArithmetic bigInteger63LinkedListArithmetic = INSTANCE;
                    j2 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(r12 & bigInteger63LinkedListArithmetic.m8539getBaseMasksVKNKU()) | ULong.m9153constructorimpl(list2.get(i7 - 1).m9205unboximpl() >>> (bigInteger63LinkedListArithmetic.getBasePowerOfTwo() - basePowerOfTwo3)));
                } else if (i5 == (size + i4) - 1) {
                    j2 = ULong.m9153constructorimpl(list2.get(i5 - i4).m9205unboximpl() >>> (INSTANCE.getBasePowerOfTwo() - basePowerOfTwo3));
                } else {
                    throw new RuntimeException(Intrinsics.stringPlus("Invalid case ", Integer.valueOf(i5)));
                }
            }
            i5 = a.b(j2, arrayList2, i5, 1);
        }
        return arrayList2;
    }

    @NotNull
    public List<ULong> shiftRight(@NotNull List<ULong> list, int i3) {
        long j2;
        Intrinsics.checkNotNullParameter(list, "operand");
        if (list.isEmpty() || i3 == 0) {
            return list;
        }
        int basePowerOfTwo2 = i3 % getBasePowerOfTwo();
        int basePowerOfTwo3 = i3 / getBasePowerOfTwo();
        if (basePowerOfTwo3 >= list.size()) {
            return getZERO();
        }
        if (basePowerOfTwo2 == 0) {
            list.subList(list.size() - basePowerOfTwo3, list.size());
        }
        if (list.size() > 1 && list.size() - basePowerOfTwo3 == 1) {
            return CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl(((ULong) android.support.v4.media.session.a.h(list, 1)).m9205unboximpl() >>> basePowerOfTwo2)));
        }
        int size = list.size() - basePowerOfTwo3;
        ArrayList arrayList = new ArrayList(size);
        int i4 = 0;
        while (i4 < size) {
            if (i4 >= 0 && i4 < (list.size() - 1) - basePowerOfTwo3) {
                int i5 = i4 + basePowerOfTwo3;
                long r6 = ULong.m9153constructorimpl(list.get(i5).m9205unboximpl() >>> basePowerOfTwo2);
                long r8 = list.get(i5 + 1).m9205unboximpl();
                BigInteger63LinkedListArithmetic bigInteger63LinkedListArithmetic = INSTANCE;
                j2 = ULong.m9153constructorimpl(r6 | ULong.m9153constructorimpl(ULong.m9153constructorimpl(r8 << (bigInteger63LinkedListArithmetic.getBasePowerOfTwo() - basePowerOfTwo2)) & bigInteger63LinkedListArithmetic.m8539getBaseMasksVKNKU()));
            } else if (i4 == (list.size() - 1) - basePowerOfTwo3) {
                j2 = ULong.m9153constructorimpl(list.get(i4 + basePowerOfTwo3).m9205unboximpl() >>> basePowerOfTwo2);
            } else {
                throw new RuntimeException(Intrinsics.stringPlus("Invalid case ", Integer.valueOf(i4)));
            }
            i4 = a.b(j2, arrayList, i4, 1);
        }
        return removeLeadingZeros(arrayList);
    }

    @NotNull
    public final SignedULongArray shl$bignum(@NotNull SignedULongArray signedULongArray, int i3) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        return new SignedULongArray(shl$bignum(signedULongArray.getUnsignedValue(), i3), signedULongArray.getSign());
    }

    @NotNull
    public final SignedULongArray shr$bignum(@NotNull SignedULongArray signedULongArray, int i3) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        return new SignedULongArray(shr$bignum(signedULongArray.getUnsignedValue(), i3), signedULongArray.getSign());
    }

    @NotNull
    public Pair<List<ULong>, List<ULong>> sqrt(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "operand");
        return reqursiveSqrt(list);
    }

    @NotNull
    public final List<ULong> sqrtInt$bignum(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "operand");
        getZERO();
        getZERO();
        List<ULong> list2 = list;
        while (true) {
            List<ULong> shr$bignum = shr$bignum(plus$bignum(list2, div$bignum(list, list2)), 1);
            if (compareTo$bignum(shr$bignum, list2) >= 0) {
                return list2;
            }
            list2 = shr$bignum;
        }
    }

    @NotNull
    public List<ULong> subtract(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        List<ULong> list3 = list;
        List<ULong> list4 = list2;
        Intrinsics.checkNotNullParameter(list3, "first");
        Intrinsics.checkNotNullParameter(list4, "second");
        List<ULong> removeLeadingZeros = removeLeadingZeros(list);
        List<ULong> removeLeadingZeros2 = removeLeadingZeros(list4);
        int compare = compare(removeLeadingZeros, removeLeadingZeros2);
        boolean z2 = compare == 1;
        if (compare == 0) {
            return getZERO();
        }
        if (list2.size() == 1 && list4.get(0).m9205unboximpl() == 0) {
            return list3;
        }
        if (z2) {
            Quadruple quadruple = z2 ? new Quadruple(Integer.valueOf(removeLeadingZeros.size()), Integer.valueOf(removeLeadingZeros2.size()), removeLeadingZeros, removeLeadingZeros2) : new Quadruple(Integer.valueOf(removeLeadingZeros2.size()), Integer.valueOf(removeLeadingZeros.size()), removeLeadingZeros2, removeLeadingZeros);
            int intValue = ((Number) quadruple.component1()).intValue();
            int intValue2 = ((Number) quadruple.component2()).intValue();
            List list5 = (List) quadruple.component3();
            List list6 = (List) quadruple.component4();
            int i3 = intValue + 1;
            ArrayList arrayList = new ArrayList(i3);
            for (int i4 = 0; i4 < i3; i4 = a.b(0, arrayList, i4, 1)) {
            }
            int i5 = 0;
            long j2 = 0;
            while (i5 < intValue2) {
                long r11 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(((ULong) list5.get(i5)).m9205unboximpl() - ((ULong) list6.get(i5)).m9205unboximpl()) - j2);
                if (ULong.m9153constructorimpl(ULong.m9153constructorimpl(m8542getOverflowMasksVKNKU() & r11) >>> 63) == 1) {
                    arrayList.set(i5, ULong.m9147boximpl(ULong.m9153constructorimpl(m8539getBaseMasksVKNKU() & r11)));
                } else {
                    arrayList.set(i5, ULong.m9147boximpl(ULong.m9153constructorimpl(m8539getBaseMasksVKNKU() & r11)));
                }
                j2 = ULong.m9153constructorimpl(r11 >>> 63);
                i5++;
            }
            while (j2 != 0) {
                long r112 = ULong.m9153constructorimpl(((ULong) list5.get(i5)).m9205unboximpl() - j2);
                if (ULong.m9153constructorimpl(ULong.m9153constructorimpl(m8542getOverflowMasksVKNKU() & r112) >>> 63) == 1) {
                    arrayList.set(i5, ULong.m9147boximpl(ULong.m9153constructorimpl(m8539getBaseMasksVKNKU() & r112)));
                } else {
                    arrayList.set(i5, ULong.m9147boximpl(ULong.m9153constructorimpl(r112 & m8539getBaseMasksVKNKU())));
                    r112 = 0;
                }
                j2 = ULong.m9153constructorimpl(r112 >>> 63);
                i5++;
            }
            while (i5 < intValue) {
                arrayList.set(i5, list5.get(i5));
                i5++;
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object next : arrayList) {
                if (((ULong) next).m9205unboximpl() == 0) {
                    arrayList2.add(next);
                }
            }
            return arrayList2.isEmpty() ? new ArrayList(0) : removeLeadingZeros(arrayList);
        }
        throw new RuntimeException("subtract result less than zero");
    }

    @NotNull
    public final SignedULongArray times$bignum(@NotNull SignedULongArray signedULongArray, @NotNull SignedULongArray signedULongArray2) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        Intrinsics.checkNotNullParameter(signedULongArray2, "other");
        return signedMultiply(signedULongArray, signedULongArray2);
    }

    @NotNull
    /* renamed from: times-2TYgG_w$bignum  reason: not valid java name */
    public final List<ULong> m8551times2TYgG_w$bignum(@NotNull List<ULong> list, long j2) {
        Intrinsics.checkNotNullParameter(list, "$receiver");
        return multiply(list, CollectionsKt.listOf(ULong.m9147boximpl(j2)));
    }

    @NotNull
    /* renamed from: to32Bit-g_c56RQ$bignum  reason: not valid java name */
    public final int[] m8552to32Bitg_c56RQ$bignum(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "$receiver");
        return m8530convertTo32BitRepresentationg_c56RQ(list);
    }

    @NotNull
    public byte[] toByteArray(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "operand");
        return BigInteger63Arithmetic.INSTANCE.m8662toByteArrayQwZRm1k(UCollectionsKt.toULongArray(list));
    }

    @NotNull
    public String toString(@NotNull List<ULong> list, int i3) {
        Intrinsics.checkNotNullParameter(list, "operand");
        if (Intrinsics.areEqual((Object) list, (Object) getZERO())) {
            return SchemaSymbols.ATTVAL_FALSE_0;
        }
        List listOf = CollectionsKt.listOf(ULong.m9147boximpl(ULong.m9153constructorimpl((long) i3)));
        StringBuilder sb = new StringBuilder();
        while (!Intrinsics.areEqual((Object) list, (Object) getZERO())) {
            Pair<List<ULong>, List<ULong>> divrem$bignum = divrem$bignum(list, listOf);
            if (divrem$bignum.getSecond().isEmpty()) {
                sb.append(0);
            } else {
                sb.append(UStringsKt.m10286toStringJSWoG40(((ULong) divrem$bignum.getSecond().get(0)).m9205unboximpl(), i3));
            }
            list = divrem$bignum.getFirst();
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "stringBuilder.toString()");
        return StringsKt___StringsKt.reversed((CharSequence) sb2).toString();
    }

    @NotNull
    /* renamed from: toUByteArray-NTtOWj4  reason: not valid java name */
    public byte[] m8553toUByteArrayNTtOWj4(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "operand");
        return BigInteger63Arithmetic.INSTANCE.m8664toUByteArraycMszsnM(UCollectionsKt.toULongArray(list));
    }

    @NotNull
    public final List<ULong> toomCook3Multiply(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "firstUnchecked");
        Intrinsics.checkNotNullParameter(list2, "secondUnchecked");
        if (list.size() % 3 != 0) {
            Collection collection = list;
            int size = (((list.size() + 2) / 3) * 3) - list.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i3 = 0; i3 < size; i3 = a.b(0, arrayList, i3, 1)) {
            }
            list = CollectionsKt.plus(collection, arrayList);
        }
        if (list2.size() % 3 != 0) {
            Collection collection2 = list2;
            int size2 = (((list2.size() + 2) / 3) * 3) - list2.size();
            ArrayList arrayList2 = new ArrayList(size2);
            for (int i4 = 0; i4 < size2; i4 = a.b(0, arrayList2, i4, 1)) {
            }
            list2 = CollectionsKt.plus(collection2, arrayList2);
        }
        int size3 = list.size();
        int size4 = list2.size();
        Pair pair = size3 > size4 ? new Pair(list, m8532extendULongArrayaPcrCvc(list2, size3 - size4, 0)) : size3 < size4 ? new Pair(m8532extendULongArrayaPcrCvc(list, size4 - size3, 0), list2) : new Pair(list, list2);
        List list3 = (List) pair.component1();
        List list4 = (List) pair.component2();
        int max = (Math.max(list.size(), list2.size()) + 2) / 3;
        SignedULongArray signedULongArray = new SignedULongArray(CollectionsKt.slice(list3, RangesKt.until(0, max)), true);
        int i5 = max * 2;
        SignedULongArray signedULongArray2 = new SignedULongArray(CollectionsKt.slice(list3, RangesKt.until(max, i5)), true);
        int i6 = max * 3;
        SignedULongArray signedULongArray3 = new SignedULongArray(CollectionsKt.slice(list3, RangesKt.until(i5, i6)), true);
        SignedULongArray signedULongArray4 = new SignedULongArray(CollectionsKt.slice(list4, RangesKt.until(0, max)), true);
        SignedULongArray signedULongArray5 = new SignedULongArray(CollectionsKt.slice(list4, RangesKt.until(max, i5)), true);
        SignedULongArray signedULongArray6 = new SignedULongArray(CollectionsKt.slice(list4, RangesKt.until(i5, i6)), true);
        SignedULongArray plus$bignum = plus$bignum(signedULongArray, signedULongArray3);
        SignedULongArray plus$bignum2 = plus$bignum(plus$bignum, signedULongArray2);
        SignedULongArray minus$bignum = minus$bignum(plus$bignum, signedULongArray2);
        SignedULongArray plus$bignum3 = plus$bignum(minus$bignum, signedULongArray3);
        SignedULongArray signedULongArray7 = SIGNED_POSITIVE_TWO;
        SignedULongArray minus$bignum2 = minus$bignum(times$bignum(plus$bignum3, signedULongArray7), signedULongArray);
        SignedULongArray plus$bignum4 = plus$bignum(signedULongArray4, signedULongArray6);
        SignedULongArray plus$bignum5 = plus$bignum(plus$bignum4, signedULongArray5);
        SignedULongArray minus$bignum3 = minus$bignum(plus$bignum4, signedULongArray5);
        SignedULongArray minus$bignum4 = minus$bignum(times$bignum(plus$bignum(minus$bignum3, signedULongArray6), signedULongArray7), signedULongArray4);
        SignedULongArray times$bignum = times$bignum(signedULongArray, signedULongArray4);
        SignedULongArray times$bignum2 = times$bignum(plus$bignum2, plus$bignum5);
        SignedULongArray times$bignum3 = times$bignum(minus$bignum, minus$bignum3);
        SignedULongArray times$bignum4 = times$bignum(minus$bignum2, minus$bignum4);
        SignedULongArray times$bignum5 = times$bignum(signedULongArray3, signedULongArray6);
        SignedULongArray div$bignum = div$bignum(minus$bignum(times$bignum4, times$bignum2), new SignedULongArray(CollectionsKt.listOf(ULong.m9147boximpl(3)), true));
        SignedULongArray shr$bignum = shr$bignum(minus$bignum(times$bignum2, times$bignum3), 1);
        SignedULongArray minus$bignum5 = minus$bignum(times$bignum3, times$bignum);
        SignedULongArray plus$bignum6 = plus$bignum(shr$bignum(minus$bignum(minus$bignum5, div$bignum), 1), times$bignum(signedULongArray7, times$bignum5));
        SignedULongArray minus$bignum6 = minus$bignum(plus$bignum(minus$bignum5, shr$bignum), times$bignum5);
        return plus$bignum(plus$bignum(plus$bignum(plus$bignum(times$bignum, shl$bignum(minus$bignum(shr$bignum, plus$bignum6), max * 63)), shl$bignum(minus$bignum6, max * 126)), shl$bignum(plus$bignum6, max * 189)), shl$bignum(times$bignum5, max * 252)).getUnsignedValue();
    }

    public int trailingZeroBits(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "value");
        if (Intrinsics.areEqual((Object) list, (Object) getZERO())) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        for (Object next : list) {
            if (((ULong) next).m9205unboximpl() != 0) {
                break;
            }
            arrayList.add(next);
        }
        int size = arrayList.size();
        if (size == list.size()) {
            return 0;
        }
        return (size * 63) + m8554trailingZeroBitsVKZWuLQ(list.get(size).m9205unboximpl());
    }

    /* renamed from: trailingZeroBits-VKZWuLQ  reason: not valid java name */
    public final int m8554trailingZeroBitsVKZWuLQ(long j2) {
        return m8547numberOfTrailingZerosInAWordVKZWuLQ(j2);
    }

    @NotNull
    public List<ULong> xor(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "operand");
        Intrinsics.checkNotNullParameter(list2, "mask");
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        int i3 = 0;
        while (i3 < size) {
            i3 = a.b(i3 < list2.size() ? ULong.m9153constructorimpl(list.get(i3).m9205unboximpl() ^ list2.get(i3).m9205unboximpl()) : ULong.m9153constructorimpl(list.get(i3).m9205unboximpl()), arrayList, i3, 1);
        }
        return removeLeadingZeros(arrayList);
    }

    @NotNull
    public final List<ULong> div$bignum(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(list2, "other");
        return baseDivide(list, list2).getFirst();
    }

    @NotNull
    public final List<ULong> minus$bignum(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(list2, "other");
        return subtract(list, list2);
    }

    @NotNull
    public final List<ULong> plus$bignum(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(list2, "other");
        return add(list, list2);
    }

    @NotNull
    public final List<ULong> rem$bignum(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(list2, "other");
        return baseDivide(list, list2).getSecond();
    }

    @NotNull
    public final List<ULong> shl$bignum(@NotNull List<ULong> list, int i3) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return shiftLeft(list, i3);
    }

    @NotNull
    public final List<ULong> shr$bignum(@NotNull List<ULong> list, int i3) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return shiftRight(list, i3);
    }

    @NotNull
    public final List<ULong> times$bignum(@NotNull List<ULong> list, @NotNull List<ULong> list2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(list2, "other");
        return multiply(list, list2);
    }

    @NotNull
    public final Pair<List<ULong>, Integer> normalize(@NotNull List<ULong> list) {
        Intrinsics.checkNotNullParameter(list, "operand");
        int r02 = m8546numberOfLeadingZerosInAWordVKZWuLQ(list.get(list.size() - 1).m9205unboximpl());
        return new Pair<>(shl$bignum(list, r02), Integer.valueOf(r02));
    }
}
