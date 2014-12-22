package to.msn.wings.camerabasic;

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
      bt.setOnClickListener(new SampleClickListener());
    }
    
    class SampleClickListener implements OnClickListener{
    	public void onClick(View v){
    		if(v == bt){
        	Intent i = new Intent(getApplication(), to.msn.wings.camerabasic.CameraActivity.class);
        	//アクティビティの開始
        	startActivity(i);
    		}
    	}
    }
    

   
}
