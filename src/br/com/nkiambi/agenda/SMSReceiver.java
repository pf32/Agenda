package br.com.nkiambi.agenda;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;
import br.com.nkiambi.agenda.DAO.AlunoDAO;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
	Object[] mensagens = (Object[]) intent.getExtras().get("pdus");
	byte[] primeira = (byte[]) mensagens[0];
	SmsMessage sms = SmsMessage.createFromPdu(primeira);

	String telefone = sms.getDisplayOriginatingAddress();
	AlunoDAO dao = new AlunoDAO(context);
	boolean ehAluno = dao.isAluno(telefone);
	dao.close();
	
	if(ehAluno){
		MediaPlayer platey = 	MediaPlayer.create(context, R.raw.sms);
		platey.start();
		Toast.makeText(context, "Tocando Musica...", Toast.LENGTH_LONG).show();
	}
	
	
	}

}
