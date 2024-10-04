package com.example.widgets;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

//public class MainActivity extends AppCompatActivity {
//    private TextView txtResult;
//    private RadioGroup rdGroup;
//    private RadioButton opt1, opt2, opt3;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        txtResult =findViewById(R.id.txtResult);
//        rdGroup = findViewById(R.id.rdGroup);
//
//        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton rdBtn = findViewById(checkedId);
//                txtResult.setVisibility(View.VISIBLE);
//                txtResult.setText(rdBtn.getText());
//            }
//        });
//
//    }
//}
//public class MainActivity extends AppCompatActivity {
//
//    private SeekBar seekBar;
//    private TextView textView;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.seekbar);
//
//        seekBar = findViewById(R.id.seekBar);
//        textView = findViewById(R.id.txtResult);
//
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
////                textView.setText("You got "+ progress +"/10");
////
////                if(progress <= 3) {
////                    textView.setTextColor(Color.RED);
////                } else if (progress <= 7) {
////                    textView.setTextColor(Color.YELLOW);
////                } else {
////                    textView.setTextColor(Color.GREEN);
////                }
//                //change 0-3 red text
////              // 4-7 yellow text
//                // green text
//
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                int progress = seekBar.getProgress();
//                textView.setText("You got "+ progress +"/10");
//
//                if(progress <= 3) {
//                    textView.setTextColor(Color.RED);
//                } else if (progress <= 7) {
//                    textView.setTextColor(Color.YELLOW);
//                } else {
//                    textView.setTextColor(Color.GREEN);
//                }
//            }
//        });
//
//
//
//    }
//}


//check box
// alert dialog
// make the tip calculator
// Create one edit text which accepts only numbers(open keyboard with only number)
// create on seek bar max of 20 and min of 0 which will act as the tip on the bill amount
// create a button and on click of it display the final amount
// create a textview which will display the final amount of button press





//CHECK BOX

//public class MainActivity extends AppCompatActivity {
//    private RelativeLayout checkBoxLayout;
//    private CheckBox chBox1, chBox2, chBoxSelect;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this); // Assuming this is a method to enable edge-to-edge layout
//        setContentView(R.layout.checkbox_learn);
//
//        checkBoxLayout = findViewById(R.id.checkBoxLayout);
//        checkBoxLayout.setBackgroundColor(Color.BLACK); // Setting the background color
//
//        chBox1 = findViewById(R.id.chBox1);
//        chBox2 = findViewById(R.id.chBox2);
//        chBoxSelect = findViewById(R.id.chBoxSelect);
//
//        chBoxSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    // Set listeners to ensure only one checkbox is checked at a time
//                    chBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                        @Override
//                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                            if (isChecked) {
//                                chBox2.setChecked(false);
//                            }
//                        }
//                    });
//
//                    chBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                        @Override
//                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                            if (isChecked) {
//                                chBox1.setChecked(false);
//                            }
//                        }
//                    });
//
//                    // If both checkboxes are checked, uncheck one
//                    if (chBox1.isChecked() && chBox2.isChecked()) {
//                        chBox2.setChecked(false);
//                    }
//                } else {
//                    // Remove the listeners to allow independent selection
//                    chBox1.setOnCheckedChangeListener(null);
//                    chBox2.setOnCheckedChangeListener(null);
//                }
//            }
//        });
//    }
//}


// ALERT DIALOG
//public class MainActivity extends AppCompatActivity{
//    private RelativeLayout alertDialogLayout;
//
//    private Button displayAlertBtn;
//    private TextView txtDisplay;
//
//    @Override
//    public void onCreate(Bundle savedInstances){
//        super.onCreate(savedInstances);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.alert_dialog_learn);
//        alertDialogLayout = findViewById(R.id.alertDialogLayout);
//        alertDialogLayout.setBackgroundColor(Color.BLACK);
//        displayAlertBtn = findViewById(R.id.displayAlertBtn);
//        txtDisplay = findViewById(R.id.txtDisplay);
//
//        displayAlertBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Alert Dialog")
//                        .setMessage("This is an example of creating an alert dialog")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                txtDisplay.setVisibility(View.VISIBLE);
//                                txtDisplay.setText(R.string.onYesSelect);
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                txtDisplay.setVisibility(View.VISIBLE);
//                                txtDisplay.setText(R.string.onNoSelection);
//                            }
//                        });
//                // Create and show the alert dialog
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        });
//
//
//
//
//
//
//
//
//    }
//}


// TIP CALCULATOR

