package com.ionspin.kotlin.bignum.integer.base63.array;

import androidx.camera.core.impl.i;
import androidx.collection.SieveCacheKt;
import com.google.firebase.crashlytics.internal.a;
import com.ionspin.kotlin.bignum.integer.BigInteger;
import com.ionspin.kotlin.bignum.integer.BigIntegerArithmetic;
import com.ionspin.kotlin.bignum.integer.Quadruple;
import com.ionspin.kotlin.bignum.integer.Sextuple;
import com.ionspin.kotlin.bignum.integer.base32.BigInteger32Arithmetic;
import com.ionspin.kotlin.bignum.integer.util.ConversionUtilsKt;
import com.ionspin.kotlin.bignum.integer.util.DigitUtilKt;
import com.ionspin.kotlin.bignum.modular.ModularBigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.UCollectionsKt;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.UStringsKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\f\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\br\bÀ\u0002\u0018\u00002\u00020\u0001:\u0002ì\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b6\u00107J%\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b;\u00107J3\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b@\u0010AJ/\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C2\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bF\u0010GJ#\u0010H\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bI\u0010JJ;\u0010K\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\u00042\u0006\u0010M\u001a\u00020\u001d2\u0006\u0010N\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bO\u0010PJ+\u0010Q\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00162\u0006\u0010R\u001a\u00020\u001dø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bS\u0010TJ#\u0010U\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bV\u00107J5\u0010W\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u001d2\u0006\u0010Y\u001a\u00020\u001dH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bZ\u0010[J)\u0010\\\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C2\u0006\u00109\u001a\u00020\u0004H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b]\u0010^J/\u0010_\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C2\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b`\u0010GJ&\u0010a\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bb\u00107J%\u0010c\u001a\u00020!2\u0006\u00109\u001a\u00020\u00042\u0006\u0010d\u001a\u00020eH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bf\u0010gJ\u001b\u0010h\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bj\u0010kJ\u001d\u0010h\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bl\u0010mJ\u001b\u0010n\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bo\u0010mJ\u001b\u0010p\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bq\u0010kJ#\u0010r\u001a\u00020=2\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bs\u0010tJ%\u0010u\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bv\u0010wJ3\u0010x\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u0010y\u001a\u00020\u001d2\u0006\u0010z\u001a\u00020\u001dø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b{\u0010|J\u001e\u0010}\u001a\u00020\u00042\u0006\u00109\u001a\u00020~H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0010\u0001J \u0010\u0001\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0004H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J \u0010\u0001\u001a\u00020~2\u0006\u00109\u001a\u00020\u0004H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J \u0010\u0001\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0004H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J\u001e\u0010\u0001\u001a\u00020\u001d2\u0007\u0010\u0001\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010mJ*\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C2\u0007\u0010\u0001\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010^J*\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C2\u0007\u0010\u0001\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010^J'\u0010\u0001\u001a\u00020=2\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010tJ(\u0010\u0001\u001a\u00020\u00042\u0007\u0010\u0001\u001a\u00020\u00042\u0007\u0010\u0001\u001a\u00020\u001dø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J3\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C2\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u0010GJ'\u0010\u0001\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0001\u00107J\u001e\u0010\u0001\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J\u001e\u0010\u0001\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0001\u0010\u0001J0\u0010 \u0001\u001a\u00020\u00042\u0007\u0010¡\u0001\u001a\u00020\u00042\u0007\u0010¢\u0001\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b£\u0001\u0010¤\u0001J%\u0010¥\u0001\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b¦\u0001\u00107J%\u0010§\u0001\u001a\u00020\u00042\b\u0010¨\u0001\u001a\u00030©\u0001H\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0006\bª\u0001\u0010«\u0001J%\u0010¬\u0001\u001a\u00020\u00042\b\u0010­\u0001\u001a\u00030®\u0001H\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0006\b¯\u0001\u0010°\u0001J$\u0010±\u0001\u001a\u00020\u00042\u0007\u0010²\u0001\u001a\u00020\u001dH\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0006\b³\u0001\u0010´\u0001J$\u0010µ\u0001\u001a\u00020\u00042\u0007\u0010¶\u0001\u001a\u00020eH\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0006\b·\u0001\u0010¸\u0001J%\u0010¹\u0001\u001a\u00020\u00042\b\u0010º\u0001\u001a\u00030»\u0001H\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0006\b¼\u0001\u0010½\u0001J\"\u0010¾\u0001\u001a\u00020\u00042\b\u0010¿\u0001\u001a\u00030À\u0001H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÁ\u0001\u0010«\u0001J\"\u0010Â\u0001\u001a\u00020\u00042\b\u0010­\u0001\u001a\u00030Ã\u0001H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÄ\u0001\u0010°\u0001J\"\u0010Å\u0001\u001a\u00020\u00042\b\u0010Æ\u0001\u001a\u00030Ç\u0001H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÈ\u0001\u0010´\u0001J!\u0010É\u0001\u001a\u00020\u00042\u0007\u0010Ê\u0001\u001a\u00020\u0016H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bË\u0001\u0010¸\u0001J\"\u0010Ì\u0001\u001a\u00020\u00042\b\u0010Í\u0001\u001a\u00030Î\u0001H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÏ\u0001\u0010½\u0001J'\u0010Ð\u0001\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÑ\u0001\u00107J'\u0010Ò\u0001\u001a\u00020\u00042\u0007\u0010Ó\u0001\u001a\u00020\u00042\u0007\u0010Ô\u0001\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÕ\u0001\u00107J:\u0010Ö\u0001\u001a\u00020\u00042\u0007\u0010Ó\u0001\u001a\u00020\u00042\u0007\u0010Ô\u0001\u001a\u00020\u00042\u0006\u0010R\u001a\u00020\u001d2\u0007\u0010×\u0001\u001a\u00020\u001dH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bØ\u0001\u0010[J%\u0010Ù\u0001\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÚ\u0001\u00107J%\u0010Û\u0001\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÜ\u0001\u00107J&\u0010Ý\u0001\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00162\u0006\u00105\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÞ\u0001\u0010ß\u0001J'\u0010Ý\u0001\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bà\u0001\u00107J8\u0010á\u0001\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u0010R\u001a\u00020\u001d2\u0007\u0010×\u0001\u001a\u00020\u001dH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bâ\u0001\u0010[J)\u0010ã\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0C2\u0006\u00109\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bä\u0001\u0010^J;\u0010ã\u0001\u001a\u0015\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0å\u00012\u0007\u0010æ\u0001\u001a\u00020\u00042\u0007\u0010ç\u0001\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bè\u0001\u0010é\u0001J \u0010ê\u0001\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bë\u0001\u0010\u0001J \u0010ì\u0001\u001a\u00020e2\u0006\u00109\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bí\u0001\u0010î\u0001J\u001d\u0010ï\u0001\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bð\u0001\u0010kJ\u001f\u0010ñ\u0001\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u0016H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bò\u0001\u0010kJ\u001d\u0010ó\u0001\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bô\u0001\u0010kJ%\u0010õ\u0001\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bö\u0001\u00107J'\u0010÷\u0001\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bø\u0001\u00107J.\u0010ù\u0001\u001a\u00020\u00042\b\u0010ú\u0001\u001a\u00030û\u00012\u0007\u0010ü\u0001\u001a\u00020\u001dH\u0016ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0006\bý\u0001\u0010þ\u0001JO\u0010ÿ\u0001\u001a\u00020!2\u0007\u0010\u0002\u001a\u00020\u001d2\u0007\u0010\u0002\u001a\u00020\u001d2\u0007\u0010\u0002\u001a\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u001d2\u0007\u0010\u0002\u001a\u00020\u001dH\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0002\u0010\u0002J)\u0010\u0002\u001a\u00020\u00042\u0007\u0010ü\u0001\u001a\u00020\u00042\u0007\u0010\u0002\u001a\u00020eH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u0010JJ0\u0010\u0002\u001a\u00020\u00042\u0007\u0010¡\u0001\u001a\u00020\u00042\u0007\u0010¢\u0001\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0002\u0010¤\u0001J+\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C2\u0006\u00109\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u0010^J3\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C2\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u0010GJ\u001f\u0010\u0002\u001a\u00020\u00042\u0007\u0010\u0001\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0002\u0010\u0001J+\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C2\u0006\u00109\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b\u0002\u0010^J1\u0010\u0002\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00042\u0006\u0010d\u001a\u00020e2\u0007\u0010\u0002\u001a\u00020!H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0002\u0010\u0002J)\u0010\u0002\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u001dH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0002\u0010\u0001J)\u0010\u0002\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u001dH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0002\u0010\u0001J\u0019\u0010\u0002\u001a\u00020\t2\u0006\u00104\u001a\u00020\t2\u0006\u00105\u001a\u00020\tH\u0002J\u0019\u0010\u0002\u001a\u00020\t2\u0006\u00104\u001a\u00020\t2\u0006\u00105\u001a\u00020\tH\u0002J\u0019\u0010 \u0002\u001a\u00020\t2\u0006\u00104\u001a\u00020\t2\u0006\u00105\u001a\u00020\tH\u0002J\u0019\u0010¡\u0002\u001a\u00020\t2\u0006\u00104\u001a\u00020\t2\u0006\u00105\u001a\u00020\tH\u0002J\u0019\u0010¢\u0002\u001a\u00020\t2\u0006\u00104\u001a\u00020\t2\u0006\u00105\u001a\u00020\tH\u0002J+\u0010£\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C2\u0006\u00109\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b¤\u0002\u0010^J \u0010¥\u0002\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0004H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b¦\u0002\u0010\u0001J'\u0010§\u0002\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b¨\u0002\u00107J5\u0010©\u0002\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u0010y\u001a\u00020\u001d2\u0006\u0010z\u001a\u00020\u001dø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bª\u0002\u0010[J!\u0010«\u0002\u001a\u00030®\u00012\u0006\u00109\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b¬\u0002\u0010­\u0002J*\u0010®\u0002\u001a\u00030û\u00012\u0006\u00109\u001a\u00020\u00042\u0007\u0010ü\u0001\u001a\u00020\u001dH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b¯\u0002\u0010°\u0002J!\u0010±\u0002\u001a\u00030Ã\u00012\u0006\u00109\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b²\u0002\u0010­\u0002J'\u0010³\u0002\u001a\u00020\u00042\u0007\u0010´\u0002\u001a\u00020\u00042\u0007\u0010µ\u0002\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b¶\u0002\u00107J'\u0010·\u0002\u001a\u00020\u00042\u0007\u0010´\u0002\u001a\u00020\u00042\u0007\u0010µ\u0002\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b¸\u0002\u00107J\u001d\u0010¹\u0002\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bº\u0002\u0010kJ\u001f\u0010¹\u0002\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b»\u0002\u0010mJ6\u0010¼\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C2\u0007\u0010\u0001\u001a\u00020\u00042\u0007\u0010½\u0002\u001a\u00020\u001dH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b¾\u0002\u0010¿\u0002J'\u0010À\u0002\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÁ\u0002\u00107J$\u00108\u001a\u00020\t*\u00020\t2\u0006\u00109\u001a\u00020\u0004H\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÂ\u0002\u0010Ã\u0002J&\u0010Ä\u0002\u001a\u00020\u001d*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÆ\u0002\u0010Ç\u0002J%\u0010Ä\u0002\u001a\u00020\u001d*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÈ\u0002\u0010wJ\u001d\u0010É\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010Å\u0002\u001a\u00020\tH\u0002¢\u0006\u0003\bÊ\u0002J%\u0010É\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bË\u0002\u0010JJ%\u0010É\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÌ\u0002\u00107J1\u0010Í\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0004H\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÎ\u0002\u0010GJ\u001c\u0010Ï\u0002\u001a\u00020\u0004*\u00020~H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÐ\u0002\u0010\u0001J\u001c\u0010Ñ\u0002\u001a\u00020!*\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÒ\u0002\u0010Ó\u0002J\u001d\u0010Ô\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010Å\u0002\u001a\u00020\tH\u0002¢\u0006\u0003\bÕ\u0002J%\u0010Ô\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÖ\u0002\u0010JJ%\u0010Ô\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b×\u0002\u00107J\u001d\u0010Ø\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010Å\u0002\u001a\u00020\tH\u0002¢\u0006\u0003\bÙ\u0002J%\u0010Ø\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÚ\u0002\u0010JJ%\u0010Ø\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÛ\u0002\u00107J\u001d\u0010Ü\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010Å\u0002\u001a\u00020\tH\u0002¢\u0006\u0003\bÝ\u0002J%\u0010Ü\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bÞ\u0002\u0010JJ%\u0010Ü\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bß\u0002\u00107J\u001d\u0010à\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010\u0002\u001a\u00020\u001dH\u0004¢\u0006\u0003\bá\u0002J&\u0010à\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u001dH\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bâ\u0002\u0010\u0001J\u001d\u0010ã\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010\u0002\u001a\u00020\u001dH\u0004¢\u0006\u0003\bä\u0002J&\u0010ã\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010\u0002\u001a\u00020\u001dH\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bå\u0002\u0010\u0001J\u001d\u0010æ\u0002\u001a\u00020\t*\u00020\t2\u0007\u0010Å\u0002\u001a\u00020\tH\u0002¢\u0006\u0003\bç\u0002J%\u0010æ\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bè\u0002\u0010JJ%\u0010æ\u0002\u001a\u00020\u0004*\u00020\u00042\u0007\u0010Å\u0002\u001a\u00020\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\bé\u0002\u00107J\u001c\u0010ê\u0002\u001a\u00020~*\u00020\u0004H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bë\u0002\u0010\u0001R\u001f\u0010\u0003\u001a\u00020\u0004X\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001f\u0010\f\u001a\u00020\u0004X\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u001f\u0010\u000e\u001a\u00020\u0004X\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006R\u001f\u0010\u0010\u001a\u00020\u0004X\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u001f\u0010\u0015\u001a\u00020\u0016XDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u001a\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u001b\u0010\u0006R\u0014\u0010\u001c\u001a\u00020\u001dXD¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!XT¢\u0006\u0002\n\u0000R\u001f\u0010\"\u001a\u00020\u0016XDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b#\u0010\u0018R\u000e\u0010$\u001a\u00020\u001dXT¢\u0006\u0002\n\u0000R\u001f\u0010%\u001a\u00020\u0016XDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b&\u0010\u0018R\u001f\u0010'\u001a\u00020\u0016XDø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b(\u0010\u0018R\u001c\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00040*ø\u0001\u0000¢\u0006\n\n\u0002\u0010-\u001a\u0004\b+\u0010,R\u001c\u0010.\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b/\u0010\u0006R\u000e\u00100\u001a\u00020\u001dXT¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\u00020\u001dXD¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001f\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006í\u0002"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/base63/array/BigInteger63Arithmetic;", "Lcom/ionspin/kotlin/bignum/integer/BigIntegerArithmetic;", "()V", "ONE", "Lkotlin/ULongArray;", "getONE-Y2RjT0g", "()[J", "[J", "SIGNED_POSITIVE_TWO", "Lcom/ionspin/kotlin/bignum/integer/base63/array/BigInteger63Arithmetic$SignedULongArray;", "getSIGNED_POSITIVE_TWO", "()Lcom/ionspin/kotlin/bignum/integer/base63/array/BigInteger63Arithmetic$SignedULongArray;", "TEN", "getTEN-Y2RjT0g", "TWO", "getTWO-Y2RjT0g", "ZERO", "getZERO-Y2RjT0g", "_emitLongArray", "", "get_emitLongArray", "baseMask", "Lkotlin/ULong;", "getBaseMask-s-VKNKU", "()J", "J", "baseMaskArray", "getBaseMaskArray-Y2RjT0g", "basePowerOfTwo", "", "getBasePowerOfTwo", "()I", "debugOperandSize", "", "highMask", "getHighMask-s-VKNKU", "karatsubaThreshold", "lowMask", "getLowMask-s-VKNKU", "overflowMask", "getOverflowMask-s-VKNKU", "powersOf10", "", "getPowersOf10", "()[Lkotlin/ULongArray;", "[Lkotlin/ULongArray;", "reciprocalOf3In2ToThePowerOf63", "getReciprocalOf3In2ToThePowerOf63-Y2RjT0g", "toomCookThreshold", "wordSizeInBits", "getWordSizeInBits", "add", "first", "second", "add-j68ebKY", "([J[J)[J", "and", "operand", "mask", "and-j68ebKY", "baseAddIntoArray", "", "resultArray", "resultArrayStart", "baseAddIntoArray-KsfQWN0", "([JI[J[J)V", "baseDivide", "Lkotlin/Pair;", "unnormalizedDividend", "unnormalizedDivisor", "baseDivide-GR1PJdc", "([J[J)Lkotlin/Pair;", "baseMultiply", "baseMultiply-ss9iZGw", "([JJ)[J", "baseMultiplyIntoArray", "result", "resultStart", "resultEnd", "baseMultiplyIntoArray-cKnQUHg", "([JII[JJ)[J", "baseMultiplyWithCorrectedSize", "firstCorrectedSize", "baseMultiplyWithCorrectedSize-_EW1lsA", "([JJI)[J", "basecaseMultiply", "basecaseMultiply-j68ebKY", "basecaseMultiplyWithCorrectedSize", "firstCorrectedSizeStart", "secondCorrectedSizeStart", "basecaseMultiplyWithCorrectedSize-mwLU0fg", "([J[JII)[J", "basecaseSqrt", "basecaseSqrt-QwZRm1k$bignum", "([J)Lkotlin/Pair;", "basicDivide2", "basicDivide2-GR1PJdc", "binaryGcd", "binaryGcd-j68ebKY", "bitAt", "position", "", "bitAt-tBf0fek", "([JJ)Z", "bitLength", "value", "bitLength-VKZWuLQ", "(J)I", "bitLength-QwZRm1k", "([J)I", "bitLengthFor64BitArray", "bitLengthFor64BitArray-QwZRm1k", "bitLengthFor64BitWord", "bitLengthFor64BitWord-VKZWuLQ", "combaMultiply", "combaMultiply-GR1PJdc", "([J[J)V", "compare", "compare-GR1PJdc", "([J[J)I", "compareWithStartIndexes", "firstStart", "secondStart", "compareWithStartIndexes-MccmUSY", "([J[JII)I", "convertFrom32BitRepresentation", "Lkotlin/UIntArray;", "convertFrom32BitRepresentation-ehPNNiw$bignum", "([I)[J", "convertFrom64BitRepresentation", "convertFrom64BitRepresentation-JIhQxVY$bignum", "([J)[J", "convertTo32BitRepresentation", "convertTo32BitRepresentation-kqpWZOw$bignum", "([J)[I", "convertTo64BitRepresentation", "convertTo64BitRepresentation-JIhQxVY$bignum", "countLeadingZeroWords", "bigInteger", "countLeadingZeroWords-QwZRm1k", "d1ReciprocalRecursive", "a", "d1ReciprocalRecursive-QwZRm1k", "d1ReciprocalRecursiveWordVersion", "d1ReciprocalRecursiveWordVersion-QwZRm1k", "debugOperandsCheck", "debugOperandsCheck-GR1PJdc", "denormalize", "remainderNormalized", "normalizationShift", "denormalize-GERUpyg", "([JI)[J", "divide", "divide-GR1PJdc", "euclideanGcd", "euclideanGcd-j68ebKY", "exactDivideBy3", "exactDivideBy3-JIhQxVY", "exactDivideBy3Better", "exactDivideBy3Better-JIhQxVY", "extendULongArray", "original", "numberOfWords", "extendULongArray-9R_UfW4", "([JIJ)[J", "fftMultiply", "fftMultiply-j68ebKY", "fromByte", "byte", "", "fromByte-DHQ6RzY", "(B)[J", "fromByteArray", "source", "", "fromByteArray-DHQ6RzY", "([B)[J", "fromInt", "int", "fromInt-DHQ6RzY", "(I)[J", "fromLong", "long", "fromLong-DHQ6RzY", "(J)[J", "fromShort", "short", "", "fromShort-DHQ6RzY", "(S)[J", "fromUByte", "uByte", "Lkotlin/UByte;", "fromUByte-ab45Ak8", "fromUByteArray", "Lkotlin/UByteArray;", "fromUByteArray-S4Jqe-A", "fromUInt", "uInt", "Lkotlin/UInt;", "fromUInt-kOc6_GI", "fromULong", "uLong", "fromULong--GCcj4Q", "fromUShort", "uShort", "Lkotlin/UShort;", "fromUShort-jOPi9CM", "gcd", "gcd-j68ebKY", "karatsubaMultiply", "firstUnsigned", "secondUnsigned", "karatsubaMultiply-j68ebKY", "karatsubaMultiplyWithCorrectedSizes", "secondCorrectedSize", "karatsubaMultiplyWithCorrectedSizes-mwLU0fg", "max", "max-j68ebKY", "min", "min-j68ebKY", "multiply", "multiply-dakbYXk", "(JJ)[J", "multiply-j68ebKY", "multiplyWithCorrectedSize", "multiplyWithCorrectedSize-mwLU0fg", "normalize", "normalize-QwZRm1k", "Lkotlin/Triple;", "dividend", "divisor", "normalize-GR1PJdc", "([J[J)Lkotlin/Triple;", "not", "not-JIhQxVY", "numberOfDecimalDigits", "numberOfDecimalDigits-QwZRm1k", "([J)J", "numberOfLeadingZeroesInA64BitWord", "numberOfLeadingZeroesInA64BitWord-VKZWuLQ", "numberOfLeadingZerosInAWord", "numberOfLeadingZerosInAWord-VKZWuLQ", "numberOfTrailingZerosInAWord", "numberOfTrailingZerosInAWord-VKZWuLQ", "oldAdd", "oldAdd-j68ebKY", "or", "or-j68ebKY", "parseForBase", "number", "", "base", "parseForBase-_llDaS8", "(Ljava/lang/String;I)[J", "possibleAdditionOverflow", "largerLength", "smallerLength", "largerData", "smallerData", "largerStart", "smallerStart", "possibleAdditionOverflow-qJ-xzII", "(II[J[JII)Z", "pow", "exponent", "pow-GERUpyg", "prependULongArray", "prependULongArray-9R_UfW4", "reciprocal", "reciprocal-QwZRm1k", "reciprocalDivision", "reciprocalDivision-GR1PJdc$bignum", "removeLeadingZeros", "removeLeadingZeros-JIhQxVY", "reqursiveSqrt", "reqursiveSqrt-QwZRm1k", "setBitAt", "bit", "setBitAt-v3PXmpk", "([JJZ)[J", "shiftLeft", "places", "shiftLeft-GERUpyg", "shiftRight", "shiftRight-GERUpyg", "signedAdd", "signedDivide", "signedMultiply", "signedRemainder", "signedSubtract", "sqrt", "sqrt-QwZRm1k", "sqrtInt", "sqrtInt-JIhQxVY$bignum", "subtract", "subtract-j68ebKY", "subtractWithStartIndexes", "subtractWithStartIndexes-mwLU0fg", "toByteArray", "toByteArray-QwZRm1k", "([J)[B", "toString", "toString-tBf0fek", "([JI)Ljava/lang/String;", "toUByteArray", "toUByteArray-cMszsnM", "toomCook3Multiply", "firstUnchecked", "secondUnchecked", "toomCook3Multiply-j68ebKY", "toomCook3WithCorrectedSizes", "toomCook3WithCorrectedSizes-j68ebKY", "trailingZeroBits", "trailingZeroBits-VKZWuLQ", "trailingZeroBits-QwZRm1k", "unbalancedReciprocal", "diff", "unbalancedReciprocal-tBf0fek", "([JI)Lkotlin/Pair;", "xor", "xor-j68ebKY", "and-SIFponk$bignum", "(Lcom/ionspin/kotlin/bignum/integer/base63/array/BigInteger63Arithmetic$SignedULongArray;[J)Lcom/ionspin/kotlin/bignum/integer/base63/array/BigInteger63Arithmetic$SignedULongArray;", "compareTo", "other", "compareTo-3yFGk1Y$bignum", "([JJ)I", "compareTo-GR1PJdc$bignum", "div", "div$bignum", "div-ss9iZGw$bignum", "div-j68ebKY$bignum", "divrem", "divrem-GR1PJdc$bignum", "from32Bit", "from32Bit-ehPNNiw$bignum", "isZero", "isZero-QwZRm1k", "([J)Z", "minus", "minus$bignum", "minus-ss9iZGw$bignum", "minus-j68ebKY$bignum", "plus", "plus$bignum", "plus-ss9iZGw$bignum", "plus-j68ebKY$bignum", "rem", "rem$bignum", "rem-ss9iZGw$bignum", "rem-j68ebKY$bignum", "shl", "shl$bignum", "shl-GERUpyg$bignum", "shr", "shr$bignum", "shr-GERUpyg$bignum", "times", "times$bignum", "times-ss9iZGw$bignum", "times-j68ebKY$bignum", "to32Bit", "to32Bit-kqpWZOw$bignum", "SignedULongArray", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BigInteger63Arithmetic implements BigIntegerArithmetic {
    @NotNull
    public static final BigInteger63Arithmetic INSTANCE;
    @NotNull
    private static final long[] ONE = {1};
    @NotNull
    private static final SignedULongArray SIGNED_POSITIVE_TWO;
    @NotNull
    private static final long[] TEN = {10};
    @NotNull
    private static final long[] TWO = {2};
    @NotNull
    private static final long[] ZERO = {0};
    @NotNull
    private static final long[] _emitLongArray = new long[0];
    private static final long baseMask = Long.MAX_VALUE;
    @NotNull
    private static final long[] baseMaskArray = {Long.MAX_VALUE};
    private static final int basePowerOfTwo = 63;
    public static final boolean debugOperandSize = true;
    private static final long highMask = 9223372032559808512L;
    public static final int karatsubaThreshold = 120;
    private static final long lowMask = 4294967295L;
    private static final long overflowMask = Long.MIN_VALUE;
    @NotNull
    private static final ULongArray[] powersOf10 = {ULongArray.m9206boximpl(new long[]{1}), ULongArray.m9206boximpl(new long[]{10}), ULongArray.m9206boximpl(new long[]{100}), ULongArray.m9206boximpl(new long[]{1000}), ULongArray.m9206boximpl(new long[]{10000}), ULongArray.m9206boximpl(new long[]{100000}), ULongArray.m9206boximpl(new long[]{1000000}), ULongArray.m9206boximpl(new long[]{10000000}), ULongArray.m9206boximpl(new long[]{100000000}), ULongArray.m9206boximpl(new long[]{1000000000}), ULongArray.m9206boximpl(new long[]{10000000000L}), ULongArray.m9206boximpl(new long[]{100000000000L}), ULongArray.m9206boximpl(new long[]{1000000000000L}), ULongArray.m9206boximpl(new long[]{10000000000000L}), ULongArray.m9206boximpl(new long[]{100000000000000L}), ULongArray.m9206boximpl(new long[]{1000000000000000L}), ULongArray.m9206boximpl(new long[]{10000000000000000L}), ULongArray.m9206boximpl(new long[]{100000000000000000L}), ULongArray.m9206boximpl(new long[]{1000000000000000000L}), ULongArray.m9206boximpl(new long[]{776627963145224192L, 1}), ULongArray.m9206boximpl(new long[]{7766279631452241920L, 10}), ULongArray.m9206boximpl(new long[]{3875820019684212736L, 108}), ULongArray.m9206boximpl(new long[]{1864712049423024128L, 1084}), ULongArray.m9206boximpl(new long[]{200376420520689664L, 10842}), ULongArray.m9206boximpl(new long[]{2003764205206896640L, 108420}), ULongArray.m9206boximpl(new long[]{1590897978359414784L, 1084202}), ULongArray.m9206boximpl(new long[]{6685607746739372032L, 10842021}), ULongArray.m9206boximpl(new long[]{2292473209410289664L, 108420217}), ULongArray.m9206boximpl(new long[]{4477988020393345024L, 1084202172}), ULongArray.m9206boximpl(new long[]{7886392056514347008L, 10842021724L}), ULongArray.m9206boximpl(new long[]{5076944270305263616L, 108420217248L}), ULongArray.m9206boximpl(new long[]{4652582518778757120L, 1084202172485L}), ULongArray.m9206boximpl(new long[]{408965003513692160L, 10842021724855L}), ULongArray.m9206boximpl(new long[]{4089650035136921600L, 108420217248550L}), ULongArray.m9206boximpl(new long[]{4003012203950112768L, 1084202172485504L}), ULongArray.m9206boximpl(new long[]{3136633892082024448L, 10842021724855044L}), ULongArray.m9206boximpl(new long[]{3696222810255917056L, 108420217248550443L}), ULongArray.m9206boximpl(new long[]{68739955140067328L, 1084202172485504434L}), ULongArray.m9206boximpl(new long[]{687399551400673280L, 1618649688000268532L, 1}), ULongArray.m9206boximpl(new long[]{6873995514006732800L, 6963124843147909512L, 11}), ULongArray.m9206boximpl(new long[]{4176350882083897344L, 5067644173495664471L, 117}), ULongArray.m9206boximpl(new long[]{4870020673419870208L, 4559581550682765674L, 1175}), ULongArray.m9206boximpl(new long[]{2583346549924823040L, 8702327359408553513L, 11754}), ULongArray.m9206boximpl(new long[]{7386721425538678784L, 4012925262392552860L, 117549}), ULongArray.m9206boximpl(new long[]{80237960548581376L, 3235764476506425376L, 1175494}), ULongArray.m9206boximpl(new long[]{802379605485813760L, 4687528654499926336L, 11754943}), ULongArray.m9206boximpl(new long[]{8023796054858137600L, 758426360725384320L, 117549435}), ULongArray.m9206boximpl(new long[]{6450984253743169536L, 7584263607253843208L, 1175494350}), ULongArray.m9206boximpl(new long[]{9169610316303040512L, 2055659777700225622L, 11754943508L}), ULongArray.m9206boximpl(new long[]{8685754831337422848L, 2109853703292704613L, 117549435082L}), ULongArray.m9206boximpl(new long[]{3847199981681246208L, 2651792959217494523L, 1175494350822L}), ULongArray.m9206boximpl(new long[]{1578511669393358848L, 8071185518465393618L, 11754943508222L}), ULongArray.m9206boximpl(new long[]{6561744657078812672L, 6924878889815729717L, 117549435082228L}), ULongArray.m9206boximpl(new long[]{1053842312804696064L, 4685184640173866521L, 1175494350822287L}), ULongArray.m9206boximpl(new long[]{1315051091192184832L, 734986217464786171L, 11754943508222875L}), ULongArray.m9206boximpl(new long[]{3927138875067072512L, 7349862174647861711L, 117549435082228750L}), ULongArray.m9206boximpl(new long[]{2377900603251621888L, 8935017488495186458L, 1175494350822287507L}), ULongArray.m9206boximpl(new long[]{5332261958806667264L, 6339826553258882310L, 2531571471368099271L, 1}), ULongArray.m9206boximpl(new long[]{7205759403792793600L, 8058033311460168257L, 6868970639971441100L, 12}), ULongArray.m9206boximpl(new long[]{7493989779944505344L, 6793356819763476113L, 4126102141730980352L, 127}), ULongArray.m9206boximpl(new long[]{LockFreeTaskQueueCore.FROZEN_MASK, 3369963939651330482L, 4367533269890700295L, 1274}), ULongArray.m9206boximpl(new long[]{LockFreeTaskQueueCore.CLOSED_MASK, 6029523285948977397L, 6781844551487899721L, 12744}), ULongArray.m9206boximpl(new long[]{4611686018427387904L, 4955000638361119124L, 3254841256895566560L, 127447}), ULongArray.m9206boximpl(new long[]{0, 3433146199337312205L, 4878296458391338181L, 1274473}), ULongArray.m9206boximpl(new long[]{0, 6661345882808794626L, 2666104399639502773L, 12744735}), ULongArray.m9206boximpl(new long[]{0, 2049854570104515604L, 8214299922685476121L, 127447352}), ULongArray.m9206boximpl(new long[]{0, 2051801627335604424L, 8356022932016554748L, 1274473528}), ULongArray.m9206boximpl(new long[]{0, 2071272199646492624L, 549880988472565210L, 12744735289L}), ULongArray.m9206boximpl(new long[]{0, 2265977922755374624L, 5498809884725652102L, 127447352890L}), ULongArray.m9206boximpl(new long[]{0, 4213035153844194624L, 8871238662982641982L, 1274473528905L}), ULongArray.m9206boximpl(new long[]{0, 5236863391022843008L, 5702038298133437552L, 12744735289059L}), ULongArray.m9206boximpl(new long[]{0, 6251773725954551040L, 1680150760205720677L, 127447352890596L}), ULongArray.m9206boximpl(new long[]{0, 7177505038416855552L, 7578135565202430968L, 1274473528905961L}), ULongArray.m9206boximpl(new long[]{0, 7211446126185124864L, 1994379357186103223L, 12744735289059618L}), ULongArray.m9206boximpl(new long[]{0, 7550857003867817984L, 1497049498151480621L, 127447352890596182L}), ULongArray.m9206boximpl(new long[]{0, 1721593743839973376L, 5747122944660030410L, 1274473528905961821L}), ULongArray.m9206boximpl(new long[]{0, 7992565401544957952L, 2130997225471649253L, 3521363252204842408L, 1}), ULongArray.m9206boximpl(new long[]{0, 6138677720611373056L, 2863228181006940922L, 7543516411484096658L, 13}), ULongArray.m9206boximpl(new long[]{0, 6046544984985075712L, 962165699505081802L, 1648187820002760119L, 138}), ULongArray.m9206boximpl(new long[]{0, 5125217628722102272L, 398284958196042218L, 7258506163172825383L, 1381}), ULongArray.m9206boximpl(new long[]{0, 5135316102947143680L, 3982849581960422185L, 8021457373744823174L, 13817}), ULongArray.m9206boximpl(new long[]{0, 5236300845197557760L, 2935007672185118623L, 6427597442610025280L, 138178}), ULongArray.m9206boximpl(new long[]{0, 6246148267701698560L, 1679960611286858811L, 8935742204971597955L, 1381786}), ULongArray.m9206boximpl(new long[]{0, 7121250455888330752L, 7576234076013812308L, 6347073718022997279L, 13817869}), ULongArray.m9206boximpl(new long[]{0, 6648900300899876864L, 1975364465299916623L, 8130504959101317950L, 138178696}), ULongArray.m9206boximpl(new long[]{0, 1925398751015337984L, 1306900579289614621L, 7518073296174973038L, 1381786968}), ULongArray.m9206boximpl(new long[]{0, 807243436443828224L, 3845633756041370404L, 1393756666911523917L, 13817869688L}), ULongArray.m9206boximpl(new long[]{0, 8072434364438282240L, 1562849412994600808L, 4714194632260463366L, 138178696881L}), ULongArray.m9206boximpl(new long[]{0, 6937367349544615936L, 6405122093091232280L, 1025086138330754621L, 1381786968815L}), ULongArray.m9206boximpl(new long[]{0, 4810069237462728704L, 8710988709783667959L, 1027489346452770408L, 13817869688151L}), ULongArray.m9206boximpl(new long[]{0, 1983832190353408000L, 4099538766143697323L, 1051521427672928281L, 138178696881511L}), ULongArray.m9206boximpl(new long[]{0, 1391577829824528384L, 4101899514017870000L, 1291842239874507006L, 1381786968815111L}), ULongArray.m9206boximpl(new long[]{0, 4692406261390508032L, 4125506992759596769L, 3695050361890294256L, 13817869688151111L}), ULongArray.m9206boximpl(new long[]{0, 807202429631201280L, 4361581780176864463L, 57015471483839332L, 138178696881511114L}), ULongArray.m9206boximpl(new long[]{0, 8072024296312012800L, 6722329654349541398L, 570154714838393324L, 1381786968815111140L}), ULongArray.m9206boximpl(new long[]{0, 6933266668281921536L, 2659692285511983332L, 5701547148383933247L, 4594497651296335592L, 1}), ULongArray.m9206boximpl(new long[]{0, 4769062424835784704L, 8150178781410281711L, 1675239262710677624L, 9051488365544252694L, 14}), ULongArray.m9206boximpl(new long[]{0, 1573764064083968000L, 7714811519264610651L, 7529020590252000440L, 7504535323749544669L, 149}), ULongArray.m9206boximpl(new long[]{0, 6514268603984904192L, 3361138897807900047L, 1503229607681797944L, 1258376942657240234L, 1498}), ULongArray.m9206boximpl(new long[]{0, 579081781865611264L, 5941272867514673053L, 5808924039963203635L, 3360397389717626533L, 14981}), ULongArray.m9206boximpl(new long[]{0, 5790817818656112640L, 4072496454018075682L, 2749008178503381508L, 5933857786611937912L, 149813})};
    @NotNull
    private static final long[] reciprocalOf3In2ToThePowerOf63 = {3074457345618258603L};
    public static final int toomCookThreshold = 15000;
    private static final int wordSizeInBits = 63;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0018\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u0019\u0010\f\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\r\u0010\nJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J*\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/base63/array/BigInteger63Arithmetic$SignedULongArray;", "", "unsignedValue", "Lkotlin/ULongArray;", "sign", "", "([JZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSign", "()Z", "getUnsignedValue-Y2RjT0g", "()[J", "[J", "component1", "component1-Y2RjT0g", "component2", "copy", "copy-tBf0fek", "([JZ)Lcom/ionspin/kotlin/bignum/integer/base63/array/BigInteger63Arithmetic$SignedULongArray;", "equals", "other", "hashCode", "", "toString", "", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class SignedULongArray {
        private final boolean sign;
        @NotNull
        private final long[] unsignedValue;

        public /* synthetic */ SignedULongArray(long[] jArr, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
            this(jArr, z2);
        }

        /* renamed from: copy-tBf0fek$default  reason: not valid java name */
        public static /* synthetic */ SignedULongArray m8670copytBf0fek$default(SignedULongArray signedULongArray, long[] jArr, boolean z2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                jArr = signedULongArray.unsignedValue;
            }
            if ((i3 & 2) != 0) {
                z2 = signedULongArray.sign;
            }
            return signedULongArray.m8672copytBf0fek(jArr, z2);
        }

        @NotNull
        /* renamed from: component1-Y2RjT0g  reason: not valid java name */
        public final long[] m8671component1Y2RjT0g() {
            return this.unsignedValue;
        }

        public final boolean component2() {
            return this.sign;
        }

        @NotNull
        /* renamed from: copy-tBf0fek  reason: not valid java name */
        public final SignedULongArray m8672copytBf0fek(@NotNull long[] jArr, boolean z2) {
            Intrinsics.checkNotNullParameter(jArr, "unsignedValue");
            return new SignedULongArray(jArr, z2, (DefaultConstructorMarker) null);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SignedULongArray)) {
                return false;
            }
            SignedULongArray signedULongArray = (SignedULongArray) obj;
            return ULongArray.m9212equalsimpl0(this.unsignedValue, signedULongArray.unsignedValue) && this.sign == signedULongArray.sign;
        }

        public final boolean getSign() {
            return this.sign;
        }

        @NotNull
        /* renamed from: getUnsignedValue-Y2RjT0g  reason: not valid java name */
        public final long[] m8673getUnsignedValueY2RjT0g() {
            return this.unsignedValue;
        }

        public int hashCode() {
            int r02 = ULongArray.m9215hashCodeimpl(this.unsignedValue) * 31;
            boolean z2 = this.sign;
            if (z2) {
                z2 = true;
            }
            return r02 + (z2 ? 1 : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder("SignedULongArray(unsignedValue=");
            sb.append(ULongArray.m9219toStringimpl(this.unsignedValue));
            sb.append(", sign=");
            return i.c(sb, this.sign, ')');
        }

        private SignedULongArray(long[] jArr, boolean z2) {
            this.unsignedValue = jArr;
            this.sign = z2;
        }
    }

    static {
        BigInteger63Arithmetic bigInteger63Arithmetic = new BigInteger63Arithmetic();
        INSTANCE = bigInteger63Arithmetic;
        SIGNED_POSITIVE_TWO = new SignedULongArray(bigInteger63Arithmetic.m8622getTWOY2RjT0g(), true, (DefaultConstructorMarker) null);
    }

    private BigInteger63Arithmetic() {
    }

    /* renamed from: basecaseMultiplyWithCorrectedSize-mwLU0fg  reason: not valid java name */
    private final long[] m8555basecaseMultiplyWithCorrectedSizemwLU0fg(long[] jArr, long[] jArr2, int i3, int i4) {
        long[] r6 = m8623getZEROY2RjT0g();
        int length = jArr2.length;
        int i5 = 0;
        int i6 = 0;
        while (i5 < length) {
            long j2 = jArr2[i5];
            int i7 = i6 + 1;
            if (i6 <= i4) {
                BigInteger63Arithmetic bigInteger63Arithmetic = INSTANCE;
                r6 = bigInteger63Arithmetic.m8641plusj68ebKY$bignum(r6, bigInteger63Arithmetic.m8653shlGERUpyg$bignum(bigInteger63Arithmetic.m8570baseMultiplyss9iZGw(jArr, j2), bigInteger63Arithmetic.getBasePowerOfTwo() * i6));
            }
            i5++;
            i6 = i7;
        }
        return r6;
    }

    /* renamed from: binaryGcd-j68ebKY  reason: not valid java name */
    private final long[] m8556binaryGcdj68ebKY(long[] jArr, long[] jArr2) {
        while (!UArraysKt.m9546contentEqualslec5QzE(jArr, jArr2)) {
            if (m8559isZeroQwZRm1k(jArr)) {
                return jArr2;
            }
            if (m8559isZeroQwZRm1k(jArr2)) {
                return jArr;
            }
            if (m8559isZeroQwZRm1k(m8567andj68ebKY(jArr, m8618getONEY2RjT0g()))) {
                if (m8559isZeroQwZRm1k(m8567andj68ebKY(jArr2, m8618getONEY2RjT0g()))) {
                    return m8653shlGERUpyg$bignum(m8556binaryGcdj68ebKY(m8654shrGERUpyg$bignum(jArr, 1), m8654shrGERUpyg$bignum(jArr2, 1)), 1);
                }
                jArr = m8654shrGERUpyg$bignum(jArr, 1);
            } else if (m8559isZeroQwZRm1k(m8567andj68ebKY(jArr2, m8618getONEY2RjT0g()))) {
                jArr2 = m8654shrGERUpyg$bignum(jArr2, 1);
            } else if (m8582compareGR1PJdc(jArr, jArr2) == 1) {
                jArr = m8654shrGERUpyg$bignum(m8657subtractj68ebKY(jArr, jArr2), 1);
            } else {
                long[] r2 = m8654shrGERUpyg$bignum(m8657subtractj68ebKY(jArr2, jArr), 1);
                jArr2 = jArr;
                jArr = r2;
            }
        }
        return jArr;
    }

    /* renamed from: debugOperandsCheck-GR1PJdc  reason: not valid java name */
    private final void m8557debugOperandsCheckGR1PJdc(long[] jArr, long[] jArr2) {
        if (ULongArray.m9216isEmptyimpl(jArr) || ULongArray.m9216isEmptyimpl(jArr2)) {
            throw new RuntimeException("Empty operands");
        }
    }

    /* renamed from: euclideanGcd-j68ebKY  reason: not valid java name */
    private final long[] m8558euclideanGcdj68ebKY(long[] jArr, long[] jArr2) {
        while (true) {
            long[] jArr3 = jArr2;
            long[] jArr4 = jArr;
            jArr = jArr3;
            if (m8559isZeroQwZRm1k(jArr)) {
                return jArr4;
            }
            jArr2 = m8647remj68ebKY$bignum(jArr4, jArr);
        }
    }

    /* renamed from: isZero-QwZRm1k  reason: not valid java name */
    private final boolean m8559isZeroQwZRm1k(long[] jArr) {
        if (ULongArray.m9212equalsimpl0(jArr, m8623getZEROY2RjT0g())) {
            return true;
        }
        return (ULongArray.m9214getSizeimpl(jArr) == 1 && ULongArray.m9213getsVKNKU(jArr, 0) == 0) || ULongArray.m9214getSizeimpl(jArr) - m8590countLeadingZeroWordsQwZRm1k(jArr) == 0;
    }

    /* renamed from: karatsubaMultiplyWithCorrectedSizes-mwLU0fg  reason: not valid java name */
    private final long[] m8560karatsubaMultiplyWithCorrectedSizesmwLU0fg(long[] jArr, long[] jArr2, int i3, int i4) {
        SignedULongArray signedULongArray = new SignedULongArray(jArr, true, (DefaultConstructorMarker) null);
        SignedULongArray signedULongArray2 = new SignedULongArray(jArr2, true, (DefaultConstructorMarker) null);
        int max = (Math.max(i3, i4) + 1) / 2;
        long[] r7 = m8618getONEY2RjT0g();
        int i5 = wordSizeInBits;
        long[] r72 = m8628minusss9iZGw$bignum(m8653shlGERUpyg$bignum(r7, max * i5), 1);
        SignedULongArray r12 = m8566andSIFponk$bignum(signedULongArray, r72);
        SignedULongArray shr$bignum = shr$bignum(signedULongArray, max * i5);
        SignedULongArray r73 = m8566andSIFponk$bignum(signedULongArray2, r72);
        SignedULongArray shr$bignum2 = shr$bignum(signedULongArray2, max * i5);
        SignedULongArray times$bignum = times$bignum(shr$bignum, shr$bignum2);
        SignedULongArray times$bignum2 = times$bignum(r12, r73);
        return plus$bignum(plus$bignum(shl$bignum(times$bignum, i5 * 2 * max), shl$bignum(minus$bignum(minus$bignum(times$bignum(plus$bignum(shr$bignum, r12), plus$bignum(shr$bignum2, r73)), times$bignum), times$bignum2), i5 * max)), times$bignum2).m8673getUnsignedValueY2RjT0g();
    }

    /* renamed from: multiplyWithCorrectedSize-mwLU0fg  reason: not valid java name */
    private final long[] m8561multiplyWithCorrectedSizemwLU0fg(long[] jArr, long[] jArr2, int i3, int i4) {
        return (m8559isZeroQwZRm1k(jArr) || m8559isZeroQwZRm1k(jArr2)) ? m8623getZEROY2RjT0g() : ((i3 >= 120 || i4 >= 120) && (i3 <= 15000 || i4 < 15000)) ? m8560karatsubaMultiplyWithCorrectedSizesmwLU0fg(jArr, jArr2, i3, i4) : (i3 < 15000 || i4 < 15000) ? m8555basecaseMultiplyWithCorrectedSizemwLU0fg(jArr, jArr2, i3, i4) : m8665toomCook3Multiplyj68ebKY(jArr, jArr2);
    }

    /* renamed from: possibleAdditionOverflow-qJ-xzII  reason: not valid java name */
    private final boolean m8562possibleAdditionOverflowqJxzII(int i3, int i4, long[] jArr, long[] jArr2, int i5, int i6) {
        return (ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i5 - 1) & 6917529027641081856L) == 0 && ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr2, i6 - 1) & 6917529027641081856L) == 0) ? false : true;
    }

    /* renamed from: reqursiveSqrt-QwZRm1k  reason: not valid java name */
    private final Pair<ULongArray, ULongArray> m8563reqursiveSqrtQwZRm1k(long[] jArr) {
        int r02 = ULongArray.m9214getSizeimpl(jArr);
        int floor = (int) Math.floor(((double) (r02 - 1)) / ((double) 4));
        if (floor == 0) {
            return m8574basecaseSqrtQwZRm1k$bignum(jArr);
        }
        int i3 = r02 / 4;
        int i4 = r02 % 4;
        int i5 = floor * 63;
        int i6 = r02 - ((i3 * 3) + i4);
        int i7 = r02 - ((i3 * 2) + i4);
        long[] r3 = ULongArray.m9208constructorimpl(ArraysKt.copyOfRange(jArr, i6, i7));
        long[] r4 = ULongArray.m9208constructorimpl(ArraysKt.copyOfRange(jArr, 0, i6));
        Pair<ULongArray, ULongArray> r7 = m8563reqursiveSqrtQwZRm1k(ULongArray.m9208constructorimpl(ArraysKt.copyOfRange(jArr, i7, r02)));
        long[] r03 = r7.component1().m9222unboximpl();
        Pair<ULongArray, ULongArray> r72 = m8597divremGR1PJdc$bignum(m8641plusj68ebKY$bignum(m8653shlGERUpyg$bignum(r7.component2().m9222unboximpl(), i5), r3), m8653shlGERUpyg$bignum(r03, 1));
        long[] r2 = r72.component1().m9222unboximpl();
        long[] r73 = r72.component2().m9222unboximpl();
        return new Pair<>(ULongArray.m9206boximpl(m8641plusj68ebKY$bignum(m8653shlGERUpyg$bignum(r03, i5), r2)), ULongArray.m9206boximpl(m8627minusj68ebKY$bignum(m8641plusj68ebKY$bignum(m8653shlGERUpyg$bignum(r73, i5), r4), m8659timesj68ebKY$bignum(r2, r2))));
    }

    private final SignedULongArray signedAdd(SignedULongArray signedULongArray, SignedULongArray signedULongArray2) {
        return signedULongArray.getSign() ^ signedULongArray2.getSign() ? m8584compareToGR1PJdc$bignum(signedULongArray.m8673getUnsignedValueY2RjT0g(), signedULongArray2.m8673getUnsignedValueY2RjT0g()) > 0 ? new SignedULongArray(m8627minusj68ebKY$bignum(signedULongArray.m8673getUnsignedValueY2RjT0g(), signedULongArray2.m8673getUnsignedValueY2RjT0g()), signedULongArray.getSign(), (DefaultConstructorMarker) null) : new SignedULongArray(m8627minusj68ebKY$bignum(signedULongArray2.m8673getUnsignedValueY2RjT0g(), signedULongArray.m8673getUnsignedValueY2RjT0g()), signedULongArray2.getSign(), (DefaultConstructorMarker) null) : new SignedULongArray(m8641plusj68ebKY$bignum(signedULongArray.m8673getUnsignedValueY2RjT0g(), signedULongArray2.m8673getUnsignedValueY2RjT0g()), signedULongArray.getSign(), (DefaultConstructorMarker) null);
    }

    private final SignedULongArray signedDivide(SignedULongArray signedULongArray, SignedULongArray signedULongArray2) {
        return new SignedULongArray(m8594divj68ebKY$bignum(signedULongArray.m8673getUnsignedValueY2RjT0g(), signedULongArray2.m8673getUnsignedValueY2RjT0g()), !(signedULongArray.getSign() ^ signedULongArray2.getSign()), (DefaultConstructorMarker) null);
    }

    private final SignedULongArray signedMultiply(SignedULongArray signedULongArray, SignedULongArray signedULongArray2) {
        return new SignedULongArray(m8659timesj68ebKY$bignum(signedULongArray.m8673getUnsignedValueY2RjT0g(), signedULongArray2.m8673getUnsignedValueY2RjT0g()), !(signedULongArray.getSign() ^ signedULongArray2.getSign()), (DefaultConstructorMarker) null);
    }

    private final SignedULongArray signedRemainder(SignedULongArray signedULongArray, SignedULongArray signedULongArray2) {
        return new SignedULongArray(m8647remj68ebKY$bignum(signedULongArray.m8673getUnsignedValueY2RjT0g(), signedULongArray2.m8673getUnsignedValueY2RjT0g()), !(signedULongArray.getSign() ^ signedULongArray2.getSign()), (DefaultConstructorMarker) null);
    }

    private final SignedULongArray signedSubtract(SignedULongArray signedULongArray, SignedULongArray signedULongArray2) {
        return signedAdd(signedULongArray, SignedULongArray.m8670copytBf0fek$default(signedULongArray2, (long[]) null, !signedULongArray2.getSign(), 1, (Object) null));
    }

    /* renamed from: unbalancedReciprocal-tBf0fek  reason: not valid java name */
    private final Pair<ULongArray, ULongArray> m8564unbalancedReciprocaltBf0fek(long[] jArr, int i3) {
        long[] jArr2;
        int r02 = (ULongArray.m9214getSizeimpl(jArr) - 1) - i3;
        long[] r12 = ULongArray.m9208constructorimpl(ArraysKt.copyOfRange(jArr, r02 + 1, ULongArray.m9214getSizeimpl(jArr)));
        long[] r2 = ULongArray.m9208constructorimpl(ArraysKt.copyOfRange(jArr, 0, r02));
        Pair<ULongArray, ULongArray> r13 = m8592d1ReciprocalRecursiveWordVersionQwZRm1k(r12);
        long[] r3 = r13.component1().m9222unboximpl();
        long[] r14 = r13.component2().m9222unboximpl();
        int i4 = r02 * 63;
        if (!ULongArray.m9212equalsimpl0(r3, m8653shlGERUpyg$bignum(m8618getONEY2RjT0g(), i4))) {
            long[] r6 = m8653shlGERUpyg$bignum(r14, i3);
            long[] r7 = m8659timesj68ebKY$bignum(r2, r3);
            if (m8584compareToGR1PJdc$bignum(r6, r7) > 0) {
                jArr2 = m8627minusj68ebKY$bignum(r6, r7);
            } else {
                r3 = m8627minusj68ebKY$bignum(r3, m8618getONEY2RjT0g());
                jArr2 = m8627minusj68ebKY$bignum(r6, m8659timesj68ebKY$bignum(r2, r3));
            }
        } else if (m8584compareToGR1PJdc$bignum(r2, m8623getZEROY2RjT0g()) == 0) {
            jArr2 = m8623getZEROY2RjT0g();
        } else {
            r3 = m8627minusj68ebKY$bignum(r3, m8618getONEY2RjT0g());
            jArr2 = m8627minusj68ebKY$bignum(jArr, m8653shlGERUpyg$bignum(r2, i4));
        }
        return new Pair<>(ULongArray.m9206boximpl(r3), ULongArray.m9206boximpl(jArr2));
    }

    @NotNull
    /* renamed from: add-j68ebKY  reason: not valid java name */
    public long[] m8565addj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        long[] jArr3;
        long[] jArr4 = jArr;
        long[] jArr5 = jArr2;
        Intrinsics.checkNotNullParameter(jArr4, "first");
        Intrinsics.checkNotNullParameter(jArr5, "second");
        if (m8559isZeroQwZRm1k(jArr)) {
            return jArr5;
        }
        if (m8559isZeroQwZRm1k(jArr5)) {
            return jArr4;
        }
        int r3 = ULongArray.m9214getSizeimpl(jArr) - m8590countLeadingZeroWordsQwZRm1k(jArr);
        int r4 = ULongArray.m9214getSizeimpl(jArr2) - m8590countLeadingZeroWordsQwZRm1k(jArr5);
        Sextuple sextuple = r3 > r4 ? new Sextuple(Integer.valueOf(ULongArray.m9214getSizeimpl(jArr)), Integer.valueOf(ULongArray.m9214getSizeimpl(jArr2)), ULongArray.m9206boximpl(jArr), ULongArray.m9206boximpl(jArr2), Integer.valueOf(r3), Integer.valueOf(r4)) : new Sextuple(Integer.valueOf(ULongArray.m9214getSizeimpl(jArr2)), Integer.valueOf(ULongArray.m9214getSizeimpl(jArr)), ULongArray.m9206boximpl(jArr2), ULongArray.m9206boximpl(jArr), Integer.valueOf(r4), Integer.valueOf(r3));
        int intValue = ((Number) sextuple.component1()).intValue();
        ((Number) sextuple.component2()).intValue();
        boolean z2 = (ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(((ULongArray) sextuple.component3()).m9222unboximpl(), ((Number) sextuple.component5()).intValue() - 1) & 6917529027641081856L) == 0 && ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(((ULongArray) sextuple.component4()).m9222unboximpl(), ((Number) sextuple.component6()).intValue() - 1) & 6917529027641081856L) == 0) ? false : true;
        if (z2) {
            int i3 = intValue + 1;
            long[] jArr6 = new long[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                jArr6[i4] = 0;
            }
            jArr3 = ULongArray.m9208constructorimpl(jArr6);
        } else {
            long[] jArr7 = new long[intValue];
            for (int i5 = 0; i5 < intValue; i5++) {
                jArr7[i5] = 0;
            }
            jArr3 = ULongArray.m9208constructorimpl(jArr7);
        }
        m8568baseAddIntoArrayKsfQWN0(jArr3, 0, jArr4, jArr5);
        return z2 ? m8649removeLeadingZerosJIhQxVY(jArr3) : jArr3;
    }

    @NotNull
    /* renamed from: and-SIFponk$bignum  reason: not valid java name */
    public final SignedULongArray m8566andSIFponk$bignum(@NotNull SignedULongArray signedULongArray, @NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(signedULongArray, "$receiver");
        Intrinsics.checkNotNullParameter(jArr, "operand");
        return new SignedULongArray(m8567andj68ebKY(signedULongArray.m8673getUnsignedValueY2RjT0g(), jArr), signedULongArray.getSign(), (DefaultConstructorMarker) null);
    }

    @NotNull
    /* renamed from: and-j68ebKY  reason: not valid java name */
    public long[] m8567andj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        Intrinsics.checkNotNullParameter(jArr2, "mask");
        Pair pair = ULongArray.m9214getSizeimpl(jArr) > ULongArray.m9214getSizeimpl(jArr2) ? new Pair(ULongArray.m9206boximpl(jArr), ULongArray.m9206boximpl(jArr2)) : new Pair(ULongArray.m9206boximpl(jArr2), ULongArray.m9206boximpl(jArr));
        ((ULongArray) pair.component1()).m9222unboximpl();
        int r6 = ULongArray.m9214getSizeimpl(((ULongArray) pair.component2()).m9222unboximpl());
        long[] jArr3 = new long[r6];
        for (int i3 = 0; i3 < r6; i3++) {
            jArr3[i3] = ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i3) & ULongArray.m9213getsVKNKU(jArr2, i3));
        }
        return ULongArray.m9208constructorimpl(jArr3);
    }

    /* renamed from: baseAddIntoArray-KsfQWN0  reason: not valid java name */
    public final void m8568baseAddIntoArrayKsfQWN0(@NotNull long[] jArr, int i3, @NotNull long[] jArr2, @NotNull long[] jArr3) {
        long[] jArr4 = jArr;
        int i4 = i3;
        long[] jArr5 = jArr2;
        long[] jArr6 = jArr3;
        Intrinsics.checkNotNullParameter(jArr4, "resultArray");
        Intrinsics.checkNotNullParameter(jArr5, "first");
        Intrinsics.checkNotNullParameter(jArr6, "second");
        int i5 = 0;
        if (m8559isZeroQwZRm1k(jArr5)) {
            ArraysKt___ArraysJvmKt.copyInto(jArr5, jArr4, i4, 0, ULongArray.m9214getSizeimpl(jArr2));
        } else if (m8559isZeroQwZRm1k(jArr6)) {
            ArraysKt___ArraysJvmKt.copyInto(jArr6, jArr4, i4, 0, ULongArray.m9214getSizeimpl(jArr3));
        } else {
            int r5 = ULongArray.m9214getSizeimpl(jArr2) - m8590countLeadingZeroWordsQwZRm1k(jArr5);
            int r7 = ULongArray.m9214getSizeimpl(jArr3) - m8590countLeadingZeroWordsQwZRm1k(jArr6);
            Sextuple sextuple = r5 > r7 ? new Sextuple(Integer.valueOf(ULongArray.m9214getSizeimpl(jArr2)), Integer.valueOf(ULongArray.m9214getSizeimpl(jArr3)), ULongArray.m9206boximpl(jArr2), ULongArray.m9206boximpl(jArr3), Integer.valueOf(r5), Integer.valueOf(r7)) : new Sextuple(Integer.valueOf(ULongArray.m9214getSizeimpl(jArr3)), Integer.valueOf(ULongArray.m9214getSizeimpl(jArr2)), ULongArray.m9206boximpl(jArr3), ULongArray.m9206boximpl(jArr2), Integer.valueOf(r7), Integer.valueOf(r5));
            int intValue = ((Number) sextuple.component1()).intValue();
            ((Number) sextuple.component2()).intValue();
            long[] r4 = ((ULongArray) sextuple.component3()).m9222unboximpl();
            long[] r52 = ((ULongArray) sextuple.component4()).m9222unboximpl();
            int intValue2 = ((Number) sextuple.component5()).intValue();
            int intValue3 = ((Number) sextuple.component6()).intValue();
            long j2 = 0;
            while (i5 < intValue3) {
                long r11 = ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r52, i5) + ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r4, i5) + j2));
                ULongArray.m9218setk8EXiF4(jArr4, i5 + i4, ULong.m9153constructorimpl(m8614getBaseMasksVKNKU() & r11));
                j2 = ULong.m9153constructorimpl(r11 >>> 63);
                i5++;
            }
            while (j2 != 0) {
                if (i5 == intValue) {
                    ULongArray.m9218setk8EXiF4(jArr4, intValue + i4, j2);
                    return;
                }
                long r10 = ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r4, i5) + j2);
                ULongArray.m9218setk8EXiF4(jArr4, i5, ULong.m9153constructorimpl(m8614getBaseMasksVKNKU() & r10));
                j2 = ULong.m9153constructorimpl(r10 >>> 63);
                i5++;
            }
            while (i5 < intValue2) {
                ULongArray.m9218setk8EXiF4(jArr4, i5 + i4, ULongArray.m9213getsVKNKU(r4, i5));
                i5++;
            }
        }
    }

    @NotNull
    /* renamed from: baseDivide-GR1PJdc  reason: not valid java name */
    public final Pair<ULongArray, ULongArray> m8569baseDivideGR1PJdc(@NotNull long[] jArr, @NotNull long[] jArr2) {
        long[] jArr3 = jArr;
        long[] jArr4 = jArr2;
        Intrinsics.checkNotNullParameter(jArr3, "unnormalizedDividend");
        Intrinsics.checkNotNullParameter(jArr4, "unnormalizedDivisor");
        if (m8584compareToGR1PJdc$bignum(jArr4, jArr3) > 0) {
            return new Pair<>(ULongArray.m9206boximpl(m8623getZEROY2RjT0g()), ULongArray.m9206boximpl(jArr));
        }
        if (ULongArray.m9214getSizeimpl(jArr2) == 1 && ULongArray.m9214getSizeimpl(jArr) == 1) {
            return new Pair<>(ULongArray.m9206boximpl(m8649removeLeadingZerosJIhQxVY(new long[]{Long.divideUnsigned(ULongArray.m9213getsVKNKU(jArr3, 0), ULongArray.m9213getsVKNKU(jArr4, 0))})), ULongArray.m9206boximpl(m8649removeLeadingZerosJIhQxVY(new long[]{Long.remainderUnsigned(ULongArray.m9213getsVKNKU(jArr3, 0), ULongArray.m9213getsVKNKU(jArr4, 0))})));
        } else if (m8577bitLengthQwZRm1k(jArr) - m8577bitLengthQwZRm1k(jArr4) == 0) {
            return new Pair<>(ULongArray.m9206boximpl(m8618getONEY2RjT0g()), ULongArray.m9206boximpl(m8627minusj68ebKY$bignum(jArr, jArr2)));
        } else {
            Triple<ULongArray, ULongArray, Integer> r12 = m8631normalizeGR1PJdc(jArr, jArr2);
            long[] r2 = r12.component1().m9222unboximpl();
            long[] r5 = r12.component2().m9222unboximpl();
            int intValue = r12.component3().intValue();
            int r6 = ULongArray.m9214getSizeimpl(r2);
            int r7 = ULongArray.m9214getSizeimpl(r5);
            int r8 = ULongArray.m9214getSizeimpl(r5) - m8590countLeadingZeroWordsQwZRm1k(r5);
            int i3 = r6 - r7;
            long[] r9 = ULongArray.m9207constructorimpl(i3);
            long[] r10 = m8653shlGERUpyg$bignum(r5, getBasePowerOfTwo() * i3);
            if (m8584compareToGR1PJdc$bignum(r2, r10) >= 0) {
                r9 = ULongArray.m9207constructorimpl(i3 + 1);
                ULongArray.m9218setk8EXiF4(r9, i3, 1);
                r2 = m8627minusj68ebKY$bignum(r2, r10);
            }
            int i4 = i3 - 1;
            if (i4 >= 0) {
                while (true) {
                    int i5 = i4 - 1;
                    int i6 = r7 + i4;
                    long[] r11 = m8602from32BitehPNNiw$bignum(BigInteger32Arithmetic.INSTANCE.m8454divideYnv0uTE(m8661to32BitkqpWZOw$bignum(i6 < ULongArray.m9214getSizeimpl(r2) ? m8642plusss9iZGw$bignum(m8653shlGERUpyg$bignum(new long[]{ULongArray.m9213getsVKNKU(r2, i6)}, getBasePowerOfTwo()), ULongArray.m9213getsVKNKU(r2, i6 - 1)) : i6 == ULongArray.m9214getSizeimpl(r2) ? new long[]{ULongArray.m9213getsVKNKU(r2, i6 - 1)} : m8623getZEROY2RjT0g()), m8661to32BitkqpWZOw$bignum(new long[]{ULongArray.m9213getsVKNKU(r5, r7 - 1)})).getFirst().m9143unboximpl());
                    ULongArray.m9218setk8EXiF4(r9, i4, m8583compareTo3yFGk1Y$bignum(r11, ULong.m9153constructorimpl(m8614getBaseMasksVKNKU() - 1)) < 0 ? ULongArray.m9213getsVKNKU(r11, 0) : m8614getBaseMasksVKNKU());
                    long[] r112 = m8653shlGERUpyg$bignum(m8572baseMultiplyWithCorrectedSize_EW1lsA(r5, ULongArray.m9213getsVKNKU(r9, i4), r8), getBasePowerOfTwo() * i4);
                    while (m8584compareToGR1PJdc$bignum(r112, r2) > 0) {
                        ULongArray.m9218setk8EXiF4(r9, i4, ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r9, i4) - ULong.m9153constructorimpl(((long) 1) & 4294967295L)));
                        r112 = m8653shlGERUpyg$bignum(m8572baseMultiplyWithCorrectedSize_EW1lsA(r5, ULongArray.m9213getsVKNKU(r9, i4), r8), getBasePowerOfTwo() * i4);
                    }
                    r2 = m8627minusj68ebKY$bignum(r2, r112);
                    if (i5 < 0) {
                        break;
                    }
                    i4 = i5;
                }
            }
            while (m8584compareToGR1PJdc$bignum(r2, r5) >= 0) {
                r9 = m8642plusss9iZGw$bignum(r9, 1);
                r2 = m8627minusj68ebKY$bignum(r2, r5);
            }
            return new Pair<>(ULongArray.m9206boximpl(m8649removeLeadingZerosJIhQxVY(r9)), ULongArray.m9206boximpl(m8593denormalizeGERUpyg(r2, intValue)));
        }
    }

    @NotNull
    /* renamed from: baseMultiply-ss9iZGw  reason: not valid java name */
    public final long[] m8570baseMultiplyss9iZGw(@NotNull long[] jArr, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "first");
        return m8572baseMultiplyWithCorrectedSize_EW1lsA(jArr, j2, ULongArray.m9214getSizeimpl(jArr) - m8590countLeadingZeroWordsQwZRm1k(jArr));
    }

    @NotNull
    /* renamed from: baseMultiplyIntoArray-cKnQUHg  reason: not valid java name */
    public final long[] m8571baseMultiplyIntoArraycKnQUHg(@NotNull long[] jArr, int i3, int i4, @NotNull long[] jArr2, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "result");
        Intrinsics.checkNotNullParameter(jArr2, "first");
        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
    }

    @NotNull
    /* renamed from: baseMultiplyWithCorrectedSize-_EW1lsA  reason: not valid java name */
    public final long[] m8572baseMultiplyWithCorrectedSize_EW1lsA(@NotNull long[] jArr, long j2, int i3) {
        long[] jArr2 = jArr;
        long j3 = j2;
        Intrinsics.checkNotNullParameter(jArr2, "first");
        long r3 = ULong.m9153constructorimpl(m8617getLowMasksVKNKU() & j3);
        char c3 = ' ';
        long r6 = ULong.m9153constructorimpl(j3 >>> 32);
        int r12 = m8578bitLengthVKZWuLQ(j3) + m8577bitLengthQwZRm1k(jArr);
        long[] r13 = ULongArray.m9207constructorimpl(r12 % 63 != 0 ? (r12 / 63) + 1 : r12 / 63);
        int i4 = 0;
        int i5 = i3;
        int i6 = 0;
        long j4 = 0;
        while (i4 < i5) {
            long r15 = ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr2, i4) & m8617getLowMasksVKNKU());
            long r17 = ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr2, i4) >>> c3);
            long r19 = ULong.m9153constructorimpl(r15 * r3);
            long r8 = ULong.m9153constructorimpl(r19 >>> 63);
            long j5 = r6;
            long d2 = a.d(r19 & m8614getBaseMasksVKNKU(), j4);
            long d3 = a.d(d2 >>> 63, r8);
            long r5 = ULong.m9153constructorimpl(d2 & m8614getBaseMasksVKNKU());
            long r11 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(r3 * r17) + ULong.m9153constructorimpl(r15 * j5));
            int i7 = i4 + 1;
            long d4 = a.d(r11 >>> 31, d3);
            long d5 = a.d(ULong.m9153constructorimpl(r11 << 32) & m8614getBaseMasksVKNKU(), r5);
            long d6 = a.d(d5 >>> 63, d4);
            ULongArray.m9218setk8EXiF4(r13, i6, ULong.m9153constructorimpl(d5 & m8614getBaseMasksVKNKU()));
            j4 = a.d(ULong.m9153constructorimpl(r17 * j5) << 1, d6);
            i6++;
            i4 = i7;
            c3 = ' ';
            r3 = r3;
            r6 = j5;
        }
        if (j4 != 0) {
            ULongArray.m9218setk8EXiF4(r13, i6, j4);
        }
        return r13;
    }

    @NotNull
    /* renamed from: basecaseMultiply-j68ebKY  reason: not valid java name */
    public final long[] m8573basecaseMultiplyj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "first");
        Intrinsics.checkNotNullParameter(jArr2, "second");
        return m8555basecaseMultiplyWithCorrectedSizemwLU0fg(jArr, jArr2, ULongArray.m9214getSizeimpl(jArr) - m8590countLeadingZeroWordsQwZRm1k(jArr), ULongArray.m9214getSizeimpl(jArr2) - m8590countLeadingZeroWordsQwZRm1k(jArr2));
    }

    @NotNull
    /* renamed from: basecaseSqrt-QwZRm1k$bignum  reason: not valid java name */
    public final Pair<ULongArray, ULongArray> m8574basecaseSqrtQwZRm1k$bignum(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        long[] r02 = m8656sqrtIntJIhQxVY$bignum(jArr);
        return new Pair<>(ULongArray.m9206boximpl(r02), ULongArray.m9206boximpl(m8627minusj68ebKY$bignum(jArr, m8659timesj68ebKY$bignum(r02, r02))));
    }

    @NotNull
    /* renamed from: basicDivide2-GR1PJdc  reason: not valid java name */
    public final Pair<ULongArray, ULongArray> m8575basicDivide2GR1PJdc(@NotNull long[] jArr, @NotNull long[] jArr2) {
        long[] jArr3;
        Intrinsics.checkNotNullParameter(jArr, "unnormalizedDividend");
        Intrinsics.checkNotNullParameter(jArr2, "unnormalizedDivisor");
        Triple<ULongArray, ULongArray, Integer> r12 = m8631normalizeGR1PJdc(jArr, jArr2);
        long[] r2 = r12.component1().m9222unboximpl();
        long[] r3 = r12.component2().m9222unboximpl();
        int intValue = r12.component3().intValue();
        int r4 = ULongArray.m9214getSizeimpl(r2) - ULongArray.m9214getSizeimpl(r3);
        long[] r5 = m8653shlGERUpyg$bignum(r3, wordSizeInBits * r4);
        int i3 = r4 + 1;
        long[] jArr4 = new long[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            jArr4[i4] = 0;
        }
        long[] r7 = ULongArray.m9208constructorimpl(jArr4);
        if (m8584compareToGR1PJdc$bignum(r2, r5) > 0) {
            ULongArray.m9218setk8EXiF4(r7, r4, 1);
            r2 = m8627minusj68ebKY$bignum(r2, r5);
        }
        m8623getZEROY2RjT0g();
        m8623getZEROY2RjT0g();
        m8623getZEROY2RjT0g();
        m8623getZEROY2RjT0g();
        int i5 = r4 - 1;
        if (i5 >= 0) {
            while (true) {
                int i6 = i5 - 1;
                BigInteger32Arithmetic bigInteger32Arithmetic = BigInteger32Arithmetic.INSTANCE;
                ULongArray.m9218setk8EXiF4(r7, i5, ULongArray.m9213getsVKNKU(m8626minj68ebKY(m8602from32BitehPNNiw$bignum(bigInteger32Arithmetic.m8454divideYnv0uTE(m8661to32BitkqpWZOw$bignum(ULongArray.m9208constructorimpl(ArraysKt.copyOfRange(jArr3, ULongArray.m9214getSizeimpl(r3) - 1, ULongArray.m9214getSizeimpl(r3) + 1))), m8661to32BitkqpWZOw$bignum(new long[]{ULongArray.m9213getsVKNKU(r3, ULongArray.m9214getSizeimpl(r3) - 1)})).getFirst().m9143unboximpl()), m8615getBaseMaskArrayY2RjT0g()), 0));
                long[] r8 = m8653shlGERUpyg$bignum(r3, bigInteger32Arithmetic.getWordSizeInBits() * i5);
                long[] r10 = m8653shlGERUpyg$bignum(m8660timesss9iZGw$bignum(r3, ULongArray.m9213getsVKNKU(r7, i5)), wordSizeInBits * i5);
                if (m8584compareToGR1PJdc$bignum(r10, jArr3) > 0) {
                    for (long[] r11 = m8627minusj68ebKY$bignum(r10, jArr3); m8584compareToGR1PJdc$bignum(r11, r10) > 0; r11 = m8627minusj68ebKY$bignum(r11, r8)) {
                        ULongArray.m9218setk8EXiF4(r7, i5, ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r7, i5) - ULong.m9153constructorimpl(((long) 1) & 4294967295L)));
                    }
                    jArr3 = m8653shlGERUpyg$bignum(m8627minusj68ebKY$bignum(jArr3, m8660timesss9iZGw$bignum(r3, ULongArray.m9213getsVKNKU(r7, i5))), BigInteger32Arithmetic.INSTANCE.getWordSizeInBits() * i5);
                } else {
                    jArr3 = m8627minusj68ebKY$bignum(jArr3, r10);
                }
                if (i6 < 0) {
                    break;
                }
                i5 = i6;
            }
        }
        return new Pair<>(ULongArray.m9206boximpl(m8649removeLeadingZerosJIhQxVY(r7)), ULongArray.m9206boximpl(m8593denormalizeGERUpyg(jArr3, intValue)));
    }

    /* renamed from: bitAt-tBf0fek  reason: not valid java name */
    public boolean m8576bitAttBf0fek(@NotNull long[] jArr, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        long j3 = (long) 63;
        long j4 = j2 / j3;
        if (j4 <= SieveCacheKt.NodeLinkMask) {
            return j4 < ((long) ULongArray.m9214getSizeimpl(jArr)) && ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, (int) j4) & ULong.m9153constructorimpl(1 << ((int) (j2 % j3)))) != 0;
        }
        throw new RuntimeException("Invalid bit index, too large, cannot access word (Word position > Int.MAX_VALUE");
    }

    /* renamed from: bitLength-QwZRm1k  reason: not valid java name */
    public int m8577bitLengthQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "value");
        if (m8559isZeroQwZRm1k(jArr)) {
            return 0;
        }
        int r02 = (ULongArray.m9214getSizeimpl(jArr) - m8590countLeadingZeroWordsQwZRm1k(jArr)) - 1;
        return (r02 * 63) + m8578bitLengthVKZWuLQ(ULongArray.m9213getsVKNKU(jArr, r02));
    }

    /* renamed from: bitLength-VKZWuLQ  reason: not valid java name */
    public final int m8578bitLengthVKZWuLQ(long j2) {
        return 63 - m8636numberOfLeadingZerosInAWordVKZWuLQ(j2);
    }

    /* renamed from: bitLengthFor64BitArray-QwZRm1k  reason: not valid java name */
    public final int m8579bitLengthFor64BitArrayQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "value");
        if (m8559isZeroQwZRm1k(jArr)) {
            return 0;
        }
        return ((ULongArray.m9214getSizeimpl(jArr) - 1) * 64) + m8580bitLengthFor64BitWordVKZWuLQ(ULongArray.m9213getsVKNKU(jArr, ULongArray.m9214getSizeimpl(jArr) - 1));
    }

    /* renamed from: bitLengthFor64BitWord-VKZWuLQ  reason: not valid java name */
    public final int m8580bitLengthFor64BitWordVKZWuLQ(long j2) {
        return 64 - m8635numberOfLeadingZeroesInA64BitWordVKZWuLQ(j2);
    }

    /* renamed from: combaMultiply-GR1PJdc  reason: not valid java name */
    public final void m8581combaMultiplyGR1PJdc(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "first");
        Intrinsics.checkNotNullParameter(jArr2, "second");
    }

    /* renamed from: compare-GR1PJdc  reason: not valid java name */
    public int m8582compareGR1PJdc(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "first");
        Intrinsics.checkNotNullParameter(jArr2, "second");
        return m8585compareWithStartIndexesMccmUSY(jArr, jArr2, ULongArray.m9214getSizeimpl(jArr) - m8590countLeadingZeroWordsQwZRm1k(jArr), ULongArray.m9214getSizeimpl(jArr2) - m8590countLeadingZeroWordsQwZRm1k(jArr2));
    }

    /* renamed from: compareTo-3yFGk1Y$bignum  reason: not valid java name */
    public final int m8583compareTo3yFGk1Y$bignum(@NotNull long[] jArr, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        return m8582compareGR1PJdc(jArr, new long[]{j2});
    }

    /* renamed from: compareTo-GR1PJdc$bignum  reason: not valid java name */
    public final int m8584compareToGR1PJdc$bignum(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        Intrinsics.checkNotNullParameter(jArr2, "other");
        return m8582compareGR1PJdc(jArr, jArr2);
    }

    /* renamed from: compareWithStartIndexes-MccmUSY  reason: not valid java name */
    public final int m8585compareWithStartIndexesMccmUSY(@NotNull long[] jArr, @NotNull long[] jArr2, int i3, int i4) {
        boolean z2;
        boolean z3;
        Intrinsics.checkNotNullParameter(jArr, "first");
        Intrinsics.checkNotNullParameter(jArr2, "second");
        if (i3 > i4) {
            return 1;
        }
        if (i4 > i3) {
            return -1;
        }
        int i5 = i3 - 1;
        while (true) {
            if (i5 < 0) {
                z2 = true;
                z3 = false;
                break;
            } else if (Long.compareUnsigned(ULongArray.m9213getsVKNKU(jArr, i5), ULongArray.m9213getsVKNKU(jArr2, i5)) > 0) {
                z3 = true;
                z2 = false;
                break;
            } else if (Long.compareUnsigned(ULongArray.m9213getsVKNKU(jArr, i5), ULongArray.m9213getsVKNKU(jArr2, i5)) < 0) {
                z2 = false;
                z3 = false;
                break;
            } else {
                i5--;
            }
        }
        if (z2) {
            return 0;
        }
        return z3 ? 1 : -1;
    }

    @NotNull
    /* renamed from: convertFrom32BitRepresentation-ehPNNiw$bignum  reason: not valid java name */
    public final long[] m8586convertFrom32BitRepresentationehPNNiw$bignum(@NotNull int[] iArr) {
        int[] iArr2 = iArr;
        int i3 = 2;
        int i4 = 0;
        Intrinsics.checkNotNullParameter(iArr2, "operand");
        if (UIntArray.m9135getSizeimpl(iArr) == 0) {
            return m8623getZEROY2RjT0g();
        }
        if (UIntArray.m9135getSizeimpl(iArr) == 1) {
            return new long[]{ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 0)) & 4294967295L)};
        }
        int r5 = BigInteger32Arithmetic.INSTANCE.m8444bitLengthajY9A(iArr2);
        int i5 = r5 % 63 == 0 ? r5 / 63 : (r5 / 63) + 1;
        long[] r8 = ULongArray.m9207constructorimpl(i5);
        if (i5 > 0) {
            int i6 = 0;
            while (true) {
                int i7 = i6 + 1;
                int i8 = i6 % 32;
                int i9 = (i6 * 2) - (i6 / 32);
                if (i5 == i3) {
                    ULongArray.m9218setk8EXiF4(r8, i4, ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i4)) & 4294967295L) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 1)) & 4294967295L) << 32) & m8616getHighMasksVKNKU())));
                    if (UIntArray.m9135getSizeimpl(iArr) == 4) {
                        ULongArray.m9218setk8EXiF4(r8, 1, ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 1)) & 4294967295L) >>> 31) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i3)) & 4294967295L) << 1)) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 3)) & 4294967295L) << 33)));
                    } else {
                        ULongArray.m9218setk8EXiF4(r8, 1, ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 1)) & 4294967295L) >>> 31) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i3)) & 4294967295L) << 1)));
                    }
                } else if (i6 == 0) {
                    ULongArray.m9218setk8EXiF4(r8, i6, ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i4)) & 4294967295L) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, 1)) & 4294967295L) << 32) & m8616getHighMasksVKNKU())));
                } else if (1 <= i6 && i6 < i5 - 1) {
                    ULongArray.m9218setk8EXiF4(r8, i6, ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i9)) & 4294967295L) << i8) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i9 - 1)) & 4294967295L) >>> (32 - i8))) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i9 + 1)) & 4294967295L) << (i8 + 32)) & m8616getHighMasksVKNKU())));
                } else if (i6 == i5 - 1) {
                    if (i9 < UIntArray.m9135getSizeimpl(iArr)) {
                        ULongArray.m9218setk8EXiF4(r8, i6, ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i9 - 1)) & 4294967295L) >>> (32 - i8)) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i9)) & 4294967295L) << i8)));
                    } else {
                        ULongArray.m9218setk8EXiF4(r8, i6, ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UIntArray.m9134getpVg5ArA(iArr2, i9 - 1)) & 4294967295L) >>> (32 - i8)));
                    }
                }
                if (i7 >= i5) {
                    break;
                }
                i6 = i7;
                i3 = 2;
                i4 = 0;
            }
        }
        return r8;
    }

    @NotNull
    /* renamed from: convertFrom64BitRepresentation-JIhQxVY$bignum  reason: not valid java name */
    public final long[] m8587convertFrom64BitRepresentationJIhQxVY$bignum(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        if (ULongArray.m9214getSizeimpl(jArr) == 0) {
            return m8623getZEROY2RjT0g();
        }
        if (ULongArray.m9214getSizeimpl(jArr) == 1) {
            long[] r02 = ULongArray.m9207constructorimpl(2);
            ULongArray.m9218setk8EXiF4(r02, 0, ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, 0) & m8614getBaseMasksVKNKU()));
            ULongArray.m9218setk8EXiF4(r02, 1, ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, 0) >>> 63));
            return r02;
        }
        int r03 = m8579bitLengthFor64BitArrayQwZRm1k(jArr);
        int i3 = r03 % 63 == 0 ? r03 / 63 : (r03 / 63) + 1;
        if (i3 == 2) {
            long[] r04 = ULongArray.m9207constructorimpl(2);
            ULongArray.m9218setk8EXiF4(r04, 0, ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, 0) & m8614getBaseMasksVKNKU()));
            ULongArray.m9218setk8EXiF4(r04, 1, ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, 0) >>> 63) | ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, 1) << 1)));
            return r04;
        }
        long[] r12 = ULongArray.m9207constructorimpl(i3);
        if (i3 > 0) {
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                int i6 = i4 % 64;
                int i7 = i4 - (i4 / 64);
                if (i4 == 0) {
                    ULongArray.m9218setk8EXiF4(r12, i4, ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, 0) & m8614getBaseMasksVKNKU()));
                } else if (1 <= i4 && i4 < i3 - 1) {
                    ULongArray.m9218setk8EXiF4(r12, i4, i6 > 0 ? ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i7) << i6) | ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i7 - 1) >>> (64 - i6))) & m8614getBaseMasksVKNKU()) : ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i7) << i6) & m8614getBaseMasksVKNKU()));
                } else if (i4 == i3 - 1) {
                    if (i7 < ULongArray.m9214getSizeimpl(jArr)) {
                        ULongArray.m9218setk8EXiF4(r12, i4, i6 > 0 ? ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i7) << i6) | ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i7 - 1) >>> (64 - i6))) & m8614getBaseMasksVKNKU()) : ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i7) << i6) & m8614getBaseMasksVKNKU()));
                    } else {
                        ULongArray.m9218setk8EXiF4(r12, i4, i6 > 0 ? ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i7 - 1) >>> (64 - i6)) : 0);
                    }
                }
                if (i5 >= i3) {
                    break;
                }
                i4 = i5;
            }
        }
        return r12;
    }

    @NotNull
    /* renamed from: convertTo32BitRepresentation-kqpWZOw$bignum  reason: not valid java name */
    public final int[] m8588convertTo32BitRepresentationkqpWZOw$bignum(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        long[] r10 = m8589convertTo64BitRepresentationJIhQxVY$bignum(jArr);
        int[] r11 = UIntArray.m9128constructorimpl(ULongArray.m9214getSizeimpl(r10) * 2);
        int r02 = ULongArray.m9214getSizeimpl(r10);
        if (r02 > 0) {
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                int i5 = i3 * 2;
                UIntArray.m9139setVXSXFK8(r11, i5, UInt.m9074constructorimpl((int) ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r10, i3) & ULong.m9153constructorimpl(((long) BigInteger32Arithmetic.INSTANCE.m8467getBasepVg5ArA()) & 4294967295L))));
                UIntArray.m9139setVXSXFK8(r11, i5 + 1, UInt.m9074constructorimpl((int) ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r10, i3) >>> 32)));
                if (i4 >= r02) {
                    break;
                }
                i3 = i4;
            }
        }
        return BigInteger32Arithmetic.INSTANCE.m8500removeLeadingZeroshkIa6DI(r11);
    }

    @NotNull
    /* renamed from: convertTo64BitRepresentation-JIhQxVY$bignum  reason: not valid java name */
    public final long[] m8589convertTo64BitRepresentationJIhQxVY$bignum(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        if (m8559isZeroQwZRm1k(jArr)) {
            return m8623getZEROY2RjT0g();
        }
        int r02 = m8577bitLengthQwZRm1k(jArr);
        int i3 = r02 % 64 == 0 ? r02 / 64 : (r02 / 64) + 1;
        long[] r12 = ULongArray.m9207constructorimpl(i3);
        if (i3 > 0) {
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                int i6 = i4 % 63;
                int i7 = (i4 / 63) + i4;
                int i8 = i7 + 1;
                if (i8 < ULongArray.m9214getSizeimpl(jArr)) {
                    ULongArray.m9218setk8EXiF4(r12, i4, ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i8) << (63 - i6)) | ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i7) >>> i6)));
                } else {
                    ULongArray.m9218setk8EXiF4(r12, i4, ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i7) >>> i6));
                }
                if (i5 >= i3) {
                    break;
                }
                i4 = i5;
            }
        }
        return m8649removeLeadingZerosJIhQxVY(r12);
    }

    /* renamed from: countLeadingZeroWords-QwZRm1k  reason: not valid java name */
    public final int m8590countLeadingZeroWordsQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "bigInteger");
        int r4 = ULongArray.m9214getSizeimpl(jArr) - 1;
        if (r4 <= 0) {
            return 0;
        }
        long j2 = ULongArray.m9213getsVKNKU(jArr, r4);
        while (j2 == 0 && r4 > 0) {
            r4--;
            j2 = ULongArray.m9213getsVKNKU(jArr, r4);
        }
        if (ULongArray.m9213getsVKNKU(jArr, r4) == 0) {
            r4--;
        }
        return (ULongArray.m9214getSizeimpl(jArr) - r4) - 1;
    }

    @NotNull
    /* renamed from: d1ReciprocalRecursive-QwZRm1k  reason: not valid java name */
    public final Pair<ULongArray, ULongArray> m8591d1ReciprocalRecursiveQwZRm1k(@NotNull long[] jArr) {
        long[] jArr2;
        Intrinsics.checkNotNullParameter(jArr, "a");
        int r2 = m8577bitLengthQwZRm1k(jArr);
        if (r2 > 63) {
            r2 -= 63;
        }
        if (r2 <= 30) {
            long r22 = ULong.m9153constructorimpl(1 << (r2 * 2));
            long j2 = ULongArray.m9213getsVKNKU(jArr, 0);
            long divideUnsigned = Long.divideUnsigned(r22, j2);
            long r7 = ULong.m9153constructorimpl(r22 - ULong.m9153constructorimpl(j2 * divideUnsigned));
            return new Pair<>(ULongArray.m9206boximpl(new long[]{divideUnsigned}), ULongArray.m9206boximpl(new long[]{r7}));
        }
        int floor = (int) Math.floor(((double) (r2 - 1)) / ((double) 2));
        int i3 = r2 - floor;
        long[] r12 = m8627minusj68ebKY$bignum(m8653shlGERUpyg$bignum(m8618getONEY2RjT0g(), floor), m8618getONEY2RjT0g());
        long[] r3 = m8654shrGERUpyg$bignum(jArr, floor);
        long[] r13 = m8567andj68ebKY(jArr, r12);
        Pair<ULongArray, ULongArray> r32 = m8591d1ReciprocalRecursiveQwZRm1k(r3);
        long[] r4 = r32.component1().m9222unboximpl();
        long[] r33 = r32.component2().m9222unboximpl();
        long[] r14 = m8659timesj68ebKY$bignum(r13, r4);
        long[] r34 = m8653shlGERUpyg$bignum(r33, floor);
        if (m8584compareToGR1PJdc$bignum(r34, r14) >= 0) {
            jArr2 = m8627minusj68ebKY$bignum(r34, r14);
        } else {
            r4 = m8627minusj68ebKY$bignum(r4, m8618getONEY2RjT0g());
            jArr2 = m8627minusj68ebKY$bignum(m8641plusj68ebKY$bignum(r34, jArr), r14);
        }
        long[] r23 = m8654shrGERUpyg$bignum(m8659timesj68ebKY$bignum(r4, m8654shrGERUpyg$bignum(jArr2, i3)), i3);
        long[] r35 = m8641plusj68ebKY$bignum(m8653shlGERUpyg$bignum(r4, floor), r23);
        long[] r02 = m8627minusj68ebKY$bignum(m8653shlGERUpyg$bignum(jArr2, floor), m8659timesj68ebKY$bignum(jArr, r23));
        if (m8584compareToGR1PJdc$bignum(r02, jArr) >= 0) {
            r35 = m8641plusj68ebKY$bignum(r35, m8618getONEY2RjT0g());
            r02 = m8627minusj68ebKY$bignum(r02, jArr);
            if (m8584compareToGR1PJdc$bignum(r02, jArr) >= 0) {
                r35 = m8641plusj68ebKY$bignum(r35, m8618getONEY2RjT0g());
                r02 = m8627minusj68ebKY$bignum(r02, jArr);
            }
        }
        return new Pair<>(ULongArray.m9206boximpl(r35), ULongArray.m9206boximpl(r02));
    }

    @NotNull
    /* renamed from: d1ReciprocalRecursiveWordVersion-QwZRm1k  reason: not valid java name */
    public final Pair<ULongArray, ULongArray> m8592d1ReciprocalRecursiveWordVersionQwZRm1k(@NotNull long[] jArr) {
        long[] jArr2;
        Intrinsics.checkNotNullParameter(jArr, "a");
        int r02 = ULongArray.m9214getSizeimpl(jArr);
        int i3 = r02 - 1;
        if (i3 <= 2) {
            if (i3 == 0) {
                i3 = 1;
            }
            long[] r03 = m8653shlGERUpyg$bignum(m8618getONEY2RjT0g(), i3 * 2 * wordSizeInBits);
            long[] r12 = m8594divj68ebKY$bignum(r03, jArr);
            return new Pair<>(ULongArray.m9206boximpl(r12), ULongArray.m9206boximpl(m8627minusj68ebKY$bignum(r03, m8659timesj68ebKY$bignum(r12, jArr))));
        }
        int floor = (int) Math.floor(((double) (r02 - 2)) / ((double) 2));
        int i4 = i3 - floor;
        long[] r2 = ULongArray.m9208constructorimpl(ArraysKt.copyOfRange(jArr, (ULongArray.m9214getSizeimpl(jArr) - i4) - 1, ULongArray.m9214getSizeimpl(jArr)));
        long[] r3 = ULongArray.m9208constructorimpl(ArraysKt.copyOfRange(jArr, 0, floor));
        Pair<ULongArray, ULongArray> r22 = m8592d1ReciprocalRecursiveWordVersionQwZRm1k(r2);
        long[] r4 = r22.component1().m9222unboximpl();
        long[] r23 = r22.component2().m9222unboximpl();
        long[] r32 = m8659timesj68ebKY$bignum(r3, r4);
        int i5 = wordSizeInBits;
        long[] r24 = m8653shlGERUpyg$bignum(r23, floor * i5);
        if (m8584compareToGR1PJdc$bignum(r24, r32) >= 0) {
            jArr2 = m8627minusj68ebKY$bignum(r24, r32);
        } else {
            r4 = m8627minusj68ebKY$bignum(r4, m8618getONEY2RjT0g());
            jArr2 = m8627minusj68ebKY$bignum(m8641plusj68ebKY$bignum(r24, jArr), r32);
        }
        long[] r13 = m8654shrGERUpyg$bignum(m8659timesj68ebKY$bignum(r4, m8654shrGERUpyg$bignum(jArr2, i4 * i5)), i4 * i5);
        long[] r33 = m8641plusj68ebKY$bignum(m8653shlGERUpyg$bignum(r4, floor * i5), r13);
        long[] r04 = m8627minusj68ebKY$bignum(m8653shlGERUpyg$bignum(jArr2, floor * i5), m8659timesj68ebKY$bignum(jArr, r13));
        if (m8584compareToGR1PJdc$bignum(r04, jArr) >= 0) {
            r33 = m8641plusj68ebKY$bignum(r33, m8618getONEY2RjT0g());
            r04 = m8627minusj68ebKY$bignum(r04, jArr);
            if (m8584compareToGR1PJdc$bignum(r04, jArr) >= 0) {
                r33 = m8641plusj68ebKY$bignum(r33, m8618getONEY2RjT0g());
                r04 = m8627minusj68ebKY$bignum(r04, jArr);
            }
        }
        return new Pair<>(ULongArray.m9206boximpl(r33), ULongArray.m9206boximpl(r04));
    }

    @NotNull
    /* renamed from: denormalize-GERUpyg  reason: not valid java name */
    public final long[] m8593denormalizeGERUpyg(@NotNull long[] jArr, int i3) {
        Intrinsics.checkNotNullParameter(jArr, "remainderNormalized");
        return m8654shrGERUpyg$bignum(jArr, i3);
    }

    @NotNull
    public final SignedULongArray div$bignum(@NotNull SignedULongArray signedULongArray, @NotNull SignedULongArray signedULongArray2) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        Intrinsics.checkNotNullParameter(signedULongArray2, "other");
        return signedDivide(signedULongArray, signedULongArray2);
    }

    @NotNull
    /* renamed from: div-j68ebKY$bignum  reason: not valid java name */
    public final long[] m8594divj68ebKY$bignum(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        Intrinsics.checkNotNullParameter(jArr2, "other");
        return m8596divideGR1PJdc(jArr, jArr2).getFirst().m9222unboximpl();
    }

    @NotNull
    /* renamed from: div-ss9iZGw$bignum  reason: not valid java name */
    public final long[] m8595divss9iZGw$bignum(@NotNull long[] jArr, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        return m8596divideGR1PJdc(jArr, new long[]{j2}).getFirst().m9222unboximpl();
    }

    @NotNull
    /* renamed from: divide-GR1PJdc  reason: not valid java name */
    public Pair<ULongArray, ULongArray> m8596divideGR1PJdc(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "first");
        Intrinsics.checkNotNullParameter(jArr2, "second");
        return m8569baseDivideGR1PJdc(jArr, jArr2);
    }

    @NotNull
    /* renamed from: divrem-GR1PJdc$bignum  reason: not valid java name */
    public final Pair<ULongArray, ULongArray> m8597divremGR1PJdc$bignum(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        Intrinsics.checkNotNullParameter(jArr2, "other");
        return m8596divideGR1PJdc(jArr, jArr2);
    }

    @NotNull
    /* renamed from: exactDivideBy3-JIhQxVY  reason: not valid java name */
    public final long[] m8598exactDivideBy3JIhQxVY(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        return UCollectionsKt.toULongArray(UArraysKt.m10048sliceZRhS8yI(m8630multiplyj68ebKY(jArr, UCollectionsKt.toULongArray(ULongArray.m9206boximpl(ModularBigInteger.Companion.creatorForModulo(BigInteger.Companion.getONE().shl(ULongArray.m9214getSizeimpl(jArr) * 63)).fromInt(3).inverse().toBigInteger().m8301getMagnitudeY2RjT0g$bignum()))), ArraysKt.getIndices(jArr)));
    }

    @NotNull
    /* renamed from: exactDivideBy3Better-JIhQxVY  reason: not valid java name */
    public final long[] m8599exactDivideBy3BetterJIhQxVY(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        return jArr;
    }

    @NotNull
    /* renamed from: extendULongArray-9R_UfW4  reason: not valid java name */
    public final long[] m8600extendULongArray9R_UfW4(@NotNull long[] jArr, int i3, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "original");
        int r3 = ULongArray.m9214getSizeimpl(jArr) + i3;
        long[] jArr2 = new long[r3];
        int i4 = 0;
        while (i4 < r3) {
            jArr2[i4] = i4 < ULongArray.m9214getSizeimpl(jArr) ? ULongArray.m9213getsVKNKU(jArr, i4) : j2;
            i4++;
        }
        return ULongArray.m9208constructorimpl(jArr2);
    }

    @NotNull
    /* renamed from: fftMultiply-j68ebKY  reason: not valid java name */
    public final long[] m8601fftMultiplyj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "first");
        Intrinsics.checkNotNullParameter(jArr2, "second");
        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
    }

    @NotNull
    /* renamed from: from32Bit-ehPNNiw$bignum  reason: not valid java name */
    public final long[] m8602from32BitehPNNiw$bignum(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "$receiver");
        return m8586convertFrom32BitRepresentationehPNNiw$bignum(iArr);
    }

    @NotNull
    /* renamed from: fromByte-DHQ6RzY  reason: not valid java name */
    public long[] m8603fromByteDHQ6RzY(byte b3) {
        return new long[]{ULong.m9153constructorimpl((long) Math.abs(b3))};
    }

    @NotNull
    /* renamed from: fromByteArray-DHQ6RzY  reason: not valid java name */
    public long[] m8604fromByteArrayDHQ6RzY(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "source");
        return m8609fromUByteArrayS4JqeA(UByteArray.m9050constructorimpl(bArr));
    }

    @NotNull
    /* renamed from: fromInt-DHQ6RzY  reason: not valid java name */
    public long[] m8605fromIntDHQ6RzY(int i3) {
        return new long[]{ULong.m9153constructorimpl(Math.abs((long) i3))};
    }

    @NotNull
    /* renamed from: fromLong-DHQ6RzY  reason: not valid java name */
    public long[] m8606fromLongDHQ6RzY(long j2) {
        if (j2 == Long.MIN_VALUE) {
            return new long[]{0, 1};
        }
        return new long[]{ULong.m9153constructorimpl(ULong.m9153constructorimpl(Math.abs(j2)) & m8614getBaseMasksVKNKU())};
    }

    @NotNull
    /* renamed from: fromShort-DHQ6RzY  reason: not valid java name */
    public long[] m8607fromShortDHQ6RzY(short s3) {
        return new long[]{ULong.m9153constructorimpl((long) Math.abs(s3))};
    }

    @NotNull
    /* renamed from: fromUByte-ab45Ak8  reason: not valid java name */
    public long[] m8608fromUByteab45Ak8(byte b3) {
        return new long[]{ULong.m9153constructorimpl(((long) b3) & 255)};
    }

    @NotNull
    /* renamed from: fromUByteArray-S4Jqe-A  reason: not valid java name */
    public long[] m8609fromUByteArrayS4JqeA(@NotNull byte[] bArr) {
        List<ULong> emptyList;
        byte[] bArr2 = bArr;
        Intrinsics.checkNotNullParameter(bArr2, "source");
        byte[] uByteArray = UCollectionsKt.toUByteArray(CollectionsKt.flatten(CollectionsKt.reversed(CollectionsKt.chunked(UByteArray.m9048boximpl(UByteArray.m9050constructorimpl(ArraysKt.plus(UByteArray.m9049constructorimpl(8 - (UByteArray.m9056getSizeimpl(bArr) % 8)), bArr2))), 8))));
        int r12 = UByteArray.m9056getSizeimpl(uByteArray) / 8;
        int r3 = UByteArray.m9056getSizeimpl(uByteArray) % 8;
        long[] r4 = ULongArray.m9207constructorimpl(r12 + 1);
        int i3 = 0;
        if (r12 > 0) {
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                int i6 = 0;
                while (true) {
                    int i7 = i6 + 1;
                    ULongArray.m9218setk8EXiF4(r4, i4, ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r4, i4) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UByteArray.m9055getw2LRezQ(uByteArray, (i4 * 8) + i6)) & 255) << (56 - (i6 * 8)))));
                    if (i7 >= 8) {
                        break;
                    }
                    i6 = i7;
                }
                if (i5 >= r12) {
                    break;
                }
                i4 = i5;
            }
        }
        if (r3 > 0) {
            while (true) {
                int i8 = i3 + 1;
                ULongArray.m9218setk8EXiF4(r4, ULongArray.m9214getSizeimpl(r4) - 1, ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r4, ULongArray.m9214getSizeimpl(r4) - 1) | ULong.m9153constructorimpl(ULong.m9153constructorimpl(((long) UByteArray.m9055getw2LRezQ(uByteArray, (r12 * 8) + i3)) & 255) << (((r3 - 1) * 8) - (i3 * 8)))));
                if (i8 >= r3) {
                    break;
                }
                i3 = i8;
            }
        }
        int lastIndex = ArraysKt.getLastIndex(r4);
        while (true) {
            if (lastIndex >= 0) {
                if (ULongArray.m9213getsVKNKU(r4, lastIndex) != 0) {
                    emptyList = UArraysKt.m10110taker7IrZao(r4, lastIndex + 1);
                    break;
                }
                lastIndex--;
            } else {
                emptyList = CollectionsKt.emptyList();
                break;
            }
        }
        return m8587convertFrom64BitRepresentationJIhQxVY$bignum(UCollectionsKt.toULongArray(emptyList));
    }

    @NotNull
    /* renamed from: fromUInt-kOc6_GI  reason: not valid java name */
    public long[] m8610fromUIntkOc6_GI(int i3) {
        return new long[]{ULong.m9153constructorimpl(((long) i3) & 4294967295L)};
    }

    @NotNull
    /* renamed from: fromULong--GCcj4Q  reason: not valid java name */
    public long[] m8611fromULongGCcj4Q(long j2) {
        if (ULong.m9153constructorimpl(m8619getOverflowMasksVKNKU() & j2) != 0) {
            return new long[]{ULong.m9153constructorimpl(j2 & m8614getBaseMasksVKNKU()), 1};
        }
        return new long[]{j2};
    }

    @NotNull
    /* renamed from: fromUShort-jOPi9CM  reason: not valid java name */
    public long[] m8612fromUShortjOPi9CM(short s3) {
        return new long[]{ULong.m9153constructorimpl(((long) s3) & 65535)};
    }

    @NotNull
    /* renamed from: gcd-j68ebKY  reason: not valid java name */
    public long[] m8613gcdj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "first");
        Intrinsics.checkNotNullParameter(jArr2, "second");
        return (ULongArray.m9214getSizeimpl(jArr) > 150 || ULongArray.m9214getSizeimpl(jArr2) > 150) ? m8558euclideanGcdj68ebKY(jArr, jArr2) : m8556binaryGcdj68ebKY(jArr, jArr2);
    }

    /* renamed from: getBaseMask-s-VKNKU  reason: not valid java name */
    public final long m8614getBaseMasksVKNKU() {
        return baseMask;
    }

    @NotNull
    /* renamed from: getBaseMaskArray-Y2RjT0g  reason: not valid java name */
    public final long[] m8615getBaseMaskArrayY2RjT0g() {
        return baseMaskArray;
    }

    public int getBasePowerOfTwo() {
        return basePowerOfTwo;
    }

    /* renamed from: getHighMask-s-VKNKU  reason: not valid java name */
    public final long m8616getHighMasksVKNKU() {
        return highMask;
    }

    /* renamed from: getLowMask-s-VKNKU  reason: not valid java name */
    public final long m8617getLowMasksVKNKU() {
        return lowMask;
    }

    @NotNull
    /* renamed from: getONE-Y2RjT0g  reason: not valid java name */
    public long[] m8618getONEY2RjT0g() {
        return ONE;
    }

    /* renamed from: getOverflowMask-s-VKNKU  reason: not valid java name */
    public final long m8619getOverflowMasksVKNKU() {
        return overflowMask;
    }

    @NotNull
    public final ULongArray[] getPowersOf10() {
        return powersOf10;
    }

    @NotNull
    /* renamed from: getReciprocalOf3In2ToThePowerOf63-Y2RjT0g  reason: not valid java name */
    public final long[] m8620getReciprocalOf3In2ToThePowerOf63Y2RjT0g() {
        return reciprocalOf3In2ToThePowerOf63;
    }

    @NotNull
    public final SignedULongArray getSIGNED_POSITIVE_TWO() {
        return SIGNED_POSITIVE_TWO;
    }

    @NotNull
    /* renamed from: getTEN-Y2RjT0g  reason: not valid java name */
    public long[] m8621getTENY2RjT0g() {
        return TEN;
    }

    @NotNull
    /* renamed from: getTWO-Y2RjT0g  reason: not valid java name */
    public long[] m8622getTWOY2RjT0g() {
        return TWO;
    }

    public final int getWordSizeInBits() {
        return wordSizeInBits;
    }

    @NotNull
    /* renamed from: getZERO-Y2RjT0g  reason: not valid java name */
    public long[] m8623getZEROY2RjT0g() {
        return ZERO;
    }

    @NotNull
    public long[] get_emitLongArray() {
        return _emitLongArray;
    }

    @NotNull
    /* renamed from: karatsubaMultiply-j68ebKY  reason: not valid java name */
    public final long[] m8624karatsubaMultiplyj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "firstUnsigned");
        Intrinsics.checkNotNullParameter(jArr2, "secondUnsigned");
        return m8560karatsubaMultiplyWithCorrectedSizesmwLU0fg(jArr, jArr2, ULongArray.m9214getSizeimpl(jArr) - m8590countLeadingZeroWordsQwZRm1k(jArr), ULongArray.m9214getSizeimpl(jArr2) - m8590countLeadingZeroWordsQwZRm1k(jArr2));
    }

    @NotNull
    /* renamed from: max-j68ebKY  reason: not valid java name */
    public final long[] m8625maxj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "first");
        Intrinsics.checkNotNullParameter(jArr2, "second");
        return m8584compareToGR1PJdc$bignum(jArr, jArr2) > 0 ? jArr : jArr2;
    }

    @NotNull
    /* renamed from: min-j68ebKY  reason: not valid java name */
    public final long[] m8626minj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "first");
        Intrinsics.checkNotNullParameter(jArr2, "second");
        return m8584compareToGR1PJdc$bignum(jArr, jArr2) < 0 ? jArr : jArr2;
    }

    @NotNull
    public final SignedULongArray minus$bignum(@NotNull SignedULongArray signedULongArray, @NotNull SignedULongArray signedULongArray2) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        Intrinsics.checkNotNullParameter(signedULongArray2, "other");
        return signedSubtract(signedULongArray, signedULongArray2);
    }

    @NotNull
    /* renamed from: minus-j68ebKY$bignum  reason: not valid java name */
    public final long[] m8627minusj68ebKY$bignum(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        Intrinsics.checkNotNullParameter(jArr2, "other");
        return m8657subtractj68ebKY(jArr, jArr2);
    }

    @NotNull
    /* renamed from: minus-ss9iZGw$bignum  reason: not valid java name */
    public final long[] m8628minusss9iZGw$bignum(@NotNull long[] jArr, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        return m8657subtractj68ebKY(jArr, new long[]{j2});
    }

    @NotNull
    /* renamed from: multiply-dakbYXk  reason: not valid java name */
    public final long[] m8629multiplydakbYXk(long j2, long j3) {
        if (j2 == 0 || j3 == 0) {
            return new long[]{0};
        }
        long r2 = ULong.m9153constructorimpl(j2 & m8617getLowMasksVKNKU());
        long r5 = ULong.m9153constructorimpl(j2 >>> 32);
        long r7 = ULong.m9153constructorimpl(j3 & m8617getLowMasksVKNKU());
        long r9 = ULong.m9153constructorimpl(j3 >>> 32);
        long r11 = ULong.m9153constructorimpl(r2 * r7);
        long r14 = ULong.m9153constructorimpl(r11 >>> 63);
        long r112 = ULong.m9153constructorimpl(r11 & m8614getBaseMasksVKNKU());
        long r22 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(r7 * r5) + ULong.m9153constructorimpl(r2 * r9));
        long d2 = a.d(r22 >>> 31, r14);
        long d3 = a.d(ULong.m9153constructorimpl(r22 << 32) & m8614getBaseMasksVKNKU(), r112);
        return m8649removeLeadingZerosJIhQxVY(new long[]{ULong.m9153constructorimpl(d3 & m8614getBaseMasksVKNKU()), a.d(ULong.m9153constructorimpl(r5 * r9) << 1, a.d(d3 >>> 63, d2))});
    }

    @NotNull
    /* renamed from: multiply-j68ebKY  reason: not valid java name */
    public long[] m8630multiplyj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "first");
        Intrinsics.checkNotNullParameter(jArr2, "second");
        return m8561multiplyWithCorrectedSizemwLU0fg(jArr, jArr2, ULongArray.m9214getSizeimpl(jArr) - m8590countLeadingZeroWordsQwZRm1k(jArr), ULongArray.m9214getSizeimpl(jArr2) - m8590countLeadingZeroWordsQwZRm1k(jArr2));
    }

    @NotNull
    /* renamed from: normalize-GR1PJdc  reason: not valid java name */
    public final Triple<ULongArray, ULongArray, Integer> m8631normalizeGR1PJdc(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "dividend");
        Intrinsics.checkNotNullParameter(jArr2, "divisor");
        int r02 = m8636numberOfLeadingZerosInAWordVKZWuLQ(ULongArray.m9213getsVKNKU(jArr2, ULongArray.m9214getSizeimpl(jArr2) - 1));
        return new Triple<>(ULongArray.m9206boximpl(m8653shlGERUpyg$bignum(jArr, r02)), ULongArray.m9206boximpl(m8653shlGERUpyg$bignum(jArr2, r02)), Integer.valueOf(r02));
    }

    @NotNull
    /* renamed from: normalize-QwZRm1k  reason: not valid java name */
    public final Pair<ULongArray, Integer> m8632normalizeQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        int r02 = m8636numberOfLeadingZerosInAWordVKZWuLQ(ULongArray.m9213getsVKNKU(jArr, ULongArray.m9214getSizeimpl(jArr) - 1));
        return new Pair<>(ULongArray.m9206boximpl(m8653shlGERUpyg$bignum(jArr, r02)), Integer.valueOf(r02));
    }

    @NotNull
    /* renamed from: not-JIhQxVY  reason: not valid java name */
    public long[] m8633notJIhQxVY(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        int r02 = m8636numberOfLeadingZerosInAWordVKZWuLQ(ULongArray.m9213getsVKNKU(jArr, ULongArray.m9214getSizeimpl(jArr) - 1));
        long r03 = ULong.m9153constructorimpl(~ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULong.m9153constructorimpl(1 << (r02 + 1)) - ULong.m9153constructorimpl(((long) 1) & 4294967295L)) << (getBasePowerOfTwo() - r02)));
        int r8 = ULongArray.m9214getSizeimpl(jArr);
        long[] jArr2 = new long[r8];
        int i3 = 0;
        while (i3 < r8) {
            jArr2[i3] = i3 < ULongArray.m9214getSizeimpl(jArr) + -2 ? ULong.m9153constructorimpl(ULong.m9153constructorimpl(~ULongArray.m9213getsVKNKU(jArr, i3)) & INSTANCE.m8614getBaseMasksVKNKU()) : ULong.m9153constructorimpl(ULong.m9153constructorimpl(~ULongArray.m9213getsVKNKU(jArr, i3)) & r03);
            i3++;
        }
        return ULongArray.m9208constructorimpl(jArr2);
    }

    /* renamed from: numberOfDecimalDigits-QwZRm1k  reason: not valid java name */
    public long m8634numberOfDecimalDigitsQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        double ceil = Math.ceil(BigInteger.Companion.getLOG_10_OF_2() * ((double) (m8577bitLengthQwZRm1k(jArr) - 1)));
        long[] r7 = m8594divj68ebKY$bignum(jArr, m8643powGERUpyg(m8621getTENY2RjT0g(), (long) ceil));
        long j2 = 0;
        while (m8582compareGR1PJdc(r7, m8623getZEROY2RjT0g()) != 0) {
            r7 = m8594divj68ebKY$bignum(r7, m8621getTENY2RjT0g());
            j2++;
        }
        return j2 + ((long) ((int) ceil));
    }

    /* renamed from: numberOfLeadingZeroesInA64BitWord-VKZWuLQ  reason: not valid java name */
    public final int m8635numberOfLeadingZeroesInA64BitWordVKZWuLQ(long j2) {
        int i3 = 32;
        long r02 = ULong.m9153constructorimpl(j2 >>> 32);
        if (r02 != 0) {
            j2 = r02;
        } else {
            i3 = 64;
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

    /* renamed from: numberOfLeadingZerosInAWord-VKZWuLQ  reason: not valid java name */
    public int m8636numberOfLeadingZerosInAWordVKZWuLQ(long j2) {
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
    public final int m8637numberOfTrailingZerosInAWordVKZWuLQ(long j2) {
        int i3;
        long r02 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 << 32) & m8614getBaseMasksVKNKU());
        if (r02 != 0) {
            long j3 = r02;
            i3 = 31;
            j2 = j3;
        } else {
            i3 = 63;
        }
        long r4 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 << 16) & m8614getBaseMasksVKNKU());
        if (r4 != 0) {
            i3 -= 16;
            j2 = r4;
        }
        long r42 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 << 8) & m8614getBaseMasksVKNKU());
        if (r42 != 0) {
            i3 -= 8;
            j2 = r42;
        }
        long r43 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 << 4) & m8614getBaseMasksVKNKU());
        if (r43 != 0) {
            i3 -= 4;
            j2 = r43;
        }
        long r44 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 << 2) & m8614getBaseMasksVKNKU());
        if (r44 != 0) {
            i3 -= 2;
            j2 = r44;
        }
        return ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2 << 1) & m8614getBaseMasksVKNKU()) != 0 ? i3 - 2 : i3 - ((int) j2);
    }

    @NotNull
    /* renamed from: oldAdd-j68ebKY  reason: not valid java name */
    public final long[] m8638oldAddj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        long[] jArr3 = jArr;
        long[] jArr4 = jArr2;
        Intrinsics.checkNotNullParameter(jArr3, "first");
        Intrinsics.checkNotNullParameter(jArr4, "second");
        if (m8559isZeroQwZRm1k(jArr)) {
            return jArr4;
        }
        if (m8559isZeroQwZRm1k(jArr4)) {
            return jArr3;
        }
        int r3 = ULongArray.m9214getSizeimpl(jArr) - m8590countLeadingZeroWordsQwZRm1k(jArr);
        int r4 = ULongArray.m9214getSizeimpl(jArr2) - m8590countLeadingZeroWordsQwZRm1k(jArr4);
        Sextuple sextuple = r3 > r4 ? new Sextuple(Integer.valueOf(ULongArray.m9214getSizeimpl(jArr)), Integer.valueOf(ULongArray.m9214getSizeimpl(jArr2)), ULongArray.m9206boximpl(jArr), ULongArray.m9206boximpl(jArr2), Integer.valueOf(r3), Integer.valueOf(r4)) : new Sextuple(Integer.valueOf(ULongArray.m9214getSizeimpl(jArr2)), Integer.valueOf(ULongArray.m9214getSizeimpl(jArr)), ULongArray.m9206boximpl(jArr2), ULongArray.m9206boximpl(jArr), Integer.valueOf(r4), Integer.valueOf(r3));
        int intValue = ((Number) sextuple.component1()).intValue();
        ((Number) sextuple.component2()).intValue();
        long[] r2 = ((ULongArray) sextuple.component3()).m9222unboximpl();
        long[] r32 = ((ULongArray) sextuple.component4()).m9222unboximpl();
        int intValue2 = ((Number) sextuple.component5()).intValue();
        int intValue3 = ((Number) sextuple.component6()).intValue();
        int i3 = intValue2 + 1;
        long[] jArr5 = new long[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            jArr5[i4] = 0;
        }
        long[] r6 = ULongArray.m9208constructorimpl(jArr5);
        int i5 = 0;
        long j2 = 0;
        while (i5 < intValue3) {
            long r12 = ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r32, i5) + ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r2, i5) + j2));
            ULongArray.m9218setk8EXiF4(r6, i5, ULong.m9153constructorimpl(m8614getBaseMasksVKNKU() & r12));
            j2 = ULong.m9153constructorimpl(r12 >>> 63);
            i5++;
        }
        while (j2 != 0) {
            if (i5 == intValue) {
                ULongArray.m9218setk8EXiF4(r6, intValue, j2);
                return r6;
            }
            long r122 = ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r2, i5) + j2);
            ULongArray.m9218setk8EXiF4(r6, i5, ULong.m9153constructorimpl(m8614getBaseMasksVKNKU() & r122));
            j2 = ULong.m9153constructorimpl(r122 >>> 63);
            i5++;
        }
        while (i5 < intValue2) {
            ULongArray.m9218setk8EXiF4(r6, i5, ULongArray.m9213getsVKNKU(r2, i5));
            i5++;
        }
        return ULongArray.m9213getsVKNKU(r6, ULongArray.m9214getSizeimpl(r6) + -1) == 0 ? ULongArray.m9214getSizeimpl(r6) + -1 == 0 ? m8623getZEROY2RjT0g() : ULongArray.m9208constructorimpl(ArraysKt.copyOfRange(r6, 0, ULongArray.m9214getSizeimpl(r6) - 1)) : r6;
    }

    @NotNull
    /* renamed from: or-j68ebKY  reason: not valid java name */
    public long[] m8639orj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        Intrinsics.checkNotNullParameter(jArr2, "mask");
        int r02 = ULongArray.m9214getSizeimpl(jArr);
        long[] jArr3 = new long[r02];
        int i3 = 0;
        while (i3 < r02) {
            jArr3[i3] = i3 < ULongArray.m9214getSizeimpl(jArr2) ? ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i3) | ULongArray.m9213getsVKNKU(jArr2, i3)) : ULongArray.m9213getsVKNKU(jArr, i3);
            i3++;
        }
        return m8649removeLeadingZerosJIhQxVY(ULongArray.m9208constructorimpl(jArr3));
    }

    @NotNull
    /* renamed from: parseForBase-_llDaS8  reason: not valid java name */
    public long[] m8640parseForBase_llDaS8(@NotNull String str, int i3) {
        Intrinsics.checkNotNullParameter(str, "number");
        long[] r02 = m8623getZEROY2RjT0g();
        String lowerCase = str.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
        for (int i4 = 0; i4 < lowerCase.length(); i4++) {
            char charAt = lowerCase.charAt(i4);
            BigInteger63Arithmetic bigInteger63Arithmetic = INSTANCE;
            r02 = bigInteger63Arithmetic.m8642plusss9iZGw$bignum(bigInteger63Arithmetic.m8660timesss9iZGw$bignum(r02, ULong.m9153constructorimpl((long) i3)), ULong.m9153constructorimpl((long) DigitUtilKt.toDigit(charAt, i3)));
        }
        return m8649removeLeadingZerosJIhQxVY(r02);
    }

    @NotNull
    public final SignedULongArray plus$bignum(@NotNull SignedULongArray signedULongArray, @NotNull SignedULongArray signedULongArray2) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        Intrinsics.checkNotNullParameter(signedULongArray2, "other");
        return signedAdd(signedULongArray, signedULongArray2);
    }

    @NotNull
    /* renamed from: plus-j68ebKY$bignum  reason: not valid java name */
    public final long[] m8641plusj68ebKY$bignum(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        Intrinsics.checkNotNullParameter(jArr2, "other");
        return m8565addj68ebKY(jArr, jArr2);
    }

    @NotNull
    /* renamed from: plus-ss9iZGw$bignum  reason: not valid java name */
    public final long[] m8642plusss9iZGw$bignum(@NotNull long[] jArr, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        return m8565addj68ebKY(jArr, new long[]{j2});
    }

    @NotNull
    /* renamed from: pow-GERUpyg  reason: not valid java name */
    public long[] m8643powGERUpyg(@NotNull long[] jArr, long j2) {
        Intrinsics.checkNotNullParameter(jArr, TtmlNode.RUBY_BASE);
        if (j2 == 0) {
            return m8618getONEY2RjT0g();
        }
        if (j2 == 1) {
            return jArr;
        }
        if (ULongArray.m9214getSizeimpl(jArr) == 1 && ULongArray.m9213getsVKNKU(jArr, 0) == 10) {
            ULongArray[] uLongArrayArr = powersOf10;
            if (j2 < ((long) uLongArrayArr.length)) {
                return uLongArrayArr[(int) j2].m9222unboximpl();
            }
        }
        ULongArray.m9214getSizeimpl(jArr);
        m8590countLeadingZeroWordsQwZRm1k(jArr);
        long[] r4 = m8618getONEY2RjT0g();
        while (j2 > 1) {
            long j3 = (long) 2;
            if (j2 % j3 == 0) {
                jArr = m8659timesj68ebKY$bignum(jArr, jArr);
                j2 /= j3;
            } else {
                r4 = m8659timesj68ebKY$bignum(jArr, r4);
                jArr = m8659timesj68ebKY$bignum(jArr, jArr);
                j2 = (j2 - 1) / j3;
            }
        }
        return m8659timesj68ebKY$bignum(r4, jArr);
    }

    @NotNull
    /* renamed from: prependULongArray-9R_UfW4  reason: not valid java name */
    public final long[] m8644prependULongArray9R_UfW4(@NotNull long[] jArr, int i3, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "original");
        int r4 = ULongArray.m9214getSizeimpl(jArr) + i3;
        long[] jArr2 = new long[r4];
        int i4 = 0;
        while (i4 < r4) {
            jArr2[i4] = i4 < i3 ? j2 : ULongArray.m9213getsVKNKU(jArr, i4 - i3);
            i4++;
        }
        return ULongArray.m9208constructorimpl(jArr2);
    }

    @NotNull
    /* renamed from: reciprocal-QwZRm1k  reason: not valid java name */
    public Pair<ULongArray, ULongArray> m8645reciprocalQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        return m8592d1ReciprocalRecursiveWordVersionQwZRm1k(jArr);
    }

    @NotNull
    /* renamed from: reciprocalDivision-GR1PJdc$bignum  reason: not valid java name */
    public final Pair<ULongArray, ULongArray> m8646reciprocalDivisionGR1PJdc$bignum(@NotNull long[] jArr, @NotNull long[] jArr2) {
        long[] jArr3 = jArr;
        long[] jArr4 = jArr2;
        Intrinsics.checkNotNullParameter(jArr3, "first");
        Intrinsics.checkNotNullParameter(jArr4, "second");
        if (ULongArray.m9214getSizeimpl(jArr) >= ULongArray.m9214getSizeimpl(jArr2)) {
            int r3 = ULongArray.m9214getSizeimpl(jArr2) == 1 ? 1 : ULongArray.m9214getSizeimpl(jArr2) - 1;
            int r5 = (ULongArray.m9214getSizeimpl(jArr) - ULongArray.m9214getSizeimpl(jArr2)) + 1;
            int r6 = ULongArray.m9214getSizeimpl(jArr2) + r5;
            long[] jArr5 = new long[r6];
            int i3 = 0;
            while (true) {
                long j2 = 0;
                if (i3 >= r6) {
                    break;
                }
                if (i3 >= r5) {
                    j2 = ULongArray.m9213getsVKNKU(jArr4, i3 - r5);
                }
                jArr5[i3] = j2;
                i3++;
            }
            long[] r62 = m8659timesj68ebKY$bignum(jArr3, m8592d1ReciprocalRecursiveWordVersionQwZRm1k(ULongArray.m9208constructorimpl(jArr5)).getFirst().m9222unboximpl());
            if (m8583compareTo3yFGk1Y$bignum(r62, 0) == 0) {
                return new Pair<>(ULongArray.m9206boximpl(m8623getZEROY2RjT0g()), ULongArray.m9206boximpl(jArr));
            }
            if (ULongArray.m9214getSizeimpl(r62) == 1) {
                if (m8583compareTo3yFGk1Y$bignum(r62, ULong.m9153constructorimpl(m8614getBaseMasksVKNKU() - 1)) >= 0) {
                    r62 = m8641plusj68ebKY$bignum(r62, m8618getONEY2RjT0g());
                }
            } else if (Long.compareUnsigned(ULongArray.m9213getsVKNKU(r62, ULongArray.m9214getSizeimpl(r62) - ULongArray.m9214getSizeimpl(jArr2)), m8614getBaseMasksVKNKU()) >= 0) {
                int r8 = ULongArray.m9214getSizeimpl(r62);
                long[] jArr6 = new long[r8];
                int i4 = 0;
                while (i4 < r8) {
                    jArr6[i4] = i4 == ULongArray.m9214getSizeimpl(r62) - 1 ? ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r62, ULongArray.m9214getSizeimpl(r62) - 1) + 1) : 0;
                    i4++;
                }
                r62 = ULongArray.m9208constructorimpl(jArr6);
            }
            long[] r32 = ULongArray.m9208constructorimpl(ArraysKt.copyOfRange(r62, (r3 * 2) + r5, ULongArray.m9214getSizeimpl(r62)));
            return new Pair<>(ULongArray.m9206boximpl(r32), ULongArray.m9206boximpl(m8627minusj68ebKY$bignum(jArr3, m8659timesj68ebKY$bignum(r32, jArr4))));
        }
        throw new RuntimeException("Invalid division: " + ULongArray.m9214getSizeimpl(jArr) + " words / " + ULongArray.m9214getSizeimpl(jArr2) + " words");
    }

    @NotNull
    public final SignedULongArray rem$bignum(@NotNull SignedULongArray signedULongArray, @NotNull SignedULongArray signedULongArray2) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        Intrinsics.checkNotNullParameter(signedULongArray2, "other");
        return signedRemainder(signedULongArray, signedULongArray2);
    }

    @NotNull
    /* renamed from: rem-j68ebKY$bignum  reason: not valid java name */
    public final long[] m8647remj68ebKY$bignum(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        Intrinsics.checkNotNullParameter(jArr2, "other");
        return m8596divideGR1PJdc(jArr, jArr2).getSecond().m9222unboximpl();
    }

    @NotNull
    /* renamed from: rem-ss9iZGw$bignum  reason: not valid java name */
    public final long[] m8648remss9iZGw$bignum(@NotNull long[] jArr, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        return m8596divideGR1PJdc(jArr, new long[]{j2}).getSecond().m9222unboximpl();
    }

    @NotNull
    /* renamed from: removeLeadingZeros-JIhQxVY  reason: not valid java name */
    public final long[] m8649removeLeadingZerosJIhQxVY(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "bigInteger");
        int r02 = ULongArray.m9214getSizeimpl(jArr) - m8590countLeadingZeroWordsQwZRm1k(jArr);
        if (r02 == 0) {
            return m8623getZEROY2RjT0g();
        }
        if (ULongArray.m9214getSizeimpl(jArr) == r02) {
            return jArr;
        }
        if (ULongArray.m9214getSizeimpl(jArr) - r02 > 1000) {
            StringBuilder sb = new StringBuilder("RLZ original array : ");
            sb.append(ULongArray.m9214getSizeimpl(jArr));
            sb.append(" contains: ");
            System.out.println(A.a.m(sb, " zeros", (ULongArray.m9214getSizeimpl(jArr) - r02) - 1));
        }
        return ULongArray.m9208constructorimpl(ArraysKt.copyOfRange(jArr, 0, r02));
    }

    @NotNull
    /* renamed from: setBitAt-v3PXmpk  reason: not valid java name */
    public long[] m8650setBitAtv3PXmpk(@NotNull long[] jArr, long j2, boolean z2) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        long j3 = (long) 63;
        long j4 = j2 / j3;
        if (j4 > SieveCacheKt.NodeLinkMask) {
            throw new RuntimeException("Invalid bit index, too large, cannot access word (Word position > Int.MAX_VALUE");
        } else if (j4 < ((long) ULongArray.m9214getSizeimpl(jArr))) {
            long r8 = ULong.m9153constructorimpl(1 << ((int) (j2 % j3)));
            int r6 = ULongArray.m9214getSizeimpl(jArr);
            long[] jArr2 = new long[r6];
            int i3 = 0;
            while (i3 < r6) {
                jArr2[i3] = i3 == ((int) j4) ? z2 ? ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i3) | r8) : ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i3) ^ r8) : ULongArray.m9213getsVKNKU(jArr, i3);
                i3++;
            }
            return ULongArray.m9208constructorimpl(jArr2);
        } else {
            StringBuilder u3 = androidx.compose.animation.core.a.u("Invalid position, addressed word ", j4, " larger than number of words ");
            u3.append(ULongArray.m9214getSizeimpl(jArr));
            throw new IndexOutOfBoundsException(u3.toString());
        }
    }

    @NotNull
    /* renamed from: shiftLeft-GERUpyg  reason: not valid java name */
    public long[] m8651shiftLeftGERUpyg(@NotNull long[] jArr, int i3) {
        long j2;
        Intrinsics.checkNotNullParameter(jArr, "operand");
        if (m8559isZeroQwZRm1k(jArr) || i3 == 0) {
            return jArr;
        }
        if (ULongArray.m9216isEmptyimpl(jArr)) {
            return m8623getZEROY2RjT0g();
        }
        int r02 = m8590countLeadingZeroWordsQwZRm1k(jArr);
        if (ULongArray.m9214getSizeimpl(jArr) == r02) {
            return m8623getZEROY2RjT0g();
        }
        int r12 = ULongArray.m9214getSizeimpl(jArr) - r02;
        int r03 = m8636numberOfLeadingZerosInAWordVKZWuLQ(ULongArray.m9213getsVKNKU(jArr, r12 - 1));
        int basePowerOfTwo2 = i3 / getBasePowerOfTwo();
        int basePowerOfTwo3 = i3 % getBasePowerOfTwo();
        int i4 = basePowerOfTwo3 > r03 ? basePowerOfTwo2 + 1 : basePowerOfTwo2;
        int i5 = 0;
        if (basePowerOfTwo3 == 0) {
            int i6 = r12 + i4;
            long[] jArr2 = new long[i6];
            while (i5 < i6) {
                jArr2[i5] = (i5 < 0 || i5 >= basePowerOfTwo2) ? ULongArray.m9213getsVKNKU(jArr, i5 - basePowerOfTwo2) : 0;
                i5++;
            }
            return ULongArray.m9208constructorimpl(jArr2);
        }
        int i7 = r12 + i4;
        long[] jArr3 = new long[i7];
        while (i5 < i7) {
            if (i5 >= 0 && i5 < basePowerOfTwo2) {
                j2 = 0;
            } else if (i5 == basePowerOfTwo2) {
                j2 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i5 - basePowerOfTwo2) << basePowerOfTwo3) & INSTANCE.m8614getBaseMasksVKNKU());
            } else {
                int i8 = basePowerOfTwo2 + 1;
                if (i5 < r12 + basePowerOfTwo2 && i8 <= i5) {
                    int i9 = i5 - basePowerOfTwo2;
                    long r8 = ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i9) << basePowerOfTwo3);
                    BigInteger63Arithmetic bigInteger63Arithmetic = INSTANCE;
                    j2 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(r8 & bigInteger63Arithmetic.m8614getBaseMasksVKNKU()) | ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i9 - 1) >>> (bigInteger63Arithmetic.getBasePowerOfTwo() - basePowerOfTwo3)));
                } else if (i5 == i7 - 1) {
                    j2 = ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i5 - i4) >>> (INSTANCE.getBasePowerOfTwo() - basePowerOfTwo3));
                } else {
                    throw new RuntimeException(Intrinsics.stringPlus("Invalid case ", Integer.valueOf(i5)));
                }
            }
            jArr3[i5] = j2;
            i5++;
        }
        return ULongArray.m9208constructorimpl(jArr3);
    }

    @NotNull
    /* renamed from: shiftRight-GERUpyg  reason: not valid java name */
    public long[] m8652shiftRightGERUpyg(@NotNull long[] jArr, int i3) {
        long j2;
        Intrinsics.checkNotNullParameter(jArr, "operand");
        if (ULongArray.m9216isEmptyimpl(jArr) || i3 == 0) {
            return jArr;
        }
        int r3 = ULongArray.m9214getSizeimpl(jArr) - m8590countLeadingZeroWordsQwZRm1k(jArr);
        int basePowerOfTwo2 = i3 % getBasePowerOfTwo();
        int basePowerOfTwo3 = i3 / getBasePowerOfTwo();
        if (basePowerOfTwo3 >= r3) {
            return m8623getZEROY2RjT0g();
        }
        if (basePowerOfTwo2 == 0) {
            ULongArray.m9208constructorimpl(ArraysKt.copyOfRange(jArr, r3 - basePowerOfTwo3, r3));
        }
        if (r3 <= 1 || r3 - basePowerOfTwo3 != 1) {
            int i4 = r3 - basePowerOfTwo3;
            if (i4 == 0) {
                return m8623getZEROY2RjT0g();
            }
            long[] jArr2 = new long[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                if (i5 >= 0 && i5 < (r3 - 1) - basePowerOfTwo3) {
                    int i6 = i5 + basePowerOfTwo3;
                    long r6 = ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i6) >>> basePowerOfTwo2);
                    long j3 = ULongArray.m9213getsVKNKU(jArr, i6 + 1);
                    BigInteger63Arithmetic bigInteger63Arithmetic = INSTANCE;
                    j2 = ULong.m9153constructorimpl(r6 | ULong.m9153constructorimpl(ULong.m9153constructorimpl(j3 << (bigInteger63Arithmetic.getBasePowerOfTwo() - basePowerOfTwo2)) & bigInteger63Arithmetic.m8614getBaseMasksVKNKU()));
                } else if (i5 == (r3 - 1) - basePowerOfTwo3) {
                    j2 = ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i5 + basePowerOfTwo3) >>> basePowerOfTwo2);
                } else {
                    throw new RuntimeException(Intrinsics.stringPlus("Invalid case ", Integer.valueOf(i5)));
                }
                jArr2[i5] = j2;
            }
            return ULongArray.m9208constructorimpl(jArr2);
        }
        return new long[]{ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, r3 - 1) >>> basePowerOfTwo2)};
    }

    @NotNull
    public final SignedULongArray shl$bignum(@NotNull SignedULongArray signedULongArray, int i3) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        return new SignedULongArray(m8653shlGERUpyg$bignum(signedULongArray.m8673getUnsignedValueY2RjT0g(), i3), signedULongArray.getSign(), (DefaultConstructorMarker) null);
    }

    @NotNull
    /* renamed from: shl-GERUpyg$bignum  reason: not valid java name */
    public final long[] m8653shlGERUpyg$bignum(@NotNull long[] jArr, int i3) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        return m8651shiftLeftGERUpyg(jArr, i3);
    }

    @NotNull
    public final SignedULongArray shr$bignum(@NotNull SignedULongArray signedULongArray, int i3) {
        Intrinsics.checkNotNullParameter(signedULongArray, "<this>");
        return new SignedULongArray(m8654shrGERUpyg$bignum(signedULongArray.m8673getUnsignedValueY2RjT0g(), i3), signedULongArray.getSign(), (DefaultConstructorMarker) null);
    }

    @NotNull
    /* renamed from: shr-GERUpyg$bignum  reason: not valid java name */
    public final long[] m8654shrGERUpyg$bignum(@NotNull long[] jArr, int i3) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        return m8652shiftRightGERUpyg(jArr, i3);
    }

    @NotNull
    /* renamed from: sqrt-QwZRm1k  reason: not valid java name */
    public Pair<ULongArray, ULongArray> m8655sqrtQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        return m8563reqursiveSqrtQwZRm1k(jArr);
    }

    @NotNull
    /* renamed from: sqrtInt-JIhQxVY$bignum  reason: not valid java name */
    public final long[] m8656sqrtIntJIhQxVY$bignum(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        m8623getZEROY2RjT0g();
        m8623getZEROY2RjT0g();
        long[] jArr2 = jArr;
        while (true) {
            long[] r12 = m8654shrGERUpyg$bignum(m8641plusj68ebKY$bignum(jArr2, m8594divj68ebKY$bignum(jArr, jArr2)), 1);
            if (m8584compareToGR1PJdc$bignum(r12, jArr2) >= 0) {
                return jArr2;
            }
            jArr2 = r12;
        }
    }

    @NotNull
    /* renamed from: subtract-j68ebKY  reason: not valid java name */
    public long[] m8657subtractj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "first");
        Intrinsics.checkNotNullParameter(jArr2, "second");
        return m8658subtractWithStartIndexesmwLU0fg(jArr, jArr2, ULongArray.m9214getSizeimpl(jArr) - m8590countLeadingZeroWordsQwZRm1k(jArr), ULongArray.m9214getSizeimpl(jArr2) - m8590countLeadingZeroWordsQwZRm1k(jArr2));
    }

    @NotNull
    /* renamed from: subtractWithStartIndexes-mwLU0fg  reason: not valid java name */
    public final long[] m8658subtractWithStartIndexesmwLU0fg(@NotNull long[] jArr, @NotNull long[] jArr2, int i3, int i4) {
        long[] jArr3 = jArr;
        long[] jArr4 = jArr2;
        Intrinsics.checkNotNullParameter(jArr3, "first");
        Intrinsics.checkNotNullParameter(jArr4, "second");
        int r3 = m8585compareWithStartIndexesMccmUSY(jArr, jArr2, i3, i4);
        int i5 = i4 + 1;
        boolean z2 = r3 == 1;
        if (r3 == 0) {
            return m8623getZEROY2RjT0g();
        }
        if (i5 == 1 && ULongArray.m9213getsVKNKU(jArr4, 0) == 0) {
            return jArr3;
        }
        if (z2) {
            Quadruple quadruple = z2 ? new Quadruple(ULongArray.m9206boximpl(jArr), ULongArray.m9206boximpl(jArr2), Integer.valueOf(i3), Integer.valueOf(i4)) : new Quadruple(ULongArray.m9206boximpl(jArr2), ULongArray.m9206boximpl(jArr), Integer.valueOf(i4), Integer.valueOf(i3));
            long[] r12 = ((ULongArray) quadruple.component1()).m9222unboximpl();
            long[] r2 = ((ULongArray) quadruple.component2()).m9222unboximpl();
            int intValue = ((Number) quadruple.component3()).intValue();
            int intValue2 = ((Number) quadruple.component4()).intValue();
            long[] jArr5 = new long[intValue];
            for (int i6 = 0; i6 < intValue; i6++) {
                jArr5[i6] = 0;
            }
            long[] r7 = ULongArray.m9208constructorimpl(jArr5);
            int i7 = 0;
            long j2 = 0;
            while (i7 < intValue2) {
                long r11 = ULong.m9153constructorimpl(ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r12, i7) - ULongArray.m9213getsVKNKU(r2, i7)) - j2);
                ULongArray.m9218setk8EXiF4(r7, i7, ULong.m9153constructorimpl(m8614getBaseMasksVKNKU() & r11));
                j2 = ULong.m9153constructorimpl(r11 >>> 63);
                i7++;
            }
            while (j2 != 0) {
                long r22 = ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(r12, i7) - j2);
                ULongArray.m9218setk8EXiF4(r7, i7, ULong.m9153constructorimpl(m8614getBaseMasksVKNKU() & r22));
                j2 = ULong.m9153constructorimpl(r22 >>> 63);
                i7++;
            }
            while (i7 < intValue) {
                ULongArray.m9218setk8EXiF4(r7, i7, ULongArray.m9213getsVKNKU(r12, i7));
                i7++;
            }
            return (m8590countLeadingZeroWordsQwZRm1k(r7) == ULongArray.m9214getSizeimpl(r7) - 1 && ULongArray.m9213getsVKNKU(r7, 0) == 0) ? m8623getZEROY2RjT0g() : m8649removeLeadingZerosJIhQxVY(r7);
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
    /* renamed from: times-j68ebKY$bignum  reason: not valid java name */
    public final long[] m8659timesj68ebKY$bignum(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        Intrinsics.checkNotNullParameter(jArr2, "other");
        return m8630multiplyj68ebKY(jArr, jArr2);
    }

    @NotNull
    /* renamed from: times-ss9iZGw$bignum  reason: not valid java name */
    public final long[] m8660timesss9iZGw$bignum(@NotNull long[] jArr, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        return m8570baseMultiplyss9iZGw(jArr, j2);
    }

    @NotNull
    /* renamed from: to32Bit-kqpWZOw$bignum  reason: not valid java name */
    public final int[] m8661to32BitkqpWZOw$bignum(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "$receiver");
        return m8588convertTo32BitRepresentationkqpWZOw$bignum(jArr);
    }

    @NotNull
    /* renamed from: toByteArray-QwZRm1k  reason: not valid java name */
    public byte[] m8662toByteArrayQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        return m8664toUByteArraycMszsnM(jArr);
    }

    @NotNull
    /* renamed from: toString-tBf0fek  reason: not valid java name */
    public String m8663toStringtBf0fek(@NotNull long[] jArr, int i3) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
        long[] r7 = ULongArray.m9208constructorimpl(copyOf);
        long[] jArr2 = {ULong.m9153constructorimpl((long) i3)};
        StringBuilder sb = new StringBuilder();
        while (!ULongArray.m9212equalsimpl0(r7, m8623getZEROY2RjT0g())) {
            Pair<ULongArray, ULongArray> r72 = m8597divremGR1PJdc$bignum(r7, jArr2);
            if (ULongArray.m9216isEmptyimpl(r72.getSecond().m9222unboximpl())) {
                sb.append(0);
            } else {
                sb.append(UStringsKt.m10286toStringJSWoG40(ULongArray.m9213getsVKNKU(r72.getSecond().m9222unboximpl(), 0), i3));
            }
            r7 = r72.getFirst().m9222unboximpl();
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "stringBuilder.toString()");
        return StringsKt___StringsKt.reversed((CharSequence) sb2).toString();
    }

    @NotNull
    /* renamed from: toUByteArray-cMszsnM  reason: not valid java name */
    public byte[] m8664toUByteArraycMszsnM(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        long[] r7 = ULongArray.m9208constructorimpl(ArraysKt.reversedArray(m8589convertTo64BitRepresentationJIhQxVY$bignum(jArr)));
        byte[] r8 = UByteArray.m9049constructorimpl(ULongArray.m9214getSizeimpl(r7) * 8);
        int r12 = ULongArray.m9214getSizeimpl(r7);
        if (r12 > 0) {
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                ArraysKt.copyInto(ConversionUtilsKt.m8683toBigEndianUByteArrayVKZWuLQ(ULongArray.m9213getsVKNKU(r7, i3)), r8, i3 * 8, 0, 8);
                if (i4 >= r12) {
                    break;
                }
                i3 = i4;
            }
        }
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (byte b3 : r8) {
            if (z2) {
                arrayList.add(UByte.m8991boximpl(b3));
            } else if (UInt.m9074constructorimpl(b3 & 255) != 0) {
                arrayList.add(UByte.m8991boximpl(b3));
                z2 = true;
            }
        }
        return UCollectionsKt.toUByteArray(arrayList);
    }

    @NotNull
    /* renamed from: toomCook3Multiply-j68ebKY  reason: not valid java name */
    public final long[] m8665toomCook3Multiplyj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Collection collection;
        Collection collection2;
        Intrinsics.checkNotNullParameter(jArr, "firstUnchecked");
        Intrinsics.checkNotNullParameter(jArr2, "secondUnchecked");
        if (ULongArray.m9214getSizeimpl(jArr) % 3 != 0) {
            ULongArray r12 = ULongArray.m9206boximpl(jArr);
            int r5 = (((ULongArray.m9214getSizeimpl(jArr) + 2) / 3) * 3) - ULongArray.m9214getSizeimpl(jArr);
            long[] jArr3 = new long[r5];
            for (int i3 = 0; i3 < r5; i3++) {
                jArr3[i3] = 0;
            }
            collection = CollectionsKt.plus(r12, ULongArray.m9206boximpl(ULongArray.m9208constructorimpl(jArr3)));
        } else {
            collection = ULongArray.m9206boximpl(jArr);
        }
        long[] uLongArray = UCollectionsKt.toULongArray(collection);
        if (ULongArray.m9214getSizeimpl(jArr2) % 3 != 0) {
            ULongArray r13 = ULongArray.m9206boximpl(jArr2);
            int r52 = (((ULongArray.m9214getSizeimpl(jArr2) + 2) / 3) * 3) - ULongArray.m9214getSizeimpl(jArr2);
            long[] jArr4 = new long[r52];
            for (int i4 = 0; i4 < r52; i4++) {
                jArr4[i4] = 0;
            }
            collection2 = CollectionsKt.plus(r13, ULongArray.m9206boximpl(ULongArray.m9208constructorimpl(jArr4)));
        } else {
            collection2 = ULongArray.m9206boximpl(jArr2);
        }
        long[] uLongArray2 = UCollectionsKt.toULongArray(collection2);
        int r14 = ULongArray.m9214getSizeimpl(uLongArray);
        int r53 = ULongArray.m9214getSizeimpl(uLongArray2);
        Pair pair = r14 > r53 ? new Pair(ULongArray.m9206boximpl(uLongArray), ULongArray.m9206boximpl(m8600extendULongArray9R_UfW4(uLongArray2, r14 - r53, 0))) : r14 < r53 ? new Pair(ULongArray.m9206boximpl(m8600extendULongArray9R_UfW4(uLongArray, r53 - r14, 0)), ULongArray.m9206boximpl(uLongArray2)) : new Pair(ULongArray.m9206boximpl(uLongArray), ULongArray.m9206boximpl(uLongArray2));
        long[] r15 = ((ULongArray) pair.component1()).m9222unboximpl();
        long[] r2 = ((ULongArray) pair.component2()).m9222unboximpl();
        int max = (Math.max(ULongArray.m9214getSizeimpl(uLongArray), ULongArray.m9214getSizeimpl(uLongArray2)) + 2) / 3;
        SignedULongArray signedULongArray = new SignedULongArray(UCollectionsKt.toULongArray(UArraysKt.m10048sliceZRhS8yI(r15, RangesKt.until(0, max))), true, (DefaultConstructorMarker) null);
        int i5 = max * 2;
        SignedULongArray signedULongArray2 = new SignedULongArray(UCollectionsKt.toULongArray(UArraysKt.m10048sliceZRhS8yI(r15, RangesKt.until(max, i5))), true, (DefaultConstructorMarker) null);
        int i6 = max * 3;
        SignedULongArray signedULongArray3 = new SignedULongArray(UCollectionsKt.toULongArray(UArraysKt.m10048sliceZRhS8yI(r15, RangesKt.until(i5, i6))), true, (DefaultConstructorMarker) null);
        SignedULongArray signedULongArray4 = new SignedULongArray(UCollectionsKt.toULongArray(UArraysKt.m10048sliceZRhS8yI(r2, RangesKt.until(0, max))), true, (DefaultConstructorMarker) null);
        SignedULongArray signedULongArray5 = new SignedULongArray(UCollectionsKt.toULongArray(UArraysKt.m10048sliceZRhS8yI(r2, RangesKt.until(max, i5))), true, (DefaultConstructorMarker) null);
        SignedULongArray signedULongArray6 = new SignedULongArray(UCollectionsKt.toULongArray(UArraysKt.m10048sliceZRhS8yI(r2, RangesKt.until(i5, i6))), true, (DefaultConstructorMarker) null);
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
        SignedULongArray div$bignum = div$bignum(minus$bignum(times$bignum4, times$bignum2), new SignedULongArray(new long[]{3}, true, (DefaultConstructorMarker) null));
        SignedULongArray shr$bignum = shr$bignum(minus$bignum(times$bignum2, times$bignum3), 1);
        SignedULongArray minus$bignum5 = minus$bignum(times$bignum3, times$bignum);
        SignedULongArray plus$bignum6 = plus$bignum(shr$bignum(minus$bignum(minus$bignum5, div$bignum), 1), times$bignum(signedULongArray7, times$bignum5));
        SignedULongArray minus$bignum6 = minus$bignum(plus$bignum(minus$bignum5, shr$bignum), times$bignum5);
        return plus$bignum(plus$bignum(plus$bignum(plus$bignum(times$bignum, shl$bignum(minus$bignum(shr$bignum, plus$bignum6), max * 63)), shl$bignum(minus$bignum6, max * 126)), shl$bignum(plus$bignum6, max * 189)), shl$bignum(times$bignum5, max * 252)).m8673getUnsignedValueY2RjT0g();
    }

    @NotNull
    /* renamed from: toomCook3WithCorrectedSizes-j68ebKY  reason: not valid java name */
    public final long[] m8666toomCook3WithCorrectedSizesj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "firstUnchecked");
        Intrinsics.checkNotNullParameter(jArr2, "secondUnchecked");
        throw new NotImplementedError("An operation is not implemented: ");
    }

    /* renamed from: trailingZeroBits-QwZRm1k  reason: not valid java name */
    public int m8667trailingZeroBitsQwZRm1k(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "value");
        if (m8559isZeroQwZRm1k(jArr)) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        int length = jArr.length;
        int i3 = 0;
        while (i3 < length) {
            long j2 = jArr[i3];
            if (j2 != 0) {
                break;
            }
            i3 = A.a.b(j2, arrayList, i3, 1);
        }
        int size = arrayList.size();
        if (size == ULongArray.m9214getSizeimpl(jArr)) {
            return 0;
        }
        return (size * 63) + m8668trailingZeroBitsVKZWuLQ(ULongArray.m9213getsVKNKU(jArr, size));
    }

    /* renamed from: trailingZeroBits-VKZWuLQ  reason: not valid java name */
    public final int m8668trailingZeroBitsVKZWuLQ(long j2) {
        return m8637numberOfTrailingZerosInAWordVKZWuLQ(j2);
    }

    @NotNull
    /* renamed from: xor-j68ebKY  reason: not valid java name */
    public long[] m8669xorj68ebKY(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "operand");
        Intrinsics.checkNotNullParameter(jArr2, "mask");
        if (ULongArray.m9214getSizeimpl(jArr) < ULongArray.m9214getSizeimpl(jArr2)) {
            return m8669xorj68ebKY(jArr2, jArr);
        }
        int r02 = ULongArray.m9214getSizeimpl(jArr);
        long[] jArr3 = new long[r02];
        int i3 = 0;
        while (i3 < r02) {
            jArr3[i3] = i3 < ULongArray.m9214getSizeimpl(jArr2) ? ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i3) ^ ULongArray.m9213getsVKNKU(jArr2, i3)) : ULong.m9153constructorimpl(ULongArray.m9213getsVKNKU(jArr, i3));
            i3++;
        }
        return m8649removeLeadingZerosJIhQxVY(ULongArray.m9208constructorimpl(jArr3));
    }
}
