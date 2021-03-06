package afkt.project.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import afkt.project.R;
import afkt.project.base.app.BaseToolbarActivity;
import butterknife.OnClick;
import dev.other.picture.PictureSelectorUtils;
import dev.utils.app.ActivityUtils;
import dev.utils.app.AppUtils;
import dev.utils.app.toast.ToastTintUtils;

/**
 * detail: 跳转 Activity 回传 CallBack
 * @author Ttt
 */
public class ActivityResultCallBackActivity extends BaseToolbarActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_activity_result_callback;
    }

    @OnClick({R.id.vid_aarc_select_btn})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.vid_aarc_select_btn:
                AppUtils.startActivityForResult(new ActivityUtils.ResultCallback() {
                    @Override
                    public boolean onStartActivityForResult(Activity activity) {
                        // 初始化图片配置
                        PictureSelectorUtils.PicConfig picConfig = new PictureSelectorUtils.PicConfig()
                                .setCompress(false).setMaxSelectNum(1).setCrop(false).setMimeType(PictureMimeType.ofImage())
                                .setCamera(true).setGif(false);
                        // 打开图片选择器
                        PictureSelectorUtils.openGallery(PictureSelector.create(activity), picConfig);
                        return true;
                    }

                    @Override
                    public void onActivityResult(boolean result, int resultCode, Intent data) {
                        if (result && data != null) {
                            LocalMedia localMedia = PictureSelectorUtils.getSingleMedia(data);
                            // 获取图片地址
                            String imgPath = PictureSelectorUtils.getLocalMediaPath(localMedia, true);
                            // 提示
                            ToastTintUtils.success("选择了图片: " + imgPath);
                        } else {
                            showToast(false, "非成功操作");
                        }
                    }
                });
                break;
        }
    }
}