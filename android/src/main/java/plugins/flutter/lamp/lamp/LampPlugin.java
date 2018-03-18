package plugins.flutter.lamp.lamp;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * LampPlugin
 */
public class LampPlugin implements MethodCallHandler {

    private LampPlugin(Registrar registrar) {
        this._registrar = registrar;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
           this. _torch = new CameraTorch();
        } else {
            this._torch = new Camera2Torch(registrar.context());
        }
    }

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "github.com/clovisnicolas/flutter_lamp");
        channel.setMethodCallHandler(new LampPlugin(registrar));
    }

    private Torch _torch;
    private Registrar _registrar;

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        switch(call.method){
            case "turnOn":
                this._torch.toggle(true);
                result.success(null);
                break;
            case "turnOff":
                this._torch.toggle(false);
                result.success(null);
                break;
            case "hasLamp":
                result.success(hasLamp());
                break;
            default:
                result.notImplemented();
        }
    }

    private boolean hasLamp() {
        return _registrar.context().getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

}
