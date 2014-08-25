How enable Robospock?
=====================

Robospock is a great framework for unit test in Android. It's a combination of Spock and Robolectric.

Once Google has release official plugin for Gradle few projects (including mine) were developed to bind Robolectric and Java plugin.
 
Since it's quick complex task which requires a lot of edge cases and Android plugin compatibility I decided to write bunch of code which allways work.
  

Project contains 2 modules - one for production Application and one for unit tests. This partition is necessary because Android plugin doesn't depend on Java plugin
 which leads to compilation errors.
 
This solution works only for running unit test from CLI.

getstarted module
=================
is a simple Android application module. The only change I've introduces is a wrapper of compiled classes

``` java
task zip2jar(type: Zip, dependsOn: "compileReleaseJava") {
    from "build/intermediates/classes/release"
    destinationDir = file("build/libs")
    extension = "jar"
}
```

The tasks packs all compiled files to a single jar. The archive is later used in Test Module.

Please also check AndoridManifest.xml file.

``` xml
    <uses-sdk android:minSdkVersion="16" />
```

Robolectric 2.3 requires that Application is at least a Ice Cream Sandwich one. Still many applications do support older SDKs. Luckily this value can be rewritten in build.gradle

``` java
android {
    compileSdkVersion 19
    buildToolsVersion "19.1.0"

    defaultConfig {
        minSdkVersion 16
        targetSdkVersion 19
    }
}
```
[Link to Application buildscript](getstarted/build.gradle)

getstarted-test module
======================

Here comes all the magic. The buildscript looks at first quite complex. Let's check it step by step.

Google local maven repository
-----------------------------

Maven by default downloads all archives in $HOME/.m2/repository directory but Android SDK doesn't.
It stores all archives in another path and fortunately using the same naming strategy.

``` java
def props = new Properties()
file("../local.properties").withInputStream {
    stream -> props.load(stream)
}
```

Instead of relying on ANDROID_HOME environment variable we can reuse what Android plugin produces. 

Since we know the path let the buildscript now where to find additional archives.
``` java
repositories {
    maven {
        url new File(props["sdk.dir"] + "/extras/android/m2repository/").toURI()
    }
}
```

Dependencies
------------

Java plugin doesn't understand `compile project(":androidproject")` dependency. 
We need to manually inform project about all required files.
 
``` java
dependencies {
    ...
    
    compile project(":getstarted")
    
    compile fileTree(dir: project(":getstarted").file("build/libs"), include: "*.jar")

    // jars from aar
    compile fileTree(dir: project(":getstarted").file("build/intermediates/exploded-aar/com.android.support/appcompat-v7/20.0.0"), include: ["*.jar"])
    compile fileTree(dir: project(":getstarted").file("build/intermediates/exploded-aar/com.android.support/support-v4/20.0.0"), include: ["*.jar"])
}
```
First compile dependency is a link to Application project. It's not necessary for running the tests but makes Android Studio happyt.

Second statement is link to previously generated jar.

Third statement is link to all jar which came from aar dependency. 
Once again Java plugin doesn't understand them and we need to provide the paths. 

Android Manifest and Resources
------------------------------

Starting from Robolectric 2.3 it's possible to pass paths for AndroidManifest.xml, resources and assets.
``` java
test {
    systemProperty "android.manifest"   ,project(":getstarted").file("src/main/AndroidManifest.xml")
    systemProperty "android.resources"  ,project(":getstarted").file("build/intermediates/res/release")
    systemProperty "android.assets"     ,project(":getstarted").file("build/intermediates/assets/release")    
}
```

Build types and flavours
------------------------

For presentation purposes I've chosen the Release build. Feel free to change it do Debug.
If your project contain flavours please mind different paths structure.



[Link to Test module buildscript](getstarted-test/build.gradle)