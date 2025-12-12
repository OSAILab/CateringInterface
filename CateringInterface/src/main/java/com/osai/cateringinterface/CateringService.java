package com.osai.cateringinterface;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.osai.catering.CateringServiceCallback;
import com.osai.catering.CateringServiceInterface;

public class CateringService extends CateringServiceInterface.Stub{

    private CateringServiceInterface cateringServiceInterface;
    private final CateringServiceCallback cateringServiceCallback;
    private final Context context;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            cateringServiceInterface = CateringServiceInterface.Stub.asInterface(service);
            try {
                cateringServiceInterface.setCallback(cateringServiceCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public CateringService(Context context, CateringServiceCallback cateringServiceCallback) {
        this.cateringServiceCallback = cateringServiceCallback;
        this.context = context;

        Intent intent = new Intent("com.osai.catering.service.action");
        intent.setComponent(new ComponentName("com.osai.catering","com.osai.catering.ui.PopupService"));
        context.startService(intent);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void unbind(){
        context.unbindService(serviceConnection);
    }


    @Override
    public void show() throws RemoteException {
        if(cateringServiceInterface != null)
            cateringServiceInterface.show();
    }

    @Override
    public void hide() throws RemoteException {
        if(cateringServiceInterface != null)
            cateringServiceInterface.hide();
    }

    @Override
    public void addProduct(String sku, String price, String name) throws RemoteException {
        if(cateringServiceInterface != null)
            cateringServiceInterface.addProduct(sku, price, name);
    }

    @Override
    public void deleteProduct(String sku) throws RemoteException {
        if(cateringServiceInterface != null)
            cateringServiceInterface.deleteProduct(sku);
    }

    @Override
    public void clearProduct() throws RemoteException {
        if(cateringServiceInterface != null)
            cateringServiceInterface.clearProduct();
    }

    @Override
    public void setCallback(CateringServiceCallback callback) throws RemoteException {
        if(cateringServiceInterface != null)
            cateringServiceInterface.setCallback(callback);
    }
}
