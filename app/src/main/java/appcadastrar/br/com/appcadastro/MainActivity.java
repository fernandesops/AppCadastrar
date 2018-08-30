package appcadastrar.br.com.appcadastro;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtNome,txtSobrenome, txtEmaiil, txtEndereco, txtCidade, txtUF, txtUserName, txtSenha;
    ImageView imgFoto;
    ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtNome = (EditText) findViewById(R.id.txtNome);
        EditText txtSobrenome = (EditText) findViewById(R.id.txtSobrenome);
        EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        EditText txtEndereco = (EditText) findViewById(R.id.txtEndereco);
        EditText txtCidade = (EditText) findViewById(R.id.txtCidade);
        EditText txtUF = (EditText) findViewById(R.id.txtUF);
        EditText txtUserName = (EditText) findViewById(R.id.txtUserName);
        EditText txtSenha = (EditText) findViewById(R.id.txtSenha);
        ImageView imgFoto = (ImageView) findViewById(R.id.imgFoto);


        //chama a classe buscarJson
        BuscarJson buscarJson = new BuscarJson();
        buscarJson.execute();

    }

    private class BuscarJson extends AsyncTask <Void, Void, Usuario> {


        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(MainActivity.this, "Por favor Aguarde......", "Lendo Arquivo JSON do servidor......");
        }

        @Override
        protected Usuario doInBackground(Void... params) {
            Utils utils = new Utils();

            return utils.getInformacao("https://randomuser.me/api/");
        }
    }

    @Override
    protected void onPostExecute(Usuario usuario) {
        txtNome.setText(usuario.getNome().substring(0,1).toUpperCase()+usuario.getNome().substring(1));
        txtSobrenome.setText(usuario.getSobrenome().substring(0,1).toUpperCase()+usuario.getSobrenome().substring(1));
        txtEmaiil.setText(usuario.getEmail().substring(0,1).toUpperCase()+usuario.getEmail().substring(1));
        txtEndereco.setText(usuario.getEndereco().substring(0,1).toUpperCase()+usuario.getEndereco().substring(1));
        txtCidade.setText(usuario.getCidade().substring(0,1).toUpperCase()+usuario.getCidade().substring(1));
        txtUserName.setText(usuario.getUsername());
        txtSenha.setText(usuario.getSenha());
        imgFoto.setImageBitmap(usuario.getFoto());

        //fecha a caixa do progressdiago
        load.dismiss();
    }
}