
# account-picker-react-native

## Getting started

`$ npm install account-picker-react-native --save`

### Mostly automatic installation

`$ react-native link account-picker-react-native`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-account-picker-react-native` and add `RNAccountPickerReactNative.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNAccountPickerReactNative.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNAccountPickerReactNativePackage;` to the imports at the top of the file
  - Add `new RNAccountPickerReactNativePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-account-picker-react-native'
  	project(':react-native-account-picker-react-native').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-account-picker-react-native/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-account-picker-react-native')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNAccountPickerReactNative.sln` in `node_modules/react-native-account-picker-react-native/windows/RNAccountPickerReactNative.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Account.Picker.React.Native.RNAccountPickerReactNative;` to the usings at the top of the file
  - Add `new RNAccountPickerReactNativePackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNAccountPickerReactNative from 'react-native-account-picker-react-native';

// TODO: What to do with the module?
RNAccountPickerReactNative;
```
  
