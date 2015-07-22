package com.alv.app;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alv.db.materiel.Materiel;
import com.alv.app.R;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link ArrowEditFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link MaterielEditFragment#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class MaterielEditFragment extends DialogFragment implements OnClickListener {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "materiel";

	// TODO: Rename and change types of parameters
	private Materiel mParam1;
    private View baseview;
    private ImageView mImageView;
	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment ArrowEditFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static MaterielEditFragment newInstance(Materiel param1) {
		MaterielEditFragment fragment = new MaterielEditFragment();
		Bundle args = new Bundle();
		args.putParcelable(ARG_PARAM1, param1);
		fragment.setArguments(args);
		return fragment;
	}

	public MaterielEditFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = (Materiel)getArguments().getParcelable(ARG_PARAM1);
		}
	}
	
	
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);

		android.support.v4.app.Fragment fr = getTargetFragment();
		
		
		System.out.println(fr.toString());
		
		
	    setTargetFragment(null, -1);

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		 baseview  = inflater.inflate(R.layout.fragment_materiel_edit, container, false);
		
		 
		 mImageView =(ImageView)baseview.findViewById(R.id.imageMateriel);

		 Button b = (Button) baseview.findViewById(R.id.okbutton);
	     b.setOnClickListener(this);
	     
	     b = (Button) baseview.findViewById(R.id.cancelbutton);
	     b.setOnClickListener(this);
	     
	     
	     b = (Button) baseview.findViewById(R.id.buttonPhoto);
	     b.setOnClickListener(this);
	     
	     
	     
	     if (mParam1 != null){
	     
	    	 EditText text = (EditText)baseview.findViewById(R.id.EditTextName);
	    	 text.setText(mParam1.getName());
	    	 
	    	 text = (EditText)baseview.findViewById(R.id.editTextSerialNumber);
	    	 
	    	 text.setText(""+mParam1.getSerialNumber());
	    	 
	    	 
	    	 text = (EditText)baseview.findViewById(R.id.editTextDate);
	    	 text.setText(""+mParam1.getDateAchat());

	    	 text = (EditText)baseview.findViewById(R.id.editTextComment);
	    	 text.setText(""+mParam1.getComment());
	    	 
	    	 
	    	 setPic(mParam1.getImagePath());
	     }
	        

	     
		return baseview;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onClick(View view) {
		
		
		 switch (view.getId()) {
		 case R.id.okbutton:
			 if (getTargetFragment() != null) {
				 
		    	 EditText text = (EditText)baseview.findViewById(R.id.EditTextName);
		    	 mParam1.setName(text.getText().toString());
		    	 
		    	 text = (EditText)baseview.findViewById(R.id.editTextSerialNumber);
		    	 mParam1.setSerialNumber(text.getText().toString());
		    	 
		    	 
		    	 text = (EditText)baseview.findViewById(R.id.editTextDate);
		    	 mParam1.setDateAchat(text.getText().toString());
		    	 
		    	 text = (EditText)baseview.findViewById(R.id.editTextComment);
		    	 mParam1.setComment(text.getText().toString());
		    	 OnFragmentInteractionListener<Materiel> target; 
		    	 
		    	 target =   ((OnFragmentInteractionListener<Materiel>) getTargetFragment());
		    	
		    	 target.onFragmentInteraction(mParam1);
		    	 this.dismiss();
				}
			 break;
		 case R.id.cancelbutton:
			 this.dismiss();
			 break;
		 case R.id.buttonPhoto:
			 
			 dispatchTakePictureIntent();
		 break;
		 }
		 
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
//		try {
//			mListener = (OnFragmentInteractionListener) activity;
//		} catch (ClassCastException e) {
//			throw new ClassCastException(activity.toString()
//					+ " must implement OnFragmentInteractionListener");
//		}
		
		
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}



// photo management
	
	String mCurrentPhotoPath;

	private File createImageFile() throws IOException {
	    // Create an image file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String imageFileName = "JPEG_" + timeStamp + "_";
	    File storageDir = Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_PICTURES);
	    File image = File.createTempFile(
	        imageFileName,  /* prefix */
	        ".jpg",         /* suffix */
	        storageDir      /* directory */
	    );

	    // Save a file: path for use with ACTION_VIEW intents
	   // mCurrentPhotoPath = "file:" + image.getAbsolutePath();
	    mCurrentPhotoPath = image.getAbsolutePath();
	    return image;
	}
	

	static final int REQUEST_TAKE_PHOTO = 101;

	private void dispatchTakePictureIntent() {
		
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    // Ensure that there's a camera activity to handle the intent
	    if (takePictureIntent.resolveActivity(this.getActivity().getPackageManager()) != null) {
	        // Create the File where the photo should go
	        File photoFile = null;
	        try {
	            photoFile = createImageFile();
	        } catch (IOException ex) {
	        	ex.printStackTrace();
	        }
	        // Continue only if the File was successfully created
	        if (photoFile != null) {
	            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
	                    Uri.fromFile(photoFile));
	            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
	        }
	    }
	}
	
	private void setPic(String pathName) {
	    // Get the dimensions of the View
		if (!pathName.equalsIgnoreCase("")){
		    int targetW = mImageView.getWidth();
		    int targetH = mImageView.getHeight();
		    if (targetW ==0) targetW=142;
		    if (targetH ==0) targetH=142;
		    
	
		    // Get the dimensions of the bitmap
		    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		    bmOptions.inJustDecodeBounds = true;
		    BitmapFactory.decodeFile(pathName, bmOptions);
		    int photoW = bmOptions.outWidth;
		    int photoH = bmOptions.outHeight;
	
		    // Determine how much to scale down the image
		    int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
	
		    // Decode the image file into a Bitmap sized to fill the View
		    bmOptions.inJustDecodeBounds = false;
		    bmOptions.inSampleSize = scaleFactor;
		    bmOptions.inPurgeable = true;
	
		    Bitmap bitmap = BitmapFactory.decodeFile(pathName, bmOptions);
		    mImageView.setImageBitmap(bitmap);
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == REQUEST_TAKE_PHOTO){
			if (resultCode == Activity.RESULT_OK){
				setPic(mCurrentPhotoPath);
				if (mParam1 != null)
					mParam1.setImagePath(mCurrentPhotoPath);
			}
		}
	}

//	private void galleryAddPic() {
//	    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//	    File f = new File(mCurrentPhotoPath);
//	    Uri contentUri = Uri.fromFile(f);
//	    mediaScanIntent.setData(contentUri);
//	    this.getActivity().sendBroadcast(mediaScanIntent);
//	}

    
}
