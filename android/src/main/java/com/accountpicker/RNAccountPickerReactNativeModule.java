
package com.accountpicker;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class RNAccountPickerReactNativeModule extends ReactContextBaseJavaModule {

    public static final String MODULE_NAME = "RNAccountPickerReactNative";
    private PromiseWrapper promiseWrapper;
    public static final int RC_ACCOUNT_PICK = 9001;
    public static final String ASYNC_OP_IN_PROGRESS = "ASYNC_OP_IN_PROGRESS";
    public static final String PLAY_SERVICES_NOT_AVAILABLE = "PLAY_SERVICES_NOT_AVAILABLE";
    public static final String ACCOUNT_RESULT_CANCELED = "ACCOUNT_RESULT_CANCELED";

    public RNAccountPickerReactNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        promiseWrapper = new PromiseWrapper();
        reactContext.addActivityEventListener(new RNActivityEventListener());
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("ACCOUNT_TYPE", AccountManager.KEY_ACCOUNT_TYPE);
        constants.put("ACCOUNT_NAME", AccountManager.KEY_ACCOUNT_NAME);
        return constants;
    }


    private class RNActivityEventListener extends BaseActivityEventListener {

        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
            if (requestCode == RC_ACCOUNT_PICK) {
                if (resultCode == RESULT_OK) {
                    WritableMap writableMap = new WritableNativeMap();
                    writableMap.putString(AccountManager.KEY_ACCOUNT_TYPE, intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE));
                    writableMap.putString(AccountManager.KEY_ACCOUNT_NAME, intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME));
                    promiseWrapper.resolve(writableMap);
                } else if (resultCode == RESULT_CANCELED) {
                    promiseWrapper.reject(ACCOUNT_RESULT_CANCELED, "request cancelled by user");
                }
            }
        }
    }


    @ReactMethod
    public void getEmail(Promise promise) {
        final Activity activity = getCurrentActivity();

        if (activity == null) {
            promise.reject(MODULE_NAME, "activity is null");
            return;
        }

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if (status != ConnectionResult.SUCCESS) {
            promise.reject(PLAY_SERVICES_NOT_AVAILABLE, "Play services not available");
        }

        boolean wasPromiseSet = promiseWrapper.setPromiseWithInProgressCheck(promise);
        if (!wasPromiseSet) {
            rejectWithAsyncOperationStillInProgress(promise);
            return;
        }

        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"},
                        false, null, null, null, null);
                activity.startActivityForResult(intent, RC_ACCOUNT_PICK);
            }
        });
    }

    private void rejectWithAsyncOperationStillInProgress(Promise promise) {
        promise.reject(ASYNC_OP_IN_PROGRESS, "cannot set promise - some async operation is still in progress");
    }
}