package com.l.oa1e;

import android.app.*;
import android.os.*;
import android.content.*;
import android.net.*;
import java.io.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.main);
	    Intent inte=getIntent();
		Uri u=inte.getData();
		CharSequence path=u.getPath();
		File f=new File((String)path);
		if(path.toString().endsWith(".apk.1")){
		try
		{
		copyFile(f.getAbsoluteFile(),new File(Environment.getExternalStorageDirectory(),"temp.apk"));
		install(new File(Environment.getExternalStorageDirectory(),"temp.apk").getAbsolutePath());
		}
		catch (Exception e) {}
		finish();
		}}
	private void install(String filePath) {
	File apkFile = new File(filePath);
	Intent intent = new Intent(Intent.ACTION_VIEW);
	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	
	intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
	startActivity(intent);
    }
	public void copyFile(File fromFile,File toFile) throws IOException{
	FileInputStream ins = new FileInputStream(fromFile);
	FileOutputStream out = new FileOutputStream(toFile);
	byte[] b = new byte[1024];
	int n=0;
	while((n=ins.read(b))!=-1){
	out.write(b, 0, n);
	}

	ins.close();
	out.close();
    }
}
