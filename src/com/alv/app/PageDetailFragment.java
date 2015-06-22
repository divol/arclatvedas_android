package com.alv.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.alv.lists.MainContent;
import com.alv.app.R;

/**
 * A fragment representing a single Page detail screen.
 * This fragment is either contained in a {@link PageListActivity}
 * in two-pane mode (on tablets) or a {@link PageDetailActivity}
 * on handsets.
 */
public class PageDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private MainContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PageDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = MainContent.MAIN_ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            
            this.getActivity().setTitle(mItem.content);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_detail, container, false);
        
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
           // ((TextView) rootView.findViewById(R.id.page_detail)).setText(mItem.content);
            WebView myWebView = (WebView) rootView.findViewById(R.id.webview);
            myWebView.setWebViewClient(new WebViewClient(
//            		public boolean shouldOverrideUrlLoading(WebView view, String url){
//            			return false;
//            		}
            		));
        	WebSettings webSettings = myWebView.getSettings();
        	myWebView.setInitialScale(0);
        	webSettings.setBuiltInZoomControls(true);
        	webSettings.setUseWideViewPort(true);
        	webSettings.setLoadWithOverviewMode(true);
        	webSettings.setJavaScriptEnabled(true);//necessaire pour flickr

            myWebView.loadUrl(mItem.strurl);
        }

        return rootView;
    }
}
