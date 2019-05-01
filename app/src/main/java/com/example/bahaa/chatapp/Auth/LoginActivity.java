package com.example.bahaa.chatapp.Auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bahaa.chatapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity {
    //Mail Auth
    @BindView(R.id.register_text)
    TextView regText;
    @BindView(R.id.login_mail)
    EditText loginMail;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_password_layout)
    TextInputLayout loginPasswordLayout;
    @BindView(R.id.mail_login)
    Button mailLoginButton;
    @BindDrawable(R.drawable.ic_error)
    Drawable errorIcon;

    //Firebase
    private FirebaseAuth mAuth;

    //Butterknife
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //inject Views via Butterknife..
        unbinder = ButterKnife.bind(this);

        //Prevent keyboard from automatic popping up once onCreate called..
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            navigateToActivity();
        }
    }

    private void navigateToActivity() {
    }

    @OnClick(R.id.mail_login)
    void validateLoginData() {
        final String loginMailStr = loginMail.getText().toString();
        final String loginPasswordStr = loginPassword.getText().toString();
        errorIcon.setBounds(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight());


        if (!isValidEmail(loginMailStr)) {
            loginMail.setError(getString(R.string.invalid_mail), errorIcon);

        }

        if (!isValidPassword(loginPasswordStr)) {
            loginPassword.setError(getString(R.string.pass_char_less), errorIcon);

            //Hide password Toggle icon to avoid icons overlay
            loginPasswordLayout.setPasswordVisibilityToggleEnabled(false);

            //Reveal password Toggle icon again once user restart typing
            callTextWatcher(loginPassword, loginPasswordLayout);
        }
        if (hasErrors()) {
            Toast.makeText(getApplicationContext(), R.string.login_data_problem, Toast.LENGTH_LONG).show();
        } else {
            performLogin(loginMailStr, loginPasswordStr);
        }
    }


    // validating email id
    private boolean isValidEmail(String email) {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        return !TextUtils.isEmpty(pass) && pass.length() > 7;
    }


    private void callTextWatcher(EditText editText, final TextInputLayout layout) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layout.setPasswordVisibilityToggleEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean hasErrors() {
        CharSequence mailError = loginMail.getError();
        CharSequence passError = loginPassword.getError();

        return mailError != null || passError != null;
    }

    private void performLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        navigateToActivity();
                        Toast.makeText(LoginActivity.this, "Success!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.i("Statuss", "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_LONG).show();
                    }

                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Free up memory from views
        unbinder.unbind();
    }
}
