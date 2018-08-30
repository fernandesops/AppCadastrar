package appcadastrar.br.com.appcadastro;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Utils {

    public Usuario getInformacao(String url) {
        String json;
        Usuario retorno;
        json = NetWorkUtils.getJSONFromAPI(url);

        //
        retorno = converterJson(json);

        return retorno;
    }

    private Usuario converterJson(String json) {

        try{
            Usuario usuario = new Usuario();

            //JSONObject é instanciado passando por parâmetro o string que contem o objeto
            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("results");

            //JSONObject é declarado e todos os objetos que estão contidos na primeira posição desse array de JSON são colocados nele
            JSONObject objArray = array.getJSONObject(0);

            //atribui os objetos que estão nas camadas mais altas
            JSONObject nome = objArray.getJSONObject("name");
            //nome da pessoa é um objeto, instancia um novo objeto
            //JSONOject
            usuario.setNome(nome.getString("first"));
            usuario.setSobrenome(nome.getString("last"));

            JSONObject endereco = objArray.getJSONObject("location");
            //nome da pessoa é um objeto, instancia um novo JSONObject
            usuario.setEndereco(endereco.getString("street"));
            usuario.setCidade(endereco.getString("city"));
            usuario.setUf(endereco.getString("state"));

            usuario.setEmail(objArray.getString("email"));

            JSONObject login = objArray.getJSONObject("login");
            //login da pessoa é um objeto, instancia um novo JSONObject
            usuario.setUsername(login.getString("username"));
            usuario.setSenha(login.getString("password"));

            JSONObject foto = objArray.getJSONObject("picture");
            usuario.setFoto(baixarImagem(foto.getString("large")));

            //objeto setado com os valores do arquivo JSON
            return usuario;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    //metodo para fazer o donwload da imagem converte um InputStream em imagem do tipo bitmap
    //utilizando o metodo estatico descodeStream da classe BitmapFactory
    private Bitmap baixarImagem(String url) {
        try {
            URL endereco;
            InputStream inputStream;
            Bitmap imagem;
            endereco = new URL(url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
