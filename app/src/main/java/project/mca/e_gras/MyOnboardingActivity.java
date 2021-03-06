package project.mca.e_gras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.marcoscg.dialogsheet.DialogSheet;

import java.util.Arrays;
import java.util.List;


public class MyOnboardingActivity extends AppIntro {

    private static final int RC_SIGN_IN = 200;

    private boolean fromMain = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        fromMain = intent.getBooleanExtra("from_main", false);


        //create slides and add them
        SliderPage s1 = new SliderPage();
        s1.setTitle(getString(R.string.ob_welcome_msg));
        s1.setTitleColor(getResources().getColor(R.color.onBoardingTextColor));
        s1.setDescription(getString(R.string.label_egras_info));
        s1.setDescColor(getResources().getColor(R.color.onBoardingTextColor));
        s1.setImageDrawable(R.drawable.logo);
        s1.setBgColor(getResources().getColor(R.color.colorBackground));
        addSlide(AppIntroFragment.newInstance(s1));

        SliderPage s2 = new SliderPage();
        s2.setTitle(getString(R.string.ob_generate_challan));
        s2.setTitleColor(getResources().getColor(R.color.onBoardingTextColor));
        s2.setDescription(getString(R.string.label_generate_echallan));
        s2.setDescColor(getResources().getColor(R.color.onBoardingTextColor));
        s2.setImageDrawable(R.drawable.invoice);
        s2.setBgColor(getResources().getColor(R.color.colorBackground));
        addSlide(AppIntroFragment.newInstance(s2));

        SliderPage s3 = new SliderPage();
        s3.setTitle(getString(R.string.ob_search_challan));
        s3.setTitleColor(getResources().getColor(R.color.onBoardingTextColor));
        s3.setDescription(getString(R.string.label_egras_serach_challan));
        s3.setDescColor(getResources().getColor(R.color.onBoardingTextColor));
        s3.setImageDrawable(R.drawable.search);
        s3.setBgColor(getResources().getColor(R.color.colorBackground));
        addSlide(AppIntroFragment.newInstance(s3));

        SliderPage s4 = new SliderPage();
        s4.setTitle(getString(R.string.ob_review_transaction));
        s4.setTitleColor(getResources().getColor(R.color.onBoardingTextColor));
        s4.setDescription(getString(R.string.label_review_trans));
        s4.setDescColor(getResources().getColor(R.color.onBoardingTextColor));
        s4.setImageDrawable(R.drawable.payment_history);
        s4.setBgColor(getResources().getColor(R.color.colorBackground));
        addSlide(AppIntroFragment.newInstance(s4));


        // divider color
        setBarColor(getResources().getColor(R.color.colorPrimaryDark));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);


        // Set silde animations
        setDepthAnimation();
    }


    private void routeToAppropriateScreen() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // User is already signed in
            // verify token at the backend

            signInUser();
        } else {
            // No user is signed in
            // Show the Firebase Auth UI

            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.PhoneBuilder().build());

            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setTheme(R.style.AppTheme)
                            .setLogo(R.drawable.image)
                            .setIsSmartLockEnabled(false)
                            .build(),
                    RC_SIGN_IN);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                // verify token at the backend

                signInUser();
            } else {
                if (response != null) {
                    showError(response.getError().getMessage());
                }
            }
        }
    }


    private void showError(String message) {
        // show the bottomSheet dialogSheet

        new DialogSheet(this)
                .setTitle(R.string.error_label_bottom_dialog)
                .setMessage(message)
                .setColoredNavigationBar(true)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogSheet.OnPositiveClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close the dialogSheet
                    }
                })
                .setRoundedCorners(true)
                .setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground))
                .show();
    }


    private void signInUser() {
        if (!fromMain) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        finish();
    }


    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.

        // let the user sign-up or log-in
        routeToAppropriateScreen();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.

        // let the user sign-up or log-in
        routeToAppropriateScreen();
    }
}

