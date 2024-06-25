package com.example;

import io.quarkus.scheduler.Scheduled;
import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.util.logging.Logger;

@ApplicationScoped
public class FileCheckJob {

    private static final Logger LOGGER = Logger.getLogger(FileCheckJob.class.getName());
    private static final String DIRECTORY_PATH = "/path/to/your/directory";
    
    private int fileCount = -1;
    private long lastCheckedTime = 0;

    @Scheduled(every="10s")
    void checkFile() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists() || !directory.isDirectory()) {
            LOGGER.warning("Directory does not exist: " + DIRECTORY_PATH);
            fileCount = 0;
            lastCheckedTime = System.currentTimeMillis();
            return;
        }

        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            LOGGER.info("No files found in directory: " + DIRECTORY_PATH);
            fileCount = 0;
        } else {
            LOGGER.info(files.length + " files found in directory: " + DIRECTORY_PATH);
            fileCount = files.length;
        }
        lastCheckedTime = System.currentTimeMillis();
    }
    
    public int getFileCount() {
        return fileCount;
    }
    
    public long getLastCheckedTime() {
        return lastCheckedTime;
    }
}
