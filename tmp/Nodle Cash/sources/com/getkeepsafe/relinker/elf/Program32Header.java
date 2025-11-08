package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Program32Header extends Elf.ProgramHeader {
    public Program32Header(ElfParser elfParser, Elf.Header header, long j2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = (j2 * ((long) header.phentsize)) + header.phoff;
        this.type = elfParser.readWord(allocate, j3);
        this.offset = elfParser.readWord(allocate, 4 + j3);
        this.vaddr = elfParser.readWord(allocate, 8 + j3);
        this.memsz = elfParser.readWord(allocate, j3 + 20);
    }
}
