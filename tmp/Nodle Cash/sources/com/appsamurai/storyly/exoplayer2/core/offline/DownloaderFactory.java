package com.appsamurai.storyly.exoplayer2.core.offline;

public interface DownloaderFactory {
    Downloader createDownloader(DownloadRequest downloadRequest);
}
