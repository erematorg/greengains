package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;

@GwtCompatible
@ElementTypesAreNonnullByDefault
final class SortedLists {

    public enum KeyAbsentBehavior {
        NEXT_LOWER {
            public int resultIndex(int i3) {
                return i3 - 1;
            }
        },
        NEXT_HIGHER {
            public int resultIndex(int i3) {
                return i3;
            }
        },
        INVERTED_INSERTION_INDEX {
            public int resultIndex(int i3) {
                return ~i3;
            }
        };

        public abstract int resultIndex(int i3);
    }

    public enum KeyPresentBehavior {
        ANY_PRESENT {
            public <E> int resultIndex(Comparator<? super E> comparator, @ParametricNullness E e3, List<? extends E> list, int i3) {
                return i3;
            }
        },
        LAST_PRESENT {
            public <E> int resultIndex(Comparator<? super E> comparator, @ParametricNullness E e3, List<? extends E> list, int i3) {
                int size = list.size() - 1;
                while (i3 < size) {
                    int i4 = ((i3 + size) + 1) >>> 1;
                    if (comparator.compare(list.get(i4), e3) > 0) {
                        size = i4 - 1;
                    } else {
                        i3 = i4;
                    }
                }
                return i3;
            }
        },
        FIRST_PRESENT {
            public <E> int resultIndex(Comparator<? super E> comparator, @ParametricNullness E e3, List<? extends E> list, int i3) {
                int i4 = 0;
                while (i4 < i3) {
                    int i5 = (i4 + i3) >>> 1;
                    if (comparator.compare(list.get(i5), e3) < 0) {
                        i4 = i5 + 1;
                    } else {
                        i3 = i5;
                    }
                }
                return i4;
            }
        },
        FIRST_AFTER {
            public <E> int resultIndex(Comparator<? super E> comparator, @ParametricNullness E e3, List<? extends E> list, int i3) {
                return KeyPresentBehavior.LAST_PRESENT.resultIndex(comparator, e3, list, i3) + 1;
            }
        },
        LAST_BEFORE {
            public <E> int resultIndex(Comparator<? super E> comparator, @ParametricNullness E e3, List<? extends E> list, int i3) {
                return KeyPresentBehavior.FIRST_PRESENT.resultIndex(comparator, e3, list, i3) - 1;
            }
        };

        public abstract <E> int resultIndex(Comparator<? super E> comparator, @ParametricNullness E e3, List<? extends E> list, int i3);
    }

    private SortedLists() {
    }

    public static <E extends Comparable> int binarySearch(List<? extends E> list, E e3, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        Preconditions.checkNotNull(e3);
        return binarySearch(list, e3, Ordering.natural(), keyPresentBehavior, keyAbsentBehavior);
    }

    public static <E, K extends Comparable> int binarySearch(List<E> list, Function<? super E, K> function, K k2, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        Preconditions.checkNotNull(k2);
        return binarySearch(list, function, k2, Ordering.natural(), keyPresentBehavior, keyAbsentBehavior);
    }

    public static <E, K> int binarySearch(List<E> list, Function<? super E, K> function, @ParametricNullness K k2, Comparator<? super K> comparator, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        return binarySearch(Lists.transform(list, function), k2, comparator, keyPresentBehavior, keyAbsentBehavior);
    }

    public static <E> int binarySearch(List<? extends E> list, @ParametricNullness E e3, Comparator<? super E> comparator, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(keyPresentBehavior);
        Preconditions.checkNotNull(keyAbsentBehavior);
        boolean z2 = list instanceof RandomAccess;
        ArrayList<? extends E> arrayList = list;
        if (!z2) {
            arrayList = Lists.newArrayList(list);
        }
        int size = arrayList.size() - 1;
        int i3 = 0;
        while (i3 <= size) {
            int i4 = (i3 + size) >>> 1;
            int compare = comparator.compare(e3, arrayList.get(i4));
            if (compare < 0) {
                size = i4 - 1;
            } else if (compare <= 0) {
                return i3 + keyPresentBehavior.resultIndex(comparator, e3, arrayList.subList(i3, size + 1), i4 - i3);
            } else {
                i3 = i4 + 1;
            }
        }
        return keyAbsentBehavior.resultIndex(i3);
    }
}
