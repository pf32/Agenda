package br.com.nkiambi.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import br.com.nkiambi.cadastro.modelo.Aluno;

public class FormularioHelper {
	
	private EditText editNome;
	private EditText editSite;
	private EditText editEndereco;
	private EditText editTelefone;
	private RatingBar ratingNota;
	private ImageView foto;
	private Aluno aluno;
	
	
	
	public FormularioHelper(Formulario formulario) {
		
		 editNome =(EditText) formulario.findViewById(R.id.nome);
		 editSite     =(EditText)formulario.findViewById(R.id.site);
		 editEndereco =(EditText)formulario.findViewById(R.id.endereco);
		 editTelefone =(EditText)formulario.findViewById(R.id.telefone);
		 editTelefone.addTextChangedListener(Mask.insert("(##)####-#####", editTelefone));
		 ratingNota    =(RatingBar)formulario.findViewById(R.id.nota);
		 foto = (ImageView) formulario.findViewById(R.id.foto);
		 
		  aluno = new Aluno();
		 
		
	}

	public Aluno pegaAlunoDoFormulario() {
	//	Aluno aluno = new Aluno();
		
		aluno.setNome(editNome.getText().toString());
		aluno.setSite(editSite.getText().toString());
		aluno.setEndereco(editEndereco.getText().toString());
		aluno.setTelefone(editTelefone.getText().toString());
		aluno.setNota(Double.valueOf(ratingNota.getRating()));
		return aluno;
		
	}

	public void colocaAlunoNoFormulario(Aluno alunoParaSerAlterado) {
		aluno = alunoParaSerAlterado;
		editNome.setText(alunoParaSerAlterado.getNome());
		editSite.setText(alunoParaSerAlterado.getSite());
		editEndereco.setText(alunoParaSerAlterado.getEndereco());
		editTelefone.setText(alunoParaSerAlterado.getTelefone());
		ratingNota.setRating((alunoParaSerAlterado.getNota().floatValue()));
		
		if(aluno.getFoto()!= null ){
			carregaImagem(alunoParaSerAlterado.getFoto());
		}
	}

	public ImageView getFoto() {
		return foto;
	}

	public void carregaImagem(String caminhoArquivo) {
		
		aluno.setFoto(caminhoArquivo);
		Bitmap imagem = BitmapFactory.decodeFile(caminhoArquivo);
		Bitmap imgemReduzida = Bitmap.createScaledBitmap(imagem, 100, 100, true);
		foto.setImageBitmap(imgemReduzida);
	}
	

}
