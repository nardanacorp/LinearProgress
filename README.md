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
		implementation 'com.github.nardanacorp:LinearProgress:1.0.4'
	}
```

# Usage

# View Layout 

add below code on custom ( every view layout nameview.xml ) :

```
        <ir.nardana.linearprogressbar.LinearProgressBar
            android:id="@+id/LinearProgressBar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:ProgressToBackground="@color/AppColor" />
```

Added below custom item to top tag 

| Attributes | Value |
| ---------- | ----- |
| app:RadiusAll  | Integer |
| app:RadiusTopLeft  | Integer |
| app:RadiusTopRight  | Integer |
| app:RadiusBottomLeft  | Integer |
| app:RadiusBottomRight  | Integer |
| app:ProgressBackground  | Color |
| app:ProgressToBackground  | Color |
| app:ColorTitle  | Color |
| app:ToProgressInt  | Integer |
| app:ToProgressPercent  | Float |
| app:TitleProgress  | String |
| app:TitleSize  | Dimension ( dp,sp,.... ) |
| app:BoxShadowSize  | Integer |
| app:BoxShadowSizeX  | Integer |
| app:BoxShadowSizeY  | Integer |
| app:BoxShadowColor  | Color |

# Coding Layout

After Casting to custom variable can access below function 

```
In Activity Main Body

val CustomVariableName = findViewById<LinearProgressBar>(R.id.LinearProgressBar)

In Custom View Or Fragment Or Inflate View

val CustomVariableName = viewname.findViewById<LinearProgressBar>(R.id.LinearProgressBar)

CustomVariableName.setTitleProgress("Downloading Media ")
CustomVariableName.setToProgress(20)

```

# Set TypeFace ( Font )

For setting custom typeface , first create typeface and second set to function.

Create Directory Name ( Font ) under 'res' directory and put custom font into /res/font/customfontname.ttf

```

val typeface = ResourcesCompat.getFont(this.context,R.font.customfontname)
CustomVariableName.setTypeFace(typeface)

```

# Functions

# Gets

| Functions Name | Function Description |
| :-------------: | :------------------: |
| getColorTitle | Get Color of Title |
| getRadiusAll | Get Radius all ( top left , top right , bottom right , bottom left ) corners |
| getTitleSize | Get Size of Title Text |
| getToProgressInt | Get Integer of Progress after sets |
| getToProgressPercent | Get Percent of Progress after sets |
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
| getBoxShadowColor | Get Shadow of Layout Color |
| getBoxShadowSize | Get Shadow of Layout Size |
| getBoxShadowSizeX | Get Shadow of Layout Size in DX |
| getBoxShadowSizeY | Get Shadow of Layout Size in DY |

# Sets

| Functions Name | Function Description |
| :-------------: | :------------------: |
| setColorTitle | Set Color of Title |
| setRadiusAll | Set Radius all ( top left , top right , bottom right , bottom left ) corners |
| setTitleSize | Set Size of Title Text |
| setToProgressInt | Set Integer of Progress |
| setToProgressPercent | Set Percent of Progress |
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
| setBoxShadowColor | Set Shadow of Layout Color |
| setBoxShadowSize | Set Shadow of Layout Size |
| setBoxShadowSizeX | Set Shadow of Layout Size in DX |
| setBoxShadowSizeY | Set Shadow of Layout Size in DY |

# Contact Us

Website : [nardana](http://nardana.ir/)

Email : nardanacorp@gmail.com
