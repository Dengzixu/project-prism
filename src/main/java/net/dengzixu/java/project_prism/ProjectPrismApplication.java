package net.dengzixu.java.project_prism;

import net.dengzixu.DanmakuListener;
import net.dengzixu.java.project_prism.entity.DanmuBodyRecord;
import net.dengzixu.java.project_prism.entity.InteractWordRecord;
import net.dengzixu.java.project_prism.entity.SendGiftRecord;
import net.dengzixu.java.project_prism.service.RecordService;
import net.dengzixu.message.Message;
import net.dengzixu.profile.DanmakuProfile;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectPrismApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ProjectPrismApplication.class);
    private static long ROOM_ID = 0;

    private final RecordService recordService;

    @Autowired
    public ProjectPrismApplication(RecordService recordService) {
        this.recordService = recordService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectPrismApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CommandLineParser parser = new DefaultParser();

        Options options = new Options();

        options.addRequiredOption("ID", "room-id", true, "直播间 ID");

        try {
            CommandLine line = parser.parse(options, args);

            if (!line.hasOption("room-id")) {
                logger.error("缺少直播间ID");
                System.exit(0);
            }

            ROOM_ID = Long.parseLong(line.getOptionValue("room-id"));

            logger.info("直播间ID: {}", ROOM_ID);

        } catch (ParseException e) {
            logger.error("命令行解析错误", e);
            System.exit(0);
        }

        DanmakuProfile.getInstance().setAutoPullFace(false);
        DanmakuListener.getInstance(ROOM_ID).connect().registerListener(new Listener());

    }

    private class Listener implements net.dengzixu.listener.Listener {
        @Override
        public void onMessage(Message message) {
            logger.info(message.toString());

            switch (message.getBodyCommand()) {
                case DANMU_MSG -> recordService.write(new DanmuBodyRecord() {{
                    setRoomId(ROOM_ID);

                    setUid(message.getUserInfo().getUid());
                    setUsername(message.getUserInfo().getUsername());

                    setTimestamp(message.getTimestamp().toString());

                    setContent((String) message.getContent().get("danmu"));
                }});

                case INTERACT_WORD -> recordService.write(new InteractWordRecord() {{
                    setRoomId(ROOM_ID);

                    setUid(message.getUserInfo().getUid());
                    setUsername(message.getUserInfo().getUsername());

                    setTimestamp(message.getTimestamp().toString());

                    setType((Integer) message.getContent().get("msg_type"));
                }});

                case SEND_GIFT -> recordService.write(new SendGiftRecord() {{
                    setRoomId(ROOM_ID);

                    setUid(message.getUserInfo().getUid());
                    setUsername(message.getUserInfo().getUsername());

                    setTimestamp(message.getTimestamp().toString());

                    setGiftId(((Number) message.getContent().get("gift_id")).longValue());
                    setGiftName((String) message.getContent().get("gift_name"));
                    setNum(((Number) message.getContent().get("num")).intValue());

                    setCoinType((String) message.getContent().get("coin_type"));
                    setPrice(((Number) message.getContent().get("price")).intValue());
                    setDiscountPrice(((Number) message.getContent().get("discount_price")).intValue());
                    setTotalCoin(((Number) message.getContent().get("total_coin")).intValue());
                }});
            }
        }
    }
}
