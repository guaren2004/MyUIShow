package com.example.robin.coordinatorlayouttest.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.robin.coordinatorlayouttest.R;
import com.example.robin.coordinatorlayouttest.utils.Util;

/**
 * 常用控件
 * 1. 下拉列表(Spinner)
 * 2. 日期选择器(DatePickerDialog)
 * 3. 时间选择器(TimePickerDialog)
 * 4. 单项选择(RadioGroup 包含 RadioButton)
 * 5. 多项选择(CheckBox)
 */
public class FragmentTwo extends BaseFragment implements CompoundButton.OnCheckedChangeListener {

    private String[] spinnerData;
    private Spinner spinner;
    private Button btn_chooseDate;
    private Button btn_chooseTime;
    private Button btn_submit;
    private RadioButton rb_C;
    private TextView tv_result;
    private CheckBox cb_1;
    private CheckBox cb_2;
    private CheckBox cb_3;
    private CheckBox cb_4;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initView() {
        spinner = (Spinner) view.findViewById(R.id.spinner);
        btn_chooseDate = (Button) view.findViewById(R.id.btn_chooseDate);
        btn_chooseTime = (Button) view.findViewById(R.id.btn_chooseTime);
        btn_submit = (Button) view.findViewById(R.id.btn_submit);
        rb_C = (RadioButton) view.findViewById(R.id.rb_C);
        tv_result = (TextView) view.findViewById(R.id.tv_result);
        cb_1 = (CheckBox) view.findViewById(R.id.cb_1);
        cb_2 = (CheckBox) view.findViewById(R.id.cb_2);
        cb_3 = (CheckBox) view.findViewById(R.id.cb_3);
        cb_4 = (CheckBox) view.findViewById(R.id.cb_4);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initData() {
        // 1. 下拉列表(Spinner)
        // 注意: spinner.setAdapter(SpinnerAdapter adapter)中的参数可以用 ArrayAdapter 代替
        // 而且 ArrayAdapter 的构造函数中的第二个参数需要一个带有 TextView 资源 ID
        spinnerData = new String[]{"北京", "上海", "广州", "成都"};
        spinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, spinnerData));
        spinner.setPopupBackgroundResource(R.drawable.activity_main3_bg);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Util.showToast(getActivity(), spinnerData[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 2. 日期选择器(DatePickerDialog)
        // 注意: 月份是按照 0-11 来计算的, 所以 DatePickerDialog 构造函数最后三个年月日如果要
        // 在初始的时候显示 2017年3月2日, 那么就应该填写 2017, 2, 2
        // 同理, onDateSet 的参数中的 month 也是一样, 所以打印出来的时候需要加一
        btn_chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        final String dateStr = year + "-" + (month + 1) + "-" + dayOfMonth;
                        Util.showToast(getActivity(), dateStr);
                        btn_chooseDate.setText(dateStr);
                    }
                }, 2017, 2, 2).show(); // 后面三个数字是初始打开 DatePickerDialog 的时候显示的日期位置
            }
        });

        // 3. 时间选择器(TimePickerDialog)
        btn_chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        final String timeStr = hourOfDay + ":" + minute;
                        Util.showToast(getActivity(), timeStr);
                        btn_chooseTime.setText(timeStr);
                    }
                }, 22, 9, true).show();
            }
        });

        // 4. 单项选择(RadioGroup 包含 RadioButton)
        // 注意: RadioButton 的选中状态是 checked, 而不是 selected
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb_C.isChecked()) {
                    Util.showToast(getActivity(), "选择正确");
                } else {
                    Util.showToast(getActivity(), "选择错误, 请重新选择");
                }
            }
        });

        // 5. 多项选择(CheckBox)
        cb_1.setOnCheckedChangeListener(this);
        cb_2.setOnCheckedChangeListener(this);
        cb_3.setOnCheckedChangeListener(this);
        cb_4.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String result = "";
        if (cb_1.isChecked()) {
            result += cb_1.getText();
        }
        if (cb_2.isChecked()) {
            result += cb_2.getText();
        }
        if (cb_3.isChecked()) {
            result += cb_3.getText();
        }
        if (cb_4.isChecked()) {
            result += cb_4.getText();
        }
        tv_result.setText(result);
    }
}
