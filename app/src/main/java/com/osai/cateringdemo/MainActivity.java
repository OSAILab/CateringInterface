package com.osai.cateringdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import com.osai.catering.CateringServiceCallback;
import com.osai.cateringinterface.CateringService;

public class MainActivity extends Activity {

    CateringService cateringService;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = findViewById(R.id.textView);

        cateringService = new CateringService(this,cateringServiceCallback);
    }

    private CateringServiceCallback cateringServiceCallback = new CateringServiceCallback.Stub() {
        @Override
        public void onShow() throws RemoteException {

        }

        @Override
        public void onHide() throws RemoteException {

        }

        @Override
        public void onOutputProduct(String sku) throws RemoteException {
            textView.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    textView.append(sku);
                    textView.append("\n");
                }
            });
        }
    };

    public void show(View view) {
        try {
            cateringService.show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void hide(View view) {
        try {
            cateringService.hide();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    int sku = 0;

    public void add(View view) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char ch = (char) (0x4E00 + (int)(Math.random() * (0x9FA5 - 0x4E00 + 1)));
            sb.append(ch);
        }
        try {
            cateringService.addProduct(String.valueOf(++sku),String.valueOf(Math.random() * 10),sb.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void delete(View view) {
        if(sku == 0)
            return;
        try {
            cateringService.deleteProduct(String.valueOf(sku--));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void clear(View view) {
        try {
            cateringService.clearProduct();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        cateringService.unbind();
        super.onDestroy();
    }

}
