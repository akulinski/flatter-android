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
import com.kpg.flatter.adapters.RangeSeekBarAdapter;
import com.kpg.flatter.utills.QuestionnaireActivityData;
import com.kpg.flatter.utills.Status;
import com.kpg.flatter.utills.Urls;

import net.igenius.customcheckbox.CustomCheckBox;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;

import static com.kpg.flatter.utills.QuestionnaireActivityData.*;


public class QuestionnaireActivity extends AppCompatActivity {

    @BindView(R.id.confirmButton)
    Button confirmButton;

    @BindView(R.id.roomAmountRangeSeekBar)
    RangeSeekBar roomAmountRangeSeekBar;

    @BindView(R.id.sizeRangeSeekBar)
    RangeSeekBar sizeRangeSeekBar;

    @BindView(R.id.totalCostRangeSeekBar)
    RangeSeekBar totalCostRangeSeekBar;

    @BindView(R.id.constructionRangeSeekBar)
    RangeSeekBar constructionRangeSeekBar;

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

    private RangeSeekBarAdapter roomAmountRangeSeekBarAdapter;
    private RangeSeekBarAdapter sizeRangeSeekBarAdapter;
    private RangeSeekBarAdapter totalCostRangeSeekBarAdapter;
    private RangeSeekBarAdapter constructionYearRangeSeekBarAdapter;

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
        textTop.setText(QuestionnaireActivityData.TITLE);

        setUpRangeSeekBar(roomAmountRangeSeekBar, ROOM_AMOUNT_MIN_VALUE, ROOM_AMOUNT_MAX_VALUE, ROOM_AMOUNT_LEFT_BAR_DEFAULT_VALUE, ROOM_AMOUNT_RIGHT_BAR_DEFAULT_VALUE);
        setUpRangeSeekBar(sizeRangeSeekBar, SIZE_MIN_VALUE, SIZE_MAX_VALUE, SIZE_LEFT_BAR_DEFAULT_VALUE, SIZE_RIGHT_BAR_DEFAULT_VALUE);
        setUpRangeSeekBar(constructionRangeSeekBar, CONSTRUCTION_YEAR_MIN_VALUE, CONSTRUCTION_YEAR_MAX_VALUE, CONSTRUCTION_YEAR_LEFT_BAR_DEFAULT_VALUE, CONSTRUCTION_YEAR_RIGHT_BAR_DEFAULT_VALUE);
        setUpRangeSeekBar(totalCostRangeSeekBar, TOTAL_COST_MIN_VALUE, TOTAL_COST_MAX_VALUE, TOTAL_COST_LEFT_BAR_DEFAULT_VALUE, TOTAL_COST_RIGHT_BAR_DEFAULT_VALUE);

        roomAmountRangeSeekBarAdapter = new RangeSeekBarAdapter(ROOM_AMOUNT_LEFT_BAR_DEFAULT_VALUE, ROOM_AMOUNT_RIGHT_BAR_DEFAULT_VALUE);
        constructionYearRangeSeekBarAdapter = new RangeSeekBarAdapter(CONSTRUCTION_YEAR_LEFT_BAR_DEFAULT_VALUE, CONSTRUCTION_YEAR_RIGHT_BAR_DEFAULT_VALUE);
        sizeRangeSeekBarAdapter = new RangeSeekBarAdapter(SIZE_LEFT_BAR_DEFAULT_VALUE, SIZE_RIGHT_BAR_DEFAULT_VALUE);
        totalCostRangeSeekBarAdapter = new RangeSeekBarAdapter(TOTAL_COST_LEFT_BAR_DEFAULT_VALUE, TOTAL_COST_RIGHT_BAR_DEFAULT_VALUE);

        roomAmountRangeSeekBar.setOnRangeChangedListener(roomAmountRangeSeekBarAdapter);
        constructionRangeSeekBar.setOnRangeChangedListener(constructionYearRangeSeekBarAdapter);
        sizeRangeSeekBar.setOnRangeChangedListener(sizeRangeSeekBarAdapter);
        totalCostRangeSeekBar.setOnRangeChangedListener(totalCostRangeSeekBarAdapter);

    }

    private void setUpRangeSeekBar(RangeSeekBar rangeSeekBar, float minValue, float maxValue, float leftValue, float rightValue) {
        rangeSeekBar.setIndicatorTextDecimalFormat("0");
        rangeSeekBar.setRange(minValue, maxValue);
        rangeSeekBar.setValue(leftValue, rightValue);
    }


    @OnClick(R.id.confirmButton)
    void confirmData() {

        if (validateFlatHouseCheckBox()) {
            Call<ResponseBody> call = apiService.createQuestionnaire(createRequestBody());
            call.enqueue(questionnaireCallback);
        }
    }


    private QuestionnairePostModel createRequestBody() {
        QuestionnairePostModel model = new QuestionnairePostModel();

        model.setType(getCorrectDataFromHouseFlatCheckBox());
        model.setPets(petsCustomCheckBox.isChecked());
        model.setFurnished(furnishedCustomCheckBox.isChecked());
        model.setSmokingInside(smokingCustomCheckBox.isChecked());

        model.setRoomAmountMin((int) roomAmountRangeSeekBarAdapter.getlValue());
        model.setRoomAmountMax((int) roomAmountRangeSeekBarAdapter.getrValue());

        model.setSizeMin((int) sizeRangeSeekBarAdapter.getlValue());
        model.setSizeMax((int) sizeRangeSeekBarAdapter.getrValue());

        model.setTotalCostMin((int) totalCostRangeSeekBarAdapter.getlValue());
        model.setTotalCostMax((int) totalCostRangeSeekBarAdapter.getrValue());

        model.setConstructionYearMin((int) constructionYearRangeSeekBarAdapter.getlValue());
        model.setConstructionYearMax((int) constructionYearRangeSeekBarAdapter.getrValue());


        return model;
    }

    private String getCorrectDataFromHouseFlatCheckBox() {
        if (houseCustomCheckBox.isChecked() && flatCustomCheckBox.isChecked()) {
            return FLAT_HOUSE;
        } else if (houseCustomCheckBox.isChecked() && !flatCustomCheckBox.isChecked()) {
            return HOUSE;
        } else if (!houseCustomCheckBox.isChecked() && flatCustomCheckBox.isChecked()) {
            return FLAT;
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
                finish();
            } else if (event.getStatus().equals(Status.UNAUTHORIZED.str)) {
                showDialog(Status.UNAUTHORIZED.str);
            } else {
                showDialog(Status.ERROR.str);
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
