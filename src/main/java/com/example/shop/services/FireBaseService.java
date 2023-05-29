package com.example.shop.services;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

@Service
public class FireBaseService {
    private Storage store = StorageOptions.getDefaultInstance().getService();

    public String save(MultipartFile multipartFile)  throws Exception{
        String imageName = String.valueOf(System.currentTimeMillis());
        //bucket on firebase
        BlobId blobId = BlobId.of("kirylshop.appspot.com", imageName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(multipartFile.getContentType())
                .build();
        store = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials
                        .fromStream(new FileInputStream("kirylshop-firebase-adminsdk-l8ix6-ad08ad2bec.json")))
                .build()
                .getService();
        store.create(blobInfo, multipartFile.getInputStream());
        return imageName;

    }

    public String getUrl(String fileName) {
return "https://firebasestorage.googleapis.com/v0/b/kirylshop.appspot.com/o/" + fileName + "?alt=media&token=4fe30b74-6836-4f4b-ad0f-0dd24dfadd96";
    }
}
