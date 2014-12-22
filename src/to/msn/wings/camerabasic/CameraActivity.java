package to.msn.wings.camerabasic;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CameraActivity extends Activity{
	SurfaceView sur;
	SurfaceHolder surHold;
	Button button;
	private Camera c;
	List<Size> cSize;
	Size niceSize;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_camera_surface);
	      StartCamera();
	      button = (Button) findViewById(R.id.button1);
	    }
	
	private void StartCamera(){
		sur = (SurfaceView) findViewById(R.id.surfaceView1);
	    surHold = sur.getHolder();
	    surHold.addCallback( new SurfaceCallback() );
	    System.out.println("Fuuuuuuaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa!2");
	}
	
	private class SurfaceCallback implements SurfaceHolder.Callback {
		
		public void surfaceCreated(SurfaceHolder holder) {
			System.out.println("Fuuuuuuaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa!!");
			c = Camera.open(0);
			if(c != null){
				try{
					c.setPreviewDisplay(surHold);
				}catch(Exception e){
					e.printStackTrace();
				}
				cSize = c.getParameters().getSupportedPreviewSizes();
			}
			
		}
		
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			System.out.println("helloworld");
			if(c != null){
				System.out.println("helloworld2");
				Parameters params = c.getParameters();
				if(cSize != null){
					System.out.println("helloworld3");
					
					
					
					final double ASPECT_TOLERANCE = 0.1;
			        double targetRatio = (double) width / height;
			            

			        Size optimalSize = null;
			        double minDiff = Double.MAX_VALUE;

			        int targetHeight = height;

			        // Try to find an size match aspect ratio and size
			        for (Size size : cSize) {
			            double ratio = (double) size.width / size.height;
			            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
			                continue;
			            if (Math.abs(size.height - targetHeight) < minDiff) {
			                optimalSize = size;
			                minDiff = Math.abs(size.height - targetHeight);
			            }
			        }

			        // Cannot find the one match the aspect ratio, ignore the requirement
			        if (optimalSize == null) {
			            minDiff = Double.MAX_VALUE;
			            for (Size size : cSize) {
			                if (Math.abs(size.height - targetHeight) < minDiff) {
			                    optimalSize = size;
			                    minDiff = Math.abs(size.height - targetHeight);
			                }
			            }
			        }
					
			        
					
					
					
					params.setPreviewSize(optimalSize.width,optimalSize.height);
	                c.setParameters(params);
				}
				c.startPreview();
			}
		}
		
		public void surfaceDestroyed(SurfaceHolder arg0) {
			c.stopPreview();
			c.release();
			c = null;
		}
	}
}