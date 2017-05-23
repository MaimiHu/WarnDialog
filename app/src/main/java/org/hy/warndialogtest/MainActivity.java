package org.hy.warndialogtest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.hy.warndialog.WarnDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.twoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarnDialog.Builder(MainActivity.this)
                        .setMessage("这是一个双按钮的弹框！")
                        .setNo("取消")
                        .setYes("哦，好吧")
                        .setYesButtonColor(Color.BLUE, Color.BLACK)
                        .setYesButtonBackgroundColor(Color.GRAY, Color.CYAN)
                        .setNoButtonColor(Color.GREEN, Color.GRAY)
                        .setTitle("提示")
                        .show();
            }
        });
        findViewById(R.id.oneButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarnDialog.Builder(MainActivity.this)
                        .setMessage("这是一个单按钮的弹框！")
                        .setYes("哦？")
                        .setYesButtonColor(Color.BLUE, Color.BLACK)
                        .setYesButtonBackgroundColor(Color.GRAY, Color.CYAN)
                        .setTitle("提示")
                        .show();
            }
        });
        findViewById(R.id.noTitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarnDialog.Builder(MainActivity.this)
                        .setMessage("这是一个无标题的弹框！")
                        .setYes("哦？")
                        .setYesButtonColor(Color.BLUE, Color.BLACK)
                        .setYesButtonBackgroundColor(Color.GRAY, Color.CYAN)
                        .show();
            }
        });
        findViewById(R.id.noMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarnDialog.Builder(MainActivity.this)
                        .setNoMessage(true)
                        .setYes("不知道", new WarnDialog.ClickObserver() {
                            @Override
                            public void click(WarnDialog dialog, View view) {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, "你咋也不知道呢？", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setYesButtonColor(Color.BLUE, Color.BLACK)
                        .setYesButtonBackgroundColor(Color.GRAY, Color.CYAN)
                        .setTitle("这是什么？没内容？")
                        .show();
            }
        });
        findViewById(R.id.radius).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarnDialog.Builder(MainActivity.this)
                        .setMessage("这是一个不同圆角的弹框！")
                        .setNo("取消")
                        .setYes("哦，好吧")
                        .setRadius(10, 5, 0, 30)
                        .setYesButtonColor(Color.BLUE, Color.BLACK)
                        .setYesButtonBackgroundColor(Color.GRAY, Color.CYAN)
                        .setNoButtonColor(Color.GREEN, Color.GRAY)
                        .setTitle("提示")
                        .show();
            }
        });
        findViewById(R.id.width).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarnDialog.Builder(MainActivity.this)
                        .setMessage("这是一个不同大小的弹框！")
                        .setWidthRadio(0.9f)
                        .setNo("取消")
                        .setYes("哦，好吧")
                        .setYesButtonColor(Color.BLUE, Color.BLACK)
                        .setYesButtonBackgroundColor(Color.GRAY, Color.CYAN)
                        .setNoButtonColor(Color.GREEN, Color.GRAY)
                        .setTitle("提示")
                        .show();
            }
        });
    }
}
