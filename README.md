# AdCountView
an simple CountView for advertisement

![](http://ww4.sinaimg.cn/large/a2f7c645jw1fcti0d34kog204t048myd.gif)
![](http://ww1.sinaimg.cn/large/a2f7c645jw1fcti0iluarg204t048q3y.gif)

![](http://ww1.sinaimg.cn/large/a2f7c645jw1fcti0oal4mg20a60a5td7.gif)

## Step 1

### Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
## Step 2
### Add the dependency

	dependencies {
	         compile 'com.github.flyou:AdCountView:1.0.4'
	}

## Step 3

###　Input in your xml
 
 			<com.flyou.AdCountView
            android:id="@+id/ad_count_view1"
            android:layout_width="80dp"
            android:layout_height="80dp"

            />

###　Or some attribute

    <com.flyou.AdCountView

            android:id="@+id/ad_count_view1"
            app:text="enter app"
            app:textColor="@color/base_white_50"
            app:textSize="16dp"
            app:backgroundColor="@color/base_black_50"
            app:outRingColor="@color/colorAccent"
            android:layout_width="80dp"
            android:layout_height="80dp"

            />

## Step 4

 		adCountView1 = (AdCountView) findViewById(R.id.ad_count_view1);
        adCountView1.start();

### Or more configure
      	adcountview1 = (AdCountView) findViewById(R.id.ad_count_view2);
        adcountview1.setbackgroundColor(getResources().getColor(R.color.base_white_80));
        adcountview1.setOutRingColor(getResources().getColor(R.color.colorAccent));
        adcountview1.setTextClor(getResources().getColor(R.color.base_black_100));
        adcountview1.setText("进入应用");
        adcountview1.start();

### Or
        adcountview3 = (AdCountView) findViewById(R.id.ad_count_view3);
        adcountview3.setbackgroundColor(getResources().getColor(R.color.base_black_30));
        adcountview3.setOnClickListener(this);
        adcountview3.setOnStatusChangeListener(this);
        adcountview3.setOutRingColor(getResources().getColor(R.color.lightseagreen));
        adcountview3.setTextClor(getResources().getColor(R.color.base_white_100));
        adcountview3.setText("跳过广告");
        adcountview3.setTextSize(15);
        adcountview3.start();


----------
## Instructions

- **setText()** 	set text of view show
- **setTextColor()** set text color
- **setTextSize()** set text size
- **setTime()** set animation time
- **setBackgroundColor()** set circle background color
- **setOutRingColor()** set out ring color
- **setOnStatusChangeListener()** anim start or stop listener
- **setInverseAnim()** anim run in inverse

----------

**Contact me**

blog: [flyou.ren](http://flyou.ren)<br/>
email:  [email me](mailto:fangjaylong@gmail.com)

**License**

AdCountView is under the Apache2.0 license. See the [LICENSE](https://github.com/flyou/AdCountView/blob/master/LICENSE) file for details.

 