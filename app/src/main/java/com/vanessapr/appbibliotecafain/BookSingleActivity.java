package com.vanessapr.appbibliotecafain;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vanessapr.appbibliotecafain.models.Libro;
import com.vanessapr.appbibliotecafain.utils.HttpConnection;
import com.vanessapr.appbibliotecafain.utils.MessageDialog;
import com.vanessapr.appbibliotecafain.utils.Systems;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class BookSingleActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "BookSingleActivity";
    public static final String EXTRA_BOOK = BookSingleActivity.class.getName()+".EXTRA_BOOK";
    private ViewGroup mContainer;
    private ProgressDialog mProgress;
    private TextView tvAutor, tvEditorial, tvTemas;
    private File file = null;
    private Libro libro;
    private MessageDialog message;
    private TaskDownloadFile task = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_single);
        Log.d(TAG, "onCreate...");

        libro = getIntent().getParcelableExtra(EXTRA_BOOK);
        message = new MessageDialog(this);
        mContainer = (ViewGroup) findViewById(R.id.container_single_book);

        displayBookSingle(libro);
    }

    public void displayBookSingle(Libro libro) {
        mContainer.removeAllViews();
        mContainer.addView(libro.render(this,libro.getTipo()));

        if(findViewById(R.id.tv_book_autor) != null) {
            tvAutor = (TextView) findViewById(R.id.tv_book_autor);
            tvAutor.setOnClickListener(this);
        }

        if(findViewById(R.id.tv_book_editorial) != null) {
            tvEditorial = (TextView) findViewById(R.id.tv_book_editorial);
            tvEditorial.setOnClickListener(this);
        }

        if(findViewById(R.id.tv_book_temas) != null) {
            tvTemas = (TextView) findViewById(R.id.tv_book_temas);
            tvTemas.setOnClickListener(this);
        }

        if(findViewById(R.id.btnDownloadCD) != null) {
            Button btnDownload = (Button) findViewById(R.id.btnDownloadCD);
            btnDownload.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {
        StringBuilder where = new StringBuilder();
        Intent mIntent = new Intent(BookSingleActivity.this, BooksActivity.class);

        switch (view.getId()) {
            case R.id.tv_book_autor:
                String autor = tvAutor.getText().toString();
                where.append("autor_libro = '").append(autor).append("'");

                mIntent.putExtra(BooksActivity.EXTRA_WHERE, where.toString());
                mIntent.putExtra(BooksActivity.EXTRA_ORDERBY, "autor_libro, titulo");
                startActivity(mIntent);
                break;

            case R.id.tv_book_editorial:
                String editorial = tvEditorial.getText().toString();
                where.append("editorial = '").append(editorial).append("'");

                mIntent.putExtra(BooksActivity.EXTRA_WHERE, where.toString());
                mIntent.putExtra(BooksActivity.EXTRA_ORDERBY, "autor_libro, titulo");
                startActivity(mIntent);
                break;

            case R.id.tv_book_temas:
                String temas = tvTemas.getText().toString();
                where.append("descriptores = '").append(temas).append("'");
                mIntent.putExtra(BooksActivity.EXTRA_WHERE, where.toString());
                mIntent.putExtra(BooksActivity.EXTRA_ORDERBY, "autor_libro, titulo");
                startActivity(mIntent);
                break;

            case R.id.btnDownloadCD:
                task = new TaskDownloadFile();
                task.execute("http://ivica.org/bibliotecafain/" + libro.getUrlPdf());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy...");
        if(mProgress != null && mProgress.isShowing())
            mProgress.cancel();

        if(task != null)
            task.cancel(true);

    }

    /**
     * task background for download file from URL
    */
    private class TaskDownloadFile extends AsyncTask<String,Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if(Systems.checkStatusConnectionNetwork(getApplicationContext())) {
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    mProgress = new ProgressDialog(BookSingleActivity.this);
                    mProgress.setMessage("Descargando...");
                    mProgress.setIndeterminate(false);
                    mProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    mProgress.setCancelable(true);
                    mProgress.setProgress(0);
                    mProgress.setMax(100);
                    mProgress.show();

                    mProgress.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            task.cancel(true);
                            if(file.exists())
                                file.delete();
                            Toast.makeText(BookSingleActivity.this, "Descarga cancelada", Toast.LENGTH_LONG).show();
                        }
                    });

                } else {
                    Log.e(TAG, "SDCard: "+ Environment.getExternalStorageState());
                    task.cancel(true);
                    message.display("Su tarjeta SDCard no está disponible");
                }

            } else {
                task.cancel(true);
                message.display("No esta conectado a Internet");
            }

        }

        @Override
        protected String doInBackground(String... urls) {
            HttpConnection conn = new HttpConnection();
            OutputStream out = null;
            InputStream in = null;
            String message;

            try {
                in = conn.open(urls[0]);
                File sdcard = Environment.getExternalStorageDirectory();
                file = new File(sdcard.getAbsolutePath(), libro.getUrlPdf());
                out = new FileOutputStream(file);

                if(in != null) {
                    Log.d(TAG, "in is not null");
                    int fileLength = conn.getFileLength();
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
                    }
                    message = "ok";
                } else {
                    message = "Hubo un problema al descargar el archivo. Intente nuevamente";
                }

            } catch (Exception e) {
                Log.e(TAG, "TaskDownloadFile 1: " + e.getLocalizedMessage());
                message = e.getMessage();

            } finally {
                try {
                    if(in != null)
                        in.close();
                    if(out != null)
                        out.close();
                } catch (IOException e){
                    Log.e(TAG, "TaskDownloadFile 2: " + e.getLocalizedMessage());
                }

                if(conn.getHttpConn() != null) {
                    conn.getHttpConn().disconnect();
                }
            }

            return message;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgress.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute....");

            mProgress.dismiss();

            if(result.equals("ok")) {
                message.displayPDF("Su descarga ha sido completada", file);
            } else {
                // diplay message sdcard
                message.display(result);
            }
        }

    }

}
