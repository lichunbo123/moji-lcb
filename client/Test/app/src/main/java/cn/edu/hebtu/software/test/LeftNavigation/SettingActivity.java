package cn.edu.hebtu.software.test.LeftNavigation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cn.edu.hebtu.software.test.Activity.MainActivity;
import cn.edu.hebtu.software.test.R;

public class SettingActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private LinearLayout msgPermission;
    private LinearLayout changePwd;
    private int tabId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.MyTheam_color));

        }

        Intent request= getIntent();
        tabId = request.getIntExtra("tab", 0);

        getViews();
        registerListener();

    }

    private void getViews(){
        toolbar = findViewById(R.id.setting_toolbar);
        changePwd = findViewById(R.id.changePwd);
        msgPermission = findViewById(R.id.ll_msgPermission);
    }

    private void registerListener(){
        CustomOnClickListener listener = new CustomOnClickListener();
        toolbar.setNavigationOnClickListener(listener);
        msgPermission.setOnClickListener(listener);
        changePwd.setOnClickListener(listener);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                intent.putExtra("flag",true);
                intent.putExtra("tab", tabId);
                startActivity(intent);
                overridePendingTransition(R.animator.in_from_left, R.animator.out_to_right);
                finish();
            }
        });
    }

    class CustomOnClickListener implements View.OnClickListener{
        Intent intent = null;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_msgPermission:
                    intent = new Intent(SettingActivity.this, MsgPermissionActivity.class);
                    intent.putExtra("tab", tabId);
                    startActivity(intent);
                    break;
                case R.id.changePwd:
                    intent = new Intent(SettingActivity.this, ChangePwdActivity.class);
                    intent.putExtra("tab", tabId);
                    startActivity(intent);
                    break;
            }

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
