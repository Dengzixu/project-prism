package net.dengzixu.java.project_prism.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class InteractWordRecord extends Record {
    private Integer type;
}
