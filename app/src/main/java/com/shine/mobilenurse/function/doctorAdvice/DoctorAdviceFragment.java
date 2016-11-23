package com.shine.mobilenurse.function.doctorAdvice;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:医嘱执行
 */

public class DoctorAdviceFragment extends BaseFragment {


    public static DoctorAdviceFragment newInstance() {
        return new DoctorAdviceFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_doctor_advice;
    }


}
