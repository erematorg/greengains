package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ElfParser implements Closeable, Elf {
    private final int MAGIC = 1179403647;
    private final FileChannel channel;

    public ElfParser(File file) throws FileNotFoundException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.channel = new FileInputStream(file).getChannel();
    }

    private long offsetFromVma(Elf.Header header, long j2, long j3) throws IOException {
        for (long j4 = 0; j4 < j2; j4++) {
            Elf.ProgramHeader programHeader = header.getProgramHeader(j4);
            if (programHeader.type == 1) {
                long j5 = programHeader.vaddr;
                if (j5 <= j3 && j3 <= programHeader.memsz + j5) {
                    return (j3 - j5) + programHeader.offset;
                }
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    public void close() throws IOException {
        this.channel.close();
    }

    public Elf.Header parseHeader() throws IOException {
        this.channel.position(0);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (readWord(allocate, 0) == 1179403647) {
            short readByte = readByte(allocate, 4);
            boolean z2 = readByte(allocate, 5) == 2;
            if (readByte == 1) {
                return new Elf32Header(z2, this);
            }
            if (readByte == 2) {
                return new Elf64Header(z2, this);
            }
            throw new IllegalStateException("Invalid class type!");
        }
        throw new IllegalArgumentException("Invalid ELF Magic!");
    }

    public List<String> parseNeededDependencies() throws IOException {
        long j2;
        this.channel.position(0);
        ArrayList arrayList = new ArrayList();
        Elf.Header parseHeader = parseHeader();
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(parseHeader.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = (long) parseHeader.phnum;
        int i3 = 0;
        if (j3 == 65535) {
            j3 = parseHeader.getSectionHeader(0).info;
        }
        long j4 = 0;
        while (true) {
            if (j4 >= j3) {
                j2 = 0;
                break;
            }
            Elf.ProgramHeader programHeader = parseHeader.getProgramHeader(j4);
            if (programHeader.type == 2) {
                j2 = programHeader.offset;
                break;
            }
            j4++;
        }
        if (j2 == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        long j5 = 0;
        while (true) {
            Elf.DynamicStructure dynamicStructure = parseHeader.getDynamicStructure(j2, i3);
            long j6 = dynamicStructure.tag;
            if (j6 == 1) {
                arrayList2.add(Long.valueOf(dynamicStructure.val));
            } else if (j6 == 5) {
                j5 = dynamicStructure.val;
            }
            i3++;
            if (dynamicStructure.tag == 0) {
                break;
            }
        }
        if (j5 != 0) {
            long offsetFromVma = offsetFromVma(parseHeader, j3, j5);
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList.add(readString(allocate, ((Long) it.next()).longValue() + offsetFromVma));
            }
            return arrayList;
        }
        throw new IllegalStateException("String table offset not found!");
    }

    public void read(ByteBuffer byteBuffer, long j2, int i3) throws IOException {
        byteBuffer.position(0);
        byteBuffer.limit(i3);
        long j3 = 0;
        while (j3 < ((long) i3)) {
            int read = this.channel.read(byteBuffer, j2 + j3);
            if (read != -1) {
                j3 += (long) read;
            } else {
                throw new EOFException();
            }
        }
        byteBuffer.position(0);
    }

    public short readByte(ByteBuffer byteBuffer, long j2) throws IOException {
        read(byteBuffer, j2, 1);
        return (short) (byteBuffer.get() & 255);
    }

    public int readHalf(ByteBuffer byteBuffer, long j2) throws IOException {
        read(byteBuffer, j2, 2);
        return byteBuffer.getShort() & 65535;
    }

    public long readLong(ByteBuffer byteBuffer, long j2) throws IOException {
        read(byteBuffer, j2, 8);
        return byteBuffer.getLong();
    }

    public String readString(ByteBuffer byteBuffer, long j2) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j3 = 1 + j2;
            short readByte = readByte(byteBuffer, j2);
            if (readByte == 0) {
                return sb.toString();
            }
            sb.append((char) readByte);
            j2 = j3;
        }
    }

    public long readWord(ByteBuffer byteBuffer, long j2) throws IOException {
        read(byteBuffer, j2, 4);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }
}
