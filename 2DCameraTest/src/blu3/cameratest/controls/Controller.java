package blu3.cameratest.controls;

import blu3.cameratest.renderer.Camera;
import blu3.cameratest.renderer.Renderer;

public class Controller {

	public static boolean walk = false;

	public void tick(boolean forward, boolean back, boolean left, boolean right, boolean mouseDown) {
		if(forward) {
			Camera.addOffsetY(-1);
			walk = true;
		}
		
		if(back) {
			Camera.addOffsetY(1);
			walk = true;
		}
		
		if(left) {
			Camera.addOffsetX(-1);
			walk = true;
		}
		
		if(right) {
			Camera.addOffsetX(1);
			walk = true;
		}

		if (!forward && !back && !left && !right) {
			walk = false;
		}
	}

}