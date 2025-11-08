package com.appsamurai.storyly.exoplayer2.extractor.text.dvb;

import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.text.SimpleSubtitleDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import java.util.List;

public final class DvbDecoder extends SimpleSubtitleDecoder {
    private final DvbParser parser;

    public DvbDecoder(List<byte[]> list) {
        super("DvbDecoder");
        ParsableByteArray parsableByteArray = new ParsableByteArray(list.get(0));
        this.parser = new DvbParser(parsableByteArray.readUnsignedShort(), parsableByteArray.readUnsignedShort());
    }

    public Subtitle decode(byte[] bArr, int i3, boolean z2) {
        if (z2) {
            this.parser.reset();
        }
        return new DvbSubtitle(this.parser.decode(bArr, i3));
    }
}
