package com.prometheus.exporter.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author mawf
 */
public class CmdUtil {

    public static String getCmdReturn(String cmdStr) {
        String[] cmd = new String[3];
        cmd[0] = "/bin/sh";
        cmd[1] = "-c";
        cmd[2] = cmdStr;
        //得到Java进程的相关Runtime运行对象
        Runtime runtime = Runtime.getRuntime();
        StringBuffer stringBuffer = null;
        try {
            Process process = runtime.exec(cmd);
            BufferedReader bufferReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            stringBuffer = new StringBuffer();
            String temp = null;
            while ((temp = bufferReader.readLine()) != null) {
                stringBuffer.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    // java -cp cmd-exporter-1.0-SNAPSHOT.jar com.upupfeng.exporter.util.CmdUtil "ps -ef|grep 'mwf'|grep -v grep|wc -l"
    public static void main(String[] args) {
//        String cmd = "echo a |wc -l";
        String cmd = args[0];
        String cmdReturn = getCmdReturn(cmd);
        System.out.println(cmdReturn);
    }

}
