package com.project.sample.data.remote;

import com.dylogicapps.muslimquranpro.data.model.TextToSpeech;
import com.dylogicapps.muslimquranpro.data.model.TextToSpeechResponse;
import com.dylogicapps.muslimquranpro.data.model.TranslationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {
    @Headers({"User-Agent: GoogleTranslate"})
    @GET
    Call<TranslationResponse> getTranslation(@Url String url,
                                             @Query("client") String client,
                                             @Query("dt") List<String> dt,
                                             @Query("otf") int otf,
                                             @Query("dj") int dj,
                                             @Query("q") String q,
                                             @Query("ie") String ie,
                                             @Query("oe") String oe,
                                             @Query("hl") String hl,
                                             @Query("sl") String sl,
                                             @Query("tl") String tl);

    @Headers({"content-type: application/json",
            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36",
            "x-referer: https://explorer.apis.google.com/"})
    @POST
    Call<TextToSpeechResponse> getTextToSpeech(@Url String url, @Body TextToSpeech body);
}
