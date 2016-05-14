package com.szr.jkxsx.teacher.view.takepicUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TakePicUtils {
    private Context context;
    private static TakePicUtils instance = null;
    //当前为取原图还是取图完毕后裁剪
    public static final int TAKE_PIC_MODE_CROP = 1;
    public static final int TAKE_PIC_MODE_ONLY = 2;
    private int currentTakePicType = TAKE_PIC_MODE_ONLY;
    //拍照请求
    public static final int REQUEST_TAKE_PICTURE = 10;
    //图片选择请求
    public static final int REQUEST_SELECT_PIC = 11;
    //图片裁剪请求
    public static final int REQUEST_CROP_PICTURE = 12;
    private String selectedPic;
    private String tempFileName;

    private TakePicUtils() {

    }

    public static TakePicUtils getInstance() {
        if (instance == null) {
            instance = new TakePicUtils();
        }
        return instance;
    }

    public void init(Context context, int takePicMode) {
        this.context = context;
        this.currentTakePicType = takePicMode;
        if (!ImageUploadUtils.haveSdcard()) {
            Toast.makeText(context, "未找到sdcard，不能进行拍照和从相册选择图片", Toast.LENGTH_SHORT).show();
            return;
        }
        File temFiles = new File(ImageUploadUtils.getPICTURE_DIR());
        if (!temFiles.exists()) {
            if (!temFiles.mkdirs()) {
                Toast.makeText(context, "创建临时文件失败，没法子往下走了，问问客服寻求帮助吧", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void paizhao() {
        try {
            tempFileName = ImageUploadUtils.getPICTURE_DIR() + "camera" + System.currentTimeMillis() + ".jpg";
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(tempFileName)));
            ((Activity) context).startActivityForResult(intent,
                    REQUEST_TAKE_PICTURE);
        } catch (Exception e) {

        }
    }

    public void getMediaDatabase() {
        try {
            Intent i = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            ((Activity) context).startActivityForResult(i, REQUEST_SELECT_PIC);
        } catch (Exception e) {

        }
    }

    public static int crop_w = 1000;
    public static int crop_h = 1000;
    public static int aspectX = 1;
    public static int aspectY = 1;

    public void cropPicture(Uri srcUri) {
        try {
            tempFileName = ImageUploadUtils.getPICTURE_DIR() + "crop" + System.currentTimeMillis() + ".jpg";
            Intent intent = new Intent("com.android.camera.action.CROP");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && srcUri.toString().startsWith("file")) {
                //TODO 或许应该放到子线程进行
                String url = UriToPathConverter.getPath(context, srcUri);
                intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
            } else {
                intent.setDataAndType(srcUri, "image/*");
            }
            // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
            intent.putExtra("crop", "true");
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", aspectX);
            intent.putExtra("aspectY", aspectY);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", crop_w);
            intent.putExtra("outputY", crop_h);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", true);
            intent.putExtra("scale", true);// 去黑边
            intent.putExtra("scaleUpIfNeeded", true);// 去黑边
            intent.putExtra("output", Uri.fromFile(new File(tempFileName)));
            intent.putExtra("return-data", false);//设置为不返回数据
            ((Activity) context).startActivityForResult(intent, REQUEST_CROP_PICTURE);
        } catch (Exception e) {

        }
    }

    public PictureBean receivePics(int requestCode, int resultCode, Intent data) {
        PictureBean pb = new PictureBean();
        Uri uri = null;
        System.gc();
        selectedPic = ImageUploadUtils.getPICTURE_DIR() + System.currentTimeMillis() + ".jpg";
        if (resultCode == -1) {// result ok
            switch (requestCode) {
                case REQUEST_SELECT_PIC:
                    // 剪切图片 保存图片到本地
                    if (data == null) {
                        Toast.makeText(context, "打开文件错误，文件有问题！", Toast.LENGTH_SHORT).show();
                    }
                    uri = data.getData();
                    if (currentTakePicType == TAKE_PIC_MODE_CROP) {
                        cropPicture(uri);
                        return null;
                    } else if (currentTakePicType == TAKE_PIC_MODE_ONLY) {
                        //TODO 可能有问题目前产品逻辑不会走这里
                        String sourcePath1 = getFilePath(uri);
                        if (sourcePath1.equals("")) {
                            return null;
                        }
                        dealPic(selectedPic, sourcePath1, context);
                    }
                    break;
                case REQUEST_TAKE_PICTURE:
                    // 剪切图片之后保存
                    if (currentTakePicType == TAKE_PIC_MODE_CROP) {
                        cropPicture(Uri.fromFile(new File(tempFileName)));
                        return null;
                    } else {
                        // 处理照片
                        dealPic(selectedPic, tempFileName, context);
                    }
                    break;
                case REQUEST_CROP_PICTURE:
                    dealPic(selectedPic, tempFileName, context);
                    break;
            }
        }
        pb.setFileStr(selectedPic);
        return pb;
    }

    private String getFilePath(Uri uri) {
        Cursor cursor = null;
        String img_path = "";
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = (context).getContentResolver().query(uri, proj, null, null,
                    null);
            if (cursor == null || !cursor.moveToFirst()) {
                Toast.makeText(context, "找不到文件路径", Toast.LENGTH_SHORT).show();
                return img_path;
            }
            int actual_image_column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            img_path = cursor.getString(actual_image_column_index);
            if (img_path == null || img_path.equals("")) {
                Toast.makeText(context, "找不到文件路径", Toast.LENGTH_SHORT).show();
                return "";
            }
        } catch (Exception e) {
            Log.e("TakePicUtils", e.getMessage());
        } finally {
            //报错 android.database.StaleDataException: Attempted to access a cursor after it has been closed
//            if (cursor != null) {
//                cursor.close();
//            }
        }
        return img_path;
    }

    //图片压缩质量
    private static final int IMG_QUILTY = 80;

    public static void dealPic(String destationPath, String srcPath, Context context) {
        // 缩放
        Bitmap bitmapSource = dealBitmap2Small(srcPath);
        if (bitmapSource != null) {
            // 旋转图片
            Bitmap afterDealBitmap = zoomDrawable(srcPath, bitmapSource);
            saveSmallPic2SdCard(afterDealBitmap, destationPath, IMG_QUILTY);
            if (bitmapSource != null) {
                bitmapSource.recycle();
            }
            // 释放图片资源
            bitmapSource = null;
            afterDealBitmap = null;
        } else {
            Toast.makeText(context, "该照片在原始路径已经不存在，请核实！", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 保存处理过的小图到指定路径
     */
    static void saveSmallPic2SdCard(Bitmap bitmap, String path, int quility) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e1) {
            Log.e("TakePicUtils", e1.getMessage());
        }
        try {
            Log.i("TakePicUtils", "bitmap大小：" + bitmap.getByteCount() / 1024 + "kb");
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, quility, out)) {
                out.flush();
                out.close();
            }
            Log.i("TakePicUtils", "文件大小：" + file.length() / 1024 + "kb");
        } catch (FileNotFoundException e) {
            Log.e("TakePicUtils", e.getMessage());
        } catch (IOException e) {
            Log.e("TakePicUtils", e.getMessage());
        }
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    /**
     * 旋转并返回新的pic,回收 bitmapsource
     */
    public static Bitmap zoomDrawable(String path, Bitmap srcBitmap) {
        int angle = readPictureDegree(path);
        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap newbmp = Bitmap.createBitmap(srcBitmap, 0, 0, width, height,
                matrix, true);
        return newbmp;
    }

    public static Bitmap dealBitmap2Small(String tempfilenameString) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(tempfilenameString, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateInSampleSize(options, 1200, 1200);
        // 重新读入bitmap
        Bitmap bitmap1 = BitmapFactory.decodeFile(tempfilenameString, options);
        return bitmap1;
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        Log.i("TakePicUtils", "图片压缩比" + inSampleSize);
        return inSampleSize;
    }

    /**
     * 得到图片角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            Log.e("TakePicUtils", e.getMessage());
        }
        return degree;
    }
}
