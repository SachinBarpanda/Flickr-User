package com.example.flickruser;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Photo_Detail_Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        activiteToolbar(true);

        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra(PHOTO_TRANSFER);
        if(photo != null){
            TextView photo_title = (TextView) findViewById(R.id.photo_Title);
            Resources resources = getResources();
            String text = resources.getString(R.string.photo_title_text,photo_title);
            photo_title.setText(text);

//            photo_title.setText("Title : "+photo.getTitle());

            TextView photoTags = (TextView) findViewById(R.id.photo_tags);
//            photoTags.setText("Tags : "+ photo.getTags());
            String text1 = resources.getString(R.string.photo_tags_text,photoTags);
            photo_title.setText(text1);

            TextView photoAuthor = findViewById(R.id.photo_author);
            photoAuthor.setText("Author : "+ photo.getAuthor());

            ImageView photoimage = findViewById(R.id.photo_Image);
            Picasso.get().load(photo.getLink())
                    .error(R.drawable.ic_image_black_24dp)
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .into(photoimage);


        }
    }

}
