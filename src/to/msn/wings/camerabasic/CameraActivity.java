package to.msn.wings.camerabasic;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class CameraActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	/*        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
	      setContentView(new CameraSurface(this));
	    }

	    

}
