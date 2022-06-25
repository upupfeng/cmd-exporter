package com.prometheus.exporter.bean;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author mawf
 */
public class Config extends BaseBean {

    private int port;

    private List<Command> commands;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Config.class.getSimpleName() + "[", "]")
                .add("port=" + port)
                .add("commands=" + commands)
                .toString();
    }

}
