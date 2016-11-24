package com.shine.mobilenurse.function.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseActivity;
import com.shine.mobilenurse.entity.Beds;
import com.shine.mobilenurse.entity.Option;
import com.shine.mobilenurse.function.OnRecyItemClickListener;
import com.shine.mobilenurse.function.UI;
import com.shine.mobilenurse.function.adapter.MainbedsPupAdapter;
import com.shine.mobilenurse.view.InterceptFrameLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, OnRecyItemClickListener<Beds>, PopupWindow.OnDismissListener {

    @BindView(R.id.toolbar_left_logo)
    ImageView toolbarLeftLogo;
    @BindView(R.id.toolbar_title_spinner)
    Spinner toolbarTitleSpinner;
    @BindView(R.id.toolbar_right_top_text)
    TextView toolbarRightTopText;
    @BindView(R.id.toolbar_right_witch)
    SwitchCompat toolbarRightWitch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_beds_left)
    TextView mainBedsLeft;
    @BindView(R.id.main_beds_mid)
    TextView mainBedsMid;
    @BindView(R.id.main_beds_mid_layout)
    LinearLayout mainBedsMidLayout;
    @BindView(R.id.main_beds_right)
    TextView mainBedsRight;
    @BindView(R.id.activity_main)
    InterceptFrameLayout activityMain;
    @BindView(R.id.toolbar_left_go_home)
    TextView toolbarLeftGoHome;
    @BindView(R.id.toolbar_title_text)
    TextView toolbarTitleText;
    @BindView(R.id.toolbar_right_rl)
    RelativeLayout toolbarRightRl;
    private Fragment mainFragment, optionFragment;

    private final String NOW_MAIN_FRAGMENT_POS = "NOW_MAIN_FRAGMENT_POS";

    //长时间无使用的时候 系统回收 记录位置
    private int nowPos = -1;

    private PopupWindow popupWindow;
    private MainbedsPupAdapter adapter;
    private RecyclerView pupRecy;


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        int pos = 0;
        if (savedInstanceState == null) {

        } else {
            pos = savedInstanceState.getInt(NOW_MAIN_FRAGMENT_POS, 0);
        }
        chooseFragment(pos);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(0);
        }
        setSupportActionBar(toolbar);
        toolbarRightWitch.setOnCheckedChangeListener(this);

    }


    @Override
    protected void initData() {
        super.initData();
        List<String> data = new ArrayList<>();
        data.add("科室1");
        data.add("科室2");
        data.add("科室3");
        data.add("科室4");
        toolbarTitleSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
    }

    /**
     * @param opId 选项ID
     */
    public void setToolbar(int opId) {
        switch (opId) {
            //主页
            case 0:
                //医嘱执行
            case 1:
                //生命体征
            case 2:
                //记账管理
            case 3:
                //床位管理
            case 4:
                //标本绑定
            case 5:
                //采血管理
            case 6:
                //皮试管理
            case 7:
                //巡视管理
            case 8:
                //评估管理
            case 9:
                //体温单
            case 10:
                //护理措施
            case 11:
                //信息查询
            case 12:
                //呼叫管理
            case 13:
                //通知管理
            case 14:
                //宣教
            case 15:
                //远程打印
            case 16:
                //检验结果
            case 17:
                //检查结果
            case 18:
                break;
            //更多
            case 19:

                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }


    public void chooseFragment(int pos) {
        chooseFragment(pos, 0, null);
    }

    /**
     * @param pos     0表示主页面  1表示选项卡页面
     * @param animDec 0表示无动画 1表示left进入 2表示right进入
     * @param option  进入tabLayout里面跳转到那个界面
     */
    public void chooseFragment(int pos, int animDec, Option option) {

        if (pos == nowPos)
            return;
        this.nowPos = pos;


        // 开启一个Fragment事务
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (animDec == 1) {
            fragmentTransaction.setCustomAnimations(R.anim.anim_left_into, R.anim.anim_right_out);
        } else if (animDec == 2) {
            fragmentTransaction.setCustomAnimations(R.anim.anim_right_into, R.anim.anim_left_out);
        }

        switch (pos) {
            case 0:
                if (optionFragment != null)
                    fragmentTransaction.hide(optionFragment);

                if (mainFragment == null) {
                    mainFragment = MainFragment.newInstance();
                    fragmentTransaction.add(R.id.frame_layout, mainFragment);
                } else {
                    fragmentTransaction.show(mainFragment);
                }

                break;
            case 1:
                if (mainFragment != null)
                    fragmentTransaction.hide(mainFragment);

                if (optionFragment == null) {
                    optionFragment = OptionFragment.newInstance();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(OptionFragment.FIRST_INTO, option);
                    optionFragment.setArguments(bundle);
                    fragmentTransaction.add(R.id.frame_layout, optionFragment);
                } else {
                    fragmentTransaction.show(optionFragment);
                }

                break;
        }

        fragmentTransaction.commit();

        if (pos == 1) {
            EventBus.getDefault().post(option);
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(NOW_MAIN_FRAGMENT_POS, nowPos);
    }

    private long mExitTime; //退出时间

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
                return true;
            }

            if (nowPos == 1) {
                chooseFragment(0, 1, null);
                return true;
            }


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


    @OnClick({R.id.toolbar_left_logo, R.id.main_beds_left, R.id.main_beds_mid_layout, R.id.main_beds_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_logo:
                break;
            case R.id.main_beds_left:
                break;
            case R.id.main_beds_mid_layout:
                showPop();
                break;
            case R.id.main_beds_right:

                break;
        }
    }


    private void showPop() {
        if (popupWindow == null || adapter == null) {
            initPup();
            showPop();
        } else {
            activityMain.setInterceptType(InterceptFrameLayout.INTERCEPT);
            popupWindow.showAtLocation(activityMain, Gravity.BOTTOM, 0, 0);
            pupShowMainBgAnim();
        }
    }

    /**
     * 初始化pop
     */
    private void initPup() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_main_pup, null);

        view.findViewById(R.id.pup_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing())
                    popupWindow.dismiss();
            }
        });

        pupRecy = (RecyclerView) view.findViewById(R.id.pup_recy);
        pupRecy.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new MainbedsPupAdapter(this);
        adapter.setOnRecyItemClickListener(this);
        pupRecy.setAdapter(adapter);
        adapter.addItems(initBedsData());

        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(view);
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        //设置为true  扫描枪失去作用
        popupWindow.setTouchable(true);
        popupWindow.setOnDismissListener(this);
    }


    private List<Beds> initBedsData() {
        List<Beds> list = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            list.add(new Beds(i, "李四"));
        }

        return list;
    }

    @Override
    public void OnItemClick(View view, Beds beds, int pos) {
        if (adapter.chooseBeds(pos)) {
            pupRecy.smoothScrollToPosition(pos);
        }
    }

    /**
     * pup消失的时候  bg变亮 并且设置activityMain不拦截点击事件
     */
    @Override
    public void onDismiss() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(178, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int alpha = (int) animation.getAnimatedValue();
                activityMain.setForeground(new ColorDrawable(Color.argb(alpha, 0, 0, 0)));
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //消失的时候父布局不拦截点击事件
                activityMain.setInterceptType(InterceptFrameLayout.NOT_INTERCEPT);
            }
        });

        valueAnimator.setDuration(100).start();
    }

    /**
     * pup显示的时候的动画显示  bg变暗 并且设置activityMain拦截点击事件防止外部点击
     */
    public void pupShowMainBgAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 178);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int alpha = (int) animation.getAnimatedValue();
                activityMain.setForeground(new ColorDrawable(Color.argb(alpha, 0, 0, 0)));
            }
        });
        valueAnimator.setDuration(100).start();
    }
}
