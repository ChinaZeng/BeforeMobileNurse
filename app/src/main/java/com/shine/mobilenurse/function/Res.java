package com.shine.mobilenurse.function;


import android.support.v4.app.Fragment;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.function.account.AccountFragment;
import com.shine.mobilenurse.function.assess.AssessFragment;
import com.shine.mobilenurse.function.beds.BedsFragment;
import com.shine.mobilenurse.function.blood.BloodFragment;
import com.shine.mobilenurse.function.call.CallFragment;
import com.shine.mobilenurse.function.checkResult.CheckResultFragment;
import com.shine.mobilenurse.function.doctorAdvice.DoctorAdviceFragment;
import com.shine.mobilenurse.function.infoquery.InfoQueryFragmeng;
import com.shine.mobilenurse.function.inspectionResult.InspectionResultFragment;
import com.shine.mobilenurse.function.mission.MissionFragment;
import com.shine.mobilenurse.function.notice.NoticeFragment;
import com.shine.mobilenurse.function.nursingMeasures.NursingMeasuresFragment;
import com.shine.mobilenurse.function.patrol.PatrolFragment;
import com.shine.mobilenurse.function.print.PrintFragment;
import com.shine.mobilenurse.function.signs.SignsFragment;
import com.shine.mobilenurse.function.skinTest.SkinTestFragment;
import com.shine.mobilenurse.function.specimen.SpecimenFragment;
import com.shine.mobilenurse.function.temperature.TemperatureFragment;

/**
 * Created by zzw on 2016/11/24.
 * 描述:
 */

public class Res {

    /**
     * 得到logoID
     *
     * @param opid
     * @return
     */
    public static int getLogoIdFromOpId(int opid) {

        int logoId = R.mipmap.ic_launcher;
        switch (opid) {
            //医嘱执行
            case 1:

                break;
            //生命体征
            case 2:

                break;
            //记账管理
            case 3:

                break;
            //床位管理
            case 4:

                break;
            //标本绑定
            case 5:

                //采血管理
            case 6:

                break;
            //皮试管理
            case 7:
                break;
            //巡视管理
            case 8:

                break;
            //评估管理
            case 9:

                break;
            //体温单
            case 10:

                break;
            //护理措施
            case 11:
                break;
            //信息查询
            case 12:

                break;
            //呼叫管理
            case 13:
                break;
            //通知管理
            case 14:
                break;
            //宣教
            case 15:

                break;
            //远程打印
            case 16:
                break;
            //检验结果
            case 17:

                break;
            //检查结果
            case 18:

                break;
            //更多
            case 19:

                break;
        }

        return logoId;
    }

    /**
     * 得到Fragmnet
     *
     * @param opid
     * @return
     */
    public static Fragment getFragmentFromOpId(int opid) {

        Fragment fragment = null;
        switch (opid) {

            //医嘱执行
            case 1:
                fragment = DoctorAdviceFragment.newInstance();
                break;
            //生命体征
            case 2:
                fragment = SignsFragment.newInstance();
                break;
            //记账管理
            case 3:
                fragment = AccountFragment.newInstance();
                break;
            //床位管理
            case 4:
                fragment = BedsFragment.newInstance();
                break;
            //标本绑定
            case 5:
                fragment = SpecimenFragment.newInstance();
                break;
            //采血管理
            case 6:
                fragment = BloodFragment.newInstance();
                break;
            //皮试管理
            case 7:
                fragment = SkinTestFragment.newInstance();
                break;
            //巡视管理
            case 8:
                fragment = PatrolFragment.newInstance();
                break;
            //评估管理
            case 9:
                fragment = AssessFragment.newInstance();
                break;
            //体温单
            case 10:
                fragment = TemperatureFragment.newInstance();
                break;
            //护理措施
            case 11:
                fragment = NursingMeasuresFragment.newInstance();
                break;
            //信息查询
            case 12:
                fragment = InfoQueryFragmeng.newInstance();
                break;
            //呼叫管理
            case 13:
                fragment = CallFragment.newInstance();
                break;
            //通知管理
            case 14:
                fragment = NoticeFragment.newInstance();
                break;
            //宣教
            case 15:
                fragment = MissionFragment.newInstance();
                break;
            //远程打印
            case 16:
                fragment = PrintFragment.newInstance();
                break;
            //检验结果
            case 17:
                fragment = InspectionResultFragment.newInstance();
                break;
            //检查结果
            case 18:
                fragment = CheckResultFragment.newInstance();
                break;
            //更多
            case 19:

                break;
        }

        return fragment;
    }
}
