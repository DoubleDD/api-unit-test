package com.keeton.log;

import lombok.Getter;

@Getter
public class LogItem {

    private long   nanoseconds;
    private String logLine;

    public LogItem(long nanoseconds, String logLine) {
        this.nanoseconds = nanoseconds;
        this.logLine     = logLine;
    }

    public void setNanoseconds(long nanoseconds) {
        this.nanoseconds = nanoseconds;
    }

    public void setLogLine(String logLine) {
        this.logLine = logLine;
    }
}
