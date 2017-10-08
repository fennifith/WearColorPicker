WearColorPicker is lightweight color picker library for Android Wear.

## Screenshots
|Square Watch|Round Watch|
|--------|--------|
|![img](https://theandroidmaster.github.io/images/screenshots/WearColorPicker-Square.png)|![img](https://theandroidmaster.github.io/images/screenshots/WearColorPicker-Round.png)|

## Usage

### Setup
The Gradle dependency is available through jCenter, which is used by default in Android Studio. To add the module to your project, copy this line into the dependencies section of your Android Wear module's build.gradle file.

``` gradle
compile 'james.wearcolorpicker:wearcolorpicker:0.0.1'
```
Please note that this library can **only** be used on Android Wear devices; it will not function inside a regular Android app.

### Opening the Color Picker
To open the Color Picker Activity, you need to call 'startActivityForResult' like below.
``` java
startActivityForResult(new Intent(this, WearColorPickerActivity.class), requestCode);
```
In this snippet, `requestCode` is a unique integer greater than or equal to 0. As stated in [the great documents of wisdom](https://developer.android.com/reference/android/app/Activity.html#startActivityForResult(android.content.Intent,%20int)), it will be passed to `onActivityResult()` as an argument once the activity has been closed.

### Getting a Returned Value
To obtain the result of the Color Picker, you need to override `onActivityResult()` in your `Activity` like below.
``` java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == this.requestCode) {
        if (resultCode == RESULT_OK && data != null && data.hasExtra(WearColorPickerActivity.EXTRA_COLOR)) {
          int color = data.getIntExtra(WearColorPickerActivity.EXTRA_COLOR, Color.BLACK);
          //do something with the color value
        } else {
          //the color has not been changed - the color picker activity has been closed without pressing the 'done' button
        }
    }
}
```
In this snippet, `this.requestCode` should be replaced with the same integer passed to `startActivityForResult()` when opening the color picker. This is mainly used to differentiate between places where `startActivityForResult()` is called in your `Activity`.

### Setting a Default Color (optional)
It is possible to specify a default color for the color picker (before the user changes it) by passing it through the `Intent` like below.
``` java
Intent intent = new Intent(this, WearColorPickerActivity.class);
intent.putExtra(WearColorPickerActivity.EXTRA_COLOR, defaultColor);
startActivityForResult(intent, requestCode);
```
