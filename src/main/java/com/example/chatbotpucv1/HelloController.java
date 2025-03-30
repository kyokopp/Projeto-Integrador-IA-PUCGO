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
        knowledgeBase.put("bom dia", "Bom dia! Em que posso ajudar?");
        knowledgeBase.put("boa tarde", "Boa tarde! Como posso ser útil hoje?");
        knowledgeBase.put("boa noite", "Boa noite! Como posso te ajudar ?");

        // Despedidas
        knowledgeBase.put("tchau", "Adeus! Tenha um ótimo dia!");
        knowledgeBase.put("adeus", "Até logo! Estou aqui se precisar de mais ajuda.");

        // pedido
        knowledgeBase.put("pedido", "Digite ``Cardápio´´ para ver o cardapio do dia !");
        knowledgeBase.put("sim", "Gostaria de uma sobremesa, entradas ou dos pratos principais ?");
        knowledgeBase.put("cardápio", "Gostaria de uma sobremesa, entradas ou dos pratos principais ?");
        knowledgeBase.put("pratos principais", "Os pratos de hoje são: Macaronada, Nhoque e Pizza !");
        knowledgeBase.put("sobremesa","As sobremesas são: Palha italiana, Tiramissu e Gellato");
        knowledgeBase.put("entradas", "As entradas são: Brusqueta, Tábua de frios e Salada tropical");
        knowledgeBase.put("brusqueta", "Qual o endereço para a entrega ?");
        knowledgeBase.put("tábua de frios", "Qual o endereço para a entrega ?");
        knowledgeBase.put("salada tropical", "Qual o endereço para a entrega ?");
        knowledgeBase.put("palha italiana", "Qual o endereço para a entrega ?");
        knowledgeBase.put("tiramissu", "Qual o endereço para a entrega ?");
        knowledgeBase.put("gellato", "Qual o endereço para a entrega ?");
        knowledgeBase.put("nhoque", "Qual o endereço para a entrega ?");
        knowledgeBase.put("macaronada","Qual o endereço para a entrega ?" );
        knowledgeBase.put("pizza","Qual o endereço para a entrega ?" );
        knowledgeBase.put("rua", "Seu pedido está sendo preparado e chegará em breve !");



        // Informacoes sobre o pedido (EXEMPLO)
        knowledgeBase.put("preparado", "Seu pedido está sendo preparado !");
        knowledgeBase.put("pagamento", "Qual será a forma de pagamento ?");
        knowledgeBase.put("dinheiro", "Ok pedido confirmado !");
        knowledgeBase.put("débito", "Ok pedido confirmado !");
        knowledgeBase.put("crédito" , "Ok pedido confirmado !");
        knowledgeBase.put("vale alimentação", "Ok pedido confirmado !");
        knowledgeBase.put("pix" , "Ok pedido confirmado !");
        knowledgeBase.put("formas de pagamentos aceitas", "Débito, Crédito, PIX, Vale alimentação");

        // espaco para adicionar futuras informacoes para o bot
        knowledgeBase.put("funcionamento", "O restaurante funcionará das 13:00 até 22:30");
        knowledgeBase.put("feedback", "Qual seria o feedback que gostaria de compartiihar" );
        knowledgeBase.put("melhorar", "Obrigado pelo feedback irei encaminha-lo aos responsáveis!");
        knowledgeBase.put("obrigado", "De nada! Precisa de alguma mais ajuda ?");
        knowledgeBase.put("obrigada", "De nada! Precisa de alguma mais ajuda ?");
        knowledgeBase.put("não", "Okay !");
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
            return "Posso responder perguntas sobre o pedido e cardápio. Como posso te ajudar hoje?";
        }

        // Resposta padrao para uma pergunta desconhecida ou fora do banco de dados
        return "Desculpe, não entendi sua pergunta. Poderia reformular ou perguntar algo sobre o pedido ?";
    }
}