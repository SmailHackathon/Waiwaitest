package to.msn.wings.camerabasic;

import to.msn.wings.camerabasic.CameraActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button bt;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
/*        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
      setContentView(R.layout.activity_main);
      
      
    bt = (Button)findViewById(R.id.button);
    bt.setOnClickListener(new OnClickListener(){
						public void onClick(View v) {
									startCameraActivity();
						}
    					});
    }
    
    private void startCameraActivity(){
    	System.out.println("Fuuuuuuaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa!1");
		Intent intent = new Intent(this,CameraActivity.class);
		startActivityForResult(intent,0);
	}
    

   
}
