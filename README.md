# Internet Connection Status Observer

An Android project that observes the device's internet connection status. This tool can:

Detect when the device's hardware (WiFi or mobile network) is unavailable.

Identify when the device is connected to a WiFi network but does not have actual internet access.

## Project Structure

The project consists of two modules:

Application Module (app): A demo application showcasing the usage of the internetconnectionobserver module.

Android Module (internetconnectionobserver): Implements the core functionality of internet status observation through interfaces and classes. This module can be used as a dependency in the app module or any other Android module.

## Features

Real-Time Monitoring: Continuously monitors internet connection changes.

Hardware Status Detection: Tracks the availability of WiFi and mobile network hardware.

Internet Availability Check: Verifies internet connectivity even when connected to WiFi.

Lightweight and Efficient: Optimized for minimal resource usage.

## Permissions

Add the necessary permissions to your AndroidManifest.xml:

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
````

## Contributing

Contributions are welcome! Please fork this repository and submit a pull request with your improvements or suggestions.

## Contact

For questions or feedback, feel free to reach out via [Email] (amirmohamad.shirin98@gmail.com).

