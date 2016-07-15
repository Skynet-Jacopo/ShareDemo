package com.example.liuqun.sharedemo;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int WEBCHAT = 1, WEBCHAT_FRIEND = 2, QQ = 3, QZONE = 4, SINA = 5;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_share).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        showShare();
        showShareDialog();
    }

    private void showShareDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View           view     = inflater.inflate(R.layout.share_dialog, null);

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("分享到:");
//        builder.setView(view);
//        mDialog = builder.create();
//        builder.show();
//        view.findViewById(R.id.wechat).setOnClickListener(dialogItemClick);
//        view.findViewById(R.id.wechat_friend).setOnClickListener(dialogItemClick);
//        view.findViewById(R.id.qq).setOnClickListener(dialogItemClick);
//        view.findViewById(R.id.qzone).setOnClickListener(dialogItemClick);
//        view.findViewById(R.id.sinaweibo).setOnClickListener(dialogItemClick);

        mDialog =  new AlertDialog.Builder(this).create();
        mDialog.setTitle("分享到:");
        mDialog.setView(view,0,40,0,40);
        mDialog.show();
        view.findViewById(R.id.wechat).setOnClickListener(dialogItemClick);
        view.findViewById(R.id.wechat_friend).setOnClickListener(dialogItemClick);
        view.findViewById(R.id.qq).setOnClickListener(dialogItemClick);
        view.findViewById(R.id.qzone).setOnClickListener(dialogItemClick);
        view.findViewById(R.id.sinaweibo).setOnClickListener(dialogItemClick);
    }

    private View.OnClickListener dialogItemClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.wechat:
                    showShare(WEBCHAT);
                    mDialog.dismiss();
                    break;
                case R.id.wechat_friend:
                    showShare(WEBCHAT_FRIEND);
                    mDialog.dismiss();
                    break;
                case R.id.qq:
                    showShare(QQ);
                    mDialog.dismiss();
                    break;
                case R.id.qzone:
                    showShare(QZONE);
                    mDialog.dismiss();
                    break;
                case R.id.sinaweibo:
                    showShare(SINA);
                    mDialog.dismiss();
                    break;
            }
        }
    };

    private void showShare(int platforms) {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        oks.setDialogMode();
// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        //oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");


        switch (platforms) {
            case WEBCHAT:
                oks.setPlatform(Wechat.NAME);
                break;
            case WEBCHAT_FRIEND:
                oks.setPlatform(Wechat.NAME);
                break;
            case QZONE:
                oks.setPlatform(WechatMoments.NAME);
                break;
            case QQ:
                oks.setPlatform(cn.sharesdk.tencent.qq.QQ.NAME);
                break;
            case SINA:
                oks.setPlatform(SinaWeibo.NAME);
                break;
        }
        //启动分享GUI
        oks.show(this);
    }

}
