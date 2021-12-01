package net.dengzixu.java.project_prism.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public abstract class Record {
    private Long id;

    private Long roomId;

    private Long uid;
    private String username;

    private String timestamp;
}
