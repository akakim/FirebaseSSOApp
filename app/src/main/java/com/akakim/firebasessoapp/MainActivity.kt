package com.akakim.firebasessoapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    companion object {
        val RC_SIGN_IN = 9001
    }
    lateinit var mAuth              : FirebaseAuth
    lateinit var mAuthListener      : FirebaseAuth.AuthStateListener

    lateinit var mGoogleSignIn      : GoogleSignIn



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {
            var user = it.currentUser

            if (user == null ){
                LogUtil.LogVerbose(this@MainActivity,"유저가 로그아웃을 하였습니다.")
            } else {
                LogUtil.LogVerbose( this@MainActivity, "유저가 로그인 했습니다. " + user.uid )
            }
        }

        findViewById<Button>(R.id.edSubmit).setOnClickListener {
            mAuth.createUserWithEmailAndPassword(
                    edEmail.text.toString(),
                    edPwd.text.toString())
                    .addOnCompleteListener {
                        LogUtil.LogVerbose( this@MainActivity,
                                "이메일과 함께 생성 "
                        )

                    }

        }


        findViewById<Button>(R.id.btnGoogleSignin ).setOnClickListener{
            signIn()
        }

        findViewById<Button>(R.id.btnGoogleSignOut ).setOnClickListener{
            signOut()
        }

        findViewById<Button>(R.id.btnDisconnect ).setOnClickListener{
            revokeAccess()
        }

        // Config Google Sign in
        var gso = GoogleSignInOptions.Builder( GoogleSignInOptions.DEFAULT_SIGN_IN )
                .requestIdToken( getString( R.string.server_api_key ))
                .requestEmail()
                .build()

        GoogleSignIn.getClient( this , gso)
    }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener { mAuthListener }

        updateUI( mAuth.currentUser )
    }


    override fun onStop() {
        super.onStop()

        if( mAuthListener != null ){
            mAuth.removeAuthStateListener(mAuthListener)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from GoogleSigninApi.getSignInIntent로 부터 인텐트를 실행한다.

        if (requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent( data )

        }
    }


    fun firebaseAuthWithGoogle( acc : GoogleSignInAccount?){

    }
    fun updateUI(currentUser : FirebaseUser? ){

    }

    fun signIn(){

    }

    fun signOut(){

    }

    fun revokeAccess(){

    }
}
