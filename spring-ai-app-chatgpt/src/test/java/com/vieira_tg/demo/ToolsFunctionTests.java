package com.vieira_tg.demo;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@SpringBootTest
class ToolsFunctionTests {

	@Autowired
	private ChatModel model;

	private ChatClient chatClient;

	@Test
	@DisplayName("Teste de execução de funções (Tools)")
	void teste_1(){
		chatClient = ChatClient
				.builder(model)
				.build();

		val response = chatClient.prompt()
				.system("Calcule a quantidade de caracter da resposta")
				.user("Como se deve utilizar a toalha?")
				.functions("lengthResponseService")
				.call()
				.chatResponse()
				.getResult();

		System.out.println(response);
	}
}
