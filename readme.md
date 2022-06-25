# cmd-exporter
## 说明
将命令行执行结果作为指标通过exporter暴露出去。

- 使用java实现
- 指标类型为Gauge，只支持返回值为数值的命令
- 支持添加labels

## 配置
在`.yml`文件完成配置，说明如下：

```
port: 8888 #端口

commands: # 命令指标列表
  - name: "kafka_up_status" # 指标名称 
    cmd: "ps -ef|grep kafka|grep -v grep|wc -l" # 命令
    labels: # 标签列表
      host: "kafka1" # k/v
    
```

## 使用
1. 打包`mvn clean install`
2. 准备配置文件`application.yml`
    ```
    port: 8888 #端口
    
    commands: # 命令指标列表
      - name: "kafka_up_status" # 指标名称 
        cmd: "ps -ef|grep kafka|grep -v grep|wc -l" # 命令
        labels: # 标签列表
          host: "kafka1" # k/v    
    ```
3. 启动exporter
    ```
    # 将配置文件路径作为参数传入
    nohup java -cp cmd-exporter-1.0-SNAPSHOT.jar \
     com.prometheus.exporter.Server /application.yml > nohup.txt 2>&1 &
    ```
4. 获取指标
    ```
    curl localhost:7998/metrics
    ```
