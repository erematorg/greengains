package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4;

import A.a;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.core.r;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.GaplessInfoHolder;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.ApicFrame;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.CommentFrame;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.Id3Frame;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.InternalFrame;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.TextInformationFrame;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.mp4.MdtaMetadataEntry;
import com.google.common.net.HttpHeaders;

final class MetadataUtil {
    private static final int PICTURE_TYPE_FRONT_COVER = 3;
    private static final int SHORT_TYPE_ALBUM = 6384738;
    private static final int SHORT_TYPE_ARTIST = 4280916;
    private static final int SHORT_TYPE_COMMENT = 6516084;
    private static final int SHORT_TYPE_COMPOSER_1 = 6516589;
    private static final int SHORT_TYPE_COMPOSER_2 = 7828084;
    private static final int SHORT_TYPE_ENCODER = 7630703;
    private static final int SHORT_TYPE_GENRE = 6776174;
    private static final int SHORT_TYPE_LYRICS = 7108978;
    private static final int SHORT_TYPE_NAME_1 = 7233901;
    private static final int SHORT_TYPE_NAME_2 = 7631467;
    private static final int SHORT_TYPE_YEAR = 6578553;
    @VisibleForTesting
    static final String[] STANDARD_GENRES = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", HttpHeaders.TRAILER, "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Afro-Punk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop", "Abstract", "Art Rock", "Baroque", "Bhangra", "Big beat", "Breakbeat", "Chillout", "Downtempo", "Dub", "EBM", "Eclectic", "Electro", "Electroclash", "Emo", "Experimental", "Garage", "Global", "IDM", "Illbient", "Industro-Goth", "Jam Band", "Krautrock", "Leftfield", "Lounge", "Math Rock", "New Romantic", "Nu-Breakz", "Post-Punk", "Post-Rock", "Psytrance", "Shoegaze", "Space Rock", "Trop Rock", "World Music", "Neoclassical", "Audiobook", "Audio theatre", "Neue Deutsche Welle", "Podcast", "Indie-Rock", "G-Funk", "Dubstep", "Garage Rock", "Psybient"};
    private static final String TAG = "MetadataUtil";
    private static final int TYPE_ALBUM_ARTIST = 1631670868;
    private static final int TYPE_COMPILATION = 1668311404;
    private static final int TYPE_COVER_ART = 1668249202;
    private static final int TYPE_DISK_NUMBER = 1684632427;
    private static final int TYPE_GAPLESS_ALBUM = 1885823344;
    private static final int TYPE_GENRE = 1735291493;
    private static final int TYPE_GROUPING = 6779504;
    private static final int TYPE_INTERNAL = 757935405;
    private static final int TYPE_RATING = 1920233063;
    private static final int TYPE_SORT_ALBUM = 1936679276;
    private static final int TYPE_SORT_ALBUM_ARTIST = 1936679265;
    private static final int TYPE_SORT_ARTIST = 1936679282;
    private static final int TYPE_SORT_COMPOSER = 1936679791;
    private static final int TYPE_SORT_TRACK_NAME = 1936682605;
    private static final int TYPE_TEMPO = 1953329263;
    private static final int TYPE_TOP_BYTE_COPYRIGHT = 169;
    private static final int TYPE_TOP_BYTE_REPLACEMENT = 253;
    private static final int TYPE_TRACK_NUMBER = 1953655662;
    private static final int TYPE_TV_SHOW = 1953919848;
    private static final int TYPE_TV_SORT_SHOW = 1936683886;

    private MetadataUtil() {
    }

