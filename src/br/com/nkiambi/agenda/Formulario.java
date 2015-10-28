package br.com.nkiambi.agenda;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.nkiambi.agenda.DAO.AlunoDAO;
import br.com.nkiambi.cadastro.modelo.Aluno;

@SuppressWarnings("deprecation")
public class Formulario extends ActionBarActivity {
	
	
	private FormularioHelper helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
	   helper = new FormularioHelper(this);
	   
	   Button botao = (Button) findViewById(R.id.gravar);
	   
	   botao.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			 Aluno aluno = helper.pegaAlunoDoFormulario();
			 AlunoDAO dao = new AlunoDAO(Formulario.this);
			   dao.salva(aluno);
			   dao.close();
			   finish();
				
		}
	});
	  
	}

}
