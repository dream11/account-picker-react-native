using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Account.Picker.React.Native.RNAccountPickerReactNative
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNAccountPickerReactNativeModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNAccountPickerReactNativeModule"/>.
        /// </summary>
        internal RNAccountPickerReactNativeModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNAccountPickerReactNative";
            }
        }
    }
}
