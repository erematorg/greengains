package com.google.common.base;

import androidx.constraintlayout.core.state.b;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;
import java.util.BitSet;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public abstract class CharMatcher implements Predicate<Character> {
    private static final int DISTINCT_CHARS = 65536;

    public static final class And extends CharMatcher {
        final CharMatcher first;
        final CharMatcher second;

        public And(CharMatcher charMatcher, CharMatcher charMatcher2) {
            this.first = (CharMatcher) Preconditions.checkNotNull(charMatcher);
            this.second = (CharMatcher) Preconditions.checkNotNull(charMatcher2);
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c3) {
            return this.first.matches(c3) && this.second.matches(c3);
        }

        @GwtIncompatible
        @J2ktIncompatible
        public void setBits(BitSet bitSet) {
            BitSet bitSet2 = new BitSet();
            this.first.setBits(bitSet2);
            BitSet bitSet3 = new BitSet();
            this.second.setBits(bitSet3);
            bitSet2.and(bitSet3);
            bitSet.or(bitSet2);
        }

        public String toString() {
            return "CharMatcher.and(" + this.first + ", " + this.second + ")";
        }
    }

    public static final class Any extends NamedFastMatcher {
        static final Any INSTANCE = new Any();

        private Any() {
            super("CharMatcher.any()");
        }

        public CharMatcher and(CharMatcher charMatcher) {
            return (CharMatcher) Preconditions.checkNotNull(charMatcher);
        }

        public String collapseFrom(CharSequence charSequence, char c3) {
            return charSequence.length() == 0 ? "" : String.valueOf(c3);
        }

        public int countIn(CharSequence charSequence) {
            return charSequence.length();
        }

        public int indexIn(CharSequence charSequence) {
            return charSequence.length() == 0 ? -1 : 0;
        }

        public int lastIndexIn(CharSequence charSequence) {
            return charSequence.length() - 1;
        }

        public boolean matches(char c3) {
            return true;
        }

        public boolean matchesAllOf(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return true;
        }

        public boolean matchesNoneOf(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        public CharMatcher negate() {
            return CharMatcher.none();
        }

        public CharMatcher or(CharMatcher charMatcher) {
            Preconditions.checkNotNull(charMatcher);
            return this;
        }

        public String removeFrom(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return "";
        }

        public String replaceFrom(CharSequence charSequence, char c3) {
            char[] cArr = new char[charSequence.length()];
            Arrays.fill(cArr, c3);
            return new String(cArr);
        }

        public String trimFrom(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return "";
        }

        public int indexIn(CharSequence charSequence, int i3) {
            int length = charSequence.length();
            Preconditions.checkPositionIndex(i3, length);
            if (i3 == length) {
                return -1;
            }
            return i3;
        }

        public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
            StringBuilder sb = new StringBuilder(charSequence2.length() * charSequence.length());
            for (int i3 = 0; i3 < charSequence.length(); i3++) {
                sb.append(charSequence2);
            }
            return sb.toString();
        }
    }

    public static final class AnyOf extends CharMatcher {
        private final char[] chars;

        public AnyOf(CharSequence charSequence) {
            char[] charArray = charSequence.toString().toCharArray();
            this.chars = charArray;
            Arrays.sort(charArray);
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c3) {
            return Arrays.binarySearch(this.chars, c3) >= 0;
        }

        @GwtIncompatible
        @J2ktIncompatible
        public void setBits(BitSet bitSet) {
            for (char c3 : this.chars) {
                bitSet.set(c3);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("CharMatcher.anyOf(\"");
            for (char access$100 : this.chars) {
                sb.append(CharMatcher.showCharacter(access$100));
            }
            sb.append("\")");
            return sb.toString();
        }
    }

    public static final class Ascii extends NamedFastMatcher {
        static final Ascii INSTANCE = new Ascii();

        public Ascii() {
            super("CharMatcher.ascii()");
        }

        public boolean matches(char c3) {
            return c3 <= 127;
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static final class BitSetMatcher extends NamedFastMatcher {
        private final BitSet table;

        public boolean matches(char c3) {
            return this.table.get(c3);
        }

        public void setBits(BitSet bitSet) {
            bitSet.or(this.table);
        }

        private BitSetMatcher(BitSet bitSet, String str) {
            super(str);
            this.table = bitSet.length() + 64 < bitSet.size() ? (BitSet) bitSet.clone() : bitSet;
        }
    }

    public static final class BreakingWhitespace extends CharMatcher {
        static final CharMatcher INSTANCE = new BreakingWhitespace();

        private BreakingWhitespace() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c3) {
            if (!(c3 == ' ' || c3 == 133 || c3 == 5760)) {
                if (c3 == 8199) {
                    return false;
                }
                if (!(c3 == 8287 || c3 == 12288 || c3 == 8232 || c3 == 8233)) {
                    switch (c3) {
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                            break;
                        default:
                            return c3 >= 8192 && c3 <= 8202;
                    }
                }
            }
            return true;
        }

        public String toString() {
            return "CharMatcher.breakingWhitespace()";
        }
    }

    public static final class Digit extends RangesMatcher {
        static final Digit INSTANCE = new Digit();
        private static final String ZEROES = "0٠۰߀०০੦૦୦௦౦೦൦෦๐໐༠၀႐០᠐᥆᧐᪀᪐᭐᮰᱀᱐꘠꣐꤀꧐꧰꩐꯰０";

        private Digit() {
            super("CharMatcher.digit()", zeroes(), nines());
        }

        private static char[] nines() {
            char[] cArr = new char[37];
            for (int i3 = 0; i3 < 37; i3++) {
                cArr[i3] = (char) (ZEROES.charAt(i3) + 9);
            }
            return cArr;
        }

        private static char[] zeroes() {
            return ZEROES.toCharArray();
        }
    }

    public static abstract class FastMatcher extends CharMatcher {
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public CharMatcher negate() {
            return new NegatedFastMatcher(this);
        }

        public final CharMatcher precomputed() {
            return this;
        }
    }

    public static final class ForPredicate extends CharMatcher {
        private final Predicate<? super Character> predicate;

        public ForPredicate(Predicate<? super Character> predicate2) {
            this.predicate = (Predicate) Preconditions.checkNotNull(predicate2);
        }

        public boolean matches(char c3) {
            return this.predicate.apply(Character.valueOf(c3));
        }

        public String toString() {
            return "CharMatcher.forPredicate(" + this.predicate + ")";
        }

        public boolean apply(Character ch2) {
            return this.predicate.apply(Preconditions.checkNotNull(ch2));
        }
    }

    public static final class InRange extends FastMatcher {
        private final char endInclusive;
        private final char startInclusive;

        public InRange(char c3, char c4) {
            Preconditions.checkArgument(c4 >= c3);
            this.startInclusive = c3;
            this.endInclusive = c4;
        }

        public boolean matches(char c3) {
            return this.startInclusive <= c3 && c3 <= this.endInclusive;
        }

        @GwtIncompatible
        @J2ktIncompatible
        public void setBits(BitSet bitSet) {
            bitSet.set(this.startInclusive, this.endInclusive + 1);
        }

        public String toString() {
            return "CharMatcher.inRange('" + CharMatcher.showCharacter(this.startInclusive) + "', '" + CharMatcher.showCharacter(this.endInclusive) + "')";
        }
    }

    public static final class Invisible extends RangesMatcher {
        static final Invisible INSTANCE = new Invisible();
        private static final String RANGE_ENDS = "  ­؅؜۝܏࢑࣢ ᠎‏ ⁤⁯　﻿￻";
        private static final String RANGE_STARTS = "\u0000­؀؜۝܏࢐࣢ ᠎   ⁦　?﻿￹";

        private Invisible() {
            super("CharMatcher.invisible()", RANGE_STARTS.toCharArray(), RANGE_ENDS.toCharArray());
        }
    }

    public static final class Is extends FastMatcher {
        private final char match;

        public Is(char c3) {
            this.match = c3;
        }

        public CharMatcher and(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? this : CharMatcher.none();
        }

        public boolean matches(char c3) {
            return c3 == this.match;
        }

        public CharMatcher negate() {
            return CharMatcher.isNot(this.match);
        }

        public CharMatcher or(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? charMatcher : CharMatcher.super.or(charMatcher);
        }

        public String replaceFrom(CharSequence charSequence, char c3) {
            return charSequence.toString().replace(this.match, c3);
        }

        @GwtIncompatible
        @J2ktIncompatible
        public void setBits(BitSet bitSet) {
            bitSet.set(this.match);
        }

        public String toString() {
            return "CharMatcher.is('" + CharMatcher.showCharacter(this.match) + "')";
        }
    }

    public static final class IsEither extends FastMatcher {
        private final char match1;
        private final char match2;

        public IsEither(char c3, char c4) {
            this.match1 = c3;
            this.match2 = c4;
        }

        public boolean matches(char c3) {
            return c3 == this.match1 || c3 == this.match2;
        }

        @GwtIncompatible
        @J2ktIncompatible
        public void setBits(BitSet bitSet) {
            bitSet.set(this.match1);
            bitSet.set(this.match2);
        }

        public String toString() {
            return "CharMatcher.anyOf(\"" + CharMatcher.showCharacter(this.match1) + CharMatcher.showCharacter(this.match2) + "\")";
        }
    }

    public static final class IsNot extends FastMatcher {
        private final char match;

        public IsNot(char c3) {
            this.match = c3;
        }

        public CharMatcher and(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? CharMatcher.super.and(charMatcher) : charMatcher;
        }

        public boolean matches(char c3) {
            return c3 != this.match;
        }

        public CharMatcher negate() {
            return CharMatcher.is(this.match);
        }

        public CharMatcher or(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? CharMatcher.any() : this;
        }

        @GwtIncompatible
        @J2ktIncompatible
        public void setBits(BitSet bitSet) {
            bitSet.set(0, this.match);
            bitSet.set(this.match + 1, 65536);
        }

        public String toString() {
            return "CharMatcher.isNot('" + CharMatcher.showCharacter(this.match) + "')";
        }
    }

    public static final class JavaDigit extends CharMatcher {
        static final JavaDigit INSTANCE = new JavaDigit();

        private JavaDigit() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c3) {
            return Character.isDigit(c3);
        }

        public String toString() {
            return "CharMatcher.javaDigit()";
        }
    }

    public static final class JavaIsoControl extends NamedFastMatcher {
        static final JavaIsoControl INSTANCE = new JavaIsoControl();

        private JavaIsoControl() {
            super("CharMatcher.javaIsoControl()");
        }

        public boolean matches(char c3) {
            return c3 <= 31 || (c3 >= 127 && c3 <= 159);
        }
    }

    public static final class JavaLetter extends CharMatcher {
        static final JavaLetter INSTANCE = new JavaLetter();

        private JavaLetter() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c3) {
            return Character.isLetter(c3);
        }

        public String toString() {
            return "CharMatcher.javaLetter()";
        }
    }

    public static final class JavaLetterOrDigit extends CharMatcher {
        static final JavaLetterOrDigit INSTANCE = new JavaLetterOrDigit();

        private JavaLetterOrDigit() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c3) {
            return Character.isLetterOrDigit(c3);
        }

        public String toString() {
            return "CharMatcher.javaLetterOrDigit()";
        }
    }

    public static final class JavaLowerCase extends CharMatcher {
        static final JavaLowerCase INSTANCE = new JavaLowerCase();

        private JavaLowerCase() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c3) {
            return Character.isLowerCase(c3);
        }

        public String toString() {
            return "CharMatcher.javaLowerCase()";
        }
    }

    public static final class JavaUpperCase extends CharMatcher {
        static final JavaUpperCase INSTANCE = new JavaUpperCase();

        private JavaUpperCase() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c3) {
            return Character.isUpperCase(c3);
        }

        public String toString() {
            return "CharMatcher.javaUpperCase()";
        }
    }

    public static abstract class NamedFastMatcher extends FastMatcher {
        private final String description;

        public NamedFastMatcher(String str) {
            this.description = (String) Preconditions.checkNotNull(str);
        }

        public final String toString() {
            return this.description;
        }
    }

    public static class Negated extends CharMatcher {
        final CharMatcher original;

        public Negated(CharMatcher charMatcher) {
            this.original = (CharMatcher) Preconditions.checkNotNull(charMatcher);
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public int countIn(CharSequence charSequence) {
            return charSequence.length() - this.original.countIn(charSequence);
        }

        public boolean matches(char c3) {
            return !this.original.matches(c3);
        }

        public boolean matchesAllOf(CharSequence charSequence) {
            return this.original.matchesNoneOf(charSequence);
        }

        public boolean matchesNoneOf(CharSequence charSequence) {
            return this.original.matchesAllOf(charSequence);
        }

        public CharMatcher negate() {
            return this.original;
        }

        @GwtIncompatible
        @J2ktIncompatible
        public void setBits(BitSet bitSet) {
            BitSet bitSet2 = new BitSet();
            this.original.setBits(bitSet2);
            bitSet2.flip(0, 65536);
            bitSet.or(bitSet2);
        }

        public String toString() {
            return this.original + ".negate()";
        }
    }

    public static class NegatedFastMatcher extends Negated {
        public NegatedFastMatcher(CharMatcher charMatcher) {
            super(charMatcher);
        }

        public final CharMatcher precomputed() {
            return this;
        }
    }

    public static final class None extends NamedFastMatcher {
        static final None INSTANCE = new None();

        private None() {
            super("CharMatcher.none()");
        }

        public CharMatcher and(CharMatcher charMatcher) {
            Preconditions.checkNotNull(charMatcher);
            return this;
        }

        public String collapseFrom(CharSequence charSequence, char c3) {
            return charSequence.toString();
        }

        public int countIn(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return 0;
        }

        public int indexIn(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return -1;
        }

        public int lastIndexIn(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return -1;
        }

        public boolean matches(char c3) {
            return false;
        }

        public boolean matchesAllOf(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        public boolean matchesNoneOf(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return true;
        }

        public CharMatcher negate() {
            return CharMatcher.any();
        }

        public CharMatcher or(CharMatcher charMatcher) {
            return (CharMatcher) Preconditions.checkNotNull(charMatcher);
        }

        public String removeFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        public String replaceFrom(CharSequence charSequence, char c3) {
            return charSequence.toString();
        }

        public String trimFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        public String trimLeadingFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        public String trimTrailingFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        public int indexIn(CharSequence charSequence, int i3) {
            Preconditions.checkPositionIndex(i3, charSequence.length());
            return -1;
        }

        public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
            Preconditions.checkNotNull(charSequence2);
            return charSequence.toString();
        }
    }

    public static final class Or extends CharMatcher {
        final CharMatcher first;
        final CharMatcher second;

        public Or(CharMatcher charMatcher, CharMatcher charMatcher2) {
            this.first = (CharMatcher) Preconditions.checkNotNull(charMatcher);
            this.second = (CharMatcher) Preconditions.checkNotNull(charMatcher2);
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c3) {
            return this.first.matches(c3) || this.second.matches(c3);
        }

        @GwtIncompatible
        @J2ktIncompatible
        public void setBits(BitSet bitSet) {
            this.first.setBits(bitSet);
            this.second.setBits(bitSet);
        }

        public String toString() {
            return "CharMatcher.or(" + this.first + ", " + this.second + ")";
        }
    }

    public static class RangesMatcher extends CharMatcher {
        private final String description;
        private final char[] rangeEnds;
        private final char[] rangeStarts;

        public RangesMatcher(String str, char[] cArr, char[] cArr2) {
            this.description = str;
            this.rangeStarts = cArr;
            this.rangeEnds = cArr2;
            Preconditions.checkArgument(cArr.length == cArr2.length);
            int i3 = 0;
            while (i3 < cArr.length) {
                Preconditions.checkArgument(cArr[i3] <= cArr2[i3]);
                int i4 = i3 + 1;
                if (i4 < cArr.length) {
                    Preconditions.checkArgument(cArr2[i3] < cArr[i4]);
                }
                i3 = i4;
            }
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c3) {
            int binarySearch = Arrays.binarySearch(this.rangeStarts, c3);
            if (binarySearch >= 0) {
                return true;
            }
            int i3 = (~binarySearch) - 1;
            return i3 >= 0 && c3 <= this.rangeEnds[i3];
        }

        public String toString() {
            return this.description;
        }
    }

    public static final class SingleWidth extends RangesMatcher {
        static final SingleWidth INSTANCE = new SingleWidth();

        private SingleWidth() {
            super("CharMatcher.singleWidth()", "\u0000־א׳؀ݐ฀Ḁ℀ﭐﹰ｡".toCharArray(), "ӹ־ת״ۿݿ๿₯℺﷿﻿ￜ".toCharArray());
        }
    }

    @VisibleForTesting
    public static final class Whitespace extends NamedFastMatcher {
        static final Whitespace INSTANCE = new Whitespace();
        static final int MULTIPLIER = 1682554634;
        static final int SHIFT = Integer.numberOfLeadingZeros(31);
        static final String TABLE = " 　\r   　 \u000b　   　 \t     \f 　 　　 \n 　";

        public Whitespace() {
            super("CharMatcher.whitespace()");
        }

        public boolean matches(char c3) {
            return TABLE.charAt((MULTIPLIER * c3) >>> SHIFT) == c3;
        }

        @GwtIncompatible
        @J2ktIncompatible
        public void setBits(BitSet bitSet) {
            for (int i3 = 0; i3 < 32; i3++) {
                bitSet.set(TABLE.charAt(i3));
            }
        }
    }

    public static CharMatcher any() {
        return Any.INSTANCE;
    }

    public static CharMatcher anyOf(CharSequence charSequence) {
        int length = charSequence.length();
        return length != 0 ? length != 1 ? length != 2 ? new AnyOf(charSequence) : isEither(charSequence.charAt(0), charSequence.charAt(1)) : is(charSequence.charAt(0)) : none();
    }

    public static CharMatcher ascii() {
        return Ascii.INSTANCE;
    }

    public static CharMatcher breakingWhitespace() {
        return BreakingWhitespace.INSTANCE;
    }

    @Deprecated
    public static CharMatcher digit() {
        return Digit.INSTANCE;
    }

    private String finishCollapseFrom(CharSequence charSequence, int i3, int i4, char c3, StringBuilder sb, boolean z2) {
        while (i3 < i4) {
            char charAt = charSequence.charAt(i3);
            if (!matches(charAt)) {
                sb.append(charAt);
                z2 = false;
            } else if (!z2) {
                sb.append(c3);
                z2 = true;
            }
            i3++;
        }
        return sb.toString();
    }

    public static CharMatcher forPredicate(Predicate<? super Character> predicate) {
        return predicate instanceof CharMatcher ? (CharMatcher) predicate : new ForPredicate(predicate);
    }

    public static CharMatcher inRange(char c3, char c4) {
        return new InRange(c3, c4);
    }

    @Deprecated
    public static CharMatcher invisible() {
        return Invisible.INSTANCE;
    }

    public static CharMatcher is(char c3) {
        return new Is(c3);
    }

    private static IsEither isEither(char c3, char c4) {
        return new IsEither(c3, c4);
    }

    public static CharMatcher isNot(char c3) {
        return new IsNot(c3);
    }

    @GwtIncompatible
    @J2ktIncompatible
    private static boolean isSmall(int i3, int i4) {
        return i3 <= 1023 && i4 > i3 * 64;
    }

    @Deprecated
    public static CharMatcher javaDigit() {
        return JavaDigit.INSTANCE;
    }

    public static CharMatcher javaIsoControl() {
        return JavaIsoControl.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaLetter() {
        return JavaLetter.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaLetterOrDigit() {
        return JavaLetterOrDigit.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaLowerCase() {
        return JavaLowerCase.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaUpperCase() {
        return JavaUpperCase.INSTANCE;
    }

    public static CharMatcher none() {
        return None.INSTANCE;
    }

    public static CharMatcher noneOf(CharSequence charSequence) {
        return anyOf(charSequence).negate();
    }

    @GwtIncompatible
    @J2ktIncompatible
    private static CharMatcher precomputedPositive(int i3, BitSet bitSet, String str) {
        if (i3 == 0) {
            return none();
        }
        if (i3 == 1) {
            return is((char) bitSet.nextSetBit(0));
        }
        if (i3 != 2) {
            return isSmall(i3, bitSet.length()) ? SmallCharMatcher.from(bitSet, str) : new BitSetMatcher(bitSet, str);
        }
        char nextSetBit = (char) bitSet.nextSetBit(0);
        return isEither(nextSetBit, (char) bitSet.nextSetBit(nextSetBit + 1));
    }

    /* access modifiers changed from: private */
    public static String showCharacter(char c3) {
        char[] cArr = {AbstractJsonLexerKt.STRING_ESC, AbstractJsonLexerKt.UNICODE_ESC, 0, 0, 0, 0};
        for (int i3 = 0; i3 < 4; i3++) {
            cArr[5 - i3] = "0123456789ABCDEF".charAt(c3 & 15);
            c3 = (char) (c3 >> 4);
        }
        return String.copyValueOf(cArr);
    }

    @Deprecated
    public static CharMatcher singleWidth() {
        return SingleWidth.INSTANCE;
    }

    public static CharMatcher whitespace() {
        return Whitespace.INSTANCE;
    }

    public CharMatcher and(CharMatcher charMatcher) {
        return new And(this, charMatcher);
    }

    public String collapseFrom(CharSequence charSequence, char c3) {
        int length = charSequence.length();
        int i3 = 0;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (matches(charAt)) {
                if (charAt != c3 || (i3 != length - 1 && matches(charSequence.charAt(i3 + 1)))) {
                    StringBuilder sb = new StringBuilder(length);
                    sb.append(charSequence, 0, i3);
                    sb.append(c3);
                    return finishCollapseFrom(charSequence, i3 + 1, length, c3, sb, true);
                }
                i3++;
            }
            i3++;
        }
        return charSequence.toString();
    }

    public int countIn(CharSequence charSequence) {
        int i3 = 0;
        for (int i4 = 0; i4 < charSequence.length(); i4++) {
            if (matches(charSequence.charAt(i4))) {
                i3++;
            }
        }
        return i3;
    }

    public int indexIn(CharSequence charSequence) {
        return indexIn(charSequence, 0);
    }

    public int lastIndexIn(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (matches(charSequence.charAt(length))) {
                return length;
            }
        }
        return -1;
    }

    public abstract boolean matches(char c3);

    public boolean matchesAllOf(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesAnyOf(CharSequence charSequence) {
        return !matchesNoneOf(charSequence);
    }

    public boolean matchesNoneOf(CharSequence charSequence) {
        return indexIn(charSequence) == -1;
    }

    public CharMatcher negate() {
        return new Negated(this);
    }

    public CharMatcher or(CharMatcher charMatcher) {
        return new Or(this, charMatcher);
    }

    public CharMatcher precomputed() {
        return Platform.precomputeCharMatcher(this);
    }

    @GwtIncompatible
    @J2ktIncompatible
    public CharMatcher precomputedInternal() {
        BitSet bitSet = new BitSet();
        setBits(bitSet);
        int cardinality = bitSet.cardinality();
        if (cardinality * 2 <= 65536) {
            return precomputedPositive(cardinality, bitSet, toString());
        }
        bitSet.flip(0, 65536);
        int i3 = 65536 - cardinality;
        final String charMatcher = toString();
        return new NegatedFastMatcher(this, precomputedPositive(i3, bitSet, charMatcher.endsWith(".negate()") ? b.y(charMatcher, 9, 0) : charMatcher.concat(".negate()"))) {
            public String toString() {
                return charMatcher;
            }
        };
    }

    public String removeFrom(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        int indexIn = indexIn(charSequence2);
        if (indexIn == -1) {
            return charSequence2;
        }
        char[] charArray = charSequence2.toCharArray();
        int i3 = 1;
        while (true) {
            indexIn++;
            while (indexIn != charArray.length) {
                if (matches(charArray[indexIn])) {
                    i3++;
                } else {
                    charArray[indexIn - i3] = charArray[indexIn];
                    indexIn++;
                }
            }
            return new String(charArray, 0, indexIn - i3);
        }
    }

    public String replaceFrom(CharSequence charSequence, char c3) {
        String charSequence2 = charSequence.toString();
        int indexIn = indexIn(charSequence2);
        if (indexIn == -1) {
            return charSequence2;
        }
        char[] charArray = charSequence2.toCharArray();
        charArray[indexIn] = c3;
        while (true) {
            indexIn++;
            if (indexIn >= charArray.length) {
                return new String(charArray);
            }
            if (matches(charArray[indexIn])) {
                charArray[indexIn] = c3;
            }
        }
    }

    public String retainFrom(CharSequence charSequence) {
        return negate().removeFrom(charSequence);
    }

    @GwtIncompatible
    @J2ktIncompatible
    public void setBits(BitSet bitSet) {
        for (int i3 = 65535; i3 >= 0; i3--) {
            if (matches((char) i3)) {
                bitSet.set(i3);
            }
        }
    }

    public String toString() {
        return super.toString();
    }

    public String trimAndCollapseFrom(CharSequence charSequence, char c3) {
        int length = charSequence.length();
        int i3 = length - 1;
        int i4 = 0;
        while (i4 < length && matches(charSequence.charAt(i4))) {
            i4++;
        }
        int i5 = i3;
        while (i5 > i4 && matches(charSequence.charAt(i5))) {
            i5--;
        }
        if (i4 == 0 && i5 == i3) {
            return collapseFrom(charSequence, c3);
        }
        int i6 = i5 + 1;
        return finishCollapseFrom(charSequence, i4, i6, c3, new StringBuilder(i6 - i4), false);
    }

    public String trimFrom(CharSequence charSequence) {
        int length = charSequence.length();
        int i3 = 0;
        while (i3 < length && matches(charSequence.charAt(i3))) {
            i3++;
        }
        int i4 = length - 1;
        while (i4 > i3 && matches(charSequence.charAt(i4))) {
            i4--;
        }
        return charSequence.subSequence(i3, i4 + 1).toString();
    }

    public String trimLeadingFrom(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i3 = 0; i3 < length; i3++) {
            if (!matches(charSequence.charAt(i3))) {
                return charSequence.subSequence(i3, length).toString();
            }
        }
        return "";
    }

    public String trimTrailingFrom(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return charSequence.subSequence(0, length + 1).toString();
            }
        }
        return "";
    }

    @Deprecated
    public boolean apply(Character ch2) {
        return matches(ch2.charValue());
    }

    public int indexIn(CharSequence charSequence, int i3) {
        int length = charSequence.length();
        Preconditions.checkPositionIndex(i3, length);
        while (i3 < length) {
            if (matches(charSequence.charAt(i3))) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
        int length = charSequence2.length();
        if (length == 0) {
            return removeFrom(charSequence);
        }
        int i3 = 0;
        if (length == 1) {
            return replaceFrom(charSequence, charSequence2.charAt(0));
        }
        String charSequence3 = charSequence.toString();
        int indexIn = indexIn(charSequence3);
        if (indexIn == -1) {
            return charSequence3;
        }
        int length2 = charSequence3.length();
        StringBuilder sb = new StringBuilder(b.b(length2, 3, 2, 16));
        do {
            sb.append(charSequence3, i3, indexIn);
            sb.append(charSequence2);
            i3 = indexIn + 1;
            indexIn = indexIn(charSequence3, i3);
        } while (indexIn != -1);
        sb.append(charSequence3, i3, length2);
        return sb.toString();
    }
}
