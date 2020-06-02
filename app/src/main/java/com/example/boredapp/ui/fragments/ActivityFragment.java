package com.example.boredapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.boredapp.R;
import com.example.boredapp.model.ActivityModel;
import com.example.boredapp.model.TypeActivity;
import com.example.boredapp.utils.ApiUtils;
import com.example.boredapp.utils.BoredApi;

import io.reactivex.Single;

public class ActivityFragment extends Fragment {
    private BoredApi apiService = ApiUtils.getApiService();

    private Button btnDo;
    private Button btnOpenLink;
    private Spinner spinnerType;
    private EditText etInputParticipants;
    private CheckBox cbPrice;
    private TextView tvActivityEn;
    private TextView tvActivityRu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_activity, container, false);
        initUI(v);
        return v;
    }

    private void initUI(View v) {
        btnDo = v.findViewById(R.id.btn_do);
        btnOpenLink = v.findViewById(R.id.btn_open_link);
        spinnerType = v.findViewById(R.id.spinner_type);
        etInputParticipants = v.findViewById(R.id.et_input_participants);
        cbPrice = v.findViewById(R.id.cb_price);
        tvActivityEn = v.findViewById(R.id.tv_activity_en);
        tvActivityRu = v.findViewById(R.id.tv_activity_ru);

        initSpinner();
    }

    private void initSpinner() {
        //TODO localization and normal ENUM
        String[] typeArray = TypeActivity.EMPTY.getLowerCase();
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, typeArray);

        spinnerType.setAdapter(spinnerAdapter);
//        spinnerType.setSelection(0);
    }

    @Override
    public void onResume() {
        btnDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFields();
            }
        });
        super.onResume();
    }

    private Single<ActivityModel> checkFields() {
        if (spinnerType.getId() == 0) {
            return checkFieldsWithoutType();
        } else {
            return checkFieldsWithType(spinnerType.getId());
        }
    }

    private Single<ActivityModel> checkFieldsWithType(int typeId) {
        try {
            int i = Integer.parseInt(etInputParticipants.getText().toString());
            if (i > 0) {
                if (cbPrice.isChecked()) {
                    return apiService.getActivity("typeId", i, ApiUtils.PRICE_FREE);
                } else {
                    return apiService.getActivity("typeId");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cbPrice.isChecked()) {
            return apiService.getActivity("typeId", ApiUtils.PRICE_FREE);
        } else {
            return apiService.getActivity("typeId");
        }
    }

    private Single<ActivityModel> checkFieldsWithoutType() {
        try {
            int i = Integer.parseInt(etInputParticipants.getText().toString());
            if (i > 0) {
                if (cbPrice.isChecked()) {
                    return apiService.getActivity(i, ApiUtils.PRICE_FREE);
                } else {
                    return apiService.getActivity();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cbPrice.isChecked()) {
            return apiService.getActivity(ApiUtils.PRICE_FREE);
        } else {
            return apiService.getActivity();
        }
    }
}