package com.namankhurpia.openaiapi;

import android.os.AsyncTask;
import android.os.Build;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;

public class RetrieveTask extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... strings) {

        OpenAiService service = new OpenAiService(strings[1]);

        System.out.println("\nCreating Image...");
        CreateImageRequest request = CreateImageRequest.builder()
                .prompt(strings[0])
                .build();

        System.out.println("\nImage is located at:");
        return service.createImage(request).getData().get(0).getUrl();
    }
}
