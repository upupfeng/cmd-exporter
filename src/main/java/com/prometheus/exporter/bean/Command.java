package com.prometheus.exporter.bean;

import java.util.Map;
import java.util.StringJoiner;

/**
 * @author mawf
 */
public class Command extends BaseBean {

    private String name;
    private String cmd;
    private Map<String, String> labels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Command.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("cmd='" + cmd + "'")
                .add("labels=" + labels)
                .toString();
    }

}
