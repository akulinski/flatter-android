package com.kpg.flatter.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kpg.flatter.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Bottom Sheet Dialog as fragment displayed after successful sign up
 */
public class EmailBottomDialog extends BottomSheetDialogFragment {

    @BindView(R.id.okButton) Button okButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_emailsent,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.okButton) void okCliked(){
        getActivity().onBackPressed();
    }
}
