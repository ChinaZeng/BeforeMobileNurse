package com.shine.mobilenurse.function;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseActivity;
import com.shine.mobilenurse.base.RecyclerItemClickListener;
import com.shine.mobilenurse.entity.Beds;
import com.shine.mobilenurse.entity.Option;
import com.shine.mobilenurse.function.account.AccountFragment;
import com.shine.mobilenurse.function.adapter.OptionAdapter;
import com.shine.mobilenurse.function.adapter.PopBedsAdapter;
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
import com.shine.mobilenurse.function.options.OptionsFragment;
import com.shine.mobilenurse.function.patrol.PatrolFragment;
import com.shine.mobilenurse.function.print.PrintFragment;
import com.shine.mobilenurse.function.signs.SignsFragment;
import com.shine.mobilenurse.function.skinTest.SkinTestFragment;
import com.shine.mobilenurse.function.specimen.SpecimenFragment;
import com.shine.mobilenurse.function.temperature.TemperatureFragment;
import com.shine.mobilenurse.utils.TDUtils;
import com.shine.mobilenurse.utils.ViewUtil;
import com.shine.mobilenurse.view.LogoAndTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {

    public static final int LEFT = 0x11;
    public static final int RIGHT = 0x12;

    private final static String TAGING = "taging";
    private final static String TABING = "tabing";

    private List<LogoAndTextView> tabViews = new ArrayList<>();
    private HashMap<String, Fragment> fragmentMap = new HashMap<>();

    //数据量较大时使用ArrayMap节省内存，牺牲查找时间为代价
//    private ArrayMap<String,Fragment> fragmentMap=new ArrayMap<>();


    private String taging = null;
    private int tabing = -1;

    private View line;
    private FrameLayout pup_duck_layout;
    private PopupWindow popupWindow;
    private int popViewHeight;

    private PopBedsAdapter bedsAdapter;
    private RecyclerView recyclerViewBeds;

//    private Fragment accountFragment, assessFragment, bedsFragment,
//            bloodFragment, callFragment, checkResultFragment, doctorAdviceFragment,
//            infoQueryFragment, inspectionResultFragment, missionFragment, noticeFragment,
//            nursingMeasuresFragment, optionsFragment, patrolFragment, printFragment, signsFragment,
//            skinTestFragment, specimenFragment, temperatureFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();

        Toolbar mToolbar = ViewUtil.$(this, R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setElevation(0);
        }
        setSupportActionBar(mToolbar);
//        setTranslucent(this);

    }


    /**
     * 使状态栏透明
     * <p>
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     *
     * @param activity 需要设置的activity
     */
    public void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        super.init(savedInstanceState);
        if (savedInstanceState == null) {
            selectionTab(0);
            selectFragment(OptionsFragment.class.getName());
        } else {
            fragmentMap.clear();
            String taging = savedInstanceState.getString(TAGING, OptionsFragment.class.getName());
            int tabing = savedInstanceState.getInt(TABING, 0);
            List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
            for (int i = 0; i < fragmentList.size(); i++) {
                Fragment fragment = fragmentList.get(i);
                String tag = fragment2Tag(fragment);
                if (tag != null)
                    fragmentMap.put(tag, fragment);
            }
            selectionTab(tabing);
            selectFragment(taging);
        }
    }

    /**
     * 根据fragment得到相应的tag
     *
     * @param fragment
     * @return
     */
    private String fragment2Tag(Fragment fragment) {
        String tag = null;
        if (fragment instanceof OptionsFragment) {
            tag = OptionsFragment.class.getName();
        } else if (fragment instanceof AccountFragment) {
            tag = AccountFragment.class.getName();
        } else if (fragment instanceof AssessFragment) {
            tag = AssessFragment.class.getName();
        } else if (fragment instanceof BloodFragment) {
            tag = BloodFragment.class.getName();
        } else if (fragment instanceof CallFragment) {
            tag = CallFragment.class.getName();
        } else if (fragment instanceof CheckResultFragment) {
            tag = CheckResultFragment.class.getName();
        } else if (fragment instanceof DoctorAdviceFragment) {
            tag = DoctorAdviceFragment.class.getName();
        } else if (fragment instanceof InfoQueryFragmeng) {
            tag = InfoQueryFragmeng.class.getName();
        } else if (fragment instanceof InspectionResultFragment) {
            tag = InspectionResultFragment.class.getName();
        } else if (fragment instanceof MissionFragment) {
            tag = MissionFragment.class.getName();
        } else if (fragment instanceof NoticeFragment) {
            tag = NoticeFragment.class.getName();
        } else if (fragment instanceof NursingMeasuresFragment) {
            tag = NursingMeasuresFragment.class.getName();
        } else if (fragment instanceof PatrolFragment) {
            tag = PatrolFragment.class.getName();
        } else if (fragment instanceof PrintFragment) {
            tag = PrintFragment.class.getName();
        } else if (fragment instanceof SignsFragment) {
            tag = SignsFragment.class.getName();
        } else if (fragment instanceof SkinTestFragment) {
            tag = SkinTestFragment.class.getName();
        } else if (fragment instanceof SpecimenFragment) {
            tag = SpecimenFragment.class.getName();
        } else if (fragment instanceof TemperatureFragment) {
            tag = TemperatureFragment.class.getName();
        }

        return tag;
    }

    /**
     * 得到相应的Fragment
     *
     * @param tag
     * @return
     */
    private Fragment tag2Fragment(String tag) {

        if (tag == null)
            return null;

        Fragment fragment = getFragmentFromMap(tag);
        if (fragment != null)
            return fragment;

        if (tag.equals(OptionsFragment.class.getName())) {
            fragment = OptionsFragment.newInstance();
        } else if (tag.equals(AccountFragment.class.getName())) {
            fragment = AccountFragment.newInstance();
        } else if (tag.equals(AssessFragment.class.getName())) {
            fragment = AssessFragment.newInstance();
        } else if (tag.equals(BedsFragment.class.getName())) {
            fragment = BedsFragment.newInstance();
        } else if (tag.equals(BloodFragment.class.getName())) {
            fragment = BloodFragment.newInstance();
        } else if (tag.equals(CallFragment.class.getName())) {
            fragment = CallFragment.newInstance();
        } else if (tag.equals(CheckResultFragment.class.getName())) {
            fragment = CheckResultFragment.newInstance();
        } else if (tag.equals(DoctorAdviceFragment.class.getName())) {
            fragment = DoctorAdviceFragment.newInstance();
        } else if (tag.equals(InfoQueryFragmeng.class.getName())) {
            fragment = InfoQueryFragmeng.newInstance();
        } else if (tag.equals(InspectionResultFragment.class.getName())) {
            fragment = InspectionResultFragment.newInstance();
        } else if (tag.equals(MissionFragment.class.getName())) {
            fragment = MissionFragment.newInstance();
        } else if (tag.equals(NoticeFragment.class.getName())) {
            fragment = NoticeFragment.newInstance();
        } else if (tag.equals(NursingMeasuresFragment.class.getName())) {
            fragment = NursingMeasuresFragment.newInstance();
        } else if (tag.equals(PatrolFragment.class.getName())) {
            fragment = PatrolFragment.newInstance();
        } else if (tag.equals(PrintFragment.class.getName())) {
            fragment = PrintFragment.newInstance();
        } else if (tag.equals(SignsFragment.class.getName())) {
            fragment = SignsFragment.newInstance();
        } else if (tag.equals(SkinTestFragment.class.getName())) {
            fragment = SkinTestFragment.newInstance();
        } else if (tag.equals(SpecimenFragment.class.getName())) {
            fragment = SpecimenFragment.newInstance();
        } else if (tag.equals(TemperatureFragment.class.getName())) {
            fragment = TemperatureFragment.newInstance();
        }
        addFragment(fragment);
        return fragment;
    }

    /**
     * 从已有的fragment数据源里面取出数据
     *
     * @param tag
     * @return
     */
    private Fragment getFragmentFromMap(String tag) {
        Fragment fragment = fragmentMap.get(tag);
        return fragment;
    }

    /**
     * 将fragment添加到fragmentTransaction和map里面
     *
     * @param fragment
     */
    private void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_layout, fragment, fragment.getClass().getName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        fragmentMap.put(fragment.getClass().getName(), fragment);
    }

    /**
     * 影藏全部的fragment
     *
     * @param fragmentTransaction
     */
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fragmentMap.size() == 0) {
            return;
        }
        Iterator<Map.Entry<String, Fragment>> iterator = fragmentMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Fragment> entry = iterator.next();
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            Fragment fragment = entry.getValue();
            if (fragment != null)
                fragmentTransaction.hide(fragment);
        }
    }


    /**
     * 选择
     *
     * @param tag
     */
    public void selectFragment(String tag) {
        selectFragment(tag, 0);
    }

    /**
     * 带有动画的选择
     *
     * @param tag
     * @param d   LEFT左点击  RIGHT右点击
     */
    public void selectFragment(String tag, int d) {

//        //快速点击返回
//        if (TDUtils.isFastClick())
//            return;

        if (tag == null)
            return;

        //当前显示的tag和点击的相同
        if (taging != null && taging.equals(tag)) {
            return;
        }

        Fragment fragment = tag2Fragment(tag);
        if (fragment == null)
            return;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (d == LEFT) {
            fragmentTransaction.setCustomAnimations(R.anim.anim_left_into, R.anim.anim_right_out);
        } else if (d == RIGHT) {
            fragmentTransaction.setCustomAnimations(R.anim.anim_right_into, R.anim.anim_left_out);
        }

        //隐藏当前fragment
        if (taging != null) {
            Fragment fragmenting = tag2Fragment(taging);
            if (fragmenting != null && !fragmenting.isHidden()) {
                fragmentTransaction.hide(fragmenting);
            }
        } else {//影藏全部fragment
            hideAllFragment(fragmentTransaction);
        }
        fragmentTransaction.show(fragment);
        taging = tag;
        fragmentTransaction.commit();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TAGING, taging);
        outState.putInt(TAGING, tabing);
    }

    @Override
    protected void findViewId() {
        super.findViewId();

        LogoAndTextView tab0 = ViewUtil.$(this, R.id.main_tab_0);
        LogoAndTextView tab1 = ViewUtil.$(this, R.id.main_tab_1);
        LogoAndTextView tab2 = ViewUtil.$(this, R.id.main_tab_2);
        LogoAndTextView tab3 = ViewUtil.$(this, R.id.main_tab_3);
        tabViews.add(tab0);
        tabViews.add(tab1);
        tabViews.add(tab2);
        tabViews.add(tab3);
        tab0.setOnClickListener(this);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);

        View v = ViewUtil.$(this, R.id.pup_into);
        v.setOnClickListener(this);

        line = ViewUtil.$(this, R.id.h_line);
        pup_duck_layout = ViewUtil.$(this, R.id.pup_duck_layout);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.main_tab_0:
                selectionTab(0);
                selectFragment(OptionsFragment.class.getName(), LEFT);
                break;
            case R.id.main_tab_1:
                selectionTab(1);
                break;
            case R.id.main_tab_2:
                selectionTab(2);
                break;
            case R.id.main_tab_3:
                selectionTab(3);

                break;
            case R.id.pup_into:
                showPopToViewTop(line);
                break;


            case R.id.pup_out:
                if (popupWindow != null && popupWindow.isShowing())
                    popupWindow.dismiss();
                break;
        }
    }

    /**
     * 选择床位
     *
     * @param pos
     */
    private void chooseBeds(int pos) {
        if (bedsAdapter.chooseBeds(pos)) {
            recyclerViewBeds.smoothScrollToPosition(comPos(pos, bedsAdapter.getOldPos()));
        }
    }


    /**
     * 计算床位跳转的位置
     *
     * @param pos 当前位置
     * @param old 原来位置
     * @return
     */
    private int comPos(int pos, int old) {
        int a = 0;
        //1.recy宽度
        int recyW = recyclerViewBeds.getWidth();
        //2.可见item总长度
        int itemLen = 0;
        for (int i = 0; i < recyclerViewBeds.getChildCount(); i++) {
            itemLen += recyclerViewBeds.getChildAt(i).getWidth();
        }
        //3.item的宽度
        int itemW = itemLen / recyclerViewBeds.getChildCount();
        //4.recy界面里面显示可见多少个item
        int rCount = recyW / itemW;

        if (pos <= rCount / 2) {
            a = 0;
        } else if (pos < bedsAdapter.getItemCount() - rCount / 2) {
            if (pos > old) {
                a = pos + rCount / 2;
            } else {
                a = pos - rCount / 2;
            }
        } else if (pos >= bedsAdapter.getItemCount() - rCount / 2) {
            a = bedsAdapter.getItemCount();
        }
        return a;
    }

    /**
     * 在指定View上方显示popWindow
     *
     * @param v
     */
    private void showPopToViewTop(View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        if (popupWindow != null) {
            //测试
            bedsAdapter.clear();
            bedsAdapter.addItems(testData());

            popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1] - popViewHeight);
            ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 178);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int alpha = (int) animation.getAnimatedValue();
                    pup_duck_layout.setForeground(new ColorDrawable(Color.argb(alpha, 0, 0, 0)));
                }
            });
            valueAnimator.setDuration(100).start();
        } else {
            initPop();
            showPopToViewTop(v);
        }
    }


    private List<Beds> testData() {
        List<Beds> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            list.add(new Beds(i, "张三" + i));
        }
        return list;
    }

    private List<Option> addOptionData() {
        List<Option> opData = new ArrayList<>();
        opData.add(new Option("新", "new", 0));
        opData.add(new Option("热", "hot", 0));
        opData.add(new Option("普", "pu", 0));
        opData.add(new Option("二级", "two", 0));
        return opData;
    }


    /**
     * 初始化popwindow
     */
    private void initPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_main_pup, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        recyclerViewBeds = ViewUtil.$(view, R.id.pup_recy_beds);
        recyclerViewBeds.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        bedsAdapter = new PopBedsAdapter(this);
        recyclerViewBeds.setAdapter(bedsAdapter);
        bedsAdapter.addItems(testData());
        recyclerViewBeds.addOnItemTouchListener(new RecyclerItemClickListener(this) {
            @Override
            protected void onItemClick(View view, int position) {
                chooseBeds(position);
            }
        });

        RecyclerView recyclerView_ = ViewUtil.$(view, R.id.pup_recy_);
        recyclerView_.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        final OptionAdapter adapter = new OptionAdapter(this, OptionAdapter.CHILD_W_WARP);
        recyclerView_.setAdapter(adapter);
        adapter.addItems(addOptionData());
        recyclerView_.addOnItemTouchListener(new RecyclerItemClickListener(this) {
            @Override
            protected void onItemClick(View view, int position) {
                chooseBeds((int) (Math.random() * bedsAdapter.getItemCount()));
            }
        });

        View pup_out = ViewUtil.$(view, R.id.pup_out);
        pup_out.setOnClickListener(this);

//        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(h, h);
        popViewHeight = view.getMeasuredHeight();
//        int width = view.getMeasuredWidth();

        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(view);
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(178, 0);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int alpha = (int) animation.getAnimatedValue();
                        pup_duck_layout.setForeground(new ColorDrawable(Color.argb(alpha, 0, 0, 0)));
                    }
                });
                valueAnimator.setDuration(100).start();
            }
        });

    }

    /**
     * 选择下方tab
     *
     * @param index
     */
    private void selectionTab(int index) {

        //快速点击返回
        if (TDUtils.isFastClick())
            return;

        if (tabing == index)
            return;
        for (int i = 0; i < tabViews.size(); i++) {
            LogoAndTextView view = tabViews.get(i);
            if (i == index) {
                this.tabing = index;
                view.setTextColor(getResources().getColor(R.color.color_ec5a7));
                view.setSrc(getResources().getDrawable(R.mipmap.ic_launcher));
            } else {
                view.setTextColor(getResources().getColor(R.color.color_525252));
                view.setSrc(getResources().getDrawable(R.mipmap.ic_launcher));
            }
        }
    }


    private long mExitTime; //退出时间

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                UI.showToast(this, R.string.toast_app_exit_for_double);
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
