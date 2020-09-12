package com.hoodad.test.utils;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFile extends AsyncTask<String, String, String> {

    /**
     * Before starting background thread
     * Show Progress Bar Dialog
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //context.showDialog(progress_bar_type);

    }


    protected String doInBackground(String... f_url) {
        int count;
        try {
            for (int i = 0; i < f_url.length; i++) {
                Log.e("0url",""+f_url[0]);
                Log.e("1url",""+f_url[1]);
                // Log.e("1url",""+f_url[1]);
                URL url = new URL(f_url[i]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file
                //   OutputStream output = new FileOutputStream("/sdcard/"+f_url[i]);
                OutputStream output = new FileOutputStream(
                        "/sdcard/" +i + "buymix.mp3");
                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }


                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
            }
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }

    /**
     * Updating progress bar
     */

    /**
     * After completing background task
     * Dismiss the progress dialog
     **/
    @Override
    protected void onProgressUpdate(String... values) {
        // Update progress
//        mBuilder.setProgress(100, Integer.parseInt(values[0]), false);
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
//        mBuilder.setContentText("Download complete");
        // Removes the progress bar
        String imagePath = Environment.getExternalStorageDirectory()
                .toString() + "/downloaded.mp3";
//        mBuilder.setProgress(0, 0, false);
//        mNotifyManager.notify(id, mBuilder.build());
    }

}