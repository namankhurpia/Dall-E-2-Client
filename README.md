# Dall-E-Client
An android client for Dall E API by openAI

Download for [PlayStore](https://play.google.com/store/apps/details?id=com.namankhurpia.openaiapi)


DALL·E 2 is an AI system that can create realistic images and art from a description in natural language.

## STRUCTURE

The stucture of app looks like this -

ACTIVITIES -

- HomeActivity.java (allows users to choose options like settings, create image with dall, aboutus)
- Settings.java (allows users to add their own keys in sharedPreferences)
- LandingPage.java (does nothing mainly - shows a page for 2 seconds)
- MainActivity.java (The main activity that calls the openai - api)
- AboutUs.java (A little desciption about myself)

UTILITY FUNCTION -

- DownloadImageTask.java (Downloads the image using the Asynchronous Task)
- checkForInternet.java (Checks for active internet connection before making a request)
- RetrieveTask.java (Asynchronous Task for fetching results)

## USAGE
- if you want to add the same logic in your code -

git clone the repo using

```
git clone https://github.com/namankhurpia/Dall-E-2-Client
```

### STEP-1
Copy the RetrieveTask.java file from -
```
https://github.com/namankhurpia/Dall-E-2-Client/blob/main/app/src/main/java/com/namankhurpia/openaiapi/RetrieveTask.java

```

it should look like this -

```
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

```

### STEP-2

Now Make sure to call the main retrieve function in a new thread, main thread won't support and might result in ANRs

```
new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                RetrieveTask task = new RetrieveTask();
                res = task.doInBackground(prompt, open_ai_key);
                new DownloadImageTask((ImageView) findViewById(R.id.image)).execute(res);
            }
            catch(OpenAiHttpException e)
            {
                e.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally {
                //your logic
            }

        }

    }).start();
```

Congratulations!, you're all set!


<img src="https://raw.githubusercontent.com/namankhurpia/Dall-E-Client/main/mockups/Android%20Large%20-%201.png?token=GHSAT0AAAAAABZ35CX7OCM6IF6MSCDYZV5EZAYYXCQ" width="360" height="640">

<img src="https://raw.githubusercontent.com/namankhurpia/Dall-E-2-Client/main/mockups/Android%20Large%20-%202.png" width="360" height="640">

<img src="https://raw.githubusercontent.com/namankhurpia/Dall-E-2-Client/main/mockups/Android%20Large%20-%203.png" width="360" height="640">

<img src="https://raw.githubusercontent.com/namankhurpia/Dall-E-2-Client/main/mockups/Android%20Large%20-%204.png" width="360" height="640">

<img src="https://raw.githubusercontent.com/namankhurpia/Dall-E-2-Client/main/mockups/Android%20Large%20-%206.png" width="360" height="640">

<img src="https://raw.githubusercontent.com/namankhurpia/Dall-E-2-Client/main/mockups/Android%20Large%20-%207.png" width="360" height="640">

<img src="https://raw.githubusercontent.com/namankhurpia/Dall-E-2-Client/main/mockups/Android%20Large%20-%208.png" width="360" height="640">



