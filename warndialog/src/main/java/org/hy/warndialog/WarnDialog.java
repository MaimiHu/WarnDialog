package org.hy.warndialog;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.hy.warndialog.util.WarnUtil;

import java.util.Arrays;

/**
 * 提示Dialog
 *
 * author: huangyue on 2016/11/4 10:46.
 */
@SuppressWarnings("unused")
public class WarnDialog extends AppCompatDialog {
    public Builder builder;

    private WarnDialog(Context context) {
        this(context, 0);
    }

    protected WarnDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        this(context, 0);
        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
    }

    private WarnDialog(Context context, int theme) {
        super(context, theme > 0 ? theme : R.style.WarnDialog);
    }

    public interface ClickObserver {
        void click(WarnDialog dialog, View view);
    }

    public static class Builder {
        private static final int DEFAULT_RADIUS = 10;
        private Context context;
        private int theme = R.style.WarnDialog;
        private float[] radius = new float[4];
        private float widthRadio = 0.75f;//dialog宽度占屏幕宽度的多少
        private boolean cancelable = true;
        private boolean canceledOnTouchOutside = true;

        private boolean hasIcon = true;//是否有Icon
        private boolean hasMessage = true;//是否有内容
        private boolean hasButton = true;//是否有按钮
        private int messageGravity = Gravity.CENTER;//内容显示位置

        private int iconRes = android.R.drawable.ic_dialog_alert;
        private String title = "";
        private String message = "";
        private String yes = "";
        private String no = "";

        /**
         * 回调接口
         */
        private ClickObserver yesObserver;
        private ClickObserver noObserver;

        /**
         * 颜色
         */
        private int titleBackgroundColor;
        private int titleTextColor;
        private int messageBackgroundColor;
        private int messageTextColor;
        private int yesButtonBackgroundColor;
        private int yesButtonPressBackgroundColor;
        private int yesButtonColor;
        private int yesButtonPressColor;
        private int noButtonBackgroundColor;
        private int noButtonPressBackgroundColor;
        private int noButtonColor;
        private int noButtonPressColor;


        public Builder(Context context) {
            this.context = context;
            titleBackgroundColor = ContextCompat.getColor(context, R.color.warn_dialog_yes_button);
            titleTextColor = ContextCompat.getColor(context, R.color.white);
            messageBackgroundColor = ContextCompat.getColor(context, R.color.white);
            messageTextColor = ContextCompat.getColor(context, R.color.black);
            yesButtonBackgroundColor = ContextCompat.getColor(context, R.color.white);
            yesButtonPressBackgroundColor = ContextCompat.getColor(context, R.color.warn_dialog_default_press_background_color);
            yesButtonColor = ContextCompat.getColor(context, R.color.warn_dialog_yes_button);
            yesButtonPressColor = ContextCompat.getColor(context, R.color.warn_dialog_yes_button);
            noButtonBackgroundColor = ContextCompat.getColor(context, R.color.white);
            noButtonPressBackgroundColor = ContextCompat.getColor(context, R.color.warn_dialog_default_press_background_color);
            noButtonColor = ContextCompat.getColor(context, R.color.warn_dialog_no_button);
            noButtonPressColor = ContextCompat.getColor(context, R.color.warn_dialog_no_button);
            setRadius(DEFAULT_RADIUS);
        }

        public Builder setTheme(@StyleRes int theme) {
            this.theme = theme;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder setRadius(float radius) {
            Arrays.fill(this.radius, WarnUtil.dp2px(context, radius));
            return this;
        }

        public Builder setRadius(float leftTopRadius, float rightTopRadius, float leftBottomRadius, float rightBottomRadius) {
            radius[0] = WarnUtil.dp2px(context, leftTopRadius);
            radius[1] = WarnUtil.dp2px(context, rightTopRadius);
            radius[2] = WarnUtil.dp2px(context, leftBottomRadius);
            radius[3] = WarnUtil.dp2px(context, rightBottomRadius);
            return this;
        }

        public Builder setWidthRadio(float widthRadio) {
            this.widthRadio = widthRadio;
            return this;
        }

        public Builder setNoIcon(boolean noIcon) {
            this.hasIcon = !noIcon;
            return this;
        }

        public Builder setNoMessage(boolean noMessage) {
            this.hasMessage = !noMessage;
            return this;
        }

        public Builder setIcon(@DrawableRes int iconRes) {
            this.iconRes = iconRes;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessageGravity(int gravity) {
            this.messageGravity = gravity;
            return this;
        }

        public Builder setYes(String yes) {
            this.yes = yes;
            return this;
        }

        public Builder setYes(ClickObserver yesObserver) {
            this.yesObserver = yesObserver;
            return this;
        }

        public Builder setYes(String yes, ClickObserver yesObserver) {
            this.yes = yes;
            this.yesObserver = yesObserver;
            return this;
        }

        public Builder setNo(String no) {
            this.no = no;
            return this;
        }

        public Builder setNo(ClickObserver noObserver) {
            this.noObserver = noObserver;
            return this;
        }

        public Builder setNo(String no, ClickObserver noObserver) {
            this.no = no;
            this.noObserver = noObserver;
            return this;
        }

        public Builder setTitleTextColor(@ColorInt int titleTextColor) {
            this.titleTextColor = titleTextColor;
            return this;
        }

        public Builder setTitleBackgroundColor(@ColorInt int titleBackgroundColor) {
            this.titleBackgroundColor = titleBackgroundColor;
            return this;
        }

        public Builder setMessageBackgroundColor(@ColorInt int messageBackgroundColor) {
            this.messageBackgroundColor = messageBackgroundColor;
            return this;
        }

        public Builder setMessageTextColor(@ColorInt int messageTextColor) {
            this.messageTextColor = messageTextColor;
            return this;
        }

        public Builder setYesButtonBackgroundColor(@ColorInt int backgroundColor, @ColorInt int pressBackgroundColor) {
            this.yesButtonBackgroundColor = backgroundColor;
            this.yesButtonPressBackgroundColor = pressBackgroundColor;
            return this;
        }

        public Builder setYesButtonColor(@ColorInt int color, @ColorInt int pressColor) {
            this.yesButtonColor = color;
            this.yesButtonPressColor = pressColor;
            return this;
        }

        public Builder setNoButtonBackgroundColor(@ColorInt int backgroundColor, @ColorInt int pressBackgroundColor) {
            this.noButtonBackgroundColor = backgroundColor;
            this.noButtonPressBackgroundColor = pressBackgroundColor;
            return this;
        }

        public Builder setNoButtonColor(@ColorInt int color, @ColorInt int pressColor) {
            this.noButtonColor = color;
            this.noButtonPressColor = pressColor;
            return this;
        }

        public WarnDialog create() {
            final WarnDialog dialog = new WarnDialog(context, theme);
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            dialog.setCancelable(cancelable);
            Window window = dialog.getWindow();
            window.setBackgroundDrawableResource(R.color.transparent);
            int pressed = android.R.attr.state_pressed;
            Resources resources = context.getResources();
            boolean hasYesBtn = !TextUtils.isEmpty(yes);
            boolean hasNoBtn = !TextUtils.isEmpty(no);
            boolean hasTitle = !TextUtils.isEmpty(title);
            hasButton = hasYesBtn || hasNoBtn;
            boolean hasOnlyYes = hasYesBtn && !hasNoBtn;
            boolean hasOnlyNo = !hasYesBtn && hasNoBtn;
            boolean hasOne = hasOnlyYes || hasOnlyNo;

            View view = LayoutInflater.from(context).inflate(R.layout.dialog_warn, null);
            View titleLayout = view.findViewById(R.id.warnDialogTitle_ll);
            ImageView iconImg = (ImageView) view.findViewById(R.id.warnDialogIcon_img);
            TextView titleTv = (TextView) view.findViewById(R.id.warnDialogTitle_tv);

            TextView messageTv = (TextView) view.findViewById(R.id.warnDialogMessage_tv);

            View horizontalLine = view.findViewById(R.id.horizontalLine);
            View verticalLine = view.findViewById(R.id.verticalLine);
            LinearLayout messageLayout = (LinearLayout) view.findViewById(R.id.warnDialogMessage_ll);
            View buttonLayout = view.findViewById(R.id.warnDialogButton_ll);
            Button yesBtn = (Button) view.findViewById(R.id.warnDialogYes_btn);
            Button noBtn = (Button) view.findViewById(R.id.warnDialogNo_btn);

            GradientDrawable titleDrawable = new GradientDrawable();
            titleDrawable.setColor(titleBackgroundColor);
            GradientDrawable messageDrawable = new GradientDrawable();
            messageDrawable.setColor(messageBackgroundColor);

            StateListDrawable yesButtonDrawable = new StateListDrawable();
            GradientDrawable yesNormalDrawable = new GradientDrawable();
            yesNormalDrawable.setColor(yesButtonBackgroundColor);
            GradientDrawable yesPressedDrawable = new GradientDrawable();
            yesPressedDrawable.setColor(yesButtonPressBackgroundColor);
            yesButtonDrawable.addState(new int[]{pressed}, yesPressedDrawable);
            yesButtonDrawable.addState(new int[]{-pressed}, yesNormalDrawable);


            StateListDrawable noButtonDrawable = new StateListDrawable();

            GradientDrawable noNormalDrawable = new GradientDrawable();
            noNormalDrawable.setColor(noButtonBackgroundColor);

            GradientDrawable noPressedDrawable = new GradientDrawable();
            noPressedDrawable.setColor(noButtonPressBackgroundColor);

            noButtonDrawable.addState(new int[]{pressed}, noPressedDrawable);
            noButtonDrawable.addState(new int[]{-pressed}, noNormalDrawable);


            float[] titleRadius = new float[8];
            float[] messageRadius = new float[8];
            float[] yesButtonRadius = new float[8];
            float[] noButtonRadius = new float[8];

            if (hasIcon) {
                iconImg.setVisibility(View.VISIBLE);
                iconImg.setImageResource(iconRes);
            }
            if (hasTitle) {
                titleLayout.setVisibility(View.VISIBLE);
                titleRadius[0] = titleRadius[1] = radius[0];
                titleRadius[2] = titleRadius[3] = radius[1];
            } else {
                if (hasMessage) {
                    messageRadius[0] = messageRadius[1] = radius[0];
                    messageRadius[2] = messageRadius[3] = radius[1];
                } else {
                    if (hasButton) {
                        if (hasOnlyYes) {
                            yesButtonRadius[0] = yesButtonRadius[1] = radius[0];
                            yesButtonRadius[2] = yesButtonRadius[3] = radius[1];
                        } else if (hasOnlyNo) {
                            noButtonRadius[0] = noButtonRadius[1] = radius[0];
                            noButtonRadius[2] = noButtonRadius[3] = radius[1];
                        } else {
                            noButtonRadius[0] = noButtonRadius[1] = radius[0];
                            yesButtonRadius[2] = yesButtonRadius[3] = radius[1];
                        }
                    }
                }
            }

            if (hasButton) {
                horizontalLine.setVisibility(View.VISIBLE);
                buttonLayout.setVisibility(View.VISIBLE);
                if (!hasOne) {
                    verticalLine.setVisibility(View.VISIBLE);
                }
                if (hasYesBtn) {
                    yesBtn.setVisibility(View.VISIBLE);
                }
                if (hasNoBtn) {
                    noBtn.setVisibility(View.VISIBLE);
                }
                if (hasOnlyYes) {
                    yesButtonRadius[4] = yesButtonRadius[5] = radius[2];
                    yesButtonRadius[6] = yesButtonRadius[7] = radius[3];
                } else if (hasOnlyNo) {
                    noButtonRadius[4] = noButtonRadius[5] = radius[2];
                    noButtonRadius[6] = noButtonRadius[7] = radius[3];
                } else {
                    yesButtonRadius[4] = yesButtonRadius[5] = radius[3];
                    noButtonRadius[6] = noButtonRadius[7] = radius[2];
                }
            } else {
                if (hasMessage) {
                    messageRadius[4] = messageRadius[5] = radius[2];
                    messageRadius[6] = messageRadius[7] = radius[3];
                } else {
                    if (hasTitle) {
                        titleRadius[4] = titleRadius[5] = radius[2];
                        titleRadius[6] = titleRadius[7] = radius[3];
                    }
                }
            }
            if (hasMessage) {
                messageLayout.setVisibility(View.VISIBLE);
                messageLayout.setGravity(messageGravity);
            }
            titleDrawable.setCornerRadii(titleRadius);
            messageDrawable.setCornerRadii(messageRadius);
            yesNormalDrawable.setCornerRadii(yesButtonRadius);
            yesPressedDrawable.setCornerRadii(yesButtonRadius);
            noNormalDrawable.setCornerRadii(noButtonRadius);
            noPressedDrawable.setCornerRadii(noButtonRadius);

            titleTv.setTextColor(titleTextColor);
            messageTv.setTextColor(messageTextColor);
            int[][] state = new int[2][];
            state[0] = new int[]{pressed};
            state[1] = new int[]{};
            int[] yesColor = new int[]{yesButtonPressColor, yesButtonColor};
            ColorStateList yesButtonColorStateList = new ColorStateList(state, yesColor);
            yesBtn.setTextColor(yesButtonColorStateList);
            int[] noColor = new int[]{noButtonPressColor, noButtonColor};
            ColorStateList noButtonColorStateList = new ColorStateList(state, noColor);
            noBtn.setTextColor(noButtonColorStateList);

            titleTv.setText(title);
            titleLayout.setBackgroundDrawable(titleDrawable);
            messageTv.setText(message);
            messageLayout.setBackgroundDrawable(messageDrawable);
            yesBtn.setText(yes);
            yesBtn.setBackgroundDrawable(yesButtonDrawable);
            yesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != yesObserver)
                        yesObserver.click(dialog, v);
                }
            });
            noBtn.setText(no);
            noBtn.setBackgroundDrawable(noButtonDrawable);
            noBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != noObserver)
                        noObserver.click(dialog, v);
                    else {
                        dialog.dismiss();
                    }
                }
            });

            window.setContentView(view);
            // 最后调用
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int) (WarnUtil.getScreenWidth(context) * widthRadio);
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
            dialog.builder = this;
            return dialog;
        }

        public WarnDialog show() {
            final WarnDialog dialog = create();
            dialog.show();
            return dialog;
        }

    }
}
