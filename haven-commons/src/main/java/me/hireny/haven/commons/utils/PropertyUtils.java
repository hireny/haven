package me.hireny.haven.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName: PropertyUtils
 * @Author: hireny
 * @Date: Create in 2019/11/29 23:30
 * @Description: TODO   配置文件工具类
 */
@Slf4j
public class PropertyUtils {

    /**
     * 获取资源
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        if (null == properties) {
            loadProps();
        }
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if (null == properties) {
            loadProps();
        }
        return properties.getProperty(key, defaultValue);
    }

    private static Properties properties;

    static {
        loadProps();
    }

    /**
     * 读取properties文件内容
     */
    private synchronized static void loadProps() {
        log.info("开始加载properties文件内容.......");
        properties = new Properties();
        try (InputStream in = PropertyUtils.class.getClassLoader().getResourceAsStream("server.properties")) {
            properties.load(in);
        } catch (FileNotFoundException e) {
            log.error(".properties文件未找到：" + e.getMessage());
        } catch (IOException e) {
            log.error("出现IOException异常：" + e.getMessage());
        }
        log.info(".properties文件内容：" + properties);
    }
}
