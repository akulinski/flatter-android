package com.kpg.flatter.activities;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.kpg.flatter.R;
import com.kpg.flatter.core.SharedPreferencesWraper;
import com.kpg.flatter.core.application.FlatterCore;
import com.kpg.flatter.core.exceptions.SharedPreferenceValueNotFoundException;
import com.kpg.flatter.eventbus.events.QuestionnaireEvent;
import com.kpg.flatter.requests.ApiClient;
import com.kpg.flatter.requests.ApiInterface;
import com.kpg.flatter.requests.callbacks.QuestionnaireCallback;
import com.kpg.flatter.requests.models.QuestionnairePostModel;
import com.kpg.flatter.utills.Status;
import com.kpg.flatter.utills.Urls;

import net.igenius.customcheckbox.CustomCheckBox;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;


public class QuestionnaireActivity extends AppCompatActivity {

    @BindView(R.id.confirmButton)
    Button confirmButton;

    @BindView(R.id.roomAmountRangeSeekBar)
    RangeSeekBar roomAmountRangeSeekBar;
    private static float roomAmountLeftValue = 3, roomAmountRightValue = 8;

    @BindView(R.id.sizeRangeSeekBar)
    RangeSeekBar sizeRangeSeekBar;
    private static float sizeLeftValue = 100, sizeRightValue = 900;

    @BindView(R.id.totalCostRangeSeekBar)
    RangeSeekBar totalCostRangeSeekBar;
    private static float totalCostLeftValue = 4000, totalCostRightValue = 900000;

    @BindView(R.id.constructionRangeSeekBar)
    RangeSeekBar constructionRangeSeekBar;
    private static float constructionLeftValue = 1900, constructionRightValue = 2000;

    @BindView(R.id.flatCustomCheckBox)
    CustomCheckBox flatCustomCheckBox;

    @BindView(R.id.houseCustomCheckBox)
    CustomCheckBox houseCustomCheckBox;

    @BindView(R.id.furnishedCustomCheckBox)
    CustomCheckBox furnishedCustomCheckBox;

    @BindView(R.id.petsCustomCheckBox)
    CustomCheckBox petsCustomCheckBox;

    @BindView(R.id.smokingCustomCheckBox)
    CustomCheckBox smokingCustomCheckBox;

    @BindView(R.id.textTop)
    TextView textTop;

    private ApiInterface apiService;
    private QuestionnaireCallback questionnaireCallback;
    private EventBus eventBus;
    private SharedPreferencesWraper sharedPreferencesWraper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnarieview);
        ((FlatterCore) getApplication()).getQuestionnaireActivityComponent().inject(this);

        try {
            apiService = new ApiClient(Urls.SERVER.url)
                    .getClientWithInterceptor(sharedPreferencesWraper.readStringFromPreferences("TOKEN"))
                    .create(ApiInterface.class);
        } catch (SharedPreferenceValueNotFoundException e) {
            e.printStackTrace();
        }

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        ButterKnife.bind(this);
        eventBus.register(new QuestionnaireEventListener());

        setUpValue();
    }


    private void setUpValue() {
        textTop.setText("Questionnaire");

        setUpRangeSeekBar(roomAmountRangeSeekBar, 1, 10, 2, 8);
        setUpRangeSeekBar(sizeRangeSeekBar, 1, 1000, 100, 900);
        setUpRangeSeekBar(constructionRangeSeekBar, 1800, 2019, 1900, 2000);
        setUpRangeSeekBar(totalCostRangeSeekBar, 100, 1000000, 4000, 900000);

        roomAmountRangeSeekBar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                roomAmountLeftValue = leftValue;
                roomAmountRightValue = rightValue;
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
        sizeRangeSeekBar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                sizeLeftValue = leftValue;
                sizeRightValue = rightValue;
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
        constructionRangeSeekBar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                constructionLeftValue = leftValue;
                constructionRightValue = rightValue;
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
        totalCostRangeSeekBar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                totalCostLeftValue = leftValue;
                totalCostRightValue = rightValue;
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });

    }

    private void setUpRangeSeekBar(RangeSeekBar rangeSeekBar, float minValue, float maxValue, float leftValue, float rightValue) {
        rangeSeekBar.setIndicatorTextDecimalFormat("0");
        rangeSeekBar.setRange(minValue, maxValue);
        rangeSeekBar.setValue(leftValue, rightValue);
    }


    @OnClick(R.id.confirmButton)
    void confirmData() {

        if (validateFlatHouseCheckBox()) {
            Call<ResponseBody> call = apiService.questionnaireConfirm(createRequestBody());
            call.enqueue(questionnaireCallback);
        }
    }



    private QuestionnairePostModel createRequestBody() {
        QuestionnairePostModel model = new QuestionnairePostModel();

        model.setType(getCorrectDataFromHouseFlatCheckBox());
        model.setPets(petsCustomCheckBox.isChecked());
        model.setFurnished(furnishedCustomCheckBox.isChecked());
        model.setSmokingInside(smokingCustomCheckBox.isChecked());

        model.setRoomAmountMin((int)roomAmountLeftValue);
        model.setRoomAmountMax((int)roomAmountRightValue);

        model.setSizeMin((int) sizeLeftValue);
        model.setSizeMax((int) sizeRightValue);

        model.setMinCost((int) totalCostLeftValue);
        model.setMaxCost((int) totalCostRightValue);

        model.setConstructionYearMin((int) constructionLeftValue);
        model.setConstructionYearMax((int) constructionRightValue);

        model.setUser(null);

        return model;
    }

    private String getCorrectDataFromHouseFlatCheckBox() {
        if (houseCustomCheckBox.isChecked() && flatCustomCheckBox.isChecked()) {
            return "flat/house";
        } else if (houseCustomCheckBox.isChecked() && !flatCustomCheckBox.isChecked()) {
            return "house";
        } else if (!houseCustomCheckBox.isChecked() && flatCustomCheckBox.isChecked()) {
            return "flat";
        } else return "";
    }

    private boolean validateFlatHouseCheckBox() {
        if (!houseCustomCheckBox.isChecked() && !flatCustomCheckBox.isChecked()) {
            Toast.makeText(getApplicationContext(), "Please pick flat or house option", Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }


    @Inject
    public void setQuestionnaireCallback(QuestionnaireCallback questionnaireCallback) {
        this.questionnaireCallback = questionnaireCallback;
    }

    @Inject
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }


    /**
     * Class that handles arrived event through EventBus
     */
    private final class QuestionnaireEventListener {
        @Subscribe
        public void getStatus(QuestionnaireEvent event) {
            if (event.getStatus().equals(Status.SUCCES.str)) {
                showDialog("All data saved");
                finish();
            } else if (event.getStatus().equals("Unauthorized")) {
                showDialog("Unauthorized");
            } else {
                showDialog("Error");
            }
        }
    }

    /**
     * Creates Dialog with an OK button
     *
     * @param message message displayed on Dialog
     */
    private void showDialog(String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                }).show();

    }


    @Inject
    public void setSharedPreferencesWraper(SharedPreferencesWraper sharedPreferencesWraper) {
        this.sharedPreferencesWraper = sharedPreferencesWraper;
    }

}
