package com.namankhurpia.openaiapi;

import android.os.AsyncTask;
import android.os.Build;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;

public class RetrieveTask extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... strings) {
        String token = System.getenv("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");
        OpenAiService service = new OpenAiService("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");

        System.out.println("\nCreating completion...");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("ada")
                .prompt("Somebody once told me the world is gonna roll me")
                .echo(true)
                .user("testing")
                .n(3)
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
        }

        System.out.println("\nCreating Image...");
        CreateImageRequest request = CreateImageRequest.builder()
                .prompt(strings[0])
                .build();

        System.out.println("\nImage is located at:");
        //System.out.println(service.createImage(request).getData().get(0).getUrl());
        return service.createImage(request).getData().get(0).getUrl();
    }
}
