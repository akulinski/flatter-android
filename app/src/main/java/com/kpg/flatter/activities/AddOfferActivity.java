package com.kpg.flatter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.kpg.flatter.R;
import com.kpg.flatter.core.SharedPreferencesWraper;
import com.kpg.flatter.core.application.FlatterCore;
import com.kpg.flatter.core.exceptions.SharedPreferenceValueNotFoundException;
import com.kpg.flatter.eventbus.events.AddOfferEvent;
import com.kpg.flatter.requests.ApiClient;
import com.kpg.flatter.requests.ApiInterface;
import com.kpg.flatter.requests.models.AddOfferPostModel;
import com.kpg.flatter.utills.Address;
import com.kpg.flatter.utills.Status;
import com.kpg.flatter.utills.Urls;

import net.igenius.customcheckbox.CustomCheckBox;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class AddOfferActivity extends AppCompatActivity {

    @BindView(R.id.autocompleteTextView)
    AutoCompleteTextView autoCompleteTextView;

    @BindView(R.id.descriptionTextField)
    TextFieldBoxes descriptionTextField;

    @BindView(R.id.descriptionTextFieldExtended)
    ExtendedEditText descriptionTextFieldExtended;

    @BindView(R.id.costTextField)
    TextFieldBoxes costTextField;

    @BindView(R.id.costTextFieldExtended)
    ExtendedEditText costTextFieldExtended;

    @BindView(R.id.roomAmountTextField)
    TextFieldBoxes roomAmountTextField;

    @BindView(R.id.roomAmountTextFieldExtended)
    ExtendedEditText roomAmountTextFieldExtended;

    @BindView(R.id.sizeTextField)
    TextFieldBoxes sizeTextField;

    @BindView(R.id.sizeTextFieldExtended)
    ExtendedEditText sizeTextFieldExtended;

    @BindView(R.id.constructionYearTextField)
    TextFieldBoxes constructionYearTextField;

    @BindView(R.id.constructionYearTextFieldExtended)
    ExtendedEditText constructionYearTextFieldExtended;


    @BindView(R.id.addOfferButton)
    Button addOfferButton;

    @BindView(R.id.flatOfferCustomCheckBox)
    CustomCheckBox flatOfferCustomCheckBox;

    @BindView(R.id.houseOfferCustomCheckBox)
    CustomCheckBox houseOfferCustomCheckBox;

    @BindView(R.id.smokingOfferCustomCheckBox)
    CustomCheckBox smokingOfferCustomCheckBox;

    @BindView(R.id.petsOfferCustomCheckBox)
    CustomCheckBox petsOfferCustomCheckBox;

    @BindView(R.id.furnishedOfferCustomCheckBox)
    CustomCheckBox furnishedOfferCustomCheckBox;


    private ApiInterface apiService;
    private EventBus eventBus;
    private SharedPreferencesWraper sharedPreferencesWraper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addofferview);
        ((FlatterCore) getApplication()).getAddOfferActivityComponent().inject(this);

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
        eventBus.register(new AddOfferEventListener());

        setUp();

    }

    @OnClick(R.id.addOfferButton)
    public void addOffer() {
        if (checkAllData()) {
            createRequestBody();
            finish();
            //Call<ResponseBody> call = apiService.cre
        }
    }

    private AddOfferPostModel createRequestBody() {
        AddOfferPostModel model = new AddOfferPostModel();
        model.setDescription(descriptionTextFieldExtended.getText().toString());
        model.setTotalCost(Double.parseDouble(costTextFieldExtended.getText().toString()));
        model.setRoomAmount(Integer.valueOf(roomAmountTextFieldExtended.getText().toString()));
        model.setJhiSize(Double.parseDouble(sizeTextFieldExtended.getText().toString()));

        if (flatOfferCustomCheckBox.isChecked()) model.setJhiType("flat");
        else if (houseOfferCustomCheckBox.isChecked()) model.setJhiType("house");

        model.setConstructionYear(Integer.valueOf(constructionYearTextFieldExtended.getText().toString()));

        model.setPets(petsOfferCustomCheckBox.isChecked());
        model.setSmokingInside(smokingOfferCustomCheckBox.isChecked());
        model.setIsFurnished(furnishedOfferCustomCheckBox.isChecked());


//        System.out.println(model.getDescription());
//        System.out.println(model.getTotalCost());
//        System.out.println(model.getRoomAmount());
//        System.out.println(model.getJhiSize());
//        System.out.println(model.getJhiType());
//        System.out.println(model.getConstructionYear());
//        System.out.println(model.getPets());
//        System.out.println(model.getSmokingInside());
//        System.out.println(model.getIsFurnished());
        return model;
    }





    private void setUp() {

        // Flat or house choice, if one is picked other is instantly unpicked.
        flatOfferCustomCheckBox.setOnCheckedChangeListener((checkBox, isChecked) -> {
            if (isChecked) houseOfferCustomCheckBox.setChecked(false);
        });

        houseOfferCustomCheckBox.setOnCheckedChangeListener((checkBox, isChecked) -> {
            if (isChecked) flatOfferCustomCheckBox.setChecked(false);
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, cities);
        autoCompleteTextView.setAdapter(adapter);

    }


    private boolean checkAllData() {

        // Checking if the house or flat is checked.
        if (!flatOfferCustomCheckBox.isChecked() && !houseOfferCustomCheckBox.isChecked()) {
            Toast.makeText(getApplicationContext(), "Pick house or room option", Toast.LENGTH_LONG).show();
            return false;
        }

        if (autoCompleteTextView.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter your city", Toast.LENGTH_LONG).show();
            return false;
        }

        // Checking if the field is not empty
        if (descriptionTextFieldExtended.getText().toString().equals("")){
            descriptionTextField.setError("Description is too short", true);
            return false;
        }

        // Checking if the field is not empty
        if (costTextFieldExtended.getText().toString().length() == 0 || costTextFieldExtended.getText().toString().equals("0")){
            costTextField.setError("Value can not be empty or equals zero!", true);
            return false;
        }

        if (roomAmountTextFieldExtended.getText().toString().length() == 0 || roomAmountTextFieldExtended.getText().toString().equals("0")){
            roomAmountTextField.setError("Value can not be empty or equals zero!", true);
            return false;
        }

        if (sizeTextFieldExtended.getText().toString().length() == 0 || sizeTextFieldExtended.getText().toString().equals("0")) {
            sizeTextField.setError("Value can not be empty or equals zero!", true);
            return false;
        }

        // Check all construction year invalid options

        if (constructionYearTextFieldExtended.getText().toString().length() == 0) {
            constructionYearTextField.setError("Value can not be empty", true);
            return false;
        }

        if (!(constructionYearTextFieldExtended.getText().toString().length() == 4)) {
            constructionYearTextField.setError("Year must have 4 digits", true);
            return false;
        }

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int minYear = 1000;
        int yearFromTextField = Integer.valueOf(constructionYearTextFieldExtended.getText().toString());

        if (yearFromTextField < minYear || yearFromTextField > currentYear) {
            constructionYearTextField.setError("Year must be in range " + minYear + "-" + currentYear, true);
            return false;
        }

        return true;
    }


    private final class AddOfferEventListener {
        @Subscribe
        public void getStatus(AddOfferEvent event) {
            if (event.getStatus().equals(Status.SUCCES.str)) {
                finish();
            }
        }
    }


    @Inject
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Inject
    public void setSharedPreferencesWraper(SharedPreferencesWraper sharedPreferencesWraper) {
        this.sharedPreferencesWraper = sharedPreferencesWraper;
    }

    private static final String[] cities = new String[] {
            "Lublin", "Warszawa", "Wroclaw", "Poznan", "Katowice"
    };
}
