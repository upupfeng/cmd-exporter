package com.prometheus.exporter.collector;

import com.prometheus.exporter.bean.Command;
import com.prometheus.exporter.util.CmdUtil;
import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author mawf
 */
public class CmdCollector extends Collector {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private List<Command> commandList;

    public CmdCollector(List<Command> commandList) {
        this.commandList = commandList;
    }

    @Override
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> mfs = new ArrayList<MetricFamilySamples>();

        for (Command command : commandList) {
            String name = command.getName();
            try {
                String cmd = command.getCmd();
                Map<String, String> labels = command.getLabels();

                // 执行命令获取结果
                String cmdReturn = CmdUtil.getCmdReturn(cmd);
                String now = dateFormat.format(new Date());
                String msg = String.format("%s 指标名：%s，命令：%s，返回值：%s", now, name, cmd, cmdReturn);
                System.out.println(msg);

                GaugeMetricFamily labeledGauge = new GaugeMetricFamily(name, "help", labels.keySet().stream().collect(Collectors.toList()));
                labeledGauge.addMetric(labels.values().stream().collect(Collectors.toList()), Integer.valueOf(cmdReturn));

                mfs.add(labeledGauge);

            } catch (Exception e) {
                System.out.println(String.format("指标%s执行失败", name));
                e.printStackTrace();
            }
        }

        return mfs;
    }
}
