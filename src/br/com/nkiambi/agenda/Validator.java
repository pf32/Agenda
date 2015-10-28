package br.com.nkiambi.agenda;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class Validator {
	
	public static boolean validateNotNull(View pView, String pMessage) {
		  if (pView instanceof EditText) {
		   EditText edText = (EditText) pView;
		   Editable text = edText.getText();
		   if (text != null) {
		    String strText = text.toString();
		    if (!TextUtils.isEmpty(strText)) {
		     return true;
		    }
		   }
		   // em qualquer outra condição é gerado um erro
		   edText.setError(pMessage);
		   edText.setFocusable(true);
		   edText.requestFocus();
		   return false;
		  }
		  return false;
		 }
		    public static boolean validateCPF(String CPF) {
		         CPF = Mask.unmask(CPF);
		         if (CPF.equals("00000000000") || CPF.equals("11111111111")
		         || CPF.equals("22222222222") || CPF.equals("33333333333")
		         || CPF.equals("44444444444") || CPF.equals("55555555555")
		         || CPF.equals("66666666666") || CPF.equals("77777777777")
		         || CPF.equals("88888888888") || CPF.equals("99999999999")) {
		             return false;
		         }
		         char dig10, dig11;
		         int sm, i, r, num, peso;
		         try {
		             sm = 0;
		             peso = 10;
		             for (i = 0; i < 9; i++) {
		                 num = (int) (CPF.charAt(i) - 48);
		                 sm = sm + (num * peso);
		                 peso = peso - 1;
		             }
		             r = 11 - (sm % 11);
		             if ((r == 10) || (r == 11))
		                 dig10 = '0';
		         else
		             dig10 = (char) (r + 48);
		         sm = 0;
		         peso = 11;
		         for (i = 0; i < 10; i++) {
		             num = (int) (CPF.charAt(i) - 48);
		            sm = sm + (num * peso);
		            peso = peso - 1;
		         }
		         r = 11 - (sm % 11);
		         if ((r == 10) || (r == 11))
		             dig11 = '0';
		             else
		                 dig11 = (char) (r + 48);
		             if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
		                 return (true);
		             else
		                 return (false);
		         } catch (Exception erro) {
		             return (false);
		         }
		    }
		 
		    public final static boolean validateEmail(String txtEmail) {
		        if (TextUtils.isEmpty(txtEmail)) {
		            return false;
		        } else {
		            return android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches();
		        }
		    }

}
