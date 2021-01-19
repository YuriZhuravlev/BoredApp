package com.example.boredapp.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import com.example.boredapp.MainActivity;
import com.example.boredapp.R;
import com.example.boredapp.model.ActivityModel;
import com.example.boredapp.model.TypeActivity;
import com.example.boredapp.ui.fragments.help.HelpFragment;
import com.example.boredapp.utils.ApiUtils;
import com.example.boredapp.utils.BoredApi;
import com.example.boredapp.utils.SharedPreferenceHelper;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ActivityFragment extends Fragment {
    private final static String TEXT_VIEW_EN = "TEXT_VIEW_EN";
    private final static String LINK = "LINK";
    private final static String PARTICIPANTS = "PARTICIPANTS";
    private final static String TYPE_SELECTED = "TYPE_SELECTED";
    private final static String IS_FREE = "IS_FREE";

    private final BoredApi apiService = ApiUtils.getApiService();
    private String mLink = "";
    private final TypeActivity mType = TypeActivity.EMPTY;

    private Button mBtnDo;
    private Button mBtnOpenLink;
    private Spinner mSpinnerType;
    private EditText mEtInputParticipants;
    private CheckBox mCbPrice;
    private TextView mTvActivityEn;
//    private TextView mTvActivityRu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        if (!SharedPreferenceHelper.getShowTutorial(getActivity())) {
            SharedPreferenceHelper.setShowTutorial(getActivity());
            getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, new HelpFragment())
                    .commit();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_activity, container, false);
        initUI(v);
        if (savedInstanceState != null)
            loadInstanceState(savedInstanceState);
        return v;
    }

    private void loadInstanceState(Bundle savedInstanceState) {
        mEtInputParticipants.setText(savedInstanceState.getString(PARTICIPANTS));
        mSpinnerType.setSelection(savedInstanceState.getInt(TYPE_SELECTED));
        mCbPrice.setSelected(savedInstanceState.getBoolean(IS_FREE));
        if (savedInstanceState.containsKey(TEXT_VIEW_EN)) {
            mTvActivityEn.setText(savedInstanceState.getString(TEXT_VIEW_EN));
            mTvActivityEn.setVisibility(View.VISIBLE);
            if (savedInstanceState.containsKey(LINK)) {
                mLink = savedInstanceState.getString(LINK);
                mBtnOpenLink.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(PARTICIPANTS, mEtInputParticipants.getText().toString());
        outState.putInt(TYPE_SELECTED, mSpinnerType.getSelectedItemPosition());
        outState.putBoolean(IS_FREE, mCbPrice.isChecked());
        if (mTvActivityEn.getVisibility() == View.VISIBLE) {
            String text = mTvActivityEn.getText().toString();
            outState.putString(TEXT_VIEW_EN, text);
            if (mBtnOpenLink.getVisibility() == View.VISIBLE) {
                outState.putString(LINK, mLink);
            }
        }
        super.onSaveInstanceState(outState);
    }


    private void initUI(View v) {
        mBtnDo = v.findViewById(R.id.btn_do);
        mBtnOpenLink = v.findViewById(R.id.btn_open_link);
        mSpinnerType = v.findViewById(R.id.spinner_type);
        mEtInputParticipants = v.findViewById(R.id.et_input_participants);
        mCbPrice = v.findViewById(R.id.cb_price);
        mTvActivityEn = v.findViewById(R.id.tv_activity_en);
        //mTvActivityRu = v.findViewById(R.id.tv_activity_ru);

        initSpinner();
    }

    private void initSpinner() {
        String[] typeArray = getResources().getStringArray(R.array.TypeActivity);
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.dropdown_item, typeArray);

        mSpinnerType.setAdapter(spinnerAdapter);
        mSpinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mEtInputParticipants.setVisibility(View.INVISIBLE);
                } else {
                    mEtInputParticipants.setVisibility(View.VISIBLE);
                }
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
        //((MainActivity) getActivity()).getToolBar().setDisplayHomeAsUpEnabled(false);
        //((MainActivity) getActivity()).getToolBar().setTitle(R.string.app_name);
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
                        } else {
                            mBtnOpenLink.setVisibility(View.GONE);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mTvActivityEn.setVisibility(View.GONE);
                        mBtnOpenLink.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
        String type = mType.getLowerCase()[typeId];
        try {
            int i = Integer.parseInt(mEtInputParticipants.getText().toString());
            if (mEtInputParticipants.getVisibility() == View.VISIBLE && i > 0) {
                if (mCbPrice.isChecked()) {
                    return apiService.getActivity(type, i, ApiUtils.PRICE_FREE);
                } else {
                    return apiService.getActivity(type, i);
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
            if (mEtInputParticipants.getVisibility() == View.VISIBLE && i > 0) {
                if (mCbPrice.isChecked()) {
                    return apiService.getActivity(i, ApiUtils.PRICE_FREE);
                } else {
                    return apiService.getActivity(i);
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

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_item_help: {
//                getFragmentManager().beginTransaction()
//                        .addToBackStack(null)
//                        .replace(R.id.fragment_container, new HelpFragment())
//                        .commit();
//                break;
//            }
//            case R.id.menu_item_theme_change: {
//                ((MainActivity) getActivity()).changeTheme();
//                break;
//            }
//            case R.id.menu_item_about: {
//                getFragmentManager().beginTransaction()
//                        .addToBackStack(null)
//                        .replace(R.id.fragment_container, new AboutFragment())
//                        .commit();
//                break;
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }
}