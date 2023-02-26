package vip.codehome.javatips.base.common;

import org.junit.Before;
import org.junit.Test;

public class PrimitiveTypeTest {
    PrimitiveTypeDemo primitiveTypeDemo;
    @Before
    public void init(){
        primitiveTypeDemo=new PrimitiveTypeDemo();
    }
    @Test
    public void printSize(){
        primitiveTypeDemo.printSize();
    }
}
