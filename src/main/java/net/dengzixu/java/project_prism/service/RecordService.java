package net.dengzixu.java.project_prism.service;

import net.dengzixu.java.project_prism.entity.DanmuBodyRecord;
import net.dengzixu.java.project_prism.entity.InteractWordRecord;
import net.dengzixu.java.project_prism.entity.Record;
import net.dengzixu.java.project_prism.entity.SendGiftRecord;

public interface RecordService {
    void write(Record record);

    void writeDanmuBody(DanmuBodyRecord danmuBodyRecord);

    void writeInteractWord(InteractWordRecord interactWordRecord);

    void writeSendGift(SendGiftRecord sendGiftRecord);
}
