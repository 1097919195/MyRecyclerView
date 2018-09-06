package com.example.zjl.myrecyclerview.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zjl.myrecyclerview.R;

/**
 * Created by Administrator on 2018/9/6 0006.
 */

public class ClipActivity extends Activity implements View.OnClickListener {
    private EditText copy_edt, paste_edt;
    private Button copy_btn, paste_btn;
    //剪切板管理工具类
    private ClipboardManager mClipboardManager;
    //剪切板Data对象
    private ClipData mClipData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_clip);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        initViews();
        initListeners();
    }

    private void initViews() {
        this.copy_btn = (Button) findViewById(R.id.copy_btn);
        this.paste_btn = (Button) findViewById(R.id.paste_btn);
        this.copy_edt = (EditText) findViewById(R.id.copy_edt);
        this.paste_edt = (EditText) findViewById(R.id.paste_edt);
    }

    private void initListeners() {
        this.copy_btn.setOnClickListener(this);
        this.paste_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String copy = copy_edt.getText().toString().trim();
        switch (v.getId()) {
            case R.id.copy_btn:
                if (TextUtils.isEmpty(copy)) {
                    Toast.makeText(getApplicationContext(), "请输入内容！",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //创建一个新的文本clip对象
                mClipData = ClipData.newPlainText("Simple test", copy);
                //把clip对象放在剪贴板中
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "文本已经复制成功！",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.paste_btn:
                //GET贴板是否有内容
                mClipData = mClipboardManager.getPrimaryClip();
                //获取到内容
                ClipData.Item item = mClipData.getItemAt(0);
                String text = item.getText().toString();
                paste_edt.setText(text);
                Toast.makeText(getApplicationContext(), "粘贴成功！s",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
