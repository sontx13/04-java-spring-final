package vn.project.smart.domain.response.exam;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResCreateExamDTO {
    private long id;
    private String name;
    private String description;
    private int level;
    private String logo;

    private boolean active;

    private Instant createdAt;
    private String createdBy;
}
