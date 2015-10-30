package br.com.nkiambi.agenda;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import br.com.nkiambi.agenda.DAO.AlunoDAO;
import br.com.nkiambi.cadastro.modelo.Aluno;

@SuppressWarnings("deprecation")
public class Formulario extends ActionBarActivity {
	
	
	private FormularioHelper helper;
	private String caminhoArquivo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		
		Intent intent =  getIntent();
		final Aluno alunoParaSerAlterado = (Aluno)intent.getSerializableExtra("alunoSelecionado");
		//Toast.makeText(this, "aluno:" + alunoParaSerAlterado, Toast.LENGTH_LONG).show();
		
	    helper = new FormularioHelper(this);
	   
	   Button botao = (Button) findViewById(R.id.gravar);
	   if(alunoParaSerAlterado !=null){
		   botao.setText("Alterar");
		   helper.colocaAlunoNoFormulario(alunoParaSerAlterado);
	   }
	   
	   botao.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			 Aluno aluno = helper.pegaAlunoDoFormulario();
			 
			 if(aluno.getNome() == null || aluno.getNome().isEmpty()){
				 Toast.makeText(Formulario.this, "Nome é de Preenchimento Obrigatório!", Toast.LENGTH_LONG).show();
			
				 
			 }else if(aluno.getEndereco() == null || aluno.getEndereco().isEmpty()){
				 Toast.makeText(Formulario.this, "Endereço é de Preenchimento Obrigatório!!", Toast.LENGTH_LONG).show();
			 }else if(aluno.getTelefone() == null || aluno.getTelefone().isEmpty()){
				 Toast.makeText(Formulario.this, "Telefone  é de Preenchimento Obrigatório!!", Toast.LENGTH_LONG).show();
			 
			 } else if(aluno.getNota()== null || aluno.getNota().isInfinite()){
				 Toast.makeText(Formulario.this, "Nota  é de Preenchimento Obrigatório!", Toast.LENGTH_LONG).show();
			 
			 }else if((aluno.getNome()== null || aluno.getNome().isEmpty()) && (aluno.getEndereco()== null || aluno.getEndereco().isEmpty())
					 && (aluno.getNota()== null || aluno.getNota().isInfinite()) && (aluno.getSite()== null|| aluno.getSite().isEmpty()) &&
					( aluno.getTelefone()== null || aluno.getTelefone().isEmpty())){ 
				     Toast.makeText(Formulario.this, "Favor Preencher dados do Formulário!", Toast.LENGTH_LONG).show();
			 
			 } else{
				 AlunoDAO dao = new AlunoDAO(Formulario.this);
				 if(alunoParaSerAlterado == null){
					 dao.salva(aluno);
					 Toast.makeText(Formulario.this, "Inclusão Realizada com Sucesso!", Toast.LENGTH_LONG).show();
				 }else {
					 aluno.setId(alunoParaSerAlterado.getId());
					 dao.altera(aluno);
					 Toast.makeText(Formulario.this, "Alteração Realizada com Sucesso!", Toast.LENGTH_LONG).show();
				 }
				 dao.close();
				   finish();
			 }
		}
	});
	      
	   ImageView foto = helper.getFoto();
	   foto.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		
		Intent  irParaCamera = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
		caminhoArquivo = Environment.getExternalStorageDirectory().toString()+"/"+ System.currentTimeMillis()+".png";

		File arquivo = new File(caminhoArquivo);
     	Uri localimagem =	Uri.fromFile(arquivo);
		irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localimagem );
		startActivityForResult(irParaCamera, 123);
			
		}
	});
	   
	  
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 123){
			if(resultCode == Activity.RESULT_OK){
				helper.carregaImagem(caminhoArquivo);
			   }else{
				   caminhoArquivo = null;
		    }
		}
		//super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	
@Override
public boolean onCreateOptionsMenu(Menu menu) {
	
	return super.onCreateOptionsMenu(menu);
}
}
