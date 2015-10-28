package br.com.nkiambi.agenda.DAO;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.nkiambi.cadastro.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper{
	
	private static final  String DATABASE = "nkiambi";
	private static final  int version = 2;
	
	public AlunoDAO(Context context) {
		super(context, DATABASE, null, version);
		
	}

	
	public void salva(Aluno aluno) {
		
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("endereco", aluno.getEndereco());
		values.put("telefone", aluno.getTelefone());
		values.put("site", aluno.getSite());
		values.put("nota", aluno.getNota());
		values.put("foto", aluno.getFoto());
		
		getWritableDatabase().insert("Alunos", null, values);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String ddl = "CREATE TABLE Alunos(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT UNIQUE NOT NULL,"
				+ " site TEXT, telefone TEXT, endereco TEXT, nota REAL, foto TEXT);";
		db.execSQL(ddl);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
		String ddl ="DROP TABLE IF EXISTS Alunos";
		db.execSQL(ddl);
		this.onCreate(db);
	}


	public List<Aluno> getLista() {
		String[] columns = {"id", "nome", "site", "telefone", "endereco", "nota", "foto"};
		Cursor cursor = getWritableDatabase().query("Alunos", columns, null, null, null, null, "nome");
		
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		while(cursor.moveToNext()){
		
		Aluno aluno = new Aluno();
		aluno.setId(cursor.getLong(0));
		aluno.setNome(cursor.getString(1));
		aluno.setSite(cursor.getString(2));
		aluno.setTelefone(cursor.getString(3));
		aluno.setEndereco(cursor.getString(4));
		aluno.setNota(cursor.getDouble(5));
		aluno.setFoto(cursor.getString(6));
		
		alunos.add(aluno);
		}
		return alunos;
	
	}


	public void deletar(Aluno aluno) {
		try {
			String[] args = {aluno.getId().toString()};
			getWritableDatabase().delete("Alunos", "id=?", args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
