package to.msn.wings.camerabasic;

import java.io.FileNotFoundException;
import java.io.IOException;

import to.msn.wings.camerabasic.MainActivity.SampleClickListener;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class GalleryActivity extends Activity {
	Button pickButton;
	private Bitmap bitmap;
	private ImageView imageView;
	private Canvas canvas;
	private int viewWidth, viewHeight; 
	int RESULT_PICK_FILENAME = 0;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		
		// ウィンドウマネージャのインスタンス取得
		WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
		// ディスプレイのインスタンスを生成
		Display display = manager.getDefaultDisplay();
		Point point = new Point();
		// 画像サイズの取得
		display.getSize(point);
		viewWidth = point.x;
		viewHeight = point.y; 
		
		imageView = (ImageView) findViewById(R.id.imageView1);
		
		pickButton = (Button)findViewById(R.id.pickButton);
		pickButton.setOnClickListener(new SampleClickListener());
	}
	
	class SampleClickListener implements OnClickListener{
    	public void onClick(View v){
    		if(v == pickButton){
        	Intent i = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);
        	//アクティビティの開始
        	startActivityForResult(i, RESULT_PICK_FILENAME);
    		}
    	}
    }
	
	@Override
	  protected void onActivityResult(
	    int requestCode,
	    int resultCode,
	    Intent data) {
	  
	    super.onActivityResult(requestCode, resultCode, data);
	  
		// 画像を選択した時のみ実行
	    if (data != null) {
	    Uri uri = data.getData();
	    try {
	    bitmap = loadImage(uri, viewWidth, viewHeight);
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
	    canvas = new Canvas(bitmap);
	    imageView.setImageBitmap(bitmap);
	    } 
	  }
	
	private Bitmap loadImage(Uri uri, int viewWidth, int viewHeight){
		// Uriから画像を読み込みBitmapを作成
		Bitmap originalBitmap = null;
		try {
		originalBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		// MediaStoreから回転情報を取得
		final int orientation;
		Cursor cursor = MediaStore.Images.Media.query(getContentResolver(), uri, new String[] {
		MediaStore.Images.ImageColumns.ORIENTATION
		});
		if (cursor != null) {
		cursor.moveToFirst();
		orientation = cursor.getInt(0);
		} else {
		orientation = 0;
		}
		int originalWidth = originalBitmap.getWidth();
		int originalHeight = originalBitmap.getHeight();
		// 縮小割合を計算
		final float scale;
		if (orientation == 90 || orientation == 270) {
		// 縦向きの画像は半分のサイズに変更
		scale = Math.min(((float)viewWidth / originalHeight)/2, ((float)viewHeight / originalWidth)/2);
		} else {
		// 横向きの画像
		scale = Math.min((float)viewWidth / originalWidth, (float)viewHeight / originalHeight);
		}
		// 変換行列の作成
		final Matrix matrix = new Matrix();
		if (orientation != 0) {
		//画像を回転させる
		matrix.postRotate(orientation);
		}
		if (scale < 1.0f) {
		// Bitmapを拡大縮小する
		matrix.postScale(scale, scale);
		}
		// 行列によって変換されたBitmapを返す
		return Bitmap.createBitmap(originalBitmap, 0, 0, originalWidth, originalHeight, matrix,
		true);
		} 
}
