package com.thiagosol.ci.service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class DeployService {

    @Value("${server.connect.user}")
    private String user;

    @Value("${server.connect.pass}")
    private String pass;

    @Value("${server.connect.host}")
    private String host;

    public String deploy(String project) {

        String responseString = "Erro";
        Session session = null;
        ChannelExec channel = null;

        try {
            session = new JSch().getSession(user, host, 22);
            session.setPassword(pass);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(". /opt/sol-apis/ci/run.sh " + project);
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }

            responseString = new String(responseStream.toByteArray());

        } catch (Exception error){

        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
        return responseString;
    }
}
