package logging.utils;

import logging.enums.LogModule;
import logging.enums.LogType;
import logging.enums.Severity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.json.JsonObject;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class LoggerService {

    private LogDetailsDTO logDetailsDTO;

    public JsonObject postLog(LogDetailsDTO logDetailsDTO) {
        try {
            if (logDetailsDTO != null) {
                JsonObject jsonObject = this.convertTOJsonObject(logDetailsDTO);
//                switch (logDetailsDTO.getLogType()){
//                    case INFO ->
//                }

            }
        } catch (Exception errMsg) {
            return null;
        }

        return null;
    }

    public JsonObject convertTOJsonObject(LogDetailsDTO logDetailsDTO) {
        return null;
    }

}
