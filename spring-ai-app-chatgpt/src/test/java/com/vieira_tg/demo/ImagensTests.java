package com.vieira_tg.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ImagensTests {

    @Autowired
    private ChatModel model;

    private ChatClient chatClient;


    @Value("classpath:/img.png")
    private Resource skynetImage;

    @Test
    @DisplayName("Teste de identificação de imagem")
    void visionTest() {
        chatClient = ChatClient
                .builder(model)
                .build();

        String response = chatClient
                .prompt()
                .user(u -> u.text("O que você vê nessa imagem?")
                        .media(MimeTypeUtils.IMAGE_JPEG, skynetImage))
                .call()
                .content();

        assertThat(response).containsIgnoringCase("join");

        System.out.println(response);
    }


}
