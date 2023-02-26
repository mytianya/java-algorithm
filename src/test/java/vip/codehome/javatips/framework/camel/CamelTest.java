package vip.codehome.javatips.framework.camel;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultProducerTemplate;
import org.apache.camel.impl.SimpleRegistry;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.junit.Before;
import org.junit.Test;

/**
 * org.apache.camel.Component
 */
public class CamelTest {
    /**
     * direct,vm 是两个虚拟的Component,不会对消息做任何处理
     * direct接到消息后不做任何处理直接路由到下一个节点
     * @throws Exception
     */
    CamelContext context;
    @Before
    public void before(){

        SimpleRegistry registry = new SimpleRegistry();
        registry.put("myEncoder", new StringEncoder(Charset.forName("ISO-8859-1")));
        registry.put("myDecoder", new StringDecoder(Charset.forName("ISO-8859-1")));
        context=new DefaultCamelContext(registry);
    }
    @Test
    public void testDirect() throws Exception {
        context.addRoutes(new StreamRouteBuidler());
        context.start();
        ProducerTemplate template=new DefaultProducerTemplate(context);
        template.start();
        template.sendBody("direct:in", "Hello Text World");
    }
    @Test
    public void testUdp() throws Exception {
        CountDownLatch latch=new CountDownLatch(1);
        context.addRoutes(new UdpRouterBuilder());
        context.start();
        latch.await();
    }
    @Test
    public void testUdpCombine() throws Exception {
        CountDownLatch latch=new CountDownLatch(1);
        context.addRoutes(new UdpCombineRouteBuilder());
        context.start();
        latch.await();
    }

  public static void main(String[] args) {
    StringBuffer sb=new StringBuffer();
    sb.append("中国");
    for(int i=0;i<6000;i++){
        sb.append(0);
    }
    sb.append("$$");
    System.out.println(sb.toString());
  }

    @Test
    public void testUdpCodec() throws Exception {
        CountDownLatch latch=new CountDownLatch(1);
        context.addRoutes(new UdpByteRouteBuilder());
        context.start();
        latch.await();
    }
    @Test
    public void testTcp() throws Exception {
        CountDownLatch latch=new CountDownLatch(1);
        context.addRoutes(new TcpRouteBuilder());
        context.start();
        latch.await();
    }
}
