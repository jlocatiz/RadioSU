package com.example.radiosu;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Session.StatusCallback;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;



@SuppressWarnings("unused")
public class Login extends Activity {
	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = new Session.StatusCallback() {
		
		@Override
		public void call(Session session, SessionState state, Exception exception) {
			onSessionStateChanged(session, state, exception);
			
		}
	};
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_login);
		
		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);
	
	
		LoginButton lb =(LoginButton) findViewById(R.id.authButton);
		lb.setPublishPermissions(Arrays.asList("email", "public_profile", "user_friends"));
	}
		public void conectarOnClick(View v){
			Intent intent = new Intent(this, com.example.radiosu.Menu.class);
			startActivity(intent);

		}
			
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		Session session = Session.getActiveSession();
		if(session != null && (session.isClosed() || session.isOpened())){
			onSessionStateChanged(session, session.getState(),null);
			Intent intent = new Intent(this, com.example.radiosu.Menu.class);
			startActivity(intent);
			session = null;
						
        }
		
		uiHelper.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		uiHelper.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		uiHelper.onDestroy();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		uiHelper.onSaveInstanceState(bundle);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}


//METHODS FACEBOOK
public void onSessionStateChanged(Session session, SessionState state, Exception exception){
	if(session != null && session.isOpened()){
		Log.i("Script", "Usuário conectado");
		Request.newMeRequest(session, new Request.GraphUserCallback() {
			@Override
			public void onCompleted(GraphUser user, Response response) {
				if(user != null){
										
				}
			}
		}).executeAsync();
	}
	else{
		Log.i("Script", "Usuário não conectado");
	}
	}

}
