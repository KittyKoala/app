package com.kangyonggan.app;

import com.kangyonggan.app.service.RedisService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试分布式锁。
 * 场景：10人同时去抢优惠券，但是官网只放出100张优惠券，当100张抢完后会继续放优惠券...
 *
 * @author kangyonggan
 * @since 2019-02-14
 */
public class RedisUtilTest extends AbstractTest {

    @Autowired
    private RedisService redisService;

    @Before
    public void before() {
        // 清空队列中的优惠券
        redisService.deleteAll("TICKETS");
    }

    /**
     * 模拟10个人同时买优惠券(不加锁)
     *
     * @throws Exception
     */
    @Test
    public void testWithoutLock() throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    buyTicketWithoutLock();
                }
            }.start();
        }

        Thread.sleep(3000);
        System.out.println("所有人抢完之后，队列实际大小：" + redisService.getRedisTemplate().opsForList().size("TICKETS"));
    }

    /**
     * 买优惠券(不加锁)
     */
    private void buyTicketWithoutLock() {
        String ticketId = (String) redisService.rightPop("TICKETS");

        if (ticketId == null) {
            System.out.println("队列中没优惠券了，再放100张");
            outTickets();

            ticketId = (String) redisService.rightPop("TICKETS");
        }

        if (ticketId == null) {
            System.out.println("没有抢到优惠券");
        } else {
            System.out.println("线程：" + Thread.currentThread().getId() + ", 抢到优惠券：" + ticketId);
        }
    }

    /**
     * 向队列中加入100张优惠券
     */
    private void outTickets() {
        String[] tickets = new String[100];
        for (int i = 0; i < 100; i++) {
            tickets[i] = "ticket-" + i;
        }
        redisService.leftPushAll("TICKETS", tickets);
    }

}
