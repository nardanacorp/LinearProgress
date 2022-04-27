# LinearProgress

This Class uses for Progress contains two layout , first layout is background and origin and second layout is top of first layout and progress from left to right and 0 to 100 percent and one view for show text given.

# How Setup

added this to root build.gradle ( build.gradle contains classpath or root build.gradle )

```python
allprojects {
    repositories {
        .........
        maven { url 'https://jitpack.io' }
    }
}
```

and added below code on build.gradle.app ( build.gradle contains implemention library )

```python
dependencies {
		implementation 'com.github.nardanacorp:LinearProgress:1.0.0'
	}
```
