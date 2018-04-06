package com.example.web.alimentesebem.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.NutricionistaBean;
import com.example.web.alimentesebem.model.UsuarioBean;
import com.example.web.alimentesebem.rest.config.RetrofitConfig;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private ProgressBar mProgressView;
    private Intent intent;
    private TextView tvLogo, tvRealiza;
    private BarraProgresso barraProgresso = BarraProgresso.getInstance();
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private List<UsuarioBean> usuario;
    private List<NutricionistaBean> nutricionista;
    private SharedPreferences preferencesPut;
    private SharedPreferences.Editor editor;
    private boolean cancel = false;
    private View focusView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = findViewById(R.id.ed_email);
        loginButton = findViewById(R.id.btn_logar_face);
        populateAutoComplete();

        mPasswordView = findViewById(R.id.ed_senha);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        
        logarFacebook();

        Button mEmailSignInButton = (Button) findViewById(R.id.btn_logar);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                attemptLogin();
            }
        });

        mProgressView = findViewById(R.id.login_progress);

        tvLogo = findViewById(R.id.tv_logo_alimente_se);
        tvRealiza = findViewById(R.id.tv_realiza_2);
        Typeface typeFont = Typeface.createFromAsset(getAssets(), "fonts/tahu.ttf");
        tvLogo.setTypeface(typeFont);
        typeFont = Typeface.createFromAsset(getAssets(), "fonts/Gotham_Condensed_Bold.otf");
        tvRealiza.setTypeface(typeFont);
        preferencesPut = getSharedPreferences("KEY", getApplicationContext().MODE_PRIVATE);
        editor = preferencesPut.edit();

    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
     /*   if (mAuthTask != null) {
            return;
        }*/
        // Reset errors.

        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();



        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.erro_senha_incorreta));
            focusView = mPasswordView;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
                mPasswordView.setError(getString(R.string.erro_senha_n_preechida));
                focusView = mPasswordView;
                cancel = true;
            }
            // Check for a valid email address.
            if (TextUtils.isEmpty(email)) {
                mEmailView.setError(getString(R.string.erro_email_n_preechida));
                focusView = mEmailView;
                cancel = true;
            } else if (!isEmailValid(email)) {
                mEmailView.setError(getString(R.string.erro_email_invalido));
                focusView = mEmailView;
                cancel = true;
            }

            logarNutricionista(email, Long.parseLong(password));

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        boolean retorno = false;

        try{
            Long.parseLong(password);
            retorno = true;
        }catch(Exception e){
            retorno = false;
        }

        return retorno;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            barraProgresso.showProgress(false, mProgressView);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.erro_senha_incorreta));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            barraProgresso.showProgress(false, mProgressView);
        }
    }

    private void logarFacebook() {
        preferencesPut = getSharedPreferences("KEY", getApplicationContext().MODE_PRIVATE);
        boolean loggedIn;
        AccessToken.getCurrentAccessToken();
        if (Profile.getCurrentProfile() != null) {
            loggedIn = true;
        } else {
            loggedIn = false;
        }

        intent = new Intent(this, MainActivity.class);
        if (loggedIn) {

            finish();
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Olá ".concat(Profile.getCurrentProfile().getFirstName()), Toast.LENGTH_SHORT).show();
        }else if (!preferencesPut.getString("nome","defValue").equals("defValue")  && !preferencesPut.getString("nome","defValue").equals("defValue")){
            finish();
            startActivity(intent);
            Toast.makeText(this, "Olá " +preferencesPut.getString("nome","defValue"),Toast.LENGTH_SHORT).show();
        }

        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

        // AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (Profile.getCurrentProfile() != null) {
                    Toast.makeText(LoginActivity.this, "Olá ".concat(Profile.getCurrentProfile().getFirstName()), Toast.LENGTH_SHORT).show();
                }

                // Facebook Email address
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                Log.v("LoginActivity Response ", response.toString());

                                try {
                                    final String name = object.getString("name");
                                    final String email = object.getString("email");
                                    //verifica se ja existe um usuario com esse email se não existir realiza o cadastro
                                    getUsuario(email, name);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();

                finish();
                startActivity(intent);
            }

            @Override
            public void onCancel() {

                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("LoginActivity 2", exception.getMessage());
            }
        });


    }

    private void getUsuario(final String email, final String name) {
        //Salva usuario e Email nas preferencias

        editor.remove("nome");
        editor.remove("email");
        editor.commit();
        editor.putString("nome", name);
        editor.putString("email", email);
        editor.commit();

        email.replace("@", "%40");
        Call<List<UsuarioBean>> call = new RetrofitConfig().getRestInterface().getUsuarioEmail(email);
        call.enqueue(new Callback<List<UsuarioBean>>() {
            @Override
            public void onResponse(Call<List<UsuarioBean>> call, Response<List<UsuarioBean>> response) {

                if (response.isSuccessful()) {

                    usuario = response.body();
                    if (usuario.size() == 0) {
                        cadastraUsuario(name, email);
                        // Toast.makeText(LoginActivity.this,"Email 1:"+ usuario.get(0).getEmail(), Toast.LENGTH_SHORT).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<List<UsuarioBean>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("LoginActivity 1", t.getMessage());
            }

        });
    }

    private void logarNutricionista(final String email, final long nif) {

        email.replace("@", "%40");
        Call<List<NutricionistaBean>> call = new RetrofitConfig().getRestInterface().logarNutricionista(email, nif);
        call.enqueue(new Callback<List<NutricionistaBean>>() {
            @Override
            public void onResponse(Call<List<NutricionistaBean>> call, Response<List<NutricionistaBean>> response) {

                if (response.isSuccessful()) {

                    nutricionista = response.body();
                    if (nutricionista != null && nutricionista.size() > 0) {

                        editor.putBoolean("nutricionista", false);
                        editor.commit();

                        getUsuario(nutricionista.get(0).getEmail(), nutricionista.get(0).getNome());
                        Toast.makeText(LoginActivity.this,"Olá " +nutricionista.get(0).getNome() , Toast.LENGTH_SHORT).show();
                        cancel = false;
                    }else{

                            mEmailView.setError(getString(R.string.email_e_senha_invalidos));
                            focusView = mEmailView;

                        cancel = true;

                    }

                    if (cancel) {
                        // There was an error; don't attempt login and focus the first
                        // form field with an error.
                        focusView.requestFocus();
                    } else {

                        // Show a progress spinner, and kick off a background task to
                        // perform the user login attempt.
                        barraProgresso.showProgress(true, mProgressView);
                        //mAuthTask = new UserLoginTask(email, password);
                        // mAuthTask.execute((Void) null);
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<NutricionistaBean>> call, Throwable t) {
                mEmailView.setError(getString(R.string.email_e_senha_invalidos));
                focusView = mEmailView;
                cancel = true;
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("LoginActivity 1", t.getMessage());

                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else {

                    // Show a progress spinner, and kick off a background task to
                    // perform the user login attempt.
                    barraProgresso.showProgress(true, mProgressView);
                    //mAuthTask = new UserLoginTask(email, password);
                    // mAuthTask.execute((Void) null);
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }

    private void cadastraUsuario(final String name, final String email) {
        Call<ResponseBody> call2 = new RetrofitConfig().getRestInterface().cadastraUsuario(new UsuarioBean(name
                , email));

        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {

                    finish();
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(getApplicationContext(), R.string.falha_de_acesso, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }
}

