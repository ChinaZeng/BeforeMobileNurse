package com.shine.mobilenurse.function.options;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioGroup;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;
import com.shine.mobilenurse.db.OptionDao;
import com.shine.mobilenurse.entity.Option;
import com.shine.mobilenurse.function.MainActivity;
import com.shine.mobilenurse.function.Res;
import com.shine.mobilenurse.function.account.AccountFragment;
import com.shine.mobilenurse.function.adapter.OptionGridViewAdapter;
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
import com.shine.mobilenurse.function.patrol.PatrolFragment;
import com.shine.mobilenurse.function.print.PrintFragment;
import com.shine.mobilenurse.function.signs.SignsFragment;
import com.shine.mobilenurse.function.specimen.SpecimenFragment;
import com.shine.mobilenurse.function.temperature.TemperatureFragment;
import com.shine.mobilenurse.utils.LogPrint;
import com.shine.mobilenurse.utils.ViewUtil;
import com.shine.mobilenurse.view.LogoAndTextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zzw on 2016/10/13.
 * 描述:首页选项
 */
public class OptionsFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {
    private List<LogoAndTextView> topViewList = new ArrayList<>();
    private List<LogoAndTextView> mid1ViewList = new ArrayList<>();
    private List<LogoAndTextView> bottomViewList = new ArrayList<>();

    private GridView gridView;
    private OptionGridViewAdapter adapter;

    public static OptionsFragment newInstance() {
        return new OptionsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_options;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        adapter = new OptionGridViewAdapter(getActivity());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }


    @Override
    protected void initData() {
        super.initData();
        setData();
    }

    private void setData() {
        List<Option> topData = OptionDao.getChatDao(getContext()).findTop();
        if (topData.size() == 0) {
            setTopInstanceData(topData);
        }
        setData2UI(topViewList, topData);

        List<Option> data = OptionDao.getChatDao(getContext()).findMid0();
        if (data.size() == 0) {
            setMid0InstanceData(data);
        }
        adapter.addItems(data);

        List<Option> mid1Data = OptionDao.getChatDao(getContext()).findMid1();
        if (mid1Data.size() == 0) {
            setMid1InstanceData(mid1Data);
        }
        setData2UI(mid1ViewList, mid1Data);

        List<Option> bottomData = OptionDao.getChatDao(getContext()).findBottom();
        if (bottomData.size() == 0) {
            setBottomViewList(bottomData);
        }
        setData2UI(bottomViewList, bottomData);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {

        } else {

        }
    }


