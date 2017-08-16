package com.example.android.dialogexample;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ChangePriceDialog.OnChangePrice {

    @BindView(R.id.button_start)
    Button btnStart;
    boolean isPriceChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_start)
    public void onBtnClick() {
//        ChangePriceDialog.getInstance(500).show(getSupportFragmentManager(), "");
        createDialog();
    }


    public void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.edit_text_dialog, null);
        EditText editor = (EditText) view.findViewById(R.id.edittext_price);
//        final EditText input = new EditText(MainActivity.this);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        input.setLayoutParams(lp);
//        input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
//        input.setText("500");
        //        final Float price = Float.valueOf(input.getText().toString());
        final Float price = Float.valueOf(editor.getText().toString());
        System.out.println("===> before price " + price);
        builder.setTitle("Title")
                .setView(view)
//                .setView(input)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isPriceChanged) {
                            System.out.println("===> yes");
                        } else {
                            System.out.println("===> no");
                        }
                    }
                }).setNegativeButton("Cancell", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
//        input.addTextChangedListener(new TextWatcher() {
        editor.addTextChangedListener(new TextWatcher() {

                @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("===> after price " + Float.valueOf(charSequence.toString()));
                if (Float.valueOf(charSequence.toString()).equals(price)) {
                    isPriceChanged = false;
                } else {
                    isPriceChanged = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onDialogPositiveClick(boolean isPriceChanged) {
        if (isPriceChanged) {
            System.out.println("===> yes");
        } else {
            System.out.println("===> no");
        }
    }

    @Override
    public void onDialogNegativeClick() {

    }
}
