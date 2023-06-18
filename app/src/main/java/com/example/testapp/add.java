package com.example.testapp;

import static android.app.Activity.RESULT_OK;

import static com.example.testapp.R.id.addb;
import static com.example.testapp.R.id.drop;
import static com.example.testapp.R.id.imagepick;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.testapp.R.id;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
//import com.theartofdev.edmodo.cropper.CropImage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add extends Fragment {
    String[] catagory = {"fast food", "Dryfruit", "backing food", "other"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;
    ProgressBar b;
    CircleImageView pick;
    Dialog d;

    TextInputLayout catagories;
    TextInputLayout title;
    TextInputLayout price;
    Uri result1;
    Button btnadd;
    Calendar calendar = Calendar.getInstance();
    //    ActivityMainBinding binding;
//    final int SELECT_IMAGE_CODE=1;
//    ActivityResultLauncher<String> tkPhoto;

//    public static final int CAMARA_REQUEST = 100;
////    public static final int STORAGE_REQUEST = 101;
//    String camaraPermission[];
//    String storagePermission[];

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public add() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add.
     */
    // TODO: Rename and change types and number of parameters
    public static add newInstance(String param1, String param2) {
        add fragment = new add();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add, container, false);
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        btnadd=(Button)view.findViewById(R.id.addb);
        catagories=view.findViewById(id.drop);
        title=view.findViewById(id.textInputLayout);
        price=view.findViewById(id.textInputLayout2);
        autoCompleteTextView=view.findViewById(R.id.autocompletetextView);
        adapter=new ArrayAdapter<String>(getContext(),R.layout.list_item,catagory);
        autoCompleteTextView.setAdapter(adapter);
        b=view.findViewById(id.progressBar);
        b.setVisibility(View.GONE);
        d=new Dialog(getContext());
        d.setContentView(R.layout.dialog_loading);
        pick=view.findViewById(imagepick);
        pick.setOnClickListener(V -> getcontent.launch("image/*") );
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(d.getWindow()!=null)
                {
                    d.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                d.show();
                uploadToFirebase();
            }
        });


        return view;
    }
    ActivityResultLauncher<String> getcontent=registerForActivityResult(new
                    ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if(result!=null)
                    {
                        pick.setImageURI(result);
                        result1=result;
                    }
                }
            });
    private void uploadToFirebase() {
          b.setVisibility(View.GONE);
//        d=new Dialog(getContext());
//        d.setTitle("adding.....");
//        d.show();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String currentTime = dateFormat.format(calendar.getTime());
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference root=db.getReference();
        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference uploader= storage.getReference("Image1"+new Random().nextInt(50));
        uploader.putFile(result1)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                b.setVisibility(View.GONE);
                                FirebaseDatabase db1=FirebaseDatabase.getInstance();
                                DatabaseReference root=db.getReference("Products");
                                DataHolder obj=new DataHolder(catagories.getEditText().getText().toString(),title.getEditText().getText().toString(),price.getEditText().getText().toString(),uri.toString(),currentTime);
                                root.child(title.getEditText().getText().toString()).setValue(obj);

//                               catagories.getEditText().setHint("Select Catagory");

                                title.getEditText().setText("");
                                price.getEditText().setText("");
//                                pick.setImageResource(R.drawable.ic_baseline_add_photo_alternate_24);
                                Toast.makeText(getContext(),"added",Toast.LENGTH_LONG).show();
                                d.dismiss();
                            }
                        });
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        float per=(100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
//                        d.setTitle("Uploaded : "+(int)per+"%");

                    }
                });
    }


}
