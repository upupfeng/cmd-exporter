package com.prometheus.exporter;

import com.prometheus.exporter.bean.Command;
import com.prometheus.exporter.bean.Config;
import com.prometheus.exporter.collector.CmdCollector;
import com.prometheus.exporter.util.ConfUtil;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.List;

/**
 * @author mawf
 */
public class Server {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("需要传入配置文件的路径");
            System.exit(1);
        }
        String confFilePath = args[0];
        Config config = ConfUtil.readConfig(confFilePath);
        System.out.println("配置信息：" + config.toString());

        int port = config.getPort();

        // 所有的command
        List<Command> commandList = config.getCommands();

        // 注册
        new CmdCollector(commandList).register();

        // 启动服务并访问
        // http://localhost:7998/metrics
        HTTPServer httpServer = new HTTPServer
                .Builder()
                .withPort(port)
                .build();

    }
}
