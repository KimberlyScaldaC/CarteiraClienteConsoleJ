/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carteiraclienteconsolej;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

public class CarteiraClienteConsoleJ {

    // URL base do serviço REST da API de Carteiras
    private static final String BASE_URL = "http://localhost:5287/api/Carteiras";

    /**
     * Ponto de entrada da aplicação.
     * Executa duas chamadas GET e uma POST para demonstrar operações CRUD básicas.
     * @param args argumentos de linha de comando (não utilizados)
     * @throws Exception em caso de erro de conexão ou leitura
     */
    public static void main(String[] args) throws Exception {
        // Exibe carteiras existentes antes da inclusão
        getCarteira();
        
        // JSON de exemplo para criação de uma nova carteira
        String novaCarteiraJson = "{\"Nome\": \"Avem J\"," +
                                 "\"Moeda\": \"Cripto J\"," +
                                 "\"Saldo\": 4400.75 }";
        // Envia a nova carteira para o servidor
        postCarteira(novaCarteiraJson);
        
        // Exibe carteiras após a inclusão da nova
        getCarteira();
    }
    
    /**
     * Realiza uma requisição HTTP GET para obter todas as carteiras.
     * @throws Exception em caso de falha na conexão ou leitura da resposta
     */
    private static void getCarteira() throws Exception{
        // Abre conexão HTTP com o método GET
        var conn = (HttpURLConnection) new URI(BASE_URL).toURL().openConnection();
        conn.setRequestMethod("GET");
        
        // Mostra código de resposta HTTP
        System.out.println("Resposta GET (Código " + conn.getResponseCode() + ")");
        // Imprime linha a linha o conteúdo da resposta
        printResponse(conn);
    }
    
    /**
     * Realiza uma requisição HTTP POST para criar uma nova carteira.
     * @param json payload JSON contendo os dados da carteira
     * @throws Exception em caso de falha na conexão ou leitura da resposta
     */
    private static void postCarteira(String json) throws Exception{
        // Abre conexão HTTP com o método POST
        var conn = (HttpURLConnection) new URI(BASE_URL).toURL().openConnection();
        conn.setRequestMethod("POST");
        // Define cabeçalho indicando JSON no corpo da requisição
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true); // Habilita envio de dados no corpo
        
        // Envia o JSON através do OutputStream
        var os = conn.getOutputStream();
        os.write(json.getBytes("UTF-8"));
        
        // Mostra código de resposta HTTP
        System.out.println("Resposta POST (Código " + conn.getResponseCode() + ")");
        // Imprime a resposta do servidor
        printResponse(conn);
    }
    
    /**
     * Lê e exibe o conteúdo da resposta HTTP da conexão fornecida.
     * @param conn conexão HTTP já configurada e conectada
     * @throws Exception em caso de erro de leitura
     */
    private static void printResponse(HttpURLConnection conn) throws Exception{
        // Prepara leitor para o fluxo de entrada da conexão
        var br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        // Enquanto houver linhas, exibe no console
        while ((line = br.readLine()) != null)
            System.out.println(line);
    }
    
}

