package com.prometheus.exporter.util;

import com.prometheus.exporter.bean.Config;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author mawf
 */
public class ConfUtil {

    /**
     * 读取任务配置
     *
     * @param filePath
     * @return
     */
    public static Config readConfig(String filePath) throws FileNotFoundException {
        try {
            File file = new File(filePath);
            Config config
                    = (new Yaml().loadAs(new FileInputStream(file), Config.class));
            return config;
        } catch (Exception e) {
            System.err.println("解析配置失败");
            e.printStackTrace();
            throw e;
        }
    }

}
