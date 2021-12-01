package net.dengzixu.java.project_prism.mapper;

import net.dengzixu.java.project_prism.entity.DanmuBodyRecord;
import net.dengzixu.java.project_prism.entity.InteractWordRecord;
import net.dengzixu.java.project_prism.entity.SendGiftRecord;
import net.dengzixu.java.project_prism.mapper.provider.RecordMapperProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RecordMapper {
    @InsertProvider(type = RecordMapperProvider.class, method = "writeDanmuBodySQLBuilder")
    void writeDanmuBody(DanmuBodyRecord danmuBodyRecord);

    @InsertProvider(type = RecordMapperProvider.class, method = "writeInteractWordSQLBuilder")
    void writeInteractWord(InteractWordRecord interactWordRecord);

    @InsertProvider(type = RecordMapperProvider.class, method = "writeSendGiftSQLBuilder")
    void writeSendGift(SendGiftRecord sendGiftRecord);
}
