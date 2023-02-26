package vip.codehome.javatips.io.channel;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;


public class ChannelTest {
    @Test
    public void testLockWrite() throws InterruptedException {
        ChannelUtils channelUtils=new ChannelUtils();
        ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime()
            .availableProcessors()*2);
        String path=System.getProperty("user.home")+ File.separator+"tmp.txt";
        CountDownLatch countDownLatch=new CountDownLatch(100);
        for(int i=0;i<100;i++){
            executor.submit(()->{
                    try{
                        channelUtils.lockWrite(path,new StringBuilder("current Thread id:")
                            .append(Thread.currentThread().getId())
                            .append(Thread.currentThread().getName()).toString());
                        System.out.println(channelUtils.read(path));
                    }catch (Exception e){

                    }
                    countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executor.shutdown();

    }
}
