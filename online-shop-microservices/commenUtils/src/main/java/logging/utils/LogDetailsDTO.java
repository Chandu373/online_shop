package logging.utils;

import logging.enums.LogModule;
import logging.enums.LogType;
import logging.enums.Severity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDetailsDTO {

    private String name;
    private String description;
    private String payLoad;
    private String className;
    private String methodName;
    private LogType logType;
    private LogModule logModule;
    private Severity severity;
    private String timeTaken;
}
