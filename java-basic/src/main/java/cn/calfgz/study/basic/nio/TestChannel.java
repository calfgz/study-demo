package cn.calfgz.study.basic.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-02 11:00
 *
 * 一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 二、通道的主要实现类
 * 	java.nio.channels.Channel 接口：
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 *
 * 三、获取通道
 * 1. Java 针对支持通道的类提供了 getChannel() 方法
 * 		本地 IO：
 * 		FileInputStream/FileOutputStream
 * 		RandomAccessFile
 *
 * 		网络IO：
 * 		Socket
 * 		ServerSocket
 * 		DatagramSocket
 *
 * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 * 六、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组  -> 字符串
 *
 */
public class TestChannel {

    @Test
    public void testCharset() throws CharacterCodingException {
        Charset charset = Charset.forName("GBK");

        //获取编码器
        CharsetEncoder encoder = charset.newEncoder();

        //获取解码器
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer buf = CharBuffer.allocate(1024);
        buf.put("深藏若虚！");
        buf.flip();

        //编码
        ByteBuffer bBuf = encoder.encode(buf);

        for (int i = 0; i < 10; i++) {
            System.out.println(bBuf.get());
        }

        //解码
        bBuf.flip();
        CharBuffer cBuf = decoder.decode(bBuf);
        System.out.println(cBuf.toString());

        System.out.println("----------------------------------");

        Charset cs = Charset.forName("GBK");
        bBuf.flip();
        CharBuffer charBuffer = cs.decode(bBuf);
        System.out.println(charBuffer.toString());
    }

    @Test
    public void testPrintCharset() {
        Map<String, Charset> map = Charset.availableCharsets();
        map.entrySet().forEach(entry ->
            System.out.println(entry.getKey() + "=" + entry.getValue())
        );
    }

    /**
     * 分散和聚集
     */
    @Test
    public void testReadWrite() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("1.txt", "rw");

        //获取通道
        FileChannel channel = raf.getChannel();

        //分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        //分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel.read(bufs);

        for (ByteBuffer buf : bufs) {
            buf.flip();
        }

        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("---------------------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        //聚集写入
        RandomAccessFile randomAccessFile = new RandomAccessFile("2.txt", "rw");
        FileChannel channel1 = randomAccessFile.getChannel();

        channel1.write(bufs);
    }

    /**
     * 通道之间的数据传输(直接缓冲区)
     */
    @Test
    public void testTransferFrom() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/apple/Movies/红红的灯笼红红的脸.mp4"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/apple/Movies/direct.mp4"),
                StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    /**
     * 使用直接缓冲区完成文件的复制(内存映射文件)
     */
    @Test
    public void testDirectCopyFile() throws IOException {
        long start = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("/Users/apple/Movies/红红的灯笼红红的脸.mp4"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/apple/Movies/direct.mp4"),
                StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    /**
     * 利用通道完成文件的复制(非直接缓冲区)
     */
    @Test
    public void testCopyFile() {
        long start = System.currentTimeMillis();

        FileInputStream fis = null;
        FileOutputStream fos = null;

        //获取通道
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            fis = new FileInputStream("/Users/apple/Movies/红红的灯笼红红的脸.mp4");
            fos = new FileOutputStream("/Users/apple/Movies/copy.mp4");

            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //将通道中的数据存入缓冲区
            while (inChannel.read(buf) != -1) {
                //切换读取数据模式
                buf.flip();
                //将缓冲区数据写入通道中
                outChannel.write(buf);
                //清空缓冲区
                buf.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outChannel.close();
                inChannel.close();
                fos.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }
}
