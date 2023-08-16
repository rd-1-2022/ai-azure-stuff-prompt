package org.springframework.ai.openai.samples.helloworld.stuff;

public class Completion {

    private final String completion;

    public Completion(String completion) {
        this.completion = completion;
    }

    public String getCompletion() {
        return completion;
    }
}
