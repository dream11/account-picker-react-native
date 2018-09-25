import {NativeModules, Platform} from 'react-native';

const {RNAccountPickerReactNative} = NativeModules;

const IS_IOS = Platform.OS === 'ios';

class AccountPicker {
    async getEmail() {
        if (IS_IOS) {
            return Promise.reject(new Error('"NOT_SUPPORTED","Api is not support"'))
        }
        return RNAccountPickerReactNative.getEmail();
    }
}

export const accountPicker = new AccountPicker();