package cn.qlq.git;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @author: 乔利强
 * @date: 2020/11/13 12:45
 * @description:
 */
@Configuration
@PropertySource(value = "classpath:git.properties", ignoreResourceNotFound = true)
public class BuildInfoProperties {

    @Autowired
    private Environment env;

    @Bean
    public BuildInfo getBuildInfo() {
        BuildInfo buildInfo = new BuildInfo();
        buildInfo.gitDirty = env.getProperty("git.dirty");
        buildInfo.gitRemoteOriginUrl = env.getProperty("git.remote.origin.url");
        buildInfo.gitTags = env.getProperty("git.tags");
        buildInfo.gitBranch = env.getProperty("git.branch");
        buildInfo.gitCommitUserName = env.getProperty("git.commit.user.name");
        buildInfo.gitCommitTime = env.getProperty("git.commit.time");
        buildInfo.gitCommitMessageFull = env.getProperty("git.commit.message.full");
        buildInfo.gitCommitId = env.getProperty("git.commit.id");
        buildInfo.gitBuildVersion = env.getProperty("git.build.version");
        buildInfo.gitBuildUserName = env.getProperty("git.build.user.name");
        buildInfo.gitBuildTime = env.getProperty("git.build.time");
        buildInfo.gitBuildHost = env.getProperty("git.build.host");
        return buildInfo;
    }

    @Data
    public static class BuildInfo {
        private String gitDirty;
        private String gitRemoteOriginUrl;
        private String gitTags;
        private String gitBranch;
        private String gitCommitUserName;
        private String gitCommitTime;
        private String gitCommitMessageFull;
        private String gitCommitId;
        private String gitBuildVersion;
        private String gitBuildUserName;
        private String gitBuildTime;
        private String gitBuildHost;
    }
}