package com.itheima.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.itheima.alipay.AlipayRemoteService;

public class MainActivity extends AppCompatActivity {
    EditText et;
    private AlipayRemoteService alipayRemoteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);

        //创建一个隐式意图，用于启动《支付宝》中的 Service
        Intent intent = new Intent();
        intent.setAction("com.itheima.alipay");
        intent.setPackage("com.itheima.alipay");


//绑定远程服务
        boolean bindService = bindService(intent, new MyConnection(),
                Context.BIND_AUTO_CREATE);
        if (bindService) {
            Toast.makeText(this, "服务绑定成功", Toast.LENGTH_SHORT).show();
            System.out.println("服务绑定成功");
        }else {
            Toast.makeText(this, "服务绑定失败", Toast.LENGTH_SHORT).show();
            System.out.println("服务绑定失败");
        } }

    public void pay(View view){
        float money = Float.valueOf(et.getText().toString());
        try {
            alipayRemoteService.forwardPayMoney(money);
        } catch (RemoteException e) {
            e.printStackTrace();
            Toast.makeText(this, "付款失败", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "成功转账："+money+"元！", Toast.LENGTH_SHORT).show();
    }
    class MyConnection implements ServiceConnection {
        /**
         * 通过 Stub 的静态方法 asInterface 将 IBinder 对象转化为本地
         AlipayRemoteService 对象
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder
                service) {
            System.out.println("服务已经连接。。。");
            alipayRemoteService = AlipayRemoteService.Stub.asInterface(service);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("服务已经关闭。");
        } }
}
