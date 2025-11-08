package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Program64Header extends Elf.ProgramHeader {
    public Program64Header(ElfParser elfParser, Elf.Header header, long j2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = (j2 * ((long) header.phentsize)) + header.phoff;
        this.type = elfParser.readWord(allocate, j3);
        this.offset = elfParser.readLong(allocate, 8 + j3);
        this.vaddr = elfParser.readLong(allocate, 16 + j3);
        this.memsz = elfParser.readLong(allocate, j3 + 40);
    }
}
