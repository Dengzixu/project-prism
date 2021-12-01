package net.dengzixu.java.project_prism.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SendGiftRecord extends Record {
    private Long giftId;
    private String giftName;
    private Integer num;

    private String coinType;
    private Integer price;
    private Integer discountPrice;
    private Integer totalCoin;
}
