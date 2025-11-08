package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class Splitter {
    /* access modifiers changed from: private */
    public final int limit;
    /* access modifiers changed from: private */
    public final boolean omitEmptyStrings;
    private final Strategy strategy;
    /* access modifiers changed from: private */
    public final CharMatcher trimmer;

    public static final class MapSplitter {
        private static final String INVALID_ENTRY_MESSAGE = "Chunk [%s] is not a valid entry";
        private final Splitter entrySplitter;
        private final Splitter outerSplitter;

        public Map<String, String> split(CharSequence charSequence) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String next : this.outerSplitter.split(charSequence)) {
                Iterator access$000 = this.entrySplitter.splittingIterator(next);
                Preconditions.checkArgument(access$000.hasNext(), INVALID_ENTRY_MESSAGE, (Object) next);
                String str = (String) access$000.next();
                Preconditions.checkArgument(!linkedHashMap.containsKey(str), "Duplicate key [%s] found.", (Object) str);
                Preconditions.checkArgument(access$000.hasNext(), INVALID_ENTRY_MESSAGE, (Object) next);
                linkedHashMap.put(str, (String) access$000.next());
                Preconditions.checkArgument(!access$000.hasNext(), INVALID_ENTRY_MESSAGE, (Object) next);
            }
            return Collections.unmodifiableMap(linkedHashMap);
        }

        private MapSplitter(Splitter splitter, Splitter splitter2) {
            this.outerSplitter = splitter;
            this.entrySplitter = (Splitter) Preconditions.checkNotNull(splitter2);
        }
    }

    public static abstract class SplittingIterator extends AbstractIterator<String> {
        int limit;
        int offset = 0;
        final boolean omitEmptyStrings;
        final CharSequence toSplit;
        final CharMatcher trimmer;

        public SplittingIterator(Splitter splitter, CharSequence charSequence) {
            this.trimmer = splitter.trimmer;
            this.omitEmptyStrings = splitter.omitEmptyStrings;
            this.limit = splitter.limit;
            this.toSplit = charSequence;
        }

        public abstract int separatorEnd(int i3);

        public abstract int separatorStart(int i3);

        @CheckForNull
        public String computeNext() {
            int i3;
            int i4 = this.offset;
            while (true) {
                int i5 = this.offset;
                if (i5 == -1) {
                    return (String) endOfData();
                }
                int separatorStart = separatorStart(i5);
                if (separatorStart == -1) {
                    separatorStart = this.toSplit.length();
                    this.offset = -1;
                } else {
                    this.offset = separatorEnd(separatorStart);
                }
                int i6 = this.offset;
                if (i6 == i4) {
                    int i7 = i6 + 1;
                    this.offset = i7;
                    if (i7 > this.toSplit.length()) {
                        this.offset = -1;
                    }
                } else {
                    while (i4 < separatorStart && this.trimmer.matches(this.toSplit.charAt(i4))) {
                        i4++;
                    }
                    while (i3 > i4 && this.trimmer.matches(this.toSplit.charAt(i3 - 1))) {
                        separatorStart = i3 - 1;
                    }
                    if (!this.omitEmptyStrings || i4 != i3) {
                        int i8 = this.limit;
                    } else {
                        i4 = this.offset;
                    }
                }
            }
            int i82 = this.limit;
            if (i82 == 1) {
                i3 = this.toSplit.length();
                this.offset = -1;
                while (i3 > i4 && this.trimmer.matches(this.toSplit.charAt(i3 - 1))) {
                    i3--;
                }
            } else {
                this.limit = i82 - 1;
            }
            return this.toSplit.subSequence(i4, i3).toString();
        }
    }

    public interface Strategy {
        Iterator<String> iterator(Splitter splitter, CharSequence charSequence);
    }

    private Splitter(Strategy strategy2) {
        this(strategy2, false, CharMatcher.none(), Integer.MAX_VALUE);
    }

    public static Splitter fixedLength(final int i3) {
        Preconditions.checkArgument(i3 > 0, "The length may not be less than 1");
        return new Splitter(new Strategy() {
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) {
                    public int separatorEnd(int i3) {
                        return i3;
                    }

                    public int separatorStart(int i3) {
                        int i4 = i3 + i3;
                        if (i4 < this.toSplit.length()) {
                            return i4;
                        }
                        return -1;
                    }
                };
            }
        });
    }

    public static Splitter on(char c3) {
        return on(CharMatcher.is(c3));
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static Splitter onPattern(String str) {
        return onPatternInternal(Platform.compilePattern(str));
    }

    public static Splitter onPatternInternal(final CommonPattern commonPattern) {
        Preconditions.checkArgument(!commonPattern.matcher("").matches(), "The pattern may not match the empty string: %s", (Object) commonPattern);
        return new Splitter(new Strategy() {
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                final CommonMatcher matcher = CommonPattern.this.matcher(charSequence);
                return new SplittingIterator(this, splitter, charSequence) {
                    public int separatorEnd(int i3) {
                        return matcher.end();
                    }

                    public int separatorStart(int i3) {
                        if (matcher.find(i3)) {
                            return matcher.start();
                        }
                        return -1;
                    }
                };
            }
        });
    }

    /* access modifiers changed from: private */
    public Iterator<String> splittingIterator(CharSequence charSequence) {
        return this.strategy.iterator(this, charSequence);
    }

    public Splitter limit(int i3) {
        Preconditions.checkArgument(i3 > 0, "must be greater than zero: %s", i3);
        return new Splitter(this.strategy, this.omitEmptyStrings, this.trimmer, i3);
    }

    public Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer, this.limit);
    }

    public Iterable<String> split(final CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return Splitter.this.splittingIterator(charSequence);
            }

            public String toString() {
                Joiner on = Joiner.on(", ");
                StringBuilder sb = new StringBuilder();
                sb.append(AbstractJsonLexerKt.BEGIN_LIST);
                StringBuilder appendTo = on.appendTo(sb, (Iterable<? extends Object>) this);
                appendTo.append(AbstractJsonLexerKt.END_LIST);
                return appendTo.toString();
            }
        };
    }

    public List<String> splitToList(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        Iterator<String> splittingIterator = splittingIterator(charSequence);
        ArrayList arrayList = new ArrayList();
        while (splittingIterator.hasNext()) {
            arrayList.add(splittingIterator.next());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Splitter trimResults() {
        return trimResults(CharMatcher.whitespace());
    }

    public MapSplitter withKeyValueSeparator(String str) {
        return withKeyValueSeparator(on(str));
    }

    private Splitter(Strategy strategy2, boolean z2, CharMatcher charMatcher, int i3) {
        this.strategy = strategy2;
        this.omitEmptyStrings = z2;
        this.trimmer = charMatcher;
        this.limit = i3;
    }

    public static Splitter on(final CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return new Splitter(new Strategy() {
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) {
                    public int separatorEnd(int i3) {
                        return i3 + 1;
                    }

                    public int separatorStart(int i3) {
                        return CharMatcher.this.indexIn(this.toSplit, i3);
                    }
                };
            }
        });
    }

    public Splitter trimResults(CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return new Splitter(this.strategy, this.omitEmptyStrings, charMatcher, this.limit);
    }

    public MapSplitter withKeyValueSeparator(char c3) {
        return withKeyValueSeparator(on(c3));
    }

    public MapSplitter withKeyValueSeparator(Splitter splitter) {
        return new MapSplitter(splitter);
    }

    public static Splitter on(final String str) {
        Preconditions.checkArgument(str.length() != 0, "The separator may not be the empty string.");
        if (str.length() == 1) {
            return on(str.charAt(0));
        }
        return new Splitter(new Strategy() {
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) {
                    public int separatorEnd(int i3) {
                        return str.length() + i3;
                    }

                    public int separatorStart(int i3) {
                        int length = str.length();
                        int length2 = this.toSplit.length() - length;
                        while (i3 <= length2) {
                            int i4 = 0;
                            while (i4 < length) {
                                if (this.toSplit.charAt(i4 + i3) != str.charAt(i4)) {
                                    i3++;
                                } else {
                                    i4++;
                                }
                            }
                            return i3;
                        }
                        return -1;
                    }
                };
            }
        });
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static Splitter on(Pattern pattern) {
        return onPatternInternal(new JdkPattern(pattern));
    }
}
