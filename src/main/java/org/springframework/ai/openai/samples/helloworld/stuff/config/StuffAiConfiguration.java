package org.springframework.ai.openai.samples.helloworld.stuff.config;

import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
public class StuffAiConfiguration {

    @Bean
    public PromptTemplate productDetailSystemPromptTemplate() {
        Resource resource = new ClassPathResource("/prompts/qa-prompt.st");
        try {
            return new PromptTemplate(StreamUtils.copyToString(resource.getInputStream(), UTF_8));
        } catch (IOException ex) {
            throw new RuntimeException("Could not create input stream from " + resource, ex);
        }
    }

}
