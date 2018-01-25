# wheel
实现日期的三级联动选择。<br>
## 效果展示
![image](https://github.com/neverream/WheelTime/blob/master/wheeltime.png)
#  实现方式

implementation 'com.android.neverream:WheelTime:1.0.2'

# 使用方法
    
     ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(this, 当前显示的年, 当前显示的年, 当前显示的年);
     mChangeBirthDialog.showAtLocation(点击显示选择器的控件（例：TextView）, Gravity.BOTTOM, 0, 0);
     mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {
            @Override
            public void onClick(String year, String month, String day) {
                
                //点击确定之后获取的年月日 year-mouth-day

            }
     });
