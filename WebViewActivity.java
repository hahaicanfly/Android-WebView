package com.akira.advenceui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity {

	private Context context;
	WebView mWebview = null;
	private String url = "https://www.google.com.tw/";
	
//	用WebView 打開一個 Google 首頁

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_layout);
		context = this;
		new Thread(new ProgressRunnable(context)).start();

		// 初始化元件
		mWebview = (WebView) findViewById(R.id.mWebview);
		mWebview.setWebViewClient(mWebViewClient);
		mWebview.loadUrl(url);

		// 畫面支援縮放
		mWebview.getSettings().setSupportZoom(true);
		// 多點觸控縮放
		mWebview.getSettings().setBuiltInZoomControls(true);
		// 標題顯示進度
		mWebview.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				setTitle("Loading..." + progress + "%");
				setProgress(progress * 100);
				if (progress == 100) {
					setTitle(R.string.finish);
					Toast.makeText(context, "網頁載入完成", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	WebViewClient mWebViewClient = new WebViewClient() {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	};

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
