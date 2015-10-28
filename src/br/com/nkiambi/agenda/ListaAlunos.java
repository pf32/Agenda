package br.com.nkiambi.agenda;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.nkiambi.agenda.DAO.AlunoDAO;
import br.com.nkiambi.cadastro.modelo.Aluno;


@SuppressLint("NewApi")
public class ListaAlunos extends ActionBarActivity {
	private Aluno aluno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);
		
		ListView lista = (ListView)findViewById(R.id.lista);
		registerForContextMenu(lista);
		
		
		// Clique para alteração
		lista.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
			
				Aluno alunoClicado = (Aluno) adapter.getItemAtPosition(position);
				Intent irParaFormulario = new Intent(ListaAlunos.this, Formulario.class);
				irParaFormulario.putExtra("alunoSelecionado", alunoClicado);
				startActivity(irParaFormulario);
			}
		});
		
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {
			
			@Override
			
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
				 aluno = (Aluno)adapter.getItemAtPosition(position);
				
				return false;
			}
		});
			
		
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		MenuItem ligar = menu.add("Ligar");
		
		
		
		MenuItem enviarSms = menu.add("Enviar SMS");
		
		
	    MenuItem deletar =	menu.add("Excluir");
	    deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
			AlunoDAO dao = 	new AlunoDAO(ListaAlunos.this);
			dao.deletar(aluno);
			
			dao.close();
			recarregaLista();
			return false;
			}
		});
	    MenuItem verAlunoNoMapa = menu.add("Ver Aluno no Mapa");
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		recarregaLista();
	}
	
	private void recarregaLista(){
		AlunoDAO dao = new AlunoDAO(this);
		List<Aluno> alunos = dao.getLista();
		dao.close();
		
		int layout = android.R.layout.simple_list_item_1;
		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout, alunos);
		
		ListView lista = (ListView)findViewById(R.id.lista);
		lista.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lista_alunos, menu);
		return super.onCreateOptionsMenu(menu);
		
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	int itemClicado = item.getItemId();
		switch (itemClicado) {
		case R.id.novo:
		Intent irParaFormulario = new Intent (this, Formulario.class);
			startActivity(irParaFormulario);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