    @Nullable
    private static CommentFrame parseCommentAttribute(int i3, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            String readNullTerminatedString = parsableByteArray.readNullTerminatedString(readInt - 16);
            return new CommentFrame(C.LANGUAGE_UNDETERMINED, readNullTerminatedString, readNullTerminatedString);
        }
        Log.w(TAG, "Failed to parse comment attribute: " + Atom.getAtomTypeString(i3));
        return null;
    }

    @Nullable
    private static ApicFrame parseCoverArt(ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            int parseFullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
            String str = parseFullAtomFlags == 13 ? "image/jpeg" : parseFullAtomFlags == 14 ? "image/png" : null;
            if (str == null) {
                r.a(parseFullAtomFlags, "Unrecognized cover art flags: ", TAG);
                return null;
            }
            parsableByteArray.skipBytes(4);
            int i3 = readInt - 16;
            byte[] bArr = new byte[i3];
            parsableByteArray.readBytes(bArr, 0, i3);
            return new ApicFrame(str, (String) null, 3, bArr);
        }
        Log.w(TAG, "Failed to parse cover art attribute");
        return null;
    }

    @Nullable
    public static Metadata.Entry parseIlstElement(ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt() + parsableByteArray.getPosition();
        int readInt2 = parsableByteArray.readInt();
        int i3 = (readInt2 >> 24) & 255;
        if (i3 == 169 || i3 == TYPE_TOP_BYTE_REPLACEMENT) {
            int i4 = 16777215 & readInt2;
            if (i4 == SHORT_TYPE_COMMENT) {
                CommentFrame parseCommentAttribute = parseCommentAttribute(readInt2, parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseCommentAttribute;
            } else if (i4 == SHORT_TYPE_NAME_1 || i4 == SHORT_TYPE_NAME_2) {
                TextInformationFrame parseTextAttribute = parseTextAttribute(readInt2, "TIT2", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute;
            } else if (i4 == SHORT_TYPE_COMPOSER_1 || i4 == SHORT_TYPE_COMPOSER_2) {
                TextInformationFrame parseTextAttribute2 = parseTextAttribute(readInt2, "TCOM", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute2;
            } else if (i4 == SHORT_TYPE_YEAR) {
                TextInformationFrame parseTextAttribute3 = parseTextAttribute(readInt2, "TDRC", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute3;
            } else if (i4 == SHORT_TYPE_ARTIST) {
                TextInformationFrame parseTextAttribute4 = parseTextAttribute(readInt2, "TPE1", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute4;
            } else if (i4 == SHORT_TYPE_ENCODER) {
                TextInformationFrame parseTextAttribute5 = parseTextAttribute(readInt2, "TSSE", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute5;
            } else if (i4 == SHORT_TYPE_ALBUM) {
                TextInformationFrame parseTextAttribute6 = parseTextAttribute(readInt2, "TALB", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute6;
            } else if (i4 == SHORT_TYPE_LYRICS) {
                TextInformationFrame parseTextAttribute7 = parseTextAttribute(readInt2, "USLT", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute7;
            } else if (i4 == SHORT_TYPE_GENRE) {
                TextInformationFrame parseTextAttribute8 = parseTextAttribute(readInt2, "TCON", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute8;
            } else if (i4 == TYPE_GROUPING) {
                TextInformationFrame parseTextAttribute9 = parseTextAttribute(readInt2, "TIT1", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute9;
            }
        } else if (readInt2 == TYPE_GENRE) {
            try {
                return parseStandardGenreAttribute(parsableByteArray);
            } finally {
                parsableByteArray.setPosition(readInt);
            }
        } else if (readInt2 == TYPE_DISK_NUMBER) {
            TextInformationFrame parseIndexAndCountAttribute = parseIndexAndCountAttribute(readInt2, "TPOS", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseIndexAndCountAttribute;
        } else if (readInt2 == TYPE_TRACK_NUMBER) {
            TextInformationFrame parseIndexAndCountAttribute2 = parseIndexAndCountAttribute(readInt2, "TRCK", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseIndexAndCountAttribute2;
        } else if (readInt2 == TYPE_TEMPO) {
            Id3Frame parseUint8Attribute = parseUint8Attribute(readInt2, "TBPM", parsableByteArray, true, false);
            parsableByteArray.setPosition(readInt);
            return parseUint8Attribute;
        } else if (readInt2 == TYPE_COMPILATION) {
            Id3Frame parseUint8Attribute2 = parseUint8Attribute(readInt2, "TCMP", parsableByteArray, true, true);
            parsableByteArray.setPosition(readInt);
            return parseUint8Attribute2;
        } else if (readInt2 == TYPE_COVER_ART) {
            ApicFrame parseCoverArt = parseCoverArt(parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseCoverArt;
        } else if (readInt2 == TYPE_ALBUM_ARTIST) {
            TextInformationFrame parseTextAttribute10 = parseTextAttribute(readInt2, "TPE2", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute10;
        } else if (readInt2 == TYPE_SORT_TRACK_NAME) {
            TextInformationFrame parseTextAttribute11 = parseTextAttribute(readInt2, "TSOT", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute11;
        } else if (readInt2 == TYPE_SORT_ALBUM) {
            TextInformationFrame parseTextAttribute12 = parseTextAttribute(readInt2, "TSO2", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute12;
        } else if (readInt2 == TYPE_SORT_ARTIST) {
            TextInformationFrame parseTextAttribute13 = parseTextAttribute(readInt2, "TSOA", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute13;
        } else if (readInt2 == TYPE_SORT_ALBUM_ARTIST) {
            TextInformationFrame parseTextAttribute14 = parseTextAttribute(readInt2, "TSOP", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute14;
        } else if (readInt2 == TYPE_SORT_COMPOSER) {
            TextInformationFrame parseTextAttribute15 = parseTextAttribute(readInt2, "TSOC", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute15;
        } else if (readInt2 == TYPE_RATING) {
            Id3Frame parseUint8Attribute3 = parseUint8Attribute(readInt2, "ITUNESADVISORY", parsableByteArray, false, false);
            parsableByteArray.setPosition(readInt);
            return parseUint8Attribute3;
        } else if (readInt2 == TYPE_GAPLESS_ALBUM) {
            Id3Frame parseUint8Attribute4 = parseUint8Attribute(readInt2, "ITUNESGAPLESS", parsableByteArray, false, true);
            parsableByteArray.setPosition(readInt);
            return parseUint8Attribute4;
        } else if (readInt2 == TYPE_TV_SORT_SHOW) {
            TextInformationFrame parseTextAttribute16 = parseTextAttribute(readInt2, "TVSHOWSORT", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute16;
        } else if (readInt2 == TYPE_TV_SHOW) {
            TextInformationFrame parseTextAttribute17 = parseTextAttribute(readInt2, "TVSHOW", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute17;
        } else if (readInt2 == TYPE_INTERNAL) {
            Id3Frame parseInternalAttribute = parseInternalAttribute(parsableByteArray, readInt);
            parsableByteArray.setPosition(readInt);
            return parseInternalAttribute;
        }
        Log.d(TAG, "Skipped unknown metadata entry: " + Atom.getAtomTypeString(readInt2));
        parsableByteArray.setPosition(readInt);
        return null;
    }

    @Nullable
    private static TextInformationFrame parseIndexAndCountAttribute(int i3, String str, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385 && readInt >= 22) {
            parsableByteArray.skipBytes(10);
            int readUnsignedShort = parsableByteArray.readUnsignedShort();
            if (readUnsignedShort > 0) {
                String k2 = a.k("", readUnsignedShort);
                int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
                if (readUnsignedShort2 > 0) {
                    k2 = com.appsamurai.storyly.exoplayer2.common.a.b(readUnsignedShort2, k2, "/");
                }
                return new TextInformationFrame(str, (String) null, k2);
            }
        }
        Log.w(TAG, "Failed to parse index/count attribute: " + Atom.getAtomTypeString(i3));
        return null;
    }

    @Nullable
    private static Id3Frame parseInternalAttribute(ParsableByteArray parsableByteArray, int i3) {
        String str = null;
        String str2 = null;
        int i4 = -1;
        int i5 = -1;
        while (parsableByteArray.getPosition() < i3) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            if (readInt2 == 1835360622) {
                str = parsableByteArray.readNullTerminatedString(readInt - 12);
            } else if (readInt2 == 1851878757) {
                str2 = parsableByteArray.readNullTerminatedString(readInt - 12);
            } else {
                if (readInt2 == 1684108385) {
                    i4 = position;
                    i5 = readInt;
                }
                parsableByteArray.skipBytes(readInt - 12);
            }
        }
        if (str == null || str2 == null || i4 == -1) {
            return null;
        }
        parsableByteArray.setPosition(i4);
        parsableByteArray.skipBytes(16);
        return new InternalFrame(str, str2, parsableByteArray.readNullTerminatedString(i5 - 16));
    }

    @Nullable
    public static MdtaMetadataEntry parseMdtaMetadataEntryFromIlst(ParsableByteArray parsableByteArray, int i3, String str) {
        while (true) {
            int position = parsableByteArray.getPosition();
            if (position >= i3) {
                return null;
            }
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1684108385) {
                int readInt2 = parsableByteArray.readInt();
                int readInt3 = parsableByteArray.readInt();
                int i4 = readInt - 16;
                byte[] bArr = new byte[i4];
                parsableByteArray.readBytes(bArr, 0, i4);
                return new MdtaMetadataEntry(str, bArr, readInt3, readInt2);
            }
            parsableByteArray.setPosition(position + readInt);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001c  */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.TextInformationFrame parseStandardGenreAttribute(com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r3) {
        /*
            int r3 = parseUint8AttributeValue(r3)
            r0 = 0
            if (r3 <= 0) goto L_0x0011
            java.lang.String[] r1 = STANDARD_GENRES
            int r2 = r1.length
            if (r3 > r2) goto L_0x0011
            int r3 = r3 + -1
            r3 = r1[r3]
            goto L_0x0012
        L_0x0011:
            r3 = r0
        L_0x0012:
            if (r3 == 0) goto L_0x001c
            com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.TextInformationFrame r1 = new com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.TextInformationFrame
            java.lang.String r2 = "TCON"
            r1.<init>(r2, r0, r3)
            return r1
        L_0x001c:
            java.lang.String r3 = "MetadataUtil"
            java.lang.String r1 = "Failed to parse standard genre code"
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r3, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.MetadataUtil.parseStandardGenreAttribute(com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray):com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.TextInformationFrame");
    }

    @Nullable
    private static TextInformationFrame parseTextAttribute(int i3, String str, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            return new TextInformationFrame(str, (String) null, parsableByteArray.readNullTerminatedString(readInt - 16));
        }
        Log.w(TAG, "Failed to parse text attribute: " + Atom.getAtomTypeString(i3));
        return null;
    }

    @Nullable
    private static Id3Frame parseUint8Attribute(int i3, String str, ParsableByteArray parsableByteArray, boolean z2, boolean z3) {
        int parseUint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        if (z3) {
            parseUint8AttributeValue = Math.min(1, parseUint8AttributeValue);
        }
        if (parseUint8AttributeValue >= 0) {
            return z2 ? new TextInformationFrame(str, (String) null, Integer.toString(parseUint8AttributeValue)) : new CommentFrame(C.LANGUAGE_UNDETERMINED, str, Integer.toString(parseUint8AttributeValue));
        }
        Log.w(TAG, "Failed to parse uint8 attribute: " + Atom.getAtomTypeString(i3));
        return null;
    }

    private static int parseUint8AttributeValue(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            return parsableByteArray.readUnsignedByte();
        }
        Log.w(TAG, "Failed to parse uint8 attribute value");
        return -1;
    }

    public static void setFormatGaplessInfo(int i3, GaplessInfoHolder gaplessInfoHolder, Format.Builder builder) {
        if (i3 == 1 && gaplessInfoHolder.hasGaplessInfo()) {
            builder.setEncoderDelay(gaplessInfoHolder.encoderDelay).setEncoderPadding(gaplessInfoHolder.encoderPadding);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
        if (r6 != null) goto L_0x003c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003f A[LOOP:1: B:17:0x003d->B:18:0x003f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setFormatMetadata(int r5, @androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.common.metadata.Metadata r6, @androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.common.metadata.Metadata r7, com.appsamurai.storyly.exoplayer2.common.Format.Builder r8, com.appsamurai.storyly.exoplayer2.common.metadata.Metadata... r9) {
        /*
            com.appsamurai.storyly.exoplayer2.common.metadata.Metadata r0 = new com.appsamurai.storyly.exoplayer2.common.metadata.Metadata
            r1 = 0
            com.appsamurai.storyly.exoplayer2.common.metadata.Metadata$Entry[] r2 = new com.appsamurai.storyly.exoplayer2.common.metadata.Metadata.Entry[r1]
            r0.<init>((com.appsamurai.storyly.exoplayer2.common.metadata.Metadata.Entry[]) r2)
            r2 = 1
            if (r5 != r2) goto L_0x000e
            if (r6 == 0) goto L_0x003b
            goto L_0x003c
        L_0x000e:
            r6 = 2
            if (r5 != r6) goto L_0x003b
            if (r7 == 0) goto L_0x003b
            r5 = r1
        L_0x0014:
            int r6 = r7.length()
            if (r5 >= r6) goto L_0x003b
            com.appsamurai.storyly.exoplayer2.common.metadata.Metadata$Entry r6 = r7.get(r5)
            boolean r3 = r6 instanceof com.appsamurai.storyly.exoplayer2.extractor.metadata.mp4.MdtaMetadataEntry
            if (r3 == 0) goto L_0x0039
            com.appsamurai.storyly.exoplayer2.extractor.metadata.mp4.MdtaMetadataEntry r6 = (com.appsamurai.storyly.exoplayer2.extractor.metadata.mp4.MdtaMetadataEntry) r6
            java.lang.String r3 = r6.key
            java.lang.String r4 = "com.android.capture.fps"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0039
            com.appsamurai.storyly.exoplayer2.common.metadata.Metadata r5 = new com.appsamurai.storyly.exoplayer2.common.metadata.Metadata
            com.appsamurai.storyly.exoplayer2.common.metadata.Metadata$Entry[] r7 = new com.appsamurai.storyly.exoplayer2.common.metadata.Metadata.Entry[r2]
            r7[r1] = r6
            r5.<init>((com.appsamurai.storyly.exoplayer2.common.metadata.Metadata.Entry[]) r7)
            r6 = r5
            goto L_0x003c
        L_0x0039:
            int r5 = r5 + r2
            goto L_0x0014
        L_0x003b:
            r6 = r0
        L_0x003c:
            int r5 = r9.length
        L_0x003d:
            if (r1 >= r5) goto L_0x0047
            r7 = r9[r1]
            com.appsamurai.storyly.exoplayer2.common.metadata.Metadata r6 = r6.copyWithAppendedEntriesFrom(r7)
            int r1 = r1 + r2
            goto L_0x003d
        L_0x0047:
            int r5 = r6.length()
            if (r5 <= 0) goto L_0x0050
            r8.setMetadata(r6)
        L_0x0050:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.MetadataUtil.setFormatMetadata(int, com.appsamurai.storyly.exoplayer2.common.metadata.Metadata, com.appsamurai.storyly.exoplayer2.common.metadata.Metadata, com.appsamurai.storyly.exoplayer2.common.Format$Builder, com.appsamurai.storyly.exoplayer2.common.metadata.Metadata[]):void");
    }
}