    @Override
    protected void findViewId(View view) {
        super.findViewId(view);

        LogoAndTextView viewTop0 = ViewUtil.$(view, R.id.op_top_0);
        LogoAndTextView viewTop1 = ViewUtil.$(view, R.id.op_top_1);
        LogoAndTextView viewTop2 = ViewUtil.$(view, R.id.op_top_2);
        LogoAndTextView viewTop3 = ViewUtil.$(view, R.id.op_top_3);
        topViewList.add(viewTop0);
        topViewList.add(viewTop1);
        topViewList.add(viewTop2);
        topViewList.add(viewTop3);
        viewTop0.setOnClickListener(this);
        viewTop1.setOnClickListener(this);
        viewTop2.setOnClickListener(this);
        viewTop3.setOnClickListener(this);

        LogoAndTextView viewMid1_0 = ViewUtil.$(view, R.id.op_mid_1_0);
        LogoAndTextView viewMid1_1 = ViewUtil.$(view, R.id.op_mid_1_1);
        LogoAndTextView viewMid1_2 = ViewUtil.$(view, R.id.op_mid_1_2);
        LogoAndTextView viewMid1_3 = ViewUtil.$(view, R.id.op_mid_1_3);
        mid1ViewList.add(viewMid1_0);
        mid1ViewList.add(viewMid1_1);
        mid1ViewList.add(viewMid1_2);
        mid1ViewList.add(viewMid1_3);
        viewMid1_0.setOnClickListener(this);
        viewMid1_1.setOnClickListener(this);
        viewMid1_2.setOnClickListener(this);
        viewMid1_3.setOnClickListener(this);

        LogoAndTextView viewBottom0 = ViewUtil.$(view, R.id.op_bottom_0);
        LogoAndTextView viewBottom1 = ViewUtil.$(view, R.id.op_bottom_1);
        LogoAndTextView viewBottom2 = ViewUtil.$(view, R.id.op_bottom_2);
        bottomViewList.add(viewBottom0);
        bottomViewList.add(viewBottom1);
        bottomViewList.add(viewBottom2);
        viewBottom0.setOnClickListener(this);
        viewBottom1.setOnClickListener(this);
        viewBottom2.setOnClickListener(this);

        RadioGroup rg = ViewUtil.$(view, R.id.radioGroup);
        rg.setOnCheckedChangeListener(this);

        gridView = ViewUtil.$(view, R.id.gridView);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.op_top_0:
            case R.id.op_top_1:
            case R.id.op_top_2:
            case R.id.op_top_3:
            case R.id.op_mid_1_0:
            case R.id.op_mid_1_1:
            case R.id.op_mid_1_2:
            case R.id.op_mid_1_3:
            case R.id.op_bottom_0:
            case R.id.op_bottom_1:
                if (v instanceof LogoAndTextView) {
                    LogoAndTextView view = (LogoAndTextView) v;
                    clickOp(view.getTeg());
                }
                break;

            //自定义
            case R.id.op_bottom_2:

                break;
        }
    }


    /**
     * 点击之后的切换调用
     *
     * @param tag
     */
    public void clickOp(String tag) {
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) activity;
            mainActivity.selectFragment(tag);
        }
    }

    /**
     * 设置数据
     *
     * @param views
     * @param data
     */
    private void setData2UI(List<LogoAndTextView> views, List<Option> data) {
        int a = data.size() > views.size() ? views.size() : data.size();
        for (int i = 0; i < a; i++) {
            Option bean = data.get(i);
            LogoAndTextView view = views.get(i);
            view.setSrc(Res.getDrawByTag(getContext(), bean.getTeg()));
            view.setText(bean.getName());
            view.setTeg(bean.getTeg());
        }
    }

    /**
     * 设置顶部菜单初始化数据
     *
     * @param dataList
     */
    private void setTopInstanceData(List<Option> dataList) {
        dataList.add(new Option("医嘱执行", DoctorAdviceFragment.class.getName(), 0));
        dataList.add(new Option("生命体征", SignsFragment.class.getName(), 0));
        dataList.add(new Option("记账管理", AccountFragment.class.getName(), 0));
        dataList.add(new Option("床位管理", BedsFragment.class.getName(), 0));
        OptionDao.getChatDao(getContext()).insert(dataList);
    }

    /**
     * 中间上部分菜单设置初始化数据
     *
     * @param dataList
     */
    private void setMid0InstanceData(List<Option> dataList) {
        dataList.add(new Option("标本绑定", SpecimenFragment.class.getName(), 1));
        dataList.add(new Option("采血管理", BloodFragment.class.getName(), 1));
        dataList.add(new Option("皮试管理", PatrolFragment.class.getName(), 1));
        dataList.add(new Option("信息查询", InfoQueryFragmeng.class.getName(), 1));
        dataList.add(new Option("宣教", MissionFragment.class.getName(), 1));
        dataList.add(new Option("评估管理", AssessFragment.class.getName(), 1));
        dataList.add(new Option("体温单", TemperatureFragment.class.getName(), 1));
        dataList.add(new Option("巡视管理", PatrolFragment.class.getName(), 1));
        OptionDao.getChatDao(getContext()).insert(dataList);
    }

    /**
     * 中间下部分菜单初始化数据
     *
     * @param mid1Data
     */
    private void setMid1InstanceData(List<Option> mid1Data) {
        mid1Data.add(new Option("护理措施", NursingMeasuresFragment.class.getName(), 2));
        mid1Data.add(new Option("呼叫管理", CallFragment.class.getName(), 2));
        mid1Data.add(new Option("通知管理", NoticeFragment.class.getName(), 2));
        mid1Data.add(new Option("远程打印", PrintFragment.class.getName(), 2));
        OptionDao.getChatDao(getContext()).insert(mid1Data);
    }

    /**
     * 设置底部部分菜单初始化数据
     *
     * @param bottomData
     */
    private void setBottomViewList(List<Option> bottomData) {
        bottomData.add(new Option("检查结果", CheckResultFragment.class.getName(), 3));
        bottomData.add(new Option("检验结果", InspectionResultFragment.class.getName(), 3));
        bottomData.add(new Option("自定义", CustomActivity.class.getName(), 3));
        OptionDao.getChatDao(getContext()).insert(bottomData);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_0:

                break;

            case R.id.rb_1:

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        clickOp(adapter.getItem(position).getTeg());
    }
}
