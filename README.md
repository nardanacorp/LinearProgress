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

# Usage

# View Layout 

add below code on custom ( every view layout nameview.xml ) :

```

```

| Attributes | Value |
| ---------- | ----- |
| radiusall  | Integer |
| radiusTopLeft  | Integer |
| radiusTopRight  | Integer |
| radiusBottomLeft  | Integer |
| radiusBottomRight  | Integer |
| Progressbackground  | Color |
| ProgressToBackground  | Color |
| colortitle  | Color |
| ToProgress  | Integer |
| titleprogress  | String |
| titlesize  | Dimension ( dp,sp,.... )

# Functions

# Gets

| Functions Name | Function Description |
| :-------------: | :------------------: |
| getColorTitle | Get Color of Title |
| getRadiusAll | Get Radius all ( top left , top right , bottom right , bottom left ) corners |
| getTitleSize | Get Size of Title Text |
| getToProgress | Get Percent of Progress after sets |
| getBackgroundTo | Get Color of background Second Layout ( progress layout ) |
| getRadiusTopRight | Get Radius of Top Right Corener |
| getRadiusTopLeft | Get Radius of Top Left Corener |
| getRadiusBottomRight | Get Radius of Bottom Right Corener |
| getRadiusBottomLeft | Get Radius of Bottom Left Corener |
| getTypeFace | Get Type Face of Title Text for added font |
| getTitleProgress | Get Title of Progress |
| getBackgroundColor | Get Color of Background ( first layout ) |
| getLayoutWidth | Get Width of Layout |
| getLayoutHeight | Get Height of Layout |

# Sets

| Functions Name | Function Description |
| :-------------: | :------------------: |
| setColorTitle | Set Color of Title |
| setRadiusAll | Set Radius all ( top left , top right , bottom right , bottom left ) corners |
| setTitleSize | Set Size of Title Text |
| setToProgress | Set Percent of Progress after sets |
| setBackgroundTo | Set Color of background Second Layout ( progress layout ) |
| setRadiusTopRight | Set Radius of Top Right Corener |
| setRadiusTopLeft | Set Radius of Top Left Corener |
| setRadiusBottomRight | Set Radius of Bottom Right Corener |
| setRadiusBottomLeft | Set Radius of Bottom Left Corener |
| setTypeFace | Set Type Face of Title Text for added font |
| setTitleProgress | Set Title of Progress |
| setBackgroundColor | Set Color of Background ( first layout ) |
| setLayoutWidth | Set Width of Layout |
| setLayoutHeight | Set Height of Layout |

# Contact Us

Website : [nardana](http://nardana.ir/)

Email : nardanacorp@gmail.com
