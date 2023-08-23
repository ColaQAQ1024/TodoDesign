package com.todoDesign;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.todoDesign.dto.QuestDTO;
import com.todoDesign.entity.Quest;
import com.todoDesign.entity.User;
import com.todoDesign.service.IGroupService;
import org.junit.jupiter.api.Test;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class TodoDesignApplicationTests {

    @Autowired
    IGroupService iGroupService;

    @Test
    void contextLoads() {
        ModelMapper modelMapper = new ModelMapper();
        //typeMap.addMappings(mapper -> mapper.skip(Destination::setName));
        TypeMap<Quest, QuestDTO> typeMap = modelMapper.createTypeMap(Quest.class,QuestDTO.class);
        typeMap.addMappings(mapping -> mapping.skip(Quest::getNameThing,QuestDTO::setNameThing));

        Converter<String, String> toUppercase =
                ctx -> ctx.getSource() == null ? null : ctx.getSource();
        typeMap.addMappings(mapper -> mapper.using(toUppercase).map(Quest::getNameThing,QuestDTO::setGroupName));

        Quest quest = new Quest();
        quest.setNameThing("halo!");
        QuestDTO questDTO = typeMap.map(quest);
        System.out.println(questDTO.getNameThing() + questDTO.getGroupName());
    }

    @Test
    public void redisTester() {
        Jedis jedis = new Jedis("localhost",6379,10000);
        int i = 0;
        try {
            long start = System.currentTimeMillis();// 开始毫秒数
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) {// 当大于等于1000毫秒（相当于1秒）时，结束操作
                    break;
                }
                i++;
                jedis.set("test" + i, String.valueOf(i));
            }
        } finally {// 关闭连接
            jedis.close();
        }
        // 打印1秒内对Redis的操作次数
        System.out.println("redis每秒操作：" + i + "次");
    }

    @Test
    public void testConcurrentIncrement() throws InterruptedException{
        int numThreads = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        //模拟100次线程
        for (int i = 0;i < numThreads; i++){
            User user = new User();
            user.setNickname("新用户_" + String.format("%04d",new Random().nextInt(10000)));

            executorService.execute(iGroupService::incrementCounter);
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("counter 列表为:" + iGroupService.getCounter());
    }

    @Test
    public void testUser() throws InterruptedException{
        int numThreads = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        //模拟一千次订阅
        for (int i = 0;i <= numThreads;i++){
            User user = new User();
            user.setNickname("新用户_" + String.format("%04d",new Random().nextInt(10000)));

            executorService.execute(() -> {
                iGroupService.cacheUser(user);
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("用户列表:" + iGroupService.getAllUserFromCache());
    }

    @Test
    public void Test() throws Exception {
        System.out.println(SaSecureUtil.rsaGenerateKeyPair());
    }

}
