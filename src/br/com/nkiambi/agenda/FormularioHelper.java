package br.com.nkiambi.agenda;

import android.widget.EditText;
import android.widget.RatingBar;
import br.com.nkiambi.cadastro.modelo.Aluno;

public class FormularioHelper {
	
	private EditText editNome;
	private EditText editSite;
	private EditText editEndereco;
	private EditText editTelefone;
	private RatingBar ratingNota;

	
	
	
	public FormularioHelper(Formulario formulario) {
		
		 editNome =(EditText) formulario.findViewById(R.id.nome);
		 editSite     =(EditText)formulario.findViewById(R.id.site);
		 editEndereco =(EditText)formulario.findViewById(R.id.endereco);
		 editTelefone =(EditText)formulario.findViewById(R.id.telefone);
		 ratingNota    =(RatingBar)formulario.findViewById(R.id.nota);
		
	}

	public Aluno pegaAlunoDoFormulario() {
		Aluno aluno = new Aluno();
		
		aluno.setNome(editNome.getText().toString());
		aluno.setSite(editSite.getText().toString());
		aluno.setEndereco(editEndereco.getText().toString());
		aluno.setTelefone(editTelefone.getText().toString());
		aluno.setNota(Double.valueOf(ratingNota.getRating()));
		
		return aluno;
		
	}

}
