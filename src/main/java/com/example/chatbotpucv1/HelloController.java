package com.example.chatbotpucv1;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class HelloController {

    @FXML
    private TextField userInput;  // Area para o input de texto do usuario

    @FXML
    private TextArea chatArea;  // Area que mostra o chat

    // Inicia o "Banco de dados" do bot
    private final Map<String, String> knowledgeBase = new HashMap<>();

    // Inicia o controlador
    @FXML
    public void initialize() {
        // Inicia o chat com uma mensagem de boas vindas
        chatArea.setText("Olá! Como posso te ajudar hoje?\n\n");

        // Define o "banco de dados/conhecimento" do bot
        setupKnowledgeBase();
    }

    // Define todas as interacoes/conhecimento do bot
    private void setupKnowledgeBase() {
        // Interacoes de boas vindas
        knowledgeBase.put("oi", "Olá! Como vai você?");
        knowledgeBase.put("olá", "Olá! Como posso ajudar?");
        knowledgeBase.put("hello", "Olá! Como posso ajudar?");
        knowledgeBase.put("bom dia", "Bom dia! Em que posso ajudar?");
        knowledgeBase.put("boa tarde", "Boa tarde! Como posso ser útil hoje?");
        knowledgeBase.put("boa noite", "Boa noite! Precisa de alguma ajuda?");

        // Despedidas
        knowledgeBase.put("tchau", "Adeus! Tenha um ótimo dia!");
        knowledgeBase.put("adeus", "Até logo! Estou aqui se precisar de mais ajuda.");
        knowledgeBase.put("bye", "Tchau! Volte logo!");

        // Informacoes sobre a puc (EXEMPLO)
        knowledgeBase.put("puc", "A PUC é uma universidade católica com excelente reputação acadêmica.");
        knowledgeBase.put("curso", "A PUC oferece diversos cursos de graduação e pós-graduação. Qual curso específico você gostaria de saber mais?");
        knowledgeBase.put("matrícula", "Para informações sobre matrícula, acesse o portal do aluno ou entre em contato com a secretaria acadêmica.");

        // espaco para adicionar futuras informacoes para o bot
    }

    @FXML
    protected void sendMessage() {
        // Garante que a UI foi inicializada corretamente
        if (userInput == null || chatArea == null) {
            System.err.println("Erro: Os componentes da UI não foram iniciados corretamente");
            return;
        }

        String inputText = userInput.getText();

        // Verifica se o input de texto não está vazio ou nulo
        if (inputText == null || inputText.trim().isEmpty()) {
            return;  // Logo não enviará mensagens vazias
        }

        inputText = inputText.trim();

        // Adiciona o horário das mensagens
        String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

        // Exibe a mensagem do usuario contendo o horario no chat
        chatArea.appendText("[" + timestamp + "] Você: " + inputText + "\n");

        // Gera e exibe uma resposta contendo o horário vinda do bot
        String botResponse = generateResponse(inputText);
        chatArea.appendText("[" + timestamp + "] Bot: " + botResponse + "\n\n");

        // Limpa o input para novas mensagens
        userInput.clear();

        // Reseta o foco para o input do usuario
        userInput.requestFocus();
    }

    private String generateResponse(String userMessage) {
        // Converte letras para minuscula para garantir erros por conta do case sensitive
        String lowerCaseMessage = userMessage.toLowerCase();

        // Checa se a mensagem do usuario tem algum termo do banco de dados do bot
        for (Map.Entry<String, String> entry : knowledgeBase.entrySet()) {
            if (lowerCaseMessage.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        // Gere a funcao de ver quantas horas sao
        if (lowerCaseMessage.contains("hora") || lowerCaseMessage.contains("horas")) {
            LocalTime now = LocalTime.now();
            return "Agora são " + now.format(DateTimeFormatter.ofPattern("HH:mm")) + ".";
        }

        // Gere os pedidos de suporte
        if (lowerCaseMessage.contains("ajuda") || lowerCaseMessage.contains("help")) {
            return "Posso responder perguntas sobre a PUC, cursos, matrícula e fornecer informações gerais. Como posso te ajudar hoje?";
        }

        // Resposta padrao para uma pergunta desconhecida ou fora do banco de dados
        return "Desculpe, não entendi sua pergunta. Poderia reformular ou perguntar algo sobre a PUC?"; // PUC É SO UM EXEMPLO TEMPORARIO
    }
}