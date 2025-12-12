Android Studio导入
---------

```
//project build.gradle:
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
        ...
        }
}

//app build.gradle:
dependencies {
    implementation 'com.github.OSAILab:CateringInterface:{latest}'
}
```

使用
---------

```
CateringService cateringService = new CateringService(this,cateringServiceCallback);

CateringServiceCallback cateringServiceCallback = new CateringServiceCallback.Stub() {
        @Override
        public void onShow() throws RemoteException {
            //浮窗显示时触发
        }

        @Override
        public void onHide() throws RemoteException {
            //浮窗隐藏时触发
        }

        @Override
        public void onOutputProduct(String sku) throws RemoteException {
            //输出商品是触发
        }
    };

cateringService.show();//显示浮窗
cateringService.hide();//隐藏浮窗
cateringService.addProduct(sku,price,name);//添加商品
cateringService.deleteProduct(sku);//删除商品
cateringService.clearProduct();//清空商品
cateringService.unbind();//解绑服务
```
