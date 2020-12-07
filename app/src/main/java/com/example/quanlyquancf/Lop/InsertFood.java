package com.example.quanlyquancf.Lop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quanlyquancf.DoiTuong.Food;
import com.example.quanlyquancf.DoiTuong.TableBan;
import com.example.quanlyquancf.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;

public class InsertFood extends AppCompatActivity {

    DatabaseReference food,foodfire;
    ArrayList<Food> arrFood=new ArrayList<>();

    EditText txtName, txtPrice , txtDiscount;
    Button btnXacNhan,btnChonHinh, btnUpHinh;
    ImageView imgA;
    public String urlImage;
    int lengthList;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;
    Uri FilePathUri;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_food);

        storageReference = FirebaseStorage.getInstance().getReference();
        food = FirebaseDatabase.getInstance().getReference();
        foodfire =  FirebaseDatabase.getInstance().getReference("Food");

        GetAllFood(new InsertFood.FirebaseCallBack() {
            @Override
            public void onCallBack(ArrayList<Food> list) {
                arrFood = list;
                //     SetTable();
                AnhXa();
            }
        });
        AnhXa();
        btnChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), Image_Request_Code);

            }
        });
        btnUpHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UpFirebase();
            }
        });
    }
    @Exclude
    private void UpFirebase()
    {
        Food f = new Food("Rỗng",txtDiscount.getText().toString().trim(),
                String.valueOf(lengthList + 1).trim(),
                "-1",
                urlImage,
                txtName.getText().toString().trim(),
                Long.valueOf(txtPrice.getText().toString().trim()));

        foodfire.child(String.valueOf(lengthList + 1).trim()).setValue(f);
        Toast.makeText(getApplicationContext(),"Thêm món thành công",Toast.LENGTH_SHORT).show();
    }
    private  void AnhXa()
    {
        progressDialog = new ProgressDialog(InsertFood.this);
        txtName =  findViewById(R.id.txtTen);
        txtPrice = findViewById(R.id.txtGia);
        txtDiscount = findViewById(R.id.txtGiam);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnChonHinh = findViewById(R.id.btnAddImg);
        imgA = findViewById(R.id.image_view);
        btnUpHinh = findViewById(R.id.btnUp);
        lengthList = arrFood.size();
    }
    private void GetAllFood(final InsertFood.FirebaseCallBack firebaseCallBack)
    {

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {

                    Food tam = ds.getValue(Food.class);
                    arrFood.add(tam);
                }
                firebaseCallBack.onCallBack(arrFood);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Lỗi kết nối mạng",Toast.LENGTH_SHORT).show();
            }
        };
        food.child("Food").addListenerForSingleValueEvent(valueEventListener);
    }

    // Upload Image to Storage
    public void UploadImage() {
        UploadTask uploadTask ;
        if (FilePathUri != null) {

            progressDialog.setTitle("Đang upload ảnh...");
            progressDialog.show();
            final StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
//            storageReference2.putFile(FilePathUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            progressDialog.dismiss();
//                         //   urlImage = "https://firebasestorage.googleapis.com/v0/b/logincf-2f273.appspot.com/o/" +taskSnapshot.getStorage().getName()+"?alt=media&token=7e54dcf8-7a4b-425f-b8d0-86dd52f0e750";
//                        }
//                    });

            uploadTask = storageReference2.putFile(FilePathUri);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }


                    return storageReference2.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        progressDialog.dismiss();
                        urlImage = downloadUri.toString();
                    } else {
                        // Handle failures
                        // ...
                    }
                }
            });
        }
        else {

            Toast.makeText(InsertFood.this, "Vui lòng chọn ảnh", Toast.LENGTH_LONG).show();

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imgA.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }
    private interface FirebaseCallBack{

        void onCallBack(ArrayList<Food> list);
    }
}