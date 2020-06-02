package com.example.boredapp.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boredapp.R;
import com.example.boredapp.model.ActivityModel;
import com.example.boredapp.model.TypeActivity;
import com.example.boredapp.utils.ApiUtils;
import com.example.boredapp.utils.BoredApi;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ActivityFragment extends Fragment {
    private BoredApi apiService = ApiUtils.getApiService();
    private String mLink = "";

    private Button mBtnDo;
    private Button mBtnOpenLink;
    private Spinner mSpinnerType;
    private EditText mEtInputParticipants;
    private CheckBox mCbPrice;
    private TextView mTvActivityEn;
    private TextView mTvActivityRu;

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
        mBtnDo = v.findViewById(R.id.btn_do);
        mBtnOpenLink = v.findViewById(R.id.btn_open_link);
        mSpinnerType = v.findViewById(R.id.spinner_type);
        mEtInputParticipants = v.findViewById(R.id.et_input_participants);
        mCbPrice = v.findViewById(R.id.cb_price);
        mTvActivityEn = v.findViewById(R.id.tv_activity_en);
        mTvActivityRu = v.findViewById(R.id.tv_activity_ru);

        initSpinner();
    }

    private void initSpinner() {
        //TODO localization and normal ENUM
        String[] typeArray = TypeActivity.EMPTY.getLowerCase();
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, typeArray);

        mSpinnerType.setAdapter(spinnerAdapter);
        mSpinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onResume() {
        mBtnDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBtnDo();
            }
        });
        mBtnOpenLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeb();
            }
        });
        super.onResume();
    }

    private void openWeb() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mLink));
        startActivity(intent);
    }

    @SuppressLint("CheckResult")
    private void clickBtnDo() {
        checkFields()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ActivityModel>() {
                    @Override
                    public void accept(ActivityModel activityModel) throws Exception {
                        mTvActivityEn.setVisibility(View.VISIBLE);
                        mTvActivityEn.setText(activityModel.getActivity());
                        if (activityModel.getLink() != null && !activityModel.getLink().isEmpty()) {
                            mLink = activityModel.getLink();
                            mBtnOpenLink.setVisibility(View.VISIBLE);
                            //TODO SetOnClickListener
                        } else {
                            mBtnOpenLink.setVisibility(View.GONE);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mTvActivityEn.setVisibility(View.GONE);
                        mTvActivityRu.setVisibility(View.GONE);
                        mBtnOpenLink.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private Single<ActivityModel> checkFields() {
        if (mSpinnerType.getSelectedItemPosition() == 0) {
            return checkFieldsWithoutType();
        } else {
            return checkFieldsWithType(mSpinnerType.getSelectedItemPosition());
        }
    }

    private Single<ActivityModel> checkFieldsWithType(int typeId) {
        //TODO normal ENUM
        String type = TypeActivity.EMPTY.getLowerCase()[typeId];
        try {
            int i = Integer.parseInt(mEtInputParticipants.getText().toString());
            if (i > 0) {
                if (mCbPrice.isChecked()) {
                    return apiService.getActivity(type, i, ApiUtils.PRICE_FREE);
                } else {
                    return apiService.getActivity(type);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mCbPrice.isChecked()) {
            return apiService.getActivity(type, ApiUtils.PRICE_FREE);
        } else {
            return apiService.getActivity(type);
        }
    }

    private Single<ActivityModel> checkFieldsWithoutType() {
        try {
            int i = Integer.parseInt(mEtInputParticipants.getText().toString());
            if (i > 0) {
                if (mCbPrice.isChecked()) {
                    return apiService.getActivity(i, ApiUtils.PRICE_FREE);
                } else {
                    return apiService.getActivity();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mCbPrice.isChecked()) {
            return apiService.getActivity(ApiUtils.PRICE_FREE);
        } else {
            return apiService.getActivity();
        }
    }
}