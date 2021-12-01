package net.dengzixu.java.project_prism.service.impl;

import net.dengzixu.java.project_prism.entity.DanmuBodyRecord;
import net.dengzixu.java.project_prism.entity.InteractWordRecord;
import net.dengzixu.java.project_prism.entity.Record;
import net.dengzixu.java.project_prism.entity.SendGiftRecord;
import net.dengzixu.java.project_prism.mapper.RecordMapper;
import net.dengzixu.java.project_prism.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordMapper recordMapper;

    @Autowired
    public RecordServiceImpl(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    @Override
    public void write(Record record) {
        // WARNING ONLY FOR JAVA17 PREVIEW
//        switch (record) {
//            case DanmuBodyRecord danmuBodyRecord -> this.writeDanmuBody(danmuBodyRecord);
//            case InteractWordRecord interactWordRecord -> this.writeInteractWord(interactWordRecord);
//
//            default -> throw new IllegalStateException("Unexpected value: " + record);
//        }

        if (record instanceof DanmuBodyRecord danmuBodyRecord) {
            this.writeDanmuBody(danmuBodyRecord);
        } else if (record instanceof InteractWordRecord interactWordRecord) {
            this.writeInteractWord(interactWordRecord);
        } else if (record instanceof SendGiftRecord sendGiftRecord) {
            this.writeSendGift(sendGiftRecord);
        } else {
            throw new IllegalStateException("Unexpected value: " + record);
        }
    }

    @Override
    public void writeDanmuBody(DanmuBodyRecord danmuBodyRecord) {
        recordMapper.writeDanmuBody(danmuBodyRecord);
    }

    @Override
    public void writeInteractWord(InteractWordRecord interactWordRecord) {
        recordMapper.writeInteractWord(interactWordRecord);
    }

    @Override
    public void writeSendGift(SendGiftRecord sendGiftRecord) {
        recordMapper.writeSendGift(sendGiftRecord);
    }
}
