package com.project.sample.data.remote;

import com.project.sample.data.model.TextToSpeech;
import com.project.sample.data.model.TextToSpeechResponse;
import com.project.sample.data.model.TranslationResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    static DataRepository dataRepository;
    public static DataRepository getInstance(){
        if (dataRepository == null){
            dataRepository = new DataRepository();
        }
        return dataRepository;
    }

    private final ApiService apiService;
    public DataRepository(){ apiService = new ApiProvider().createService(ApiService.class); }


    public static Call<TextToSpeechResponse> textToSpeechModelCall = null;

    public MutableLiveData<TextToSpeechResponse> getTextToSpeech(TextToSpeech body) {
        MutableLiveData<TextToSpeechResponse> textToSpeechLiveData = new MutableLiveData<>();

        if (textToSpeechModelCall != null)
            textToSpeechModelCall.cancel();

        textToSpeechModelCall = apiService.getTextToSpeech("https://content-texttospeech.googleapis.com/v1/text:synthesize?alt=json&key=AIzaSyAa8yy0GdcGPHdtD083HiGGx_S0vMPScDM",
                body);
        textToSpeechModelCall.enqueue(new Callback<TextToSpeechResponse>() {
            @Override
            public void onResponse(Call<TextToSpeechResponse> call, Response<TextToSpeechResponse> response) {
                if (response.isSuccessful()){
                    textToSpeechLiveData.postValue(response.body());
                } else {
                    textToSpeechLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<TextToSpeechResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    textToSpeechLiveData.postValue(null);
                }
            }
        });
        return textToSpeechLiveData;
    }

    public static Call<TranslationResponse> translationResponseModelCall = null;

    public MutableLiveData<TranslationResponse> getTranslation(String q, String tr){
        MutableLiveData<TranslationResponse> translationData = new MutableLiveData<>();

        if (translationResponseModelCall != null)
            translationResponseModelCall.cancel();

        List<String> dt = new ArrayList<String>(){{
            add("t");
            add("rmt");
            add("bd");
            add("rms");
            add("qca");
            add("ss");
            add("md");
            add("ld");
            add("ex");}};

        translationResponseModelCall = apiService.getTranslation("https://translate.google.com/translate_a/single",
                "it", dt,2,1,q,"UTF-8","UTF-8","en","auto,", tr);

        translationResponseModelCall.enqueue(new Callback<TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslationResponse> call, Response<TranslationResponse> response) {
                if (response.isSuccessful()){
                    translationData.postValue(response.body());
                } else {
                    translationData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<TranslationResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    translationData.postValue(null);
                }
            }
        });

        return translationData;
    }

    public void cancelTextToSpeechRequest() {
        if (textToSpeechModelCall != null)
            textToSpeechModelCall.cancel();
    }

    public void cancelTranslationRequest() {
        if (translationResponseModelCall != null)
            translationResponseModelCall.cancel();
    }

}
