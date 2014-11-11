package com.vanessapr.appbibliotecafain.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.vanessapr.appbibliotecafain.R;
import com.vanessapr.appbibliotecafain.models.Libro;
import com.vanessapr.appbibliotecafain.utils.MessageDialog;
import com.vanessapr.appbibliotecafain.utils.Systems;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Milagros on 27/10/2014.
 */
public class BookFragment extends Fragment {
    private static final String TAG = "BookFragment";
    public static final String EXTRA_POSITION_BOOK = BookFragment.class.getName() + ".EXTRA_POSITION_BOOK";
    private ViewGroup mContainer;
    private ProgressDialog mProgress;
    private File file;
    private Libro mCurrentPosition = null;

    public BookFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView..." + savedInstanceState);

        if(savedInstanceState != null)
            mCurrentPosition = savedInstanceState.getParcelable(EXTRA_POSITION_BOOK);

        View view = inflater.inflate(R.layout.fragment_book, container, false);
        mContainer = (ViewGroup) view.findViewById(R.id.container_book);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart..." + mCurrentPosition);
        if(mCurrentPosition != null) {
            displayBookSingle(mCurrentPosition);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState...");
        // Save the current book selection in case we need to recreate the fragment
        outState.putParcelable(EXTRA_POSITION_BOOK, mCurrentPosition);
    }

    public void displayBookSingle(Libro libro) {
        Log.i(TAG, "my activity: " + getActivity());
        mCurrentPosition = libro;
        mContainer.removeAllViews();
        mContainer.addView(libro.render(getActivity(),libro.getTipo()));
        DownloadFile();
    }

    private void DownloadFile() {
        // if exists button, download file
        if(getActivity().findViewById(R.id.btnDownloadCD) != null) {
            Button btnDownload = (Button) getActivity().findViewById(R.id.btnDownloadCD);
            btnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MessageDialog message = new MessageDialog(getActivity());

                    if(Systems.checkStatusConnectionNetwork(getActivity().getApplicationContext())) {
                        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            mProgress = new ProgressDialog(getActivity());
                            mProgress.setMessage("Descargando...");
                            mProgress.setIndeterminate(false);
                            mProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            mProgress.setCancelable(true);
                            mProgress.setProgress(0);
                            mProgress.setMax(100);

                            final TaskDownloadFile task = new TaskDownloadFile();
                            task.execute("http://ivica.org/bibliotecafain/" + mCurrentPosition.getUrlPdf());

                            mProgress.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialogInterface) {
                                    task.cancel(true);
                                    if(file.exists())
                                        file.delete();
                                    Toast.makeText(getActivity(), "Descarga cancelada", Toast.LENGTH_LONG).show();
                                }
                            });

                        } else {
                            Log.e(TAG, "SDCard: "+ Environment.getExternalStorageState());
                            message.display("Su tarjeta SDCard no está disponible");
                        }
                    } else {
                        message.display("No esta conectado a Internet");
                    }

                }
            });
        } else {
            Log.e(TAG, "no existe");
        }
    }

    /*
    * task background for download file from URL
    */
    private class TaskDownloadFile extends AsyncTask<String,Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... urls) {
            InputStream in = null;
            OutputStream out = null;
            HttpURLConnection httpConn = null;
            boolean isDownload = false;

            try {
                File sdcard = Environment.getExternalStorageDirectory();
                file = new File(sdcard.getAbsolutePath(), mCurrentPosition.getUrlPdf());
                out = new FileOutputStream(file);

                URL url = new URL(urls[0]);
                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();
                if(httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    int fileLength = httpConn.getContentLength();
                    in = httpConn.getInputStream();

                    byte data[] = new byte[1024];
                    long total = 0;
                    int count;
                    while((count = in.read(data)) != -1) {
                        total += count;
                        if(fileLength > 0)
                            publishProgress((int) (total*100/fileLength));

                        out.write(data, 0, count);

                        if(isCancelled())
                            break;
                        else
                            isDownload = true;
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "TaskDownloadFile: " + e.getLocalizedMessage());

            } finally {
                try {
                    if(in != null)
                        in.close();
                    if(out != null)
                        out.close();
                } catch (IOException e){
                    Log.e(TAG, "TaskDownloadFile: " + e.getLocalizedMessage());
                }

                if(httpConn != null)
                    httpConn.disconnect();
            }

            return isDownload;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgress.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgress.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute....");

            mProgress.dismiss();

            MessageDialog msg = new MessageDialog(getActivity());
            if(result) {
                msg.displayPDF("Su descarga ha sido completada", file);
            } else {
                // diplay message sdcard
                msg.display("No se pudo descargar. Intente nuevamente");
            }
        }

    }



}
