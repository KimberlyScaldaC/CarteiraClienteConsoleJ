/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carteiraclienteconsolej;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

/**
 *
 * @author Mancer
 */
public class CarteiraClienteConsoleJ {

    private static final String BASE_URL = "http://localhost:5287/api/Carteiras";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        getCarteira();
        
        String novaCarteiraJson = "{\"Nome\": \"Avem J\"," +
                                 "\"Moeda\": \"Cripto J\","+
                                "\"Saldo\": 4400.75 }";
        postCarteira(novaCarteiraJson);
        
        getCarteira();
    }
    
    private static void getCarteira() throws Exception{
        var conn = (HttpURLConnection) new URI(BASE_URL).toURL().openConnection();
        conn.setRequestMethod("GET");
        System.out.println("Resposta GET (Código " + conn.getResponseCode() + ")");
        printResponse(conn);
    }
    
    private static void postCarteira(String json) throws Exception{
        var conn = (HttpURLConnection) new URI(BASE_URL).toURL().openConnection();
        
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        
        var os = conn.getOutputStream();
        os.write(json.getBytes("UTF-8"));
        
        System.out.println("Resposta POST (Código " + conn.getResponseCode() + ")");
        printResponse(conn);
    }
    
    private static void printResponse(HttpURLConnection conn) throws Exception{
        var br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        String line;
        while ((line = br.readLine()) != null)
            System.out.println(line);
    }
    
}
