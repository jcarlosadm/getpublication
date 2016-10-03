package getpublication.util.logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogFileWriter {

    private BufferedWriter bufferedWriter = null;

    private static LogFileWriter instance = null;

    private LogFileWriter() {
    }

    public static LogFileWriter getInstance(File logFile) {
        if (instance == null) {
            instance = getNewLogFile(logFile);
        }

        return instance;
    }

    public static LogFileWriter getNewLogFile(File logFile) {
        LogFileWriter logFileWriter = new LogFileWriter();

        try {
            logFileWriter.bufferedWriter = new BufferedWriter(
                    new FileWriter(logFile));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        instance = logFileWriter;

        return logFileWriter;
    }

    private String buildRealMessage(String oldMessage) {
        String realMessage = "";
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        realMessage += sFormat.format(Calendar.getInstance().getTime());
        realMessage += " : ";
        realMessage += oldMessage;

        return realMessage;
    }

    public void writeLog(String message) throws Exception {
        this.bufferedWriter
                .write(this.buildRealMessage(message) + System.lineSeparator());
    }

    public void close() throws Exception {
        this.bufferedWriter.close();
    }
}
