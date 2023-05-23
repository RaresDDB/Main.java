package serviciu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {

    private static AuditService singleInstance = null;
    private static final String FILE_NAME = "audit.csv";

    private AuditService() {
    }

    public static AuditService getSingleInstance() {
        if (singleInstance == null) {
            singleInstance = new AuditService();
        }
        return singleInstance;
    }

    public void writeAuditLog(String actionName) {
        try (FileWriter fileWriter = new FileWriter(FILE_NAME, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            printWriter.println(actionName + "," + timestamp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

