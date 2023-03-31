package com.wilp.simpledemoapiexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView emailTextView,firstNameTextView,lastNameTextView,createdAtTextView,rolesTextView;
    private TextView availableAssetsCountTextView, checkoutAssetsCountTextView, disposedAssetsCountTextView, lostAssetsCountTextView;
    private TextView totalAssetsCountTextView ,totalCostOfAssetsTextView ,assetCountLastYearTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailTextView = findViewById(R.id.email_value);
        firstNameTextView = findViewById(R.id.first_name_value);
        lastNameTextView = findViewById(R.id.last_name_value);
        createdAtTextView = findViewById(R.id.created_at_value);
        rolesTextView = findViewById(R.id.roles_value);

        availableAssetsCountTextView = findViewById(R.id.availableAssetsCountValue);
        checkoutAssetsCountTextView = findViewById(R.id.checkoutAssetsCountValue);
        disposedAssetsCountTextView = findViewById(R.id.disposedAssetsCountValue);
        lostAssetsCountTextView = findViewById(R.id.lostAssetsCountValue);

        totalAssetsCountTextView = findViewById(R.id.totalAssetsCountValue);
        totalCostOfAssetsTextView = findViewById(R.id.totalCostOfAssetsValue);
        assetCountLastYearTextView = findViewById(R.id.assetCountLastYearValue);

        ApiClient.getLauncherApiData(
                "application/json",
                "berer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEwLCJpc3MiOiJodHRwczovL3d3dy5ncmV5dHJ1bmtyZmlkLmNvbS9hcGkvdjEvYXV0aCIsImlhdCI6MTY3OTU1MTMzMCwiZXhwIjoxNjgwODM1MzMwLCJuYmYiOjE2Nzk1NTEzMzAsImp0aSI6Im5sRE5BMkpmcndDbG1vbDgifQ.mir6a4iYtoaQ8MBKp_du5RXJUXMD3piW4HAKWjGNQwM",
                "android",
                "IqJit4XEaiM71D2tBPauHG6dS78BfuMLQuJVZiwsw6Y=",
                new Callback<LauncherApi>() {
                    @Override
                    public void onResponse(@NonNull Call<LauncherApi> call, @NonNull Response<LauncherApi> response) {
                        if(response.isSuccessful()){

                            LauncherApi launcherApi = response.body();
                            // userDetails data getting from the API
                            String firstName = Objects.requireNonNull(launcherApi).getUserDetails().getFirstName();
                            String lastName = launcherApi.getUserDetails().getLastName();
                            String email = launcherApi.getUserDetails().getEmail();
                            String roles = launcherApi.getUserDetails().getRoles();
                            String created_at = launcherApi.getUserDetails().getCreatedAt();

                            firstNameTextView.setText(firstName);
                            lastNameTextView.setText(lastName);
                            emailTextView.setText(email);
                           rolesTextView.setText(roles);
                            createdAtTextView.setText(created_at);

                            // Dashboard Data is fetched from the API

                            String availableAssetsCount = String.valueOf(launcherApi.getmDashbaordData().getAssetCountByStatus().getAvailableAssetsCount());
                            String checkoutAssetsCount = String.valueOf(launcherApi.getmDashbaordData().getAssetCountByStatus().getCheckoutAssetsCount());
                            String disposedAssetsCount = String.valueOf(launcherApi.getmDashbaordData().getAssetCountByStatus().getDisposedAssetsCount());
                            String lostAssetsCount = String.valueOf(launcherApi.getmDashbaordData().getAssetCountByStatus().getLostAssetsCount());
                            String totalAssetsCount = String.valueOf(launcherApi.getmDashbaordData().getAssetCountData().getTotalAssetsCount());
                            String totalCostOfAssets = launcherApi.getmDashbaordData().getAssetCountData().getTotalCostOfAssets();
                            String assetCountLastYear = String.valueOf(launcherApi.getmDashbaordData().getAssetCountData().getAssetCountLastYear());

                            availableAssetsCountTextView.setText(availableAssetsCount);
                            checkoutAssetsCountTextView.setText(checkoutAssetsCount);
                            disposedAssetsCountTextView.setText(disposedAssetsCount);
                            lostAssetsCountTextView.setText(lostAssetsCount);
                           totalAssetsCountTextView.setText(totalAssetsCount);
                            totalCostOfAssetsTextView.setText(totalCostOfAssets);
                            assetCountLastYearTextView.setText(assetCountLastYear);

                        }else{

                            Log.e("API CALL","Hello  Error");

                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LauncherApi> call, @NonNull Throwable t) {

                    }
                }

        );
    }




}
