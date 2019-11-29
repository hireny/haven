package me.hireny.haven;

import me.hireny.haven.commons.utils.PropertyUtils;

/**
 * @ClassName: Bootstrap
 * @Author: hireny
 * @Date: Create in 2019/11/29 23:27
 * @Description: TODO   启动器
 */
public class Bootstrap {
    /**
     * 服务器入口(启动引导)
     */
    public static void run() {
        // 获取服务器端口
        String port = PropertyUtils.getProperty("server.port");
        if (null == port) {
            throw new IllegalArgumentException("server.port 不存在.");
        }
        // 获取当前设置的 IO 模式
        String connector = PropertyUtils.getProperty("server.connector");
        boolean isSetting = !"bio".equalsIgnoreCase(connector)
                && !"nio".equalsIgnoreCase(connector)
                && !"aio".equalsIgnoreCase(connector);
        if (null == connector || isSetting) {
            throw new IllegalArgumentException("server.network 不存在或不符合规范");
        }
        // 获取具体IO模式对应的endpoint(端点)实例

    }
}
