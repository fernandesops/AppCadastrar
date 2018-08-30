package appcadastrar.br.com.appcadastro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetWorkUtils {

    //Responsável por carregar o Objeto JSON
    //em abrir uma conexão com o endereço onde os dados do
    // webservice estão localizados
    public static String getJSONFromAPI(String url){
        String retorno = "";
        try {
            URL apiEnd = new URL(url);
            int codigoResposta;

            //objeto responsável por estabelecer a conexão utilizando o
            //protocolo HTTP
            HttpURLConnection conexao;

            //responsável por receber o Stream do servidor
            InputStream is;

            // Isso significa que o tratamento de conexão aberta será feita com o
            // protocolo HTTP. O tipo de requisição é setado como GET e os
            // tempos de timeout também são setados para 15000 milissegundos.
            conexao = (HttpURLConnection) apiEnd.openConnection();

            //comunicação é estabelecida com o servidor utilizando
            // o protocolo HTTP e o tipo de requisição GET.
            conexao.setRequestMethod("GET");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect();

            //recebe o código da resposta
            //código de resposta inferior ao código contido na constante
            // HTTP_BAD_REQUEST, que tem o valor de 400, será dado ao atributo is
            // do tipo InputStream
            codigoResposta = conexao.getResponseCode();

            //Se a condição não for válida, um errorStream será atribuído.
            if(codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST){
                is = conexao.getInputStream();
            }else{
                is = conexao.getErrorStream();
            }

            //recebe o resultado do método, onde o is é encerrado e a conexão fechada.
            // Com isso o método já pode retornar o JSON para ser utilizado na aplicação.
            retorno = converterInputStreamToString(is);
            is.close();
            conexao.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        //retorna o arquivo convertido novamente em JSON
        return retorno;
    }


    //Como o retorno obtido anteriormente foi um InputStream, nossa aplicação
    // trabalharia muito melhor com uma String contendo o JSON completo e,
    // como é mais fácil fazer o parse, esse método auxiliar será responsável
    // por converter um InputStream para String.
    private static String converterInputStreamToString
    (InputStream is){

        //responsável por montar a string JSON a ser retornada.
        StringBuffer buffer = new StringBuffer();
        try{
            //responsável por fazer a leitura do buffer InputStream,
            // juntamente com uma String para receber a linha lida do
            // BufferedReader.
            BufferedReader br;
            String linha;

            //objeto br é inicializado recebendo um novo InputStreamReader, que nada mais é
            // do que a classe lida com o InputStream que foi passado por parâmetro.
            br = new BufferedReader(new InputStreamReader(is));

            //while recebe a linha atual e, caso seja diferente de nulo, essa linha
            // é inserida no StringBuffer, senão, o loop termina e a String montada
            // é retornada para o método principal.

            while((linha = br.readLine())!=null){
                buffer.append(linha);
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        //nota-se que sempre que quiser um StringBuffer retornado como String é
        // necessário invocar o método toString() dele.
        return buffer.toString();
    }
}
