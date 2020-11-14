package cn.qlq.git;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 乔利强
 * @date: 2020/11/13 12:46
 * @description:
 */
@RestController
public class BuildInfoController {

    @Autowired
    private BuildInfoProperties.BuildInfo buildInfo;

    @Autowired
    private Environment env;

    @GetMapping(value = "/buildinfo", produces = {"application/json;charset=UTF-8"})
    public String getBuildInfo() {
        return buildInfo.toString();
    }

    @GetMapping(value = "/testEnv")
    public String testEnv() {
        String serverPort = env.getProperty("server.port");
        String commitId = env.getProperty("git.commit.id");

        return serverPort + "\t" + commitId;
    }
}