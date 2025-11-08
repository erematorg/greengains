package com.google.crypto.tink.shaded.protobuf;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class UnmodifiableLazyStringList extends AbstractList<String> implements LazyStringList, RandomAccess {
    /* access modifiers changed from: private */
    public final LazyStringList list;

    public UnmodifiableLazyStringList(LazyStringList lazyStringList) {
        this.list = lazyStringList;
    }

    public void add(ByteString byteString) {
        throw new UnsupportedOperationException();
    }

    public boolean addAllByteArray(Collection<byte[]> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean addAllByteString(Collection<? extends ByteString> collection) {
        throw new UnsupportedOperationException();
    }

    public List<byte[]> asByteArrayList() {
        return Collections.unmodifiableList(this.list.asByteArrayList());
    }

    public List<ByteString> asByteStringList() {
        return Collections.unmodifiableList(this.list.asByteStringList());
    }

    public byte[] getByteArray(int i3) {
        return this.list.getByteArray(i3);
    }

    public ByteString getByteString(int i3) {
        return this.list.getByteString(i3);
    }

    public Object getRaw(int i3) {
        return this.list.getRaw(i3);
    }

    public List<?> getUnderlyingElements() {
        return this.list.getUnderlyingElements();
    }

    public LazyStringList getUnmodifiableView() {
        return this;
    }

    public Iterator<String> iterator() {
        return new Iterator<String>() {
            Iterator<String> iter;

            {
                this.iter = UnmodifiableLazyStringList.this.list.iterator();
            }

            public boolean hasNext() {
                return this.iter.hasNext();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public String next() {
                return this.iter.next();
            }
        };
    }

    public ListIterator<String> listIterator(int i3) {
        return new ListIterator<String>(i3) {
            ListIterator<String> iter;
            final /* synthetic */ int val$index;

            {
                this.val$index = r2;
                this.iter = UnmodifiableLazyStringList.this.list.listIterator(r2);
            }

            public boolean hasNext() {
                return this.iter.hasNext();
            }

            public boolean hasPrevious() {
                return this.iter.hasPrevious();
            }

            public int nextIndex() {
                return this.iter.nextIndex();
            }

            public int previousIndex() {
                return this.iter.previousIndex();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public void add(String str) {
                throw new UnsupportedOperationException();
            }

            public String next() {
                return this.iter.next();
            }

            public String previous() {
                return this.iter.previous();
            }

            public void set(String str) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public void mergeFrom(LazyStringList lazyStringList) {
        throw new UnsupportedOperationException();
    }

    public void set(int i3, ByteString byteString) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.list.size();
    }

    public void add(byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    public String get(int i3) {
        return (String) this.list.get(i3);
    }

    public void set(int i3, byte[] bArr) {
        throw new UnsupportedOperationException();
    }
}
