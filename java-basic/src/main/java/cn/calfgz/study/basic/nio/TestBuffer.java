package cn.calfgz.study.basic.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author calfgz
 * @description:
 * @date 2020-02-29 18:50
 */
public class TestBuffer {

    @Test
    public void test3() {
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        System.out.println(buf.isDirect());
    }

    @Test
    public void test2() {
        String str = "abcde";

        ByteBuffer buf = ByteBuffer.allocate(1024);

        buf.put(str.getBytes());

        buf.flip();

        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println(buf.position());

        //标记
        buf.mark();

        buf.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(buf.position());

        //reset
        buf.reset();
        System.out.println(buf.position());

        if (buf.hasRemaining()) {
            System.out.println(buf.remaining());
        }
    }

    @Test
    public void test1() {
        //分配指定大小的缓存区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("-------------allocate()-------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //利用 put() 存入数据到缓冲区
        buf.put("123456".getBytes());

        buf.put("789".getBytes());

        System.out.println("-------------put()-------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());


        //切换读取数据模式
        buf.flip();

        System.out.println("-------------flip()-------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //利用 get() 读取缓冲区的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));

        System.out.println("-------------get()-------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // rewind() 可重复读
        buf.rewind();

        System.out.println("-------------rewind()-------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //clear() 清空缓冲区。但是缓冲区中的数据依然存在，只是处于“被遗忘”状态
        buf.clear();

        System.out.println("-------------clear()-------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        System.out.println((char)buf.get());


    }
}
