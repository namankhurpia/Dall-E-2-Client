package com.namankhurpia.imagegen.utils;

import android.os.AsyncTask;

import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;

public class RetrieveTask extends AsyncTask<String,Void,String> {
    @Override
    public String doInBackground(String... strings) {

        OpenAiService service = new OpenAiService(strings[1]);

        System.out.println("\nCreating Image...");
        CreateImageRequest request = CreateImageRequest.builder()
                .prompt(strings[0])
                .build();

        System.out.println("\nImage is located at:");
        return service.createImage(request).getData().get(0).getUrl();
    }
}
