package crepe.backend.domain.log.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LogInfoList {

    List<LogInfo> logs = new ArrayList<>();

    public void addAllLogInfo(List<LogInfo> logs)
    {
        this.logs.addAll(logs);
    }

    public void addLogInfo(LogInfo log)
    {
        this.logs.add(log);
    }

}
