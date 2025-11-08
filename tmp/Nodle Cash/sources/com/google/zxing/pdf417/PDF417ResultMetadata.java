package com.google.zxing.pdf417;

public final class PDF417ResultMetadata {
    private String addressee;
    private int checksum = -1;
    private String fileId;
    private String fileName;
    private long fileSize = -1;
    private boolean lastSegment;
    private int[] optionalData;
    private int segmentCount = -1;
    private int segmentIndex;
    private String sender;
    private long timestamp = -1;

    public String getAddressee() {
        return this.addressee;
    }

    public int getChecksum() {
        return this.checksum;
    }

    public String getFileId() {
        return this.fileId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    @Deprecated
    public int[] getOptionalData() {
        return this.optionalData;
    }

    public int getSegmentCount() {
        return this.segmentCount;
    }

    public int getSegmentIndex() {
        return this.segmentIndex;
    }

    public String getSender() {
        return this.sender;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public boolean isLastSegment() {
        return this.lastSegment;
    }

    public void setAddressee(String str) {
        this.addressee = str;
    }

    public void setChecksum(int i3) {
        this.checksum = i3;
    }

    public void setFileId(String str) {
        this.fileId = str;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setFileSize(long j2) {
        this.fileSize = j2;
    }

    public void setLastSegment(boolean z2) {
        this.lastSegment = z2;
    }

    @Deprecated
    public void setOptionalData(int[] iArr) {
        this.optionalData = iArr;
    }

    public void setSegmentCount(int i3) {
        this.segmentCount = i3;
    }

    public void setSegmentIndex(int i3) {
        this.segmentIndex = i3;
    }

    public void setSender(String str) {
        this.sender = str;
    }

    public void setTimestamp(long j2) {
        this.timestamp = j2;
    }
}
