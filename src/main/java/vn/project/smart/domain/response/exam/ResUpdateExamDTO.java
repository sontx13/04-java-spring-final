package vn.project.smart.domain.response.exam;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResUpdateExamDTO {
    private long id;
    private int level;
    private String name;

    private String description;

    private String logo;
    private boolean active;

    private Instant updatedAt;
    private String updatedBy;
}
