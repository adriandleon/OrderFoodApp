package com.japsystem.orderfoodapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = SignInActivity.class.getSimpleName();

    private FirebaseAuth mAuth;

    @BindView(R.id.email_editText) AppCompatEditText mEmailEditText;
    @BindView(R.id.password_editText) AppCompatEditText mPasswordEditText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }
    
    @OnClick(R.id.btnSignIn)
    public void onSignIn() {

        if (mEmailEditText.getText() == null) return;
        if (mPasswordEditText.getText() == null) return;

        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        final ProgressDialog mProgressDialog = new ProgressDialog(SignInActivity.this);
        mProgressDialog.setMessage("Login in. Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgressDialog.dismiss();

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            startActivity(HomeActivity.newIntent(SignInActivity.this));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "signInWithEmail:failure",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
