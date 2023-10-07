package com.hanaahany.foodplannerapp.auth.account;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.filterbycategory.view.CategoryMealsFragmentArgs;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class EditProfileFragment extends Fragment {

    CircleImageView circleImageViewProfile;
    TextInputEditText textInputEditTextUserName;
    MaterialButton materialButtonSave;
    String userName;
    String image;
    Uri pickImage;
    NavController navController;
    private final StorageReference storageReference= FirebaseStorage.getInstance().getReference();
    private final FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    LottieAnimationView lottieAnimationViewLoading;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        if (getArguments()!=null){
            String image=EditProfileFragmentArgs.fromBundle(getArguments()).getImageUser();
            String user=EditProfileFragmentArgs.fromBundle(getArguments()).getUserName();
            textInputEditTextUserName.setHint(user);
            Picasso.get().load(image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(circleImageViewProfile);
        }
        onClicks();
    }

    private void onClicks() {
        circleImageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
        materialButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottieAnimationViewLoading.setVisibility(View.VISIBLE);
                userName=textInputEditTextUserName.getText().toString().trim();
                updateImage();
            }
        });
    }

    private void updateUI() {

        lottieAnimationViewLoading.setVisibility(View.INVISIBLE);
        navController.navigate(R.id.action_editProfileFragment_to_accountFragment);
    }

    private void initViews() {
        circleImageViewProfile=getView().findViewById(R.id.circle_image_edit_profile);
        materialButtonSave=getView().findViewById(R.id.btn_save_edit);
        textInputEditTextUserName=getView().findViewById(R.id.et_user_name_edit_profile);
        navController= Navigation.findNavController(getActivity(),R.id.nav_host_fragment_home);
        lottieAnimationViewLoading=getView().findViewById(R.id.lotti_edit);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK&&data!=null){
            pickImage=data.getData();
            circleImageViewProfile.setImageURI(pickImage);
        }
    }
    private void updateImage(){
        storageReference.child("Photos")
                .child(firebaseAuth.getCurrentUser().getUid())
                .putFile(pickImage)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){
                            getImageFromStorage();
                        }else {
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void getImageFromStorage(){
        storageReference.child("Photos")
                .child(firebaseAuth.getCurrentUser().getUid())
                .getDownloadUrl()
                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            image=task.getResult().toString();
                            UpdateInfo();
                        }else {
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private void UpdateInfo(){
        firebaseFirestore.collection("Users")
                .document(firebaseAuth.getCurrentUser().getUid())
                .update("userName",userName,"imageUser",image)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                            updateUI();

                        }else{
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}