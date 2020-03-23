package cn.calfgz.study.single;

import lombok.Data;

import java.util.Properties;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-22 14:04
 */
@Data
public class Singleton3 {

    public static final Singleton3 INSTANCE;
    private String info;

    static {
        try {
            Properties properties = new Properties();
            properties.load(Singleton3.class.getClassLoader().getResourceAsStream("single.properties"));
            INSTANCE = new Singleton3(properties.getProperty("info"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Singleton3(String info){
        this.info = info;
    }

}
