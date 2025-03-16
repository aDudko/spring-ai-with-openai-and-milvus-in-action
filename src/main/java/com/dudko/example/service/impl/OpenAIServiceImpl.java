package com.dudko.example.service.impl;

import com.dudko.example.model.Answer;
import com.dudko.example.model.Question;
import com.dudko.example.service.AIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OpenAIServiceImpl implements AIService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public OpenAIServiceImpl(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    @Value("classpath:/templates/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    @Value("classpath:/templates/system-message.st")
    private Resource systemMessageTemplate;


    @Override
    public Answer getAnswer(Question question) {
        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder()
                .query(question.question())
                .topK(5)
                .build());
        if (documents == null || documents.isEmpty()) {
            return new Answer("Documents not found.");
        }
        String documentContent = documents.stream()
                .map(Document::getFormattedContent)
                .collect(Collectors.joining("\n"));
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Message userMessage = promptTemplate.createMessage(Map.of(
                "input", question.question(),
                "documents", documentContent
        ));
        Message systemMessage = new SystemPromptTemplate(systemMessageTemplate).createMessage();
        ChatResponse response = chatClient.prompt(new Prompt(List.of(systemMessage, userMessage))).call().chatResponse();
        return response != null && response.getResult() != null && response.getResult().getOutput() != null
                ? new Answer(response.getResult().getOutput().getText())
                : new Answer("Response not found.");
    }

}
