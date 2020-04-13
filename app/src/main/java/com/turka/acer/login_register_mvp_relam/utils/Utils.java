package com.turka.acer.login_register_mvp_relam.utils;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Utils {

    public static Uri saveFile(Intent data, Activity activity){
        Uri selectedImage = data.getData();
        File file;
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        System.out.println("File Path Column "+ Arrays.toString(filePathColumn));

        assert selectedImage != null;
        Cursor cursor = activity.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();
        else {
            System.out.println("Cursor is null");
            return null;
        }

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        System.out.println("Column Index "+columnIndex);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize = 3;
        Bitmap preview_bitmap=BitmapFactory.decodeFile(picturePath,options);

        OutputStream out;
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/Solve");

        if (!direct.exists()) {
            direct.mkdirs();
        }
        file = new File(Environment.getExternalStorageDirectory()+ "/Solve"+ System.currentTimeMillis() +"jpg");
        try {
            file.createNewFile();
            out = new FileOutputStream(file);
            //Bitmap bmp = intent.getExtras().get("data");
            //ByteArrayOutputStream stream = new ByteArrayOutputStream();

            int bytes = preview_bitmap.getByteCount();
            //or we can calculate bytes this way. Use a different value than 4 if you don't use 32bit images.
            //int bytes = b.getWidth()*b.getHeight()*4;

            ByteBuffer buffer = ByteBuffer.allocate(bytes); //Create a new buffer
            preview_bitmap.copyPixelsToBuffer(buffer); //Move the byte data to the buffer

            byte[] array = buffer.array();


            out.write(array);
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Uri.parse(file.getAbsolutePath());
    }
}
