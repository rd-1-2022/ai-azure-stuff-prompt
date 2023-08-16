# Stuffing the Prompt in Generative AI Models

## Prerequisite

Before using the AI commands, obtain your Azure OpenAI `endpoint` and `api-key` from the Azure OpenAI Service section on [Azure Portal](https://portal.azure.com)

The Spring AI project defines a configuration property named `spring.ai.openai.api-key` that you should set to the value of the `API Key` obtained from `openai.com`.

Exporting an environment variables is one way to set these configuration properties.
```shell
export SPRING_AI_AZURE_OPENAI_API_KEY=<INSERT KEY HERE>
export SPRING_AI_AZURE_OPENAI_ENDPOINT=<INSERT ENDPOINT URL HERE>
```

## Building and running

```
./mvnw spring-boot:run
```

## Introduction

In this exercise, you will learn about the technique known as 'stuffing the prompt' and how it can be applied to enhance generative AI models like Azure OpenAI.

By incorporating data into the prompt, you enable AI models to generate more accurate and contextually relevant responses.

For this example, we'll explore a query about Curling in the 2022 Olympics. The dataset used to train the AI model only goes up until September 2021. 
Therefore, without furnishing additional data in the prompt, the model won't be capable of answering the question.

The technique simply includes a document within the prompt sent to Azure OPenAI that provides information about Curling in the 2022 Olympics.

The project demonstrates this concept with a web service that accepts HTTP GET requests at `http://localhost:8080/ai/stuff``

There is a request parameter named `message` whose default value is

```
Which athletes won the gold medal in curling at the 2022 Winter Olympics?
```

## Initial interaction

Using the `http` utility to send a request without any additional information in the prompt:

```shell
http GET localhost:8080/ai/stuff
```

This will result in a response like this:

```json
{
  "completion": "I'm sorry, I cannot predict future events as I am an AI language model and do not have information beyond what has been recorded."
}
```

## Stuffing the prompt

Now we will provide relevant information for Azure OpenAI to refer to reference.

We'll use Wikipedia article on Curling at the 2022 Winter Olympics.[Curling a the 2022 Winter Olympics ](https://en.wikipedia.org/wiki/Curling_at_the_2022_Winter_Olympics).

In the `resources\docs\` directory, there's a file named `wikipedia-curling.md` containing the article's content in Markdown format.

To stuff the prompt, send a request with the `stuffit` request parameter set to true.

```shell
http GET localhost:8080/ai/stuff?stuffit=true
```

The response will include information about gold medalists in curling at the 2022 Winter Olympics:

```json
{
    "completion": "The gold medalists in curling at the 2022 Winter Olympics were as follows:\n-Men's tournament: Brad Gushue, Mark Nichols, Brett Gallant, Geoff Walker, and Marc Kennedy (alternate) from Canada.\n-Women's tournament: Eve Muirhead, Vicky Wright, Jennifer Dodds, Hailey Duff, and Mili Smith (alternate) from Great Britain.\n-Mixed doubles tournament: Stefania Constantini and Amos Mosaner from Italy."
}
```


