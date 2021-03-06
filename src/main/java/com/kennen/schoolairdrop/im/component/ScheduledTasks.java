package com.kennen.schoolairdrop.im.component;

import com.kennen.schoolairdrop.im.dao.OfflineDao;
import com.kennen.schoolairdrop.im.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kennen
 * @date 2021/2/25 14:31
 */

@Slf4j
@Component
@Transactional
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private OfflineDao offlineDao;

    /**
     * 每日凌晨四点统一删除数据库中已经被ack的离线消息
     */
    @Scheduled(cron = "0 0 4 * * ? ")
    public void clearReceivedOffline() {
        for (int table = 0; table < Constants.OFFLINE_TABLE_NUMS; table++) {
            offlineDao.clearReceivedOffline(table);
        }
    }
}
