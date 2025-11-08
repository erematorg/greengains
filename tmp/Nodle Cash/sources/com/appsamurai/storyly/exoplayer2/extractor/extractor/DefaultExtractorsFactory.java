package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import android.net.Uri;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.FileTypes;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.amr.AmrExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.avi.AviExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.flac.FlacExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.flv.FlvExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.jpeg.JpegExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Mp3Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Mp4Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ogg.OggExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.Ac3Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.Ac4Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.AdtsExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.PsExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.wav.WavExtractor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public final class DefaultExtractorsFactory implements ExtractorsFactory {
    private static final int[] DEFAULT_EXTRACTOR_ORDER = {5, 4, 12, 8, 3, 10, 9, 11, 6, 2, 0, 1, 7, 16, 15, 14};
    private static final ExtensionLoader FLAC_EXTENSION_LOADER = new ExtensionLoader(new a(3));
    private static final ExtensionLoader MIDI_EXTENSION_LOADER = new ExtensionLoader(new a(4));
    private int adtsFlags;
    private int amrFlags;
    private boolean constantBitrateSeekingAlwaysEnabled;
    private boolean constantBitrateSeekingEnabled;
    private int flacFlags;
    private int fragmentedMp4Flags;
    private int matroskaFlags;
    private int mp3Flags;
    private int mp4Flags;
    private int tsFlags;
    private int tsMode = 1;
    private int tsTimestampSearchBytes = TsExtractor.DEFAULT_TIMESTAMP_SEARCH_BYTES;

    public static final class ExtensionLoader {
        private final ConstructorSupplier constructorSupplier;
        private final AtomicBoolean extensionLoaded = new AtomicBoolean(false);
        @GuardedBy("extensionLoaded")
        @Nullable
        private Constructor<? extends Extractor> extractorConstructor;

        public interface ConstructorSupplier {
            @Nullable
            Constructor<? extends Extractor> getConstructor() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException;
        }

        public ExtensionLoader(ConstructorSupplier constructorSupplier2) {
            this.constructorSupplier = constructorSupplier2;
        }

        @Nullable
        private Constructor<? extends Extractor> maybeLoadExtractorConstructor() {
            synchronized (this.extensionLoaded) {
                if (this.extensionLoaded.get()) {
                    Constructor<? extends Extractor> constructor = this.extractorConstructor;
                    return constructor;
                }
                try {
                    Constructor<? extends Extractor> constructor2 = this.constructorSupplier.getConstructor();
                    return constructor2;
                } catch (ClassNotFoundException unused) {
                    this.extensionLoaded.set(true);
                    return this.extractorConstructor;
                } catch (Exception e3) {
                    throw new RuntimeException("Error instantiating extension", e3);
                }
            }
        }

        @Nullable
        public Extractor getExtractor(Object... objArr) {
            Constructor<? extends Extractor> maybeLoadExtractorConstructor = maybeLoadExtractorConstructor();
            if (maybeLoadExtractorConstructor == null) {
                return null;
            }
            try {
                return (Extractor) maybeLoadExtractorConstructor.newInstance(objArr);
            } catch (Exception e3) {
                throw new IllegalStateException("Unexpected error creating extractor", e3);
            }
        }
    }

    private void addExtractorsForFileType(int i3, List<Extractor> list) {
        int i4 = 2;
        switch (i3) {
            case 0:
                list.add(new Ac3Extractor());
                return;
            case 1:
                list.add(new Ac4Extractor());
                return;
            case 2:
                boolean z2 = this.adtsFlags | this.constantBitrateSeekingEnabled;
                if (!this.constantBitrateSeekingAlwaysEnabled) {
                    i4 = 0;
                }
                list.add(new AdtsExtractor(z2 | i4 ? 1 : 0));
                return;
            case 3:
                boolean z3 = this.amrFlags | this.constantBitrateSeekingEnabled;
                if (!this.constantBitrateSeekingAlwaysEnabled) {
                    i4 = 0;
                }
                list.add(new AmrExtractor(z3 | i4 ? 1 : 0));
                return;
            case 4:
                Extractor extractor = FLAC_EXTENSION_LOADER.getExtractor(Integer.valueOf(this.flacFlags));
                if (extractor != null) {
                    list.add(extractor);
                    return;
                } else {
                    list.add(new FlacExtractor(this.flacFlags));
                    return;
                }
            case 5:
                list.add(new FlvExtractor());
                return;
            case 6:
                list.add(new MatroskaExtractor(this.matroskaFlags));
                return;
            case 7:
                boolean z4 = this.mp3Flags | this.constantBitrateSeekingEnabled;
                if (!this.constantBitrateSeekingAlwaysEnabled) {
                    i4 = 0;
                }
                list.add(new Mp3Extractor(z4 | i4 ? 1 : 0));
                return;
            case 8:
                list.add(new FragmentedMp4Extractor(this.fragmentedMp4Flags));
                list.add(new Mp4Extractor(this.mp4Flags));
                return;
            case 9:
                list.add(new OggExtractor());
                return;
            case 10:
                list.add(new PsExtractor());
                return;
            case 11:
                list.add(new TsExtractor(this.tsMode, this.tsFlags, this.tsTimestampSearchBytes));
                return;
            case 12:
                list.add(new WavExtractor());
                return;
            case 14:
                list.add(new JpegExtractor());
                return;
            case 15:
                Extractor extractor2 = MIDI_EXTENSION_LOADER.getExtractor(new Object[0]);
                if (extractor2 != null) {
                    list.add(extractor2);
                    return;
                }
                return;
            case 16:
                list.add(new AviExtractor());
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    @Nullable
    public static Constructor<? extends Extractor> getFlacExtractorConstructor() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (Boolean.TRUE.equals(Class.forName("com.google.android.exoplayer2.ext.flac.FlacLibrary").getMethod("isAvailable", (Class[]) null).invoke((Object) null, (Object[]) null))) {
            return Class.forName("com.google.android.exoplayer2.ext.flac.FlacExtractor").asSubclass(Extractor.class).getConstructor(new Class[]{Integer.TYPE});
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static Constructor<? extends Extractor> getMidiExtractorConstructor() throws ClassNotFoundException, NoSuchMethodException {
        return Class.forName("com.google.android.exoplayer2.decoder.midi.MidiExtractor").asSubclass(Extractor.class).getConstructor((Class[]) null);
    }

    public synchronized Extractor[] createExtractors() {
        return createExtractors(Uri.EMPTY, new HashMap());
    }

    public synchronized DefaultExtractorsFactory setAdtsExtractorFlags(int i3) {
        this.adtsFlags = i3;
        return this;
    }

    public synchronized DefaultExtractorsFactory setAmrExtractorFlags(int i3) {
        this.amrFlags = i3;
        return this;
    }

    public synchronized DefaultExtractorsFactory setConstantBitrateSeekingAlwaysEnabled(boolean z2) {
        this.constantBitrateSeekingAlwaysEnabled = z2;
        return this;
    }

    public synchronized DefaultExtractorsFactory setConstantBitrateSeekingEnabled(boolean z2) {
        this.constantBitrateSeekingEnabled = z2;
        return this;
    }

    public synchronized DefaultExtractorsFactory setFlacExtractorFlags(int i3) {
        this.flacFlags = i3;
        return this;
    }

    public synchronized DefaultExtractorsFactory setFragmentedMp4ExtractorFlags(int i3) {
        this.fragmentedMp4Flags = i3;
        return this;
    }

    public synchronized DefaultExtractorsFactory setMatroskaExtractorFlags(int i3) {
        this.matroskaFlags = i3;
        return this;
    }

    public synchronized DefaultExtractorsFactory setMp3ExtractorFlags(int i3) {
        this.mp3Flags = i3;
        return this;
    }

    public synchronized DefaultExtractorsFactory setMp4ExtractorFlags(int i3) {
        this.mp4Flags = i3;
        return this;
    }

    public synchronized DefaultExtractorsFactory setTsExtractorFlags(int i3) {
        this.tsFlags = i3;
        return this;
    }

    public synchronized DefaultExtractorsFactory setTsExtractorMode(int i3) {
        this.tsMode = i3;
        return this;
    }

    public synchronized DefaultExtractorsFactory setTsExtractorTimestampSearchBytes(int i3) {
        this.tsTimestampSearchBytes = i3;
        return this;
    }

    public synchronized Extractor[] createExtractors(Uri uri, Map<String, List<String>> map) {
        ArrayList arrayList;
        try {
            int[] iArr = DEFAULT_EXTRACTOR_ORDER;
            arrayList = new ArrayList(iArr.length);
            int inferFileTypeFromResponseHeaders = FileTypes.inferFileTypeFromResponseHeaders(map);
            if (inferFileTypeFromResponseHeaders != -1) {
                addExtractorsForFileType(inferFileTypeFromResponseHeaders, arrayList);
            }
            int inferFileTypeFromUri = FileTypes.inferFileTypeFromUri(uri);
            if (!(inferFileTypeFromUri == -1 || inferFileTypeFromUri == inferFileTypeFromResponseHeaders)) {
                addExtractorsForFileType(inferFileTypeFromUri, arrayList);
            }
            for (int i3 : iArr) {
                if (!(i3 == inferFileTypeFromResponseHeaders || i3 == inferFileTypeFromUri)) {
                    addExtractorsForFileType(i3, arrayList);
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return (Extractor[]) arrayList.toArray(new Extractor[arrayList.size()]);
    }
}
