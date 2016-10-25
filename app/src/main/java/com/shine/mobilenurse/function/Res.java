package com.shine.mobilenurse.function;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.function.account.AccountFragment;
import com.shine.mobilenurse.function.assess.AssessFragment;
import com.shine.mobilenurse.function.beds.BedsFragment;
import com.shine.mobilenurse.function.blood.BloodFragment;
import com.shine.mobilenurse.function.call.CallFragment;
import com.shine.mobilenurse.function.checkResult.CheckResultFragment;
import com.shine.mobilenurse.function.custom.CustomActivity;
import com.shine.mobilenurse.function.doctorAdvice.DoctorAdviceFragment;
import com.shine.mobilenurse.function.infoquery.InfoQueryFragmeng;
import com.shine.mobilenurse.function.inspectionResult.InspectionResultFragment;
import com.shine.mobilenurse.function.mission.MissionFragment;
import com.shine.mobilenurse.function.notice.NoticeFragment;
import com.shine.mobilenurse.function.nursingMeasures.NursingMeasuresFragment;
import com.shine.mobilenurse.function.options.OptionsFragment;
import com.shine.mobilenurse.function.patrol.PatrolFragment;
import com.shine.mobilenurse.function.print.PrintFragment;
import com.shine.mobilenurse.function.signs.SignsFragment;
import com.shine.mobilenurse.function.skinTest.SkinTestFragment;
import com.shine.mobilenurse.function.specimen.SpecimenFragment;
import com.shine.mobilenurse.function.temperature.TemperatureFragment;

/**
 * Created by zzw on 2016/10/19.
 * 描述:
 */

public class Res {

    public static Drawable getDrawByTag(Context context, String tag) {
        int id = R.mipmap.ic_launcher;
        if (tag.equals(OptionsFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(AccountFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(AssessFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(BedsFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(BloodFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(CallFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(CheckResultFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(DoctorAdviceFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(InfoQueryFragmeng.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(InspectionResultFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(MissionFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(NoticeFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(NursingMeasuresFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(PatrolFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(PrintFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(SignsFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(SkinTestFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(SpecimenFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        } else if (tag.equals(TemperatureFragment.class.getName())) {
            id = R.mipmap.ic_launcher;
        }else if(tag.equals(CustomActivity.class.getName())){
            id = R.mipmap.ic_launcher;
        }
        return context.getResources().getDrawable(id);
    }


}
