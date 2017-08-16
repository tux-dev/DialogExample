package com.example.android.dialogexample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.example.android.dialogexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Mher on 16.08.17.
 */

public class ChangePriceDialog extends AppCompatDialogFragment {
//    public class ChangePriceDialog extends AppCompatDialog {

    public static String ARGUMENT_PRICE = "price";
    public static boolean isPriceChanged = false;

    @BindView(R.id.edittext__change_price)
    EditText vEditTxtChangePrice;

    Float currentPrice;
    OnChangePrice changePriceListener;
    Context ctx;

    public static ChangePriceDialog getInstance(float price) {
        ChangePriceDialog instance = new ChangePriceDialog();
        Bundle args = new Bundle();
        args.putFloat(ARGUMENT_PRICE, price);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            changePriceListener = (OnChangePrice) context;
            ctx = context;
        } catch (ClassCastException e) {
            throw new ClassCastException(e.getMessage() + " must implement OnChangePrice");
        }
    }

    //    public ChangePriceDialog(Context context, float price) {
//        super(context);
//        currentPrice = price;
//        setContentView(R.layout.dialog_change_price);
//        ButterKnife.bind(this);
//        changePriceListener = (OnChangePrice) context;
//        vEditTxtChangePrice.setText(String.valueOf(currentPrice));
//        vEditTxtChangePrice.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (Float.valueOf(charSequence.toString()).equals(currentPrice)) {
//                    isPriceChanged = true;
//                } else {
//                    isPriceChanged = false;
//                }
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPrice = getArguments().getFloat(ARGUMENT_PRICE);
    }

    @OnClick(R.id.edittext__change_price)
    public void onEditTextClick() {
        System.out.println("===> I am here -1-");
        vEditTxtChangePrice.requestFocus();
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(vEditTxtChangePrice, InputMethodManager.SHOW_FORCED); //SHOW_IMPLICIT
        vEditTxtChangePrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Float.valueOf(charSequence.toString()).equals(currentPrice)) {
                    isPriceChanged = true;
                } else {
                    isPriceChanged = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        super.onCreateDialog(savedInstanceState);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle()
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_change_price, container, false);
        ButterKnife.bind(this, view);
        vEditTxtChangePrice.setText(String.valueOf(currentPrice));
        return view;
    }

    @OnClick(R.id.button_positive_change_price)
    public void onPositiveClick() {
        dismiss();
        changePriceListener.onDialogPositiveClick(isPriceChanged);
    }

    @OnClick(R.id.button_negative_change_price)
    public void onNegativeClick() {
        dismiss();
        changePriceListener.onDialogNegativeClick();
    }

    /**
     * интерфейс нажатия на кнопки дилалога (реагирует на нажатие на кнопку подтвержелния)
     */
    public interface OnChangePrice {

        /**
         * Метод реации на позитивную кнопку диалога (отмену заказа), возврат к домашнему фрагменту
         */
        void onDialogPositiveClick(boolean isPriceChanged);

        /**
         * Метод реации на кнопку отказа отмены диалога, возврат к домашнему фрагменту
         */
        void onDialogNegativeClick();
    }
}