//public class MainActivity extends AppCompatActivity{
//
//
//    private EditText edBillAmount;
//    private SeekBar sbTipSelector;
//    private TextView txtTotalBill, txtDisplayTip;
////    private Button btnDisplayTotalBill;
//
//
//    @Override
//    public void onCreate(Bundle savedInstances){
//        super.onCreate(savedInstances);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.tip_calculator);
//
////
//
//
//        edBillAmount = findViewById(R.id.edBillAmount);
//        sbTipSelector = findViewById(R.id.sbTipSelector);
//        txtTotalBill = findViewById(R.id.txtTotalBill);
//        txtDisplayTip = findViewById(R.id.txtDisplayTip);
////        btnDisplayTotalBill = findViewById(R.id.btnDisplayTotalBill);
//
//
//
//        if(edBillAmount.getText().toString().isEmpty()){
//            txtTotalBill.setVisibility(View.GONE);
//        }
//
//        sbTipSelector.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                txtDisplayTip.setVisibility(View.VISIBLE);
//                txtDisplayTip.setText(getString(R.string.selected_tip, progress));
//
//
//                //showing the realtime tip
//                if(edBillAmount.getText().toString().isEmpty()){
//                    txtTotalBill.setVisibility(View.VISIBLE);
//                    txtTotalBill.setTextColor(Color.RED);
//                    txtTotalBill.setText(R.string.bill_amount_not_entered);
//                } else {
//                    try {
//                        double billAmount = Double.parseDouble(edBillAmount.getText().toString());
//                        int tipAmount = sbTipSelector.getProgress();
//                        double totalBillAmount = billAmount + (billAmount * tipAmount / 100);
//                        txtTotalBill.setTextColor(Color.WHITE);
//                        txtTotalBill.setVisibility(View.VISIBLE);
//                        txtTotalBill.setText(getString(R.string.total_bill, totalBillAmount));
//                    } catch (NumberFormatException e) {
//                        txtTotalBill.setVisibility(View.VISIBLE);
//                        txtTotalBill.setTextColor(Color.RED);
//                        txtTotalBill.setText(R.string.invalid_bill_amount);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                int progress = seekBar.getProgress();
//                if(progress == 0 || edBillAmount.getText().toString().isEmpty()){
//                    txtTotalBill.setVisibility(View.GONE);
//                    txtDisplayTip.setVisibility(View.GONE);
//                }
//
//
//            }
//        });
//
//
////        btnDisplayTotalBill.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if(edBillAmount.getText().toString().isEmpty()){
////                    txtTotalBill.setVisibility(View.VISIBLE);
////                    txtTotalBill.setTextColor(Color.RED);
////                    txtTotalBill.setText(R.string.bill_amount_not_entered);
////                } else {
////                    try {
////                        double billAmount = Double.parseDouble(edBillAmount.getText().toString());
////                        int tipAmount = sbTipSelector.getProgress();
////                        double totalBillAmount = billAmount + (billAmount * tipAmount / 100);
////                        txtTotalBill.setTextColor(Color.WHITE);
////                        txtTotalBill.setVisibility(View.VISIBLE);
////                        txtTotalBill.setText(getString(R.string.total_bill, totalBillAmount));
////                    } catch (NumberFormatException e) {
////                        txtTotalBill.setVisibility(View.VISIBLE);
////                        txtTotalBill.setTextColor(Color.RED);
////                        txtTotalBill.setText(R.string.invalid_bill_amount);
////                    }
////                }
////            }
////        });
//
//    }
//}



//Custom alert dialog box
public class MainActivity extends AppCompatActivity {

    Button btnShowDialog, btnDialogYes, btnDialogNo;
    TextView tvShowChoice;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_ad_layout); // Change to your activity layout if needed

        tvShowChoice = findViewById(R.id.tvShowChoice);
        btnShowDialog = findViewById(R.id.btnShowDialog);

        // Create and configure the dialog
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_alert_dialog); // Use the correct dialog layout

        // Set dialog background drawable
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(this, R.drawable.custom_alert_bg));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }

        dialog.setCancelable(false);

        // Initialize dialog buttons
        btnDialogYes = dialog.findViewById(R.id.btnDialogYes);
        btnDialogNo = dialog.findViewById(R.id.btnDialogNo);

        btnDialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowChoice.setVisibility(View.VISIBLE);
                tvShowChoice.setText(btnDialogYes.getText());
                dialog.dismiss();
            }
        });

        btnDialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowChoice.setVisibility(View.VISIBLE);
                tvShowChoice.setText(btnDialogNo.getText());
                dialog.dismiss();
            }
        });

        // Show dialog when button is clicked
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }
}