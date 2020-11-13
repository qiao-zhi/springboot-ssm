package cn.qlq.git;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.application.name}")
    String applicationName;

    @GetMapping(value = "/buildinfo", produces = {"application/json;charset=UTF-8"})
    public String getBuildInfo() {
        return "当前应用:" + applicationName + ",\n" + buildInfo.toString();
    }
}
