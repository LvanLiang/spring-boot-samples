package com.bohoog.test;

import com.bohoog.entity.TrsGovmsgbox;
import com.bohoog.entity.TrsGovmsgboxReply;
import com.bohoog.mapper.source.SourceBoxMapper;
import com.bohoog.mapper.target.TrsGovmsgboxMapper;
import com.bohoog.mapper.target.TrsGovmsgboxReplyMapper;
import com.bohoog.util.ThreadPoolUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.*;

/**
 * 信箱数据导入
 *
 * @author Liangxp
 * @date 2019/11/21 15:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrsGovmsgboxService {

    /**
     * 每个线程处理的条数
     */
    private int pageSize = 1000;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private TrsGovmsgboxMapper trsGovmsgboxMapper;

    @Autowired
    private TrsGovmsgboxReplyMapper trsGovmsgboxReplyMapper;

    @Autowired
    private SourceBoxMapper sourceBoxMapper;

    private static final ThreadPoolExecutor executor;

    static {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        executor = new ThreadPoolExecutor(5, 8,
                30L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 信箱及回复导入[遵义市科学技术局]
     */
    @Test
    public void testImportJsj() {
        // 遵义市科学技术局、信箱ID
        Long siteId = 500544L, appId = 518L;
        List<TrsGovmsgbox> boxList = sourceBoxMapper.listZyskxjsjBox();
        for (TrsGovmsgbox trsGovmsgbox : boxList) {
            trsGovmsgbox.setSiteid(siteId);
            trsGovmsgbox.setAppId(appId);
            // 提交人姓名、提交时间
            trsGovmsgbox.setUsername(trsGovmsgbox.getCruser());
            trsGovmsgbox.setSubmitTime(trsGovmsgbox.getCreateDate());
            trsGovmsgbox.setModifyDate(trsGovmsgbox.getCreateDate());
            // 已办结
            trsGovmsgbox.setStatus(7);
            // 保存到信箱表
            trsGovmsgboxMapper.insert(trsGovmsgbox);
            // 构建回复信息并保存
            TrsGovmsgboxReply boxReply = TrsGovmsgboxReply.builder().dataId(trsGovmsgbox.getId())
                    .replycontent(trsGovmsgbox.getReply()).replyhtmlcontent(trsGovmsgbox.getReply())
                    .replytime(trsGovmsgbox.getReplyTime()).siteId(siteId).build();
            trsGovmsgboxReplyMapper.insert(boxReply);
            logger.info("回复:{}", boxReply);
        }
    }

    /**
     * 信箱及回复导入[遵义市人力资源和社会保障局]
     */
    @Test
    public void testImportRlzy() {
        // 遵义市人力资源和社会保障局、信箱ID
        Long siteId = 500556L, appId = 313L;
        // 查询总条数，方便分页处理
        Long rowsCount = sourceBoxMapper.getRowsCount("complaints");
        logger.info("总条数：{}", rowsCount);
        // 总页数
        long totalPage = rowsCount / pageSize + 1;
        try {
            // 闭锁，等待所有的线程执行完毕
            CountDownLatch latch = new CountDownLatch((int) totalPage);
            // 确定每条线程的数据
            for (int i = 1; i <= totalPage; i++) {
                // 为线程池提交执行任务
                int pageIndex = i;
                ThreadPoolUtils.execute(() -> {
                    PageHelper.startPage(pageIndex, pageSize);
                    List<TrsGovmsgbox> boxList = sourceBoxMapper.listZysrlzyBox();
                    for (TrsGovmsgbox trsGovmsgbox : boxList) {
                        trsGovmsgbox.setSiteid(siteId);
                        trsGovmsgbox.setAppId(appId);
                        // 提交人姓名、提交时间
                        trsGovmsgbox.setUsername(trsGovmsgbox.getCruser());
                        trsGovmsgbox.setSubmitTime(trsGovmsgbox.getCreateDate());
                        trsGovmsgbox.setModifyDate(trsGovmsgbox.getCreateDate());

                        // 保存到信箱表
                        trsGovmsgboxMapper.insert(trsGovmsgbox);
                        // 构建回复信息并保存
                        TrsGovmsgboxReply boxReply = TrsGovmsgboxReply.builder().dataId(trsGovmsgbox.getId())
                                .replycontent(trsGovmsgbox.getReply()).replyhtmlcontent(trsGovmsgbox.getReply())
                                .replytime(trsGovmsgbox.getReplyTime()).siteId(siteId).build();
                        trsGovmsgboxReplyMapper.insert(boxReply);
                        logger.info("线程{}----数据：{}", Thread.currentThread().getName(), trsGovmsgbox.getCruser());
                    }
                    latch.countDown();
                });
            }
            // 等待所有的任务执行完成
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("数据任务出现异常: latch.await");
        } finally {
            // 关闭线程池
            ThreadPoolUtils.shutdown();
        }
    }
}

