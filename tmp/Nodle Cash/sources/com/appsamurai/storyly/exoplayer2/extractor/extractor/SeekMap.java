package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import A.a;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;

public interface SeekMap {

    public static final class SeekPoints {
        public final SeekPoint first;
        public final SeekPoint second;

        public SeekPoints(SeekPoint seekPoint) {
            this(seekPoint, seekPoint);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || SeekPoints.class != obj.getClass()) {
                return false;
            }
            SeekPoints seekPoints = (SeekPoints) obj;
            return this.first.equals(seekPoints.first) && this.second.equals(seekPoints.second);
        }

        public int hashCode() {
            return this.second.hashCode() + (this.first.hashCode() * 31);
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder("[");
            sb.append(this.first);
            if (this.first.equals(this.second)) {
                str = "";
            } else {
                str = ", " + this.second;
            }
            return a.n(sb, str, "]");
        }

        public SeekPoints(SeekPoint seekPoint, SeekPoint seekPoint2) {
            this.first = (SeekPoint) Assertions.checkNotNull(seekPoint);
            this.second = (SeekPoint) Assertions.checkNotNull(seekPoint2);
        }
    }

    public static class Unseekable implements SeekMap {
        private final long durationUs;
        private final SeekPoints startSeekPoints;

        public Unseekable(long j2) {
            this(j2, 0);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public SeekPoints getSeekPoints(long j2) {
            return this.startSeekPoints;
        }

        public boolean isSeekable() {
            return false;
        }

        public Unseekable(long j2, long j3) {
            this.durationUs = j2;
            this.startSeekPoints = new SeekPoints(j3 == 0 ? SeekPoint.START : new SeekPoint(0, j3));
        }
    }

    long getDurationUs();

    SeekPoints getSeekPoints(long j2);

    boolean isSeekable();
}
