package net.dengzixu.java.project_prism.mapper.provider;

import net.dengzixu.java.project_prism.entity.DanmuBodyRecord;
import net.dengzixu.java.project_prism.entity.InteractWordRecord;
import net.dengzixu.java.project_prism.entity.SendGiftRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

public class RecordMapperProvider {
    private final static String PRISM_REC_DANMU_BODY_TABLE = "prism_rec_danmu_body";
    private final static String PRISM_REC_INTERACT_WORD_TABLE = "prism_rec_interact_word";
    private final static String PRISM_REC_SEND_GIFT_TABLE = "prism_rec_send_gift";

    public String writeDanmuBodySQLBuilder(DanmuBodyRecord danmuBodyRecord) {
        return new SQL() {{
            INSERT_INTO(PRISM_REC_DANMU_BODY_TABLE);
            VALUES("room_id", "#{roomId}");
            VALUES("uid", "#{uid}");
            VALUES("username", "#{username}");
            if (StringUtils.isNotBlank(danmuBodyRecord.getTimestamp())) {
                VALUES("timestamp", "FROM_UNIXTIME(#{timestamp})");
            }
            VALUES("content", "#{content}");
        }}.toString();
    }

    public String writeInteractWordSQLBuilder(InteractWordRecord interactWordRecord) {
        return new SQL() {{
            INSERT_INTO(PRISM_REC_INTERACT_WORD_TABLE);
            VALUES("room_id", "#{roomId}");
            VALUES("uid", "#{uid}");
            VALUES("username", "#{username}");
            if (StringUtils.isNotBlank(interactWordRecord.getTimestamp())) {
                VALUES("timestamp", "FROM_UNIXTIME(#{timestamp})");
            }
            VALUES("type", "#{type}");
        }}.toString();
    }

    public String writeSendGiftSQLBuilder(SendGiftRecord sendGiftRecord) {
        return new SQL() {{
            INSERT_INTO(PRISM_REC_SEND_GIFT_TABLE);
            VALUES("room_id", "#{roomId}");
            VALUES("uid", "#{uid}");
            VALUES("username", "#{username}");
            if (StringUtils.isNotBlank(sendGiftRecord.getTimestamp())) {
                VALUES("timestamp", "FROM_UNIXTIME(#{timestamp})");
            }

            VALUES("gift_id", "#{giftId}");
            VALUES("gift_name", "#{giftName}");
            VALUES("num", "#{num}");
            VALUES("coin_type", "#{coinType}");
            VALUES("price", "#{price}");
            VALUES("discount_price", "#{discountPrice}");
            VALUES("total_coin", "#{totalCoin}");
        }}.toString();
    }

}
